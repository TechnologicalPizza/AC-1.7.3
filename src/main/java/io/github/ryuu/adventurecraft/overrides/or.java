package io.github.ryuu.adventurecraft.overrides;

import java.util.Random;

public class or extends Tile {
    protected or(int i, int j) {
        super(i, j, ln.v);
        b(true);
    }

    public void a(fd world, int i, int j, int k, Random random) {
    }

    public eq e(fd world, int i, int j, int k) {
        float f = 0.0625F;
        return eq.b((i + f), j, (k + f), ((i + 1) - f), ((j + 1) - f), ((k + 1) - f));
    }

    public eq f(fd world, int i, int j, int k) {
        float f = 0.0625F;
        return eq.b((i + f), j, (k + f), ((i + 1) - f), (j + 1), ((k + 1) - f));
    }

    public int a(int i) {
        if (i == 1)
            return this.bm - 1;
        if (i == 0)
            return this.bm + 1;
        return this.bm;
    }

    public boolean d() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public int b() {
        return 13;
    }

    public boolean a(fd world, int i, int j, int k) {
        if (!super.a(world, i, j, k))
            return false;
        return g(world, i, j, k);
    }

    public void b(fd world, int i, int j, int k, int l) {
        if (!g(world, i, j, k)) {
            g(world, i, j, k, world.e(i, j, k));
            world.f(i, j, k, 0);
        }
    }

    public boolean g(fd world, int i, int j, int k) {
        if (world.f(i - 1, j, k).a())
            return false;
        if (world.f(i + 1, j, k).a())
            return false;
        if (world.f(i, j, k - 1).a())
            return false;
        if (world.f(i, j, k + 1).a())
            return false;
        int l = world.a(i, j - 1, k);
        return (l == Tile.aW.bn || l == Tile.F.bn);
    }

    public void a(fd world, int i, int j, int k, sn entity) {
        entity.a((sn) null, 1);
    }
}
