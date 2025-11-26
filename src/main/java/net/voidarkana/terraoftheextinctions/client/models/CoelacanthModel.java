package net.voidarkana.terraoftheextinctions.client.models;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.voidarkana.terraoftheextinctions.client.animations.CoelacanthAnims;
import net.voidarkana.terraoftheextinctions.client.animations.GarAnims;
import net.voidarkana.terraoftheextinctions.common.entity.animals.Coelacanth;

public class CoelacanthModel<T extends Coelacanth> extends HierarchicalModel<T> {

	private final ModelPart coel;
	private final ModelPart swim_rot;
	private final ModelPart body;
	private final ModelPart boddy;
	private final ModelPart fin1;
	private final ModelPart fin2;
	private final ModelPart head;
	private final ModelPart ayes;
	private final ModelPart fin;
	private final ModelPart assfin;
	private final ModelPart backfin;
	private final ModelPart fin4;
	private final ModelPart fin3;
	private final ModelPart tail;
	private final ModelPart tailfin;

	public CoelacanthModel(ModelPart root) {
		this.coel = root.getChild("coel");
		this.swim_rot = this.coel.getChild("swim_rot");
		this.body = this.swim_rot.getChild("body");
		this.boddy = this.body.getChild("boddy");
		this.fin1 = this.boddy.getChild("fin1");
		this.fin2 = this.boddy.getChild("fin2");
		this.head = this.boddy.getChild("head");
		this.ayes = this.head.getChild("ayes");
		this.fin = this.boddy.getChild("fin");
		this.assfin = this.body.getChild("assfin");
		this.backfin = this.body.getChild("backfin");
		this.fin4 = this.body.getChild("fin4");
		this.fin3 = this.body.getChild("fin3");
		this.tail = this.body.getChild("tail");
		this.tailfin = this.tail.getChild("tailfin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition coel = partdefinition.addOrReplaceChild("coel", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, -2.0F));

		PartDefinition swim_rot = coel.addOrReplaceChild("swim_rot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, -3.0F));

		PartDefinition body = swim_rot.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 19).addBox(-2.5F, -2.0F, 0.0F, 5.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, 4.0F));

		PartDefinition boddy = body.addOrReplaceChild("boddy", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -2.0F, -10.0F, 5.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition fin1 = boddy.addOrReplaceChild("fin1", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, 5.0F, -7.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r1 = fin1.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(22, 43).addBox(1.0F, -2.0F, -1.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition fin2 = boddy.addOrReplaceChild("fin2", CubeListBuilder.create(), PartPose.offsetAndRotation(2.5F, 5.0F, -7.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r2 = fin2.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(22, 43).addBox(-1.0F, -2.0F, -1.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 2.0F, 1.0F, 0.0F, 0.0F, -0.0349F));

		PartDefinition head = boddy.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 38).addBox(1.5F, 1.0F, -5.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(44, 34).addBox(-1.5F, -1.0F, -6.0F, 3.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
				.texOffs(22, 38).addBox(-1.5F, 1.0F, -5.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.01F))
				.texOffs(30, 34).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -10.0F));

		PartDefinition ayes = head.addOrReplaceChild("ayes", CubeListBuilder.create().texOffs(2, 1).addBox(1.5F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.01F))
				.texOffs(26, 38).addBox(-1.5F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.01F)), PartPose.offset(0.0F, 0.5F, -3.5F));

		PartDefinition fin = boddy.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(12, 38).addBox(0.0F, -5.0F, -1.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, -3.0F));

		PartDefinition assfin = body.addOrReplaceChild("assfin", CubeListBuilder.create().texOffs(32, 43).addBox(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 10.0F));

		PartDefinition backfin = body.addOrReplaceChild("backfin", CubeListBuilder.create().texOffs(42, 43).addBox(0.0F, -5.0F, -1.0F, 0.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 9.0F));

		PartDefinition fin4 = body.addOrReplaceChild("fin4", CubeListBuilder.create(), PartPose.offsetAndRotation(2.5F, 6.0F, 1.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r3 = fin4.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 38).addBox(-1.0F, -2.0F, -1.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 2.0F, 1.0F, 0.0F, 0.0F, -0.0349F));

		PartDefinition fin3 = body.addOrReplaceChild("fin3", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.5F, 6.0F, 1.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r4 = fin3.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 38).addBox(1.0F, -2.0F, -1.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0349F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(30, 21).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 10.0F));

		PartDefinition tailfin = tail.addOrReplaceChild("tailfin", CubeListBuilder.create().texOffs(30, 0).addBox(0.0F, -7.0F, -2.0F, 0.0F, 12.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Coelacanth entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isInWaterOrBubble()){

			if (entity.isSprinting())
				this.animateWalk(CoelacanthAnims.SWIM, limbSwing, limbSwingAmount*4f, 1.5f, 3f);
			else
				this.animateWalk(CoelacanthAnims.FAST_SWIM, limbSwing, limbSwingAmount*4f, 1.5f, 3f);

			this.swim_rot.xRot = headPitch * ((float)Math.PI / 180F)/2;
			this.swim_rot.zRot = netHeadYaw * (((float)Math.PI / 180F)/4);

			this.animate(entity.attackAnimationState, CoelacanthAnims.ATTACK, ageInTicks, 1f);

		} else {
			this.swim_rot.resetPose();
			this.animate(entity.idleAnimationState, CoelacanthAnims.FLOP, ageInTicks, 1.0F);
		}

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		poseStack.pushPose();
		if (this.young){
			poseStack.scale(0.5f, 0.5f, 0.5f);
			poseStack.translate(0, 1.75, 0);
		}else {
			poseStack.translate(0, 0.25, 0);
		}
		coel.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();	}

	@Override
	public ModelPart root() {
		return coel;
	}
}