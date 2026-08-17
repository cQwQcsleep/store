package org.jetbrains.kotlin.fir.analysis.wasm.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.declaration.FirWasmExternalFileChecker;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.declaration.FirWasmJsAssociatedObjectChecker;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.declaration.FirWasmJsFunAnnotationChecker;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.declaration.FirWasmJsInteropTypesChecker;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.declaration.FirWasmJsModuleChecker;
import org.jetbrains.kotlin.fir.analysis.wasm.checkers.declaration.FirWasmJsNativeInvokeChecker;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirJsExportAnnotationChecker;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\n¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/wasm/checkers/WasmJsDeclarationCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "<init>", "()V", "basicDeclarationCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "getBasicDeclarationCheckers", "()Ljava/util/Set;", "simpleFunctionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirSimpleFunctionChecker;", "getSimpleFunctionCheckers", "org.jetbrains.kotlin:checkers.wasm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class WasmJsDeclarationCheckers extends DeclarationCheckers {
    public static final WasmJsDeclarationCheckers INSTANCE = new WasmJsDeclarationCheckers();

    private WasmJsDeclarationCheckers() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirDeclaration>> getBasicDeclarationCheckers() {
        return SetsKt.setOf(new FirDeclarationChecker[]{FirWasmJsInteropTypesChecker.INSTANCE, FirWasmJsFunAnnotationChecker.INSTANCE, FirJsExportAnnotationChecker.INSTANCE, FirWasmJsModuleChecker.INSTANCE, FirWasmExternalFileChecker.INSTANCE, FirWasmJsAssociatedObjectChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirNamedFunction>> getSimpleFunctionCheckers() {
        return SetsKt.setOf(FirWasmJsNativeInvokeChecker.INSTANCE);
    }
}
