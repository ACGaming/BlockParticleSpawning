package mod.acgaming.cbp.core;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

public class CBPContainer extends DummyModContainer
{
    public CBPContainer()
    {
        super(new ModMetadata());
        ModMetadata meta = this.getMetadata();
        meta.modId = "cbpcore";
        meta.name = "Custom Block Particles Core";
        meta.description = "Core functionality of Custom Block Particles";
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