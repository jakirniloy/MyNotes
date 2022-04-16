package me.jakirniloy.mynotes;

public class Event {
    String key = "";
    String Title = "";
    String Course_code = "";
    String Course_topic = "";
    String Date_of_lecture ="";
    String description = "";

    public Event(String key, String title, String course_code, String course_topic, String date_of_lecture, String description) {
        this.key = key;
        Title = title;
        Course_code = course_code;
        Course_topic = course_topic;
        Date_of_lecture = date_of_lecture;
        this.description = description;
    }
}
