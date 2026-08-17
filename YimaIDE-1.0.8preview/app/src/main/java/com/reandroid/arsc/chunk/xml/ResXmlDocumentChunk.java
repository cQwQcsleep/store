package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.ApkFile;
import com.reandroid.arsc.chunk.Chunk;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.header.HeaderBlock;
import com.reandroid.arsc.header.InfoHeader;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.pool.ResXmlStringPool;
import com.reandroid.common.BytesOutputStream;
import defpackage.dfc;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlDocumentChunk extends Chunk<HeaderBlock> {
    private static boolean UNKNOWN_CHUNK_LOGGED;
    private final ResXmlNodeList mNodeList;
    private PackageBlock mPackageBlock;
    private final ResXmlIDMap mResXmlIDMap;
    private final ResXmlStringPool mResXmlStringPool;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.chunk.xml.ResXmlNodeList] */
    public ResXmlDocumentChunk() {
        super(new HeaderBlock(ChunkType.XML), 3);
        ResXmlStringPool resXmlStringPool = new ResXmlStringPool(true);
        this.mResXmlStringPool = resXmlStringPool;
        ResXmlIDMap resXmlIDMap = new ResXmlIDMap();
        this.mResXmlIDMap = resXmlIDMap;
        ?? resXmlNodeList = new ResXmlNodeList();
        this.mNodeList = resXmlNodeList;
        addChild(resXmlStringPool);
        addChild(resXmlIDMap);
        addChild(resXmlNodeList);
    }

    private static void logUnknownChunkOnce(HeaderBlock headerBlock) {
        if (UNKNOWN_CHUNK_LOGGED) {
            return;
        }
        UNKNOWN_CHUNK_LOGGED = true;
        System.err.println("Read unknown chunk: " + headerBlock);
    }

    private boolean readNext(BlockReader blockReader) throws IOException {
        if (!blockReader.isAvailable()) {
            return false;
        }
        int position = blockReader.getPosition();
        InfoHeader headerBlock = blockReader.readHeaderBlock();
        if (headerBlock == null) {
            return false;
        }
        ChunkType chunkType = headerBlock.getChunkType();
        if (chunkType == ChunkType.STRING && this.mResXmlStringPool.size() == 0) {
            this.mResXmlStringPool.readBytes(blockReader);
        } else if (chunkType == ChunkType.XML_RESOURCE_MAP && this.mResXmlIDMap.size() == 0) {
            this.mResXmlIDMap.readBytes(blockReader);
        } else {
            if (chunkType == ChunkType.XML_CDATA) {
                document().newText().readBytes(blockReader);
                return blockReader.isAvailable();
            }
            if (chunkType == ChunkType.XML) {
                document().newDocument().readBytes(blockReader);
                return blockReader.isAvailable();
            }
            if (chunkType == ChunkType.XML_START_ELEMENT || chunkType == ChunkType.XML_START_NAMESPACE) {
                document().newElement().readBytes(blockReader);
                return blockReader.isAvailable();
            }
            logUnknownChunkOnce(headerBlock);
            document().newUnknown(chunkType).readBytes(blockReader);
        }
        return blockReader.isAvailable() && position != blockReader.getPosition();
    }

    public void clearEmptyElements() {
        document().removeElementsIf(new dfc());
    }

    public ResXmlDocument document() {
        return (ResXmlDocument) getParentInstance(ResXmlDocument.class);
    }

    public ApkFile getApkFile() {
        return document().getApkFile();
    }

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public byte[] getBytes() {
        BytesOutputStream bytesOutputStream = new BytesOutputStream(getHeaderBlock().getChunkSize());
        try {
            writeBytes(bytesOutputStream);
            bytesOutputStream.close();
        } catch (IOException unused) {
        }
        return bytesOutputStream.toByteArray();
    }

    public ResXmlElement getDocumentElement() {
        return document().m48getDocumentElement();
    }

    public ResXmlNodeList getNodeList() {
        return this.mNodeList;
    }

    public BlockList<ResXmlNode> getNodeListBlockInternal() {
        return this.mNodeList;
    }

    public PackageBlock getPackageBlock() {
        TableBlock loadedTableBlock;
        ApkFile apkFile = getApkFile();
        PackageBlock packageBlock = this.mPackageBlock;
        if (apkFile == null || packageBlock != null || (loadedTableBlock = apkFile.getLoadedTableBlock()) == null) {
            return packageBlock;
        }
        PackageBlock packageBlockSelectPackageBlock = selectPackageBlock(loadedTableBlock);
        this.mPackageBlock = packageBlockSelectPackageBlock;
        return packageBlockSelectPackageBlock;
    }

    public ResXmlIDMap getResXmlIDMap() {
        return this.mResXmlIDMap;
    }

    public ResXmlStringPool getStringPool() {
        return this.mResXmlStringPool;
    }

    public TableBlock getTableBlock() {
        TableBlock tableBlock;
        PackageBlock packageBlock = getPackageBlock();
        if (packageBlock != null && (tableBlock = packageBlock.getTableBlock()) != null) {
            return tableBlock;
        }
        ApkFile apkFile = getApkFile();
        if (apkFile != null) {
            return apkFile.getLoadedTableBlock();
        }
        return null;
    }

    public ResXmlElement newElement() {
        clearEmptyElements();
        return document().newElement();
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkLoaded() {
        super.onChunkLoaded();
        document().linkStringReferences();
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
    }

    @Override // com.reandroid.arsc.base.BlockContainer
    public void onPreRefresh() {
        clearEmptyElements();
        getNodeListBlockInternal().refresh();
        super.onPreRefresh();
    }

    @Override // com.reandroid.arsc.chunk.Chunk, com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        InfoHeader headerBlock = blockReader.readHeaderBlock();
        if (headerBlock == null) {
            r8g.a("Not bin xml: ", blockReader);
            return;
        }
        int chunkSize = headerBlock.getChunkSize();
        if (chunkSize < 0) {
            t8g.a("Negative chunk size: ", chunkSize);
            return;
        }
        if (chunkSize > blockReader.available()) {
            bk3.a("Higher chunk size: ", chunkSize, ", available = ", blockReader.available());
            return;
        }
        if (chunkSize < headerBlock.getHeaderSize()) {
            r8g.a("Higher header size: ", headerBlock);
            return;
        }
        BlockReader blockReaderCreate = blockReader.create(chunkSize);
        HeaderBlock headerBlock2 = getHeaderBlock();
        headerBlock2.readBytes(blockReaderCreate);
        headerBlock2.setType(ChunkType.XML);
        document().clear();
        while (blockReaderCreate.isAvailable() && readNext(blockReaderCreate)) {
        }
        blockReader.offset(headerBlock2.getChunkSize());
        blockReaderCreate.close();
        onChunkLoaded();
    }

    public void refreshFull() {
        ResXmlStringPool stringPool = getStringPool();
        stringPool.compressDuplicates();
        stringPool.removeUnusedStrings();
        refresh();
    }

    public PackageBlock selectPackageBlock(TableBlock tableBlock) {
        PackageBlock packageBlockPickOne = tableBlock.pickOne();
        return packageBlockPickOne == null ? tableBlock.pickOrEmptyPackage() : packageBlockPickOne;
    }

    public void setPackageBlock(PackageBlock packageBlock) {
        this.mPackageBlock = packageBlock;
    }
}
