package student;

import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Student extends JFrame implements ActionListener
{
    private JMenuBar menuBar;
    private JMenu menu2;
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
    private JPanel panel1;
    private JScrollPane panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JCheckBox[] checkBoxes2;

    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=student";//添加数据库名
    private String admin = "sa";//账号
    private String password = "TY1472583690";//密码
    private Connection conn;
    private String id;
    public Student(String name,String id)
    {
        super(name);
        this.setSize(400,300);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.id=id;

        menu2=new JMenu("数据操作");
        m21=new JMenuItem("课程成绩查询");
        m21.addActionListener(this);
        m23=new JMenuItem("全科成绩查询");
        m23.addActionListener(this);
        m22=new JMenuItem("选课");
        m22.addActionListener(this);
        m24=new JMenuItem("重修选课");
        m24.addActionListener(this);

        menu2.add(m21);
        menu2.add(m23);
        menu2.add(m22);
        menu2.add(m24);
        menuBar=new JMenuBar();
        menuBar.add(menu2);
        setJMenuBar(menuBar);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Student("nan","1");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==m21)
        {
//            flag=1;
            if (panel1!=null) panel1.setVisible(false);
            if (panel2!=null) panel2.setVisible(false);
            if (panel3!=null) panel3.setVisible(false);
            if (panel4!=null) panel4.setVisible(false);
            panel1 = new JPanel();
            button1 = new JButton("查询成绩");
            button1.addActionListener(this);
            textField = new JTextField(10);
            panel1.add(button1);
            panel1.add(textField);
            String[] title = {"课程", "成绩"};
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
            if (panel3!=null) panel3.setVisible(false);
            if (panel4!=null) panel4.setVisible(false);
            panel1 = new JPanel();
            button2 = new JButton("查询成绩");
            button2.addActionListener(this);
            panel1.add(button2);
            String[] title = {"课程", "成绩"};
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
            if (panel3!=null) panel3.setVisible(false);
            if (panel4!=null) panel4.setVisible(false);

            String sql="select class from class,grade where class.cno=grade.cno and no=?";
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, admin, password);
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, this.id);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                class Class {
                    private String classname;

                    private String cno;

                    public String getCno() {
                        return cno;
                    }

                    public void setCno(String cno) {
                        this.cno = cno;
                    }
                    public String getClassname() {
                        return classname;
                    }

                    public void setClassname(String classname) {
                        this.classname = classname;
                    }
                }
                ArrayList<Class> v = new ArrayList<Class>();
                while (rs.next()) {
                    Class grade = new Class();
                    grade.setClassname(rs.getString("class"));
                    v.add(grade);
                }
                rs.close();

                JCheckBox[] checkBoxes = new JCheckBox[v.size()];

                panel3=new JPanel(new GridLayout(2,1));
                this.add(panel3);
                panel1 = new JPanel();

                JLabel label1 = new JLabel("已选课");
                panel1.add(label1);
                for (int i = 0; i < v.size(); i++) {
                    checkBoxes[i] = new JCheckBox(v.get(i).getClassname());
                    panel1.add(checkBoxes[i]);
                    checkBoxes[i].setSelected(true);
                }

                panel3.add(panel1);

                panel4=new JPanel();
                JLabel label2=new JLabel("未选课");
                panel4.add(label2);
                button3=new JButton("确认选课");

                String sql2="select class,cno from class where class.class not in (select class from class,grade where class.cno=grade.cno and no =?)";
                ps = conn.prepareStatement(sql2);
                ps.setString(1, this.id);
                rs = ps.executeQuery();
                rsmd = rs.getMetaData();
                final ArrayList<Class> v2 = new ArrayList<Class>();
                while (rs.next()) {
                    Class grade = new Class();
                    grade.setClassname(rs.getString("class"));
                    grade.setCno(rs.getString("cno"));
                    v2.add(grade);
                }
                rs.close();
                checkBoxes2 = new JCheckBox[v2.size()];
                for (int i = 0; i < v2.size(); i++) {
                    checkBoxes2[i] = new JCheckBox(v2.get(i).getClassname());
                    panel4.add(checkBoxes2[i]);
                }

                panel4.add(button3,"South");
                panel3.add(panel4);
                panel3.setVisible(true);
                panel4.setVisible(true);
                //conn.close();
                this.setVisible(true);
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if (e.getSource()==button3)
                        {
                            String sqltem="INSERT into grade VALUES (?,?,null)";

                            try {
                                java.lang.Class.forName(driver);
                                conn = DriverManager.getConnection(url, admin, password);
                                PreparedStatement ps2 = conn.prepareStatement(sqltem);
                                for (int i = 0; i< v2.size(); i++)
                                {
                                    if (checkBoxes2[i].isSelected())
                                    {
                                        ps2.setString(1, v2.get(i).getCno());
                                        ps2.setString(2, Student.this.id);
                                        ps2.executeUpdate();
                                    }
                                }
                                conn.close();
                            } catch (SQLException | ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
                conn.close();
            }
                catch (ClassNotFoundException | SQLException ex)
            {
                ex.printStackTrace();
            }
        }

        if (e.getSource()==m24)
        {
            if (panel1!=null) panel1.setVisible(false);
            if (panel2!=null) panel2.setVisible(false);
            if (panel3!=null) panel3.setVisible(false);
            if (panel4!=null) panel4.setVisible(false);

            String sql="select class,class.cno rcno,no from class,grade where class.cno=grade.cno and no=? and exam='1' and grade<'60'";
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, admin, password);
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, this.id);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                class Class implements Comparable<Class>
                {
                    private String classname;
                    private String no;
                     private int year;

                    public int getYear() {
                        return year;
                    }

                    public void setYear(int year) {
                        this.year = year;
                    }

                    public String getNo() {
                        return no;
                    }

                    public void setNo(String no) {
                        this.no = no;
                    }

                    private String cno;

                    public String getCno() {
                        return cno;
                    }

                    public void setCno(String cno) {
                        this.cno = cno;
                    }
                    public String getClassname() {
                        return classname;
                    }

                    public void setClassname(String classname) {
                        this.classname = classname;
                    }

                    @Override
                    public int compareTo(Class o)
                    {
                        if (this.getCno().equals(o.getCno())&&this.getNo().equals(o.getNo()))
                        {
                            return 1;
                        }
                        return 0;
                    }
                }
                final ArrayList<Class> v2 = new ArrayList<Class>();
                while (rs.next()) {
                    Class grade = new Class();
                    grade.setClassname(rs.getString("class"));
                    grade.setCno(rs.getString("rcno"));
                    grade.setNo(rs.getString("no"));
                    v2.add(grade);
                }
                rs.close();




                String sql2="select cno,no from regrade";
                java.lang.Class.forName(driver);
                conn = DriverManager.getConnection(url, admin, password);
                PreparedStatement ps2 = conn.prepareStatement(sql2);
