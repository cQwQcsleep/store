package org.jdom2;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class IllegalDataException extends IllegalArgumentException {
    private static final long serialVersionUID = 200;

    public IllegalDataException(String str, String str2, String str3) {
        super("The data \"" + str + "\" is not legal for a JDOM " + str2 + ": " + str3 + ".");
    }

    public IllegalDataException(String str, String str2) {
        super("The data \"" + str + "\" is not legal for a JDOM " + str2 + ".");
    }

    public IllegalDataException(String str) {
        super(str);
    }
}
