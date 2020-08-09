package com.muta1.italomutao.process.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muta1.italomutao.process.dto.ProcessDTO;
import com.muta1.italomutao.process.service.ProcessService;

@RestController
@RequestMapping("/process")
@CrossOrigin
public class ProcessController {
	@Autowired
	private ProcessService processService;

	@GetMapping()
	public List<ProcessDTO> Getprocesss() {
		return processService.getAllProcesses();
	}

	@PostMapping()
	public ProcessDTO postUser(@RequestBody ProcessDTO process) {
		return this.processService.createProcess(process);
	}

	@PutMapping()
	public ProcessDTO putUser(@RequestBody ProcessDTO process) {
		return this.processService.updateProcess(process);
	}

	@DeleteMapping()
	public void deleteUser(@RequestBody ProcessDTO process) {
		this.processService.removeProcess(process);
	}

}
