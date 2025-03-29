package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Sword;

public class Goblin extends Enemy
{
    public Goblin()
    {
        super("Goblin", 50, 10, new Sword());
    }
    @Override
    public int getHp()
    {
        return super.getHp();
    }

    @Override
    public void useSpecialAbility(Entity target)
    {
        System.out.println("Goblin growls angrily... but does nothing specia...it seems like one of those days...goblin days.");
    }

}
