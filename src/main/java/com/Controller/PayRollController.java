package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.BasicSalaryBean;
import com.Bean.EmployeeBean;
import com.Bean.PayRollBean;
import com.Dao.BasicsalaryDao;
import com.Dao.EmployeeDao;
import com.Dao.PayrollDao;

@RestController
public class PayRollController {

	@Autowired
	PayrollDao payrollDao;

	@Autowired
	EmployeeDao empDao;

	@Autowired
	BasicsalaryDao basicSalaryDao;

	@PostMapping("/payroll/{emp_id}")
	public String savePaySlip(@PathVariable("emp_id") int empid) {
		
		PayRollBean prb=new PayRollBean();
		
		EmployeeBean empbean = empDao.getemployeebyid(empid);
		BasicSalaryBean bsb = basicSalaryDao.getbasicSalarybyempId(empid);

		if (empbean != null) {
			System.out.println("Employee Got");
			prb.setEmp_id(empbean.getEmp_id());
			prb.setDepartment_name(empbean.getDepartment_name());
			prb.setFirst_name(empbean.getFirst_name());
			prb.setLast_name(empbean.getLast_name());
			prb.setBasic_salary(bsb.getBasic_salary());

			double da = (prb.getBasic_salary() * 10) / 100;
			prb.setDa(da);

			double hra = ((prb.getBasic_salary() + da) * 30) / 100;
			prb.setHra(hra);

			double total_salary = prb.getBasic_salary() + da + hra;
			prb.setTotal_salary(total_salary);

			payrollDao.savepayslip(prb);

			return "request inserted";
		} else {
			return "employeeId not found!";
		}

	}

}
