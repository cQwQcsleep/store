package org.jetbrains.kotlin.fileClasses;

import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B#\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fileClasses/ParsedJvmFileClassAnnotations;", "", "jvmName", "", "jvmPackageName", "Lorg/jetbrains/kotlin/name/FqName;", "isMultifileClass", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Lorg/jetbrains/kotlin/name/FqName;Z)V", "getJvmName", "()Ljava/lang/String;", "getJvmPackageName", "()Lorg/jetbrains/kotlin/name/FqName;", "()Z", "org.jetbrains.kotlin:frontend.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ParsedJvmFileClassAnnotations {
    private final boolean isMultifileClass;
    private final String jvmName;
    private final FqName jvmPackageName;

    public ParsedJvmFileClassAnnotations(String str, FqName fqName, boolean z) {
        this.jvmName = str;
        this.jvmPackageName = fqName;
        this.isMultifileClass = z;
    }

    public final String getJvmName() {
        return this.jvmName;
    }

    public final FqName getJvmPackageName() {
        return this.jvmPackageName;
    }

    /* JADX INFO: renamed from: isMultifileClass, reason: from getter */
    public final boolean getIsMultifileClass() {
        return this.isMultifileClass;
    }
}
