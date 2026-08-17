package org.jetbrains.kotlin.platform;

import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010(\n\u0000\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0096\u0082\u0004J\n\u0010\u0013\u001a\u00020\nH\u0096\u0080\u0004J\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00020\u0015H\u0096\u0083\u0004R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/platform/TargetPlatform;", "", "Lorg/jetbrains/kotlin/platform/SimplePlatform;", "componentPlatforms", "", "<init>", "(Ljava/util/Set;)V", "getComponentPlatforms", "()Ljava/util/Set;", "size", "", "getSize", "()I", "toString", "", "equals", "", "other", "", "hashCode", "iterator", "", "org.jetbrains.kotlin:language.targets"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class TargetPlatform implements Iterable<SimplePlatform>, KMappedMarker {
    private final Set<SimplePlatform> componentPlatforms;

    /* JADX WARN: Multi-variable type inference failed */
    public TargetPlatform(Set<? extends SimplePlatform> set) {
        set.getClass();
        this.componentPlatforms = set;
        if (set.isEmpty()) {
            w01.a("Don't instantiate TargetPlatform with empty set of platforms");
            throw null;
        }
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof TargetPlatform) && Intrinsics.areEqual(this.componentPlatforms, ((TargetPlatform) other).componentPlatforms);
    }

    public final Set<SimplePlatform> getComponentPlatforms() {
        return this.componentPlatforms;
    }

    public final int getSize() {
        return this.componentPlatforms.size();
    }

    public int hashCode() {
        return this.componentPlatforms.hashCode();
    }

    @Override // java.lang.Iterable
    public Iterator<SimplePlatform> iterator() {
        return this.componentPlatforms.iterator();
    }

    public String toString() {
        return PlatformUtilKt.getPresentableDescription(this);
    }
}
