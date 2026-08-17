package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0080\b\u0018\u00002\u00020\u0001B\u001b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\f\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0014\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/ObjCExportMetaAnnotations;", Argument.Delimiters.none, "hidesFromObjCAnnotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "refinesInSwiftAnnotation", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)V", "getHidesFromObjCAnnotation", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getRefinesInSwiftAnnotation", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ObjCExportMetaAnnotations {
    private final FirAnnotation hidesFromObjCAnnotation;
    private final FirAnnotation refinesInSwiftAnnotation;

    public ObjCExportMetaAnnotations(FirAnnotation firAnnotation, FirAnnotation firAnnotation2) {
        this.hidesFromObjCAnnotation = firAnnotation;
        this.refinesInSwiftAnnotation = firAnnotation2;
    }

    public static /* synthetic */ ObjCExportMetaAnnotations copy$default(ObjCExportMetaAnnotations objCExportMetaAnnotations, FirAnnotation firAnnotation, FirAnnotation firAnnotation2, int i, Object obj) {
        if ((i & 1) != 0) {
            firAnnotation = objCExportMetaAnnotations.hidesFromObjCAnnotation;
        }
        if ((i & 2) != 0) {
            firAnnotation2 = objCExportMetaAnnotations.refinesInSwiftAnnotation;
        }
        return objCExportMetaAnnotations.copy(firAnnotation, firAnnotation2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FirAnnotation getHidesFromObjCAnnotation() {
        return this.hidesFromObjCAnnotation;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FirAnnotation getRefinesInSwiftAnnotation() {
        return this.refinesInSwiftAnnotation;
    }

    public final ObjCExportMetaAnnotations copy(FirAnnotation hidesFromObjCAnnotation, FirAnnotation refinesInSwiftAnnotation) {
        return new ObjCExportMetaAnnotations(hidesFromObjCAnnotation, refinesInSwiftAnnotation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ObjCExportMetaAnnotations)) {
            return false;
        }
        ObjCExportMetaAnnotations objCExportMetaAnnotations = (ObjCExportMetaAnnotations) other;
        return Intrinsics.areEqual(this.hidesFromObjCAnnotation, objCExportMetaAnnotations.hidesFromObjCAnnotation) && Intrinsics.areEqual(this.refinesInSwiftAnnotation, objCExportMetaAnnotations.refinesInSwiftAnnotation);
    }

    public final FirAnnotation getHidesFromObjCAnnotation() {
        return this.hidesFromObjCAnnotation;
    }

    public final FirAnnotation getRefinesInSwiftAnnotation() {
        return this.refinesInSwiftAnnotation;
    }

    public int hashCode() {
        FirAnnotation firAnnotation = this.hidesFromObjCAnnotation;
        int iHashCode = (firAnnotation == null ? 0 : firAnnotation.hashCode()) * 31;
        FirAnnotation firAnnotation2 = this.refinesInSwiftAnnotation;
        return iHashCode + (firAnnotation2 != null ? firAnnotation2.hashCode() : 0);
    }

    public String toString() {
        return "ObjCExportMetaAnnotations(hidesFromObjCAnnotation=" + this.hidesFromObjCAnnotation + ", refinesInSwiftAnnotation=" + this.refinesInSwiftAnnotation + ')';
    }
}
