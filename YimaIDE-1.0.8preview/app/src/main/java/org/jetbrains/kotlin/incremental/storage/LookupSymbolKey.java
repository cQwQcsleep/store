package org.jetbrains.kotlin.incremental.storage;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.BuiltInOperatorNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\tB\u0019\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\b\u0010\nJ\u0012\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0000H\u0096\u0082\u0004J\n\u0010\u0013\u001a\u00020\u0003H\u0096\u0080\u0004J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0006HÆ\u0003J1\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u0006HÆ\u0001J\n\u0010\u001c\u001a\u00020\u0006HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000f¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/LookupSymbolKey;", "", "nameHash", "", "scopeHash", "name", "", "scope", "<init>", "(IILjava/lang/String;Ljava/lang/String;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "getNameHash", "()I", "getScopeHash", "getName", "()Ljava/lang/String;", "getScope", BuiltInOperatorNames.COMPARE_TO, "other", "hashCode", "equals", "", "", "component1", "component2", "component3", "component4", "copy", "toString", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class LookupSymbolKey implements Comparable<LookupSymbolKey> {
    private final String name;
    private final int nameHash;
    private final String scope;
    private final int scopeHash;

    public LookupSymbolKey(int i, int i2, String str, String str2) {
        str.getClass();
        str2.getClass();
        this.nameHash = i;
        this.scopeHash = i2;
        this.name = str;
        this.scope = str2;
    }

    public static /* synthetic */ LookupSymbolKey copy$default(LookupSymbolKey lookupSymbolKey, int i, int i2, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = lookupSymbolKey.nameHash;
        }
        if ((i3 & 2) != 0) {
            i2 = lookupSymbolKey.scopeHash;
        }
        if ((i3 & 4) != 0) {
            str = lookupSymbolKey.name;
        }
        if ((i3 & 8) != 0) {
            str2 = lookupSymbolKey.scope;
        }
        return lookupSymbolKey.copy(i, i2, str, str2);
    }

    @Override // java.lang.Comparable
    public int compareTo(LookupSymbolKey other) {
        other.getClass();
        int iCompare = Intrinsics.compare(this.nameHash, other.nameHash);
        return iCompare != 0 ? iCompare : Intrinsics.compare(this.scopeHash, other.scopeHash);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getNameHash() {
        return this.nameHash;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getScopeHash() {
        return this.scopeHash;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final String getScope() {
        return this.scope;
    }

    public final LookupSymbolKey copy(int nameHash, int scopeHash, String name, String scope) {
        name.getClass();
        scope.getClass();
        return new LookupSymbolKey(nameHash, scopeHash, name, scope);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(LookupSymbolKey.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        LookupSymbolKey lookupSymbolKey = (LookupSymbolKey) other;
        return this.nameHash == lookupSymbolKey.nameHash && this.scopeHash == lookupSymbolKey.scopeHash;
    }

    public final String getName() {
        return this.name;
    }

    public final int getNameHash() {
        return this.nameHash;
    }

    public final String getScope() {
        return this.scope;
    }

    public final int getScopeHash() {
        return this.scopeHash;
    }

    public int hashCode() {
        return (this.nameHash * 31) + this.scopeHash;
    }

    public String toString() {
        return "LookupSymbolKey(nameHash=" + this.nameHash + ", scopeHash=" + this.scopeHash + ", name=" + this.name + ", scope=" + this.scope + ')';
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public LookupSymbolKey(String str, String str2) {
        this(str.hashCode(), str2.hashCode(), str, str2);
        str.getClass();
        str2.getClass();
    }
}
