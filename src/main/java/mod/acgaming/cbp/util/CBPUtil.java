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
    public static List<Block> cbpBlockMap = new ArrayList<>();
    public static List<EnumParticleTypes> cbpParticleTypeMap = new ArrayList<>();

    public static void initLists()
    {
        cbpBlockMap.clear();
        cbpParticleTypeMap.clear();

        for (int i = 0; i < CBPConfig.cbpBlocks.length; i++)
        {
            cbpBlockMap.add(getBlockFromRegistryName(CBPConfig.cbpBlocks[i]));
            cbpParticleTypeMap.add(EnumParticleTypes.getByName(CBPConfig.cbpParticleTypes[i]));
        }
    }

    public static Block getBlockFromRegistryName(String string)
    {
        ResourceLocation resLoc = new ResourceLocation(string);
        if (ForgeRegistries.BLOCKS.containsKey(resLoc)) return ForgeRegistries.BLOCKS.getValue(resLoc);
        return null;
    }
}