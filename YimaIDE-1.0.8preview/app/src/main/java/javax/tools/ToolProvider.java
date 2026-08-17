package javax.tools;

import com.sun.tools.javac.api.JavacTool;
import java.util.Objects;
import java.util.ServiceConfigurationError;
import java.util.function.Supplier;
import javax.tools.ToolProvider;
import nbjavac.ModuleWrapper;
import nbjavac.ServiceLoaderWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ToolProvider {
    private static final String systemDocumentationToolModule = "jdk.javadoc";
    private static final String systemDocumentationToolName = "jdk.javadoc.internal.api.JavadocTool";
    private static final String systemJavaCompilerModule = "jdk.compiler";
    private static final String systemJavaCompilerName = "com.sun.tools.javac.api.JavacTool";

    private ToolProvider() {
    }

    public static /* synthetic */ Object a() {
        Object objNewInstance;
        try {
            try {
                objNewInstance = getSystemTool(DocumentationTool.class, systemDocumentationToolModule, systemDocumentationToolName);
            } catch (Error unused) {
                objNewInstance = null;
            }
            if (objNewInstance == null) {
                objNewInstance = Class.forName(systemDocumentationToolName).newInstance();
            }
            return DocumentationTool.class.cast(objNewInstance);
        } catch (Throwable th) {
            throw new Error(th);
        }
    }

    public static /* synthetic */ Object b() {
        Object objNewInstance;
        try {
            try {
                objNewInstance = getSystemTool(JavaCompiler.class, systemJavaCompilerModule, systemJavaCompilerName);
            } catch (Error unused) {
                objNewInstance = null;
            }
            if (objNewInstance == null) {
                objNewInstance = JavacTool.class.newInstance();
            }
            return JavaCompiler.class.cast(objNewInstance);
        } catch (Throwable th) {
            throw new Error(th);
        }
    }

    public static DocumentationTool getSystemDocumentationTool() {
        return (DocumentationTool) DocumentationTool.class.cast(new Supplier() { // from class: rge
            @Override // java.util.function.Supplier
            public final Object get() {
                return ToolProvider.a();
            }
        }.get());
    }

    public static JavaCompiler getSystemJavaCompiler() {
        return (JavaCompiler) JavaCompiler.class.cast(new Supplier() { // from class: sge
            @Override // java.util.function.Supplier
            public final Object get() {
                return ToolProvider.b();
            }
        }.get());
    }

    private static <T> T getSystemTool(Class<T> cls, String str, String str2) {
        try {
            for (T t : ServiceLoaderWrapper.loadTool(cls)) {
                if (Objects.equals(ModuleWrapper.getModule(t.getClass()).getName(), str)) {
                    return t;
                }
            }
            return null;
        } catch (ServiceConfigurationError e) {
            throw new Error(e);
        }
    }

    @Deprecated
    public static ClassLoader getSystemToolClassLoader() {
        return null;
    }
}
