# Sleeping Bag Guide

This file documents how sleeping bags work in the `1.20.1` branch of Traveler's Backpack.

## What sleeping bags are

Sleeping bags are a separate item and block family in this mod, with **16 color variants**:

- White
- Orange
- Magenta
- Light Blue
- Yellow
- Lime
- Pink
- Gray
- Light Gray
- Cyan
- Purple
- Blue
- Brown
- Green
- Red
- Black

They serve two main roles:

1. **They are craftable placeable sleeping blocks**.
2. **They are part of the backpack system**, because the Standard Traveler's Backpack recipe requires one.

## Why they matter for backpacks

The **Standard Traveler's Backpack** recipe requires:

- 4 Leather
- 2 String
- 1 Wooden Chest
- 1 Sleeping Bag

Any item in the `travelersbackpack:sleeping_bags` tag can satisfy that recipe.

That means the crafted backpack stores the sleeping bag's color and uses it later for the backpack's sleeping bag visuals and deployment behavior.

## Supported sleeping bag inputs

The sleeping bag item tag includes:

- all 16 built-in Traveler's Backpack sleeping bags
- optional compatibility with `#comforts:sleeping_bags` when the Comforts mod is installed

So if Comforts is present, its sleeping bags can also count for backpack crafting.

## Crafting sleeping bags

### Base recipe

Each built-in sleeping bag is crafted from wool using the same one-row pattern:

- pattern: `##X`
- `#` = the target color wool
- `X` = white wool

Example:

- `white_sleeping_bag` uses 3 white wool
- `red_sleeping_bag` uses 2 red wool + 1 white wool
- `blue_sleeping_bag` uses 2 blue wool + 1 white wool

### Recoloring recipe

Sleeping bags can also be recolored shapelessly:

- 1 dye of the target color
- 1 sleeping bag of any other color

This lets you convert an existing sleeping bag without recrafting from wool.

## How sleeping bags behave when placed directly

Sleeping bags are implemented as a custom low-profile `BedBlock`.

### Placement rules

- They place as a **two-block bed-style structure** with foot and head parts.
- They face the direction the player is looking when placed.
- The second block must be replaceable, or placement fails.

### Shape and collision

- They are much flatter than a normal bed.
- The foot is basically a thin ground-level pad.
- The head has a small raised section.

### Falling on them

Sleeping bags soften falls a little and add a small bounce effect:

- fall damage is reduced to `90%` of normal before the bed-style bounce logic
- entities that are not suppressing bounce rebound upward slightly, but much less than before

So yes: they are nap pads with a little trampoline energy.

## Sleeping in a placed sleeping bag

Using a placed sleeping bag works similarly to a bed, with some custom behavior.

### If the dimension does not allow sleeping

If the current dimension cannot set spawn with beds:

- the sleeping bag removes itself
- the linked deployed state on the backpack is cleared if it came from a backpack

So in unsafe dimensions, it does **not** stay around as a permanent deployed bag.

### If the sleeping bag is occupied

- it attempts to kick out a sleeping villager
- if that fails, the player gets the standard occupied-bed message

### Spawn point behavior

Sleeping bags can optionally set respawn position, but this is **config-controlled**.

Relevant server config setting:

- `backpackSettings.enableSleepingBagSpawnPoint`

Current default in this branch:

- `false`

That means **sleeping bags do not set spawn by default** in the current simplified setup.

## Sleeping bags and backpacks

Sleeping bags are tightly integrated with backpacks in three ways:

1. **Backpack crafting input**
2. **Backpack appearance / stored color**
3. **Deploying or quick-using a sleeping bag from a backpack**

### Stored sleeping bag color

When a backpack is crafted, the recipe looks for the sleeping bag ingredient and stores its color in backpack data.

That stored color is then used for:

- the backpack model's sleeping bag visuals
- deployed sleeping bag color when the backpack places one into the world

If no sleeping bag color is found, fallback behavior defaults to **red**.

## Using a sleeping bag from a backpack

There are two separate usage modes.

### 1. Deployed from a placed backpack

If the backpack is placed as a block, the sleeping bag button toggles deployment.

Behavior:

- if no sleeping bag is currently deployed, the backpack tries to place one in front of itself
- if one is already deployed, the backpack removes it

Deployment requirements:

- both sleeping bag blocks must be placeable
- the first block's supporting block cannot be air
- the first block's supporting block cannot be a liquid block

If deployment succeeds:

