package io.github.rosemoe.sora.langs.textmate.registry;

import io.github.rosemoe.sora.langs.textmate.registry.provider.FileResolver;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class FileProviderRegistry {
    private static FileProviderRegistry fileProviderRegistry;
    private final List<FileResolver> allFileResolvers;

    private FileProviderRegistry() {
        ArrayList arrayList = new ArrayList();
        this.allFileResolvers = arrayList;
        arrayList.add(FileResolver.DEFAULT);
    }

    public static synchronized FileProviderRegistry getInstance() {
        try {
            if (fileProviderRegistry == null) {
                fileProviderRegistry = new FileProviderRegistry();
            }
        } catch (Throwable th) {
            throw th;
        }
        return fileProviderRegistry;
    }

    public synchronized void addFileProvider(FileResolver fileResolver) {
        if (fileResolver != FileResolver.DEFAULT) {
            this.allFileResolvers.add(fileResolver);
        }
    }

    public void dispose() {
        Iterator<FileResolver> it2 = this.allFileResolvers.iterator();
        while (it2.hasNext()) {
            it2.next().dispose();
        }
        this.allFileResolvers.clear();
    }

    public synchronized void removeFileProvider(FileResolver fileResolver) {
        if (fileResolver != FileResolver.DEFAULT) {
            this.allFileResolvers.remove(fileResolver);
        }
    }

    public InputStream tryGetInputStream(String str) {
        Iterator<FileResolver> it2 = this.allFileResolvers.iterator();
        while (it2.hasNext()) {
            InputStream inputStreamResolveStreamByPath = it2.next().resolveStreamByPath(str);
            if (inputStreamResolveStreamByPath != null) {
                return inputStreamResolveStreamByPath;
            }
        }
        return null;
    }
}
