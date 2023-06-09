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

		ld.saveleave(lb);

		System.out.println("leave type inserted!");

		return "EmployeeId not found";

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

	@GetMapping("/adminsideallleaves")
	public List<LeaveBean> getAllLeavesadminside() {
		List<LeaveBean> adminsideleaves = ld.getAllLeavesadmin();
		System.out.println("ALL Leaves are displayed at admin side");

		return adminsideleaves;
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
