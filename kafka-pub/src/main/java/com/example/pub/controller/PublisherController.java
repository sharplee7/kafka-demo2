package com.example.pub.controller;


import com.example.common.EmpDTO;
import com.example.pub.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublisherController {

    @Autowired
    EmpService empService;

    @PostMapping(value = "/employee")
    public ResponseEntity save(@RequestBody final EmpDTO empDTO) throws Exception {
        if(empService.insertEmp(empDTO) == 1) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("fail", HttpStatus.OK);
        }
    }

    @GetMapping(value = "/employee")
    public ResponseEntity<List<EmpDTO>> select() throws Exception {
        List<EmpDTO> list = empService.getEmpList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
