package org.jetbrains.kotlin.backend.wasm.ir2wasm;

import java.util.Base64;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\u000e\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0003HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\b¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/JsModuleAndQualifierReference;", "", "module", "", "qualifier", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Ljava/lang/String;)V", "getModule", "()Ljava/lang/String;", "getQualifier", "moduleBase64", "qualifierBase64", "jsReference", "getJsReference", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class JsModuleAndQualifierReference {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
    private final String jsReference;
    private final String module;
    private final String moduleBase64;
    private final String qualifier;
    private final String qualifierBase64;

    public JsModuleAndQualifierReference(String str, String str2) {
        String strValueOf;
        this.module = str;
        this.qualifier = str2;
        String strEncode = str != null ? INSTANCE.encode(str) : null;
        strEncode = strEncode == null ? "" : strEncode;
        this.moduleBase64 = strEncode;
        String strEncode2 = str2 != null ? INSTANCE.encode(str2) : null;
        String str3 = strEncode2 != null ? strEncode2 : "";
        this.qualifierBase64 = str3;
        if (str != null) {
            strValueOf = "_ref_" + strEncode + '_' + str3;
        } else {
            strValueOf = String.valueOf(str2);
        }
        this.jsReference = strValueOf;
    }

    public static /* synthetic */ JsModuleAndQualifierReference copy$default(JsModuleAndQualifierReference jsModuleAndQualifierReference, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = jsModuleAndQualifierReference.module;
        }
        if ((i & 2) != 0) {
            str2 = jsModuleAndQualifierReference.qualifier;
        }
        return jsModuleAndQualifierReference.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getModule() {
        return this.module;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getQualifier() {
        return this.qualifier;
    }

    public final JsModuleAndQualifierReference copy(String module, String qualifier) {
        return new JsModuleAndQualifierReference(module, qualifier);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof JsModuleAndQualifierReference)) {
            return false;
        }
        JsModuleAndQualifierReference jsModuleAndQualifierReference = (JsModuleAndQualifierReference) other;
        return Intrinsics.areEqual(this.module, jsModuleAndQualifierReference.module) && Intrinsics.areEqual(this.qualifier, jsModuleAndQualifierReference.qualifier);
    }

    public final String getJsReference() {
        return this.jsReference;
    }

    public final String getModule() {
        return this.module;
    }

    public final String getQualifier() {
        return this.qualifier;
    }

    public int hashCode() {
        String str = this.module;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.qualifier;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "JsModuleAndQualifierReference(module=" + this.module + ", qualifier=" + this.qualifier + Util.C_PARAM_END;
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bR\u0016\u0010\u0004\u001a\n \u0006*\u0004\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/ir2wasm/JsModuleAndQualifierReference$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "encoder", "Ljava/util/Base64$Encoder;", "kotlin.jvm.PlatformType", "encode", "", "value", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String encode(String value) {
            value.getClass();
            String strEncodeToString = JsModuleAndQualifierReference.encoder.encodeToString(StringsKt.encodeToByteArray(value));
            strEncodeToString.getClass();
            return StringsKt.replace$default(strEncodeToString, Util.C_SUPER, '_', false, 4, (Object) null);
        }

        private Companion() {
        }
    }
}
