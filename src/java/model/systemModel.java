package model;

import controller.systemController;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "systemModel")
@SessionScoped

public class systemModel {

    private String announcement;

    public systemModel() {
    }

    public systemModel(String announcement) {
        this.announcement = announcement;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public void announcementSend() throws SQLException, ClassNotFoundException {

        systemController s = new systemController();
        if (announcement.equals("")) {
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, r.getString("javasm1"), r.getString("javasm2")));
        } else {
            Locale lang = FacesContext.getCurrentInstance().getViewRoot().getLocale();
            ResourceBundle r = ResourceBundle.getBundle("resources/a",lang);
            s.announcementSend(announcement);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, r.getString("javasm3"), r.getString("javasm4")));
            setAnnouncement("");
        }

    }

}
