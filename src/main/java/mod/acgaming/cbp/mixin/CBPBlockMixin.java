package mod.acgaming.cbp.mixin;

import java.util.Random;
import mod.acgaming.cbp.CustomBlockParticles;
import mod.acgaming.cbp.config.CBPConfig;
import mod.acgaming.cbp.util.CBPUtil;
import net.minecraft.block.Block;
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
                int index = CBPUtil.cbpBlockList.indexOf(state.getBlock());
                if (cbp$rate <= 0)
                {
                    switch (CBPConfig.cbpParticleModes[index])
                    {
                        case "FIRE_SMOKE":
                            cbp$spawnParticlesFireSmoke(world, pos, index);
                            break;
                        case "MYCELIUM":
                            cbp$spawnParticlesMycelium(world, pos, index);
                            break;
                        case "REDSTONE_ORE":
                            cbp$spawnParticlesRedstoneOre(world, pos, index);
                            break;
                        case "TORCH":
                            cbp$spawnParticlesTorch(world, pos, index);
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
    private void cbp$spawnParticlesFireSmoke(World world, BlockPos pos, int index)
    {
        for (int i = 0; i < CBPConfig.cbpParticleDensities[index]; ++i)
        {
            double d0 = (double) pos.getX() + world.rand.nextDouble();
            double d1 = (double) pos.getY() + world.rand.nextDouble() * 0.5D + 0.5D;
            double d2 = (double) pos.getZ() + world.rand.nextDouble();
            world.spawnParticle(CBPUtil.cbpParticleTypeList.get(index), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Unique
    private void cbp$spawnParticlesMycelium(World world, BlockPos pos, int index)
    {
        if (world.rand.nextInt(CBPConfig.cbpParticleDensities[index]) == 0)
        {
            world.spawnParticle(CBPUtil.cbpParticleTypeList.get(index), (float) pos.getX() + world.rand.nextFloat(), (float) pos.getY() + 1.1F, (float) pos.getZ() + world.rand.nextFloat(), 0.0D, 0.0D, 0.0D);
        }
    }

    @Unique
    private void cbp$spawnParticlesRedstoneOre(World world, BlockPos pos, int index)
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
                world.spawnParticle(CBPUtil.cbpParticleTypeList.get(index), d1, d2, d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Unique
    private void cbp$spawnParticlesTorch(World world, BlockPos pos, int index)
    {
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.7D;
        double d2 = (double) pos.getZ() + 0.5D;
        world.spawnParticle(CBPUtil.cbpParticleTypeList.get(index), d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
}