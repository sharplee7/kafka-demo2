package com.example.pub.service;

import com.example.common.EmpDTO;
import com.example.pub.publish.EmpPublisher;
import com.example.pub.repository.EmpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmpServiceImpl implements EmpService{
    @Autowired
    private EmpRepository empRepository;

    @Autowired
    private EmpPublisher empPublisher;

    @Override
    public int insertEmp(EmpDTO empDTO) throws Exception {
        int ret = 0;

        if(empRepository.insertEmp(empDTO) >= 1) {
            empPublisher.publish(empDTO);
            ret =1;
        }

        return ret;
    }

    @Override
    public List<EmpDTO> getEmpList() throws Exception {
        return empRepository.selectEmpList();
    }
}
