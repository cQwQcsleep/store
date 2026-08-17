package org.jetbrains.kotlin.ir.backend.js.lower.calls;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/lower/calls/SimpleMemberKey;", "", "klass", "Lorg/jetbrains/kotlin/ir/types/IrType;", "name", "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(Lorg/jetbrains/kotlin/ir/types/IrType;Lorg/jetbrains/kotlin/name/Name;)V", "getKlass", "()Lorg/jetbrains/kotlin/ir/types/IrType;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:backend.js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final /* data */ class SimpleMemberKey {
    private final IrType klass;
    private final Name name;

    public SimpleMemberKey(IrType irType, Name name) {
        irType.getClass();
        name.getClass();
        this.klass = irType;
        this.name = name;
    }

    public static /* synthetic */ SimpleMemberKey copy$default(SimpleMemberKey simpleMemberKey, IrType irType, Name name, int i, Object obj) {
        if ((i & 1) != 0) {
            irType = simpleMemberKey.klass;
        }
        if ((i & 2) != 0) {
            name = simpleMemberKey.name;
        }
        return simpleMemberKey.copy(irType, name);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final IrType getKlass() {
        return this.klass;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Name getName() {
        return this.name;
    }

    public final SimpleMemberKey copy(IrType klass, Name name) {
        klass.getClass();
        name.getClass();
        return new SimpleMemberKey(klass, name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimpleMemberKey)) {
            return false;
        }
        SimpleMemberKey simpleMemberKey = (SimpleMemberKey) other;
        return Intrinsics.areEqual(this.klass, simpleMemberKey.klass) && Intrinsics.areEqual(this.name, simpleMemberKey.name);
    }

    public final IrType getKlass() {
        return this.klass;
    }

    public final Name getName() {
        return this.name;
    }

    public int hashCode() {
        return (this.klass.hashCode() * 31) + this.name.hashCode();
    }

    public String toString() {
        return "SimpleMemberKey(klass=" + this.klass + ", name=" + this.name + ')';
    }
}
