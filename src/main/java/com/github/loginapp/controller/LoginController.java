package com.github.loginapp.controller;

import com.github.loginapp.dtos.LoginRecordDTO;
import com.github.loginapp.entities.Login;
import com.github.loginapp.repository.LoginRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginRepository repository;

    @GetMapping
    public ResponseEntity<List<Login>> getLogin() {
        List<Login> listLogins = repository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    @PostMapping
    public ResponseEntity<Login> postLogin(@RequestBody LoginRecordDTO loginRecordDTO) {
        Login login = new Login();
        BeanUtils.copyProperties(loginRecordDTO, login);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(login));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteById(@PathVariable Long id) {
        Optional<Login> login = repository.findById(id);
        if (login.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi possível encontrar o login informado");
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Login deletado com sucesso");
    }
}
