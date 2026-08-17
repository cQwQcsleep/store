package com.reandroid.archive.block;

import com.reandroid.archive.block.pad.SchemePadding;
import com.reandroid.archive.block.stamp.SchemeStampV1;
import com.reandroid.archive.block.stamp.SchemeStampV2;
import com.reandroid.archive.block.v2.SchemeV2;
import com.reandroid.archive.block.v3.SchemeV3;
import com.reandroid.archive.block.v3.SchemeV31;
import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.container.SingleBlockContainer;
import com.reandroid.arsc.io.BlockLoad;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.utils.collection.EmptyIterator;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class SignatureInfo extends LengthPrefixedBlock implements BlockLoad {
    private final IntegerItem idItem;
    private final SingleBlockContainer<SignatureScheme> schemeContainer;

    public SignatureInfo() {
        super(2, true);
        IntegerItem integerItem = new IntegerItem();
        this.idItem = integerItem;
        SingleBlockContainer<SignatureScheme> singleBlockContainer = new SingleBlockContainer<>();
        this.schemeContainer = singleBlockContainer;
        addChild(integerItem);
        addChild(singleBlockContainer);
        integerItem.setBlockLoad(this);
    }

    private void onIdLoaded() {
        Block schemePadding;
        SignatureId id = getId();
        if (id == SignatureId.V2) {
            schemePadding = new SchemeV2();
        } else if (id == SignatureId.V3) {
            schemePadding = new SchemeV3();
        } else if (id == SignatureId.V31) {
            schemePadding = new SchemeV31();
        } else if (id == SignatureId.STAMP_V1) {
            schemePadding = new SchemeStampV1();
        } else if (id == SignatureId.STAMP_V2) {
            schemePadding = new SchemeStampV2();
        } else {
            schemePadding = id == SignatureId.PADDING ? new SchemePadding() : new UnknownScheme(id);
        }
        this.schemeContainer.setItem(schemePadding);
    }

    public Iterator<CertificateBlock> getCertificates() {
        SignatureScheme signatureScheme = getSignatureScheme();
        return signatureScheme != null ? signatureScheme.getCertificates() : EmptyIterator.of();
    }

    public SignatureId getId() {
        return SignatureId.valueOf(getIdValue());
    }

    public int getIdValue() {
        return this.idItem.get();
    }

    public SignatureScheme getSignatureScheme() {
        return this.schemeContainer.getItem();
    }

    public void onBlockLoaded(BlockReader blockReader, Block block) throws IOException {
        if (block == this.idItem) {
            onIdLoaded();
        }
    }

    public void read(File file) throws IOException {
        super/*com.reandroid.arsc.base.Block*/.readBytes(new BlockReader(file));
    }

    public void setId(SignatureId signatureId) {
        setId(signatureId == null ? 0 : signatureId.getId());
    }

    public void setSignatureScheme(SignatureScheme signatureScheme) {
        this.schemeContainer.setItem(signatureScheme);
    }

    @Override // com.reandroid.archive.block.LengthPrefixedBlock
    public String toString() {
        return getId() + ", scheme: " + getSignatureScheme();
    }

    public void writeRaw(File file) throws IOException {
        File parentFile = file.getParentFile();
        if (parentFile != null && !parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        writeBytes(fileOutputStream);
        fileOutputStream.close();
    }

    public File writeRawToDirectory(File file) throws IOException {
        File file2 = new File(file, getIndex() + "_" + getId().toFileName());
        writeRaw(file2);
        return file2;
    }

    public void setId(int i) {
        this.idItem.set(i);
    }
}
