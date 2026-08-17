package org.jetbrains.kotlin.fir.java.scopes;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.java.scopes.BuiltinMethodsWithSpecialGenericSignature;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.jvm.SignatureUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.load.java.SpecialGenericSignatures;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\t\u001a\u0004\u0018\u00010\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0007b\u0002\b\rR\u0018\u0010\u0004\u001a\u00020\u0005*\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u000e\u001a\u00020\u0005*\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/BuiltinMethodsWithSpecialGenericSignature;", Argument.Delimiters.none, "<init>", "()V", "hasErasedValueParametersInJava", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getHasErasedValueParametersInJava", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)Z", "getOverriddenBuiltinFunctionWithErasedValueParametersInJava", "functionSymbol", "containingScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lkotlin/jvm/JvmStatic;", "sameAsBuiltinMethodWithErasedValueParameters", "Lorg/jetbrains/kotlin/name/Name;", "getSameAsBuiltinMethodWithErasedValueParameters", "(Lorg/jetbrains/kotlin/name/Name;)Z", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BuiltinMethodsWithSpecialGenericSignature {
    public static final BuiltinMethodsWithSpecialGenericSignature INSTANCE = new BuiltinMethodsWithSpecialGenericSignature();

    private BuiltinMethodsWithSpecialGenericSignature() {
    }

    public static boolean a(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return INSTANCE.getHasErasedValueParametersInJava(firNamedFunctionSymbol);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean getHasErasedValueParametersInJava(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        return CollectionsKt.contains(SpecialGenericSignatures.Companion.getERASED_VALUE_PARAMETERS_SIGNATURES(), SignatureUtilsKt.computeJvmSignature$default((FirFunction) firNamedFunctionSymbol.getFir(), null, 1, null));
    }

    @JvmStatic
    public static final FirNamedFunctionSymbol getOverriddenBuiltinFunctionWithErasedValueParametersInJava(FirNamedFunctionSymbol functionSymbol, FirTypeScope containingScope) {
        functionSymbol.getClass();
        containingScope.getClass();
        if (INSTANCE.getSameAsBuiltinMethodWithErasedValueParameters(functionSymbol.getName())) {
            return JavaScopeUtilsKt.firstOverriddenFunction(functionSymbol, containingScope, new Function1() { // from class: j21
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(BuiltinMethodsWithSpecialGenericSignature.a((FirNamedFunctionSymbol) obj));
                }
            });
        }
        return null;
    }

    public final boolean getSameAsBuiltinMethodWithErasedValueParameters(Name name) {
        name.getClass();
        return SpecialGenericSignatures.Companion.getERASED_VALUE_PARAMETERS_SHORT_NAMES().contains(name);
    }
}
