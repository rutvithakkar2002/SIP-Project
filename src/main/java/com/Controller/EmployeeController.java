package com.Controller;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.EmployeeBean;
import com.Bean.EmployeeLoginBean;

import com.Dao.EmployeeDao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
public class EmployeeController {

	public static String getDate() {
		Date date = new Date();
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
		return s.format(date);
	}

	@Autowired
	EmployeeDao employeeDao;

	@Autowired
	BCryptPasswordEncoder bcryptPasswordEncoder;

	/*
	 * @Autowired MailService mailerService;
	 */

	// signup
	// new
	@PostMapping("Employee")
	public ResponseEntity<EmployeeBean> saveEmployee(@RequestBody EmployeeBean emp) {
		System.out.println(emp.getEmp_id());
		System.out.println(emp.getFirst_name());
		System.out.println(emp.getLast_name());
		System.out.println(emp.getEmail());
		System.out.println(emp.getContact_no());
		System.out.println(emp.getAddress());
		System.out.println(emp.getPassword());

		String plainPassword = emp.getPassword();
		String encPassword = bcryptPasswordEncoder.encode(plainPassword);
		System.out.println(encPassword);
		emp.setPassword(encPassword);

		String date = getDate();

		emp.setJoining_date(date);

		employeeDao.saveEmployee(emp);

		System.out.println("Employee inserted!");

		ResponseEntity<EmployeeBean> r = new ResponseEntity<EmployeeBean>(emp, HttpStatus.OK);
		/*
		 * ResponseEntity<StudentBean> r = new
		 * ResponseEntity<StudentBean>(student,HttpStatus.ACCEPTED); return r;
		 */
		return r;
	}

	// login

//	@PostMapping("emplogin")
//	public String authenticate(EmployeeBean emp)
//	@GetMapping("emplogin/{email}")
	/*
	 * public EmployeeBean authenticat(@PathVariable("email") String email)
	 * 
	 * { boolean incorrect=false; EmployeeBean
	 * dbEmp=employeeDao.getEmpByEmail(email);
	 * 
	 * if(dbEmp!=null) { if(bcryptPasswordEncoder.matches(emp.getPassword(),
	 * dbEmp.getPassword())==true) { incorrect=true;
	 * System.out.println("Welcome dear"+emp.getFirst_name()); } }
	 * 
	 * 
	 * // return "something went Wrong!!!"; return dbEmp; }
	 */

	@PostMapping("/emplogin")
	public String authenticate(@RequestBody EmployeeLoginBean emplogin) {
		boolean isCorrect = false;
		EmployeeLoginBean dbUser = employeeDao.getEmpByEmail(emplogin.getEmail());

		if (dbUser != null) {

			if (bcryptPasswordEncoder.matches(emplogin.getPassword(), dbUser.getPassword()) == true) {
				isCorrect = true;

				EmployeeLoginBean dbloginuser = employeeDao.getEmpBylogintable(emplogin.getEmail());
				System.out.println("after method");
				if (dbloginuser.isStatus()==false) {

					String date = getDate();
					emplogin.setLogin_time(date);
					emplogin.setStatus(true);
					emplogin.setEmp_id(dbUser.getEmp_id());
					emplogin.setFirst_name(dbUser.getFirst_name());
					emplogin.setLast_name(dbUser.getLast_name());

					System.out.println("Login Successfully");

					System.out.println("login time entry");

					System.out.println(emplogin.getEmp_id());
					System.out.println(emplogin.getLogin_time());

					employeeDao.savelogin(emplogin);
					return "Login Successfully";
				}
				else
				{
					return "You are Already Loged in";
				}

			} else {
				return "Your Password is incorrect";
			}
		}
		if (dbUser == null) {
			return "Invalid User with wrong emailId!";
		}

		return "Login Successfully";

	}

	
	@PostMapping("emplogout")
	public String employeeLogout(@RequestBody EmployeeLoginBean emp)
	{
		EmployeeLoginBean dblogoutuser = employeeDao.getEmpBylogintable(emp.getEmail());
		
		
		return "";
	}
	
	
	
	
	
	// list
	@GetMapping("/employees")
	public List<EmployeeBean> getAllEmployees() {
		List<EmployeeBean> employees = employeeDao.getAllEmployees();
		System.out.println("ALL Employees list are displayed");
		return employees;
	}

	// delete
	@DeleteMapping("employee/{emp_id}")
	public String deleteEmployee(@PathVariable("emp_id") int employeeid) {

		EmployeeBean emp = employeeDao.getemployeebyid(employeeid);
		boolean flag = employeeDao.deleteemployeebyid(employeeid);

		if (flag) {
			return "Employee was removed";
		} else {
			return "something went wrong";
		}
		// System.out.println("Deleted Successfully");
		// List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		// return employees;

	}

	@GetMapping("/Employee/{emp_id}")
	public EmployeeBean getEmployeeById(@PathVariable("emp_id") int empid) {
		EmployeeBean empBean = employeeDao.getemployeebyid(empid);
		return empBean;

	}

	@PutMapping("/employee")
	public EmployeeBean updateEmployee(@RequestBody EmployeeBean employee) {

		System.out.println(employee.getFirst_name());
		System.out.println(employee.getLast_name());
		System.out.println(employee.getEmail());
		System.out.println(employee.getPassword());

		employeeDao.updateEmployee(employee);

		System.out.println("employee Updated..");

		return employee;// object json

	}

}