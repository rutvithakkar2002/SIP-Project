package com.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Bean.PayRollBean;

@Repository
public class PayrollDao {

	@Autowired
	JdbcTemplate stmt;

	public void savepayslip(PayRollBean pb) {
		stmt.update(
				"insert into payroll (emp_id,first_name,last_name,department_name,basic_salary,da,hra,total_salary)values(?,?,?,?,?,?,?,?)",
				pb.getEmp_id(), pb.getFirst_name(), pb.getLast_name(), pb.getDepartment_name(), pb.getBasic_salary(),
				pb.getDa(),pb.getHra(),pb.getTotal_salary());
	}

}
