package com.uni.university.service;

import com.uni.university.domain.Professor;
import com.uni.university.repository.ProfessorRepository;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ProfessorService {

    private final ProfessorRepository repository;


    public List<Professor> findAll() {
        return repository.findAll();
    }

    public Professor findById(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    public Professor save(Professor professor) {
        return repository.save(professor);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void findBy() {
        repository.findBy();
    }
}
