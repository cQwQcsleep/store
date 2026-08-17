package com.sun.tools.javap;

import com.sun.tools.classfile.AccessFlags;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Options {
    public boolean fullVersion;
    public boolean help;
    public String moduleName;
    public int showAccess;
    public boolean showAllAttrs;
    public boolean showConstants;
    public boolean showDescriptors;
    public boolean showDisassembled;
    public boolean showFlags;
    public boolean showInnerClasses;
    public boolean showLineAndLocalVariableTables;
    public boolean sysInfo;
    public boolean verbose;
    public boolean version;
    public Set<String> accessOptions = new HashSet();
    public Set<InstructionDetailWriter.Kind> details = EnumSet.noneOf(InstructionDetailWriter.Kind.class);
    public int indentWidth = 2;
    public int tabColumn = 40;

    public Options(Context context) {
        context.put(Options.class, this);
    }

    public static Options instance(Context context) {
        Options options = (Options) context.get(Options.class);
        return options == null ? new Options(context) : options;
    }

    public boolean checkAccess(AccessFlags accessFlags) {
        boolean zIs = accessFlags.is(1);
        boolean zIs2 = accessFlags.is(4);
        boolean zIs3 = accessFlags.is(2);
        boolean z = (zIs || zIs2 || zIs3) ? false : true;
        int i = this.showAccess;
        if (i == 1 && (zIs2 || zIs3 || z)) {
            return false;
        }
        if (i == 4 && (zIs3 || z)) {
            return false;
        }
        return (i == 0 && zIs3) ? false : true;
    }
}
