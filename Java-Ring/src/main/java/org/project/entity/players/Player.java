package org.project.entity.players;

import org.project.entity.Entity;
import org.project.object.armors.Armor;
import org.project.object.weapons.Weapon;

// TODO: UPDATE IMPLEMENTATION
public abstract class Player implements Entity
{
    protected String name;
    protected Weapon weapon;
    protected Armor armor;
    protected int hp;
    protected int maxHP;
    protected int mp;
    protected int maxMP;
    protected boolean isDefending;

    public Player(String name, int hp, int mp, Weapon weapon, Armor armor)
    {
        this.name = name;
        this.maxHP = hp;
        this.hp = hp;
        this.maxMP = mp;
        this.mp = mp;
        this.weapon = weapon;
        this.armor = armor;
        this.isDefending = false;
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
        isDefending = true;
        System.out.println(name + " raises " + armor.getName() + " to defend!");
    }

    @Override
    public void takeDamage(int damage)
    {
        int actualDamage = isDefending ?
                Math.max(1, damage - armor.getEffectiveDefense()) : damage;

        hp -= actualDamage;
        System.out.println(name + " takes " + actualDamage + " damage!");

        if (isDefending)
        {
            isDefending = false;
        }
    }

    @Override
    public void heal(int health)
    {
        hp = Math.min(maxHP, hp + health);
        System.out.println(name + " heals " + health + " HP!");
    }

    @Override
    public void fillMana(int mana)
    {
        mp = Math.min(maxMP, mp + mana);
        System.out.println(name + " restores " + mana + " MP!");
    }
    public boolean isAlive()
    {
        return hp > 0;
    }
    public abstract void useSpecialAbility(Entity target);
    public String getName()
    { return name; }
    public int getHp()
    { return hp; }
    public int getMaxHP()
    { return maxHP; }
    public int getMp()
    { return mp; }
    public int getMaxMP()
    { return maxMP; }
    public Weapon getWeapon()
    { return weapon; }
    public Armor getArmor()
    { return armor; }
    public boolean isDefending()
    { return isDefending; }
}
