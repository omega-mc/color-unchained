package draylar.cu.mixin;

import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Formatting.class)
public interface FormattingAccessor {
    @Accessor
    char getCode();
}
