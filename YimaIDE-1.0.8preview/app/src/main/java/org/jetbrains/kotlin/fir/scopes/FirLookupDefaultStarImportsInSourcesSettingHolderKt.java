package org.jetbrains.kotlin.fir.scopes;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0007\u001a\u00020\b*\u00020\u00028@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"lookupDefaultStarImportsInSourcesSettingHolder", "Lorg/jetbrains/kotlin/fir/scopes/FirLookupDefaultStarImportsInSourcesSettingHolder;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getLookupDefaultStarImportsInSourcesSettingHolder", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/FirLookupDefaultStarImportsInSourcesSettingHolder;", "lookupDefaultStarImportsInSourcesSettingHolder$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "lookupDefaultStarImportsInSources", Argument.Delimiters.none, "getLookupDefaultStarImportsInSources", "(Lorg/jetbrains/kotlin/fir/FirSession;)Z", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirLookupDefaultStarImportsInSourcesSettingHolderKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirLookupDefaultStarImportsInSourcesSettingHolderKt.class, "lookupDefaultStarImportsInSourcesSettingHolder", "getLookupDefaultStarImportsInSourcesSettingHolder(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/FirLookupDefaultStarImportsInSourcesSettingHolder;", 1)};
    private static final ArrayMapAccessor lookupDefaultStarImportsInSourcesSettingHolder$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirLookupDefaultStarImportsInSourcesSettingHolder.class), (Object) null, 2, (Object) null);

    public static final boolean getLookupDefaultStarImportsInSources(FirSession firSession) {
        firSession.getClass();
        return getLookupDefaultStarImportsInSourcesSettingHolder(firSession).getValue();
    }

    private static final FirLookupDefaultStarImportsInSourcesSettingHolder getLookupDefaultStarImportsInSourcesSettingHolder(FirSession firSession) {
        return (FirLookupDefaultStarImportsInSourcesSettingHolder) lookupDefaultStarImportsInSourcesSettingHolder$delegate.getValue(firSession, $$delegatedProperties[0]);
    }
}
