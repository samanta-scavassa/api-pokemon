package com.fatec.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;

@Service
public class PokemonService {
    
    @Autowired
    private PokemonRepository pokemonRepository;

    public Iterable<Pokemon> getPokemons() {
        return pokemonRepository.findAllByOrderByPokedexNumberAsc();
    }

    public Optional<Pokemon> getPokemonById(int pokedexNumber) {
        return pokemonRepository.findById(pokedexNumber);
    }

    public Pokemon savePokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public Pokemon updatePokemon(Pokemon pokemon, int pokedexNumber) {

        Assert.notNull(pokedexNumber, "Update failed");

        Optional<Pokemon> optional = getPokemonById(pokedexNumber);

        if(optional.isPresent()) {
            Pokemon db = optional.get();
            db.setName((pokemon.getName()));
            db.setPokedexNumber(pokemon.getPokedexNumber());
            db.setType1(pokemon.getType1());
            db.setType2(pokemon.getType2());
            db.setGeneration(pokemon.getGeneration());

            System.out.println("Pokedex number: " + db.getPokedexNumber());

            pokemonRepository.save(db);

            return db;
        } else {
            return null;
        }
    }

    public boolean deletePokemon(int pokedexNumber) {
        if(getPokemonById(pokedexNumber).isPresent()) {
            pokemonRepository.deleteById(pokedexNumber);
            return true;
        }
        return false;
    }


}
