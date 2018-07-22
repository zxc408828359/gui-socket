/**

 * 功能:流式布局案例

 */

package gui;



import javax.swing.*;



import java.awt.*;



public class Flow extends JFrame{



//定义需要的组件

    JButton jb1,jb2,jb3,jb4,jb5,jb6;

    public static void main(String[] args) {

        Flow flow = new Flow();

    }

//构造函数

    public Flow(){

//创建组件

        jb1=new JButton("关羽");

        jb2=new JButton("张飞");

        jb3=new JButton("赵云");

        jb4=new JButton("马超");

        jb5=new JButton("黄忠");

        jb6=new JButton("魏延");

//添加组件

        this.add(jb1);

        this.add(jb2);

        this.add(jb3);

        this.add(jb4);

        this.add(jb5);

        this.add(jb6);

//设置布局管理器

        this.setLayout(new FlowLayout(FlowLayout.LEFT));

//this.setLayout(new FlowLayout(FlowLayout.TRAILING));

//this.setLayout(new FlowLayout(FlowLayout.RIGHT) );

//this.setLayout(new FlowLayout(FlowLayout.CENTER));

//this.setLayout(new FlowLayout(FlowLayout.L));

//设置窗体属性

        this.setTitle("边界布局");

        this.setSize(300,200);

        this.setLocation(400,200);

//禁止用户改变窗体大小

        this.setResizable(false);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//显示

        this.setVisible(true);

    }

}