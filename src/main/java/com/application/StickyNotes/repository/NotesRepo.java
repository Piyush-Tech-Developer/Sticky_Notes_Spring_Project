package com.application.StickyNotes.repository;

import com.application.StickyNotes.model.NotesData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepo extends JpaRepository<NotesData,Integer> {
}
