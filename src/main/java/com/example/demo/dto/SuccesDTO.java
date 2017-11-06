package com.example.demo.dto;

import com.example.demo.Entity.User;

public class SuccesDTO extends WrapperDTO {
    UserDTO userDTO;

    public SuccesDTO(User user) {
        super();
        userDTO = new UserDTO(user);
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
