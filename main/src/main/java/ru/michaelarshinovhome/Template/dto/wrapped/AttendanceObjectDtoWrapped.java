package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;
import java.util.List;

import ru.michaelarshinovhome.Template.dto.AttendanceObjectDto;

public class AttendanceObjectDtoWrapped extends Wrapper<AttendanceObjectDto> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1298450772912215630L;

	public AttendanceObjectDtoWrapped(List<AttendanceObjectDto> attendanceObjectDto) {
		this.setSuccess(true);
		this.setMessage("Alert_GetOrCreateAttendanceObjectFromCacheAsync_info");
		this.setSnackbarType("info");
		this.setContent(attendanceObjectDto);
	}
}
