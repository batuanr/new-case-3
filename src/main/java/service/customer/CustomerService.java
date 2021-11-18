package service.customer;

import connection.ConnectionSingleton;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomerService{
    Connection connection= ConnectionSingleton.getConnection();
    @Override
    public List<Customer> findAll() {
        List<Customer> customerList=new ArrayList<>();
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select *from customer");
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                String name=rs.getString("name");
                String phone= rs.getString("phone");
                String email=rs.getString("email");
                String pass=rs.getString("pass");
                Customer customer=new Customer(name,phone,email,pass);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customerList;
    }

    @Override
    public void save(Customer customer) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("insert into customer(name,phone,email,pass) value (?,?,?,?);");
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2,customer.getPhone());
            preparedStatement.setString(3,customer.getEmail());
            preparedStatement.setString(4,customer.getPass());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  boolean checkAccount(String email,String pass){
        boolean check=false;
        Customer customer=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select * from customer where email=? and pass=?");
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,pass);
            ResultSet rs=preparedStatement.executeQuery();
            if(rs.next()){
                String name=rs.getString("name");
                String phone=rs.getString("phone");
                customer=new Customer(name,phone,email,pass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(customer!=null){
            check=true;
        }
        return check;
    }
    @Override
    public Customer findById(int id) {
        Customer customer=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select * from customer where id=?");
            preparedStatement.setInt(1,id);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                String name=rs.getString("name");
                String phone =rs.getString("phone");
                String email=rs.getString("email");
                String pass=rs.getString("pass");
                customer=new Customer(name,phone,email,pass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return customer;
    }

    public Customer findByEmail(String email){
        Customer customer=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("select * from customer where email=?;");
            preparedStatement.setString(1,email);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                String phone=resultSet.getString("phone");
                String pass=resultSet.getString("pass");
                customer= new Customer(id,name,phone,email,pass);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;

    }
    @Override
    public void update(int id, Customer customer) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("update customer set name=?,phone=?,email=?,pass=? where id=?");
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPass());
            preparedStatement.setInt(5,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("delete from customer where id=?;");
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
