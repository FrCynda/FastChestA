package foundationgames.enhancedblockentities.config.gui.widget;

import net.minecraft.client.gui.Font;
//? if <= 1.21.11 {
/*import net.minecraft.client.gui.GuiGraphics;
*///?} else {
import net.minecraft.client.gui.GuiGraphicsExtractor;
//?}
import net.minecraft.client.gui.components.AbstractStringWidget;
//? if >= 1.21.11 {
import net.minecraft.client.gui.ActiveTextCollector;
//?}
import net.minecraft.network.chat.Component;

public class SectionTextWidget extends AbstractStringWidget {
    public SectionTextWidget(Component message, Font textRenderer) {
        this(0, 0, 200, 20, message, textRenderer);
    }

    public SectionTextWidget(int x, int y, int width, int height, Component message, Font textRenderer) {
        super(x, y, width, height, message, textRenderer);
        this.active = false;
    }

    //? if >= 1.21.11 {
    @Override
    public void visitLines(ActiveTextCollector collector) {
    }
    //?}

    @Override
    //? if <= 1.21.11 {
    /*public void renderWidget(GuiGraphics context, int mouseX, int mouseY, float delta) {
    *///?} else {
    public void extractWidgetRenderState(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
    //?}
        final int white = 0xFFFFFFFF;
        var font = this.getFont();
        var msg = this.getMessage();

        int l = this.getX();
        int w = this.getWidth();
        int r = l + w;
        int y = (this.getY() + this.getHeight()) - 6;

        int tx = l + (w / 2);
        int ty = y - (font.lineHeight / 2);
        int tw = font.width(msg);

        int ml = l + ((w - tw) / 2) - 5;
        int mr = ml + tw + 10;

        l += 1;
        r -= 1;

        context.fill(l, y, ml, y + 2, white);
        context.fill(mr, y, r, y + 2, white);

        //? if <= 1.21.11 {
        /*context.drawCenteredString(font, msg, tx, ty, 0xFFFFFFFF);
        *///?} else {
        context.centeredText(font, msg, tx, ty, 0xFFFFFFFF);
        //?}
    }
}
