package mod.acgaming.bps.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;
import mod.acgaming.bps.BlockParticleSpawning;
import mod.acgaming.bps.config.BPSConfig;
import mod.acgaming.bps.util.BPSUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BPSBlockMixin
{
    @Unique
    private int bps$rate;

    @Shadow
    public abstract String getLocalizedName();

    @Inject(method = "randomDisplayTick", at = @At(value = "HEAD"))
    private void bpsRandomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand, CallbackInfo ci)
    {
        try
        {
            if (!BPSUtil.bpsSlockList.isEmpty() && BPSUtil.bpsSlockList.contains(state.getBlock()))
            {
                if (bps$rate <= 0)
                {
                    int index = BPSUtil.bpsSlockList.indexOf(state.getBlock());
                    BlockPos particlePos = pos.add(BPSConfig.bpsParticleOffsetX[index], BPSConfig.bpsParticleOffsetY[index], BPSConfig.bpsParticleOffsetZ[index]);
                    double particleXSpeed = BPSConfig.bpsParticleMotionX[index];
                    double particleYSpeed = BPSConfig.bpsParticleMotionY[index];
                    double particleZSpeed = BPSConfig.bpsParticleMotionZ[index];
                    String biomeName = BPSConfig.bpsParticleBiomes[index];
                    switch (BPSConfig.bpsParticleModes[index])
                    {
                        case "BASIC":
                            bps$spawnParticlesBasic(world, particlePos, particleXSpeed, particleYSpeed, particleZSpeed, index, biomeName);
                            break;
                        case "DRIP":
                            bps$spawnParticlesDrip(world, particlePos, particleXSpeed, particleYSpeed, particleZSpeed, index, biomeName);
                            break;
                        case "FIRE_SMOKE":
                            bps$spawnParticlesFireSmoke(world, particlePos, particleXSpeed, particleYSpeed, particleZSpeed, index, biomeName);
                            break;
                        case "MYCELIUM":
                            bps$spawnParticlesMycelium(world, particlePos, particleXSpeed, particleYSpeed, particleZSpeed, index, biomeName);
                            break;
                        case "REDSTONE_ORE":
                            bps$spawnParticlesRedstoneOre(world, particlePos, particleXSpeed, particleYSpeed, particleZSpeed, index, biomeName);
                            break;
                        case "TORCH":
                            bps$spawnParticlesTorch(world, particlePos, particleXSpeed, particleYSpeed, particleZSpeed, index, biomeName);
                            break;
                    }
                    bps$rate = BPSConfig.bpsParticleRates[index];
                }
                else bps$rate--;
            }
        }
        catch (Exception e)
        {
            BlockParticleSpawning.LOGGER.error("Failed to spawn particles for block: " + this.getLocalizedName());
            BlockParticleSpawning.LOGGER.error("Check your config settings!");
        }
    }

    @Unique
    private void bps$spawnParticlesBasic(World world, BlockPos pos, double xSpeed, double ySpeed, double zSpeed, int index, String biomeName)
    {
        if (!biomeName.equals("ANY") && !world.getBiome(pos).getRegistryName().toString().equals(biomeName)) return;
        world.spawnParticle(BPSUtil.bpsParticleTypeList.get(index), pos.getX(), pos.getY(), pos.getZ(), xSpeed, ySpeed, zSpeed);
    }

    @Unique
    private void bps$spawnParticlesDrip(World world, BlockPos pos, double xSpeed, double ySpeed, double zSpeed, int index, String biomeName)
    {
        if (!biomeName.equals("ANY") && !world.getBiome(pos).getRegistryName().toString().equals(biomeName)) return;
        if (world.rand.nextInt(10) == 0 && world.getBlockState(pos.down()).isTopSolid())
        {
            Material material = world.getBlockState(pos.down(2)).getMaterial();

            if (!material.blocksMovement() && !material.isLiquid())
            {
                double d3 = pos.getX() + (double) world.rand.nextFloat();
                double d5 = pos.getY() - 1.05D;
                double d7 = pos.getZ() + (double) world.rand.nextFloat();
                world.spawnParticle(BPSUtil.bpsParticleTypeList.get(index), d3, d5, d7, xSpeed, ySpeed, zSpeed);
            }
        }
    }

    @Unique
    private void bps$spawnParticlesFireSmoke(World world, BlockPos pos, double xSpeed, double ySpeed, double zSpeed, int index, String biomeName)
    {
        if (!biomeName.equals("ANY") && !world.getBiome(pos).getRegistryName().toString().equals(biomeName)) return;
        for (int i = 0; i < BPSConfig.bpsParticleDensities[index]; ++i)
        {
            double d0 = (double) pos.getX() + world.rand.nextDouble();
            double d1 = (double) pos.getY() + world.rand.nextDouble() * 0.5D + 0.5D;
            double d2 = (double) pos.getZ() + world.rand.nextDouble();
            world.spawnParticle(BPSUtil.bpsParticleTypeList.get(index), d0, d1, d2, xSpeed, ySpeed, zSpeed);
        }
    }

    @Unique
    private void bps$spawnParticlesMycelium(World world, BlockPos pos, double xSpeed, double ySpeed, double zSpeed, int index, String biomeName)
    {
        if (!biomeName.equals("ANY") && !world.getBiome(pos).getRegistryName().toString().equals(biomeName)) return;
        if (world.rand.nextInt(BPSConfig.bpsParticleDensities[index]) == 0)
        {
            world.spawnParticle(BPSUtil.bpsParticleTypeList.get(index), (float) pos.getX() + world.rand.nextFloat(), (float) pos.getY() + 1.1F, (float) pos.getZ() + world.rand.nextFloat(), xSpeed, ySpeed, zSpeed);
        }
    }

    @Unique
    private void bps$spawnParticlesRedstoneOre(World world, BlockPos pos, double xSpeed, double ySpeed, double zSpeed, int index, String biomeName)
    {
        if (!biomeName.equals("ANY") && !world.getBiome(pos).getRegistryName().toString().equals(biomeName)) return;
        Random random = world.rand;

        for (int i = 0; i < BPSConfig.bpsParticleDensities[index]; ++i)
        {
            double d1 = (float) pos.getX() + random.nextFloat();
            double d2 = (float) pos.getY() + random.nextFloat();
            double d3 = (float) pos.getZ() + random.nextFloat();

            if (i == 0 && !world.getBlockState(pos.up()).isOpaqueCube())
            {
                d2 = (double) pos.getY() + 0.0625D + 1.0D;
            }

            if (i == 1 && !world.getBlockState(pos.down()).isOpaqueCube())
            {
                d2 = (double) pos.getY() - 0.0625D;
            }

            if (i == 2 && !world.getBlockState(pos.south()).isOpaqueCube())
            {
                d3 = (double) pos.getZ() + 0.0625D + 1.0D;
            }

            if (i == 3 && !world.getBlockState(pos.north()).isOpaqueCube())
            {
                d3 = (double) pos.getZ() - 0.0625D;
            }

            if (i == 4 && !world.getBlockState(pos.east()).isOpaqueCube())
            {
                d1 = (double) pos.getX() + 0.0625D + 1.0D;
            }

            if (i == 5 && !world.getBlockState(pos.west()).isOpaqueCube())
            {
                d1 = (double) pos.getX() - 0.0625D;
            }

            if (d1 < (double) pos.getX() || d1 > (double) (pos.getX() + 1) || d2 < 0.0D || d2 > (double) (pos.getY() + 1) || d3 < (double) pos.getZ() || d3 > (double) (pos.getZ() + 1))
            {
                world.spawnParticle(BPSUtil.bpsParticleTypeList.get(index), d1, d2, d3, xSpeed, ySpeed, zSpeed);
            }
        }
    }

    @Unique
    private void bps$spawnParticlesTorch(World world, BlockPos pos, double xSpeed, double ySpeed, double zSpeed, int index, String biomeName)
    {
        if (!biomeName.equals("ANY") && !world.getBiome(pos).getRegistryName().toString().equals(biomeName)) return;
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.7D;
        double d2 = (double) pos.getZ() + 0.5D;
        world.spawnParticle(BPSUtil.bpsParticleTypeList.get(index), d0, d1, d2, xSpeed, ySpeed, zSpeed);
    }
}