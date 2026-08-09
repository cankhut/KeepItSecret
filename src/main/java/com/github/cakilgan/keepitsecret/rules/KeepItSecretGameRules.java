package com.github.cakilgan.keepitsecret.rules;

import com.github.cakilgan.keepitsecret.net.SyncNameRulesPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;


public class KeepItSecretGameRules {


    public static final GameRules.Key<GameRules.BooleanValue> PLAYERS =
            GameRules.register(
                    "keepItSecretPlayers",
                    GameRules.Category.PLAYER,
                    GameRules.BooleanValue.create(true, (server, value) -> {
                        boolean hide = server.getGameRules()
                                .getBoolean(KeepItSecretGameRules.MOBS);
                        boolean hide2 = server.getGameRules()
                                .getBoolean(KeepItSecretGameRules.PLAYERS);

                        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                            player.connection.send(
                                    new SyncNameRulesPayload(hide, hide2)
                            );
                        }
                    })
            );


    public static final GameRules.Key<GameRules.BooleanValue> MOBS =
            GameRules.register(
                    "keepItSecretMobs",
                    GameRules.Category.MOBS,
                    GameRules.BooleanValue.create(true, (server, value) -> {
                       boolean hide = server.getGameRules()
                               .getBoolean(KeepItSecretGameRules.MOBS);
                       boolean hide2 = server.getGameRules()
                               .getBoolean(KeepItSecretGameRules.PLAYERS);

                       for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                           player.connection.send(
                                   new SyncNameRulesPayload(hide, hide2)
                           );
                       }
                    })
            );

    public static void init(){
    }
}
