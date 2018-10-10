package com.zhixun.mvptest.ui.bean;

/**
 * Created by Administrator on 2018/9/18.
 */

public class AbilityBean {
    //有哪个些能力
    public static final String[] abilitys = {"击杀", "生存", "助攻", "物理", "魔法", "防御", "金钱"};

    //每个能力的值，范围0~100，单位%
    private int kill;//击杀
    private int survival;//生存
    private int assist;//助攻
    private int ad;//物理
    private int ap;//魔法
    private int defense;//防御
    private int money;//金钱

    public AbilityBean(int kill, int survival, int assist, int ad, int ap, int defense, int money) {
        this.kill = kill;
        this.survival = survival;
        this.assist = assist;
        this.ad = ad;
        this.ap = ap;
        this.defense = defense;
        this.money = money;
    }

    public static String[] getAbilitys() {
        return abilitys;
    }

    public int getKill() {
        return kill;
    }

    public void setKill(int kill) {
        this.kill = kill;
    }

    public int getSurvival() {
        return survival;
    }

    public void setSurvival(int survival) {
        this.survival = survival;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    public int getAd() {
        return ad;
    }

    public void setAd(int ad) {
        this.ad = ad;
    }

    public int getAp() {
        return ap;
    }

    public void setAp(int ap) {
        this.ap = ap;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int[] getAllAbility() {
        int[] allAbility = {kill, survival, assist, ad, ap, defense, money};
        return allAbility;
    }
}
