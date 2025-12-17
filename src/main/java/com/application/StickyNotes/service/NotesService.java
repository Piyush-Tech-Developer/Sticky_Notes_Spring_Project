package com.application.StickyNotes.service;

import com.application.StickyNotes.model.NotesData;
import com.application.StickyNotes.repository.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class NotesService {

    @Autowired
    NotesRepo repo;

    //here we will define some dummy data and return it
//    List<NotesData> data = new ArrayList<>(Arrays.asList(new NotesData(101,"Hello this is my first note",1,1),
//            new NotesData(102,"My second note",2,2)));

    public List<NotesData> getData(){
//        return data;
        return repo.findAll();
    }

    public NotesData getDataById(int ID){
//        return data.stream()
//                .filter(d -> d.getNote_id() == ID)
//                .findFirst().orElse(new NotesData(100,"Null Note",-1,-1));
        return repo.findById(ID).orElse(new NotesData(-1,"Null", "Null"));
    }

    public String addNote(NotesData Note){
//        data.add(Note);
        repo.save(Note);
        return "Data Successfully Added:"+Note;
    }

    public String updateNote(NotesData Note){
//        int index = 0;
//        for (int i =0;i<data.size();i++)
//            if(data.get(i).getNote_id()==Note.getNote_id())
//                index=i;
//
//        data.set(index,Note);
        repo.save(Note);
        return "Successfully Made The Changes.";
    }

    public String deleteNote(int ID){
//        int index = 0;
//        for (int i =0;i<data.size();i++)
//            if(data.get(i).getNote_id()==ID)
//                index=i;
//        data.remove(index);
        repo.deleteById(ID);
        return "Done";
    }

}
