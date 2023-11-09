package ru.michaelarshinovhome.Template.dto.wrapped;

import java.io.Serializable;

import lombok.Data;
import ru.michaelarshinovhome.Template.dto.AttendanceObjectDto;

@Data
public class AttendanceObjectDtoOneWrapped extends WrapperOne<AttendanceObjectDto> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9125759199884664744L;

	public AttendanceObjectDtoOneWrapped(AttendanceObjectDto attendanceObjectDto) {
		if (attendanceObjectDto == null) {
			this.setSuccess(false);
			this.setMessage("Alert_GetOrCreateAttendanceObjectFromCacheAsync_error");
			this.setSnackbarType("error");
			this.setContent(null);
		} else {
			this.setSuccess(true);
			this.setMessage("Alert_GetOrCreateAttendanceObjectFromCacheAsync_info");
			this.setSnackbarType("info");
			this.setContent(attendanceObjectDto);
		}
	}
}
