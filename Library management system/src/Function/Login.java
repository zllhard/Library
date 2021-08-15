package Function;

import db.Select;
import entity.Borrower;
import entity.Manger;

import java.util.List;

public class Login {
    Select select = new Select();

    /**
     * 借阅者登录
     * @param cardno
     * @param password
     * @return
     */
    public boolean Borrower_login(String cardno, String password) {
        List<Borrower> list = select.selectBorrower(cardno);
        if (list.isEmpty()) {
            return  false;
        }
        Borrower borrower = list.get(0);
        return borrower.getPassword().equals(password);
    }

    /**
     * 管理员登录
     * @param username
     * @param password
     * @return
     */
    public boolean Manger_login(String username, String password) {
        List<Manger> list= select.selectManger(username);
        if (list.isEmpty()) {
            return false;
        }
        Manger manger = list.get(0);
        return manger.getPassword().equals(password);
    }
}
