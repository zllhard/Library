package GUI;

import Function.Login;
import db.Select;
import entity.Manger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.List;

public class MangerLoginView extends JFrame {
    JLabel nameLabel = new JLabel("管理员端", JLabel.CENTER);

    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    //组件
    JLabel usernameLabel = new JLabel("用户名：");
    JTextField usernameField = new JTextField();
    JLabel passwordLabel = new JLabel("密码：");
    JTextField passwordField = new JPasswordField();
    JButton loginButton = new JButton("登录");
    JButton resetButton = new JButton("重置");
    JButton registerButton = new JButton("注册账号");

    public MangerLoginView() {
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

        //把组件加入面板
        centerPanel.add(usernameLabel);
        centerPanel.add(usernameField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        centerPanel.add(loginButton);

        loginButton.addActionListener(new MangerLoginListener(this));
        centerPanel.add(resetButton);
        resetButton.addActionListener(new MangerLoginListener(this));

        centerPanel.add(registerButton);
        registerButton.addActionListener(new MangerLoginListener(this));


        //弹簧布局
        //布局cardnoLabel
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(usernameLabel), Spring.width(usernameField)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, usernameLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameLabel, 20, SpringLayout.NORTH, centerPanel);
        //布局cardnoField
        springLayout.putConstraint(SpringLayout.WEST, usernameField, 20, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, usernameField, 0, SpringLayout.NORTH, usernameLabel);
        //布局passwordLabel
        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, usernameLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 27, SpringLayout.SOUTH, usernameLabel);
        //布局passField
        springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, usernameField);
        springLayout.putConstraint(SpringLayout.NORTH, passwordField, 20, SpringLayout.SOUTH, usernameField);
        //loginButton
        springLayout.putConstraint(SpringLayout.WEST, loginButton, 50, SpringLayout.WEST, passwordLabel);
        springLayout.putConstraint(SpringLayout.NORTH, loginButton, 30, SpringLayout.SOUTH, passwordLabel);
        //resetButton
        springLayout.putConstraint(SpringLayout.WEST, resetButton, 50, SpringLayout.EAST, loginButton);
        springLayout.putConstraint(SpringLayout.NORTH, resetButton, 0, SpringLayout.NORTH, loginButton);

        springLayout.putConstraint(SpringLayout.EAST, registerButton, -70, SpringLayout.WEST, loginButton);
        springLayout.putConstraint(SpringLayout.NORTH, registerButton, 40, SpringLayout.SOUTH, loginButton);

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


    //登录监听
    private class MangerLoginListener implements ActionListener {
        private MangerLoginView mangerLoginView;

        public MangerLoginListener(MangerLoginView mangerLoginView) {
            this.mangerLoginView = mangerLoginView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if(jButtonText.equals("重置")) {
                mangerLoginView.getUsernameField().setText("");
                mangerLoginView.getPasswordField().setText("");
            }else if (jButtonText.equals("登录")) {
                String username = mangerLoginView.getUsernameField().getText();
                String password = mangerLoginView.getPasswordField().getText();

                Login login = new Login();
                boolean b = login.Manger_login(username, password);
                if (b) {
                    Select select = new Select();
                    List<Manger> mangerList = select.selectManger(username);
                    Manger manger = mangerList.get(0);
                    if (manger.getType().equals("系统管理员")) {
                        //跳转到系统管理员界面
                        new SystemMangerView();
                        mangerLoginView.dispose();
                    }else if (manger.getType().equals("图书管理员")) {
                        //跳转到图书管理员界面
                        new LibraryMangerView();
                        mangerLoginView.dispose();
                    }
                }else {
                    JOptionPane.showMessageDialog(mangerLoginView, "用户名或者密码错误");
                }
            }else if (jButtonText.equals("注册账号")) {
                new MangerRegisterView();
                mangerLoginView.dispose();
            }
        }
    }

    public static void main(String[] args) {
        new MangerLoginView();
    }
}
