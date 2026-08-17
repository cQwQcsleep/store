package org.codehaus.stax2.typed;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public abstract class TypedArrayDecoder {
    public abstract boolean decodeValue(String str) throws IllegalArgumentException;

    public abstract boolean decodeValue(char[] cArr, int i, int i2) throws IllegalArgumentException;

    public abstract int getCount();

    public abstract boolean hasRoom();
}
