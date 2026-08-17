package org.jetbrains.kotlin.fir.analysis.jvm.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirDynamicUnsupportedChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.type.FirArrayOfNullableNothingTypeChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.type.FirFunctionalTypeParameterNameChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.type.FirJvmModuleAccessibilityTypeChecker;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/JvmTypeCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "<init>", "()V", "functionTypeRefCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirFunctionTypeRefChecker;", "getFunctionTypeRefCheckers", "()Ljava/util/Set;", "resolvedTypeRefCheckers", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirResolvedTypeRefChecker;", "getResolvedTypeRefCheckers", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmTypeCheckers extends TypeCheckers {
    public static final JvmTypeCheckers INSTANCE = new JvmTypeCheckers();
    private static final Set<FirTypeChecker<FirFunctionTypeRef>> functionTypeRefCheckers = SetsKt.setOf(FirFunctionalTypeParameterNameChecker.INSTANCE);
    private static final Set<FirTypeChecker<FirResolvedTypeRef>> resolvedTypeRefCheckers = SetsKt.setOf(new FirTypeChecker[]{FirDynamicUnsupportedChecker.INSTANCE, FirJvmModuleAccessibilityTypeChecker.INSTANCE, FirArrayOfNullableNothingTypeChecker.INSTANCE});

    private JvmTypeCheckers() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirFunctionTypeRef>> getFunctionTypeRefCheckers() {
        return functionTypeRefCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.type.TypeCheckers
    public Set<FirTypeChecker<FirResolvedTypeRef>> getResolvedTypeRefCheckers() {
        return resolvedTypeRefCheckers;
    }
}
