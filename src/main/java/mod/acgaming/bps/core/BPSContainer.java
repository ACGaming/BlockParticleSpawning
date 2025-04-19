package mod.acgaming.bps.core;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

public class BPSContainer extends DummyModContainer
{
    public BPSContainer()
    {
        super(new ModMetadata());
        ModMetadata meta = this.getMetadata();
        meta.modId = "bpscore";
        meta.name = "Block Particle Spawning Core";
        meta.description = "Core functionality of Block Particle Spawning";
        meta.version = "1.12.2-1.0.0";
        meta.authorList.add("ACGaming");
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller)
    {
        bus.register(this);
        return true;
    }
}