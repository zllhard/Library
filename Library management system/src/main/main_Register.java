package main;

import Function.Register;
import entity.Borrower;
import entity.Manger;

import java.util.Scanner;

public class main_Register {

    public static void main_Borrower_register() {
        Borrower borrower = new Borrower();
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("---------填写注册信息---------");
            System.out.println("姓名：");
            borrower.setName(sc.next());
            System.out.println("卡号：");
            borrower.setCardno(sc.next());
            System.out.println("密码：");
            borrower.setPassword(sc.next());
            System.out.println("专业：");
            borrower.setMajor(sc.next());
            System.out.println("宿舍：");
            borrower.setDepartment(sc.next());
            System.out.println("选择借阅者类型（本科生，研究生，博士生，专科生，老师）：");
            borrower.setType(sc.next());

            Register register = new Register();
            boolean b = register.Borrower_register(borrower);
            if (b) {
                System.out.println("注册成功！");
            }

        }
    }

    public static void main_Manger_Register() {
        Manger manger = new Manger();
        Scanner sc = new Scanner(System.in);
        System.out.println("---------填写注册信息---------");
        System.out.println("用户名：");
        manger.setUsername(sc.next());
        System.out.println("密码：");
        manger.setPassword(sc.next());
        System.out.println("选择管理员类型（0是图书管理员， 1是系统管理员）：");
        manger.setType(sc.next());

        Register register = new Register();
        boolean b = register.Manger_register(manger);

        if (!b) System.out.println("管理员已存在");
    }

    public static void main(String[] args) {
//        main_Borrower_register();
        main_Manger_Register();
    }


}