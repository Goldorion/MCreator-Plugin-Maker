package net.goldorion.pluginmaker.ui.modgui;

import net.goldorion.pluginmaker.elements.parts.BlocklyCategory;
import net.goldorion.pluginmaker.elements.types.BlocklyBlock;
import net.goldorion.pluginmaker.mcreator.ElementUtils;
import net.goldorion.pluginmaker.ui.mcreator.codetemplates.JCodeTemplatesList;
import net.goldorion.pluginmaker.ui.mcreator.dependencies.JDependencyEntriesList;
import net.mcreator.ui.MCreator;
import net.mcreator.ui.component.JColor;
import net.mcreator.ui.component.util.PanelUtils;
import net.mcreator.ui.help.HelpUtils;
import net.mcreator.ui.init.L10N;
import net.mcreator.ui.minecraft.DataListComboBox;
import net.mcreator.ui.modgui.ModElementGUI;
import net.mcreator.ui.validation.AggregatedValidationResult;
import net.mcreator.ui.validation.component.VTextField;
import net.mcreator.workspace.elements.ModElement;

import javax.annotation.Nonnull;
import javax.swing.*;
import java.awt.*;

public class BlocklyBlockGUI extends ModElementGUI<BlocklyBlock> {


    private final VTextField text = new VTextField(30);
    private final JComboBox<String> editor = new JComboBox<>(new String[]{"procedures"});
    private final JComboBox<String> blockType = new JComboBox<>(new String[]{"Normal", "Output"});
    private final JColor hexColor;
    private DataListComboBox category;

    // Block type parameters
    private final JComboBox<String> outputType = new JComboBox<>(ElementUtils.loadBlocklyTypes());
    private final JCheckBox hasNextStatement = L10N.checkbox("elementgui.common.enable");

    private JDependencyEntriesList dependencies;
    private JCodeTemplatesList codeTemplates;

    private final CardLayout blockTypeCL = new CardLayout();
    private final JPanel specificPropertiesPanel = new JPanel(blockTypeCL);

    public BlocklyBlockGUI(MCreator mcreator, @Nonnull ModElement modElement, boolean editingMode) {
        super(mcreator, modElement, editingMode);

        this.hexColor = new JColor(this.mcreator, false, false);

        this.initGUI();
        super.finalizeGUI();
    }

    @Override
    protected void initGUI() {
        category = new DataListComboBox(mcreator, ElementUtils.loadCategories(mcreator.getWorkspace(), (String) editor.getSelectedItem()));
        category.addActionListener(e -> {
            category.removeAllItems();
            ElementUtils.loadCategories(mcreator.getWorkspace(), (String) editor.getSelectedItem()).forEach(de -> category.addItem(de));
        });

        JPanel northProperties = new JPanel(new GridLayout(5, 2, 10, 10));
        northProperties.setOpaque(false);

        northProperties.add(HelpUtils.wrapWithHelpButton(this.withEntry("blockly/editor"), L10N.label("elementgui.blockly.editor")));
        northProperties.add(editor);

        northProperties.add(HelpUtils.wrapWithHelpButton(this.withEntry("blockly_block/text"), L10N.label("elementgui.blockly_block.text")));
        northProperties.add(text);

        northProperties.add(HelpUtils.wrapWithHelpButton(this.withEntry("blockly/hex_color"), L10N.label("elementgui.blockly.hex_color")));
        northProperties.add(hexColor);

        northProperties.add(HelpUtils.wrapWithHelpButton(this.withEntry("blockly_block/category"), L10N.label("elementgui.blockly_block.category")));
        northProperties.add(category);

        northProperties.add(HelpUtils.wrapWithHelpButton(this.withEntry("blockly_block/block_type"), L10N.label("elementgui.blockly_block.block_type")));
        northProperties.add(blockType);
        blockType.addActionListener(e -> updateSpecificPanelUI());

        JPanel normalPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        normalPanel.setOpaque(false);
        normalPanel.add(HelpUtils.wrapWithHelpButton(this.withEntry("blockly_block/has_next_statement"), L10N.label("elementgui.blockly_block.has_next_statement")));
        normalPanel.add(hasNextStatement);
        hasNextStatement.setOpaque(false);

        JPanel outputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        outputPanel.setOpaque(false);
        outputPanel.add(HelpUtils.wrapWithHelpButton(this.withEntry("blockly_block/output_type"), L10N.label("elementgui.blockly_block.output_type")));
        outputPanel.add(outputType);

        specificPropertiesPanel.add(normalPanel, "Normal");
        specificPropertiesPanel.add(outputPanel, "Output");
        specificPropertiesPanel.setOpaque(false);

        dependencies = new JDependencyEntriesList(mcreator, this);

        codeTemplates = new JCodeTemplatesList(mcreator, this);

        addPage(L10N.t("elementgui.common.page_properties"), PanelUtils.northAndCenterElement(PanelUtils.totalCenterInPanel(PanelUtils.northAndCenterElement(northProperties, specificPropertiesPanel, 10, 10)), dependencies));
        addPage(L10N.t("elementgui.common.code_templates"), codeTemplates);

        if (!isEditingMode()) {
            hasNextStatement.setSelected(true);
            updateSpecificPanelUI();
        }
    }

    private void updateSpecificPanelUI() {
        blockTypeCL.show(specificPropertiesPanel, (String) blockType.getSelectedItem());
    }

    @Override
    protected AggregatedValidationResult validatePage(int i) {
        return new AggregatedValidationResult.PASS();
    }

    @Override
    protected void openInEditingMode(BlocklyBlock block) {
        editor.setSelectedItem(block.editor);
        text.setText(block.text);
        hexColor.setColor(Color.decode(block.hexColor));
        category.setSelectedItem(block.category.getUnmappedValue());
        blockType.setSelectedItem(block.blockType);
        outputType.setSelectedItem(block.outputType);
        hasNextStatement.setSelected(block.hasNextStatement);
        codeTemplates.setCodeTemplates(block.codeTemplates);
        dependencies.setDependencies(block.dependencies);
    }

    @Override
    public BlocklyBlock getElementFromGUI() {
        BlocklyBlock block = new BlocklyBlock(modElement);
        block.editor = (String) editor.getSelectedItem();
        block.text = text.getText();
        block.hexColor = String.format("#%02x%02x%02x", hexColor.getColor().getRed(), hexColor.getColor().getGreen(), hexColor.getColor().getBlue());
        block.category = new BlocklyCategory(mcreator.getWorkspace(), category.getSelectedItem());
        block.blockType = (String) blockType.getSelectedItem();
        block.outputType = (String) outputType.getSelectedItem();
        block.hasNextStatement = hasNextStatement.isSelected();
        block.codeTemplates = codeTemplates.getCodeTemplates();
        block.dependencies = dependencies.getDependencies();
        return block;
    }
}
