package androidx.compose.runtime.tooling;

import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MovableContentKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.ArrayList;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004H\u0000\u001a\u001c\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004H\u0000\u001a\u0018\u0010\u0007\u001a\u00020\b*\u00060\tj\u0002`\n2\u0006\u0010\u0003\u001a\u00020\u0005H\u0000\u001a\u0018\u0010\u000b\u001a\u00020\b*\u00060\tj\u0002`\n2\u0006\u0010\u0003\u001a\u00020\u0005H\u0000\u001a\u0012\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\u00020\u0005H\u0000\u001a\u0018\u0010\u000f\u001a\u00020\b*\u00060\tj\u0002`\n2\u0006\u0010\u0003\u001a\u00020\u0005H\u0000\"\u000e\u0010\u0010\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0012\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"tryAttachComposeStackTrace", "", "", "trace", "Lkotlin/Function0;", "Landroidx/compose/runtime/tooling/ComposeStackTrace;", "attachComposeStackTrace", "appendStackTrace", "", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "appendSourceInformationStackTrace", "filterInternalFramesByGroupKey", "", "Landroidx/compose/runtime/tooling/ComposeStackTraceFrame;", "appendGroupKeyStackTrace", "RuntimePackageHash", "", "IncludeDebugInfo", "runtime"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ComposeStackTraceKt {
    private static final boolean IncludeDebugInfo = false;
    private static final String RuntimePackageHash = "9igjgp";

    public static final void appendGroupKeyStackTrace(StringBuilder sb, ComposeStackTrace composeStackTrace) {
        List<ComposeStackTraceFrame> listFilterInternalFramesByGroupKey = filterInternalFramesByGroupKey(composeStackTrace);
        int size = listFilterInternalFramesByGroupKey.size();
        for (int i = 0; i < size; i++) {
            ComposeStackTraceFrame composeStackTraceFrame = listFilterInternalFramesByGroupKey.get(i);
            sb.append("\tat $$compose.m$");
            sb.append(composeStackTraceFrame.getGroupKey());
            sb.append("(SourceFile:1)");
            sb.append('\n');
        }
    }

    /* JADX WARN: Code duplicated, block: B:15:0x003d A[PHI: r9
      0x003d: PHI (r9v1 java.lang.String) = (r9v0 java.lang.String), (r9v13 java.lang.String) binds: [B:7:0x002a, B:12:0x0036] A[DONT_GENERATE, DONT_INLINE]] */
    public static final void appendSourceInformationStackTrace(StringBuilder sb, ComposeStackTrace composeStackTrace) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List listAsReversed = CollectionsKt.asReversed(composeStackTrace.getFrames());
        int size = listAsReversed.size();
        String str = null;
        String str2 = null;
        for (int i = 0; i < size; i++) {
            ComposeStackTraceFrame composeStackTraceFrame = (ComposeStackTraceFrame) listAsReversed.get(i);
            SourceInformation sourceInfo = composeStackTraceFrame.getSourceInfo();
            if (sourceInfo != null) {
                String functionName = sourceInfo.getFunctionName();
                if (functionName != null) {
                    str = functionName;
                } else {
                    functionName = sourceInfo.getIsCall() ? "<lambda>" : null;
                    if (functionName != null) {
                        str = functionName;
                    } else if (str == null) {
                        str = "<unknown function>";
                    }
                }
                String sourceFile = sourceInfo.getSourceFile();
                if (sourceFile != null) {
                    str2 = sourceFile;
                } else if (str2 == null) {
                    str2 = "<unknown file>";
                }
                List<LocationSourceInformation> locations = sourceInfo.getLocations();
                String str3 = str + '(' + str2 + ':' + ((composeStackTraceFrame.getGroupOffset() == null || composeStackTraceFrame.getGroupOffset().intValue() >= locations.size()) ? "<unknown line>" : String.valueOf(locations.get(composeStackTraceFrame.getGroupOffset().intValue()).getLineNumber())) + ')';
                if (!sourceInfo.getIsCall()) {
                }
                if (!Intrinsics.areEqual(sourceInfo.getFunctionName(), "rememberCompositionContext") || !Intrinsics.areEqual(sourceInfo.getPackageHash(), RuntimePackageHash)) {
                    listCreateListBuilder.add(str3);
                }
            }
        }
        List listAsReversed2 = CollectionsKt.asReversed(CollectionsKt.build(listCreateListBuilder));
        int size2 = listAsReversed2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            String str4 = (String) listAsReversed2.get(i2);
            sb.append("\tat ");
            sb.append(str4);
            sb.append('\n');
        }
    }

    public static final void appendStackTrace(StringBuilder sb, ComposeStackTrace composeStackTrace) {
        if (composeStackTrace.getHasSourceInformation()) {
            appendSourceInformationStackTrace(sb, composeStackTrace);
        } else {
            appendGroupKeyStackTrace(sb, composeStackTrace);
        }
    }

    public static final Throwable attachComposeStackTrace(Throwable th, Function0<ComposeStackTrace> function0) {
        tryAttachComposeStackTrace(th, function0);
        return th;
    }

    public static final List<ComposeStackTraceFrame> filterInternalFramesByGroupKey(ComposeStackTrace composeStackTrace) {
        int[] iArr = {ComposerKt.providerKey, ComposerKt.compositionLocalMapKey, ComposerKt.providerMapsKey, ComposerKt.referenceKey, ComposerKt.reuseKey, 125, ComposerKt.defaultsKey, MovableContentKt.movableContentKey, ComposerKt.invocationKey};
        int size = composeStackTrace.getFrames().size();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (i < size) {
            int i2 = i + 1;
            ComposeStackTraceFrame composeStackTraceFrame = composeStackTrace.getFrames().get(i);
            if (!ArraysKt.contains(iArr, composeStackTraceFrame.getGroupKey())) {
                if (composeStackTraceFrame.getGroupKey() == 100) {
                    int i3 = i + 2;
                    if (i3 < size && composeStackTrace.getFrames().get(i3).getGroupKey() == 1000) {
                        break;
                    }
                    CollectionsKt.removeLastOrNull(arrayList);
                } else {
                    arrayList.add(composeStackTraceFrame);
                }
            }
            i = i2;
        }
        return arrayList;
    }

    public static final boolean tryAttachComposeStackTrace(Throwable th, Function0<ComposeStackTrace> function0) {
        DiagnosticComposeException diagnosticComposeException;
        List suppressedExceptions = ExceptionsKt.getSuppressedExceptions(th);
        int size = suppressedExceptions.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            if (((Throwable) suppressedExceptions.get(i)) instanceof DiagnosticComposeException) {
                return false;
            }
        }
        try {
            ComposeStackTrace composeStackTrace = (ComposeStackTrace) function0.invoke();
            if (composeStackTrace != null && !composeStackTrace.getFrames().isEmpty()) {
                z = true;
            }
            if (z) {
                composeStackTrace.getClass();
                diagnosticComposeException = new DiagnosticComposeException(composeStackTrace);
            } else {
                diagnosticComposeException = null;
            }
        } catch (Throwable th2) {
            diagnosticComposeException = th2;
        }
        if (diagnosticComposeException != null) {
            ExceptionsKt.addSuppressed(th, diagnosticComposeException);
        }
        return z;
    }
}
