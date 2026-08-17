package com.reandroid.dex.header;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.DirectStreamReader;
import com.reandroid.arsc.base.OffsetSupplier;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.sections.SpecialItem;
import com.reandroid.utils.io.FileUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexHeader extends SpecialItem implements OffsetSupplier, DirectStreamReader {
    public final DexChecksum checksum;
    public final CountAndOffset class_id;
    public final DexContainerInfo containerInfo;
    public final CountAndOffset data;
    public final Endian endian;
    public final CountAndOffset field_id;
    public final IntegerReference fileSize;
    public final IntegerReference headerSize;
    public final Magic magic;
    public final IntegerReference map;
    public final CountAndOffset method_id;
    public final CountAndOffset proto_id;
    public final Signature signature;
    public final CountAndOffset string_id;
    public final CountAndOffset type_id;
    public final UnknownHeaderBytes unknown;
    public final DexVersion version;

    public DexHeader() {
        super(17);
        Magic magic = new Magic();
        this.magic = magic;
        DexVersion dexVersion = new DexVersion();
        this.version = dexVersion;
        DexChecksum dexChecksum = new DexChecksum();
        this.checksum = dexChecksum;
        Signature signature = new Signature();
        this.signature = signature;
        IntegerItem integerItem = new IntegerItem();
        this.fileSize = integerItem;
        IntegerItem integerItem2 = new IntegerItem();
        this.headerSize = integerItem2;
        Endian endian = new Endian();
        this.endian = endian;
        IntegerItem integerItem3 = new IntegerItem();
        this.map = integerItem3;
        CountAndOffset countAndOffset = new CountAndOffset();
        this.string_id = countAndOffset;
        CountAndOffset countAndOffset2 = new CountAndOffset();
        this.type_id = countAndOffset2;
        CountAndOffset countAndOffset3 = new CountAndOffset();
        this.proto_id = countAndOffset3;
        CountAndOffset countAndOffset4 = new CountAndOffset();
        this.field_id = countAndOffset4;
        CountAndOffset countAndOffset5 = new CountAndOffset();
        this.method_id = countAndOffset5;
        CountAndOffset countAndOffset6 = new CountAndOffset();
        this.class_id = countAndOffset6;
        CountAndOffset countAndOffset7 = new CountAndOffset();
        this.data = countAndOffset7;
        DexContainerInfo dexContainerInfo = new DexContainerInfo();
        this.containerInfo = dexContainerInfo;
        UnknownHeaderBytes unknownHeaderBytes = new UnknownHeaderBytes();
        this.unknown = unknownHeaderBytes;
        addChildBlock(0, magic);
        addChildBlock(1, dexVersion);
        addChildBlock(2, dexChecksum);
        addChildBlock(3, signature);
        addChildBlock(4, (Block) integerItem);
        addChildBlock(5, (Block) integerItem2);
        addChildBlock(6, endian);
        addChildBlock(7, (Block) integerItem3);
        addChildBlock(8, countAndOffset);
        addChildBlock(9, countAndOffset2);
        addChildBlock(10, countAndOffset3);
        addChildBlock(11, countAndOffset4);
        addChildBlock(12, countAndOffset5);
        addChildBlock(13, countAndOffset6);
        addChildBlock(14, countAndOffset7);
        addChildBlock(15, dexContainerInfo);
        addChildBlock(16, unknownHeaderBytes);
        setOffsetReference(dexContainerInfo.getOffsetReference());
    }

    public static DexHeader readHeader(byte[] bArr) throws IOException {
        DexHeader dexHeader = new DexHeader();
        dexHeader.magic.setDisableVerification(true);
        if (bArr.length >= dexHeader.countBytes()) {
            dexHeader.readBytes(new BlockReader(bArr));
            return dexHeader;
        }
        e44.a("Few bytes to read header: ", bArr.length);
        return null;
    }

    public CountAndOffset get(SectionType<?> sectionType) {
        if (sectionType == SectionType.STRING_ID) {
            return this.string_id;
        }
        if (sectionType == SectionType.TYPE_ID) {
            return this.type_id;
        }
        if (sectionType == SectionType.PROTO_ID) {
            return this.proto_id;
        }
        if (sectionType == SectionType.FIELD_ID) {
            return this.field_id;
        }
        if (sectionType == SectionType.METHOD_ID) {
            return this.method_id;
        }
        if (sectionType == SectionType.CLASS_ID) {
            return this.class_id;
        }
        return null;
    }

    public int getFileSize() {
        return this.fileSize.get();
    }

    @Override // com.reandroid.dex.common.SectionItemContainer, com.reandroid.arsc.base.OffsetSupplier, com.reandroid.dex.base.DexArraySupplier
    public IntegerReference getOffsetReference() {
        return this.containerInfo.getOffsetReference();
    }

    @Override // com.reandroid.dex.common.SectionItem
    public SectionType<DexHeader> getSectionType() {
        return SectionType.HEADER;
    }

    public int getVersion() {
        return this.version.getVersionAsInteger();
    }

    public boolean isClassDefinitionOrderEnforced() {
        return this.version.isClassDefinitionOrderEnforced();
    }

    public boolean isMultiLayoutVersion() {
        return this.version.isMultiLayoutVersion();
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public boolean isValidOffset(int i) {
        return i >= 0;
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.nonCheckRead(blockReader);
    }

    @Override // com.reandroid.dex.common.SectionItemContainer
    public void onRefreshed() {
        super.onRefreshed();
        this.headerSize.set(countBytes());
    }

    @Override // com.reandroid.dex.common.SectionItemContainer, com.reandroid.arsc.base.DirectStreamReader
    public int readBytes(InputStream inputStream) throws IOException {
        int bytes = 0;
        for (int i = 0; i < 17; i++) {
            bytes += getChildBlockAt(i).readBytes(inputStream);
        }
        return bytes;
    }

    @Override // com.reandroid.dex.common.SectionItemContainer, com.reandroid.dex.base.OffsetReceiver
    public void setOffsetReference(IntegerReference integerReference) {
        if (integerReference == this.containerInfo.getOffsetReference()) {
            return;
        }
        f63.a("Header already has offset reference");
    }

    public void setVersion(int i) {
        this.version.setVersionAsInteger(i);
    }

    public String toString() {
        return "Header {magic=" + this.magic + ", version=" + this.version + ", checksum=" + this.checksum + ", signature=" + this.signature + ", fileSize=" + this.fileSize + ", headerSize=" + this.headerSize + ", endian=" + this.endian + ", map=" + this.map + ", strings=" + this.string_id + ", type=" + this.type_id + ", proto=" + this.proto_id + ", field=" + this.field_id + ", method=" + this.method_id + ", clazz=" + this.class_id + ", data=" + this.data + ", container-v41" + this.containerInfo + ", unknown=" + this.unknown + '}';
    }

    public boolean updateChecksum() {
        return this.checksum.update();
    }

    public void updateSignature() {
        this.signature.update();
    }

    public static DexHeader readHeader(InputStream inputStream) throws IOException {
        DexHeader dexHeader = new DexHeader();
        dexHeader.magic.setDisableVerification(true);
        int bytes = dexHeader.readBytes(inputStream);
        if (bytes >= dexHeader.countBytes()) {
            return dexHeader;
        }
        t8g.a("Few bytes to read header: ", bytes);
        return null;
    }

    public static DexHeader readHeader(File file) throws IOException {
        InputStream inputStream = FileUtil.inputStream(file);
        DexHeader header = readHeader(inputStream);
        inputStream.close();
        return header;
    }
}
