package com.reandroid.arsc.container;

import com.reandroid.arsc.array.SpecTypePairArray;
import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.chunk.LibraryBlock;
import com.reandroid.arsc.chunk.OverlayablePolicy;
import com.reandroid.arsc.chunk.StagedAlias;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.chunk.UnknownChunk;
import com.reandroid.arsc.header.InfoHeader;
import com.reandroid.arsc.header.SpecHeader;
import com.reandroid.arsc.header.TypeHeader;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.list.OverlayableList;
import com.reandroid.arsc.list.StagedAliasList;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class PackageBody extends FixedBlockContainer {
    private final LibraryBlock mLibraryBlock;
    private final OverlayableList mOverlayableList;
    private final BlockList<OverlayablePolicy> mOverlayablePolicyList;
    private final SpecTypePairArray mSpecTypePairArray;
    private final StagedAliasList mStagedAliasList;
    private final BlockList<UnknownChunk> mUnknownChunkList;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [com.reandroid.arsc.array.SpecTypePairArray, com.reandroid.arsc.base.Block] */
    /* JADX WARN: Type inference failed for: r2v0, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.list.StagedAliasList] */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.reandroid.arsc.base.Block, com.reandroid.arsc.list.OverlayableList] */
    public PackageBody() {
        super(6);
        ?? specTypePairArray = new SpecTypePairArray();
        this.mSpecTypePairArray = specTypePairArray;
        LibraryBlock libraryBlock = new LibraryBlock();
        this.mLibraryBlock = libraryBlock;
        ?? stagedAliasList = new StagedAliasList();
        this.mStagedAliasList = stagedAliasList;
        ?? overlayableList = new OverlayableList();
        this.mOverlayableList = overlayableList;
        BlockList<OverlayablePolicy> blockList = new BlockList<>();
        this.mOverlayablePolicyList = blockList;
        BlockList<UnknownChunk> blockList2 = new BlockList<>();
        this.mUnknownChunkList = blockList2;
        addChild(0, specTypePairArray);
        addChild(1, libraryBlock);
        addChild(2, stagedAliasList);
        addChild(3, overlayableList);
        addChild(4, blockList);
        addChild(5, blockList2);
    }

    private void readLibraryBlock(BlockReader blockReader) throws IOException {
        LibraryBlock libraryBlock = new LibraryBlock();
        libraryBlock.readBytes(blockReader);
        this.mLibraryBlock.addLibraryInfo(libraryBlock);
    }

    private boolean readNextBlock(BlockReader blockReader) throws IOException {
        InfoHeader headerBlock = blockReader.readHeaderBlock();
        if (headerBlock == null) {
            return false;
        }
        int position = blockReader.getPosition();
        ChunkType chunkType = headerBlock.getChunkType();
        if (chunkType == ChunkType.SPEC) {
            readSpecBlock(blockReader);
        } else if (chunkType == ChunkType.TYPE) {
            readTypeBlock(blockReader);
        } else if (chunkType == ChunkType.LIBRARY) {
            readLibraryBlock(blockReader);
        } else if (chunkType == ChunkType.OVERLAYABLE) {
            readOverlayable(blockReader);
        } else if (chunkType == ChunkType.OVERLAYABLE_POLICY) {
            readOverlayablePolicy(blockReader);
        } else if (chunkType == ChunkType.STAGED_ALIAS) {
            readStagedAlias(blockReader);
        } else {
            readUnknownChunk(blockReader);
        }
        return position != blockReader.getPosition();
    }

    private void readOverlayable(BlockReader blockReader) throws IOException {
        getOverlayableList().createNext().readBytes(blockReader);
    }

    private void readOverlayablePolicy(BlockReader blockReader) throws IOException {
        OverlayablePolicy overlayablePolicy = new OverlayablePolicy();
        this.mOverlayablePolicyList.add(overlayablePolicy);
        overlayablePolicy.readBytes(blockReader);
    }

    private void readSpecBlock(BlockReader blockReader) throws IOException {
        this.mSpecTypePairArray.getOrCreate(SpecHeader.read(blockReader).getId().getByte()).getSpecBlock().readBytes(blockReader);
    }

    private void readStagedAlias(BlockReader blockReader) throws IOException {
        StagedAlias stagedAlias = new StagedAlias();
        stagedAlias.readBytes(blockReader);
        this.mStagedAliasList.add(stagedAlias);
    }

    private void readTypeBlock(BlockReader blockReader) throws IOException {
        ((TypeBlock) this.mSpecTypePairArray.getOrCreate(TypeHeader.read(blockReader).getId().getByte()).getTypeBlockArray().createNext()).readBytes(blockReader);
    }

    private void readUnknownChunk(BlockReader blockReader) throws IOException {
        UnknownChunk unknownChunk = new UnknownChunk();
        unknownChunk.readBytes(blockReader);
        this.mUnknownChunkList.add(unknownChunk);
    }

    public void destroy() {
        getSpecTypePairArray().clear();
        getLibraryBlock().getLibraryInfoArray().clear();
        getStagedAliasList().clearChildes();
        getOverlayableList().clearChildes();
        getUnknownChunkList().clearChildes();
    }

    public LibraryBlock getLibraryBlock() {
        return this.mLibraryBlock;
    }

    public OverlayableList getOverlayableList() {
        return this.mOverlayableList;
    }

    public SpecTypePairArray getSpecTypePairArray() {
        return this.mSpecTypePairArray;
    }

    public StagedAliasList getStagedAliasList() {
        return this.mStagedAliasList;
    }

    public BlockList<UnknownChunk> getUnknownChunkList() {
        return this.mUnknownChunkList;
    }

    public boolean isEmpty() {
        return getSpecTypePairArray().isEmpty() && getLibraryBlock().isEmpty() && getStagedAliasList().size() == 0 && getOverlayableList().size() == 0 && getUnknownChunkList().size() == 0;
    }

    @Override // com.reandroid.arsc.base.BlockContainer, com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        boolean nextBlock = true;
        while (nextBlock) {
            nextBlock = readNextBlock(blockReader);
        }
    }
}
