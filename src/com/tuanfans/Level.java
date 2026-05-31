package com.tuanfans;

/**
 * @author TuanFans
 * @date 2026/5/30
 */
public enum Level {
    EASY(2,1,3,0.01),
    MEDIUM(3,2,4,0.03),
    HARD(4,3,5,0.05),
    CUSTOM(getCustomBaseSpeed(),getCustomFactor(),getCustomCount(),getCustomShootRate());
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
    public static int getCustomBaseSpeed(){
        return ConfigManager.getInt("custom.baseSpeed",5);
    }
    public static int getCustomFactor(){
        return ConfigManager.getInt("custom.factor",4);
    }
    public static int getCustomCount(){
        return ConfigManager.getInt("custom.count",10);
    }
    public static double getCustomShootRate(){
        return ConfigManager.getDouble("custom.shootRate",0.07);
    }
}
