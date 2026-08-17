package com.reandroid.arsc.pool;

import com.reandroid.arsc.chunk.ChunkType;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.UnknownChunk;
import com.reandroid.arsc.header.TableHeader;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.StringCreator;
import com.reandroid.arsc.item.StringItem;
import com.reandroid.arsc.item.TableString;
import com.reandroid.xml.StyleDocument;
import java.io.IOException;
import java.io.InputStream;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class TableStringPool extends StringPool<TableString> {
    public TableStringPool(boolean z) {
        super(z, new StringCreator() { // from class: o2e
            @Override // com.reandroid.arsc.item.StringCreator
            public final StringItem newInstance(boolean z2) {
                return new TableString(z2);
            }
        });
    }

    public static TableStringPool readFromTable(InputStream inputStream) throws IOException {
        TableHeader tableHeader = new TableHeader();
        tableHeader.readBytes(inputStream);
        if (tableHeader.getChunkType() != ChunkType.TABLE) {
            r8g.a("Not TableBlock: ", tableHeader);
            return null;
        }
        UnknownChunk unknownChunk = new UnknownChunk();
        unknownChunk.readBytes(inputStream);
        if (unknownChunk.getHeaderBlock().getChunkType() != ChunkType.STRING) {
            r8g.a("Not StringPool chunk: ", unknownChunk);
            return null;
        }
        BlockReader blockReader = new BlockReader(unknownChunk.getBytes());
        TableStringPool tableStringPool = new TableStringPool(true);
        tableStringPool.readBytes(blockReader);
        blockReader.close();
        return tableStringPool;
    }

    public TableString getOrCreate(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return getOrCreate(StyleDocument.parseNext(xmlPullParser));
    }

    public TableString getOrCreateStyled(String str) throws XmlPullParserException, IOException {
        return getOrCreate(StyleDocument.parseStyledString(str));
    }

    @Override // com.reandroid.arsc.pool.StringPool
    public void linkStrings() {
        super.linkStrings();
        TableBlock tableBlock = (TableBlock) getParentInstance(TableBlock.class);
        if (tableBlock != null) {
            tableBlock.linkTableStringsInternal(this);
        }
    }
}
