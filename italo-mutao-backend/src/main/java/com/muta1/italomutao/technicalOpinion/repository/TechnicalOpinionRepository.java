package com.muta1.italomutao.technicalOpinion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muta1.italomutao.technicalOpinion.entity.TechnicalOpinion;
import com.muta1.italomutao.user.entity.User;
import com.muta1.italomutao.process.entity.Process;

public interface TechnicalOpinionRepository extends JpaRepository<TechnicalOpinion, Long> {
	List<TechnicalOpinion> findByUser(User user);
	List<TechnicalOpinion> findByProcess(Process process);
	TechnicalOpinion findByUserAndProcess(User user, Process process);
}
