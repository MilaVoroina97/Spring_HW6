package ru.voronina.gb.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.voronina.gb.model.Note;
import ru.voronina.gb.repositories.NoteRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class NoteService implements NoteServiceInterface{

    private final NoteRepository noteRepository;

    /**
     * Метод получения списка всех заметок
     * @return список всех заметок
     */
    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    /**
     * Метод сохранения заметки
     * @param note новая заметка
     * @return сохраненная заметка
     */
    @Override
    public Note createNote(Note note) {
        note.setDateCreate(LocalDateTime.now());
        return noteRepository.save(note);
    }

    /**
     * Метод получения заметки по id
     * @param id заметки
     * @return заметка
     */
    @Override
    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElseThrow(null);
    }

    /**
     * Метод изменения заметки по id
     * @param id заметки
     * @param changeHeader заголовок заметки
     * @param changeContent содержимое заметки
     * @return обновленная заметка
     */
    @Override
    public Note updateNote(Long id, String changeHeader, String changeContent) {
        Note noteById = getNoteById(id);
        noteById.setHeader(changeHeader);
        noteById.setContent(changeContent);
        return noteRepository.save(noteById);
    }


    /**
     * Метод удаления заметки по id
     * @param id заметки
     */
    @Override
    public void deleteNote(Long id) {

        Note noteById = getNoteById(id);
        noteRepository.delete(noteById);
    }
}
