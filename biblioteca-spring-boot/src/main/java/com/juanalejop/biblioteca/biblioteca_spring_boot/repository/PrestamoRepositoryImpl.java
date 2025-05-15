package com.juanalejop.biblioteca.biblioteca_spring_boot.repository;

import com.juanalejop.biblioteca.biblioteca_spring_boot.model.Prestamo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PrestamoRepositoryImpl implements PrestamoRepository{
    private final List<Prestamo> prestamos = new ArrayList<>();
    private final AtomicLong nextId = new AtomicLong(1);

    @Override
    public List<Prestamo> findAll() {
        return new ArrayList<>(prestamos);
    }

    @Override
    public Optional<Prestamo> findById(Long id) {
        return prestamos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public Prestamo save(Prestamo prestamo) {
        if (prestamo.getId() == null) {
            prestamo.setId(nextId.getAndIncrement());
        } else {
            deleteById((prestamo.getId()));
        }
        prestamos.add(prestamo);
        return prestamo;
    }

    @Override
    public void deleteById(Long id) {
        prestamos.removeIf(p -> p.getId().equals(id));
    }

    @Override
    public boolean existsById(Long id) {
        return prestamos.stream()
                .anyMatch(p -> p.getId().equals(id));
    }
}