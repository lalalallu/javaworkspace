
package login;
/*
管理员实体
*/

public class User {
    private String id;                 //编号
    private String password;      //密码
    private String limits;           //权限

    public void setID(String id) {
        this.id=id;
    }
    void setName(String name) {
        this.limits=limits;
    }
    public void setPassword(String password) {
        this.password=password;
    }

    String getID() {
        return this.id;
    }
    String getName() {
        return this.limits;
    }
    String getPassword() {
        return this.password;
    }

}


