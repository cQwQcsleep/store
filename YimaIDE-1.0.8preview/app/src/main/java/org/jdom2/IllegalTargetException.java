package org.jdom2;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class IllegalTargetException extends IllegalArgumentException {
    private static final long serialVersionUID = 200;

    public IllegalTargetException(String str, String str2) {
        super("The target \"" + str + "\" is not legal for JDOM/XML Processing Instructions: " + str2 + ".");
    }

    public IllegalTargetException(String str) {
        super(str);
    }
}
