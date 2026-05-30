package com.tuanfans;

/**
 * @author TuanFans
 * @date 2026/5/30
 */
public enum Level {
    EASY(2,1,3,0.01),
    MEDIUM(3,2,4,0.03),
    HARD(4,3,5,0.06);
    private final int baseSpeed;
    private final int factor;
    private final int count;
    private final double shootRate;
    Level(int baseSpeed,int factor, int count, double shootRate){
        this.baseSpeed = baseSpeed;
        this.factor = factor;
        this.count = count;
        this.shootRate = shootRate;
    }
    public int getBaseSpeed(){
        return baseSpeed;
    }
    public int getFactor(){
        return factor;
    }
    public int getCount(){
        return count;
    }
    public double getShootRate(){
        return shootRate;
    }
}
