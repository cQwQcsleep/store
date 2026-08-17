package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001:\u0002\u0005\u0006R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirGenericArrayClassLiteralSupport;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "isEnabled", Argument.Delimiters.none, "()Z", "Enabled", "Disabled", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirGenericArrayClassLiteralSupport extends FirSessionComponent {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirGenericArrayClassLiteralSupport$Disabled;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirGenericArrayClassLiteralSupport;", "<init>", "()V", "isEnabled", Argument.Delimiters.none, "()Z", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Disabled implements FirGenericArrayClassLiteralSupport {
        public static final Disabled INSTANCE = new Disabled();
        private static final boolean isEnabled = false;

        private Disabled() {
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirGenericArrayClassLiteralSupport
        public boolean isEnabled() {
            return isEnabled;
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirGenericArrayClassLiteralSupport$Enabled;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirGenericArrayClassLiteralSupport;", "<init>", "()V", "isEnabled", Argument.Delimiters.none, "()Z", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Enabled implements FirGenericArrayClassLiteralSupport {
        public static final Enabled INSTANCE = new Enabled();
        private static final boolean isEnabled = true;

        private Enabled() {
        }

        @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirGenericArrayClassLiteralSupport
        public boolean isEnabled() {
            return isEnabled;
        }
    }

    boolean isEnabled();
}
