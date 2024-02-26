package ru.voronina.gb.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import ru.voronina.gb.model.Note;
import ru.voronina.gb.services.NoteService;

import java.util.List;

@Repository
@RequestMapping("/notes")
@AllArgsConstructor
public class NoteController {

    private final NoteService noteService;

    /**
     * Метод обработки GET запроса без параметров
     * @return список всех заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAlNotes() {
        List<Note> notes = noteService.getAllNotes();
        if (notes.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(notes);
    }

    /**
     * POST метод обработки запроса добавления заметки через параметр
     * @param note новая заметка
     * @return новая заметка
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note newNote = noteService.createNote(note);

        if (newNote == null) return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(newNote);
    }

    /**
     * GET метод обработки запроса списка заметок с указанным статусом
     * @param id id заметки
     * @return найденная заметка
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Note note;
        try {
            note = noteService.getNoteById(id);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
        return new ResponseEntity<>(note, HttpStatus.OK);
    }

    /**
     * PUT метод обработки запроса изменения заметки по id:
     * //localhost:8080/notes/{id}?heading={heading}&content={content}
     *
     * @param id      id заметки
     * @param header заголовок заметки, передается при помощи параметра
     * @param content содержимое заметки, передается при помощи параметра
     * @return измененная заметка
     */
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Note> updateProduct(@PathVariable Long id,
                                              @RequestParam String header,
                                              @RequestParam String content) {
        Note updateNote = noteService.updateNote(id, header, content);
        if (updateNote == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok().build();
    }

    /**
     * DELETE метод обработки запроса удаления заметки по id
     * @param id заметки
     * @return ResponseEntity<Void>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.ok().build();
    }

}
