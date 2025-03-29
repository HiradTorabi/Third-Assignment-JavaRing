package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Sword;

import java.util.List;

public class Dragon extends Enemy
{
    private static final int FLAME_BREATH_DAMAGE = 20;
    public Dragon()
    {
        super("Dragon", 150, 50, new Sword());
    }
    @Override
    public void attack(Entity target)
    {
        System.out.println(name + " breathes fire and bypasses armor!");
        target.takeDamage(getWeapon().getDamage());
    }

    @Override
    public void useSpecialAbility(Entity target)
    {
        System.out.println("🔥 Dragon uses Flame Breath on " + target.getName() + "!");
        target.takeDamage(FLAME_BREATH_DAMAGE);
    }
    @Override
    public int getHp()
    {
        return hp;
    }
    public void useFlameBreathOnAll(List<Entity> players)
    {
        System.out.println("🔥 Dragon unleashes Flame Breath on ALL players!");
        for (Entity player : players)
        {
            System.out.println("→ " + player.getName() + " takes " + FLAME_BREATH_DAMAGE + " damage!");
            player.takeDamage(FLAME_BREATH_DAMAGE);
        }
    }
}
