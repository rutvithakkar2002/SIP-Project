package com.Controller;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDateTime now = LocalDateTime.now();
			System.out.println(dtf.format(now));

			rb.setDateofreview(dtf.format(now));

			SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
			simpleformat = new SimpleDateFormat("MMMM");
			String currentMonth = simpleformat.format(new Date());
			System.out.println("Month in MMMM format = " + currentMonth);

			rb.setMonth(currentMonth);

			reviewDao.savereview(rb);
		} else {
			return "employee not found!";
		}

		return "done";
	}

	// list
	@GetMapping("/reviews")
	public List<ReviewBean> getAllEmployees() {
		List<ReviewBean> reviews = reviewDao.getAllReviews();
		System.out.println("ALL Reviews are displayed");
		return reviews;
	}

	@GetMapping("/viewreviewofempmonthly/{emp_id}/{month}")
	public ResponseEntity<ReviewBean> getreviewbyempidmonthwise(@PathVariable("emp_id") int empid,
			@PathVariable("month") String month) {
		System.out.println("I am here");
		ReviewBean rb = reviewDao.getReviewbyEmpidMonthwise(empid, month);

		ResponseEntity<ReviewBean> r = new ResponseEntity<ReviewBean>(rb, HttpStatus.OK);

		return r;

	}

	@GetMapping("/viewreviewofemp/{emp_id}")
	public ResponseEntity<List<ReviewBean>> getreviewbyempid(@PathVariable("emp_id") int empid) {
		List<ReviewBean> rb = reviewDao.getReviewbyEmpid(empid);

		for (ReviewBean reviewBean : rb) {

			reviewBean.getReview_id();
			reviewBean.getEmp_id();
			reviewBean.getFirst_name();
			reviewBean.getLast_name();
			reviewBean.getDepartment_name();
			reviewBean.getDateofreview();
			reviewBean.getRank();
			reviewBean.getDescription();
			reviewBean.getMonth();

		}

		return new ResponseEntity<List<ReviewBean>>(rb, HttpStatus.OK);

	}

	@DeleteMapping("deletereview/{review_id}")
	public String deleteReview(@PathVariable("review_id") int reviewid) {

		boolean i=reviewDao.deleteReview(reviewid);
		
		if(i==true)
		{
			return "delete successfully";
		}
		else
		{
			return "not deleted";
		}
	}

}