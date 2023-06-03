package com.Controller;

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
import com.Bean.LeaveBean;
import com.Dao.LeaveDao;

@RestController
public class LeaveController {

	@Autowired
	LeaveDao ld;
	
	@PostMapping("/leaves")
	public ResponseEntity<LeaveBean> saveLeaves(@RequestBody LeaveBean lb) {
		System.out.println(lb.getEmp_id());
		System.out.println(lb.getDepartment_name());
		System.out.println(lb.getFull_day_leave());
		System.out.println(lb.getHalf_day_leave());
		System.out.println(lb.getMedical_leave());
		System.out.println(lb.getStart_date());
		System.out.println(lb.getEnd_date());
		System.out.println(lb.isIsapproved());
		
		ld.saveleave(lb);

		System.out.println("leave type inserted!");

		ResponseEntity<LeaveBean> r = new ResponseEntity<LeaveBean>(lb, HttpStatus.OK);
		/*
		 * ResponseEntity<StudentBean> r = new
		 * ResponseEntity<StudentBean>(student,HttpStatus.ACCEPTED); return r;
		 */
		return r;
	}
	
	@DeleteMapping("leaves/{emp_id}")
	public String deleteLeave(@PathVariable("emp_id") int employeeid) {

		LeaveBean emp = ld.getleavebyid(employeeid);
		boolean flag = ld.deleteleavebyid(employeeid);

		if (flag) {
			return "leave request was removed";
		} else {
			return "something went wrong";
		}
		// System.out.println("Deleted Successfully");
		// List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		// return employees;

	}
	
	
	// list
		@GetMapping("/allleaves")
		public List<LeaveBean> getAllLeaves() {
			List<LeaveBean> leaves = ld.getAllLeaves();
			System.out.println("ALL Leave requests are displayed");
			return leaves;
		}

}
