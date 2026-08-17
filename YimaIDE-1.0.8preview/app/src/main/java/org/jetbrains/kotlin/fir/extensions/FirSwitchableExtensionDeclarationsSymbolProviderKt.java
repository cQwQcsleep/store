package org.jetbrains.kotlin.fir.extensions;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderInternals;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001e\u0010\u0007\u001a\u00020\b*\u00020\u00022\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\nH\u0007b\u0002\b\u000b\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004¨\u0006\f"}, d2 = {"generatedDeclarationsSymbolProvider", "Lorg/jetbrains/kotlin/fir/extensions/FirSwitchableExtensionDeclarationsSymbolProvider;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getGeneratedDeclarationsSymbolProvider", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/extensions/FirSwitchableExtensionDeclarationsSymbolProvider;", "generatedDeclarationsSymbolProvider$delegate", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "withGeneratedDeclarationsSymbolProviderDisabled", Argument.Delimiters.none, "action", "Lkotlin/Function0;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSwitchableExtensionDeclarationsSymbolProviderKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirSwitchableExtensionDeclarationsSymbolProviderKt.class, "generatedDeclarationsSymbolProvider", "getGeneratedDeclarationsSymbolProvider(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/extensions/FirSwitchableExtensionDeclarationsSymbolProvider;", 1)};
    private static final NullableArrayMapAccessor generatedDeclarationsSymbolProvider$delegate = FirSession.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(FirSwitchableExtensionDeclarationsSymbolProvider.class));

    public static final FirSwitchableExtensionDeclarationsSymbolProvider getGeneratedDeclarationsSymbolProvider(FirSession firSession) {
        firSession.getClass();
        return (FirSwitchableExtensionDeclarationsSymbolProvider) generatedDeclarationsSymbolProvider$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    @FirSymbolProviderInternals
    public static final void withGeneratedDeclarationsSymbolProviderDisabled(FirSession firSession, Function0<Unit> function0) {
        firSession.getClass();
        function0.getClass();
        FirSwitchableExtensionDeclarationsSymbolProvider generatedDeclarationsSymbolProvider = getGeneratedDeclarationsSymbolProvider(firSession);
        if (generatedDeclarationsSymbolProvider == null || generatedDeclarationsSymbolProvider.isDisabled$org_jetbrains_kotlin_providers()) {
            generatedDeclarationsSymbolProvider = null;
        }
        if (generatedDeclarationsSymbolProvider != null) {
            generatedDeclarationsSymbolProvider.disable();
        }
        try {
            function0.invoke();
        } finally {
            if (generatedDeclarationsSymbolProvider != null) {
                generatedDeclarationsSymbolProvider.enable();
            }
        }
    }
}
