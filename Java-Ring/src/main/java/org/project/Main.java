package org.project;

import org.project.entity.Entity;
import org.project.entity.enemies.Dragon;
import org.project.entity.enemies.Goblin;
import org.project.entity.enemies.Skeleton;
import org.project.entity.players.Assassin;
import org.project.entity.players.Knight;
import org.project.entity.players.Player;
import org.project.entity.players.Wizard;
import org.project.location.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) throws InterruptedException
    {
        Scanner scanner = new Scanner(System.in);
        Player player = null;
        System.out.println("Welcome to Java-Ring!");
        System.out.print("👤 Enter your name pls: ");
        String name = scanner.nextLine();
        System.out.println("\nChoose your hero, " + name + ":");
        System.out.println("1. 🛡️ Knight - Tough and balanced");
        System.out.println("2. 🧙‍♂️ Wizard - Powerful magic user");
        System.out.println("3. 🗡️ Assassin - Fast and deadly");
        while (player == null)
        {
            System.out.print("> Enter number (1-3): ");
            int charChoice = scanner.nextInt();
            switch (charChoice) {
                case 1 -> player = new Knight(name);
                case 2 -> player = new Wizard(name);
                case 3 -> player = new Assassin(name);
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
        List<Location> locations = new ArrayList<>();
        List<Entity> forestEnemies = new ArrayList<>();
        forestEnemies.add(new Skeleton());
        forestEnemies.add(new Goblin());
        locations.add(new Location("🌲 Dark Forest", toEnemyList(forestEnemies)));
        List<Entity> caveEnemies = new ArrayList<>();
        caveEnemies.add(new Dragon());
        locations.add(new Location("🏰 Ancient Cave", toEnemyList(caveEnemies)));
        System.out.println("\n🏰 Welcome to Java-Ring, " + player.getName() + "!");
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
                System.out.println("\n──────────────────────────────");
                System.out.println("▶️ Your Turn:");
                System.out.println("1. Attack");
                System.out.println("2. Defend");
                System.out.println("3. Special Ability");
                System.out.print("> Choose action: ");

                int action = scanner.nextInt();
                switch (action) {
                    case 1 -> player.attack(enemy);
                    case 2 -> player.defend();
                    case 3 -> player.useSpecialAbility(enemy);
                    default -> System.out.println("Invalid input.");
                }
                if (enemy.isAlive())
                {
                    Thread.sleep(500);
                    System.out.println("\n💀 " + enemy.getName() + "'s Turn!");
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
                Thread.sleep(1000);
            }

            if (!player.isAlive())
            {
                System.out.println("☠️ You were slain by " + enemy.getName() + "... Game Over.");
                break;
            }
            else
            {
                System.out.println("🏆 " + enemy.getName() + " defeated!");
            }
        }

        System.out.println("\n🎮 Thanks for playing!");
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
