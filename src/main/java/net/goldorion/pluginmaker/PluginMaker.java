package net.goldorion.pluginmaker;

import net.goldorion.pluginmaker.elements.PluginElementTypes;
import net.mcreator.plugin.JavaPlugin;
import net.mcreator.plugin.Plugin;
import net.mcreator.plugin.events.PreGeneratorsLoadingEvent;

public class PluginMaker extends JavaPlugin {

    public PluginMaker(Plugin plugin) {
        super(plugin);

        addListener(PreGeneratorsLoadingEvent.class, e -> {
            PluginElementTypes.load();
        });
    }
}