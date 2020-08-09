package com.muta1.italomutao.process.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muta1.italomutao.process.dto.ProcessDTO;
import com.muta1.italomutao.process.repository.ProcessRepository;

@RestController
@RequestMapping("/process")
@CrossOrigin
public class ProcessController {
    @Autowired
    private ProcessRepository processRepository;

    @GetMapping()
    public List<ProcessDTO> Getprocesss() {
        return processRepository.findAll();
    }
}
