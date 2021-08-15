package Function;

import db.Delete;
import db.Insert;
import db.Select;
import db.Update;
import entity.Book;
import entity.Borrower;
import entity.InfoBorrow;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class LibraryManger_Function {
    Update update = new Update();
    Select select = new Select();

    /**
     * 借阅图书
     * @param book_name
     * @param borrower_cardno
     * @return
     */
    public boolean borrowBook(String book_name, String borrower_cardno) {

        //查找书是否还有
        List<Book> bookList = select.selectBook(book_name);
        List<Borrower> borrowerList = select.selectBorrower(borrower_cardno);
        Borrower borrower = borrowerList.get(0);
        int count = 0;
        for(Book book : bookList) {
            if (book.getIsBorrowed() == 0 && book.getOrder_cardno().isEmpty()) {
                count++;
            }else if (book.getIsBorrowed() == 0 && book.getOrder_cardno().equals(borrower_cardno)) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("书借完了！");
            return false;
        }

        //查询借阅信息
        List<InfoBorrow> infoBorrowList = select.selectInfoBorrow(borrower_cardno);

        //获取借阅者身份,本科生可以借6本，研究生7本，博士生8本，专科生5本，老师9本

        String type = borrower.getType();
        int size = infoBorrowList.size();
        if (type.equals("本科生") && size == 6) {
            System.out.println("该本科生已经借了6本了，先去还书！");
            return false;
        }else if (type.equals("研究生") && size == 7) {
            System.out.println("该研究生已经借了7本了，先去还书！");
            return false;
        }else if (type.equals("博士生") && size == 8) {
            System.out.println("该博士生已经借了8本了，先去还书！");
            return false;
        }else if (type.equals("专科生") && size == 5) {
            System.out.println("该专科生已经借了5本了，先去还书！");
            return false;
        }else if (type.equals("老师") && size == 9) {
            System.out.println("该老师已经借了9本了，先去还书！");
            return false;
        }

        //生成借阅信息，加入到数据库中
        Book book = bookList.get(0);

        InfoBorrow infoBorrow = new InfoBorrow();
        infoBorrow.setBorrower_name(borrower.getName());
        infoBorrow.setBorrower_cardno(borrower_cardno);
        infoBorrow.setBook_name(book_name);
        infoBorrow.setBook_id(book.getId());
        infoBorrow.setStartDate(new Date());

        //借阅时间：老师一个月(30天)，博士生四个星期，研究生三个星期，本科生两个星期，专科生一个星期
        Calendar calendar = Calendar.getInstance();
        switch (type) {
            case "本科生": calendar.add(Calendar.DAY_OF_MONTH, 14); break;
            case "研究生": calendar.add(Calendar.DAY_OF_MONTH, 21); break;
            case "博士生": calendar.add(Calendar.DAY_OF_MONTH, 28); break;
            case "专科生": calendar.add(Calendar.DAY_OF_MONTH, 7); break;
            case "老师": calendar.add(Calendar.DAY_OF_MONTH, 30); break;
            default: break;
        }
        Date enddate = calendar.getTime();
        infoBorrow.setEndDate(enddate);
        Insert insert = new Insert();
        if (!insert.insertInfoBorrow(infoBorrow)) {
            System.out.println("生成借阅信息失败, 未借阅！");
            return false;
        }

        //更新借出的书的信息，isBorrowed为1
        update.updateBookOrder_cardno(book.getId(), "");
        return update.updateBookIsBorrowed(book.getId(), 1);
    }

    /**
     * 归还图书
     * @param book_name
     * @param borrower_cardno
     * @return
     */
    public boolean returnBook(String book_name, String borrower_cardno) {
        List<InfoBorrow> infoBorrowList = select.selectInfoBorrow(borrower_cardno);
        InfoBorrow infoBorrow = new InfoBorrow();
        for (InfoBorrow infoBorrow2 : infoBorrowList) {
            if (infoBorrow2.getBook_name().equals(book_name)) {
                infoBorrow = infoBorrow2;
                break;
            }
        }

        //更新书籍信息
        boolean b = update.updateBookIsBorrowed(infoBorrow.getBook_id(), 0);
        if (!b) {
            System.out.println("更新书籍信息失败，未归还！");
            return false;
        }


        //删除借阅信息
        Delete delete = new Delete();
        return delete.deleteInfoBorrow(book_name, borrower_cardno);
    }

    /**
     * 查询借阅信息
     * @param borrower_cardno
     * @return
     */
    public Vector<Vector<Object>> selectInfoBorrow(String borrower_cardno) {
        Vector<Vector<Object>> data = new Vector<>();
        Vector<Object> row;
        List<InfoBorrow> infoBorrowList = select.selectInfoBorrow(borrower_cardno);
        for (InfoBorrow infoBorrow : infoBorrowList) {
            row = new Vector<>();
            row.add(infoBorrow.getBorrower_name());
            row.add(infoBorrow.getBorrower_cardno());
            row.add(infoBorrow.getBook_name());
            row.add(infoBorrow.getBook_id());
            row.add(infoBorrow.getStartDate());
            row.add(infoBorrow.getEndDate());
            data.add(row);
        }

        return data;
    }
}
