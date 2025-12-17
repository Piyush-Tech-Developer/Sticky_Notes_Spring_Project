package com.application.StickyNotes.controller;

import com.application.StickyNotes.model.NotesData;
import com.application.StickyNotes.service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotesController {

    //here we will return data using request mapping

    @Autowired
    NotesService service;

    @GetMapping("/notes_info")
    public List<NotesData> info(){
        return service.getData();
    }

    @GetMapping("notes_info/{id}")
    public NotesData noteById(@PathVariable int id){
        return service.getDataById(id);
    }

    @PostMapping("/notes_info")
    public String addNote(@RequestBody NotesData Note){
        System.out.println(Note);
        return service.addNote(Note);
    }

    @PutMapping("/notes_info")
    public String updateNote(@RequestBody NotesData Note){
        return service.updateNote(Note);
    }

    @DeleteMapping("/notes_info/{id}")
    public String deleteNote(@PathVariable int id){
        return service.deleteNote(id);
    }

}
