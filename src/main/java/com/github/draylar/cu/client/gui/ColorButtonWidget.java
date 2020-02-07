package com.github.draylar.cu.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;

public class ColorButtonWidget extends Button {

    private int x;
    private int y;
    private TextFormatting formatting;

    public ColorButtonWidget(ChatFormatting format, int x, int y, int width, int height, String text, Button.IPressable pressAction) {
        super(x, y, width, height, text, pressAction);
        this.x = x;
        this.y = y;

        formatting = TextFormatting.fromFormattingCode(format.func_225041_a());
        visible = false;
    }

    @Override
    public void renderButton(int mouseX, int mouseY, float float_1) {
        Minecraft client = Minecraft.getInstance();
        client.getTextureManager().bindTexture(WIDGETS_LOCATION);

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        int int_3 = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

        this.blit(this.x, this.y, 0, 46 + int_3 * 20, this.width / 2, this.height + 4);
        this.blit(this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + int_3 * 20, this.width / 2, this.height + 4);

        int int_4 = 14737632;
        if (!this.active) {
            int_4 = 10526880;
        } else if (this.isHovered()) {
            int_4 = 16777120;
        }

        if(formatting.getColor() != null) {
            AbstractGui.fill(x + 5, y + 6, x + 11, y + 12, formatting.getColor() | 0xFF000000);
        } else {
            String icon = "";
            switch(formatting) {
                case UNDERLINE:
                    icon = "\u00a7nb";
                    break;
                case OBFUSCATED:
                    icon = "\u00a7kb";
                    break;
                case BOLD:
                    icon = "\u00a7lb";
                    break;
                case STRIKETHROUGH:
                    icon = "\u00a7mb";
                    break;
                case ITALIC:
                    icon = "\u00a7ob";
                    break;
                case RESET:
                    icon = "R";
            }
            this.drawCenteredString(Minecraft.getInstance().fontRenderer, icon, this.x + this.width / 2, this.y + (this.height - 8) / 2, int_4 | MathHelper.ceil(this.alpha * 255.0F) << 24);
        }
    }

    public void setVisiblity(boolean visible) {
        this.visible = visible;
    }
}
