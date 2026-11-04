package com.github.cakilgan.keepitsecret.rules;

import com.github.cakilgan.keepitsecret.net.SyncNameRulesPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;


@EventBusSubscriber(modid = "keepitsecret")
public class KeepItSecretGameRules {


    public static final GameRules.Key<GameRules.BooleanValue> PLAYERS =
            GameRules.register(
                    "keepItSecretPlayers",
                    GameRules.Category.PLAYER,
                    GameRules.BooleanValue.create(true, (server, value) -> syncAll(server))
            );


    public static final GameRules.Key<GameRules.BooleanValue> MOBS =
            GameRules.register(
                    "keepItSecretMobs",
                    GameRules.Category.MOBS,
                    GameRules.BooleanValue.create(true, (server, value) -> syncAll(server))
            );

    @SubscribeEvent
    public static void onJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            sync(player);
        }
    }

    public static void syncAll(MinecraftServer server) {
        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            sync(player);
        }
    }

    public static void sync(ServerPlayer player) {
        if (player.connection.hasChannel(SyncNameRulesPayload.TYPE)) {
            GameRules rules = player.server.getGameRules();
            player.connection.send(
                    new SyncNameRulesPayload(rules.getBoolean(MOBS), rules.getBoolean(PLAYERS))
            );
        }
    }

    public static void init(){
    }
}
