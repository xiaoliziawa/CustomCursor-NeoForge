package fr.atesab.customcursormod.common.handler;

import com.mojang.blaze3d.vertex.PoseStack;

public class CommonMatrixStack extends BasicHandler<PoseStack> {
	public CommonMatrixStack(PoseStack handle) {
		super(handle);
	}

	public void scale(float x, float y, float z) {
		handle.scale(x, y, z);
	}

	public void scale(float factor) {
		scale(factor, factor, factor);
	}

	public void scaleInv(float factor) {
		scale(1 / factor);
	}

	public void scaleInv(float x, float y, float z) {
		scale(1 / x, 1 / y, 1 / z);
	}

	public void setIdentity() {
		handle.setIdentity();
	}

	public void translate(float x, float y, float z) {
		handle.translate(x, y, z);
	}

	public void translateOposite(float x, float y, float z) {
		translate(-x, -y, -z);
	}
}
