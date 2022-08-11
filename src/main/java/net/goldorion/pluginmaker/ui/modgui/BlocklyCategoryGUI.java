package net.goldorion.pluginmaker.ui.modgui;

import net.goldorion.pluginmaker.elements.types.BlocklyCategory;
import net.mcreator.ui.MCreator;
import net.mcreator.ui.component.JColor;
import net.mcreator.ui.component.util.ComponentUtils;
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

public class BlocklyCategoryGUI extends ModElementGUI<BlocklyCategory> {

    private final VTextField name = new VTextField(20);
    private final JComboBox<String> editor = new JComboBox<>(new String[]{"aitasks", "cmdargs", "jsontriggers", "procedures"});
    private final JColor hexColor;

    public BlocklyCategoryGUI(MCreator mcreator, @Nonnull ModElement modElement, boolean editingMode) {
        super(mcreator, modElement, editingMode);
        this.hexColor = new JColor(this.mcreator, false, false);

        initGUI();
        super.finalizeGUI();
    }

    @Override
    protected void initGUI() {
        JPanel page1 = new JPanel(new GridLayout(3, 2, 10, 10));
        page1.setOpaque(false);

        page1.add(HelpUtils.wrapWithHelpButton(this.withEntry("common/gui_name"), L10N.label("elementgui.common.name")));
        ComponentUtils.deriveFont(this.name, 16.0F);
        this.name.setValidator(new TextFieldValidator(this.name, L10N.t("elementgui.blockly_category.error_category_needs_name")));
        this.name.enableRealtimeValidation();
        page1.add(name);

        page1.add(HelpUtils.wrapWithHelpButton(this.withEntry("blockly/editor"), L10N.label("elementgui.blockly.editor")));
        page1.add(editor);

        page1.add(HelpUtils.wrapWithHelpButton(this.withEntry("blockly/hex_color"), L10N.label("elementgui.blockly.hex_color")));
        page1.add(hexColor);

        addPage(PanelUtils.totalCenterInPanel(page1));

        if (!this.isEditingMode())
            this.name.setText(StringUtils.machineToReadableName(this.modElement.getName()));
    }

    @Override
    protected AggregatedValidationResult validatePage(int i) {
        return new AggregatedValidationResult(name);
    }

    @Override
    protected void openInEditingMode(BlocklyCategory category) {
        name.setText(category.name);
        editor.setSelectedItem(category.editor);
        hexColor.setColor(Color.decode(category.hexColor));
    }

    @Override
    public BlocklyCategory getElementFromGUI() {
        BlocklyCategory category = new BlocklyCategory(modElement);
        category.name = name.getText();
        category.editor = (String) editor.getSelectedItem();
        category.hexColor = String.format("#%02x%02x%02x", hexColor.getColor().getRed(), hexColor.getColor().getGreen(), hexColor.getColor().getBlue());
        return category;
    }
}
