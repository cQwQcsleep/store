package org.eclipse.tm4e.core.internal.utils;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class ScopeNames {
    public static final char CONTRIBUTOR_SEPARATOR = '@';

    private ScopeNames() {
    }

    public static String getContributor(String str) {
        int iIndexOf = str.indexOf(64);
        return iIndexOf == -1 ? "" : str.substring(iIndexOf + 1);
    }

    public static boolean hasContributor(String str) {
        return str.indexOf(64) > -1;
    }

    public static String withoutContributor(String str) {
        int iIndexOf = str.indexOf(64);
        return iIndexOf == -1 ? str : str.substring(0, iIndexOf);
    }
}
