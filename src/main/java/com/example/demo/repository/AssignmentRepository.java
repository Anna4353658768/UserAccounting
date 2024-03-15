package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Assignment;

public interface AssignmentRepository extends JpaRepository<Assignment, Long>{
	
	@Query(value = "SELECT assignments.* FROM assignments "
			+ "JOIN roles ON assignments.role_id = roles.id "
			+ "ORDER BY roles.name_role,assignments.user_id", nativeQuery = true)
	Iterable<Assignment> findAllByOrderByRole();
	
	
	@Query(value = "SELECT assignments.* FROM assignments\r\n"
			+ "JOIN users ON assignments.user_id = users.id\r\n"
			+ "JOIN roles ON assignments.role_id = roles.id\r\n"
			+ "ORDER BY users.username, roles.name_role", nativeQuery = true)
	Iterable<Assignment> findAllByOrderByUser();
	
	
	@Query(value = "SELECT * FROM assignments "
			+ "WHERE user_id = ? AND role_id = ?", nativeQuery = true)
	Assignment findByUserAndRole(Long userId, Long roleId);
	
	
	Iterable<Assignment> findAllByUserId(Long userId);
	
}
