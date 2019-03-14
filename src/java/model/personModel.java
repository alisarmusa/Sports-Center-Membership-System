package model;

import controller.personController;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@ManagedBean(name = "personModel")
@SessionScoped
public class personModel {
    
    private String question;
    private int id;
    private String username;
    private String username1;
    private String password;
    private String name;
    private String surname;
    private String telNo;
    private String mail;
    private String birthday;
    private String job;
    private String txtcreditCardOwner;
    private String txtcreditCardNumber;
    private String cvc;
    private String validityDate;
    private String heartResult;
    private String bodyResult;
    private String basalResult;
    private Integer cigarettes;
    private Integer cholesterol;
    private Double weight;
    private Double height;
    private Integer age;
    private Integer debt;

    public personModel() {
    }

    public personModel(int id, String username, String password, String name, String surname, String telNo, String mail, String birthday) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.telNo = telNo;
        this.mail = mail;
        this.birthday = birthday;
    }

    public String checkValidUser() throws SQLException, ClassNotFoundException {

        personController p = new personController();
        int sonuc = p.login(getUsername(), getPassword(), getJob());

        if (sonuc == 2) {
            return "member";
        }

        if (sonuc == 3) {

            return "trainer";
        }
        if (sonuc == 4) {

            return "manager";
        }
        if (sonuc == 5) {

            return "systemAdmin";
        }
        
        return null;

    }

    public void memberSend() throws SQLException, ClassNotFoundException {

        personController p = new personController();
        if (id == 0 || username == null || password == null || name == null || surname == null || telNo == null || mail == null || birthday == null || question ==null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please enter all information."));
        } else {

            p.registerMemberSend(id,username,password,name,surname,telNo,mail,birthday,question,"member");
        }

    }

    public void cancellation() throws SQLException, ClassNotFoundException {

        personController p = new personController();
        p.cancellationOfMembership(username, password);

    }

   public void addNewTrainerModel() throws SQLException, ClassNotFoundException {

        personController p = new personController();
        if (id==0 || username == null || password == null || name == null || surname == null || telNo == null || mail == null || birthday == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please enter all information."));
        } else {
            p.addNewTrainer(id, username, password, name, surname, telNo, mail, birthday,question, "trainer");
        }

    }

    public void deleteTrainerModel() throws SQLException, ClassNotFoundException {

        personController p = new personController();
        p.deleteTrainer(username);

    }
    
     public void forgetPassword() throws SQLException, ClassNotFoundException {

        personController p = new personController();
        if (username == null || password == null || question == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please enter all information."));
        } else {
             p.forgetMyPassword(username,password,question);
        }
    }

    public void updateInfoModel() throws SQLException, ClassNotFoundException {

        personController p2 = new personController();
        p2.updateInfo(username, password, name, surname, telNo, mail, birthday,question, id);
    }

    public void updateTrainerModel() throws SQLException, ClassNotFoundException {

        personController p2 = new personController();
        p2.updateTrainerInfo(username, password, name, surname, telNo, mail, birthday,question, id);
    }
    

     public void memberSendCreditCard() throws SQLException, ClassNotFoundException {

        personController p2 = new personController();
        if (txtcreditCardOwner == null || txtcreditCardNumber == null || cvc == null || validityDate == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Please enter all information."));
        } else {
             p2.registerMemberSendCreditCard(id, txtcreditCardOwner, txtcreditCardNumber, cvc, validityDate);
        }
       
    }
    
     public void addNewManagerModel() throws SQLException, ClassNotFoundException {

        personController p = new personController();
        p.addNewManager(id, username, password, name, surname, telNo, mail, birthday,question, "manager");

    }

    public void deleteManagerModel() throws SQLException, ClassNotFoundException {

        personController p = new personController();
        p.deleteManager(username);

    }
    public void makePayment(){
        if(debt!=0){
        Connection con = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String url = "jdbc:mysql://localhost:3306/db";
        String username = "root";
        String password = "987654321";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            LocalDateTime ldt = LocalDateTime.now().plusDays(1);
            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            String formatter = format.format(ldt);

            String sql = "INSERT INTO payments(payer,paymentDate, paymentAmount) VALUES (?,?,?)";
            st = con.prepareStatement(sql);
            st.setString(1, getUsername());
            st.setString(2, formatter);
            st.setInt(3, 200);
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "You made monthly payment"));

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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "You don't have any debts"));
        }
    }
    
    public void updateManagerModel() throws SQLException, ClassNotFoundException {

        personController p2 = new personController();
        p2.updateManagerInfo(username, password, name, surname, telNo, mail, birthday,question, id);
    }

        public void heartAttack() throws SQLException, ClassNotFoundException {

        personController p2 = new personController();
        if(cigarettes==null || cholesterol==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Missing Informations"));
        }
        else
        heartResult=p2.heartAttack(cigarettes,cholesterol);
    }
        public void bodyMassIndex() throws SQLException, ClassNotFoundException {

        personController p2 = new personController();
        if(height==null || weight==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Missing Informations"));
        }
        else
        bodyResult=p2.bodyMassIndex(height,weight);
    }
        public void basalMetabolicRate() throws SQLException, ClassNotFoundException {

        personController p2 = new personController();
        if(height==null || weight==null || age==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Missing Informations"));
        }
        else
        basalResult=p2.basalMetabolicRate(height,weight,age);
    }
        public void paymentInfo(){
        Connection con;
        PreparedStatement ps;
        ResultSet rs;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "987654321");
            ps = con.prepareStatement("Select * from payments");
            rs = ps.executeQuery();
            ArrayList<String> list = new ArrayList<String>();
            PrintWriter writer = new PrintWriter("Payment.txt", "UTF-8");
            while (rs.next()) {
                
            String s = rs.getString("payer") + " "+ rs.getString("paymentDate") + " "+ rs.getString("paymentAmount")+" TL"+"\n";

            writer.print(s);
            }
            writer.close();
  
        } catch (Exception e) {

        }
        File file = new File("Payment.txt");
    HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();  
    response.setHeader("Content-Disposition", "attachment;filename=payment.txt");  
    response.setContentLength((int) file.length());  
    ServletOutputStream out = null;  
    try {  
        FileInputStream input = new FileInputStream(file);  
        byte[] buffer = new byte[1024];  
        out = response.getOutputStream();  
        int i = 0;  
        while ((i = input.read(buffer)) != -1) {  
            out.write(buffer);  
            out.flush();  
        }  
        FacesContext.getCurrentInstance().getResponseComplete();  
    } catch (IOException err) {  
        err.printStackTrace();  
    } finally {  
        try {  
            if (out != null) {  
                out.close();  
            }  
        } catch (IOException err) {  
            err.printStackTrace();  
        }  
    }  
      }
       public void showDebt(){
        Connection con;
        PreparedStatement ps;
        PreparedStatement ps2;
        ResultSet rs;
        ResultSet rs2;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "987654321");
            ps = con.prepareStatement("Select * from payments");
            ps2 = con.prepareStatement("Select * from payments");
            rs = ps.executeQuery();
            rs2 = ps2.executeQuery();
            int paid = 0;
            while (rs.next()) {
                String[] date = null;
                if(rs.getString("payer").equals(username)){
                    while (rs2.next()) {
                    date = rs.getString("paymentDate").split("-");
                    if(Integer.parseInt(date[1])==Calendar.getInstance().get(Calendar.MONTH)+1){
                        debt = 0;
                        paid = 1;
                    }
                    else{
                       debt = 200;
                    }
                    }
                }
                else{
                    debt = 200;
                }
 
            }
            if(paid == 0)
                debt = 200;
  
        } catch (Exception e) {

        }

      }
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the telNo
     */
    public String getTelNo() {
        return telNo;
    }

    /**
     * @param telNo the telNo to set
     */
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the txtcreditCardOwner
     */
    public String getTxtcreditCardOwner() {
        return txtcreditCardOwner;
    }

    /**
     * @param txtcreditCardOwner the txtcreditCardOwner to set
     */
    public void setTxtcreditCardOwner(String txtcreditCardOwner) {
        this.txtcreditCardOwner = txtcreditCardOwner;
    }

    /**
     * @return the txtcreditCardNumber
     */
    public String getTxtcreditCardNumber() {
        return txtcreditCardNumber;
    }

    /**
     * @param txtcreditCardNumber the txtcreditCardNumber to set
     */
    public void setTxtcreditCardNumber(String txtcreditCardNumber) {
        this.txtcreditCardNumber = txtcreditCardNumber;
    }

    /**
     * @return the cvc
     */
    public String getCvc() {
        return cvc;
    }

    /**
     * @param cvc the cvc to set
     */
    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    /**
     * @return the validityDate
     */
    public String getValidityDate() {
        return validityDate;
    }

    /**
     * @param validityDate the validityDate to set
     */
    public void setValidityDate(String validityDate) {
        this.validityDate = validityDate;
    }

    /**
     * @return the job
     */
    public String getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * @return the cigarettes
     */
    public Integer getCigarettes() {
        return cigarettes;
    }

    /**
     * @param cigarettes the cigarettes to set
     */
    public void setCigarettes(Integer cigarettes) {
        this.cigarettes = cigarettes;
    }

    /**
     * @return the cholesterol
     */
    public Integer getCholesterol() {
        return cholesterol;
    }

    /**
     * @param cholesterol the cholesterol to set
     */
    public void setCholesterol(Integer cholesterol) {
        this.cholesterol = cholesterol;
    }

    /**
     * @return the weight
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * @return the height
     */
    public Double getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(Integer age) {
        this.age = age;
    }
    
        /**
     * @return the heartResult
     */
    
    public String getHeartResult() {
        return heartResult;
    }

    /**
     * @param hs the heartResult to set
     */
    public void setHeartResult(String hs) {
        this.heartResult = hs;
    }
     /**
     * @return the bodyResult
     */
    
    public String getBodyResult() {
        return bodyResult;
    }

    /**
     * @param br the bodyResult to set
     */
    public void setBodyResult(String br) {
        this.bodyResult = br;
    }
     /**
     * @return the basalResult
     */
    public String getBasalResult() {
        return basalResult;
    }

    /**
     * @param br the basalResult to set
     */
    public void setBasalResult(String br) {
        this.basalResult = br;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }
    /**
     * @return the debt
     */
    public Integer getDebt() {
        return debt;
    }

    /**
     * @param debt the debt to set
     */
    public void setDebt(Integer debt) {
        this.debt = debt;
    }

    /**
     * @return the username1
     */
    public String getUsername1() {
        return username1;
    }

    /**
     * @param username1 the username1 to set
     */
    public void setUsername1(String username1) {
        this.username1 = username1;
    }
    /**
     * @return the pc
     */

    public ArrayList<personModel> getMessages1() throws ClassNotFoundException, SQLException {
        return personController.getCheckCourseMembers();
    }

    public ArrayList<personModel> getMessages2() throws ClassNotFoundException, SQLException {
        return personController.getCheckTrainerInfo(getUsername1());
    }
    public ArrayList<courseModel> getMessages4() throws ClassNotFoundException, SQLException {
        return personController.getCheckMyCourses();
    }
    public ArrayList<courseModel> getMessages5() throws ClassNotFoundException, SQLException {
        String instructorUserName=getUsername();
        return personController.getCheckMySchedule(instructorUserName);
	}
      public ArrayList<personModel> getMessages6() throws ClassNotFoundException, SQLException {
        return personController.getCheckTrainerInfo();
    }
       public ArrayList<personModel> getMessages7() throws ClassNotFoundException, SQLException {
        return personController.getCheckManagerInfo();
    }
}
