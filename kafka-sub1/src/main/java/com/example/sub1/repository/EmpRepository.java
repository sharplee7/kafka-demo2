package com.example.sub1.repository;

import com.example.sub1.domain.EmpDTO2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpRepository {
    int insertEmp(EmpDTO2 empDTO) throws Exception;
    List<EmpDTO2> selectEmpList() throws Exception;
}
