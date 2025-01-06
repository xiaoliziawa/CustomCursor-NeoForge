package fr.atesab.customcursormod.neoforge;

import fr.atesab.customcursormod.common.handler.CommonShader;
import fr.atesab.customcursormod.common.handler.CommonShaders;
import net.minecraft.client.renderer.GameRenderer;

public class NeoForgeCommonShaders extends CommonShaders {

    private NeoForgeCommonShaders() {
    }

    private static final NeoForgeCommonShaders instance = new NeoForgeCommonShaders();

    /**
     * @return the instance
     */
    public static NeoForgeCommonShaders getForge() {
        return instance;
    }

    @Override
    public CommonShader getPositionShader() {
        return new NeoForgeCommonShader(GameRenderer::getPositionShader);
    }

    @Override
    public CommonShader getPositionColorShader() {
        return new NeoForgeCommonShader(GameRenderer::getPositionColorShader);
    }

    @Override
    public CommonShader getPositionColorTexShader() {
        return new NeoForgeCommonShader(GameRenderer::getPositionTexColorShader);
    }

    @Override
    public CommonShader getPositionTexShader() {
        return new NeoForgeCommonShader(GameRenderer::getPositionTexShader);
    }

    @Override
    public CommonShader getPositionTexColorShader() {
        return new NeoForgeCommonShader(GameRenderer::getPositionTexColorShader);
    }

    @Override
    public CommonShader getBlockShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeSolidShader);
    }

    @Override
    public CommonShader getNewEntityShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntitySolidShader);
    }

    @Override
    public CommonShader getParticleShader() {
        return new NeoForgeCommonShader(GameRenderer::getParticleShader);
    }

    @Override
    public CommonShader getPositionColorLightmapShader() {
        return new NeoForgeCommonShader(GameRenderer::getPositionColorLightmapShader);
    }

    @Override
    public CommonShader getPositionColorTexLightmapShader() {
        return new NeoForgeCommonShader(GameRenderer::getPositionColorTexLightmapShader);
    }

    @Override
    public CommonShader getPositionTexColorNormalShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeCloudsShader);
    }

    @Override
    public CommonShader getPositionTexLightmapColorShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeTextShader);
    }

    @Override
    public CommonShader getRendertypeSolidShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeSolidShader);
    }

    @Override
    public CommonShader getRendertypeCutoutMippedShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeCutoutMippedShader);
    }

    @Override
    public CommonShader getRendertypeCutoutShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeCutoutShader);
    }

    @Override
    public CommonShader getRendertypeTranslucentShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeTranslucentShader);
    }

    @Override
    public CommonShader getRendertypeTranslucentMovingBlockShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeTranslucentMovingBlockShader);
    }

    @Override
    public CommonShader getRendertypeTranslucentNoCrumblingShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeCrumblingShader);
    }

    @Override
    public CommonShader getRendertypeArmorCutoutNoCullShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeArmorCutoutNoCullShader);
    }

    @Override
    public CommonShader getRendertypeEntitySolidShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntitySolidShader);
    }

    @Override
    public CommonShader getRendertypeEntityCutoutShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntityCutoutShader);
    }

    @Override
    public CommonShader getRendertypeEntityCutoutNoCullShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntityCutoutNoCullShader);
    }

    @Override
    public CommonShader getRendertypeEntityCutoutNoCullZOffsetShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntityCutoutNoCullZOffsetShader);
    }

    @Override
    public CommonShader getRendertypeItemEntityTranslucentCullShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeItemEntityTranslucentCullShader);
    }

    @Override
    public CommonShader getRendertypeEntityTranslucentCullShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntityTranslucentCullShader);
    }

    @Override
    public CommonShader getRendertypeEntityTranslucentShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntityTranslucentShader);
    }

    @Override
    public CommonShader getRendertypeEntitySmoothCutoutShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntitySmoothCutoutShader);
    }

    @Override
    public CommonShader getRendertypeBeaconBeamShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeBeaconBeamShader);
    }

    @Override
    public CommonShader getRendertypeEntityDecalShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntityDecalShader);
    }

    @Override
    public CommonShader getRendertypeEntityNoOutlineShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntityNoOutlineShader);
    }

    @Override
    public CommonShader getRendertypeEntityShadowShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntityShadowShader);
    }

    @Override
    public CommonShader getRendertypeEntityAlphaShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntityAlphaShader);
    }

    @Override
    public CommonShader getRendertypeEyesShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEyesShader);
    }

    @Override
    public CommonShader getRendertypeEnergySwirlShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEnergySwirlShader);
    }

    @Override
    public CommonShader getRendertypeLeashShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeLeashShader);
    }

    @Override
    public CommonShader getRendertypeWaterMaskShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeWaterMaskShader);
    }

    @Override
    public CommonShader getRendertypeOutlineShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeOutlineShader);
    }

    @Override
    public CommonShader getRendertypeArmorGlintShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeArmorGlintShader);
    }

    @Override
    public CommonShader getRendertypeArmorEntityGlintShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeArmorEntityGlintShader);
    }

    @Override
    public CommonShader getRendertypeGlintTranslucentShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeGlintTranslucentShader);
    }

    @Override
    public CommonShader getRendertypeGlintShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeGlintShader);
    }

    @Override
    public CommonShader getRendertypeGlintDirectShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeGlintDirectShader);
    }

    @Override
    public CommonShader getRendertypeEntityGlintShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntityGlintShader);
    }

    @Override
    public CommonShader getRendertypeEntityGlintDirectShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEntityGlintDirectShader);
    }

    @Override
    public CommonShader getRendertypeTextShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeTextShader);
    }

    @Override
    public CommonShader getRendertypeTextIntensityShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeTextIntensityShader);
    }

    @Override
    public CommonShader getRendertypeTextSeeThroughShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeTextSeeThroughShader);
    }

    @Override
    public CommonShader getRendertypeTextIntensitySeeThroughShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeTextIntensitySeeThroughShader);
    }

    @Override
    public CommonShader getRendertypeLightningShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeLightningShader);
    }

    @Override
    public CommonShader getRendertypeTripwireShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeTripwireShader);
    }

    @Override
    public CommonShader getRendertypeEndPortalShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEndPortalShader);
    }

    @Override
    public CommonShader getRendertypeEndGatewayShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeEndGatewayShader);
    }

    @Override
    public CommonShader getRendertypeLinesShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeLinesShader);
    }

    @Override
    public CommonShader getRendertypeCrumblingShader() {
        return new NeoForgeCommonShader(GameRenderer::getRendertypeCrumblingShader);
    }
}