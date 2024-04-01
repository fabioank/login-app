package com.github.loginapp.services;

import com.github.loginapp.dtos.LoginRecordDTO;
import com.github.loginapp.entities.Login;
import com.github.loginapp.mapper.LoginMapper;
import com.github.loginapp.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private LoginRepository repository;

    public LoginRecordDTO save(LoginRecordDTO loginRecordDTO){
        Login login = LoginMapper.loginRecordDTOtoEntity(loginRecordDTO);
        LoginRecordDTO saved = LoginMapper.toLoginRecordDTO(repository.save(login));
        return saved;
    }

    public List<LoginRecordDTO> findAll( ){
        List<Login> logins = repository.findAll();
        return logins.stream().map(LoginMapper::toLoginRecordDTO).collect(Collectors.toList());
    }
    public ResponseEntity<LoginRecordDTO> deleteById(Long id){
        Optional<Login> login = repository.findById(id);
        if (login.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<LoginRecordDTO> authLogin(LoginRecordDTO loginDTO){

        Login login = new Login();
        login = LoginMapper.loginRecordDTOtoEntity(loginDTO);

        Optional<Login> loginOptional = repository.findByNome(login.getNome());

        if(loginOptional.isPresent()){
            Login loginResult = loginOptional.get();
            if(loginResult.getSenha().equals(login.getSenha())){
                return ResponseEntity.ok().build();
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
