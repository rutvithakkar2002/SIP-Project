package com.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import com.Bean.BasicSalaryBean;
import com.Bean.EmployeeBean;
import com.Bean.EmployeeLoginBean;
import com.Bean.ForgetPasswordBean;
import com.Dao.EmployeeDao;
import com.service.MailService;
import com.service.TokenGenerator;

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

	@Autowired
	TokenGenerator tokenGenerator;

	@Autowired
	MailService mailerService;

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

		if (emp.getDepartment_name().toUpperCase().equals("SALES")
				|| emp.getDepartment_name().toUpperCase().equals("HR")
				|| emp.getDepartment_name().toUpperCase().equals("FINANCE")
				|| emp.getDepartment_name().toUpperCase().equals("ACCOUNTING")
				|| emp.getDepartment_name().toUpperCase().equals("TRAINING")
				|| emp.getDepartment_name().toUpperCase().equals("DEVELOPER")
				|| emp.getDepartment_name().toUpperCase().equals("QA")
				|| emp.getDepartment_name().toUpperCase().equals("ITSERVICE")
				|| emp.getDepartment_name().toUpperCase().equals("PRODUCTION")
				|| emp.getDepartment_name().toUpperCase().equals("MANUFACTURING")) {
			
			emp.setStatus(true);
			
			employeeDao.saveEmployee(emp);

			System.out.println("Employee inserted!");
		} else {
			System.out.println("invalid departmentname");
		}

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
			System.out.println("dbuser got");
			if (bcryptPasswordEncoder.matches(emplogin.getPassword(), dbUser.getPassword()) == true) {
				isCorrect = true;

				EmployeeLoginBean dbloginuser = employeeDao.getEmpBylogintable(emplogin.getEmail());
				System.out.println("after method");
				// if (dbloginuser.isStatus()==false) {
				if (dbloginuser == null) {
					String date = getDate();
					emplogin.setLogin_time(date);

					// Calendar calendar = Calendar.getInstance();
					// System.out.println(calendar.get(Calendar.MONTH));

					emplogin.setStatus(true);
					emplogin.setEmp_id(dbUser.getEmp_id());
					emplogin.setFirst_name(dbUser.getFirst_name());
					emplogin.setLast_name(dbUser.getLast_name());

					SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
					simpleformat = new SimpleDateFormat("MMMM");
					String currentMonth = simpleformat.format(new Date());
					System.out.println("Month in MMMM format = " + currentMonth);
					emplogin.setMonth(currentMonth);

					Calendar cal = Calendar.getInstance();
					int year = cal.get(Calendar.YEAR);
					System.out.println(year);

					emplogin.setYear(year);

					System.out.println("Login Successfully");

					System.out.println("login time entry");

					System.out.println(emplogin.getEmp_id());
					System.out.println(emplogin.getLogin_time());

					String token = tokenGenerator.generateToken();
					System.out.println(token);

					emplogin.setToken(token);

					employeeDao.savelogin(emplogin);
					return "Login Successfully and Your logout token is" + token;
				} else {
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

	@GetMapping("/emplogout/{token}")
	public String employeeLogout(@PathVariable("token") String token) {

		System.out.println("Hello");

		EmployeeLoginBean dbemplogout = employeeDao.getEmpByToken(token);

		/*
		 * if (dbemplogout == null) { return "can't logout"; } else {
		 */

		String date = getDate();

		dbemplogout.setLogout_time(date);
		dbemplogout.setStatus(false);
		employeeDao.updatelogouttime(token, dbemplogout.setLogout_time(date));
		return "Logout Successfully";
		// }
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

	// Update Employee
	/*
	 * @PutMapping("/employee") public EmployeeBean updateEmployee(@RequestBody
	 * EmployeeBean employee) {
	 * 
	 * System.out.println(employee.getFirst_name());
	 * System.out.println(employee.getLast_name());
	 * System.out.println(employee.getEmail());
	 * System.out.println(employee.getPassword());
	 * 
	 * employeeDao.updateEmployee(employee);
	 * 
	 * System.out.println("employee Updated..");
	 * 
	 * return employee;// object json
	 * 
	 * }
	 */

	@PutMapping("/employee/{emp_id}")
	public EmployeeBean updateEmployee(@PathVariable("emp_id") int empid, @RequestBody EmployeeBean employee) {

		EmployeeBean emp = employeeDao.getemployeebyid(empid);
		System.out.println("Employee Got");
		System.out.println(employee.getFirst_name());
		System.out.println(employee.getLast_name());
		System.out.println(employee.getEmail());
		System.out.println(employee.getPassword());

		employeeDao.updateEmployee(employee);

		System.out.println("employee Updated..");

		return employee;// object json

	}

	// Forgot Password

	@PostMapping("/forgetpassword")
	public String forgetPassword(@RequestBody EmployeeBean emp) {

		EmployeeBean dbemp = employeeDao.getEmployeeByEmail(emp.getEmail());

		if (dbemp == null) {
			return "Invalid Mail!!";
		}

		else {

			int otp1 = (int) (Math.random() * 1000000);
			System.out.println(otp1);
			String otp = Integer.toString(otp1);

			emp.setOtp(otp);
			employeeDao.updateOtp(emp.getEmail(), otp);
			mailerService.sendMail(dbemp);
			return "Otp Sent on your email";

		}

	}

	@PostMapping("/updatepassword")
	public ResponseEntity updatePassword(@RequestBody ForgetPasswordBean fdto) {
		EmployeeBean emp = employeeDao.getEmployeeByEmail(fdto.getEmail());

		if (emp != null) {
			// db check
			if (emp.getOtp().equals(fdto.getOtp())) {
				// employeeDao.updateOtp(emp.getEmail(), "");
				String password = bcryptPasswordEncoder.encode(fdto.getPassword());
				employeeDao.updatePassword(emp.getEmail(), password);

				System.out.println("Password Updated");

			} else {
				System.out.println("You entered wrong otp!!!");
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fdto);
			}

			return ResponseEntity.ok(fdto);
		} else {
			System.out.println("employee not found!!");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fdto);
	}

	/*
	 * @PostMapping("/updatepassword") public String updatePassword(@RequestBody
	 * EmployeeBean emp) { System.out.println(emp.getEmail());
	 * System.out.println(emp.getOtp()); System.out.println(emp.getPassword());
	 * EmployeeBean dbUser = employeeDao.getEmployeeByEmail(emp.getEmail()); if
	 * (dbUser == null) { return "Invalid EMAIL";
	 * 
	 * } else { System.out.println("original otp = > " + dbUser.getOtp()); if
	 * (dbUser.getOtp().equals(emp.getOtp())) {
	 * employeeDao.updatePassword(emp.getEmail(), emp.getPassword()); return
	 * "Password successfully modified..."; // now try login with new password }
	 * else { return "Invalid OTP";
	 * 
	 * } }
	 * 
	 * }
	 */

}
