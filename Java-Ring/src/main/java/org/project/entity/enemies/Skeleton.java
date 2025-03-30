package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Sword;

public class Skeleton extends Enemy
{
    private boolean hasResurrected = false;
    public Skeleton()
    {
        super("Skeleton", 60, 20, new Sword());
    }
    @Override
    public void takeDamage(int damage)
    {
        super.takeDamage(damage);
        if (hp <= 0 && !hasResurrected)
        {
            hasResurrected = true;
            hp = maxHP / 2;
            System.out.println("☠️ Skeleton resurrects with " + hp + " HP!");
        }
        else if (hp <= 0)
        {
            System.out.println("Skeleton is finally defeated.");
        }
    }

    @Override
    public void useSpecialAbility(Entity target)
    {
        System.out.println("Skeleton uses Bone Club Bash! Deals 10 extra damage.");
        target.takeDamage(10);
    }
}
