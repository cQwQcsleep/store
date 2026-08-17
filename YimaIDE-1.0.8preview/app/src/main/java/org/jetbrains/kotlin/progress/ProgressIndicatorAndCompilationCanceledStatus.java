package org.jetbrains.kotlin.progress;

import com.intellij.openapi.progress.ProgressIndicatorProvider;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.jetbrains.kotlin.progress.ProgressIndicatorAndCompilationCanceledStatus;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\u0007J\b\u0010\n\u001a\u00020\bH\u0007R\u0016\u0010\u0004\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/progress/ProgressIndicatorAndCompilationCanceledStatus;", "", "<init>", "()V", "canceledStatus", "Ljava/lang/ThreadLocal;", "Lorg/jetbrains/kotlin/progress/CompilationCanceledStatus;", "setCompilationCanceledStatus", "", "newCanceledStatus", "checkCanceled", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ProgressIndicatorAndCompilationCanceledStatus {
    public static final ProgressIndicatorAndCompilationCanceledStatus INSTANCE = new ProgressIndicatorAndCompilationCanceledStatus();
    private static ThreadLocal<CompilationCanceledStatus> canceledStatus;

    static {
        ThreadLocal<CompilationCanceledStatus> threadLocalWithInitial = ThreadLocal.withInitial(new Supplier() { // from class: qcb
            @Override // java.util.function.Supplier
            public final Object get() {
                return ProgressIndicatorAndCompilationCanceledStatus.a();
            }
        });
        threadLocalWithInitial.getClass();
        canceledStatus = threadLocalWithInitial;
    }

    private ProgressIndicatorAndCompilationCanceledStatus() {
    }

    public static CompilationCanceledStatus a() {
        return null;
    }

    @JvmStatic
    public static final void checkCanceled() {
        ProgressIndicatorProvider.checkCanceled();
        CompilationCanceledStatus compilationCanceledStatus = canceledStatus.get();
        if (compilationCanceledStatus != null) {
            compilationCanceledStatus.checkCanceled();
        }
    }

    @JvmStatic
    public static final void setCompilationCanceledStatus(CompilationCanceledStatus newCanceledStatus) {
        canceledStatus.set(newCanceledStatus);
    }
}
