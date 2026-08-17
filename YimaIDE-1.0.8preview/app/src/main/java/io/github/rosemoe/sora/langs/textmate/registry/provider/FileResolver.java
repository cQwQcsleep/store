package io.github.rosemoe.sora.langs.textmate.registry.provider;

import io.github.rosemoe.sora.langs.textmate.registry.provider.FileResolver;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@FunctionalInterface
public interface FileResolver {
    public static final FileResolver DEFAULT = new FileResolver() { // from class: pq4
        @Override // io.github.rosemoe.sora.langs.textmate.registry.provider.FileResolver
        public final InputStream resolveStreamByPath(String str) {
            return FileResolver.a(str);
        }
    };

    static /* synthetic */ InputStream a(String str) {
        File file = new File(str);
        if (!file.isFile()) {
            return null;
        }
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException unused) {
            return null;
        }
    }

    default void dispose() {
    }

    InputStream resolveStreamByPath(String str);
}
