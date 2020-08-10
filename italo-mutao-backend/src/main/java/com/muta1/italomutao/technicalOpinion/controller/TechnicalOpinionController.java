package com.muta1.italomutao.technicalOpinion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.muta1.italomutao.api.ApiResponse;
import com.muta1.italomutao.process.dto.ProcessDTO;
import com.muta1.italomutao.technicalOpinion.dto.TechnicalOpinionDTO;
import com.muta1.italomutao.technicalOpinion.service.TechnicalOpinionService;
import com.muta1.italomutao.user.dto.UserDTO;

@RestController
@RequestMapping("/technical-opinion")
@CrossOrigin
public class TechnicalOpinionController {

	@Autowired
	private TechnicalOpinionService technicalOpinionService;

	@PostMapping(path = "/readbyprocess")
	public ApiResponse<List<TechnicalOpinionDTO>> getByProccess(@RequestBody ProcessDTO processDTO) {
		ApiResponse<List<TechnicalOpinionDTO>> ret = new  ApiResponse<>();
		ret.setResponse(TechnicalOpinionDTO.toDTOList(this.technicalOpinionService.getAllByProcess(ProcessDTO.toEntity(processDTO))));
		return ret;
	}

	@PostMapping(path = "/readbyuser")
	public ApiResponse<List<TechnicalOpinionDTO>> getByUser(@RequestBody UserDTO userDTO) {
		ApiResponse<List<TechnicalOpinionDTO>> ret = new  ApiResponse<>();
		ret.setResponse(TechnicalOpinionDTO.toDTOList(this.technicalOpinionService.getAllByUser(UserDTO.toEntity(userDTO))));
		return ret;
	}

	@PostMapping(path = "/create")
	public ApiResponse<TechnicalOpinionDTO> createTechnicalOpinion(@RequestBody TechnicalOpinionDTO technicalOpinionDTO) {
		ApiResponse<TechnicalOpinionDTO> ret = new  ApiResponse<>();
		System.out.println(technicalOpinionDTO);
		ret.setResponse(TechnicalOpinionDTO.toDTO(this.technicalOpinionService.createTechnicalOpinion(TechnicalOpinionDTO.toEntity(technicalOpinionDTO))));
		return ret;
	}

	@PutMapping(path = "/giveopinion")
	public ApiResponse<TechnicalOpinionDTO> putUser(@RequestBody TechnicalOpinionDTO technicalOpinionDTO) {
		ApiResponse<TechnicalOpinionDTO> ret = new  ApiResponse<>();
		ret.setResponse(TechnicalOpinionDTO.toDTO(this.technicalOpinionService.giveOpinion(TechnicalOpinionDTO.toEntity(technicalOpinionDTO))));
		return ret;
	}
}
