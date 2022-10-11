package student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class Admin extends JFrame implements ActionListener
{
    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=student";//添加数据库名
    private String admin = "sa";//账号
    private String password = "TY1472583690";//密码
    private Connection conn;
    private String id;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenu menu2;
    private JMenu menu3;
    private JMenuItem m21;
    private JMenuItem m22;
    private JMenuItem m23;
    private JMenuItem m24;
    private JMenuItem m25;
    private JMenuItem m26;
    private JMenuItem m27;
    private JMenuItem m28;
    private JMenuItem m29;
    private JMenuItem m30;

    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField textField;
    private JTextField textField2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JPanel panel1;
    private JScrollPane panel2;
    private JPanel panel3;
    private JCheckBox checkBoxes;
    private JCheckBox[] checkBoxes2;
    public Admin(String name,String id)
    {
        super(name);
        this.setSize(500,300);
        this.setLocationRelativeTo(getOwner());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.id=id;

        menu=new JMenu("数据操作");
        menu2=new JMenu("增加操作");
        menu3=new JMenu("删除操作");

        m21=new JMenuItem("学号成绩查询");
        m21.addActionListener(this);
        m22=new JMenuItem("课程成绩查询");
        m22.addActionListener(this);
        m23=new JMenuItem("姓名成绩查询");
        m23.addActionListener(this);
        m24=new JMenuItem("教师成绩查询");
        m24.addActionListener(this);
        m29=new JMenuItem("全科成绩查询");
        m29.addActionListener(this);
        m30=new JMenuItem("重修成绩查询");
        m30.addActionListener(this);

        menu.add(m21);
        menu.add(m22);
        menu.add(m23);
        menu.add(m24);
        menu.add(m29);
        menu.add(m30);

        m25=new JMenuItem("新增学生");
        m25.addActionListener(this);
        m26=new JMenuItem("新增教师");
        m26.addActionListener(this);
        menu2.add(m25);
        menu2.add(m26);

        m27=new JMenuItem("删除学生");
        m27.addActionListener(this);
        m28=new JMenuItem("删除教师");
        m28.addActionListener(this);
        menu3.add(m27);
        menu3.add(m28);

        menuBar=new JMenuBar();
        menuBar.add(menu);
        menuBar.add(menu2);
        menuBar.add(menu3);
        setJMenuBar(menuBar);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Admin("gaun","10086");
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==m30)
        {
            if (panel1!=null) panel1.setVisible(false);
            if (panel2!=null) panel2.setVisible(false);
            if (panel3!=null) panel3.setVisible(false);
            panel1 = new JPanel();
            JButton button1 = new JButton("查询排名");
            panel1.add(button1);
            String[] title = {"学号","课程","平时成绩","期末成绩","成绩"};
            tableModel = new DefaultTableModel(title, 0);
            table = new JTable(tableModel);
            panel2 = new JScrollPane(table);
            this.add(panel2);
            this.add(panel1, "South");
            panel2.setVisible(true);
            panel1.setVisible(true);
            this.setVisible(true);
            button1.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (panel2!=null) panel2.setVisible(false);
                    if (panel3!=null) panel3.setVisible(false);
                    String[] title = {"学号","课程","平时成绩","期末成绩","成绩"};
                    tableModel = new DefaultTableModel(title, 0);
                    table = new JTable(tableModel);
                    panel2 = new JScrollPane(table);
                    Admin.this.add(panel2);
                    Admin.this.add(panel1, "South");
                    panel2.setVisible(true);
                    panel1.setVisible(true);
                    Admin.this.setVisible(true);
                    String sql="select no,class,nograde,fingrade,regrade from class,regrade where class.cno=regrade.cno";
                    Admin.this.searchAll(sql);
                }
            });
        }
        if (e.getSource()==m29)
        {
            if (panel1!=null) panel1.setVisible(false);
            if (panel2!=null) panel2.setVisible(false);
            if (panel3!=null) panel3.setVisible(false);
            panel1 = new JPanel();
            JButton button1 = new JButton("查询排名");
            JButton button2 = new JButton("查询总成绩与平均成绩");
            JButton button3 = new JButton("查询课程平均成绩");
            panel1.add(button1);
            panel1.add(button2);
            panel1.add(button3);
            String[] title = {"学号","课程", "成绩"};
            tableModel = new DefaultTableModel(title, 0);
            table = new JTable(tableModel);
            panel2 = new JScrollPane(table);
            this.add(panel2);
            this.add(panel1, "South");
            panel2.setVisible(true);
            panel1.setVisible(true);
            this.setVisible(true);
            button1.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (panel2!=null) panel2.setVisible(false);
                    if (panel3!=null) panel3.setVisible(false);
                    String[] title = {"学号","课程", "成绩"};
                    tableModel = new DefaultTableModel(title, 0);
                    table = new JTable(tableModel);
                    panel2 = new JScrollPane(table);
                    Admin.this.add(panel2);
                    Admin.this.add(panel1, "South");
                    panel2.setVisible(true);
                    panel1.setVisible(true);
                    Admin.this.setVisible(true);
                    String sql="select no,class,grade from class,grade where class.cno=grade.cno";
                    Admin.this.searchAll(sql);
                }
            });
            button2.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (panel2!=null) panel2.setVisible(false);
                    if (panel3!=null) panel3.setVisible(false);
                    panel2 = new JScrollPane(table);
                    Admin.this.add(panel2);
                    String[] title = {"学号","总成绩","平均成绩"};
                    tableModel = new DefaultTableModel(title, 0);
                    table = new JTable(tableModel);
                    panel2 = new JScrollPane(table);
                    Admin.this.add(panel2);
                    //Admin.this.add(panel1, "South");
                    panel2.setVisible(true);
                    panel1.setVisible(true);
                    Admin.this.setVisible(true);
                    String sql="SELECT no,sum(grade) sum,AVG(grade) avg FROM Grade GROUP by [NO] order by AVG(grade) asc";
                    Admin.this.searchavg(sql);
                }
            });
            button3.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    if (panel2!=null) panel2.setVisible(false);
                    if (panel3!=null) panel3.setVisible(false);
                    panel2 = new JScrollPane(table);
                    Admin.this.add(panel2);
                    String[] title = {"课程号","平均成绩"};
                    tableModel = new DefaultTableModel(title, 0);
                    table = new JTable(tableModel);
                    panel2 = new JScrollPane(table);
                    Admin.this.add(panel2);
                    panel2.setVisible(true);
                    panel1.setVisible(true);
                    Admin.this.setVisible(true);
                    String sql="SELECT grade.cno,AVG(grade) avg FROM Grade ,class WHERE grade.cno=class.cno GROUP by grade.cno order by AVG(grade) asc";
                    Admin.this.searchclassavg(sql);
                }
            });
        }
        if (e.getSource()==m21)
        {
            if (panel1!=null) panel1.setVisible(false);
            if (panel2!=null) panel2.setVisible(false);
            if (panel3!=null) panel3.setVisible(false);
            panel1 = new JPanel();
            button1 = new JButton("查询学号");
            button1.addActionListener(this);
            textField = new JTextField(10);
            checkBoxes=new JCheckBox("补考");
            panel1.add(button1);
            panel1.add(textField);
            panel1.add(checkBoxes);
            String[] title = {"学号","课程","平时成绩","期末成绩","成绩"};
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
            panel1 = new JPanel();
            button2 = new JButton("查询课程");
            button2.addActionListener(this);
            textField = new JTextField(10);
            panel1.add(button2);
            panel1.add(textField);
            JLabel label=new JLabel("学年");
            textField2 = new JTextField(10);
            panel1.add(label);
            panel1.add(textField2);
            String[] title = {"学号","课程","平时成绩","期末成绩","成绩"};
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
            if (panel1!=null) panel1.setVisible(false);
            if (panel2!=null) panel2.setVisible(false);
            if (panel3!=null) panel3.setVisible(false);
            panel1 = new JPanel();
            button4 = new JButton("查询教师姓名");
            button4.addActionListener(this);
            textField = new JTextField(10);
            panel1.add(button4);
            panel1.add(textField);
            String[] title = {"姓名","学号","课程","教师", "成绩"};
            tableModel = new DefaultTableModel(title, 0);
            table = new JTable(tableModel);
            panel2 = new JScrollPane(table);
            this.add(panel2);
            this.add(panel1, "South");
            panel2.setVisible(true);
            panel1.setVisible(true);
            this.setVisible(true);
        }
        if (e.getSource()==m25)
        {
            if (panel1!=null) panel1.setVisible(false);
            if (panel2!=null) panel2.setVisible(false);
            if (panel3!=null) panel3.setVisible(false);
            panel1 = new JPanel();
            button5 = new JButton("增加学生");
            button5.addActionListener(this);
            panel1.add(button5);

            String[] title = {"学号","姓名","性别", "籍贯","专业","学院"};
            tableModel = new DefaultTableModel(title, 1);
            //tableModel.addTableModelListener(this);
            table = new JTable(tableModel);
            panel2 = new JScrollPane(table);
            this.add(panel2);
            this.add(panel1, "South");
            panel2.setVisible(true);
            panel1.setVisible(true);
            this.setVisible(true);
        }
        if (e.getSource()==m26)
        {
            if (panel1!=null) panel1.setVisible(false);
            if (panel2!=null) panel2.setVisible(false);
            if (panel3!=null) panel3.setVisible(false);
            panel1 = new JPanel();
            button6 = new JButton("增加教师");
            button6.addActionListener(this);
            panel1.add(button6);

            String[] title = {"教师号","姓名","性别", "生日","学院","职称"};
            tableModel = new DefaultTableModel(title, 1);
            table = new JTable(tableModel);
            panel2 = new JScrollPane(table);
            this.add(panel2);
            this.add(panel1, "South");
            panel2.setVisible(true);
            panel1.setVisible(true);
            this.setVisible(true);
        }
        if (e.getSource()==m27)
        {
            if (panel1 != null) panel1.setVisible(false);
            if (panel2 != null) panel2.setVisible(false);
            if (panel3!=null) panel3.setVisible(false);
            panel1 = new JPanel();
            button8 = new JButton("删除学生");

            panel1.add(button8);
            panel3 = new JPanel();
            String sql = "select name,no from student";
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, admin, password);
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                class student
                {
                    private String name;
                    private String sex;
                    private String no;
                    private String place;
                    private String major;
                    private String college;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getSex() {
                        return sex;
                    }

                    public void setSex(String sex) {
                        this.sex = sex;
                    }

                    public String getNo() {
                        return no;
                    }

                    public void setNo(String no) {
                        this.no = no;
                    }

                    public String getPlace() {
                        return place;
                    }

                    public void setPlace(String place) {
                        this.place = place;
                    }

                    public String getMajor() {
                        return major;
                    }

                    public void setMajor(String major) {
                        this.major = major;
                    }

                    public String getCollege() {
                        return college;
                    }

                    public void setCollege(String college) {
                        this.college = college;
                    }
                }
                final ArrayList<student> v= new ArrayList<>();
                while (rs.next()) {
                    student student = new student();
                    student.setName(rs.getString("name"));
                    student.setNo(rs.getString("no"));
                    v.add(student);
                }
                rs.close();
                checkBoxes2 = new JCheckBox[v.size()];
                for (int i = 0; i < v.size(); i++) {
                    checkBoxes2[i] = new JCheckBox(v.get(i).getName());
                    panel3.add(checkBoxes2[i]);
                }
                this.add(panel3);
                this.add(panel1, "South");
                panel3.setVisible(true);
                panel1.setVisible(true);
                this.setVisible(true);

                button8.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == button8) {
                            String sqltem = "DELETE from student WHERE no=?";
                            try {
                                java.lang.Class.forName(driver);
                                conn = DriverManager.getConnection(url, admin, password);
                                PreparedStatement ps2 = conn.prepareStatement(sqltem);
                                for (int i = 0; i < v.size(); i++) {
                                    if (checkBoxes2[i].isSelected()) {
                                        ps2.setString(1, v.get(i).getNo());
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
            } catch (ClassNotFoundException | SQLException event)
            {
                //event.printStackTrace();
                JOptionPane.showMessageDialog(null,event.getMessage());
            }

        }
        if (e.getSource()==m28)
        {
            if (panel1!=null) panel1.setVisible(false);
            if (panel2!=null) panel2.setVisible(false);
            if (panel3!=null) panel3.setVisible(false);
            panel1 = new JPanel();
            button7 = new JButton("删除教师");

            panel1.add(button7);
            panel3=new JPanel();
            String sql="select name,tno from teacher";
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, admin, password);
                PreparedStatement ps = conn.prepareStatement(sql);
//                ps.setString(1, this.id);
                ResultSet rs = ps.executeQuery();
                ResultSetMetaData rsmd = rs.getMetaData();
                class teacher
                {
                    private String name;
                    private String sex;
                    private String tno;
                    private String professor;
                    private String birthdate;
                    private String college;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getSex() {
                        return sex;
                    }

                    public void setSex(String sex) {
                        this.sex = sex;
                    }

                    public String getTno() {
                        return tno;
                    }

                    public void setTno(String tno) {
                        this.tno = tno;
                    }

                    public String getProfessor() {
                        return professor;
                    }

                    public void setProfessor(String professor) {
                        this.professor = professor;
                    }

                    public String getBirthdate() {
                        return birthdate;
                    }

                    public void setBirthdate(String birthdate) {
                        this.birthdate = birthdate;
                    }

                    public String getCollege() {
                        return college;
                    }

                    public void setCollege(String college) {
                        this.college = college;
                    }
                }
                final ArrayList<teacher> v = new ArrayList<>();
                while (rs.next()) {
                    teacher teacher = new teacher();
                    teacher.setName(rs.getString("name"));
                    teacher.setTno(rs.getString("tno"));
                    v.add(teacher);
                }
                rs.close();
                checkBoxes2 = new JCheckBox[v.size()];
                for (int i = 0; i < v.size(); i++)
                {
                    checkBoxes2[i] = new JCheckBox(v.get(i).getName());
                    panel3.add(checkBoxes2[i]);
                }
                this.add(panel3);
                this.add(panel1, "South");
                panel3.setVisible(true);
                panel1.setVisible(true);
                this.setVisible(true);

                button7.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        if (e.getSource()==button7)
                        {
                            String sqltem="DELETE from teacher WHERE tno=?";
                            try {
                                java.lang.Class.forName(driver);
                                conn = DriverManager.getConnection(url, admin, password);
                                PreparedStatement ps2 = conn.prepareStatement(sqltem);
                                for (int i = 0; i< v.size(); i++)
                                {
                                    if (checkBoxes2[i].isSelected())
                                    {
                                        ps2.setString(1, v.get(i).getTno());
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
            tableModel.setRowCount(0);
            String sql="select no,class,nograde,fingrade,grade from class,grade where class.cno=grade.cno and no=? and exam=?";
            this.search(sql);
        }
        if (e.getSource()==button2)
        {
            String sql="select no,class,nograde,fingrade,grade from class,grade where class.cno=grade.cno and year=? and class like "+"'"+textField.getText()+"%"+"'";
            this.search2(sql);
        }
        if (e.getSource()==button3)
        {
            String sql="select name,student.no,class,grade from class,grade,student where class.cno=grade.cno and grade.no=student.no and name like "+"'"+textField.getText()+"%"+"'";
            this.searchName(sql);
        }
        if (e.getSource()==button4)
        {
            String sql="select student.name sname,student.no sno,class,teacher.name tname,grade from class,grade,student,teacher where class.cno=grade.cno and grade.no=student.no and teacher.tno=class.tno and teacher.name like "+"'"+textField.getText()+"%"+"'";
            this.searchteaName(sql);
        }
        if (e.getSource()==button5)
        {
            String sql="INSERT into student VALUES (?,?,?,?,?,?)";
            this.insertStudent(sql);
        }
        if (e.getSource()==button6)
        {
            String sql="INSERT into teacher VALUES (?,?,?,?,?,?)";
            this.insertteacher(sql);
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
            if (checkBoxes.isSelected())  ps.setString(2,"1");
            else ps.setString(2,"0");
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();

            class Grade
            {
                private String classname;
                private String grade;
                private String no;
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
                grade.setNograde(rs.getString("nograde"));
                grade.setFingrade(rs.getString("fingrade"));
                v.add(grade);
            }
            rs.close();
            for ( int i = 0;i < v.size(); i++){
                tableModel.addRow(new Object[]{v.get(i).getNo(),v.get(i).getClassname(),v.get(i).getNograde(),v.get(i).getFingrade(),v.get(i).getGrade()});
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException event)
        {
            JOptionPane.showMessageDialog(null,event.getMessage());
        }
    }
    private void search2(String sql)
    {
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, admin, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, textField2.getText());
//            ps.setString(2, textField2.getText());
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();

            class Grade
            {
                private String classname;
                private String grade;
                private String no;
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
                grade.setNograde(rs.getString("nograde"));
                grade.setFingrade(rs.getString("fingrade"));
                v.add(grade);
            }
            rs.close();
            for ( int i = 0;i < v.size(); i++){
                tableModel.addRow(new Object[]{v.get(i).getNo(),v.get(i).getClassname(),v.get(i).getNograde(),v.get(i).getFingrade(),v.get(i).getGrade()});
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException event)
        {
            JOptionPane.showMessageDialog(null,event.getMessage());
        }
    }
    private void searchclassavg(String sql)
    {
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, admin, password);
            PreparedStatement ps = conn.prepareStatement(sql);
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
                //grade.setClassname(rs.getString("sum"));
                grade.setNo(rs.getString("cno"));
                grade.setGrade(rs.getString("avg"));
                v.add(grade);
            }
            rs.close();
            for ( int i = 0;i < v.size(); i++){
                tableModel.addRow(new Object[]{v.get(i).getNo(),v.get(i).getGrade()});
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException event)
        {
            JOptionPane.showMessageDialog(null,event.getMessage());
        }
    }
    private void searchavg(String sql)
    {
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, admin, password);
            PreparedStatement ps = conn.prepareStatement(sql);
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
                grade.setClassname(rs.getString("sum"));
                grade.setNo(rs.getString("no"));
                grade.setGrade(rs.getString("avg"));
                v.add(grade);
            }
            rs.close();
            for ( int i = 0;i < v.size(); i++){
                tableModel.addRow(new Object[]{v.get(i).getNo(),v.get(i).getClassname(),v.get(i).getGrade()});
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException event)
        {
            JOptionPane.showMessageDialog(null,event.getMessage());
        }
    }
    private void searchAll(String sql)
    {
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, admin, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();

            class Grade
            {
                private String classname;
                private String grade;
                private String no;
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
                grade.setGrade(rs.getString("regrade"));
                grade.setNograde(rs.getString("nograde"));
                grade.setFingrade(rs.getString("fingrade"));
                v.add(grade);
            }
            rs.close();
            for ( int i = 0;i < v.size(); i++){
                tableModel.addRow(new Object[]{v.get(i).getNo(),v.get(i).getClassname(),v.get(i).getNograde(),v.get(i).getFingrade(),v.get(i).getGrade()});
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException event)
        {
            JOptionPane.showMessageDialog(null,event.getMessage());
        }
    }
    private void searchName(String sql)
    {
        try
        {
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
            //String sql1="select student.name sname,student.no sno,class,teacher.name tname,grade from class,grade,student,teacher where class.cno=grade.cno and grade.no=student.no and teacher.tno=class.tno and teacher.name like "+"'"+textField.getText()+"%"+"'";;
            Class.forName(driver);
            conn = DriverManager.getConnection(url, admin, password);
            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, textField.getText());
//            ps.setString(2, this.id);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
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
            JOptionPane.showMessageDialog(null,event.getMessage());
        }
    }
    private void searchteaName(String sql)
    {
        try
        {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, admin, password);
            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setString(1, textField.getText());
//            ps.setString(2, this.id);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();

            class Grade
            {
                private String classname;
                private String grade;
                private String no;
                private String name;
                private String tname;

                public String getTname() {
                    return tname;
                }

                public void setTname(String tname) {
                    this.tname = tname;
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
            while (rs.next())
            {
                Grade grade=new Grade();
                grade.setName(rs.getString("sname"));
                grade.setTname(rs.getString("tname"));
                grade.setNo(rs.getString("sno"));
                grade.setClassname(rs.getString("class"));
                grade.setGrade(rs.getString("grade"));

                v.add(grade);
            }
            rs.close();
            for ( int i = 0;i < v.size(); i++){
                tableModel.addRow(new Object[]{v.get(i).getName(),v.get(i).getNo(),v.get(i).getClassname(),v.get(i).getTname(),v.get(i).getGrade()});
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException event)
        {
            JOptionPane.showMessageDialog(null,event.getMessage());
        }
    }
    private void insertStudent(String sql)
    {
            class student
            {
                private String name;
                private String sex;
                private String no;
                private String place;
                private String major;
                private String college;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getSex() {
                    return sex;
                }

                public void setSex(String sex) {
                    this.sex = sex;
                }

                public String getNo() {
                    return no;
                }

                public void setNo(String no) {
                    this.no = no;
                }

                public String getPlace() {
                    return place;
                }

                public void setPlace(String place) {
                    this.place = place;
                }

                public String getMajor() {
                    return major;
                }

                public void setMajor(String major) {
                    this.major = major;
                }

                public String getCollege() {
                    return college;
                }

                public void setCollege(String college) {
                    this.college = college;
                }
            }
            ArrayList<student> v= new ArrayList<>();
            for (int i=this.tableModel.getRowCount()-1;i<tableModel.getRowCount();i++)
            {
                student student=new student();
                int j=0;
                student.setNo(tableModel.getValueAt(i, j++).toString());
                student.setName(tableModel.getValueAt(i, j++).toString());
                student.setSex(tableModel.getValueAt(i, j++).toString());
                student.setPlace(tableModel.getValueAt(i, j++).toString());
                student.setMajor(tableModel.getValueAt(i, j++).toString());
                student.setCollege(tableModel.getValueAt(i,j).toString());
                v.add(student);
            }
            try {
                Class.forName(driver);
                conn = DriverManager.getConnection(url, admin, password);
                PreparedStatement ps = conn.prepareStatement(sql);
                for (int i = 0; i< v.size(); i++)
                {
                    ps.setString(1, v.get(i).getNo());
                    ps.setString(2, v.get(i).getName());
                    ps.setString(3, v.get(i).getSex());
                    ps.setString(4, v.get(i).getPlace());
                    ps.setString(5, v.get(i).getMajor());
                    ps.setString(6, v.get(i).getCollege());
                    tableModel.setRowCount(this.tableModel.getRowCount()+1);
                    ps.executeUpdate();
                }
                conn.close();

        } catch (ClassNotFoundException | SQLException event)
        {
            //event.printStackTrace();
            JOptionPane.showMessageDialog(null,event.getMessage());
        }
    }
    private void insertteacher(String sql)
    {
        class teacher
        {
            private String name;
            private String sex;
            private String tno;
            private String professor;
            private String birthdate;
            private String college;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getTno() {
                return tno;
            }

            public void setTno(String tno) {
                this.tno = tno;
            }

            public String getProfessor() {
                return professor;
            }

            public void setProfessor(String professor) {
                this.professor = professor;
            }

            public String getBirthdate() {
                return birthdate;
            }

            public void setBirthdate(String birthdate) {
                this.birthdate = birthdate;
            }

            public String getCollege() {
                return college;
            }

            public void setCollege(String college) {
                this.college = college;
            }
        }
        ArrayList<teacher> v= new ArrayList<>();
        for (int i=this.tableModel.getRowCount()-1;i<tableModel.getRowCount();i++)
        {
            teacher teacher=new teacher();
            int j=0;
            teacher.setTno(tableModel.getValueAt(i, j++).toString());
            teacher.setName(tableModel.getValueAt(i, j++).toString());
            teacher.setSex(tableModel.getValueAt(i, j++).toString());
            teacher.setBirthdate(tableModel.getValueAt(i, j++).toString());
            teacher.setCollege(tableModel.getValueAt(i, j++).toString());
            teacher.setProfessor(tableModel.getValueAt(i,j).toString());
            v.add(teacher);
        }
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, admin, password);
            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i< v.size(); i++)
            {
                ps.setString(1, v.get(i).getTno());
                ps.setString(2, v.get(i).getName());
                ps.setString(3, v.get(i).getSex());
                ps.setString(4, v.get(i).getBirthdate());
                ps.setString(5, v.get(i).getCollege());
                ps.setString(6, v.get(i).getProfessor());
                tableModel.setRowCount(this.tableModel.getRowCount()+1);
                ps.executeUpdate();
            }
            conn.close();

        } catch (ClassNotFoundException | SQLException event)
        {
            //event.printStackTrace();
            JOptionPane.showMessageDialog(null,event.getMessage());
        }
    }
}
