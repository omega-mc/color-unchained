package com.github.draylar.cu.mixin;

import com.github.draylar.cu.client.gui.ColorButtonWidget;
import com.github.draylar.cu.client.gui.ColorToggleWidget;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.inventory.AnvilScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.util.text.ITextComponent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;

/*
  Mixin for adding the color picker to the book edit screen.
  Also allows for typing the special formatting character.
 */
@Mixin(AnvilScreen.class)
public abstract class AnvilScreenMixin extends Screen {

    @Shadow private TextFieldWidget nameField;
    boolean showColors = false;

    ColorToggleWidget colorButton = new ColorToggleWidget(2, 2, 16, 16, "Color", (widget) -> {
        toggleVisible();
    });

    private ArrayList<ColorButtonWidget> colors = new ArrayList<>();

    protected AnvilScreenMixin(ITextComponent text_1) {
        super(text_1);
    }

    private void toggleVisible() {
        showColors = !showColors;

        colors.forEach(color -> color.setVisiblity(showColors));
    }


    @Inject(at = @At("HEAD"), method = "init")
    private void init(CallbackInfo ci) {
        int index = 0;

        for(ChatFormatting color : ChatFormatting.values()) {
            index++;
            ColorButtonWidget red = new ColorButtonWidget(color,18 * index + 3, 2, 16, 16, color.name(), (widget) -> {
                nameField.setText("§" + color.func_225041_a());
                this.setFocused(nameField);
            });

            colors.add(red);
            addButton(red);
        }

        addButton(colorButton);
    }
}
