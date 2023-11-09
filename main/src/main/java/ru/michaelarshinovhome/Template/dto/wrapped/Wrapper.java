package ru.michaelarshinovhome.Template.dto.wrapped;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Wrapper<T> {
	private List<T> content;
	private boolean success;
	private String message;
	private String snackbarType;
	@SuppressWarnings("rawtypes")
	public Wrapper forLogger() {
		return Wrapper.builder()
				.content(List.of())
				.success(this.success)
				.message(this.message)
				.snackbarType(this.snackbarType)
				.build();
	}
	public Wrapper() {}
}
