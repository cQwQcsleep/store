package org.jetbrains.kotlin.modules;

import java.io.Serializable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/modules/TargetId;", "Ljava/io/Serializable;", "name", "", "type", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "getType", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "org.jetbrains.kotlin:util"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class TargetId implements Serializable {
    private final String name;
    private final String type;

    public TargetId(String str, String str2) {
        str.getClass();
        str2.getClass();
        this.name = str;
        this.type = str2;
    }

    public static /* synthetic */ TargetId copy$default(TargetId targetId, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = targetId.name;
        }
        if ((i & 2) != 0) {
            str2 = targetId.type;
        }
        return targetId.copy(str, str2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getType() {
        return this.type;
    }

    public final TargetId copy(String name, String type) {
        name.getClass();
        type.getClass();
        return new TargetId(name, type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TargetId)) {
            return false;
        }
        TargetId targetId = (TargetId) other;
        return Intrinsics.areEqual(this.name, targetId.name) && Intrinsics.areEqual(this.type, targetId.type);
    }

    public final String getName() {
        return this.name;
    }

    public final String getType() {
        return this.type;
    }

    public int hashCode() {
        return (this.name.hashCode() * 31) + this.type.hashCode();
    }

    public String toString() {
        return "TargetId(name=" + this.name + ", type=" + this.type + ')';
    }
}
