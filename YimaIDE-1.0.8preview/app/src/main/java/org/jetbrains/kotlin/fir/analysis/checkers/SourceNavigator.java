package org.jetbrains.kotlin.fir.analysis.checkers;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013J\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H&J\f\u0010\u0005\u001a\u00020\u0003*\u00020\u0004H&J\u000e\u0010\u0006\u001a\u0004\u0018\u00010\u0007*\u00020\bH&J\u000e\u0010\t\u001a\u0004\u0018\u00010\n*\u00020\u000bH&J\f\u0010\f\u001a\u00020\u0003*\u00020\rH&J\f\u0010\u000e\u001a\u00020\u0003*\u00020\u0004H&J\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003*\u00020\u0010H&¢\u0006\u0002\u0010\u0011J\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0003*\u00020\u0010H&¢\u0006\u0002\u0010\u0011ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/SourceNavigator;", Argument.Delimiters.none, "isInConstructorCallee", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "isInTypeConstraint", "getRawIdentifier", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceElement;", "getRawName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "isCatchElementParameter", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "isRedundantNullable", "hasBody", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "(Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;)Ljava/lang/Boolean;", "hasInitializer", "Companion", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface SourceNavigator {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u000bJ4\u0010\f\u001a\u0002H\r\"\u0004\b\u0000\u0010\r*\u00020\t2\u0017\u0010\u000e\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\r0\u000f¢\u0006\u0002\b\u0010H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/SourceNavigator$Companion;", Argument.Delimiters.none, "<init>", "()V", "lightTreeInstance", "Lorg/jetbrains/kotlin/fir/analysis/checkers/LightTreeSourceNavigator;", "forElement", "Lorg/jetbrains/kotlin/fir/analysis/checkers/SourceNavigator;", "e", "Lorg/jetbrains/kotlin/fir/FirElement;", "forSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "withNavigator", "R", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lorg/jetbrains/kotlin/fir/FirElement;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final LightTreeSourceNavigator lightTreeInstance = new LightTreeSourceNavigator();

        private Companion() {
        }

        public final SourceNavigator forElement(FirElement e) {
            e.getClass();
            return forSource(e.getSource());
        }

        public final SourceNavigator forSource(KtSourceElement e) {
            if (e instanceof KtLightSourceElement) {
                return lightTreeInstance;
            }
            if (e instanceof KtPsiSourceElement) {
                return PsiSourceNavigator.INSTANCE;
            }
            if (e == null) {
                return lightTreeInstance;
            }
            bu8.a();
            return null;
        }

        public final <R> R withNavigator(FirElement firElement, Function1<? super SourceNavigator, ? extends R> function1) {
            firElement.getClass();
            function1.getClass();
            return (R) function1.invoke(forSource(firElement.getSource()));
        }
    }

    CharSequence getRawIdentifier(KtSourceElement ktSourceElement);

    String getRawName(FirDeclaration firDeclaration);

    Boolean hasBody(FirEnumEntry firEnumEntry);

    Boolean hasInitializer(FirEnumEntry firEnumEntry);

    boolean isCatchElementParameter(FirValueParameterSymbol firValueParameterSymbol);

    boolean isInConstructorCallee(FirTypeRef firTypeRef);

    boolean isInTypeConstraint(FirTypeRef firTypeRef);

    boolean isRedundantNullable(FirTypeRef firTypeRef);
}
