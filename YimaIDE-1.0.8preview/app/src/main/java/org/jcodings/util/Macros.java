package org.jcodings.util;

import org.eclipse.jdt.internal.compiler.codegen.ConstantPool;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class Macros {
    public static final int MBCLEN_INVALID = -1;

    public static int CONSTRUCT_MBCLEN_CHARFOUND(int i) {
        return i;
    }

    public static int CONSTRUCT_MBCLEN_INVALID() {
        return -1;
    }

    public static int CONSTRUCT_MBCLEN_NEEDMORE(int i) {
        return (-1) - i;
    }

    public static int MBCLEN_CHARFOUND_LEN(int i) {
        return i;
    }

    public static boolean MBCLEN_CHARFOUND_P(int i) {
        return i > 0;
    }

    public static boolean MBCLEN_INVALID_P(int i) {
        return i == -1;
    }

    public static int MBCLEN_NEEDMORE_LEN(int i) {
        return (-1) - i;
    }

    public static boolean MBCLEN_NEEDMORE_P(int i) {
        return i < -1;
    }

    public static boolean UNICODE_VALID_CODEPOINT_P(int i) {
        if (Integer.compareUnsigned(i, 1114111) <= 0) {
            return i >= 65536 || !UTF16_IS_SURROGATE(i >> 8);
        }
        return false;
    }

    public static boolean UTF16_IS_SURROGATE(int i) {
        return (i & ConstantPool.INT_INITIAL_SIZE) == 216;
    }

    public static boolean UTF16_IS_SURROGATE_FIRST(int i) {
        return (i & 252) == 216;
    }

    public static boolean UTF16_IS_SURROGATE_SECOND(int i) {
        return (i & 252) == 220;
    }
}
