package com.reandroid.apk;

import com.reandroid.app.AndroidManifest;
import com.reandroid.archive.ArchiveBytes;
import com.reandroid.archive.ArchiveFile;
import com.reandroid.archive.BlockInputSource;
import com.reandroid.archive.InputSource;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.chunk.TableBlock;
import com.reandroid.arsc.chunk.xml.AndroidManifestBlock;
import com.reandroid.arsc.chunk.xml.ResXmlAttribute;
import com.reandroid.arsc.model.FrameworkTable;
import com.reandroid.arsc.value.ValueType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class FrameworkApk extends ApkModule {
    private boolean mDestroyed;
    private final Object mLock;
    private boolean mOptimizing;
    private String packageName;
    private int versionCode;
    private String versionName;

    public FrameworkApk(String str, ZipEntryMap zipEntryMap) {
        super(str, zipEntryMap);
        this.mLock = new Object();
        super.setLoadDefaultFramework(false);
    }

    private void initValues() {
        PackageBlock packageBlockPickOne;
        int versionCode;
        if (hasAndroidManifest()) {
            AndroidManifestBlock androidManifest = getAndroidManifest();
            Integer versionCode2 = androidManifest.getVersionCode();
            if (versionCode2 != null) {
                this.versionCode = versionCode2.intValue();
            }
            if (this.versionName == null) {
                this.versionName = androidManifest.getVersionName();
            }
            if (this.packageName == null) {
                this.packageName = androidManifest.getPackageName();
            }
        }
        if (hasTableBlock()) {
            FrameworkTable tableBlock = getTableBlock();
            if (tableBlock.isOptimized() && this.versionCode == 0 && (versionCode = tableBlock.getVersionCode()) != 0) {
                this.versionCode = versionCode;
                if (this.versionName == null) {
                    this.versionName = String.valueOf(versionCode);
                }
            }
            if (this.packageName != null || (packageBlockPickOne = tableBlock.pickOne()) == null) {
                return;
            }
            this.packageName = packageBlockPickOne.getName();
        }
    }

    public static boolean isFramework(AndroidManifestBlock androidManifestBlock) {
        ResXmlAttribute resXmlAttributeSearchAttributeByName = androidManifestBlock.getManifestElement().searchAttributeByName(AndroidManifest.NAME_coreApp);
        if (resXmlAttributeSearchAttributeByName == null || resXmlAttributeSearchAttributeByName.getValueType() != ValueType.BOOLEAN) {
            return false;
        }
        return resXmlAttributeSearchAttributeByName.getValueAsBoolean();
    }

    public static FrameworkApk loadApkBuffer(String str, InputStream inputStream) throws IOException {
        FrameworkApk frameworkApk = new FrameworkApk(str, new ArchiveBytes(inputStream).createZipEntryMap());
        frameworkApk.initValues();
        return frameworkApk;
    }

    private static FrameworkApk loadApkFile(File file, boolean z) throws IOException {
        ArchiveFile archiveFile = new ArchiveFile(file);
        String str = TableBlock.FILE_NAME;
        InputSource entrySource = archiveFile.getEntrySource(str);
        if (entrySource == null) {
            a28.a("Missing ", str, ", on ", file);
            return null;
        }
        ZipEntryMap zipEntryMap = new ZipEntryMap();
        zipEntryMap.add(entrySource);
        if (z) {
            zipEntryMap.add(archiveFile.getEntrySource(AndroidManifest.FILE_NAME));
        }
        FrameworkApk frameworkApk = new FrameworkApk(zipEntryMap);
        frameworkApk.setCloseable(archiveFile);
        return frameworkApk;
    }

    @Override // com.reandroid.apk.ApkModule
    public void destroy() {
        synchronized (this.mLock) {
            this.versionCode = -1;
            this.versionName = "-1";
            this.packageName = "destroyed";
            super.destroy();
            this.mDestroyed = true;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return getName().equals(((FrameworkApk) obj).getName());
    }

    @Override // com.reandroid.apk.ApkModule
    public FrameworkTable getLoadedTableBlock() {
        return super.getLoadedTableBlock();
    }

    public String getName() {
        if (isDestroyed()) {
            return "destroyed";
        }
        String packageName = getPackageName();
        if (packageName == null) {
            return "";
        }
        return packageName + "-" + getVersionCode();
    }

    @Override // com.reandroid.apk.ApkModule
    public String getPackageName() {
        if (this.packageName == null) {
            initValues();
        }
        return this.packageName;
    }

    @Override // com.reandroid.apk.ApkModule
    public FrameworkTable getTableBlock() {
        return super.getTableBlock();
    }

    @Override // com.reandroid.apk.ApkModule
    public int getVersionCode() {
        if (this.versionCode == 0) {
            initValues();
        }
        return this.versionCode;
    }

    public String getVersionName() {
        if (this.versionName == null) {
            initValues();
        }
        return this.versionName;
    }

    public int hashCode() {
        return Objects.hash(getClass(), getName());
    }

    public boolean isDestroyed() {
        synchronized (this.mLock) {
            try {
                if (!this.mDestroyed) {
                    return false;
                }
                if (!hasTableBlock()) {
                    return true;
                }
                this.versionCode = 0;
                this.versionName = null;
                this.packageName = null;
                this.mDestroyed = false;
                return false;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.reandroid.apk.ApkModule
    public FrameworkTable loadTableBlock() throws IOException {
        ZipEntryMap zipEntryMap = getZipEntryMap();
        String str = TableBlock.FILE_NAME;
        InputSource inputSource = zipEntryMap.getInputSource(str);
        if (inputSource == null) {
            r8g.a("Entry not found: ", str);
            return null;
        }
        FrameworkTable frameworkTableLoad = FrameworkTable.load(inputSource.openStream());
        frameworkTableLoad.setApkFile(this);
        BlockInputSource blockInputSource = new BlockInputSource(inputSource.getName(), frameworkTableLoad);
        blockInputSource.setMethod(inputSource.getMethod());
        blockInputSource.setSort(inputSource.getSort());
        zipEntryMap.add(blockInputSource);
        return frameworkTableLoad;
    }

    public void optimize() {
        synchronized (this.mLock) {
            try {
                if (this.mOptimizing) {
                    return;
                }
                if (!hasTableBlock()) {
                    this.mOptimizing = false;
                    return;
                }
                if (getTableBlock().isOptimized()) {
                    this.mOptimizing = false;
                    initValues();
                } else {
                    new FrameworkOptimizer(this).optimize();
                    this.mOptimizing = false;
                    initValues();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // com.reandroid.apk.ApkModule
    public void setManifest(AndroidManifestBlock androidManifestBlock) {
        synchronized (this.mLock) {
            super.setManifest(androidManifestBlock);
            this.versionCode = 0;
            this.versionName = null;
            this.packageName = null;
        }
    }

    @Override // com.reandroid.apk.ApkModule
    public void setPackageName(String str) {
        super.setPackageName(str);
        this.packageName = null;
    }

    @Override // com.reandroid.apk.ApkModule
    public void setTableBlock(TableBlock tableBlock) {
        synchronized (this.mLock) {
            super.setTableBlock(tableBlock);
            this.versionCode = 0;
            this.versionName = null;
            this.packageName = null;
        }
    }

    @Override // com.reandroid.apk.ApkModule
    public String toString() {
        return getName();
    }

    public FrameworkApk(ZipEntryMap zipEntryMap) {
        this("framework", zipEntryMap);
    }

    public static FrameworkApk loadApkBuffer(InputStream inputStream) throws IOException {
        return loadApkBuffer("framework", inputStream);
    }

    public static boolean isFramework(ApkModule apkModule) {
        if (apkModule.hasAndroidManifest()) {
            return isFramework(apkModule.getAndroidManifest());
        }
        return false;
    }

    public static FrameworkApk loadApkFile(File file) throws IOException {
        return loadApkFile(file, true);
    }

    public static FrameworkApk loadApkFile(File file, String str) throws IOException {
        ArchiveFile archiveFile = new ArchiveFile(file);
        FrameworkApk frameworkApk = new FrameworkApk(str, archiveFile.createZipEntryMap());
        frameworkApk.setCloseable(archiveFile);
        return frameworkApk;
    }

    public static void optimize(File file, File file2, APKLogger aPKLogger) throws IOException {
        FrameworkApk frameworkApkLoadApkFile = loadApkFile(file);
        frameworkApkLoadApkFile.setAPKLogger(aPKLogger);
        frameworkApkLoadApkFile.optimize();
        frameworkApkLoadApkFile.writeApk(file2);
    }

    public static FrameworkApk loadTableBlock(File file) throws IOException {
        return loadApkFile(file, false);
    }
}
