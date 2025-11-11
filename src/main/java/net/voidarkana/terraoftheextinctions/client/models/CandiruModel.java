package net.voidarkana.terraoftheextinctions.client.models;// Made with Blockbench 5.0.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.terraoftheextinctions.client.animations.CandiruAnims;
import net.voidarkana.terraoftheextinctions.client.animations.PerchAnims;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Candiru;

public class CandiruModel<T extends Candiru> extends HierarchicalModel<T> {

	private final ModelPart root;
	private final ModelPart swim_rot;
	private final ModelPart body;
	private final ModelPart eyes;
	private final ModelPart whisk1;
	private final ModelPart whisk2;
	private final ModelPart fin1;
	private final ModelPart fin2;
	private final ModelPart fin3;
	private final ModelPart fin4;
	private final ModelPart tail;
	private final ModelPart tail_fin;
	private final ModelPart bone;

	public CandiruModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_rot = this.root.getChild("swim_rot");
		this.body = this.swim_rot.getChild("body");
		this.eyes = this.body.getChild("eyes");
		this.whisk1 = this.body.getChild("whisk1");
		this.whisk2 = this.body.getChild("whisk2");
		this.fin1 = this.body.getChild("fin1");
		this.fin2 = this.body.getChild("fin2");
		this.fin3 = this.body.getChild("fin3");
		this.fin4 = this.body.getChild("fin4");
		this.tail = this.body.getChild("tail");
		this.tail_fin = this.tail.getChild("tail_fin");
		this.bone = this.body.getChild("bone");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 23.5F, 0.0F));

		PartDefinition swim_rot = root.addOrReplaceChild("swim_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -1.5F, -2.0F));

		PartDefinition body = swim_rot.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(2, 11).addBox(0.0F, -1.5F, 1.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(6, 11).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.5F, 0.5F, -2.0F, 0.0F, 0.0F, -0.2356F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(6, 11).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.0F, 0.0F, 0.2356F));

		PartDefinition eyes = body.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(10, 10).addBox(0.5F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.01F))
		.texOffs(0, 11).addBox(-0.5F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.0F, -1.5F));

		PartDefinition whisk1 = body.addOrReplaceChild("whisk1", CubeListBuilder.create().texOffs(12, 4).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, -2.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition whisk2 = body.addOrReplaceChild("whisk2", CubeListBuilder.create().texOffs(12, 5).addBox(0.0F, -1.0F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.5F, -2.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition fin1 = body.addOrReplaceChild("fin1", CubeListBuilder.create().texOffs(12, 0).addBox(0.0F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.5F, 0.5F, 0.0F, 0.0F, -0.3927F));

		PartDefinition fin2 = body.addOrReplaceChild("fin2", CubeListBuilder.create().texOffs(12, 2).addBox(0.0F, 0.0F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.5F, 0.5F, 0.0F, 0.0F, 0.3927F));

		PartDefinition fin3 = body.addOrReplaceChild("fin3", CubeListBuilder.create().texOffs(6, 11).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.5F, 2.0F));

		PartDefinition fin4 = body.addOrReplaceChild("fin4", CubeListBuilder.create().texOffs(8, 11).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.5F, 2.0F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(10, 6).addBox(0.0F, 0.5F, 1.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(0, 6).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition tail_fin = tail.addOrReplaceChild("tail_fin", CubeListBuilder.create().texOffs(4, 11).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

		PartDefinition bone = body.addOrReplaceChild("bone", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 16, 16);
	}

	@Override
	public void setupAnim(Candiru pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (pEntity.isInWaterOrBubble()){
			if (pEntity.isSprinting())
				this.animateWalk(CandiruAnims.FAST_SWIM, pLimbSwing, pLimbSwingAmount, 1.25f, 3f);
			else
				this.animateWalk(CandiruAnims.SWIM, pLimbSwing, pLimbSwingAmount*2, 2f, 3f);
			this.swim_rot.xRot = pHeadPitch * ((float)Math.PI / 180F)/2;
			this.swim_rot.zRot = pNetHeadYaw * (((float)Math.PI / 180F)/4);

			this.animate(pEntity.attackAnimationState, CandiruAnims.ATTACK, pAgeInTicks, 1f);
			this.animate(pEntity.getIntoBodyAnimationState, CandiruAnims.GO_INTO_BODY, pAgeInTicks, 1f);
		}else {
			this.swim_rot.resetPose();
			this.animate(pEntity.idleAnimationState, CandiruAnims.FLOP, pAgeInTicks, 1F);
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