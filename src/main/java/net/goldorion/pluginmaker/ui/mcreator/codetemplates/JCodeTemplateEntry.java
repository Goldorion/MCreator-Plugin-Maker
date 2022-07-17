package net.goldorion.pluginmaker.ui.mcreator.codetemplates;

import net.goldorion.pluginmaker.elements.parts.CodeTemplate;
import net.mcreator.ui.component.util.PanelUtils;
import net.mcreator.ui.help.HelpUtils;
import net.mcreator.ui.help.IHelpContext;
import net.mcreator.ui.ide.RSyntaxTextAreaStyler;
import net.mcreator.ui.init.L10N;
import net.mcreator.ui.init.UIRES;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class JCodeTemplateEntry extends JPanel {

    private final JComboBox<String> generator = new JComboBox<>(new String[]{ "forge-1.16.5", "forge-1.18.2", "fabric-1.17.1" });

    private final RSyntaxTextArea code = new RSyntaxTextArea();

    public JCodeTemplateEntry(IHelpContext gui, JPanel parent, List<JCodeTemplateEntry> entryList) {
        super(new FlowLayout(FlowLayout.LEFT));

        final JComponent container = PanelUtils.expandHorizontally(this);

        parent.add(container);
        entryList.add(this);

        add(HelpUtils.wrapWithHelpButton(gui.withEntry("code_template/generator"),
                L10N.label("elementgui.code_template.generator")));
        add(generator);

        code.setText("// Enter the FTL code here");

        RTextScrollPane sp = new RTextScrollPane(code, true);

        RSyntaxTextAreaStyler.style(code, sp, 14);
        LanguageSupportFactory.get().register(code);

        code.requestFocusInWindow();
        code.setMarkOccurrences(true);
        code.setCodeFoldingEnabled(false);
        code.setClearWhitespaceLinesEnabled(true);
        code.setAutoIndentEnabled(false);
        code.setTabSize(4);
        code.setTabsEmulated(false);

        sp.setFoldIndicatorEnabled(true);
        sp.getGutter().setFoldBackground((Color) UIManager.get("MCreatorLAF.DARK_ACCENT"));
        sp.getGutter().setBorderColor((Color) UIManager.get("MCreatorLAF.DARK_ACCENT"));
        sp.getGutter().setBackground((Color) UIManager.get("MCreatorLAF.DARK_ACCENT"));
        sp.getGutter().setBookmarkingEnabled(true);
        sp.setIconRowHeaderEnabled(false);
        sp.setBackground((Color) UIManager.get("MCreatorLAF.DARK_ACCENT"));
        sp.setBorder(new LineBorder((Color) UIManager.get("MCreatorLAF.LIGHT_ACCENT")));
        sp.setPreferredSize(new Dimension(900, 200));

        code.setSyntaxEditingStyle("text/txt");

        add(HelpUtils.wrapWithHelpButton(gui.withEntry("code_template/code"),
                L10N.label("elementgui.code_template.code")));
        add(PanelUtils.totalCenterInPanel(sp));

        JButton remove = new JButton(UIRES.get("16px.clear"));
        remove.setText(L10N.t("dialog.common.remove_entry"));
        remove.addActionListener(e -> {
            entryList.remove(this);
            parent.remove(container);
            parent.revalidate();
            parent.repaint();
        });
        add(remove);

        parent.revalidate();
        parent.repaint();
    }

    public CodeTemplate getCodeTemplate() {
        return new CodeTemplate((String) generator.getSelectedItem(), code.getText());
    }

    public void setCodeTemplate(CodeTemplate codeTemplate) {
        this.generator.setSelectedItem(codeTemplate.generator);
        this.code.setText(codeTemplate.code);
    }
}
