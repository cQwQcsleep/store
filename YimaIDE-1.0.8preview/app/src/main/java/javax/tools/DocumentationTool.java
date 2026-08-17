package javax.tools;

import java.io.Writer;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface DocumentationTool extends Tool, OptionChecker {

    public interface DocumentationTask extends Callable<Boolean> {
        void addModules(Iterable<String> iterable);

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        Boolean call();

        void setLocale(Locale locale);
    }

    public enum Location implements JavaFileManager.Location {
        DOCUMENTATION_OUTPUT,
        DOCLET_PATH,
        TAGLET_PATH,
        SNIPPET_PATH;

        @Override // javax.tools.JavaFileManager.Location
        public String getName() {
            return name();
        }

        @Override // javax.tools.JavaFileManager.Location
        public boolean isOutputLocation() {
            return ordinal() == 0;
        }
    }

    StandardJavaFileManager getStandardFileManager(DiagnosticListener<? super JavaFileObject> diagnosticListener, Locale locale, Charset charset);

    DocumentationTask getTask(Writer writer, JavaFileManager javaFileManager, DiagnosticListener<? super JavaFileObject> diagnosticListener, Class<?> cls, Iterable<String> iterable, Iterable<? extends JavaFileObject> iterable2);
}
