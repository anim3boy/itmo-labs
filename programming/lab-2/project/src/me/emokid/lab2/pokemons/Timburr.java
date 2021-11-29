package me.emokid.lab2.pokemons;

import me.emokid.lab2.moves.*;
import ru.ifmo.se.pokemon.*;

public class Timburr extends Pokemon {
    public Timburr(String name, int level){
        super(name,level);
        setStats(75,80,55,25,35,35);
        setType(Type.FIGHTING);
        setMove(new RockSlide(), new Swagger());
    }
}