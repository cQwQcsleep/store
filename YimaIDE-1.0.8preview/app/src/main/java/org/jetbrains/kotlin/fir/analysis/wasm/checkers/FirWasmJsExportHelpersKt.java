package org.jetbrains.kotlin.fir.analysis.wasm.checkers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.WebCommonStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"isJsExportedDeclaration", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:checkers.wasm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWasmJsExportHelpersKt {
    public static final boolean isJsExportedDeclaration(FirDeclaration firDeclaration, FirSession firSession) {
        firDeclaration.getClass();
        firSession.getClass();
        if (!(firDeclaration instanceof FirNamedFunction) || !Intrinsics.areEqual(((FirMemberDeclaration) firDeclaration).getStatus().getVisibility(), Visibilities.Public.INSTANCE)) {
            return false;
        }
        ClassId classId = WebCommonStandardClassIds.Annotations.JsExport;
        if (FirAnnotationUtilsKt.hasAnnotation(firDeclaration, classId, firSession)) {
            return true;
        }
        FirFile firCallableContainerFile = FirProviderKt.getFirProvider(firSession).getFirCallableContainerFile(((FirNamedFunction) firDeclaration).getSymbol());
        return firCallableContainerFile != null && FirAnnotationUtilsKt.hasAnnotation((FirDeclaration) firCallableContainerFile, classId, firSession);
    }
}
