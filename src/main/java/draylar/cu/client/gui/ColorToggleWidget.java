package draylar.cu.client.gui;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;

import java.awt.*;

public class ColorToggleWidget extends ButtonWidget {

    private final int x;
    private final int y;

    int index = 0;

    private final PressAction pressAction;

    public ColorToggleWidget(int x, int y, int width, int height, Text text, PressAction pressAction) {
        super(x, y, width, height, text, pressAction);

        this.x = x;
        this.y = y;
        this.pressAction = pressAction;
    }

    @Override
    public void onClick(double x, double y) {
        super.onClick(x, y);
    }

    public void onPress() {
        this.pressAction.onPress(this);
    }

    @Override
    public void render(MatrixStack stack, int mouseX, int mouseY, float float_1) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.getTextureManager().bindTexture(WIDGETS_LOCATION);

        RenderSystem.color4f(1.0F, 1.0F, 1.0F, this.alpha);
        int int_3 = this.getYImage(this.isHovered());
        RenderSystem.enableBlend();
        RenderSystem.blendFuncSeparate(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SrcFactor.ONE, GlStateManager.DstFactor.ZERO);
        RenderSystem.blendFunc(GlStateManager.SrcFactor.SRC_ALPHA, GlStateManager.DstFactor.ONE_MINUS_SRC_ALPHA);

        this.drawTexture(stack, this.x, this.y, 0, 46 + int_3 * 20, this.width / 2, this.height + 4);
        this.drawTexture(stack, this.x + this.width / 2, this.y, 200 - this.width / 2, 46 + int_3 * 20, this.width / 2, this.height + 4);


        double red = Math.sin(.03 * index + 0) * 127 + 128;
        double green = Math.sin(.03 * index + 2) * 127 + 128;
        double blue = Math.sin(.03 * index + 4) * 127 + 128;

        DrawableHelper.fill(stack,x + 5, y + 5, x + 11, y + 14, new Color((int) red, (int) green, (int) blue).getRGB());

        index++;

        if(index >= 10000) {
            index = 0;
        }
    }
}
