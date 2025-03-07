package io.github.ryuu.adventurecraft.overrides;

import java.util.Random;

public class tc extends rw {
    private final Random a;

    private final boolean b;

    protected tc(int i, boolean flag) {
        super(i, ln.e);
        this.a = new Random();
        this.b = flag;
        this.bm = 45;
    }

    public int a(int i, Random random) {
        return Tile.aC.bn;
    }

    public void c(fd world, int i, int j, int k) {
        super.c(world, i, j, k);
        h(world, i, j, k);
    }

    private void h(fd world, int i, int j, int k) {
        if (world.B)
            return;
        int l = world.a(i, j, k - 1);
        int i1 = world.a(i, j, k + 1);
        int j1 = world.a(i - 1, j, k);
        int k1 = world.a(i + 1, j, k);
        byte byte0 = 3;
        if (Tile.o[l] && !Tile.o[i1])
            byte0 = 3;
        if (Tile.o[i1] && !Tile.o[l])
            byte0 = 2;
        if (Tile.o[j1] && !Tile.o[k1])
            byte0 = 5;
        if (Tile.o[k1] && !Tile.o[j1])
            byte0 = 4;
        world.d(i, j, k, byte0);
    }

    public int a(xp iblockaccess, int i, int j, int k, int l) {
        if (l == 1)
            return this.bm + 17;
        if (l == 0)
            return this.bm + 17;
        int i1 = iblockaccess.e(i, j, k);
        if (l != i1)
            return this.bm;
        if (this.b)
            return this.bm + 16;
        return this.bm - 1;
    }

    public void b(fd world, int i, int j, int k, Random random) {
        if (!this.b)
            return;
        int l = world.e(i, j, k);
        float f = i + 0.5F;
        float f1 = j + 0.0F + random.nextFloat() * 6.0F / 16.0F;
        float f2 = k + 0.5F;
        float f3 = 0.52F;
        float f4 = random.nextFloat() * 0.6F - 0.3F;
        if (l == 4) {
            world.a("smoke", (f - f3), f1, (f2 + f4), 0.0D, 0.0D, 0.0D);
            world.a("flame", (f - f3), f1, (f2 + f4), 0.0D, 0.0D, 0.0D);
        } else if (l == 5) {
            world.a("smoke", (f + f3), f1, (f2 + f4), 0.0D, 0.0D, 0.0D);
            world.a("flame", (f + f3), f1, (f2 + f4), 0.0D, 0.0D, 0.0D);
        } else if (l == 2) {
            world.a("smoke", (f + f4), f1, (f2 - f3), 0.0D, 0.0D, 0.0D);
            world.a("flame", (f + f4), f1, (f2 - f3), 0.0D, 0.0D, 0.0D);
        } else if (l == 3) {
            world.a("smoke", (f + f4), f1, (f2 + f3), 0.0D, 0.0D, 0.0D);
            world.a("flame", (f + f4), f1, (f2 + f3), 0.0D, 0.0D, 0.0D);
        }
    }

    public int a(int i) {
        if (i == 1)
            return this.bm + 17;
        if (i == 0)
            return this.bm + 17;
        if (i == 3)
            return this.bm - 1;
        return this.bm;
    }

    public boolean a(fd world, int i, int j, int k, gs entityplayer) {
        if (world.B)
            return true;
        sk tileentityfurnace = (sk) world.b(i, j, k);
        entityplayer.a(tileentityfurnace);
        return true;
    }

    public static void a(boolean flag, fd world, int i, int j, int k) {
        int l = world.e(i, j, k);
        ow tileentity = world.b(i, j, k);
        c = true;
        if (flag) {
            world.f(i, j, k, Tile.aD.bn);
        } else {
            world.f(i, j, k, Tile.aC.bn);
        }
        c = false;
        world.d(i, j, k, l);
        tileentity.j();
        world.a(i, j, k, tileentity);
        tileentity.j();
    }

    protected ow a_() {
        return (ow) new sk();
    }

    public void a(fd world, int i, int j, int k, ls entityliving) {
        int l = in.b((entityliving.aS * 4.0F / 360.0F) + 0.5D) & 0x3;
        if (l == 0)
            world.d(i, j, k, 2);
        if (l == 1)
            world.d(i, j, k, 5);
        if (l == 2)
            world.d(i, j, k, 3);
        if (l == 3)
            world.d(i, j, k, 4);
    }

    public void b(fd world, int i, int j, int k) {
        if (!c) {
            sk tileentityfurnace = (sk) world.b(i, j, k);
            for (int l = 0; l < tileentityfurnace.a(); l++) {
                iz itemstack = tileentityfurnace.f_(l);
                if (itemstack != null) {
                    float f = this.a.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.a.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.a.nextFloat() * 0.8F + 0.1F;
                    while (itemstack.a > 0) {
                        int i1 = this.a.nextInt(21) + 10;
                        if (i1 > itemstack.a)
                            i1 = itemstack.a;
                        itemstack.a -= i1;
                        hl entityitem = new hl(world, (i + f), (j + f1), (k + f2), new iz(itemstack.c, i1, itemstack.i()));
                        float f3 = 0.05F;
                        entityitem.aP = ((float) this.a.nextGaussian() * f3);
                        entityitem.aQ = ((float) this.a.nextGaussian() * f3 + 0.2F);
                        entityitem.aR = ((float) this.a.nextGaussian() * f3);
                        world.b((sn) entityitem);
                    }
                }
            }
        }
        super.b(world, i, j, k);
    }

    private static boolean c = false;
}
