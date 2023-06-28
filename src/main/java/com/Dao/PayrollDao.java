package com.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Bean.LeaveBean;
import com.Bean.PayRollBean;

@Repository
public class PayrollDao {

	@Autowired
	JdbcTemplate stmt;

	public void savepayslip(PayRollBean pb) {
		stmt.update(
				"insert into payroll (emp_id,first_name,last_name,department_name,basic_salary,da,hra,pf,total_leave,total_deduction,net_salary,total_salary)values(?,?,?,?,?,?,?,?,?,?,?,?)",
				pb.getEmp_id(), pb.getFirst_name(), pb.getLast_name(), pb.getDepartment_name(), pb.getBasic_salary(),
				pb.getDa(), pb.getHra(), pb.getPf(), pb.getTotal_leaves(), pb.getTotal_deduction(), pb.getNet_salary(),
				pb.getTotal_salary());
	}

	public PayRollBean getpayslipemployeebyid(int empid) {
		return stmt.queryForObject("select * from payroll where emp_id=?",
				new BeanPropertyRowMapper<PayRollBean>(PayRollBean.class), new Object[] { empid });

	}

}
