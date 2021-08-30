package com.example.sub.service;


import com.example.common.EmpDTO;

import java.util.List;

public interface EmpService {
    public void insertEmp(EmpDTO empDTO) throws Exception;
    public List<EmpDTO> getEmpList() throws Exception;
}
