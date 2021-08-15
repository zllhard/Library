package GUI;

import Function.Register;
import entity.Borrower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class BorrowerRegisterView extends JFrame{
    JLabel titleLabel = new JLabel("填写注册信息", JLabel.CENTER);

    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    //组件
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
    JLabel typeLabel = new JLabel("借阅者类型：");
    JComboBox typeComboBox = new JComboBox();

    JButton registerButton = new JButton("注册");
    JButton resetButton = new JButton("重置");

    public BorrowerRegisterView() {
        super("图书管理系统");
        Container container = getContentPane();

        //字体、大小
        titleLabel.setFont(new Font("楷体", Font.BOLD, 30));
        titleLabel.setPreferredSize(new Dimension(0, 80));

        Font font = new Font("楷体", Font.PLAIN, 20);
        nameLabel.setFont(font);
        nameField.setPreferredSize(new Dimension(200, 30));
        cardnoLabel.setFont(font);
        cardnoField.setPreferredSize(new Dimension(200, 30));
        passwordLabel.setFont(font);
        passwordField.setPreferredSize(new Dimension(200, 30));
        majorLabel.setFont(font);
        majorField.setPreferredSize(new Dimension(200, 30));
        departmentLabel.setFont(font);
        departmentField.setPreferredSize(new Dimension(200, 30));
        typeLabel.setFont(font);
        registerButton.setFont(font);
        resetButton.setFont(font);


        typeComboBox.addItem("请选择");
        typeComboBox.addItem("本科生");
        typeComboBox.addItem("研究生");
        typeComboBox.addItem("博士生");
        typeComboBox.addItem("专科生");
        typeComboBox.addItem("老师");

        //把组件加入面板
        centerPanel.add(nameLabel);
        centerPanel.add(nameField);
        centerPanel.add(cardnoLabel);
        centerPanel.add(cardnoField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        centerPanel.add(majorLabel);
        centerPanel.add(majorField);
        centerPanel.add(departmentLabel);
        centerPanel.add(departmentField);
        centerPanel.add(typeLabel);
        centerPanel.add(typeComboBox);
        centerPanel.add(registerButton);

        registerButton.addActionListener(new BorrowerRegisterListener(this));
        centerPanel.add(resetButton);
        resetButton.addActionListener(new BorrowerRegisterListener(this));


        //弹簧布局
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(nameLabel), Spring.width(nameField)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, nameLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, nameLabel, 20, SpringLayout.NORTH, centerPanel);

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


        springLayout.putConstraint(SpringLayout.WEST, registerButton, 100, SpringLayout.WEST, typeLabel);
        springLayout.putConstraint(SpringLayout.NORTH, registerButton, 20, SpringLayout.SOUTH, typeLabel);

        springLayout.putConstraint(SpringLayout.WEST, resetButton, 50, SpringLayout.EAST, registerButton);
        springLayout.putConstraint(SpringLayout.NORTH, resetButton, 0, SpringLayout.NORTH, registerButton);


        container.add(titleLabel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);


        //加载图片
        URL imgUrl2 = BorrowerRegisterView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        setSize(500, 600);
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

    //注册监听
    private class BorrowerRegisterListener implements ActionListener {
        private BorrowerRegisterView borrowerRegisterView;

        public BorrowerRegisterListener(BorrowerRegisterView borrowerRegisterView) {
            this.borrowerRegisterView = borrowerRegisterView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if(jButtonText.equals("重置")) {
                borrowerRegisterView.getNameField().setText("");
                borrowerRegisterView.getCardnoField().setText("");
                borrowerRegisterView.getPasswordField().setText("");
                borrowerRegisterView.getMajorField().setText("");
                borrowerRegisterView.getDepartmentField().setText("");
            }else if (jButtonText.equals("注册")) {
                String name = borrowerRegisterView.getNameField().getText();
                String cardno = borrowerRegisterView.getCardnoField().getText();
                String password = borrowerRegisterView.getPasswordField().getText();
                String major = borrowerRegisterView.getMajorField().getText();
                String department = borrowerRegisterView.getDepartmentField().getText();
                String type = borrowerRegisterView.getTypeComboBox().getSelectedItem().toString();

                Borrower borrower = new Borrower(name, cardno, password, major, department, type);
                Register register = new Register();
                boolean b = register.Borrower_register(borrower);
                if (b) {
                    new BorrowerLoginView();
                    borrowerRegisterView.dispose();
                }else {
                    JOptionPane.showMessageDialog(borrowerRegisterView, "注册失败");
                }
            }
        }
    }

    public static void main(String[] args) {
        new BorrowerRegisterView();
    }
}
