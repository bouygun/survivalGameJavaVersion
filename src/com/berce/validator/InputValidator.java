package com.berce.validator;

import com.berce.model.Enemy;
import com.berce.model.Hero;
import com.berce.model.Resource;

import java.util.List;

public class InputValidator {

    public static boolean validateParser(Resource resource, Hero hero, List<Enemy> enemies) {
        return resource != null && hero != null && !enemies.isEmpty();
    }
}
