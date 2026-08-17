package org.jetbrains.kotlin.fir.java;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.java.JavaAnnotationsMappingKt$buildFirAnnotation$argumentMapping$1;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotation;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotationArgument;
import org.jetbrains.kotlin.load.java.structure.JavaArrayAnnotationArgument;
import org.jetbrains.kotlin.load.java.structure.JavaEnumValueAnnotationArgument;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.JvmStandardClassIds;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000A\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J5\u0010\u0002\u001a\u00020\u0003\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00072\u0006\u0010\b\u001a\u0002H\u0005H\u0016¢\u0006\u0002\u0010\tJ)\u0010\n\u001a\u00020\u000b\"\u0004\b\u0000\u0010\u00052\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00050\r2\u0006\u0010\b\u001a\u0002H\u0005H\u0016¢\u0006\u0002\u0010\u000eR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R'\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u00148VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001b"}, d2 = {"org/jetbrains/kotlin/fir/java/JavaAnnotationsMappingKt$buildFirAnnotation$argumentMapping$1", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "mapping", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getMapping", "()Ljava/util/Map;", "mapping$delegate", "Lkotlin/Lazy;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JavaAnnotationsMappingKt$buildFirAnnotation$argumentMapping$1 extends FirAnnotationArgumentMapping {

    /* JADX INFO: renamed from: mapping$delegate, reason: from kotlin metadata */
    private final Lazy mapping;

    public JavaAnnotationsMappingKt$buildFirAnnotation$argumentMapping$1(final ClassId classId, final JavaAnnotation javaAnnotation, final FirSession firSession, final ConeClassLikeLookupTagImpl coneClassLikeLookupTagImpl, final KtSourceElement ktSourceElement) {
        this.mapping = LazyKt.lazy(new Function0() { // from class: jb7
            public final Object invoke() {
                return JavaAnnotationsMappingKt$buildFirAnnotation$argumentMapping$1.a(classId, javaAnnotation, firSession, coneClassLikeLookupTagImpl, ktSourceElement);
            }
        });
    }

    public static Map a(ClassId classId, JavaAnnotation javaAnnotation, FirSession firSession, ConeClassLikeLookupTagImpl coneClassLikeLookupTagImpl, KtSourceElement ktSourceElement) {
        FirExpression firExpressionMapJavaRetentionArgument;
        FirExpression firExpressionMapJavaTargetArguments;
        JvmStandardClassIds.Annotations.Java java = JvmStandardClassIds.Annotations.Java.INSTANCE;
        Map mapMapOf = null;
        if (Intrinsics.areEqual(classId, java.getTarget())) {
            JavaArrayAnnotationArgument javaArrayAnnotationArgument = (JavaAnnotationArgument) CollectionsKt.firstOrNull(javaAnnotation.getArguments());
            if (javaArrayAnnotationArgument instanceof JavaArrayAnnotationArgument) {
                firExpressionMapJavaTargetArguments = JavaAnnotationsMappingKt.mapJavaTargetArguments(javaArrayAnnotationArgument.getElements());
            } else {
                firExpressionMapJavaTargetArguments = javaArrayAnnotationArgument instanceof JavaEnumValueAnnotationArgument ? JavaAnnotationsMappingKt.mapJavaTargetArguments(CollectionsKt.listOf(javaArrayAnnotationArgument)) : null;
            }
            if (firExpressionMapJavaTargetArguments != null) {
                mapMapOf = MapsKt.mapOf(TuplesKt.to(StandardClassIds$Annotations.ParameterNames.INSTANCE.getTargetAllowedTargets(), firExpressionMapJavaTargetArguments));
            }
        } else if (Intrinsics.areEqual(classId, java.getRetention())) {
            JavaAnnotationArgument javaAnnotationArgument = (JavaAnnotationArgument) CollectionsKt.firstOrNull(javaAnnotation.getArguments());
            if (javaAnnotationArgument != null && (firExpressionMapJavaRetentionArgument = JavaAnnotationsMappingKt.mapJavaRetentionArgument(javaAnnotationArgument)) != null) {
                mapMapOf = MapsKt.mapOf(TuplesKt.to(StandardClassIds$Annotations.ParameterNames.INSTANCE.getRetentionValue(), firExpressionMapJavaRetentionArgument));
            }
        } else {
            Collection arguments = javaAnnotation.getArguments();
            if (!arguments.isEmpty()) {
                LinkedHashMap linkedHashMap = new LinkedHashMap(arguments.size());
                JavaAnnotationsMappingKt.fillAnnotationArgumentMapping(firSession, coneClassLikeLookupTagImpl, javaAnnotation, arguments, linkedHashMap, ktSourceElement);
                mapMapOf = linkedHashMap;
            }
        }
        return mapMapOf == null ? MapsKt.emptyMap() : mapMapOf;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping
    public Map<Name, FirExpression> getMapping() {
        return (Map) this.mapping.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirElement transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }
}
