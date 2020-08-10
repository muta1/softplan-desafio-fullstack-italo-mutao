package com.muta1.italomutao.technicalOpinion.dto;

import java.util.ArrayList;
import java.util.List;

import com.muta1.italomutao.process.dto.ProcessDTO;
import com.muta1.italomutao.technicalOpinion.entity.TechnicalOpinion;
import com.muta1.italomutao.user.dto.UserDTO;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TechnicalOpinionDTO {

	private Long id;
	private Boolean hasTechnicalOpinionPending;
	private String technicalOpinion;
	private ProcessDTO process;
	private UserDTO user;

	public static TechnicalOpinion toEntity(TechnicalOpinionDTO technicalOpinionDto) {
		TechnicalOpinion technicalOpinion = new TechnicalOpinion();

		technicalOpinion.setHasTechnicalOpinionPending(technicalOpinionDto.getHasTechnicalOpinionPending())
				.setId(technicalOpinionDto.getId()).setProcess(ProcessDTO.toEntity(technicalOpinionDto.getProcess()))
				.setUser(UserDTO.toEntity(technicalOpinionDto.getUser()))
				.setTechnicalOpinion(technicalOpinionDto.getTechnicalOpinion());

		return technicalOpinion;
	}

	public static TechnicalOpinionDTO toDTO(TechnicalOpinion technicalOpinion) {
		TechnicalOpinionDTO technicalOpinionDTO = new TechnicalOpinionDTO();
		technicalOpinionDTO.setHasTechnicalOpinionPending(technicalOpinion.getHasTechnicalOpinionPending())
				.setId(technicalOpinion.getId()).setProcess(ProcessDTO.toDTO(technicalOpinion.getProcess()))
				.setUser(UserDTO.toDTO(technicalOpinion.getUser()))
				.setTechnicalOpinion(technicalOpinion.getTechnicalOpinion());

		return technicalOpinionDTO;
	}

	public static List<TechnicalOpinionDTO> toDTOList(List<TechnicalOpinion> technicalOpinionEntityList) {
		List<TechnicalOpinionDTO> ret = new ArrayList<TechnicalOpinionDTO>();

		for (TechnicalOpinion entity : technicalOpinionEntityList) {
			ret.add(TechnicalOpinionDTO.toDTO(entity));
		}
	
		return ret;
	}

}
