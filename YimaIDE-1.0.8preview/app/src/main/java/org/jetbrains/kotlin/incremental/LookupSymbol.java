package org.jetbrains.kotlin.incremental;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.BuiltInOperatorNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0000H\u0096\u0082\u0004J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\f\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u000bHÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/incremental/LookupSymbol;", "", "name", "", "scope", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getScope", BuiltInOperatorNames.COMPARE_TO, "", "other", "component1", "component2", "copy", "equals", "", "", "hashCode", "toString", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class LookupSymbol implements Comparable<LookupSymbol> {
    private final String name;
    private final String scope;

    public LookupSymbol(String str, String str2) {
        str.getClass();
        str2.getClass();
        this.name = str;
        this.scope = str2;
    }

    public static /* synthetic */ LookupSymbol copy$default(LookupSymbol lookupSymbol, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lookupSymbol.name;
        }
        if ((i & 2) != 0) {
            str2 = lookupSymbol.scope;
        }
        return lookupSymbol.copy(str, str2);
    }

    @Override // java.lang.Comparable
    public int compareTo(LookupSymbol other) {
        other.getClass();
        int iCompareTo = this.scope.compareTo(other.scope);
        return iCompareTo != 0 ? iCompareTo : this.name.compareTo(other.name);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getScope() {
        return this.scope;
    }

    public final LookupSymbol copy(String name, String scope) {
        name.getClass();
        scope.getClass();
        return new LookupSymbol(name, scope);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LookupSymbol)) {
            return false;
        }
        LookupSymbol lookupSymbol = (LookupSymbol) other;
        return Intrinsics.areEqual(this.name, lookupSymbol.name) && Intrinsics.areEqual(this.scope, lookupSymbol.scope);
    }

    public final String getName() {
        return this.name;
    }

    public final String getScope() {
        return this.scope;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.scope.hashCode();
    }

    public String toString() {
        return "LookupSymbol(name=" + this.name + ", scope=" + this.scope + ')';
    }
}
