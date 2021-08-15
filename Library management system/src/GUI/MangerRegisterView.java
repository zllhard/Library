package GUI;

import Function.Register;
import entity.Manger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class MangerRegisterView extends JFrame{
    JLabel nameLabel = new JLabel("填写注册信息", JLabel.CENTER);

    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    //组件
    JLabel usernameLabel = new JLabel("用户名：");
    JTextField usernameField = new JTextField();
    JLabel passwordLabel = new JLabel("密码：");
    JTextField passwordField = new JPasswordField();
    JLabel typeLabel = new JLabel("管理员类型");
    JComboBox typeComboBox = new JComboBox();

    JButton loginButton = new JButton("注册");
    JButton resetButton = new JButton("重置");

    public MangerRegisterView() {
        super("图书管理系统");
        Container container = getContentPane();

        //字体
        nameLabel.setFont(new Font("华文行楷", Font.BOLD, 30));
        nameLabel.setPreferredSize(new Dimension(0, 80));

        Font font = new Font("楷体", Font.PLAIN, 20);
        usernameLabel.setFont(font);
        usernameField.setPreferredSize(new Dimension(200, 30));
        passwordLabel.setFont(font);
        passwordField.setPreferredSize(new Dimension(200, 30));
        loginButton.setFont(font);
        resetButton.setFont(font);


        typeComboBox.addItem("请选择");
        typeComboBox.addItem("系统管理员");
        typeComboBox.addItem("图书管理员");
        //把组件加入面板
        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        centerPanel.add(typeLabel);
        centerPanel.add(typeComboBox);
        centerPanel.add(loginButton);

        loginButton.addActionListener(new MangerRegisterListener(this));
        centerPanel.add(resetButton);
        resetButton.addActionListener(new MangerRegisterListener(this));



        //弹簧布局
        //布局cardnoLabel
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(usernameLabel), Spring.width(usernameField)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, usernameLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 20, SpringLayout.NORTH, centerPanel);

        springLayout.putConstraint(SpringLayout.WEST, usernameField, 20, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameField, 0, SpringLayout.NORTH, usernameLabel);

        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 27, SpringLayout.SOUTH, usernameLabel);

        springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, usernameField);
        springLayout.putConstraint(SpringLayout.NORTH, passwordField, 20, SpringLayout.SOUTH, usernameField);

        springLayout.putConstraint(SpringLayout.EAST, typeLabel, 0, SpringLayout.EAST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, typeLabel, 27, SpringLayout.SOUTH, passwordLabel);

        springLayout.putConstraint(SpringLayout.WEST, typeComboBox, 0, SpringLayout.WEST, passwordField);
        springLayout.putConstraint(SpringLayout.NORTH, typeComboBox, 20, SpringLayout.SOUTH, passwordField);

        springLayout.putConstraint(SpringLayout.WEST, loginButton, 50, SpringLayout.WEST, typeLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginButton, 30, SpringLayout.SOUTH, typeLabel);

        springLayout.putConstraint(SpringLayout.WEST, resetButton, 50, SpringLayout.EAST, loginButton);
        springLayout.putConstraint(SpringLayout.NORTH, resetButton, 0, SpringLayout.NORTH, loginButton);


        container.add(nameLabel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);


        //加载图片
        URL imgUrl2 = BorrowerLoginView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        setSize(500, 350);
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

    public JComboBox getTypeComboBox() {
        return typeComboBox;
    }

    public void setTypeComboBox(JComboBox typeComboBox) {
        this.typeComboBox = typeComboBox;
    }

    private class MangerRegisterListener implements ActionListener {
        private MangerRegisterView mangerRegisterView;

        public MangerRegisterListener(MangerRegisterView mangerRegisterView) {
            this.mangerRegisterView = mangerRegisterView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if(jButtonText.equals("重置")) {
                mangerRegisterView.getUsernameField().setText("");
                mangerRegisterView.getPasswordField().setText("");
            }else if (jButtonText.equals("注册")) {
                String username = mangerRegisterView.getUsernameField().getText();
                String password = mangerRegisterView.getPasswordField().getText();
                String type = mangerRegisterView.getTypeComboBox().getSelectedItem().toString();

                Manger manger = new Manger(username, password, type);
                Register register = new Register();
                boolean b = register.Manger_register(manger);
                if (b) {
                    new MangerLoginView();
                    mangerRegisterView.dispose();
                }else {
                    JOptionPane.showMessageDialog(mangerRegisterView, "注册失败");
                }
            }
        }
    }

    public static void main(String[] args) {
        new MangerRegisterView();
    }
}
