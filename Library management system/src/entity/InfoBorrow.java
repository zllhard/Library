package entity;

import java.util.Date;

public class InfoBorrow {

    private String borrower_name;          //借阅者姓名
    private String borrower_cardno;        //借阅者卡号
    private String book_name;              //借阅的书名
    private String book_id;                //借阅的书号
    private Date startDate;                 //借阅的日期
    private Date endDate;                   //归还日期（老师一个月，博士生四个星期，研究生三个星期，本科生两个星期，专科生一个星期）

    public InfoBorrow() {
    }

    public InfoBorrow(String borrower_name, String borrower_cardno, String book_name, String book_id, Date startDate, Date endDate) {
        this.borrower_name = borrower_name;
        this.borrower_cardno = borrower_cardno;
        this.book_name = book_name;
        this.book_id = book_id;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getBorrower_name() {
        return borrower_name;
    }

    public void setBorrower_name(String borrower_name) {
        this.borrower_name = borrower_name;
    }

    public String getBorrower_cardno() {
        return borrower_cardno;
    }

    public void setBorrower_cardno(String borrower_cardno) {
        this.borrower_cardno = borrower_cardno;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "InfoBorrow{" +
                "borrower_name='" + borrower_name + '\'' +
                ", borrower_cardno='" + borrower_cardno + '\'' +
                ", book_name='" + book_name + '\'' +
                ", book_id='" + book_id + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
