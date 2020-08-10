package com.muta1.italomutao.technicalOpinion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.muta1.italomutao.exception.FormException;
import com.muta1.italomutao.exception.ValidationException;
import com.muta1.italomutao.process.entity.Process;
import com.muta1.italomutao.technicalOpinion.entity.TechnicalOpinion;
import com.muta1.italomutao.technicalOpinion.repository.TechnicalOpinionRepository;
import com.muta1.italomutao.user.entity.User;

@Service
public class TechnicalOpinionService {

	@Autowired
	private TechnicalOpinionRepository technicalOpinionRepository;

	public List<TechnicalOpinion> getAllByUser(User user) {
		return this.technicalOpinionRepository.findByUser(user);
	}

	public List<TechnicalOpinion> getAllByProcess(Process process) {
		return this.technicalOpinionRepository.findByProcess(process);
	}

	public TechnicalOpinion createTechnicalOpinion(TechnicalOpinion technicalOpinion) {
		if (technicalOpinion == null) {
			throw new ValidationException("Falha ao criar parecer técnico, parecer técnico não pode estar vazio.");
		}

		TechnicalOpinion findByUserAndProcess = this.technicalOpinionRepository
				.findByUserAndProcess(technicalOpinion.getUser(), technicalOpinion.getProcess());

		if (findByUserAndProcess != null) {
			throw new ValidationException(
					"Falha ao criar parecer técnico, já existe um parecer técnico criado para esse usuário e processo.");
		}

		return this.technicalOpinionRepository.save(technicalOpinion);
	}

	public TechnicalOpinion giveOpinion(TechnicalOpinion technicalOpinion) {
		if (technicalOpinion == null) {
			throw new ValidationException(
					"Falha ao atualizar parecer técnico, já existe um parecer técnico criado para esse usuário e processo.");
		}

		if (technicalOpinion.getId() == null) {
			throw new FormException("id",
					"Falha ao atualizar parecer técnico, parecer técnico não pode estar vazio.");
		}

		TechnicalOpinion technicalOpinionFromDb = technicalOpinionRepository.getOne(technicalOpinion.getId());
		// crush the variables of the object found
		technicalOpinionFromDb.setHasTechnicalOpinionPending(true);
		technicalOpinionFromDb.setTechnicalOpinion(technicalOpinion.getTechnicalOpinion());

		return technicalOpinionRepository.save(technicalOpinionFromDb);
	}

}
