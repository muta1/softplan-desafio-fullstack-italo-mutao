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
import org.springframework.web.bind.annotation.RequestParam;
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
	public List<ProcessDTO> getProcesses() {
		return ProcessDTO.toDTOList(processService.getAllProcesses());
	}

	@GetMapping(path = "read")
	public ProcessDTO getProcess(@RequestParam Long id) {
		return ProcessDTO.toDTO(processService.getProcess(id));
	}

	@PostMapping(path = "create")
	public ProcessDTO postProcess(@RequestBody ProcessDTO process) {
		return ProcessDTO.toDTO(this.processService.createProcess(ProcessDTO.toEntity(process)));
	}

	@PutMapping(path = "update")
	public ProcessDTO putProcess(@RequestBody ProcessDTO process) {
		return ProcessDTO.toDTO(this.processService.updateProcess(ProcessDTO.toEntity(process)));
	}

	@DeleteMapping(path = "delete")
	public void deleteProcess(@RequestBody Long id) {
		this.processService.removeProcess(id);
	}

}
