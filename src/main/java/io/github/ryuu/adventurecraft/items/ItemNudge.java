package io.github.ryuu.adventurecraft.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.Player;
import net.minecraft.item.ItemInstance;
import net.minecraft.item.ItemType;
import net.minecraft.level.Level;
import net.minecraft.util.maths.Vec3f;

public class ItemNudge extends ItemType {
    public ItemNudge(int i) {
        super(i);
    }

    public ItemInstance a(ItemInstance itemstack, Level world, Player entityplayer) {
        if (ItemCursor.bothSet) {
            LivingEntity camera = Minecraft.minecraftInstance.i;
            Vec3f lookDir = camera.ac();
            int width = ItemCursor.maxX - ItemCursor.minX + 1;
            int height = ItemCursor.maxY - ItemCursor.minY + 1;
            int depth = ItemCursor.maxZ - ItemCursor.minZ + 1;
            int[] blocks = new int[width * height * depth];
            int[] meta = new int[width * height * depth];
            for (int i = 0; i < width; i++) {
                for (int k = 0; k < height; k++) {
                    for (int m = 0; m < depth; m++) {
                        int blockID = world.a(i + ItemCursor.minX, k + ItemCursor.minY, m + ItemCursor.minZ);
                        int metadata = world.e(i + ItemCursor.minX, k + ItemCursor.minY, m + ItemCursor.minZ);
                        blocks[depth * (height * i + k) + m] = blockID;
                        meta[depth * (height * i + k) + m] = metadata;
                        world.c(i + ItemCursor.minX, k + ItemCursor.minY, m + ItemCursor.minZ, 0);
                    }
                }
            }
            double absX = Math.abs(lookDir.a);
            double absY = Math.abs(lookDir.b);
            double absZ = Math.abs(lookDir.c);
            int xOffset = ItemCursor.minX;
            int yOffset = ItemCursor.minY;
            int zOffset = ItemCursor.minZ;
            if (absX > absY && absX > absZ) {
                if (lookDir.a < 0.0D) {
                    xOffset++;
                    ItemCursor.minX++;
                    ItemCursor.maxX++;
                    ItemCursor.oneX++;
                    ItemCursor.twoX++;
                } else {
                    xOffset--;
                    ItemCursor.minX--;
                    ItemCursor.maxX--;
                    ItemCursor.oneX--;
                    ItemCursor.twoX--;
                }
            } else if (absY > absZ) {
                if (lookDir.b < 0.0D) {
                    yOffset++;
                    ItemCursor.minY++;
                    ItemCursor.maxY++;
                    ItemCursor.oneY++;
                    ItemCursor.twoY++;
                } else {
                    yOffset--;
                    ItemCursor.minY--;
                    ItemCursor.maxY--;
                    ItemCursor.oneY--;
                    ItemCursor.twoY--;
                }
            } else if (lookDir.c < 0.0D) {
                zOffset++;
                ItemCursor.minZ++;
                ItemCursor.maxZ++;
                ItemCursor.oneZ++;
                ItemCursor.twoZ++;
            } else {
                zOffset--;
                ItemCursor.minZ--;
                ItemCursor.maxZ--;
                ItemCursor.oneZ--;
                ItemCursor.twoZ--;
            }
            int j;
            for (j = 0; j < width; j++) {
                for (int k = 0; k < height; k++) {
                    for (int m = 0; m < depth; m++) {
                        int blockID = blocks[depth * (height * j + k) + m];
                        int metadata = meta[depth * (height * j + k) + m];
                        world.a(xOffset + j, yOffset + k, zOffset + m, blockID, metadata);
                    }
                }
            }
            for (j = 0; j < width; j++) {
                for (int k = 0; k < height; k++) {
                    for (int m = 0; m < depth; m++) {
                        int blockID = blocks[depth * (height * j + k) + m];
                        int metadata = meta[depth * (height * j + k) + m];
                        world.a(xOffset + j, yOffset + k, zOffset + m, blockID, metadata);
                    }
                }
            }
            for (j = 0; j < width; j++) {
                for (int k = 0; k < height; k++) {
                    for (int m = 0; m < depth; m++) {
                        int blockID = blocks[depth * (height * j + k) + m];
                        world.g(xOffset + j, yOffset + k, zOffset + m, blockID);
                    }
                }
            }
        }
        return itemstack;
    }

