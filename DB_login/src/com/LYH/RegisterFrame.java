package com.LYH;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterFrame extends JFrame
{
    private JTextField username;
    private JPasswordField password,passwordAgain;
    private JRadioButton sexFemale,sexMale;
    private JPanel sex,birth,fav;
    private JTextField year;
    private JComboBox<Integer> month,day;
    private JCheckBox f1,f2,f3;
    private JTextArea remmond;
    private JButton register,cancel;
    private JScrollPane scroll;
    public RegisterFrame(){
        super();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(450,400);
        this.setTitle("Login");
        this.setLocationRelativeTo(getOwner());

        Container contain=getContentPane();
        contain.setLayout(new BoxLayout(contain,BoxLayout.Y_AXIS));

        JPanel cont =new JPanel(new GridLayout(6,2));

        cont.add(new JLabel("用户名"));
        username=new JTextField(10);
        cont.add(username);

        cont.add(new JLabel("密码"));
        password=new JPasswordField(10);
        cont.add(password);

        cont.add(new JLabel("再输一次密码"));
        passwordAgain=new JPasswordField(10);
        cont.add(passwordAgain);

        cont.add(new JLabel("性别"));
        sexMale=new JRadioButton("男",true);
        sexFemale=new JRadioButton("女");
        ButtonGroup bg=new ButtonGroup();
        bg.add(sexMale);
        bg.add(sexFemale);
        sex=new JPanel(new GridLayout(1,2));
        sex.add(sexMale);
        sex.add(sexFemale);
        cont.add(sex);

        cont.add(new JLabel("出生日期"));
        year=new JTextField(4);
        month=new JComboBox<Integer>();
        int i;
        for (i=1; i<=12;i++)
        {
            month.addItem(i);
        }
        day = new JComboBox<Integer>();
        for (i=1; i<=31; i++)
            day.addItem(i);
        birth= new JPanel();
        birth.add(year);
        birth.add(new JLabel("-"));
        birth.add (month);
        birth.add(new JLabel("-"));
        birth.add(day);
        cont.add(birth);

        cont.add(new JLabel("爱好"));
        f1 = new JCheckBox("运动");
        f2 = new JCheckBox("看电影");
        f3 = new JCheckBox("睡觉");
        fav= new JPanel();
        fav.add(f1);
        fav.add(f2);
        fav.add(f3);
        cont.add(fav);

        JPanel cont1 = new JPanel(new GridLayout(1,2));
        cont1.add(new JLabel("简历"));
        remmond = new JTextArea(5,10);
        scroll = new JScrollPane(remmond);
        cont1.add(scroll);

        JPanel cont2 = new JPanel (new GridLayout(1,2));
        register = new JButton("注册");
        cancel = new JButton("取消");
        cont2.add(register);
        cont2.add(cancel);
//加入到内容面板
        contain.add(cont);
        contain.add(cont1);
        contain.add(cont2);
//-------end--------
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String pass=new String (password.getPassword());
                String passagain=new String(passwordAgain.getPassword());
                if (passagain.equals(pass))
                {
                    String s;
                    s="用户名："+username.getText()+"\n";
                    s+="密码:"+pass+"\n";
                    s+="性别:"+(sexMale.isSelected()?sexMale.getText():sexFemale.getText())+"\n";
                    s+="出生日期:"+year.getText()+"-"+month.getSelectedItem()+"-"+day.getSelectedItem()+"\n";
                    s+="爱好:"+(f1.isSelected()?f1.getText():"")+(f2.isSelected()?f2.getText():"")+(f3.isSelected()?f3.getText():"")+"\n";
                    s+="简历:"+remmond.getText();
                    JOptionPane.showMessageDialog(null,s);
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"密码错误！");
                }
            }
        });

    }

    public static void main(String[] args)
    {
        RegisterFrame w=new RegisterFrame();
        w.setVisible(true);
    }
}
