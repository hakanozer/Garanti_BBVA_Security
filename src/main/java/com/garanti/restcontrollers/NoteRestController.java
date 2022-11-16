package com.garanti.restcontrollers;

import com.garanti.entities.Note;
import com.garanti.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteRestController {

    final NoteService nService;

    @PostMapping("/save")
    public ResponseEntity save( @Valid @RequestBody Note note ) {
        return nService.save(note);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        return nService.list();
    }

}
