package org.project.location;

import org.project.entity.enemies.Enemy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Location
{
    private String name;
    private List<Enemy> originalEnemies;
    private List<Enemy> currentEnemies;
    public Location(String name, List<Enemy> enemies)
    {
        this.name = name;
        this.originalEnemies = new ArrayList<>(enemies);
        this.currentEnemies = new ArrayList<>(enemies);
    }
    public String getName()
    {
        return name;
    }
    public List<Enemy> getEnemies()
    {
        return currentEnemies;
    }
    public void resetEnemies()
    {
        currentEnemies = new ArrayList<>(originalEnemies);
        System.out.println("🔄 Enemies in " + name + " have been reset!");
    }
    public Enemy getRandomEnemy()
    {
        if (currentEnemies.isEmpty())
        {
            System.out.println("No enemies left in " + name + ".");
            return null;
        }
        Collections.shuffle(currentEnemies);
        return currentEnemies.remove(0);
    }
    public void displayEnemies()
    {
        System.out.println("👾 Enemies in " + name + ":");
        for (Enemy enemy : currentEnemies)
        {
            System.out.println("- " + enemy.getName());
        }
    }
}
