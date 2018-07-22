/**

 * 功能:网格布局

 */

package gui;

import java.awt.*;

import javax.swing.*;



public class Grid extends JFrame{



//定义组件

    int size = 9 ;

    JButton jbs[] = new JButton[size];

    public static void main(String[] args) {

//创建实例

        Grid grid = new Grid();

    }

    public Grid(){

//创建组件

        for(int i = 0 ; i<9 ; i++){

            jbs[i] = new JButton (String.valueOf(i));

        }

//设置网格布局(3,3)表示3行3列

        this.setLayout(new GridLayout(3,3,10,10));

//添加组件

        for(int i = 0 ; i < 9 ; i++){

            this.add(jbs[i]);

        }

//设置窗体属性

        this.setTitle("网格布局");

        this.setSize(300,200);

        this.setLocation(400,200);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//显示窗体

        this.setVisible(true);

    }

}