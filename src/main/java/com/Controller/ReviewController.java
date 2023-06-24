package com.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.EmployeeBean;
import com.Bean.ReviewBean;

import com.Dao.EmployeeDao;
import com.Dao.ReviewDao;
@RestController
public class ReviewController {

	@Autowired
	EmployeeDao empDao;

	@Autowired
	ReviewDao reviewDao;
	
	@PostMapping("reviewofemp/{emp_id}")
	public String saveReviews(@PathVariable("emp_id") int empid, @RequestBody ReviewBean rb) {

		EmployeeBean eb = empDao.getemployeebyid(empid);
		System.out.println("Employee Id Got");

		if (eb != null) {

			rb.setEmp_id(eb.getEmp_id());
			rb.setFirst_name(eb.getFirst_name());
			rb.setLast_name(eb.getLast_name());
			rb.setDepartment_name(eb.getDepartment_name());
			
			reviewDao.savereview(rb);
		}
		else
		{
			return "employee not found!";
		}
	
	return "done";
	}
}