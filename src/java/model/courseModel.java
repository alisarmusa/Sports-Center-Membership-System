/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.courseController;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author eorcu
 */

@ManagedBean(name = "courseModel")
@SessionScoped


public class courseModel {
 
    private String courseName;
    private String courseDay;
    private String startTime;
    private String finishTime;
    private String courseInstructor;
    private String courseClass;
    private int courseId;
    private String takeCourseName;
    
    public courseModel() {
    }
    
    
    public void openCourseModel() throws SQLException, ClassNotFoundException {

        courseController c = new courseController();
        c.openCourse(courseName,courseDay,startTime,finishTime,courseInstructor,courseClass,courseId);

    }

    public void deleteCourseModel() throws SQLException, ClassNotFoundException {

        courseController c = new courseController();
        c.deleteCourse(courseName);

    }
        public void dropCourseModel() throws SQLException, ClassNotFoundException {

        courseController c = new courseController();
        c.dropCourse(takeCourseName);

    }
    
    public void updateCourseInfoModel() throws SQLException, ClassNotFoundException {

        courseController c = new courseController();
        c.updateCourse(courseName,courseDay,startTime,finishTime,courseInstructor,courseClass,courseId);
    }
     public void takeCourseModel() throws SQLException, ClassNotFoundException {

        courseController c = new courseController();
        c.takeCourse(takeCourseName);
    }
    public ArrayList<courseModel> getMessages3() throws ClassNotFoundException, SQLException {
        return courseController.getCheckCourses();
    }
    public ArrayList<courseModel> getMessages5() throws ClassNotFoundException, SQLException {
        return courseController.getTakenCourses();
    }
    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the courseDay
     */
    public String getCourseDay() {
        return courseDay;
    }

    /**
     * @param courseDay the courseDay to set
     */
    public void setCourseDay(String courseDay) {
        this.courseDay = courseDay;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the finishTime
     */
    public String getFinishTime() {
        return finishTime;
    }

    /**
     * @param finishTime the finishTime to set
     */
    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * @return the courseInstructor
     */
    public String getCourseInstructor() {
        return courseInstructor;
    }

    /**
     * @param courseInstructor the courseInstructor to set
     */
    public void setCourseInstructor(String courseInstructor) {
        this.courseInstructor = courseInstructor;
    }

    /**
     * @return the courseClass
     */
    public String getCourseClass() {
        return courseClass;
    }

    /**
     * @param courseClass the courseClass to set
     */
    public void setCourseClass(String courseClass) {
        this.courseClass = courseClass;
    }

    /**
     * @return the courseId
     */
    public int getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    /**
     * @return the takeCourseName
     */
    public String getTakeCourseName() {
        return takeCourseName;
    }

    /**
     * @param takeCourseName the takeCourseName to set
     */
    public void setTakeCourseName(String takeCourseName) {
        this.takeCourseName = takeCourseName;
    }

}
