package db;

import entity.Borrower;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {

    /**
     * 删除借阅者
     * @param cardno
     * @return
     */
    public boolean deleteBorrower(String cardno) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "delete from borrower where cardno = ?";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, cardno);
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
     * 删除系统管理员
     * @param username
     * @return
     */
    public boolean deleteLibraryManger(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "delete from manger where username = ?";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, username);
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
     * 删除图书
     * @param id
     * @return
     */
    public boolean deleteBook(String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "delete from book where id = ?";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, id);
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
     * 删除借阅信息
     * @param book_name
     * @param borrow_cardno
     * @return
     */
    public boolean deleteInfoBorrow(String book_name, String borrow_cardno) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "delete from infoborrow where borrower_cardno = ? and book_name = ?";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, borrow_cardno);
            preparedStatement.setString(2, book_name);
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
