package com.github.cakilgan.keepitsecret.mixin;

import com.github.cakilgan.keepitsecret.Config;
import com.github.cakilgan.keepitsecret.net.ClientRuleCache;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Font.DisplayMode;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.awt.*;


@Mixin(EntityRenderer.class)
public abstract class NameTagRendering_Mixin<T extends Entity> {

    @WrapOperation(
            method = "renderNameTag",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/Font;drawInBatch(Lnet/minecraft/network/chat/Component;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/gui/Font$DisplayMode;II)I",
                    ordinal = 0
            )
    )
    private int keepitsecret$skipSeeThroughPass(
            Font font,
            Component text,
            float x,
            float y,
            int color,
            boolean shadow,
            Matrix4f matrix,
            MultiBufferSource buffers,
            DisplayMode mode,
            int backgroundColor,
            int light,
            Operation<Integer> original, @Local(argsOnly = true) T entity
    ) {
        //DEBUG
        //System.out.println(entity.getClass().getName());
        if (entity.isDiscrete()) {
            keepitsecret$drawBackground(font, text, x, y, matrix, buffers, backgroundColor);
            return original.call(font, text, x, y, color, shadow, matrix, buffers, mode, 0, light);
        }

        boolean hide = (entity instanceof Mob &&
                keepitsecret$enabled(ClientRuleCache.HIDE_MOB_NAMES, Config.HIDE_MOB_NAMES.getAsBoolean())) ||
                (entity instanceof Player &&
                keepitsecret$enabled(ClientRuleCache.HIDE_PLAYER_NAMES, Config.HIDE_PLAYER_NAMES.getAsBoolean()));

        if (hide) {
            keepitsecret$drawBackground(font, text, x, y, matrix, buffers, backgroundColor);
            return 0;
        }

        return original.call(font, text, x, y, color, shadow, matrix, buffers, mode, backgroundColor, light);
    }

    private static boolean keepitsecret$enabled(boolean serverRule, boolean clientSetting) {
        return ClientRuleCache.SERVER_RULES ? serverRule : clientSetting;
    }

    private static void keepitsecret$drawBackground(Font font, Component text, float x, float y, Matrix4f matrix, MultiBufferSource buffers, int backgroundColor) {
        if (backgroundColor == 0) {
            return;
        }

        float a = (backgroundColor >> 24 & 0xFF) / 255.0F;
        float r = (backgroundColor >> 16 & 0xFF) / 255.0F;
        float g = (backgroundColor >> 8 & 0xFF) / 255.0F;
        float b = (backgroundColor & 0xFF) / 255.0F;

        float x0 = x - 1.0F;
        float x1 = x + font.width(text) + 1.0F;
        float y0 = y - 1.0F;
        float y1 = y + 9.0F;

        VertexConsumer consumer = buffers.getBuffer(RenderType.debugStructureQuads());
        consumer.addVertex(matrix, x0, y1, -0.01F).setColor(r, g, b, a);
        consumer.addVertex(matrix, x1, y1, -0.01F).setColor(r, g, b, a);
        consumer.addVertex(matrix, x1, y0, -0.01F).setColor(r, g, b, a);
        consumer.addVertex(matrix, x0, y0, -0.01F).setColor(r, g, b, a);
    }
}