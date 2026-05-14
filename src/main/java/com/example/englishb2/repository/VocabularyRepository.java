package com.example.englishb2.repository;

import com.example.englishb2.model.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {

    List<Vocabulary> findByNivel(String nivel);

    List<Vocabulary> findByBloqueExamen(String bloqueExamen);

    List<Vocabulary> findByCategoria(String categoria);

    List<Vocabulary> findByDificultad(String dificultad);

    List<Vocabulary> findByNivelAndBloqueExamen(String nivel, String bloqueExamen);

    List<Vocabulary> findByNivelAndCategoria(String nivel, String categoria);

    List<Vocabulary> findByNivelAndDificultad(String nivel, String dificultad);

    List<Vocabulary> findByNivelAndBloqueExamenAndCategoria(
            String nivel,
            String bloqueExamen,
            String categoria
    );
}