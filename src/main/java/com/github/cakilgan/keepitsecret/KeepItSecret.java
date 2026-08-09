package com.github.cakilgan.keepitsecret;

import com.github.cakilgan.keepitsecret.rules.KeepItSecretGameRules;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(KeepItSecret.MODID)
public class KeepItSecret {
    static{
        KeepItSecretGameRules.init();
    }
    public static final String MODID = "keepitsecret";
    private static final Logger LOGGER = LogUtils.getLogger();
    public KeepItSecret(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
    }

}
