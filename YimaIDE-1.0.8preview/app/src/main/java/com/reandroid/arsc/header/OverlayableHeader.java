package com.reandroid.arsc.header;

import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.item.FixedLengthString;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class OverlayableHeader extends HeaderBlock {
    private final FixedLengthString actor;
    private final FixedLengthString name;

    public OverlayableHeader() {
        super(ChunkType.OVERLAYABLE.ID);
        FixedLengthString fixedLengthString = new FixedLengthString(512);
        this.name = fixedLengthString;
        FixedLengthString fixedLengthString2 = new FixedLengthString(512);
        this.actor = fixedLengthString2;
        addChild(fixedLengthString);
        addChild(fixedLengthString2);
    }

    public FixedLengthString getActor() {
        return this.actor;
    }

    public FixedLengthString getName() {
        return this.name;
    }

    @Override // com.reandroid.arsc.header.HeaderBlock
    public String toString() {
        if (getChunkType() != ChunkType.OVERLAYABLE) {
            return super.toString();
        }
        return getClass().getSimpleName() + " {count=" + getName() + ", actor=" + getActor() + '}';
    }
}
