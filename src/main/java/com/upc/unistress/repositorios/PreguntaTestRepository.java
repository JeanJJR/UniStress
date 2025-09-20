package com.upc.unistress.repositorios;

import com.upc.unistress.entidades.PreguntaTest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreguntaTestRepository extends JpaRepository<PreguntaTest, Long> {
    List<PreguntaTest> findByTest_Id(Long testId);
}
