package com.github.draylar.cu;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class MixinConnector implements IMixinConnector {

    private static final Logger log = LogManager.getLogger();
    @Override
    public void connect() {
        log.info("Invoking Mixin Connector for Color Unchained");
        Mixins.addConfiguration(
                "assets/colorunchained/colorunchained.mixins.json"
        );
    }
}
