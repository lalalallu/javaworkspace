package db;

import java.sql.*;

public class DB
{
    private Connection dbconn;
    private Statement stateMent;
    public DB()
    {
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dburl="jdbc:sqlserver://localhost:1433;DatabaseName=Education";
        String userName="sa";
        String userPwd="TY1472583690";
        try {
            Class.forName(driverName);
            dbconn= DriverManager.getConnection(dburl,userName,userPwd);
            //System.out.println("Connection Successful!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public int executeUpdate(String sql) throws SQLException
    {
        stateMent=dbconn.createStatement();
        return stateMent.executeUpdate(sql);
    }
    public ResultSet executeQuery(String sql) throws SQLException
    {
        stateMent=dbconn.createStatement();
        return stateMent.executeQuery(sql);
    }
    public void closeConn() throws SQLException
    {
        stateMent.close();
        dbconn.close();
    }
}
class DB_test
{
    public static void main(String[] args) {
        new DB();
    }
}
