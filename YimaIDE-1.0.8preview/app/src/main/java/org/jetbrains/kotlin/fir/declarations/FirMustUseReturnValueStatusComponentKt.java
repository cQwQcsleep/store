package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u0007"}, d2 = {"mustUseReturnValueStatusComponent", "Lorg/jetbrains/kotlin/fir/declarations/FirMustUseReturnValueStatusComponent;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getMustUseReturnValueStatusComponent", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/declarations/FirMustUseReturnValueStatusComponent;", "mustUseReturnValueStatusComponent$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMustUseReturnValueStatusComponentKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirMustUseReturnValueStatusComponentKt.class, "mustUseReturnValueStatusComponent", "getMustUseReturnValueStatusComponent(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/declarations/FirMustUseReturnValueStatusComponent;", 1)};
    private static final ArrayMapAccessor mustUseReturnValueStatusComponent$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirMustUseReturnValueStatusComponent.class), (Object) null, 2, (Object) null);

    public static final FirMustUseReturnValueStatusComponent getMustUseReturnValueStatusComponent(FirSession firSession) {
        firSession.getClass();
        return (FirMustUseReturnValueStatusComponent) mustUseReturnValueStatusComponent$delegate.getValue(firSession, $$delegatedProperties[0]);
    }
}
