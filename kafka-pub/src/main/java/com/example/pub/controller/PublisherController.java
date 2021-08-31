package com.example.pub.controller;


import com.example.pub.domain.EmpDTO;
import com.example.pub.service.EmpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PublisherController {

    @Autowired
    EmpService empService;

    @Operation(summary = "사용자 저장", description = "사용자 정보를 저장합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping(value = "/employee")
    public ResponseEntity save(@RequestBody final EmpDTO empDTO) throws Exception {
        if(empService.insertEmp(empDTO) != 1) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping(value = "/employee")
    public ResponseEntity<List<EmpDTO>> select() throws Exception {
        List<EmpDTO> list = empService.getEmpList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
