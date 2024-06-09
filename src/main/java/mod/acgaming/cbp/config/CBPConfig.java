package mod.acgaming.cbp.config;

import mod.acgaming.cbp.CustomBlockParticles;
import mod.acgaming.cbp.util.CBPUtil;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = CustomBlockParticles.MOD_ID, name = CustomBlockParticles.NAME)
public class CBPConfig
{
    @Config.Name("Blocks")
    @Config.Comment("Example: minecraft:grass")
    public static String[] cbpBlocks = new String[] {"minecraft:grass"};

    @Config.Name("Particle Types")
    @Config.Comment({"Example: note", "Exclusive particles: underwater, crimsonSpore, warpedSpore"})
    public static String[] cbpParticleTypes = new String[] {"note"};

    @Config.Name("Particle Densities")
    @Config.Comment({"The amount of particles that are spawned", "Example: 5"})
    public static int[] cbpParticleDensities = new int[] {5};

    @Config.Name("Particle Rates")
    @Config.Comment({"The cooldown between each particle spawning", "Example: 10"})
    public static int[] cbpParticleRates = new int[] {10};

    @Config.Name("Particle Modes")
    @Config.Comment({"The way particles are spawned", "Valid values: BASIC, DRIP, FIRE_SMOKE, MYCELIUM, REDSTONE_ORE", "TORCH"})
    public static String[] cbpParticleModes = new String[] {"MYCELIUM"};

    @Config.Name("Particle Offset X")
    @Config.Comment({"The offset of the particle (x-axis)", "Example: 0.5"})
    public static double[] cbpParticleOffsetX = new double[] {0.5D};

    @Config.Name("Particle Offset Y")
    @Config.Comment({"The offset of the particle (y-axis)", "Example: 0.5"})
    public static double[] cbpParticleOffsetY = new double[] {0.5D};

    @Config.Name("Particle Offset Z")
    @Config.Comment({"The offset of the particle (z-axis)", "Example: 0.5"})
    public static double[] cbpParticleOffsetZ = new double[] {0.5D};

    @Config.Name("Particle Motion X")
    @Config.Comment({"The speed of the particle (x-axis), only supported by some particles", "Example: 0.0"})
    public static double[] cbpParticleMotionX = new double[] {0.0D};

    @Config.Name("Particle Motion Y")
    @Config.Comment({"The speed of the particle (y-axis), only supported by some particles", "Example: 0.0"})
    public static double[] cbpParticleMotionY = new double[] {0.0D};

    @Config.Name("Particle Motion Z")
    @Config.Comment({"The speed of the particle (z-axis), only supported by some particles", "Example: 0.0"})
    public static double[] cbpParticleMotionZ = new double[] {0.0D};

    @Config.Name("Particle Biomes")
    @Config.Comment({"The resource location of the biomes where particles can spawn, ANY for every biome", "Example: minecraft:plains"})
    public static String[] cbpParticleBiomes = new String[] {"minecraft:plains"};

    @Mod.EventBusSubscriber(modid = CustomBlockParticles.MOD_ID)
    public static class EventHandler
    {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
        {
            if (event.getModID().equals(CustomBlockParticles.MOD_ID)) ConfigManager.sync(CustomBlockParticles.MOD_ID, Config.Type.INSTANCE);
            CBPUtil.initLists();
        }
    }
}