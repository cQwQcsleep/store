package com.reandroid.dex.base;

import com.reandroid.utils.ObjectsUtil;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface UsageMarker {
    public static final int USAGE_NONE = ObjectsUtil.of(0);
    public static final int USAGE_INSTRUCTION = ObjectsUtil.of(1);
    public static final int USAGE_ENCODED_VALUE = ObjectsUtil.of(2);
    public static final int USAGE_ANNOTATION = ObjectsUtil.of(4);
    public static final int USAGE_TYPE_NAME = ObjectsUtil.of(8);
    public static final int USAGE_SIGNATURE_TYPE = ObjectsUtil.of(16);
    public static final int USAGE_FIELD_NAME = ObjectsUtil.of(32);
    public static final int USAGE_METHOD_NAME = ObjectsUtil.of(64);
    public static final int USAGE_SHORTY = ObjectsUtil.of(512);
    public static final int USAGE_SOURCE = ObjectsUtil.of(256);
    public static final int USAGE_DEBUG = ObjectsUtil.of(512);
    public static final int USAGE_DEFINITION = ObjectsUtil.of(1024);
    public static final int USAGE_SUPER_CLASS = ObjectsUtil.of(2048);
    public static final int USAGE_FIELD_CLASS = ObjectsUtil.of(4096);
    public static final int USAGE_FIELD_TYPE = ObjectsUtil.of(8192);
    public static final int USAGE_METHOD = ObjectsUtil.of(16384);
    public static final int USAGE_PROTO = ObjectsUtil.of(15);
    public static final int USAGE_INTERFACE = ObjectsUtil.of(65536);
    public static final int USAGE_CALL_SITE = ObjectsUtil.of(131072);
    public static final int USAGE_STATIC_VALUES = ObjectsUtil.of(262144);
    public static final int USAGE_METHOD_HANDLE = ObjectsUtil.of(524288);
    public static final int USAGE_MARKER = ObjectsUtil.of(1048576);

    static void clearUsageTypes(Iterator<?> it) {
        while (it.hasNext()) {
            ((UsageMarker) it.next()).clearUsageType();
        }
    }

    static boolean containsUsage(int i, int i2) {
        if (i2 == 0) {
            return i == 0;
        }
        return (i & i2) == i2;
    }

    static String toUsageString(int i) {
        if (containsUsage(i, USAGE_NONE)) {
            return "NONE";
        }
        StringBuilder sb = new StringBuilder();
        if (containsUsage(i, USAGE_INSTRUCTION)) {
            sb.append("INSTRUCTION");
        }
        if (containsUsage(i, USAGE_ENCODED_VALUE)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("ENCODED_VALUE");
        }
        if (containsUsage(i, USAGE_ANNOTATION)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("ANNOTATION");
        }
        if (containsUsage(i, USAGE_TYPE_NAME)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("TYPE_NAME");
        }
        if (containsUsage(i, USAGE_SIGNATURE_TYPE)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("SIGNATURE_TYPE");
        }
        if (containsUsage(i, USAGE_FIELD_NAME)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("FIELD_NAME");
        }
        if (containsUsage(i, USAGE_METHOD_NAME)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("METHOD_NAME");
        }
        if (containsUsage(i, USAGE_SHORTY)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("SHORTY");
        }
        if (containsUsage(i, USAGE_SOURCE)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("SOURCE");
        }
        if (containsUsage(i, USAGE_DEBUG)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("DEBUG");
        }
        if (containsUsage(i, USAGE_DEFINITION)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("DEFINITION");
        }
        if (containsUsage(i, USAGE_SUPER_CLASS)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("SUPER_CLASS");
        }
        if (containsUsage(i, USAGE_FIELD_CLASS)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("FIELD_CLASS");
        }
        if (containsUsage(i, USAGE_FIELD_TYPE)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("FIELD_TYPE");
        }
        if (containsUsage(i, USAGE_METHOD)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("METHOD");
        }
        if (containsUsage(i, USAGE_PROTO)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("PROTO");
        }
        if (containsUsage(i, USAGE_INTERFACE)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("INTERFACE");
        }
        if (containsUsage(i, USAGE_MARKER)) {
            if (sb.length() != 0) {
                sb.append('|');
            }
            sb.append("MARKER");
        }
        return sb.toString();
    }

    void addUsageType(int i);

    void clearUsageType();

    boolean containsUsage(int i);

    int getUsageType();
}
