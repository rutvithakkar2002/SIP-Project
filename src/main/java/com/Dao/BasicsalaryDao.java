package com.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Bean.BasicSalaryBean;
import com.Bean.LeaveBean;

@Repository
public class BasicsalaryDao {

	@Autowired
	JdbcTemplate stmt;

	public void saveSalarydepwise(BasicSalaryBean bs) {
		// TODO Auto-generated method stub
		System.out.println("I am here");

		stmt.update("insert into salary (emp_id,department_name,first_name,last_name,basic_salary) values(?,?,?,?,?)",
				bs.getEmp_id(), bs.getDepartment_name(), bs.getFirst_name(), bs.getLast_name(), bs.getBasic_salary());

		System.out.println("request inserted successfully!");
	}

	public List<BasicSalaryBean> getAllDepartment() {

		System.out.println("department name");
		List<BasicSalaryBean> deptname = stmt.query("select department_name from salary",
				new BeanPropertyRowMapper<BasicSalaryBean>(BasicSalaryBean.class));
		System.out.println("department name got");
		System.out.println(deptname.size());
		for (int i = 0; i <= deptname.size(); i++) {
			System.out.println(deptname);
		}

		return deptname;
	}

	public BasicSalaryBean getbasicSalarybyempId(int empid) {

		return stmt.queryForObject("select basic_salary from salary where emp_id=?",
				new BeanPropertyRowMapper<BasicSalaryBean>(BasicSalaryBean.class), new Object[] { empid });

	}

}
