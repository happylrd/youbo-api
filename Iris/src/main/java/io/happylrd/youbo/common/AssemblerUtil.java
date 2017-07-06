package io.happylrd.youbo.common;

import io.happylrd.youbo.model.domain.User;
import io.happylrd.youbo.model.dto.UserDTO;

public class AssemblerUtil {

    public static UserDTO assembleIntoUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setNickname(user.getNickname());
        userDTO.setRealname(user.getRealname());
        userDTO.setAvatar(user.getAvatar());
        userDTO.setGender(user.getGender());
        userDTO.setBirthday(user.getBirthday());
        userDTO.setDescription(user.getDescription());
        return userDTO;
    }
}
