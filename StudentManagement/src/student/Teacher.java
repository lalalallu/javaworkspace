package student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Teacher extends JFrame implements ActionListener
{
    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=student";//添加数据库名
    private String admin = "sa";//账号
    private String password = "TY1472583690";//密码
    private Connection conn;
    private String id;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem m21;
    private JMenuItem m22;
    private JMenuItem m23;
    private JMenuItem m24;
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField textField;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel panel1;
    private JScrollPane panel2;
    public Teacher(String name,String id)
    {
        super(name);
        this.setSize(400,300);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.id=id;

        menu=new JMenu("数据操作");
        m21=new JMenuItem("学号成绩查询");
        m21.addActionListener(this);
        m22=new JMenuItem("课程成绩查询");
        m22.addActionListener(this);
        m23=new JMenuItem("姓名成绩查询");
        m23.addActionListener(this);
        m24=new JMenuItem("输入成绩");
        m24.addActionListener(this);
        menu.add(m21);
        menu.add(m23);
        menu.add(m22);
        menu.add(m24);
        menuBar=new JMenuBar();
        menuBar.add(menu);
        setJMenuBar(menuBar);

        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        new Teacher("nan","5");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==m21)
        {
            if (panel1!=null) panel1.setVisible(false);
            if (panel2!=null) panel2.setVisible(false);
            panel1 = new JPanel();
            button1 = new JButton("查询学号");
            button1.addActionListener(this);
            textField = new JTextField(10);
            panel1.add(button1);
            panel1.add(textField);
            String[] title = {"学号","课程", "成绩"};
            tableModel = new DefaultTableModel(title, 0);
            table = new JTable(tableModel);
            panel2 = new JScrollPane(table);
            this.add(panel2);
            this.add(panel1, "South");
            panel2.setVisible(true);
            panel1.setVisible(true);
            this.setVisible(true);
        }
        if (e.getSource()==m22)
        {
            if (panel1!=null) panel1.setVisible(false);
            if (panel2!=null) panel2.setVisible(false);
            panel1 = new JPanel();
            button2 = new JButton("查询课程");
            button2.addActionListener(this);
            textField = new JTextField(10);
            panel1.add(button2);
            panel1.add(textField);
            String[] title = {"学号","课程", "成绩"};
            tableModel = new DefaultTableModel(title, 0);
            table = new JTable(tableModel);
            panel2 = new JScrollPane(table);
            this.add(panel2);
            this.add(panel1, "South");
            panel2.setVisible(true);
            panel1.setVisible(true);
            this.setVisible(true);
        }
        if (e.getSource()==m23)
        {
            if (panel1!=null) panel1.setVisible(false);
            if (panel2!=null) panel2.setVisible(false);
            panel1 = new JPanel();
            button3 = new JButton("查询姓名");
            button3.addActionListener(this);
            textField = new JTextField(10);
            panel1.add(button3);
            panel1.add(textField);
            String[] title = {"姓名","学号","课程", "成绩"};
            tableModel = new DefaultTableModel(title, 0);
            table = new JTable(tableModel);
            panel2 = new JScrollPane(table);
            this.add(panel2);
            this.add(panel1, "South");
            panel2.setVisible(true);
            panel1.setVisible(true);
            this.setVisible(true);
        }
        if (e.getSource()==m24)
        {
            this.view();
        }

        if (e.getSource()==button1)
        {
            String sql="select no,class,grade from class,grade where class.cno=grade.cno and no=? and tno=?";
            this.search(sql);
        }
        if (e.getSource()==button2)
        {
            String sql="select no,class,grade from class,grade where class.cno=grade.cno and class=? and tno=?";
            this.search(sql);
        }
        if (e.getSource()==button3)
        {
            String sql="select name,student.no,class,grade from class,grade,student where class.cno=grade.cno and grade.no=student.no and name=? and tno=?";
            this.searchName(sql);
        }
        if (e.getSource()==button4)
        {
            String sql="update grade set nograde=?,fingrade=? where no=? and cno =(select cno from class where tno=? and class=?)";
            this.updateGrade(sql);
        }
    }
    private void view()
    {
        if (panel1!=null) panel1.setVisible(false);
        if (panel2!=null) panel2.setVisible(false);
        panel1 = new JPanel();
        button4 = new JButton("确认更新");
        button4.addActionListener(this);
        panel1.add(button4);
        String[] title = {"姓名","学号","课程","平时成绩","期末成绩","成绩"};
        tableModel = new DefaultTableModel(title, 0);

        table = new JTable(tableModel);
        panel2 = new JScrollPane(table);
        this.add(panel2);
        this.add(panel1, "South");
        panel2.setVisible(true);
        panel1.setVisible(true);
        String sql="select name,student.no,class,nograde,fingrade,grade from class,grade,student where class.cno=grade.cno and grade.no=student.no and tno=?";
        this.searchAll(sql);

        this.setVisible(true);
    }

    private void updateGrade(String sql)
    {
        String sql1=sql;
        class Grade
        {
                private String classname;
                private String grade;
                private String no;
                private String name;
                private String nograde;
                private String fingrade;

            public String getNograde() {
                return nograde;
            }

            public void setNograde(String nograde) {
                this.nograde = nograde;
            }

            public String getFingrade() {
                return fingrade;
            }

            public void setFingrade(String fingrade) {
                this.fingrade = fingrade;
            }


                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getNo() {
                    return no;
                }

                public void setNo(String no) {
                    this.no = no;
                }

                public String getClassname() {
                    return classname;
                }

                public void setClassname(String classname) {
                    this.classname = classname;
                }

                public String getGrade() {
                    return grade;
                }

                public void setGrade(String grade) {
                    this.grade = grade;
                }
            }
        ArrayList<Grade> v= new ArrayList<>();
        for (int i=0;i<tableModel.getRowCount();i++)
        {
            Grade grade=new Grade();
            int j=0;
            grade.setName(tableModel.getValueAt(i,j++).toString());
            grade.setNo(tableModel.getValueAt(i,j++).toString());
            grade.setClassname(tableModel.getValueAt(i,j++).toString());
            grade.setNograde(tableModel.getValueAt(i,j++).toString());
            grade.setFingrade(tableModel.getValueAt(i,j++).toString());
            grade.setGrade(tableModel.getValueAt(i,j).toString());
            v.add(grade);
        }
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, admin, password);
            PreparedStatement ps = conn.prepareStatement(sql1);
            for (int i = 0; i< v.size(); i++)
            {
                ps.setString(1, v.get(i).getNograde());
                ps.setString(2, v.get(i).getFingrade());
                ps.setString(3, v.get(i).getNo());
                ps.setString(4, this.id);
                ps.setString(5, v.get(i).getClassname());
                ps.executeUpdate();
            }
            conn.close();
            this.view();
        }
        catch (ClassNotFoundException | SQLException event)
        {
            event.printStackTrace();
        }

    }
    private void search(String sql)
    {
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, admin, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, textField.getText());
            ps.setString(2, this.id);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();

            class Grade
            {
                private String classname;
                private String grade;
                private String no;

                public String getNo() {
                    return no;
                }

                public void setNo(String no) {
                    this.no = no;
                }

                public String getClassname() {
                    return classname;
                }

                public void setClassname(String classname) {
                    this.classname = classname;
                }

                public String getGrade() {
                    return grade;
                }

                public void setGrade(String grade) {
                    this.grade = grade;
                }
            }
            ArrayList<Grade> v= new ArrayList<>();
            while (rs.next())
            {
                Grade grade=new Grade();
                grade.setClassname(rs.getString("class"));
                grade.setNo(rs.getString("no"));
                grade.setGrade(rs.getString("grade"));
                v.add(grade);
            }
            rs.close();
            for ( int i = 0;i < v.size(); i++){
                tableModel.addRow(new Object[]{v.get(i).getNo(),v.get(i).getClassname(),v.get(i).getGrade()});
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException event)
        {
            event.printStackTrace();
        }
    }
    private void searchName(String sql)
    {
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, admin, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, textField.getText());
            ps.setString(2, this.id);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();

            class Grade
            {
                private String classname;
                private String grade;
                private String no;
                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getNo() {
                    return no;
                }

                public void setNo(String no) {
                    this.no = no;
                }

                public String getClassname() {
                    return classname;
                }

                public void setClassname(String classname) {
                    this.classname = classname;
                }

                public String getGrade() {
                    return grade;
                }

                public void setGrade(String grade) {
                    this.grade = grade;
                }
            }
            ArrayList<Grade> v= new ArrayList<>();
            while (rs.next())
            {
                Grade grade=new Grade();
                grade.setClassname(rs.getString("class"));
                grade.setNo(rs.getString("no"));
                grade.setGrade(rs.getString("grade"));
                grade.setName(rs.getString("name"));
                v.add(grade);
            }
            rs.close();
            for ( int i = 0;i < v.size(); i++){
                tableModel.addRow(new Object[]{v.get(i).getName(),v.get(i).getNo(),v.get(i).getClassname(),v.get(i).getGrade()});
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException event)
        {
            event.printStackTrace();
        }
    }
    private void searchAll(String sql)
    {
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, admin, password);
            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, textField.getText());
            ps.setString(1, this.id);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();

            class Grade
            {
                private String classname;
                private String grade;
                private String no;
                private String name;

                public String getNograde() {
                    return nograde;
                }

                public void setNograde(String nograde) {
                    this.nograde = nograde;
                }

                public String getFingrade() {
                    return fingrade;
                }

                public void setFingrade(String fingrade) {
                    this.fingrade = fingrade;
                }

                private String nograde;
                private String fingrade;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getNo() {
                    return no;
                }

                public void setNo(String no) {
                    this.no = no;
                }

                public String getClassname() {
                    return classname;
                }

                public void setClassname(String classname) {
                    this.classname = classname;
                }

                public String getGrade() {
                    return grade;
                }

                public void setGrade(String grade) {
                    this.grade = grade;
                }
            }
            ArrayList<Grade> v= new ArrayList<>();
            while (rs.next())
            {
                Grade grade=new Grade();
                grade.setClassname(rs.getString("class"));
                grade.setNo(rs.getString("no"));
                grade.setGrade(rs.getString("grade"));
                grade.setName(rs.getString("name"));
                grade.setNograde(rs.getString("nograde"));
                grade.setFingrade(rs.getString("fingrade"));
                v.add(grade);
            }
            rs.close();
            for ( int i = 0;i < v.size(); i++){
                tableModel.addRow(new Object[]{v.get(i).getName(),v.get(i).getNo(),v.get(i).getClassname(),v.get(i).getNograde(),v.get(i).getFingrade(),v.get(i).getGrade()});
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException event)
        {
            event.printStackTrace();
        }
    }
}
