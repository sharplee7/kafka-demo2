package com.example.sub1.service;


import com.example.sub1.domain.EmpDTO2;

import java.util.List;

public interface EmpService {
    public int insertEmp(EmpDTO2 empDTO) throws Exception;
    public List<EmpDTO2> getEmpList() throws Exception;
}
