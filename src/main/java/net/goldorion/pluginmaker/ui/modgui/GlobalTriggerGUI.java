package net.goldorion.pluginmaker.ui.modgui;

import net.goldorion.pluginmaker.elements.types.GlobalTrigger;
import net.goldorion.pluginmaker.ui.mcreator.codetemplates.JCodeTemplatesList;
import net.goldorion.pluginmaker.ui.mcreator.dependencies.JDependencyEntriesList;
import net.mcreator.ui.MCreator;
import net.mcreator.ui.component.util.PanelUtils;
import net.mcreator.ui.help.HelpUtils;
import net.mcreator.ui.init.L10N;
import net.mcreator.ui.modgui.ModElementGUI;
import net.mcreator.ui.validation.AggregatedValidationResult;
import net.mcreator.ui.validation.component.VTextField;
import net.mcreator.ui.validation.validators.TextFieldValidator;
import net.mcreator.util.StringUtils;
import net.mcreator.workspace.elements.ModElement;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;
import java.util.stream.Collectors;

public class GlobalTriggerGUI extends ModElementGUI<GlobalTrigger> {

    private final VTextField name = new VTextField(20);
    private final VTextField requiredAPIs = new VTextField(20);
    private final JCheckBox cancelable = L10N.checkbox("elementgui.common.enable");
    private final JCheckBox hasResult = L10N.checkbox("elementgui.common.enable");
    private final JComboBox<String> side = new JComboBox<>(new String[] {"both", "client", "server"});
    private JDependencyEntriesList dependencies;
    private JCodeTemplatesList codeTemplates;

    public GlobalTriggerGUI(MCreator mcreator, @Nonnull ModElement modElement, boolean editingMode) {
        super(mcreator, modElement, editingMode);
        initGUI();
        super.finalizeGUI();
    }
    @Override
    protected void initGUI() {
        JPanel properties = new JPanel(new GridLayout(5, 2, 10, 10));
        properties.setOpaque(false);

        properties.add(HelpUtils.wrapWithHelpButton(this.withEntry("common/gui_name"), L10N.label("elementgui.common.name")));
        net.mcreator.ui.component.util.ComponentUtils.deriveFont(this.name, 16.0F);
        this.name.setValidator(new TextFieldValidator(this.name, L10N.t("elementgui.global_trigger.error_category_needs_name")));
        this.name.enableRealtimeValidation();
        properties.add(name);

        properties.add(HelpUtils.wrapWithHelpButton(this.withEntry("global_trigger/requiredAPIs"), L10N.label("elementgui.global_trigger.required_apis")));
        properties.add(requiredAPIs);

        properties.add(HelpUtils.wrapWithHelpButton(this.withEntry("global_trigger/cancelable"), L10N.label("elementgui.global_trigger.cancelable")));
        properties.add(cancelable);
        cancelable.setOpaque(false);

        properties.add(HelpUtils.wrapWithHelpButton(this.withEntry("global_trigger/hasResult"), L10N.label("elementgui.global_trigger.has_result")));
        properties.add(hasResult);
        hasResult.setOpaque(false);

        properties.add(HelpUtils.wrapWithHelpButton(this.withEntry("global_trigger/side"), L10N.label("elementgui.global_trigger.side")));
        properties.add(side);

        dependencies = new JDependencyEntriesList(mcreator, this);

        codeTemplates = new JCodeTemplatesList(mcreator, this);

        addPage(L10N.t("elementgui.common.properties_general"), (PanelUtils.northAndCenterElement(PanelUtils.totalCenterInPanel(properties), dependencies)));
        addPage(L10N.t("elementgui.common.code_templates"), codeTemplates);
    }

    @Override
    protected AggregatedValidationResult validatePage(int i) {
        return new AggregatedValidationResult(name);
    }

    @Override
    protected void openInEditingMode(GlobalTrigger globalTrigger) {
        name.setText(globalTrigger.name);
        requiredAPIs.setText(globalTrigger.requiredAPIs.stream().map(info -> info.replace(",", "\\,")).collect(Collectors.joining(",")));
        cancelable.setSelected(globalTrigger.cancelable);
        hasResult.setSelected(globalTrigger.hasResult);
        side.setSelectedItem(globalTrigger.side);
        dependencies.setDependencies(globalTrigger.dependencies);
        codeTemplates.setCodeTemplates(globalTrigger.codeTemplates);
    }

    @Override
    public GlobalTrigger getElementFromGUI() {
        GlobalTrigger globalTrigger = new GlobalTrigger(modElement);
        globalTrigger.name = name.getText();
        globalTrigger.requiredAPIs = StringUtils.splitCommaSeparatedStringListWithEscapes(requiredAPIs.getText());
        globalTrigger.cancelable = cancelable.isSelected();
        globalTrigger.hasResult = hasResult.isSelected();
        globalTrigger.side = (String) side.getSelectedItem();
        globalTrigger.dependencies = dependencies.getDependencies();
        globalTrigger.codeTemplates = codeTemplates.getCodeTemplates();

        return globalTrigger;
    }
}
