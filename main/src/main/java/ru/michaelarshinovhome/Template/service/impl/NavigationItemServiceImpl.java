package ru.michaelarshinovhome.Template.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.michaelarshinovhome.Template.dao.NavigationItemRepository;
import ru.michaelarshinovhome.Template.dto.NavigationItemModelDto;
import ru.michaelarshinovhome.Template.model.NavigationItem;
import ru.michaelarshinovhome.Template.service.NavigationItemService;

@Service("navigationItemService")
public class NavigationItemServiceImpl implements NavigationItemService {
	@Autowired
	NavigationItemRepository repository;
	
	@Override
	@Transactional
	public List<NavigationItemModelDto> findAllUnwrapped() {
		List<NavigationItem> list = (List<NavigationItem>) repository.findAll();
		List<NavigationItemModelDto> ret = new ArrayList<>();
		if (list != null) {
			list.stream().forEach(x->ret.add(new NavigationItemModelDto(x)));
		}
		return ret;
	}

}
