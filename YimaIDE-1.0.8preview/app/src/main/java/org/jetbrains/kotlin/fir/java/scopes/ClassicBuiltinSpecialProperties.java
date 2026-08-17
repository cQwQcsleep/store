package org.jetbrains.kotlin.fir.java.scopes;

import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.java.scopes.ClassicBuiltinSpecialProperties;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.load.java.BuiltinSpecialProperties;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u0005*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\bJ \u0010\t\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00062\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u001e\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0006*\u0006\u0012\u0002\b\u00030\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u0010\u0010\f\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u0006H\u0002¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/ClassicBuiltinSpecialProperties;", Argument.Delimiters.none, "<init>", "()V", "getBuiltinSpecialPropertyGetterName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "containingScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "findBuiltinSpecialPropertyFqName", "symbol", "hasBuiltinSpecialPropertyFqNameImpl", "valueParametersAreEmpty", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClassicBuiltinSpecialProperties {
    public static final ClassicBuiltinSpecialProperties INSTANCE = new ClassicBuiltinSpecialProperties();

    private ClassicBuiltinSpecialProperties() {
    }

    public static ProcessorAction a(Ref.ObjectRef objectRef, FirNamedFunctionSymbol firNamedFunctionSymbol, FirTypeScope firTypeScope) {
        firNamedFunctionSymbol.getClass();
        firTypeScope.getClass();
        return hasBuiltinSpecialPropertyFqNameImpl$process(objectRef, firNamedFunctionSymbol, firTypeScope);
    }

    public static ProcessorAction b(Ref.ObjectRef objectRef, FirPropertySymbol firPropertySymbol, FirTypeScope firTypeScope) {
        firPropertySymbol.getClass();
        firTypeScope.getClass();
        return hasBuiltinSpecialPropertyFqNameImpl$process(objectRef, firPropertySymbol, firTypeScope);
    }

    private final FirCallableSymbol<?> hasBuiltinSpecialPropertyFqNameImpl(FirCallableSymbol<?> firCallableSymbol, FirTypeScope firTypeScope) {
        Set special_fq_names = BuiltinSpecialProperties.INSTANCE.getSPECIAL_FQ_NAMES();
        CallableId callableId = firCallableSymbol.getCallableId();
        callableId.getClass();
        if (special_fq_names.contains(callableId.asSingleFqName()) && valueParametersAreEmpty(firCallableSymbol)) {
            return firCallableSymbol;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            firTypeScope.processDirectOverriddenFunctionsWithBaseScope((FirNamedFunctionSymbol) firCallableSymbol, new Function2() { // from class: cy1
                public final Object invoke(Object obj, Object obj2) {
                    return ClassicBuiltinSpecialProperties.a(objectRef, (FirNamedFunctionSymbol) obj, (FirTypeScope) obj2);
                }
            });
        } else if (firCallableSymbol instanceof FirPropertySymbol) {
            firTypeScope.processDirectOverriddenPropertiesWithBaseScope((FirPropertySymbol) firCallableSymbol, new Function2() { // from class: dy1
                public final Object invoke(Object obj, Object obj2) {
                    return ClassicBuiltinSpecialProperties.b(objectRef, (FirPropertySymbol) obj, (FirTypeScope) obj2);
                }
            });
        }
        return (FirCallableSymbol) objectRef.element;
    }

    private static final ProcessorAction hasBuiltinSpecialPropertyFqNameImpl$process(Ref.ObjectRef<FirCallableSymbol<?>> objectRef, FirCallableSymbol<?> firCallableSymbol, FirTypeScope firTypeScope) {
        FirCallableSymbol<?> firCallableSymbolFindBuiltinSpecialPropertyFqName = INSTANCE.findBuiltinSpecialPropertyFqName(firCallableSymbol, firTypeScope);
        if (firCallableSymbolFindBuiltinSpecialPropertyFqName == null) {
            return ProcessorAction.NEXT;
        }
        objectRef.element = firCallableSymbolFindBuiltinSpecialPropertyFqName;
        return ProcessorAction.STOP;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean valueParametersAreEmpty(FirCallableSymbol<?> firCallableSymbol) {
        if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            return ((FirNamedFunction) ((FirNamedFunctionSymbol) firCallableSymbol).getFir()).getValueParameters().isEmpty();
        }
        return true;
    }

    public final FirCallableSymbol<?> findBuiltinSpecialPropertyFqName(FirCallableSymbol<?> symbol, FirTypeScope containingScope) {
        symbol.getClass();
        containingScope.getClass();
        if (BuiltinSpecialProperties.INSTANCE.getSPECIAL_SHORT_NAMES().contains(symbol.getName())) {
            return hasBuiltinSpecialPropertyFqNameImpl(symbol, containingScope);
        }
        return null;
    }

    public final String getBuiltinSpecialPropertyGetterName(FirCallableSymbol<?> firCallableSymbol, FirTypeScope firTypeScope) {
        firCallableSymbol.getClass();
        firTypeScope.getClass();
        FirCallableSymbol<?> firCallableSymbolFindBuiltinSpecialPropertyFqName = findBuiltinSpecialPropertyFqName(firCallableSymbol, firTypeScope);
        if (firCallableSymbolFindBuiltinSpecialPropertyFqName == null) {
            return null;
        }
        Map property_fq_name_to_jvm_getter_name_map = BuiltinSpecialProperties.INSTANCE.getPROPERTY_FQ_NAME_TO_JVM_GETTER_NAME_MAP();
        CallableId callableId = firCallableSymbolFindBuiltinSpecialPropertyFqName.getCallableId();
        callableId.getClass();
        Name name = (Name) property_fq_name_to_jvm_getter_name_map.get(callableId.asSingleFqName());
        if (name != null) {
            return name.asString();
        }
        return null;
    }
}
