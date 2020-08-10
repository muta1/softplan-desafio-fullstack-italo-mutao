package com.muta1.italomutao.process.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muta1.italomutao.exception.FormException;
import com.muta1.italomutao.exception.ValidationException;
import com.muta1.italomutao.process.entity.Process;
import com.muta1.italomutao.process.repository.ProcessRepository;

@Service
public class ProcessService {

	@Autowired
	private ProcessRepository processRepository;

	public List<Process> getAllProcesses() {
		return this.processRepository.findAll();
	}

	public Process getProcess(Long id) {
		return this.processRepository.getOne(id);
	}

	public Process createProcess(Process process)  {
		if (process == null) {
			throw new ValidationException("Impossible to create a new process, process must not be null.");
		}

		return this.processRepository.save(process);
	}

	public Process updateProcess(Process process) {
		if (process == null) {
			throw new ValidationException("Update process fail, process must not be null.");
		}

		if (process.getId() == null) {
			throw new FormException("id", "Update process fail, id must not be null.");
		}

		Process processFromDb = processRepository.getOne(process.getId());
		processFromDb.setName(process.getName());
	
		return processRepository.save(processFromDb);
	}

	public void removeProcess(Long id) {
		if (id == null) {
			throw new ValidationException("Delete process fail, id must not be null.");
		}

		this.processRepository.deleteById(id);
	}
}
