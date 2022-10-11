package com.LYH;

import javax.swing.*;
import java.awt.*;

public class HelloWorld extends JFrame {
    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenu menu2;
    private JMenuItem m11;
    private JMenuItem m12;
    private JMenuItem m21;
    private JMenuItem m22;
    private JMenuItem m23;
    private JMenuItem m24;
    private JButton b1;
    private JButton b2;
    private JToolBar tool;


    public HelloWorld() throws HeadlessException {

        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(400,300);
        this.setTitle("HelloWorld");
        this.setLocationRelativeTo(getOwner());

        menu1=new JMenu("系统");
        m11=new JMenuItem("用户管理");
        m12=new JMenuItem();
        m12.setText("退出");
        menu1.add(m11);
        menu1.add(m12);
        menu2=new JMenu("数据操作");
        m21=new JMenuItem("查询");
        m22=new JMenuItem("添加");
        m23=new JMenuItem("修改");
        m24=new JMenuItem("删除");
        menu2.add(m21);
        menu2.add(m22);
        menu2.add(m23);
        menu2.add(m24);
        menuBar=new JMenuBar();
        menuBar.add(menu1);
        menuBar.add(menu2);
        setJMenuBar(menuBar);
        b1=new JButton(new ImageIcon("icon\\UPWATER.GIF"));
        b1.setToolTipText("查询");
        b1.setFocusable(false);
        b1.setHorizontalTextPosition(SwingConstants.CENTER);
        b1.setVerticalTextPosition(SwingConstants.BOTTOM);
        b2=new JButton();
        b2.setIcon(new ImageIcon("icon\\WRITER.GIF"));
        b2.setToolTipText("添加");
        b2.setFocusable(false);
        b2.setHorizontalTextPosition(SwingConstants.CENTER);
        b2.setVerticalTextPosition(SwingConstants.BOTTOM);
        tool=new JToolBar();
        tool.add(b1);
        tool.add(b2);
        tool.setRollover(true);

        getContentPane().add(tool,BorderLayout.PAGE_START);

        this.setVisible(true);

    }

    public static void main(String[] args) {
        new HelloWorld();
    }
}
