package com.aten.punk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aten.punk.entity.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	
//	@Query(value = "select id,name,roll_no from USER_INFO_TEST where rollNo = ?1", nativeQuery = true)
//    ArrayList<IUserProjection> findUserUsingRollNo(String rollNo);
}
