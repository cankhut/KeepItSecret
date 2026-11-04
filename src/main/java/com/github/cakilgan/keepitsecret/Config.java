package com.github.cakilgan.keepitsecret;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final ModConfigSpec SPEC;

    public static final ModConfigSpec.BooleanValue HIDE_PLAYER_NAMES;
    public static final ModConfigSpec.BooleanValue HIDE_MOB_NAMES;

    static {
        BUILDER.push("Keep It Secret Config");

        HIDE_PLAYER_NAMES = BUILDER
                .comment("Hide player nametags behind blocks. Used on servers without KeepItSecret, on servers with it the keepItSecretPlayers gamerule decides")
                .define("hidePlayerNames", true);

        HIDE_MOB_NAMES = BUILDER
                .comment("Hide mob nametags behind blocks. Used on servers without KeepItSecret, on servers with it the keepItSecretMobs gamerule decides")
                .define("hideMobNames", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
