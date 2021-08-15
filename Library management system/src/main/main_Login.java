package main;

import Function.Login;

import java.util.Scanner;

public class main_Login {

    public static void main_borrower_login() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("---------登录---------");
            System.out.println("输入卡号：");
            String cardno = sc.next();
            System.out.println("输入密码：");
            String password = sc.next();

            Login login = new Login();
            boolean b = login.Borrower_login(cardno, password);
            if (b) {
                System.out.println("登录成功");
                break;
            }else {
                System.out.println("卡号或者密码错误,请重新输入");
            }
        }
    }

    public static void main_manger_login() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("---------登录---------");
            System.out.println("输入用户名：");
            String username = sc.next();
            System.out.println("输入密码：");
            String password = sc.next();

            Login login = new Login();
            boolean b = login.Manger_login(username, password);
            if (b) {
                System.out.println("登录成功");
                break;
            }else {
                System.out.println("用户名或者密码错误,请重新输入");
            }
        }
    }



    public static void main(String[] args) {
//        main_borrower_login();
        main_manger_login();
    }
}
