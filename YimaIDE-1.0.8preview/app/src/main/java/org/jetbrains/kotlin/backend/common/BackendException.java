package org.jetbrains.kotlin.backend.common;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.backend.common.BackendException;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.util.SourceCodeAnalysisException;
import org.jetbrains.kotlin.utils.KotlinExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.ExceptionUtilKt;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u0000 \t2\u00060\u0001j\u0002`\u0002:\u0001\tB\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/BackendException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "message", "", "cause", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;Ljava/lang/Throwable;)V", "Companion", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class BackendException extends IllegalStateException {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BackendException(String str, Throwable th) {
        super(str, th);
        str.getClass();
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JP\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\t2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\t2\"\b\u0002\u0010\f\u001a\u001c\u0012\u0004\u0012\u00020\u000e\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u000f0\r¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/BackendException$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "report", "", "exception", "", "phase", "", "location", "additionalMessage", "linesMapping", "Lkotlin/Function1;", "", "Lkotlin/Pair;", "org.jetbrains.kotlin:ir.backend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static Pair a(int i) {
            return null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Void report$default(Companion companion, Throwable th, String str, String str2, String str3, Function1 function1, int i, Object obj) {
            if ((i & 8) != 0) {
                str3 = null;
            }
            String str4 = str3;
            if ((i & 16) != 0) {
                function1 = new Function1() { // from class: am0
                    public final Object invoke(Object obj2) {
                        return BackendException.Companion.a(((Integer) obj2).intValue());
                    }
                };
            }
            return companion.report(th, str, str2, str4, function1);
        }

        public final Void report(Throwable exception, String phase, String location, String additionalMessage, Function1<? super Integer, Pair<Integer, Integer>> linesMapping) throws Throwable {
            Pair pair;
            exception.getClass();
            phase.getClass();
            linesMapping.getClass();
            if (exception instanceof KotlinExceptionWithAttachments) {
                throw exception;
            }
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(exception);
            if (location != null) {
                SourceCodeAnalysisException sourceCodeAnalysisException = exception instanceof SourceCodeAnalysisException ? (SourceCodeAnalysisException) exception : null;
                if (sourceCodeAnalysisException != null && (pair = (Pair) linesMapping.invoke(Integer.valueOf(sourceCodeAnalysisException.getSource().getStartOffset()))) != null) {
                    location = location + ':' + (((Number) pair.component1()).intValue() + 1) + ':' + (((Number) pair.component2()).intValue() + 1);
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(ExceptionUtilKt.getExceptionMessage("Backend", "Exception during " + phase, exception, location));
            String strConcat = additionalMessage != null ? "\n".concat(additionalMessage) : null;
            if (strConcat == null) {
                strConcat = "";
            }
            sb.append(strConcat);
            throw new BackendException(sb.toString(), exception);
        }

        private Companion() {
        }
    }
}
