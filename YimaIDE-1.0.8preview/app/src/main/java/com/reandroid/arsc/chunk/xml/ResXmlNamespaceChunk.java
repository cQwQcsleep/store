package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.item.ResXmlString;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
abstract class ResXmlNamespaceChunk extends BaseXmlChunk implements ResXmlNamespace {
    public ResXmlNamespaceChunk(ChunkType chunkType) {
        super(chunkType, 0);
    }

    public String getPrefix() {
        return getString(getPrefixReference());
    }

    public int getPrefixReference() {
        return getNamespaceReference();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public String getUri() {
        return getString(getUriReference());
    }

    public int getUriReference() {
        return getStringReference();
    }

    public boolean isRemoved() {
        return getParent() == null;
    }

    public boolean isUnused() {
        return isRemoved();
    }

    public void onUriReferenceChanged(int i) {
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public void setLineNumber(int i) {
        super.setLineNumber(i);
    }

    public void setPrefix(String str) {
        int index;
        if (str == null) {
            index = BaseXmlChunk.NULL_REFERENCE;
        } else {
            ResXmlString resXmlString = getResXmlString(getUriReference());
            ResXmlString namespacePrefix = resXmlString != null ? resXmlString.getNamespacePrefix() : null;
            if (namespacePrefix == null) {
                namespacePrefix = getOrCreateString(str);
            }
            if (namespacePrefix == null) {
                w01.a("Null ResXmlString, add to parent element first");
                return;
            }
            index = namespacePrefix.getIndex();
        }
        setPrefixReference(index);
    }

    public void setPrefixReference(int i) {
        setNamespaceReference(i);
    }

    public void setUri(String str) {
        int index;
        if (str == null) {
            index = BaseXmlChunk.NULL_REFERENCE;
        } else {
            ResXmlString orCreateString = getOrCreateString(str);
            if (orCreateString == null) {
                w01.a("Null ResXmlString, add to parent element first");
                return;
            }
            index = orCreateString.getIndex();
        }
        setUriReference(index);
    }

    public void setUriReference(int i) {
        int uriReference = getUriReference();
        setStringReference(i);
        if (uriReference != i) {
            onUriReferenceChanged(i);
        }
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk, com.reandroid.arsc.chunk.Chunk
    public String toString() {
        return "xmlns:" + getPrefix() + "=\"" + getUri() + "\"";
    }
}
