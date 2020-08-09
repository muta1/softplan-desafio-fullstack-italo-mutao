package com.muta1.italomutao.process.dto;

import java.util.ArrayList;
import java.util.List;

import com.muta1.italomutao.process.entity.Process;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProcessDTO {

	private Long id;

	private String name;

	private String technicalOpinion;

	private Boolean hasTechnicalOpinionPending;

	public static Process toEntity(ProcessDTO processDto) {
		Process process = new Process();

		process.setId(processDto.getId()).setName(processDto.getName())
				.setHasTechnicalOpinionPending(processDto.getHasTechnicalOpinionPending())
				.setTechnicalOpinion(processDto.getTechnicalOpinion());

		return process;
	}

	public static ProcessDTO toDTO(Process processEntity) {
		ProcessDTO processDto = new ProcessDTO();
		processDto.setId(processEntity.getId()).setName(processEntity.getName())
				.setHasTechnicalOpinionPending(processEntity.getHasTechnicalOpinionPending())
				.setTechnicalOpinion(processEntity.getTechnicalOpinion());
		return processDto;
	}

	public static List<ProcessDTO> toDTOList(List<Process> processEntityList) {
		List<ProcessDTO> ret = new ArrayList<ProcessDTO>();

		for (Process entity : processEntityList) {
			ret.add(ProcessDTO.toDTO(entity));
		}

		return ret;
	}
}
