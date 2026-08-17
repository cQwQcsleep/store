package org.jetbrains.kotlin.fir.analysis;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004¨\u0006\t"}, d2 = {"nullableCheckersComponent", "Lorg/jetbrains/kotlin/fir/analysis/CheckersComponent;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getNullableCheckersComponent", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/analysis/CheckersComponent;", "nullableCheckersComponent$delegate", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "checkersComponent", "getCheckersComponent", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckersComponentKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(CheckersComponentKt.class, "nullableCheckersComponent", "getNullableCheckersComponent(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/analysis/CheckersComponent;", 1)};
    private static final NullableArrayMapAccessor nullableCheckersComponent$delegate = FirSession.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(CheckersComponent.class));

    public static final CheckersComponent getCheckersComponent(FirSession firSession) {
        firSession.getClass();
        CheckersComponent nullableCheckersComponent = getNullableCheckersComponent(firSession);
        if (nullableCheckersComponent != null) {
            return nullableCheckersComponent;
        }
        b88.a("Expected `", Reflection.getOrCreateKotlinClass(CheckersComponent.class), "` to be registered in CLI compiler mode.");
        return null;
    }

    public static final CheckersComponent getNullableCheckersComponent(FirSession firSession) {
        firSession.getClass();
        return (CheckersComponent) nullableCheckersComponent$delegate.getValue(firSession, $$delegatedProperties[0]);
    }
}
