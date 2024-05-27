## Custom Block Particles

###### Customize block particle effects with ease!

Custom Block Particles allows you to attach particle effects to any block in Minecraft, allowing modpack authors to enhance the visual aspects of blocks with many use cases.

Requires [**MixinBooter**](https://www.curseforge.com/minecraft/mc-mods/mixin-booter)

### Configuration Settings

The following five config settings enable fine-tuning of particle spawning.
The settings go by ordinal numbers, so the first block takes the first value of each category, the second block takes every second value and so on...

#### Blocks

Specifies which blocks will have particle effects.
**Example:** `minecraft:grass`

#### Particle Densities

Determines the number of particles that will be spawned.
**Example:** `5`

#### Particle Modes

Specifies the method by which particles are spawned. Valid values include `FIRE_SMOKE`, `MYCELIUM`, `REDSTONE_ORE`, `TORCH`.
**Example:** `MYCELIUM`

#### Particle Rates

Sets the cooldown time between each particle spawning.
**Example:** `10`

#### Particle Types

Defines the types of particles that will be spawned around the specified blocks.
**Example:** `minecraft:note`

### Configuration Example

```
# Example: minecraft:grass
S:Blocks <
    minecraft:grass
    minecraft:obsidian
 >

# The amount of particles that are spawned
# Example: 5
I:"Particle Densities" <
    10
    20
 >

# The way particles are spawned
# Valid values: FIRE_SMOKE, MYCELIUM, REDSTONE_ORE
# TORCH
S:"Particle Modes" <
    MYCELIUM
    FIRE_SMOKE
 >

# The cooldown between each particle spawning
# Example: 10
I:"Particle Rates" <
    5
    10
 >

# Example: minecraft:note
S:"Particle Types" <
    note
    spell
 >
```

This mod was commissioned for Minecraft 1.12.2