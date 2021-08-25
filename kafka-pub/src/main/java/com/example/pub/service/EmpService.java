package com.example.pub.service;


import com.example.common.EmpDTO;

import java.util.List;

public interface EmpService {
    public int insertEmp(EmpDTO empDTO) throws Exception;
    public List<EmpDTO> getEmpList() throws Exception;
}
