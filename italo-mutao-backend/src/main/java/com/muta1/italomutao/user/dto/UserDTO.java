package com.muta1.italomutao.user.dto;

import java.util.ArrayList;
import java.util.List;

import com.muta1.italomutao.user.entity.User;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserDTO {

	private Long id;
	private String name;
	private String password;
	private String role;

	public static User toEntity(UserDTO userDto) {
		User user = new User();
		user.setId(userDto.getId()).setName(userDto.getName()).setPassword(userDto.getPassword()).setRole(userDto.role);
		return user;
	}

	public static UserDTO toDTO(User userEntity) {
		UserDTO userDto = new UserDTO();
		userDto.setId(userEntity.getId()).setName(userEntity.getName()).setPassword(userEntity.getPassword())
				.setRole(userEntity.getRole());
		return userDto;
	}

	public static List<UserDTO> toDTOList(List<User> userEntityList) {
		List<UserDTO> ret = new ArrayList<UserDTO>();

		for (User entity : userEntityList) {
			ret.add(UserDTO.toDTO(entity));
		}

		return ret;
	}

}
