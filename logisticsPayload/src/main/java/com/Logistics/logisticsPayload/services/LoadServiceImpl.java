package com.Logistics.logisticsPayload.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.Logistics.logisticsPayload.dao.LoadDao;
import com.Logistics.logisticsPayload.entities.Load;

@Service
public class LoadServiceImpl implements LoadService{
	
	@Autowired		//Automatic wiring
	////@Autowired will create an object of implementation class of LoadDao 
	//and inject in the LoadDao variable using dependency injection rule.
	private LoadDao loadDao;
	
	@Override
	public List<Load> getLoads() {
		try {
			return loadDao.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
	}

	
	@Override
	public Load getLoadsByLoadId(@PathVariable Integer loadId){
		return loadDao.findById(loadId).orElse(null);
	}
	
	
	@Override
	public List<Load> getLoadsByShipperId(@PathVariable String shipperId){
		return loadDao.findByShipperId(shipperId);
	}

	@Override
	public ResponseEntity<String> addLoad(@RequestBody Load load) {
		try {
			loadDao.save(load);
			return ResponseEntity.ok("Loads details added successfully");
		} 
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public ResponseEntity<Load> updateLoad(@RequestBody Load load,@PathVariable Integer loadId)
	{
		try {
			Load entity=loadDao.findById(loadId).orElseThrow();		//entity contains the pay load of given loadId
			
			//The control will go to the Load class and using getter method we fetch the previous value of load and 
			//using setter method we set the updated value to the load.
			
			entity.setLoadingPoint(load.getLoadingPoint());
			entity.setUnloadingPoint(load.getUnloadingPoint());
			entity.setProductType(load.getProductType());
			entity.setTruckType(load.getTruckType());
			entity.setNoOfTrucks(load.getNoOfTrucks());
			entity.setWeight(load.getWeight());
			entity.setComment(load.getComment());
			entity.setShipperId(load.getShipperId());
			entity.setDate(load.getDate());
			
			Load updatedLoad= loadDao.save(entity);		//It will save the updated load to the database
			return ResponseEntity.ok(updatedLoad);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@Override
	public ResponseEntity<String> deleteLoad(@PathVariable Integer loadId) {
        try {
            Optional<Load> entity=loadDao.findById(loadId);		//find the Load entity with the specified loadId
            //Optional is a container object that may or may not contain a non-null value so If the entity is found, the Optional contains the Load object else it is empty
            
            if (entity.isPresent()) {
                Load loadToDelete=entity.get();
                loadDao.delete(loadToDelete);
                return ResponseEntity.ok("Load deleted successfully");
            } 
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            
        } 
        catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }	
}
