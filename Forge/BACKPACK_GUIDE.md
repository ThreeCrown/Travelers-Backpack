# Traveler's Backpack Guide

This guide reflects the lighter, more vanilla-friendly backpack rules for the `1.20.1` branch.

## How progression works now

- **Dedicated storage tiers** are smaller and cleaner:
  - **Leather / Standard:** 5 inventory slots, 0 tool slots
  - **Iron:** 10 inventory slots, 2 tool slots
  - **Gold:** 15 inventory slots, 3 tool slots
  - **Diamond:** 20 inventory slots, 4 tool slots
  - **Netherite:** 27 inventory slots, 6 tool slots
- **Upgrade slots are gone.**
- **Backpack upgrade items are gone.**
- **Themed backpacks keep their crafting recipes** but are now cosmetic unless noted otherwise below.
- **Mob backpack spawning is disabled by default.**

## Ability scope legend

- **Equipped**: works while the backpack is worn
- **None**: cosmetic / recipe-only variant with no unique special ability

## General obtain notes

- If loot integration is enabled, the **Standard Traveler's Backpack** can still appear in:
  - Abandoned Mineshafts
  - Simple Dungeons
  - Desert Pyramids
- Special obtain-only variants are called out individually below.

## Core backpacks

| Backpack                          | How to obtain / craft                                                                                                                  | Ability                   | Scope    |
| --------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------- | ------------------------- | -------- |
| **Standard Traveler's Backpack**  | Craft with **4 Leather**, **2 String**, **1 Wooden Chest**, and **1 Sleeping Bag**                                                     | No unique special ability | None     |
| **Iron Traveler's Backpack**      | Craft from **Standard Backpack + 4 Iron Ingots** in the small-cross pattern                                                            | **+2 Armor** while worn   | Equipped |
| **Gold Traveler's Backpack**      | Craft from **Standard Backpack + 4 Gold Ingots** in the small-cross pattern                                                            | **+2 Armor** while worn   | Equipped |
| **Diamond Traveler's Backpack**   | Craft from **Standard Backpack + 4 Diamonds** in the small-cross pattern                                                               | **+3 Armor** while worn   | Equipped |
| **Netherite Traveler's Backpack** | Upgrade a **Diamond Traveler's Backpack** in a smithing table with a **Netherite Upgrade Smithing Template** and **1 Netherite Ingot** | **+4 Armor** while worn   | Equipped |
| **Emerald Traveler's Backpack**   | Craft from **Standard Backpack + 4 Emeralds** in the small-cross pattern                                                               | No unique special ability | None     |

## Material and structure backpacks

All of these keep their themed recipes but are cosmetic:

- **Lapis**
- **Redstone**
- **Coal**
- **Quartz**
- **Bookshelf**
- **End**
- **Nether**
- **Sandstone**
- **Snow**
- **Sponge**

## Food and farm backpacks

All of these keep their recipes but are cosmetic:

- **Cake**
- **Cactus**
- **Hay**
- **Melon**
- **Pumpkin**

## Hostile mob backpacks

All of these keep their recipes but are cosmetic:

- **Creeper**
- **Dragon**
- **Enderman**
- **Blaze**
- **Ghast**
- **Magma Cube**
- **Skeleton**
- **Spider**
- **Wither**
- **Warden**

## Passive / friendly mob backpacks

All of these keep their recipes or special obtain methods but are cosmetic:

- **Bat** - loot in **Abandoned Mineshafts**
- **Bee**
- **Wolf**
- **Fox**
- **Ocelot**
- **Horse**
- **Cow**
- **Pig**
- **Sheep**
- **Chicken**
- **Squid**
- **Villager** - sold by **level 3 Librarians** if villager trades are enabled
- **Iron Golem** - found in **Village Armorer** loot if loot injection is enabled

## Quick recipe reference

### Small-cross recipes

These place the themed ingredient above, below, left, and right of the **Standard Backpack**:

- Iron, Gold, Diamond, Emerald
- Enderman, Wolf, Fox, Ocelot

### Full-frame recipes

These use **8 matching ingredients around a Standard Backpack**:

- Redstone, Coal, Sponge, Hay

## Which backpacks still do something special?

Only the core metal backpacks keep special behavior:

- **Iron**: +2 Armor
- **Gold**: +2 Armor
- **Diamond**: +3 Armor
- **Netherite**: +4 Armor

Everything else is now a themed cosmetic backpack with its original recipe or obtain method.

---

If you update recipes, abilities, or loot hooks later, refresh this file from:

- `src/main/java/com/tiviacz/travelersbackpack/datagen/ModRecipeProvider.java`
- `src/main/java/com/tiviacz/travelersbackpack/common/BackpackAbilities.java`
- `src/main/java/com/tiviacz/travelersbackpack/handlers/NeoForgeEventHandler.java`
- `src/main/resources/assets/travelersbackpack/lang/en_us.json`
