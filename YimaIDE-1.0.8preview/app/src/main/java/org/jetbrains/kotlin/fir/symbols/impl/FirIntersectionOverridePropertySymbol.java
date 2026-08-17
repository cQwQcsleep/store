package org.jetbrains.kotlin.fir.symbols.impl;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.CallableId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B)\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0010\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u001e\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/FirIntersectionOverridePropertySymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularPropertySymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirIntersectionCallableSymbol;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "intersections", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "containsMultipleNonSubsumed", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/name/CallableId;Ljava/util/Collection;Z)V", "getIntersections", "()Ljava/util/Collection;", "getContainsMultipleNonSubsumed", "()Z", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirIntersectionOverridePropertySymbol extends FirRegularPropertySymbol implements FirIntersectionCallableSymbol {
    private final boolean containsMultipleNonSubsumed;
    private final Collection<FirCallableSymbol<?>> intersections;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FirIntersectionOverridePropertySymbol(CallableId callableId, Collection<? extends FirCallableSymbol<?>> collection, boolean z) {
        super(callableId);
        callableId.getClass();
        collection.getClass();
        this.intersections = collection;
        this.containsMultipleNonSubsumed = z;
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionCallableSymbol
    public boolean getContainsMultipleNonSubsumed() {
        return this.containsMultipleNonSubsumed;
    }

    @Override // org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionCallableSymbol
    public Collection<FirCallableSymbol<?>> getIntersections() {
        return this.intersections;
    }
}
