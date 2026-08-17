package com.reandroid.json;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JSONException extends IllegalArgumentException {
    private static final long serialVersionUID = 0;

    public JSONException(Throwable th) {
        super(th.getMessage(), th);
    }

    public JSONException(String str, Throwable th) {
        super(str, th);
    }

    public JSONException(String str) {
        super(str);
    }
}
