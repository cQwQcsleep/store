package org.jetbrains.kotlin.fir.resolve;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&J(\u0010\u000b\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\r\u0018\u00010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\n0\tH&¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirQualifierResolver;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "resolveSymbolWithPrefix", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassifierSymbol;", "prefix", "Lorg/jetbrains/kotlin/name/ClassId;", "remainingParts", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirQualifierPart;", "resolveFullyQualifiedSymbol", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "parts", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirQualifierResolver implements FirSessionComponent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public abstract Pair<FirClassifierSymbol<?>, FirResolvedSymbolOrigin> resolveFullyQualifiedSymbol(List<? extends FirQualifierPart> parts);

    public abstract FirClassifierSymbol<?> resolveSymbolWithPrefix(ClassId prefix, List<? extends FirQualifierPart> remainingParts);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0004\u001a\u00020\u0005R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\bJ\u0017\u0010\t\u001a\u00020\u0005R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirQualifierResolver$Companion;", Argument.Delimiters.none, "<init>", "()V", "isRootIdePackageAllowed", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionHolder;", "<unused var>", "(Lorg/jetbrains/kotlin/fir/SessionHolder;)Z", "isRootIdePackageDeprecated", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean isRootIdePackageAllowed(SessionHolder sessionHolder) {
            sessionHolder.getClass();
            return LanguageVersionUtilsKt.isSet(sessionHolder, AnalysisFlags.getIdeMode()) || LanguageVersionUtilsKt.isDisabled(sessionHolder, LanguageFeature.ForbidRootIdePackageInCli);
        }

        public final boolean isRootIdePackageDeprecated(SessionHolder sessionHolder) {
            sessionHolder.getClass();
            return !LanguageVersionUtilsKt.isSet(sessionHolder, AnalysisFlags.getIdeMode()) && LanguageVersionUtilsKt.isDisabled(sessionHolder, LanguageFeature.ForbidRootIdePackageInCli);
        }

        private Companion() {
        }
    }
}
