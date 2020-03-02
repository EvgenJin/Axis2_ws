package com.evg_axis_ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axis2.description.AxisService;
import org.apache.axis2.engine.ServiceLifeCycle;

public class DBSampleServiceLifeCycle implements ServiceLifeCycle {
    public static final String DB_CONNECTION = "dbconnection";

    @Override
    public void startUp(ConfigurationContext configctx, AxisService service) {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            // Creating the DB connection for the sample DB
            Connection conn;
            conn = DriverManager.getConnection("jdbc:oracle:thin:@10.26.232.216:1521:card","zc","gtpltw21");
            //Storing the DB in the ConfigurationContext
            configctx.setProperty(DB_CONNECTION, conn);
        } 
        catch (ClassNotFoundException | SQLException e) {
        }
    }

    @Override
    public void shutDown(ConfigurationContext configctx, AxisService service) {
        Connection conn = (Connection) configctx.getProperty(DB_CONNECTION);
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error while closing the DB connection");
            }
        }
    }

}
