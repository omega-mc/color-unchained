package com.github.draylar.cu.mixin;

import net.minecraft.util.SharedConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*
  Similar to FormattingMixin; allows for the use of § in several areas.
 */
@Mixin(SharedConstants.class)
public class SharedConstantsMixin {

    @Inject(at = @At("HEAD"), method = "isAllowedCharacter", cancellable = true)
    private static void isAllowedCharacter(char character, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
