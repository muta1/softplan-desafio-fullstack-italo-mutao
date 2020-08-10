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

import com.muta1.italomutao.api.ApiResponse;
import com.muta1.italomutao.process.dto.ProcessDTO;
import com.muta1.italomutao.process.service.ProcessService;

@RestController
@RequestMapping("/process")
@CrossOrigin
public class ProcessController {
	@Autowired
	private ProcessService processService;

	@GetMapping()
	public ApiResponse<List<ProcessDTO>> getProcesses() {
		ApiResponse<List<ProcessDTO>> ret = new ApiResponse<>();
		ret.setResponse(ProcessDTO.toDTOList(processService.getAllProcesses()));
		return ret;
	}

	@GetMapping(path = "read")
	public ApiResponse<ProcessDTO> getProcess(@RequestParam Long id) {
		ApiResponse<ProcessDTO> ret = new ApiResponse<>();
		ret.setResponse(ProcessDTO.toDTO(processService.getProcess(id)));
		return ret;
	}

	@PostMapping(path = "create")
	public ApiResponse<ProcessDTO> postProcess(@RequestBody ProcessDTO process) {
		ApiResponse<ProcessDTO> ret = new ApiResponse<>();
		ret.setResponse(ProcessDTO.toDTO(this.processService.createProcess(ProcessDTO.toEntity(process))));
		return ret;
	}

	@PutMapping(path = "update")
	public ApiResponse<ProcessDTO> putProcess(@RequestBody ProcessDTO process) {
		ApiResponse<ProcessDTO> ret = new ApiResponse<>();
		ret.setResponse(ProcessDTO.toDTO(this.processService.updateProcess(ProcessDTO.toEntity(process))));
		return ret;
	}

	@DeleteMapping(path = "delete")
	public ApiResponse<Boolean> deleteProcess(@RequestBody Long id) {
		ApiResponse<Boolean> ret = new ApiResponse<>();
		this.processService.removeProcess(id);
		ret.setResponse(true);
		return ret;
	}

}
