package login;

import student.Admin;
import student.Student;
import student.Teacher;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class userLogin extends JFrame implements ActionListener
{
    private JTextField username;
    private JPasswordField password;
    private JButton login,register;
    private JRadioButton[] button;
//    private Connection conn;
    userLogin()
    {
        super("登录");
        this.setSize(300,200);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();
        this.add(panel1);
        this.add(panel2);
        panel2.setLayout(new GridLayout(3,2));
        button=new JRadioButton[3];
        String[] str={"管理员","教师","学生"};
        ButtonGroup bu=new ButtonGroup();
        for (int i=0;i<3;i++)
        {
            bu.add(button[i]=new JRadioButton(str[i]));
            panel1.add(button[i]);
            button[i].addActionListener(this);
        }
        panel2.add(new JLabel("用户名"));
        username=new JTextField(10);
        panel2.add(username);
        panel2.add(new JLabel("密码"));
        password=new JPasswordField(10);
        panel2.add(password);
        login=new JButton("login");
        register=new JButton("register");
        panel2.add(login);
        panel2.add(register);
        login.addActionListener(this);
        register.addActionListener(this);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        if (event.getSource() == login)
        {
            if (new String(this.password.getPassword()).equals(""))
            {
                JOptionPane.showMessageDialog(null,"password is null!");
            }
            else
            {
                String ID = username.getText();
                String passwd = new String (password.getPassword());

                //创建一个Admin用户，把输入框中的用户名密码和提出来
                User admin = new User();
                admin.setID(ID);
                admin.setPassword(passwd);

                //登录
                Login login = new Login();
                login.setAdmin(admin);
                int flag=-1;

                if (button[0].isSelected())
                {
                    try {
                        flag=0;
                        login.login0(admin);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (button[1].isSelected())
                {
                    try {
                        flag=1;
                        login.login1(admin);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                if (button[2].isSelected())
                {
                    try {
                        flag=2;
                        login.login(admin);
                    } catch (SQLException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }


                if(login.JudgeAdmin(flag)==0) {
                    //弹出账号或密码错误的窗口
                    JOptionPane.showMessageDialog(null, "账号或密码错误", "账号或密码错误", JOptionPane.WARNING_MESSAGE);
                    //清除密码框中的信息
                    password.setText("");
                    //清除账号框中的信息
                    username.setText("");

                    //System.out.println("登陆失败");
                }
                else
                {
                    //弹出登录成功的窗口
                    JOptionPane.showMessageDialog(null, "登陆成功", "登陆成功", JOptionPane.NO_OPTION);
                    //点击确定后会跳转到主窗口
                    this.setVisible(false);
                    //TODO 弹出管理页面
                    if (button[0].isSelected())
                    {
                        new Admin("管理员",username.getText());
                    }
                    if (button[1].isSelected())
                    {
                        new Teacher("教师",username.getText());
                    }
                    if (button[2].isSelected())
                    {
                        new Student("学生",username.getText());
                    }
                }
            }
        }
        if (event.getSource() == register)
        {
            this.password.setText("");
        }
    }
    public static void main(String[] args)
    {
        new userLogin();
    }
}
