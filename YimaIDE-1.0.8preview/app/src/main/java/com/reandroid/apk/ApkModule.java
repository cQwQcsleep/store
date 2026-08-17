package com.reandroid.apk;

import com.reandroid.apk.ApkModule;
import com.reandroid.app.AndroidManifest;
import com.reandroid.archive.ArchiveBytes;
import com.reandroid.archive.ArchiveFile;
import com.reandroid.archive.BlockInputSource;
import com.reandroid.archive.FileInputSource;
import com.reandroid.archive.InputSource;
import com.reandroid.archive.WriteProgress;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.archive.block.ApkSignatureBlock;
import com.reandroid.archive.io.ArchiveFileEntrySource;
import com.reandroid.archive.writer.ApkByteWriter;
import com.reandroid.archive.writer.ApkFileWriter;
import com.reandroid.archive.writer.ApkStreamWriter;
import com.reandroid.archive.writer.ApkWriter;
import com.reandroid.archive.writer.DataDescriptorFactory;
import com.reandroid.arsc.ApkFile;
import com.reandroid.arsc.array.PackageArray;
import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.chunk.xml.AndroidManifestBlock;
import com.reandroid.arsc.chunk.xml.ResXmlDocument;
import com.reandroid.arsc.container.SpecTypePair;
import com.reandroid.arsc.item.TableString;
import com.reandroid.arsc.model.FrameworkTable;
import com.reandroid.arsc.pool.TableStringPool;
import com.reandroid.arsc.value.Entry;
import com.reandroid.arsc.value.ResConfig;
import com.reandroid.identifiers.PackageIdentifier;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.xml.XMLDocument;
import com.reandroid.xml.XMLElement;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkModule implements ApkFile, Closeable {
    private APKLogger apkLogger;
    private ApkSignatureBlock apkSignatureBlock;
    private int extractNativeLibs;
    private boolean loadDefaultFramework;
    private ApkFile.ApkType mApkType;
    private Closeable mCloseable;
    private boolean mDisableLoadFramework;
    private final List<TableBlock> mExternalFrameworks;
    private AndroidManifestBlock mManifestBlock;
    private InputSource mManifestOriginalSource;
    private TableBlock mTableBlock;
    private InputSource mTableOriginalSource;
    private final Map<Object, Object> mTagMaps;
    private final UncompressedFiles mUncompressedFiles;
    private String moduleName;
    private Integer preferredFramework;
    private final ZipEntryMap zipEntryMap;

    public ApkModule(String str, ZipEntryMap zipEntryMap) {
        this.loadDefaultFramework = true;
        this.mDisableLoadFramework = false;
        this.moduleName = str;
        this.zipEntryMap = zipEntryMap;
        UncompressedFiles uncompressedFiles = new UncompressedFiles();
        this.mUncompressedFiles = uncompressedFiles;
        uncompressedFiles.addPath(zipEntryMap);
        this.mExternalFrameworks = new ArrayCollection();
        zipEntryMap.setModuleName(str);
        this.mTagMaps = new HashMap();
    }

    public static /* synthetic */ boolean a(int i, ResConfig resConfig, Entry entry) {
        if (!entry.isScalar() || !TypeBlock.canHaveResourceFile(entry.getTypeName())) {
            return false;
        }
        if (i == 0 || i == entry.getResourceId()) {
            return resConfig == null || resConfig.equals(entry.getResConfig());
        }
        return false;
    }

    private void addInputSource(InputSource inputSource) {
        getZipEntryMap().add(inputSource);
    }

    private void applyDefaultApkWriterSetting(ApkWriter<?, ?> apkWriter) {
        apkWriter.setAPKLogger(getApkLogger());
        apkWriter.setApkSignatureBlock(getApkSignatureBlock());
        apkWriter.setArchiveInfo(getZipEntryMap().getArchiveInfo());
        apkWriter.setDataDescriptorFactory(DataDescriptorFactory.NO_ACTION);
    }

    private void applyExtractNativeLibs() {
        Boolean bool;
        int i = this.extractNativeLibs;
        if (i == 1) {
            bool = null;
        } else if (i == 2) {
            bool = Boolean.FALSE;
        } else if (i != 3) {
            return;
        } else {
            bool = Boolean.TRUE;
        }
        applyExtractNativeLibsOnLoadedManifest(bool);
        unCompressNativeLibs(Boolean.FALSE.equals(bool));
    }

    private void applyExtractNativeLibsOnLoadedManifest(Boolean bool) {
        AndroidManifestBlock androidManifestBlock = this.mManifestBlock;
        if (androidManifestBlock != null) {
            androidManifestBlock.setExtractNativeLibs(bool);
        }
    }

    public static /* synthetic */ boolean c(Entry entry) {
        return entry.isScalar() && TypeBlock.canHaveResourceFile(entry.getTypeName());
    }

    private void checkExternalFramework() {
        if (this.mDisableLoadFramework || this.preferredFramework != null || this.mExternalFrameworks.size() == 0) {
            return;
        }
        this.mDisableLoadFramework = true;
    }

    private void checkSelfFramework() {
        AndroidManifestBlock androidManifest;
        if (this.mDisableLoadFramework || this.preferredFramework != null || (androidManifest = getAndroidManifest()) == null || androidManifest.isCoreApp() == null || !"android".equals(androidManifest.getPackageName()) || androidManifest.guessCurrentPackageId() != 1) {
            return;
        }
        logMessage("Looks like framework apk, skip loading framework");
        this.mDisableLoadFramework = true;
    }

    public static /* synthetic */ boolean d(String str) {
        return str != null && str.startsWith("lib/") && str.endsWith(".so");
    }

    private void ensureFrameworkLinked() {
        TableBlock tableBlock;
        if (this.mDisableLoadFramework || (tableBlock = this.mTableBlock) == null || (tableBlock instanceof FrameworkTable) || isAndroid(tableBlock)) {
            return;
        }
        Integer num = this.preferredFramework;
        if (num == null && (this.mManifestBlock == null || tableBlock.hasFramework())) {
            return;
        }
        try {
            initializeAndroidFramework(tableBlock, num);
        } catch (IOException unused) {
        }
    }

    private void ensureLoadedManifestLinked() {
        AndroidManifestBlock androidManifestBlock;
        TableBlock tableBlock = this.mTableBlock;
        if (tableBlock == null || (androidManifestBlock = this.mManifestBlock) == null) {
            return;
        }
        PackageBlock packageBlock = androidManifestBlock.getPackageBlock();
        if (packageBlock == null || packageBlock.getTableBlock() != tableBlock) {
            PackageBlock packageBlockPickOne = tableBlock.pickOne(androidManifestBlock.guessCurrentPackageId());
            if (packageBlockPickOne == null) {
                packageBlockPickOne = tableBlock.pickOne();
            }
            if (packageBlockPickOne != null) {
                androidManifestBlock.setPackageBlock(packageBlockPickOne);
            }
            androidManifestBlock.setApkFile(this);
            ensureFrameworkLinked();
        }
    }

    private List<Entry> filterResFileEntries(TableString tableString, final int i, final ResConfig resConfig) {
        return CollectionUtil.toList(tableString.getEntries(new Predicate() { // from class: ya0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ApkModule.a(i, resConfig, (Entry) obj);
            }
        }));
    }

    private PackageBlock findPackageForPath(String str) {
        TableBlock tableBlock = getTableBlock();
        if (tableBlock == null) {
            return null;
        }
        if (tableBlock.size() == 1) {
            return tableBlock.get(0);
        }
        PackageBlock packageBlock = (PackageBlock) CollectionUtil.getFirst(tableBlock.getStringPool().getUsers(PackageBlock.class, str));
        return packageBlock == null ? tableBlock.pickOne() : packageBlock;
    }

    private ApkFile.ApkType initializeApkType(AndroidManifestBlock androidManifestBlock) {
        ApkFile.ApkType apkType = this.mApkType;
        if (apkType != null) {
            return apkType;
        }
        ApkFile.ApkType apkTypeGuessApkType = androidManifestBlock != null ? androidManifestBlock.guessApkType() : null;
        if (apkTypeGuessApkType == null) {
            return ApkFile.ApkType.UNKNOWN;
        }
        this.mApkType = apkTypeGuessApkType;
        return apkTypeGuessApkType;
    }

    private boolean isAndroid(TableBlock tableBlock) {
        if (tableBlock instanceof FrameworkTable) {
            return ((FrameworkTable) tableBlock).isAndroid();
        }
        return false;
    }

    private boolean isAndroidCoreApp(XMLDocument xMLDocument) {
        XMLElement documentElement = xMLDocument.getDocumentElement();
        if (documentElement != null && "android".equals(documentElement.getAttributeValue("package"))) {
            return "true".equals(documentElement.getAttributeValue("coreApp"));
        }
        return false;
    }

    public static ApkModule loadApkFile(APKLogger aPKLogger, File file, File... fileArr) throws IOException {
        ArchiveFile archiveFile = new ArchiveFile(file);
        ApkModule apkModule = new ApkModule(ApkUtil.DEF_MODULE_NAME, archiveFile.createZipEntryMap());
        apkModule.setAPKLogger(aPKLogger);
        apkModule.setApkSignatureBlock(archiveFile.getApkSignatureBlock());
        apkModule.setCloseable(archiveFile);
        if (fileArr != null && fileArr.length != 0) {
            for (File file2 : fileArr) {
                if (file2 != null) {
                    if (file.equals(file2)) {
                        r8g.a("External framework should be different: ", file);
                        return null;
                    }
                    apkModule.addExternalFramework(file2);
                }
            }
        }
        return apkModule;
    }

    private void logError(String str, Throwable th) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logError(str, th);
        }
    }

    private void logVerbose(String str) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logVerbose(str);
        }
    }

    private void mergeDexFiles(ApkModule apkModule) {
        int i;
        UncompressedFiles uncompressedFiles = apkModule.getUncompressedFiles();
        List<DexFileInputSource> listListDexFiles = listDexFiles();
        List<DexFileInputSource> listListDexFiles2 = apkModule.listDexFiles();
        ZipEntryMap zipEntryMap = getZipEntryMap();
        if (listListDexFiles.size() > 0) {
            int dexNumber = listListDexFiles.get(listListDexFiles.size() - 1).getDexNumber();
            i = dexNumber == 0 ? 2 : dexNumber + 1;
        } else {
            i = 0;
        }
        for (DexFileInputSource dexFileInputSource : listListDexFiles2) {
            uncompressedFiles.removePath(dexFileInputSource.getAlias());
            String dexName = DexFileInputSource.getDexName(i);
            zipEntryMap.add(new DexFileInputSource(dexName, dexFileInputSource.getInputSource()));
            logMessage("Added [" + apkModule.getModuleName() + "] " + dexFileInputSource.getAlias() + " -> " + dexName);
            i++;
            if (i == 1) {
                i = 2;
            }
        }
    }

    private void mergeFiles(ApkModule apkModule) {
        ZipEntryMap zipEntryMap = getZipEntryMap();
        LinkedHashMap<String, InputSource> aliasMap = apkModule.getZipEntryMap().toAliasMap();
        LinkedHashMap<String, InputSource> aliasMap2 = zipEntryMap.toAliasMap();
        UncompressedFiles uncompressedFiles = apkModule.getUncompressedFiles();
        for (InputSource inputSource : aliasMap.values()) {
            if (!aliasMap2.containsKey(inputSource.getAlias()) && !aliasMap2.containsKey(inputSource.getName()) && !DexFileInputSource.isDexName(inputSource.getName())) {
                if (inputSource.getAlias().startsWith("lib/")) {
                    uncompressedFiles.removePath(inputSource.getAlias());
                }
                logVerbose("Added: " + inputSource.getAlias());
                zipEntryMap.add(inputSource);
            }
        }
    }

    private void mergeFusedModules(ApkModule apkModule) {
        if (hasAndroidManifest() && apkModule.hasAndroidManifest()) {
            AndroidManifestBlock androidManifest = getAndroidManifest();
            AndroidManifestBlock androidManifest2 = apkModule.getAndroidManifest();
            if (androidManifest2.isFusingInclude()) {
                String[] fusedModuleNames = androidManifest2.getFusedModuleNames();
                if (fusedModuleNames != null && fusedModuleNames.length != 0) {
                    androidManifest.addFusedModuleNames(fusedModuleNames);
                    logMessage("Fused modules added [" + StringsUtil.join(fusedModuleNames, ',') + "]");
                }
                String split = androidManifest2.getSplit();
                if (split != null) {
                    androidManifest.addFusedModuleNames(new String[]{split});
                    logMessage("Added as fused module <" + split + ">");
                }
            }
        }
    }

    private void mergeTable(ApkModule apkModule) {
        TableBlock tableBlock;
        if (apkModule.hasTableBlock()) {
            if (hasTableBlock()) {
                tableBlock = getTableBlock();
            } else {
                tableBlock = new TableBlock();
                addInputSource(new BlockInputSource(TableBlock.FILE_NAME, tableBlock));
            }
            tableBlock.merge(apkModule.getTableBlock());
        }
    }

    private void onManifestBlockLoaded(AndroidManifestBlock androidManifestBlock) {
        initializeApkType(androidManifestBlock);
    }

    public static ApkModule readApkBytes(byte[] bArr) throws IOException {
        ApkModule apkModule = new ApkModule(new ArchiveBytes(bArr).createZipEntryMap());
        apkModule.setModuleName("byte_" + System.currentTimeMillis());
        return apkModule;
    }

    private Integer readVersionCode(XMLDocument xMLDocument) {
        String attributeValue;
        Integer numSafeParseInteger = null;
        if (xMLDocument == null) {
            return null;
        }
        XMLElement documentElement = xMLDocument.getDocumentElement();
        if (documentElement == null) {
            logMessage("WARN: Manifest root not found");
            return null;
        }
        String attributeValue2 = documentElement.getAttributeValue("android:compileSdkVersion");
        Integer numSafeParseInteger2 = attributeValue2 != null ? safeParseInteger(attributeValue2) : null;
        if (numSafeParseInteger2 == null && (attributeValue = documentElement.getAttributeValue("platformBuildVersionCode")) != null) {
            numSafeParseInteger2 = safeParseInteger(attributeValue);
        }
        Iterator elements = documentElement.getElements(AndroidManifest.TAG_uses_sdk);
        while (elements.hasNext()) {
            String attributeValue3 = ((XMLElement) elements.next()).getAttributeValue("android:targetSdkVersion");
            if (attributeValue3 != null) {
                numSafeParseInteger = safeParseInteger(attributeValue3);
            }
        }
        return (numSafeParseInteger2 != null && (numSafeParseInteger == null || numSafeParseInteger.intValue() <= numSafeParseInteger2.intValue())) ? numSafeParseInteger2 : numSafeParseInteger;
    }

    private Integer safeParseInteger(String str) {
        try {
            return Integer.valueOf(Integer.parseInt(str));
        } catch (NumberFormatException e) {
            logMessage("NumberFormatException on manifest version reading: '" + str + "': " + e.getMessage());
            return null;
        }
    }

    private void setManifestOriginalSource(InputSource inputSource) {
        if (this.mManifestOriginalSource != null || (inputSource instanceof BlockInputSource)) {
            return;
        }
        this.mManifestOriginalSource = inputSource;
    }

    private void setTableOriginalSource(InputSource inputSource) {
        if (this.mTableOriginalSource != null || (inputSource instanceof BlockInputSource)) {
            return;
        }
        this.mTableOriginalSource = inputSource;
    }

    private void unCompressNativeLibs(boolean z) {
        List<InputSource> listListNativeLibraryFiles = listNativeLibraryFiles();
        UncompressedFiles uncompressedFiles = getUncompressedFiles();
        for (InputSource inputSource : listListNativeLibraryFiles) {
            inputSource.setUncompressed(z);
            if (z) {
                uncompressedFiles.addPath(inputSource);
            } else {
                uncompressedFiles.removePath(inputSource.getName());
                uncompressedFiles.removePath(inputSource.getAlias());
            }
        }
    }

    private void unlinkLoadedManifest() {
        AndroidManifestBlock androidManifestBlock = this.mManifestBlock;
        if (androidManifestBlock == null) {
            return;
        }
        androidManifestBlock.setPackageBlock((PackageBlock) null);
        androidManifestBlock.setApkFile((ApkFile) null);
    }

    private void updateExternalFramework() {
        TableBlock tableBlock = this.mTableBlock;
        if (tableBlock == null) {
            return;
        }
        Iterator<TableBlock> it = this.mExternalFrameworks.iterator();
        while (it.hasNext()) {
            tableBlock.addFramework(it.next());
        }
    }

    private void validateMerge(ApkModule apkModule, boolean z) throws IOException {
        if (hasTableBlock()) {
            String packageName = getPackageName();
            int versionCode = getVersionCode();
            if (packageName == null || versionCode == 0) {
                return;
            }
            String packageName2 = apkModule.getPackageName();
            int versionCode2 = apkModule.getVersionCode();
            if (packageName2 == null || versionCode2 == 0 || !packageName.equals(packageName2) || versionCode == versionCode2) {
                return;
            }
            StringBuilder sb = new StringBuilder();
            if (!z) {
                sb.append("WARN: ");
            }
            sb.append("Incompatible to merge: {");
            sb.append(packageName);
            sb.append(", ");
            sb.append(versionCode);
            sb.append("}, with {");
            sb.append(packageName2);
            sb.append(", ");
            sb.append(versionCode2);
            sb.append("}");
            String string = sb.toString();
            if (z) {
                a16.a(string);
            } else {
                logMessage(string);
            }
        }
    }

    public void add(InputSource inputSource) {
        if (inputSource == null) {
            return;
        }
        String alias = inputSource.getAlias();
        if (AndroidManifest.FILE_NAME.equals(alias)) {
            if (getManifestOriginalSource() != inputSource) {
                this.mManifestBlock = null;
            }
            setManifestOriginalSource(inputSource);
        } else if (TableBlock.FILE_NAME.equals(alias)) {
            if (inputSource != getTableOriginalSource()) {
                this.mTableBlock = null;
            }
            setTableOriginalSource(inputSource);
        }
        addInputSource(inputSource);
    }

    public void addExternalFramework(File file) throws IOException {
        if (file == null) {
            return;
        }
        logMessage("Loading external framework: " + file);
        FrameworkApk frameworkApkLoadTableBlock = FrameworkApk.loadTableBlock(file);
        frameworkApkLoadTableBlock.setAPKLogger(getApkLogger());
        addExternalFramework(frameworkApkLoadTableBlock);
    }

    public void clearTags() {
        this.mTagMaps.clear();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        Closeable closeable = this.mCloseable;
        if (closeable != null) {
            closeable.close();
        }
    }

    public boolean containsFile(String str) {
        return getZipEntryMap().contains(str);
    }

    public ApkByteWriter createApkByteWriter() {
        updateUncompressedFiles();
        ApkByteWriter apkByteWriter = new ApkByteWriter(getZipEntryMap().toArray(true));
        applyDefaultApkWriterSetting(apkByteWriter);
        return apkByteWriter;
    }

    public ApkFileWriter createApkFileWriter(File file) throws IOException {
        updateUncompressedFiles();
        ApkFileWriter apkFileWriter = new ApkFileWriter(file, getZipEntryMap().toArray(true));
        applyDefaultApkWriterSetting(apkFileWriter);
        return apkFileWriter;
    }

    public ApkStreamWriter createApkStreamWriter(OutputStream outputStream) {
        updateUncompressedFiles();
        ApkStreamWriter apkStreamWriter = new ApkStreamWriter(outputStream, getZipEntryMap().toArray(true));
        applyDefaultApkWriterSetting(apkStreamWriter);
        return apkStreamWriter;
    }

    public XMLDocument decodeXMLFile(String str) throws IOException {
        PackageBlock packageBlockPickOne;
        ResXmlDocument resXmlDocumentLoadResXmlDocument = loadResXmlDocument(str);
        int iGuessCurrentPackageId = getAndroidManifest().guessCurrentPackageId();
        if (iGuessCurrentPackageId != 0 && hasTableBlock() && (packageBlockPickOne = getTableBlock().pickOne(iGuessCurrentPackageId)) != null) {
            resXmlDocumentLoadResXmlDocument.setPackageBlock(packageBlockPickOne);
        }
        throw new RuntimeException("Method not implemented");
    }

    public void destroy() {
        getZipEntryMap().clear();
        AndroidManifestBlock androidManifestBlock = this.mManifestBlock;
        if (androidManifestBlock != null) {
            androidManifestBlock.clear();
            this.mManifestBlock = null;
        }
        TableBlock tableBlock = this.mTableBlock;
        if (tableBlock != null) {
            this.mExternalFrameworks.clear();
            tableBlock.clear();
            this.mTableBlock = null;
        }
        try {
            close();
        } catch (IOException unused) {
        }
    }

    public void discardManifestChanges() {
        getZipEntryMap().add(getManifestOriginalSource());
    }

    public void discardTableBlockChanges() {
        getZipEntryMap().add(getTableOriginalSource());
    }

    public void dumpSignatureBlock(File file) throws IOException {
        ApkSignatureBlock apkSignatureBlock = getApkSignatureBlock();
        if (apkSignatureBlock != null) {
            apkSignatureBlock.writeRaw(file);
        } else {
            a16.a("Don't have signature block");
        }
    }

    public void dumpSignatureInfoFiles(File file) throws IOException {
        ApkSignatureBlock apkSignatureBlock = getApkSignatureBlock();
        if (apkSignatureBlock != null) {
            apkSignatureBlock.writeSplitRawToDirectory(file);
        } else {
            a16.a("Don't have signature block");
        }
    }

    public boolean ensureTableBlock() {
        if (hasTableBlock()) {
            return false;
        }
        setTableBlock(TableBlock.createEmpty());
        return true;
    }

    public Integer getAndroidFrameworkVersion() {
        Integer num = this.preferredFramework;
        if (num != null) {
            return num;
        }
        if (!hasAndroidManifest()) {
            return null;
        }
        AndroidManifestBlock androidManifest = getAndroidManifest();
        Integer compileSdkVersion = androidManifest.getCompileSdkVersion();
        if (compileSdkVersion == null) {
            compileSdkVersion = androidManifest.getPlatformBuildVersionCode();
        }
        Integer targetSdkVersion = androidManifest.getTargetSdkVersion();
        return (compileSdkVersion != null && (targetSdkVersion == null || targetSdkVersion.intValue() <= compileSdkVersion.intValue())) ? compileSdkVersion : targetSdkVersion;
    }

    public AndroidManifestBlock getAndroidManifest() {
        AndroidManifestBlock androidManifestBlock = this.mManifestBlock;
        if (androidManifestBlock != null) {
            return androidManifestBlock;
        }
        InputSource inputSource = getInputSource(AndroidManifest.FILE_NAME);
        if (inputSource == null) {
            return null;
        }
        setManifestOriginalSource(inputSource);
        try {
            InputStream inputStreamOpenStream = inputSource.openStream();
            AndroidManifestBlock androidManifestBlockLoad = AndroidManifestBlock.load(inputStreamOpenStream);
            inputStreamOpenStream.close();
            this.mManifestBlock = androidManifestBlockLoad;
            BlockInputSource blockInputSource = new BlockInputSource(inputSource.getName(), androidManifestBlockLoad);
            blockInputSource.copyAttributes(inputSource);
            addInputSource(blockInputSource);
            ensureLoadedManifestLinked();
            onManifestBlockLoaded(androidManifestBlockLoad);
            return this.mManifestBlock;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Deprecated
    public AndroidManifestBlock getAndroidManifestBlock() {
        return getAndroidManifest();
    }

    public APKLogger getApkLogger() {
        return this.apkLogger;
    }

    public ApkSignatureBlock getApkSignatureBlock() {
        return this.apkSignatureBlock;
    }

    public ApkFile.ApkType getApkType() {
        ApkFile.ApkType apkType = this.mApkType;
        return apkType != null ? apkType : initializeApkType(this.mManifestBlock);
    }

    public InputSource getInputSource(String str) {
        return getZipEntryMap().getInputSource(str);
    }

    public InputSource[] getInputSources() {
        return getZipEntryMap().toArray();
    }

    public FrameworkApk getLoadedFramework(Integer num, boolean z) {
        Iterator<TableBlock> it = getLoadedFrameworks().iterator();
        while (it.hasNext()) {
            FrameworkTable frameworkTable = (TableBlock) it.next();
            if (frameworkTable instanceof FrameworkTable) {
                FrameworkTable frameworkTable2 = frameworkTable;
                if (!z || isAndroid(frameworkTable2)) {
                    if (num == null || num.equals(Integer.valueOf(frameworkTable2.getVersionCode()))) {
                        return (FrameworkApk) frameworkTable2.getApkFile();
                    }
                }
            }
        }
        return null;
    }

    public List<TableBlock> getLoadedFrameworks() {
        ArrayCollection arrayCollection = new ArrayCollection();
        if (!hasTableBlock()) {
            return arrayCollection;
        }
        arrayCollection.addAll(getTableBlock(false).getFrameWorks());
        return arrayCollection;
    }

    public TableBlock getLoadedTableBlock() {
        return this.mTableBlock;
    }

    public InputSource getManifestOriginalSource() {
        InputSource inputSource = this.mManifestOriginalSource;
        if (inputSource != null) {
            return inputSource;
        }
        InputSource inputSource2 = getInputSource(AndroidManifest.FILE_NAME);
        this.mManifestOriginalSource = inputSource2;
        return inputSource2;
    }

    public String getModuleName() {
        return this.moduleName;
    }

    public String getPackageName() {
        PackageBlock packageBlock;
        if (hasAndroidManifest()) {
            return getAndroidManifest().getPackageName();
        }
        if (hasTableBlock() && (packageBlock = getTableBlock().getPackageArray().get(0)) != null) {
            return packageBlock.getName();
        }
        return null;
    }

    public ResFile getResFile(String str) {
        InputSource inputSource = getInputSource(str);
        if (inputSource == null) {
            return null;
        }
        List<Entry> listListReferencedEntries = listReferencedEntries(str);
        if (listListReferencedEntries.isEmpty()) {
            return null;
        }
        return new ResFile(inputSource, listListReferencedEntries);
    }

    public ResXmlDocument getResXmlDocument(String str) {
        InputSource inputSource = getInputSource(str);
        if (inputSource == null) {
            return null;
        }
        try {
            return loadResXmlDocument(inputSource);
        } catch (IOException unused) {
            return null;
        }
    }

    public String getSplit() {
        if (hasAndroidManifest()) {
            return getAndroidManifest().getSplit();
        }
        return null;
    }

    public TableBlock getTableBlock(boolean z) {
        TableBlock tableBlock = this.mTableBlock;
        if (tableBlock != null) {
            return tableBlock;
        }
        if (!hasTableBlock()) {
            return null;
        }
        try {
            TableBlock tableBlockLoadTableBlock = loadTableBlock();
            this.mTableBlock = tableBlockLoadTableBlock;
            if (z && this.loadDefaultFramework) {
                initializeAndroidFramework(tableBlockLoadTableBlock, getAndroidFrameworkVersion());
            }
            updateExternalFramework();
            ensureLoadedManifestLinked();
            return tableBlockLoadTableBlock;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public InputSource getTableOriginalSource() {
        InputSource inputSource = this.mTableOriginalSource;
        if (inputSource != null) {
            return inputSource;
        }
        InputSource inputSource2 = getInputSource(TableBlock.FILE_NAME);
        this.mTableOriginalSource = inputSource2;
        return inputSource2;
    }

    public Object getTag(Object obj) {
        return this.mTagMaps.get(obj);
    }

    public UncompressedFiles getUncompressedFiles() {
        return this.mUncompressedFiles;
    }

    public int getVersionCode() {
        Integer versionCode;
        AndroidManifestBlock androidManifest = getAndroidManifest();
        if (androidManifest == null || (versionCode = androidManifest.getVersionCode()) == null) {
            return 0;
        }
        return versionCode.intValue();
    }

    public TableStringPool getVolatileTableStringPool() throws IOException {
        TableBlock tableBlock = this.mTableBlock;
        if (tableBlock != null) {
            return tableBlock.getStringPool();
        }
        String str = TableBlock.FILE_NAME;
        InputSource inputSource = getInputSource(str);
        if (inputSource == null) {
            r8g.a("Module don't have: ", str);
            return null;
        }
        if (!(inputSource instanceof ArchiveFileEntrySource) && !(inputSource instanceof FileInputSource)) {
            return getTableBlock().getStringPool();
        }
        InputStream inputStreamOpenStream = inputSource.openStream();
        TableStringPool fromTable = TableStringPool.readFromTable(inputStreamOpenStream);
        inputStreamOpenStream.close();
        return fromTable;
    }

    public ZipEntryMap getZipEntryMap() {
        return this.zipEntryMap;
    }

    public boolean hasAndroidManifest() {
        return (this.mManifestBlock == null && getZipEntryMap().getInputSource(AndroidManifest.FILE_NAME) == null) ? false : true;
    }

    @Deprecated
    public boolean hasAndroidManifestBlock() {
        return hasAndroidManifest();
    }

    public boolean hasSignatureBlock() {
        return getApkSignatureBlock() != null;
    }

    public boolean hasTableBlock() {
        return (this.mTableBlock == null && getZipEntryMap().getInputSource(TableBlock.FILE_NAME) == null) ? false : true;
    }

    public FrameworkApk initializeAndroidFramework(TableBlock tableBlock, Integer num) throws IOException {
        FrameworkApk bestMatch;
        if (this.mDisableLoadFramework || tableBlock == null || isAndroid(tableBlock)) {
            return null;
        }
        FrameworkApk loadedFramework = getLoadedFramework(num, true);
        if (loadedFramework != null) {
            return loadedFramework;
        }
        logMessage("Initializing android framework ...");
        if (num == null) {
            logMessage("Can not read framework version, loading latest");
            bestMatch = AndroidFrameworks.getLatest();
        } else {
            logMessage("Loading android framework for version: " + num);
            bestMatch = AndroidFrameworks.getBestMatch(num.intValue());
        }
        tableBlock.addFramework(bestMatch.getTableBlock());
        logMessage("Initialized framework: " + bestMatch.getName() + " (" + bestMatch.getVersionName() + ")");
        return bestMatch;
    }

    public boolean isBaseModule() {
        if (!hasAndroidManifest()) {
            return false;
        }
        try {
            AndroidManifestBlock androidManifest = getAndroidManifest();
            return (androidManifest.isSplit() || androidManifest.getMainActivity() == null) ? false : true;
        } catch (Exception unused) {
        }
    }

    public boolean isFrameworkVersionLoaded(Integer num) {
        if (num == null) {
            return false;
        }
        Iterator<TableBlock> it = getLoadedFrameworks().iterator();
        while (it.hasNext()) {
            FrameworkTable frameworkTable = (TableBlock) it.next();
            if ((frameworkTable instanceof FrameworkTable) && num.equals(Integer.valueOf(frameworkTable.getVersionCode()))) {
                return true;
            }
        }
        return false;
    }

    public void keepManifestChanges() {
        this.mManifestOriginalSource = null;
    }

    public void keepTableBlockChanges() {
        this.mTableOriginalSource = null;
    }

    public List<DexFileInputSource> listDexFiles() {
        ArrayCollection arrayCollection = new ArrayCollection();
        for (InputSource inputSource : getInputSources()) {
            if (DexFileInputSource.isDexName(inputSource.getAlias())) {
                arrayCollection.add(inputSource instanceof DexFileInputSource ? (DexFileInputSource) inputSource : new DexFileInputSource(inputSource.getAlias(), inputSource));
            }
        }
        DexFileInputSource.sort(arrayCollection);
        return arrayCollection;
    }

    public List<InputSource> listInputSources() {
        return getZipEntryMap().listInputSources();
    }

    public List<InputSource> listNativeLibraryFiles() {
        return CollectionUtil.toList(getZipEntryMap().iteratorWithPath(new Predicate() { // from class: wa0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ApkModule.d((String) obj);
            }
        }));
    }

    public List<Entry> listReferencedEntries(String str) {
        ArrayCollection arrayCollection = new ArrayCollection();
        TableBlock tableBlock = getTableBlock();
        if (tableBlock != null) {
            Iterator all = tableBlock.getStringPool().getAll(str);
            Predicate predicate = new Predicate() { // from class: xa0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ApkModule.c((Entry) obj);
                }
            };
            while (all.hasNext()) {
                arrayCollection.addAll(((TableString) all.next()).getEntries(predicate));
            }
        }
        return arrayCollection;
    }

    public List<ResFile> listResFiles(int i, ResConfig resConfig) {
        ArrayCollection arrayCollection = new ArrayCollection();
        TableBlock tableBlock = getTableBlock();
        if (tableBlock != null) {
            TableStringPool stringPool = tableBlock.getStringPool();
            for (InputSource inputSource : getInputSources()) {
                Iterator all = stringPool.getAll(inputSource.getAlias());
                while (all.hasNext()) {
                    List<Entry> listFilterResFileEntries = filterResFileEntries((TableString) all.next(), i, resConfig);
                    if (!listFilterResFileEntries.isEmpty()) {
                        arrayCollection.add(new ResFile(inputSource, listFilterResFileEntries));
                    }
                }
            }
        }
        return arrayCollection;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0012  */
    public ResXmlDocument loadResXmlDocument(InputSource inputSource) throws IOException {
        ResXmlDocument resXmlDocument;
        if (inputSource instanceof BlockInputSource) {
            Block block = ((BlockInputSource) inputSource).getBlock();
            if (block instanceof ResXmlDocument) {
                resXmlDocument = (ResXmlDocument) block;
            } else {
                resXmlDocument = null;
            }
        } else {
            resXmlDocument = null;
        }
        if (resXmlDocument == null) {
            resXmlDocument = new ResXmlDocument();
            resXmlDocument.readBytes(inputSource.openStream());
        }
        resXmlDocument.setApkFile(this);
        if (resXmlDocument.getPackageBlock() == null) {
            resXmlDocument.setPackageBlock(findPackageForPath(inputSource.getAlias()));
        }
        return resXmlDocument;
    }

    public void loadSignatureBlock(File file) throws IOException {
        if (!file.isFile()) {
            r8g.a("No such file: ", file);
            return;
        }
        ApkSignatureBlock apkSignatureBlock = this.apkSignatureBlock;
        if (apkSignatureBlock == null) {
            apkSignatureBlock = new ApkSignatureBlock();
        }
        apkSignatureBlock.read(file);
        setApkSignatureBlock(apkSignatureBlock);
    }

    public TableBlock loadTableBlock() throws IOException {
        TableBlock tableBlock;
        String str = TableBlock.FILE_NAME;
        InputSource inputSource = getInputSource(str);
        if (inputSource == null) {
            r8g.a("Entry not found: ", str);
            return null;
        }
        if (inputSource instanceof BlockInputSource) {
            tableBlock = (TableBlock) ((BlockInputSource) inputSource).getBlock();
        } else {
            setTableOriginalSource(inputSource);
            InputStream inputStreamOpenStream = inputSource.openStream();
            TableBlock tableBlockLoad = TableBlock.load(inputStreamOpenStream);
            inputStreamOpenStream.close();
            tableBlock = tableBlockLoad;
        }
        BlockInputSource blockInputSource = new BlockInputSource(inputSource.getName(), tableBlock);
        blockInputSource.copyAttributes(inputSource);
        getZipEntryMap().add(blockInputSource);
        tableBlock.setApkFile(this);
        return tableBlock;
    }

    public void logMessage(String str) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logMessage(str);
        }
    }

    public void merge(ApkModule apkModule, boolean z) throws IOException {
        if (apkModule == null || apkModule == this) {
            return;
        }
        logMessage("Merging: " + apkModule.getModuleName());
        validateMerge(apkModule, z);
        mergeDexFiles(apkModule);
        mergeTable(apkModule);
        mergeFiles(apkModule);
        getUncompressedFiles().merge(apkModule.getUncompressedFiles());
        mergeFusedModules(apkModule);
    }

    public void putTag(Object obj, Object obj2) {
        this.mTagMaps.put(obj, obj2);
    }

    public void refreshManifest() {
        AndroidManifestBlock androidManifestBlock = this.mManifestBlock;
        if (androidManifestBlock != null) {
            androidManifestBlock.refreshFull();
        }
    }

    public String refreshTable() {
        TableBlock tableBlock = this.mTableBlock;
        if (tableBlock != null) {
            return tableBlock.refreshFull();
        }
        return null;
    }

    public void removeDir(String str) {
        getZipEntryMap().removeDir(str);
    }

    public InputSource removeInputSource(String str) {
        return getZipEntryMap().remove(str);
    }

    public boolean removeResFile(String str, boolean z) {
        ResFile resFile;
        if (getInputSource(str) == null || (resFile = getResFile(str)) == null) {
            return false;
        }
        resFile.delete(z);
        removeInputSource(str);
        return true;
    }

    public List<Entry> removeResFiles(int i, ResConfig resConfig) {
        ArrayCollection arrayCollection = new ArrayCollection();
        if (i != 0 || resConfig != null) {
            List<ResFile> listListResFiles = listResFiles(i, resConfig);
            ZipEntryMap zipEntryMap = getZipEntryMap();
            for (ResFile resFile : listListResFiles) {
                arrayCollection.addAll(resFile.iterator());
                zipEntryMap.remove(resFile.getInputSource());
            }
        }
        return arrayCollection;
    }

    public void removeResFilesWithEntry(int i, ResConfig resConfig, boolean z) {
        SpecTypePair parentSpecTypePair = null;
        for (Entry entry : removeResFiles(i, resConfig)) {
            if (entry != null && !entry.isNull()) {
                if (z && parentSpecTypePair == null) {
                    parentSpecTypePair = entry.getTypeBlock().getParentSpecTypePair();
                }
                entry.setNull(true);
            }
        }
        if (parentSpecTypePair != null) {
            parentSpecTypePair.removeNullEntries(i);
        }
    }

    public Object removeTag(Object obj) {
        return this.mTagMaps.remove(obj);
    }

    public void scanSignatureInfoFiles(File file) throws IOException {
        if (!file.isDirectory()) {
            r8g.a("No such directory: ", file);
            return;
        }
        ApkSignatureBlock apkSignatureBlock = this.apkSignatureBlock;
        if (apkSignatureBlock == null) {
            apkSignatureBlock = new ApkSignatureBlock();
        }
        apkSignatureBlock.scanSplitFiles(file);
        setApkSignatureBlock(apkSignatureBlock);
    }

    public void setAPKLogger(APKLogger aPKLogger) {
        this.apkLogger = aPKLogger;
    }

    public void setApkSignatureBlock(ApkSignatureBlock apkSignatureBlock) {
        this.apkSignatureBlock = apkSignatureBlock;
    }

    public void setApkType(ApkFile.ApkType apkType) {
        this.mApkType = apkType;
    }

    public void setCloseable(Closeable closeable) {
        this.mCloseable = closeable;
    }

    public void setExtractNativeLibs(Boolean bool) {
        int i;
        if (bool == null) {
            i = 1;
        } else {
            i = !bool.booleanValue() ? 2 : 3;
        }
        this.extractNativeLibs = i;
    }

    public void setLoadDefaultFramework(boolean z) {
        this.loadDefaultFramework = z;
        this.mDisableLoadFramework = !z;
    }

    public void setManifest(AndroidManifestBlock androidManifestBlock) {
        ZipEntryMap zipEntryMap = getZipEntryMap();
        if (androidManifestBlock == null) {
            this.mManifestBlock = null;
            this.mManifestOriginalSource = null;
            zipEntryMap.remove(AndroidManifest.FILE_NAME);
        } else {
            androidManifestBlock.setApkFile(this);
            BlockInputSource blockInputSource = new BlockInputSource(AndroidManifest.FILE_NAME, androidManifestBlock);
            blockInputSource.setMethod(0);
            blockInputSource.setSort(0);
            zipEntryMap.add(blockInputSource);
            this.mManifestBlock = androidManifestBlock;
        }
    }

    public void setModuleName(String str) {
        str.getClass();
        this.moduleName = str;
        this.zipEntryMap.setModuleName(str);
    }

    public void setPackageName(String str) {
        String packageName = getPackageName();
        if (hasAndroidManifest()) {
            getAndroidManifest().setPackageName(str);
        }
        if (hasTableBlock()) {
            PackageArray packageArray = getTableBlock().getPackageArray();
            for (PackageBlock packageBlock : packageArray.listItems()) {
                if (packageArray.size() == 1) {
                    packageBlock.setName(str);
                } else {
                    String name = packageBlock.getName();
                    if (name.startsWith(packageName)) {
                        packageBlock.setName(name.replace(packageName, str));
                    }
                }
            }
        }
    }

    public void setPreferredFramework(Integer num) {
        if (num == null || !num.equals(this.preferredFramework)) {
            this.preferredFramework = num;
            if (num == null || this.mTableBlock == null || isFrameworkVersionLoaded(num)) {
                return;
            }
            logMessage("Initializing preferred framework: " + num);
            this.mTableBlock.clearFrameworks();
            FrameworkApk bestMatch = AndroidFrameworks.getBestMatch(num.intValue());
            AndroidFrameworks.setCurrent(bestMatch);
            this.mTableBlock.addFramework(bestMatch.getTableBlock());
            logMessage("Initialized framework: " + bestMatch.getVersionCode());
        }
    }

    public void setResourcesRootDir(String str) {
        List<ResFile> listListResFiles = listResFiles();
        HashSet hashSet = new HashSet();
        for (InputSource inputSource : getInputSources()) {
            hashSet.add(inputSource.getAlias());
        }
        for (ResFile resFile : listListResFiles) {
            String filePath = resFile.getFilePath();
            String strReplaceRootDir = ApkUtil.replaceRootDir(filePath, str);
            if (!hashSet.contains(strReplaceRootDir)) {
                hashSet.remove(filePath);
                hashSet.add(strReplaceRootDir);
                resFile.setFilePath(strReplaceRootDir);
                if (resFile.getInputSource().getMethod() == 0) {
                    getUncompressedFiles().replacePath(filePath, strReplaceRootDir);
                }
                logVerbose("Root changed: '" + filePath + "' -> '" + strReplaceRootDir + "'");
            }
        }
        getTableBlock().refresh();
    }

    public void setTableBlock(TableBlock tableBlock) {
        ZipEntryMap zipEntryMap = getZipEntryMap();
        if (tableBlock == null) {
            this.mTableBlock = null;
            this.mTableOriginalSource = null;
            zipEntryMap.remove(TableBlock.FILE_NAME);
            unlinkLoadedManifest();
            return;
        }
        tableBlock.setApkFile(this);
        BlockInputSource blockInputSource = new BlockInputSource(TableBlock.FILE_NAME, tableBlock);
        zipEntryMap.add(blockInputSource);
        blockInputSource.setMethod(0);
        blockInputSource.setSort(1);
        getUncompressedFiles().addPath(blockInputSource);
        this.mTableBlock = tableBlock;
        updateExternalFramework();
        ensureLoadedManifestLinked();
    }

    public String toString() {
        return getModuleName();
    }

    public void uncompressNonXmlResFiles() {
        for (ResFile resFile : listResFiles()) {
            if (!resFile.isBinaryXml()) {
                resFile.getInputSource().setMethod(0);
            }
        }
    }

    public void updateUncompressedFiles() {
        getUncompressedFiles().apply(getZipEntryMap());
        applyExtractNativeLibs();
    }

    public void validateResourceNames() {
        if (hasTableBlock()) {
            logMessage("Validating resource names ...");
            Iterator it = getTableBlock().listPackages().iterator();
            while (it.hasNext()) {
                validateResourceNames((PackageBlock) it.next());
            }
        }
    }

    public void validateResourcesDir() {
        List<ResFile> listListResFiles = listResFiles();
        HashSet hashSet = new HashSet();
        for (InputSource inputSource : getInputSources()) {
            hashSet.add(inputSource.getAlias());
        }
        for (ResFile resFile : listListResFiles) {
            String filePath = resFile.getFilePath();
            String strValidateTypeDirectoryName = resFile.validateTypeDirectoryName();
            if (strValidateTypeDirectoryName != null && !strValidateTypeDirectoryName.equals(filePath) && !hashSet.contains(strValidateTypeDirectoryName)) {
                hashSet.remove(filePath);
                hashSet.add(strValidateTypeDirectoryName);
                resFile.setFilePath(strValidateTypeDirectoryName);
                if (resFile.getInputSource().getMethod() == 0) {
                    getUncompressedFiles().replacePath(filePath, strValidateTypeDirectoryName);
                }
                logVerbose("Dir validated: '" + filePath + "' -> '" + strValidateTypeDirectoryName + "'");
            }
        }
        getTableBlock().refresh();
    }

    public void writeApk(File file, WriteProgress writeProgress) throws IOException {
        ApkFileWriter apkFileWriterCreateApkFileWriter = createApkFileWriter(file);
        apkFileWriterCreateApkFileWriter.setWriteProgress(writeProgress);
        apkFileWriterCreateApkFileWriter.write();
    }

    public byte[] writeApkBytes() throws IOException {
        ApkByteWriter apkByteWriterCreateApkByteWriter = createApkByteWriter();
        apkByteWriterCreateApkByteWriter.write();
        return apkByteWriterCreateApkByteWriter.toByteArray();
    }

    public void writeApk(File file) throws IOException {
        writeApk(file, null);
    }

    public void writeApk(OutputStream outputStream) throws IOException {
        createApkStreamWriter(outputStream).write();
    }

    public boolean removeResFile(String str) {
        return removeResFile(str, true);
    }

    public void addExternalFramework(ApkModule apkModule) {
        if (apkModule == null || apkModule == this || !apkModule.hasTableBlock()) {
            return;
        }
        addExternalFramework(apkModule.getTableBlock());
    }

    public void addExternalFramework(TableBlock tableBlock) {
        if (tableBlock == null || tableBlock.getApkFile() == this || this.mExternalFrameworks.contains(tableBlock)) {
            return;
        }
        this.mExternalFrameworks.add(tableBlock);
        updateExternalFramework();
    }

    public ApkModule(ZipEntryMap zipEntryMap) {
        this(ApkUtil.DEF_MODULE_NAME, zipEntryMap);
    }

    public void validateResourceNames(PackageBlock packageBlock) {
        PackageIdentifier packageIdentifier = new PackageIdentifier();
        packageIdentifier.load(packageBlock);
        if (packageIdentifier.hasDuplicateResources()) {
            logMessage("Renaming duplicate resources ... ");
            packageIdentifier.ensureUniqueResourceNames();
            packageIdentifier.setResourceNamesToPackage(packageBlock);
        }
    }

    public ApkModule() {
        this(ApkUtil.DEF_MODULE_NAME, new ZipEntryMap());
    }

    public TableBlock getTableBlock() {
        TableBlock tableBlock = this.mTableBlock;
        if (tableBlock != null) {
            return tableBlock;
        }
        checkExternalFramework();
        checkSelfFramework();
        return getTableBlock(!this.mDisableLoadFramework);
    }

    public List<Entry> removeResFiles(int i) {
        return removeResFiles(i, null);
    }

    public void merge(ApkModule apkModule) throws IOException {
        merge(apkModule, false);
    }

    public void removeResFilesWithEntry(int i) {
        removeResFilesWithEntry(i, null, true);
    }

    public ResXmlDocument loadResXmlDocument(String str) throws IOException {
        InputSource inputSource = getInputSource(str);
        if (inputSource != null) {
            return loadResXmlDocument(inputSource);
        }
        s8g.a("No such file in apk: ", str);
        return null;
    }

    public static ApkModule loadApkFile(File file, String str) throws IOException {
        ArchiveFile archiveFile = new ArchiveFile(file);
        ApkModule apkModule = new ApkModule(str, archiveFile.createZipEntryMap());
        apkModule.setApkSignatureBlock(archiveFile.getApkSignatureBlock());
        apkModule.setCloseable(archiveFile);
        return apkModule;
    }

    public static ApkModule loadApkFile(File file, File... fileArr) throws IOException {
        return loadApkFile(null, file, fileArr);
    }

    public static ApkModule loadApkFile(File file) throws IOException {
        return loadApkFile(file, ApkUtil.DEF_MODULE_NAME);
    }

    public List<ResFile> listResFiles() {
        return listResFiles(0, null);
    }

    public FrameworkApk initializeAndroidFramework(Integer num) throws IOException {
        return initializeAndroidFramework(getTableBlock(false), num);
    }

    public FrameworkApk initializeAndroidFramework(XMLDocument xMLDocument) throws IOException {
        Integer num = this.preferredFramework;
        if (num != null) {
            return initializeAndroidFramework(num);
        }
        if (isAndroidCoreApp(xMLDocument)) {
            logMessage("Looks framework itself, skip loading frameworks");
            return null;
        }
        return initializeAndroidFramework(readVersionCode(xMLDocument));
    }
}
