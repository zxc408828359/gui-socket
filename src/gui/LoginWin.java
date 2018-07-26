package gui;

import util.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginWin extends JFrame implements ActionListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public static final int FRAME_WIDTH = 310;
    public static final int FRAME_HEIGHT = 250;

    private JLabel userNameLabel;
    private JTextField userName;
    private JLabel passwordLabel;
    private JPasswordField password;
    private JButton login;
    private JButton reset;
    private JButton signUp;

    public LoginWin() {
        this.setLayout(null);
        this.setBounds(100, 100, FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("登录窗口");


        userNameLabel = new JLabel("用户名");
        userNameLabel.setBounds(30, 50, 50, 20);
        this.add(userNameLabel);

        userName = new JTextField();
        userName.setBounds(80, 50, 180, 25);
        this.add(userName);
        userName.setName("userName");

        passwordLabel = new JLabel("密码");
        passwordLabel.setBounds(30, 100, 50, 25);
        this.add(passwordLabel);

        password = new JPasswordField();
        password.setBounds(80, 100, 180, 25);
        this.add(password);
        password.setName("password");

        login = new JButton("登录");
        login.setBounds(50, 160, 70, 25);
        login.addActionListener(this);
        this.add(login);
        login.setName("login");

        reset = new JButton("退出");
        reset.setBounds(165, 160, 70, 25);
        reset.addActionListener(this);
        this.add(reset);
        reset.setName("reset");

 /*       signUp = new JButton("注册");
        signUp.setBounds(280, 150, 70, 25);
        signUp.addActionListener(this);
        this.add(signUp);
        signUp.setName("signUp");*/
        this.setLocationRelativeTo(null);
        this.setUndecorated(false);
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try {
            if (actionEvent.getActionCommand().equals("登录")) {
                System.out.println("登入");
                new Flow();
                this.dispose();
            }
            if (actionEvent.getActionCommand().equals("退出")) {
                System.out.println("退出");
                String text = userName.getText();
                System.out.println(text);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        LoginWin loginWin = new LoginWin();
    }
}
