package com.fatec.pokemon;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
    public List<Pokemon> findAllByOrderByPokedexNumberAsc();
}
