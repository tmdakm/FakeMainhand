package win.huangyu.fakemainhand;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.util.Arm;

public @Config(name = "fakemainhand")
class ModConfig implements ConfigData {
    public boolean enableMod = true;

    @ConfigEntry.Gui.Tooltip
    public Arm armServer = Arm.RIGHT;

    @ConfigEntry.Gui.Tooltip
    public Arm armClient = Arm.RIGHT;
}

