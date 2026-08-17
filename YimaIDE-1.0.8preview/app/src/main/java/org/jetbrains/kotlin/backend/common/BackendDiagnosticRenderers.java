package org.jetbrains.kotlin.backend.common;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.BackendDiagnosticRenderers;
import org.jetbrains.kotlin.diagnostics.rendering.ContextIndependentParameterRenderer;
import org.jetbrains.kotlin.diagnostics.rendering.DiagnosticParameterRendererKt;
import org.jetbrains.kotlin.library.abi.AbiCompoundName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/BackendDiagnosticRenderers;", "", "<init>", "()V", "EVALUATION_ERROR_EXPLANATION", "Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "", "getEVALUATION_ERROR_EXPLANATION", "()Lorg/jetbrains/kotlin/diagnostics/rendering/ContextIndependentParameterRenderer;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class BackendDiagnosticRenderers {
    public static final BackendDiagnosticRenderers INSTANCE = new BackendDiagnosticRenderers();
    private static final ContextIndependentParameterRenderer<String> EVALUATION_ERROR_EXPLANATION = DiagnosticParameterRendererKt.Renderer(new Function1() { // from class: yl0
        public final Object invoke(Object obj) {
            return BackendDiagnosticRenderers.a((String) obj);
        }
    });

    private BackendDiagnosticRenderers() {
    }

    public static String a(String str) {
        str.getClass();
        MatchResult matchResultMatchEntire = new Regex("Exception (\\S+)(: (.*))?").matchEntire(str);
        List groupValues = matchResultMatchEntire != null ? matchResultMatchEntire.getGroupValues() : null;
        String str2 = groupValues != null ? (String) CollectionsKt.getOrNull(groupValues, 1) : null;
        String str3 = groupValues != null ? (String) CollectionsKt.getOrNull(groupValues, 3) : null;
        if (str3 != null && !StringsKt.isBlank(str3)) {
            return str3;
        }
        if (Intrinsics.areEqual(str2, StackOverflowError.class.getName())) {
            return "stack overflow (potentially due to infinite recursion)";
        }
        if (Intrinsics.areEqual(str2, NullPointerException.class.getName())) {
            return "null reference access";
        }
        return str2 != null ? (String) CollectionsKt.last(StringsKt.split$default(str2, new char[]{AbiCompoundName.SEPARATOR}, false, 0, 6, (Object) null)) : str;
    }

    public final ContextIndependentParameterRenderer<String> getEVALUATION_ERROR_EXPLANATION() {
        return EVALUATION_ERROR_EXPLANATION;
    }
}
