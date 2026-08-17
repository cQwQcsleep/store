package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u0000¨\u0006\u0006"}, d2 = {"findMetaAnnotations", "Lorg/jetbrains/kotlin/fir/analysis/native/checkers/ObjCExportMetaAnnotations;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:checkers.native"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeObjCRefinementAnnotationCheckerKt {
    public static final ObjCExportMetaAnnotations findMetaAnnotations(List<? extends FirAnnotation> list, FirSession firSession) {
        list.getClass();
        firSession.getClass();
        FirAnnotation firAnnotation = null;
        FirAnnotation firAnnotation2 = null;
        for (FirAnnotation firAnnotation3 : list) {
            ClassId annotationClassId = FirAnnotationUtilsKt.toAnnotationClassId(firAnnotation3, firSession);
            FirNativeObjCRefinementChecker firNativeObjCRefinementChecker = FirNativeObjCRefinementChecker.INSTANCE;
            if (Intrinsics.areEqual(annotationClassId, firNativeObjCRefinementChecker.getHidesFromObjCClassId())) {
                firAnnotation = firAnnotation3;
            } else if (Intrinsics.areEqual(annotationClassId, firNativeObjCRefinementChecker.getRefinesInSwiftClassId())) {
                firAnnotation2 = firAnnotation3;
            }
            if (firAnnotation != null && firAnnotation2 != null) {
                break;
            }
        }
        return new ObjCExportMetaAnnotations(firAnnotation, firAnnotation2);
    }
}
