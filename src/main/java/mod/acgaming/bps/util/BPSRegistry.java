package mod.acgaming.bps.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.util.EnumHelper;

import mod.acgaming.bps.particle.ParticleUnderwater;

public class BPSRegistry
{
    public static EnumParticleTypes UNDERWATER;
    public static EnumParticleTypes CRIMSON_SPORE;
    public static EnumParticleTypes WARPED_SPORE;

    public static void registerParticles()
    {
        int id = EnumParticleTypes.values().length;
        UNDERWATER = registerParticle("underwater", "underwater", id++, false, 0, new ParticleUnderwater.UnderwaterFactory());
        CRIMSON_SPORE = registerParticle("crimsonSpore", "crimsonSpore", id++, false, 0, new ParticleUnderwater.CrimsonSporeFactory());
        WARPED_SPORE = registerParticle("warpedSpore", "warpedSpore", id++, false, 0, new ParticleUnderwater.WarpedSporeFactory());
    }

    public static EnumParticleTypes registerParticle(String enumName, String name, int id, boolean ignoreRange, int argumentCount, IParticleFactory factory)
    {
        for (EnumParticleTypes existingParticle : EnumParticleTypes.values())
        {
            if (existingParticle.getParticleID() == id)
            {
                throw new RuntimeException("Attempted to register a particle with the same integer ID as " + existingParticle.getParticleName() + " (" + existingParticle + ").");
            }
        }

        EnumParticleTypes particle = EnumHelper.addEnum(EnumParticleTypes.class, enumName, new Class[] {String.class, int.class, boolean.class, int.class}, name, id, ignoreRange, argumentCount);
        Minecraft.getMinecraft().effectRenderer.registerParticle(id, factory);

        return particle;
    }
}