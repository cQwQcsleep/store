package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.item.ResXmlString;
import com.reandroid.arsc.pool.ResXmlStringPool;
import com.reandroid.common.Namespace;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.StringsUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlStartNamespace extends ResXmlNamespaceChunk implements JSONConvert<JSONObject> {
    private final ResXmlEndNamespace mEndNamespace;
    private final Set<ResXmlAttribute> mReferencedAttributes;
    private final Set<ResXmlStartElement> mReferencedElements;

    public ResXmlStartNamespace(ResXmlEndNamespace resXmlEndNamespace) {
        super(ChunkType.XML_START_NAMESPACE);
        this.mEndNamespace = resXmlEndNamespace;
        this.mReferencedAttributes = new HashSet();
        this.mReferencedElements = new HashSet();
    }

    public void addAttributeReference(ResXmlAttribute resXmlAttribute) {
        if (resXmlAttribute != null) {
            this.mReferencedAttributes.add(resXmlAttribute);
        }
    }

    public void addElementReference(ResXmlStartElement resXmlStartElement) {
        if (resXmlStartElement != null) {
            this.mReferencedElements.add(resXmlStartElement);
        }
    }

    public ResXmlElement element() {
        return (ResXmlElement) getParentInstance(ResXmlElement.class);
    }

    public void ensureUniqueUri() {
        ResXmlString namespacePrefix;
        ResXmlString resXmlString = getResXmlString(getUriReference());
        if (resXmlString == null || (namespacePrefix = resXmlString.getNamespacePrefix()) == null || namespacePrefix.getIndex() != getPrefixReference()) {
            set(getUri(), getPrefix());
        }
    }

    public void fixEmpty() {
        if (StringsUtil.isBlank(getPrefix())) {
            setPrefix("ns" + getIndex());
        }
        if (StringsUtil.isBlank(getUri())) {
            setUri(Namespace.URI_RES_AUTO);
        }
    }

    public void fromJson(JSONObject jSONObject) {
        set(jSONObject.getString(ResXmlNode.JSON_uri), jSONObject.getString(ResXmlNode.JSON_prefix));
        setLineNumber(jSONObject.optInt(ResXmlNode.JSON_line));
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ String getComment() {
        return super.getComment();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ int getCommentReference() {
        return super.getCommentReference();
    }

    public ResXmlEndNamespace getEnd() {
        return this.mEndNamespace;
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

    public int getReferencedCount() {
        if (isRemoved()) {
            return 0;
        }
        return this.mReferencedAttributes.size() + this.mReferencedElements.size();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ String getUri() {
        return super.getUri();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.arsc.chunk.xml.ResXmlNamespace
    public /* bridge */ /* synthetic */ int getUriReference() {
        return super.getUriReference();
    }

    public boolean isBetterThan(ResXmlStartNamespace resXmlStartNamespace) {
        return resXmlStartNamespace == null || getReferencedCount() > resXmlStartNamespace.getReferencedCount();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk
    public /* bridge */ /* synthetic */ boolean isRemoved() {
        return super.isRemoved();
    }

    public boolean isUndefined() {
        if (isRemoved()) {
            return true;
        }
        return getUriReference() == -1 && getPrefixReference() == -1;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.arsc.chunk.xml.ResXmlNamespace
    public boolean isUnused() {
        return getReferencedCount() == 0;
    }

    public void merge(ResXmlStartNamespace resXmlStartNamespace) {
        if (resXmlStartNamespace == this) {
            return;
        }
        set(resXmlStartNamespace.getUri(), resXmlStartNamespace.getPrefix());
        setLineNumber(resXmlStartNamespace.getLineNumber());
        setComment(resXmlStartNamespace.getComment());
        ResXmlEndNamespace end = getEnd();
        ResXmlEndNamespace end2 = resXmlStartNamespace.getEnd();
        end.setLineNumber(end2.getLineNumber());
        end.setComment(end2.getComment());
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkLoaded() {
        super.onChunkLoaded();
        linkStringReferences();
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void onPreRefresh() {
        super.onPreRefresh();
        ResXmlEndNamespace end = getEnd();
        end.setNamespaceReference(getNamespaceReference());
        end.setStringReference(getStringReference());
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public void onPreRemove() {
        this.mReferencedAttributes.clear();
        this.mReferencedElements.clear();
        super.onPreRemove();
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk
    public void onUriReferenceChanged(int i) {
        Iterator<ResXmlAttribute> it = this.mReferencedAttributes.iterator();
        while (it.hasNext()) {
            it.next().setUriReference(i);
        }
        Iterator<ResXmlStartElement> it2 = this.mReferencedElements.iterator();
        while (it2.hasNext()) {
            it2.next().setNamespaceReference(i);
        }
    }

    public void removeAttributeReference(ResXmlAttribute resXmlAttribute) {
        if (resXmlAttribute != null) {
            this.mReferencedAttributes.remove(resXmlAttribute);
        }
    }

    public void removeElementReference(ResXmlStartElement resXmlStartElement) {
        if (resXmlStartElement != null) {
            this.mReferencedElements.remove(resXmlStartElement);
        }
    }

    public void set(String str, String str2) {
        int index;
        int index2;
        ResXmlStringPool stringPool = getStringPool();
        if (stringPool != null) {
            ResXmlString orCreateNamespaceString = stringPool.getOrCreateNamespaceString(str, str2);
            if (orCreateNamespaceString != null) {
                index = orCreateNamespaceString.getIndex();
                index2 = orCreateNamespaceString.getNamespacePrefix().getIndex();
            } else {
                index = BaseXmlChunk.NULL_REFERENCE;
                index2 = index;
            }
            setUriReference(index);
            setPrefixReference(index2);
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
    public void setLineNumber(int i) {
        if (i != getLineNumber()) {
            super.setLineNumber(i);
            getEnd().setLineNumber(i);
        }
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public void setNamespaceReference(int i) {
        super.setNamespaceReference(i);
        getEnd().setNamespaceReference(i);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.arsc.chunk.xml.ResXmlNamespace
    public /* bridge */ /* synthetic */ void setPrefix(String str) {
        super.setPrefix(str);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk
    public /* bridge */ /* synthetic */ void setPrefixReference(int i) {
        super.setPrefixReference(i);
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public void setStringReference(int i) {
        super.setStringReference(i);
        getEnd().setStringReference(i);
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.arsc.chunk.xml.ResXmlNamespace
    public /* bridge */ /* synthetic */ void setUri(String str) {
        super.setUri(str);
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ResXmlNode.JSON_uri, getUri());
        jSONObject.put(ResXmlNode.JSON_prefix, getPrefix());
        jSONObject.put(ResXmlNode.JSON_line, getLineNumber());
        return jSONObject;
    }

    @Override // com.reandroid.arsc.chunk.xml.ResXmlNamespaceChunk, com.reandroid.arsc.chunk.xml.BaseXmlChunk, com.reandroid.arsc.chunk.Chunk
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }
}
