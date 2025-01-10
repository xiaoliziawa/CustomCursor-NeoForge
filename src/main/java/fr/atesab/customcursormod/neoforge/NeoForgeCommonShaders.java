package fr.atesab.customcursormod.neoforge;

import fr.atesab.customcursormod.common.handler.CommonShader;
import fr.atesab.customcursormod.common.handler.CommonShaders;
import net.minecraft.client.renderer.CoreShaders;

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
        return new NeoForgeCommonShader(() -> CoreShaders.POSITION);
    }

    @Override
    public CommonShader getPositionColorShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.POSITION_COLOR);
    }

    @Override
    public CommonShader getPositionColorTexShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.POSITION_COLOR_TEX_LIGHTMAP);
    }

    @Override
    public CommonShader getPositionTexShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.POSITION_TEX);
    }

    @Override
    public CommonShader getPositionTexColorShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.POSITION_TEX_COLOR);
    }

    @Override
    public CommonShader getBlockShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_SOLID);
    }

    @Override
    public CommonShader getNewEntityShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_SOLID);
    }

    @Override
    public CommonShader getParticleShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.PARTICLE);
    }

    @Override
    public CommonShader getPositionColorLightmapShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.POSITION_COLOR_LIGHTMAP);
    }

    @Override
    public CommonShader getPositionColorTexLightmapShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.POSITION_COLOR_TEX_LIGHTMAP);
    }

    @Override
    public CommonShader getPositionTexColorNormalShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_CLOUDS);
    }

    @Override
    public CommonShader getPositionTexLightmapColorShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_TEXT);
    }

    @Override
    public CommonShader getRendertypeSolidShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_SOLID);
    }

    @Override
    public CommonShader getRendertypeCutoutMippedShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_CUTOUT_MIPPED);
    }

    @Override
    public CommonShader getRendertypeCutoutShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_CUTOUT);
    }

    @Override
    public CommonShader getRendertypeTranslucentShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_TRANSLUCENT);
    }

    @Override
    public CommonShader getRendertypeTranslucentMovingBlockShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_TRANSLUCENT_MOVING_BLOCK);
    }

    @Override
    public CommonShader getRendertypeTranslucentNoCrumblingShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_CRUMBLING);
    }

    @Override
    public CommonShader getRendertypeArmorCutoutNoCullShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ARMOR_CUTOUT_NO_CULL);
    }

    @Override
    public CommonShader getRendertypeEntitySolidShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_SOLID);
    }

    @Override
    public CommonShader getRendertypeEntityCutoutShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_CUTOUT);
    }

    @Override
    public CommonShader getRendertypeEntityCutoutNoCullShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_CUTOUT_NO_CULL);
    }

    @Override
    public CommonShader getRendertypeEntityCutoutNoCullZOffsetShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_CUTOUT_NO_CULL_Z_OFFSET);
    }

    @Override
    public CommonShader getRendertypeItemEntityTranslucentCullShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ITEM_ENTITY_TRANSLUCENT_CULL);
    }

    @Override
    public CommonShader getRendertypeEntityTranslucentCullShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_TRANSLUCENT);
    }

    @Override
    public CommonShader getRendertypeEntityTranslucentShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_TRANSLUCENT);
    }

    @Override
    public CommonShader getRendertypeEntitySmoothCutoutShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_SMOOTH_CUTOUT);
    }

    @Override
    public CommonShader getRendertypeBeaconBeamShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_BEACON_BEAM);
    }

    @Override
    public CommonShader getRendertypeEntityDecalShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_DECAL);
    }

    @Override
    public CommonShader getRendertypeEntityNoOutlineShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_NO_OUTLINE);
    }

    @Override
    public CommonShader getRendertypeEntityShadowShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_SHADOW);
    }

    @Override
    public CommonShader getRendertypeEntityAlphaShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_ALPHA);
    }

    @Override
    public CommonShader getRendertypeEyesShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_EYES);
    }

    @Override
    public CommonShader getRendertypeEnergySwirlShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENERGY_SWIRL);
    }

    @Override
    public CommonShader getRendertypeLeashShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_LEASH);
    }

    @Override
    public CommonShader getRendertypeWaterMaskShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_WATER_MASK);
    }

    @Override
    public CommonShader getRendertypeOutlineShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_OUTLINE);
    }

    @Override
    public CommonShader getRendertypeArmorGlintShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ARMOR_ENTITY_GLINT);
    }

    @Override
    public CommonShader getRendertypeArmorEntityGlintShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ARMOR_ENTITY_GLINT);
    }

    @Override
    public CommonShader getRendertypeGlintTranslucentShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_GLINT_TRANSLUCENT);
    }

    @Override
    public CommonShader getRendertypeGlintShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_GLINT);
    }

    @Override
    public CommonShader getRendertypeGlintDirectShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_GLINT);
    }

    @Override
    public CommonShader getRendertypeEntityGlintShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_GLINT);
    }

    @Override
    public CommonShader getRendertypeEntityGlintDirectShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_ENTITY_GLINT);
    }

    @Override
    public CommonShader getRendertypeTextShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_TEXT);
    }

    @Override
    public CommonShader getRendertypeTextIntensityShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_TEXT_INTENSITY);
    }

    @Override
    public CommonShader getRendertypeTextSeeThroughShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_TEXT_SEE_THROUGH);
    }

    @Override
    public CommonShader getRendertypeTextIntensitySeeThroughShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_TEXT_INTENSITY_SEE_THROUGH);
    }

    @Override
    public CommonShader getRendertypeLightningShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_LIGHTNING);
    }

    @Override
    public CommonShader getRendertypeTripwireShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_TRIPWIRE);
    }

    @Override
    public CommonShader getRendertypeEndPortalShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_END_PORTAL);
    }

    @Override
    public CommonShader getRendertypeEndGatewayShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_END_GATEWAY);
    }

    @Override
    public CommonShader getRendertypeLinesShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_LINES);
    }

    @Override
    public CommonShader getRendertypeCrumblingShader() {
        return new NeoForgeCommonShader(() -> CoreShaders.RENDERTYPE_CRUMBLING);
    }
}