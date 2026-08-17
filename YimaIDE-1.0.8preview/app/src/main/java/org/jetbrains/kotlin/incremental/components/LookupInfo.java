package org.jetbrains.kotlin.incremental.components;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004J\n\u0010 \u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/incremental/components/LookupInfo;", "Ljava/io/Serializable;", "filePath", "", "position", "Lorg/jetbrains/kotlin/incremental/components/Position;", "scopeFqName", "scopeKind", "Lorg/jetbrains/kotlin/incremental/components/ScopeKind;", "name", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/incremental/components/Position;Ljava/lang/String;Lorg/jetbrains/kotlin/incremental/components/ScopeKind;Ljava/lang/String;)V", "getFilePath", "()Ljava/lang/String;", "getPosition", "()Lorg/jetbrains/kotlin/incremental/components/Position;", "getScopeFqName", "getScopeKind", "()Lorg/jetbrains/kotlin/incremental/components/ScopeKind;", "getName", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class LookupInfo implements Serializable {
    private final String filePath;
    private final String name;
    private final Position position;
    private final String scopeFqName;
    private final ScopeKind scopeKind;

    public LookupInfo(String str, Position position, String str2, ScopeKind scopeKind, String str3) {
        str.getClass();
        position.getClass();
        str2.getClass();
        scopeKind.getClass();
        str3.getClass();
        this.filePath = str;
        this.position = position;
        this.scopeFqName = str2;
        this.scopeKind = scopeKind;
        this.name = str3;
    }

    public static /* synthetic */ LookupInfo copy$default(LookupInfo lookupInfo, String str, Position position, String str2, ScopeKind scopeKind, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = lookupInfo.filePath;
        }
        if ((i & 2) != 0) {
            position = lookupInfo.position;
        }
        if ((i & 4) != 0) {
            str2 = lookupInfo.scopeFqName;
        }
        if ((i & 8) != 0) {
            scopeKind = lookupInfo.scopeKind;
        }
        if ((i & 16) != 0) {
            str3 = lookupInfo.name;
        }
        String str4 = str3;
        String str5 = str2;
        return lookupInfo.copy(str, position, str5, scopeKind, str4);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getFilePath() {
        return this.filePath;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Position getPosition() {
        return this.position;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final String getScopeFqName() {
        return this.scopeFqName;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final ScopeKind getScopeKind() {
        return this.scopeKind;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final LookupInfo copy(String filePath, Position position, String scopeFqName, ScopeKind scopeKind, String name) {
        filePath.getClass();
        position.getClass();
        scopeFqName.getClass();
        scopeKind.getClass();
        name.getClass();
        return new LookupInfo(filePath, position, scopeFqName, scopeKind, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LookupInfo)) {
            return false;
        }
        LookupInfo lookupInfo = (LookupInfo) other;
        return Intrinsics.areEqual(this.filePath, lookupInfo.filePath) && Intrinsics.areEqual(this.position, lookupInfo.position) && Intrinsics.areEqual(this.scopeFqName, lookupInfo.scopeFqName) && this.scopeKind == lookupInfo.scopeKind && Intrinsics.areEqual(this.name, lookupInfo.name);
    }

    public final String getFilePath() {
        return this.filePath;
    }

    public final String getName() {
        return this.name;
    }

    public final Position getPosition() {
        return this.position;
    }

    public final String getScopeFqName() {
        return this.scopeFqName;
    }

    public final ScopeKind getScopeKind() {
        return this.scopeKind;
    }

    public int hashCode() {
        return (((((((this.filePath.hashCode() * 31) + this.position.hashCode()) * 31) + this.scopeFqName.hashCode()) * 31) + this.scopeKind.hashCode()) * 31) + this.name.hashCode();
    }

    public String toString() {
        return "LookupInfo(filePath=" + this.filePath + ", position=" + this.position + ", scopeFqName=" + this.scopeFqName + ", scopeKind=" + this.scopeKind + ", name=" + this.name + ')';
    }
}
