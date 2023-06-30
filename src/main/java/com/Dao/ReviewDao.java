package com.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Bean.EmployeeBean;
import com.Bean.ReviewBean;

@Repository
public class ReviewDao {

	@Autowired
	JdbcTemplate stmt;

	public void savereview(ReviewBean rb) {
		stmt.update(
				"insert into review(emp_id,first_name,last_name,department_name,dateofreview,rank,description,month )values(?,?,?,?,?,?,?,?)",
				rb.getEmp_id(), rb.getFirst_name(), rb.getLast_name(), rb.getDepartment_name(), rb.getDateofreview(),
				rb.getRank(), rb.getDescription(), rb.getMonth());
	}

	public List<ReviewBean> getAllReviews() {

		return stmt.query("select * from review", new BeanPropertyRowMapper<ReviewBean>(ReviewBean.class));
	}

	//monthwise review
	public ReviewBean getReviewbyEmpidMonthwise(int empid, String month) {
		return stmt.queryForObject("select *from review where emp_id=? and month=?",
				new BeanPropertyRowMapper<ReviewBean>(ReviewBean.class), new Object[] { empid, month });

	}
	
	
	//all review
		public List<ReviewBean> getReviewbyEmpid(int empid) {
			return stmt.query("select * from review where emp_id=? and 1=1 group by review_id",
					new BeanPropertyRowMapper<ReviewBean>(ReviewBean.class), new Object[] { empid });

		}

		public boolean deleteReview(int reviewid) {
			
			int i = stmt.update("delete from review where review_id=?",reviewid);
			if (i == 0) {
				return false;
			} else {
				return true;
			}

		}
	
	

	/*
	 * public EmployeeBean getemployeebyid(int employeeid) { return
	 * stmt.queryForObject("select * from review where emp_id=? and month=?", new
	 * BeanPropertyRowMapper<EmployeeBean>(EmployeeBean.class), new Object[] {
	 * employeeid });
	 * 
	 * }
	 */

}
