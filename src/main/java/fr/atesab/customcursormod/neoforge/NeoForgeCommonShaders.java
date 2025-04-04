package fr.atesab.customcursormod.neoforge;

import fr.atesab.customcursormod.common.handler.CommonShader;
import fr.atesab.customcursormod.common.handler.CommonShaders;
import net.minecraft.client.renderer.RenderPipelines;

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
        return new NeoForgeCommonShader(() -> RenderPipelines.SOLID);
    }

    @Override
    public CommonShader getPositionColorShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.GUI);
    }

    @Override
    public CommonShader getPositionColorTexShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.GUI_TEXTURED);
    }

    @Override
    public CommonShader getPositionTexShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.GUI_TEXTURED);
    }

    @Override
    public CommonShader getPositionTexColorShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.GUI_TEXTURED);
    }

    @Override
    public CommonShader getBlockShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.SOLID);
    }

    @Override
    public CommonShader getNewEntityShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ENTITY_SOLID);
    }

    @Override
    public CommonShader getParticleShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.OPAQUE_PARTICLE);
    }

    @Override
    public CommonShader getPositionColorLightmapShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.LIGHTMAP);
    }

    @Override
    public CommonShader getPositionColorTexLightmapShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.TEXT);
    }

    @Override
    public CommonShader getPositionTexColorNormalShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.CLOUDS);
    }

    @Override
    public CommonShader getPositionTexLightmapColorShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.TEXT);
    }

    @Override
    public CommonShader getRendertypeSolidShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.SOLID);
    }

    @Override
    public CommonShader getRendertypeCutoutMippedShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.CUTOUT_MIPPED);
    }

    @Override
    public CommonShader getRendertypeCutoutShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.CUTOUT);
    }

    @Override
    public CommonShader getRendertypeTranslucentShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.TRANSLUCENT);
    }

    @Override
    public CommonShader getRendertypeTranslucentMovingBlockShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.TRANSLUCENT_MOVING_BLOCK);
    }

    @Override
    public CommonShader getRendertypeTranslucentNoCrumblingShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.CRUMBLING);
    }

    @Override
    public CommonShader getRendertypeArmorCutoutNoCullShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ARMOR_CUTOUT_NO_CULL);
    }

    @Override
    public CommonShader getRendertypeEntitySolidShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ENTITY_SOLID);
    }

    @Override
    public CommonShader getRendertypeEntityCutoutShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ENTITY_CUTOUT);
    }

    @Override
    public CommonShader getRendertypeEntityCutoutNoCullShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ENTITY_CUTOUT_NO_CULL);
    }

    @Override
    public CommonShader getRendertypeEntityCutoutNoCullZOffsetShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ENTITY_CUTOUT_NO_CULL_Z_OFFSET);
    }

    @Override
    public CommonShader getRendertypeItemEntityTranslucentCullShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ITEM_ENTITY_TRANSLUCENT_CULL);
    }

    @Override
    public CommonShader getRendertypeEntityTranslucentCullShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ENTITY_TRANSLUCENT);
    }

    @Override
    public CommonShader getRendertypeEntityTranslucentShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ENTITY_TRANSLUCENT);
    }

    @Override
    public CommonShader getRendertypeEntitySmoothCutoutShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ENTITY_SMOOTH_CUTOUT);
    }

    @Override
    public CommonShader getRendertypeBeaconBeamShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.BEACON_BEAM_OPAQUE);
    }

    @Override
    public CommonShader getRendertypeEntityDecalShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ENTITY_DECAL);
    }

    @Override
    public CommonShader getRendertypeEntityNoOutlineShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ENTITY_NO_OUTLINE);
    }

    @Override
    public CommonShader getRendertypeEntityShadowShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ENTITY_SHADOW);
    }

    @Override
    public CommonShader getRendertypeEntityAlphaShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.DRAGON_EXPLOSION_ALPHA);
    }

    @Override
    public CommonShader getRendertypeEyesShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.EYES);
    }

    @Override
    public CommonShader getRendertypeEnergySwirlShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.ENERGY_SWIRL);
    }

    @Override
    public CommonShader getRendertypeLeashShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.LEASH);
    }

    @Override
    public CommonShader getRendertypeWaterMaskShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.WATER_MASK);
    }

    @Override
    public CommonShader getRendertypeOutlineShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.OUTLINE_CULL);
    }

    @Override
    public CommonShader getRendertypeArmorGlintShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.GLINT);
    }

    @Override
    public CommonShader getRendertypeArmorEntityGlintShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.GLINT);
    }

    @Override
    public CommonShader getRendertypeGlintTranslucentShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.GLINT);
    }

    @Override
    public CommonShader getRendertypeGlintShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.GLINT);
    }

    @Override
    public CommonShader getRendertypeGlintDirectShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.GLINT);
    }

    @Override
    public CommonShader getRendertypeEntityGlintShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.GLINT);
    }

    @Override
    public CommonShader getRendertypeEntityGlintDirectShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.GLINT);
    }

    @Override
    public CommonShader getRendertypeTextShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.TEXT);
    }

    @Override
    public CommonShader getRendertypeTextIntensityShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.TEXT_INTENSITY);
    }

    @Override
    public CommonShader getRendertypeTextSeeThroughShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.TEXT_SEE_THROUGH);
    }

    @Override
    public CommonShader getRendertypeTextIntensitySeeThroughShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.TEXT_INTENSITY_SEE_THROUGH);
    }

    @Override
    public CommonShader getRendertypeLightningShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.LIGHTNING);
    }

    @Override
    public CommonShader getRendertypeTripwireShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.TRIPWIRE);
    }

    @Override
    public CommonShader getRendertypeEndPortalShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.END_PORTAL);
    }

    @Override
    public CommonShader getRendertypeEndGatewayShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.END_GATEWAY);
    }

    @Override
    public CommonShader getRendertypeLinesShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.LINES);
    }

    @Override
    public CommonShader getRendertypeCrumblingShader() {
        return new NeoForgeCommonShader(() -> RenderPipelines.CRUMBLING);
    }
}