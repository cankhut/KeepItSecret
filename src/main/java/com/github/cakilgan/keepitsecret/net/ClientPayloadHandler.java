package com.github.cakilgan.keepitsecret.net;

public final class ClientPayloadHandler {

    public static void handle(SyncNameRulesPayload payload) {
        ClientRuleCache.HIDE_MOB_NAMES = payload.hideMobs();
        ClientRuleCache.HIDE_PLAYER_NAMES = payload.hidePlayers();
        ClientRuleCache.SERVER_RULES = true;
    }
}
