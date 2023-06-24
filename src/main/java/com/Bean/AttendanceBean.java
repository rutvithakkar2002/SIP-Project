package com.Bean;

public class AttendanceBean {

	private Integer attendence_id;
	private Integer emp_id;
	private String first_name;
	private String last_name;
	private String department_name;
	private String login_time;
	private String logout_time;
	private double total_hours;
	private String month;
	private Integer year;

	
	
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

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public String getLogin_time() {
		return login_time;
	}

	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}

	public String getLogout_time() {
		return logout_time;
	}

	public void setLogout_time(String logout_time) {
		this.logout_time = logout_time;
	}

	public double getTotal_hours() {
		return total_hours;
	}

	public void setTotal_hours(double total_hours) {
		this.total_hours = total_hours;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
	

}
