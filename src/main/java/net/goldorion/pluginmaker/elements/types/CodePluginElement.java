package net.goldorion.pluginmaker.elements.types;

import net.goldorion.pluginmaker.elements.parts.CodeTemplate;
import net.goldorion.pluginmaker.elements.types.interfaces.ICodePluginElement;
import net.mcreator.element.GeneratableElement;
import net.mcreator.workspace.elements.ModElement;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class CodePluginElement extends GeneratableElement implements ICodePluginElement {

    public List<CodeTemplate> codeTemplates;

    public CodePluginElement(ModElement element) {
        super(element);
        codeTemplates = new ArrayList<>();
    }

    public boolean hasGenerator(String generator) {
        return codeTemplates.stream().anyMatch(ct -> ct.generator.equals(generator));
    }

    public String getCodeTemplate(String generator) {
        return codeTemplates.stream().filter(codeTemplate -> codeTemplate.generator.equals(generator)).findFirst().map(codeTemplate -> codeTemplate.code).orElse("");
    }


}
