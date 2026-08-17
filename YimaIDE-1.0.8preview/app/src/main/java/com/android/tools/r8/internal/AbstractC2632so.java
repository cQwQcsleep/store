package com.android.tools.r8.internal;

import com.android.tools.r8.CompilationFailedException;
import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.DiagnosticsHandler;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.StringConsumer;
import com.android.tools.r8.T;
import com.android.tools.r8.internal.AbstractC2632so;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.utils.ExceptionDiagnostic;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.FileSystemException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* JADX INFO: renamed from: com.android.tools.r8.internal.so, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2632so {

    /* JADX INFO: renamed from: com.android.tools.r8.internal.so$a */
    public interface a {
        void run() throws ResourceException, C0613Ke, IOException;
    }

    public static Exception a(DiagnosticsHandler diagnosticsHandler, Throwable th, InterfaceC1938ki0 interfaceC1938ki0, Class cls) {
        Position position;
        Diagnostic diagnostic;
        Position position2;
        Origin originUnknown = Origin.unknown();
        Position position3 = Position.UNKNOWN;
        ArrayList arrayList = new ArrayList();
        Throwable cause = th;
        boolean zIsAssignableFrom = false;
        boolean z = false;
        while (true) {
            z |= cause instanceof C2148n8;
            zIsAssignableFrom |= cls.isAssignableFrom(cause.getClass());
            Origin originA = a(cause);
            if (originA != Origin.unknown()) {
                originUnknown = originA;
            }
            if (cause instanceof C0613Ke) {
                position = ((C0613Ke) cause).c;
            } else if (cause instanceof C2803uo) {
                position = ((C2803uo) cause).c;
            } else {
                position = (!(cause instanceof C1534g) || (diagnostic = ((C1534g) cause).b) == null) ? Position.UNKNOWN : diagnostic.getPosition();
            }
            position2 = Position.UNKNOWN;
            if (position != position2) {
                position3 = position;
            }
            if (cause.getCause() == null || arrayList.contains(cause)) {
                break;
            }
            arrayList.add(cause);
            cause = cause.getCause();
        }
        if (th != cause) {
            cause.addSuppressed(th);
        }
        if (!z && !zIsAssignableFrom) {
            diagnosticsHandler.error(new ExceptionDiagnostic(cause, originUnknown, position3));
        }
        StringBuilder sb = new StringBuilder("Compilation failed to complete");
        if (position3 != position2) {
            sb.append(", position: ");
            sb.append(position3);
        }
        if (originUnknown != Origin.unknown()) {
            sb.append(", origin: ");
            sb.append(originUnknown);
        }
        Exception exc = (Exception) interfaceC1938ki0.a(sb.toString(), cause, Boolean.valueOf(z));
        StackTraceElement stackTraceElement = new StackTraceElement("Version", "fakeStackEntry", "Version_8.5.10.java", 0);
        StackTraceElement[] stackTrace = exc.getStackTrace();
        int length = stackTrace.length + 1;
        int i = WW.a;
        Object[] objArrCopyOf = Arrays.copyOf(stackTrace.length == 0 ? stackTrace : Arrays.copyOf(stackTrace, 0), length);
        objArrCopyOf[0] = stackTraceElement;
        System.arraycopy(stackTrace, 0, objArrCopyOf, 1, stackTrace.length);
        exc.setStackTrace((StackTraceElement[]) objArrCopyOf);
        return exc;
    }

    public static /* synthetic */ StackTraceElement[] b(Map.Entry entry) {
        return (StackTraceElement[]) entry.getValue();
    }

    public static void c(C2742u50 c2742u50, a aVar) {
        a(c2742u50, aVar);
    }

    public static void b(C2742u50 c2742u50, a aVar) throws CompilationFailedException {
        a(c2742u50, aVar);
    }

    public static void a(C2742u50 c2742u50, final StringConsumer stringConsumer, final String str) {
        new Consumer() { // from class: gbi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                stringConsumer.accept(str, (DiagnosticsHandler) obj);
            }
        }.accept(c2742u50);
        c2742u50.a();
    }

    public static void a(C2742u50 c2742u50, final StringConsumer stringConsumer) {
        Objects.requireNonNull(stringConsumer);
        new Consumer() { // from class: fbi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                stringConsumer.finished((DiagnosticsHandler) obj);
            }
        }.accept(c2742u50);
        c2742u50.a();
    }

    public static void a(C2742u50 c2742u50, Consumer consumer) {
        consumer.accept(c2742u50);
        c2742u50.a();
    }

    public static void a(C2742u50 c2742u50, a aVar) {
        try {
            aVar.run();
            c2742u50.a();
        } catch (Throwable th) {
            throw a(c2742u50, th);
        }
    }

    public static CompilationFailedException a(C2742u50 c2742u50, Throwable th) {
        return (CompilationFailedException) a(c2742u50, th, new InterfaceC1938ki0() { // from class: abi
            @Override // com.android.tools.r8.internal.InterfaceC1938ki0
            public final Object a(Object obj, Object obj2, Object obj3) {
                return T.a((String) obj, (Throwable) obj2, ((Boolean) obj3).booleanValue());
            }
        }, C1534g.class);
    }

    public static String a() {
        return (String) Thread.getAllStackTraces().entrySet().stream().filter(new Predicate() { // from class: bbi
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Thread) ((Map.Entry) obj).getKey()).getName().equals("main");
            }
        }).map(new Function() { // from class: cbi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return AbstractC2632so.b((Map.Entry) obj);
            }
        }).flatMap(new Function() { // from class: dbi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Stream.of((Object[]) ((StackTraceElement[]) obj));
            }
        }).map(new Function() { // from class: ebi
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((StackTraceElement) obj).toString();
            }
        }).collect(Collectors.joining(System.lineSeparator()));
    }

    public static Origin a(Throwable th) {
        if (th instanceof IOException) {
            IOException iOException = (IOException) th;
            if (iOException instanceof FileSystemException) {
                FileSystemException fileSystemException = (FileSystemException) iOException;
                if (fileSystemException.getFile() != null && !fileSystemException.getFile().isEmpty()) {
                    return new PathOrigin(Paths.get(fileSystemException.getFile(), new String[0]));
                }
            }
            return Origin.unknown();
        }
        if (th instanceof C0613Ke) {
            return ((C0613Ke) th).b;
        }
        if (th instanceof ResourceException) {
            return ((ResourceException) th).getOrigin();
        }
        if (th instanceof C2803uo) {
            return ((C2803uo) th).b;
        }
        if (th instanceof C1534g) {
            Diagnostic diagnostic = ((C1534g) th).b;
            return diagnostic != null ? diagnostic.getOrigin() : Origin.unknown();
        }
        return Origin.unknown();
    }

    public static void a(InterfaceC2718to interfaceC2718to) {
        String str = "Compilation failed";
        try {
            interfaceC2718to.run();
        } catch (CompilationFailedException e) {
            Throwable cause = e.getCause();
            PrintStream printStream = System.err;
            if (!(cause instanceof C0613Ke) && !(cause instanceof C1534g)) {
                str = "Compilation failed with an internal error.";
            }
            printStream.println(str);
            rc6.a(e);
        } catch (RuntimeException e2) {
            PrintStream printStream2 = System.err;
            if (!(e2 instanceof C0613Ke) && !(e2 instanceof C1534g)) {
                str = "Compilation failed with an internal error.";
            }
            printStream2.println(str);
            throw e2;
        }
    }

    public static RuntimeException a(ExecutionException executionException) {
        return new RuntimeException(executionException);
    }

    public static void a(Origin origin, Position position, final Runnable runnable) {
        a(origin, position, new Supplier() { // from class: hbi
            @Override // java.util.function.Supplier
            public final Object get() {
                return AbstractC2632so.a(runnable);
            }
        });
    }

    public static /* synthetic */ Object a(Runnable runnable) {
        runnable.run();
        return null;
    }

    public static Object a(Origin origin, Position position, Supplier supplier) {
        Position position2;
        Diagnostic diagnostic;
        try {
            return supplier.get();
        } catch (C2148n8 e) {
            throw e;
        } catch (RuntimeException e2) {
            if (origin != Origin.unknown() || position != Position.UNKNOWN) {
                Origin originA = a(e2);
                if (e2 instanceof C0613Ke) {
                    position2 = ((C0613Ke) e2).c;
                } else if (e2 instanceof C2803uo) {
                    position2 = ((C2803uo) e2).c;
                } else {
                    position2 = (!(e2 instanceof C1534g) || (diagnostic = ((C1534g) e2).b) == null) ? Position.UNKNOWN : diagnostic.getPosition();
                }
                if (origin != originA || position != position2) {
                    throw new C2803uo(e2, origin, position);
                }
                throw e2;
            }
            throw e2;
        }
    }
}
