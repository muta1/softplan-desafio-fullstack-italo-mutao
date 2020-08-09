package com.muta1.italomutao.process.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muta1.italomutao.exception.CodeException;
import com.muta1.italomutao.exception.ExceptionValidation;
import com.muta1.italomutao.exception.ServiceException;
import com.muta1.italomutao.process.dto.ProcessDTO;
import com.muta1.italomutao.process.repository.ProcessRepository;
import com.muta1.italomutao.process.entity.Process;

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

	public Process createProcess(Process process) throws ExceptionValidation, ServiceException {
		if (process == null) {
			throw new ServiceException("Impossible to create a new process, process must not be null.",
					CodeException.GENERAL);
		}

		return this.processRepository.save(process);
	}

	public Process updateProcess(Process process) {
		if (process == null) {
			throw new ServiceException("Update process fail, process must not be null.", CodeException.GENERAL);
		}

		if (process.getId() == null) {
			throw new ExceptionValidation("id", "Update process fail, id must not be null.");
		}

		Process processFromDb = processRepository.getOne(process.getId());
		processFromDb.setName(process.getName());
		processFromDb.setTechnicalOpinion(process.getTechnicalOpinion());
		processFromDb.setHasTechnicalOpinionPending(process.getHasTechnicalOpinionPending());

		return processRepository.save(processFromDb);
	}

	public void removeProcess(Long id) {
		if (id == null) {
			throw new ServiceException("Delete process fail, id must not be null.", CodeException.GENERAL);
		}

		this.processRepository.deleteById(id);
	}
}
