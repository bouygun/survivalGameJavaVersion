import java.util.List;

public class Simulation {
    private final Hero hero;
    private final List<Enemy> enemies;

    // constructor: run simulation
    public Simulation(Hero hero, List<Enemy> enemies, Resource resource) {
        this.hero = hero;
        this.enemies = enemies;
    }

    public void start() {
        System.out.println("Hero started journey with " + hero.getHp() + " HP!");

        for (Enemy enemy : enemies) {
            while (hero.getHp() > 0 && enemy.getHp() > 0) {
                hero.attack(enemy);
                enemy.attack(hero);
            }


            if (hero.getHp() <= 0) {
                System.out.println("Hero is Dead!! Last seen at position " + enemy.getPosition() + "!!");
                return;
            } else {
                System.out.println("Hero defeated " + enemy.getName() + " with " + hero.getHp() + " HP remaining");
            }
        }

        System.out.println("Hero Survived!");
    }
}