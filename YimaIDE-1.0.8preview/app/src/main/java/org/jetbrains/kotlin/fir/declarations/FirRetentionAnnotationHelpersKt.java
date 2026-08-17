package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.annotation.AnnotationRetention;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0006\u001a\u0014\u0010\u0007\u001a\u0004\u0018\u00010\u0006*\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\u0007\u001a\u0004\u0018\u00010\u0006*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0018\u0010\t\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\u000b\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\f"}, d2 = {"getRetention", "Lkotlin/annotation/AnnotationRetention;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getRetentionAnnotation", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getExplicitAnnotationRetention", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getAnnotationRetention", "org.jetbrains.kotlin:providers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirRetentionAnnotationHelpersKt {
    public static final AnnotationRetention getAnnotationRetention(FirClassLikeSymbol<?> firClassLikeSymbol, FirSession firSession) {
        firClassLikeSymbol.getClass();
        firSession.getClass();
        AnnotationRetention explicitAnnotationRetention = getExplicitAnnotationRetention(firClassLikeSymbol, firSession);
        return explicitAnnotationRetention == null ? AnnotationRetention.RUNTIME : explicitAnnotationRetention;
    }

    public static final AnnotationRetention getExplicitAnnotationRetention(FirClassLikeSymbol<?> firClassLikeSymbol, FirSession firSession) {
        firClassLikeSymbol.getClass();
        firSession.getClass();
        FirAnnotation annotationWithResolvedArgumentsByClassId = FirAnnotationUtilsKt.getAnnotationWithResolvedArgumentsByClassId(firClassLikeSymbol, StandardClassIds$Annotations.INSTANCE.getRetention(), firSession);
        if (annotationWithResolvedArgumentsByClassId != null) {
            return getRetention(annotationWithResolvedArgumentsByClassId);
        }
        return null;
    }

    public static final AnnotationRetention getRetention(FirAnnotation firAnnotation) {
        EnumValueArgumentInfo enumValueArgumentInfoExtractEnumValueArgumentInfo;
        firAnnotation.getClass();
        Object obj = null;
        FirExpression firExpressionFindArgumentByName$default = FirAnnotationUtilsKt.findArgumentByName$default(firAnnotation, StandardClassIds$Annotations.ParameterNames.INSTANCE.getRetentionValue(), false, 2, null);
        if (firExpressionFindArgumentByName$default == null || (enumValueArgumentInfoExtractEnumValueArgumentInfo = FirAnnotationUtilsKt.extractEnumValueArgumentInfo(firExpressionFindArgumentByName$default)) == null) {
            return null;
        }
        ClassId enumClassId = enumValueArgumentInfoExtractEnumValueArgumentInfo.getEnumClassId();
        Name enumEntryName = enumValueArgumentInfoExtractEnumValueArgumentInfo.getEnumEntryName();
        if (!Intrinsics.areEqual(enumClassId, StandardClassIds.INSTANCE.getAnnotationRetention())) {
            return null;
        }
        for (Object obj2 : AnnotationRetention.getEntries()) {
            if (Intrinsics.areEqual(((AnnotationRetention) obj2).name(), enumEntryName.asString())) {
                obj = obj2;
                break;
            }
        }
        return (AnnotationRetention) obj;
    }

    public static final FirAnnotation getRetentionAnnotation(FirDeclaration firDeclaration, FirSession firSession) {
        firDeclaration.getClass();
        firSession.getClass();
        return FirAnnotationUtilsKt.getAnnotationByClassId(firDeclaration, StandardClassIds$Annotations.INSTANCE.getRetention(), firSession);
    }

    private static final FirAnnotation getRetentionAnnotation(FirRegularClassSymbol firRegularClassSymbol, FirSession firSession) {
        return FirAnnotationUtilsKt.getAnnotationWithResolvedArgumentsByClassId(firRegularClassSymbol, StandardClassIds$Annotations.INSTANCE.getRetention(), firSession);
    }

    public static final AnnotationRetention getRetention(FirRegularClassSymbol firRegularClassSymbol, FirSession firSession) {
        AnnotationRetention retention;
        firRegularClassSymbol.getClass();
        firSession.getClass();
        FirAnnotation retentionAnnotation = getRetentionAnnotation(firRegularClassSymbol, firSession);
        return (retentionAnnotation == null || (retention = getRetention(retentionAnnotation)) == null) ? AnnotationRetention.RUNTIME : retention;
    }

    public static final AnnotationRetention getRetention(FirRegularClass firRegularClass, FirSession firSession) {
        AnnotationRetention retention;
        firRegularClass.getClass();
        firSession.getClass();
        FirAnnotation retentionAnnotation = getRetentionAnnotation(firRegularClass, firSession);
        return (retentionAnnotation == null || (retention = getRetention(retentionAnnotation)) == null) ? AnnotationRetention.RUNTIME : retention;
    }
}
