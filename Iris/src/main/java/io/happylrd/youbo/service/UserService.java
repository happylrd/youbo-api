package io.happylrd.youbo.service;

import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.dto.UserDTO;
import io.happylrd.youbo.model.vo.LoginVO;
import io.happylrd.youbo.model.vo.RegisterVO;

public interface UserService {

    ServerResponse<UserDTO> register(RegisterVO registerVO);

    ServerResponse<UserDTO> login(LoginVO loginVO);

    ServerResponse<UserDTO> getInfo(Long userId);
}
