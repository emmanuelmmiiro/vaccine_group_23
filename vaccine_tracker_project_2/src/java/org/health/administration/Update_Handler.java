/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.health.administration;

import org.health.health.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author HP
 */
public class Update_Handler extends SimpleTagSupport {

    private String table;
    private String where;
    private String newvalues;

    /**
     * Called by the container to invoke this tag.The implementation of this
 method is provided by the tag library developer, and handles all tag
 processing, body iteration, etc.
     * @throws jakarta.servlet.jsp.JspException
     * @throws java.io.IOException
     */
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        
        String update_query = "UPDATE "+table+ " SET "+newvalues+"' WHERE id = "+where;
       
        //attempting to import driver class
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
             System.out.println(ex);
        }
        
        try {
            //connecting to database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccine_tracker","root","");  
            
            //executing query
            PreparedStatement st = con.prepareStatement(update_query);
           
            st.executeUpdate();
                
            out.println("<div class=\"alert alert-success\" role=\"alert\">\n" +newvalues+
                " updated successfully!\n" +
                "</div>");
            
            
            st.close();
            con.close();  
        } catch (SQLException ex) {
            out.println(ex);
        }
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setWhere(String where) {
        this.where = where;
    }

    public void setNewvalues(String newvalues) {
        this.newvalues = newvalues;
    }
    
}
