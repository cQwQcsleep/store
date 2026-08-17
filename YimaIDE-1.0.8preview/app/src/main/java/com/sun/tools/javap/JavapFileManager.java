package com.sun.tools.javap;

import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.util.Log;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavapFileManager extends JavacFileManager {
    private JavapFileManager(com.sun.tools.javac.util.Context context, Charset charset) {
        super(context, true, charset);
        setSymbolFileEnabled(false);
    }

    public static JavapFileManager create(DiagnosticListener<? super JavaFileObject> diagnosticListener, PrintWriter printWriter) {
        com.sun.tools.javac.util.Context context = new com.sun.tools.javac.util.Context();
        if (diagnosticListener != null) {
            context.put((Class<DiagnosticListener<? super JavaFileObject>>) DiagnosticListener.class, diagnosticListener);
        }
        context.put(Log.errKey, printWriter);
        return new JavapFileManager(context, null);
    }
}
