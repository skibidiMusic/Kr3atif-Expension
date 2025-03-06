package net.mcreator.kratifexpension.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGuiEvent;

public class GifVideoRenderer {
    private final ResourceLocation texture;
    private final int frameCount;
    private final int frameWidth;
    private final int frameHeight;
    private final int frameDelay; // Delay in milliseconds between frames

    private int currentFrame = 0;
    private long lastFrameTime = 0;

    public GifVideoRenderer(String texturePath, int frameCount, int frameWidth, int frameHeight, int fps) {
        this.texture = new ResourceLocation(texturePath);
        this.frameCount = frameCount;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameDelay = 1000 / fps;
    }

    public void onRenderGameOverlay(RenderGuiEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.screen != null) return;

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= frameDelay) {
            lastFrameTime = currentTime;
            currentFrame = (currentFrame + 1) % frameCount;
        }

        GuiGraphics guiGraphics = event.getGuiGraphics();
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();
        int x = 0; // Render from top-left corner
        int y = 0;

        int u = currentFrame * frameWidth;
        int v = 0;

        //RenderSystem.setShaderTexture(0, texture);
        guiGraphics.blit(texture, x, y, screenWidth, screenHeight, u, v, frameWidth, frameHeight, frameWidth * frameCount, frameHeight);
    }
}
