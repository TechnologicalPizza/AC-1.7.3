package io.github.ryuu.adventurecraft.overrides;

import java.util.Random;

public class bk extends nr {
    private final int c;

    int[] a;

    protected bk(int i, int j) {
        super(i, j, ln.i, false);
        this.c = j;
        b(true);
    }

    public int b(int i) {
        if ((i & 0x1) == 1)
            return jh.a();
        if ((i & 0x2) == 2)
            return jh.b();
        return jh.c();
    }

    public int b(xp iblockaccess, int i, int j, int k) {
        int l = iblockaccess.e(i, j, k);
        if ((l & 0x1) == 1)
            return jh.a();
        if ((l & 0x2) == 2)
            return jh.b();
        iblockaccess.a().a(i, k, 1, 1);
        double d = (iblockaccess.a()).a[0];
        double d1 = (iblockaccess.a()).b[0];
        return jh.a(d, d1);
    }

    public void b(Level world, int i, int j, int k) {
        int l = 1;
        int i1 = l + 1;
        if (world.a(i - i1, j - i1, k - i1, i + i1, j + i1, k + i1))
            for (int j1 = -l; j1 <= l; j1++) {
                for (int k1 = -l; k1 <= l; k1++) {
                    for (int l1 = -l; l1 <= l; l1++) {
                        int i2 = world.a(i + j1, j + k1, k + l1);
                        if (i2 == Tile.L.bn) {
                            int j2 = world.e(i + j1, j + k1, k + l1);
                            world.e(i + j1, j + k1, k + l1, j2 | 0x8);
                        }
                    }
                }
            }
    }

    public void a(Level world, int i, int j, int k, Random random) {
    }

    private void h(Level world, int i, int j, int k) {
        g(world, i, j, k, world.e(i, j, k));
        world.f(i, j, k, 0);
    }

    public int a(Random random) {
        return (random.nextInt(20) != 0) ? 0 : 1;
    }

    public int a(int i, Random random) {
        return Tile.z.bn;
    }

    public void a(Level world, gs entityplayer, int i, int j, int k, int l) {
        if (!world.B && entityplayer.G() != null && (entityplayer.G()).c == ItemType.bc.bf) {
            entityplayer.a(jl.C[this.bn], 1);
            a(world, i, j, k, new iz(Tile.L.bn, 1, l & 0x3));
        } else {
            super.a(world, entityplayer, i, j, k, l);
        }
    }

    protected int b_(int i) {
        return i & 0x3;
    }

    public boolean c() {
        return !this.b;
    }

    public int a(int i, int j) {
        if ((j & 0x3) == 1)
            return this.bm + 80;
        return this.bm;
    }

    public void a(boolean flag) {
        this.b = flag;
        this.bm = this.c + (flag ? 0 : 1);
    }

    public void b(Level world, int i, int j, int k, sn entity) {
        super.b(world, i, j, k, entity);
    }
}
