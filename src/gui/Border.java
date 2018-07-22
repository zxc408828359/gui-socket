/*

 * 边界布局管理器

 * BorderLayout演示

 * 1.继承JFrame

 * 2.定义你需要的各个组建

 * 3.创建组件

 * 4.添加组建

 * 5.对窗体设这

 * 6.显示窗体

 */

package gui;



import java.awt.BorderLayout;



import javax.swing.*;

import javax.swing.*;



public class Border extends JFrame{

    JButton jb1,jb2,jb3,jb4,jb5;

    public static void main(String[] args) {

        Border border = new Border();

    }

//构造函数

    public Border(){

//创建组件

        jb1 = new JButton("中");

        jb2 = new JButton("北");

        jb3 = new JButton("东");

        jb4 = new JButton("南");

        jb5 = new JButton("西");

//添加各个组件

//this.add(jb1)没有指定位置的话,会被覆盖

        this.add(jb1, BorderLayout.CENTER);

        this.add(jb2, BorderLayout.NORTH);

        this.add(jb3, BorderLayout.EAST);

        this.add(jb4, BorderLayout.SOUTH);

        this.add(jb5, BorderLayout.WEST);

//设置窗体属性

        this.setTitle("边界布局");

        this.setSize(300,200);

        this.setLocation(400,200);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setVisible(true);

    }

}