package org.jetbrains.kotlin.fir.analysis.jvm.checkers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.AnnotationsPosition;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JvmStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR \u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00110\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/FirJvmAnnotationsPlatformSpecificSupportComponent;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnnotationsPlatformSpecificSupportComponent;", "<init>", "()V", "requiredAnnotationsWithArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "getRequiredAnnotationsWithArguments", "()Ljava/util/Set;", "requiredAnnotations", "getRequiredAnnotations", "volatileAnnotations", "getVolatileAnnotations", "repeatableAnnotations", "getRepeatableAnnotations", "deprecationAnnotationsWithOverridesPropagation", Argument.Delimiters.none, Argument.Delimiters.none, "getDeprecationAnnotationsWithOverridesPropagation", "()Ljava/util/Map;", "extractBackingFieldAnnotationsFromProperty", "Lorg/jetbrains/kotlin/fir/declarations/AnnotationsPosition;", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "propertyAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "backingFieldAnnotations", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmAnnotationsPlatformSpecificSupportComponent extends FirAnnotationsPlatformSpecificSupportComponent {
    public static final FirJvmAnnotationsPlatformSpecificSupportComponent INSTANCE;
    private static final Map<ClassId, Boolean> deprecationAnnotationsWithOverridesPropagation;
    private static final Set<ClassId> repeatableAnnotations;
    private static final Set<ClassId> requiredAnnotations;
    private static final Set<ClassId> requiredAnnotationsWithArguments;
    private static final Set<ClassId> volatileAnnotations;

    static {
        FirJvmAnnotationsPlatformSpecificSupportComponent firJvmAnnotationsPlatformSpecificSupportComponent = new FirJvmAnnotationsPlatformSpecificSupportComponent();
        INSTANCE = firJvmAnnotationsPlatformSpecificSupportComponent;
        JvmStandardClassIds.Annotations.Java java = JvmStandardClassIds.Annotations.Java.INSTANCE;
        ClassId target = java.getTarget();
        JvmStandardClassIds.Annotations annotations = JvmStandardClassIds.Annotations.INSTANCE;
        requiredAnnotationsWithArguments = SetsKt.setOf(new ClassId[]{target, annotations.getJvmName()});
        requiredAnnotations = SetsKt.plus(firJvmAnnotationsPlatformSpecificSupportComponent.getRequiredAnnotationsWithArguments(), SetsKt.setOf(new ClassId[]{java.getDeprecated(), annotations.getJvmRecord()}));
        volatileAnnotations = SetsKt.setOf(annotations.getJvmVolatile());
        repeatableAnnotations = SetsKt.setOf(new ClassId[]{java.getRepeatable(), annotations.getJvmRepeatable()});
        deprecationAnnotationsWithOverridesPropagation = MapsKt.mapOf(TuplesKt.to(java.getDeprecated(), Boolean.FALSE));
    }

    private FirJvmAnnotationsPlatformSpecificSupportComponent() {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent
    public AnnotationsPosition extractBackingFieldAnnotationsFromProperty(FirProperty property, FirSession session, List<? extends FirAnnotation> propertyAnnotations, List<? extends FirAnnotation> backingFieldAnnotations) {
        property.getClass();
        session.getClass();
        propertyAnnotations.getClass();
        backingFieldAnnotations.getClass();
        if (propertyAnnotations.isEmpty() || property.getBackingField() == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : propertyAnnotations) {
            if (Intrinsics.areEqual(FirAnnotationUtilsKt.toAnnotationClassIdSafe((FirAnnotation) obj, session), JvmStandardClassIds.Annotations.Java.INSTANCE.getDeprecated())) {
                arrayList.add(obj);
            } else {
                arrayList2.add(obj);
            }
        }
        Pair pair = new Pair(arrayList, arrayList2);
        List list = (List) pair.component1();
        List list2 = (List) pair.component2();
        if (list.isEmpty()) {
            return null;
        }
        return new AnnotationsPosition(CollectionsKt.plus(backingFieldAnnotations, list), list2);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent
    public Map<ClassId, Boolean> getDeprecationAnnotationsWithOverridesPropagation() {
        return deprecationAnnotationsWithOverridesPropagation;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent
    public Set<ClassId> getRepeatableAnnotations() {
        return repeatableAnnotations;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent
    public Set<ClassId> getRequiredAnnotations() {
        return requiredAnnotations;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent
    public Set<ClassId> getRequiredAnnotationsWithArguments() {
        return requiredAnnotationsWithArguments;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent
    public Set<ClassId> getVolatileAnnotations() {
        return volatileAnnotations;
    }
}
