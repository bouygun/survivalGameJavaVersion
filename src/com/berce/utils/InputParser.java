package com.berce.utils;
import com.berce.model.Enemy;
import com.berce.model.Hero;
import com.berce.model.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputParser {

    private static final Pattern resourcePattern = Pattern.compile("Resources are (\\d+) meters away");
    private static final Pattern heroHpPattern = Pattern.compile("Hero has (\\d+) hp");
    private static final Pattern heroAttackPattern = Pattern.compile("Hero attack is (\\d+)");
    private static final Pattern enemyStatsPattern = Pattern.compile("(\\w+) has (\\d+) hp\\n\\w+ attack is (\\d+)");

    public static Resource parseResource(String input) {
        Matcher resourceMatcher = resourcePattern.matcher(input);
        if (resourceMatcher.find()) {
            int distance = Integer.parseInt(resourceMatcher.group(1));
            return new Resource(distance);
        }
        return null;
    }

    public static Hero parseHero(String input) {
        Matcher heroHpMatcher = heroHpPattern.matcher(input);
        Matcher heroAttackMatcher = heroAttackPattern.matcher(input);

        if (heroHpMatcher.find() && heroAttackMatcher.find()) {
            int hp = Integer.parseInt(heroHpMatcher.group(1));
            int attack = Integer.parseInt(heroAttackMatcher.group(1));
            return new Hero(hp, attack);
        }
        return null;
    }

    public static List<Enemy> parseEnemies(String input) {
        Matcher enemyStatsMatcher = enemyStatsPattern.matcher(input);
        List<Enemy> enemies = new ArrayList<>();

        while (enemyStatsMatcher.find()) {
            String enemyName = enemyStatsMatcher.group(1);
            int enemyHp = Integer.parseInt(enemyStatsMatcher.group(2));
            int enemyAttack = Integer.parseInt(enemyStatsMatcher.group(3));

            Pattern enemyPositionPattern = Pattern.compile("There is a " + enemyName + " at position (\\d+)");
            Matcher enemyPositionMatcher = enemyPositionPattern.matcher(input);

            while (enemyPositionMatcher.find()) {
                int enemyPosition = Integer.parseInt(enemyPositionMatcher.group(1));
                enemies.add(new Enemy(enemyName, enemyHp, enemyAttack, enemyPosition));
            }
        }
        return enemies;
    }
}
