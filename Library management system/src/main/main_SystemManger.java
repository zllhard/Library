package main;

import Function.SystemManger_Function;
import entity.Book;

import java.util.Scanner;

public class main_SystemManger {

    public static void insertBook() {
        Book book = new Book();
        Scanner sc = new Scanner(System.in);
        System.out.println("---------填写图书信息---------");
        System.out.println("书名：");
        book.setName(sc.next());
        System.out.println("书号：");
        book.setId(sc.next());
        System.out.println("出版社：");
        book.setPress(sc.next());
        System.out.println("价格：");
        book.setPrice(sc.nextFloat());
        System.out.println("类型（图书，杂志，论文）：");
        book.setType(sc.next());

        SystemManger_Function systemManger = new SystemManger_Function();
        systemManger.insertBook(book);

    }

    public static void main(String[] args) {
        insertBook();
    }
}
