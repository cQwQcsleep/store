package org.jetbrains.kotlin.fir.lightTree.fir;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/fir/PrimaryConstructor;", Argument.Delimiters.none, "firConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "valueParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/lightTree/fir/ValueParameter;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;Ljava/util/List;)V", "getFirConstructor", "()Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "getValueParameters", "()Ljava/util/List;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class PrimaryConstructor {
    private final FirConstructor firConstructor;
    private final List<ValueParameter> valueParameters;

    public PrimaryConstructor(FirConstructor firConstructor, List<ValueParameter> list) {
        firConstructor.getClass();
        list.getClass();
        this.firConstructor = firConstructor;
        this.valueParameters = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PrimaryConstructor copy$default(PrimaryConstructor primaryConstructor, FirConstructor firConstructor, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            firConstructor = primaryConstructor.firConstructor;
        }
        if ((i & 2) != 0) {
            list = primaryConstructor.valueParameters;
        }
        return primaryConstructor.copy(firConstructor, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FirConstructor getFirConstructor() {
        return this.firConstructor;
    }

    public final List<ValueParameter> component2() {
        return this.valueParameters;
    }

    public final PrimaryConstructor copy(FirConstructor firConstructor, List<ValueParameter> valueParameters) {
        firConstructor.getClass();
        valueParameters.getClass();
        return new PrimaryConstructor(firConstructor, valueParameters);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PrimaryConstructor)) {
            return false;
        }
        PrimaryConstructor primaryConstructor = (PrimaryConstructor) other;
        return Intrinsics.areEqual(this.firConstructor, primaryConstructor.firConstructor) && Intrinsics.areEqual(this.valueParameters, primaryConstructor.valueParameters);
    }

    public final FirConstructor getFirConstructor() {
        return this.firConstructor;
    }

    public final List<ValueParameter> getValueParameters() {
        return this.valueParameters;
    }

    public int hashCode() {
        return (this.firConstructor.hashCode() * 31) + this.valueParameters.hashCode();
    }

    public String toString() {
        return "PrimaryConstructor(firConstructor=" + this.firConstructor + ", valueParameters=" + this.valueParameters + ')';
    }
}
