package com.garanti.services;

import com.garanti.entities.Note;
import com.garanti.entities.Product;
import com.garanti.props.Rest;
import com.garanti.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NoteService {

    final NoteRepository nRepo;

    // save
    public ResponseEntity<Rest> save(Note note) {
        nRepo.save(note);
        Rest rest = new Rest();
        if ( note.getNid() != null ) {
            rest.setStatus(true);
            rest.setResult(note);
            return new ResponseEntity<>(rest, HttpStatus.OK);
        }else {
            rest.setStatus(false);
            rest.setResult(note);
            return new ResponseEntity<>(rest, HttpStatus.BAD_REQUEST);
        }
    }

    // list
    public ResponseEntity<Rest> list() {
        Rest rest = new Rest();
        rest.setStatus(true);
        rest.setResult(nRepo.findAll());
        return new ResponseEntity<>(rest, HttpStatus.OK);
    }
}
