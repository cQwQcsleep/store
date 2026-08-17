package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.chunk.UnknownChunk;
import com.reandroid.arsc.header.HeaderBlock;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.utils.collection.ArrayCollection;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
class ElementChunkReader {
    private final ResXmlElementChunk elementChunk;
    private boolean startElementRead = false;
    private boolean endElementRead = false;
    private ArrayCollection<ResXmlEndNamespace> endNamespaceStack = new ArrayCollection<>();

    public ElementChunkReader(ResXmlElementChunk resXmlElementChunk) {
        this.elementChunk = resXmlElementChunk;
    }

    private Block getNext(HeaderBlock headerBlock) throws IOException {
        if (this.startElementRead) {
            return !this.endElementRead ? onElementNotEnded(headerBlock) : onElementEnded(headerBlock);
        }
        return onElementNotStarted(headerBlock);
    }

    private boolean hasEndNamespaceStack() {
        ArrayCollection<ResXmlEndNamespace> arrayCollection = this.endNamespaceStack;
        return (arrayCollection == null || arrayCollection.isEmpty()) ? false : true;
    }

    private Block newDiscardingChunk() {
        return new UnknownChunk();
    }

    private ResXmlStartNamespace newNamespace() {
        ResXmlStartNamespace resXmlStartNamespaceCreateNext = this.elementChunk.getStartNamespaceList().createNext();
        putEndNamespace(resXmlStartNamespaceCreateNext.getEnd());
        return resXmlStartNamespaceCreateNext;
    }

    private Block onElementEnded(HeaderBlock headerBlock) {
        if (hasEndNamespaceStack() && headerBlock.getChunkType() == ChunkType.XML_END_NAMESPACE) {
            return pickEndNamespace();
        }
        return null;
    }

    private Block onElementNotEnded(HeaderBlock headerBlock) {
        ChunkType chunkType = headerBlock.getChunkType();
        if (chunkType == ChunkType.XML_END_ELEMENT) {
            this.endElementRead = true;
            return this.elementChunk.getEndElement();
        }
        if (chunkType == ChunkType.XML_END_NAMESPACE) {
            ResXmlEndNamespace resXmlEndNamespacePickEndNamespace = pickEndNamespace();
            return resXmlEndNamespacePickEndNamespace == null ? newDiscardingChunk() : resXmlEndNamespacePickEndNamespace;
        }
        if (chunkType == ChunkType.XML_START_ELEMENT || chunkType == ChunkType.XML_START_NAMESPACE) {
            return this.elementChunk.element().newElement();
        }
        if (chunkType == ChunkType.XML_CDATA) {
            return this.elementChunk.element().newText();
        }
        ChunkType chunkType2 = ChunkType.XML;
        ResXmlElementChunk resXmlElementChunk = this.elementChunk;
        return chunkType == chunkType2 ? resXmlElementChunk.element().newDocument() : resXmlElementChunk.element().newUnknown();
    }

    private Block onElementNotStarted(HeaderBlock headerBlock) throws IOException {
        ChunkType chunkType = headerBlock.getChunkType();
        if (chunkType == ChunkType.XML_START_ELEMENT) {
            this.startElementRead = true;
            return this.elementChunk.getStartElement();
        }
        if (chunkType == ChunkType.XML_END_ELEMENT) {
            r8g.a("END element detected before START: ", headerBlock);
            return null;
        }
        if (chunkType == ChunkType.XML_START_NAMESPACE) {
            return newNamespace();
        }
        if (chunkType != ChunkType.XML_END_NAMESPACE) {
            if (chunkType == ChunkType.XML_CDATA) {
                return this.elementChunk.element().m66getParentNode().newText();
            }
            r8g.a("Unexpected chunk: ", headerBlock);
            return null;
        }
        ResXmlEndNamespace resXmlEndNamespacePickEndNamespace = pickEndNamespace();
        if (resXmlEndNamespacePickEndNamespace != null) {
            return resXmlEndNamespacePickEndNamespace;
        }
        this.startElementRead = true;
        this.endElementRead = true;
        return newDiscardingChunk();
    }

    private ResXmlEndNamespace pickEndNamespace() {
        ArrayCollection<ResXmlEndNamespace> arrayCollection = this.endNamespaceStack;
        if (arrayCollection == null || arrayCollection.isEmpty()) {
            return null;
        }
        return (ResXmlEndNamespace) arrayCollection.remove(arrayCollection.size() - 1);
    }

    private void putEndNamespace(ResXmlEndNamespace resXmlEndNamespace) {
        ArrayCollection<ResXmlEndNamespace> arrayCollection = this.endNamespaceStack;
        if (arrayCollection == null) {
            arrayCollection = new ArrayCollection<>();
            this.endNamespaceStack = arrayCollection;
        }
        arrayCollection.add(resXmlEndNamespace);
    }

    public void read(BlockReader blockReader) throws IOException {
        Block next;
        while (blockReader.isAvailable() && (next = getNext(blockReader.readHeaderBlock())) != null) {
            next.readBytes(blockReader);
        }
    }
}
