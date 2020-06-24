package draylar.cu.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;

public class ColorButtonWidget extends ButtonWidget {

    private final int x;
    private final int y;
    private final Formatting formatting;

    public ColorButtonWidget(Formatting formatting, int x, int y, int width, int height, Text message, PressAction onPress) {
        super(x, y, width, height, message, onPress);

        this.x = x;
        this.y = y;
        this.formatting = formatting;
        this.visible = false;
    }

    @Override
    public void renderButton(MatrixStack matrices, int mouseX, int mouseY, float float_1) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.getTextureManager().bindTexture(WIDGETS_LOCATION);

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        int int_3 = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);

        this.drawTexture(matrices, this.x, this.y, 0, 46 + int_3 * 20, this.width / 2, this.height + 4);
        this.drawTexture(matrices, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + int_3 * 20, this.width / 2, this.height + 4);

        int int_4 = 14737632;
        if (!this.active) {
            int_4 = 10526880;
        } else if (this.isHovered()) {
            int_4 = 16777120;
        }

        if(formatting.getColorValue() != null) {
            DrawableHelper.fill(matrices,x + 5, y + 6, x + 11, y + 12, formatting.getColorValue() | 0xFF000000);
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
            this.drawCenteredString(matrices, MinecraftClient.getInstance().textRenderer, icon, this.x + this.width / 2, this.y + (this.height - 8) / 2, int_4 | MathHelper.ceil(this.alpha * 255.0F) << 24);
        }
    }

    public void setVisiblity(boolean visible) {
        this.visible = visible;
    }
}
