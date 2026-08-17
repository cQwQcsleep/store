package org.jetbrains.kotlin.library;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/library/LenientUnresolvedLibrary;", "Lorg/jetbrains/kotlin/library/UnresolvedLibrary;", "path", "", "(Ljava/lang/String;)V", "getPath", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "kotlin-util-klib"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class LenientUnresolvedLibrary extends UnresolvedLibrary {
    private final String path;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LenientUnresolvedLibrary(String str) {
        super(null);
        str.getClass();
        this.path = str;
    }

    public static /* synthetic */ LenientUnresolvedLibrary copy$default(LenientUnresolvedLibrary lenientUnresolvedLibrary, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lenientUnresolvedLibrary.path;
        }
        return lenientUnresolvedLibrary.copy(str);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    public final LenientUnresolvedLibrary copy(String path) {
        path.getClass();
        return new LenientUnresolvedLibrary(path);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof LenientUnresolvedLibrary) && Intrinsics.areEqual(this.path, ((LenientUnresolvedLibrary) other).path);
    }

    @Override // org.jetbrains.kotlin.library.UnresolvedLibrary
    public String getPath() {
        return this.path;
    }

    public int hashCode() {
        return this.path.hashCode();
    }

    public String toString() {
        return "LenientUnresolvedLibrary(path=" + this.path + ')';
    }
}
