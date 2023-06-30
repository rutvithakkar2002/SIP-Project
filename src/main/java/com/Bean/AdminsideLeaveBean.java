package com.Bean;

public class AdminsideLeaveBean {

	private Integer response_id;
	private Integer leave_id;
	private Integer emp_id;
	private String first_name;
	private String last_name;

	private String department_name;
	private Integer full_day_leave;
	private Integer half_day_leave;
	private Integer medical_leave;
	private String start_date;
	private String end_date;
	private String month;
	private String leave_description;
	private boolean isapproved;

	public Integer getResponse_id() {
		return response_id;
	}

	public void setResponse_id(Integer response_id) {
		this.response_id = response_id;
	}

	public Integer getLeave_id() {
		return leave_id;
	}

	public void setLeave_id(Integer leave_id) {
		this.leave_id = leave_id;
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

	public Integer getFull_day_leave() {
		return full_day_leave;
	}

	public void setFull_day_leave(Integer full_day_leave) {
		this.full_day_leave = full_day_leave;
	}

	public Integer getHalf_day_leave() {
		return half_day_leave;
	}

	public void setHalf_day_leave(Integer half_day_leave) {
		this.half_day_leave = half_day_leave;
	}

	public Integer getMedical_leave() {
		return medical_leave;
	}

	public void setMedical_leave(Integer medical_leave) {
		this.medical_leave = medical_leave;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public boolean isIsapproved() {
		return isapproved;
	}

	public void setIsapproved(boolean isapproved) {
		this.isapproved = isapproved;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getLeave_description() {
		return leave_description;
	}

	public void setLeave_description(String leave_description) {
		this.leave_description = leave_description;
	}

}
