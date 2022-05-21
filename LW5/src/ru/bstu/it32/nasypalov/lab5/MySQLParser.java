package ru.bstu.it32.nasypalov.lab5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class MySQLParser {
    private static ResultSet rs;
    private static Connection con;
    private static Statement statement;
    private static Prop p;
    private static String url;

    public MySQLParser() throws SQLException, ClassNotFoundException {
        //Class.forName("com.mysql.jdbc.Driver");
        p = new Prop();
        url = String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", p.getHost(), p.getPort(), p.getDb());
    }

    public void selectSQL() throws SQLException {
        try {
            con = DriverManager.getConnection(url, p.getUser(), p.getPassword());
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT * FROM cars");
            String[] temp = new String[7];
            while (rs.next()) {
                int id = rs.getInt(1);
                for (int i = 2; i < 9; i++) {
                    temp[i - 2] = rs.getString(i);
                }
                System.out.printf("id: %d, Brand: %s, Model: %s, Color: %s, Plate: %s, FirstName: %s, SecondName: %s, MiddleName: %s %n", id, temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                statement.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }

    }

    public void updateSQL(Integer id, String ...params) throws SQLException {
        try {
            con = DriverManager.getConnection(url, p.getUser(), p.getPassword());
            con.setAutoCommit(false);
            statement = con.createStatement();
            //String sql = String.format("insert into cars values(NULL, %s, %s, %s, %s, %s, %s, %s)",  params[0], params[1], params[2], params[3], params[4], params[5], params[6]);
            String sql = String.format("UPDATE cars SET Brand = '%s', Model = '%s', Color =  '%s', Plate = '%s', FirstName = '%s', SecondName = '%s', MiddleName = '%s' WHERE id = %d",  params[0], params[1], params[2], params[3], params[4], params[5], params[6], id);
            statement.execute(sql);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                statement.close();
            } catch (SQLException se) { /*can't do anything */ }
        }


    }

    public void insertSQL(String ...params) throws SQLException {
        try {
            con = DriverManager.getConnection(url, p.getUser(), p.getPassword());
            con.setAutoCommit(false);
            statement = con.createStatement();
            String sql = String.format("insert into cars values(NULL, '%s', '%s', '%s', '%s', '%s', '%s', '%s')",  params[0], params[1], params[2], params[3], params[4], params[5], params[6]);
            //String sql = String.format("UPDATE cars SET Brand = %s, Model = %s, Color =  %s, Plate = %s, FirstName = %s, SecondName %s, MiddleName = %s WHERE id = %d",  params[0], params[1], params[2], params[3], params[4], params[5], params[6], id);
            statement.execute(sql);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                statement.close();
            } catch (SQLException se) { /*can't do anything */ }

        }
    }

    public void deleteSQL(Integer id) throws SQLException {
        try {
            con = DriverManager.getConnection(url, p.getUser(), p.getPassword());
            con.setAutoCommit(false);
            statement = con.createStatement();
            String sql = String.format("DELETE FROM cars WHERE id = %d", id);
            //String sql = String.format("UPDATE cars SET Brand = %s, Model = %s, Color =  %s, Plate = %s, FirstName = %s, SecondName %s, MiddleName = %s WHERE id = %d",  params[0], params[1], params[2], params[3], params[4], params[5], params[6], id);
            statement.execute(sql);
            con.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                statement.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }

    public void findSQL(Integer id, String ...params){
        try {
            con = DriverManager.getConnection(url, p.getUser(), p.getPassword());
            statement = con.createStatement();
            String sql = String.format("SELECT * FROM cars WHERE "); //WHERE ID = %d AND Brand = '%s', Model = '%s', Color =  '%s', Plate = '%s', FirstName = '%s', SecondName = '%s', MiddleName = '%s'", id, params[0], params[1], params[2], params[3], params[4], params[5], params[6]);
            if (id != 0)
                sql += String.format("id = %d", id);
            if (!params[0].equals("0"))
                sql += String.format(", Brand = '%s'", params[0]);
            if (!params[1].equals("0"))
                sql += String.format(", Model = '%s',", params[1]);
            if (!params[2].equals("0"))
                sql += String.format(", Color = '%s',", params[2]);
            if (!params[3].equals("0"))
                sql += String.format(", Plate = '%s',", params[3]);
            if (!params[4].equals("0"))
                sql += String.format(", FirstName = '%s'", params[4]);
            if (!params[5].equals("0"))
                sql += String.format(", SecondName = '%s'", params[5]);
            if (!params[6].equals("0"))
                sql += String.format(", MiddleName = '%s'", params[6]);
            rs = statement.executeQuery(sql);
            String[] temp = new String[7];
            while (rs.next()) {
                for (int i = 2; i < 9; i++) {
                    temp[i - 2] = rs.getString(i);
                }
                System.out.printf("id: %d, Brand: %s, Model: %s, Color: %s, Plate: %s, FirstName: %s, SecondName: %s, MiddleName: %s %n", id, temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                statement.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }
    public void convertToXML(){
        try {
            con = DriverManager.getConnection(url, p.getUser(), p.getPassword());
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT * FROM cars");
            String[] temp = new String[7];
            while (rs.next()) {
                int id = rs.getInt(1);
                for (int i = 2; i < 9; i++) {
                    temp[i - 2] = rs.getString(i);
                }
                //System.out.printf("id: %d, Brand: %s, Model: %s, Color: %s, Plate: %s, FirstName: %s,
                // SecondName: %s, MiddleName: %s %n", id, temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
                XMLParser write = new XMLParser();
                write.writeXML(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5], temp[6]);
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        } finally {
            //close connection ,stmt and resultset here
            try {
                con.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                statement.close();
            } catch (SQLException se) { /*can't do anything */ }
            try {
                rs.close();
            } catch (SQLException se) { /*can't do anything */ }
        }
    }
}
