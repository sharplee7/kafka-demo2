package com.example.sub.repository;

import com.example.sub.domain.EmpDTO2;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpRepository {
    int insertEmp(EmpDTO2 empDTO) throws Exception;
    List<EmpDTO2> selectEmpList() throws Exception;
}