    public void onItemLeftClick(ItemInstance itemstack, Level world, Player entityplayer) {
        if (ItemCursor.bothSet) {
            LivingEntity camera = Minecraft.minecraftInstance.i;
            Vec3f lookDir = camera.ac();
            int width = ItemCursor.maxX - ItemCursor.minX + 1;
            int height = ItemCursor.maxY - ItemCursor.minY + 1;
            int depth = ItemCursor.maxZ - ItemCursor.minZ + 1;
            int[] blocks = new int[width * height * depth];
            int[] meta = new int[width * height * depth];
            for (int i = 0; i < width; i++) {
                for (int k = 0; k < height; k++) {
                    for (int m = 0; m < depth; m++) {
                        int blockID = world.a(i + ItemCursor.minX, k + ItemCursor.minY, m + ItemCursor.minZ);
                        int metadata = world.e(i + ItemCursor.minX, k + ItemCursor.minY, m + ItemCursor.minZ);
                        blocks[depth * (height * i + k) + m] = blockID;
                        meta[depth * (height * i + k) + m] = metadata;
                        world.c(i + ItemCursor.minX, k + ItemCursor.minY, m + ItemCursor.minZ, 0);
                    }
                }
            }
            double absX = Math.abs(lookDir.a);
            double absY = Math.abs(lookDir.b);
            double absZ = Math.abs(lookDir.c);
            int xOffset = ItemCursor.minX;
            int yOffset = ItemCursor.minY;
            int zOffset = ItemCursor.minZ;
            if (absX > absY && absX > absZ) {
                if (lookDir.a > 0.0D) {
                    xOffset++;
                    ItemCursor.minX++;
                    ItemCursor.maxX++;
                    ItemCursor.oneX++;
                    ItemCursor.twoX++;
                } else {
                    xOffset--;
                    ItemCursor.minX--;
                    ItemCursor.maxX--;
                    ItemCursor.oneX--;
                    ItemCursor.twoX--;
                }
            } else if (absY > absZ) {
                if (lookDir.b > 0.0D) {
                    yOffset++;
                    ItemCursor.minY++;
                    ItemCursor.maxY++;
                    ItemCursor.oneY++;
                    ItemCursor.twoY++;
                } else {
                    yOffset--;
                    ItemCursor.minY--;
                    ItemCursor.maxY--;
                    ItemCursor.oneY--;
                    ItemCursor.twoY--;
                }
            } else if (lookDir.c > 0.0D) {
                zOffset++;
                ItemCursor.minZ++;
                ItemCursor.maxZ++;
                ItemCursor.oneZ++;
                ItemCursor.twoZ++;
            } else {
                zOffset--;
                ItemCursor.minZ--;
                ItemCursor.maxZ--;
                ItemCursor.oneZ--;
                ItemCursor.twoZ--;
            }
            int j;
            for (j = 0; j < width; j++) {
                for (int k = 0; k < height; k++) {
                    for (int m = 0; m < depth; m++) {
                        int blockID = blocks[depth * (height * j + k) + m];
                        int metadata = meta[depth * (height * j + k) + m];
                        world.a(xOffset + j, yOffset + k, zOffset + m, blockID, metadata);
                    }
                }
            }
            for (j = 0; j < width; j++) {
                for (int k = 0; k < height; k++) {
                    for (int m = 0; m < depth; m++) {
                        int blockID = blocks[depth * (height * j + k) + m];
                        world.g(xOffset + j, yOffset + k, zOffset + m, blockID);
                    }
                }
            }
        }
    }
}