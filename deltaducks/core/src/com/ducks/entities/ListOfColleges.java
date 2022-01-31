package com.ducks.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.ducks.DeltaDucks;
import com.ducks.screens.MainGameScreen;
import com.ducks.sprites.Cannon;
import com.ducks.sprites.College;
import com.ducks.sprites.Monster;

public class ListOfColleges {

    private World world;
    private MainGameScreen screen;

    private Array<College> collegeBodies;
    private final int NUMBER_OF_MONSTERS = 1;

    private final int SPAWN_X = 400;
    private final int SPAWN_Y = 400;
    private final float SPAWN_RADIUS = 10f * 4f * College.COLLEGE_HEIGHT / DeltaDucks.PIXEL_PER_METER;

    ListOfCannons cannons;

    public ListOfColleges(World world, MainGameScreen screen, ListOfCannons cannons) {
        this.world = world;
        this.screen = screen;
        this.cannons = cannons;
        collegeBodies = new Array<College>();
        spawnColleges();
    }

    public void spawnColleges() {
        for(int i = 0; i < NUMBER_OF_MONSTERS; i++) {
            collegeBodies.add(new College(world, screen, SPAWN_X,SPAWN_Y, SPAWN_RADIUS, College.CollegeName.DERWENT, cannons));
        }
    }

    public void update(float deltaTime) {
        Array<College> collegeBodiesToRemove = new Array<College>();
        for( College college : collegeBodies) {
            if(college.health <= 0){
                collegeBodiesToRemove.add(college);
                college.dispose();
            } else {
                college.update(deltaTime);
            }
        }
        collegeBodies.removeAll(collegeBodiesToRemove, true);
    }

    public void draw(SpriteBatch batch) {
        for( College college : collegeBodies) {
            college.extendedDraw(batch);
        }
    }

    public Array<Vector2> getCoordinates() {
        Array <Vector2> coordinates = new Array <Vector2>();
        for( College college : collegeBodies) {
            coordinates.add(college.collegeBody.getPosition());
        }
        return coordinates;
    }
}
