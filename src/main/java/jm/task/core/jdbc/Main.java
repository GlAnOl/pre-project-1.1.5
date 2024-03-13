package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {


        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Oleg1", "Glush1", (byte) 51);
        userService.saveUser("Oleg2", "Glush2", (byte) 52);
        userService.saveUser("Oleg3", "Glush3", (byte) 53);
        userService.saveUser("Oleg4", "Glush4", (byte) 54);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();


    }
}
