package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.KnightArmor;
import org.project.object.weapons.Sword;

public class Knight extends Player
{
    private int kickCooldown;
    private static final int KICK_COOLDOWN_RESET = 3;
    private static final int KICK_DAMAGE = 25;
    public Knight(String name)
    {
        super(name,
                120, // HP
                30,  // MP
                new Sword(),
                new KnightArmor());
        this.kickCooldown = 0;
    }
    @Override
    public void useSpecialAbility(Entity target)
    {
        if (kickCooldown <= 0)
        {
            System.out.println(name + " uses Power Kick! 💥");
            target.takeDamage(KICK_DAMAGE);
            kickCooldown = KICK_COOLDOWN_RESET;
        }
        else
        {
            System.out.println("Power Kick is on cooldown! (" + kickCooldown + " turns remaining)");
        }
    }

    @Override
    public void attack(Entity target)
    {
        super.attack(target);
        if (kickCooldown > 0)
        {
            kickCooldown--;
        }
    }
    public int getKickCooldown()
    {
        return kickCooldown;
    }
}
