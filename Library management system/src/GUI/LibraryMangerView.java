package GUI;

import db.Select;
import entity.Borrower;
import entity.InfoBorrow;
import entity.LibraryManger;
import utils.DimensionUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class LibraryMangerView extends JFrame{
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton searchButton = new JButton("查询");
    JButton borrowButton = new JButton("借阅图书");
    JButton returnButton = new JButton("归还图书");
    JButton fireButton = new JButton("处理罚款");
    JTextField searchField = new JTextField();

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preButton = new JButton("上一页");
    JButton nextButton = new JButton("下一页");

    LibraryMangerViewTable libraryMangerViewTable = new LibraryMangerViewTable();
    private int pageNow = 1;
    private  int pageSize = 20;

    public LibraryMangerView() {
        super("图书管理系统<图书管理员端>");
        Container contentPane = getContentPane();

        searchField.setPreferredSize(new Dimension(200, 30));
        northPanel.add(searchField);
        northPanel.add(searchButton);

        searchButton.addActionListener(new LibraryListener(this));
        northPanel.add(fireButton);
        fireButton.addActionListener(new LibraryListener(this));
        northPanel.add(borrowButton);
        borrowButton.addActionListener(new LibraryListener(this));
        northPanel.add(returnButton);
        returnButton.addActionListener(new LibraryListener(this));
        fireButton.addActionListener(new LibraryListener(this));
        JScrollPane jScrollPane = new JScrollPane(libraryMangerViewTable);
        southPanel.add(preButton);
        southPanel.add(nextButton);


        InfoBorrowTableModel infoBorrowTableModel = InfoBorrowTableModel.assembleModel(null);
        libraryMangerViewTable.setDataModel(infoBorrowTableModel);

        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(southPanel, BorderLayout.SOUTH);
        contentPane.add(jScrollPane, BorderLayout.CENTER);


        //加载图片
        URL imgUrl2 = BorrowerLoginView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        //根据屏幕大小设置主界面
        setBounds(DimensionUtil.getBounds());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        setVisible(true);

    }

    public JTextField getSearchField() {
        return searchField;
    }

    public void setSearchField(JTextField searchField) {
        this.searchField = searchField;
    }

    // 监听类
    private class LibraryListener implements ActionListener {
        LibraryMangerView libraryMangerView;

        public LibraryListener(LibraryMangerView libraryMangerView) {
            this.libraryMangerView = libraryMangerView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if(jButtonText.equals("查询")) {
                String borrower_cardno = libraryMangerView.getSearchField().getText();
                LibraryManger libraryManger = new LibraryManger();
                Vector<Vector<Object>> data = libraryManger.selectInfoBorrow(borrower_cardno);
                InfoBorrowTableModel infoBorrowTableModel = InfoBorrowTableModel.assembleModel(data);
                libraryMangerViewTable.setDataModel(infoBorrowTableModel);
                libraryMangerViewTable.renderRule();
            }else if (jButtonText.equals("上一页")) {

            }else if (jButtonText.equals("下一页")) {

            }else if (jButtonText.equals("借阅图书")) {
                new BorrowBookView();
            }else if (jButtonText.equals("归还图书")) {
                new ReturnBookView();
            }else if (jButtonText.equals("处理罚款")) {
                String cardno = libraryMangerView.getSearchField().getText();
                long sec = 0;
                float fire = 0;
                Select select = new Select();
                List<InfoBorrow> infoBorrowList = select.selectInfoBorrow(cardno);
                for (InfoBorrow infoBorrow : infoBorrowList) {
                    if ((sec = new Date().getTime() - infoBorrow.getEndDate().getTime()) > 0) {
                        if (infoBorrow.getBook_name().equals("图书")) {
                            fire += sec/60/60/24 * 0.3;
                        } else if (infoBorrow.getBook_name().equals("杂志")) {
                            fire += sec/60/60/24 * 0.2;
                        } else if (infoBorrow.getBook_name().equals("论文")) {
                            fire += sec/60/60/24 * 0.4;
                        }
                    }
                }

                String message = "该借阅者需要支付" + fire + "元罚款！";

                if (fire == 0) {
                    JOptionPane.showMessageDialog(libraryMangerView, "没有逾期未还的书籍！");
                }else {
                    JOptionPane.showMessageDialog(libraryMangerView, message);
                }
            }

        }
    }

    public static void main(String[] args) {
        new LibraryMangerView();
    }

}

