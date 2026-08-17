package com.reandroid.arsc.header;

import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.item.ByteItem;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.ShortItem;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StringPoolHeader extends HeaderBlock {
    private final IntegerItem countStrings;
    private final IntegerItem countStyles;
    private EncodingChangedListener encodingChangedListener;
    private final ShortItem flagExtra;
    private final ByteItem flagSorted;
    private final ByteItem flagUtf8;
    private final IntegerItem startStrings;
    private final IntegerItem startStyles;

    public interface EncodingChangedListener {
        void onEncodingChanged(boolean z);
    }

    public StringPoolHeader() {
        super(ChunkType.STRING.ID);
        IntegerItem integerItem = new IntegerItem();
        this.countStrings = integerItem;
        IntegerItem integerItem2 = new IntegerItem();
        this.countStyles = integerItem2;
        ByteItem byteItem = new ByteItem();
        this.flagSorted = byteItem;
        ByteItem byteItem2 = new ByteItem();
        this.flagUtf8 = byteItem2;
        ShortItem shortItem = new ShortItem();
        this.flagExtra = shortItem;
        IntegerItem integerItem3 = new IntegerItem();
        this.startStrings = integerItem3;
        IntegerItem integerItem4 = new IntegerItem();
        this.startStyles = integerItem4;
        addChild(integerItem);
        addChild(integerItem2);
        addChild(byteItem);
        addChild(byteItem2);
        addChild(shortItem);
        addChild(integerItem3);
        addChild(integerItem4);
    }

    public IntegerItem getCountStrings() {
        return this.countStrings;
    }

    public IntegerItem getCountStyles() {
        return this.countStyles;
    }

    public ShortItem getFlagExtra() {
        return this.flagExtra;
    }

    public ByteItem getFlagSorted() {
        return this.flagSorted;
    }

    public ByteItem getFlagUtf8() {
        return this.flagUtf8;
    }

    public IntegerItem getStartStrings() {
        return this.startStrings;
    }

    public IntegerItem getStartStyles() {
        return this.startStyles;
    }

    public boolean isSorted() {
        return (getFlagSorted().getByte() & 1) != 0;
    }

    public boolean isUtf8() {
        return (getFlagUtf8().getByte() & 1) != 0;
    }

    public void setEncodingChangedListener(EncodingChangedListener encodingChangedListener) {
        this.encodingChangedListener = encodingChangedListener;
    }

    public void setSorted(boolean z) {
        getFlagSorted().set(z ? (byte) 1 : (byte) 0);
    }

    public void setUtf8(boolean z) {
        EncodingChangedListener encodingChangedListener;
        boolean z2 = isUtf8() != z;
        setUtf8Flag(z);
        if (!z2 || (encodingChangedListener = this.encodingChangedListener) == null) {
            return;
        }
        encodingChangedListener.onEncodingChanged(z);
    }

    public void setUtf8Flag(boolean z) {
        getFlagUtf8().set(z ? (byte) 1 : (byte) 0);
    }

    public String toString() {
        if (getChunkType() != ChunkType.STRING) {
            return super.toString();
        }
        return getClass().getSimpleName() + " {strings=" + getCountStrings() + ", styles=" + getCountStyles() + ", utf8=" + isUtf8() + ", sorted=" + isSorted() + ", flagExtra=" + getFlagExtra().toHex() + ", offset-strings=" + getStartStrings().get() + ", offset-styles=" + getStartStyles().get() + '}';
    }
}
