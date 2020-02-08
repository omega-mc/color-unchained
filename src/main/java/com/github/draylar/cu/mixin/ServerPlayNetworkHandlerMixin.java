package com.github.draylar.cu.mixin;

import net.minecraft.network.play.ServerPlayNetHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

/*
  Utility feature where players can type %% to get the same effect as §.
 */
@Mixin(ServerPlayNetHandler.class)
public class ServerPlayNetworkHandlerMixin {

    @ModifyVariable(at = @At(
            value = "INVOKE",
            target = "Ljava/lang/String;startsWith(Ljava/lang/String;)Z",
            shift = At.Shift.BEFORE
    ), method = "processChatMessage", ordinal = 0)
    private String processChatMessage(String s) {
        return s.replace("%%", "§");
    }
}
