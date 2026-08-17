package com.reandroid.arsc.header;

import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.item.IntegerItem;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class XmlNodeHeader extends HeaderBlock {
    private final ChunkType chunkType;
    private final IntegerItem commentReference;
    private final IntegerItem lineNumber;

    public XmlNodeHeader(ChunkType chunkType) {
        super(chunkType.ID);
        this.chunkType = chunkType;
        IntegerItem integerItem = new IntegerItem();
        this.lineNumber = integerItem;
        IntegerItem integerItem2 = new IntegerItem(-1);
        this.commentReference = integerItem2;
        addChild(integerItem);
        addChild(integerItem2);
    }

    public IntegerItem getCommentReference() {
        return this.commentReference;
    }

    public IntegerItem getLineNumber() {
        return this.lineNumber;
    }

    @Override // com.reandroid.arsc.header.HeaderBlock
    public String toString() {
        if (getChunkType() != this.chunkType) {
            return super.toString();
        }
        return getClass().getSimpleName() + " {lineNumber=" + getLineNumber() + ", commentReference=" + getCommentReference() + '}';
    }
}
