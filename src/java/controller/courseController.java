package controller;

;
import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.courseModel;



@ManagedBean(name = "courseController")
public class courseController 
{
    public String openCourse() {
        return "openCourse";
    }

    public String deleteCourse() {
        return "deleteCourse";
    }

    public String updateCourse() {
        return "updateCourse";
    }
   
    public void openCourse(String courseName, String courseDay, String startTime, String finishTime, String courseInstructor, String courseClass,int courseId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO courses(courseName, courseDay, startTime, finishTime, courseInstructor, courseClass,courseId) VALUES (?,?,?,?,?,?,?)";
            st = con.prepareStatement(sql);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc11")));
            
            st.setString(1, courseName);
            st.setString(2, courseDay);
            st.setString(3, startTime);
            st.setString(4, finishTime);
            st.setString(5, courseInstructor);
            st.setString(6, courseClass);
            st.setInt(7, courseId);
            st.executeUpdate();
            con.commit();

            st.close();
        } catch (Exception e) {
            Logger.getLogger(courseController.class.getName()).log(Level.SEVERE, null, e);
            try {
                con.close();
            } catch (SQLException e1) {
            }
        }

    }
   public void deleteCourse(String delCourseName) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "delete from  courses  where courseName = '" + delCourseName + "'";
            st = con.prepareStatement(sql);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc7")));

            st.executeUpdate();
            con.commit();

            st.close();
        } catch (Exception e) {
            Logger.getLogger(courseController.class.getName()).log(Level.SEVERE, null, e);
            try {
                con.close();
            } catch (SQLException e1) {
            }
        }

    }
     public void dropCourse(String delCourseName) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "delete from  takecourses  where courseName = '" + delCourseName + "'";
            st = con.prepareStatement(sql);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc7")));

            st.executeUpdate();
            con.commit();

            st.close();
        } catch (Exception e) {
            Logger.getLogger(courseController.class.getName()).log(Level.SEVERE, null, e);
            try {
                con.close();
            } catch (SQLException e1) {
            }
        }

    }
    
    public void updateCourse(String updateCourseName, String updateCourseDay, String updateStartTime, String updateFinishTime, String updateCourseInstructor, String updateCourseClass,int courseId) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "update courses set coursename = ?, courseDay = ?, startTime =?, finishTime =?, courseInstructor =? ,courseClass =?,courseId = ? where courseId = '" + courseId + "'";
            st = con.prepareStatement(sql);
            st.setString(1, updateCourseName);
            st.setString(2, updateCourseDay);
            st.setString(3, updateStartTime);
            st.setString(4, updateFinishTime);
            st.setString(5, updateCourseInstructor);
            st.setString(6, updateCourseClass);
            st.setInt(7, courseId);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc9")));

            st.executeUpdate();
            con.commit();

            st.close();
        } catch (Exception e) {
            Logger.getLogger(courseController.class.getName()).log(Level.SEVERE, null, e);
            try {
                con.close();
            } catch (SQLException e1) {
            }
        }

    }
    public void takeCourse(String courseName) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO takecourses select * from courses where courseName = '" + courseName + "'";
            st = con.prepareStatement(sql);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc11")));
            
            
            st.executeUpdate();
            con.commit();

            st.close();
        } catch (Exception e) {
            Logger.getLogger(courseController.class.getName()).log(Level.SEVERE, null, e);
            try {
                con.close();
            } catch (SQLException e1) {
            }
        }

    }
    public static ArrayList<courseModel> getCheckCourses() throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "Select * from courses";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            ArrayList<courseModel> al = new ArrayList<>();
            while (rs.next()) {
                courseModel cm = new courseModel();
                cm.setCourseName(rs.getString(1));
                cm.setCourseDay(rs.getString(2));
                cm.setStartTime(rs.getString(3));
                cm.setFinishTime(rs.getString(4));
                cm.setCourseInstructor(rs.getString(5));
                cm.setCourseClass(rs.getString(6));
                cm.setCourseId(rs.getInt(7));
  
                al.add(cm);
            }

            return al;

        } catch (Exception e) {
            Logger.getLogger(personController.class.getName()).log(Level.SEVERE, null, e);
            try {
                con.close();
            } catch (SQLException e1) {
            }
        }
        return null;
        
    }
     public static ArrayList<courseModel> getTakenCourses() throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "Select * from takecourses";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            ArrayList<courseModel> al = new ArrayList<>();
            while (rs.next()) {
                courseModel cm = new courseModel();
                cm.setCourseName(rs.getString(1));
                cm.setCourseDay(rs.getString(2));
                cm.setStartTime(rs.getString(3));
                cm.setFinishTime(rs.getString(4));
                cm.setCourseInstructor(rs.getString(5));
                cm.setCourseClass(rs.getString(6));
                cm.setCourseId(rs.getInt(7));
  
                al.add(cm);
            }

            return al;

        } catch (Exception e) {
            Logger.getLogger(personController.class.getName()).log(Level.SEVERE, null, e);
            try {
                con.close();
            } catch (SQLException e1) {
            }
        }
        return null;
        
    }
   
}
