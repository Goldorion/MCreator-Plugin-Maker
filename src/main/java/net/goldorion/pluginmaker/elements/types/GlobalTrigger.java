package net.goldorion.pluginmaker.elements.types;

import net.mcreator.blockly.data.Dependency;
import net.mcreator.element.GeneratableElement;
import net.mcreator.workspace.elements.ModElement;

import java.util.List;

public class GlobalTrigger extends GeneratableElement {

    public String name;
    public List<String> requiredAPIs;
    public List<Dependency> dependencies;
    public boolean cancelable;
    public boolean hasResult;
    public String side;

    public GlobalTrigger(ModElement element) {
        super(element);
    }
}
