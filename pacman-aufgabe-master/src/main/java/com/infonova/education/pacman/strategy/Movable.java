package com.infonova.education.pacman.strategy;


import com.infonova.education.pacman.GameObject;
import com.infonova.education.pacman.Level;
import com.infonova.education.pacman.UserAction;

public interface Movable {

    void move(UserAction userAction, Level level, GameObject gameObject);

}
