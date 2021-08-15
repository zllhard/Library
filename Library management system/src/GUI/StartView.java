package GUI;

import entity.Borrower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class StartView extends JFrame {
    JLabel nameLabel = new JLabel("选择登录端", JLabel.CENTER);

    JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    //组件
    JButton jButton1 = new JButton("借阅者端");
    JButton jButton2 = new JButton("管理员端");

    public StartView() {
        super("图书管理系统");
        Container container = getContentPane();

        //字体
        nameLabel.setFont(new Font("华文行楷", Font.BOLD, 30));
        nameLabel.setPreferredSize(new Dimension(0, 80));

        Font font = new Font("楷体", Font.PLAIN, 20);
        jButton1.setFont(font);
        jButton2.setFont(font);

        //把组件加入面板
        centerPanel.add(jButton1);

        jButton1.addActionListener(new StartListener(this));
        centerPanel.add(jButton2);
        jButton2.addActionListener(new StartListener(this));


        container.add(nameLabel, BorderLayout.NORTH);
        container.add(centerPanel, BorderLayout.CENTER);


        //加载图片
        URL imgUrl2 = BorrowerLoginView.class.getClassLoader().getResource("whut.jpg");
        setIconImage(new ImageIcon(imgUrl2).getImage());

        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);

    }

    private class StartListener implements ActionListener {
        private StartView startView;

        public StartListener(StartView startView) {
            this.startView = startView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton jButton = (JButton) e.getSource();
            String jButtonText = jButton.getText();
            if(jButtonText.equals("借阅者端")) {
                new BorrowerLoginView();
                startView.dispose();
            }else {
                new MangerLoginView();
                startView.dispose();
            }
        }
    }

    public static void main(String[] args) {
        new StartView();
    }
}
