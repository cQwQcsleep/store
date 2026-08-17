package com.reandroid.apk;

import com.reandroid.app.AndroidManifest;
import com.reandroid.archive.InputSource;
import com.reandroid.arsc.chunk.Overlayable;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.xml.AndroidManifestBlock;
import com.reandroid.arsc.chunk.xml.ResXmlDocument;
import com.reandroid.arsc.coder.xml.XmlCoder;
import com.reandroid.arsc.list.OverlayableList;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.utils.io.IOUtil;
import com.reandroid.xml.XMLFactory;
import com.reandroid.xml.XmlIndentingSerializer;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkModuleXmlDecoder extends ApkModuleDecoder implements Predicate<Entry> {
    private final Map<Integer, Set<ResConfig>> decodedEntries;
    private boolean keepResPath;

    public ApkModuleXmlDecoder(ApkModule apkModule) {
        super(apkModule);
        this.decodedEntries = new HashMap();
    }

    private void addDecodedEntry(Entry entry) {
        if (entry.isNull()) {
            return;
        }
        int resourceId = entry.getResourceId();
        Set<ResConfig> hashSet = this.decodedEntries.get(Integer.valueOf(resourceId));
        if (hashSet == null) {
            hashSet = new HashSet<>();
            this.decodedEntries.put(Integer.valueOf(resourceId), hashSet);
        }
        hashSet.add(entry.getResConfig());
    }

    private boolean containsDecodedEntry(Entry entry) {
        Set<ResConfig> set = this.decodedEntries.get(Integer.valueOf(entry.getResourceId()));
        if (set == null) {
            return false;
        }
        return set.contains(entry.getResConfig());
    }

    private void decodeAndroidManifestBin(File file) throws IOException {
        File file2 = new File(file, AndroidManifest.FILE_NAME_BIN);
        logMessage("Decode manifest binary: " + file2.getName());
        ApkModule apkModule = getApkModule();
        InputSource manifestOriginalSource = apkModule.getManifestOriginalSource();
        if (manifestOriginalSource == null) {
            manifestOriginalSource = apkModule.getInputSource(AndroidManifest.FILE_NAME);
        }
        manifestOriginalSource.write(file2);
        addDecodedPath(AndroidManifest.FILE_NAME);
    }

    private void decodeAndroidManifestXml(File file) throws IOException {
        AndroidManifestBlock androidManifest = getApkModule().getAndroidManifest();
        String str = AndroidManifest.FILE_NAME;
        File file2 = new File(file, str);
        logMessage("Decoding: " + file2.getName());
        PackageBlock packageBlock = androidManifest.getPackageBlock();
        if (packageBlock == null) {
            int iGuessCurrentPackageId = androidManifest.guessCurrentPackageId();
            TableBlock tableBlock = getApkModule().getTableBlock();
            packageBlock = tableBlock.pickOne(iGuessCurrentPackageId);
            if (packageBlock == null) {
                packageBlock = tableBlock.pickOne();
            }
        }
        serializeXml(packageBlock, (ResXmlDocument) androidManifest, file2);
        addDecodedPath(str);
    }

    private void decodeEmptyAndroidManifestXml(File file) throws IOException {
        StringBuilder sb = new StringBuilder("WARN: Missing ");
        String str = AndroidManifest.FILE_NAME;
        sb.append(str);
        sb.append(", could be framework apk or you are decompiling wrong apk file");
        logMessage(sb.toString());
        XmlSerializer xmlSerializerNewSerializer = XMLFactory.newSerializer(new File(file, str));
        xmlSerializerNewSerializer.startDocument("utf-8", null);
        xmlSerializerNewSerializer.text("\n");
        String str2 = AndroidManifest.EMPTY_MANIFEST_TAG;
        xmlSerializerNewSerializer.startTag(null, str2);
        xmlSerializerNewSerializer.endTag(null, str2);
        xmlSerializerNewSerializer.endDocument();
        xmlSerializerNewSerializer.flush();
        IOUtil.close(xmlSerializerNewSerializer);
        addDecodedPath(str);
    }

    private void decodeEmptyTable(File file, TableBlock tableBlock) throws IOException {
        logMessage("Decoding empty table ...");
        File file2 = new File(new File(file, TableBlock.DIRECTORY_NAME), PackageBlock.DIRECTORY_NAME_PREFIX + "1");
        StringBuilder sb = new StringBuilder("Empty public.xml: ");
        sb.append(file2.getName());
        logMessage(sb.toString());
        tableBlock.pickOrEmptyPackage().serializePublicXml(new File(new File(new File(file2, PackageBlock.RES_DIRECTORY_NAME), PackageBlock.VALUES_DIRECTORY_NAME), PackageBlock.PUBLIC_XML));
    }

    private void decodeOverlayable(File file, PackageBlock packageBlock) throws IOException {
        OverlayableList overlayableList = packageBlock.getOverlayableList();
        if (overlayableList.isEmpty()) {
            return;
        }
        logMessage("Decode: overlayable");
        XmlIndentingSerializer xmlIndentingSerializer = new XmlIndentingSerializer(XMLFactory.newSerializer(new File(new File(new File(ApkModuleDecoder.toPackageDirectory(file, packageBlock), PackageBlock.RES_DIRECTORY_NAME), PackageBlock.VALUES_DIRECTORY_NAME), Overlayable.FILE_NAME_XML)));
        XMLFactory.setEnableIndentAttributes(xmlIndentingSerializer, false);
        overlayableList.serialize(xmlIndentingSerializer);
    }

    private void decodePackageInfo(File file, TableBlock tableBlock) throws IOException {
        Iterator it = tableBlock.listPackages().iterator();
        while (it.hasNext()) {
            decodePackageInfo(file, (PackageBlock) it.next());
        }
    }

    private void decodePublicXml(File file, PackageBlock packageBlock) throws IOException {
        File packageDirectory = ApkModuleDecoder.toPackageDirectory(file, packageBlock);
        logMessage("public.xml: " + packageBlock.getName() + " -> " + packageDirectory.getName());
        packageBlock.serializePublicXml(new File(new File(new File(packageDirectory, PackageBlock.RES_DIRECTORY_NAME), PackageBlock.VALUES_DIRECTORY_NAME), PackageBlock.PUBLIC_XML));
    }

    private void decodeResFile(File file, ResFile resFile) throws IOException {
        if (!resFile.isBinaryXml()) {
            String filePath = resFile.getFilePath();
            if (filePath.endsWith(".xml")) {
                logMessage("Ignore non bin xml: ".concat(filePath));
                return;
            } else {
                decodeResRaw(file, resFile);
                return;
            }
        }
        try {
            decodeResXml(file, resFile);
        } catch (Exception e) {
            logOrThrow("Failed to decode: " + resFile.getFilePath(), e);
        }
    }

    private void decodeResFiles(File file) throws IOException {
        if (keepResPath()) {
            logMessage("Res files: " + TableBlock.RES_FILES_DIRECTORY_NAME);
        } else {
            logMessage("Res files: " + TableBlock.DIRECTORY_NAME);
        }
        Iterator<ResFile> it = getApkModule().listResFiles().iterator();
        while (it.hasNext()) {
            decodeResFile(file, it.next());
        }
    }

    private void decodeResRaw(File file, ResFile resFile) throws IOException {
        Entry entryPickOne = resFile.pickOne();
        File decodeResFile = toDecodeResFile(file, resFile, entryPickOne.getPackageBlock());
        InputSource inputSource = resFile.getInputSource();
        logVerbose(inputSource.getAlias());
        inputSource.write(decodeResFile);
        if (!keepResPath()) {
            addDecodedEntry(entryPickOne);
        }
        addDecodedPath(inputSource.getAlias());
    }

    private void decodeResXml(File file, ResFile resFile) throws IOException {
        Entry entryPickOne = resFile.pickOne();
        PackageBlock packageBlock = entryPickOne.getPackageBlock();
        File decodeResFile = toDecodeResFile(file, resFile, packageBlock);
        InputSource inputSource = resFile.getInputSource();
        logVerbose(inputSource.getAlias());
        serializeXml(packageBlock, resFile.getInputSource(), decodeResFile);
        if (!keepResPath()) {
            addDecodedEntry(entryPickOne);
        }
        addDecodedPath(inputSource.getAlias());
    }

    private void decodeTableBlock(File file, TableBlock tableBlock) throws IOException {
        try {
            decodePackageInfo(file, tableBlock);
            decodePublicXml(file, tableBlock);
            addDecodedPath(TableBlock.FILE_NAME);
        } catch (IOException e) {
            logOrThrow("Error decoding resource table", e);
        }
    }

    private void decodeValues(File file, TableBlock tableBlock) throws IOException {
        XmlCoder.getInstance().VALUES_XML.decodeTable(new File(file, TableBlock.DIRECTORY_NAME), tableBlock, this);
    }

    private void serializeXml(PackageBlock packageBlock, ResXmlDocument resXmlDocument, File file) throws IOException {
        if (packageBlock != null && resXmlDocument.getPackageBlock() == null) {
            resXmlDocument.setPackageBlock(packageBlock);
        }
        XmlSerializer xmlSerializerNewSerializer = XMLFactory.newSerializer(file, resXmlDocument.getEncoding());
        resXmlDocument.serialize(xmlSerializerNewSerializer);
        IOUtil.close(xmlSerializerNewSerializer);
    }

    private File toDecodeResFile(File file, ResFile resFile, PackageBlock packageBlock) {
        String strBuildPath;
        File file2;
        if (keepResPath()) {
            strBuildPath = resFile.getInputSource().getAlias();
            file2 = new File(file, TableBlock.RES_FILES_DIRECTORY_NAME);
        } else {
            strBuildPath = resFile.buildPath(PackageBlock.RES_DIRECTORY_NAME);
            File packageDirectory = ApkModuleDecoder.toPackageDirectory(file, packageBlock);
            resFile.setFilePath(strBuildPath);
            file2 = packageDirectory;
        }
        return new File(file2, strBuildPath.replace('/', File.separatorChar));
    }

    @Override // com.reandroid.apk.ApkModuleDecoder
    public void decodeAndroidManifest(File file) throws IOException {
        String str = AndroidManifest.FILE_NAME;
        if (containsDecodedPath(str)) {
            return;
        }
        if (!getApkModule().hasAndroidManifest()) {
            decodeEmptyAndroidManifestXml(file);
        } else if (isExcluded(str)) {
            decodeAndroidManifestBin(file);
        } else {
            decodeAndroidManifestXml(file);
        }
    }

    @Override // com.reandroid.apk.ApkModuleDecoder
    public void decodeResourceTable(File file) throws IOException {
        TableBlock tableBlock = getApkModule().getTableBlock();
        decodeTableBlock(file, tableBlock);
        decodeResFiles(file);
        decodeValues(file, tableBlock);
        decodeOverlayable(file, tableBlock);
    }

    @Override // com.reandroid.apk.ApkModuleDecoder
    public void initialize() {
        super.initialize();
        validateResourceNames();
    }

    public boolean keepResPath() {
        return this.keepResPath;
    }

    public void setKeepResPath(boolean z) {
        this.keepResPath = z;
    }

    @Override // java.util.function.Predicate
    public boolean test(Entry entry) {
        return containsDecodedEntry(entry);
    }

    private void decodePackageInfo(File file, PackageBlock packageBlock) throws IOException {
        packageBlock.toJson(false).write(new File(ApkModuleDecoder.toPackageDirectory(file, packageBlock), PackageBlock.JSON_FILE_NAME));
    }

    private void serializeXml(PackageBlock packageBlock, InputSource inputSource, File file) throws IOException {
        ResXmlDocument resXmlDocument = new ResXmlDocument();
        resXmlDocument.readBytes(inputSource.openStream());
        resXmlDocument.setPackageBlock(packageBlock);
        serializeXml(packageBlock, resXmlDocument, file);
    }

    private void decodeOverlayable(File file, TableBlock tableBlock) throws IOException {
        Iterator it = tableBlock.iterator();
        while (it.hasNext()) {
            decodeOverlayable(file, (PackageBlock) it.next());
        }
    }

    private void decodePublicXml(File file, TableBlock tableBlock) throws IOException {
        Iterator it = tableBlock.listPackages().iterator();
        while (it.hasNext()) {
            decodePublicXml(file, (PackageBlock) it.next());
        }
        if (tableBlock.size() == 0) {
            decodeEmptyTable(file, tableBlock);
        }
    }
}
