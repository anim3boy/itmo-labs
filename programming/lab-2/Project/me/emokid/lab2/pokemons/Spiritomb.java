package me.emokid.lab2.pokemons;
import me.emokid.lab2.moves.*;
import ru.ifmo.se.pokemon.*;

public class Spiritomb extends Pokemon {
    public Spiritomb(String name, int level){
        super(name,level);
        setStats('50', '92', '108', '92', '108', '35');
        setType(Type.GHOST);
        setMove(new Confuse_Ray(), new Will-O-Wisp(), new Facade(), new Rest());
    }
}