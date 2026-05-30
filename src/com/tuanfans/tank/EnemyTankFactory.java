package com.tuanfans.tank;

import com.tuanfans.Level;

import java.util.ArrayList;

/**
 * @author TuanFans
 * @date 2026/5/29
 */
public class EnemyTankFactory {
    private EnemyTankFactory(){}
    public static ArrayList<EnemyTank> initEnemyTanks(Level level){
        ArrayList<EnemyTank> enemyTanks = new ArrayList<>();
        while(enemyTanks.size()<level.getCount()){
            enemyTanks.add(EnemyTank.createEnemyTank());
        }
        return enemyTanks;
    }
}
