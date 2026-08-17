package com.sun.tools.doclint;

import com.sun.source.util.JavacTask;
import com.sun.source.util.Plugin;
import com.sun.tools.doclint.DocLint;
import java.util.function.Predicate;
import javax.tools.ToolProvider;
import nbjavac.ServiceLoaderWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class DocLint implements Plugin {
    public static final String XCHECK_PACKAGE = "-XcheckPackage:";
    public static final String XMSGS_CUSTOM_PREFIX = "-Xmsgs:";
    public static final String XMSGS_OPTION = "-Xmsgs";
    private static ServiceLoaderWrapper.Provider<DocLint> docLintProvider;

    public static class NoDocLint extends DocLint {
        private NoDocLint() {
        }

        @Override // com.sun.source.util.Plugin
        public String getName() {
            return "doclint-not-available";
        }

        @Override // com.sun.source.util.Plugin
        public void init(JavacTask javacTask, String... strArr) {
            throw new IllegalStateException("doclint not available");
        }

        @Override // com.sun.tools.doclint.DocLint
        public boolean isValidOption(String str) {
            return str.equals(DocLint.XMSGS_OPTION) || str.startsWith(DocLint.XMSGS_CUSTOM_PREFIX) || str.startsWith(DocLint.XCHECK_PACKAGE);
        }
    }

    public static synchronized DocLint newDocLint() {
        try {
            if (docLintProvider == null) {
                docLintProvider = (ServiceLoaderWrapper.Provider) ServiceLoaderWrapper.load(DocLint.class, ToolProvider.class.getClassLoader()).stream().filter(new Predicate() { // from class: bv3
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ((DocLint) ((ServiceLoaderWrapper.Provider) obj).get()).getName().equals("doclint");
                    }
                }).findFirst().orElse(new ServiceLoaderWrapper.Provider<DocLint>() { // from class: com.sun.tools.doclint.DocLint.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // nbjavac.ServiceLoaderWrapper.Provider
                    public DocLint get() {
                        return new NoDocLint();
                    }

                    @Override // nbjavac.ServiceLoaderWrapper.Provider
                    public Class<? extends DocLint> type() {
                        return NoDocLint.class;
                    }
                });
            }
        } catch (Throwable th) {
            throw th;
        }
        return docLintProvider.get();
    }

    public abstract boolean isValidOption(String str);
}
