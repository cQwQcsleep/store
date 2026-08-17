package org.jetbrains.kotlin.fir.builder;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J%\u0010\u0016\u001a\u00020\u0017*\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bH&¢\u0006\u0002\u0010\u001cJ \u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u00112\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001e0!H\u0016R\u0016\u0010\u0003\u001a\u00020\u0004*\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0016\u0010\u0007\u001a\u00020\b*\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0018\u0010\u000b\u001a\u0004\u0018\u00010\b*\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0016\u0010\r\u001a\u00020\u000e*\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000fR\u0016\u0010\u0010\u001a\u00020\u0011*\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0014\u001a\u0004\u0018\u00010\u0011*\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\"À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/DestructuringContext;", "T", Argument.Delimiters.none, "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getReturnTypeRef", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/name/Name;", "initializerName", "getInitializerName", "isVar", Argument.Delimiters.none, "(Ljava/lang/Object;)Z", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/KtSourceElement;", "initializerSource", "getInitializerSource", "extractAnnotationsTo", Argument.Delimiters.none, "target", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "containerSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "interceptExpressionBuilding", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "sourceElement", "buildExpression", "Lkotlin/Function0;", "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface DestructuringContext<T> {
    void extractAnnotationsTo(T t, FirAnnotationContainerBuilder firAnnotationContainerBuilder, FirBasedSymbol<?> firBasedSymbol);

    Name getInitializerName(T t);

    KtSourceElement getInitializerSource(T t);

    Name getName(T t);

    FirTypeRef getReturnTypeRef(T t);

    KtSourceElement getSource(T t);

    default FirExpression interceptExpressionBuilding(KtSourceElement sourceElement, Function0<? extends FirExpression> buildExpression) {
        buildExpression.getClass();
        return (FirExpression) buildExpression.invoke();
    }

    boolean isVar(T t);
}
