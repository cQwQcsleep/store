package com.sun.tools.javac.file;

import com.sun.tools.javac.file.CacheFSInfo;
import com.sun.tools.javac.util.Context;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CacheFSInfo extends FSInfo {
    protected final ConcurrentHashMap<Path, Path> canonicalPathCache = new ConcurrentHashMap<>();
    protected final ConcurrentHashMap<Path, Optional<BasicFileAttributes>> attributeCache = new ConcurrentHashMap<>();
    protected final ConcurrentHashMap<Path, List<Path>> jarClassPathCache = new ConcurrentHashMap<>();

    public static /* synthetic */ FSInfo b(Context context) {
        CacheFSInfo cacheFSInfo = new CacheFSInfo();
        context.put((Class<CacheFSInfo>) FSInfo.class, cacheFSInfo);
        return cacheFSInfo;
    }

    public static void preRegister(Context context) {
        context.put(FSInfo.class, new Context.Factory() { // from class: e91
            @Override // com.sun.tools.javac.util.Context.Factory
            public final Object make(Context context2) {
                return CacheFSInfo.b(context2);
            }
        });
    }

    public void clearCache() {
        this.canonicalPathCache.clear();
        this.attributeCache.clear();
        this.jarClassPathCache.clear();
    }

    @Override // com.sun.tools.javac.file.FSInfo
    public boolean exists(Path path) {
        return getAttributes(path).isPresent();
    }

    public Optional<BasicFileAttributes> getAttributes(Path path) {
        return this.attributeCache.computeIfAbsent(path, new Function() { // from class: b91
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.maybeReadAttributes((Path) obj);
            }
        });
    }

    @Override // com.sun.tools.javac.file.FSInfo
    public Path getCanonicalFile(Path path) {
        return this.canonicalPathCache.computeIfAbsent(path, new Function() { // from class: f91
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return super/*com.sun.tools.javac.file.FSInfo*/.getCanonicalFile((Path) obj);
            }
        });
    }

    @Override // com.sun.tools.javac.file.FSInfo
    public List<Path> getJarClassPath(Path path) throws IOException {
        List<Path> jarClassPath;
        synchronized (this.jarClassPathCache) {
            try {
                jarClassPath = this.jarClassPathCache.get(path);
                if (jarClassPath == null) {
                    jarClassPath = super.getJarClassPath(path);
                    this.jarClassPathCache.put(path, jarClassPath);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return jarClassPath;
    }

    @Override // com.sun.tools.javac.file.FSInfo
    public boolean isDirectory(Path path) {
        return ((Boolean) getAttributes(path).map(new Function() { // from class: d91
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((BasicFileAttributes) obj).isDirectory());
            }
        }).orElse(Boolean.FALSE)).booleanValue();
    }

    @Override // com.sun.tools.javac.file.FSInfo
    public boolean isFile(Path path) {
        return ((Boolean) getAttributes(path).map(new Function() { // from class: c91
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Boolean.valueOf(((BasicFileAttributes) obj).isRegularFile());
            }
        }).orElse(Boolean.FALSE)).booleanValue();
    }

    public Optional<BasicFileAttributes> maybeReadAttributes(Path path) {
        try {
            return Optional.of(Files.readAttributes(path, BasicFileAttributes.class, new LinkOption[0]));
        } catch (IOException unused) {
            return Optional.empty();
        }
    }
}
