package com.rhynia.gtnh_no_zero.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import gregtech.api.util.GT_RecipeBuilder;

/**
 * @author Rhynia
 */
@Mixin(value = GT_RecipeBuilder.class, remap = false)
public abstract class MixinRecipeAdder {

    @ModifyReturnValue(method = "duration(I)Lgregtech/api/util/GT_RecipeBuilder;", at = @At("RETURN"))
    private GT_RecipeBuilder nz$ReduceTime(GT_RecipeBuilder original) {
        while (original.getDuration() > 300) {
            original.duration(original.getDuration() / 2);
        }
        return original;
    }

    @ModifyReturnValue(method = "duration(J)Lgregtech/api/util/GT_RecipeBuilder;", at = @At("RETURN"))
    private GT_RecipeBuilder nz$ReduceTimeL(GT_RecipeBuilder original) {
        while (original.getDuration() > 300) {
            original.duration(original.getDuration() / 2);
        }
        return original;
    }

}
