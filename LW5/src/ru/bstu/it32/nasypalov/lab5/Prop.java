package ru.bstu.it32.nasypalov.lab5;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class Prop {
    private String db;
    private String port;
    private String data;
    private String host;
    private String user;
    private String password;
    public Prop() {
        Properties prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("./settings.properties");
            prop.load(fis);
            db = new String(prop.getProperty("db").getBytes("ISO8859-1"));
            port = new String(prop.getProperty("port").getBytes("ISO8859-1"));
            data = new String(prop.getProperty("data").getBytes("ISO8859-1"));
            host = new String(prop.getProperty("host").getBytes("ISO8859-1"));
            user = new String(prop.getProperty("user").getBytes("ISO8859-1"));
            password = new String(prop.getProperty("password").getBytes("ISO8859-1"));
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл не найден");
            e.printStackTrace();

        }
    }

    public String getDb() {
        return db;
    }

    public String getPort() {
        return port;
    }

    public String getData() {
        return data;
    }

    public String getHost() {
        return host;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Prop{" +
                "db='" + db + '\'' +
                ", port='" + port + '\'' +
                ", data='" + data + '\'' +
                ", host='" + host + '\'' +
                ", user='" + user + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}