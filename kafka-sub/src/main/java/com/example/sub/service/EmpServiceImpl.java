package com.example.sub.service;

import com.example.common.EmpDTO;
import com.example.sub.controller.Book;
import com.example.sub.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insertEmp(EmpDTO empDTO) throws Exception {
        mongoTemplate.save(empDTO);
    }

    @Override
    public List<EmpDTO> getEmpList() throws Exception {
        List<EmpDTO> list = mongoTemplate.findAll(EmpDTO.class);

        return list;
    }
}
