package com.patrick.betteremerald;

import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(betteremerald.MODID)
public class betteremerald {
    public static final String MODID = "betteremerald";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    // Register the Emerald Apple
    public static final RegistryObject<Item> EMERALD_APPLE = ITEMS.register("emerald_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible()
            .nutrition(8)
            .saturationModifier(1.5f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), 1.0f) // Stronger regeneration
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 1200, 1), 1.0f) // Longer absorption
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 600, 0), 1.0f) // Temporary damage boost
            .build())));

    // Register the Enchanted Emerald Apple
    public static final RegistryObject<Item> ENCHANTED_EMERALD_APPLE = ITEMS.register("enchanted_emerald_apple", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .alwaysEdible()
            .nutrition(12)
            .saturationModifier(2.5f)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 800, 2), 1.0f) // Very strong regeneration
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3), 1.0f) // Much longer and stronger absorption
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 1), 1.0f) // Movement speed boost
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1200, 0), 1.0f) // Fire resistance
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 1), 1.0f) // Stronger damage boost
            .build()))
    {
        @Override
        public boolean isFoil(ItemStack stack) {
            return true;
        }
    });

    //register the Empowered Emerald
    public static final RegistryObject<Item> EMPOWERED_EMERALD = ITEMS.register("empowered_emerald",
            () -> new Item(new Item.Properties()) {
                @Override
                public boolean isFoil(ItemStack stack) {
                    return true;
                }
            });

    // Create the Creative Tab
    public static final RegistryObject<CreativeModeTab> EMERALD_TAB = CREATIVE_MODE_TABS.register("emerald_tab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .title(Component.translatable("itemGroup.betteremerald.emerald_tab"))
            .icon(() -> EMERALD_APPLE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(EMERALD_APPLE.get());
                output.accept(ENCHANTED_EMERALD_APPLE.get());
                output.accept(EMPOWERED_EMERALD.get());
            }).build());

    public betteremerald() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the emerald items to the creative tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {

    }
}
