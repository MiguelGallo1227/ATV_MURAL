package com.mural.avisos.repository;

import com.mural.avisos.entity.Recado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuralRepository extends JpaRepository<Recado, Long> {
}
