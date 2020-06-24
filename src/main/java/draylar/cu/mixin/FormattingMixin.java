package draylar.cu.mixin;

import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.regex.Pattern;

/*
  Mixin that allows custom color formatting in several different areas.
 */
@Mixin(Formatting.class)
public class FormattingMixin {

    @Shadow @Final @Mutable
    private static Pattern FORMATTING_CODE_PATTERN;

    static {
        FORMATTING_CODE_PATTERN = Pattern.compile("");
    }
}
