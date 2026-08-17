package com.reandroid.graph.cleaners;

import com.reandroid.apk.ApkModule;
import com.reandroid.dex.model.Dex;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.graph.ApkBuildOption;
import com.reandroid.graph.BaseApkModuleProcessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class UnusedCleaner<T extends Dex> extends BaseApkModuleProcessor {
    private final ApkBuildOption buildOption;
    private int mCount;

    public UnusedCleaner(ApkBuildOption apkBuildOption, ApkModule apkModule, DexClassRepository dexClassRepository) {
        super(apkModule, dexClassRepository);
        this.buildOption = apkBuildOption;
    }

    public void addCount() {
        this.mCount++;
    }

    public ApkBuildOption getBuildOption() {
        return this.buildOption;
    }

    public int getCount() {
        return this.mCount;
    }

    public abstract boolean isEnabled();

    public void setCount(int i) {
        this.mCount = i;
    }
}
