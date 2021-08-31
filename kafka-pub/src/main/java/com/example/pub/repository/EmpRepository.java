package com.example.pub.repository;

import com.example.pub.domain.EmpDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpRepository {
    int insertEmp(EmpDTO empDTO) throws Exception;
    List<EmpDTO> selectEmpList() throws Exception;
}
