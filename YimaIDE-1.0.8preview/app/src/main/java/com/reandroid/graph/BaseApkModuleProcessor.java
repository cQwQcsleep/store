package com.reandroid.graph;

import com.reandroid.apk.ApkModule;
import com.reandroid.archive.ZipEntryMap;
import com.reandroid.dex.model.DexClassRepository;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class BaseApkModuleProcessor extends BaseDexClassProcessor {
    private final ApkModule apkModule;

    public BaseApkModuleProcessor(ApkModule apkModule, DexClassRepository dexClassRepository) {
        super(dexClassRepository);
        this.apkModule = apkModule;
    }

    public ApkModule getApkModule() {
        return this.apkModule;
    }

    public ZipEntryMap getZipEntryMap() {
        return getApkModule().getZipEntryMap();
    }
}
