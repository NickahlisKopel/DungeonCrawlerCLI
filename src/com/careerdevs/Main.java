package com.careerdevs;

import com.careerdevs.ui.CLI;

import java.util.Random;

public class Main {
    static Enemy enemy1 = new Enemy("bat",5,1);
    static Enemy enemy2 = new Enemy("rat",10,2);
    static Enemy enemy3 = new Enemy("guard",50,25);
    static Enemy enemy4 = new Enemy("dragon",100,35);
    public static Enemy[] enemies = {enemy1,enemy2,enemy3,enemy4};
    static Player player = new Player("Name",50,20);
    static Random rand = new Random();
    static int runNum = 1;
    public static void main(String[] args) {

        System.out.println("DUNGEON CRAWLER CLI");
        mainMenu();

    }
    public static void mainMenu(){
       int selection = CLI.readInt("1)Play\n2)Exit\n3)Cheats\nSelection",1,3);
        if(selection == 1){
            game();
            return;
        }else if (selection == 3){
            cheatsMenu();
            return;
        }else{

        }
    }
    public static void game(){
        System.out.println("RUN: "+runNum);
        if(player.getHealth() <= 0){
            player.setHealth(50);
            System.out.println("Health Restored!");
        }
        int selection = CLI.readInt("1)Ready\n2)Stats\n3)Main Menu\nSelection",1,3);
        if(selection == 1){
            encounter();
            return;
        }else if (selection == 2){
            System.out.println("Stats");
            game();
            return;
        }else{
            mainMenu();
            return;
        }

    }
    public static void cheatsMenu(){
        System.out.println("Cheats");
        String cheat = CLI.readString("Insert Cheat");
        if(cheat.equals("bigman")){
            player.setHealth(999);
            System.out.println("Health Increased to "+player.getHealth()+"!");
        }else if (cheat.equals("smallman")){
            player.setHealth(25);
            System.out.println("Health Decreased to "+player.getHealth()+"!");
        }else if (cheat.equals("strongman")){
            player.setDamage(100);
            System.out.println("Damage Increased to "+player.getDamage()+"!");
        }else if (cheat.equals("exit")){
            mainMenu();
            return;
        }else{
            System.out.println("Cheat Invalid!");
        }

        cheatsMenu();
        return;

    }
    public static void encounter(){
    Enemy enemy = enemies[rand.nextInt(enemies.length)];
    int originalHealth = enemy.getHealth();
    int turn = 0;
        System.out.println(enemy.getName()+" Has Appeared!");
        while(enemy.getHealth() > 0){
            if(player.getHealth() <=0){
                System.out.println("You Lose!");
                runNum = 1;
                game();
                return;
            }
            System.out.println("Enemy Health: "+enemy.getHealth());
            System.out.println("Player Health: "+player.getHealth());
            int move = CLI.readInt("1)Attack\n2)Flee",1,2);
            if(move == 1){
                int damage =  rand.nextInt(player.getDamage());
                System.out.println(player.getName()+" attacks "+enemy.getName()+" for "+damage+" damage!");
                enemy.setHealth(enemy.getHealth() - damage);
            }else{
                int chance = rand.nextInt(100);
                if(chance > 50){
                    game();
                }else{
                    System.out.println("Flee Failed!");
                }

            }
            turn++;
            if(turn == 1){
                int enemyDamage =  rand.nextInt(enemy.getDamage());
                System.out.println(enemy.getName()+" attacks "+player.getName()+" for "+enemyDamage+" damage!");
                player.setHealth(player.getHealth() - enemyDamage);
                turn--;
            }

        }
        System.out.println("You Win!");
        runNum++;
        game();
        return;

    }
}
