package com.Dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.Bean.ReviewBean;

@Repository
public class ReviewDao {

	@Autowired
	JdbcTemplate stmt;

	public void savereview(ReviewBean rb) {
		stmt.update(
				"insert into review(emp_id,first_name,last_name,department_name,dateofreview,rank,description )values(?,?,?,?,?,?,?)",
				rb.getEmp_id(),rb.getFirst_name(),rb.getLast_name(),rb.getDepartment_name(),rb.getDateofreview(),rb.getRank(),rb.getDescription());
	}
	
	
	
	
}
