package com.rhynia.gtnh_no_zero.mixins;

import java.util.ArrayList;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.github.technus.tectech.recipe.EyeOfHarmonyRecipe;
import com.rhynia.gtnh_no_zero.Config;

import pers.gwyog.gtneioreplugin.plugin.block.BlockDimensionDisplay;

/**
 * @author Rhynia
 */
@Mixin(value = EyeOfHarmonyRecipe.class, remap = false)
public abstract class MixinEyeOfHarmonyRecipe {

    @Shadow
    @Final
    @Mutable
    private long miningTimeSeconds;

    @Unique
    private final int nz_timeLimit = Config.timeLimit;

    @Inject(
        method = "<init>(Ljava/util/ArrayList;Lpers/gwyog/gtneioreplugin/plugin/block/BlockDimensionDisplay;DJJJJD)V",
        at = @At("TAIL"))
    private void nz$ReduceTimes(ArrayList materialList, BlockDimensionDisplay block, double recipeEnergyEfficiency,
        long hydrogenRequirement, long heliumRequirement, long miningTimeSeconds, long rocketTierOfRecipe,
        double baseSuccessChance, CallbackInfo ci) {
        while (this.miningTimeSeconds >= nz_timeLimit) {
            this.miningTimeSeconds /= 2;
        }
    }
}
