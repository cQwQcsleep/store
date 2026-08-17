package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.utils.CompareUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlEndNamespace extends ResXmlNamespaceChunk implements Comparable<ResXmlEndNamespace> {
    private ResXmlStartNamespace mStartNamespace;

    public ResXmlEndNamespace() {
        super(ChunkType.XML_END_NAMESPACE);
    }

    @Override // java.lang.Comparable
    public int compareTo(ResXmlEndNamespace resXmlEndNamespace) {
        if (resXmlEndNamespace == this) {
            return 0;
        }
        return CompareUtil.compareUnsigned(resXmlEndNamespace.getStartIndex(), getStartIndex());
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ String getComment() {
        return super.getComment();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ int getCommentReference() {
        return super.getCommentReference();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ int getLineNumber() {
        return super.getLineNumber();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ String getName() {
        return super.getName();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ ResXmlElement getNodeElement() {
        return super.getNodeElement();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.common.Namespace
    public /* bridge */ /* synthetic */ String getPrefix() {
        return super.getPrefix();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk
    public /* bridge */ /* synthetic */ int getPrefixReference() {
        return super.getPrefixReference();
    }

    public ResXmlStartNamespace getStart() {
        return this.mStartNamespace;
    }

    public int getStartIndex() {
        return getStart().getIndex();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ String getUri() {
        return super.getUri();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.arsc.chunk.xml.ResXmlNamespace
    public /* bridge */ /* synthetic */ int getUriReference() {
        return super.getUriReference();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk
    public /* bridge */ /* synthetic */ boolean isRemoved() {
        return super.isRemoved();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.arsc.chunk.xml.ResXmlNamespace
    public /* bridge */ /* synthetic */ boolean isUnused() {
        return super.isUnused();
    }

    @Override // com.reandroid.arsc.chunk.Chunk, com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        if (blockReader.readHeaderBlock().getChunkSize() < 8) {
            super.onReadChildes(blockReader);
        } else {
            super.onReadBytes(blockReader);
        }
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ void setComment(String str) {
        super.setComment(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ void setCommentReference(int i) {
        super.setCommentReference(i);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ void setLineNumber(int i) {
        super.setLineNumber(i);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.arsc.chunk.xml.ResXmlNamespace
    public /* bridge */ /* synthetic */ void setPrefix(String str) {
        super.setPrefix(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk
    public /* bridge */ /* synthetic */ void setPrefixReference(int i) {
        super.setPrefixReference(i);
    }

    public void setStart(ResXmlStartNamespace resXmlStartNamespace) {
        if (resXmlStartNamespace.getEnd() == this) {
            this.mStartNamespace = resXmlStartNamespace;
        } else {
            aca.a("Invalid start namespace: ", resXmlStartNamespace);
        }
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.arsc.chunk.xml.ResXmlNamespace
    public /* bridge */ /* synthetic */ void setUri(String str) {
        super.setUri(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.arsc.chunk.xml.BaseXmlChunk, com.reandroid.arsc.chunk.Chunk
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
