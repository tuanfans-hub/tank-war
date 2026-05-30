package com.tuanfans.tank;

import java.util.ArrayList;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class EnemyTankFactory {
    public static ArrayList<EnemyTank> initEnemyTanks(int count){
        ArrayList<EnemyTank> enemyTanks = new ArrayList<>();
        while(enemyTanks.size()<count){
            enemyTanks.add(new EnemyTank());
        }
        return enemyTanks;
    }
}
