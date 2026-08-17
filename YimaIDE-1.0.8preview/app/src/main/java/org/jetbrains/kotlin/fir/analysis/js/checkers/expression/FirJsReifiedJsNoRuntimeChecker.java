package org.jetbrains.kotlin.fir.analysis.js.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsWebCheckerUtils;
import org.jetbrains.kotlin.fir.analysis.web.common.checkers.expression.FirAbstractReifiedOnDeclarationWithoutRuntimeChecker;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.name.JsStandardClassIds;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\u00020\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/expression/FirJsReifiedJsNoRuntimeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/web/common/checkers/expression/FirAbstractReifiedOnDeclarationWithoutRuntimeChecker;", "<init>", "()V", "isDeclarationWithoutRuntime", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;)Z", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsReifiedJsNoRuntimeChecker extends FirAbstractReifiedOnDeclarationWithoutRuntimeChecker {
    public static final FirJsReifiedJsNoRuntimeChecker INSTANCE = new FirJsReifiedJsNoRuntimeChecker();

    private FirJsReifiedJsNoRuntimeChecker() {
        super(FirJsWebCheckerUtils.INSTANCE, FirJsErrors.INSTANCE.getJS_NO_RUNTIME_INTERFACE_AS_REIFIED_TYPE_ARGUMENT());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.analysis.web.common.checkers.expression.FirAbstractReifiedOnDeclarationWithoutRuntimeChecker
    public boolean isDeclarationWithoutRuntime(CheckerContext checkerContext, FirClassifierSymbol<?> firClassifierSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        checkerContext.getClass();
        firClassifierSymbol.getClass();
        Object fir = firClassifierSymbol.getFir();
        FirClass firClass = fir instanceof FirClass ? (FirClass) fir : null;
        return firClass != null && firClass.getClassKind() == ClassKind.INTERFACE && FirAnnotationUtilsKt.hasAnnotation((FirDeclaration) firClass, JsStandardClassIds.Annotations.JsNoRuntime, checkerContext.getSession());
    }
}
