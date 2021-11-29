package me.emokid.lab2.pokemons;

import me.emokid.lab2.moves.*;
import ru.ifmo.se.pokemon.*;

public class Conkeldurr extends Pokemon {
    public Conkeldurr(String name, int level){
        super(name,level);
        setStats(105, 140, 95, 55, 65, 45);
        setType(Type.FIGHTING);
        setMove(new RockSlide(), new Swagger(), new PsychoCut(), new MudShot());
    }
}