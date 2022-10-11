package login;
/*
处理用户登录
*/

import java.sql.*;


public class Login {

    User user;

    public Login()
    {

    }
    public void setAdmin(User user) {
        this.user = user;
        //System.out.println(this.admin.getPassword()+"   " + this.admin.getID());
    }
    /*
     * JudgeAdmin()方法
     * 判断Admin的ID和密码是否正确，如果正确，显示登录成功
     * 如果错误，弹出一个窗口，显示账号或密码错误
     */
    private String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String url = "jdbc:sqlserver://localhost:1433;DatabaseName=student";//添加数据库名
    private String admin = "sa";//账号
    private String password = "TY1472583690";//密码
    private Connection conn;
    public boolean login(User user) throws SQLException, ClassNotFoundException
    {
        String sql="select * from stu_users where id=? and pass=?";
        Class.forName(driver);
        conn = DriverManager.getConnection(url, admin, password);
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, user.getID());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        boolean bool=rs.next();
        rs.close();
        ps.close();
        conn.close();
        return bool;
    }

    public Connection getConnection()
    {
        return this.conn;
    }
    public boolean login1(User user) throws SQLException, ClassNotFoundException
    {
        String sql="select * from tea_users where id=? and pass=?";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, admin, password);
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, user.getID());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        boolean bool=rs.next();
        rs.close();
        ps.close();
        conn.close();
        return bool;
    }
    public boolean login0(User user) throws SQLException, ClassNotFoundException
    {
        String sql="select * from man_users where id=? and pass=?";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection(url, admin, password);
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, user.getID());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        boolean bool=rs.next();
        rs.close();
        ps.close();
        conn.close();
        return bool;
    }
    public int JudgeAdmin(int i) {
        if (i==0)
        {
            try {
                if (login0(this.user)) {
                    //System.out.println("登录成功");
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (i==1)
        {
            try {
                if (login1(this.user)) {
                    //System.out.println("登录成功");
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (i==2)
        {
            try {
                if (login(this.user)) {
                    //System.out.println("登录成功");
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return 0;
    }
}

