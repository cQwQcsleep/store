package org.jetbrains.kotlin.fir.analysis.js.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsActualExternalInterfaceSuggestJsNoRuntimeChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsBuiltinNameClashChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsDynamicDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsExportDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsExportedActualMatchExpectChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsExternalChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsExternalFileChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsExternalInheritorOnlyChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsInheritanceClassChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsInheritanceFunctionChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsModuleChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsMultipleInheritanceChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsNameCharsChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsNameChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsNameClashClassMembersChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsNameClashFileTopLevelDeclarationsChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsNativeGetterChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsNativeInvokeChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsNativeSetterChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsNoRuntimeDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsPackageDirectiveChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsPropertyDelegationByDynamicChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsRuntimeAnnotationChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsStaticChecker;
import org.jetbrains.kotlin.fir.analysis.js.checkers.declaration.FirJsSymbolChecker;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirJsExportAnnotationChecker;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirMultipleJsExportDefaultAnnotationChecker;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonAbstractNativeAnnotationChecker;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonExternalPropertyAccessorChecker;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR$\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u0006j\u0002`\u00110\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\nR$\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0006j\u0002`\u00150\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\nR$\u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00180\u0006j\u0002`\u00190\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\nR$\u0010\u001b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001c0\u0006j\u0002`\u001d0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\nR$\u0010\u001f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020 0\u0006j\u0002`!0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\n¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/JsDeclarationCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "<init>", "()V", "functionCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "getFunctionCheckers", "()Ljava/util/Set;", "basicDeclarationCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "getBasicDeclarationCheckers", "classCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "getClassCheckers", "simpleFunctionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirSimpleFunctionChecker;", "getSimpleFunctionCheckers", "propertyCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "getPropertyCheckers", "fileCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFileChecker;", "getFileCheckers", "propertyAccessorCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyAccessorChecker;", "getPropertyAccessorCheckers", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JsDeclarationCheckers extends DeclarationCheckers {
    public static final JsDeclarationCheckers INSTANCE = new JsDeclarationCheckers();

    private JsDeclarationCheckers() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirDeclaration>> getBasicDeclarationCheckers() {
        return SetsKt.setOf(new FirDeclarationChecker[]{FirJsModuleChecker.INSTANCE, FirJsRuntimeAnnotationChecker.INSTANCE, FirJsExternalChecker.INSTANCE, FirJsExternalFileChecker.INSTANCE, FirJsNameChecker.INSTANCE, FirJsSymbolChecker.INSTANCE, FirJsExportAnnotationChecker.INSTANCE, FirJsExportDeclarationChecker.INSTANCE, FirJsExportedActualMatchExpectChecker.INSTANCE, FirJsActualExternalInterfaceSuggestJsNoRuntimeChecker.INSTANCE, FirJsBuiltinNameClashChecker.INSTANCE, FirJsNameCharsChecker.INSTANCE, FirJsStaticChecker.INSTANCE, FirJsNoRuntimeDeclarationChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirClass>> getClassCheckers() {
        return SetsKt.setOf(new FirDeclarationChecker[]{FirJsMultipleInheritanceChecker.Regular.INSTANCE, FirJsMultipleInheritanceChecker.ForExpectClass.INSTANCE, FirJsDynamicDeclarationChecker.INSTANCE, FirJsInheritanceClassChecker.Regular.INSTANCE, FirJsInheritanceClassChecker.ForExpectClass.INSTANCE, FirJsExternalInheritorOnlyChecker.Regular.INSTANCE, FirJsExternalInheritorOnlyChecker.ForExpectClass.INSTANCE, FirJsNameClashClassMembersChecker.Regular.INSTANCE, FirJsNameClashClassMembersChecker.ForExpectClass.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirFile>> getFileCheckers() {
        return SetsKt.setOf(new FirDeclarationChecker[]{FirJsPackageDirectiveChecker.INSTANCE, FirMultipleJsExportDefaultAnnotationChecker.INSTANCE, FirJsNameClashFileTopLevelDeclarationsChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirFunction>> getFunctionCheckers() {
        return SetsKt.setOf(new FirJsInheritanceFunctionChecker[]{FirJsInheritanceFunctionChecker.Regular.INSTANCE, FirJsInheritanceFunctionChecker.ForExpectClass.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirPropertyAccessor>> getPropertyAccessorCheckers() {
        return SetsKt.setOf(FirWebCommonExternalPropertyAccessorChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirProperty>> getPropertyCheckers() {
        return SetsKt.setOf(FirJsPropertyDelegationByDynamicChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirNamedFunction>> getSimpleFunctionCheckers() {
        return SetsKt.setOf(new FirWebCommonAbstractNativeAnnotationChecker[]{FirJsNativeInvokeChecker.INSTANCE, FirJsNativeGetterChecker.INSTANCE, FirJsNativeSetterChecker.INSTANCE});
    }
}
