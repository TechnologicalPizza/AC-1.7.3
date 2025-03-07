package io.github.ryuu.adventurecraft.util;

import java.awt.image.BufferedImage;

import net.minecraft.client.Minecraft;

public class TextureAnimated {
    public byte[] imageData;

    public int x;

    public int y;

    public int width;

    public int height;

    public String texName;

    public int curFrame;

    public int numFrames;

    public int[] frameImages;

    public boolean hasImages;

    public TextureAnimated(String _texName, String animatedTex, int _x, int _y, int _width, int _height) {
        this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
        this.imageData = new byte[this.width * this.height * 4];
        this.texName = _texName;
        loadImage(animatedTex);
    }

    public void loadImage(String animatedTex) {
        this.hasImages = false;
        BufferedImage bufferedimage = null;
        if (Minecraft.minecraftInstance.f != null)
            bufferedimage = Minecraft.minecraftInstance.f.loadMapTexture(animatedTex);
        this.curFrame = 0;
        if (bufferedimage == null) {
            Minecraft.minecraftInstance.v.a(String.format("Unable to load texture '%s'", new Object[]{animatedTex}));
            return;
        }
        if (this.width != bufferedimage.getWidth()) {
            Minecraft.minecraftInstance.v.a(String.format("Animated texture width of %d didn't match the specified width of %d", new Object[]{Integer.valueOf(bufferedimage.getWidth()), Integer.valueOf(this.width)}));
            return;
        }
        if (0 != bufferedimage.getHeight() % this.height) {
            Minecraft.minecraftInstance.v.a(String.format("Animated texture height of %d isn't a multiple of the specified height of %d", new Object[]{Integer.valueOf(bufferedimage.getHeight()), Integer.valueOf(this.height)}));
            return;
        }
        this.numFrames = bufferedimage.getHeight() / this.height;
        this.frameImages = new int[bufferedimage.getWidth() * bufferedimage.getHeight()];
        bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), this.frameImages, 0, bufferedimage.getWidth());
        this.hasImages = true;
    }

    public void onTick() {
        if (this.hasImages) {
            int frameOffset = this.curFrame * this.width * this.height;
            int k = 0;
            for (int i = 0; i < this.height; i++) {
                for (int j = 0; j < this.width; j++) {
                    int curPixel = this.frameImages[j + i * this.width + frameOffset];
                    this.imageData[k + 0] = (byte) (curPixel >> 16 & 0xFF);
                    this.imageData[k + 1] = (byte) (curPixel >> 8 & 0xFF);
                    this.imageData[k + 2] = (byte) (curPixel & 0xFF);
                    this.imageData[k + 3] = (byte) (curPixel >> 24 & 0xFF);
                    k += 4;
                }
            }
            this.curFrame = (this.curFrame + 1) % this.numFrames;
        }
    }

    public String getTexture() {
        return this.texName;
    }
}
