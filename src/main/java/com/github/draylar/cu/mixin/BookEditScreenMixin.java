package com.github.draylar.cu.mixin;

import com.github.draylar.cu.client.gui.ColorButtonWidget;
import com.github.draylar.cu.client.gui.ColorToggleWidget;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.gui.screen.EditBookScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.button.ChangePageButton;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

/*
  Mixin for adding the color picker to the book edit screen.
  Also allows for typing the special formatting character.
 */
@Mixin(EditBookScreen.class)
public abstract class BookEditScreenMixin extends Screen {

    @Shadow protected abstract void insertTextIntoPage(String string_1);

    @Shadow public abstract boolean keyPressed(int int_1, int int_2, int int_3);

    @Shadow public abstract boolean charTyped(char char_1, int int_1);

    @Shadow private ChangePageButton buttonNextPage;

    @Inject(at = @At("HEAD"), method = "removeUnprintableChars", cancellable = true)
    private void removeUnprintableChars(String string_1, CallbackInfoReturnable<String> info) {
        StringBuilder stringBuilder_1 = new StringBuilder();
        char[] var3 = string_1.toCharArray();
        int var4 = var3.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            char char_1 = var3[var5];
            if (char_1 != 127) {
                stringBuilder_1.append(char_1);
            }
        }

        info.setReturnValue(stringBuilder_1.toString());
    }


    boolean showColors = false;

    ColorToggleWidget colorButton = new ColorToggleWidget(2, 2, 16, 16, "Color", (widget) -> {
        toggleVisible();
    });

    private ArrayList<ColorButtonWidget> colors = new ArrayList<>();

    protected BookEditScreenMixin(ITextComponent text_1) {
        super(text_1);
    }

    private void toggleVisible() {
        showColors = !showColors;

        colors.forEach(color -> color.setVisiblity(showColors));
    }

    @Inject(at = @At("HEAD"), method = "mouseClicked", cancellable = true)
    private void mouseClicked(double double_1, double double_2, int int_1, CallbackInfoReturnable<Boolean> cir) {
        buttons.forEach(button -> {
            if(button instanceof ColorToggleWidget || button instanceof ColorButtonWidget) {
                if (button.isHovered()) {
                    button.onClick(double_1, double_2);
                    cir.cancel();
                }
            }
        });
    }


    @Inject(at = @At("HEAD"), method = "init")
    private void init(CallbackInfo ci) {
        int x = 0;
        int y = 0;

        for(ChatFormatting color : ChatFormatting.values()) {
            ColorButtonWidget red = new ColorButtonWidget(color, 2 + x * 20, 2 + y * 20, 16, 16, color.func_225038_b(), (widget) -> {
                this.charTyped('\u00a7', 1);
                this.charTyped(color.func_225041_a(), 0);
            });

            if(y < 8) {
                y++;
            } else {
                x++;
                y = 0;
            }

            colors.add(red);
            addButton(red);
        }

        addButton(colorButton);
    }
}
