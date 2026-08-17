package com.android.apksig.apk;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class CodenameMinSdkVersionException extends MinSdkVersionException {
    private static final long serialVersionUID = 1;
    private final String mCodename;

    public CodenameMinSdkVersionException(String str, String str2) {
        super(str);
        this.mCodename = str2;
    }

    public String getCodename() {
        return this.mCodename;
    }
}
