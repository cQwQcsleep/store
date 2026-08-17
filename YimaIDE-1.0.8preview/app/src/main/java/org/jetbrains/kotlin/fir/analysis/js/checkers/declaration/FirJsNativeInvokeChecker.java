package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.declaration.FirWebCommonAbstractNativeAnnotationChecker;
import org.jetbrains.kotlin.name.JsStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNativeInvokeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/declaration/FirWebCommonAbstractNativeAnnotationChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsNativeInvokeChecker extends FirWebCommonAbstractNativeAnnotationChecker {
    public static final FirJsNativeInvokeChecker INSTANCE = new FirJsNativeInvokeChecker();

    private FirJsNativeInvokeChecker() {
        super(JsStandardClassIds.Annotations.JsNativeInvoke, FirJsErrors.INSTANCE.getNATIVE_ANNOTATIONS_ALLOWED_ONLY_ON_MEMBER_OR_EXTENSION_FUN());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
