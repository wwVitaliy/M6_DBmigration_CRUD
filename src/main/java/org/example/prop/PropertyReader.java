package org.example.prop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static final String APPLICATION_PROPERTIES_FILE = "application.properties";
    public static final String APPLICATION_PROPERTIES_FILE_EX_MESSAGE = "Cannot find application.properties";
    public static final String JDBC_TYPE_POSTGRES = "jdbc:postgresql://";
    public static final String POSTGRES_HOST_PROP_NAME = "postgres.db.host";
    public static final String POSTGRES_PORT_PROP_NAME = "postgres.db.port";
    public static final String POSTGRES_DB_PROP_NAME = "postgres.db.database";
    public static final String POSTGRES_USERNAME_PROP_NAME = "postgres.db.username";
    public static final String POSTGRES_PASSWORD_PROP_NAME = "postgres.db.password";
    public static final String POSTGRES_DB_NAME = "postgres.db.database";

    public static String getConnectionURLForPostgres() {
        try (InputStream inputStream = PropertyReader.class.getClassLoader()
                .getResourceAsStream(APPLICATION_PROPERTIES_FILE)) {

            if (inputStream == null) {
                System.out.println(APPLICATION_PROPERTIES_FILE_EX_MESSAGE);
                return null;
            }

            Properties prop = new Properties();
            prop.load(inputStream);

            return new StringBuilder(JDBC_TYPE_POSTGRES)
                    .append(prop.getProperty(POSTGRES_HOST_PROP_NAME))
                    .append(":")
                    .append(prop.getProperty(POSTGRES_PORT_PROP_NAME))
                    .append("/")
                    .append(prop.getProperty(POSTGRES_DB_PROP_NAME))
                    .toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getUserForPostgres() {
        try (InputStream inputStream = PropertyReader.class.getClassLoader()
                .getResourceAsStream(APPLICATION_PROPERTIES_FILE)) {

            if (inputStream == null) {
                System.out.println(APPLICATION_PROPERTIES_FILE_EX_MESSAGE);
                return null;
            }

            Properties prop = new Properties();
            prop.load(inputStream);

            return prop.getProperty(POSTGRES_USERNAME_PROP_NAME);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPasswordForPostgres() {
        try (InputStream inputStream = PropertyReader.class.getClassLoader()
                .getResourceAsStream(APPLICATION_PROPERTIES_FILE)) {

            if (inputStream == null) {
                System.out.println(APPLICATION_PROPERTIES_FILE_EX_MESSAGE);
                return null;
            }

            Properties prop = new Properties();
            prop.load(inputStream);

            return prop.getProperty(POSTGRES_PASSWORD_PROP_NAME);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDBName(){
        try (InputStream inputStream = PropertyReader.class.getClassLoader()
                .getResourceAsStream(APPLICATION_PROPERTIES_FILE)) {

            if (inputStream == null) {
                System.out.println(APPLICATION_PROPERTIES_FILE_EX_MESSAGE);
                return null;
            }

            Properties prop = new Properties();
            prop.load(inputStream);

            return prop.getProperty(POSTGRES_DB_NAME);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
