package org.joni;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public final class Option {
    public static final int ASCII_RANGE = 4096;
    public static final int CAPTURE_GROUP = 256;
    public static final int CR_7_BIT = 262144;
    public static final int DEFAULT = 0;
    public static final int DONT_CAPTURE_GROUP = 128;
    public static final int EXTEND = 2;
    public static final int FIND_LONGEST = 16;
    public static final int FIND_NOT_EMPTY = 32;
    public static final int IGNORECASE = 1;
    public static final int MAXBIT = 524288;
    public static final int MULTILINE = 4;
    public static final int NEGATE_SINGLELINE = 64;
    public static final int NEWLINE_CRLF = 32768;
    public static final int NONE = 0;
    public static final int NOTBOL = 512;
    public static final int NOTBOS = 65536;
    public static final int NOTEOL = 1024;
    public static final int NOTEOS = 131072;
    public static final int POSIX_BRACKET_ALL_RANGE = 8192;
    public static final int POSIX_REGION = 2048;
    public static final int SINGLELINE = 8;
    public static final int WORD_BOUND_ALL_RANGE = 16384;

    public static boolean isAsciiRange(int i) {
        return (i & 4096) != 0;
    }

    public static boolean isCR7Bit(int i) {
        return (i & 262144) != 0;
    }

    public static boolean isCaptureGroup(int i) {
        return (i & 256) != 0;
    }

    public static boolean isDontCaptureGroup(int i) {
        return (i & 128) != 0;
    }

    public static boolean isDynamic(int i) {
        return false;
    }

    public static boolean isExtend(int i) {
        return (i & 2) != 0;
    }

    public static boolean isFindCondition(int i) {
        return (i & 48) != 0;
    }

    public static boolean isFindLongest(int i) {
        return (i & 16) != 0;
    }

    public static boolean isFindNotEmpty(int i) {
        return (i & 32) != 0;
    }

    public static boolean isIgnoreCase(int i) {
        return (i & 1) != 0;
    }

    public static boolean isMultiline(int i) {
        return (i & 4) != 0;
    }

    public static boolean isNegateSingleline(int i) {
        return (i & 64) != 0;
    }

    public static boolean isNewlineCRLF(int i) {
        return (i & 32768) != 0;
    }

    public static boolean isNotBol(int i) {
        return (i & 512) != 0;
    }

    public static boolean isNotEol(int i) {
        return (i & 1024) != 0;
    }

    public static boolean isPosixBracketAllRange(int i) {
        return (i & 8192) != 0;
    }

    public static boolean isPosixRegion(int i) {
        return (i & 2048) != 0;
    }

    public static boolean isSingleline(int i) {
        return (i & 8) != 0;
    }

    public static boolean isWordBoundAllRange(int i) {
        return (i & 16384) != 0;
    }

    public static String toString(int i) {
        String strConcat = isIgnoreCase(i) ? "IGNORECASE" : "";
        if (isExtend(i)) {
            strConcat = strConcat.concat("EXTEND");
        }
        if (isMultiline(i)) {
            strConcat = strConcat.concat("MULTILINE");
        }
        if (isSingleline(i)) {
            strConcat = strConcat.concat("SINGLELINE");
        }
        if (isFindLongest(i)) {
            strConcat = strConcat.concat("FIND_LONGEST");
        }
        if (isFindNotEmpty(i)) {
            strConcat = strConcat.concat("FIND_NOT_EMPTY");
        }
        if (isNegateSingleline(i)) {
            strConcat = strConcat.concat("NEGATE_SINGLELINE");
        }
        if (isDontCaptureGroup(i)) {
            strConcat = strConcat.concat("DONT_CAPTURE_GROUP");
        }
        if (isCaptureGroup(i)) {
            strConcat = strConcat.concat("CAPTURE_GROUP");
        }
        if (isNotBol(i)) {
            strConcat = strConcat.concat("NOTBOL");
        }
        if (isNotEol(i)) {
            strConcat = strConcat.concat("NOTEOL");
        }
        if (isPosixRegion(i)) {
            strConcat = strConcat.concat("POSIX_REGION");
        }
        return isCR7Bit(i) ? strConcat.concat("CR_7_BIT") : strConcat;
    }
}
