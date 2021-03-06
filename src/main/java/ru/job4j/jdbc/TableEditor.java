package ru.job4j.jdbc;

import org.postgresql.util.PSQLException;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) throws SQLException, ClassNotFoundException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url,
                login, password);
    }

    private void query(String query) throws Exception {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createTable(String tableName) throws Exception {
        String sql = String.format(
                "create table if not exists %s(%s);",
                tableName,
                "id serial primary key"
        );
        query(sql);
    }

    public void dropTable(String tableName) throws Exception {
        String sql = String.format(
                "drop table %s",
                tableName
        );
        query(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        String sql = String.format(
                "alter table %s add column %s %s",
                tableName,
                columnName,
                type
        );
        query(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        String sql = String.format(
                "alter table %s drop column %s",
                tableName,
                columnName
        );
        query(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)
            throws Exception {
        String sql = String.format(
                "alter table %s rename column %s to %s",
                tableName,
                columnName,
                newColumnName
        );
        query(sql);
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            ResultSet selection = null;
            try {
                selection = statement.executeQuery(String.format(
                        "select * from %s limit 1", tableName
                ));
            } catch (PSQLException e) {
                return "?????????????? ???????? ??????????????";
            }
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try (FileReader fr = new FileReader("./app.properties")) {
            properties.load(fr);
        }
        try (TableEditor tableEditor = new TableEditor(properties)) {
            String tableName = "demo_table";
            tableEditor.createTable(tableName);
            System.out.println(TableEditor.getTableScheme(tableEditor.connection, tableName));
            tableEditor.addColumn(tableName, "name", "text");
            System.out.println(TableEditor.getTableScheme(tableEditor.connection, tableName));
            tableEditor.dropColumn(tableName, "name");
            System.out.println(TableEditor.getTableScheme(tableEditor.connection, tableName));
            tableEditor.renameColumn(tableName, "id", "num");
            System.out.println(TableEditor.getTableScheme(tableEditor.connection, tableName));
            tableEditor.dropTable(tableName);
            System.out.println(TableEditor.getTableScheme(tableEditor.connection, tableName));
        }
    }
}