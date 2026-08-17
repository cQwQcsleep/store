package com.reandroid.dex.common;

import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.StringsUtil;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Modifier implements SmaliFormat {
    private final int hash;
    private final String name;
    private final int value;

    public Modifier(int i, String str) {
        this.value = i;
        this.name = str;
        this.hash = str.hashCode() + (i * 31);
    }

    public static void append(SmaliWriter smaliWriter, Modifier[] modifierArr) throws IOException {
        if (modifierArr == null) {
            return;
        }
        for (Modifier modifier : modifierArr) {
            if (modifier != null) {
                smaliWriter.append((CharSequence) modifier.getName());
                smaliWriter.append(' ');
            }
        }
    }

    public static int combineValues(Modifier[] modifierArr) {
        if (modifierArr == null) {
            return 0;
        }
        int value = 0;
        for (Modifier modifier : modifierArr) {
            if (modifier != null) {
                value |= modifier.getValue();
            }
        }
        return value;
    }

    public static boolean contains(Modifier[] modifierArr, Modifier modifier) {
        if (modifierArr != null && modifier != null) {
            for (Modifier modifier2 : modifierArr) {
                if (modifier == modifier2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String toString(Modifier[] modifierArr) {
        if (modifierArr == null) {
            return StringsUtil.EMPTY;
        }
        StringBuilder sb = new StringBuilder();
        for (Modifier modifier : modifierArr) {
            if (modifier != null) {
                sb.append(modifier.getName());
                sb.append(' ');
            }
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public String getName() {
        return this.name;
    }

    public int getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.hash;
    }

    public abstract boolean isSet(int i);

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) getName());
        smaliWriter.append(' ');
    }

    public static void append(SmaliWriter smaliWriter, Iterator<? extends Modifier> it) throws IOException {
        while (it.hasNext()) {
            smaliWriter.append((CharSequence) it.next().getName());
            smaliWriter.append(' ');
        }
    }

    public static String toString(Iterator<? extends Modifier> it) {
        StringBuilder sb = new StringBuilder();
        while (it.hasNext()) {
            sb.append(it.next().getName());
            sb.append(' ');
        }
        return sb.toString();
    }

    public String toString() {
        return getName();
    }
}
