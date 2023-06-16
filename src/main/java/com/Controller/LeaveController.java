package com.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.AdminsideLeaveBean;
import com.Bean.EmployeeBean;
import com.Bean.LeaveBean;
import com.Dao.EmployeeDao;
import com.Dao.LeaveDao;

@RestController
public class LeaveController {

	@Autowired
	LeaveDao ld;

	@Autowired
	EmployeeDao employeeDao;

	double total_leave=0;
	
//	public ResponseEntity<LeaveBean> saveLeaves(@PathVariable("emp_id") int empid, @RequestBody LeaveBean lb) {
	@PostMapping("/leaves/{emp_id}")
	public String saveLeaves(@PathVariable("emp_id") int empid, @RequestBody LeaveBean lb) {
		EmployeeBean empBean = employeeDao.getemployeebyid(empid);

		if (empBean != null) {
			System.out.println("Employee got");

			lb.setEmp_id(empBean.getEmp_id());
			lb.setFirst_name(empBean.getFirst_name());
			lb.setLast_name(empBean.getLast_name());
			lb.setDepartment_name(empBean.getDepartment_name());

		}
		
		total_leave=lb.getFull_day_leave()+lb.getHalf_day_leave()+lb.getMedical_leave();
		lb.setTotal_leave(total_leave);
		
		ld.saveleave(lb);

		System.out.println("leave type inserted!");

		return "Employee request sent to the admin";

		/*
		 * System.out.println(lb.getEmp_id());
		 * System.out.println(lb.getDepartment_name());
		 * System.out.println(lb.getFull_day_leave());
		 * System.out.println(lb.getHalf_day_leave());
		 * System.out.println(lb.getMedical_leave());
		 * System.out.println(lb.getStart_date()); System.out.println(lb.getEnd_date());
		 */
		// System.out.println(lb.isIsapproved());

		/*
		 * ResponseEntity<LeaveBean> r = new ResponseEntity<LeaveBean>(lb,
		 * HttpStatus.OK); /* ResponseEntity<StudentBean> r = new
		 * ResponseEntity<StudentBean>(student,HttpStatus.ACCEPTED); return r;
		 * 
		 * return r;
		 */
	}

	@DeleteMapping("/leaves/{emp_id}")
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

	@GetMapping("/adminsideallleaves")
	public List<LeaveBean> getAllLeavesadminside() {
		List<LeaveBean> adminsideleaves = ld.getAllLeavesadmin();
		System.out.println("ALL Leaves are displayed at admin side");

		return adminsideleaves;
	}

	@PostMapping("leaveresponse/{emp_id}")
	public String saveLeavesresponse(@PathVariable("emp_id") int empid) {
		AdminsideLeaveBean aslb=new AdminsideLeaveBean();

		LeaveBean lb = ld.getleavebyempid(empid);
		System.out.println("Employee Id Got");

		if (lb != null) {
			aslb.setLeave_id(lb.getLeave_id());
			aslb.setEmp_id(lb.getEmp_id());
			aslb.setFirst_name(lb.getFirst_name());
			aslb.setLast_name(lb.getLast_name());
			aslb.setDepartment_name(lb.getDepartment_name());
			aslb.setStart_date(lb.getStart_date());
			aslb.setEnd_date(lb.getEnd_date());
			aslb.setFull_day_leave(lb.getFull_day_leave());
			aslb.setHalf_day_leave(lb.getHalf_day_leave());
			aslb.setMedical_leave(lb.getMedical_leave());
			aslb.setIsapproved(true);

			ld.saveLeaveresponse(aslb);
			return "save request";
		} else {
			return "Employee Not Found!";
		}
	}
	
	
	

	@GetMapping("/approvedleaves")
	public List<AdminsideLeaveBean> getAllapprovedLeavesempside() {
		List<AdminsideLeaveBean> empsideapprovedleaves = ld.getAllapprovedLeaves();
		System.out.println("ALL Approved Leaves are displayed at employee side");

		return empsideapprovedleaves;
	}

	
	
	
	
	
	

	// list
	/*
	 * @GetMapping("/allleaves") public List<LeaveBean> getAllLeaves() {
	 * List<LeaveBean> leaves = ld.getAllLeaves();
	 * System.out.println("ALL Leave requests are displayed"); return leaves; }
	 */

	/*
	 * @GetMapping("/adminsideallleaves") public List<LeaveBean>
	 * getAllLeavesadminside() { List<LeaveBean> adminsideleaves =
	 * ld.getAllLeavesadminside();
	 * System.out.println("ALL Leaves are displayed at admin side"); return
	 * adminsideleaves; }
	 */

}
