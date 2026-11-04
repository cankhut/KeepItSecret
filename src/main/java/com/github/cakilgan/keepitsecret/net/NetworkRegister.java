package com.github.cakilgan.keepitsecret.net;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = "keepitsecret")
public final class NetworkRegister {

    @SubscribeEvent
    public static void register(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1").optional();

        registrar.playToClient(
                SyncNameRulesPayload.TYPE,
                SyncNameRulesPayload.CODEC,
                NetworkRegister::handleSyncNameRules
        );
    }

    private static void handleSyncNameRules(SyncNameRulesPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ClientPayloadHandler.handle(payload);
        });
    }
}