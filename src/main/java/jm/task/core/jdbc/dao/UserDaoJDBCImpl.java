//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//    private static Connection connection = Util.getConnection();
//
//    public UserDaoJDBCImpl() {
//
//    }
//
//    @Override
//    public void createUsersTable() {
//        try {
//            Statement statement = connection.createStatement();
//            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user " +
//                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), last_name VARCHAR(255), age INT)");
//            System.out.println("Таблица создана!");
//        } catch (SQLException e) {
//            System.out.println("ОШИБКА! Не удалось создать таблицу.");
//            ;
//        }
//    }
//
//    @Override
//    public void dropUsersTable() {
//        try {
//            Statement statement = connection.createStatement();
//            statement.executeUpdate("DROP TABLE IF NOT EXISTS user");
//            System.out.println("Таблица удалена!");
//        } catch (SQLException e) {
//            System.out.println("ОШИБКА! Не удалось удалить таблицу.");
//        }
//    }
//
//    @Override
//    public void saveUser(String name, String lastName, byte age) {
//        try (PreparedStatement preparedStatement
//                     = connection.prepareStatement("INSERT INTO user " +
//                "(name, last_name, age) VALUES (?, ?, ?)")) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setByte(3, age);
//            preparedStatement.executeUpdate();
//            System.out.println("Пользователь с именем " + name + " добавлен успешно!");
//        } catch (SQLException e) {
//            System.out.println("ОШИБКА! Не удалось добавить пользователя.");
//        }
//    }
//
//    @Override
//    public void removeUserById(long id) {
//        try (PreparedStatement pstm = connection.prepareStatement("DELETE FROM user WHERE id = ?")) {
//            pstm.setLong(1, id);
//            pstm.executeUpdate();
//            System.out.println("Пользователь удален");
//        } catch (SQLException e) {
//            System.out.println("ОШИБКА! Не удалось удалить пользователя.");
//        }
//    }
//
//    @Override
//    public List<User> getAllUsers() {
//        List<User> users = new ArrayList<>();
//
//        try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM user")) {
//            while(resultSet.next()) {
//                User user = new User(resultSet.getString("name"),
//                        resultSet.getString("last_name"), resultSet.getByte("age"));
//                user.setId(resultSet.getLong("id"));
//                users.add(user);
//            }
//        } catch (SQLException e) {
//            System.out.println("ОШИБКА! Таблица либо не создана, либо удалена!");
//        }
//
//        return users;
//    }
//
//    @Override
//    public void cleanUsersTable() {
//        String clean = "TRUNCATE user";
//        try (Statement statement = connection.createStatement()) {
//            statement.executeUpdate(clean);
//        } catch (SQLException e) {
//            System.out.println("ОШИБКА! Не удалось очистить таблицу.");
//            ;
//        }
//    }
//}
