package net.goldorion.pluginmaker.elements.types.interfaces;

import net.mcreator.element.BaseType;
import net.mcreator.element.types.interfaces.ICommonType;

import java.util.Collection;
import java.util.List;

public interface ICodePluginElement extends ICommonType {

    @Override default Collection<BaseType> getBaseTypesProvided() {
        return List.of(BaseType.OTHER);
    }
}
