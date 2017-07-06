package io.happylrd.youbo.service.impl;

import io.happylrd.youbo.common.AssemblerUtil;
import io.happylrd.youbo.common.ServerResponse;
import io.happylrd.youbo.model.domain.Role;
import io.happylrd.youbo.model.domain.User;
import io.happylrd.youbo.model.dto.UserDTO;
import io.happylrd.youbo.model.vo.LoginVO;
import io.happylrd.youbo.model.vo.RegisterVO;
import io.happylrd.youbo.repository.RoleRepository;
import io.happylrd.youbo.repository.UserRepository;
import io.happylrd.youbo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public ServerResponse<UserDTO> register(RegisterVO registerVO) {
        long resultCount = userRepository.countByUsername(registerVO.getUsername());
        if (resultCount > 0) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }

        User user = new User();
        user.setUsername(registerVO.getUsername());
        user.setPassword(registerVO.getPassword());
        user.setNickname(registerVO.getNickname());

        Role role = roleRepository.findByName("ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(role)));

        userRepository.save(user);

        // do log

        return ServerResponse.createBySuccessMessage("注册成功");
    }

    @Override
    public ServerResponse<UserDTO> login(LoginVO loginVO) {
        long resultCount = userRepository.countByUsername(loginVO.getUsername());
        if (resultCount == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }

        // MD5 process for password

        User user = userRepository.findByUsernameAndPassword(
                loginVO.getUsername(), loginVO.getPassword());

        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }

        UserDTO userDTO = AssemblerUtil.assembleIntoUserDTO(user);

        return ServerResponse.createBySuccess("登录成功", userDTO);
    }

    @Override
    public ServerResponse<UserDTO> getInfo(Long userId) {
        User user = userRepository.findOne(userId);

        if (user == null) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }

        UserDTO userDTO = AssemblerUtil.assembleIntoUserDTO(user);

        return ServerResponse.createBySuccess(userDTO);
    }
}
