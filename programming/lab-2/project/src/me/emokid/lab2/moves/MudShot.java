package me.emokid.lab2.moves;
import ru.ifmo.se.pokemon.*;

public class MudShot extends SpecialMove{
    public MudShot(){
        super(Type.GROUND, 55, 95);
    }
    @Override
    protected String describe(){
        return "confuse";
    }
    @Override
    protected void applyOppEffects(Pokemon opp){
        opp.setMod(Stat.SPEED, -1);
    }
}