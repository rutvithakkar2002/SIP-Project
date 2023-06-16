package com.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Bean.AdminsideLeaveBean;

import com.Bean.LeaveBean;
@Repository
public class LeaveDao {
	
	@Autowired
	JdbcTemplate stmt;


	public void saveleave(LeaveBean lb) {
		// TODO Auto-generated method stub
		System.out.println("I am here");
		stmt.update(
				"insert into leave (emp_id,department_name,first_name,last_name,full_day_leave,half_day_leave,medical_leave,start_date,end_date,total_leave) values (?,?,?,?,?,?,?,?,?,?)",
				lb.getEmp_id(),lb.getDepartment_name(),lb.getFirst_name(),lb.getLast_name(),lb.getFull_day_leave(),lb.getHalf_day_leave(),lb.getMedical_leave(),lb.getStart_date(),lb.getEnd_date(),lb.getTotal_leave());
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
	
	/*public List<LeaveBean> getAllLeaves() {

		return stmt.query("select * from leave", new BeanPropertyRowMapper<LeaveBean>(LeaveBean.class));
	}*/


	/*public List<LeaveBean> getAllLeavesadminside() {
		
		return stmt.query("select leave_id,emp_id,department_name,full_day_leave,half_day_leave,medical_leave,start_date,end_date from leave", new BeanPropertyRowMapper<LeaveBean>(LeaveBean.class));
	}*/
	

	public List<LeaveBean> getAllLeavesadmin() {
		return stmt.query("select * from leave", new BeanPropertyRowMapper<LeaveBean>(LeaveBean.class));
	}
	
	public void saveleaveinemployeeside(AdminsideLeaveBean aslb) {
		// TODO Auto-generated method stub
		System.out.println("I am at admin here");
		stmt.update(
				"insert into leave (emp_id,department_name,first_name,last_name,full_day_leave,half_day_leave,medical_leave,start_date,end_date,is_Approved) values (?,?,?,?,?,?,?,?,?,?)",
				aslb.getEmp_id(),aslb.getDepartment_name(),aslb.getFirst_name(),aslb.getLast_name(),aslb.getFull_day_leave(),aslb.getHalf_day_leave(),aslb.getMedical_leave(),aslb.getStart_date(),aslb.getEnd_date(),aslb.isIsapproved());
	}


	public LeaveBean getleavebyempid(int empid) {
		return stmt.queryForObject("select * from leave where emp_id=?",
				new BeanPropertyRowMapper<LeaveBean>(LeaveBean.class), new Object[] { empid });
		
	}


	public void saveLeaveresponse(AdminsideLeaveBean aslb) {
		stmt.update(
				"insert into leave_admin (emp_id,department_name,first_name,last_name,full_day_leave,half_day_leave,medical_leave,start_date,end_date,isapproved ) values (?,?,?,?,?,?,?,?,?,?)",
				aslb.getEmp_id(),aslb.getDepartment_name(),aslb.getFirst_name(),aslb.getLast_name(),aslb.getFull_day_leave(),aslb.getHalf_day_leave(),aslb.getMedical_leave(),aslb.getStart_date(),aslb.getEnd_date(),aslb.isIsapproved());
		
	}


	public List<AdminsideLeaveBean> getAllapprovedLeaves() {
		return stmt.query("select * from leave_admin", new BeanPropertyRowMapper<AdminsideLeaveBean>(AdminsideLeaveBean.class));
		}

}
