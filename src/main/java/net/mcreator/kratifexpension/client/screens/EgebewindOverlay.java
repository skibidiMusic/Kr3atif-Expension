package net.mcreator.kratifexpension.client.screens;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;    
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.mcreator.kratifexpension.init.KratifExpensionModMobEffects;
import net.mcreator.kratifexpension.procedures.EffectDisplayOverlayIngameProcedure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class EgebewindOverlay {

    private static final GifVideoRenderer gifRenderer = new GifVideoRenderer("kratif_expension:textures/screens/egeberushvignette.png", 20, 128, 128, 10); // Adjust the tickDelay as needed);

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void eventHandler(RenderGuiEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        Player entity = mc.player;
        if (entity != null && entity.hasEffect(KratifExpensionModMobEffects.EGEBE_RUSH.get())) {
            RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
            RenderSystem.setShaderColor(1.0F, 1F, 1F, 1.0F); // Adjust the RGB values to achieve the desired red tint
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
			gifRenderer.onRenderGameOverlay(event);
        }
    }
}
