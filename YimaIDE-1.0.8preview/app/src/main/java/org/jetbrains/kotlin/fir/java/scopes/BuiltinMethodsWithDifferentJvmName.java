package org.jetbrains.kotlin.fir.java.scopes;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.scopes.jvm.SignatureUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.load.java.SpecialGenericSignatures;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bR\u0015\u0010\f\u001a\u00020\t*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/scopes/BuiltinMethodsWithDifferentJvmName;", Argument.Delimiters.none, "<init>", "()V", "getJvmName", "Lorg/jetbrains/kotlin/name/Name;", "functionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "isBuiltinFunctionWithDifferentNameInJvm", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isRemoveAtByIndex", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)Z", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BuiltinMethodsWithDifferentJvmName {
    public static final BuiltinMethodsWithDifferentJvmName INSTANCE = new BuiltinMethodsWithDifferentJvmName();

    private BuiltinMethodsWithDifferentJvmName() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Name getJvmName(FirNamedFunctionSymbol functionSymbol) {
        functionSymbol.getClass();
        Map signature_to_jvm_representation_name = SpecialGenericSignatures.Companion.getSIGNATURE_TO_JVM_REPRESENTATION_NAME();
        String strComputeJvmSignature$default = SignatureUtilsKt.computeJvmSignature$default((FirFunction) functionSymbol.getFir(), null, 1, null);
        if (strComputeJvmSignature$default == null) {
            return null;
        }
        return (Name) signature_to_jvm_representation_name.get(strComputeJvmSignature$default);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isBuiltinFunctionWithDifferentNameInJvm(FirNamedFunctionSymbol functionSymbol, FirSession session) {
        functionSymbol.getClass();
        session.getClass();
        return JavaScopeUtilsKt.isFromBuiltinClass(functionSymbol, session) && SpecialGenericSignatures.Companion.getSIGNATURE_TO_JVM_REPRESENTATION_NAME().containsKey(SignatureUtilsKt.computeJvmSignature$default((FirFunction) functionSymbol.getFir(), null, 1, null));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean isRemoveAtByIndex(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return Intrinsics.areEqual(firNamedFunctionSymbol.getName().asString(), "removeAt") && Intrinsics.areEqual(SignatureUtilsKt.computeJvmSignature$default((FirFunction) firNamedFunctionSymbol.getFir(), null, 1, null), SpecialGenericSignatures.Companion.getREMOVE_AT_NAME_AND_SIGNATURE().getSignature());
    }
}
