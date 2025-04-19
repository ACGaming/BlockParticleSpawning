package mod.acgaming.bps.config;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import mod.acgaming.bps.BlockParticleSpawning;
import mod.acgaming.bps.util.BPSUtil;

@Config(modid = BlockParticleSpawning.MOD_ID, name = "BlockParticleSpawning")
public class BPSConfig
{
    @Config.Name("Blocks")
    @Config.Comment("Example: minecraft:grass")
    public static String[] bpsBlocks = new String[] {"minecraft:grass"};

    @Config.Name("Particle Types")
    @Config.Comment({"Example: note", "Exclusive particles: underwater, crimsonSpore, warpedSpore"})
    public static String[] bpsParticleTypes = new String[] {"note"};

    @Config.Name("Particle Densities")
    @Config.Comment({"The amount of particles that are spawned", "Example: 5"})
    public static int[] bpsParticleDensities = new int[] {5};

    @Config.Name("Particle Rates")
    @Config.Comment({"The cooldown between each particle spawning", "Example: 10"})
    public static int[] bpsParticleRates = new int[] {10};

    @Config.Name("Particle Modes")
    @Config.Comment({"The way particles are spawned", "Valid values: BASIC, DRIP, FIRE_SMOKE, MYCELIUM, REDSTONE_ORE", "TORCH"})
    public static String[] bpsParticleModes = new String[] {"MYCELIUM"};

    @Config.Name("Particle Offset X")
    @Config.Comment({"The offset of the particle (x-axis)", "Example: 0.5"})
    public static double[] bpsParticleOffsetX = new double[] {0.5D};

    @Config.Name("Particle Offset Y")
    @Config.Comment({"The offset of the particle (y-axis)", "Example: 0.5"})
    public static double[] bpsParticleOffsetY = new double[] {0.5D};

    @Config.Name("Particle Offset Z")
    @Config.Comment({"The offset of the particle (z-axis)", "Example: 0.5"})
    public static double[] bpsParticleOffsetZ = new double[] {0.5D};

    @Config.Name("Particle Motion X")
    @Config.Comment({"The speed of the particle (x-axis), only supported by some particles", "Example: 0.0"})
    public static double[] bpsParticleMotionX = new double[] {0.0D};

    @Config.Name("Particle Motion Y")
    @Config.Comment({"The speed of the particle (y-axis), only supported by some particles", "Example: 0.0"})
    public static double[] bpsParticleMotionY = new double[] {0.0D};

    @Config.Name("Particle Motion Z")
    @Config.Comment({"The speed of the particle (z-axis), only supported by some particles", "Example: 0.0"})
    public static double[] bpsParticleMotionZ = new double[] {0.0D};

    @Config.Name("Particle Biomes")
    @Config.Comment({"The resource location of the biomes where particles can spawn, ANY for every biome", "Example: minecraft:plains"})
    public static String[] bpsParticleBiomes = new String[] {"minecraft:plains"};

    @Mod.EventBusSubscriber(modid = BlockParticleSpawning.MOD_ID)
    public static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(BlockParticleSpawning.MOD_ID)) ConfigManager.sync(BlockParticleSpawning.MOD_ID, Config.Type.INSTANCE);
            BPSUtil.initLists();
        }
    }
}