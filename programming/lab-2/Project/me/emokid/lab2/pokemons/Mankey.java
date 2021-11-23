package me.emokid.lab2.pokemons;
import me.emokid.lab2.moves.*;
import ru.ifmo.se.pokemon.*;

public class Mankey extends Pokemon {
    public Mankey(String name, int level){
        super(name,level);
        setStats('40', '80', '35', '35', '45', '70');
        setType(Type.FIGHTING);
        setMove(new Icy_Wind(), new Scratch(), new Drain_Punch());
    }
}