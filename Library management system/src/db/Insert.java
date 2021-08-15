package db;

import com.mysql.cj.jdbc.JdbcConnection;
import entity.Book;
import entity.Borrower;
import entity.InfoBorrow;
import entity.Manger;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {

    /**
     * 往数据库添加借阅者
     *
     * @param borrower
     * @return
     */
    public boolean insertBorrower(Borrower borrower) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "insert into borrower values(?,?,?,?,?,?)";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, borrower.getName());
            preparedStatement.setString(2, borrower.getCardno());
            preparedStatement.setString(3, borrower.getPassword());
            preparedStatement.setString(4, borrower.getMajor());
            preparedStatement.setString(5, borrower.getDepartment());
            preparedStatement.setString(6, borrower.getType());
            //执行sql
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }

        return false;
    }

    /**
     * 向数据库增加管理员
     * @param manger
     * @return
     */
    public boolean insertManger(Manger manger) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "insert into manger values(?,?,?)";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, manger.getUsername());
            preparedStatement.setString(2, manger.getPassword());
            preparedStatement.setString(3, manger.getType());
            //执行sql
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }

        return false;
    }

    /**
     * 添加图书
     * @param book
     * @return
     */
    public boolean insertBook(Book book) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "insert into book values(?,?,?,?,?,?,?)";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getId());
            preparedStatement.setString(3, book.getPress());
            preparedStatement.setFloat(4, book.getPrice());
            preparedStatement.setInt(5, 0);
            preparedStatement.setString(6, book.getType());
            preparedStatement.setString(7, "");

            //执行sql
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }

        return false;
    }


    /**
     * 添加借阅信息
     * @param infoBorrow
     * @return
     */
    public boolean insertInfoBorrow(InfoBorrow infoBorrow) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "insert into infoborrow values(?,?,?,?,?,?)";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, infoBorrow.getBorrower_name());
            preparedStatement.setString(2, infoBorrow.getBorrower_cardno());
            preparedStatement.setString(3, infoBorrow.getBook_name());
            preparedStatement.setString(4, infoBorrow.getBook_id());
            java.util.Date startDate = infoBorrow.getStartDate();
            java.sql.Date startDate2 = new Date(startDate.getTime());
            preparedStatement.setDate(5, startDate2);
            java.util.Date endDate = infoBorrow.getEndDate();
            java.sql.Date endDate2 = new Date(endDate.getTime());
            preparedStatement.setDate(6, endDate2);

            //执行sql
            int count = preparedStatement.executeUpdate();
            if (count == 1) {
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(preparedStatement, connection);
        }

        return false;
    }
}
