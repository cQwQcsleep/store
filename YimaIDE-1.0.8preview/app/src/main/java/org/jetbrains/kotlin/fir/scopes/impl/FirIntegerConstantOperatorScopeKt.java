package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ScopeSessionKey;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeIntegerConstantOperatorType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u001b\u0010\u0019\u001a\u00020\t*\u00020\u001a\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0000(\u0000\u001a\u001f\u0010\u0019\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\u001b\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0000(\u0001\u001a\u000e\u0010\u001c\u001a\u00020\t*\u0006\u0012\u0002\b\u00030\u001b\"\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000\"3\u0010\f\u001a\u0004\u0018\u00010\u000b*\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\u000b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\"3\u0010\u0014\u001a\u0004\u0018\u00010\t*\u00020\r2\b\u0010\n\u001a\u0004\u0018\u00010\t8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017ò\u0001\b\n\u00020\r\n\u00020\u000b¨\u0006\u001d"}, d2 = {"getOrBuildScopeForIntegerConstantOperatorType", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirIntegerConstantOperatorScope;", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeIntegerConstantOperatorType;", "INTEGER_CONSTANT_OPERATOR_SCOPE", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;", Argument.Delimiters.none, "<set-?>", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "originalForWrappedIntegerOperator", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "getOriginalForWrappedIntegerOperator", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "setOriginalForWrappedIntegerOperator", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)V", "originalForWrappedIntegerOperator$delegate", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationDataRegistry$DeclarationDataAccessor;", "isUnsignedWrappedIntegerOperator", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)Ljava/lang/Boolean;", "setUnsignedWrappedIntegerOperator", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Ljava/lang/Boolean;)V", "isUnsignedWrappedIntegerOperator$delegate", "isWrappedIntegerOperator", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isWrappedIntegerOperatorForUnsignedType", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirIntegerConstantOperatorScopeKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirIntegerConstantOperatorScopeKt.class, "originalForWrappedIntegerOperator", "getOriginalForWrappedIntegerOperator(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", 1), new MutablePropertyReference1Impl<>(FirIntegerConstantOperatorScopeKt.class, "isUnsignedWrappedIntegerOperator", "isUnsignedWrappedIntegerOperator(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)Ljava/lang/Boolean;", 1)};
    private static final ScopeSessionKey<Boolean, FirIntegerConstantOperatorScope> INTEGER_CONSTANT_OPERATOR_SCOPE = new ScopeSessionKey<Boolean, FirIntegerConstantOperatorScope>() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirIntegerConstantOperatorScopeKt$special$$inlined$scopeSessionKey$1
    };
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor isUnsignedWrappedIntegerOperator$delegate;
    private static final FirDeclarationDataRegistry.DeclarationDataAccessor originalForWrappedIntegerOperator$delegate;

    static {
        FirDeclarationDataRegistry firDeclarationDataRegistry = FirDeclarationDataRegistry.INSTANCE;
        originalForWrappedIntegerOperator$delegate = firDeclarationDataRegistry.data(OriginalForWrappedIntegerOperator.INSTANCE);
        isUnsignedWrappedIntegerOperator$delegate = firDeclarationDataRegistry.data(IsUnsignedForWrappedIntegerOperator.INSTANCE);
    }

    public static final FirIntegerConstantOperatorScope getOrBuildScopeForIntegerConstantOperatorType(ScopeSession scopeSession, FirSession firSession, ConeIntegerConstantOperatorType coneIntegerConstantOperatorType) {
        scopeSession.getClass();
        firSession.getClass();
        coneIntegerConstantOperatorType.getClass();
        Boolean boolValueOf = Boolean.valueOf(coneIntegerConstantOperatorType.getIsUnsigned());
        ScopeSessionKey<Boolean, FirIntegerConstantOperatorScope> scopeSessionKey = INTEGER_CONSTANT_OPERATOR_SCOPE;
        HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes = scopeSession.scopes();
        HashMap<ScopeSessionKey<?, ?>, Object> map = mapScopes.get(boolValueOf);
        if (map == null) {
            map = new HashMap<>();
            mapScopes.put(boolValueOf, map);
        }
        HashMap<ScopeSessionKey<?, ?>, Object> map2 = map;
        Object firIntegerConstantOperatorScope = map2.get(scopeSessionKey);
        if (firIntegerConstantOperatorScope == null) {
            firIntegerConstantOperatorScope = new FirIntegerConstantOperatorScope(firSession, scopeSession, coneIntegerConstantOperatorType.getIsUnsigned());
            map2.put(scopeSessionKey, firIntegerConstantOperatorScope);
        }
        return (FirIntegerConstantOperatorScope) firIntegerConstantOperatorScope;
    }

    public static final FirNamedFunctionSymbol getOriginalForWrappedIntegerOperator(FirNamedFunction firNamedFunction) {
        firNamedFunction.getClass();
        return (FirNamedFunctionSymbol) originalForWrappedIntegerOperator$delegate.getValue(firNamedFunction, $$delegatedProperties[0]);
    }

    private static final Boolean isUnsignedWrappedIntegerOperator(FirNamedFunction firNamedFunction) {
        return (Boolean) isUnsignedWrappedIntegerOperator$delegate.getValue(firNamedFunction, $$delegatedProperties[1]);
    }

    public static final boolean isWrappedIntegerOperator(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        FirNamedFunction firNamedFunction = firDeclaration instanceof FirNamedFunction ? (FirNamedFunction) firDeclaration : null;
        return (firNamedFunction != null ? getOriginalForWrappedIntegerOperator(firNamedFunction) : null) != null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isWrappedIntegerOperatorForUnsignedType(FirBasedSymbol<?> firBasedSymbol) {
        FirNamedFunction firNamedFunction;
        Boolean boolIsUnsignedWrappedIntegerOperator;
        firBasedSymbol.getClass();
        FirNamedFunctionSymbol firNamedFunctionSymbol = firBasedSymbol instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) firBasedSymbol : null;
        if (firNamedFunctionSymbol == null || (firNamedFunction = (FirNamedFunction) firNamedFunctionSymbol.getFir()) == null || (boolIsUnsignedWrappedIntegerOperator = isUnsignedWrappedIntegerOperator(firNamedFunction)) == null) {
            return false;
        }
        return boolIsUnsignedWrappedIntegerOperator.booleanValue();
    }

    public static final void setOriginalForWrappedIntegerOperator(FirNamedFunction firNamedFunction, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunction.getClass();
        originalForWrappedIntegerOperator$delegate.setValue(firNamedFunction, $$delegatedProperties[0], firNamedFunctionSymbol);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setUnsignedWrappedIntegerOperator(FirNamedFunction firNamedFunction, Boolean bool) {
        isUnsignedWrappedIntegerOperator$delegate.setValue(firNamedFunction, $$delegatedProperties[1], bool);
    }

    public static final boolean isWrappedIntegerOperator(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        return isWrappedIntegerOperator(firBasedSymbol.getFir());
    }
}
