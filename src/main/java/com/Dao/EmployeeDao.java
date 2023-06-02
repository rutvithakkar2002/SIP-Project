package com.Dao;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Bean.EmployeeBean;
import com.Bean.EmployeeLoginBean;




@Repository
public class EmployeeDao {

	@Autowired
	JdbcTemplate stmt;

	public void saveEmployee(EmployeeBean emp) {
		stmt.update(
				"insert into employee (first_name,last_name,gender,age,contact_no,email,address,city,state,pincode,joining_date,department_name,password) values (?,?,?,?,?,?,?,?,?,?,?,?,?)",
				emp.getFirst_name(), emp.getLast_name(), emp.getGender(), emp.getAge(), emp.getContact_no(),
				emp.getEmail(), emp.getAddress(), emp.getCity(), emp.getState(), emp.getPincode(),
				emp.getJoining_date(), emp.getDepartment_name(), emp.getPassword());
	}

	public List<EmployeeBean> getAllEmployees() {

		return stmt.query("select * from employee", new BeanPropertyRowMapper<EmployeeBean>(EmployeeBean.class));
	}

	public boolean deleteemployeebyid(int employeeid) {
		int i = stmt.update("delete from employee where emp_id=?", employeeid);
		if (i == 0) {
			return false;
		} else {
			return true;
		}

	}

	public EmployeeBean getemployeebyid(int employeeid) {
		return stmt.queryForObject("select * from employee where emp_id=?",
				new BeanPropertyRowMapper<EmployeeBean>(EmployeeBean.class), new Object[] { employeeid });
	}

	public boolean updateEmployee(EmployeeBean employee) {

		int i = stmt.update(
				"update employee set first_name=?,last_name=?,gender=?,age=?,contact_no=?,email=?,address=?,city=?,state=?,pincode=?,department_name=?,password=?",
				employee.getFirst_name(), employee.getLast_name(), employee.getGender(), employee.getAge(),
				employee.getContact_no(), employee.getEmail(), employee.getAddress(), employee.getCity(),
				employee.getState(), employee.getPincode(), employee.getDepartment_name(), employee.getPassword());

		if(i==1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	//this will check original employee table
	public EmployeeLoginBean getEmpByEmail(String email) {
		EmployeeLoginBean dbemp = null;
		try {
			System.out.println("I am here");
			dbemp = stmt.queryForObject("select * from employee where email = ? ",
					new BeanPropertyRowMapper<EmployeeLoginBean>(EmployeeLoginBean.class), new Object[] { email });
			
			System.out.println("email got");
		} catch (Exception e) {
			return null;
		}
		return dbemp;
	}
	
	
/*	public List<PgBean> getallpg() {
		List<PgBean> pg = stmt.query(
				"select p.*,u.firstname,u.lastname,s.timeduration,s.amount from pg p,users u,subscription s where p.userId=u.userId and s.subid=p.subid ",
				new BeanPropertyRowMapper<PgBean>(PgBean.class));
		return pg;
	}*/

	
	
	public void savelogin(EmployeeLoginBean emp) {
		stmt.update(
				"insert into emp_login(emp_id,first_name,last_name,status,login_time,email,password) values (?,?,?,?,?,?,?)",
				emp.getEmp_id(),emp.getFirst_name(),emp.getLast_name(),emp.isStatus(),emp.getLogin_time(),emp.getEmail(),emp.getPassword());
	}
	
	//This will check in emplogin table
	public EmployeeLoginBean getEmpBylogintable(String email) {
		EmployeeLoginBean dbemplogin = null;
		System.out.println("hello !!!!!!!");
		
		try {
			System.out.println("I am here");
			dbemplogin = stmt.queryForObject("select * from emp_login where email = ? and status = true",
					new BeanPropertyRowMapper<EmployeeLoginBean>(EmployeeLoginBean.class), new Object[] { email });
			
			System.out.println("email got");
		} catch (Exception e) {
			return null;
		}
		return dbemplogin;
	}
	
	
	
	
	
	

}