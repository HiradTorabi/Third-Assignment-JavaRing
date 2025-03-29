package org.project.object.weapons;

import org.project.entity.Entity;

public class Sword extends Weapon
{
    private int abilityCharge;
    private static final int ABILITY_COST = 3;
    private static final int ABILITY_MULTIPLIER = 2;

    public Sword()
    {
        super("Sword", 15, 10, 100, 15, false);
        this.abilityCharge = 0;
    }

    @Override
    public void uniqueAbility(Entity target)
    {
        if (abilityCharge >= ABILITY_COST)
        {
            System.out.println("Sword's special ability: Power Strike!");
            int damage = getDamage() * ABILITY_MULTIPLIER;
            System.out.println("Deals " + damage + " damage to " + target.getName());
            target.takeDamage(damage);
            abilityCharge = 0;
        }
        else
        {
            System.out.println("Not enough charge (" + abilityCharge + "/" + ABILITY_COST + ")");
        }
    }

    @Override
    public void use(Entity target)
    {
        if (getDurability() <= 0)
        {
            System.out.println("Sword is broken and cannot be used!");
            return;
        }
        super.use(target);
        abilityCharge++;
        System.out.println("Sword charge: " + abilityCharge + "/" + ABILITY_COST);
    }

    public int getCharge()
    { return abilityCharge; }
}
