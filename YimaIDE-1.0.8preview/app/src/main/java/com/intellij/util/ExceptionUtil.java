package com.intellij.util;

import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import com.intellij.util.ExceptionUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ExceptionUtil {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 6 || i == 9 || i == 11 || i == 14 || i == 21 || i == 24 || i == 33 || i == 16 || i == 17) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 6 || i == 9 || i == 11 || i == 14 || i == 21 || i == 24 || i == 33 || i == 16 || i == 17) ? 2 : 3];
        switch (i) {
            case 1:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 9:
            case 11:
            case 14:
            case 16:
            case 17:
            case 21:
            case 24:
            case 33:
                objArr[0] = "com/intellij/util/ExceptionUtil";
                break;
            case 2:
            case 4:
                objArr[0] = "error";
                break;
            case 3:
            case 5:
                objArr[0] = "klass";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "th";
                break;
            case 8:
                objArr[0] = "relativeTo";
                break;
            case 10:
            case 22:
                objArr[0] = "t";
                break;
            case 12:
            case 15:
                objArr[0] = "aThrowable";
                break;
            case 13:
                objArr[0] = "stackFrameSkipPattern";
                break;
            case 18:
            default:
                objArr[0] = "e";
                break;
            case 19:
                objArr[0] = "result";
                break;
            case 20:
                objArr[0] = "errorPattern";
                break;
            case 23:
                objArr[0] = "defaultMessage";
                break;
            case 25:
                objArr[0] = "runnable";
                break;
            case 26:
                objArr[0] = "exampleClass";
                break;
            case 27:
                objArr[0] = "exampleSupplier";
                break;
            case 28:
            case 30:
                objArr[0] = "potentiallyFailingTasks";
                break;
            case 29:
                objArr[0] = "exceptionsCombiner";
                break;
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                objArr[0] = "throwable";
                break;
            case 32:
                objArr[0] = "classToUnwrap";
                break;
        }
        if (i == 1) {
            objArr[1] = "getRootCause";
        } else if (i == 6) {
            objArr[1] = "causeAndSuppressed";
        } else if (i == 9) {
            objArr[1] = "makeStackTraceRelative";
        } else if (i == 11 || i == 14) {
            objArr[1] = "getThrowableText";
        } else if (i == 21) {
            objArr[1] = "extractMessage";
        } else if (i == 24) {
            objArr[1] = "getNonEmptyMessage";
        } else if (i == 33) {
            objArr[1] = "unwrapException";
        } else if (i == 16 || i == 17) {
            objArr[1] = "getUserStackTrace";
        } else {
            objArr[1] = "com/intellij/util/ExceptionUtil";
        }
        switch (i) {
            case 1:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 9:
            case 11:
            case 14:
            case 16:
            case 17:
            case 21:
            case 24:
            case 33:
                break;
            case 2:
            case 3:
                objArr[2] = "findCauseAndSuppressed";
                break;
            case 4:
            case 5:
                objArr[2] = "causeAndSuppressed";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[2] = "makeStackTraceRelative";
                break;
            case 10:
            case 12:
            case 13:
                objArr[2] = "getThrowableText";
                break;
            case 15:
                objArr[2] = "getUserStackTrace";
                break;
            case 18:
                objArr[2] = "getMessage";
                break;
            case 19:
            case 20:
                objArr[2] = "extractMessage";
                break;
            case 22:
            case 23:
                objArr[2] = "getNonEmptyMessage";
                break;
            case 25:
                objArr[2] = "runAndCatch";
                break;
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
                objArr[2] = "runAllAndRethrowAllExceptions";
                break;
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 32:
                objArr[2] = "unwrapException";
                break;
            default:
                objArr[2] = "getRootCause";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 6 && i != 9 && i != 11 && i != 14 && i != 21 && i != 24 && i != 33 && i != 16 && i != 17) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static /* synthetic */ Exception a(Class cls, Supplier supplier, List list) {
        Iterator it = list.iterator();
        Exception exc = null;
        while (it.hasNext()) {
            Throwable th = (Throwable) it.next();
            if (exc != null) {
                exc.addSuppressed(th);
            } else if (cls.isAssignableFrom(th.getClass())) {
                exc = (Exception) th;
            } else {
                exc = (Exception) supplier.get();
                exc.addSuppressed(th);
            }
        }
        return exc;
    }

    public static <T> Stream<T> causeAndSuppressed(Throwable th, final Class<T> cls) {
        if (th == null) {
            $$$reportNull$$$0(4);
        }
        if (cls == null) {
            $$$reportNull$$$0(5);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.add(th);
        while (!arrayDeque.isEmpty()) {
            Throwable th2 = (Throwable) arrayDeque.removeFirst();
            if (linkedHashSet.add(th2)) {
                for (Throwable cause = th2.getCause(); cause != null; cause = cause.getCause()) {
                    arrayDeque.addLast(cause);
                }
                for (Throwable th3 : th2.getSuppressed()) {
                    arrayDeque.addLast(th3);
                }
            }
        }
        Stream stream = linkedHashSet.stream();
        Objects.requireNonNull(cls);
        Stream<T> stream2 = (Stream<T>) stream.filter(new Predicate() { // from class: ld4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return cls.isInstance((Throwable) obj);
            }
        }).map(new java.util.function.Function() { // from class: nd4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return cls.cast((Throwable) obj);
            }
        });
        if (stream2 == null) {
            $$$reportNull$$$0(6);
        }
        return stream2;
    }

    public static String currentStackTrace() {
        return getThrowableText(new Throwable());
    }

    private static String extractMessage(String str, String str2) {
        if (str == null) {
            $$$reportNull$$$0(19);
        }
        if (str2 == null) {
            $$$reportNull$$$0(20);
        }
        return str.lastIndexOf(str2) >= 0 ? str.substring(str.lastIndexOf(str2) + str2.length()) : str;
    }

    public static String getMessage(Throwable th) {
        if (th == null) {
            $$$reportNull$$$0(18);
        }
        String message = th.getMessage();
        while (th.getCause() != null && (message == null || message.contains("Exception: ") || message.contains("Error: "))) {
            th = th.getCause();
            message = th.getMessage();
        }
        return message != null ? extractMessage(extractMessage(message, "Exception: "), "Error: ") : message;
    }

    public static Throwable getRootCause(Throwable th) {
        if (th == null) {
            $$$reportNull$$$0(0);
        }
        while (th.getCause() != null) {
            th = th.getCause();
        }
        return th;
    }

    public static String getThrowableText(Throwable th) {
        if (th == null) {
            $$$reportNull$$$0(10);
        }
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String string = stringWriter.getBuffer().toString();
        if (string == null) {
            $$$reportNull$$$0(11);
        }
        return string;
    }

    public static Throwable makeStackTraceRelative(Throwable th, Throwable th2) {
        if (th == null) {
            $$$reportNull$$$0(7);
        }
        if (th2 == null) {
            $$$reportNull$$$0(8);
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        StackTraceElement[] stackTrace2 = th2.getStackTrace();
        int iMin = Math.min(stackTrace.length, stackTrace2.length);
        for (int i = 0; i < iMin; i++) {
            if (!stackTrace[(stackTrace.length - i) - 1].equals(stackTrace2[(stackTrace2.length - i) - 1])) {
                th.setStackTrace((StackTraceElement[]) Arrays.copyOf(stackTrace, stackTrace.length - i));
                break;
            }
        }
        return th;
    }

    public static void rethrow(Throwable th) throws Error, RuntimeException {
        rethrowUnchecked(th);
        throw new RuntimeException(th);
    }

    public static void rethrowAllAsUnchecked(Throwable th) throws Error, RuntimeException {
        if (th != null) {
            rethrow(th);
        }
    }

    public static void rethrowUnchecked(Throwable th) throws Error, RuntimeException {
        ExceptionUtilRt.rethrowUnchecked(th);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: E extends java.lang.Exception */
    @SafeVarargs
    public static <E extends Exception> void runAllAndRethrowAllExceptions(java.util.function.Function<List<? extends Throwable>, E> function, ThrowableRunnable<? extends Exception>... throwableRunnableArr) throws Exception {
        if (function == null) {
            $$$reportNull$$$0(29);
        }
        if (throwableRunnableArr == null) {
            $$$reportNull$$$0(30);
        }
        ArrayList arrayList = null;
        for (ThrowableRunnable<? extends Exception> throwableRunnable : throwableRunnableArr) {
            try {
                throwableRunnable.run();
            } catch (Throwable th) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(th);
            }
        }
        if (arrayList != null) {
            throw function.apply(arrayList);
        }
    }

    public static Exception runAndCatch(ThrowableRunnable<? extends Exception> throwableRunnable) throws Throwable {
        if (throwableRunnable == null) {
            $$$reportNull$$$0(25);
        }
        try {
            throwableRunnable.run();
            return null;
        } catch (Exception e) {
            return e;
        }
    }

    @SafeVarargs
    public static <E extends Exception> void runAllAndRethrowAllExceptions(final Class<? extends E> cls, final Supplier<E> supplier, ThrowableRunnable<? extends Exception>... throwableRunnableArr) throws Exception {
        if (cls == null) {
            $$$reportNull$$$0(26);
        }
        if (supplier == null) {
            $$$reportNull$$$0(27);
        }
        if (throwableRunnableArr == null) {
            $$$reportNull$$$0(28);
        }
        runAllAndRethrowAllExceptions(new java.util.function.Function() { // from class: pd4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ExceptionUtil.a(cls, supplier, (List) obj);
            }
        }, throwableRunnableArr);
    }
}
