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
import model.personModel;



@ManagedBean(name = "personController")
public class personController {

    List<personModel> liste = new ArrayList<personModel>();
    FacesContext context = FacesContext.getCurrentInstance();
    
    public int login(String UName, String PWord, String type) throws SQLException, ClassNotFoundException {
        Connection con;
        PreparedStatement ps;
        ResultSet rs;
        int controlnum = -1;
        int sayNum = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "987654321");
            ps = con.prepareStatement("Select * from users");
            rs = ps.executeQuery();
            while (rs.next()) {

                if (rs.getString("username").equals(UName) && rs.getString("password").equals(PWord)) {
                    if (rs.getString("job").equals("member")) {
                        type += "member";
                        controlnum = 2;
                        break;
                    } else if (rs.getString("job").equals("trainer")) {
                        type += "trainer";
                        controlnum = 3;
                        break;
                    } else if (rs.getString("job").equals("manager")) {
                        type += "manager";
                        controlnum = 4;
                        break;
                    } else if (rs.getString("job").equals("systemAdmin")) {
                        type += "systemAdmin";
                        controlnum = 5;
                        break;
                    }
                } else {
                    sayNum++;
                    if (sayNum == 1) {
                        Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
                        ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, r.getString("javapc2"), r.getString("javapc1")));
                    }
                    controlnum = 0;
                }
            }
        } catch (Exception e) {

        }
        return controlnum;
    }

    public String logout() {
        HttpSession session = getSession();
        session.invalidate();
        return "index";
    }

    public void registerMemberSend(int id, String username1, String password1, String name, String surname, String telNo, String mail, String birthday,String question, String job) throws ClassNotFoundException, SQLException {
        PreparedStatement ps;
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Connection con2 = null;
        PreparedStatement st2 = null;
        ResultSet rs2 = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";
        int exist = 0;
       try {

            Class.forName("com.mysql.jdbc.Driver");
            con2 = DriverManager.getConnection(url, username, password);
            String sql = "Select * from  users";
            con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "987654321");
            ps = con2.prepareStatement("Select * from users");
            rs2 = ps.executeQuery();
            while(rs2.next()){
                if(rs2.getString("username").equals(username1))
                exist=1;
            }
            st2.executeUpdate();
            con2.commit();

            st2.close();
        } catch (Exception e) {
            Logger.getLogger(personController.class.getName()).log(Level.SEVERE, null, e);
            try {
                con2.close();
            } catch (SQLException e1) {
            }
        }
       if(exist!=1){
       try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO users(id, username, password, name, surname, telNo, mail, birthday,answer ,job) VALUES (?,?,?,?,?,?,?,?,?,?)";
            st = con.prepareStatement(sql);
            
            st.setInt(1, id);
            st.setString(2, username1);
            st.setString(3, password1);
            st.setString(4, name);
            st.setString(5, surname);
            st.setString(6, telNo);
            st.setString(7, mail);
            st.setString(8, birthday);
            st.setString(9, question);
            st.setString(10, job);
            st.executeUpdate();
            con.commit();
            
            st.close();
        } catch (Exception e) {
            Logger.getLogger(personController.class.getName()).log(Level.SEVERE, null, e);
            try {
                con.close();
            } catch (SQLException e1) {
            }
        }}
       if(exist==1){
           Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
           ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "This username is taken."));

       }
       else{
           Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
           ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success!","Please go to page 2"));

       }
    }

    public void registerMemberSendCreditCard(int id, String creditCardOwner, String creditCardNumber, String cvc, String validityDate) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO users_credit_card(id,creditCardOwner, creditCardNumber, cvc, validityDate) VALUES (?,?,?,?,?)";
            st = con.prepareStatement(sql);
            st.setInt(1, id);
            st.setString(2, creditCardOwner);
            st.setString(3, creditCardNumber);
            st.setString(4, cvc);
            st.setString(5, validityDate);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc4")));

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

    public void addNewTrainer(int id, String username1, String password1, String name, String surname, String telNo, String mail, String birthday,String question, String job) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO users(id, username, password, name, surname, telNo, mail, birthday ,answer, job) VALUES (?,?,?,?,?,?,?,?,?,?)";
            st = con.prepareStatement(sql);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc6")));

            st.setInt(1, id);
            st.setString(2, username1);
            st.setString(3, password1);
            st.setString(4, name);
            st.setString(5, surname);
            st.setString(6, telNo);
            st.setString(7, mail);
            st.setString(8, birthday);
            st.setString(9, question);
             st.setString(10, job);
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

    public void deleteTrainer(String delUsername) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";
        Connection con2;
        PreparedStatement ps;
        ResultSet rs2;
        int exists = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "987654321");
            ps = con2.prepareStatement("Select * from  users  where username = '" + delUsername + "'and job = '" + "trainer" + "'");
            rs2 = ps.executeQuery();

            while (rs2.next()) {
                exists=1;
            }
        } catch (Exception e) {

        }
        
        if(exists==1){
        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);

            String sql = "delete from  users  where username = '" + delUsername + "'and job = '" + "trainer" + "'";
            st = con.prepareStatement(sql);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc7")));

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
        else{
         Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Invalid Username!"));   
        }
    }

    public int cancellationOfMembership(String cancelUsername, String cancelPassword) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "delete from  users  where username = '" + cancelUsername + "'and password = '" + cancelPassword + "'";
            st = con.prepareStatement(sql);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc8")));

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
        return 1;

    }

    public void forgetMyPassword(String Username, String newPassword, String question) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;

        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);

            String sql = "update users set password = '"+newPassword+"' where username = '" + Username + "' and answer = '" + question +  "'";
            st = con.prepareStatement(sql);

           
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc9")));

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
    
    public void updateInfo(String updateUsername, String updatePassword, String updateName, String updateSurname, String updateTelNo, String updateMail, String updateBirthday,String updateQuestion, int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;

        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);

            String sql = "update users set username = ?, password = ?, name =?, surname =?, telNo =? ,mail =?,birthday =?, answer =?,job =? , id = ? where id = '" + id + "'";
            st = con.prepareStatement(sql);
            st.setString(1, updateUsername);
            st.setString(2, updatePassword);
            st.setString(3, updateName);
            st.setString(4, updateSurname);
            st.setString(5, updateTelNo);
            st.setString(6, updateMail);
            st.setString(7, updateBirthday);
            st.setString(8, updateQuestion);
            st.setString(9, "member");
            st.setInt(10, id);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc9")));

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

    public void updateTrainerInfo(String updateUsername, String updatePassword, String updateName, String updateSurname, String updateTelNo, String updateMail, String updateBirthday,String updateQuestion, int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "update users set username = ?, password = ?, name =?, surname =?, telNo =? ,mail =?,birthday =?,answer =?,job = ?, id = ? where id = '" + id + "'";
            st = con.prepareStatement(sql);
            st.setString(1, updateUsername);
            st.setString(2, updatePassword);
            st.setString(3, updateName);
            st.setString(4, updateSurname);
            st.setString(5, updateTelNo);
            st.setString(6, updateMail);
            st.setString(7, updateBirthday);
            st.setString(8, updateQuestion);
            st.setString(9, "trainer");
            st.setInt(10, id);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc9")));

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
    
    public static ArrayList<personModel> getCheckCourseMembers() throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "Select * from users where job='member'";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            ArrayList<personModel> al = new ArrayList<>();
            while (rs.next()) {
                personModel pm = new personModel();
                pm.setUsername(rs.getString(1));
                pm.setPassword(rs.getString(2));
                pm.setName(rs.getString(3));
                pm.setSurname(rs.getString(4));
                pm.setTelNo(rs.getString(5));
                pm.setMail(rs.getString(6));
                pm.setBirthday(rs.getString(7));
                pm.setJob(rs.getString(8));
                pm.setQuestion(rs.getString(9));
                pm.setId(rs.getInt(10));
                al.add(pm);
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
    public static ArrayList<personModel> getCheckTrainerInfo(String username1) throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "Select * from users where username = '" + username1 +"'";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            ArrayList<personModel> al = new ArrayList<>();
            while (rs.next()) {
                personModel pm = new personModel();
                pm.setUsername(rs.getString(1));
                pm.setPassword(rs.getString(2));
                pm.setName(rs.getString(3));
                pm.setSurname(rs.getString(4));
                pm.setTelNo(rs.getString(5));
                pm.setMail(rs.getString(6));
                pm.setBirthday(rs.getString(7));
                pm.setJob(rs.getString(8));
                pm.setQuestion(rs.getString(9));
                pm.setId(rs.getInt(10));
                al.add(pm);
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
    public static ArrayList<courseModel> getCheckMyCourses() throws ClassNotFoundException, SQLException {

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
    
    
    public void addNewManager(int id, String username1, String password1, String name, String surname, String telNo, String mail, String birthday,String question, String job) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO users(id, username, password, name, surname, telNo, mail, birthday ,answer, job) VALUES (?,?,?,?,?,?,?,?,?,?)";
            st = con.prepareStatement(sql);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc12")));

            st.setInt(1, id);
            st.setString(2, username1);
            st.setString(3, password1);
            st.setString(4, name);
            st.setString(5, surname);
            st.setString(6, telNo);
            st.setString(7, mail);
            st.setString(8, birthday);
            st.setString(9, question);
            st.setString(10, job);
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

    public void deleteManager(String delUsername) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "delete from  users  where username = '" + delUsername + "'and job = '" + "manager" + "'";
            st = con.prepareStatement(sql);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc7")));

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
    
    
    public void updateManagerInfo(String updateUsername, String updatePassword, String updateName, String updateSurname, String updateTelNo, String updateMail, String updateBirthday,String updateQuestion, int id) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "update users set username = ?, password = ?, name =?, surname =?, telNo =? ,mail =?,birthday =?,answer =?,job = ?, id = ? where id = '" + id + "'";
            st = con.prepareStatement(sql);
            st.setString(1, updateUsername);
            st.setString(2, updatePassword);
            st.setString(3, updateName);
            st.setString(4, updateSurname);
            st.setString(5, updateTelNo);
            st.setString(6, updateMail);
            st.setString(7, updateBirthday);
            st.setString(8, updateQuestion);
            st.setString(9, "manager");
            st.setInt(10, id);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc9")));

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
    

    
    
        public String heartAttack(Integer cig, Integer cho){
        
          
       double value=((cig * 0.05) + (cho  * 0.004))*24;
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Calculated Successfully!"));
       String s = String.format("%,.2f", value);
       return s+"%";
      }
     
          
      public String bodyMassIndex(Double height1,Double weight1){
        height1=height1/100;
       double value=((weight1) / (height1*height1));
       FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Calculated Successfully!"));
       String s = String.format("%,.2f", value);
       return s;
      }
     
     
          
      public String basalMetabolicRate(Double height2,Double weight2,Integer age){
          height2=height2/100;
          double value = 66 + (13.75 * weight2) + (5 * height2) - (6.8 * age);
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success","Calculated Successfully!"));
          String s = String.format("%,.2f", value);
          return s;
      }
    

    public void error() {
                    Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, r.getString("javapc2"), r.getString("javapc1")));
    }

    public void info() {
                    Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javapc3"), r.getString("javapc10")));
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }
    public static ArrayList<courseModel> getCheckMySchedule(String instructorName) throws ClassNotFoundException, SQLException {

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
                if(rs.getString(5).equals(instructorName)){
                    al.add(cm);
                }
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
    public static ArrayList<personModel> getCheckTrainerInfo() throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "Select * from users where job='trainer'";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            ArrayList<personModel> al = new ArrayList<>();
            while (rs.next()) {
                personModel pm = new personModel();
                pm.setUsername(rs.getString(1));
                pm.setPassword(rs.getString(2));
                pm.setName(rs.getString(3));
                pm.setSurname(rs.getString(4));
                pm.setTelNo(rs.getString(5));
                pm.setMail(rs.getString(6));
                pm.setBirthday(rs.getString(7));
                pm.setJob(rs.getString(8));
                pm.setQuestion(rs.getString(9));
                pm.setId(rs.getInt(10));
                al.add(pm);
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
    public static ArrayList<personModel> getCheckManagerInfo() throws ClassNotFoundException, SQLException {

        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            String sql = "Select * from users where job='manager'";
            st = con.prepareStatement(sql);
            rs = st.executeQuery();
            ArrayList<personModel> al = new ArrayList<>();
            while (rs.next()) {
                personModel pm = new personModel();
                pm.setUsername(rs.getString(1));
                pm.setPassword(rs.getString(2));
                pm.setName(rs.getString(3));
                pm.setSurname(rs.getString(4));
                pm.setTelNo(rs.getString(5));
                pm.setMail(rs.getString(6));
                pm.setBirthday(rs.getString(7));
                pm.setJob(rs.getString(8));
                pm.setQuestion(rs.getString(9));
                pm.setId(rs.getInt(10));
                al.add(pm);
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
