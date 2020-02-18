package com.github.draylar.cu.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.TextFormat;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;

public class ColorButtonWidget extends ButtonWidget {

    private int x;
    private int y;
    private Formatting formatting;

    public ColorButtonWidget(TextFormat format, int x, int y, int width, int height, String text, PressAction pressAction) {
        super(x, y, width, height, text, pressAction);
        this.x = x;
        this.y = y;

        formatting = Formatting.byCode(format.getChar());
        visible = false;
    }

    @Override
    public void renderButton(int mouseX, int mouseY, float float_1) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.getTextureManager().bindTexture(WIDGETS_LOCATION);

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        int int_3 = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);

        this.blit(this.x, this.y, 0, 46 + int_3 * 20, this.width / 2, this.height + 4);
        this.blit(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + int_3 * 20, this.width / 2, this.height + 4);

        int int_4 = 14737632;
        if (!this.active) {
            int_4 = 10526880;
        } else if (this.isHovered()) {
            int_4 = 16777120;
        }

        if(formatting.getColorValue() != null) {
            DrawableHelper.fill(x + 5, y + 6, x + 11, y + 12, formatting.getColorValue() | 0xFF000000);
        } else {
            String icon = "";
            switch(formatting) {
                case UNDERLINE:
                    icon = "§nb";
                    break;
                case OBFUSCATED:
                    icon = "§kb";
                    break;
                case BOLD:
                    icon = "§lb";
                    break;
                case STRIKETHROUGH:
                    icon = "§mb";
                    break;
                case ITALIC:
                    icon = "§ob";
                    break;
                case RESET:
                    icon = "R";
            }
            this.drawCenteredString(MinecraftClient.getInstance().textRenderer, icon, this.x + this.width / 2, this.y + (this.height - 8) / 2, int_4 | MathHelper.ceil(this.alpha * 255.0F) << 24);
        }
    }

    public void setVisiblity(boolean visible) {
        this.visible = visible;
    }
}
