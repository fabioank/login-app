package com.github.loginapp.mapper;

import com.github.loginapp.dtos.LoginRecordDTO;
import com.github.loginapp.entities.Login;

public class LoginMapper {

    public static LoginRecordDTO toLoginRecordDTO(Login login){

        LoginRecordDTO loginRecordDTO = new LoginRecordDTO(login.getNome(), login.getSenha());

        return loginRecordDTO;
    }
    public static Login loginRecordDTOtoEntity(LoginRecordDTO loginRecordDTO){

        Login login = new Login();
        login.setNome(loginRecordDTO.nome());
        login.setSenha(loginRecordDTO.senha());

        return login;
    }
}
