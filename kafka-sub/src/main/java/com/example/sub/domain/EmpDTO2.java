package com.example.sub.domain;

public class EmpDTO2 {
    private String empNO=null;
    private String empName = null;
    private String deptNo = null;
    private int number =0;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public EmpDTO2(){}

    public EmpDTO2(String empNO, String empName, String deptNo) {
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
