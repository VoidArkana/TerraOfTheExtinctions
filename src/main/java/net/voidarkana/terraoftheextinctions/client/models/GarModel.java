package net.voidarkana.terraoftheextinctions.client.models;// Made with Blockbench 5.0.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import net.minecraft.client.model.HierarchicalModel;
import net.voidarkana.terraoftheextinctions.client.animations.GarAnims;
import net.voidarkana.terraoftheextinctions.client.animations.PerchAnims;
import net.voidarkana.terraoftheextinctions.common.entity.animals.AlligatorGar;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class GarModel<T extends AlligatorGar> extends HierarchicalModel<T> {

	private final ModelPart gar;
	private final ModelPart swim_rot;
	private final ModelPart body;
	private final ModelPart front_body;
	private final ModelPart right_fin1;
	private final ModelPart left_fin1;
	private final ModelPart head;
	private final ModelPart upperjaw;
	private final ModelPart lowerjaw;
	private final ModelPart eyes;
	private final ModelPart back_body;
	private final ModelPart tail;
	private final ModelPart backfin;
	private final ModelPart tailfin;
	private final ModelPart bottomfin;
	private final ModelPart left_fin2;
	private final ModelPart right_fin2;

	public GarModel(ModelPart root) {
		this.gar = root.getChild("gar");
		this.swim_rot = this.gar.getChild("swim_rot");
		this.body = this.swim_rot.getChild("body");
		this.front_body = this.body.getChild("front_body");
		this.right_fin1 = this.front_body.getChild("right_fin1");
		this.left_fin1 = this.front_body.getChild("left_fin1");
		this.head = this.front_body.getChild("head");
		this.upperjaw = this.head.getChild("upperjaw");
		this.lowerjaw = this.head.getChild("lowerjaw");
		this.eyes = this.lowerjaw.getChild("eyes");
		this.back_body = this.body.getChild("back_body");
		this.tail = this.back_body.getChild("tail");
		this.backfin = this.tail.getChild("backfin");
		this.tailfin = this.tail.getChild("tailfin");
		this.bottomfin = this.tail.getChild("bottomfin");
		this.left_fin2 = this.back_body.getChild("left_fin2");
		this.right_fin2 = this.back_body.getChild("right_fin2");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition gar = partdefinition.addOrReplaceChild("gar", CubeListBuilder.create(), PartPose.offset(0.0F, 15.0F, 1.0F));

		PartDefinition swim_rot = gar.addOrReplaceChild("swim_rot", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -9.0F));

		PartDefinition body = swim_rot.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition front_body = body.addOrReplaceChild("front_body", CubeListBuilder.create().texOffs(0, 35).addBox(-4.0F, -5.0F, -16.0F, 8.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition right_fin1 = front_body.addOrReplaceChild("right_fin1", CubeListBuilder.create().texOffs(16, 72).addBox(0.0F, 0.0F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 4.0F, -12.5F, 0.0F, 0.0F, 0.2443F));

		PartDefinition left_fin1 = front_body.addOrReplaceChild("left_fin1", CubeListBuilder.create().texOffs(16, 72).addBox(0.0F, 0.0F, -1.5F, 0.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 4.0F, -12.5F, 0.0F, 0.0F, -0.2443F));

		PartDefinition head = front_body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 60).addBox(-4.0F, -2.5F, -6.0F, 8.0F, 6.0F, 6.0F, new CubeDeformation(0.001F)), PartPose.offset(0.0F, -0.5F, -16.0F));

		PartDefinition upperjaw = head.addOrReplaceChild("upperjaw", CubeListBuilder.create().texOffs(68, 22).addBox(-2.5F, 1.0F, -10.0F, 5.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(98, 13).addBox(-1.5F, 1.0F, -10.0F, 3.0F, 1.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(68, 11).addBox(-2.5F, 0.0F, -10.0F, 5.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -6.0F));

		PartDefinition lowerjaw = head.addOrReplaceChild("lowerjaw", CubeListBuilder.create().texOffs(73, 54).addBox(-1.5F, 0.0F, -9.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
		.texOffs(70, 45).addBox(-2.5F, 0.0F, -9.0F, 0.0F, 1.0F, 9.0F, new CubeDeformation(0.001F))
		.texOffs(68, 0).addBox(-2.5F, 1.0F, -9.0F, 5.0F, 2.0F, 9.0F, new CubeDeformation(0.01F))
		.texOffs(84, 54).addBox(1.5F, 0.0F, -9.0F, 0.0F, 1.0F, 1.0F, new CubeDeformation(-0.01F))
		.texOffs(70, 44).mirror().addBox(2.5F, 0.0F, -9.0F, 0.0F, 1.0F, 9.0F, new CubeDeformation(0.001F)).mirror(false), PartPose.offset(0.0F, 0.5F, -6.0F));

		PartDefinition eyes = lowerjaw.addOrReplaceChild("eyes", CubeListBuilder.create().texOffs(46, 60).addBox(-2.5F, 0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.002F))
		.texOffs(45, 60).addBox(2.5F, 0.5F, -0.5F, 0.0F, 1.0F, 1.0F, new CubeDeformation(0.002F)), PartPose.offset(0.0F, -0.5F, -0.5F));

		PartDefinition back_body = body.addOrReplaceChild("back_body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, 0.0F, 8.0F, 9.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 9.0F));

		PartDefinition tail = back_body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(48, 57).addBox(-2.0F, -3.0F, 0.0F, 4.0F, 6.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 26.0F));

		PartDefinition backfin = tail.addOrReplaceChild("backfin", CubeListBuilder.create().texOffs(28, 60).addBox(0.0F, -4.0F, -1.0F, 0.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -1.0F));

		PartDefinition tailfin = tail.addOrReplaceChild("tailfin", CubeListBuilder.create().texOffs(48, 35).addBox(0.0F, -4.0F, -2.0F, 0.0F, 11.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));

		PartDefinition bottomfin = tail.addOrReplaceChild("bottomfin", CubeListBuilder.create().texOffs(70, 32).addBox(0.0F, -1.0F, -1.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -4.0F));

		PartDefinition left_fin2 = back_body.addOrReplaceChild("left_fin2", CubeListBuilder.create().texOffs(0, 72).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 3.0F, 1.0F, 0.0F, 0.0436F, 0.0F));

		PartDefinition right_fin2 = back_body.addOrReplaceChild("right_fin2", CubeListBuilder.create().texOffs(0, 72).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 3.0F, 1.0F, 0.0F, -0.0436F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(AlligatorGar entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		if (entity.isInWaterOrBubble()){

			if (entity.isSprinting())
				this.animateWalk(GarAnims.SWIM, limbSwing, limbSwingAmount*4f, 1.5f, 3f);
			else
				this.animateWalk(GarAnims.FAST_SWIM, limbSwing, limbSwingAmount*4f, 1.5f, 3f);

			this.swim_rot.xRot = headPitch * ((float)Math.PI / 180F)/2;
			this.swim_rot.zRot = netHeadYaw * (((float)Math.PI / 180F)/4);

			this.animate(entity.attackAnimationState, GarAnims.ATTACK, ageInTicks, 1f);

		} else {
			this.swim_rot.resetPose();
			this.animate(entity.idleAnimationState, GarAnims.FLOP, ageInTicks, 1.0F);
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
			gar.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		poseStack.popPose();
	}

	@Override
	public ModelPart root() {
		return gar;
	}
}