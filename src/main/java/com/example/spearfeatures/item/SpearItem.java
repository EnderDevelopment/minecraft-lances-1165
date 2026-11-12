package com.example.spearfeatures.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.vector.Vector3d;

import java.util.List;

public final class SpearItem extends Item {
    private static final int DASH_COOLDOWN_TICKS = 20;
    private final List<EffectInstance> effects;

    public SpearItem(Properties properties, List<EffectInstance> effects) {
        super(properties);
        this.effects = effects;
    }

    @Override
    public void inventoryTick(ItemStack stack, net.minecraft.world.World world, net.minecraft.entity.Entity entity, int slot, boolean selected) {
        if (!(entity instanceof PlayerEntity)) {
            return;
        }
        PlayerEntity player = (PlayerEntity) entity;
        if (!isHeld(player, stack)) {
            return;
        }
        for (EffectInstance effect : effects) {
            player.addPotionEffect(new EffectInstance(effect.getPotion(), 40, effect.getAmplifier(), effect.isAmbient(), effect.doesShowParticles(), effect.isShowIcon()));
        }
    }

    @Override
    public boolean onEntitySwing(ItemStack stack, LivingEntity entity) {
        if (entity instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) entity;
            if (!player.world.isRemote && !player.getCooldownTracker().hasCooldown(this)) {
                Vector3d look = player.getLookVec();
                Vector3d current = player.getMotion();
                player.setMotion(current.x + look.x * 1.2D, Math.max(current.y, 0.25D) + 0.35D, current.z + look.z * 1.2D);
                player.velocityChanged = true;
                player.getCooldownTracker().setCooldown(this, DASH_COOLDOWN_TICKS);
            }
        }
        return super.onEntitySwing(stack, entity);
    }

    private static boolean isHeld(PlayerEntity player, ItemStack stack) {
        return player.getHeldItemMainhand() == stack || player.getHeldItemOffhand() == stack;
    }
}
