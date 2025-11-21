package net.voidarkana.terraoftheextinctions.client.models;// Made with Blockbench 5.0.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.terraoftheextinctions.client.animations.PerchAnims;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Perch;

public class PerchModel<T extends Perch> extends HierarchicalModel<T> {

	private final ModelPart root;
	private final ModelPart swim_rot;
	private final ModelPart perch;
	private final ModelPart body;
	private final ModelPart fin;
	private final ModelPart head;
	private final ModelPart lowerjaw;
	private final ModelPart eyes;
	private final ModelPart fin1;
	private final ModelPart fin2;
	private final ModelPart fin3;
	private final ModelPart fin4;
	private final ModelPart tail;
	private final ModelPart backfin;
	private final ModelPart assfin;
	private final ModelPart tailfin;

	public PerchModel(ModelPart root) {
		this.root = root.getChild("root");
		this.swim_rot = this.root.getChild("swim_rot");
		this.perch = this.swim_rot.getChild("perch");
		this.body = this.perch.getChild("body");
		this.fin = this.body.getChild("fin");
		this.head = this.body.getChild("head");
		this.lowerjaw = this.head.getChild("lowerjaw");
		this.eyes = this.head.getChild("eyes");
		this.fin1 = this.body.getChild("fin1");
		this.fin2 = this.body.getChild("fin2");
		this.fin3 = this.body.getChild("fin3");
		this.fin4 = this.body.getChild("fin4");
		this.tail = this.perch.getChild("tail");
		this.backfin = this.tail.getChild("backfin");
		this.assfin = this.tail.getChild("assfin");
		this.tailfin = this.tail.getChild("tailfin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition swim_rot = root.addOrReplaceChild("swim_rot", CubeListBuilder.create(), PartPose.offset(0.0F, -2.5F, -3.0F));

		PartDefinition perch = swim_rot.addOrReplaceChild("perch", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = perch.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -1.0F, -5.0F, 2.0F, 3.0F, 5.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -0.5F, 4.0F));

		PartDefinition fin = body.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(8, 8).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -4.0F));

		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 8).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, 1.0F, -5.0F));

		PartDefinition lowerjaw = head.addOrReplaceChild("lowerjaw", CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition eyes = head.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(8, 17).addBox(1.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.01F))
		.texOffs(10, 17).addBox(-1.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -0.5F, -0.5F));

		PartDefinition fin1 = body.addOrReplaceChild("fin1", CubeListBuilder.create().texOffs(14, 16).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 1.5F, -4.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition fin2 = body.addOrReplaceChild("fin2", CubeListBuilder.create().texOffs(4, 15).addBox(0.0F, -0.5F, 0.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 1.5F, -4.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition fin3 = body.addOrReplaceChild("fin3", CubeListBuilder.create().texOffs(0, 15).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 2.0F, -3.0F));

		PartDefinition fin4 = body.addOrReplaceChild("fin4", CubeListBuilder.create().texOffs(14, 13).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.0F, -3.0F));

		PartDefinition tail = perch.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(8, 13).addBox(0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, -0.5F, 4.0F));

		PartDefinition backfin = tail.addOrReplaceChild("backfin", CubeListBuilder.create().texOffs(14, 5).addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));

		PartDefinition assfin = tail.addOrReplaceChild("assfin", CubeListBuilder.create().texOffs(14, 9).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 1.0F));

		PartDefinition tailfin = tail.addOrReplaceChild("tailfin", CubeListBuilder.create().texOffs(14, 0).addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 3.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Perch pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (pEntity.isInWaterOrBubble()){
			if (pEntity.isSprinting())
				this.animateWalk(PerchAnims.SWIM_FAST, pLimbSwing, pLimbSwingAmount*4, 1.25f, 3f);
			else
				this.animateWalk(PerchAnims.SWIM, pLimbSwing, pLimbSwingAmount*4, 2f, 3f);
			this.swim_rot.xRot = pHeadPitch * ((float)Math.PI / 180F)/2;
			this.swim_rot.zRot = pNetHeadYaw * (((float)Math.PI / 180F)/4);

			this.animate(pEntity.attackAnimationState, PerchAnims.ATTACK, pAgeInTicks, 1f);
		}else {
			this.swim_rot.resetPose();
			this.animate(pEntity.idleAnimationState, PerchAnims.FLOP, pAgeInTicks, 1F);
		}

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();

		if (this.young){
			poseStack.scale(0.5f, 0.5f, 0.5f);
			poseStack.translate(0, 1.5, 0);
		}else {
			poseStack.translate(0, -0.05, 0);
		}

		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();
	}

	@Override
	public ModelPart root() {
		return root;
	}
}