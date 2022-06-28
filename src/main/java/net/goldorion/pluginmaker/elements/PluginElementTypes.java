package net.goldorion.pluginmaker.elements;

import net.goldorion.pluginmaker.elements.types.BlocklyCategory;
import net.goldorion.pluginmaker.elements.types.GlobalTrigger;
import net.goldorion.pluginmaker.ui.modgui.BlocklyCategoryGUI;
import net.goldorion.pluginmaker.ui.modgui.GlobalTriggerGUI;
import net.mcreator.element.BaseType;
import net.mcreator.element.ModElementType;
import net.mcreator.element.RecipeType;

import static net.mcreator.element.ModElementTypeLoader.register;

public class PluginElementTypes {

    public static ModElementType<?> BLOCKLY_CATEGORY;
    public static ModElementType<?> GLOBAL_TRIGGER;

    public static void load() {
        BLOCKLY_CATEGORY = register(
                new ModElementType<>("blockly_category", 'c', BaseType.OTHER, RecipeType.NONE, BlocklyCategoryGUI::new, BlocklyCategory.class));
        GLOBAL_TRIGGER = register(
                new ModElementType<>("global_trigger", 't', BaseType.OTHER, RecipeType.NONE, GlobalTriggerGUI::new, GlobalTrigger.class));
    }
}
