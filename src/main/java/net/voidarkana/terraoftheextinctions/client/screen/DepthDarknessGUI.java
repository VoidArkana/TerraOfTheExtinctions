package net.voidarkana.terraoftheextinctions.client.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.voidarkana.terraoftheextinctions.TerraOfTheExtinctions;
import net.voidarkana.terraoftheextinctions.registry.TotEDamageTypes;
import org.joml.Matrix4f;

@OnlyIn(Dist.CLIENT)
public class DepthDarknessGUI{

    private static final ResourceLocation TEXTURE = new ResourceLocation(TerraOfTheExtinctions.MOD_ID, "textures/gui/darkness.png");

    int ticksUnderwater = 0;
    static int maxTicksUnderwater = 20;

    @SubscribeEvent
    public void preGuiRender(RenderGuiOverlayEvent.Pre event) {
        Minecraft minecraft = Minecraft.getInstance();
        LocalPlayer player = minecraft.player;
        if (canRender(player)) {
            PoseStack poseStack = event.getGuiGraphics().pose();

            poseStack.pushPose();
            RenderSystem.enableBlend();

            render(event.getGuiGraphics(), minecraft);

            RenderSystem.disableBlend();
            poseStack.popPose();
        }
    }

    public void render(GuiGraphics guiGraphics, Minecraft minecraft) {
        int screenWidth = minecraft.getWindow().getGuiScaledWidth();
        int screenHeight = minecraft.getWindow().getGuiScaledHeight();
        RenderSystem.enableBlend();

        this.ticksUnderwater = TerraOfTheExtinctions.PROXY.getTicksUnderwater();

        System.out.println(TerraOfTheExtinctions.PROXY.getTicksUnderwater());

//        this.drawTexturedQuadColor(guiGraphics, TEXTURE, 0, screenWidth, 0 ,screenHeight,
//                -90, 0, 16, 0, 16, 0, 0, 0,
//                (float) ticksUnderwater/TerraOfTheExtinctions.PROXY.getMaxTicksUnderwater());
        this.renderTextureOverlay(guiGraphics, TEXTURE, (float) ticksUnderwater/TerraOfTheExtinctions.PROXY.getMaxTicksUnderwater(), screenWidth, screenHeight);
    }


    boolean canRender(LocalPlayer player){
        if (player == null)
            return false;
        return TerraOfTheExtinctions.PROXY.getTicksUnderwater()>0;
    }

    protected void renderTextureOverlay(GuiGraphics pGuiGraphics, ResourceLocation pShaderLocation, float pAlpha, int screenWidth, int screenHeight) {
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, pAlpha);
        pGuiGraphics.blit(pShaderLocation, 0, 0, -90, 0.0F, 0.0F, screenWidth, screenHeight, screenWidth, screenHeight);
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        pGuiGraphics.setColor(1.0F, 1.0F, 1.0F, pAlpha);
    }

    public void drawTexturedQuadColor(GuiGraphics pGuiGraphics, ResourceLocation pAtlasLocation, float pX1, float pX2,
                                      float pY1, float pY2, float pBlitOffset, float pMinU, float pMaxU, float pMinV, float pMaxV, float pRed, float pGreen, float pBlue, float pAlpha) {
        RenderSystem.setShaderTexture(0, pAtlasLocation);
        RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
        RenderSystem.enableBlend();
        Matrix4f matrix4f = pGuiGraphics.pose().last().pose();
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR_TEX);
        bufferbuilder.vertex(matrix4f, pX1, pY1, pBlitOffset).color(pRed, pGreen, pBlue, pAlpha).uv(pMinU, pMinV).endVertex();
        bufferbuilder.vertex(matrix4f, pX1, pY2, pBlitOffset).color(pRed, pGreen, pBlue, pAlpha).uv(pMinU, pMaxV).endVertex();
        bufferbuilder.vertex(matrix4f, pX2, pY2, pBlitOffset).color(pRed, pGreen, pBlue, pAlpha).uv(pMaxU, pMaxV).endVertex();
        bufferbuilder.vertex(matrix4f, pX2, pY1, pBlitOffset).color(pRed, pGreen, pBlue, pAlpha).uv(pMaxU, pMinV).endVertex();
        BufferUploader.drawWithShader(bufferbuilder.end());
        RenderSystem.disableBlend();
    }

}
