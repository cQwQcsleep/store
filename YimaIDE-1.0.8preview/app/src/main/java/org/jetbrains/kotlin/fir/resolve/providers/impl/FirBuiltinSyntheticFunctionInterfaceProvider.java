package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKindKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018H\u0014J\f\u0010\u0019\u001a\u00020\t*\u00020\u0018H\u0014R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirBuiltinSyntheticFunctionInterfaceProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirSyntheticFunctionInterfaceProviderBase;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "originateFromFallbackBuiltIns", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Z)V", "generatedClassIdSet", "Ljava/util/concurrent/ConcurrentHashMap;", "Lorg/jetbrains/kotlin/name/ClassId;", Argument.Delimiters.none, "generatedClassIds", Argument.Delimiters.none, "getGeneratedClassIds", "()Ljava/util/Set;", "createSyntheticFunctionInterface", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "classId", "kind", "Lorg/jetbrains/kotlin/builtins/functions/FunctionTypeKind;", "isAcceptable", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirBuiltinSyntheticFunctionInterfaceProvider extends FirSyntheticFunctionInterfaceProviderBase {
    private final ConcurrentHashMap<ClassId, Unit> generatedClassIdSet;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirBuiltinSyntheticFunctionInterfaceProvider(FirSession firSession, FirModuleData firModuleData, FirKotlinScopeProvider firKotlinScopeProvider, boolean z) {
        super(firSession, firModuleData, firKotlinScopeProvider, z);
        firSession.getClass();
        firModuleData.getClass();
        firKotlinScopeProvider.getClass();
        firSession.register(Reflection.getOrCreateKotlinClass(FirBuiltinSyntheticFunctionInterfaceProvider.class), this);
        this.generatedClassIdSet = new ConcurrentHashMap<>();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.impl.FirSyntheticFunctionInterfaceProviderBase
    public FirRegularClassSymbol createSyntheticFunctionInterface(ClassId classId, FunctionTypeKind kind) {
        classId.getClass();
        kind.getClass();
        FirRegularClassSymbol firRegularClassSymbolCreateSyntheticFunctionInterface = super.createSyntheticFunctionInterface(classId, kind);
        if (firRegularClassSymbolCreateSyntheticFunctionInterface == null) {
            return null;
        }
        this.generatedClassIdSet.put(classId, Unit.INSTANCE);
        return firRegularClassSymbolCreateSyntheticFunctionInterface;
    }

    public final Set<ClassId> getGeneratedClassIds() {
        Set<ClassId> setKeySet = this.generatedClassIdSet.keySet();
        setKeySet.getClass();
        return setKeySet;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.impl.FirSyntheticFunctionInterfaceProviderBase
    public boolean isAcceptable(FunctionTypeKind functionTypeKind) {
        functionTypeKind.getClass();
        return FunctionTypeKindKt.isBuiltin(functionTypeKind);
    }

    public /* synthetic */ FirBuiltinSyntheticFunctionInterfaceProvider(FirSession firSession, FirModuleData firModuleData, FirKotlinScopeProvider firKotlinScopeProvider, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firModuleData, firKotlinScopeProvider, (i & 8) != 0 ? false : z);
    }
}
