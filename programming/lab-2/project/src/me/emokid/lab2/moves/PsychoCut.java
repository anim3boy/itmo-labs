package me.emokid.lab2.moves;
import ru.ifmo.se.pokemon.*;

public class PsychoCut extends PhysicalMove{
    public PsychoCut(){
        super(Type.PSYCHIC, 70, 100);
    }
    @Override
    protected String describe(){
        return "kick hard";
    }
}