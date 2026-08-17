package com.reandroid.archive.block;

import com.reandroid.archive.block.pad.SchemePadding;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.io.FileUtil;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkSignatureBlock extends LengthPrefixedList<SignatureInfo> implements Comparator<SignatureInfo> {
    public static final String FILE_EXT = ".sig";

    public ApkSignatureBlock() {
        this(new SignatureFooter());
    }

    private SchemePadding getOrCreateSchemePadding() {
        SignatureId signatureId = SignatureId.PADDING;
        SignatureInfo signature = getSignature(signatureId);
        if (signature == null) {
            signature = new SignatureInfo();
            signature.setId(signatureId);
            signature.setSignatureScheme(new SchemePadding());
            add(signature);
        }
        SignatureScheme signatureScheme = signature.getSignatureScheme();
        if (!(signatureScheme instanceof SchemePadding)) {
            signatureScheme = new SchemePadding();
            signature.setSignatureScheme(signatureScheme);
        }
        return (SchemePadding) signatureScheme;
    }

    public SignatureInfo addSplitRaw(File file) throws IOException {
        SignatureInfo signatureInfo = new SignatureInfo();
        signatureInfo.read(file);
        add(signatureInfo);
        return signatureInfo;
    }

    @Override // java.util.Comparator
    public int compare(SignatureInfo signatureInfo, SignatureInfo signatureInfo2) {
        return signatureInfo.getId().compareTo(signatureInfo2.getId());
    }

    public Iterator<CertificateBlock> getCertificates() {
        return new IterableIterator<SignatureInfo, CertificateBlock>(iterator()) { // from class: com.reandroid.archive.block.ApkSignatureBlock.1
            @Override // com.reandroid.utils.collection.IterableIterator
            public Iterator<CertificateBlock> iterator(SignatureInfo signatureInfo) {
                return signatureInfo.getCertificates();
            }
        };
    }

    public SignatureInfo getSignature(SignatureId signatureId) {
        for (SignatureInfo signatureInfo : this) {
            if (signatureInfo.getId().equals(signatureId)) {
                return signatureInfo;
            }
        }
        return null;
    }

    public SignatureFooter getSignatureFooter() {
        return getBottomBlock();
    }

    @Override // com.reandroid.arsc.base.BlockCreator
    /* JADX INFO: renamed from: newInstance, reason: merged with bridge method [inline-methods] */
    public SignatureInfo mo6464newInstance() {
        return new SignatureInfo();
    }

    @Override // com.reandroid.archive.block.LengthPrefixedList
    public void onRefreshed() {
        SignatureFooter signatureFooter = getSignatureFooter();
        signatureFooter.updateMagic();
        super.onRefreshed();
        signatureFooter.setSignatureSize(getDataSize());
    }

    public void read(File file) throws IOException {
        super/*com.reandroid.arsc.base.Block*/.readBytes(new BlockReader(file));
    }

    public void scanSplitFiles(File file) throws IOException {
        if (!file.isDirectory()) {
            a16.a("No such directory");
            return;
        }
        File[] fileArrListFiles = file.listFiles(new FileFilter() { // from class: com.reandroid.archive.block.ApkSignatureBlock.2
            @Override // java.io.FileFilter
            public boolean accept(File file2) {
                if (file2.isFile()) {
                    return file2.getName().toLowerCase().endsWith(SignatureId.FILE_EXT_RAW);
                }
                return false;
            }
        });
        if (fileArrListFiles == null) {
            return;
        }
        for (File file2 : fileArrListFiles) {
            addSplitRaw(file2);
        }
        sortSignatures();
    }

    public void sortSignatures() {
        sort(this);
    }

    public void updatePadding() {
        SchemePadding orCreateSchemePadding = getOrCreateSchemePadding();
        orCreateSchemePadding.setPadding(0);
        sortSignatures();
        refresh();
        orCreateSchemePadding.setPadding((4096 - (countBytes() % 4096)) % 4096);
        refresh();
    }

    public void writeRaw(File file) throws IOException {
        refresh();
        OutputStream outputStream = FileUtil.outputStream(file);
        writeBytes(outputStream);
        outputStream.close();
    }

    public List<File> writeSplitRawToDirectory(File file) throws IOException {
        refresh();
        ArrayCollection arrayCollection = new ArrayCollection(size());
        Iterator<SignatureInfo> it = iterator();
        while (it.hasNext()) {
            arrayCollection.add(it.next().writeRawToDirectory(file));
        }
        return arrayCollection;
    }

    public ApkSignatureBlock(SignatureFooter signatureFooter) {
        super(true);
        setBottomBlock(signatureFooter);
    }
}
