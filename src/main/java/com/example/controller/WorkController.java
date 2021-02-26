package com.example.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Work;
import com.example.service.WorkService;

/**
 * The type Work controller.
 *
 * @author Bao
 */
@RestController
public class WorkController {

	@Autowired
	private WorkService workService;

	@GetMapping("/works")
	public List<Work> getAllWorkInfo() {
		return workService.listAllWorkInfo();
	}

	@GetMapping("/works/{id}")
	public ResponseEntity<Work> getWorkInfo(@PathVariable Integer id) {
		try {
			Work work = workService.getWorkInfo(id);
			return new ResponseEntity<Work>(work, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Work>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/works")
	public void addWorkInfo(@RequestBody Work work) {
		try {
			workService.addWorkInfo(work);
		} catch (Exception e) {
			//
		}
	}

	@PutMapping("/works/{id}")
	public ResponseEntity<Work> editWorkInfo(@RequestBody Work work, @PathVariable Integer id) {
		try {
			if (workService.getWorkInfo(id) != null) {
				work.setId(id);
				workService.addWorkInfo(work);
				return new ResponseEntity<Work>(work, HttpStatus.OK);
			}
			return new ResponseEntity<Work>(work, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Work>(work, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/works/{id}", method = { RequestMethod.DELETE })
	public void deleteWork(@PathVariable("id") Integer id) {
		workService.deleteWork(id);
	}

	@RequestMapping(value = "works/listPageable", method = RequestMethod.GET)
	Page<Work> worksPageable(Pageable pageable) {
		return workService.listWorkPaging(pageable);
	}
}
