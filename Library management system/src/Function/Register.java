package Function;

import db.Insert;
import db.Select;
import entity.Borrower;
import entity.Manger;

import java.util.List;

public class Register {
    Insert insert = new Insert();
    Select select = new Select();

    /**
     * 借阅者注册
     * @return
     */
    public boolean Borrower_register(Borrower borrower) {

        //核实借阅者是否存在
        List<Borrower> list = select.selectBorrower(borrower.getCardno());
        if (!list.isEmpty()) {
            System.out.println("该借阅者已经存在！");
            return false;
        }

        //系统管理员审核
        SystemManger_Function systemManger = new SystemManger_Function();
        if (!systemManger.checkBorrower(borrower)) {
            System.out.println("系统管理员审核未通过!");
            return false;
        }

        return insert.insertBorrower(borrower);
    }

    /**
     * 管理员注册
     * @param manger
     * @return
     */
    public boolean Manger_register(Manger manger) {

        //核实管理员是否存在
        List<Manger> list = select.selectManger(manger.getUsername());
        if (!list.isEmpty()) {
            System.out.println("该管理员已经存在！");
            return false;
        }

        return insert.insertManger(manger);
    }
}
