package com.intellij.util.lang;

import com.intellij.util.ExceptionUtil;
import com.intellij.util.lang.CompoundRuntimeException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class CompoundRuntimeException extends RuntimeException {
    private final List<? extends Throwable> exceptions;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 4 || i == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 4 || i == 5) ? 2 : 3];
        if (i == 1 || i == 2) {
            objArr[0] = "s";
        } else if (i == 3) {
            objArr[0] = "exceptionProcessor";
        } else if (i == 4 || i == 5) {
            objArr[0] = "com/intellij/util/lang/CompoundRuntimeException";
        } else {
            objArr[0] = "throwables";
        }
        if (i == 4 || i == 5) {
            objArr[1] = "processAll";
        } else {
            objArr[1] = "com/intellij/util/lang/CompoundRuntimeException";
        }
        if (i == 1 || i == 2) {
            objArr[2] = "printStackTrace";
        } else if (i == 3) {
            objArr[2] = "processAll";
        } else if (i != 4 && i != 5) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i != 4 && i != 5) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public CompoundRuntimeException(List<? extends Throwable> list) {
        if (list == null) {
            $$$reportNull$$$0(0);
        }
        this.exceptions = list;
    }

    public static /* synthetic */ String b(PrintWriter printWriter, Throwable th) {
        th.printStackTrace(printWriter);
        return "";
    }

    public static /* synthetic */ String d(PrintStream printStream, Throwable th) {
        th.printStackTrace(printStream);
        return "";
    }

    private CharSequence processAll(Function<? super Throwable, String> function, Consumer<? super String> consumer) {
        if (function == null) {
            $$$reportNull$$$0(3);
        }
        int size = this.exceptions.size();
        List<? extends Throwable> list = this.exceptions;
        int i = 0;
        if (size == 1) {
            String strApply = function.apply(list.get(0));
            if (consumer != null) {
                consumer.accept(strApply);
            }
            return strApply == null ? "" : strApply;
        }
        StringBuilder sb = new StringBuilder();
        String str = "CompositeException (" + list.size() + " nested):\n------------------------------\n";
        if (consumer != null) {
            consumer.accept(str);
        }
        sb.append(str);
        while (i < this.exceptions.size()) {
            Throwable th = this.exceptions.get(i);
            StringBuilder sb2 = new StringBuilder("[");
            i++;
            sb2.append(i);
            sb2.append("]: ");
            String string = sb2.toString();
            if (consumer != null) {
                consumer.accept(string);
            }
            sb.append(string);
            String strApply2 = function.apply(th);
            if (strApply2 == null) {
                strApply2 = "null\n";
            } else if (!strApply2.endsWith("\n")) {
                strApply2 = strApply2.concat("\n");
            }
            if (consumer != null) {
                consumer.accept(strApply2);
            }
            sb.append(strApply2);
        }
        if (consumer != null) {
            consumer.accept("------------------------------\n");
        }
        sb.append("------------------------------\n");
        return sb;
    }

    public static void throwIfNotEmpty(List<? extends Throwable> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        if (list.size() != 1) {
            throw new CompoundRuntimeException(list);
        }
        ExceptionUtil.rethrow(list.get(0));
    }

    @Override // java.lang.Throwable
    public synchronized Throwable getCause() {
        return this.exceptions.isEmpty() ? null : this.exceptions.get(0);
    }

    @Override // java.lang.Throwable
    public String getLocalizedMessage() {
        return processAll(new Function() { // from class: zo2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Throwable) obj).getLocalizedMessage();
            }
        }, null).toString();
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return processAll(new Function() { // from class: xo2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Throwable) obj).getMessage();
            }
        }, null).toString();
    }

    @Override // java.lang.Throwable
    public void printStackTrace(final PrintStream printStream) {
        if (printStream == null) {
            $$$reportNull$$$0(1);
        }
        Function<? super Throwable, String> function = new Function() { // from class: to2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CompoundRuntimeException.d(printStream, (Throwable) obj);
            }
        };
        Objects.requireNonNull(printStream);
        processAll(function, new Consumer() { // from class: uo2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                printStream.print((String) obj);
            }
        });
    }

    @Override // java.lang.Throwable
    public String toString() {
        return processAll(new Function() { // from class: yo2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Throwable) obj).toString();
            }
        }, null).toString();
    }

    @Override // java.lang.Throwable
    public void printStackTrace(final PrintWriter printWriter) {
        if (printWriter == null) {
            $$$reportNull$$$0(2);
        }
        Function<? super Throwable, String> function = new Function() { // from class: vo2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return CompoundRuntimeException.b(printWriter, (Throwable) obj);
            }
        };
        Objects.requireNonNull(printWriter);
        processAll(function, new Consumer() { // from class: wo2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                printWriter.print((String) obj);
            }
        });
    }
}
