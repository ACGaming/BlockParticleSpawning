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
    @Config.Comment("Example: note")
    public static String[] cbpParticleTypes = new String[] {"note"};

    @Config.Name("Particle Densities")
    @Config.Comment({"The amount of particles that are spawned", "Example: 5"})
    public static int[] cbpParticleDensities = new int[] {5};

    @Config.Name("Particle Rates")
    @Config.Comment({"The cooldown between each particle spawning", "Example: 10"})
    public static int[] cbpParticleRates = new int[] {10};

    @Config.Name("Particle Modes")
    @Config.Comment({"The way particles are spawned", "Valid values: FIRE_SMOKE, MYCELIUM, REDSTONE_ORE", "TORCH"})
    public static String[] cbpParticleModes = new String[] {"MYCELIUM"};

    //@Config.Name("Custom Particles")
    //@Config.Comment({"Build custom particles", "Syntax: name;resourceLocation;width;height;duration"})
    //public static String[] cbpCustomParticles = new String[] {};

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