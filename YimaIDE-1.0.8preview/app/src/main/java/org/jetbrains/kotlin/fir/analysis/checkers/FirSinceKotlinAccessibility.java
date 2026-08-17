package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.ApiVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinAccessibility;", Argument.Delimiters.none, "<init>", "()V", "Accessible", "NotAccessibleButWasExperimental", "NotAccessible", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinAccessibility$Accessible;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinAccessibility$NotAccessible;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinAccessibility$NotAccessibleButWasExperimental;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirSinceKotlinAccessibility {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinAccessibility$Accessible;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinAccessibility;", "<init>", "()V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Accessible extends FirSinceKotlinAccessibility {
        public static final Accessible INSTANCE = new Accessible();

        private Accessible() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinAccessibility$NotAccessible;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinAccessibility;", "version", "Lorg/jetbrains/kotlin/config/ApiVersion;", "<init>", "(Lorg/jetbrains/kotlin/config/ApiVersion;)V", "getVersion", "()Lorg/jetbrains/kotlin/config/ApiVersion;", "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class NotAccessible extends FirSinceKotlinAccessibility {
        private final ApiVersion version;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NotAccessible(ApiVersion apiVersion) {
            super(null);
            apiVersion.getClass();
            this.version = apiVersion;
        }

        public static /* synthetic */ NotAccessible copy$default(NotAccessible notAccessible, ApiVersion apiVersion, int i, Object obj) {
            if ((i & 1) != 0) {
                apiVersion = notAccessible.version;
            }
            return notAccessible.copy(apiVersion);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ApiVersion getVersion() {
            return this.version;
        }

        public final NotAccessible copy(ApiVersion version) {
            version.getClass();
            return new NotAccessible(version);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof NotAccessible) && Intrinsics.areEqual(this.version, ((NotAccessible) other).version);
        }

        public final ApiVersion getVersion() {
            return this.version;
        }

        public int hashCode() {
            return this.version.hashCode();
        }

        public String toString() {
            return "NotAccessible(version=" + this.version + ')';
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinAccessibility$NotAccessibleButWasExperimental;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinAccessibility;", "version", "Lorg/jetbrains/kotlin/config/ApiVersion;", "markerClasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "<init>", "(Lorg/jetbrains/kotlin/config/ApiVersion;Ljava/util/List;)V", "getVersion", "()Lorg/jetbrains/kotlin/config/ApiVersion;", "getMarkerClasses", "()Ljava/util/List;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class NotAccessibleButWasExperimental extends FirSinceKotlinAccessibility {
        private final List<FirRegularClassSymbol> markerClasses;
        private final ApiVersion version;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public NotAccessibleButWasExperimental(ApiVersion apiVersion, List<FirRegularClassSymbol> list) {
            super(null);
            apiVersion.getClass();
            list.getClass();
            this.version = apiVersion;
            this.markerClasses = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ NotAccessibleButWasExperimental copy$default(NotAccessibleButWasExperimental notAccessibleButWasExperimental, ApiVersion apiVersion, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                apiVersion = notAccessibleButWasExperimental.version;
            }
            if ((i & 2) != 0) {
                list = notAccessibleButWasExperimental.markerClasses;
            }
            return notAccessibleButWasExperimental.copy(apiVersion, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final ApiVersion getVersion() {
            return this.version;
        }

        public final List<FirRegularClassSymbol> component2() {
            return this.markerClasses;
        }

        public final NotAccessibleButWasExperimental copy(ApiVersion version, List<FirRegularClassSymbol> markerClasses) {
            version.getClass();
            markerClasses.getClass();
            return new NotAccessibleButWasExperimental(version, markerClasses);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof NotAccessibleButWasExperimental)) {
                return false;
            }
            NotAccessibleButWasExperimental notAccessibleButWasExperimental = (NotAccessibleButWasExperimental) other;
            return Intrinsics.areEqual(this.version, notAccessibleButWasExperimental.version) && Intrinsics.areEqual(this.markerClasses, notAccessibleButWasExperimental.markerClasses);
        }

        public final List<FirRegularClassSymbol> getMarkerClasses() {
            return this.markerClasses;
        }

        public final ApiVersion getVersion() {
            return this.version;
        }

        public int hashCode() {
            return (this.version.hashCode() * 31) + this.markerClasses.hashCode();
        }

        public String toString() {
            return "NotAccessibleButWasExperimental(version=" + this.version + ", markerClasses=" + this.markerClasses + ')';
        }
    }

    public /* synthetic */ FirSinceKotlinAccessibility(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private FirSinceKotlinAccessibility() {
    }
}
