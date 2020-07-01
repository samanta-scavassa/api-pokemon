package com.fatec.pokemon;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name="pokemon")
public class Pokemon {

    @Column(name="name", length = 50, nullable = false)
    @NotNull(message = "O nome é obrigatório")
    @NotEmpty
    private String name;

    @Id
    @Column(name="pokedex_number", length = 10, nullable = false)
    @NotNull
    private int pokedexNumber;

    @Column(name="type1", length = 50)
    private String type1;

    @Column(name="type2", length = 50)
    private String type2;

    @Column(name="generation", nullable = false)
    @NotNull
    private int generation;
}
