package net.goldorion.pluginmaker.elements.types;

import net.goldorion.pluginmaker.elements.parts.BlocklyCategory;
import net.mcreator.blockly.data.Dependency;
import net.mcreator.workspace.elements.ModElement;

import java.util.List;

public class BlocklyBlock extends CodePluginElement {

    public String editor;
    public String text;
    public String blockType;
    public String hexColor;
    public BlocklyCategory category;
    public String outputType;
    public boolean hasNextStatement;

    public List<Dependency> dependencies;

    public BlocklyBlock(ModElement element) {
        super(element);
    }
}
