package org.jdom2;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class IllegalNameException extends IllegalArgumentException {
    private static final long serialVersionUID = 200;

    public IllegalNameException(String str, String str2, String str3) {
        super("The name \"" + str + "\" is not legal for JDOM/XML " + str2 + "s: " + str3 + ".");
    }

    public IllegalNameException(String str, String str2) {
        super("The name \"" + str + "\" is not legal for JDOM/XML " + str2 + "s.");
    }

    public IllegalNameException(String str) {
        super(str);
    }
}
