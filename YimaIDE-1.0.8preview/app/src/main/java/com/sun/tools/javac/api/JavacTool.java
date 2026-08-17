package com.sun.tools.javac.api;

import com.sun.source.util.JavacTask;
import com.sun.tools.javac.Main;
import com.sun.tools.javac.file.CacheFSInfo;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.main.Arguments;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.util.ClientCodeException;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.PropagatedException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;
import javax.lang.model.SourceVersion;
import javax.tools.DiagnosticListener;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class JavacTool implements JavaCompiler {
    @Deprecated
    public JavacTool() {
    }

    public static JavacTool create() {
        return new JavacTool();
    }

    @Override // javax.tools.Tool
    public Set<SourceVersion> getSourceVersions() {
        return Collections.unmodifiableSet(EnumSet.range(SourceVersion.RELEASE_3, SourceVersion.latest()));
    }

    @Override // javax.tools.JavaCompiler
    public JavacFileManager getStandardFileManager(DiagnosticListener<? super JavaFileObject> diagnosticListener, Locale locale, Charset charset) {
        Context context = new Context();
        context.put((Class<Locale>) Locale.class, locale);
        if (diagnosticListener != null) {
            context.put((Class<DiagnosticListener<? super JavaFileObject>>) DiagnosticListener.class, diagnosticListener);
        }
        context.put(Log.errKey, charset == null ? new PrintWriter((OutputStream) System.err, true) : new PrintWriter((Writer) new OutputStreamWriter(System.err, charset), true));
        CacheFSInfo.preRegister(context);
        return new JavacFileManager(context, true, charset);
    }

    /* JADX WARN: Code duplicated, block: B:43:0x00df A[Catch: ClientCodeException -> 0x001b, PropagatedException -> 0x0140, TryCatch #2 {ClientCodeException -> 0x001b, PropagatedException -> 0x0140, blocks: (B:3:0x0001, B:5:0x0007, B:6:0x000b, B:8:0x0011, B:12:0x0020, B:13:0x0024, B:15:0x002a, B:17:0x0038, B:19:0x0043, B:20:0x004a, B:21:0x0060, B:22:0x0061, B:25:0x0068, B:26:0x007e, B:28:0x0081, B:29:0x0089, B:31:0x008f, B:34:0x009e, B:35:0x00bd, B:37:0x00c0, B:40:0x00cc, B:42:0x00d4, B:49:0x00f9, B:51:0x00ff, B:52:0x0101, B:54:0x011d, B:55:0x0132, B:43:0x00df, B:45:0x00e3, B:47:0x00ed), top: B:62:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:45:0x00e3 A[Catch: ClientCodeException -> 0x001b, PropagatedException -> 0x0140, TryCatch #2 {ClientCodeException -> 0x001b, PropagatedException -> 0x0140, blocks: (B:3:0x0001, B:5:0x0007, B:6:0x000b, B:8:0x0011, B:12:0x0020, B:13:0x0024, B:15:0x002a, B:17:0x0038, B:19:0x0043, B:20:0x004a, B:21:0x0060, B:22:0x0061, B:25:0x0068, B:26:0x007e, B:28:0x0081, B:29:0x0089, B:31:0x008f, B:34:0x009e, B:35:0x00bd, B:37:0x00c0, B:40:0x00cc, B:42:0x00d4, B:49:0x00f9, B:51:0x00ff, B:52:0x0101, B:54:0x011d, B:55:0x0132, B:43:0x00df, B:45:0x00e3, B:47:0x00ed), top: B:62:0x0001 }] */
    /* JADX WARN: Code duplicated, block: B:46:0x00eb A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:47:0x00ed A[Catch: ClientCodeException -> 0x001b, PropagatedException -> 0x0140, TryCatch #2 {ClientCodeException -> 0x001b, PropagatedException -> 0x0140, blocks: (B:3:0x0001, B:5:0x0007, B:6:0x000b, B:8:0x0011, B:12:0x0020, B:13:0x0024, B:15:0x002a, B:17:0x0038, B:19:0x0043, B:20:0x004a, B:21:0x0060, B:22:0x0061, B:25:0x0068, B:26:0x007e, B:28:0x0081, B:29:0x0089, B:31:0x008f, B:34:0x009e, B:35:0x00bd, B:37:0x00c0, B:40:0x00cc, B:42:0x00d4, B:49:0x00f9, B:51:0x00ff, B:52:0x0101, B:54:0x011d, B:55:0x0132, B:43:0x00df, B:45:0x00e3, B:47:0x00ed), top: B:62:0x0001 }] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.sun.tools.javac.api.ClientCodeWrapper] */
    /* JADX WARN: Type inference failed for: r9v0, types: [javax.tools.JavaFileManager] */
    /* JADX WARN: Type inference failed for: r9v1, types: [javax.tools.JavaFileManager] */
    /* JADX WARN: Type inference failed for: r9v9, types: [com.sun.tools.javac.file.BaseFileManager, com.sun.tools.javac.file.JavacFileManager] */
    public JavacTask getTask(Writer writer, JavaFileManager javaFileManager, DiagnosticListener<? super JavaFileObject> diagnosticListener, Iterable<String> iterable, Iterable<String> iterable2, Iterable<? extends JavaFileObject> iterable3, Context context) {
        try {
            ?? Instance = ClientCodeWrapper.instance(context);
            if (iterable != null) {
                Iterator<String> it = iterable.iterator();
                while (it.hasNext()) {
                    Objects.requireNonNull(it.next());
                }
            }
            if (iterable2 != null) {
                Iterator<String> it2 = iterable2.iterator();
                while (it2.hasNext()) {
                    String next = it2.next();
                    int iIndexOf = next.indexOf(47);
                    if (iIndexOf > 0) {
                        String strSubstring = next.substring(0, iIndexOf);
                        if (!SourceVersion.isName(strSubstring)) {
                            throw new IllegalArgumentException("Not a valid module name: " + strSubstring);
                        }
                        next = next.substring(iIndexOf + 1);
                    }
                    if (!SourceVersion.isName(next)) {
                        throw new IllegalArgumentException("Not a valid class name: " + next);
                    }
                }
            }
            if (iterable3 != null) {
                iterable3 = Instance.wrapJavaFileObjects(iterable3);
                for (JavaFileObject javaFileObject : iterable3) {
                    if (javaFileObject.getKind() != JavaFileObject.Kind.SOURCE) {
                        throw new IllegalArgumentException("Compilation unit is not of SOURCE kind: \"" + javaFileObject.getName() + "\"");
                    }
                }
            }
            if (diagnosticListener != null) {
                context.put((Class<DiagnosticListener>) DiagnosticListener.class, Instance.wrap(diagnosticListener));
            }
            if (writer == null) {
                Context.Key<PrintWriter> key = Log.errKey;
                if (context.get(key) == null) {
                    context.put(key, new PrintWriter((OutputStream) System.err, true));
                } else if (writer instanceof PrintWriter) {
                    context.put(Log.errKey, (PrintWriter) writer);
                } else if (writer != null) {
                    context.put(Log.errKey, new PrintWriter(writer, true));
                }
            } else if (writer instanceof PrintWriter) {
                context.put(Log.errKey, (PrintWriter) writer);
            } else if (writer != null) {
                context.put(Log.errKey, new PrintWriter(writer, true));
            }
            if (javaFileManager == 0 && (javaFileManager = getStandardFileManager(diagnosticListener, (Locale) null, (Charset) null)) != 0) {
                javaFileManager.autoClose = true;
            }
            JavaFileManager javaFileManagerWrap = Instance.wrap(javaFileManager);
            context.put((Class<JavaFileManager>) JavaFileManager.class, javaFileManagerWrap);
            Arguments.instance(context).init("javac", iterable, iterable2, iterable3);
            Option option = Option.MULTIRELEASE;
            if (javaFileManagerWrap.isSupportedOption(option.primaryName) == 1) {
                javaFileManagerWrap.handleOption(option.primaryName, List.of(Target.instance(context).multiReleaseValue()).iterator());
            }
            return new JavacTaskImpl(context);
        } catch (ClientCodeException e) {
            rc6.a(e.getCause());
            return null;
        } catch (PropagatedException e2) {
            throw e2.getCause();
        }
    }

    @Override // javax.tools.OptionChecker
    public int isSupportedOption(String str) {
        for (Option option : Option.getJavacToolOptions()) {
            if (option.matches(str)) {
                return option.hasSeparateArg() ? 1 : 0;
            }
        }
        return -1;
    }

    @Override // javax.tools.Tool
    public String name() {
        return "javac";
    }

    @Override // javax.tools.Tool
    public int run(InputStream inputStream, OutputStream outputStream, OutputStream outputStream2, String... strArr) {
        if (outputStream2 == null) {
            outputStream2 = System.err;
        }
        for (String str : strArr) {
            Objects.requireNonNull(str);
        }
        return Main.compile(strArr, new PrintWriter(outputStream2, true));
    }

    @Override // javax.tools.JavaCompiler
    public /* bridge */ /* synthetic */ StandardJavaFileManager getStandardFileManager(DiagnosticListener diagnosticListener, Locale locale, Charset charset) {
        return getStandardFileManager((DiagnosticListener<? super JavaFileObject>) diagnosticListener, locale, charset);
    }

    @Override // javax.tools.JavaCompiler
    public JavacTask getTask(Writer writer, JavaFileManager javaFileManager, DiagnosticListener<? super JavaFileObject> diagnosticListener, Iterable<String> iterable, Iterable<String> iterable2, Iterable<? extends JavaFileObject> iterable3) {
        return getTask(writer, javaFileManager, diagnosticListener, iterable, iterable2, iterable3, new Context());
    }

    @Override // javax.tools.JavaCompiler
    public /* bridge */ /* synthetic */ JavaCompiler.CompilationTask getTask(Writer writer, JavaFileManager javaFileManager, DiagnosticListener diagnosticListener, Iterable iterable, Iterable iterable2, Iterable iterable3) {
        return getTask(writer, javaFileManager, (DiagnosticListener<? super JavaFileObject>) diagnosticListener, (Iterable<String>) iterable, (Iterable<String>) iterable2, (Iterable<? extends JavaFileObject>) iterable3);
    }
}
