package com.rhynia.gtnh_no_zero.mixins;

import java.util.Arrays;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.rhynia.gtnh_no_zero.Config;

import gregtech.api.util.GT_RecipeBuilder;

/**
 * @author Rhynia
 */
@Mixin(value = GT_RecipeBuilder.class, remap = false)
public abstract class MixinRecipeBuilder {

    @Shadow
    protected int[] chances;

    @Unique
    private final int nz_timeLimit = Config.timeLimit * 20;

    @Unique
    private final boolean nz_enableFullChance = true;

    @ModifyReturnValue(method = "duration(I)Lgregtech/api/util/GT_RecipeBuilder;", at = @At("RETURN"))
    private GT_RecipeBuilder nz$ReduceTime(GT_RecipeBuilder original) {
        while (original.getDuration() > nz_timeLimit) {
            original.duration(original.getDuration() / 2);
        }
        return original;
    }

    @ModifyReturnValue(method = "duration(J)Lgregtech/api/util/GT_RecipeBuilder;", at = @At("RETURN"))
    private GT_RecipeBuilder nz$ReduceTimeL(GT_RecipeBuilder original) {
        while (original.getDuration() > nz_timeLimit) {
            original.duration(original.getDuration() / 2);
        }
        return original;
    }

    @ModifyReturnValue(method = "outputChances", at = @At("RETURN"))
    private GT_RecipeBuilder nz$NoChances(GT_RecipeBuilder original) {
        if (nz_enableFullChance) {
            int len = original.getChances().length;
            int[] full = new int[len];
            Arrays.fill(full, 10000);
            this.chances = full;
        }
        return original;
    }

}
