package jdk.internal.jimage;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface ImageStrings {
    int add(String str);

    String get(int i);

    default int match(int i, String str, int i2) {
        throw new UnsupportedOperationException();
    }
}
