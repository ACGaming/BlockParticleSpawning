package mod.acgaming.bps.util;

import net.minecraft.block.Block;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import mod.acgaming.bps.config.BPSConfig;

public class BPSUtil
{
    public static List<Block> bpsSlockList = new ArrayList<>();
    public static List<EnumParticleTypes> bpsParticleTypeList = new ArrayList<>();

    public static void initLists()
    {
        bpsSlockList.clear();
        bpsParticleTypeList.clear();

        for (int i = 0; i < BPSConfig.bpsBlocks.length; i++)
        {
            bpsSlockList.add(getBlockFromRegistryName(BPSConfig.bpsBlocks[i]));
            switch (BPSConfig.bpsParticleTypes[i])
            {
                case "underwater":
                    bpsParticleTypeList.add(BPSRegistry.UNDERWATER);
                    break;
                case "crimsonSpore":
                    bpsParticleTypeList.add(BPSRegistry.CRIMSON_SPORE);
                    break;
                case "warpedSpore":
                    bpsParticleTypeList.add(BPSRegistry.WARPED_SPORE);
                    break;
                default:
                    bpsParticleTypeList.add(EnumParticleTypes.getByName(BPSConfig.bpsParticleTypes[i]));
                    break;
            }
        }
    }

    public static Block getBlockFromRegistryName(String string)
    {
        ResourceLocation resLoc = new ResourceLocation(string);
        if (ForgeRegistries.BLOCKS.containsKey(resLoc)) return ForgeRegistries.BLOCKS.getValue(resLoc);
        return null;
    }
}