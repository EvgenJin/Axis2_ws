/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evg_axis_ws;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.axis2.context.MessageContext;

/**
 *
 * @author eshahov
 */
public class PersonDBService {
    /**
     *
     * @return
     * @throws java.sql.SQLException
     */
    public List<Person> getPerson() throws SQLException {
        Person person = new Person();
        ArrayList list = new ArrayList();
        Connection conn = (Connection) MessageContext.getCurrentMessageContext().getProperty(DBSampleServiceLifeCycle.DB_CONNECTION);
        if(conn != null) {
            String SQL = "SELECT * FROM exp_cus where rownum <= 1000";
            PreparedStatement statement = conn.prepareStatement(SQL);
                ResultSet result = statement.executeQuery();
                while (result.next()) {
                    person.setId(result.getInt("id"));
                    person.setName(result.getString("PLACE1"));
                    person.setAddress(result.getString("PLACE1"));
                    list.add(person);
                }
            return list;
        }
        return null;
    }
    
}
