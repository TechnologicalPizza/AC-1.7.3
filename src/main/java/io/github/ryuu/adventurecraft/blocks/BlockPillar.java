package io.github.ryuu.adventurecraft.blocks;

import net.minecraft.level.Level;
import net.minecraft.tile.Tile;
import net.minecraft.tile.material.Material;

public class BlockPillar extends Tile implements IBlockColor {
    public BlockPillar(int i, int j) {
        super(i, j, Material.STONE);
    }

    public int a(int i, int j) {
        if (i == 1)
            return this.bm - 16 + j;
        if (i == 0)
            return this.bm + 16 + j;
        return this.bm + j;
    }

    public void incrementColor(Level world, int i, int j, int k) {
        int metadata = world.e(i, j, k);
        world.d(i, j, k, (metadata + 1) % subTypes[this.bn]);
    }
}
