package com.application.StickyNotes.model;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.IdGeneratorType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class NotesData {

    // here will add data parameters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int note_id;
    private String note_title;
    private String note_content;
    @CreatedDate
    private LocalDateTime created_on;
    @LastModifiedDate
    private LocalDateTime modified_on;

    public NotesData(){}

    public NotesData(int note_id, String note_content, String note_title){
        this.note_id = note_id;
        this.note_content = note_content;
        this.note_title = note_title;
//        this.created_on = created_on;
//        this.modified_on = modified_on;
    }

//    public void setNote_id(int note_id) {
//        this.note_id = note_id;
//    }
//
//    public void setNote_content(String note_content) {
//        this.note_content = note_content;
//    }
//
//    public void setCreated_on(LocalDate created_on) {
//        this.created_on = LocalDate.now();
//    }
//
//    public void setModified_on(LocalDate modified_on) {
//        this.modified_on = LocalDate.now();
//    }
//
//    public int getNote_id() {
//        return note_id;
//    }
//
//    public String getNote_content() {
//        return note_content;
//    }
//
//    public LocalDate getCreated_on() {
//        return created_on;
//    }
//
//    public LocalDate getModified_on() {
//        return modified_on;
//    }

    @Override
    public String toString() {
        return "NotesData{" +
                "note_id=" + note_id +
                ", note_content='" + note_content + '\'' +
                ", created_on=" + created_on +
                ", modified_on=" + modified_on +
                '}';
    }
}
