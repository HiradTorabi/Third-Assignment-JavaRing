package org.project.object.consumables;

import org.project.entity.Entity;

public class Flask extends Consumable
{
    private static final int HEAL_PERCENTAGE = 10;
    public Flask()
    {
        super(
                "Healing Flask",
                "Restores 10% of max HP.",
                "Common",
                2,   // cooldown: 2 turns
                5    // max 5 per inventory slot
        );
    }

    @Override
    protected void applyEffect(Entity target)
    {
        int healAmount = target.getMaxHP() * HEAL_PERCENTAGE / 100;
        target.heal(healAmount);
        System.out.println(getName() + " used! Restored " + healAmount + " HP.");
    }
}
