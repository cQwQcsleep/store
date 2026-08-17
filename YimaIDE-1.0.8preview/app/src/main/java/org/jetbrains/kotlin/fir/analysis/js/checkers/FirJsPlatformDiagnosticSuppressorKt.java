package org.jetbrains.kotlin.fir.analysis.js.checkers;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JsStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u001d\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"isLexicallyInsideJsNative", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Z", "org.jetbrains.kotlin:checkers.js"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsPlatformDiagnosticSuppressorKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isLexicallyInsideJsNative(CheckerContext checkerContext, FirDeclaration firDeclaration) {
        Set set = JsStandardClassIds.Annotations.nativeAnnotations;
        if ((set instanceof Collection) && set.isEmpty()) {
            return false;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (FirHelpersKt.hasAnnotationOrInsideAnnotatedClass(firDeclaration, (ClassId) it.next(), checkerContext.getSession())) {
                return true;
            }
        }
        return false;
    }
}
