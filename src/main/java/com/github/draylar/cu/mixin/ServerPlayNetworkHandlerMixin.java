package com.github.draylar.cu.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/*
  Utility feature where players can type %% to get the same effect as §.
 */
@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {

    @ModifyVariable(at = @At(
            value = "INVOKE",
            target = "Ljava/lang/String;startsWith(Ljava/lang/String;)Z",
            shift = At.Shift.BEFORE
    ), method = "onChatMessage", ordinal = 0)
    private String onChatMessage(String string_1) {
        return string_1.replace("%%", "§");
    }
}
