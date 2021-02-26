package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.model.Work;
import com.example.repository.WorkRepository;

/**
 * Work Service
 * 
 * @author Bao
 *
 */
@Service
public class WorkService {

	@Autowired
	private WorkRepository workRepository;

	public List<Work> listAllWorkInfo() {
		return workRepository.findAll();
	}

	public void addWorkInfo(Work work) {
		workRepository.save(work);
	}

	public Work getWorkInfo(Integer id) {
		return workRepository.findById(id).get();
	}

	public void deleteWork(Integer id) {
		workRepository.deleteById(id);
	}
	
	public Page<Work> listWorkPaging(Pageable pageable) {
		return workRepository.findAll(pageable);
	}
}
