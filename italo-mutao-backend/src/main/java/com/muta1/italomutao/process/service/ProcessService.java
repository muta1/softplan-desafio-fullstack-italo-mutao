package com.muta1.italomutao.process.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muta1.italomutao.exception.CodeException;
import com.muta1.italomutao.exception.ExceptionValidation;
import com.muta1.italomutao.exception.ServiceException;
import com.muta1.italomutao.process.dto.ProcessDTO;
import com.muta1.italomutao.process.repository.ProcessRepository;

@Service
public class ProcessService {

	@Autowired
	private ProcessRepository processRepository;

	public List<ProcessDTO> getAllProcesses() {
		return this.processRepository.findAll();
	}

	public ProcessDTO createProcess(ProcessDTO process) throws ExceptionValidation, ServiceException {
		if (process == null) {
			throw new ServiceException("Impossible to create a new process, process must not be null.",
					CodeException.GENERAL);
		}

		return this.processRepository.save(process);
	}

	public ProcessDTO updateProcess(ProcessDTO process) {
		if (process == null) {
			throw new ServiceException("Update process fail, process must not be null.", CodeException.GENERAL);
		}

		if (process.getId() == null) {
			throw new ExceptionValidation("id", "Update process fail, id must not be null.");
		}

		ProcessDTO processFromDb = processRepository.getOne(process.getId());
		processFromDb.setName(process.getName());
		processFromDb.setTechnicalOpinion(process.getTechnicalOpinion());
		processFromDb.setHasTechnicalOpinionPending(process.getHasTechnicalOpinionPending());

		return processRepository.save(processFromDb);
	}

	public void removeProcess(ProcessDTO process) {
		if (process == null) {
			throw new ServiceException("Delete process fail, process must not be null.", CodeException.GENERAL);
		}

		this.processRepository.delete(process);
	}
}
