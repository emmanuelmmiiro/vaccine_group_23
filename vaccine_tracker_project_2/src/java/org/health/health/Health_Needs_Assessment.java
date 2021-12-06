/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.health.health;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jakarta.servlet.jsp.JspWriter;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 *
 * @author HP
 */
public class Health_Needs_Assessment extends SimpleTagSupport {

    private String table;
    private String displayformat;
    private String where;

    /**
     * Called by the container to invoke this tag.The implementation of this
 method is provided by the tag library developer, and handles all tag
 processing, body iteration, etc.
     * @throws jakarta.servlet.jsp.JspException
     * @throws java.io.IOException
     */
    @Override
    public void doTag() throws JspException, IOException {
        
        String retrieveQuery = "SELECT DISTINCT hv.hc_id,hv.vc_id, hc.name AS hc_name,vc.type AS vaccine_name, hv.dozzes  AS dozzes"
                + " FROM hc_vc AS hv "
                + " LEFT JOIN " + table + " hc"
                + " ON hv.hc_id = hc.id"
                + " LEFT JOIN vaccine vc"
                + " ON hv.vc_id = vc.id"
                + " ORDER BY hv.id DESC";
       
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException ex){
             System.out.println(ex);
        }
        
        JspWriter out = getJspContext().getOut();
        
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccine_tracker","root","");  
            Statement st= con.createStatement();
            
            //health centers results
            ResultSet r = st.executeQuery(retrieveQuery);
            int x = 1;
            
            
            if("table".equals(displayformat)){
                out.println(" <table class=\"table\">\n" +
"                    <tr>\n" +
"                        <th></th>\n" +
"                        <th>HEALTH CENTER</th>\n" +
"                        <th>AVAILABLE VACCINE</th>\n" +
"                        <th>TOTAL DOZZES</th>\n" +
"                        <th>CURRENT BOOKING'S</th>\n" +
"                        <th>VACCINATED PATIENT'S</th>\n" +
"                    </tr>");
                while(r.next()){
                    out.println("<tr><td>"+x+"</td>");
                    out.println("<td>"+r.getString("hc_name")+"</td>");
                    out.println("<td>"+r.getString("vaccine_name")+"</td>");
                    out.println("<td>"+r.getString("dozzes")+"</td>");
                    out.println("<td>"+number_of_bookings(r.getInt("hc_id"),r.getInt("vc_id"))+"</td>");
                    out.println("<td>"+number_of_patients(r.getInt("hc_id"),r.getInt("vc_id"))+"</td>");
                    x++;
                }
                out.println("</table>");
            
                st.close();
                con.close();
            }else{
                out.println("<ul>");
                    while(r.next()){
                        
                        out.println("<li><p>"+r.getString("hc_name")+"</p>");
                        out.println("<p>"+r.getString("vaccine_name")+"</p>");
                        out.println("<p>"+r.getString("dozzes")+"</p>");
                        out.println("<p>"+number_of_bookings(r.getInt("hc_id"),r.getInt("vc_id"))+"</p>");
                        out.println("<p>"+number_of_patients(r.getInt("hc_id"),r.getInt("vc_id"))+"</p></li>");
     
                    }
                out.println("</ul>");
            }
            
        } catch (java.io.IOException ex) {
            throw new JspException("Error in Needs_Assessment tag", ex);
        } catch (SQLException ex) {
            out.println(ex);
        }
    }
    
    
    public int number_of_bookings(int hc_id,int vc_id){
        int number  = 0;          
        String query = "SELECT COUNT(id) AS total_bookings " +
                   "FROM booking " +   
                   "WHERE health_center_id = " + hc_id;
                  
        
       
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccine_tracker","root","");  
            Statement st= con.createStatement();
            
            //health centers results
            ResultSet r = st.executeQuery(query);
            while(r.next()){
                number  = r.getInt("total_bookings");
            }
            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return number;
    }
    
    public int number_of_patients(int hc_id, int vc_id){
        int number  = 0;          
        String query = "SELECT COUNT(nin) AS total_patients " +
                   "FROM people " +   
                   "WHERE health_center_id = " + hc_id + " " +
                   "AND vaccine_id = " + vc_id; 
       
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/vaccine_tracker","root","");  
            Statement st= con.createStatement();
            
            //health centers results
            ResultSet r = st.executeQuery(query);
            while(r.next()){
                number  = r.getInt("total_patients");
            }
            
            st.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return number;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public void setDisplayformat(String displayformat) {
        this.displayformat = displayformat;
    }

    public void setWhere(String where) {
        this.where = where;
    }
    
}
