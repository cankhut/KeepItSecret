package com.github.cakilgan.keepitsecret.net;

import com.github.cakilgan.keepitsecret.other.Reference;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record SyncNameRulesPayload(
        boolean hideMobs,
        boolean hidePlayers
) implements CustomPacketPayload {

    public static final Type<SyncNameRulesPayload> TYPE =
            new Type<>(ResourceLocation.fromNamespaceAndPath(Reference.MOD_ID, "sync_name_rules"));

    public static final StreamCodec<RegistryFriendlyByteBuf, SyncNameRulesPayload> CODEC =
            StreamCodec.of(
                    (buf, msg) -> {
                        buf.writeBoolean(msg.hideMobs);
                        buf.writeBoolean(msg.hidePlayers);
                    },
                    buf -> new SyncNameRulesPayload(
                            buf.readBoolean(),
                            buf.readBoolean()
                    )
            );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
