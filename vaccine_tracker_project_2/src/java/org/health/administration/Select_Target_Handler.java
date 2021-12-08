/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.health.administration;

import org.health.health.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 *
 * @author HP
 */
public class Select_Target_Handler extends SimpleTagSupport {

    private String table;
    private String where = "";
    private String displayformat;

    /**
     * Called by the container to invoke this tag.The implementation of this
 method is provided by the tag library developer, and handles all tag
 processing, body iteration, etc.
     * @throws jakarta.servlet.jsp.JspException
     */
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
             System.out.println(ex);
        }
        
        String retrieveQuery = "SELECT COUNT(*) as num FROM " + table + "";
        
        //checking if where 
        if(!this.where.trim().equals("")){
          retrieveQuery = "SELECT COUNT(*) as num FROM " + table + "";
        
        }
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccine_tracker","root","");  
            Statement st= con.createStatement();
            
            //health centers results
            ResultSet r = st.executeQuery(retrieveQuery);
            int x = 1;
            
            if("table".equals(displayformat)){
                
                out.println("<table class='table'>"
                    + "<tr>"
                   
                    + "<th>VACCINATED PEOPLE</th>"
                    + "<th>TARGET</th>"
                    + "<th>PERCENTAGE</th>"
                    + "<th>REMAINING_PERCENTAGE</th>"
                    + "</tr>");
                         
                    while(r.next()){
                        
   
                        double count = r.getDouble("num");
                        
                        double target = 100.000;
                       
                        double percentage = (double)((count/target)*target);
                        out.println("<td>"+r.getString("num")+"</td>");
                        out.println("<td>"+target+"</td>");
                        out.println("<td>"+percentage+"%</td>");
                        out.println("<td>"+(target-percentage)+"%</td>");
                        
                   
                    }
                out.println("</table>");
            
                st.close();
                con.close();
                
            }else{
               
                out.println("<ol>");
                while(r.next()){
                        out.println("<li>");
                        out.println("<p>"+r.getString("id")+"</p>");
                        out.println("<p>"+r.getString("name")+"</p>");
                        out.println("<p>"+r.getString("location")+"</p>");
                        out.println("<p>"+r.getString("added_on")+"</p>");
                        out.println("<p><a type='button' class='btn btn-sm btn-primary' href='/vaccine_tracker_project/update_health_center.jsp?id="+r.getString("id")+"&hc="+r.getString("name")+"&loc="+r.getString("location")+"'>UPDATE</p> ");
                        out.println("</li>");
                }
                out.println("</ol>");
                st.close();
                con.close();
            }
            
            
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

    public void setDisplayformat(String displayformat) {
        this.displayformat = displayformat;
    }
    
}
