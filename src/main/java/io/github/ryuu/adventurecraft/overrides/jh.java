package io.github.ryuu.adventurecraft.overrides;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;

public class jh {
    public static void a(int[] ai) {
        a = ai;
    }

    public static int a(double d, double d1) {
        d1 *= d;
        int i = (int) ((1.0D - d) * 255.0D);
        int j = (int) ((1.0D - d1) * 255.0D);
        return a[j << 8 | i];
    }

    public static int a() {
        return 6396257;
    }

    public static int b() {
        return 8431445;
    }

    public static int c() {
        return 4764952;
    }

    static void loadFoliage(String foliageName) {
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
