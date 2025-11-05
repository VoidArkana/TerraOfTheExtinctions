package net.voidarkana.terraoftheextinctions.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.terraoftheextinctions.client.animations.BleakAnims;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Bleak;

public class BleakModel<T extends Bleak> extends HierarchicalModel<T> {

	private final ModelPart root;
	private final ModelPart bleak;
	private final ModelPart eyes;
	private final ModelPart tail;
	private final ModelPart fin1;
	private final ModelPart fin2;
	private final ModelPart fin3;
	private final ModelPart fin4;

	public BleakModel(ModelPart root) {
		this.root = root.getChild("root");
		this.bleak = this.root.getChild("bleak");
		this.eyes = this.bleak.getChild("eyes");
		this.tail = this.bleak.getChild("tail");
		this.fin1 = this.bleak.getChild("fin1");
		this.fin2 = this.bleak.getChild("fin2");
		this.fin3 = this.bleak.getChild("fin3");
		this.fin4 = this.bleak.getChild("fin4");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 26.0F, 0.0F));

		PartDefinition bleak = root.addOrReplaceChild("bleak", CubeListBuilder.create().texOffs(6, 9).addBox(0.0F, -2.0F, 0.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-0.5F, -1.0F, -4.0F, 1.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(10, 9).addBox(0.0F, 1.0F, 1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));

		PartDefinition eyes = bleak.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(6, 14).addBox(0.5F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.01F))
				.texOffs(8, 14).addBox(-0.5F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.5F, -2.5F));

		PartDefinition tail = bleak.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 3.0F));

		PartDefinition fin1 = bleak.addOrReplaceChild("fin1", CubeListBuilder.create(), PartPose.offset(-0.5F, 0.5F, -2.0F));

		PartDefinition cube_r1 = fin1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 12).addBox(0.0F, -8.0F, 1.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 7.5F, -1.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition fin2 = bleak.addOrReplaceChild("fin2", CubeListBuilder.create(), PartPose.offset(0.5F, 0.5F, -2.0F));

		PartDefinition cube_r2 = fin2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(10, 12).addBox(0.0F, -8.0F, 1.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.4F, 7.5F, -1.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition fin3 = bleak.addOrReplaceChild("fin3", CubeListBuilder.create().texOffs(12, 12).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 1.0F, 0.0F));

		PartDefinition fin4 = bleak.addOrReplaceChild("fin4", CubeListBuilder.create().texOffs(6, 12).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(T pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (pEntity.isInWaterOrBubble()){
			this.animateWalk(BleakAnims.FAST_SWIM, pLimbSwing, pLimbSwingAmount, 2f, 3f);
			this.bleak.xRot = pHeadPitch * ((float)Math.PI / 180F);
			this.bleak.zRot = pNetHeadYaw * (((float)Math.PI / 180F)/2);
			this.animate(pEntity.idleAnimationState, BleakAnims.SWIM, pAgeInTicks, 0.25F);
		}else {
			this.bleak.resetPose();
			this.animate(pEntity.idleAnimationState, BleakAnims.FLOP, pAgeInTicks, 1F);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root;
	}
}