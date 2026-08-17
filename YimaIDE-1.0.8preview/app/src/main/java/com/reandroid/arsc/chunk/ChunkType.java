package com.reandroid.arsc.chunk;

import com.reandroid.utils.HexUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum ChunkType {
    NULL(0),
    STRING(1),
    TABLE(2),
    XML(3),
    XML_START_NAMESPACE(256),
    XML_END_NAMESPACE(257),
    XML_START_ELEMENT(258),
    XML_END_ELEMENT(259),
    XML_CDATA(260),
    XML_LAST_CHUNK(383),
    XML_RESOURCE_MAP(384),
    PACKAGE(512),
    TYPE(513),
    SPEC(514),
    LIBRARY(515),
    OVERLAYABLE(516),
    OVERLAYABLE_POLICY(517),
    STAGED_ALIAS(518);

    private static final ChunkType[] table_chunk_types;
    private static final ChunkType[] xml_chunk_types;
    public final short ID;

    static {
        ChunkType chunkType = XML_START_NAMESPACE;
        ChunkType chunkType2 = XML_END_NAMESPACE;
        ChunkType chunkType3 = XML_START_ELEMENT;
        ChunkType chunkType4 = XML_END_ELEMENT;
        ChunkType chunkType5 = XML_CDATA;
        ChunkType chunkType6 = XML_LAST_CHUNK;
        ChunkType chunkType7 = XML_RESOURCE_MAP;
        table_chunk_types = new ChunkType[]{PACKAGE, TYPE, SPEC, LIBRARY};
        xml_chunk_types = new ChunkType[]{chunkType, chunkType2, chunkType3, chunkType4, chunkType5, chunkType6, chunkType7};
    }

    ChunkType(short s) {
        this.ID = s;
    }

    public static ChunkType get(short s) {
        for (ChunkType chunkType : values()) {
            if (chunkType.ID == s) {
                return chunkType;
            }
        }
        return null;
    }

    public static ChunkType getTable(short s) {
        for (ChunkType chunkType : table_chunk_types) {
            if (chunkType.ID == s) {
                return chunkType;
            }
        }
        return null;
    }

    public static ChunkType getXml(short s) {
        for (ChunkType chunkType : xml_chunk_types) {
            if (chunkType.ID == s) {
                return chunkType;
            }
        }
        return null;
    }

    @Override // java.lang.Enum
    public String toString() {
        return name() + "(" + HexUtil.toHex4(this.ID) + ")";
    }
}
