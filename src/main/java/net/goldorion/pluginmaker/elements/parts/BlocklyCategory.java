package net.goldorion.pluginmaker.elements.parts;

import net.mcreator.generator.mapping.MappableElement;
import net.mcreator.generator.mapping.NameMapper;
import net.mcreator.minecraft.DataListEntry;
import net.mcreator.workspace.Workspace;

import javax.annotation.Nonnull;

public class BlocklyCategory extends MappableElement {

    private BlocklyCategory() {
        super(new NameMapper(null, "blockly_categories"));
    }

    public BlocklyCategory(@Nonnull Workspace owner, String name) {
        this();
        mapper.setWorkspace(owner);
        setValue(name);
    }

    public BlocklyCategory(@Nonnull Workspace owner, DataListEntry name) {
        this(owner, name.getName());
    }
}
