package org.jetbrains.kotlin.ir.backend.js.transformers.irToJs;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/transformers/irToJs/JsIrProgramTestEnvironment;", "", "testFunctionTag", "", "suiteFunctionTag", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getTestFunctionTag", "()Ljava/lang/String;", "getSuiteFunctionTag", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class JsIrProgramTestEnvironment {
    private final String suiteFunctionTag;
    private final String testFunctionTag;

    public JsIrProgramTestEnvironment(String str, String str2) {
        str.getClass();
        str2.getClass();
        this.testFunctionTag = str;
        this.suiteFunctionTag = str2;
    }

    public static /* synthetic */ JsIrProgramTestEnvironment copy$default(JsIrProgramTestEnvironment jsIrProgramTestEnvironment, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = jsIrProgramTestEnvironment.testFunctionTag;
        }
        if ((i & 2) != 0) {
            str2 = jsIrProgramTestEnvironment.suiteFunctionTag;
        }
        return jsIrProgramTestEnvironment.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getTestFunctionTag() {
        return this.testFunctionTag;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSuiteFunctionTag() {
        return this.suiteFunctionTag;
    }

    public final JsIrProgramTestEnvironment copy(String testFunctionTag, String suiteFunctionTag) {
        testFunctionTag.getClass();
        suiteFunctionTag.getClass();
        return new JsIrProgramTestEnvironment(testFunctionTag, suiteFunctionTag);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JsIrProgramTestEnvironment)) {
            return false;
        }
        JsIrProgramTestEnvironment jsIrProgramTestEnvironment = (JsIrProgramTestEnvironment) other;
        return Intrinsics.areEqual(this.testFunctionTag, jsIrProgramTestEnvironment.testFunctionTag) && Intrinsics.areEqual(this.suiteFunctionTag, jsIrProgramTestEnvironment.suiteFunctionTag);
    }

    public final String getSuiteFunctionTag() {
        return this.suiteFunctionTag;
    }

    public final String getTestFunctionTag() {
        return this.testFunctionTag;
    }

    public int hashCode() {
        return (this.testFunctionTag.hashCode() * 31) + this.suiteFunctionTag.hashCode();
    }

    public String toString() {
        return "JsIrProgramTestEnvironment(testFunctionTag=" + this.testFunctionTag + ", suiteFunctionTag=" + this.suiteFunctionTag + ')';
    }
}
