package GUI;

import Function.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class BorrowerLoginView extends JFrame {
    JLabel nameLabel = new JLabel("借阅者端", JLabel.CENTER);

    SpringLayout springLayout = new SpringLayout();
    JPanel centerPanel = new JPanel(springLayout);
    //组件
    JLabel cardnoLabel = new JLabel("卡号：");
    JTextField cardnoField = new JTextField();
    JLabel passwordLabel = new JLabel("密码：");
    JTextField passwordField = new JPasswordField();
    JButton loginButton = new JButton("登录");
    JButton resetButton = new JButton("重置");
    JButton registerButton = new JButton("注册账号");

    public BorrowerLoginView() {
        super("图书管理系统");
        Container container = getContentPane();

        //字体
        nameLabel.setFont(new Font("华文行楷", Font.BOLD, 30));
        nameLabel.setPreferredSize(new Dimension(0, 80));

        Font font = new Font("楷体", Font.PLAIN, 20);
        cardnoLabel.setFont(font);
        cardnoField.setPreferredSize(new Dimension(200, 30));
        passwordLabel.setFont(font);
        passwordField.setPreferredSize(new Dimension(200, 30));
        loginButton.setFont(font);
        resetButton.setFont(font);

        //把组件加入面板
        centerPanel.add(cardnoLabel);
        centerPanel.add(cardnoField);
        centerPanel.add(passwordLabel);
        centerPanel.add(passwordField);
        centerPanel.add(loginButton);

        loginButton.addActionListener(new LoginListener(this));
        centerPanel.add(resetButton);
        resetButton.addActionListener(new LoginListener(this));

        centerPanel.add(registerButton);
        registerButton.addActionListener(new LoginListener(this));


        //弹簧布局
        //布局cardnoLabel
        Spring childWidth = Spring.sum(Spring.sum(Spring.width(cardnoLabel), Spring.width(cardnoField)),
                Spring.constant(20));
        int offsetX = childWidth.getValue() / 2;
        springLayout.putConstraint(SpringLayout.WEST, cardnoLabel, -offsetX,
                SpringLayout.HORIZONTAL_CENTER, centerPanel);
        springLayout.putConstraint(SpringLayout.NORTH, cardnoLabel, 20, SpringLayout.NORTH, centerPanel);
        //布局cardnoField
        springLayout.putConstraint(SpringLayout.WEST, cardnoField, 20, SpringLayout.EAST, cardnoLabel);
        springLayout.putConstraint(SpringLayout.NORTH, cardnoField, 0, SpringLayout.NORTH, cardnoLabel);
        //布局passwordLabel
        springLayout.putConstraint(SpringLayout.EAST, passwordLabel, 0, SpringLayout.EAST, cardnoLabel);
        springLayout.putConstraint(SpringLayout.NORTH, passwordLabel, 27, SpringLayout.SOUTH, cardnoLabel);
        //布局passField
        springLayout.putConstraint(SpringLayout.EAST, passwordField, 0, SpringLayout.EAST, cardnoField);
        springLayout.putConstraint(SpringLayout.NORTH, passwordField, 20, SpringLayout.SOUTH, cardnoField);
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


    //登录监听
    private class LoginListener implements ActionListener {
        private BorrowerLoginView loginView;

        public LoginListener(BorrowerLoginView loginView) {
            this.loginView = loginView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if(jButtonText.equals("重置")) {
                loginView.getCardnoField().setText("");
                loginView.getPasswordField().setText("");
            }else if (jButtonText.equals("登录")) {
                String cardno = loginView.getCardnoField().getText();
                String password = loginView.getPasswordField().getText();

                Login login = new Login();
                boolean b = login.Borrower_login(cardno, password);
                if (b) {
                    //跳转到借阅者界面
                    new BorrowerView();
                    loginView.dispose();
                }else {
                    JOptionPane.showMessageDialog(loginView, "卡号或者密码错误");
                }
            }else if (jButtonText.equals("注册账号")) {
                new BorrowerRegisterView();
                loginView.dispose();
            }
        }
    }
    public static void main(String[] args) {
        new BorrowerLoginView();
    }
}
