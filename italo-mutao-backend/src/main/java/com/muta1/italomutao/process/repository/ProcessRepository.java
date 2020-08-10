package com.muta1.italomutao.process.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.muta1.italomutao.process.entity.Process;

public interface ProcessRepository extends JpaRepository<Process, Long> {

}
