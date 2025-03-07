package io.github.ryuu.adventurecraft.rendering;

import io.github.ryuu.adventurecraft.entities.EntityNPC;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;

public class RenderNPC extends RenderBipedScaledScripted {
    public RenderNPC(BipedModel modelbase) {
        super(modelbase);
    }

    protected void a(LivingEntity entityliving, double d, double d1, double d2) {
        EntityNPC npc = (EntityNPC) entityliving;
        if (Minecraft.w()) {
            a(entityliving, String.format("%s - %d", new Object[]{npc.npcName, Integer.valueOf(npc.aD)}), d, d1, d2, 64);
        } else if (npc.seesThePlayer()) {
            a(entityliving, npc.npcName, d, d1, d2, 64);
        }
    }
}
