package com.example.englishb2.repository;

import com.example.englishb2.model.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocabularyRepository
        extends JpaRepository<Vocabulary, Long> {

    List<Vocabulary> findByNivel(
            String nivel
    );

    List<Vocabulary> findByBloqueExamen(
            String bloqueExamen
    );

    List<Vocabulary> findByCategoria(
            String categoria
    );

    List<Vocabulary> findByNivelAndBloqueExamen(
            String nivel,
            String bloqueExamen
    );
}