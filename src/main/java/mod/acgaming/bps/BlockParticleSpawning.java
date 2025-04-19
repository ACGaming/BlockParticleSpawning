package mod.acgaming.bps;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import mod.acgaming.bps.util.BPSRegistry;
import mod.acgaming.bps.util.BPSUtil;

@Mod(modid = BlockParticleSpawning.MOD_ID, name = BlockParticleSpawning.NAME, version = BlockParticleSpawning.VERSION, acceptedMinecraftVersions = BlockParticleSpawning.ACCEPTED_VERSIONS, clientSideOnly = true)
public class BlockParticleSpawning
{
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String NAME = "Block Particle Spawning";
    public static final String VERSION = Tags.VERSION;
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        BPSRegistry.registerParticles();
        BPSUtil.initLists();
        LOGGER.info(NAME + " initialized");
    }
}