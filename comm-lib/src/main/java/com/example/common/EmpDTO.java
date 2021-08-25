package com.example.common;

import java.io.Serializable;

public class EmpDTO implements Serializable {
    private String empNO=null;
    private String empName = null;
    private String deptNo = null;

    public EmpDTO(){}

    public EmpDTO(String empNO, String empName, String deptNo) {
        this.empNO = empNO;
        this.empName = empName;
        this.deptNo = deptNo;
    }

    public String getEmpNO() {
        return empNO;
    }

    public void setEmpNO(String empNO) {
        this.empNO = empNO;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }



    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo;
    }

    @Override
    public String toString() {
        return "EmpDTO{" +
                "empNO='" + empNO + '\'' +
                ", empName='" + empName + '\'' +
                ", deptNo='" + deptNo + '\'' +
                '}';
    }
}
