package net.roguelogix.biggerreactors;

import it.unimi.dsi.fastutil.objects.ReferenceArrayList;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.common.NeoForge;
import net.roguelogix.biggerreactors.multiblocks.heatexchanger.client.HeatExchangerFluidPortScreen;
import net.roguelogix.biggerreactors.multiblocks.heatexchanger.client.HeatExchangerTerminalScreen;
import net.roguelogix.biggerreactors.multiblocks.heatexchanger.containers.HeatExchangerFluidPortContainer;
import net.roguelogix.biggerreactors.multiblocks.heatexchanger.containers.HeatExchangerTerminalContainer;
import net.roguelogix.biggerreactors.multiblocks.reactor.client.*;
import net.roguelogix.biggerreactors.multiblocks.reactor.containers.*;
import net.roguelogix.biggerreactors.multiblocks.turbine.client.TurbineFluidPortScreen;
import net.roguelogix.biggerreactors.multiblocks.turbine.client.TurbineTerminalScreen;
import net.roguelogix.biggerreactors.multiblocks.turbine.containers.TurbineFluidPortContainer;
import net.roguelogix.biggerreactors.multiblocks.turbine.containers.TurbineTerminalContainer;
import net.roguelogix.biggerreactors.registries.FluidTransitionRegistry;
import net.roguelogix.biggerreactors.registries.ReactorModeratorRegistry;
import net.roguelogix.biggerreactors.registries.TurbineCoilRegistry;
import net.roguelogix.phosphophyllite.Phosphophyllite;
import net.roguelogix.phosphophyllite.event.ReloadDataEvent;
import net.roguelogix.phosphophyllite.registry.Registry;
import net.roguelogix.quartz.Quartz;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("unused")
@Mod(BiggerReactors.modid)
public class BiggerReactors {

    public static final String modid = "biggerreactors";

    public static final Logger LOGGER = LogManager.getLogger();
    public static final boolean LOG_DEBUG = LOGGER.isDebugEnabled();
    
    public BiggerReactors() {
        new Registry(new ReferenceArrayList<>(), ReferenceArrayList.of(ResourceLocation.fromNamespaceAndPath(Phosphophyllite.modid, "creative_tab"), ResourceLocation.fromNamespaceAndPath(Quartz.modid, "creative_tab")));
        if (FMLLoader.getDist().isClient()) {
            ModLoadingContext.get().getActiveContainer().getEventBus().addListener(this::onRegisterMenuScreens);
        }
        NeoForge.EVENT_BUS.addListener(this::onReloadData);
        version = FMLLoader.getLoadingModList().getModFileById(modid).versionString();
    }
    
    public void onReloadData(final ReloadDataEvent reloadDataEvent) {
        ReactorModeratorRegistry.loadRegistry();
        TurbineCoilRegistry.loadRegistry();
        FluidTransitionRegistry.loadRegistry();
    }

    public void onRegisterMenuScreens(final RegisterMenuScreensEvent e) {
        // TODO: 6/28/20 Registry.
        //  Since I already have the comment here, also need to do a capability registry. I have a somewhat dumb capability to register.
//        e.register(CyaniteReprocessorContainer.INSTANCE,
//                CyaniteReprocessorScreen::new);
        e.register(ReactorTerminalContainer.INSTANCE,
                CommonReactorTerminalScreen::new);
        e.register(ReactorCoolantPortContainer.INSTANCE,
                ReactorCoolantPortScreen::new);
        e.register(ReactorAccessPortContainer.INSTANCE,
                ReactorAccessPortScreen::new);
        e.register(ReactorControlRodContainer.INSTANCE,
                ReactorControlRodScreen::new);
        e.register(ReactorRedstonePortContainer.INSTANCE,
                ReactorRedstonePortScreen::new);
        e.register(TurbineTerminalContainer.INSTANCE,
                TurbineTerminalScreen::new);
        e.register(TurbineFluidPortContainer.INSTANCE,
                TurbineFluidPortScreen::new);
        e.register(HeatExchangerTerminalContainer.INSTANCE,
                HeatExchangerTerminalScreen::new);
        e.register(HeatExchangerFluidPortContainer.INSTANCE,
                HeatExchangerFluidPortScreen::new);
    }
    
    private static String version;
    public static String modVersion(){
        return version;
    }
}
