package ua.com.expo.model.dao.daoImpl;

import ua.com.expo.entity.Role;
import ua.com.expo.model.dao.interfaces.IRoleDao;

import java.util.List;

public class MySqlRoleDao implements IRoleDao {

    @Override
    public List<Role> findAll() {
        return null;
    }

    @Override
    public Role findEntityById(Long id) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean delete(Role entity) {
        return false;
    }

    @Override
    public boolean create(Role entity) {
        return false;
    }

    @Override
    public Role update(Role entity) {
        return null;
    }
}
