package org.jetbrains.kotlin.wasm.ir;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/JsBuiltinDescriptor;", "", "moduleName", "", "declarationName", "polyfillImpl", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getModuleName", "()Ljava/lang/String;", "getDeclarationName", "getPolyfillImpl", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class JsBuiltinDescriptor {
    private final String declarationName;
    private final String moduleName;
    private final String polyfillImpl;

    public JsBuiltinDescriptor(String str, String str2, String str3) {
        str.getClass();
        str2.getClass();
        str3.getClass();
        this.moduleName = str;
        this.declarationName = str2;
        this.polyfillImpl = str3;
    }

    public static /* synthetic */ JsBuiltinDescriptor copy$default(JsBuiltinDescriptor jsBuiltinDescriptor, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = jsBuiltinDescriptor.moduleName;
        }
        if ((i & 2) != 0) {
            str2 = jsBuiltinDescriptor.declarationName;
        }
        if ((i & 4) != 0) {
            str3 = jsBuiltinDescriptor.polyfillImpl;
        }
        return jsBuiltinDescriptor.copy(str, str2, str3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getModuleName() {
        return this.moduleName;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getDeclarationName() {
        return this.declarationName;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getPolyfillImpl() {
        return this.polyfillImpl;
    }

    public final JsBuiltinDescriptor copy(String moduleName, String declarationName, String polyfillImpl) {
        moduleName.getClass();
        declarationName.getClass();
        polyfillImpl.getClass();
        return new JsBuiltinDescriptor(moduleName, declarationName, polyfillImpl);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JsBuiltinDescriptor)) {
            return false;
        }
        JsBuiltinDescriptor jsBuiltinDescriptor = (JsBuiltinDescriptor) other;
        return Intrinsics.areEqual(this.moduleName, jsBuiltinDescriptor.moduleName) && Intrinsics.areEqual(this.declarationName, jsBuiltinDescriptor.declarationName) && Intrinsics.areEqual(this.polyfillImpl, jsBuiltinDescriptor.polyfillImpl);
    }

    public final String getDeclarationName() {
        return this.declarationName;
    }

    public final String getModuleName() {
        return this.moduleName;
    }

    public final String getPolyfillImpl() {
        return this.polyfillImpl;
    }

    public int hashCode() {
        return (((this.moduleName.hashCode() * 31) + this.declarationName.hashCode()) * 31) + this.polyfillImpl.hashCode();
    }

    public String toString() {
        return "JsBuiltinDescriptor(moduleName=" + this.moduleName + ", declarationName=" + this.declarationName + ", polyfillImpl=" + this.polyfillImpl + Util.C_PARAM_END;
    }
}
