package mod.acgaming.cbp.util;

import mod.acgaming.cbp.particle.CBPParticle;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.util.EnumHelper;

public class CBPRegistry
{
    public static int registerParticle(String name)
    {
        EnumParticleTypes particle = EnumHelper.addEnum(EnumParticleTypes.class, name, new Class[] {String.class, int.class, boolean.class}, name, EnumParticleTypes.values().length, false);
        Minecraft.getMinecraft().effectRenderer.registerParticle(particle.getParticleID(), new CBPParticle.Factory());
        return particle.getParticleID();
    }
}