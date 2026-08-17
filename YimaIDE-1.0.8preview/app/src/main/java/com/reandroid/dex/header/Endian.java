package com.reandroid.dex.header;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Endian extends HeaderPiece {
    public static final int BIG = 2018915346;
    public static final int LITTLE = 305419896;

    public Endian() {
        super(12);
        putInteger(0, LITTLE);
    }

    public int getType() {
        return getInteger(0);
    }

    public boolean isBig() {
        return getType() == 2018915346;
    }

    public boolean isLittle() {
        return getType() == 305419896;
    }

    public void setType(int i) {
        setSize(12);
        putInteger(0, i);
        putInteger(4, 0);
        putInteger(8, 0);
    }

    @Override // com.reandroid.dex.header.HeaderPiece, com.reandroid.arsc.item.ByteArray
    public String toString() {
        if (isBig()) {
            return "BIG";
        }
        return isLittle() ? "LITTLE" : HeaderPiece.printHex(getBytesInternal());
    }
}
