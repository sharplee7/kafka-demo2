package com.example.sub2.service;


import com.example.sub2.domain.EmpDTO2;

import java.util.List;

public interface EmpService {
    public int insertEmp(EmpDTO2 empDTO) throws Exception;
    public List<EmpDTO2> getEmpList() throws Exception;
}
