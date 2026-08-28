package com.shadow.okio;

import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.collections.MapsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.ClassReference;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import java.util.ArrayList;
import java.util.Map;
import kotlin.reflect.KClass;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class FileMetadata {
    private final Long createdAtMillis;
    private final Map<KClass<?>, Object> extras;
    private final boolean isDirectory;
    private final boolean isRegularFile;
    private final Long lastAccessedAtMillis;
    private final Long lastModifiedAtMillis;
    private final Long size;
    private final Path symlinkTarget;

    public FileMetadata() {
        this(false, false, null, null, null, null, null, null, 255, null);
    }

    public final FileMetadata copy(boolean z, boolean z2, Path path, Long l, Long l2, Long l3, Long l4, Map<KClass<?>, ? extends Object> map) {
        CloseableKt.checkNotNullParameter(map, "extras");
        return new FileMetadata(z, z2, path, l, l2, l3, l4, map);
    }

    public final <T> T extra(KClass<? extends T> kClass) {
        CloseableKt.checkNotNullParameter(kClass, "type");
        T t = (T) this.extras.get(kClass);
        if (t == null) {
            return null;
        }
        ClassReference classReference = (ClassReference) kClass;
        if (classReference.isInstance(t)) {
            return t;
        }
        throw new ClassCastException("Value cannot be cast to " + classReference.getQualifiedName());
    }

    public final Long getCreatedAtMillis() {
        return this.createdAtMillis;
    }

    public final Map<KClass<?>, Object> getExtras() {
        return this.extras;
    }

    public final Long getLastAccessedAtMillis() {
        return this.lastAccessedAtMillis;
    }

    public final Long getLastModifiedAtMillis() {
        return this.lastModifiedAtMillis;
    }

    public final Long getSize() {
        return this.size;
    }

    public final Path getSymlinkTarget() {
        return this.symlinkTarget;
    }

    public final boolean isDirectory() {
        return this.isDirectory;
    }

    public final boolean isRegularFile() {
        return this.isRegularFile;
    }

    public String toString() {
        ArrayList arrayList = new ArrayList();
        if (this.isRegularFile) {
            arrayList.add("isRegularFile");
        }
        if (this.isDirectory) {
            arrayList.add("isDirectory");
        }
        if (this.size != null) {
            arrayList.add("byteCount=" + this.size);
        }
        if (this.createdAtMillis != null) {
            arrayList.add("createdAt=" + this.createdAtMillis);
        }
        if (this.lastModifiedAtMillis != null) {
            arrayList.add("lastModifiedAt=" + this.lastModifiedAtMillis);
        }
        if (this.lastAccessedAtMillis != null) {
            arrayList.add("lastAccessedAt=" + this.lastAccessedAtMillis);
        }
        if (!this.extras.isEmpty()) {
            arrayList.add("extras=" + this.extras);
        }
        return CollectionsKt.c(arrayList, "FileMetadata(", ")", null, 56);
    }

    public FileMetadata(boolean z, boolean z2, Path path, Long l, Long l2, Long l3, Long l4, Map<KClass<?>, ? extends Object> map) {
        CloseableKt.checkNotNullParameter(map, "extras");
        this.isRegularFile = z;
        this.isDirectory = z2;
        this.symlinkTarget = path;
        this.size = l;
        this.createdAtMillis = l2;
        this.lastModifiedAtMillis = l3;
        this.lastAccessedAtMillis = l4;
        this.extras = MapsKt.c(map);
    }

    public /* synthetic */ FileMetadata(boolean z, boolean z2, Path path, Long l, Long l2, Long l3, Long l4, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) == 0 ? z2 : false, (i & 4) != 0 ? null : path, (i & 8) != 0 ? null : l, (i & 16) != 0 ? null : l2, (i & 32) != 0 ? null : l3, (i & 64) == 0 ? l4 : null, (i & 128) != 0 ? MapsKt.a() : map);
    }
}
