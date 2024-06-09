package mod.acgaming.cbp.util;

import java.util.ArrayList;
import java.util.List;
import mod.acgaming.cbp.config.CBPConfig;
import net.minecraft.block.Block;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CBPUtil
{
    public static List<Block> cbpBlockList = new ArrayList<>();
    public static List<EnumParticleTypes> cbpParticleTypeList = new ArrayList<>();

    public static void initLists()
    {
        cbpBlockList.clear();
        cbpParticleTypeList.clear();

        for (int i = 0; i < CBPConfig.cbpBlocks.length; i++)
        {
            cbpBlockList.add(getBlockFromRegistryName(CBPConfig.cbpBlocks[i]));
            switch (CBPConfig.cbpParticleTypes[i])
            {
                case "underwater":
                    cbpParticleTypeList.add(CBPRegistry.UNDERWATER);
                    break;
                case "crimsonSpore":
                    cbpParticleTypeList.add(CBPRegistry.CRIMSON_SPORE);
                    break;
                case "warpedSpore":
                    cbpParticleTypeList.add(CBPRegistry.WARPED_SPORE);
                    break;
                default:
                    cbpParticleTypeList.add(EnumParticleTypes.getByName(CBPConfig.cbpParticleTypes[i]));
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