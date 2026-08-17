package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic;", Argument.Delimiters.none, "<init>", "()V", "IllegalOperatorDiagnostic", "DeprecatedOperatorDiagnostic", "Unsupported", "ReturnTypeMismatchWithOuterClass", "Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic$DeprecatedOperatorDiagnostic;", "Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic$IllegalOperatorDiagnostic;", "Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic$ReturnTypeMismatchWithOuterClass;", "Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic$Unsupported;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class OperatorDiagnostic {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic$DeprecatedOperatorDiagnostic;", "Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic;", "message", Argument.Delimiters.none, "feature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/kotlin/config/LanguageFeature;)V", "getMessage", "()Ljava/lang/String;", "getFeature", "()Lorg/jetbrains/kotlin/config/LanguageFeature;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DeprecatedOperatorDiagnostic extends OperatorDiagnostic {
        private final LanguageFeature feature;
        private final String message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DeprecatedOperatorDiagnostic(String str, LanguageFeature languageFeature) {
            super(null);
            str.getClass();
            languageFeature.getClass();
            this.message = str;
            this.feature = languageFeature;
        }

        public final LanguageFeature getFeature() {
            return this.feature;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic$IllegalOperatorDiagnostic;", "Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic;", "message", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IllegalOperatorDiagnostic extends OperatorDiagnostic {
        private final String message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IllegalOperatorDiagnostic(String str) {
            super(null);
            str.getClass();
            this.message = str;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic$Unsupported;", "Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic;", "feature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "<init>", "(Lorg/jetbrains/kotlin/config/LanguageFeature;)V", "getFeature", "()Lorg/jetbrains/kotlin/config/LanguageFeature;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Unsupported extends OperatorDiagnostic {
        private final LanguageFeature feature;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Unsupported(LanguageFeature languageFeature) {
            super(null);
            languageFeature.getClass();
            this.feature = languageFeature;
        }

        public final LanguageFeature getFeature() {
            return this.feature;
        }
    }

    public /* synthetic */ OperatorDiagnostic(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private OperatorDiagnostic() {
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic$ReturnTypeMismatchWithOuterClass;", "Lorg/jetbrains/kotlin/fir/declarations/OperatorDiagnostic;", "outer", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "dueToNullability", Argument.Delimiters.none, "dueToFlexibility", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;ZZ)V", "getOuter", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getDueToNullability", "()Z", "getDueToFlexibility", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ReturnTypeMismatchWithOuterClass extends OperatorDiagnostic {
        private final boolean dueToFlexibility;
        private final boolean dueToNullability;
        private final FirRegularClassSymbol outer;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ReturnTypeMismatchWithOuterClass(FirRegularClassSymbol firRegularClassSymbol, boolean z, boolean z2) {
            super(null);
            firRegularClassSymbol.getClass();
            this.outer = firRegularClassSymbol;
            this.dueToNullability = z;
            this.dueToFlexibility = z2;
            if (z && z2) {
                w01.a("Failed requirement.");
                throw null;
            }
        }

        public final boolean getDueToFlexibility() {
            return this.dueToFlexibility;
        }

        public final boolean getDueToNullability() {
            return this.dueToNullability;
        }

        public final FirRegularClassSymbol getOuter() {
            return this.outer;
        }

        public /* synthetic */ ReturnTypeMismatchWithOuterClass(FirRegularClassSymbol firRegularClassSymbol, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(firRegularClassSymbol, (i & 2) != 0 ? false : z, (i & 4) != 0 ? false : z2);
        }
    }
}
