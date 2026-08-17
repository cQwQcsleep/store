package org.jetbrains.kotlin.fir.analysis.jvm.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirAccidentalOverrideClashChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirImplementationByDelegationWithDifferentGenericSignatureChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirIncompatibleAnnotationsChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJavaClassInheritsKtPrivateClassDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmConflictsChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmDefaultChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmExposeBoxedChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmExternalDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmFieldApplicabilityChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmFunctionDelegateMemberNameClashChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmInlineApplicabilityChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmInvalidAndDangerousCharactersChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmNameChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmRecordChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmRedundantRepeatableChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmStaticChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmSyntheticApplicabilityChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmThrowsChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirJvmVersionOverloadsChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirOverloadsChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirOverrideJavaNullabilityWarningChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirPropertyHidesJavaFieldChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirPropertyJavaNullabilityWarningChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirRepeatableAnnotationChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirStrictfpApplicabilityChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirSynchronizedAnnotationChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirUpperBoundsChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.declaration.FirValueParameterJavaNullabilityWarningChecker;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR$\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u0006j\u0002`\u00110\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\nR$\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0006j\u0002`\u00150\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\nR$\u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00180\u0006j\u0002`\u00190\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\nR$\u0010\u001b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001c0\u0006j\u0002`\u001d0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\nR$\u0010\u001f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020 0\u0006j\u0002`!0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\nR$\u0010#\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020$0\u0006j\u0002`%0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\nR$\u0010'\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020(0\u0006j\u0002`)0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\nR$\u0010+\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020,0\u0006j\u0002`-0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\nR$\u0010/\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002000\u0006j\u0002`10\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\n¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/JvmDeclarationCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "<init>", "()V", "basicDeclarationCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "getBasicDeclarationCheckers", "()Ljava/util/Set;", "constructorCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirConstructorChecker;", "getConstructorCheckers", "classCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "getClassCheckers", "regularClassCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "getRegularClassCheckers", "classLikeCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassLikeChecker;", "getClassLikeCheckers", "callableDeclarationCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCallableDeclarationChecker;", "getCallableDeclarationCheckers", "propertyCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "getPropertyCheckers", "functionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "getFunctionCheckers", "typeParameterCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeParameterChecker;", "getTypeParameterCheckers", "simpleFunctionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirSimpleFunctionChecker;", "getSimpleFunctionCheckers", "valueParameterCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirValueParameterChecker;", "getValueParameterCheckers", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmDeclarationCheckers extends DeclarationCheckers {
    public static final JvmDeclarationCheckers INSTANCE = new JvmDeclarationCheckers();

    private JvmDeclarationCheckers() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirDeclaration>> getBasicDeclarationCheckers() {
        return SetsKt.setOf(new FirDeclarationChecker[]{FirJvmExternalDeclarationChecker.INSTANCE, FirJvmNameChecker.INSTANCE, FirJvmExposeBoxedChecker.INSTANCE, FirJvmDefaultChecker.INSTANCE, FirJvmStaticChecker.INSTANCE, FirRepeatableAnnotationChecker.INSTANCE, FirJvmInvalidAndDangerousCharactersChecker.INSTANCE, FirJvmRedundantRepeatableChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirCallableDeclaration>> getCallableDeclarationCheckers() {
        return SetsKt.setOf(FirJvmFunctionDelegateMemberNameClashChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirClass>> getClassCheckers() {
        return SetsKt.setOf(new FirDeclarationChecker[]{FirStrictfpApplicabilityChecker.INSTANCE, FirOverrideJavaNullabilityWarningChecker.Regular.INSTANCE, FirOverrideJavaNullabilityWarningChecker.ForExpectClass.INSTANCE, FirImplementationByDelegationWithDifferentGenericSignatureChecker.INSTANCE, FirPropertyHidesJavaFieldChecker.INSTANCE, FirIncompatibleAnnotationsChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirClassLikeDeclaration>> getClassLikeCheckers() {
        return SetsKt.setOf(FirJvmConflictsChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirConstructor>> getConstructorCheckers() {
        return SetsKt.setOf(FirJavaClassInheritsKtPrivateClassDeclarationChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirFunction>> getFunctionCheckers() {
        return SetsKt.setOf(new FirDeclarationChecker[]{FirSynchronizedAnnotationChecker.INSTANCE, FirOverloadsChecker.INSTANCE, FirJvmThrowsChecker.INSTANCE, FirJvmVersionOverloadsChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirProperty>> getPropertyCheckers() {
        return SetsKt.setOf(new FirDeclarationChecker[]{FirJvmFieldApplicabilityChecker.INSTANCE, FirJvmSyntheticApplicabilityChecker.INSTANCE, FirPropertyJavaNullabilityWarningChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirRegularClass>> getRegularClassCheckers() {
        return SetsKt.setOf(new FirDeclarationChecker[]{FirJvmRecordChecker.INSTANCE, FirJvmInlineApplicabilityChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirNamedFunction>> getSimpleFunctionCheckers() {
        return SetsKt.setOf(FirAccidentalOverrideClashChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirTypeParameter>> getTypeParameterCheckers() {
        return SetsKt.setOf(FirUpperBoundsChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirValueParameter>> getValueParameterCheckers() {
        return SetsKt.setOf(FirValueParameterJavaNullabilityWarningChecker.INSTANCE);
    }
}
