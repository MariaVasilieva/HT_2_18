package com.example.HTGOIT_2_18.model;

import java.util.List;

public interface NoteService {
    List<Note> listAll();
    Note add(Note note);
    void deleteById(long id);
    Note update(Note note);
    Note getById(long id);
}
