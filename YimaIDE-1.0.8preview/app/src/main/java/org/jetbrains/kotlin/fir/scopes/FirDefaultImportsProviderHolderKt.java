package org.jetbrains.kotlin.fir.scopes;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.resolve.DefaultImportsProvider;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"defaultImportsProviderHolder", "Lorg/jetbrains/kotlin/fir/scopes/FirDefaultImportsProviderHolder;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getDefaultImportsProviderHolder", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/FirDefaultImportsProviderHolder;", "defaultImportsProviderHolder$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "defaultImportsProvider", "Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "getDefaultImportsProvider", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/resolve/DefaultImportsProvider;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDefaultImportsProviderHolderKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirDefaultImportsProviderHolderKt.class, "defaultImportsProviderHolder", "getDefaultImportsProviderHolder(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/FirDefaultImportsProviderHolder;", 1)};
    private static final ArrayMapAccessor defaultImportsProviderHolder$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirDefaultImportsProviderHolder.class), (Object) null, 2, (Object) null);

    public static final DefaultImportsProvider getDefaultImportsProvider(FirSession firSession) {
        firSession.getClass();
        return getDefaultImportsProviderHolder(firSession).getProvider();
    }

    private static final FirDefaultImportsProviderHolder getDefaultImportsProviderHolder(FirSession firSession) {
        return (FirDefaultImportsProviderHolder) defaultImportsProviderHolder$delegate.getValue(firSession, $$delegatedProperties[0]);
    }
}
