package Zim.model;

/**
 * Created by Laxton-Joe on 2017/9/6.
 */
abstract class QueueObject {

    private boolean poisonPill;


    public boolean isPoisonPill() {
        return poisonPill;
    }

    public void setPoisonPill(boolean poisonPill) {
        this.poisonPill = poisonPill;
    }
}
