package com.reandroid.arsc.model;

import com.reandroid.common.Namespace;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ResourceLibrary extends Namespace {
    static boolean packageNameMatches(ResourceLibrary resourceLibrary, String str) {
        if (str == null) {
            return false;
        }
        String str2 = Namespace.PREFIX_ANDROID;
        if (str2.equals(resourceLibrary.getName())) {
            return str2.equals(str);
        }
        return str.equals(resourceLibrary.getName()) || str.equals(resourceLibrary.getPrefix());
    }

    static String toPrefix(String str) {
        if (str == null) {
            return null;
        }
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf > 0 ? str.substring(iLastIndexOf + 1) : str;
    }

    int getId();

    String getName();

    boolean packageNameMatches(String str);
}
