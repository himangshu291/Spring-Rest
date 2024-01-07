package com.Logistics.logisticsPayload.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.Logistics.logisticsPayload.entities.Load;

public interface LoadService {

	public List<Load> getLoads();

	public Load getLoadsByLoadId(Integer loadId);

	public List<Load> getLoadsByShipperId(String shipperId);

	public ResponseEntity<String> addLoad(Load load);

	public ResponseEntity<Load> updateLoad(Load load, Integer loadId);

	public ResponseEntity<String> deleteLoad(Integer loadId);

}
