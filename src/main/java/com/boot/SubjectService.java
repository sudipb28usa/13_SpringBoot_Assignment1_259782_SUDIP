package com.boot;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SubjectService {
	
	 @Autowired SubjectRepository repo;
	 
	 public void save(Subject subject) {
	        repo.save(subject);
	    }
 
	     
	    public List<Subject> listAll() {
	        return (List<Subject>) repo.findAll();
	    }
	    
	    public List<Subject> search(String keyword) {
	        return repo.search(keyword);
	    }
	     
	    public Subject findById(Long subjectId) {
	        return repo.findById(subjectId).get();
	    }
	     
	    public void delete(Subject subject) {
	        repo.delete(subject);
	    }
	    
	    public List<Subject> findBySubtitle(String subTitle){
	    	return repo.findBySubtitle(subTitle);
	    }
	    
	    
	    
	    

}
