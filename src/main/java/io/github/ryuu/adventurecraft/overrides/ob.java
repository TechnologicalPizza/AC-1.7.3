package io.github.ryuu.adventurecraft.overrides;

import net.minecraft.client.Minecraft;

public class ob {
    protected final Minecraft a;

    public boolean b;

    public int destroyExtraWidth;

    public int destroyExtraDepth;

    public ob(Minecraft minecraft) {
        this.destroyExtraWidth = 0;
        this.destroyExtraDepth = 0;
        this.b = false;
        this.a = minecraft;
    }

    public void a(fd world) {
    }

    public void a(int i, int j, int k, int l) {
        this.a.f.a((gs) this.a.h, i, j, k, l);
        b(i, j, k, l);
    }

    public boolean b(int i, int j, int k, int l) {
        fd world = this.a.f;
        Tile block = Tile.m[world.a(i, j, k)];
        world.e(2001, i, j, k, block.bn + world.e(i, j, k) * 256);
        int i1 = world.e(i, j, k);
        boolean flag = world.f(i, j, k, 0);
        if (block != null && flag)
            block.c(world, i, j, k, i1);
        return flag;
    }

    public void c(int i, int j, int k, int l) {
    }

    public void a() {
    }

    public void a(float f) {
    }

    public float b() {
        return 5.0F;
    }

    public boolean a(gs entityplayer, fd world, iz itemstack) {
        int i = itemstack.a;
        iz itemstack1 = itemstack.a(world, entityplayer);
        if (itemstack1 != itemstack || (itemstack1 != null && itemstack1.a != i)) {
            entityplayer.c.a[entityplayer.c.c] = itemstack1;
            if (itemstack1.a == 0)
                entityplayer.c.a[entityplayer.c.c] = null;
            return true;
        }
        return false;
    }

    public void a(gs entityplayer) {
    }

    public void c() {
    }

    public boolean d() {
        return true;
    }

    public void b(gs entityplayer) {
    }

    public boolean a(gs entityplayer, fd world, iz itemstack, int i, int j, int k, int l) {
        int i1 = world.a(i, j, k);
        if (i1 > 0 && Tile.m[i1].a(world, i, j, k, entityplayer))
            return true;
        if (itemstack == null)
            return false;
        return itemstack.a(entityplayer, world, i, j, k, l);
    }

    public gs b(fd world) {
        return (gs) new dc(this.a, world, this.a.k, world.t.g);
    }

    public void a(gs entityplayer, sn entity) {
        entityplayer.c(entity);
    }

    public void b(gs entityplayer, sn entity) {
        entityplayer.d(entity);
    }

    public iz a(int i, int j, int k, boolean flag, gs entityplayer) {
        return entityplayer.e.a(j, k, flag, entityplayer);
    }

    public void a(int i, gs entityplayer) {
        entityplayer.e.a(entityplayer);
        entityplayer.e = entityplayer.d;
    }
}
