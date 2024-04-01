package com.github.loginapp.controller;

import com.github.loginapp.dtos.LoginRecordDTO;
import com.github.loginapp.entities.Login;
import com.github.loginapp.repository.LoginRepository;
import com.github.loginapp.services.LoginService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService service;

    @GetMapping
    public ResponseEntity<List<LoginRecordDTO>> getAll() {
        List<LoginRecordDTO> listLogins = service.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<LoginRecordDTO> postLogin(@RequestBody LoginRecordDTO loginRecordDTO) {
        Login login = new Login();
        BeanUtils.copyProperties(loginRecordDTO, login);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(loginRecordDTO));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<LoginRecordDTO> deleteById(@PathVariable Long id) {
        return service.deleteById(id);
    }

    @GetMapping("/auth")
    public ResponseEntity<LoginRecordDTO> authLogin (@RequestParam String nome, @RequestParam String senha){
        LoginRecordDTO loginRecordDTO = new LoginRecordDTO(nome, senha);
        return service.authLogin(loginRecordDTO);
    }
}
