package de.gamexlive.pvptrainer.gamemodes.aimtrainer.enemytypes;

public enum Enemy {

    A(10);

    private int points;

    Enemy(int points) { this.points = points; }

    int getPoints() { return points; }

}