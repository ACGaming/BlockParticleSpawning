package mod.acgaming.cbp.particle;

import mod.acgaming.cbp.util.CBPUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class CBPParticle extends Particle
{
    public CBPParticle(int id, World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed)
    {
        super(world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
        String[] properties = null;
        for (String[] s : CBPUtil.cbpCustomParticleList)
        {
            if (s[0].equals(String.valueOf(id))) properties = s;
        }
        this.setParticleTexture(Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(new ResourceLocation(properties[1]).toString()));
        this.setSize(Float.parseFloat(properties[2]), Float.parseFloat(properties[3]));
        this.particleMaxAge = Integer.parseInt(properties[4]);
    }

    public static class Factory implements IParticleFactory
    {
        @Override
        public Particle createParticle(int id, World world, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed, int... params)
        {
            return new CBPParticle(id, world, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
        }
    }
}