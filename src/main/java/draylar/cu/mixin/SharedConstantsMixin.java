package draylar.cu.mixin;

import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/*
  Similar to FormattingMixin; allows for the use of § in several areas.
 */
@Mixin(SharedConstants.class)
public class SharedConstantsMixin {

    @Inject(at = @At("HEAD"), method = "isValidChar", cancellable = true)
    private static void isValidChar(char char_1, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(true);
    }
}
