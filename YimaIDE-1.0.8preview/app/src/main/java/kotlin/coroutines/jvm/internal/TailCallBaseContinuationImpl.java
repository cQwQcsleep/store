package kotlin.coroutines.jvm.internal;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\b\u0001\u0018\u00002\u00020\u0001BI\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\u000e\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\f¢\u0006\u0004\b\r\u0010\u000eJ!\u0010\u0018\u001a\u0004\u0018\u00010\n2\u000e\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u001aH\u0094\u0080\u0004¢\u0006\u0002\u0010\u001bJ\n\u0010\u001c\u001a\u00020\u001dH\u0096\u0080\u0004R\u0015\u0010\u0002\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\u0004\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0015\u0010\u0005\u001a\u00020\u0003X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0015\u0010\u0006\u001a\u00020\u0007X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001f\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0086\u0084\b¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\fX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u001e\u001a\u00020\u001f8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b \u0010!Ê\u0001\u0002\b#¨\u0006\""}, d2 = {"Lkotlin/coroutines/jvm/internal/TailCallBaseContinuationImpl;", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "declaringClass", "", "methodName", "fileName", "lineNumber", "", "spilledVariables", "", "", "continuation", "Lkotlin/coroutines/Continuation;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I[Ljava/lang/Object;Lkotlin/coroutines/Continuation;)V", "getDeclaringClass", "()Ljava/lang/String;", "getMethodName", "getFileName", "getLineNumber", "()I", "getSpilledVariables", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "invokeSuspend", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)Ljava/lang/Object;", "getStackTraceElement", "Ljava/lang/StackTraceElement;", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "kotlin-stdlib", "Lkotlin/PublishedApi;"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class TailCallBaseContinuationImpl extends BaseContinuationImpl {
    private final Continuation<Object> continuation;
    private final String declaringClass;
    private final String fileName;
    private final int lineNumber;
    private final String methodName;
    private final Object[] spilledVariables;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TailCallBaseContinuationImpl(String str, String str2, String str3, int i, Object[] objArr, Continuation<Object> continuation) {
        super(continuation);
        str.getClass();
        str2.getClass();
        str3.getClass();
        objArr.getClass();
        continuation.getClass();
        this.declaringClass = str;
        this.methodName = str2;
        this.fileName = str3;
        this.lineNumber = i;
        this.spilledVariables = objArr;
        this.continuation = continuation;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this.continuation.getContext();
    }

    public final String getDeclaringClass() {
        return this.declaringClass;
    }

    public final String getFileName() {
        return this.fileName;
    }

    public final int getLineNumber() {
        return this.lineNumber;
    }

    public final String getMethodName() {
        return this.methodName;
    }

    public final Object[] getSpilledVariables() {
        return this.spilledVariables;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public StackTraceElement getStackTraceElement() {
        String str;
        String moduleName = ModuleNameRetriever.INSTANCE.getModuleName(this);
        if (moduleName == null) {
            str = this.declaringClass;
        } else {
            str = moduleName + '/' + this.declaringClass;
        }
        return new StackTraceElement(str, this.methodName, this.fileName, this.lineNumber);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public Object invokeSuspend(Object result) throws Throwable {
        ResultKt.throwOnFailure(result);
        return result;
    }
}
