package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.Chunk;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.container.SingleBlockContainer;
import com.reandroid.arsc.header.XmlNodeHeader;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.ResXmlString;
import com.reandroid.arsc.pool.ResXmlStringPool;
import com.reandroid.utils.ObjectsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
class BaseXmlChunk extends Chunk<XmlNodeHeader> {
    public static final int NULL_REFERENCE = ObjectsUtil.of(-1);
    private final IntegerItem mNamespaceReference;
    private final IntegerItem mStringReference;

    public BaseXmlChunk(ChunkType chunkType, SingleBlockContainer<Block> singleBlockContainer, int i) {
        super(new XmlNodeHeader(chunkType), singleBlockContainer, i + 2);
        int i2 = NULL_REFERENCE;
        IntegerItem integerItem = new IntegerItem(i2);
        this.mNamespaceReference = integerItem;
        IntegerItem integerItem2 = new IntegerItem(i2);
        this.mStringReference = integerItem2;
        addChild(integerItem);
        addChild(integerItem2);
    }

    private void linkStringReference(IntegerItem integerItem) {
        ResXmlString resXmlString = getResXmlString(integerItem.get());
        if (resXmlString != null) {
            resXmlString.addReference(integerItem);
        }
    }

    public String getComment() {
        return getString(getCommentReference());
    }

    public int getCommentReference() {
        return getHeaderBlock().getCommentReference().get();
    }

    public int getLineNumber() {
        return getHeaderBlock().getLineNumber().get();
    }

    public String getName() {
        return getString(getStringReference());
    }

    public int getNamespaceReference() {
        return this.mNamespaceReference.get();
    }

    public ResXmlElement getNodeElement() {
        return (ResXmlElement) getParentInstance(ResXmlElement.class);
    }

    public ResXmlString getOrCreateString(String str) {
        ResXmlStringPool stringPool = getStringPool();
        if (stringPool == null) {
            return null;
        }
        return stringPool.getOrCreate(str);
    }

    public int getOrCreateStringReference(String str) {
        ResXmlString orCreateString = getOrCreateString(str);
        return orCreateString != null ? orCreateString.getIndex() : NULL_REFERENCE;
    }

    public ResXmlString getResXmlString(int i) {
        ResXmlStringPool stringPool;
        if (i == NULL_REFERENCE || (stringPool = getStringPool()) == null) {
            return null;
        }
        return stringPool.get(i);
    }

    public String getString(int i) {
        ResXmlString resXmlString = getResXmlString(i);
        if (resXmlString != null) {
            return resXmlString.get();
        }
        return null;
    }

    public ResXmlStringPool getStringPool() {
        for (Block parent = getParent(); parent != null; parent = parent.getParent()) {
            if (parent instanceof ResXmlDocument) {
                return ((ResXmlDocument) parent).getStringPool();
            }
            if (parent instanceof ResXmlElement) {
                return ((ResXmlElement) parent).getStringPool();
            }
        }
        return null;
    }

    public int getStringReference() {
        return this.mStringReference.get();
    }

    public String getUri() {
        return getString(getNamespaceReference());
    }

    public void linkStringReferences() {
        linkStringReference(getHeaderBlock().getCommentReference());
        linkStringReference(this.mNamespaceReference);
        linkStringReference(this.mStringReference);
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
    }

    public void onPreRemove() {
        ResXmlStringPool stringPool = getStringPool();
        if (stringPool == null) {
            return;
        }
        stringPool.removeReference(getHeaderBlock().getCommentReference());
        stringPool.removeReference(this.mNamespaceReference);
        stringPool.removeReference(this.mStringReference);
    }

    public void setComment(String str) {
        if (str == null || str.length() == 0) {
            setCommentReference(NULL_REFERENCE);
        } else {
            if (str.equals(getComment())) {
                return;
            }
            setCommentReference(getOrCreateString(str).getIndex());
        }
    }

    public void setCommentReference(int i) {
        if (i == getCommentReference()) {
            return;
        }
        IntegerItem commentReference = getHeaderBlock().getCommentReference();
        unLinkStringReference(commentReference);
        getHeaderBlock().getCommentReference().set(i);
        linkStringReference(commentReference);
    }

    public void setLineNumber(int i) {
        getHeaderBlock().getLineNumber().set(i);
    }

    public void setNamespaceReference(int i) {
        if (i == getNamespaceReference()) {
            return;
        }
        unLinkStringReference(this.mNamespaceReference);
        this.mNamespaceReference.set(i);
        linkStringReference(this.mNamespaceReference);
    }

    public void setString(String str) {
        ResXmlStringPool stringPool = getStringPool();
        if (stringPool != null) {
            setStringReference(stringPool.getOrCreate(str).getIndex());
        }
    }

    public void setStringReference(int i) {
        if (i == getStringReference()) {
            return;
        }
        unLinkStringReference(this.mStringReference);
        this.mStringReference.set(i);
        linkStringReference(this.mStringReference);
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public String toString() {
        ChunkType chunkType = getHeaderBlock().getChunkType();
        if (chunkType == null) {
            return super.toString();
        }
        return chunkType.toString() + ": line=" + getLineNumber() + " {" + getName() + "}";
    }

    public void unLinkStringReference(IntegerItem integerItem) {
        ResXmlString resXmlString = getResXmlString(integerItem.get());
        if (resXmlString != null) {
            resXmlString.removeReference(integerItem);
        }
    }

    public BaseXmlChunk(ChunkType chunkType, int i) {
        this(chunkType, new SingleBlockContainer(), i);
    }
}
