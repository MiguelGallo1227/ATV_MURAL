package com.mural.avisos.controller;

import com.mural.avisos.entity.Recado;
import com.mural.avisos.repository.MuralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/avisos")
public class RecadoController {
    @Autowired
    MuralRepository recRepository;

    @GetMapping
    public ResponseEntity<List<Recado>> getRecados() {
        List<Recado> listaRecados = recRepository.findAll();
        return ResponseEntity.ok(listaRecados);
    }

    @PostMapping
    public ResponseEntity<Recado> postRecado(@Validated @RequestBody Recado recado) {
        try {
            Recado salvo = recRepository.save(recado);
            return new ResponseEntity<>(salvo, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao salvar recado", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recado> updateRecado(@PathVariable Long id, @Validated @RequestBody Recado recadoAtualizado) {
        try {
            Recado recadoExistente = recRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Recado não encontrado"));

            recadoExistente.setTitulo(recadoAtualizado.getTitulo());
            recadoExistente.setDescricao(recadoAtualizado.getDescricao());
            recadoExistente.setAutor(recadoAtualizado.getAutor());
            recadoExistente.setDataPublicacao(LocalDate.now());

            Recado salvo = recRepository.save(recadoExistente);
            return ResponseEntity.ok(salvo);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao atualizar recado", e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecado(@PathVariable Long id) {
        if (!recRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recado não encontrado");
        }
        recRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}