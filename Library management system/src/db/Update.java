package db;

import utils.JdbcUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {

    /**
     * 更新图书的idBorrowed
     * @param id
     * @return
     */
    public boolean updateBookIsBorrowed(String id, int isBorrowed) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "update book set isBorrowed = ? where id = ?";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setInt(1, isBorrowed);
            preparedStatement.setString(2, id);

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
     * 更新order_cardno
     * @param id
     * @param cardno
     * @return
     */
    public boolean updateBookOrder_cardno(String id, String cardno) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            //获取连接
            connection = JdbcUtils.getConnection();
            //定义sql
            String sql = "update book set order_cardno = ? where id = ?";
            //获取preparedStatement
            preparedStatement = connection.prepareStatement(sql);
            //给？赋值
            preparedStatement.setString(1, cardno);
            preparedStatement.setString(2, id);

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
