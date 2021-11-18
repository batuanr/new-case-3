package service.admin;

import connection.ConnectionSingleton;
import model.Admin;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService implements IAdminService{
    Connection connection= ConnectionSingleton.getConnection();
    @Override
    public List<Admin> findAllAdmin() {
        List<Admin> adminList= new ArrayList<>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select * from admin ");
            ResultSet rs =preparedStatement.executeQuery();
            while (rs.next()){
                int id=rs.getInt("id");
                String account=rs.getString("account");
                String pass = rs.getString("pass");
                Admin admin=new Admin(account,pass);
                adminList.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminList;
    }
    public boolean checkAccount(String account,String pass){
        boolean check=false;
        Admin admin=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select * from admin where account=? and pass=?");
            preparedStatement.setString(1,account);
            preparedStatement.setString(2,pass);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                String account1=rs.getString("account");
                String pass1=rs.getString("pass");
                admin=new Admin(account1,pass1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(admin!=null){
            check=true;
        }
        return check;
    }

    @Override
    public void save(Admin admin) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("insert into admin (account, pass) value (?,?)");
            preparedStatement.setString(1,admin.getAccount());
            preparedStatement.setString(2, admin.getPass());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Admin findById(int id) {
        Admin admin=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select *from admin where id=?");
            preparedStatement.setInt(1,id);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                String account =rs.getString("account");
                String pass=rs.getString("pass");
                admin=new Admin(account,pass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;

    }

    @Override
    public void update(int id, Admin admin) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("update admin set account=?,pass=? where id=?;");
            preparedStatement.setString(1,admin.getAccount());
            preparedStatement.setString(2,admin.getPass());
            preparedStatement.setInt(3,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(int id) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("delete from admin where id=?;");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
