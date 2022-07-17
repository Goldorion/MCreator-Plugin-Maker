package net.goldorion.pluginmaker.ui.mcreator.dependencies;

import net.mcreator.blockly.data.Dependency;
import net.mcreator.ui.component.util.PanelUtils;
import net.mcreator.ui.help.HelpUtils;
import net.mcreator.ui.help.IHelpContext;
import net.mcreator.ui.init.L10N;
import net.mcreator.ui.init.UIRES;
import net.mcreator.ui.validation.component.VTextField;
import net.mcreator.ui.validation.validators.TextFieldValidator;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class JDependencyListEntry extends JPanel {

    private final VTextField type = new VTextField(20);
    private final VTextField name = new VTextField(20);

    public JDependencyListEntry(IHelpContext gui, JPanel parent, List<JDependencyListEntry> entryList) {
        super(new FlowLayout(FlowLayout.LEFT));

        final JComponent container = PanelUtils.expandHorizontally(this);

        parent.add(container);
        entryList.add(this);

        add(HelpUtils.wrapWithHelpButton(gui.withEntry("dependencies/type"),
                L10N.label("dialog.dependency_list.type")));
        add(type);

        add(HelpUtils.wrapWithHelpButton(gui.withEntry("dependencies/name"),
                L10N.label("dialog.dependency_list.name")));
        add(name);
        name.setValidator(new TextFieldValidator(name, L10N.t("dialog.dependency_list.error_needs_name")));
        name.enableRealtimeValidation();

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

    public Dependency getDependency() {
        return new Dependency(type.getText(), name.getText());
    }

    public void setDependency(Dependency dependency) {
        type.setText(dependency.getRawType());
        name.setText(dependency.getName());
    }
}
