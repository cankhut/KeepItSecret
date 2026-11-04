package com.github.cakilgan.keepitsecret;

import com.github.cakilgan.keepitsecret.net.ClientRuleCache;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = KeepItSecret.MODID, dist = Dist.CLIENT)
public class KeepItSecretClient {

    public KeepItSecretClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
        NeoForge.EVENT_BUS.addListener(ClientPlayerNetworkEvent.LoggingOut.class, event -> ClientRuleCache.SERVER_RULES = false);
    }
}
