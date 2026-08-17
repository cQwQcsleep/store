package org.jdom2.output;

import org.jdom2.JDOMConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public enum LineSeparator {
    CRNL("\r\n"),
    NL("\n"),
    CR("\r"),
    DOS("\r\n"),
    UNIX("\n"),
    SYSTEM(System.getProperty("line.separator")),
    NONE(null),
    DEFAULT(getDefaultLineSeparator());

    private final String value;

    LineSeparator(String str) {
        this.value = str;
    }

    private static String getDefaultLineSeparator() {
        String property = System.getProperty(JDOMConstants.JDOM2_PROPERTY_LINE_SEPARATOR, "DEFAULT");
        if ("DEFAULT".equals(property)) {
            return DOS.value();
        }
        try {
            return ((LineSeparator) Enum.valueOf(LineSeparator.class, property)).value();
        } catch (Exception unused) {
            return property;
        }
    }

    public String value() {
        return this.value;
    }
}
