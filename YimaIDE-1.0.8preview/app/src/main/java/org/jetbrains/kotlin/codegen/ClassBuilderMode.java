package org.jetbrains.kotlin.codegen;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public enum ClassBuilderMode {
    FULL(true, false),
    KAPT3(false, true);

    public final boolean generateBodies;
    public final boolean generateSourceRetentionAnnotations;

    ClassBuilderMode(boolean z, boolean z2) {
        this.generateBodies = z;
        this.generateSourceRetentionAnnotations = z2;
    }
}
