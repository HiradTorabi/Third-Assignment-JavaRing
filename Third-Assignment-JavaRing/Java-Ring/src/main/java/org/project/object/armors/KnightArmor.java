package org.project.object.armors;

public class KnightArmor extends Armor
{
    private static final String DEFAULT_NAME = "Knight's Plate Armor";
    private static final int BASE_DEFENSE = 25;
    private static final int BASE_DURABILITY = 100;
    public KnightArmor()
    {
        super(DEFAULT_NAME, BASE_DEFENSE, BASE_DURABILITY);
    }
    public KnightArmor(String customName)
    {
        super(customName, BASE_DEFENSE, BASE_DURABILITY);
    }
    public KnightArmor(String name, int defense, int durability)
    {
        super(name, defense, durability);
    }
    public String getInfo()
    {
        return "KnightArmor: Heavy defense armor designed for Knights.\n" +
                "Defense: " + getDefense() + "\n" +
                "Durability: " + getDurability();
    }
}
