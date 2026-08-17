package org.eclipse.jdt.internal.compiler.env;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class AccessRestriction {
    public static final byte COMMAND_LINE = 0;
    public static final byte LIBRARY = 2;
    public static final byte PROJECT = 1;
    private final AccessRule accessRule;
    public String classpathEntryName;
    public byte classpathEntryType;

    public AccessRestriction(AccessRule accessRule, byte b, String str) {
        this.accessRule = accessRule;
        this.classpathEntryName = str;
        this.classpathEntryType = b;
    }

    public int getProblemId() {
        return this.accessRule.getProblemId();
    }

    public boolean ignoreIfBetter() {
        return this.accessRule.ignoreIfBetter();
    }
}
