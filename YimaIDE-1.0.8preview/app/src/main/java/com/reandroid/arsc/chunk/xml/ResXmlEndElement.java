package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.chunk.ChunkType;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlEndElement extends BaseXmlChunk {
    public ResXmlEndElement() {
        super(ChunkType.XML_END_ELEMENT, 0);
    }

    private ResXmlStartElement getStartElement() {
        ResXmlElementChunk resXmlElementChunk = (ResXmlElementChunk) getParentInstance(ResXmlElementChunk.class);
        if (resXmlElementChunk != null) {
            return resXmlElementChunk.getStartElement();
        }
        return null;
    }

    private void syncWithStartElement() {
        ResXmlStartElement startElement = getStartElement();
        if (startElement == null) {
            return;
        }
        setStringReference(startElement.getStringReference());
        setNamespaceReference(startElement.getNamespaceReference());
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
    public String getName() {
        return super.getName();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ ResXmlElement getNodeElement() {
        return super.getNodeElement();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ String getUri() {
        return super.getUri();
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void onPreRefresh() {
        super.onPreRefresh();
        syncWithStartElement();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ void setComment(String str) {
        super.setComment(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ void setCommentReference(int i) {
        super.setCommentReference(i);
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ void setLineNumber(int i) {
        super.setLineNumber(i);
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk, com.reandroid.arsc.chunk.Chunk
    public String toString() {
        return "</" + getName() + ">";
    }
}
