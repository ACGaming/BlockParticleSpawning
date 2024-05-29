package mod.acgaming.cbp.mixin;

import java.util.Random;
import mod.acgaming.cbp.CustomBlockParticles;
import mod.acgaming.cbp.config.CBPConfig;
import mod.acgaming.cbp.util.CBPUtil;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class CBPBlockMixin
{
    @Unique private int cbp$rate;

    @Shadow
    public abstract String getLocalizedName();

    @Inject(method = "randomDisplayTick", at = @At(value = "HEAD"))
    private void cbpRandomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand, CallbackInfo ci)
    {
        try
        {
            if (!CBPUtil.cbpBlockList.isEmpty() && CBPUtil.cbpBlockList.contains(state.getBlock()))
            {
                if (cbp$rate <= 0)
                {
                    int index = CBPUtil.cbpBlockList.indexOf(state.getBlock());
                    BlockPos particlePos = pos.add(CBPConfig.cbpParticleOffsetX[index], CBPConfig.cbpParticleOffsetY[index], CBPConfig.cbpParticleOffsetZ[index]);
                    double particleXSpeed = CBPConfig.cbpParticleMotionX[index];
                    double particleYSpeed = CBPConfig.cbpParticleMotionY[index];
                    double particleZSpeed = CBPConfig.cbpParticleMotionZ[index];
                    switch (CBPConfig.cbpParticleModes[index])
                    {
                        case "BASIC":
                            cbp$spawnParticlesBasic(world, particlePos, particleXSpeed, particleYSpeed, particleZSpeed, index);
                            break;
                        case "DRIP":
                            cbp$spawnParticlesDrip(world, particlePos, particleXSpeed, particleYSpeed, particleZSpeed, index);
                            break;
                        case "FIRE_SMOKE":
                            cbp$spawnParticlesFireSmoke(world, particlePos, particleXSpeed, particleYSpeed, particleZSpeed, index);
                            break;
                        case "MYCELIUM":
                            cbp$spawnParticlesMycelium(world, particlePos, particleXSpeed, particleYSpeed, particleZSpeed, index);
                            break;
                        case "REDSTONE_ORE":
                            cbp$spawnParticlesRedstoneOre(world, particlePos, particleXSpeed, particleYSpeed, particleZSpeed, index);
                            break;
                        case "TORCH":
                            cbp$spawnParticlesTorch(world, particlePos, particleXSpeed, particleYSpeed, particleZSpeed, index);
                            break;
                    }
                    cbp$rate = CBPConfig.cbpParticleRates[index];
                }
                else cbp$rate--;
            }
        }
        catch (Exception e)
        {
            CustomBlockParticles.LOGGER.error("Failed to spawn particles for block: " + this.getLocalizedName());
            CustomBlockParticles.LOGGER.error("Check your config settings!");
        }
    }

    @Unique
    private void cbp$spawnParticlesBasic(World world, BlockPos pos, double xSpeed, double ySpeed, double zSpeed, int index)
    {
        world.spawnParticle(CBPUtil.cbpParticleTypeList.get(index), pos.getX(), pos.getY(), pos.getZ(), xSpeed, ySpeed, zSpeed);
    }

    @Unique
    private void cbp$spawnParticlesDrip(World world, BlockPos pos, double xSpeed, double ySpeed, double zSpeed, int index)
    {
        if (world.rand.nextInt(10) == 0 && world.getBlockState(pos.down()).isTopSolid())
        {
            Material material = world.getBlockState(pos.down(2)).getMaterial();

            if (!material.blocksMovement() && !material.isLiquid())
            {
                double d3 = pos.getX() + (double) world.rand.nextFloat();
                double d5 = pos.getY() - 1.05D;
                double d7 = pos.getZ() + (double) world.rand.nextFloat();
                world.spawnParticle(CBPUtil.cbpParticleTypeList.get(index), d3, d5, d7, xSpeed, ySpeed, zSpeed);
            }
        }
    }

    @Unique
    private void cbp$spawnParticlesFireSmoke(World world, BlockPos pos, double xSpeed, double ySpeed, double zSpeed, int index)
    {
        for (int i = 0; i < CBPConfig.cbpParticleDensities[index]; ++i)
        {
            double d0 = (double) pos.getX() + world.rand.nextDouble();
            double d1 = (double) pos.getY() + world.rand.nextDouble() * 0.5D + 0.5D;
            double d2 = (double) pos.getZ() + world.rand.nextDouble();
            world.spawnParticle(CBPUtil.cbpParticleTypeList.get(index), d0, d1, d2, xSpeed, ySpeed, zSpeed);
        }
    }

    @Unique
    private void cbp$spawnParticlesMycelium(World world, BlockPos pos, double xSpeed, double ySpeed, double zSpeed, int index)
    {
        if (world.rand.nextInt(CBPConfig.cbpParticleDensities[index]) == 0)
        {
            world.spawnParticle(CBPUtil.cbpParticleTypeList.get(index), (float) pos.getX() + world.rand.nextFloat(), (float) pos.getY() + 1.1F, (float) pos.getZ() + world.rand.nextFloat(), xSpeed, ySpeed, zSpeed);
        }
    }

    @Unique
    private void cbp$spawnParticlesRedstoneOre(World world, BlockPos pos, double xSpeed, double ySpeed, double zSpeed, int index)
    {
        Random random = world.rand;

        for (int i = 0; i < CBPConfig.cbpParticleDensities[index]; ++i)
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
                world.spawnParticle(CBPUtil.cbpParticleTypeList.get(index), d1, d2, d3, xSpeed, ySpeed, zSpeed);
            }
        }
    }

    @Unique
    private void cbp$spawnParticlesTorch(World world, BlockPos pos, double xSpeed, double ySpeed, double zSpeed, int index)
    {
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.7D;
        double d2 = (double) pos.getZ() + 0.5D;
        world.spawnParticle(CBPUtil.cbpParticleTypeList.get(index), d0, d1, d2, xSpeed, ySpeed, zSpeed);
    }
}