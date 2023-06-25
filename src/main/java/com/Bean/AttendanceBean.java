package com.Bean;

public class AttendanceBean {

	private Integer attendence_id;
	private Integer emp_id;

	private String login_time;
	private String logout_time;
	private String total_working_hours;
	private String month;

	public Integer getAttendence_id() {
		return attendence_id;
	}

	public void setAttendence_id(Integer attendence_id) {
		this.attendence_id = attendence_id;
	}

	public Integer getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(Integer emp_id) {
		this.emp_id = emp_id;
	}

	

	public String getLogin_time() {
		return login_time;
	}

	public String setLogin_time(String login_time) {
		return this.login_time = login_time;
	}

	public String getLogout_time() {
		return logout_time;
	}

	public String setLogout_time(String logout_time) {
		return this.logout_time = logout_time;
	}

	

	public String getTotal_working_hours() {
		return total_working_hours;
	}

	public void setTotal_working_hours(String total_working_hours) {
		this.total_working_hours = total_working_hours;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
