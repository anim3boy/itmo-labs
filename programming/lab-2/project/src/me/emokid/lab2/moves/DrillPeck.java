package me.emokid.lab2.moves;
import ru.ifmo.se.pokemon.*;

public class DrillPeck extends PhysicalMove{

    public DrillPeck(){
        super(Type.FLYING,80,100);

    }
    @Override
    protected String describe(){
        return "kick";
    }
}