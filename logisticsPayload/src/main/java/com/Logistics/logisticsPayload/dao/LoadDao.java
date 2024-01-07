package com.Logistics.logisticsPayload.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Logistics.logisticsPayload.entities.Load;


@Repository		//Now Its object will go to the spring container using @Repository 

public interface LoadDao extends JpaRepository<Load, Integer>{

	List<Load> findByShipperId(String shipperId);

}
//JpaRepository<Load, Integer> - whose value we want to change that is Load. Means In a Load class 
//we will perform all the operations like add,update,delete and Integer is a data type of a primary key 
//Actually the load is comes from entities package
