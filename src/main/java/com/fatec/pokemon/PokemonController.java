package com.fatec.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/pokemon")
public class PokemonController {

    @Autowired
    private PokemonService service;

    @GetMapping()
    public ResponseEntity<Iterable<Pokemon>> getPokemons() {
        return ResponseEntity.ok(service.getPokemons());
    }

    @GetMapping("/{pokedexNumber}")
    public ResponseEntity<Pokemon> getPokemonbyId(@PathVariable("pokedexNumber") int pokedexNumber) {

        Optional<Pokemon> pokemon = service.getPokemonById(pokedexNumber);

        return pokemon
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity postPokemon(@Valid @RequestBody Pokemon pokemon) {

        try {
            Pokemon p = service.savePokemon(pokemon);
            return ResponseEntity.created(null).build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{pokedexNumber}")
    public ResponseEntity putPokemon(@PathVariable ("pokedexNumber") int pokedexNumber, @Valid @RequestBody Pokemon pokemon) {

        pokemon.setPokedexNumber(pokedexNumber);

        Pokemon p = service.updatePokemon(pokemon, pokedexNumber);

        return p != null ?
                ResponseEntity.ok(p) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{pokedexNumber}")
    public ResponseEntity deletePokemonById(@PathVariable("pokedexNumber") int pokedexNumber) {
        boolean ok = service.deletePokemon(pokedexNumber);

        return ok ?
                ResponseEntity.ok().build() :
                ResponseEntity.notFound().build();
    }
}
