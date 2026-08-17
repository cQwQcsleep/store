package org.jetbrains.kotlin.contracts.model.structure;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESValue;
import org.jetbrains.kotlin.contracts.model.SimpleEffect;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010\fJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0010HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/structure/ESReturns;", "Lorg/jetbrains/kotlin/contracts/model/SimpleEffect;", "value", "Lorg/jetbrains/kotlin/contracts/model/ESValue;", "<init>", "(Lorg/jetbrains/kotlin/contracts/model/ESValue;)V", "getValue", "()Lorg/jetbrains/kotlin/contracts/model/ESValue;", "isImplies", Argument.Delimiters.none, "other", "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "(Lorg/jetbrains/kotlin/contracts/model/ESEffect;)Ljava/lang/Boolean;", "component1", "copy", "equals", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ESReturns extends SimpleEffect {
    private final ESValue value;

    public ESReturns(ESValue eSValue) {
        eSValue.getClass();
        this.value = eSValue;
    }

    public static /* synthetic */ ESReturns copy$default(ESReturns eSReturns, ESValue eSValue, int i, Object obj) {
        if ((i & 1) != 0) {
            eSValue = eSReturns.value;
        }
        return eSReturns.copy(eSValue);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ESValue getValue() {
        return this.value;
    }

    public final ESReturns copy(ESValue value) {
        value.getClass();
        return new ESReturns(value);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof ESReturns) && Intrinsics.areEqual(this.value, ((ESReturns) other).value);
    }

    public final ESValue getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value.hashCode();
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESEffect
    public Boolean isImplies(ESEffect other) {
        other.getClass();
        if (!(other instanceof ESReturns)) {
            return null;
        }
        ESValue eSValue = this.value;
        if (eSValue instanceof ESConstant) {
            ESReturns eSReturns = (ESReturns) other;
            ESValue eSValue2 = eSReturns.value;
            if (eSValue2 instanceof ESConstant) {
                return ValuesKt.isWildcard(eSValue2) ? Boolean.TRUE : Boolean.valueOf(Intrinsics.areEqual(this.value, eSReturns.value));
            }
        }
        return Boolean.valueOf(Intrinsics.areEqual(eSValue, ((ESReturns) other).value));
    }

    public String toString() {
        return "ESReturns(value=" + this.value + ')';
    }
}
