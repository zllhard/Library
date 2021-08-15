//package main;
//
//import entity.Book;
//import entity.Borrower;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class main_Borrower {
//
//    public static void selectBook() {
//
//        System.out.println("---------查询图书（输入0退出）---------");
//        System.out.println("请输入书名：");
//        Scanner scanner = new Scanner(System.in);
//        String name = scanner.next();
//        if (name.equals("0")) {
//            return;
//        }
//        Borrower borrower = new Borrower();
//        List<Book> bookList = borrower.selectBook(name);
//        System.out.println(bookList);
//    }
//
//    public static void main(String[] args) {
//        selectBook();
//    }
//}
