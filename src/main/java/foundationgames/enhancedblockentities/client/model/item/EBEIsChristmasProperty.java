package foundationgames.enhancedblockentities.client.model.item;

import com.mojang.serialization.MapCodec;
import foundationgames.enhancedblockentities.util.DateUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public record EBEIsChristmasProperty() implements ConditionalItemModelProperty {
    public static final MapCodec<EBEIsChristmasProperty> CODEC = MapCodec.unit(new EBEIsChristmasProperty());

    @Override
    public boolean get(ItemStack stack, @Nullable ClientLevel world, @Nullable LivingEntity user, int seed, ItemDisplayContext modelTransformationMode) {
        return DateUtil.isChristmas();
    }

    @Override
    public MapCodec<EBEIsChristmasProperty> type() {
        return CODEC;
    }
}