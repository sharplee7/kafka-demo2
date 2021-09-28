package com.example.sub1.service;

import com.example.sub1.domain.EmpDTO2;
import com.example.sub1.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpRepository empRepository;


    @Override
    public int insertEmp(EmpDTO2 empDTO) throws Exception {
        int ret = 0;

        if(empRepository.insertEmp(empDTO) >= 1) {
            ret =1;
        }

        return ret;
    }

    @Override
    public List<EmpDTO2> getEmpList() throws Exception {
        return empRepository.selectEmpList();
    }
}
