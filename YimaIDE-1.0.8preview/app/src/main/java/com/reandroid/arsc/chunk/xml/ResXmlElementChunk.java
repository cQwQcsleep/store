package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.xml.XMLUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlElementChunk extends FixedBlockContainer {
    private final ResXmlEndElement mEndElement;
    private final ResXmlChunkList<ResXmlEndNamespace> mEndNamespaceList;
    private final ResXmlNodeList mNodeList;
    private final ResXmlStartElement mStartElement;
    private final ResXmlStartNamespaceList mStartNamespaceList;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.chunk.xml.ResXmlChunkList, com.reandroid.arsc.chunk.xml.ResXmlChunkList<com.reandroid.arsc.chunk.xml.ResXmlEndNamespace>, com.reandroid.arsc.container.BlockList] */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.chunk.xml.ResXmlStartNamespaceList] */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.chunk.xml.ResXmlNodeList] */
    public ResXmlElementChunk() {
        super(5);
        ?? resXmlChunkList = new ResXmlChunkList();
        ?? resXmlStartNamespaceList = new ResXmlStartNamespaceList(resXmlChunkList);
        this.mStartNamespaceList = resXmlStartNamespaceList;
        ResXmlEndElement resXmlEndElement = new ResXmlEndElement();
        ResXmlStartElement resXmlStartElement = new ResXmlStartElement(resXmlEndElement);
        this.mStartElement = resXmlStartElement;
        ?? resXmlNodeList = new ResXmlNodeList();
        this.mNodeList = resXmlNodeList;
        this.mEndElement = resXmlEndElement;
        this.mEndNamespaceList = resXmlChunkList;
        addChild(0, resXmlStartNamespaceList);
        addChild(1, resXmlStartElement);
        addChild(2, resXmlNodeList);
        addChild(3, resXmlEndElement);
        addChild(4, resXmlChunkList);
    }

    public ResXmlElement element() {
        return (ResXmlElement) getParentInstance(ResXmlElement.class);
    }

    public ResXmlEndElement getEndElement() {
        return this.mEndElement;
    }

    public ResXmlNodeList getNodeList() {
        return this.mNodeList;
    }

    public ResXmlStartElement getStartElement() {
        return this.mStartElement;
    }

    public ResXmlStartNamespaceList getStartNamespaceList() {
        return this.mStartNamespaceList;
    }

    public void linkStringReferences() {
        this.mStartElement.linkStringReferences();
    }

    public void onPreRemove() {
        this.mStartNamespaceList.clear();
        this.mEndNamespaceList.clearChildes();
        this.mStartElement.onPreRemove();
    }

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        new ElementChunkReader(this).read(blockReader);
    }

    public void setName(String str) {
        ResXmlNamespace orCreateNamespaceForPrefix;
        ResXmlStartElement startElement = getStartElement();
        if (str == null) {
            startElement.setName(null);
            return;
        }
        String strSplitPrefix = XMLUtil.splitPrefix(str);
        startElement.setName(XMLUtil.splitName(str));
        if (strSplitPrefix == null || (orCreateNamespaceForPrefix = element().getOrCreateNamespaceForPrefix(strSplitPrefix)) == null) {
            return;
        }
        startElement.setNamespaceReference(orCreateNamespaceForPrefix.getUriReference());
    }
}
