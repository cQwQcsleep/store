package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.chunk.Chunk;
import com.reandroid.arsc.header.XmlIDMapHeader;
import com.reandroid.arsc.item.ResXmlID;
import com.reandroid.arsc.item.ResXmlString;
import com.reandroid.arsc.list.ResXmlIDList;
import com.reandroid.arsc.pool.ResXmlStringPool;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlIDMap extends Chunk<XmlIDMapHeader> implements Iterable<ResXmlID> {
    private final ResXmlIDList mResXmlIDArray;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.list.ResXmlIDList] */
    public ResXmlIDMap() {
        super(new XmlIDMapHeader(), 1);
        ?? resXmlIDList = new ResXmlIDList(((XmlIDMapHeader) getHeaderBlock()).getIdsCount());
        this.mResXmlIDArray = resXmlIDList;
        addChild(resXmlIDList);
    }

    private ResXmlStringPool getXmlStringPool() {
        ResXmlDocument resXmlDocument = (ResXmlDocument) getParentInstance(ResXmlDocument.class);
        if (resXmlDocument != null) {
            return resXmlDocument.getStringPool();
        }
        return null;
    }

    public void destroy() {
        getResXmlIDArray().clear();
    }

    public ResXmlID get(int i) {
        return (ResXmlID) getResXmlIDArray().get(i);
    }

    public ResXmlID getOrCreate(int i) {
        return getResXmlIDArray().getOrCreate(i);
    }

    public ResXmlIDList getResXmlIDArray() {
        return this.mResXmlIDArray;
    }

    @Override // java.lang.Iterable
    public Iterator<ResXmlID> iterator() {
        return getResXmlIDArray().iterator();
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkLoaded() {
        super.onChunkLoaded();
        ResXmlStringPool xmlStringPool = getXmlStringPool();
        if (xmlStringPool == null || xmlStringPool.isEmpty()) {
            return;
        }
        xmlStringPool.linkResXmlIDMapInternal();
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
    }

    public void removeSafely(ResXmlID resXmlID) {
        ResXmlString resXmlString;
        ResXmlStringPool xmlStringPool;
        if (resXmlID == null || resXmlID.getParent() == null || resXmlID.getIndex() < 0 || resXmlID.hasReference() || (resXmlString = resXmlID.getResXmlString()) == null || resXmlString.getParent() == null || resXmlString.getIndex() < 0 || resXmlString.hasReference() || (xmlStringPool = getXmlStringPool()) == null) {
            return;
        }
        resXmlID.set(0);
        getResXmlIDArray().remove(resXmlID);
        xmlStringPool.removeString(resXmlString);
    }

    public int size() {
        return getResXmlIDArray().size();
    }
}