//借阅图书
class BorrowBookView extends JFrame {
    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);
    JButton borrowButton = new JButton("借阅");
    JButton cancelButton = new JButton("取消");
    JLabel bookNameLabel = new JLabel("书名：");
    JTextField bookNameField = new JTextField();
    JLabel cardnoLabel = new JLabel("卡号：");
    JTextField cardnoField = new JTextField();

    public BorrowBookView() {
        super("图书管理系统");
        Container contentPane = getContentPane();

        Font font = new Font("楷体", Font.PLAIN, 20);
        bookNameLabel.setFont(font);
        bookNameField.setPreferredSize(new Dimension(200, 30));
        cardnoLabel.setFont(font);
        cardnoField.setPreferredSize(new Dimension(200, 30));
        borrowButton.setFont(font);
        cancelButton.setFont(font);

        jPanel.add(bookNameLabel);
        jPanel.add(bookNameField);
        jPanel.add(cardnoLabel);
        jPanel.add(cardnoField);
        jPanel.add(borrowButton);
        borrowButton.addActionListener(new BorrowBookListener(this));
        jPanel.add(cancelButton);
        cancelButton.addActionListener(new BorrowBookListener(this));

        Spring childWidth = Spring.sum(Spring.sum(Spring.width(bookNameLabel), Spring.width(bookNameField)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, bookNameLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, bookNameLabel, 20, SpringLayout.NORTH, jPanel);

        springLayout.putConstraint(SpringLayout.WEST, bookNameField, 20, SpringLayout.EAST, bookNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, bookNameField, 0, SpringLayout.NORTH, bookNameLabel);

        springLayout.putConstraint(SpringLayout.EAST, cardnoLabel, 0, SpringLayout.EAST, bookNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, cardnoLabel, 27, SpringLayout.SOUTH, bookNameField);
        //布局passField
        springLayout.putConstraint(SpringLayout.EAST, cardnoField, 0, SpringLayout.EAST, bookNameField);
        springLayout.putConstraint(SpringLayout.NORTH, cardnoField, 20, SpringLayout.SOUTH, bookNameField);

        springLayout.putConstraint(SpringLayout.WEST, borrowButton, 50, SpringLayout.WEST, cardnoLabel);
        springLayout.putConstraint(SpringLayout.NORTH, borrowButton, 30, SpringLayout.SOUTH, cardnoLabel);

        springLayout.putConstraint(SpringLayout.WEST, cancelButton, 50, SpringLayout.EAST, borrowButton);
        springLayout.putConstraint(SpringLayout.NORTH, cancelButton, 0, SpringLayout.NORTH, borrowButton);

        contentPane.add(jPanel);

        URL imgUrl2 = BorrowerLoginView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public JTextField getBookNameField() {
        return bookNameField;
    }

    public void setBookNameField(JTextField bookNameField) {
        this.bookNameField = bookNameField;
    }

    public JTextField getCardnoField() {
        return cardnoField;
    }

    public void setCardnoField(JTextField cardnoField) {
        this.cardnoField = cardnoField;
    }

    // 监听类
    private class BorrowBookListener implements ActionListener {
        private BorrowBookView borrowBookView;

        public BorrowBookListener(BorrowBookView borrowBookView) {
            this.borrowBookView = borrowBookView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if(jButtonText.equals("借阅")) {
                String book_name = borrowBookView.getBookNameField().getText();
                String borrow_cardno = borrowBookView.getCardnoField().getText();
                LibraryManger libraryManger = new LibraryManger();
                boolean b = libraryManger.borrowBook(book_name, borrow_cardno);
                if (b) {
                    JOptionPane.showMessageDialog(borrowBookView, "借阅成功");
                }else {
                    JOptionPane.showMessageDialog(borrowBookView, "借阅失败");
                }
            }else if (jButtonText.equals("取消")) {
                borrowBookView.dispose();
            }

        }
    }
}

//归还图书
class ReturnBookView extends JFrame {
    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);
    JButton borrowButton = new JButton("归还");
    JButton cancelButton = new JButton("取消");
    JLabel bookNameLabel = new JLabel("书名：");
    JTextField bookNameField = new JTextField();
    JLabel cardnoLabel = new JLabel("卡号：");
    JTextField cardnoField = new JTextField();

    public ReturnBookView() {
        super("图书管理系统");
        Container contentPane = getContentPane();

        Font font = new Font("楷体", Font.PLAIN, 20);
        bookNameLabel.setFont(font);
        bookNameField.setPreferredSize(new Dimension(200, 30));
        cardnoLabel.setFont(font);
        cardnoField.setPreferredSize(new Dimension(200, 30));
        borrowButton.setFont(font);
        cancelButton.setFont(font);

        jPanel.add(bookNameLabel);
        jPanel.add(bookNameField);
        jPanel.add(cardnoLabel);
        jPanel.add(cardnoField);
        jPanel.add(borrowButton);
        borrowButton.addActionListener(new ReturnBookListener(this));
        jPanel.add(cancelButton);
        cancelButton.addActionListener(new ReturnBookListener(this));

        Spring childWidth = Spring.sum(Spring.sum(Spring.width(bookNameLabel), Spring.width(bookNameField)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, bookNameLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, bookNameLabel, 20, SpringLayout.NORTH, jPanel);

        springLayout.putConstraint(SpringLayout.WEST, bookNameField, 20, SpringLayout.EAST, bookNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, bookNameField, 0, SpringLayout.NORTH, bookNameLabel);

        springLayout.putConstraint(SpringLayout.EAST, cardnoLabel, 0, SpringLayout.EAST, bookNameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, cardnoLabel, 27, SpringLayout.SOUTH, bookNameField);
        //布局passField
        springLayout.putConstraint(SpringLayout.EAST, cardnoField, 0, SpringLayout.EAST, bookNameField);
        springLayout.putConstraint(SpringLayout.NORTH, cardnoField, 20, SpringLayout.SOUTH, bookNameField);

        springLayout.putConstraint(SpringLayout.WEST, borrowButton, 50, SpringLayout.WEST, cardnoLabel);
        springLayout.putConstraint(SpringLayout.NORTH, borrowButton, 30, SpringLayout.SOUTH, cardnoLabel);

        springLayout.putConstraint(SpringLayout.WEST, cancelButton, 50, SpringLayout.EAST, borrowButton);
        springLayout.putConstraint(SpringLayout.NORTH, cancelButton, 0, SpringLayout.NORTH, borrowButton);

        contentPane.add(jPanel);

        URL imgUrl2 = BorrowerLoginView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public JTextField getBookNameField() {
        return bookNameField;
    }

    public void setBookNameField(JTextField bookNameField) {
        this.bookNameField = bookNameField;
    }

    public JTextField getCardnoField() {
        return cardnoField;
    }

    public void setCardnoField(JTextField cardnoField) {
        this.cardnoField = cardnoField;
    }

    // 监听类
    private class ReturnBookListener implements ActionListener {
        private ReturnBookView returnBookView;

        public ReturnBookListener(ReturnBookView returnBookView) {
            this.returnBookView = returnBookView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if(jButtonText.equals("归还")) {
                String book_name = returnBookView.getBookNameField().getText();
                String borrow_cardno = returnBookView.getCardnoField().getText();
                LibraryManger libraryManger = new LibraryManger();
                boolean b = libraryManger.returnBook(book_name, borrow_cardno);
                if (b) {
                    JOptionPane.showMessageDialog(returnBookView, "归还成功");
                }else {
                    JOptionPane.showMessageDialog(returnBookView, "归还失败");
                }
                setVisible(false);
            }else if(jButtonText.equals("取消")) {
                returnBookView.dispose();
            }

        }
    }
}

class LibraryMangerViewTable extends JTable {

    public LibraryMangerViewTable() {
        //设置表头
        JTableHeader tableHeader = getTableHeader();
        tableHeader.setFont(new Font(null, Font.BOLD, 16));
        tableHeader.setForeground(Color.RED);
        //设置表格体
        setFont(new Font(null, Font.PLAIN, 14));
        setForeground(Color.BLACK);
        setGridColor(Color.BLACK);
        setRowHeight(30);
    }

    public void setDataModel(InfoBorrowTableModel infoBorrowTableModel) {
        this.setModel(infoBorrowTableModel);
    }

    //设置渲染方式
    public void renderRule() {
        Vector<String> columns = InfoBorrowTableModel.getColumns();
        InfoBorrowCellRender infoBorrowCellRender = new InfoBorrowCellRender();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(infoBorrowCellRender);
        }
    }
}

class InfoBorrowCellRender extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (row % 2 == 1) {
            setBackground(Color.LIGHT_GRAY);
        }else {
            setBackground(Color.WHITE);
        }
        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}

class InfoBorrowTableModel extends DefaultTableModel {

    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("姓名");
        columns.addElement("卡号");
        columns.addElement("书名");
        columns.addElement("书号");
        columns.addElement("借阅时间");
        columns.addElement("截止时间");
    }
    private InfoBorrowTableModel() {
        super(null, columns);
    }

    private static InfoBorrowTableModel infoBorrowTableModel = new InfoBorrowTableModel();

    public static InfoBorrowTableModel assembleModel(Vector<Vector<Object>> data) {
        infoBorrowTableModel.setDataVector(data, columns);
        return infoBorrowTableModel;
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }


}







