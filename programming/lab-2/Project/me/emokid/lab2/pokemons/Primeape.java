package me.emokid.lab2.pokemons;
import me.emokid.lab2.moves.*;
import ru.ifmo.se.pokemon.*;

public class Primeape extends Pokemon {
    public Primeape(String name, int level){
        super(name,level);
        setStats('65', '105', '60', '60', '70', '95');
        setType(Type.FIGHTING);
        setMove(new Icy_Wind(), new Scratch(), new Drain_Punch(), new Rock_Wrecker());
    }
}