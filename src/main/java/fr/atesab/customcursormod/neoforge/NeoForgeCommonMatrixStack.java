package fr.atesab.customcursormod.neoforge;

import fr.atesab.customcursormod.common.handler.CommonMatrixStack;

import com.mojang.blaze3d.vertex.PoseStack;

import fr.atesab.customcursormod.common.handler.BasicHandler;

public class NeoForgeCommonMatrixStack extends BasicHandler<PoseStack> implements CommonMatrixStack {
	public NeoForgeCommonMatrixStack(PoseStack handle) {
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
