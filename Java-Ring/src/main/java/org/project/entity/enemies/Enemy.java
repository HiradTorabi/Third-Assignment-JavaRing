package org.project.entity.enemies;

import org.project.entity.Entity;
import org.project.object.weapons.Weapon;

public abstract class Enemy implements Entity
{
    protected Weapon weapon;
    protected int hp;
    protected int maxHP;
    protected int mp;
    protected int maxMP;
    protected String name;

    public Enemy(String name, int hp, int mp, Weapon weapon)
    {
        this.name = name;
        this.maxHP = hp;
        this.hp = hp;
        this.maxMP = mp;
        this.mp = mp;
        this.weapon = weapon;
    }

    @Override
    public void attack(Entity target)

    {
        System.out.println(name + " attacks with " + weapon.getName() + "!");
        weapon.use(target);
    }

    @Override
    public void defend()
    {
        System.out.println(name + " has no defend mechanic.");
    }

    @Override
    public void takeDamage(int damage)
    {
        hp -= damage;
        System.out.println(name + " takes " + damage + " damage!");
    }

    @Override
    public void heal(int health)
    {
        hp = Math.min(maxHP, hp + health);
        System.out.println(name + " heals " + health + " HP.");
    }

    @Override
    public void fillMana(int mana)
    {
        mp = Math.min(maxMP, mp + mana);
        System.out.println(name + " restores " + mana + " MP.");
    }

    @Override
    public boolean isAlive()
    {
        return hp > 0;
    }

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public int getMaxHP()
    {
        return maxHP;
    }

    @Override
    public int getMaxMP()
    {
        return maxMP;
    }

    @Override
    public int getHp()
    {
        return hp;
    }

    public Weapon getWeapon()
    {
        return weapon;
    }

    public abstract void useSpecialAbility(Entity target);
}
