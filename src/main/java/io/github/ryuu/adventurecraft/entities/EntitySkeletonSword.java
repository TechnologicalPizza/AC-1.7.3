package io.github.ryuu.adventurecraft.entities;

import net.minecraft.item.ItemInstance;
import net.minecraft.level.Level;

public class EntitySkeletonSword extends fr {
    public EntitySkeletonSword(Level world) {
        super(world);
        this.c = 1;
        this.heldItem = new ItemInstance(gm.p, 1);
    }

    protected void a(sn entity, float f) {
        if (f < 2.5D && entity.aW.e > this.aW.b && entity.aW.b < this.aW.e) {
            this.ae = 20;
            entity.a(this, this.c);
        }
    }

    protected int j() {
        return 0;
    }
}
