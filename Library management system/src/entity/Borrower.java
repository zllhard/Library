package entity;

import Function.Borrower_Function;

public class Borrower extends Borrower_Function {
    private String name;  //姓名
    private String cardno;  //卡号
    private String password;  //密码
    private String major;  //专业
    private String department;  //宿舍
    private String type;  // 本科生，研究生，博士生，专科生，老师

    public Borrower() {
    }

    public Borrower(String name, String cardno, String password, String major, String department, String type) {
        this.name = name;
        this.cardno = cardno;
        this.password = password;
        this.major = major;
        this.department = department;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardno() {
        return cardno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Borrower{" +
                "name='" + name + '\'' +
                ", cardno='" + cardno + '\'' +
                ", password='" + password + '\'' +
                ", major='" + major + '\'' +
                ", department='" + department + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
