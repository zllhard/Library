package Function;

import db.Select;
import db.Update;
import entity.Book;

import java.util.List;
import java.util.Vector;

public class Borrower_Function {

    Select select = new Select();

    /**
     * 查询图书
     * @param name
     * @return
     */
    public Vector<Vector<Object>> selectBook(String name) {
        Vector<Vector<Object>> data = new Vector<>();
        Vector<Object> row;
        List<Book> bookList = select.selectBook(name);
        for (Book book : bookList) {
            row = new Vector<>();
            row.add(book.getName());
            row.add(book.getId());
            row.add(book.getPress());
            row.add(book.getPrice());
            if (book.getIsBorrowed() == 0) {
                row.add("未借出");
            }else {
                row.add("已借出");
            }
            row.add(book.getType());
            if (book.getOrder_cardno().isEmpty()) {
                row.add("未预约");
            }else {
                row.add("已预约");
            }
            data.add(row);
        }

        return data;
    }

    /**
     * 预约
     * @param id
     * @return
     */
    public boolean Order(String id, String cardno) {
        List<Book> bookList = select.selectBook2(id);
        if (bookList.isEmpty()) {
            System.out.println("书籍不存在");
        }
        Book book = bookList.get(0);
        if (!book.getOrder_cardno().isEmpty()) {
            System.out.println("书籍已经被预约");
            return false;
        }else {
            Update update = new Update();
            update.updateBookOrder_cardno(id, cardno);
        }

        return  true;
    }

}
