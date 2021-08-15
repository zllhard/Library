package GUI;

import db.Delete;
import entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class SystemMangerView extends JFrame {
    JLabel nameLabel = new JLabel("选择功能", JLabel.CENTER);
    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);

    JButton insertBookButton = new JButton("添加图书");
    JButton deleteBookButton = new JButton("删除图书");
    JButton insertBorrowerButton = new JButton("添加借阅者");
    JButton deleteBorrowerButton = new JButton("删除借阅者");
    JButton insertMangerButton = new JButton("添加图书管理员");
    JButton deleteMangerButton = new JButton("删除图书管理员");

    public SystemMangerView() {
        super("图书管理系统<系统管理员端>");
        Container container = getContentPane();

        nameLabel.setFont(new Font("华文行楷", Font.BOLD, 30));
        nameLabel.setPreferredSize(new Dimension(0, 60));
        Font font = new Font("楷体", Font.PLAIN, 20);
        insertBookButton.setFont(font);
        deleteBookButton.setFont(font);
        insertBorrowerButton.setFont(font);
        deleteBorrowerButton.setFont(font);
        insertMangerButton.setFont(font);
        deleteMangerButton.setFont(font);

        centerPanel.add(insertBookButton);
        insertBookButton.addActionListener(new SystemMangerViewListener(this));
        centerPanel.add(deleteBookButton);
        deleteBookButton.addActionListener(new SystemMangerViewListener(this));
        centerPanel.add(insertBorrowerButton);
        insertBorrowerButton.addActionListener(new SystemMangerViewListener(this));
        centerPanel.add(deleteBorrowerButton);
        deleteBorrowerButton.addActionListener(new SystemMangerViewListener(this));
        centerPanel.add(insertMangerButton);
        insertMangerButton.addActionListener(new SystemMangerViewListener(this));
        centerPanel.add(deleteMangerButton);
        deleteMangerButton.addActionListener(new SystemMangerViewListener(this));

        Spring childWidth = Spring.sum(Spring.sum(Spring.width(insertBookButton), Spring.width(deleteBookButton)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, insertBookButton, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, insertBookButton, 20, SpringLayout.NORTH, centerPanel);

        springLayout.putConstraint(SpringLayout.WEST, deleteBookButton, 20, SpringLayout.EAST, insertBookButton);
        springLayout.putConstraint(SpringLayout.NORTH, deleteBookButton, 0, SpringLayout.NORTH, insertBookButton);

        springLayout.putConstraint(SpringLayout.EAST, insertBorrowerButton, 0, SpringLayout.EAST, insertBookButton);
        springLayout.putConstraint(SpringLayout.NORTH, insertBorrowerButton, 27, SpringLayout.SOUTH, insertBookButton);

        springLayout.putConstraint(SpringLayout.WEST, deleteBorrowerButton, 0, SpringLayout.WEST, deleteBookButton);
        springLayout.putConstraint(SpringLayout.NORTH, deleteBorrowerButton, 27, SpringLayout.SOUTH, deleteBookButton);

        springLayout.putConstraint(SpringLayout.EAST, insertMangerButton, 0, SpringLayout.EAST, insertBorrowerButton);
        springLayout.putConstraint(SpringLayout.NORTH, insertMangerButton, 27, SpringLayout.SOUTH, insertBorrowerButton);

        springLayout.putConstraint(SpringLayout.WEST, deleteMangerButton, 0, SpringLayout.WEST, deleteBorrowerButton);
        springLayout.putConstraint(SpringLayout.NORTH, deleteMangerButton, 27, SpringLayout.SOUTH, deleteBorrowerButton);

        container.add(nameLabel, BorderLayout.NORTH);
        container.add(centerPanel);


        //加载图片
        URL imgUrl2 = BorrowerLoginView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private class SystemMangerViewListener implements ActionListener {
        private SystemMangerView systemMangerView;

        public SystemMangerViewListener(SystemMangerView systemMangerView) {
            this.systemMangerView = systemMangerView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if (jButtonText.equals("添加图书")) {
                new InsertBookView();
            } else if (jButtonText.equals("删除图书")) {
                new DeleteBookView();
            } else if (jButtonText.equals("添加借阅者")) {
                new InsertBorrowerView();
            } else if (jButtonText.equals("删除借阅者")) {
                new DeleteBorrowerView();
            } else if (jButtonText.equals("添加图书管理员")) {
                new InsertLibraryMangerView();
            } else if (jButtonText.equals("删除图书管理员")) {
                new DeleteLibraryMangerView();
            }
        }
    }

    public static void main(String[] args) {
        new SystemMangerView();
    }


}

//添加图书界面
class InsertBookView extends JFrame {
    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);
    JButton insertButton = new JButton("添加");
    JButton cancelButton = new JButton("取消");
    JLabel nameLabel = new JLabel("书名：");
    JTextField nameField = new JTextField();
    JLabel idLabel = new JLabel("书号：");
    JTextField idField = new JTextField();
    JLabel pressLabel = new JLabel("出版社：");
    JTextField pressField = new JTextField();
    JLabel priceLabel = new JLabel("价格：");
    JTextField priceField = new JTextField();
    JLabel typeLabel = new JLabel("类型：");
    JComboBox typeComboBox = new JComboBox();

    public InsertBookView() {
        super("图书管理系统");
        Container contentPane = getContentPane();

        Font font = new Font("楷体", Font.PLAIN, 20);
        nameLabel.setFont(font);
        idLabel.setFont(font);
        pressLabel.setFont(font);
        priceLabel.setFont(font);
        typeLabel.setFont(font);
        nameField.setPreferredSize(new Dimension(200, 30));
        idField.setPreferredSize(new Dimension(200, 30));
        pressField.setPreferredSize(new Dimension(200, 30));
        priceField.setPreferredSize(new Dimension(200, 30));

        insertButton.setFont(font);
        cancelButton.setFont(font);

        typeComboBox.addItem("请选择");
        typeComboBox.addItem("图书");
        typeComboBox.addItem("杂志");
        typeComboBox.addItem("论文");


        jPanel.add(nameLabel);
        jPanel.add(nameField);
        jPanel.add(idLabel);
        jPanel.add(idField);
        jPanel.add(pressLabel);
        jPanel.add(pressField);
        jPanel.add(priceLabel);
        jPanel.add(priceField);
        jPanel.add(typeLabel);
        jPanel.add(typeComboBox);

        jPanel.add(insertButton);
        insertButton.addActionListener(new InsertBookListener(this));
        jPanel.add(cancelButton);
        cancelButton.addActionListener(new InsertBookListener(this));

        Spring childWidth = Spring.sum(Spring.sum(Spring.width(nameLabel), Spring.width(nameField)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, nameLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 20, SpringLayout.NORTH, jPanel);

        springLayout.putConstraint(SpringLayout.WEST, nameField, 20, SpringLayout.EAST, nameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, nameField, 0, SpringLayout.NORTH, nameLabel);

        springLayout.putConstraint(SpringLayout.EAST, idLabel, 0, SpringLayout.EAST, nameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, idLabel, 27, SpringLayout.SOUTH, nameLabel);

        springLayout.putConstraint(SpringLayout.EAST, idField, 0, SpringLayout.EAST, nameField);
        springLayout.putConstraint(SpringLayout.NORTH, idField, 20, SpringLayout.SOUTH, nameField);

        springLayout.putConstraint(SpringLayout.EAST, pressLabel, 0, SpringLayout.EAST, idLabel);
        springLayout.putConstraint(SpringLayout.NORTH, pressLabel, 27, SpringLayout.SOUTH, idLabel);

        springLayout.putConstraint(SpringLayout.EAST, pressField, 0, SpringLayout.EAST, idField);
        springLayout.putConstraint(SpringLayout.NORTH, pressField, 20, SpringLayout.SOUTH, idField);

        springLayout.putConstraint(SpringLayout.EAST, priceLabel, 0, SpringLayout.EAST, pressLabel);
        springLayout.putConstraint(SpringLayout.NORTH, priceLabel, 27, SpringLayout.SOUTH, pressLabel);

        springLayout.putConstraint(SpringLayout.EAST, priceField, 0, SpringLayout.EAST, pressField);
        springLayout.putConstraint(SpringLayout.NORTH, priceField, 20, SpringLayout.SOUTH, pressField);

        springLayout.putConstraint(SpringLayout.EAST, typeLabel, 0, SpringLayout.EAST, priceLabel);
        springLayout.putConstraint(SpringLayout.NORTH, typeLabel, 27, SpringLayout.SOUTH, priceLabel);

        springLayout.putConstraint(SpringLayout.WEST, typeComboBox, 0, SpringLayout.WEST, priceField);
        springLayout.putConstraint(SpringLayout.NORTH, typeComboBox, 20, SpringLayout.SOUTH, priceField);

        springLayout.putConstraint(SpringLayout.WEST, insertButton, 50, SpringLayout.WEST, typeLabel);
        springLayout.putConstraint(SpringLayout.NORTH, insertButton, 30, SpringLayout.SOUTH, typeLabel);

        springLayout.putConstraint(SpringLayout.WEST, cancelButton, 50, SpringLayout.EAST, insertButton);
        springLayout.putConstraint(SpringLayout.NORTH, cancelButton, 0, SpringLayout.NORTH, insertButton);

        contentPane.add(jPanel);

        URL imgUrl2 = BorrowerLoginView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JTextField getIdField() {
        return idField;
    }

    public void setIdField(JTextField idField) {
        this.idField = idField;
    }

    public JTextField getPressField() {
        return pressField;
    }

    public void setPressField(JTextField pressField) {
        this.pressField = pressField;
    }

    public JTextField getPriceField() {
        return priceField;
    }

    public void setPriceField(JTextField priceField) {
        this.priceField = priceField;
    }

    public JComboBox getTypeComboBox() {
        return typeComboBox;
    }

    public void setTypeComboBox(JComboBox typeComboBox) {
        this.typeComboBox = typeComboBox;
    }

    // 监听类
    private class InsertBookListener implements ActionListener {
        private InsertBookView insertBookView;

        public InsertBookListener(InsertBookView insertBookView) {
            this.insertBookView = insertBookView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if (jButtonText.equals("添加")) {
                String name = insertBookView.getNameField().getText();
                String id = insertBookView.getIdField().getText();
                String press = insertBookView.getPressField().getText();
                Float price = Float.valueOf(insertBookView.getPriceField().getText());
                String type = insertBookView.getTypeComboBox().getSelectedItem().toString();

                Book book = new Book(name, id, press, price, type);
                SystemManger systemManger = new SystemManger();
                boolean b = systemManger.insertBook(book);
                if (b) {
                    JOptionPane.showMessageDialog(insertBookView, "添加图书成功！");
                } else {
                    JOptionPane.showMessageDialog(insertBookView, "添加图书失败！");
                }
                insertBookView.getNameField().setText("");
                insertBookView.getIdField().setText("");
                insertBookView.getPressField().setText("");
                insertBookView.getPriceField().setText("");
                insertBookView.getTypeComboBox().setSelectedIndex(0);
            } else if (jButtonText.equals("取消")) {
                insertBookView.dispose();
            }

        }
    }
}

//添加图书界面
class InsertBorrowerView extends JFrame {
    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);
    JButton insertButton = new JButton("添加");
    JButton cancelButton = new JButton("取消");
    JLabel nameLabel = new JLabel("姓名：");
    JTextField nameField = new JTextField();
    JLabel cardnoLabel = new JLabel("卡号：");
    JTextField cardnoField = new JTextField();
    JLabel passwordLabel = new JLabel("密码：");
    JTextField passwordField = new JPasswordField();
    JLabel majorLabel = new JLabel("专业：");
    JTextField majorField = new JTextField();
    JLabel departmentLabel = new JLabel("宿舍：");
    JTextField departmentField = new JTextField();
    JLabel typeLabel = new JLabel("类型：");
    JComboBox typeComboBox = new JComboBox();

    public InsertBorrowerView() {
        super("图书管理系统");
        Container contentPane = getContentPane();

        Font font = new Font("楷体", Font.PLAIN, 20);
        nameLabel.setFont(font);
        cardnoLabel.setFont(font);
        passwordLabel.setFont(font);
        majorLabel.setFont(font);
        departmentLabel.setFont(font);
        typeLabel.setFont(font);
        nameField.setPreferredSize(new Dimension(200, 30));
        cardnoField.setPreferredSize(new Dimension(200, 30));
        passwordField.setPreferredSize(new Dimension(200, 30));
        majorField.setPreferredSize(new Dimension(200, 30));
        departmentField.setPreferredSize(new Dimension(200, 30));

        insertButton.setFont(font);
        cancelButton.setFont(font);

        typeComboBox.addItem("请选择");
        typeComboBox.addItem("本科生");
        typeComboBox.addItem("研究生");
        typeComboBox.addItem("博士生");
        typeComboBox.addItem("专科生");
        typeComboBox.addItem("老师");


        jPanel.add(nameLabel);
        jPanel.add(nameField);
        jPanel.add(cardnoLabel);
        jPanel.add(cardnoField);
        jPanel.add(passwordLabel);
        jPanel.add(passwordField);
        jPanel.add(majorField);
        jPanel.add(majorLabel);
        jPanel.add(departmentField);
        jPanel.add(departmentLabel);
        jPanel.add(typeLabel);
        jPanel.add(typeComboBox);

        jPanel.add(insertButton);
        insertButton.addActionListener(new InsertBorrowerListener(this));
        jPanel.add(cancelButton);
        cancelButton.addActionListener(new InsertBorrowerListener(this));

        Spring childWidth = Spring.sum(Spring.sum(Spring.width(nameLabel), Spring.width(nameField)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, nameLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 20, SpringLayout.NORTH, jPanel);

        springLayout.putConstraint(SpringLayout.WEST, nameField, 20, SpringLayout.EAST, nameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, nameField, 0, SpringLayout.NORTH, nameLabel);

        springLayout.putConstraint(SpringLayout.EAST, cardnoLabel, 0, SpringLayout.EAST, nameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, cardnoLabel, 27, SpringLayout.SOUTH, nameLabel);

        springLayout.putConstraint(SpringLayout.EAST, cardnoField, 0, SpringLayout.EAST, nameField);
        springLayout.putConstraint(SpringLayout.NORTH, cardnoField, 20, SpringLayout.SOUTH, nameField);

        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, cardnoLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 27, SpringLayout.SOUTH, cardnoLabel);

        springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, cardnoField);
        springLayout.putConstraint(SpringLayout.NORTH, passwordField, 20, SpringLayout.SOUTH, cardnoField);

        springLayout.putConstraint(SpringLayout.EAST, majorLabel, 0, SpringLayout.EAST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, majorLabel, 27, SpringLayout.SOUTH, passwordLabel);

        springLayout.putConstraint(SpringLayout.EAST, majorField, 0, SpringLayout.EAST, passwordField);
        springLayout.putConstraint(SpringLayout.NORTH, majorField, 20, SpringLayout.SOUTH, passwordField);

        springLayout.putConstraint(SpringLayout.EAST, departmentLabel, 0, SpringLayout.EAST, majorLabel);
        springLayout.putConstraint(SpringLayout.NORTH, departmentLabel, 27, SpringLayout.SOUTH, majorLabel);

        springLayout.putConstraint(SpringLayout.EAST, departmentField, 0, SpringLayout.EAST, majorField);
        springLayout.putConstraint(SpringLayout.NORTH, departmentField, 20, SpringLayout.SOUTH, majorField);

        springLayout.putConstraint(SpringLayout.EAST, typeLabel, 0, SpringLayout.EAST, departmentLabel);
        springLayout.putConstraint(SpringLayout.NORTH, typeLabel, 27, SpringLayout.SOUTH, departmentLabel);

        springLayout.putConstraint(SpringLayout.WEST, typeComboBox, 0, SpringLayout.WEST, departmentField);
        springLayout.putConstraint(SpringLayout.NORTH, typeComboBox, 20, SpringLayout.SOUTH, departmentField);

        springLayout.putConstraint(SpringLayout.WEST, insertButton, 50, SpringLayout.WEST, typeLabel);
        springLayout.putConstraint(SpringLayout.NORTH, insertButton, 30, SpringLayout.SOUTH, typeLabel);

        springLayout.putConstraint(SpringLayout.WEST, cancelButton, 50, SpringLayout.EAST, insertButton);
        springLayout.putConstraint(SpringLayout.NORTH, cancelButton, 0, SpringLayout.NORTH, insertButton);

        contentPane.add(jPanel);

        URL imgUrl2 = BorrowerLoginView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        setSize(400, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public JTextField getNameField() {
        return nameField;
    }

    public void setNameField(JTextField nameField) {
        this.nameField = nameField;
    }

    public JTextField getCardnoField() {
        return cardnoField;
    }

    public void setCardnoField(JTextField cardnoField) {
        this.cardnoField = cardnoField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JTextField passwordField) {
        this.passwordField = passwordField;
    }

    public JTextField getMajorField() {
        return majorField;
    }

    public void setMajorField(JTextField majorField) {
        this.majorField = majorField;
    }

    public JTextField getDepartmentField() {
        return departmentField;
    }

    public void setDepartmentField(JTextField departmentField) {
        this.departmentField = departmentField;
    }

    public JComboBox getTypeComboBox() {
        return typeComboBox;
    }

    public void setTypeComboBox(JComboBox typeComboBox) {
        this.typeComboBox = typeComboBox;
    }

    // 监听类
    private class InsertBorrowerListener implements ActionListener {
        private InsertBorrowerView insertBorrowerView;

        public InsertBorrowerListener(InsertBorrowerView insertBorrowerView) {
            this.insertBorrowerView = insertBorrowerView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if (jButtonText.equals("添加")) {
                String name = insertBorrowerView.getNameField().getText();
                String cardno = insertBorrowerView.getCardnoField().getText();
                String password = insertBorrowerView.getPasswordField().getText();
                String major = insertBorrowerView.getMajorField().getText();
                String department = insertBorrowerView.getDepartmentField().getText();
                String type = insertBorrowerView.getTypeComboBox().getSelectedItem().toString();

                Borrower borrower = new Borrower(name, cardno, password, major, department, type);
                SystemManger systemManger = new SystemManger();
                boolean b = systemManger.insertBorrower(borrower);
                if (b) {
                    JOptionPane.showMessageDialog(insertBorrowerView, "添加借阅者成功！");
                } else {
                    JOptionPane.showMessageDialog(insertBorrowerView, "添加借阅者失败！");
                }
                insertBorrowerView.getNameField().setText("");
                insertBorrowerView.getCardnoField().setText("");
                insertBorrowerView.getPasswordField().setText("");
                insertBorrowerView.getMajorField().setText("");
                insertBorrowerView.getDepartmentField().setText("");
                insertBorrowerView.getTypeComboBox().setSelectedIndex(0);

            } else if (jButtonText.equals("取消")) {
                insertBorrowerView.dispose();
            }
        }
    }
}

//添加图书管理员
class InsertLibraryMangerView extends JFrame {
    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);
    JButton insertButton = new JButton("添加");
    JButton cancelButton = new JButton("取消");
    JLabel usernameLabel = new JLabel("用户名：");
    JTextField usernameField = new JTextField();
    JLabel passwordLabel = new JLabel("密码：");
    JTextField passwordField = new JPasswordField();

    public InsertLibraryMangerView() {
        super("图书管理系统");
        Container contentPane = getContentPane();

        Font font = new Font("楷体", Font.PLAIN, 20);
        usernameLabel.setFont(font);
        passwordLabel.setFont(font);
        usernameField.setPreferredSize(new Dimension(200, 30));
        passwordField.setPreferredSize(new Dimension(200, 30));

        insertButton.setFont(font);
        cancelButton.setFont(font);

        jPanel.add(usernameLabel);
        jPanel.add(usernameField);
        jPanel.add(passwordLabel);
        jPanel.add(passwordField);

        jPanel.add(insertButton);
        insertButton.addActionListener(new InsertLibraryMangerListener(this));
        jPanel.add(cancelButton);
        cancelButton.addActionListener(new InsertLibraryMangerListener(this));

        Spring childWidth = Spring.sum(Spring.sum(Spring.width(usernameLabel), Spring.width(usernameField)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, usernameLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 20, SpringLayout.NORTH, jPanel);

        springLayout.putConstraint(SpringLayout.WEST, usernameField, 20, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameField, 0, SpringLayout.NORTH, usernameLabel);

        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 27, SpringLayout.SOUTH, usernameLabel);

        springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, usernameField);
        springLayout.putConstraint(SpringLayout.NORTH, passwordField, 20, SpringLayout.SOUTH, usernameField);

        springLayout.putConstraint(SpringLayout.WEST, insertButton, 50, SpringLayout.WEST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, insertButton, 30, SpringLayout.SOUTH, passwordLabel);

        springLayout.putConstraint(SpringLayout.WEST, cancelButton, 50, SpringLayout.EAST, insertButton);
        springLayout.putConstraint(SpringLayout.NORTH, cancelButton, 0, SpringLayout.NORTH, insertButton);

        contentPane.add(jPanel);

        URL imgUrl2 = BorrowerLoginView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    public JTextField getPasswordField() {
        return passwordField;
    }

    public void setPasswordField(JTextField passwordField) {
        this.passwordField = passwordField;
    }

    // 监听类
    private class InsertLibraryMangerListener implements ActionListener {
        private  InsertLibraryMangerView insertLibraryMangerView;

        public InsertLibraryMangerListener(InsertLibraryMangerView insertLibraryMangerView) {
            this.insertLibraryMangerView = insertLibraryMangerView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if (jButtonText.equals("添加")) {
                String username = insertLibraryMangerView.getUsernameField().getText();
                String password = insertLibraryMangerView.getPasswordField().getText();

                Manger libraryManger = new Manger(username, password, "图书管理员");
                SystemManger systemManger = new SystemManger();
                boolean b = systemManger.insertManger(libraryManger);
                if(b) {
                    JOptionPane.showMessageDialog(insertLibraryMangerView, "添加图书管理员成功！");
                }else {
                    JOptionPane.showMessageDialog(insertLibraryMangerView, "添加图书管理员失败！");
                }

                insertLibraryMangerView.getUsernameField().setText("");
                insertLibraryMangerView.getPasswordField().setText("");
            } else if(jButtonText.equals("取消")){
                insertLibraryMangerView.dispose();
            }
        }
    }
}

//删除图书界面
class DeleteBookView extends JFrame {
    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);
    JButton deleteButton = new JButton("删除");
    JButton cancelButton = new JButton("取消");
    JLabel idLabel = new JLabel("书号：");
    JTextField idField = new JTextField();

    public DeleteBookView() {
        super("图书管理系统");
        Container contentPane = getContentPane();

        Font font = new Font("楷体", Font.PLAIN, 20);
        idLabel.setFont(font);
        idField.setPreferredSize(new Dimension(200, 30));

        deleteButton.setFont(font);
        cancelButton.setFont(font);

        jPanel.add(idLabel);
        jPanel.add(idField);

        jPanel.add(deleteButton);
        deleteButton.addActionListener(new DeleteBookListener(this));
        jPanel.add(cancelButton);
        cancelButton.addActionListener(new DeleteBookListener(this));

        Spring childWidth = Spring.sum(Spring.sum(Spring.width(idLabel), Spring.width(idField)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, idLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, idLabel, 20, SpringLayout.NORTH, jPanel);

        springLayout.putConstraint(SpringLayout.WEST, idField, 20, SpringLayout.EAST, idLabel);
        springLayout.putConstraint(SpringLayout.NORTH, idField, 0, SpringLayout.NORTH, idLabel);

        springLayout.putConstraint(SpringLayout.WEST, deleteButton, 50, SpringLayout.WEST, idLabel);
        springLayout.putConstraint(SpringLayout.NORTH, deleteButton, 30, SpringLayout.SOUTH, idLabel);

        springLayout.putConstraint(SpringLayout.WEST, cancelButton, 50, SpringLayout.EAST, deleteButton);
        springLayout.putConstraint(SpringLayout.NORTH, cancelButton, 0, SpringLayout.NORTH, deleteButton);

        contentPane.add(jPanel);

        URL imgUrl2 = BorrowerLoginView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public JTextField getIdField() {
        return idField;
    }

    public void setIdField(JTextField idField) {
        this.idField = idField;
    }

    // 监听类
    private class DeleteBookListener implements ActionListener {
        private DeleteBookView deleteBookView;

        public DeleteBookListener(DeleteBookView deleteBookView) {
            this.deleteBookView = deleteBookView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if (jButtonText.equals("删除")) {
                String id = deleteBookView.getIdField().getText();

                SystemManger systemManger = new SystemManger();
                boolean b = systemManger.deleteBook(id);
                if (b) {
                    JOptionPane.showMessageDialog(deleteBookView, "删除图书成功！");
                } else {
                    JOptionPane.showMessageDialog(deleteBookView, "删除图书失败！");
                }

                deleteBookView.getIdField().setText("");
            } else if (jButtonText.equals("取消")) {
                deleteBookView.dispose();
            }

        }
    }
}

//删除借阅者界面
class DeleteBorrowerView extends JFrame {
    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);
    JButton deleteButton = new JButton("删除");
    JButton cancelButton = new JButton("取消");
    JLabel cardnoLabel = new JLabel("卡号：");
    JTextField cardnoField = new JTextField();

    public DeleteBorrowerView() {
        super("图书管理系统");
        Container contentPane = getContentPane();

        Font font = new Font("楷体", Font.PLAIN, 20);
        cardnoLabel.setFont(font);
        cardnoField.setPreferredSize(new Dimension(200, 30));

        deleteButton.setFont(font);
        cancelButton.setFont(font);

        jPanel.add(cardnoLabel);
        jPanel.add(cardnoField);

        jPanel.add(deleteButton);
        deleteButton.addActionListener(new DeleteBorrowerListener(this));
        jPanel.add(cancelButton);
        cancelButton.addActionListener(new DeleteBorrowerListener(this));

        Spring childWidth = Spring.sum(Spring.sum(Spring.width(cardnoLabel), Spring.width(cardnoField)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, cardnoLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, cardnoLabel, 20, SpringLayout.NORTH, jPanel);

        springLayout.putConstraint(SpringLayout.WEST, cardnoField, 20, SpringLayout.EAST, cardnoLabel);
        springLayout.putConstraint(SpringLayout.NORTH, cardnoField, 0, SpringLayout.NORTH, cardnoLabel);

        springLayout.putConstraint(SpringLayout.WEST, deleteButton, 50, SpringLayout.WEST, cardnoLabel);
        springLayout.putConstraint(SpringLayout.NORTH, deleteButton, 30, SpringLayout.SOUTH, cardnoLabel);

        springLayout.putConstraint(SpringLayout.WEST, cancelButton, 50, SpringLayout.EAST, deleteButton);
        springLayout.putConstraint(SpringLayout.NORTH, cancelButton, 0, SpringLayout.NORTH, deleteButton);

        contentPane.add(jPanel);

        URL imgUrl2 = BorrowerLoginView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public JTextField getCardnoField() {
        return cardnoField;
    }

    public void setCardnoField(JTextField cardnoField) {
        this.cardnoField = cardnoField;
    }

    // 监听类
    private class DeleteBorrowerListener implements ActionListener {
        private DeleteBorrowerView deleteBorrowerView;

        public DeleteBorrowerListener(DeleteBorrowerView deleteBorrowerView) {
            this.deleteBorrowerView = deleteBorrowerView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if (jButtonText.equals("删除")) {
                String cardno = deleteBorrowerView.getCardnoField().getText();

                SystemManger systemManger = new SystemManger();
                boolean b = systemManger.deleteBorrower(cardno);
                if (b) {
                    JOptionPane.showMessageDialog(deleteBorrowerView, "删除借阅者成功！");
                } else {
                    JOptionPane.showMessageDialog(deleteBorrowerView, "删除借阅者失败！");
                }
                deleteBorrowerView.getCardnoField().setText("");
            } else if (jButtonText.equals("取消")) {
                deleteBorrowerView.dispose();
            }

        }
    }
}

//删除图书管理员
class DeleteLibraryMangerView extends JFrame {
    SpringLayout springLayout = new SpringLayout();
    JPanel jPanel = new JPanel(springLayout);
    JButton deleteButton = new JButton("删除");
    JButton cancelButton = new JButton("取消");
    JLabel usernameLabel = new JLabel("用户名：");
    JTextField usernameField = new JTextField();

    public DeleteLibraryMangerView() {
        super("图书管理系统");
        Container contentPane = getContentPane();

        Font font = new Font("楷体", Font.PLAIN, 20);
        usernameLabel.setFont(font);
        usernameField.setPreferredSize(new Dimension(200, 30));

        deleteButton.setFont(font);
        cancelButton.setFont(font);

        jPanel.add(usernameLabel);
        jPanel.add(usernameField);

        jPanel.add(deleteButton);
        deleteButton.addActionListener(new DeleteLibraryMangerListener(this));
        jPanel.add(cancelButton);
        cancelButton.addActionListener(new DeleteLibraryMangerListener(this));

        Spring childWidth = Spring.sum(Spring.sum(Spring.width(usernameLabel), Spring.width(usernameField)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, usernameLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, jPanel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 20, SpringLayout.NORTH, jPanel);

        springLayout.putConstraint(SpringLayout.WEST, usernameField, 20, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameField, 0, SpringLayout.NORTH, usernameLabel);

        springLayout.putConstraint(SpringLayout.WEST, deleteButton, 50, SpringLayout.WEST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, deleteButton, 30, SpringLayout.SOUTH, usernameLabel);



        springLayout.putConstraint(SpringLayout.WEST, cancelButton, 50, SpringLayout.EAST, deleteButton);
        springLayout.putConstraint(SpringLayout.NORTH, cancelButton, 0, SpringLayout.NORTH, deleteButton);

        contentPane.add(jPanel);

        URL imgUrl2 = BorrowerLoginView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField = usernameField;
    }

    // 监听类
    private class DeleteLibraryMangerListener implements ActionListener {
        private DeleteLibraryMangerView deleteLibraryMangerView;

        public DeleteLibraryMangerListener(DeleteLibraryMangerView deleteLibraryMangerView) {
            this.deleteLibraryMangerView = deleteLibraryMangerView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if (jButtonText.equals("删除")) {
                String username = deleteLibraryMangerView.getUsernameField().getText();
                SystemManger systemManger = new SystemManger();
                boolean b = systemManger.deleteLibraryManger(username);
                if (b) {
                    JOptionPane.showMessageDialog(deleteLibraryMangerView, "删除图书管理员成功！");
                }else {
                    JOptionPane.showMessageDialog(deleteLibraryMangerView, "删除图书管理员失败！");
                }

                deleteLibraryMangerView.getUsernameField().setText("");
            } else if (jButtonText.equals("取消")) {
                deleteLibraryMangerView.dispose();
            }

        }
    }
}
