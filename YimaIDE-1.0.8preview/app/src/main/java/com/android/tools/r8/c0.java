package com.android.tools.r8;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract /* synthetic */ class c0 {
    public static final /* synthetic */ int[] a;

    static {
        int[] iArr = new int[AndroidResourceInput.Kind.values().length];
        a = iArr;
        try {
            iArr[AndroidResourceInput.Kind.MANIFEST.ordinal()] = 1;
        } catch (NoSuchFieldError unused) {
        }
        try {
            a[AndroidResourceInput.Kind.UNKNOWN.ordinal()] = 2;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            a[AndroidResourceInput.Kind.RESOURCE_TABLE.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            a[AndroidResourceInput.Kind.RES_FOLDER_FILE.ordinal()] = 4;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            a[AndroidResourceInput.Kind.XML_FILE.ordinal()] = 5;
        } catch (NoSuchFieldError unused5) {
        }
    }
}
