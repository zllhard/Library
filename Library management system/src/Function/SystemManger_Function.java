package Function;

import db.Delete;
import db.Insert;
import entity.Book;
import entity.Borrower;
import entity.Manger;

public class SystemManger_Function {

    /**
     * 核实借阅者信息
     *
     * @param borrower
     * @return
     */
    public boolean checkBorrower(Borrower borrower) {
        System.out.println(borrower);
        return true;
    }


    Delete delete = new Delete();
    /**
     * 删除借阅者
     * @param cardno
     * @return
     */
    public boolean deleteBorrower(String cardno) {
        return delete.deleteBorrower(cardno);
    }

    /**
     * 删除图书管理员
     * @param username
     * @return
     */
    public boolean deleteLibraryManger(String username) {
        return delete.deleteLibraryManger(username);
    }

    /**
     * 删除图书
     * @param id
     * @return
     */
    public boolean deleteBook(String id) {
        return delete.deleteBook(id);
    }


    Insert insert = new Insert();
    /**
     * 添加借阅者
     * @param borrower
     * @return
     */
    public boolean insertBorrower(Borrower borrower) {
        return insert.insertBorrower(borrower);
    }

    /**
     * 添加管理者
     * @param manger
     * @return
     */
    public boolean insertManger(Manger manger) {
        return insert.insertManger(manger);
    }

    /**
     * 增加图书
     * @param book
     * @return
     */
    public boolean insertBook(Book book) {
        return insert.insertBook(book);
    }


}
