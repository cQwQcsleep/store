package org.jetbrains.kotlin.utils;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0082\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u001b\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0004HÆ\u0003J\u0010\u0010\u000e\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\u000bJ,\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00018\u0000HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0004HÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0005\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/utils/NamedArgument;", "T", "", "name", "", "value", "<init>", "(Ljava/lang/String;Ljava/lang/Object;)V", "getName", "()Ljava/lang/String;", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(Ljava/lang/String;Ljava/lang/Object;)Lorg/jetbrains/kotlin/utils/NamedArgument;", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
final /* data */ class NamedArgument<T> {
    private final String name;
    private final T value;

    public NamedArgument(String str, T t) {
        this.name = str;
        this.value = t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ NamedArgument copy$default(NamedArgument namedArgument, String str, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            str = namedArgument.name;
        }
        if ((i & 2) != 0) {
            obj = namedArgument.value;
        }
        return namedArgument.copy(str, obj);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    public final T component2() {
        return this.value;
    }

    public final NamedArgument<T> copy(String name, T value) {
        return new NamedArgument<>(name, value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof NamedArgument)) {
            return false;
        }
        NamedArgument namedArgument = (NamedArgument) other;
        return Intrinsics.areEqual(this.name, namedArgument.name) && Intrinsics.areEqual(this.value, namedArgument.value);
    }

    public final String getName() {
        return this.name;
    }

    public final T getValue() {
        return this.value;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        T t = this.value;
        return iHashCode + (t != null ? t.hashCode() : 0);
    }

    public String toString() {
        return "NamedArgument(name=" + this.name + ", value=" + this.value + ')';
    }
}
