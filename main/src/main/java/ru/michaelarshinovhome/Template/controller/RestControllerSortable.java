package ru.michaelarshinovhome.Template.controller;

import java.lang.reflect.Field;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import liquibase.repackaged.org.apache.commons.lang3.StringUtils;

public interface RestControllerSortable {
	static final String DESC_SKIP_SIZE = "Количество записей передаваемых данных, которые пропускаются. Значение по умолчанию - 0.";
	static final int VAL_SKIP_SIZE = 0;
	static final String DESC_TAKE_SIZE = "Количество записей передаваемых данных, которые возвращаются. Значение по умолчанию - 20.";
	static final int VAL_TAKE_SIZE = 20;
	static final String DESC_PAGE_SIZE = "Количество записей передаваемых данных. Значение по умолчанию - 20.";
	static final int VAL_PAGE_SIZE = 20;
	static final String DESC_PAGE_NUMBER = "Номер страницы передаваемых данных. Значение по умолчанию - 1.";
	static final int VAL_PAGE_NUMBER = 1;
	
	static final String DESC_SORTING_ORDER = "Порядок, согласно которому осуществляется сортировка. "
			+ "Поля перечисляются через запятую. Допустимые значения: asc/desc. Например: asc,desc,desc.";
	static final String DESC_SORTING_FIELDS = " Поля перечисляются через запятую. "
	+ "Срабатывает для полей не имеющих в названии ключевое слово formatted. "
	+ "Пустые (null) поля идут в конце. Несуществующие поля игнорируются.";
	
	default boolean doesClassHaveAField(@SuppressWarnings("rawtypes") Class clazz, String field) {
		return List.of(clazz.getDeclaredFields()).stream().map(Field::getName).anyMatch(f->f.equals(field));
	}
	default Pageable getPageRequestWithSorting(int pageNumberVal, int pageSizeVal, 
			String fieldsToSortBy, String orders, Class clazz) {
		Pageable pageable = PageRequest.of(pageNumberVal, pageSizeVal);
		if (fieldsToSortBy != null && orders != null) {
			String[] fieldsVal = fieldsToSortBy.split(",");
			String[] ordersVal = orders.split(",");
			if (fieldsVal.length == ordersVal.length) {
				if (doesClassHaveAField(clazz, fieldsVal[0].trim())) {
					Sort sort = StringUtils.equalsIgnoreCase(ordersVal[0].trim(), "desc")
							? Sort.by(fieldsVal[0].trim()).descending()
							: Sort.by(fieldsVal[0].trim()).ascending();
					for (int i=1; i<fieldsVal.length; i++) {
						if (!doesClassHaveAField(clazz, fieldsVal[i].trim())) continue;
						Sort newsort = StringUtils.equalsIgnoreCase(ordersVal[i].trim(), "desc")
							? Sort.by(fieldsVal[i].trim()).descending()
							: Sort.by(fieldsVal[i].trim()).ascending();
						sort = sort.and(newsort);
					}
					pageable = PageRequest.of(pageNumberVal, pageSizeVal, sort);
				}
			}
		}
		return pageable;
	}
}
