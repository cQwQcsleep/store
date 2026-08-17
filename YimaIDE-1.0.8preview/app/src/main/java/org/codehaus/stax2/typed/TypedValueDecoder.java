package org.codehaus.stax2.typed;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public abstract class TypedValueDecoder {
    public abstract void decode(String str) throws IllegalArgumentException;

    public abstract void decode(char[] cArr, int i, int i2) throws IllegalArgumentException;

    public abstract void handleEmptyValue() throws IllegalArgumentException;
}
