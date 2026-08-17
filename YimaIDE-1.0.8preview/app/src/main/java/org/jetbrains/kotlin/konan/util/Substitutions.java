package org.jetbrains.kotlin.konan.util;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001c\u0010\f\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/Substitutions;", "", "()V", "archValue", "", "getArchValue", "()Ljava/lang/String;", "setArchValue", "(Ljava/lang/String;)V", "familyValue", "getFamilyValue", "setFamilyValue", "targetValue", "getTargetValue", "setTargetValue", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class Substitutions {
    private String archValue;
    private String familyValue;
    private String targetValue;

    public final String getArchValue() {
        return this.archValue;
    }

    public final String getFamilyValue() {
        return this.familyValue;
    }

    public final String getTargetValue() {
        return this.targetValue;
    }

    public final void setArchValue(String str) {
        this.archValue = str;
    }

    public final void setFamilyValue(String str) {
        this.familyValue = str;
    }

    public final void setTargetValue(String str) {
        this.targetValue = str;
    }
}
