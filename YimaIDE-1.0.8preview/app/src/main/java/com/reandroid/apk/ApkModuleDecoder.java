package com.reandroid.apk;

import com.reandroid.archive.ArchiveInfo;
import com.reandroid.archive.InputSource;
import com.reandroid.archive.block.ApkSignatureBlock;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.dex.model.DexDirectory;
import com.reandroid.dex.sections.Marker;
import com.reandroid.identifiers.PackageIdentifier;
import com.reandroid.identifiers.TableIdentifier;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class ApkModuleDecoder extends ApkModuleCoder {
    private final ApkModule apkModule;
    private DecodeFilter mDecodeFilter;
    private final Set<String> mDecodedPaths = new HashSet();
    private DexDecoder mDexDecoder;
    private DexProfileDecoder mDexProfileDecoder;
    private boolean mLogErrors;

    public ApkModuleDecoder(ApkModule apkModule) {
        this.apkModule = apkModule;
        setApkLogger(apkModule.getApkLogger());
    }

    private void ensureTableBlock() {
        if (getApkModule().ensureTableBlock()) {
            logMessage("Missing " + TableBlock.FILE_NAME + ", created empty");
        }
    }

    private void extractRootFile(File file, InputSource inputSource) throws IOException {
        inputSource.write(inputSource.toFile(file));
    }

    public static File toPackageDirectory(File file, PackageBlock packageBlock) {
        return new File(new File(file, TableBlock.DIRECTORY_NAME), packageBlock.buildDecodeDirectoryName());
    }

    public void addDecodedPath(String str) {
        this.mDecodedPaths.add(str);
    }

    public boolean containsDecodedPath(String str) {
        return this.mDecodedPaths.contains(str);
    }

    public final void decode(File file) throws IOException {
        initialize();
        decodeArchiveInfo(file);
        decodeUncompressedFiles(file);
        decodeAndroidManifest(file);
        decodeResourceTable(file);
        decodeDexFiles(file);
        extractRootFiles(file);
        decodeDexProfile(file);
        decodePathMap(file);
        dumpSignatures(file);
    }

    public abstract void decodeAndroidManifest(File file) throws IOException;

    public void decodeArchiveInfo(File file) throws IOException {
        logMessage("Decode: " + new File(file, ArchiveInfo.JSON_FILE).getName());
        this.apkModule.getZipEntryMap().getOrCreateArchiveInfo().writeToDirectory(file);
    }

    public void decodeDexFiles(File file) throws IOException {
        ApkModule apkModule = getApkModule();
        List<DexFileInputSource> listListDexFiles = apkModule.listDexFiles();
        getDexDecoder().decodeDex(apkModule, file);
        Iterator<DexFileInputSource> it = listListDexFiles.iterator();
        while (it.hasNext()) {
            addDecodedPath(it.next().getAlias());
        }
    }

    public void decodeDexInfo(File file) throws IOException {
        File file2 = new File(file, "dex-info.json");
        logMessage("Decode: " + file2.getName());
        DexDirectory strings = DexDirectory.readStrings(this.apkModule.getZipEntryMap());
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        Iterator markers = strings.getMarkers();
        while (markers.hasNext()) {
            jSONArray.put(((Marker) markers.next()).getJsonObject());
        }
        jSONObject.put("markers", jSONArray);
        jSONObject.write(file2);
    }

    public void decodeDexProfile(File file) throws IOException {
        DexProfileDecoder dexProfileDecoder = getDexProfileDecoder();
        if (dexProfileDecoder != null) {
            dexProfileDecoder.decodeDexProfile(this.apkModule, file);
        }
    }

    public void decodePathMap(File file) throws IOException {
        File file2 = new File(file, PathMap.JSON_FILE);
        PathMap pathMap = new PathMap();
        pathMap.add(getApkModule().getZipEntryMap());
        pathMap.m6458toJson().write(file2);
    }

    public abstract void decodeResourceTable(File file) throws IOException;

    public void decodeUncompressedFiles(File file) throws IOException {
        File file2 = new File(file, UncompressedFiles.JSON_FILE);
        logMessage("Decode: " + file2.getName());
        UncompressedFiles uncompressedFiles = new UncompressedFiles();
        uncompressedFiles.addCommonExtensions();
        uncompressedFiles.addPath(getApkModule().getZipEntryMap());
        uncompressedFiles.toJson().write(file2);
    }

    public void dumpSignatures(File file) throws IOException {
        ApkSignatureBlock apkSignatureBlock = getApkModule().getApkSignatureBlock();
        if (apkSignatureBlock == null) {
            return;
        }
        File file2 = new File(file, "signatures");
        logMessage("Dumping signatures ...");
        apkSignatureBlock.writeSplitRawToDirectory(file2);
    }

    public void extractRootFiles(File file) throws IOException {
        logMessage("Extracting root files ...");
        File file2 = new File(file, "root");
        for (InputSource inputSource : this.apkModule.getInputSources()) {
            if (!containsDecodedPath(inputSource.getAlias())) {
                extractRootFile(file2, inputSource);
                addDecodedPath(inputSource.getAlias());
            }
        }
    }

    @Override // com.reandroid.apk.ApkModuleCoder
    public ApkModule getApkModule() {
        return this.apkModule;
    }

    public DecodeFilter getDecodeFilter() {
        if (this.mDecodeFilter == null) {
            this.mDecodeFilter = new DecodeFilter();
        }
        return this.mDecodeFilter;
    }

    public DexDecoder getDexDecoder() {
        if (this.mDexDecoder == null) {
            DexFileRawDecoder dexFileRawDecoder = new DexFileRawDecoder();
            dexFileRawDecoder.setApkLogger(getApkLogger());
            this.mDexDecoder = dexFileRawDecoder;
        }
        return this.mDexDecoder;
    }

    public DexProfileDecoder getDexProfileDecoder() {
        return this.mDexProfileDecoder;
    }

    public void initialize() {
        this.mDecodedPaths.clear();
        ensureTableBlock();
    }

    public boolean isExcluded(String str) {
        return getDecodeFilter().isExcluded(str);
    }

    public boolean isLogErrors() {
        return this.mLogErrors;
    }

    public void logOrThrow(String str, Throwable th) throws IOException {
        if (isLogErrors()) {
            logError(str, th);
            return;
        }
        if (str == null && th == null) {
            return;
        }
        if (th == null) {
            th = new IOException(str);
        }
        if (!(th instanceof IOException)) {
            throw new IOException(th);
        }
        throw ((IOException) th);
    }

    public void sanitizeFilePaths() {
        PathSanitizer.create(getApkModule()).sanitize();
    }

    public void setDecodeFilter(DecodeFilter decodeFilter) {
        this.mDecodeFilter = decodeFilter;
    }

    public void setDexDecoder(DexDecoder dexDecoder) {
        this.mDexDecoder = dexDecoder;
    }

    public void setDexProfileDecoder(DexProfileDecoder dexProfileDecoder) {
        this.mDexProfileDecoder = dexProfileDecoder;
    }

    public void setLogErrors(boolean z) {
        this.mLogErrors = z;
    }

    public void validateResourceNames(PackageBlock packageBlock) {
        logMessage("Validating: " + packageBlock.getName());
        PackageIdentifier packageIdentifier = new PackageIdentifier();
        packageIdentifier.load(packageBlock);
        String strValidateSpecNames = packageIdentifier.validateSpecNames();
        if (strValidateSpecNames == null) {
            logMessage("[" + packageBlock.getName() + "] All resource names are valid");
            return;
        }
        if (packageBlock.removeUnusedSpecs()) {
            logMessage("[" + packageBlock.getName() + "]" + strValidateSpecNames + ", removed specs");
        }
    }

    public void validateResourceNames() {
        logMessage("Validating resource names ...");
        TableBlock tableBlock = this.apkModule.getTableBlock();
        TableIdentifier tableIdentifier = new TableIdentifier();
        tableIdentifier.load(tableBlock);
        String strValidateSpecNames = tableIdentifier.validateSpecNames();
        if (strValidateSpecNames == null) {
            logMessage("All resource names are valid");
            return;
        }
        if (tableBlock.removeUnusedSpecs()) {
            strValidateSpecNames = strValidateSpecNames.concat(", removed specs");
        }
        logMessage(strValidateSpecNames);
    }
}
