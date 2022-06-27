package net.goldorion.pluginmaker.elements;

import net.goldorion.pluginmaker.elements.types.BlocklyCategory;
import net.goldorion.pluginmaker.ui.modgui.BlocklyCategoryGUI;
import net.mcreator.element.BaseType;
import net.mcreator.element.ModElementType;
import net.mcreator.element.ModElementTypeLoader;
import net.mcreator.element.RecipeType;

public class PluginElementTypes {

    public static ModElementType<?> BLOCKLY_CATEGORY;

    public static void load() {
        BLOCKLY_CATEGORY = ModElementTypeLoader.register(
                new ModElementType<>("blockly_category", 'c', BaseType.OTHER, RecipeType.NONE, BlocklyCategoryGUI::new, BlocklyCategory.class));
    }
}
