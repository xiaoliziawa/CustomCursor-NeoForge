package fr.atesab.customcursormod.fabric;

import com.mojang.blaze3d.vertex.PoseStack;
import fr.atesab.customcursormod.common.handler.BasicHandler;
import fr.atesab.customcursormod.common.handler.CommonMatrixStack;

public class FabricCommonMatrixStack extends BasicHandler<PoseStack> implements CommonMatrixStack {
	public FabricCommonMatrixStack(PoseStack handle) {
		super(handle);
	}

	@Override
	public void scale(float x, float y, float z) {
		handle.scale(x, y, z);
	}

	@Override
	public void setIdentity() {
		handle.setIdentity();
	}

	@Override
	public void translate(float x, float y, float z) {
		handle.translate(x, y, z);
	}
}
