package org.project.object.consumables;

import org.project.entity.Entity;
import org.project.object.GameObject;

public abstract class Consumable implements GameObject
{

    private String name;
    private String description;
    private String rarity; // e.g., "Common", "Rare", "Epic"
    private int cooldown;         // current cooldown counter
    private int defaultCooldown;  // resets to this after use
    private int maxStackSize;

    public Consumable(String name, String description, String rarity, int defaultCooldown, int maxStackSize)
    {
        this.name = name;
        this.description = description;
        this.rarity = rarity;
        this.defaultCooldown = defaultCooldown;
        this.maxStackSize = maxStackSize;
        this.cooldown = 0; // ready at the start
    }
    public boolean isUsable()
    {
        return cooldown <= 0;
    }
    public void tickCooldown()
    {
        if (cooldown > 0)
        {
            cooldown--;
        }
    }
    @Override
    public final void use(Entity target)
    {
        if (!isUsable())
        {
            System.out.println(name + " is on cooldown (" + cooldown + " turns remaining).");
            return;
        }
        applyEffect(target);
        cooldown = defaultCooldown;
    }
    protected abstract void applyEffect(Entity target);
    public String getName()
    {
        return name;
    }
    public String getDescription()
    {
        return description;
    }
    public String getRarity()
    {
        return rarity;
    }
    public int getCooldown()
    {
        return cooldown;
    }
    public int getDefaultCooldown()
    {
        return defaultCooldown;
    }
    public int getMaxStackSize()
    {
        return maxStackSize;
    }
}
