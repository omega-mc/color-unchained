package com.github.draylar.cu.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.widget.ButtonWidget;

import java.awt.*;

public class ColorToggleWidget extends ButtonWidget {

    private int x;
    private int y;

    int index = 0;

    private PressAction pressAction;

    public ColorToggleWidget(int x, int y, int width, int height, String text, PressAction pressAction) {
        super(x, y, width, height, text, pressAction);
        this.x = x;
        this.y = y;
        this.pressAction = pressAction;
    }

    @Override
    public void onClick(double double_1, double double_2) {
        super.onClick(double_1, double_2);
    }

    public void onPress() {
        this.pressAction.onPress(this);
    }

    @Override
    public void render(int mouseX, int mouseY, float float_1) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.getTextureManager().bindTexture(WIDGETS_LOCATION);

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        int int_3 = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);

        this.blit(this.x, this.y, 0, 46 + int_3 * 20, this.width / 2, this.height + 4);
        this.blit(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + int_3 * 20, this.width / 2, this.height + 4);


        double red = Math.sin(.03 * index + 0) * 127 + 128;
        double green = Math.sin(.03 * index + 2) * 127 + 128;
        double blue = Math.sin(.03 * index + 4) * 127 + 128;

        DrawableHelper.fill(x + 5, y + 5, x + 11, y + 14, new Color((int) red, (int) green, (int) blue).getRGB());

        index++;

        if(index >= 10000) {
            index = 0;
        }
    }
}
