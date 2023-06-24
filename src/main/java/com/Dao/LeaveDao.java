package com.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Bean.AdminsideLeaveBean;

import com.Bean.LeaveBean;
import com.Bean.TotalLeavesofEmp;

@Repository
public class LeaveDao {

	@Autowired
	JdbcTemplate stmt;

	public void saveleave(LeaveBean lb) {
		// TODO Auto-generated method stub
		System.out.println("I am here");
		stmt.update(
				"insert into leave (emp_id,department_name,first_name,last_name,full_day_leave,half_day_leave,medical_leave,start_date,end_date) values (?,?,?,?,?,?,?,?,?)",
				lb.getEmp_id(), lb.getDepartment_name(), lb.getFirst_name(), lb.getLast_name(), lb.getFull_day_leave(),
				lb.getHalf_day_leave(), lb.getMedical_leave(), lb.getStart_date(), lb.getEnd_date());
	}

	public LeaveBean getleavebyid(int employeeid) {
		return stmt.queryForObject("select * from leave where emp_id=?",
				new BeanPropertyRowMapper<LeaveBean>(LeaveBean.class), new Object[] { employeeid });
	}

	public boolean deleteleavebyid(int employeeid) {
		int i = stmt.update("delete from leave where emp_id=?", employeeid);
		if (i == 0) {
			return false;
		} else {
			return true;
		}

	}

	/*
	 * public List<LeaveBean> getAllLeaves() {
	 * 
	 * return stmt.query("select * from leave", new
	 * BeanPropertyRowMapper<LeaveBean>(LeaveBean.class)); }
	 */

	/*
	 * public List<LeaveBean> getAllLeavesadminside() {
	 * 
	 * return stmt.
	 * query("select leave_id,emp_id,department_name,full_day_leave,half_day_leave,medical_leave,start_date,end_date from leave"
	 * , new BeanPropertyRowMapper<LeaveBean>(LeaveBean.class)); }
	 */

	public List<LeaveBean> getAllLeavesadmin() {
		return stmt.query("select * from leave", new BeanPropertyRowMapper<LeaveBean>(LeaveBean.class));
	}

	public void saveleaveinemployeeside(AdminsideLeaveBean aslb) {
		// TODO Auto-generated method stub
		System.out.println("I am at admin here");
		stmt.update(
				"insert into leave (emp_id,department_name,first_name,last_name,full_day_leave,half_day_leave,medical_leave,start_date,end_date,is_Approved) values (?,?,?,?,?,?,?,?,?,?)",
				aslb.getEmp_id(), aslb.getDepartment_name(), aslb.getFirst_name(), aslb.getLast_name(),
				aslb.getFull_day_leave(), aslb.getHalf_day_leave(), aslb.getMedical_leave(), aslb.getStart_date(),
				aslb.getEnd_date(), aslb.isIsapproved());
	}

	public LeaveBean getleavebyleaveid(int leaveid) {
		return stmt.queryForObject("select * from leave where leave_id=?",
				new BeanPropertyRowMapper<LeaveBean>(LeaveBean.class), new Object[] { leaveid });

	}

	public void saveLeaveresponse(AdminsideLeaveBean aslb) {
		stmt.update(
				"insert into leave_admin (leave_id,emp_id,department_name,first_name,last_name,full_day_leave,half_day_leave,medical_leave,start_date,end_date,isapproved ) values (?,?,?,?,?,?,?,?,?,?,?)",
				aslb.getLeave_id(), aslb.getEmp_id(), aslb.getDepartment_name(), aslb.getFirst_name(),
				aslb.getLast_name(), aslb.getFull_day_leave(), aslb.getHalf_day_leave(), aslb.getMedical_leave(),
				aslb.getStart_date(), aslb.getEnd_date(), aslb.isIsapproved());

	}

	public List<AdminsideLeaveBean> getAllapprovedLeaves() {
		return stmt.query("select * from leave_admin",
				new BeanPropertyRowMapper<AdminsideLeaveBean>(AdminsideLeaveBean.class));
	}

	public List<AdminsideLeaveBean> gettotalLeavebyempid(int empid) {
		System.out.println("Hey i m here dear vidhi");
		return  stmt.query("select * from leave_admin where emp_id=?",
				new BeanPropertyRowMapper<AdminsideLeaveBean>(AdminsideLeaveBean.class), new Object[] { empid });

	}

	public void savetotalleaveperemp(TotalLeavesofEmp tle) {

		stmt.update(
				"insert into totalleaveofemp (emp_id,leave_id,full_day_leave,half_day_leave,medical_leave,total_leave ) values (?,?,?,?,?,?)",
				tle.getEmp_id(), tle.getLeave_id(), tle.getFull_day_leave(), tle.getHalf_day_leave(),
				tle.getMedical_leave(), tle.getTotal_leave());

	}

	/*
	 * public int getfulldayleaveperemp(int empid) { return stmt.
	 * queryForObject("select SUM(full_day_leave) from leave_admin where emp_id=?",
	 * new BeanPropertyRowMapper<Integer>(Integer.class), new Object[] { empid });
	 * 
	 * }
	 * 
	 * public int gethalfdayleaveperemp(int empid) { return stmt.
	 * queryForObject("select SUM(half_day_leave) from leave_admin where emp_id=?",
	 * new BeanPropertyRowMapper<Integer>(Integer.class), new Object[] { empid });
	 * 
	 * }
	 */

	/*
	 * public int getmedicalleaveperemp(int empid) { return stmt.
	 * queryForObject("select SUM(medical_leave) from leave_admin where emp_id=?",
	 * new BeanPropertyRowMapper<Integer>(Integer.class), new Object[] { empid });
	 * 
	 * }
	 */

	public Integer gethalfdayleaveperemp(int empid) {
		Integer hl = (Integer) stmt.queryForObject("select SUM(half_day_leave) from leave_admin where emp_id=? and 1=1 group by emp_id",
				new Object[] { empid }, Integer.class);
		return hl;

	}

	public Integer getfulldayleaveperemp(int empid) {
		Integer fl = (Integer) stmt.queryForObject("select SUM(full_day_leave) from leave_admin where emp_id=? and 1=1 group by emp_id",
				new Object[] { empid }, Integer.class);
		return fl;

	}

	public Integer getmedicalleaveperemp(int empid) {
		Integer ml = (Integer) stmt.queryForObject("select SUM(medical_leave) from leave_admin where emp_id=? and 1=1 group by emp_id",
				new Object[] { empid }, Integer.class);
		return ml;

	}

/*	public List<AdminsideLeaveBean> gettotalLeavebyleaveid(int leaveid) {
		System.out.println("Hey i m here dear vidhi");
		return  stmt.query("select * from leave_admin where leave_id=?",
				new BeanPropertyRowMapper<AdminsideLeaveBean>(AdminsideLeaveBean.class), new Object[] { leaveid });

	}*/

}
