package org.jetbrains.kotlin.fir.scopes;

import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004\u001a\u001e\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u0005\u001a\u00020\b\"\u001f\u0010\t\u001a\u00020\u0002*\u00020\n8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"isOverriddenFunction", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "overrideCandidate", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "baseDeclaration", "isOverriddenProperty", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "firOverrideChecker", "Lorg/jetbrains/kotlin/fir/FirSession;", "getFirOverrideChecker", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", "firOverrideChecker$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOverrideCheckerKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirOverrideCheckerKt.class, "firOverrideChecker", "getFirOverrideChecker(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/scopes/FirOverrideChecker;", 1)};
    private static final ArrayMapAccessor firOverrideChecker$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirOverrideChecker.class), (Object) null, 2, (Object) null);

    public static final FirOverrideChecker getFirOverrideChecker(FirSession firSession) {
        firSession.getClass();
        return (FirOverrideChecker) firOverrideChecker$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isOverriddenFunction(FirOverrideChecker firOverrideChecker, FirNamedFunctionSymbol firNamedFunctionSymbol, FirNamedFunctionSymbol firNamedFunctionSymbol2) {
        firOverrideChecker.getClass();
        firNamedFunctionSymbol.getClass();
        firNamedFunctionSymbol2.getClass();
        return firOverrideChecker.isOverriddenFunction((FirNamedFunction) firNamedFunctionSymbol.getFir(), (FirNamedFunction) firNamedFunctionSymbol2.getFir());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isOverriddenProperty(FirOverrideChecker firOverrideChecker, FirCallableSymbol<?> firCallableSymbol, FirPropertySymbol firPropertySymbol) {
        firOverrideChecker.getClass();
        firCallableSymbol.getClass();
        firPropertySymbol.getClass();
        return firOverrideChecker.isOverriddenProperty((FirCallableDeclaration) firCallableSymbol.getFir(), (FirProperty) firPropertySymbol.getFir());
    }
}
