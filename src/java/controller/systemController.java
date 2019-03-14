package controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.RequestScoped;
import javax.annotation.PostConstruct;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.personModel;

@ManagedBean(name = "systemController")
@SessionScoped
@RequestScoped
public class systemController {

    private static final long serialVersionUID = 1L;
    private ArrayList<String> list = new ArrayList<>();


    public String loginDirect() {
        return "index";
    }

    public String gymMainPage() {
        return "gym";
    }

    public String signUpDirect() {
        return "register";
    }

    public String forgetPassword() {
        return "forgetPassword";
    }
    
    public String registerCreditPage() {
        return "register2";
    }

    public String communicationDirect() {
        return "communication";
    }

    public String editCourseSchedule() {
        return "editCourseSchedule";
    }

    public String paymentInfo() {
        return "paymentInfo";
    }

    public String addNewTrainer() {
        return "addNewTrainer";
    }

    public String updateTrainer() {
        return "updateTrainer";
    }

    public String deleteTrainer() {
        return "deleteTrainer";
    }
    
    public String addNewManager() {
        return "addNewManager";
    }

    public String updateManager() {
        return "updateManager";
    }

    public String deleteManager() {
        return "deleteManager";
    }

    public String makeAnnouncementTrainer() {
        return "makeAnnouncementTrainer";
    }
    public String makeAnnouncementManager() {
        return "makeAnnouncementManager";
    }

    public String checkTrainerInfo() {
        return "checkTrainerInfo";
    }

    public String courseFollowupSystem() {
        return "courseFollowupSystem";
    }

    public String checkMyCourses() {
        return "checkMyCourses";
    }

    public String takeCourses() {
        return "takeCourses";
    }


    public String personHealthSystem() {
        return "personHealthSystem";
    }

    public String health1() {
        return "health1";
    }

    public String health2() {
        return "health2";
    }

    public String returnToManagerPage() {
        return "managerMainPage";
    }

    public String health3() {
        return "health3";
    }

    public String updateInformation() {
        return "updateInformation";
    }
    public String checkCourseMembers() {
        return "checkCourseMembers";
    }
    
    public String checkMySchedule() {
        return "checkMySchedule";
    }

    public String makeAPayment() { 
        return "makeAPayment";
    }

    public String showAnnouncement() {
        showAnnouncements();
        return "showAnnouncements";
    }

    public String cancellationOfMembership() {
        return "cancellationOfMembership";
    }

    public String logout() {
        return "index";
    }
  
    public String dropCourses() {
        return "dropCourses";
    }

    public ArrayList getlist() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setlist(ArrayList list) {
        this.list = list;
    }


    public void showAnnouncements() {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "987654321");
            ps = con.prepareStatement("Select * from announcements");
            rs = ps.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("announcement"));
            }
        } catch (Exception e) {

        }
    }
    @PostConstruct
    public void clearAnnouncement(){
      list.clear();
    }


    public void announcementSend(String announcement) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO announcements(announcement) VALUES (?)";
            st = con.prepareStatement(sql);

            st.setString(1, announcement);
            st.executeUpdate();
            con.commit();
            st.close();

        } catch (Exception e) {
            Logger.getLogger(personController.class.getName()).log(Level.SEVERE, null, e);
            try {
                con.close();
            } catch (SQLException e1) {
            }
        }
    }
}
