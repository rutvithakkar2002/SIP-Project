package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.BasicSalaryBean;
import com.Bean.EmployeeBean;
import com.Dao.BasicsalaryDao;
import com.Dao.EmployeeDao;

@RestController
public class BasicsalaryController {

	@Autowired
	BasicsalaryDao basicSalarydao;

	@Autowired
	EmployeeDao empDao;

	@PostMapping("/depwisesalary/{emp_id}")
	public String saveEmployee(@PathVariable("emp_id") int empid,@RequestBody BasicSalaryBean bsb) {

	//	BasicSalaryBean bsb = new BasicSalaryBean();
		EmployeeBean eb = empDao.getemployeebyid(empid);

		System.out.println(eb.getFirst_name());

		if (eb != null) {
			bsb.setFirst_name(eb.getFirst_name());
			bsb.setEmp_id(eb.getEmp_id());

			bsb.setLast_name(eb.getLast_name());
			bsb.setDepartment_name(eb.getDepartment_name());
			//bsb.setBasic_salary(4500.33);
		
			System.out.println(bsb.getBasic_salary());
			basicSalarydao.saveSalarydepwise(bsb);
		}
		return "done";
	}

}
