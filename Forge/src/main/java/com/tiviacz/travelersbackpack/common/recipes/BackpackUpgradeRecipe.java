package com.tiviacz.travelersbackpack.common.recipes;

import com.google.gson.JsonObject;
import com.tiviacz.travelersbackpack.components.RenderInfo;
import com.tiviacz.travelersbackpack.init.ModDataHelper;
import com.tiviacz.travelersbackpack.init.ModRecipeSerializers;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.SmithingTransformRecipe;

public class BackpackUpgradeRecipe extends SmithingTransformRecipe {
    final Ingredient template;
    final Ingredient base;
    final Ingredient addition;
    final ItemStack result;

    public BackpackUpgradeRecipe(ResourceLocation id, Ingredient pTemplate, Ingredient pBase, Ingredient pAddition, ItemStack pResult) {
        super(id, pTemplate, pBase, pAddition, pResult);
        this.template = pTemplate;
        this.base = pBase;
        this.addition = pAddition;
        this.result = pResult;
    }

    @Override
    public ItemStack assemble(Container pInput, RegistryAccess pRegistryAccess) {
        return ItemStack.EMPTY;
    }

    public void upgradeInventory(ItemStack stack, Object nextTier) {
    }

    public RenderInfo getUpgradedTanksCapacity(ItemStack stack, int storageSlots) {
        return RenderInfo.EMPTY;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.BACKPACK_UPGRADE.get();
    }

    public static class Serializer implements RecipeSerializer<BackpackUpgradeRecipe> {
        public BackpackUpgradeRecipe fromJson(ResourceLocation p_266953_, JsonObject p_266720_) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_266720_, "template"));
            Ingredient ingredient1 = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_266720_, "base"));
            Ingredient ingredient2 = Ingredient.fromJson(GsonHelper.getAsJsonObject(p_266720_, "addition"));
            ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_266720_, "result"));
            return new BackpackUpgradeRecipe(p_266953_, ingredient, ingredient1, ingredient2, itemstack);
        }

        public BackpackUpgradeRecipe fromNetwork(ResourceLocation p_267117_, FriendlyByteBuf p_267316_) {
            Ingredient ingredient = Ingredient.fromNetwork(p_267316_);
            Ingredient ingredient1 = Ingredient.fromNetwork(p_267316_);
            Ingredient ingredient2 = Ingredient.fromNetwork(p_267316_);
            ItemStack itemstack = p_267316_.readItem();
            return new BackpackUpgradeRecipe(p_267117_, ingredient, ingredient1, ingredient2, itemstack);
        }

        public void toNetwork(FriendlyByteBuf p_266746_, BackpackUpgradeRecipe p_266927_) {
            p_266927_.template.toNetwork(p_266746_);
            p_266927_.base.toNetwork(p_266746_);
            p_266927_.addition.toNetwork(p_266746_);
            p_266746_.writeItem(p_266927_.result);
        }
    }
}