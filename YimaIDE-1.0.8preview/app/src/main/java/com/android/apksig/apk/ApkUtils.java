package com.android.apksig.apk;

import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v1.V1SchemeVerifier;
import com.android.apksig.internal.util.Pair;
import com.android.apksig.internal.zip.CentralDirectoryRecord;
import com.android.apksig.internal.zip.LocalFileRecord;
import com.android.apksig.internal.zip.ZipUtils;
import com.android.apksig.util.DataSource;
import com.android.apksig.zip.ZipFormatException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class ApkUtils {
    public static final String ANDROID_MANIFEST_ZIP_ENTRY_NAME = "AndroidManifest.xml";
    private static final int DEBUGGABLE_ATTR_ID = 16842767;
    private static final String MANIFEST_ELEMENT_TAG = "manifest";
    private static final int MIN_SDK_VERSION_ATTR_ID = 16843276;
    public static final String SOURCE_STAMP_CERTIFICATE_HASH_ZIP_ENTRY_NAME = "stamp-cert-sha256";
    private static final int TARGET_SANDBOX_VERSION_ATTR_ID = 16844108;
    private static final int TARGET_SDK_VERSION_ATTR_ID = 16843376;
    private static final String USES_SDK_ELEMENT_TAG = "uses-sdk";
    private static final int VERSION_CODE_ATTR_ID = 16843291;
    private static final int VERSION_CODE_MAJOR_ATTR_ID = 16844150;

    public static class ApkSigningBlock extends ApkUtilsLite.ApkSigningBlock {
        public ApkSigningBlock(long j, DataSource dataSource) {
            super(j, dataSource);
        }
    }

    public static class CodenamesLazyInitializer {
        private static final Pair<Character, Integer>[] SORTED_CODENAMES_FIRST_CHAR_TO_API_LEVEL = {Pair.of('C', 2), Pair.of('D', 3), Pair.of('E', 4), Pair.of('F', 7), Pair.of('G', 8), Pair.of('H', 10), Pair.of('I', 13), Pair.of('J', 15), Pair.of('K', 18), Pair.of('L', 20), Pair.of('M', 22), Pair.of('N', 23), Pair.of('O', 25)};
        private static final Comparator<Pair<Character, Integer>> CODENAME_FIRST_CHAR_COMPARATOR = new ByFirstComparator();

        public static class ByFirstComparator implements Comparator<Pair<Character, Integer>> {
            private ByFirstComparator() {
            }

            @Override // java.util.Comparator
            public int compare(Pair<Character, Integer> pair, Pair<Character, Integer> pair2) {
                return pair.getFirst().charValue() - pair2.getFirst().charValue();
            }
        }

        private CodenamesLazyInitializer() {
        }
    }

    public static class ZipSections extends com.android.apksig.zip.ZipSections {
        public ZipSections(long j, long j2, int i, long j3, ByteBuffer byteBuffer) {
            super(j, j2, i, j3, byteBuffer);
        }
    }

    private ApkUtils() {
    }

    public static byte[] computeSha256DigestBytes(byte[] bArr) {
        return ApkUtilsLite.computeSha256DigestBytes(bArr);
    }

    public static ApkSigningBlock findApkSigningBlock(DataSource dataSource) throws ApkSigningBlockNotFoundException, IOException, ApkFormatException {
        try {
            return findApkSigningBlock(dataSource, findZipSections(dataSource));
        } catch (ZipFormatException e) {
            throw new ApkFormatException("Malformed APK: not a ZIP archive", e);
        }
    }

    public static ZipSections findZipSections(DataSource dataSource) throws IOException, ZipFormatException {
        com.android.apksig.zip.ZipSections zipSectionsFindZipSections = ApkUtilsLite.findZipSections(dataSource);
        return new ZipSections(zipSectionsFindZipSections.getZipCentralDirectoryOffset(), zipSectionsFindZipSections.getZipCentralDirectorySizeBytes(), zipSectionsFindZipSections.getZipCentralDirectoryRecordCount(), zipSectionsFindZipSections.getZipEndOfCentralDirectoryOffset(), zipSectionsFindZipSections.getZipEndOfCentralDirectory());
    }

    public static ByteBuffer getAndroidManifest(DataSource dataSource) throws IOException, ApkFormatException {
        CentralDirectoryRecord next;
        try {
            ZipSections zipSectionsFindZipSections = findZipSections(dataSource);
            Iterator<CentralDirectoryRecord> it = V1SchemeVerifier.parseZipCentralDirectory(dataSource, zipSectionsFindZipSections).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!ANDROID_MANIFEST_ZIP_ENTRY_NAME.equals(next.getName()));
            if (next == null) {
                throw new ApkFormatException("Missing AndroidManifest.xml");
            }
            DataSource dataSourceSlice = dataSource.slice(0L, zipSectionsFindZipSections.getZipCentralDirectoryOffset());
            try {
                return ByteBuffer.wrap(LocalFileRecord.getUncompressedData(dataSourceSlice, next, dataSourceSlice.size()));
            } catch (ZipFormatException e) {
                throw new ApkFormatException("Failed to read AndroidManifest.xml", e);
            }
        } catch (ZipFormatException e2) {
            throw new ApkFormatException("Not a valid ZIP archive", e2);
        }
    }

    private static int getAttributeValueFromBinaryAndroidManifest(ByteBuffer byteBuffer, String str, int i) throws ApkFormatException {
        if (str == null) {
            x0e.a("elementName cannot be null");
            return 0;
        }
        try {
            AndroidBinXmlParser androidBinXmlParser = new AndroidBinXmlParser(byteBuffer);
            for (int eventType = androidBinXmlParser.getEventType(); eventType != 2; eventType = androidBinXmlParser.next()) {
                if (eventType == 3 && str.equals(androidBinXmlParser.getName())) {
                    for (int i2 = 0; i2 < androidBinXmlParser.getAttributeCount(); i2++) {
                        if (androidBinXmlParser.getAttributeNameResourceId(i2) == i) {
                            int attributeValueType = androidBinXmlParser.getAttributeValueType(i2);
                            if (attributeValueType != 1 && attributeValueType != 2) {
                                throw new ApkFormatException("Unsupported value type, " + attributeValueType + ", for attribute " + String.format("0x%08X", Integer.valueOf(i)) + " under element " + str);
                            }
                            return androidBinXmlParser.getAttributeIntValue(i2);
                        }
                    }
                }
            }
            throw new ApkFormatException("Failed to determine APK's " + str + " attribute " + String.format("0x%08X", Integer.valueOf(i)) + " value");
        } catch (AndroidBinXmlParser.XmlParserException e) {
            throw new ApkFormatException("Unable to determine value for attribute " + String.format("0x%08X", Integer.valueOf(i)) + " under element " + str + "; malformed binary resource: AndroidManifest.xml", e);
        }
    }

    public static boolean getDebuggableFromBinaryAndroidManifest(ByteBuffer byteBuffer) throws ApkFormatException {
        try {
            AndroidBinXmlParser androidBinXmlParser = new AndroidBinXmlParser(byteBuffer);
            for (int eventType = androidBinXmlParser.getEventType(); eventType != 2; eventType = androidBinXmlParser.next()) {
                if (eventType == 3 && androidBinXmlParser.getDepth() == 2 && "application".equals(androidBinXmlParser.getName()) && androidBinXmlParser.getNamespace().isEmpty()) {
                    for (int i = 0; i < androidBinXmlParser.getAttributeCount(); i++) {
                        if (androidBinXmlParser.getAttributeNameResourceId(i) == 16842767) {
                            int attributeValueType = androidBinXmlParser.getAttributeValueType(i);
                            if (attributeValueType != 1 && attributeValueType != 2) {
                                if (attributeValueType == 3) {
                                    throw new ApkFormatException("Unable to determine whether APK is debuggable: AndroidManifest.xml's android:debuggable attribute references a resource. References are not supported for security reasons. Only constant boolean, string and int values are supported.");
                                }
                                if (attributeValueType != 4) {
                                    throw new ApkFormatException("Unable to determine whether APK is debuggable: AndroidManifest.xml's android:debuggable attribute uses unsupported value type. Only boolean, string and int values are supported.");
                                }
                            }
                            String attributeStringValue = androidBinXmlParser.getAttributeStringValue(i);
                            return "true".equals(attributeStringValue) || "TRUE".equals(attributeStringValue) || "1".equals(attributeStringValue);
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (AndroidBinXmlParser.XmlParserException e) {
            throw new ApkFormatException("Unable to determine whether APK is debuggable: malformed binary resource: AndroidManifest.xml", e);
        }
    }

    public static long getLongVersionCodeFromBinaryAndroidManifest(ByteBuffer byteBuffer) throws ApkFormatException {
        long attributeValueFromBinaryAndroidManifest;
        int versionCodeFromBinaryAndroidManifest = getVersionCodeFromBinaryAndroidManifest(byteBuffer);
        try {
            byteBuffer.rewind();
            attributeValueFromBinaryAndroidManifest = getAttributeValueFromBinaryAndroidManifest(byteBuffer, MANIFEST_ELEMENT_TAG, 16844150);
        } catch (ApkFormatException unused) {
            attributeValueFromBinaryAndroidManifest = 0;
        }
        return (attributeValueFromBinaryAndroidManifest << 32) | ((long) versionCodeFromBinaryAndroidManifest);
    }

    public static int getMinSdkVersionForCodename(String str) throws CodenameMinSdkVersionException {
        char cCharAt = str.isEmpty() ? ' ' : str.charAt(0);
        if (cCharAt < 'A' || cCharAt > 'Z') {
            throw new CodenameMinSdkVersionException("Unable to determine APK's minimum supported Android platform version : Unsupported codename in AndroidManifest.xml's minSdkVersion: \"" + str + "\"", str);
        }
        Pair[] pairArr = CodenamesLazyInitializer.SORTED_CODENAMES_FIRST_CHAR_TO_API_LEVEL;
        int iBinarySearch = Arrays.binarySearch(pairArr, Pair.of(Character.valueOf(cCharAt), null), CodenamesLazyInitializer.CODENAME_FIRST_CHAR_COMPARATOR);
        if (iBinarySearch >= 0) {
            return ((Integer) pairArr[iBinarySearch].getSecond()).intValue();
        }
        if ((-1) - iBinarySearch == 0) {
            return 1;
        }
        Pair pair = pairArr[(-2) - iBinarySearch];
        return ((Integer) pair.getSecond()).intValue() + (cCharAt - ((Character) pair.getFirst()).charValue());
    }

    public static int getMinSdkVersionFromBinaryAndroidManifest(ByteBuffer byteBuffer) throws MinSdkVersionException {
        int minSdkVersionForCodename;
        try {
            AndroidBinXmlParser androidBinXmlParser = new AndroidBinXmlParser(byteBuffer);
            int iMax = 1;
            for (int eventType = androidBinXmlParser.getEventType(); eventType != 2; eventType = androidBinXmlParser.next()) {
                if (eventType == 3 && androidBinXmlParser.getDepth() == 2 && USES_SDK_ELEMENT_TAG.equals(androidBinXmlParser.getName()) && androidBinXmlParser.getNamespace().isEmpty()) {
                    int i = 0;
                    while (true) {
                        if (i >= androidBinXmlParser.getAttributeCount()) {
                            minSdkVersionForCodename = 1;
                            break;
                        }
                        if (androidBinXmlParser.getAttributeNameResourceId(i) == 16843276) {
                            int attributeValueType = androidBinXmlParser.getAttributeValueType(i);
                            if (attributeValueType == 1) {
                                minSdkVersionForCodename = getMinSdkVersionForCodename(androidBinXmlParser.getAttributeStringValue(i));
                                break;
                            }
                            if (attributeValueType != 2) {
                                throw new MinSdkVersionException("Unable to determine APK's minimum supported Android: unsupported value type in AndroidManifest.xml's minSdkVersion. Only integer values supported.");
                            }
                            minSdkVersionForCodename = androidBinXmlParser.getAttributeIntValue(i);
                            break;
                        }
                        i++;
                    }
                    iMax = Math.max(iMax, minSdkVersionForCodename);
                }
            }
            return iMax;
        } catch (AndroidBinXmlParser.XmlParserException e) {
            throw new MinSdkVersionException("Unable to determine APK's minimum supported Android platform version: malformed binary resource: AndroidManifest.xml", e);
        }
    }

    public static String getPackageNameFromBinaryAndroidManifest(ByteBuffer byteBuffer) throws ApkFormatException {
        try {
            AndroidBinXmlParser androidBinXmlParser = new AndroidBinXmlParser(byteBuffer);
            for (int eventType = androidBinXmlParser.getEventType(); eventType != 2; eventType = androidBinXmlParser.next()) {
                if (eventType == 3 && androidBinXmlParser.getDepth() == 1 && MANIFEST_ELEMENT_TAG.equals(androidBinXmlParser.getName()) && androidBinXmlParser.getNamespace().isEmpty()) {
                    for (int i = 0; i < androidBinXmlParser.getAttributeCount(); i++) {
                        if ("package".equals(androidBinXmlParser.getAttributeName(i)) && androidBinXmlParser.getNamespace().isEmpty()) {
                            return androidBinXmlParser.getAttributeStringValue(i);
                        }
                    }
                    return null;
                }
            }
            return null;
        } catch (AndroidBinXmlParser.XmlParserException e) {
            throw new ApkFormatException("Unable to determine APK package name: malformed binary resource: AndroidManifest.xml", e);
        }
    }

    public static int getTargetSandboxVersionFromBinaryAndroidManifest(ByteBuffer byteBuffer) {
        try {
            return getAttributeValueFromBinaryAndroidManifest(byteBuffer, MANIFEST_ELEMENT_TAG, 16844108);
        } catch (ApkFormatException unused) {
            return 1;
        }
    }

    public static int getTargetSdkVersionFromBinaryAndroidManifest(ByteBuffer byteBuffer) {
        try {
            return getAttributeValueFromBinaryAndroidManifest(byteBuffer, USES_SDK_ELEMENT_TAG, 16843376);
        } catch (ApkFormatException unused) {
            byteBuffer.rewind();
            try {
                return getMinSdkVersionFromBinaryAndroidManifest(byteBuffer);
            } catch (ApkFormatException unused2) {
                return 1;
            }
        }
    }

    public static int getVersionCodeFromBinaryAndroidManifest(ByteBuffer byteBuffer) throws ApkFormatException {
        return getAttributeValueFromBinaryAndroidManifest(byteBuffer, MANIFEST_ELEMENT_TAG, 16843291);
    }

    public static void setZipEocdCentralDirectoryOffset(ByteBuffer byteBuffer, long j) {
        ByteBuffer byteBufferSlice = byteBuffer.slice();
        byteBufferSlice.order(ByteOrder.LITTLE_ENDIAN);
        ZipUtils.setZipEocdCentralDirectoryOffset(byteBufferSlice, j);
    }

    public static void updateZipEocdCommentLen(ByteBuffer byteBuffer) {
        ByteBuffer byteBufferSlice = byteBuffer.slice();
        byteBufferSlice.order(ByteOrder.LITTLE_ENDIAN);
        ZipUtils.updateZipEocdCommentLen(byteBufferSlice);
    }

    public static ApkSigningBlock findApkSigningBlock(DataSource dataSource, ZipSections zipSections) throws ApkSigningBlockNotFoundException, IOException {
        ApkUtilsLite.ApkSigningBlock apkSigningBlockFindApkSigningBlock = ApkUtilsLite.findApkSigningBlock(dataSource, zipSections);
        return new ApkSigningBlock(apkSigningBlockFindApkSigningBlock.getStartOffset(), apkSigningBlockFindApkSigningBlock.getContents());
    }
}
