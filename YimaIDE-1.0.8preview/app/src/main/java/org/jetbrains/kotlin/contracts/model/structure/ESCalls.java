package org.jetbrains.kotlin.contracts.model.structure;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRange;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESValue;
import org.jetbrains.kotlin.contracts.model.SimpleEffect;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016¢\u0006\u0002\u0010\u0010J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0015HÖ\u0083\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/structure/ESCalls;", "Lorg/jetbrains/kotlin/contracts/model/SimpleEffect;", "callable", "Lorg/jetbrains/kotlin/contracts/model/ESValue;", "kind", "Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "<init>", "(Lorg/jetbrains/kotlin/contracts/model/ESValue;Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;)V", "getCallable", "()Lorg/jetbrains/kotlin/contracts/model/ESValue;", "getKind", "()Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "isImplies", Argument.Delimiters.none, "other", "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "(Lorg/jetbrains/kotlin/contracts/model/ESEffect;)Ljava/lang/Boolean;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ESCalls extends SimpleEffect {
    private final ESValue callable;
    private final EventOccurrencesRange kind;

    public ESCalls(ESValue eSValue, EventOccurrencesRange eventOccurrencesRange) {
        eSValue.getClass();
        eventOccurrencesRange.getClass();
        this.callable = eSValue;
        this.kind = eventOccurrencesRange;
    }

    public static /* synthetic */ ESCalls copy$default(ESCalls eSCalls, ESValue eSValue, EventOccurrencesRange eventOccurrencesRange, int i, Object obj) {
        if ((i & 1) != 0) {
            eSValue = eSCalls.callable;
        }
        if ((i & 2) != 0) {
            eventOccurrencesRange = eSCalls.kind;
        }
        return eSCalls.copy(eSValue, eventOccurrencesRange);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ESValue getCallable() {
        return this.callable;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final EventOccurrencesRange getKind() {
        return this.kind;
    }

    public final ESCalls copy(ESValue callable, EventOccurrencesRange kind) {
        callable.getClass();
        kind.getClass();
        return new ESCalls(callable, kind);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ESCalls)) {
            return false;
        }
        ESCalls eSCalls = (ESCalls) other;
        return Intrinsics.areEqual(this.callable, eSCalls.callable) && this.kind == eSCalls.kind;
    }

    public final ESValue getCallable() {
        return this.callable;
    }

    public final EventOccurrencesRange getKind() {
        return this.kind;
    }

    public int hashCode() {
        return (this.callable.hashCode() * 31) + this.kind.hashCode();
    }

    @Override // org.jetbrains.kotlin.contracts.model.ESEffect
    public Boolean isImplies(ESEffect other) {
        other.getClass();
        if (!(other instanceof ESCalls)) {
            return null;
        }
        ESCalls eSCalls = (ESCalls) other;
        if (Intrinsics.areEqual(this.callable, eSCalls.callable)) {
            return Boolean.valueOf(this.kind == eSCalls.kind);
        }
        return null;
    }

    public String toString() {
        return "ESCalls(callable=" + this.callable + ", kind=" + this.kind + ')';
    }
}
