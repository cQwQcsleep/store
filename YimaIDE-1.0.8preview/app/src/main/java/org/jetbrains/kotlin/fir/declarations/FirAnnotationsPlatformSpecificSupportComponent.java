package org.jetbrains.kotlin.fir.declarations;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirComposableSessionComponent;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionConfiguration;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u000212B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010 \u001a\u00020\u00162\n\u0010!\u001a\u0006\u0012\u0002\b\u00030\"2\u0006\u0010#\u001a\u00020$J:\u0010%\u001a\u0004\u0018\u00010&2\u0006\u0010'\u001a\u00020(2\u0006\u0010#\u001a\u00020$2\u000e\b\u0002\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020+0*H&J\u001a\u0010-\u001a\u00020.2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00000*H\u0017b\u0002\b0R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0018\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\bR\u0018\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¤\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\bR!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0011\u0010\bR\u001e\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00160\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R!\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u0013\u001a\u0004\b\u001a\u0010\bR!\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001f\u0010\u0013\u001a\u0004\b\u001e\u0010\b¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirAnnotationsPlatformSpecificSupportComponent;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "<init>", "()V", "requiredAnnotationsWithArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "getRequiredAnnotationsWithArguments", "()Ljava/util/Set;", "requiredAnnotations", "getRequiredAnnotations", "volatileAnnotations", "getVolatileAnnotations", "repeatableAnnotations", "getRepeatableAnnotations", "requiredAnnotationsShortClassNames", "Lorg/jetbrains/kotlin/name/Name;", "getRequiredAnnotationsShortClassNames", "requiredAnnotationsShortClassNames$delegate", "Lkotlin/Lazy;", "deprecationAnnotationsWithOverridesPropagation", Argument.Delimiters.none, Argument.Delimiters.none, "getDeprecationAnnotationsWithOverridesPropagation", "()Ljava/util/Map;", "deprecationAnnotations", "getDeprecationAnnotations", "deprecationAnnotations$delegate", "deprecationAnnotationsSimpleNames", Argument.Delimiters.none, "getDeprecationAnnotationsSimpleNames", "deprecationAnnotationsSimpleNames$delegate", "symbolContainsRepeatableAnnotation", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "extractBackingFieldAnnotationsFromProperty", "Lorg/jetbrains/kotlin/fir/declarations/AnnotationsPosition;", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "propertyAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "backingFieldAnnotations", "createComposed", "Lorg/jetbrains/kotlin/fir/declarations/FirAnnotationsPlatformSpecificSupportComponent$Composed;", "components", "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "Composed", "Default", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAnnotationsPlatformSpecificSupportComponent implements FirComposableSessionComponent<FirAnnotationsPlatformSpecificSupportComponent> {

    /* JADX INFO: renamed from: requiredAnnotationsShortClassNames$delegate, reason: from kotlin metadata */
    private final Lazy requiredAnnotationsShortClassNames = LazyKt.lazy(new Function0() { // from class: iy4
        public final Object invoke() {
            return FirAnnotationsPlatformSpecificSupportComponent.a(this.b);
        }
    });

    /* JADX INFO: renamed from: deprecationAnnotations$delegate, reason: from kotlin metadata */
    private final Lazy deprecationAnnotations = LazyKt.lazy(new Function0() { // from class: jy4
        public final Object invoke() {
            return FirAnnotationsPlatformSpecificSupportComponent.c(this.b);
        }
    });

    /* JADX INFO: renamed from: deprecationAnnotationsSimpleNames$delegate, reason: from kotlin metadata */
    private final Lazy deprecationAnnotationsSimpleNames = LazyKt.lazy(new Function0() { // from class: ky4
        public final Object invoke() {
            return FirAnnotationsPlatformSpecificSupportComponent.b(this.b);
        }
    });

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006J6\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00042\f\u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u0004H\u0016R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR \u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00160\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirAnnotationsPlatformSpecificSupportComponent$Composed;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnnotationsPlatformSpecificSupportComponent;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "components", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "requiredAnnotationsWithArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "getRequiredAnnotationsWithArguments", "()Ljava/util/Set;", "requiredAnnotations", "getRequiredAnnotations", "volatileAnnotations", "getVolatileAnnotations", "repeatableAnnotations", "getRepeatableAnnotations", "deprecationAnnotationsWithOverridesPropagation", Argument.Delimiters.none, Argument.Delimiters.none, "getDeprecationAnnotationsWithOverridesPropagation", "()Ljava/util/Map;", "extractBackingFieldAnnotationsFromProperty", "Lorg/jetbrains/kotlin/fir/declarations/AnnotationsPosition;", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "propertyAnnotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "backingFieldAnnotations", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends FirAnnotationsPlatformSpecificSupportComponent implements FirComposableSessionComponent.Composed<FirAnnotationsPlatformSpecificSupportComponent> {
        private final List<FirAnnotationsPlatformSpecificSupportComponent> components;
        private final Map<ClassId, Boolean> deprecationAnnotationsWithOverridesPropagation;
        private final Set<ClassId> repeatableAnnotations;
        private final Set<ClassId> requiredAnnotations;
        private final Set<ClassId> requiredAnnotationsWithArguments;
        private final Set<ClassId> volatileAnnotations;

        /* JADX WARN: Multi-variable type inference failed */
        public Composed(List<? extends FirAnnotationsPlatformSpecificSupportComponent> list) {
            list.getClass();
            this.components = list;
            List<FirAnnotationsPlatformSpecificSupportComponent> components = getComponents();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator<T> it = components.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(linkedHashSet, ((FirAnnotationsPlatformSpecificSupportComponent) it.next()).getRequiredAnnotationsWithArguments());
            }
            this.requiredAnnotationsWithArguments = linkedHashSet;
            List<FirAnnotationsPlatformSpecificSupportComponent> components2 = getComponents();
            LinkedHashSet linkedHashSet2 = new LinkedHashSet();
            Iterator<T> it2 = components2.iterator();
            while (it2.hasNext()) {
                CollectionsKt.addAll(linkedHashSet2, ((FirAnnotationsPlatformSpecificSupportComponent) it2.next()).getRequiredAnnotations());
            }
            this.requiredAnnotations = linkedHashSet2;
            List<FirAnnotationsPlatformSpecificSupportComponent> components3 = getComponents();
            LinkedHashSet linkedHashSet3 = new LinkedHashSet();
            Iterator<T> it3 = components3.iterator();
            while (it3.hasNext()) {
                CollectionsKt.addAll(linkedHashSet3, ((FirAnnotationsPlatformSpecificSupportComponent) it3.next()).getVolatileAnnotations());
            }
            this.volatileAnnotations = linkedHashSet3;
            List<FirAnnotationsPlatformSpecificSupportComponent> components4 = getComponents();
            LinkedHashSet linkedHashSet4 = new LinkedHashSet();
            Iterator<T> it4 = components4.iterator();
            while (it4.hasNext()) {
                CollectionsKt.addAll(linkedHashSet4, ((FirAnnotationsPlatformSpecificSupportComponent) it4.next()).getRepeatableAnnotations());
            }
            this.repeatableAnnotations = linkedHashSet4;
            Map mapCreateMapBuilder = MapsKt.createMapBuilder();
            Iterator<T> it5 = getComponents().iterator();
            while (it5.hasNext()) {
                mapCreateMapBuilder.putAll(((FirAnnotationsPlatformSpecificSupportComponent) it5.next()).getDeprecationAnnotationsWithOverridesPropagation());
            }
            this.deprecationAnnotationsWithOverridesPropagation = MapsKt.build(mapCreateMapBuilder);
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent
        public AnnotationsPosition extractBackingFieldAnnotationsFromProperty(FirProperty property, FirSession session, List<? extends FirAnnotation> propertyAnnotations, List<? extends FirAnnotation> backingFieldAnnotations) {
            property.getClass();
            session.getClass();
            propertyAnnotations.getClass();
            backingFieldAnnotations.getClass();
            Iterator<T> it = getComponents().iterator();
            while (it.hasNext()) {
                AnnotationsPosition annotationsPositionExtractBackingFieldAnnotationsFromProperty = ((FirAnnotationsPlatformSpecificSupportComponent) it.next()).extractBackingFieldAnnotationsFromProperty(property, session, propertyAnnotations, backingFieldAnnotations);
                if (annotationsPositionExtractBackingFieldAnnotationsFromProperty != null) {
                    return annotationsPositionExtractBackingFieldAnnotationsFromProperty;
                }
            }
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<FirAnnotationsPlatformSpecificSupportComponent> getComponents() {
            return this.components;
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent
        public Map<ClassId, Boolean> getDeprecationAnnotationsWithOverridesPropagation() {
            return this.deprecationAnnotationsWithOverridesPropagation;
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent
        public Set<ClassId> getRepeatableAnnotations() {
            return this.repeatableAnnotations;
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent
        public Set<ClassId> getRequiredAnnotations() {
            return this.requiredAnnotations;
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent
        public Set<ClassId> getRequiredAnnotationsWithArguments() {
            return this.requiredAnnotationsWithArguments;
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent
        public Set<ClassId> getVolatileAnnotations() {
            return this.volatileAnnotations;
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010$\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J6\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0016R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR \u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00110\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirAnnotationsPlatformSpecificSupportComponent$Default;", "Lorg/jetbrains/kotlin/fir/declarations/FirAnnotationsPlatformSpecificSupportComponent;", "<init>", "()V", "requiredAnnotationsWithArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "getRequiredAnnotationsWithArguments", "()Ljava/util/Set;", "requiredAnnotations", "getRequiredAnnotations", "volatileAnnotations", "getVolatileAnnotations", "repeatableAnnotations", "getRepeatableAnnotations", "deprecationAnnotationsWithOverridesPropagation", Argument.Delimiters.none, Argument.Delimiters.none, "getDeprecationAnnotationsWithOverridesPropagation", "()Ljava/util/Map;", "extractBackingFieldAnnotationsFromProperty", "Lorg/jetbrains/kotlin/fir/declarations/AnnotationsPosition;", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "propertyAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "backingFieldAnnotations", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default extends FirAnnotationsPlatformSpecificSupportComponent {
        public static final Default INSTANCE;
        private static final Map<ClassId, Boolean> deprecationAnnotationsWithOverridesPropagation;
        private static final Set<ClassId> repeatableAnnotations;
        private static final Set<ClassId> requiredAnnotations;
        private static final Set<ClassId> requiredAnnotationsWithArguments;
        private static final Set<ClassId> volatileAnnotations;

        static {
            Default r0 = new Default();
            INSTANCE = r0;
            StandardClassIds$Annotations standardClassIds$Annotations = StandardClassIds$Annotations.INSTANCE;
            requiredAnnotationsWithArguments = SetsKt.setOf(new ClassId[]{standardClassIds$Annotations.getDeprecated(), standardClassIds$Annotations.getTarget(), standardClassIds$Annotations.getDeprecatedSinceKotlin(), standardClassIds$Annotations.getSinceKotlin(), standardClassIds$Annotations.getIntroducedAt()});
            requiredAnnotations = SetsKt.plus(r0.getRequiredAnnotationsWithArguments(), SetsKt.setOf(standardClassIds$Annotations.getWasExperimental()));
            volatileAnnotations = SetsKt.setOf(standardClassIds$Annotations.getVolatile());
            repeatableAnnotations = SetsKt.setOf(standardClassIds$Annotations.getRepeatable());
            ClassId deprecated = standardClassIds$Annotations.getDeprecated();
            Boolean bool = Boolean.TRUE;
            deprecationAnnotationsWithOverridesPropagation = MapsKt.mapOf(new Pair[]{TuplesKt.to(deprecated, bool), TuplesKt.to(standardClassIds$Annotations.getSinceKotlin(), bool)});
        }

        private Default() {
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirAnnotationsPlatformSpecificSupportComponent
        public AnnotationsPosition extractBackingFieldAnnotationsFromProperty(FirProperty property, FirSession session, List<? extends FirAnnotation> propertyAnnotations, List<? extends FirAnnotation> backingFieldAnnotations) {
            property.getClass();
            session.getClass();
            propertyAnnotations.getClass();
            backingFieldAnnotations.getClass();
            return null;
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

    public static Set a(FirAnnotationsPlatformSpecificSupportComponent firAnnotationsPlatformSpecificSupportComponent) {
        Set<ClassId> requiredAnnotations = firAnnotationsPlatformSpecificSupportComponent.getRequiredAnnotations();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = requiredAnnotations.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((ClassId) it.next()).getShortClassName());
        }
        return linkedHashSet;
    }

    public static Set b(FirAnnotationsPlatformSpecificSupportComponent firAnnotationsPlatformSpecificSupportComponent) {
        Set<ClassId> deprecationAnnotations = firAnnotationsPlatformSpecificSupportComponent.getDeprecationAnnotations();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<T> it = deprecationAnnotations.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(((ClassId) it.next()).getShortClassName().asString());
        }
        return linkedHashSet;
    }

    public static Set c(FirAnnotationsPlatformSpecificSupportComponent firAnnotationsPlatformSpecificSupportComponent) {
        return firAnnotationsPlatformSpecificSupportComponent.getDeprecationAnnotationsWithOverridesPropagation().keySet();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AnnotationsPosition extractBackingFieldAnnotationsFromProperty$default(FirAnnotationsPlatformSpecificSupportComponent firAnnotationsPlatformSpecificSupportComponent, FirProperty firProperty, FirSession firSession, List list, List list2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: extractBackingFieldAnnotationsFromProperty");
            return null;
        }
        if ((i & 4) != 0) {
            list = firProperty.getAnnotations();
        }
        if ((i & 8) != 0) {
            FirBackingField backingField = firProperty.getBackingField();
            List<FirAnnotation> annotations = backingField != null ? backingField.getAnnotations() : null;
            list2 = annotations == null ? CollectionsKt.emptyList() : annotations;
        }
        return firAnnotationsPlatformSpecificSupportComponent.extractBackingFieldAnnotationsFromProperty(firProperty, firSession, list, list2);
    }

    private final Set<ClassId> getDeprecationAnnotations() {
        return (Set) this.deprecationAnnotations.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public Composed createComposed(List<? extends FirAnnotationsPlatformSpecificSupportComponent> components) {
        components.getClass();
        return new Composed(components);
    }

    public abstract AnnotationsPosition extractBackingFieldAnnotationsFromProperty(FirProperty property, FirSession session, List<? extends FirAnnotation> propertyAnnotations, List<? extends FirAnnotation> backingFieldAnnotations);

    public final Set<String> getDeprecationAnnotationsSimpleNames() {
        return (Set) this.deprecationAnnotationsSimpleNames.getValue();
    }

    public abstract Map<ClassId, Boolean> getDeprecationAnnotationsWithOverridesPropagation();

    public abstract Set<ClassId> getRepeatableAnnotations();

    public abstract Set<ClassId> getRequiredAnnotations();

    public final Set<Name> getRequiredAnnotationsShortClassNames() {
        return (Set) this.requiredAnnotationsShortClassNames.getValue();
    }

    public abstract Set<ClassId> getRequiredAnnotationsWithArguments();

    public abstract Set<ClassId> getVolatileAnnotations();

    public final boolean symbolContainsRepeatableAnnotation(FirClassLikeSymbol<?> symbol, FirSession session) {
        symbol.getClass();
        session.getClass();
        return FirAnnotationUtilsKt.getAnnotationByClassIds(symbol.getResolvedAnnotationsWithClassIds(), getRepeatableAnnotations(), session) != null;
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends FirAnnotationsPlatformSpecificSupportComponent>) list);
    }
}
