package org.jetbrains.kotlin.library.metadata;

import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00028\u0000¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0011\u001a\u00020\u0004HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0006HÆ\u0003J\u000e\u0010\u0013\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000fJ2\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u0015J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004J\n\u0010\u001b\u001a\u00020\u0004HÖ\u0081\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0007\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/library/metadata/KlibInputModule;", "Config", "", "name", "", "path", "Ljava/nio/file/Path;", "config", "<init>", "(Ljava/lang/String;Ljava/nio/file/Path;Ljava/lang/Object;)V", "getName", "()Ljava/lang/String;", "getPath", "()Ljava/nio/file/Path;", "getConfig", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Ljava/nio/file/Path;Ljava/lang/Object;)Lorg/jetbrains/kotlin/library/metadata/KlibInputModule;", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:kotlin-util-klib-metadata"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class KlibInputModule<Config> {
    private final Config config;
    private final String name;
    private final Path path;

    public KlibInputModule(String str, Path path, Config config) {
        str.getClass();
        path.getClass();
        this.name = str;
        this.path = path;
        this.config = config;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ KlibInputModule copy$default(KlibInputModule klibInputModule, String str, Path path, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            str = klibInputModule.name;
        }
        if ((i & 2) != 0) {
            path = klibInputModule.path;
        }
        if ((i & 4) != 0) {
            obj = klibInputModule.config;
        }
        return klibInputModule.copy(str, path, obj);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Path getPath() {
        return this.path;
    }

    public final Config component3() {
        return this.config;
    }

    public final KlibInputModule<Config> copy(String name, Path path, Config config) {
        name.getClass();
        path.getClass();
        return new KlibInputModule<>(name, path, config);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KlibInputModule)) {
            return false;
        }
        KlibInputModule klibInputModule = (KlibInputModule) other;
        return Intrinsics.areEqual(this.name, klibInputModule.name) && Intrinsics.areEqual(this.path, klibInputModule.path) && Intrinsics.areEqual(this.config, klibInputModule.config);
    }

    public final Config getConfig() {
        return this.config;
    }

    public final String getName() {
        return this.name;
    }

    public final Path getPath() {
        return this.path;
    }

    public int hashCode() {
        int iHashCode = ((this.name.hashCode() * 31) + this.path.hashCode()) * 31;
        Config config = this.config;
        return iHashCode + (config == null ? 0 : config.hashCode());
    }

    public String toString() {
        return "KlibInputModule(name=" + this.name + ", path=" + this.path + ", config=" + this.config + ')';
    }
}
