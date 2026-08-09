package com.github.cakilgan.keepitsecret.mixin;

import com.github.cakilgan.keepitsecret.Config;
import com.github.cakilgan.keepitsecret.net.ClientRuleCache;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Font.DisplayMode;
import net.minecraft.client.renderer.MultiBufferSource;
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
        if (entity instanceof Mob &&
                ClientRuleCache.HIDE_MOB_NAMES &&
                Config.HIDE_MOB_NAMES.getAsBoolean()) {
            return 0;
        }
        if (entity instanceof Player &&
                ClientRuleCache.HIDE_PLAYER_NAMES &&
                Config.HIDE_PLAYER_NAMES.getAsBoolean()) {
            return 0;
        }

        return original.call(font, text, x, y, color, shadow, matrix, buffers, mode, backgroundColor, light);
    }
}