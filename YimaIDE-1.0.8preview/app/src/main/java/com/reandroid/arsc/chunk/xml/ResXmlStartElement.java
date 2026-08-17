package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.container.SingleBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.BlockItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.utils.StringsUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlStartElement extends BaseXmlChunk {
    private static final short ATTRIBUTES_DEFAULT_START = 20;
    private static final short ATTRIBUTES_UNIT_SIZE = 20;
    private final IntegerReference attributesOffset;
    private final SingleBlockContainer<Block> firstPlaceHolder2;
    private final ResXmlAttributeArray mAttributeArray;
    private final ShortItem mAttributeCount;
    private final ShortItem mAttributeStart;
    private final IntegerReference mAttributeUnitSize;
    private final ResXmlAttributePosition mClassAttributePosition;
    private final ResXmlAttributePosition mIdAttributePosition;
    private final ResXmlEndElement mResXmlEndElement;
    private final ResXmlAttributePosition mStyleAttributePosition;
    private final UnknownBytes unknownBytes;

    public static class UnknownBytes extends BlockItem {
        private final ResXmlStartElement startElement;

        public UnknownBytes(ResXmlStartElement resXmlStartElement) {
            super(0);
            this.startElement = resXmlStartElement;
        }

        public void clear() {
            setSize(0);
        }

        @Override // com.reandroid.arsc.item.BlockItem, com.reandroid.arsc.base.Block
        public byte[] getBytes() {
            return super.getBytesInternal();
        }

        public boolean isEmpty() {
            return size() == 0;
        }

        @Override // com.reandroid.arsc.item.BlockItem, com.reandroid.arsc.base.Block
        public void onReadBytes(BlockReader blockReader) throws IOException {
            ResXmlStartElement resXmlStartElement = this.startElement;
            setBytesLength(resXmlStartElement.getAttributeCount().get() == 0 ? 0 : resXmlStartElement.getAttributesOffset().get() - blockReader.getPosition(), false);
            super.onReadBytes(blockReader);
        }

        public void setBytes(byte[] bArr) {
            int length = bArr.length;
            setSize(length);
            if (length != 0) {
                System.arraycopy(bArr, 0, getBytes(), 0, length);
            }
        }

        public void setSize(int i) {
            setBytesLength(i, false);
            this.startElement.updateAttributesOffset();
        }

        public int size() {
            return countBytes();
        }

        public String toString() {
            return "size = " + size();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.chunk.xml.ResXmlAttributeArray] */
    public ResXmlStartElement(ResXmlEndElement resXmlEndElement) {
        super(ChunkType.XML_START_ELEMENT, null, 9);
        this.mResXmlEndElement = resXmlEndElement;
        ShortItem shortItem = new ShortItem((short) 20);
        this.mAttributeStart = shortItem;
        ShortItem shortItem2 = new ShortItem((short) 20);
        ShortItem shortItem3 = new ShortItem();
        this.mAttributeCount = shortItem3;
        ResXmlAttributePosition resXmlAttributePosition = new ResXmlAttributePosition(ResXmlAttributePosition.TYPE_ID);
        this.mIdAttributePosition = resXmlAttributePosition;
        ResXmlAttributePosition resXmlAttributePosition2 = new ResXmlAttributePosition(ResXmlAttributePosition.TYPE_CLASS);
        this.mClassAttributePosition = resXmlAttributePosition2;
        ResXmlAttributePosition resXmlAttributePosition3 = new ResXmlAttributePosition(ResXmlAttributePosition.TYPE_STYLE);
        this.mStyleAttributePosition = resXmlAttributePosition3;
        SingleBlockContainer<Block> singleBlockContainer = new SingleBlockContainer<>();
        this.firstPlaceHolder2 = singleBlockContainer;
        this.attributesOffset = new IntegerReference() { // from class: com.reandroid.arsc.chunk.xml.ResXmlStartElement.1
            private final ResXmlStartElement _startElement;

            {
                this._startElement = ResXmlStartElement.this;
            }

            @Override // com.reandroid.arsc.item.IntegerReference
            public int get() {
                return this._startElement.getHeaderBlock().getHeaderSize() + this._startElement.getAttributeStart().get();
            }

            @Override // com.reandroid.arsc.item.IntegerReference
            public void set(int i) {
                this._startElement.getAttributeStart().set(i - this._startElement.getHeaderBlock().countBytes());
            }

            public String toString() {
                return Integer.toString(get());
            }
        };
        UnknownBytes unknownBytes = new UnknownBytes(this);
        this.unknownBytes = unknownBytes;
        IntegerReference integerReference = new IntegerReference(shortItem2) { // from class: com.reandroid.arsc.chunk.xml.ResXmlStartElement.2
            private final IntegerReference _reference;
            private final ResXmlStartElement _startElement;
            final /* synthetic */ ShortItem val$_unitSizeReferenceBlock;

            {
                this.val$_unitSizeReferenceBlock = shortItem2;
                this._startElement = ResXmlStartElement.this;
                this._reference = shortItem2;
            }

            @Override // com.reandroid.arsc.item.IntegerReference
            public int get() {
                int i = this._reference.get();
                if (i >= 20 || this._startElement.getAttributeCount().get() == 0) {
                    return i;
                }
                this._reference.set(20);
                return 20;
            }

            @Override // com.reandroid.arsc.item.IntegerReference
            public void set(int i) {
                this._reference.set(i);
                ResXmlAttributeArray resXmlAttributeArray = this._startElement.getResXmlAttributeArray();
                int size = resXmlAttributeArray.size();
                for (int i2 = 0; i2 < size; i2++) {
                    ((ResXmlAttribute) resXmlAttributeArray.get(i2)).setAttributesUnitSize(i);
                }
            }

            public String toString() {
                return Integer.toString(this._reference.get());
            }
        };
        this.mAttributeUnitSize = integerReference;
        ?? resXmlAttributeArray = new ResXmlAttributeArray(integerReference, shortItem3);
        this.mAttributeArray = resXmlAttributeArray;
        addChild(shortItem);
        addChild(shortItem2);
        addChild(shortItem3);
        addChild(resXmlAttributePosition);
        addChild(resXmlAttributePosition2);
        addChild(resXmlAttributePosition3);
        addChild(singleBlockContainer);
        addChild(unknownBytes);
        addChild(resXmlAttributeArray);
    }

    public void fixClassStyleAttributeNames() {
        getClassAttributePosition().fixName();
        getStyleAttributePosition().fixName();
    }

    public IntegerReference getAttributeCount() {
        return this.mAttributeCount;
    }

    public IntegerReference getAttributeStart() {
        return this.mAttributeStart;
    }

    public IntegerReference getAttributeUnitSize() {
        return this.mAttributeUnitSize;
    }

    public IntegerReference getAttributesOffset() {
        return this.attributesOffset;
    }

    public ResXmlAttributePosition getClassAttributePosition() {
        return this.mClassAttributePosition;
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ String getComment() {
        return super.getComment();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ int getCommentReference() {
        return super.getCommentReference();
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public SingleBlockContainer<Block> getFirstPlaceHolder() {
        return this.firstPlaceHolder2;
    }

    public ResXmlAttributePosition getIdAttributePosition() {
        return this.mIdAttributePosition;
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ int getLineNumber() {
        return super.getLineNumber();
    }

    public String getName(boolean z) {
        String prefix;
        String name = super.getName();
        if (!z || (prefix = getPrefix()) == null) {
            return name;
        }
        return prefix + ":" + name;
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ ResXmlElement getNodeElement() {
        return super.getNodeElement();
    }

    public String getPrefix() {
        ResXmlStartNamespace resXmlStartNamespace = getResXmlStartNamespace();
        if (resXmlStartNamespace != null) {
            return resXmlStartNamespace.getPrefix();
        }
        return null;
    }

    public ResXmlAttributeArray getResXmlAttributeArray() {
        return this.mAttributeArray;
    }

    public ResXmlEndElement getResXmlEndElement() {
        return this.mResXmlEndElement;
    }

    public ResXmlStartNamespace getResXmlStartNamespace() {
        ResXmlElement nodeElement;
        int namespaceReference = getNamespaceReference();
        if (namespaceReference == BaseXmlChunk.NULL_REFERENCE || (nodeElement = getNodeElement()) == null) {
            return null;
        }
        return (ResXmlStartNamespace) nodeElement.getNamespaceForUriReference(namespaceReference);
    }

    public ResXmlAttributePosition getStyleAttributePosition() {
        return this.mStyleAttributePosition;
    }

    public UnknownBytes getUnknownBytes() {
        return this.unknownBytes;
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public String getUri() {
        ResXmlStartNamespace resXmlStartNamespace = getResXmlStartNamespace();
        if (resXmlStartNamespace != null) {
            return resXmlStartNamespace.getUri();
        }
        return null;
    }

    public void linkNamespace() {
        ResXmlStartNamespace resXmlStartNamespace = getResXmlStartNamespace();
        if (resXmlStartNamespace != null) {
            resXmlStartNamespace.addElementReference(this);
        }
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public void linkStringReferences() {
        super.linkStringReferences();
        getResXmlEndElement().linkStringReferences();
        linkNamespace();
        getIdAttributePosition().linkAttribute();
        getClassAttributePosition().linkAttribute();
        getStyleAttributePosition().linkAttribute();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk, com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
        super.onChunkRefreshed();
        updateAttributesOffset();
        refreshAttributePositions();
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void onPreRefresh() {
        super.onPreRefresh();
        getResXmlAttributeArray().sort();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public void onPreRemove() {
        super.onPreRemove();
        unlinkNamespace();
        getResXmlAttributeArray().clear();
        getResXmlEndElement().onPreRemove();
        getIdAttributePosition().clear();
        getClassAttributePosition().clear();
        getStyleAttributePosition().clear();
    }

    public void refreshAttributePositions() {
        getIdAttributePosition().refresh();
        getClassAttributePosition().refresh();
        getStyleAttributePosition().refresh();
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
    public void setLineNumber(int i) {
        super.setLineNumber(i);
        getResXmlEndElement().setLineNumber(i);
    }

    public void setName(String str) {
        if (str == null) {
            setStringReference(-1);
        } else {
            setString(str);
        }
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public void setNamespaceReference(int i) {
        super.setNamespaceReference(i);
        getResXmlEndElement().setNamespaceReference(i);
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public void setStringReference(int i) {
        super.setStringReference(i);
        getResXmlEndElement().setStringReference(i);
    }

    public void setTagNamespace(String str, String str2) {
        unlinkNamespace();
        if (str == null || str2 == null) {
            setNamespaceReference(-1);
            return;
        }
        ResXmlElement nodeElement = getNodeElement();
        if (nodeElement == null) {
            return;
        }
        setNamespaceReference(nodeElement.getOrCreateNamespace(str, str2).getUriReference());
        linkNamespace();
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk, com.reandroid.arsc.chunk.Chunk
    public String toString() {
        String name = getName(true);
        if (name == null) {
            return super.toString();
        }
        return name + " " + StringsUtil.join(getResXmlAttributeArray().iterator(), ' ');
    }

    public void unlinkNamespace() {
        ResXmlStartNamespace resXmlStartNamespace = getResXmlStartNamespace();
        if (resXmlStartNamespace != null) {
            resXmlStartNamespace.removeElementReference(this);
        }
    }

    public void updateAttributesOffset() {
        if (getAttributeCount().get() != 0) {
            getAttributesOffset().set(countUpTo(getResXmlAttributeArray()));
        }
    }

    @Override // com.reandroid.arsc.chunk.xml.BaseXmlChunk
    public /* bridge */ /* synthetic */ String getName() {
        return super.getName();
    }
}
