package org.project.object.armors;

import org.project.entity.Entity;
import org.project.object.GameObject;

public abstract class Armor implements GameObject
{
    private String name;
    private int defense;
    private int maxDefense;
    private int durability;
    private int maxDurability;
    private boolean isBroke;
    public Armor(String name, int defense, int durability)
    {
        this.name = name;
        this.defense = defense;
        this.maxDefense = defense;
        this.maxDurability = durability;
        this.durability = durability;
        this.isBroke = false;
    }
    public void checkBreak()
    {
        if (durability <= 0 && !isBroke)
        {
            isBroke = true;
            defense = 0;
            System.out.println(name + " has broken!");
        }
    }
    public void takeHit(int damage)
    {
        if (!isBroke)
        {
            int actualDamage = Math.max(1, damage / 2);
            durability -= actualDamage;
            checkBreak();
            System.out.println(name + " took " + actualDamage + " durability damage!");
        }
    }
    public boolean repair(int repairAmount)
    {
        if (!isBroke && durability == maxDurability)
        {
            System.out.println(name + " doesn't need repair!");
            return false;
        }
        durability = Math.min(maxDurability, durability + repairAmount);
        if (isBroke && durability > 0)
        {
            isBroke = false;
            defense = maxDefense;
        }
        System.out.println("Repaired " + name + " to " + durability + "/" + maxDurability);
        return true;
    }

    public void upgrade(int defenseBonus, int durabilityBonus)
    {
        this.maxDefense += defenseBonus;
        this.defense = maxDefense;
        this.maxDurability += durabilityBonus;
        this.durability = maxDurability;
        System.out.println(name + " upgraded! Defense: +" + defenseBonus + ", Durability: +" + durabilityBonus);
    }

    public int getEffectiveDefense()
    {
        return isBroke ? 0 : (int)(defense * ((double)durability / maxDurability));
    }
    public String getStatus()
    {
        return name + " | Defense: " + defense + "/" + maxDefense + " | Durability: " + durability + "/" + maxDurability + (isBroke ? " (BROKEN)" : "");
    }

    @Override
    public void use(Entity target)
    {
        System.out.println(getName() + " equipped.");
    }
    public String getName()
    { return name; }
    public int getDefense()
    { return defense; }
    public int getMaxDefense()
    { return maxDefense; }
    public int getDurability()
    { return durability; }
    public int getMaxDurability()
    { return maxDurability; }
    public boolean isBroke()
    { return isBroke; }
    public abstract void activateBonus();
}
