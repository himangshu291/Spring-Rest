package com.Logistics.logisticsPayload.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Logistics.logisticsPayload.entities.Load;
import com.Logistics.logisticsPayload.services.LoadService;

@RestController
public class LoadController {
	//@Autowired will create an object of loadServiceImpl class which is implemented class of loadService class 
	//and inject in the load service variable using dependency injection rule.
	@Autowired
	private LoadService loadService;

	//Get all pay loads
	
	@GetMapping("/load")
	public List<Load> getLoads(){
		return this.loadService.getLoads();
	}
	
	//Get single pay load By load Id
	
	@GetMapping("/load/byId/{loadId}")
	public ResponseEntity<Load> getLoadsByLoadId(@PathVariable Integer loadId){
		Load load = this.loadService.getLoadsByLoadId(loadId);
        if (load != null) {
            return ResponseEntity.ok(load);
        } else {
            return ResponseEntity.notFound().build();
        }
        
	}
		
	//Get a list of Loads based on the shipper id
	
	@GetMapping("/load/byShipper/{shipperId}")
	public ResponseEntity<List<Load>> getLoadsByShipperId(@PathVariable String shipperId){
		List<Load> loads=this.loadService.getLoadsByShipperId(shipperId);
		return ResponseEntity.ok(loads);
	}
	
	//Add a load
	
	@PostMapping("/load")
	public ResponseEntity<String> addLoad(@RequestBody Load load) {
		return this.loadService.addLoad(load);
	}
			
	//Update the load
	
	@PutMapping("/load/{loadId}")
	public ResponseEntity<Load> updateLoad(@RequestBody Load load, @PathVariable Integer loadId)
	{
		return this.loadService.updateLoad(load, loadId);
	}
	
	//Delete the load
	
	@DeleteMapping("/load/{loadId}")
	public ResponseEntity<String> deleteLoad(@PathVariable Integer loadId) {
		return this.loadService.deleteLoad(loadId);
    }
			
}
