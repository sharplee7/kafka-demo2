package com.example.sub.controller;


import com.example.common.EmpDTO;
import com.example.sub.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubscriberController {

    @Autowired
    EmpService empService;

    @GetMapping(value = "/employee")
    public ResponseEntity<List<EmpDTO>> select() throws Exception {
        List<EmpDTO> list = empService.getEmpList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
