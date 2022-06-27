package net.goldorion.pluginmaker.elements.types;

import net.mcreator.element.GeneratableElement;
import net.mcreator.workspace.elements.ModElement;

public class BlocklyCategory extends GeneratableElement {

    public String name;
    public String editor;
    public String hexColor;

    public BlocklyCategory(ModElement element) {
        super(element);
    }
}
