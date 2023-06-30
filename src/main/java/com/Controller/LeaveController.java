package com.Controller;

import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Bean.AdminsideLeaveBean;
import com.Bean.EmployeeBean;
import com.Bean.LeaveBean;
import com.Bean.ReviewBean;
import com.Bean.TotalLeavesofEmp;
import com.Dao.EmployeeDao;
import com.Dao.LeaveDao;

@RestController
public class LeaveController {

	@Autowired
	LeaveDao ld;

	@Autowired
	EmployeeDao employeeDao;

	// double total_leave=0;

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

		// total_leave=lb.getFull_day_leave()+lb.getHalf_day_leave()+lb.getMedical_leave();
		// lb.setTotal_leave(total_leave);

		SimpleDateFormat simpleformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
		simpleformat = new SimpleDateFormat("MMMM");
		String currentMonth = simpleformat.format(new Date());
		System.out.println("Month in MMMM format = " + currentMonth);

		lb.setMonth(currentMonth);

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

	// employeeside
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

	@PutMapping("/updateleave/{emp_id}")
	public String updateLeave(@PathVariable("emp_id") int employeeid) {

		// ld.updateEmployeeleave();

		return "";

	}

	@GetMapping("/viewleaveofemp/{emp_id}")
	public ResponseEntity<List<LeaveBean>> getleavebyempid(@PathVariable("emp_id") int empid) {
		List<LeaveBean> lb = ld.getLeavebyEmpid(empid);

		for (LeaveBean leaveBean : lb) {

			leaveBean.getLeave_id();
			leaveBean.getEmp_id();
			leaveBean.getFirst_name();
			leaveBean.getLast_name();
			leaveBean.getDepartment_name();
			leaveBean.getFull_day_leave();
			leaveBean.getHalf_day_leave();
			leaveBean.getMedical_leave();
			leaveBean.getStart_date();
			leaveBean.getEnd_date();
			leaveBean.getMonth();
			leaveBean.getLeave_description();
		}

		return new ResponseEntity<List<LeaveBean>>(lb, HttpStatus.OK);

	}

	@GetMapping("/viewleaveofempmonthly/{emp_id}/{month}")
	public ResponseEntity<List<LeaveBean>> getleavebyempidmonthwise(@PathVariable("emp_id") int empid,
			@PathVariable("month") String month) {
		System.out.println("I am here");
		List<LeaveBean> lb = ld.getLeavebyEmpidMonthwise(empid, month);

		for (LeaveBean leaveBean : lb) {

			leaveBean.getLeave_id();
			leaveBean.getEmp_id();
			leaveBean.getFirst_name();
			leaveBean.getLast_name();
			leaveBean.getDepartment_name();
			leaveBean.getFull_day_leave();
			leaveBean.getHalf_day_leave();
			leaveBean.getMedical_leave();
			leaveBean.getStart_date();
			leaveBean.getEnd_date();
			leaveBean.getMonth();
			leaveBean.getLeave_description();
		}

		return new ResponseEntity<List<LeaveBean>>(lb, HttpStatus.OK);

	}

	@GetMapping("/viewapprovedorrejectedleaveofemp/{emp_id}")
	public ResponseEntity<List<AdminsideLeaveBean>> getaorrleavebyempid(@PathVariable("emp_id") int empid) {
		List<AdminsideLeaveBean> aslb = ld.getaorrLeavebyEmpid(empid);

		for (AdminsideLeaveBean adminsideLeaveBean : aslb) {

			adminsideLeaveBean.getResponse_id();
			adminsideLeaveBean.getLeave_id();
			adminsideLeaveBean.getEmp_id();
			adminsideLeaveBean.getFirst_name();
			adminsideLeaveBean.getLast_name();
			adminsideLeaveBean.getDepartment_name();
			adminsideLeaveBean.getFull_day_leave();
			adminsideLeaveBean.getHalf_day_leave();
			adminsideLeaveBean.getMedical_leave();
			adminsideLeaveBean.getStart_date();
			adminsideLeaveBean.getEnd_date();
			adminsideLeaveBean.getMonth();
			adminsideLeaveBean.getLeave_description();
			adminsideLeaveBean.isIsapproved();

		}

		return new ResponseEntity<List<AdminsideLeaveBean>>(aslb, HttpStatus.OK);

	}

