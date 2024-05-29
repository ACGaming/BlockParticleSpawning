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
    //public static List<String[]> cbpCustomParticleList = new ArrayList<>();

    public static void initLists()
    {
        cbpBlockList.clear();
        cbpParticleTypeList.clear();
        //cbpCustomParticleList.clear();

        //for (int i = 0; i < CBPConfig.cbpCustomParticles.length; i++)
        //{
        //    String[] properties = CBPConfig.cbpCustomParticles[i].split(";");
        //    cbpCustomParticleList.add(new String[] {String.valueOf(CBPRegistry.registerParticle(properties[0])), properties[1], properties[2], properties[3], properties[4]});
        //}

        for (int i = 0; i < CBPConfig.cbpBlocks.length; i++)
        {
            cbpBlockList.add(getBlockFromRegistryName(CBPConfig.cbpBlocks[i]));
            cbpParticleTypeList.add(EnumParticleTypes.getByName(CBPConfig.cbpParticleTypes[i]));
        }
    }

    public static Block getBlockFromRegistryName(String string)
    {
        ResourceLocation resLoc = new ResourceLocation(string);
        if (ForgeRegistries.BLOCKS.containsKey(resLoc)) return ForgeRegistries.BLOCKS.getValue(resLoc);
        return null;
    }
}