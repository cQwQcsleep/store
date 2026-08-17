package org.jetbrains.kotlin.fir.resolve.providers.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKindKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeKindServiceKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\f\u0010\n\u001a\u00020\u000b*\u00020\fH\u0014¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirExtensionSyntheticFunctionInterfaceProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirSyntheticFunctionInterfaceProviderBase;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;)V", "isAcceptable", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExtensionSyntheticFunctionInterfaceProvider extends FirSyntheticFunctionInterfaceProviderBase {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirExtensionSyntheticFunctionInterfaceProvider(FirSession firSession, FirModuleData firModuleData, FirKotlinScopeProvider firKotlinScopeProvider) {
        super(firSession, firModuleData, firKotlinScopeProvider, false, 8, null);
        firSession.getClass();
        firModuleData.getClass();
        firKotlinScopeProvider.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.impl.FirSyntheticFunctionInterfaceProviderBase
    public boolean isAcceptable(FunctionTypeKind functionTypeKind) {
        functionTypeKind.getClass();
        return !FunctionTypeKindKt.isBuiltin(functionTypeKind);
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirExtensionSyntheticFunctionInterfaceProvider$Companion;", Argument.Delimiters.none, "<init>", "()V", "createIfNeeded", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirExtensionSyntheticFunctionInterfaceProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirExtensionSyntheticFunctionInterfaceProvider createIfNeeded(FirSession session, FirModuleData moduleData, FirKotlinScopeProvider kotlinScopeProvider) {
            session.getClass();
            moduleData.getClass();
            kotlinScopeProvider.getClass();
            if (FirFunctionTypeKindServiceKt.getFunctionTypeService(session).hasExtensionKinds()) {
                return new FirExtensionSyntheticFunctionInterfaceProvider(session, moduleData, kotlinScopeProvider);
            }
            return null;
        }

        private Companion() {
        }
    }
}
