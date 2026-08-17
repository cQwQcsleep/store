package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.ResXmlString;
import com.reandroid.arsc.pool.ResXmlStringPool;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlTextChunk extends BaseXmlChunk {
    private final IntegerItem mReserved;

    public ResXmlTextChunk() {
        super(ChunkType.XML_CDATA, 1);
        IntegerItem integerItem = new IntegerItem();
        this.mReserved = integerItem;
        addChild(integerItem);
        setStringReference(0);
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

    public String getText() {
        ResXmlString resXmlString = getResXmlString(getTextReference());
        if (resXmlString != null) {
            return resXmlString.getXml();
        }
        return null;
    }

    public int getTextReference() {
        return getNamespaceReference();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ String getUri() {
        return super.getUri();
    }

    @Override // com.reandroid.arsc.base.Block
    public boolean isNull() {
        return (getComment() == null && getText() == null) || super.isNull();
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void onPreRefresh() {
        if (getComment() != null && getText() == null) {
            setText(XmlPullParser.NO_NAMESPACE);
        }
        super.onPreRefresh();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public void setComment(String str) {
        super.setComment(str);
        if (str == null || getText() != null) {
            return;
        }
        setText(XmlPullParser.NO_NAMESPACE);
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ void setCommentReference(int i) {
        super.setCommentReference(i);
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ void setLineNumber(int i) {
        super.setLineNumber(i);
    }

    public void setText(String str) {
        ResXmlStringPool stringPool = getStringPool();
        if (stringPool == null) {
            return;
        }
        setTextReference(stringPool.getOrCreate(str).getIndex());
    }

    public void setTextReference(int i) {
        setNamespaceReference(i);
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk, com.reandroid.arsc.chunk.Chunk
    public String toString() {
        String text = getText();
        return text != null ? text : super.toString();
    }
}
