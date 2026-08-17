package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.chunk.Chunk;
import com.reandroid.arsc.header.XmlNodeHeader;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlChunkApi implements BlockRefresh {
    private final BaseXmlChunk chunk;

    public ResXmlChunkApi(Chunk<XmlNodeHeader> chunk) {
        this.chunk = (BaseXmlChunk) chunk;
    }

    public int countBytes() {
        return this.chunk.countBytes();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return ObjectsUtil.equals(this.chunk, ((ResXmlChunkApi) obj).chunk);
    }

    public byte[] getBytes() {
        return this.chunk.getBytes();
    }

    public Chunk<XmlNodeHeader> getChunk() {
        return this.chunk;
    }

    public String getComment() {
        return this.chunk.getComment();
    }

    public int getCommentReference() {
        return this.chunk.getCommentReference();
    }

    public XmlNodeHeader getHeader() {
        return this.chunk.getHeaderBlock();
    }

    public int getIndex() {
        return this.chunk.getIndex();
    }

    public int getLineNumber() {
        return this.chunk.getLineNumber();
    }

    public String getName() {
        return this.chunk.getName();
    }

    public String getNamespace() {
        return this.chunk.getString(getNamespaceReference());
    }

    public int getNamespaceReference() {
        return this.chunk.getNamespaceReference();
    }

    public Block getParent() {
        return this.chunk.getParent();
    }

    public <T> T getParentInstance(Class<T> cls) {
        return (T) this.chunk.getParentInstance(cls);
    }

    public String getString() {
        return this.chunk.getString(getStringReference());
    }

    public int getStringReference() {
        return this.chunk.getStringReference();
    }

    public int hashCode() {
        return ObjectsUtil.hash(this.chunk);
    }

    public void readBytes(BlockReader blockReader) throws IOException {
        this.chunk.readBytes(blockReader);
    }

    @Override // com.reandroid.arsc.base.BlockRefresh
    public void refresh() {
        this.chunk.refresh();
    }

    public void setComment(String str) {
        this.chunk.setComment(str);
    }

    public void setCommentReference(int i) {
        this.chunk.setCommentReference(i);
    }

    public void setIndex(int i) {
        this.chunk.setIndex(i);
    }

    public void setLineNumber(int i) {
        this.chunk.setLineNumber(i);
    }

    public void setName(String str) {
        this.chunk.setString(str);
    }

    public void setNamespace(String str) {
        setNamespaceReference(this.chunk.getOrCreateStringReference(str));
    }

    public void setNamespaceReference(int i) {
        this.chunk.setNamespaceReference(i);
    }

    public void setParent(Block block) {
        this.chunk.setParent(block);
    }

    public void setString(String str) {
        this.chunk.setString(str);
    }

    public void setStringReference(int i) {
        this.chunk.setStringReference(i);
    }

    public String toString() {
        return this.chunk.toString();
    }

    public int writeBytes(OutputStream outputStream) throws IOException {
        return this.chunk.writeBytes(outputStream);
    }

    public <T> T getParent(Class<T> cls) {
        return (T) this.chunk.getParent(cls);
    }
}