//                ps.setString(1, this.id);
                ResultSet rs2 = ps2.executeQuery();
                ResultSetMetaData rsmd2 = rs2.getMetaData();
                final ArrayList<Class> v22 = new ArrayList<Class>();
                while (rs2.next())
                {
                    Class grade = new Class();
                    grade.setCno(rs2.getString("cno"));
                    grade.setNo(rs2.getString("no"));
                    v22.add(grade);
                }
                rs2.close();

                final ArrayList<JCheckBox> checkBoxes = new ArrayList<>();
                final ArrayList<Class> classes = new ArrayList<>();
                int k=0;

                panel3=new JPanel(new GridLayout(2,1));
                this.add(panel3);

                panel1 = new JPanel();
                JLabel label1 = new JLabel("未选课");
                panel1.add(label1);
                boolean flag=true;
                for (int i = 0; i < v2.size(); i++)
                {
                    for (int j=0;j< v22.size();j++)
                    {
                        if (v2.get(i).compareTo(v22.get(j))==1)
                        {
                            flag=false;
                            break;
                        }
                    }
                    if (flag)
                    {
                        Class grade = new Class();
                        grade.setCno(v2.get(i).getCno());
                        grade.setNo(v2.get(i).getNo());
                        classes.add(grade);
                        checkBoxes.add(new JCheckBox(v2.get(i).getClassname()));
                        classes.add(grade);
                        panel1.add(checkBoxes.get(k++));
                    }
                    flag=true;
                }
                panel3.add(panel1);

                panel4=new JPanel();
                button3=new JButton("确认选课");

                panel4.add(button3,"South");
                panel3.add(panel4);
                panel3.setVisible(true);
                panel4.setVisible(true);
                //conn.close();
                this.setVisible(true);
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if (e.getSource()==button3)
                        {
                            String sqltem="INSERT into regrade VALUES (?,?,null,null,?)";

                            try {
                                java.lang.Class.forName(driver);
                                conn = DriverManager.getConnection(url, admin, password);
                                //PreparedStatement ps3 = conn.prepareStatement(sqltem);
                                CallableStatement c=conn.prepareCall("{call Tea_year(?,?,?)}");


                                java.lang.Class.forName(driver);
                                conn = DriverManager.getConnection(url, admin, password);
                                PreparedStatement ps2 = conn.prepareStatement(sqltem);
                                for (int i = 0; i< checkBoxes.size(); i++)
                                {
                                    if (checkBoxes.get(i).isSelected())
                                    {
                                        //todo
                                        c.setString(1,classes.get(i).getNo());
                                        c.setString(2,classes.get(i).getCno());
                                        //c.setString(3,classes.get(i).getCno());
                                        c.registerOutParameter(3, Types.BIGINT);
                                        c.execute();
                                        //ResultSet rs = (ResultSet)c.getObject();
                                        classes.get(i).setYear(c.getInt(3)+1);

                                        ps2.setString(1, classes.get(i).getCno());
                                        ps2.setString(2, classes.get(i).getNo());
                                        ps2.setInt(3, classes.get(i).getYear());
                                        ps2.executeUpdate();
                                    }
                                }
                                conn.close();
                            } catch (SQLException | ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
                conn.close();
            }
            catch (ClassNotFoundException | SQLException ex)
            {
                ex.printStackTrace();
            }
        }

        if (e.getSource()==button1)
        {
            String sql="select class,grade from class,grade where class.cno=grade.cno and class.class=? and no=?";
            this.search(sql);
        }

        if (e.getSource()==button2)
        {
            String sql="select class,grade from class,grade where class.cno=grade.cno and no=?";
            this.searchAll(sql);
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
            ArrayList<Grade> v=new ArrayList<Grade>();
            while (rs.next())
            {
                Grade grade=new Grade();
                grade.setClassname(rs.getString("class"));
                grade.setGrade(rs.getString("grade"));
                v.add(grade);
            }
            rs.close();
            for ( int i = 0;i < v.size(); i++){
                tableModel.addRow(new Object[]{v.get(i).getClassname(),v.get(i).getGrade()});
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
            ps.setString(1, this.id);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();

            class Grade
            {
                private String classname;
                private String grade;

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
            ArrayList<Grade> v=new ArrayList<Grade>();
            while (rs.next())
            {
                Grade grade=new Grade();
                grade.setClassname(rs.getString("class"));
                grade.setGrade(rs.getString("grade"));
                v.add(grade);
            }
            rs.close();
            for ( int i = 0;i < v.size(); i++){
                tableModel.addRow(new Object[]{v.get(i).getClassname(),v.get(i).getGrade()});
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException event)
        {
            event.printStackTrace();
        }
    }
}

