package org.project.object.weapons;

import org.project.entity.Entity;

// TODO: UPDATE IMPLEMENTATION
public abstract class Weapon
{
    private final String name;
    private int damage;
    private int manaCost;
    private int durability;
    private final int maxDurability;
    private int criticalChance;
    private final boolean isMagic;

    public Weapon(String name, int damage, int manaCost, int durability, int criticalChance, boolean isMagic) {
        this.name = name;
        this.damage = damage;
        this.manaCost = manaCost;
        this.durability = durability;
        this.maxDurability = durability;
        this.criticalChance = criticalChance;
        this.isMagic = isMagic;
    }

    public void use(Entity target)
    {
        if(durability <= 0)
        {
            System.out.println(name + " is broken and cannot be used!");
            return;
        }

        int actualDamage = calculateActualDamage();
        System.out.println(name + " used! (" + actualDamage + " damage)");
        target.takeDamage(actualDamage);
        durability--;
    }

    private int calculateActualDamage()
    {
        return Math.random() * 100 < criticalChance ?
                damage * 2 : damage;
    }

    public abstract void uniqueAbility(Entity target);

    public boolean canUse(int currentMana)
    {
        return currentMana >= manaCost && durability > 0;
    }

    public void repair()
    {
        durability = maxDurability;
        System.out.println(name + " has been repaired!");
    }

    public void upgrade(int damageBonus, int manaReduction)
    {
        this.damage += damageBonus;
        this.manaCost = Math.max(0, manaCost - manaReduction);
        System.out.println(name + " upgraded! Damage +" + damageBonus + ", Mana cost -" + manaReduction);
    }

    // Getters
    public String getName()
    { return name; }
    public int getDamage()
    { return damage; }
    public int getManaCost()
    { return manaCost; }
    public int getDurability()
    { return durability; }
    public int getCriticalChance()
    { return criticalChance; }
    public boolean isMagic()
    { return isMagic; }

    public String getStatus()
    {
        return name + " | Damage: " + damage +
                " | Mana: " + manaCost +
                " | Durability: " + durability + "/" + maxDurability +
                " | Crit: " + criticalChance + "%" +
                (isMagic ? " (Magic)" : "");
    }
}