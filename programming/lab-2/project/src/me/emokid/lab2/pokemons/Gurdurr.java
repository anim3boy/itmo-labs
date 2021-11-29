package me.emokid.lab2.pokemons;

import me.emokid.lab2.moves.*;
import ru.ifmo.se.pokemon.*;

public class Gurdurr extends Pokemon {
    public Gurdurr(String name, int level){
        super(name,level);
        setStats(85, 105, 85, 40, 50, 40);
        setType(Type.FIGHTING);
        setMove(new RockSlide(), new Swagger(), new PsychoCut());
    }
}