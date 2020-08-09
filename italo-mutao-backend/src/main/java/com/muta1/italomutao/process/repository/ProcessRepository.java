package com.muta1.italomutao.process.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.muta1.italomutao.process.dto.ProcessDTO;

public interface ProcessRepository extends JpaRepository<ProcessDTO, Long> {

}
