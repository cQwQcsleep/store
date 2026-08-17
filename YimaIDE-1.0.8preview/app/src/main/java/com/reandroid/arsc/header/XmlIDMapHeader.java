package com.reandroid.arsc.header;

import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.item.IntegerReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class XmlIDMapHeader extends HeaderBlock {
    private final IntegerReference idsCount;

    public static class CountReference implements IntegerReference {
        private final XmlIDMapHeader header;

        public CountReference(XmlIDMapHeader xmlIDMapHeader) {
            this.header = xmlIDMapHeader;
        }

        @Override // com.reandroid.arsc.item.IntegerReference
        public int get() {
            return (this.header.getChunkSize() - this.header.getHeaderSize()) / 4;
        }

        @Override // com.reandroid.arsc.item.IntegerReference
        public void set(int i) {
            XmlIDMapHeader xmlIDMapHeader = this.header;
            xmlIDMapHeader.setChunkSize((i * 4) + xmlIDMapHeader.getHeaderSize());
        }

        public String toString() {
            return Integer.toString(get());
        }
    }

    public XmlIDMapHeader() {
        super(ChunkType.XML_RESOURCE_MAP);
        this.idsCount = new CountReference(this);
    }

    public IntegerReference getIdsCount() {
        return this.idsCount;
    }
}
