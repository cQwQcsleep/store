package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.ApiVersion;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/FirSinceKotlinValue;", Argument.Delimiters.none, "apiVersion", "Lorg/jetbrains/kotlin/config/ApiVersion;", "wasExperimentalMarkerClasses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "<init>", "(Lorg/jetbrains/kotlin/config/ApiVersion;Ljava/util/List;)V", "getApiVersion", "()Lorg/jetbrains/kotlin/config/ApiVersion;", "getWasExperimentalMarkerClasses", "()Ljava/util/List;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final /* data */ class FirSinceKotlinValue {
    private final ApiVersion apiVersion;
    private final List<FirRegularClassSymbol> wasExperimentalMarkerClasses;

    public FirSinceKotlinValue(ApiVersion apiVersion, List<FirRegularClassSymbol> list) {
        apiVersion.getClass();
        list.getClass();
        this.apiVersion = apiVersion;
        this.wasExperimentalMarkerClasses = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirSinceKotlinValue copy$default(FirSinceKotlinValue firSinceKotlinValue, ApiVersion apiVersion, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            apiVersion = firSinceKotlinValue.apiVersion;
        }
        if ((i & 2) != 0) {
            list = firSinceKotlinValue.wasExperimentalMarkerClasses;
        }
        return firSinceKotlinValue.copy(apiVersion, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ApiVersion getApiVersion() {
        return this.apiVersion;
    }

    public final List<FirRegularClassSymbol> component2() {
        return this.wasExperimentalMarkerClasses;
    }

    public final FirSinceKotlinValue copy(ApiVersion apiVersion, List<FirRegularClassSymbol> wasExperimentalMarkerClasses) {
        apiVersion.getClass();
        wasExperimentalMarkerClasses.getClass();
        return new FirSinceKotlinValue(apiVersion, wasExperimentalMarkerClasses);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FirSinceKotlinValue)) {
            return false;
        }
        FirSinceKotlinValue firSinceKotlinValue = (FirSinceKotlinValue) other;
        return Intrinsics.areEqual(this.apiVersion, firSinceKotlinValue.apiVersion) && Intrinsics.areEqual(this.wasExperimentalMarkerClasses, firSinceKotlinValue.wasExperimentalMarkerClasses);
    }

    public final ApiVersion getApiVersion() {
        return this.apiVersion;
    }

    public final List<FirRegularClassSymbol> getWasExperimentalMarkerClasses() {
        return this.wasExperimentalMarkerClasses;
    }

    public int hashCode() {
        return (this.apiVersion.hashCode() * 31) + this.wasExperimentalMarkerClasses.hashCode();
    }

    public String toString() {
        return "FirSinceKotlinValue(apiVersion=" + this.apiVersion + ", wasExperimentalMarkerClasses=" + this.wasExperimentalMarkerClasses + ')';
    }
}