	@GetMapping("/viewaorrleaveofempmonthly/{emp_id}/{month}")
	public ResponseEntity<List<AdminsideLeaveBean>> getleaveaorrbyempidmonthwise(@PathVariable("emp_id") int empid,
			@PathVariable("month") String month) {
		System.out.println("I am here");

		List<AdminsideLeaveBean> aslb = ld.getaorrLeavebyEmpidMonthwise(empid, month);

		for (AdminsideLeaveBean adminsideLeaveBean : aslb) {

			adminsideLeaveBean.getResponse_id();
			adminsideLeaveBean.getLeave_id();
			adminsideLeaveBean.getEmp_id();
			adminsideLeaveBean.getFirst_name();
			adminsideLeaveBean.getLast_name();
			adminsideLeaveBean.getDepartment_name();
			adminsideLeaveBean.getFull_day_leave();
			adminsideLeaveBean.getHalf_day_leave();
			adminsideLeaveBean.getMedical_leave();
			adminsideLeaveBean.getStart_date();
			adminsideLeaveBean.getEnd_date();
			adminsideLeaveBean.getMonth();
			adminsideLeaveBean.getLeave_description();
			adminsideLeaveBean.isIsapproved();

		}

		return new ResponseEntity<List<AdminsideLeaveBean>>(aslb, HttpStatus.OK);
	}

	@GetMapping("/adminsideallleaves")
	public List<LeaveBean> getAllLeavesadminside() {
		List<LeaveBean> adminsideleaves = ld.getAllLeavesadmin();
		System.out.println("ALL Leaves are displayed at admin side");

		return adminsideleaves;
	}

	@PostMapping("leaveresponse/{leave_id}")
	public String saveLeavesresponse(@PathVariable("leave_id") int leaveid, @RequestBody AdminsideLeaveBean aslb) {
		// AdminsideLeaveBean aslb=new AdminsideLeaveBean();

		LeaveBean lb = ld.getleavebyleaveid(leaveid);
		System.out.println("leave Id Got");

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
			aslb.setMonth(lb.getMonth());
			aslb.setLeave_description(lb.getLeave_description());

			// aslb.setIsapproved(true);
			ld.saveLeaveresponse(aslb);
			return "save request";
			/*
			 * if(aslb.isIsapproved()==true) { return "already approved"; } else
			 * if(aslb.isIsapproved()==false) {
			 * 
			 * return "already rejected!"; } else { ld.saveLeaveresponse(aslb); return
			 * "save request"; }
			 */
		} else {
			return "leave id Not Found!";
		}
	}

	@GetMapping("/approvedleaves")
	public List<AdminsideLeaveBean> getAllapprovedLeavesempside() {
		List<AdminsideLeaveBean> empsideapprovedleaves = ld.getAllapprovedLeaves();
		System.out.println("ALL Approved Leaves are displayed at admin side");

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

	// total leaves of leaves

	@PostMapping("peremptotalleaves/{emp_id}")
	public String saveTotalLeaves(@PathVariable("emp_id") int empid) {
		// AdminsideLeaveBean aslb=new AdminsideLeaveBean();

		// LeaveBean lb = ld.getleavebyempid(empid);

		TotalLeavesofEmp tle = new TotalLeavesofEmp();
		List<AdminsideLeaveBean> aslb = ld.gettotalLeavebyempid(empid);
		System.out.println("Employee Id Got");

		if (aslb.size() > 0) {

			TotalLeavesofEmp tloe = ld.getempbyempidtl(empid);

			for (AdminsideLeaveBean adminsideLeaveBean : aslb) {
				tle.setEmp_id(adminsideLeaveBean.getEmp_id());
				tle.setLeave_id(adminsideLeaveBean.getLeave_id());

				// Integer i=ld.getfulldayLeave();

				Integer fl = ld.getfulldayleaveperemp(empid);

				tle.setFull_day_leave(fl);

				System.out.println(fl);

				Integer hl = ld.gethalfdayleaveperemp(empid);

				tle.setHalf_day_leave(hl);

				Integer ml = ld.getmedicalleaveperemp(empid);

				tle.setMedical_leave(ml);

				double totalleave = fl + hl + ml;

				tle.setTotal_leave(totalleave);

			}

			if (tloe == null) {
				ld.savetotalleaveperemp(tle);
			} else {
				tloe.setFull_day_leave(tle.getFull_day_leave());
				tloe.setHalf_day_leave(tle.getHalf_day_leave());
				tloe.setMedical_leave(tle.getMedical_leave());
				tloe.setTotal_leave(tle.getTotal_leave());
				ld.updatetotalleave(tloe);
			}

			return "save request";
		} else {
			return "Employee Not Found!";
		}
	}

}
