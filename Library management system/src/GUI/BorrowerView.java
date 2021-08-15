package GUI;

import entity.Borrower;
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
import java.util.Vector;

public class BorrowerView extends JFrame{
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton searchButton = new JButton("搜索");
    JButton orderButton = new JButton("预约");
    JTextField searchField = new JTextField();

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preButton = new JButton("上一页");
    JButton nextButton = new JButton("下一页");

    BorrowerViewTable borrowerViewTable = new BorrowerViewTable();
    private int pageNow = 1;
    private  int pageSize = 20;

    public BorrowerView() {
        super("图书管理系统<借阅者端>");
        Container contentPane = getContentPane();

        searchField.setPreferredSize(new Dimension(200, 30));
        northPanel.add(searchField);
        northPanel.add(searchButton);

        searchButton.addActionListener(new BorrowListener(this));
        northPanel.add(orderButton);
        orderButton.addActionListener(new BorrowListener(this));
        JScrollPane jScrollPane = new JScrollPane(borrowerViewTable);
        southPanel.add(preButton);
        southPanel.add(nextButton);


        BookTableModel bookTableModel = BookTableModel.assembleModel(null);
        borrowerViewTable.setDataModel(bookTableModel);

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
    private class BorrowListener implements ActionListener {
        private BorrowerView borrowerView;

        public BorrowListener(BorrowerView borrowerView) {
            this.borrowerView = borrowerView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if(jButtonText.equals("搜索")) {
                String book_name = borrowerView.getSearchField().getText();
                Borrower borrower = new Borrower();
                Vector<Vector<Object>> data = borrower.selectBook(book_name);
                BookTableModel bookTableModel = BookTableModel.assembleModel(data);
                borrowerViewTable.setDataModel(bookTableModel);
                borrowerViewTable.renderRule();
            }else if (jButtonText.equals("上一页")) {

            }else if (jButtonText.equals("下一页")) {

            }else if (jButtonText.equals("预约")) {
                new BorrowerOrderView();

            }

        }
    }

    public static void main(String[] args) {
        new BorrowerView();
    }

}

class BorrowerOrderView extends JFrame {
    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);
    JButton orderButton = new JButton("预约");
    JButton cancelButton = new JButton("取消");
    JLabel orderLabel = new JLabel("书号：");
    JTextField orderField = new JTextField();
    JLabel cardnoLabel = new JLabel("卡号:");
    JTextField cardField = new JTextField();

    public BorrowerOrderView() {
        super("图书管理系统");
        Container contentPane = getContentPane();

        Font font = new Font("楷体", Font.PLAIN, 20);
        orderLabel.setFont(font);
        cardnoLabel.setFont(font);
        orderField.setPreferredSize(new Dimension(200, 30));
        cardField.setPreferredSize(new Dimension(200, 30));

        orderButton.setFont(font);
        cancelButton.setFont(font);

        jPanel.add(orderLabel);
        jPanel.add(orderField);
        jPanel.add(cardnoLabel);
        jPanel.add(cardField);
        jPanel.add(orderButton);
        orderButton.addActionListener(new OrderListener(this));
        jPanel.add(cancelButton);
        cancelButton.addActionListener(new OrderListener(this));

        Spring childWidth = Spring.sum(Spring.sum(Spring.width(orderLabel), Spring.width(orderField)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, orderLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, orderLabel, 20, SpringLayout.NORTH, jPanel);

        springLayout.putConstraint(SpringLayout.WEST, orderField, 20, SpringLayout.EAST, orderLabel);
        springLayout.putConstraint(SpringLayout.NORTH, orderField, 0, SpringLayout.NORTH, orderLabel);

        springLayout.putConstraint(SpringLayout.EAST, cardnoLabel, 0, SpringLayout.EAST, orderLabel);
        springLayout.putConstraint(SpringLayout.NORTH, cardnoLabel, 27, SpringLayout.SOUTH, orderLabel);

        springLayout.putConstraint(SpringLayout.EAST, cardField, 0, SpringLayout.EAST, orderField);
        springLayout.putConstraint(SpringLayout.NORTH, cardField, 20, SpringLayout.SOUTH, orderField);

        springLayout.putConstraint(SpringLayout.WEST, orderButton, 50, SpringLayout.WEST, cardnoLabel);
        springLayout.putConstraint(SpringLayout.NORTH, orderButton, 30, SpringLayout.SOUTH, cardnoLabel);

        springLayout.putConstraint(SpringLayout.WEST, cancelButton, 50, SpringLayout.EAST, orderButton);
        springLayout.putConstraint(SpringLayout.NORTH, cancelButton, 0, SpringLayout.NORTH, orderButton);

        contentPane.add(jPanel);

        URL imgUrl2 = BorrowerLoginView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public JTextField getOrderField() {
        return orderField;
    }

    public void setOrderField(JTextField orderField) {
        this.orderField = orderField;
    }

    public JTextField getCardField() {
        return cardField;
    }

    public void setCardField(JTextField cardField) {
        this.cardField = cardField;
    }

    // 监听类
    private class OrderListener implements ActionListener {
        private BorrowerOrderView borrowerOrderView;

        public OrderListener(BorrowerOrderView borrowerOrderView) {
            this.borrowerOrderView = borrowerOrderView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if(jButtonText.equals("预约")) {
                String id = borrowerOrderView.getOrderField().getText();
                String cardno = borrowerOrderView.getCardField().getText();
                Borrower borrower = new Borrower();
                boolean b = borrower.Order(id, cardno);
                if (b) {
                    JOptionPane.showMessageDialog(borrowerOrderView, "预约成功");
                }else {
                    JOptionPane.showMessageDialog(borrowerOrderView, "预约失败");
                }
            }else if(jButtonText.equals("取消")) {
                borrowerOrderView.dispose();
            }

        }
    }
}

class BorrowerViewTable extends JTable {

    public BorrowerViewTable() {
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

    public void setDataModel(BookTableModel bookTableModel) {
        this.setModel(bookTableModel);
    }

    //设置渲染方式
    public void renderRule() {
        Vector<String> columns = BookTableModel.getColumns();
        BookCellRender bookCellRender = new BookCellRender();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = getColumn(columns.get(i));
            column.setCellRenderer(bookCellRender);
        }
    }
}

class BookCellRender extends DefaultTableCellRenderer {
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

class BookTableModel extends DefaultTableModel {

    static Vector<String> columns = new Vector<>();
    static {
        columns.addElement("书名");
        columns.addElement("书号");
        columns.addElement("出版社");
        columns.addElement("价格");
        columns.addElement("是否借出");
        columns.addElement("书籍类型");
        columns.addElement("是否预约");
    }
    private BookTableModel() {
        super(null, columns);
    }

    private static BookTableModel bookTableModel = new BookTableModel();

    public static BookTableModel assembleModel(Vector<Vector<Object>> data) {
        bookTableModel.setDataVector(data, columns);
        return bookTableModel;
    }

    public static Vector<String> getColumns() {
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }


}







