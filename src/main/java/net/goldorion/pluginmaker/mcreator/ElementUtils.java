package net.goldorion.pluginmaker.mcreator;

import net.goldorion.pluginmaker.elements.PluginElementTypes;
import net.goldorion.pluginmaker.elements.types.BlocklyCategory;
import net.mcreator.element.ModElementType;
import net.mcreator.minecraft.DataListEntry;
import net.mcreator.minecraft.DataListLoader;
import net.mcreator.workspace.Workspace;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ElementUtils {

    public static String[] loadBlocklyTypes() {
        return DataListLoader.loadDataList("blockly_types").stream().map(DataListEntry::getName).toList().toArray(new String[0]);
    }

    public static List<DataListEntry> loadCategories(Workspace workspace, String type) {
        List<DataListEntry> retval = getCustomElementsOfType(workspace, PluginElementTypes.BLOCKLY_CATEGORY).stream()
                .filter(e -> ((BlocklyCategory) Objects.requireNonNull(((DataListEntry.Custom) e).getModElement().getGeneratableElement()))
                        .editor.equals(type)).collect(Collectors.toList());
        retval.addAll( DataListLoader.loadDataList("blockly_categories").stream()
                .filter(e -> e.getType().equals(type)).toList());
        return retval;
    }

    public static List<DataListEntry> getCustomElementsOfType(@Nonnull Workspace workspace, ModElementType<?> type) {
        return workspace.getModElements().stream().filter(mu -> mu.getType() == type).map(DataListEntry.Custom::new)
                .collect(Collectors.toList());
    }
}
