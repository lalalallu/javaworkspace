package com.LYH;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Loginframe extends JFrame
{
    private JTextField username;
    private JPasswordField password;
    private JButton login,register;

    public Loginframe()
    {
        super("Login");
        this.setSize(300,200);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container cont=getContentPane();
        cont.setLayout(new GridLayout(3,2));
        cont.add(new JLabel("username"));
        username=new JTextField(10);
        cont.add(username);
        cont.add(new JLabel("password"));
        password=new JPasswordField(10);
        cont.add(password);
        login=new JButton(new ImageIcon("ico\\enter.gif"));
        register=new JButton(new ImageIcon("ico\\register.gif"));
        cont.add(login);
        cont.add(register);
        login.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent evt)
            {
                String pass=new String(password.getPassword());
                if (username.getText().equals("ding")&&pass.equals("123456"))
                {
                    HelloWorld hello=new HelloWorld();
                    hello.setVisible(true);
                    dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"密码错误！");
                }
            }
        });
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        new Loginframe();

    }
}
