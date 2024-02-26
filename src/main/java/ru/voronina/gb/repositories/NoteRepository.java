package ru.voronina.gb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.voronina.gb.model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {
}
