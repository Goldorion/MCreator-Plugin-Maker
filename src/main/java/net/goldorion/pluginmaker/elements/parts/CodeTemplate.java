package net.goldorion.pluginmaker.elements.parts;

public class CodeTemplate {

    public String generator;
    public String code;

    public CodeTemplate(String generator, String code) {
        this.generator = generator;
        this.code = code;
    }

    public boolean hasCode() {
        return code != null && !code.isEmpty();
    }
}
