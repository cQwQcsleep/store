package kotlin.reflect.jvm.internal.impl.platform;

import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class TargetPlatform implements Iterable<Object>, KMappedMarker {
    private final Set<Object> componentPlatforms;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TargetPlatform) && Intrinsics.areEqual(this.componentPlatforms, ((TargetPlatform) obj).componentPlatforms);
    }

    public final Set<Object> getComponentPlatforms() {
        return this.componentPlatforms;
    }

    public int hashCode() {
        return this.componentPlatforms.hashCode();
    }

    @Override // java.lang.Iterable
    public Iterator<Object> iterator() {
        return this.componentPlatforms.iterator();
    }

    public String toString() {
        return PlatformUtilKt.getPresentableDescription(this);
    }
}
