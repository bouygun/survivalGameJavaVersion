import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Pls enter the text input: ");
        StringBuilder inputBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            inputBuilder.append(line).append("\n");
        }
        String input = inputBuilder.toString();

        // resource pattern
        Pattern resourcePattern = Pattern.compile("Resources are (\\d+) meters");
        Matcher resourceMatcher = resourcePattern.matcher(input);
        int resourceDistance = 0;
        if (resourceMatcher.find()) {
            resourceDistance = Integer.parseInt(resourceMatcher.group(1));
        }

        // hero patterns
        Pattern heroHpPattern = Pattern.compile("Hero has (\\d+) hp");
        Pattern heroAttackPattern = Pattern.compile("Hero attack is (\\d+)");
        Matcher heroHpMatcher = heroHpPattern.matcher(input);
        Matcher heroAttackMatcher = heroAttackPattern.matcher(input);
        int heroHp = 0;
        int heroAttack = 0;
        if (heroHpMatcher.find()) {
            heroHp = Integer.parseInt(heroHpMatcher.group(1));
        }
        if (heroAttackMatcher.find()) {
            heroAttack = Integer.parseInt(heroAttackMatcher.group(1));
        }

        Hero hero = new Hero(heroHp, heroAttack);

        // enemy patterns
        Pattern enemyStatsPattern = Pattern.compile("(\\w+) has (\\d+) hp\\n\\w+ attack is (\\d+)");
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

        Resource resource = new Resource(resourceDistance);

        Simulation simulation = new Simulation(hero, enemies, resource);

        simulation.start();
    }
}
