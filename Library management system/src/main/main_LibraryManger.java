//package main;
//
//import entity.InfoBorrow;
//import entity.LibraryManger;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class main_LibraryManger {
//
//    public static void borrowBook() {
//        System.out.println("---------借阅图书---------");
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("输入学生卡号");
//        String borrower_cardno = scanner.next();
//        System.out.println("输入借阅的书籍");
//        String book_name = scanner.next();
//
//        LibraryManger libraryManger = new LibraryManger();
//        boolean b = libraryManger.borrowBook(book_name, borrower_cardno);
//        if (b) {
//            System.out.println("借阅成功！");
//        }
//
//    }
//
//    public static void returnBook() {
//        System.out.println("---------归还图书---------");
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("输入学生卡号");
//        String borrower_cardno = scanner.next();
//        System.out.println("输入归还的书籍");
//        String book_name = scanner.next();
//
//        LibraryManger libraryManger = new LibraryManger();
//        boolean b = libraryManger.returnBook(book_name, borrower_cardno);
//        if (b) {
//            System.out.println("归还成功！");
//        }
//
//    }
//
//    public static void selectInfoBorrow() {
//        System.out.println("---------查询借阅信息---------");
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("输入学生卡号");
//        String borrower_cardno = scanner.next();
//
//        LibraryManger libraryManger = new LibraryManger();
//        List<InfoBorrow> infoBorrowList = libraryManger.selectInfoBorrow(borrower_cardno);
//        System.out.println(infoBorrowList);
//    }
//
//    public static void main(String[] args) {
////        borrowBook();
////        returnBook();
//        selectInfoBorrow();
//    }
//}
