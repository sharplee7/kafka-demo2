package com.example.sub.service;


import com.example.sub.domain.EmpDTO2;

import java.util.List;

public interface EmpService {
    public int insertEmp(EmpDTO2 empDTO) throws Exception;
    public List<EmpDTO2> getEmpList() throws Exception;
}
