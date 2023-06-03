package com.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Bean.EmployeeBean;
import com.Bean.LeaveBean;
@Repository
public class LeaveDao {
	
	@Autowired
	JdbcTemplate stmt;


	public void saveleave(LeaveBean lb) {
		// TODO Auto-generated method stub
		System.out.println("I am here");
		stmt.update(
				"insert into leave (emp_id,department_name,full_day_leave,half_day_leave,medical_leave,start_date,end_date,isapproved) values (?,?,?,?,?,?,?,?)",
				lb.getEmp_id(),lb.getDepartment_name(),lb.getFull_day_leave(),lb.getHalf_day_leave(),lb.getMedical_leave(),lb.getStart_date(),lb.getEnd_date(),lb.isIsapproved());
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
	
	public List<LeaveBean> getAllLeaves() {

		return stmt.query("select * from leave", new BeanPropertyRowMapper<LeaveBean>(LeaveBean.class));
	}


	


	
}
