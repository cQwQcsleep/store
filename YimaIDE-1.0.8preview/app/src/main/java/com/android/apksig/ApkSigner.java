package com.android.apksig;

import com.android.apksig.ApkSigner;
import com.android.apksig.KeyConfig;
import com.android.apksig.apk.ApkFormatException;
import com.android.apksig.apk.ApkSigningBlockNotFoundException;
import com.android.apksig.apk.ApkUtils;
import com.android.apksig.apk.MinSdkVersionException;
import com.android.apksig.internal.util.ByteBufferDataSource;
import com.android.apksig.internal.zip.CentralDirectoryRecord;
import com.android.apksig.internal.zip.EocdRecord;
import com.android.apksig.internal.zip.LocalFileRecord;
import com.android.apksig.internal.zip.ZipUtils;
import com.android.apksig.util.DataSink;
import com.android.apksig.util.DataSinks;
import com.android.apksig.util.DataSource;
import com.android.apksig.util.DataSources;
import com.android.apksig.util.ReadableDataSink;
import com.android.apksig.zip.ZipFormatException;
import defpackage.ib0;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SignatureException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkSigner {
    private static final short ALIGNMENT_ZIP_EXTRA_DATA_FIELD_HEADER_ID = -9931;
    private static final short ALIGNMENT_ZIP_EXTRA_DATA_FIELD_MIN_SIZE_BYTES = 6;
    private static final short ANDROID_FILE_ALIGNMENT_BYTES = 4096;
    private static final String ANDROID_MANIFEST_ZIP_ENTRY_NAME = "AndroidManifest.xml";
    private final boolean mAlignFileSize;
    private final boolean mAlignmentPreserved;
    private final String mCreatedBy;
    private final boolean mDebuggableApkPermitted;
    private final boolean mForceSourceStampOverwrite;
    private final DataSource mInputApkDataSource;
    private final File mInputApkFile;
    private final int mLibraryPageAlignmentBytes;
    private final Integer mMinSdkVersion;
    private final boolean mOtherSignersSignaturesPreserved;
    private final DataSink mOutputApkDataSink;
    private final DataSource mOutputApkDataSource;
    private final File mOutputApkFile;
    private final File mOutputV4File;
    private final int mRotationMinSdkVersion;
    private final boolean mRotationTargetsDevRelease;
    private final List<SignerConfig> mSignerConfigs;
    private final ApkSignerEngine mSignerEngine;
    private final SigningCertificateLineage mSigningCertificateLineage;
    private final SignerConfig mSourceStampSignerConfig;
    private final SigningCertificateLineage mSourceStampSigningCertificateLineage;
    private final boolean mSourceStampTimestampEnabled;
    private final boolean mV1SigningEnabled;
    private final boolean mV2SigningEnabled;
    private final boolean mV3SigningEnabled;
    private final boolean mV4ErrorReportingEnabled;
    private final boolean mV4SigningEnabled;
    private final boolean mVerityEnabled;

    /* JADX INFO: renamed from: com.android.apksig.ApkSigner$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$apksig$ApkSignerEngine$InputJarEntryInstructions$OutputPolicy;

        static {
            int[] iArr = new int[ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.values().length];
            $SwitchMap$com$android$apksig$ApkSignerEngine$InputJarEntryInstructions$OutputPolicy = iArr;
            try {
                iArr[ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.OUTPUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$apksig$ApkSignerEngine$InputJarEntryInstructions$OutputPolicy[ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.OUTPUT_BY_ENGINE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$apksig$ApkSignerEngine$InputJarEntryInstructions$OutputPolicy[ApkSignerEngine.InputJarEntryInstructions.OutputPolicy.SKIP.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public static class OutputSizeAndDataOffset {
        public long dataOffsetBytes;
        public long outputBytes;

        public OutputSizeAndDataOffset(long j, long j2) {
            this.outputBytes = j;
            this.dataOffsetBytes = j2;
        }
    }

    private ApkSigner(List<SignerConfig> list, SignerConfig signerConfig, SigningCertificateLineage signingCertificateLineage, boolean z, boolean z2, Integer num, int i, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, int i2, String str, ApkSignerEngine apkSignerEngine, File file, DataSource dataSource, File file2, DataSink dataSink, DataSource dataSource2, File file3, SigningCertificateLineage signingCertificateLineage2) {
        this.mSignerConfigs = list;
        this.mSourceStampSignerConfig = signerConfig;
        this.mSourceStampSigningCertificateLineage = signingCertificateLineage;
        this.mForceSourceStampOverwrite = z;
        this.mSourceStampTimestampEnabled = z2;
        this.mMinSdkVersion = num;
        this.mRotationMinSdkVersion = i;
        this.mRotationTargetsDevRelease = z3;
        this.mV1SigningEnabled = z4;
        this.mV2SigningEnabled = z5;
        this.mV3SigningEnabled = z6;
        this.mV4SigningEnabled = z7;
        this.mAlignFileSize = z8;
        this.mVerityEnabled = z9;
        this.mV4ErrorReportingEnabled = z10;
        this.mDebuggableApkPermitted = z11;
        this.mOtherSignersSignaturesPreserved = z12;
        this.mAlignmentPreserved = z13;
        this.mLibraryPageAlignmentBytes = i2;
        this.mCreatedBy = str;
        this.mSignerEngine = apkSignerEngine;
        this.mInputApkFile = file;
        this.mInputApkDataSource = dataSource;
        this.mOutputApkFile = file2;
        this.mOutputApkDataSink = dataSink;
        this.mOutputApkDataSource = dataSource2;
        this.mOutputV4File = file3;
        this.mSigningCertificateLineage = signingCertificateLineage2;
    }

    private static ByteBuffer createExtraFieldToAlignData(ByteBuffer byteBuffer, long j, int i) {
        if (i <= 1) {
            return byteBuffer;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(byteBuffer.remaining() + 5 + i);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        while (byteBuffer.remaining() >= 4) {
            short s = byteBuffer.getShort();
            int unsignedInt16 = ZipUtils.getUnsignedInt16(byteBuffer);
            if (unsignedInt16 > byteBuffer.remaining()) {
                break;
            }
            if ((s == 0 && unsignedInt16 == 0) || s == -9931) {
                byteBuffer.position(byteBuffer.position() + unsignedInt16);
            } else {
                byteBuffer.position(byteBuffer.position() - 4);
                int iLimit = byteBuffer.limit();
                byteBuffer.limit(byteBuffer.position() + 4 + unsignedInt16);
                byteBufferAllocate.put(byteBuffer);
                byteBuffer.limit(iLimit);
            }
        }
        int iPosition = (i - ((int) (((j + ((long) byteBufferAllocate.position())) + 6) % ((long) i)))) % i;
        byteBufferAllocate.putShort(ALIGNMENT_ZIP_EXTRA_DATA_FIELD_HEADER_ID);
        ZipUtils.putUnsignedInt16(byteBufferAllocate, iPosition + 2);
        ZipUtils.putUnsignedInt16(byteBufferAllocate, i);
        byteBufferAllocate.position(byteBufferAllocate.position() + iPosition);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    private static List<Hints.PatternWithRange> extractPinPatterns(List<CentralDirectoryRecord> list, DataSource dataSource) throws IOException, ApkFormatException {
        CentralDirectoryRecord centralDirectoryRecordFindCdRecord = findCdRecord(list, Hints.PIN_HINT_ASSET_ZIP_ENTRY_NAME);
        if (centralDirectoryRecordFindCdRecord == null) {
            return null;
        }
        new ArrayList();
        try {
            return Hints.parsePinPatterns(LocalFileRecord.getUncompressedData(dataSource, centralDirectoryRecordFindCdRecord, dataSource.size()));
        } catch (ZipFormatException unused) {
            throw new ApkFormatException("Bad " + centralDirectoryRecordFindCdRecord);
        }
    }

    private static CentralDirectoryRecord findCdRecord(List<CentralDirectoryRecord> list, String str) {
        for (CentralDirectoryRecord centralDirectoryRecord : list) {
            if (str.equals(centralDirectoryRecord.getName())) {
                return centralDirectoryRecord;
            }
        }
        return null;
    }

    private static void fulfillInspectInputJarEntryRequest(DataSource dataSource, LocalFileRecord localFileRecord, ApkSignerEngine.InspectJarEntryRequest inspectJarEntryRequest) throws IOException, ApkFormatException {
        try {
            localFileRecord.outputUncompressedData(dataSource, inspectJarEntryRequest.getDataSink());
            inspectJarEntryRequest.done();
        } catch (ZipFormatException e) {
            throw new ApkFormatException("Malformed ZIP entry: " + localFileRecord.getName(), e);
        }
    }

    public static ByteBuffer getAndroidManifestFromApk(List<CentralDirectoryRecord> list, DataSource dataSource) throws IOException, ApkFormatException, ZipFormatException {
        CentralDirectoryRecord centralDirectoryRecordFindCdRecord = findCdRecord(list, "AndroidManifest.xml");
        if (centralDirectoryRecordFindCdRecord != null) {
            return ByteBuffer.wrap(LocalFileRecord.getUncompressedData(dataSource, centralDirectoryRecordFindCdRecord, dataSource.size()));
        }
        throw new ApkFormatException("Missing AndroidManifest.xml");
    }

    private int getInputJarEntryDataAlignmentMultiple(LocalFileRecord localFileRecord) {
        if (localFileRecord.isDataCompressed()) {
            return 1;
        }
        ByteBuffer extra = localFileRecord.getExtra();
        if (extra.hasRemaining()) {
            extra.order(ByteOrder.LITTLE_ENDIAN);
            while (extra.remaining() >= 4) {
                short s = extra.getShort();
                int unsignedInt16 = ZipUtils.getUnsignedInt16(extra);
                if (unsignedInt16 > extra.remaining()) {
                    break;
                }
                if (s == -9931) {
                    if (unsignedInt16 < 2) {
                        break;
                    }
                    return ZipUtils.getUnsignedInt16(extra);
                }
                extra.position(extra.position() + unsignedInt16);
            }
        }
        if (localFileRecord.getName().endsWith(".so")) {
            return this.mLibraryPageAlignmentBytes;
        }
        return 4;
    }

    private static int getMinSdkVersionFromApk(List<CentralDirectoryRecord> list, DataSource dataSource) throws MinSdkVersionException, IOException {
        try {
            return ApkUtils.getMinSdkVersionFromBinaryAndroidManifest(getAndroidManifestFromApk(list, dataSource));
        } catch (ApkFormatException | ZipFormatException e) {
            throw new MinSdkVersionException("Failed to determine APK's minimum supported Android platform version", e);
        }
    }

    private static ByteBuffer getZipCentralDirectory(DataSource dataSource, ApkUtils.ZipSections zipSections) throws IOException, ApkFormatException {
        long zipCentralDirectorySizeBytes = zipSections.getZipCentralDirectorySizeBytes();
        if (zipCentralDirectorySizeBytes <= 2147483647L) {
            ByteBuffer byteBuffer = dataSource.getByteBuffer(zipSections.getZipCentralDirectoryOffset(), (int) zipCentralDirectorySizeBytes);
            byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
            return byteBuffer;
        }
        throw new ApkFormatException("ZIP Central Directory too large: " + zipCentralDirectorySizeBytes);
    }

    private static long outputDataToOutputApk(String str, byte[] bArr, long j, List<CentralDirectoryRecord> list, int i, int i2, DataSink dataSink) throws IOException {
        ZipUtils.DeflateResult deflateResultDeflate = ZipUtils.deflate(ByteBuffer.wrap(bArr));
        byte[] bArr2 = deflateResultDeflate.output;
        long j2 = deflateResultDeflate.inputCrc32;
        long jOutputRecordWithDeflateCompressedData = LocalFileRecord.outputRecordWithDeflateCompressedData(str, i, i2, bArr2, j2, bArr.length, dataSink);
        list.add(CentralDirectoryRecord.createWithDeflateCompressedData(str, i, i2, j2, bArr2.length, bArr.length, j));
        return jOutputRecordWithDeflateCompressedData;
    }

    private OutputSizeAndDataOffset outputInputJarEntryLfhRecord(DataSource dataSource, LocalFileRecord localFileRecord, DataSink dataSink, long j) throws IOException {
        long startOffsetInArchive = localFileRecord.getStartOffsetInArchive();
        if (startOffsetInArchive == j && this.mAlignmentPreserved) {
            return new OutputSizeAndDataOffset(localFileRecord.outputRecord(dataSource, dataSink), localFileRecord.getDataStartOffsetInRecord());
        }
        int inputJarEntryDataAlignmentMultiple = getInputJarEntryDataAlignmentMultiple(localFileRecord);
        if (inputJarEntryDataAlignmentMultiple > 1) {
            long j2 = inputJarEntryDataAlignmentMultiple;
            if (startOffsetInArchive % j2 != j % j2 || !this.mAlignmentPreserved) {
                if ((startOffsetInArchive + ((long) localFileRecord.getDataStartOffsetInRecord())) % j2 != 0 && this.mAlignmentPreserved) {
                    return new OutputSizeAndDataOffset(localFileRecord.outputRecord(dataSource, dataSink), localFileRecord.getDataStartOffsetInRecord());
                }
                ByteBuffer byteBufferCreateExtraFieldToAlignData = createExtraFieldToAlignData(localFileRecord.getExtra(), j + ((long) localFileRecord.getExtraFieldStartOffsetInsideRecord()), inputJarEntryDataAlignmentMultiple);
                return new OutputSizeAndDataOffset(localFileRecord.outputRecordWithModifiedExtra(dataSource, byteBufferCreateExtraFieldToAlignData, dataSink), (((long) localFileRecord.getDataStartOffsetInRecord()) + ((long) byteBufferCreateExtraFieldToAlignData.remaining())) - ((long) localFileRecord.getExtra().remaining()));
            }
        }
        return new OutputSizeAndDataOffset(localFileRecord.outputRecord(dataSource, dataSink), localFileRecord.getDataStartOffsetInRecord());
    }

    private static List<CentralDirectoryRecord> parseZipCentralDirectory(ByteBuffer byteBuffer, ApkUtils.ZipSections zipSections) throws ApkFormatException {
        long zipCentralDirectoryOffset = zipSections.getZipCentralDirectoryOffset();
        int zipCentralDirectoryRecordCount = zipSections.getZipCentralDirectoryRecordCount();
        ArrayList arrayList = new ArrayList(zipCentralDirectoryRecordCount);
        HashSet hashSet = new HashSet(zipCentralDirectoryRecordCount);
        for (int i = 0; i < zipCentralDirectoryRecordCount; i++) {
            int iPosition = byteBuffer.position();
            try {
                CentralDirectoryRecord record = CentralDirectoryRecord.getRecord(byteBuffer);
                String name = record.getName();
                if (!hashSet.add(name)) {
                    throw new ApkFormatException("Multiple ZIP entries with the same name: " + name);
                }
                arrayList.add(record);
            } catch (ZipFormatException e) {
                throw new ApkFormatException("Malformed ZIP Central Directory record #" + (i + 1) + " at file offset " + (zipCentralDirectoryOffset + ((long) iPosition)), e);
            }
        }
        if (!byteBuffer.hasRemaining()) {
            return arrayList;
        }
        throw new ApkFormatException("Unused space at the end of ZIP Central Directory: " + byteBuffer.remaining() + " bytes starting at file offset " + (zipCentralDirectoryOffset + ((long) byteBuffer.position())));
    }

    private static void requestOutputEntryInspection(ApkSignerEngine apkSignerEngine, String str, byte[] bArr) throws IOException {
        ApkSignerEngine.InspectJarEntryRequest inspectJarEntryRequestOutputJarEntry = apkSignerEngine.outputJarEntry(str);
        if (inspectJarEntryRequestOutputJarEntry != null) {
            inspectJarEntryRequestOutputJarEntry.getDataSink().consume(bArr, 0, bArr.length);
            inspectJarEntryRequestOutputJarEntry.done();
        }
    }

    private void sign(DataSource dataSource, DataSink dataSink, DataSource dataSource2) throws SignatureException, NoSuchAlgorithmException, IOException, InvalidKeyException, ApkFormatException {
        long zipCentralDirectoryOffset;
        DataSource contents;
        DataSource dataSourceSlice;
        List<CentralDirectoryRecord> zipCentralDirectory;
        List<Hints.PatternWithRange> listExtractPinPatterns;
        ArrayList arrayList;
        long j;
        ApkSignerEngine apkSignerEngine;
        HashMap map;
        Iterator it;
        long j2;
        long j3;
        int i;
        int i2;
        byte[] uncompressedData;
        int i3;
        int i4;
        int i5;
        boolean z;
        DataSource dataSource3;
        byte[] bArr;
        int i6;
        int i7;
        Hints.ByteRange byteRangeClampToAbsoluteByteRange;
        try {
            ApkUtils.ZipSections zipSectionsFindZipSections = ApkUtils.findZipSections(dataSource);
            try {
                ApkUtils.ApkSigningBlock apkSigningBlockFindApkSigningBlock = ApkUtils.findApkSigningBlock(dataSource, zipSectionsFindZipSections);
                zipCentralDirectoryOffset = apkSigningBlockFindApkSigningBlock.getStartOffset();
                try {
                    contents = apkSigningBlockFindApkSigningBlock.getContents();
                    while (true) {
                        zipSectionsFindZipSections = zipSectionsFindZipSections;
                        if (!it.hasNext()) {
                            int i8 = i;
                            DataSource dataSource4 = dataSourceSlice;
                            int i9 = i2;
                            long j4 = j2;
                            List<CentralDirectoryRecord> list = zipCentralDirectory;
                            byte[] bArr2 = uncompressedData;
                            long size = dataSource4.size();
                            if (j4 < size) {
                                long j5 = size - j4;
                                dataSource4.feed(j4, j5, dataSink);
                                j3 += j5;
                            }
                            long jOutputDataToOutputApk = j3;
                            ArrayList arrayList2 = new ArrayList(list.size() + 10);
                            Iterator<CentralDirectoryRecord> it2 = list.iterator();
                            while (it2.hasNext()) {
                                CentralDirectoryRecord centralDirectoryRecord = (CentralDirectoryRecord) map.get(it2.next().getName());
                                if (centralDirectoryRecord != null) {
                                    arrayList2.add(centralDirectoryRecord);
                                }
                            }
                            if (i8 == -1) {
                                i3 = 14881;
                                i4 = 0;
                            } else {
                                i3 = i8;
                                i4 = i9;
                            }
                            if (apkSignerEngine.isEligibleForSourceStamp()) {
                                i5 = i3;
                                byte[] bArrGenerateSourceStampCertificateDigest = apkSignerEngine.generateSourceStampCertificateDigest();
                                if (!this.mForceSourceStampOverwrite && bArr2 != null && !Arrays.equals(bArrGenerateSourceStampCertificateDigest, bArr2)) {
                                    throw new ApkFormatException(String.format("Cannot generate SourceStamp. APK contains an existing entry with the name: %s, and it is different than the provided source stamp certificate", "stamp-cert-sha256"));
                                }
                                jOutputDataToOutputApk += outputDataToOutputApk("stamp-cert-sha256", bArrGenerateSourceStampCertificateDigest, jOutputDataToOutputApk, arrayList2, i4, i5, dataSink);
                            } else {
                                i5 = i3;
                            }
                            if (arrayList != null) {
                                arrayList.add(new Hints.ByteRange(jOutputDataToOutputApk, Long.MAX_VALUE));
                                byte[] bArrEncodeByteRangeList = Hints.encodeByteRangeList(arrayList);
                                requestOutputEntryInspection(apkSignerEngine, Hints.PIN_BYTE_RANGE_ZIP_ENTRY_NAME, bArrEncodeByteRangeList);
                                jOutputDataToOutputApk += outputDataToOutputApk(Hints.PIN_BYTE_RANGE_ZIP_ENTRY_NAME, bArrEncodeByteRangeList, jOutputDataToOutputApk, arrayList2, i4, i5, dataSink);
                            }
                            ApkSignerEngine.OutputJarSignatureRequest outputJarSignatureRequestOutputJarEntries = apkSignerEngine.outputJarEntries();
                            if (outputJarSignatureRequestOutputJarEntries != null) {
                                for (ApkSignerEngine.OutputJarSignatureRequest.JarEntry jarEntry : outputJarSignatureRequestOutputJarEntries.getAdditionalJarEntries()) {
                                    String name = jarEntry.getName();
                                    byte[] data = jarEntry.getData();
                                    requestOutputEntryInspection(apkSignerEngine, name, data);
                                    jOutputDataToOutputApk += outputDataToOutputApk(name, data, jOutputDataToOutputApk, arrayList2, i4, i5, dataSink);
                                }
                                outputJarSignatureRequestOutputJarEntries.done();
                            }
                            long j6 = jOutputDataToOutputApk;
                            Iterator it3 = arrayList2.iterator();
                            long size2 = j;
                            while (it3.hasNext()) {
                                size2 += (long) ((CentralDirectoryRecord) it3.next()).getSize();
                            }
                            if (size2 > 2147483647L) {
                                throw new IOException("Output ZIP Central Directory too large: " + size2 + " bytes");
                            }
                            ByteBuffer byteBufferAllocate = ByteBuffer.allocate((int) size2);
                            Iterator it4 = arrayList2.iterator();
                            while (it4.hasNext()) {
                                ((CentralDirectoryRecord) it4.next()).copyTo(byteBufferAllocate);
                            }
                            byteBufferAllocate.flip();
                            ByteBufferDataSource byteBufferDataSource = new ByteBufferDataSource(byteBufferAllocate);
                            ByteBuffer byteBufferCreateWithModifiedCentralDirectoryInfo = EocdRecord.createWithModifiedCentralDirectoryInfo(zipSectionsFindZipSections.getZipEndOfCentralDirectory(), arrayList2.size(), byteBufferDataSource.size(), j6);
                            ApkSignerEngine.OutputApkSigningBlockRequest2 outputApkSigningBlockRequest2OutputZipSections2 = apkSignerEngine.outputZipSections2(dataSource2, byteBufferDataSource, DataSources.asDataSource(byteBufferCreateWithModifiedCentralDirectoryInfo));
                            if (outputApkSigningBlockRequest2OutputZipSections2 != null) {
                                int paddingSizeBeforeApkSigningBlock = outputApkSigningBlockRequest2OutputZipSections2.getPaddingSizeBeforeApkSigningBlock();
                                byte[] apkSigningBlock = outputApkSigningBlockRequest2OutputZipSections2.getApkSigningBlock();
                                outputApkSigningBlockRequest2OutputZipSections2.done();
                                long j7 = paddingSizeBeforeApkSigningBlock;
                                long size3 = j6 + byteBufferDataSource.size() + j7 + ((long) apkSigningBlock.length) + ((long) byteBufferCreateWithModifiedCentralDirectoryInfo.remaining());
                                if (this.mAlignFileSize) {
                                    long j8 = size3 % 4096;
                                    if (j8 != j) {
                                        byteBufferCreateWithModifiedCentralDirectoryInfo = EocdRecord.createWithPaddedComment(byteBufferCreateWithModifiedCentralDirectoryInfo, (int) (4096 - j8));
                                        ApkSignerEngine.OutputApkSigningBlockRequest2 outputApkSigningBlockRequest2OutputZipSections3 = apkSignerEngine.outputZipSections2(dataSource2, new ByteBufferDataSource(byteBufferAllocate), DataSources.asDataSource(byteBufferCreateWithModifiedCentralDirectoryInfo));
                                        apkSigningBlock = outputApkSigningBlockRequest2OutputZipSections3.getApkSigningBlock();
                                        outputApkSigningBlockRequest2OutputZipSections3.done();
                                    }
                                }
                                dataSink.consume(ByteBuffer.allocate(paddingSizeBeforeApkSigningBlock));
                                dataSink.consume(apkSigningBlock, 0, apkSigningBlock.length);
                                ZipUtils.setZipEocdCentralDirectoryOffset(byteBufferCreateWithModifiedCentralDirectoryInfo, j6 + j7 + ((long) apkSigningBlock.length));
                            }
                            byteBufferDataSource.feed(0L, byteBufferDataSource.size(), dataSink);
                            dataSink.consume(byteBufferCreateWithModifiedCentralDirectoryInfo);
                            apkSignerEngine.outputDone();
                            if (this.mV4SigningEnabled) {
                                apkSignerEngine.signV4(dataSource2, this.mOutputV4File, !this.mV4ErrorReportingEnabled);
                                return;
                            }
                            return;
                        }
                        CentralDirectoryRecord centralDirectoryRecordCreateWithModifiedLocalFileHeaderOffset = (CentralDirectoryRecord) it.next();
                        zipCentralDirectory = zipCentralDirectory;
                        String name2 = centralDirectoryRecordCreateWithModifiedLocalFileHeaderOffset.getName();
                        if (!Hints.PIN_BYTE_RANGE_ZIP_ENTRY_NAME.equals(name2)) {
                            if ("stamp-cert-sha256".equals(name2)) {
                                int i10 = i;
                                try {
                                    uncompressedData = LocalFileRecord.getUncompressedData(dataSourceSlice, centralDirectoryRecordCreateWithModifiedLocalFileHeaderOffset, dataSourceSlice.size());
                                    i = i10;
                                } catch (ZipFormatException unused) {
                                    throw new ApkFormatException("Bad source stamp entry");
                                }
                            } else {
                                int i11 = i;
                                ApkSignerEngine.InputJarEntryInstructions inputJarEntryInstructionsInputJarEntry = apkSignerEngine.inputJarEntry(name2);
                                int i12 = AnonymousClass1.$SwitchMap$com$android$apksig$ApkSignerEngine$InputJarEntryInstructions$OutputPolicy[inputJarEntryInstructionsInputJarEntry.getOutputPolicy().ordinal()];
                                if (i12 == 1) {
                                    z = true;
                                } else {
                                    if (i12 != 2 && i12 != 3) {
                                        ib0.a("Unknown output policy: ", inputJarEntryInstructionsInputJarEntry.getOutputPolicy());
                                        return;
                                    }
                                    z = false;
                                }
                                long localFileHeaderOffset = centralDirectoryRecordCreateWithModifiedLocalFileHeaderOffset.getLocalFileHeaderOffset();
                                if (localFileHeaderOffset > j2) {
                                    dataSource3 = dataSourceSlice;
                                    byte[] bArr3 = uncompressedData;
                                    long j9 = j2;
                                    long j10 = localFileHeaderOffset - j9;
                                    bArr = bArr3;
                                    i6 = i2;
                                    dataSource3.feed(j9, j10, dataSink);
                                    j3 += j10;
                                    j2 = localFileHeaderOffset;
                                } else {
                                    dataSource3 = dataSourceSlice;
                                    bArr = uncompressedData;
                                    i6 = i2;
                                }
                                try {
                                    LocalFileRecord record = LocalFileRecord.getRecord(dataSource3, centralDirectoryRecordCreateWithModifiedLocalFileHeaderOffset, dataSource3.size());
                                    long size4 = j2 + record.getSize();
                                    ApkSignerEngine.InspectJarEntryRequest inspectJarEntryRequest = inputJarEntryInstructionsInputJarEntry.getInspectJarEntryRequest();
                                    if (inspectJarEntryRequest != null) {
                                        fulfillInspectInputJarEntryRequest(dataSource3, record, inspectJarEntryRequest);
                                    }
                                    if (z) {
                                        int lastModificationDate = centralDirectoryRecordCreateWithModifiedLocalFileHeaderOffset.getLastModificationDate();
                                        int lastModificationTime = centralDirectoryRecordCreateWithModifiedLocalFileHeaderOffset.getLastModificationTime();
                                        if (i11 == -1 || lastModificationDate > i11 || (lastModificationDate == i11 && lastModificationTime > i6)) {
                                            i11 = lastModificationDate;
                                            i6 = lastModificationTime;
                                        }
                                        ApkSignerEngine.InspectJarEntryRequest inspectJarEntryRequestOutputJarEntry = apkSignerEngine.outputJarEntry(name2);
                                        if (inspectJarEntryRequestOutputJarEntry != null) {
                                            fulfillInspectInputJarEntryRequest(dataSource3, record, inspectJarEntryRequestOutputJarEntry);
                                        }
                                        long j11 = j3;
                                        OutputSizeAndDataOffset outputSizeAndDataOffsetOutputInputJarEntryLfhRecord = outputInputJarEntryLfhRecord(dataSource3, record, dataSink, j11);
                                        long j12 = outputSizeAndDataOffsetOutputInputJarEntryLfhRecord.outputBytes + j11;
                                        long j13 = outputSizeAndDataOffsetOutputInputJarEntryLfhRecord.dataOffsetBytes + j11;
                                        if (listExtractPinPatterns != 0) {
                                            Iterator<Hints.PatternWithRange> it5 = listExtractPinPatterns.iterator();
                                            boolean z2 = false;
                                            while (it5.hasNext()) {
                                                Iterator<Hints.PatternWithRange> it6 = it5;
                                                Hints.PatternWithRange next = it5.next();
                                                int i13 = i11;
                                                if (next.matcher(centralDirectoryRecordCreateWithModifiedLocalFileHeaderOffset.getName()).matches() && (byteRangeClampToAbsoluteByteRange = next.ClampToAbsoluteByteRange(new Hints.ByteRange(j13, j12))) != null) {
                                                    arrayList.add(byteRangeClampToAbsoluteByteRange);
                                                    z2 = true;
                                                }
                                                i11 = i13;
                                                it5 = it6;
                                            }
                                            i7 = i11;
                                            if (z2) {
                                                arrayList.add(new Hints.ByteRange(j11, j13));
                                            }
                                        } else {
                                            i7 = i11;
                                        }
                                        if (j11 != record.getStartOffsetInArchive()) {
                                            centralDirectoryRecordCreateWithModifiedLocalFileHeaderOffset = centralDirectoryRecordCreateWithModifiedLocalFileHeaderOffset.createWithModifiedLocalFileHeaderOffset(j11);
                                        }
                                        map.put(name2, centralDirectoryRecordCreateWithModifiedLocalFileHeaderOffset);
                                        i = i7;
                                        j3 = j12;
                                    } else {
                                        i = i11;
                                    }
                                    i2 = i6;
                                    uncompressedData = bArr;
                                    it = it;
                                    j2 = size4;
                                    dataSourceSlice = dataSource3;
                                    listExtractPinPatterns = listExtractPinPatterns;
                                } catch (ZipFormatException e) {
                                    throw new ApkFormatException("Malformed ZIP entry: " + centralDirectoryRecordCreateWithModifiedLocalFileHeaderOffset.getName(), e);
                                }
                            }
                        }
                    }
                } catch (ApkSigningBlockNotFoundException unused2) {
                    contents = null;
                }
            } catch (ApkSigningBlockNotFoundException unused3) {
                zipCentralDirectoryOffset = -1;
            }
            if (zipCentralDirectoryOffset == -1) {
                zipCentralDirectoryOffset = zipSectionsFindZipSections.getZipCentralDirectoryOffset();
            }
            long j14 = 0;
            dataSourceSlice = dataSource.slice(0L, zipCentralDirectoryOffset);
            zipCentralDirectory = parseZipCentralDirectory(getZipCentralDirectory(dataSource, zipSectionsFindZipSections), zipSectionsFindZipSections);
            listExtractPinPatterns = extractPinPatterns(zipCentralDirectory, dataSourceSlice);
            arrayList = listExtractPinPatterns == null ? null : new ArrayList();
            ApkSignerEngine apkSignerEngineBuild = this.mSignerEngine;
            if (apkSignerEngineBuild != null) {
                j = 0;
            } else {
                Integer num = this.mMinSdkVersion;
                int iIntValue = num != null ? num.intValue() : getMinSdkVersionFromApk(zipCentralDirectory, dataSourceSlice);
                ArrayList arrayList3 = new ArrayList(this.mSignerConfigs.size());
                Iterator<SignerConfig> it7 = this.mSignerConfigs.iterator();
                while (it7.hasNext()) {
                    SignerConfig next2 = it7.next();
                    long j15 = j14;
                    Iterator<SignerConfig> it8 = it7;
                    DefaultApkSignerEngine.SignerConfig.Builder builder = new DefaultApkSignerEngine.SignerConfig.Builder(next2.getName(), next2.getKeyConfig(), next2.getCertificates(), next2.getDeterministicDsaSigning());
                    int minSdkVersion = next2.getMinSdkVersion();
                    SigningCertificateLineage signingCertificateLineage = next2.getSigningCertificateLineage();
                    if (minSdkVersion > 0) {
                        builder.setLineageForMinSdkVersion(signingCertificateLineage, minSdkVersion);
                    }
                    arrayList3.add(builder.build());
                    it7 = it8;
                    j14 = j15;
                }
                j = j14;
                DefaultApkSignerEngine.Builder rotationTargetsDevRelease = new DefaultApkSignerEngine.Builder(arrayList3, iIntValue).setV1SigningEnabled(this.mV1SigningEnabled).setV2SigningEnabled(this.mV2SigningEnabled).setV3SigningEnabled(this.mV3SigningEnabled).setVerityEnabled(this.mVerityEnabled).setDebuggableApkPermitted(this.mDebuggableApkPermitted).setOtherSignersSignaturesPreserved(this.mOtherSignersSignaturesPreserved).setSigningCertificateLineage(this.mSigningCertificateLineage).setMinSdkVersionForRotation(this.mRotationMinSdkVersion).setRotationTargetsDevRelease(this.mRotationTargetsDevRelease);
                String str = this.mCreatedBy;
                if (str != null) {
                    rotationTargetsDevRelease.setCreatedBy(str);
                }
                SignerConfig signerConfig = this.mSourceStampSignerConfig;
                if (signerConfig != null) {
                    rotationTargetsDevRelease.setStampSignerConfig(new DefaultApkSignerEngine.SignerConfig.Builder(signerConfig.getName(), this.mSourceStampSignerConfig.getKeyConfig(), this.mSourceStampSignerConfig.getCertificates(), this.mSourceStampSignerConfig.getDeterministicDsaSigning()).build());
                    rotationTargetsDevRelease.setSourceStampTimestampEnabled(this.mSourceStampTimestampEnabled);
                }
                SigningCertificateLineage signingCertificateLineage2 = this.mSourceStampSigningCertificateLineage;
                if (signingCertificateLineage2 != null) {
                    rotationTargetsDevRelease.setSourceStampSigningCertificateLineage(signingCertificateLineage2);
                }
                apkSignerEngineBuild = rotationTargetsDevRelease.build();
            }
            apkSignerEngine = apkSignerEngineBuild;
            if (contents != null) {
                apkSignerEngine.inputApkSigningBlock(contents);
            }
            ArrayList arrayList4 = new ArrayList(zipCentralDirectory);
            Collections.sort(arrayList4, CentralDirectoryRecord.BY_LOCAL_FILE_HEADER_OFFSET_COMPARATOR);
            map = new HashMap(zipCentralDirectory.size());
            it = arrayList4.iterator();
            j2 = j;
            j3 = j2;
            i = -1;
            i2 = -1;
            uncompressedData = null;
        } catch (ZipFormatException e2) {
            throw new ApkFormatException("Malformed APK: not a ZIP archive", e2);
        }
    }

    public static class Builder {
        private boolean mAlignFileSize;
        private boolean mAlignmentPreserved;
        private String mCreatedBy;
        private boolean mDebuggableApkPermitted;
        private boolean mForceSourceStampOverwrite;
        private DataSource mInputApkDataSource;
        private File mInputApkFile;
        private int mLibraryPageAlignmentBytes;
        private Integer mMinSdkVersion;
        private boolean mOtherSignersSignaturesPreserved;
        private DataSink mOutputApkDataSink;
        private DataSource mOutputApkDataSource;
        private File mOutputApkFile;
        private File mOutputV4File;
        private int mRotationMinSdkVersion;
        private boolean mRotationTargetsDevRelease;
        private final List<SignerConfig> mSignerConfigs;
        private final ApkSignerEngine mSignerEngine;
        private SigningCertificateLineage mSigningCertificateLineage;
        private SignerConfig mSourceStampSignerConfig;
        private SigningCertificateLineage mSourceStampSigningCertificateLineage;
        private boolean mSourceStampTimestampEnabled;
        private boolean mV1SigningEnabled;
        private boolean mV2SigningEnabled;
        private boolean mV3SigningEnabled;
        private boolean mV3SigningExplicitlyDisabled;
        private boolean mV3SigningExplicitlyEnabled;
        private boolean mV4ErrorReportingEnabled;
        private boolean mV4SigningEnabled;
        private boolean mVerityEnabled;

        public Builder(List<SignerConfig> list) {
            this.mForceSourceStampOverwrite = false;
            this.mSourceStampTimestampEnabled = true;
            this.mV1SigningEnabled = true;
            this.mV2SigningEnabled = true;
            this.mV3SigningEnabled = true;
            this.mV4SigningEnabled = false;
            this.mAlignFileSize = false;
            this.mVerityEnabled = false;
            this.mV4ErrorReportingEnabled = false;
            this.mDebuggableApkPermitted = true;
            this.mAlignmentPreserved = false;
            this.mLibraryPageAlignmentBytes = 16384;
            this.mRotationMinSdkVersion = 33;
            this.mRotationTargetsDevRelease = false;
            this.mV3SigningExplicitlyDisabled = false;
            this.mV3SigningExplicitlyEnabled = false;
            if (list.isEmpty()) {
                w01.a("At least one signer config must be provided");
                throw null;
            }
            if (list.size() > 1) {
                this.mV3SigningEnabled = false;
            }
            this.mSignerConfigs = new ArrayList(list);
            this.mSignerEngine = null;
        }

        private void checkInitializedWithoutEngine() {
            if (this.mSignerEngine == null) {
                return;
            }
            k2d.a("Operation is not available when builder initialized with an engine");
        }

        public ApkSigner build() {
            boolean z = this.mV3SigningExplicitlyDisabled;
            if (z && this.mV3SigningExplicitlyEnabled) {
                k2d.a("Builder configured to both enable and disable APK Signature Scheme v3 signing");
                return null;
            }
            if (z) {
                this.mV3SigningEnabled = false;
            }
            if (this.mV3SigningExplicitlyEnabled) {
                this.mV3SigningEnabled = true;
            }
            if (this.mV4SigningEnabled && !this.mV2SigningEnabled && !this.mV3SigningEnabled) {
                if (this.mV4ErrorReportingEnabled) {
                    k2d.a("APK Signature Scheme v4 signing requires at least v2 or v3 signing to be enabled");
                    return null;
                }
                this.mV4SigningEnabled = false;
            }
            return new ApkSigner(this.mSignerConfigs, this.mSourceStampSignerConfig, this.mSourceStampSigningCertificateLineage, this.mForceSourceStampOverwrite, this.mSourceStampTimestampEnabled, this.mMinSdkVersion, this.mRotationMinSdkVersion, this.mRotationTargetsDevRelease, this.mV1SigningEnabled, this.mV2SigningEnabled, this.mV3SigningEnabled, this.mV4SigningEnabled, this.mAlignFileSize, this.mVerityEnabled, this.mV4ErrorReportingEnabled, this.mDebuggableApkPermitted, this.mOtherSignersSignaturesPreserved, this.mAlignmentPreserved, this.mLibraryPageAlignmentBytes, this.mCreatedBy, this.mSignerEngine, this.mInputApkFile, this.mInputApkDataSource, this.mOutputApkFile, this.mOutputApkDataSink, this.mOutputApkDataSource, this.mOutputV4File, this.mSigningCertificateLineage, null);
        }

        public Builder setAlignFileSize(boolean z) {
            checkInitializedWithoutEngine();
            this.mAlignFileSize = z;
            return this;
        }

        public Builder setAlignmentPreserved(boolean z) {
            this.mAlignmentPreserved = z;
            return this;
        }

        public Builder setCreatedBy(String str) {
            checkInitializedWithoutEngine();
            str.getClass();
            this.mCreatedBy = str;
            return this;
        }

        public Builder setDebuggableApkPermitted(boolean z) {
            checkInitializedWithoutEngine();
            this.mDebuggableApkPermitted = z;
            return this;
        }

        public Builder setForceSourceStampOverwrite(boolean z) {
            this.mForceSourceStampOverwrite = z;
            return this;
        }

        public Builder setInputApk(File file) {
            if (file == null) {
                x0e.a("inputApk == null");
                return null;
            }
            this.mInputApkFile = file;
            this.mInputApkDataSource = null;
            return this;
        }

        public Builder setLibraryPageAlignmentBytes(int i) {
            this.mLibraryPageAlignmentBytes = i;
            return this;
        }

        public Builder setMinSdkVersion(int i) {
            checkInitializedWithoutEngine();
            this.mMinSdkVersion = Integer.valueOf(i);
            return this;
        }

        public Builder setMinSdkVersionForRotation(int i) {
            checkInitializedWithoutEngine();
            if (i < 33) {
                this.mRotationMinSdkVersion = 28;
                return this;
            }
            this.mRotationMinSdkVersion = i;
            return this;
        }

        public Builder setOtherSignersSignaturesPreserved(boolean z) {
            checkInitializedWithoutEngine();
            this.mOtherSignersSignaturesPreserved = z;
            return this;
        }

        public Builder setOutputApk(DataSink dataSink, DataSource dataSource) {
            if (dataSink == null) {
                x0e.a("outputApkOut == null");
                return null;
            }
            if (dataSource == null) {
                x0e.a("outputApkIn == null");
                return null;
            }
            this.mOutputApkFile = null;
            this.mOutputApkDataSink = dataSink;
            this.mOutputApkDataSource = dataSource;
            return this;
        }

        public Builder setRotationTargetsDevRelease(boolean z) {
            checkInitializedWithoutEngine();
            this.mRotationTargetsDevRelease = z;
            return this;
        }

        public Builder setSigningCertificateLineage(SigningCertificateLineage signingCertificateLineage) {
            if (signingCertificateLineage != null) {
                this.mV3SigningEnabled = true;
                this.mSigningCertificateLineage = signingCertificateLineage;
            }
            return this;
        }

        public Builder setSourceStampSignerConfig(SignerConfig signerConfig) {
            this.mSourceStampSignerConfig = signerConfig;
            return this;
        }

        public Builder setSourceStampSigningCertificateLineage(SigningCertificateLineage signingCertificateLineage) {
            this.mSourceStampSigningCertificateLineage = signingCertificateLineage;
            return this;
        }

        public Builder setSourceStampTimestampEnabled(boolean z) {
            this.mSourceStampTimestampEnabled = z;
            return this;
        }

        public Builder setV1SigningEnabled(boolean z) {
            checkInitializedWithoutEngine();
            this.mV1SigningEnabled = z;
            return this;
        }

        public Builder setV2SigningEnabled(boolean z) {
            checkInitializedWithoutEngine();
            this.mV2SigningEnabled = z;
            return this;
        }

        public Builder setV3SigningEnabled(boolean z) {
            checkInitializedWithoutEngine();
            this.mV3SigningEnabled = z;
            if (z) {
                this.mV3SigningExplicitlyEnabled = true;
                return this;
            }
            this.mV3SigningExplicitlyDisabled = true;
            return this;
        }

        public Builder setV4ErrorReportingEnabled(boolean z) {
            checkInitializedWithoutEngine();
            this.mV4ErrorReportingEnabled = z;
            return this;
        }

        public Builder setV4SignatureOutputFile(File file) {
            if (file != null) {
                this.mOutputV4File = file;
                return this;
            }
            x0e.a("v4HashRootOutputFile == null");
            return null;
        }

        public Builder setV4SigningEnabled(boolean z) {
            checkInitializedWithoutEngine();
            this.mV4SigningEnabled = z;
            this.mV4ErrorReportingEnabled = z;
            return this;
        }

        public Builder setVerityEnabled(boolean z) {
            checkInitializedWithoutEngine();
            this.mVerityEnabled = z;
            return this;
        }

        public Builder setInputApk(DataSource dataSource) {
            if (dataSource != null) {
                this.mInputApkDataSource = dataSource;
                this.mInputApkFile = null;
                return this;
            }
            x0e.a("inputApk == null");
            return null;
        }

        public Builder setOutputApk(ReadableDataSink readableDataSink) {
            if (readableDataSink != null) {
                return setOutputApk(readableDataSink, readableDataSink);
            }
            x0e.a("outputApk == null");
            return null;
        }

        public Builder setOutputApk(File file) {
            if (file != null) {
                this.mOutputApkFile = file;
                this.mOutputApkDataSink = null;
                this.mOutputApkDataSource = null;
                return this;
            }
            x0e.a("outputApk == null");
            return null;
        }

        public Builder(ApkSignerEngine apkSignerEngine) {
            this.mForceSourceStampOverwrite = false;
            this.mSourceStampTimestampEnabled = true;
            this.mV1SigningEnabled = true;
            this.mV2SigningEnabled = true;
            this.mV3SigningEnabled = true;
            this.mV4SigningEnabled = false;
            this.mAlignFileSize = false;
            this.mVerityEnabled = false;
            this.mV4ErrorReportingEnabled = false;
            this.mDebuggableApkPermitted = true;
            this.mAlignmentPreserved = false;
            this.mLibraryPageAlignmentBytes = 16384;
            this.mRotationMinSdkVersion = 33;
            this.mRotationTargetsDevRelease = false;
            this.mV3SigningExplicitlyDisabled = false;
            this.mV3SigningExplicitlyEnabled = false;
            if (apkSignerEngine != null) {
                this.mSignerEngine = apkSignerEngine;
                this.mSignerConfigs = null;
            } else {
                x0e.a("signerEngine == null");
                throw null;
            }
        }
    }

    public static class SignerConfig {
        private final List<X509Certificate> mCertificates;
        private final boolean mDeterministicDsaSigning;
        private final KeyConfig mKeyConfig;
        private final int mMinSdkVersion;
        private final String mName;
        private final SigningCertificateLineage mSigningCertificateLineage;

        private SignerConfig(Builder builder) {
            this.mName = builder.mName;
            this.mKeyConfig = builder.mKeyConfig;
            this.mCertificates = Collections.unmodifiableList(new ArrayList(builder.mCertificates));
            this.mDeterministicDsaSigning = builder.mDeterministicDsaSigning;
            this.mMinSdkVersion = builder.mMinSdkVersion;
            this.mSigningCertificateLineage = builder.mSigningCertificateLineage;
        }

        public static /* synthetic */ PrivateKey b(KeyConfig.Kms kms) {
            return null;
        }

        public List<X509Certificate> getCertificates() {
            return this.mCertificates;
        }

        public boolean getDeterministicDsaSigning() {
            return this.mDeterministicDsaSigning;
        }

        public KeyConfig getKeyConfig() {
            return this.mKeyConfig;
        }

        public int getMinSdkVersion() {
            return this.mMinSdkVersion;
        }

        public String getName() {
            return this.mName;
        }

        @Deprecated
        public PrivateKey getPrivateKey() {
            return (PrivateKey) this.mKeyConfig.match(new Function() { // from class: jb0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((KeyConfig.Jca) obj).privateKey;
                }
            }, new Function() { // from class: kb0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ApkSigner.SignerConfig.b((KeyConfig.Kms) obj);
                }
            });
        }

        public SigningCertificateLineage getSigningCertificateLineage() {
            return this.mSigningCertificateLineage;
        }

        public static class Builder {
            private final List<X509Certificate> mCertificates;
            private final boolean mDeterministicDsaSigning;
            private final KeyConfig mKeyConfig;
            private int mMinSdkVersion;
            private final String mName;
            private SigningCertificateLineage mSigningCertificateLineage;

            @Deprecated
            public Builder(String str, PrivateKey privateKey, List<X509Certificate> list, boolean z) {
                if (str.isEmpty()) {
                    w01.a("Empty name");
                    throw null;
                }
                this.mName = str;
                this.mKeyConfig = new KeyConfig.Jca(privateKey);
                this.mCertificates = new ArrayList(list);
                this.mDeterministicDsaSigning = z;
            }

            public SignerConfig build() {
                return new SignerConfig(this, null);
            }

            public Builder setLineageForMinSdkVersion(SigningCertificateLineage signingCertificateLineage, int i) {
                if (i < 28) {
                    w01.a("SDK targeted signing config is only supported with the V3 signature scheme on Android P (SDK version 28) and later");
                    return null;
                }
                if (i < 33) {
                    i = 28;
                }
                this.mMinSdkVersion = i;
                if (signingCertificateLineage == null || signingCertificateLineage.isCertificateInLineage(this.mCertificates.get(0))) {
                    this.mSigningCertificateLineage = signingCertificateLineage;
                    return this;
                }
                yba.a("The provided lineage does not contain the signing certificate, ", this.mCertificates.get(0).getSubjectDN(), ", for this SignerConfig");
                return null;
            }

            public Builder setMinSdkVersion(int i) {
                return setLineageForMinSdkVersion(null, i);
            }

            @Deprecated
            public Builder(String str, PrivateKey privateKey, List<X509Certificate> list) {
                this(str, privateKey, list, false);
            }

            public Builder(String str, KeyConfig keyConfig, List<X509Certificate> list) {
                this(str, keyConfig, list, false);
            }

            public Builder(String str, KeyConfig keyConfig, List<X509Certificate> list, boolean z) {
                if (!str.isEmpty()) {
                    this.mName = str;
                    this.mKeyConfig = keyConfig;
                    this.mCertificates = new ArrayList(list);
                    this.mDeterministicDsaSigning = z;
                    return;
                }
                w01.a("Empty name");
                throw null;
            }
        }

        public /* synthetic */ SignerConfig(Builder builder, AnonymousClass1 anonymousClass1) {
            this(builder);
        }
    }

    public /* synthetic */ ApkSigner(List list, SignerConfig signerConfig, SigningCertificateLineage signingCertificateLineage, boolean z, boolean z2, Integer num, int i, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, int i2, String str, ApkSignerEngine apkSignerEngine, File file, DataSource dataSource, File file2, DataSink dataSink, DataSource dataSource2, File file3, SigningCertificateLineage signingCertificateLineage2, AnonymousClass1 anonymousClass1) {
        this(list, signerConfig, signingCertificateLineage, z, z2, num, i, z3, z4, z5, z6, z7, z8, z9, z10, z11, z12, z13, i2, str, apkSignerEngine, file, dataSource, file2, dataSink, dataSource2, file3, signingCertificateLineage2);
    }

    public void sign() throws Throwable {
        RandomAccessFile randomAccessFile;
        DataSource dataSourceAsDataSource;
        DataSource dataSourceAsDataSource2;
        RandomAccessFile randomAccessFile2 = null;
        try {
            DataSource dataSource = this.mInputApkDataSource;
            if (dataSource != null) {
                dataSourceAsDataSource = dataSource;
                randomAccessFile = null;
            } else if (this.mInputApkFile != null) {
                randomAccessFile = new RandomAccessFile(this.mInputApkFile, "r");
                try {
                    dataSourceAsDataSource = DataSources.asDataSource(randomAccessFile);
                } catch (Throwable th) {
                    th = th;
                    randomAccessFile2 = randomAccessFile;
                    if (randomAccessFile2 != null) {
                        randomAccessFile2.close();
                    }
                    throw th;
                }
            } else {
                throw new IllegalStateException("Input APK not specified");
            }
            try {
                DataSink dataSink = this.mOutputApkDataSink;
                if (dataSink != null) {
                    dataSourceAsDataSource2 = this.mOutputApkDataSource;
                } else if (this.mOutputApkFile != null) {
                    RandomAccessFile randomAccessFile3 = new RandomAccessFile(this.mOutputApkFile, "rw");
                    try {
                        randomAccessFile3.setLength(0L);
                        DataSink dataSinkAsDataSink = DataSinks.asDataSink(randomAccessFile3);
                        dataSourceAsDataSource2 = DataSources.asDataSource(randomAccessFile3);
                        dataSink = dataSinkAsDataSink;
                        randomAccessFile2 = randomAccessFile3;
                    } catch (Throwable th2) {
                        th = th2;
                        randomAccessFile2 = randomAccessFile3;
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        }
                        throw th;
                    }
                } else {
                    throw new IllegalStateException("Output APK not specified");
                }
                sign(dataSourceAsDataSource, dataSink, dataSourceAsDataSource2);
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
        }
    }
}
