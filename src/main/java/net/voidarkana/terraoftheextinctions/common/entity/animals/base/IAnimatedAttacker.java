package net.voidarkana.terraoftheextinctions.common.entity.animals.base;

public interface IAnimatedAttacker {

    boolean isAttacking();

    void setAttacking(boolean attacking);


    int attackAnimationTimeout();

    void setAttackAnimationTimeout(int attackAnimationTimeout);

}