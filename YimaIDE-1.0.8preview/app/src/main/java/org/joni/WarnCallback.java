package org.joni;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public interface WarnCallback {
    public static final WarnCallback DEFAULT = new WarnCallback() { // from class: org.joni.WarnCallback.1
        @Override // org.joni.WarnCallback
        public void warn(String str) {
            System.err.println(str);
        }
    };
    public static final WarnCallback NONE = new WarnCallback() { // from class: org.joni.WarnCallback.2
        @Override // org.joni.WarnCallback
        public void warn(String str) {
        }
    };

    void warn(String str);
}
