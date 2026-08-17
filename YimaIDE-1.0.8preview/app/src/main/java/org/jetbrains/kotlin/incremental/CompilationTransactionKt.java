package org.jetbrains.kotlin.incremental;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.text.Charsets;
import org.jetbrains.kotlin.incremental.CompilationTransactionKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\u001a \u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006\u001a\u001a\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t\u001a\u001a\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f\u001aE\u0010\r\u001a\u0002H\u000e\"\u0004\b\u0000\u0010\u000e*\u00020\u00022\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u0002H\u000e0\u00102\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u0002H\u000e0\u0010H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0013\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, d2 = {"write", "", "Lorg/jetbrains/kotlin/incremental/CompilationTransaction;", "file", "Ljava/nio/file/Path;", "writeAction", "Lkotlin/Function0;", "writeText", "text", "", "writeBytes", "array", "", "runWithin", "R", "exceptionTransformer", "Lkotlin/Function1;", "", "body", "(Lorg/jetbrains/kotlin/incremental/CompilationTransaction;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "org.jetbrains.kotlin:kotlin-build-common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class CompilationTransactionKt {
    public static Unit a(Path path, byte[] bArr) throws IOException {
        if (!Files.exists(path.getParent(), new LinkOption[0])) {
            Files.createDirectories(path.getParent(), new FileAttribute[0]);
        }
        Files.write(path, bArr, new OpenOption[0]);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:24:0x0036 A[Catch: all -> 0x001e, TryCatch #5 {all -> 0x001e, blocks: (B:3:0x0009, B:6:0x0010, B:22:0x0031, B:24:0x0036, B:25:0x0039, B:26:0x003c, B:5:0x000c), top: B:40:0x0009, inners: #1 }] */
    public static final <R> R runWithin(CompilationTransaction compilationTransaction, Function1<? super Throwable, ? extends R> function1, Function1<? super CompilationTransaction, ? extends R> function2) {
        Object obj;
        compilationTransaction.getClass();
        function1.getClass();
        function2.getClass();
        try {
            Result.Companion companion = Result.Companion;
            try {
                Object objInvoke = function2.invoke(compilationTransaction);
                InlineMarker.finallyStart(1);
                hv3.a(compilationTransaction);
                InlineMarker.finallyEnd(1);
                obj = (R) Result.constructor-impl(objInvoke);
            } catch (Throwable th) {
                boolean z = false;
                try {
                    compilationTransaction.setExecutionThrowable(th);
                    throw th;
                } catch (Exception e) {
                    try {
                        try {
                            hv3.a(compilationTransaction);
                        } catch (Exception unused) {
                        }
                        throw e;
                    } catch (Throwable th2) {
                        th = th2;
                        z = true;
                        InlineMarker.finallyStart(1);
                        if (!z) {
                            hv3.a(compilationTransaction);
                        }
                        InlineMarker.finallyEnd(1);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    InlineMarker.finallyStart(1);
                    if (!z) {
                        hv3.a(compilationTransaction);
                    }
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            }
        } catch (Throwable th4) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th4));
        }
        Throwable th5 = Result.exceptionOrNull-impl(obj);
        if (th5 != null) {
            obj = (R) Result.constructor-impl(function1.invoke(th5));
        }
        ResultKt.throwOnFailure(obj);
        return (R) obj;
    }

    /* JADX WARN: Code duplicated, block: B:26:0x003b A[Catch: all -> 0x0023, TryCatch #2 {all -> 0x0023, blocks: (B:6:0x000f, B:8:0x0015, B:24:0x0036, B:26:0x003b, B:27:0x003e, B:28:0x0041, B:7:0x0011), top: B:39:0x000f, inners: #3 }] */
    public static /* synthetic */ Object runWithin$default(CompilationTransaction compilationTransaction, Function1 function1, Function1 function2, int i, Object obj) {
        Object obj2;
        if ((i & 1) != 0) {
            function1 = new Function1() { // from class: org.jetbrains.kotlin.incremental.CompilationTransactionKt.runWithin.1
                public final Void invoke(Throwable th) throws Throwable {
                    th.getClass();
                    throw th;
                }
            };
        }
        compilationTransaction.getClass();
        function1.getClass();
        function2.getClass();
        try {
            Result.Companion companion = Result.Companion;
            try {
                Object objInvoke = function2.invoke(compilationTransaction);
                InlineMarker.finallyStart(1);
                hv3.a(compilationTransaction);
                InlineMarker.finallyEnd(1);
                obj2 = Result.constructor-impl(objInvoke);
            } catch (Throwable th) {
                boolean z = false;
                try {
                    compilationTransaction.setExecutionThrowable(th);
                    throw th;
                } catch (Exception e) {
                    try {
                        try {
                            hv3.a(compilationTransaction);
                        } catch (Throwable th2) {
                            th = th2;
                            z = true;
                            InlineMarker.finallyStart(1);
                            if (!z) {
                                hv3.a(compilationTransaction);
                            }
                            InlineMarker.finallyEnd(1);
                            throw th;
                        }
                    } catch (Exception unused) {
                    }
                    throw e;
                } catch (Throwable th3) {
                    th = th3;
                    InlineMarker.finallyStart(1);
                    if (!z) {
                        hv3.a(compilationTransaction);
                    }
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            }
        } catch (Throwable th4) {
            Result.Companion companion2 = Result.Companion;
            obj2 = Result.constructor-impl(ResultKt.createFailure(th4));
        }
        Throwable th5 = Result.exceptionOrNull-impl(obj2);
        if (th5 != null) {
            obj2 = Result.constructor-impl(function1.invoke(th5));
        }
        ResultKt.throwOnFailure(obj2);
        return obj2;
    }

    public static final void write(CompilationTransaction compilationTransaction, Path path, Function0<Unit> function0) {
        compilationTransaction.getClass();
        path.getClass();
        function0.getClass();
        compilationTransaction.registerAddedOrChangedFile(path);
        function0.invoke();
    }

    public static final void writeBytes(CompilationTransaction compilationTransaction, final Path path, final byte[] bArr) {
        compilationTransaction.getClass();
        path.getClass();
        bArr.getClass();
        write(compilationTransaction, path, new Function0() { // from class: n92
            public final Object invoke() {
                return CompilationTransactionKt.a(path, bArr);
            }
        });
    }

    public static final void writeText(CompilationTransaction compilationTransaction, Path path, String str) {
        compilationTransaction.getClass();
        path.getClass();
        str.getClass();
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        bytes.getClass();
        writeBytes(compilationTransaction, path, bytes);
    }
}
