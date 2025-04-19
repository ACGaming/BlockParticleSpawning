package mod.acgaming.bps.particle;

import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

public class ParticleUnderwater extends Particle
{
    private ParticleUnderwater(World world, double x, double y, double z)
    {
        super(world, x, y - 0.125D, z);
        this.particleRed = 0.4F;
        this.particleGreen = 0.4F;
        this.particleBlue = 0.7F;
        this.setSize(0.01F, 0.01F);
        this.particleScale *= this.rand.nextFloat() * 0.6F + 0.2F;
        this.particleMaxAge = (int) (16.0D / (world.rand.nextDouble() * 0.8D + 0.2D));
        this.canCollide = false;
    }

    private ParticleUnderwater(World world, double x, double y, double z, double motionX, double motionY, double motionZ)
    {
        super(world, x, y - 0.125D, z, motionX, motionY, motionZ);
        this.setSize(0.01F, 0.01F);
        this.particleScale *= this.rand.nextFloat() * 0.6F + 0.6F;
        this.particleMaxAge = (int) (16.0D / (world.rand.nextDouble() * 0.8D + 0.2D));
        this.canCollide = false;
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleMaxAge-- <= 0)
        {
            this.setExpired();
        }
        else
        {
            this.move(this.motionX, this.motionY, this.motionZ);
        }
    }

    @SideOnly(Side.CLIENT)
    public static class CrimsonSporeFactory implements IParticleFactory
    {
        public Particle createParticle(int id, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... params)
        {
            Random random = world.rand;
            double d0 = random.nextGaussian() * 1.0E-6F;
            double d1 = random.nextGaussian() * 1.0E-4F;
            double d2 = random.nextGaussian() * 1.0E-6F;
            ParticleUnderwater particleUnderwater = new ParticleUnderwater(world, x, y, z, d0, d1, d2);
            particleUnderwater.setRBGColorF(0.9F, 0.4F, 0.5F);
            return particleUnderwater;
        }
    }

    @SideOnly(Side.CLIENT)
    public static class UnderwaterFactory implements IParticleFactory
    {
        public Particle createParticle(int id, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... params)
        {
            return new ParticleUnderwater(world, x, y, z);
        }
    }

    @SideOnly(Side.CLIENT)
    public static class WarpedSporeFactory implements IParticleFactory
    {
        @Override
        public Particle createParticle(int id, World world, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed, int... params)
        {
            double d0 = (double) world.rand.nextFloat() * -1.9D * (double) world.rand.nextFloat() * 0.1D;
            ParticleUnderwater particleUnderwater = new ParticleUnderwater(world, x, y, z, 0.0D, d0, 0.0D);
            particleUnderwater.setRBGColorF(0.1F, 0.1F, 0.3F);
            particleUnderwater.setSize(0.001F, 0.001F);
            return particleUnderwater;
        }
    }
}
