package org.jetbrains.kotlin.fir.analysis.checkers;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"isOptionalAnnotationClass", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOptionalExpectationHelpersKt {
    public static final boolean isOptionalAnnotationClass(FirRegularClassSymbol firRegularClassSymbol, FirSession firSession) {
        firRegularClassSymbol.getClass();
        firSession.getClass();
        return firRegularClassSymbol.getClassKind() == ClassKind.ANNOTATION_CLASS && firRegularClassSymbol.getRawStatus().isExpect() && FirAnnotationUtilsKt.hasAnnotation(firRegularClassSymbol, StandardClassIds$Annotations.INSTANCE.getOptionalExpectation(), firSession);
    }
}
