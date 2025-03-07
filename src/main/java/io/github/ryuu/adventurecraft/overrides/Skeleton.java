package io.github.ryuu.adventurecraft.overrides;

import net.minecraft.entity.monster.Monster;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;

public class Skeleton extends Monster {
    public Skeleton(Level world) {
        super(world);
        this.O = "/mob/skeleton.png";
        this.heldItem = new iz(ItemType.i, 1);
    }

    protected String g() {
        return "mob.skeleton";
    }

    protected String j_() {
        return "mob.skeletonhurt";
    }

    protected String i() {
        return "mob.skeletonhurt";
    }

    public void o() {
        if (this.aI.f() && this.aI.x.mobsBurn) {
            float f = a(1.0F);
            if (f > 0.5F && this.aI.l(in.b(this.aM), in.b(this.aN), in.b(this.aO)) && this.bs.nextFloat() * 30.0F < (f - 0.4F) * 2.0F)
                this.bv = 300;
        }
        super.o();
    }

    protected void a(sn entity, float f) {
        if (f < 10.0F) {
            double d = entity.aM - this.aM;
            double d1 = entity.aO - this.aO;
            if (this.ae == 0) {
                sl entityarrow = new sl(this.aI, this, this.c);
                entityarrow.aN += 1.399999976158142D;
                double d2 = entity.aN + entity.w() - 0.20000000298023224D - entityarrow.aN;
                float f1 = in.a(d * d + d1 * d1) * 0.2F;
                this.aI.a(this, "random.bow", 1.0F, 1.0F / (this.bs.nextFloat() * 0.4F + 0.8F));
                this.aI.b(entityarrow);
                entityarrow.a(d, d2 + f1, d1, 0.6F, 12.0F);
                this.ae = 30;
            }
            this.aS = (float) (Math.atan2(d1, d) * 180.0D / 3.1415927410125732D) - 90.0F;
            this.e = true;
        }
    }

    public void b(CompoundTag nbttagcompound) {
        super.b(nbttagcompound);
    }

    public void a(CompoundTag nbttagcompound) {
        super.a(nbttagcompound);
    }

    protected int j() {
        return ItemType.j.bf;
    }

    protected void q() {
        if (j() != 0) {
            int m = this.bs.nextInt(3) + 1;
            for (int j = 0; j < m; j++) {
                hl item = b(j(), 1);
                if (j() != ItemType.j.bf)
                    item.a.a = 3;
            }
        }
        int i = this.bs.nextInt(3);
        for (int k = 0; k < i; k++)
            b(ItemType.aV.bf, 1);
    }
}
