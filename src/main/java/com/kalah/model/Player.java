package com.kalah.model;

public class Player {

    private static int counter;

    private String name;

    private int playerId;

    public Player(String name) {
        this.name = name;
        this.playerId = ++counter;
        //System.out.println("Kala Map Before starting: " + kalaMap);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        return this.getName().equalsIgnoreCase(((Player) obj).getName());
    }

    @Override
    public int hashCode() {
        return playerId * 9;
    }


}
