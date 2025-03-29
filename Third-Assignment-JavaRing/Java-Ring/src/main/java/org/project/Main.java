package org.project;

import org.project.entity.Entity;
import org.project.entity.enemies.Dragon;
import org.project.entity.enemies.Goblin;
import org.project.entity.enemies.Skeleton;
import org.project.entity.players.Knight;
import org.project.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Knight player = new Knight("dear player");
        List<Location> locations = new ArrayList<>();
        List<Entity> forestEnemies = new ArrayList<>();
        forestEnemies.add(new Skeleton());
        forestEnemies.add(new Goblin());
        locations.add(new Location("Dark Forest", toEnemyList(forestEnemies)));
        List<Entity> caveEnemies = new ArrayList<>();
        caveEnemies.add(new Dragon());
        locations.add(new Location("Ancient Cave", toEnemyList(caveEnemies)));
        System.out.println("🏰 Welcome to Java-Ring, " + player.getName() + "!");
        while (player.isAlive())
        {
            System.out.println("\n🌍 Available Locations:");
            for (int i = 0; i < locations.size(); i++)
            {
                System.out.println((i + 1) + ". " + locations.get(i).getName());
            }
            System.out.print("📍 Choose a location (number): ");
            int choice = scanner.nextInt() - 1;
            if (choice < 0 || choice >= locations.size())
            {
                System.out.println("❌ Invalid choice. Try again.");
                continue;
            }
            Location selectedLocation = locations.get(choice);
            Entity enemy = selectedLocation.getRandomEnemy();
            if (enemy == null)
            {
                System.out.println("✅ This location is cleared! Choose another one.");
                continue;
            }
            System.out.println("\n⚔️ A wild " + enemy.getName() + " appeared in " + selectedLocation.getName() + "!");
            boolean playerTurn = true;
            while (player.isAlive() && enemy.isAlive())
            {
                if (playerTurn)
                {
                    System.out.println("──────────────────────────────");
                    System.out.println("\n▶️ Your Turn:");
                    System.out.println("1. Attack");
                    System.out.println("2. Defend");
                    System.out.println("3. Special Ability");
                    System.out.print("> Choose action: ");
                    System.out.println("\n──────────────────────────────");
                    int action = scanner.nextInt();
                    switch (action)
                    {
                        case 1 -> player.attack(enemy);
                        case 2 -> player.defend();
                        case 3 -> player.useSpecialAbility(enemy);
                        default -> System.out.println("Invalid input.");
                    }
                }
                else
                {
                    if (Math.random() < 0.3)
                    {
                        enemy.useSpecialAbility(player);
                    }
                    else
                    {
                        enemy.attack(player);
                    }
                }
                System.out.println("\n❤️ You: " + player.getHp() + " HP");
                System.out.println("💀 " + enemy.getName() + ": " + ((enemy.getHp() <= 0) ? "DEFEATED" : enemy.getHp() + " HP"));
                playerTurn = !playerTurn;
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }

            if (!player.isAlive()) {
                System.out.println("☠️ You were slain by " + enemy.getName() + "...");
                break;
            }
            else
            {
                System.out.println("🏆 " + enemy.getName() + " defeated!");
            }
        }

        System.out.println("\n🎮 Game Over. Thanks for playing!");
        scanner.close();
    }
    private static List<org.project.entity.enemies.Enemy> toEnemyList(List<Entity> entities)
    {
        List<org.project.entity.enemies.Enemy> enemies = new ArrayList<>();
        for (Entity e : entities)
        {
            enemies.add((org.project.entity.enemies.Enemy) e);
        }
        return enemies;
    }
}
