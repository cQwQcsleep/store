package nbjavac;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ModuleWrapper {
    private final Class<?> clazz;

    public static class Configuration {
        public Configuration resolveAndBind(ModuleFinder moduleFinder, ModuleFinder moduleFinder2, Set<?> set) {
            return new Configuration();
        }
    }

    public static class ModuleDescriptor {

        public static class Version {
            public static void parse(String str) {
            }
        }
    }

    public static class ModuleFinder {
        public static ModuleFinder of(Path... pathArr) {
            return new ModuleFinder();
        }
    }

    public static class ModuleLayer {
        public static ModuleLayer boot() {
            return new ModuleLayer();
        }

        public Configuration configuration() {
            return new Configuration();
        }

        public ModuleLayer defineModulesWithOneLoader(Configuration configuration, ClassLoader classLoader) {
            return new ModuleLayer();
        }

        public Set<ModuleWrapper> modules() {
            return Collections.EMPTY_SET;
        }
    }

    private ModuleWrapper(Class<?> cls) {
        this.clazz = cls;
    }

    private static void ensureUses(Class<?> cls, Class<?> cls2) {
        try {
            Class.forName(Constants.MODULE_CLASS).getDeclaredMethod("addUses", Class.class).invoke(Class.class.getDeclaredMethod(Constants.GET_MODULE, null).invoke(cls, null), cls2);
        } catch (ReflectiveOperationException | SecurityException unused) {
        }
    }

    public static ModuleWrapper getModule(Class<?> cls) {
        return new ModuleWrapper(cls);
    }

    public static ModuleWrapper getUnnamedModule(ClassLoader classLoader) {
        return new ModuleWrapper(null);
    }

    public void addExports(String str, ModuleWrapper moduleWrapper) {
    }

    public <S> void addUses(Class<S> cls) {
        Class<?> cls2 = this.clazz;
        if (cls2 != null) {
            ensureUses(cls2, cls);
        }
        ensureUses(cls);
    }

    public String getName() {
        return !this.clazz.getName().equals("jdk.javadoc.internal.api.JavadocTool") ? "jdk.compiler" : "jdk.javadoc";
    }

    public boolean isNamed() {
        return false;
    }

    public static void ensureUses(Class<?> cls) {
        ensureUses(ServiceLoaderWrapper.class, cls);
    }
}
