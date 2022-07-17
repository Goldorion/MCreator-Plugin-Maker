package net.goldorion.pluginmaker.ui.mcreator.codetemplates;

import net.goldorion.pluginmaker.elements.parts.CodeTemplate;
import net.mcreator.ui.MCreator;
import net.mcreator.ui.component.util.PanelUtils;
import net.mcreator.ui.help.IHelpContext;
import net.mcreator.ui.init.L10N;
import net.mcreator.ui.minecraft.JEntriesList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JCodeTemplatesList extends JEntriesList {

    private final List<JCodeTemplateEntry> entryList = new ArrayList<>();

    private final JPanel entries = new JPanel();

    public JCodeTemplatesList(MCreator mcreator, IHelpContext gui) {
        super(mcreator, new BorderLayout(), gui);
        setOpaque(false);

        JPanel topbar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topbar.setBackground((Color) UIManager.get("MCreatorLAF.LIGHT_ACCENT"));

        add.setText(L10N.t("elementgui.code_template.add_entry"));
        topbar.add(add);

        add("North", topbar);

        entries.setLayout(new BoxLayout(entries, BoxLayout.PAGE_AXIS));
        entries.setOpaque(false);

        add.addActionListener(e -> registerEntryUI(new JCodeTemplateEntry(gui, entries, entryList)));

        add("Center", new JScrollPane(PanelUtils.pullElementUp(entries)));

        setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder((Color) UIManager.get("MCreatorLAF.BRIGHT_COLOR"), 1),
                L10N.t("elementgui.code_template.entries"), 0, 0, getFont().deriveFont(12.0f),
                (Color) UIManager.get("MCreatorLAF.BRIGHT_COLOR")));
        setPreferredSize(new Dimension(getPreferredSize().width, (int) (mcreator.getSize().height * 0.6)));
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);

        add.setEnabled(false);
    }

    public List<CodeTemplate> getCodeTemplates() {
        return entryList.stream().map(JCodeTemplateEntry::getCodeTemplate).filter(codeTemplate -> codeTemplate != null &&
                codeTemplate.hasCode()).collect(Collectors.toList());
    }

    public void setCodeTemplates(List<CodeTemplate> pool) {
        pool.forEach(e -> {
            JCodeTemplateEntry entry = new JCodeTemplateEntry(gui, entries, entryList);
            registerEntryUI(entry);
            entry.setCodeTemplate(e);
        });
    }
}
