package gui;

/**

 * 功能:gui界面开发演示

 */

import java.awt.*;

import javax.swing.*;



public class Swing_1 extends JFrame{

//定义按钮组件

    JButton jb1 = null;

    public static void main(String[] args) {

        Swing_1 swing = new Swing_1();

    }

//构造函数

    public Swing_1(){

//JFrame是一个顶层容器

        jb1 = new JButton("我是按钮");

//设置窗体的标题

        this.setTitle("MyFirstSwing");

//设置大小,按像素

        this.setSize(200,200);

//添加JButton组建

        this.add(jb1);

//设置窗体位置

        this.setLocation(400, 200);

//设置当关闭窗口时,保证jvm也退出

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//设置窗体可见性

        this.setVisible(true);

    }

}