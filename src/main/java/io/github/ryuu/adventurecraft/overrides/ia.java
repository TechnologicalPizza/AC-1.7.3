package io.github.ryuu.adventurecraft.overrides;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;

public class ia {
    public static void a(int[] ai) {
        a = ai;
    }

    public static int a(double d, double d1) {
        d1 *= d;
        int i = (int) ((1.0D - d) * 255.0D);
        int j = (int) ((1.0D - d1) * 255.0D);
        return a[j << 8 | i];
    }

    static void loadGrass(String foliageName) {
        BufferedImage bufferedimage = Minecraft.minecraftInstance.f.loadMapTexture(foliageName);
        if (bufferedimage == null)
            try {
                bufferedimage = ImageIO.read(jh.class.getResource(foliageName));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        if (bufferedimage != null)
            bufferedimage.getRGB(0, 0, 256, 256, a, 0, 256);
    }

    private static int[] a = new int[65536];
}
