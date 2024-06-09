package mod.acgaming.cbp;

import mod.acgaming.cbp.util.CBPRegistry;
import mod.acgaming.cbp.util.CBPUtil;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = CustomBlockParticles.MOD_ID, name = CustomBlockParticles.NAME, version = CustomBlockParticles.VERSION, acceptedMinecraftVersions = CustomBlockParticles.ACCEPTED_VERSIONS, clientSideOnly = true)
public class CustomBlockParticles
{
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String NAME = "Custom Block Particles";
    public static final String VERSION = Tags.VERSION;
    public static final String ACCEPTED_VERSIONS = "[1.12.2]";
    public static final Logger LOGGER = LogManager.getLogger(NAME);

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        CBPRegistry.registerParticles();
        CBPUtil.initLists();
        LOGGER.info(NAME + " initialized");
    }
}