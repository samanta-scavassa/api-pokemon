package com.fatec.pokemon;

import org.springframework.data.repository.CrudRepository;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
    Pokemon findByName(String name);
}
