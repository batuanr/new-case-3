package service.admin;

import model.Admin;

import java.util.List;

public interface IAdminService {
    List<Admin> findAllAdmin();
    void save(Admin admin);
    Admin findById(int id);
    void update(int id,Admin admin);
    void remove(int id);
}