- the sleeping bag uses the backpack's stored sleeping bag color
- the bag is marked as deployed in backpack block entity data

If deployment fails:

- the player gets the message: `Can't deploy the sleeping bag! Check the surrounding area.`

### 2. Quick sleeping from an equipped backpack

There is a config option for sleeping directly from a worn backpack without placing the backpack first.

Relevant server config setting:

- `backpackSettings.quickSleepingBag`

Current default:

- `true`

When used from an equipped backpack, the mod tries to:

- place a temporary two-block sleeping bag starting at the player's position and extending forward
- immediately put the player into sleep

Requirements for quick sleeping:

- player must be on the ground
- the supporting block below the first sleeping bag block cannot be air
- the supporting block below the first sleeping bag block cannot be liquid
- the dimension must allow sleeping (`BedBlock.canSetSpawn(level)`)
- both sleeping bag positions must be placeable

If sleeping fails after placement:

- the temporary sleeping bag blocks are cleaned up again

The quick-use path also triggers the sleeping bag advancement action.

## Changing a backpack's sleeping bag color

A placed backpack supports changing its sleeping bag color.

### How to do it

- hold **Shift**
- use a sleeping bag item on a **placed backpack**

What happens:

- the backpack updates its stored sleeping bag color
- the old sleeping bag color is dropped into the world as an item
- the new sleeping bag item in the player's hand is consumed by 1
- a leather armor equip sound is played

So swapping colors is effectively a one-for-one exchange on placed backpacks.

## Removal and drops

### Standalone sleeping bags

Normal sleeping bag blocks can drop themselves when broken.

### Backpack-deployed sleeping bags

Sleeping bags deployed by a backpack are placed with `CAN_DROP = false`.

That means they are meant to behave like an extension of the backpack, not a free duplicate item source.

When the backpack retracts the sleeping bag:

- both placed sleeping bag blocks are removed
- the backpack keeps its sleeping bag color data
- no separate sleeping bag item is dropped from that deployed structure

### Breaking a deployed sleeping bag manually

If the deployed sleeping bag is broken by the player:

- the connected backpack clears its `isSleepingBagDeployed` state

This keeps the backpack's state in sync with the world.

## Visual behavior

Backpacks visually track sleeping bag state and color.

The backpack model uses stored data for:

- `SLEEPING_BAG_DEPLOYED`
- `SLEEPING_BAG_COLOR`

So the backpack can visually reflect:

- whether a sleeping bag is currently deployed
- what color sleeping bag it is associated with

## Advancement support

There is a sleeping bag advancement in the mod.

Current English strings:

- title: `Getting Started`
- description: `Craft your first sleeping bag that is required for basic backpack recipe`

There are also action triggers for:

- changing a backpack's sleeping bag
- using a sleeping bag from a backpack

## Practical player summary

If you only care about how it works in play:

- craft any sleeping bag color you like
- use it in the Standard Backpack recipe to give that backpack a matching sleeping bag color
- while wearing a backpack, you can quick-sleep if the config allows it and the terrain is valid
- while the backpack is placed, you can deploy or retract its sleeping bag
- you can recolor a placed backpack's sleeping bag by shift-using another sleeping bag on it
- sleeping bags do **not** set spawn by default in this branch unless the config is changed

## Current defaults in this simplified branch

- `quickSleepingBag = true`
- `enableSleepingBagSpawnPoint = false`

So the intended current feel is:

- **easy to use for sleeping**
- **not a default respawn-point setter**

## Source references

If you update sleeping bag behavior later, refresh this file from:

- `src/main/java/com/tiviacz/travelersbackpack/blocks/SleepingBagBlock.java`
- `src/main/java/com/tiviacz/travelersbackpack/items/SleepingBagItem.java`
- `src/main/java/com/tiviacz/travelersbackpack/blockentity/BackpackBlockEntity.java`
- `src/main/java/com/tiviacz/travelersbackpack/common/ServerActions.java`
- `src/main/java/com/tiviacz/travelersbackpack/common/recipes/ShapedBackpackRecipe.java`
- `src/main/java/com/tiviacz/travelersbackpack/handlers/NeoForgeEventHandler.java`
- `src/main/java/com/tiviacz/travelersbackpack/datagen/ModRecipeProvider.java`
- `src/main/resources/data/travelersbackpack/tags/items/sleeping_bags.json`
- `src/main/resources/assets/travelersbackpack/lang/en_us.json`
