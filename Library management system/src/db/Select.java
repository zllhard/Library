package db;

import entity.Book;
import entity.Borrower;
import entity.InfoBorrow;
import entity.Manger;
import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Select {

    /**
     * 查询借阅者信息
     * @param cardno
     * @return
     */
    public List<Borrower> selectBorrower(String cardno) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Borrower> list = new ArrayList<>();

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "select * from borrower where cardno = ?";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, cardno);
            //执行sql
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Borrower borrower = new Borrower();
                borrower.setName(resultSet.getString("name"));
                borrower.setCardno(resultSet.getString("cardno"));
                borrower.setPassword(resultSet.getString("password"));
                borrower.setMajor(resultSet.getString("major"));
                borrower.setDepartment(resultSet.getString("department"));
                borrower.setType(resultSet.getString("type"));

                list.add(borrower);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }

        return list;
    }

    /**
     * 获取管理员信息
     * @param username
     * @return
     */
    public List<Manger> selectManger(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Manger> list = new ArrayList<>();

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "select * from manger where username = ?";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, username);
            //执行sql
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Manger manger = new Manger();
                manger.setUsername(resultSet.getString("username"));
                manger.setPassword(resultSet.getString("password"));
                manger.setType(resultSet.getString("type"));
                list.add(manger);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }

        return list;
    }

    /**
     * 查询图书信息
     * @param name
     * @return
     */
    public List<Book> selectBook(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> list = new ArrayList<>();

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "select * from book where name = ?";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, name);
            //执行sql
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setName(resultSet.getString("name"));
                book.setId(resultSet.getString("id"));
                book.setPress(resultSet.getString("press"));
                book.setPrice(resultSet.getFloat("price"));
                book.setIsBorrowed(resultSet.getInt("isBorrowed"));
                book.setType(resultSet.getString("type"));
                book.setOrder_cardno(resultSet.getString("order_cardno"));
                list.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }

        return list;
    }


    public List<Book> selectBook2(String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> list = new ArrayList<>();

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "select * from book where id = ?";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, id);
            //执行sql
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setName(resultSet.getString("name"));
                book.setId(resultSet.getString("id"));
                book.setPress(resultSet.getString("press"));
                book.setPrice(resultSet.getFloat("price"));
                book.setIsBorrowed(resultSet.getInt("isBorrowed"));
                book.setType(resultSet.getString("type"));
                book.setOrder_cardno(resultSet.getString("order_cardno"));
                list.add(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }

        return list;
    }



    /**
     * 获取借阅信息
     * @param borrower_cardno
     * @return
     */
    public List<InfoBorrow> selectInfoBorrow(String borrower_cardno) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<InfoBorrow> list = new ArrayList<>();

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "select * from infoborrow where borrower_cardno = ?";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, borrower_cardno);
            //执行sql
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                InfoBorrow infoBorrow = new InfoBorrow();
                infoBorrow.setBorrower_name(resultSet.getString("borrower_name"));
                infoBorrow.setBorrower_cardno(resultSet.getString("borrower_cardno"));
                infoBorrow.setBook_name(resultSet.getString("book_name"));
                infoBorrow.setBook_id(resultSet.getString("book_id"));
                infoBorrow.setStartDate(resultSet.getDate("startdate"));
                infoBorrow.setEndDate(resultSet.getDate("enddate"));
                list.add(infoBorrow);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JdbcUtils.close(resultSet, preparedStatement, connection);
        }

        return list;
    }
}
