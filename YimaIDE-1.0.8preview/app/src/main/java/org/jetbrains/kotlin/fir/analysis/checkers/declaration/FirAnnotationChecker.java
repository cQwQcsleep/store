package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.optimization.fixStack.FixStackAnalyzer;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirAnnotationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirOptInUsageBaseChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.AnnotationTargetUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.CallableIdUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbolKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.AnnotationTargetList;
import org.jetbrains.kotlin.resolve.AnnotationTargetLists;
import org.jetbrains.kotlin.resolve.checkers.OptInNames;
import org.jetbrains.kotlin.utils.CollectionsKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0002H\u0016R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0010J-\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0012H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0013J=\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u001bJ5\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u001dJ5\u0010\u001e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u001dJK\u0010\u001f\u001a\u00020\n2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010!\u001a\u00020\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010&J\\\u0010'\u001a\u00020(2\u0006\u0010 \u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010!\u001a\u00020\"2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00180*H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0001\u001a\u0004\u0010\u0001(\u0000¢\u0006\u0002\u0010,J7\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020\u00162\b\u0010/\u001a\u0004\u0018\u00010\u0016H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u00100J-\u00101\u001a\u00020\n2\u0006\u00102\u001a\u00020\u0012H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010\u0013J-\u00103\u001a\u00020\n2\u0006\u00104\u001a\u000205H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u00106J-\u00107\u001a\u00020\n2\u0006\u00108\u001a\u00020+H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u00109J-\u0010:\u001a\u00020\n2\u0006\u0010;\u001a\u00020<H\u0002R\u00020\u000bR\u00020\rj\u0006\u0010\f\u001a\u00020\u000bj\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0002\u0010=J\u0014\u0010>\u001a\u00020(*\u00020\u00162\u0006\u0010?\u001a\u00020@H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010B\u001a\b\u0012\u0004\u0012\u00020C0$X\u0082\u0004¢\u0006\u0002\n\u0000ò\u0001\u0004\n\u00020+¨\u0006D"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnnotationChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "<init>", "()V", "deprecatedClassId", "Lorg/jetbrains/kotlin/name/FqName;", "deprecatedSinceKotlinClassId", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "checkAnnotationContainer", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;)V", "reportIfMfvc", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "hint", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Ljava/lang/String;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "checkMultiFieldValueClassAnnotationRestrictions", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)V", "checkAnnotationTarget", "checkAnnotationUseSiteTarget", "annotated", "target", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "applicableTargets", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/descriptors/annotations/KotlinTarget;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;Ljava/util/Set;)V", "checkPropertyGetter", Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;)Z", "checkDeprecatedCalls", "deprecatedSinceKotlin", "deprecated", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)V", "checkDeclaredRepeatedAnnotations", "annotationContainer", "checkAllRepeatedAnnotations", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "checkRepeatedAnnotationsInProperty", "property", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)V", "checkPossibleMigrationToPropertyOrField", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;)V", "requiresMigrationToPropertyOrFieldWarning", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "JAVA_LANG_PACKAGE", "STANDARD_ANNOTATION_IDS_WITHOUT_NECESSARY_MIGRATION", "Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationChecker extends FirDeclarationChecker<FirDeclaration> {
    private static final FqName JAVA_LANG_PACKAGE;
    private static final Set<ClassId> STANDARD_ANNOTATION_IDS_WITHOUT_NECESSARY_MIGRATION;
    public static final FirAnnotationChecker INSTANCE = new FirAnnotationChecker();
    private static final FqName deprecatedClassId = new FqName("kotlin.Deprecated");
    private static final FqName deprecatedSinceKotlinClassId = new FqName("kotlin.DeprecatedSinceKotlin");

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AnnotationUseSiteTarget.values().length];
            try {
                iArr[AnnotationUseSiteTarget.FIELD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[AnnotationUseSiteTarget.RECEIVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[AnnotationUseSiteTarget.FILE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[AnnotationUseSiteTarget.PROPERTY.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[AnnotationUseSiteTarget.PROPERTY_GETTER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[AnnotationUseSiteTarget.PROPERTY_SETTER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[AnnotationUseSiteTarget.SETTER_PARAMETER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[AnnotationUseSiteTarget.ALL.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        FqName fqName = new FqName("java.lang");
        JAVA_LANG_PACKAGE = fqName;
        ClassId opt_in_class_id = OptInNames.INSTANCE.getOPT_IN_CLASS_ID();
        StandardClassIds$Annotations standardClassIds$Annotations = StandardClassIds$Annotations.INSTANCE;
        ClassId deprecated = standardClassIds$Annotations.getDeprecated();
        ClassId deprecatedSinceKotlin = standardClassIds$Annotations.getDeprecatedSinceKotlin();
        ClassId suppress = standardClassIds$Annotations.getSuppress();
        Name nameIdentifier = Name.identifier("Deprecated");
        nameIdentifier.getClass();
        ClassId classId = new ClassId(fqName, nameIdentifier);
        Name nameIdentifier2 = Name.identifier("SuppressWarnings");
        nameIdentifier2.getClass();
        STANDARD_ANNOTATION_IDS_WITHOUT_NECESSARY_MIGRATION = SetsKt.hashSetOf(new ClassId[]{opt_in_class_id, deprecated, deprecatedSinceKotlin, suppress, classId, new ClassId(fqName, nameIdentifier2)});
    }

    private FirAnnotationChecker() {
        super(MppCheckerKind.Common);
    }

    public static KtSourceElement b(FirAnnotation firAnnotation) {
        firAnnotation.getClass();
        return firAnnotation.getSource();
    }

    public static KtSourceElement c(FirAnnotation firAnnotation) {
        firAnnotation.getClass();
        return firAnnotation.getSource();
    }

    private final void checkAllRepeatedAnnotations(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeRef firTypeRef) {
        Map mapKeysToMap = CollectionsKt.keysToMap(firTypeRef.getAnnotations(), new Function1() { // from class: gy4
            public final Object invoke(Object obj) {
                return FirAnnotationChecker.b((FirAnnotation) obj);
            }
        });
        KtSourceElement source = firTypeRef.getSource();
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firTypeRef);
        FirSession session = checkerContext.getSession();
        List listMutableListOf = kotlin.collections.CollectionsKt.mutableListOf(new ConeKotlinType[]{coneType});
        while (!listMutableListOf.isEmpty()) {
            ConeKotlinType coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default((ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf), session, (Function1) null, 2, (Object) null);
            CheckerContext checkerContext2 = checkerContext;
            DiagnosticReporter diagnosticReporter2 = diagnosticReporter;
            FirAnnotationHelpersKt.checkRepeatedAnnotation(checkerContext2, diagnosticReporter2, (FirAnnotationContainer) null, CustomAnnotationTypeAttributeKt.getTypeAnnotations(coneKotlinTypeFullyExpandedType$default), (Map<FirAnnotation, ? extends KtSourceElement>) mapKeysToMap, source);
            if (coneKotlinTypeFullyExpandedType$default instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinTypeFullyExpandedType$default;
                listMutableListOf.add(coneFlexibleType.getLowerBound());
                if (!coneFlexibleType.getIsTrivial()) {
                    listMutableListOf.add(coneFlexibleType.getUpperBound());
                }
            } else if (coneKotlinTypeFullyExpandedType$default instanceof ConeDefinitelyNotNullType) {
                listMutableListOf.add(((ConeDefinitelyNotNullType) coneKotlinTypeFullyExpandedType$default).getOriginal());
            } else if (coneKotlinTypeFullyExpandedType$default instanceof ConeIntersectionType) {
                listMutableListOf.addAll(((ConeIntersectionType) coneKotlinTypeFullyExpandedType$default).getIntersectedTypes());
            } else {
                ConeKotlinTypeProjection[] typeArguments = coneKotlinTypeFullyExpandedType$default.getTypeArguments();
                for (ConeKotlinTypeProjection coneKotlinTypeProjection : typeArguments) {
                    if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                        listMutableListOf.add(coneKotlinTypeProjection.getType());
                    }
                }
            }
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
        }
    }

    private final void checkAnnotationContainer(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotationContainer firAnnotationContainer) {
        FirReceiverParameter receiverParameter;
        FirAnnotation firAnnotation = null;
        FirAnnotation firAnnotation2 = null;
        for (FirAnnotation firAnnotation3 : firAnnotationContainer.getAnnotations()) {
            FqName fqName = CallableIdUtilsKt.fqName(firAnnotation3, checkerContext.getSession());
            if (fqName != null) {
                if (Intrinsics.areEqual(fqName, deprecatedClassId)) {
                    firAnnotation = firAnnotation3;
                } else if (Intrinsics.areEqual(fqName, deprecatedSinceKotlinClassId)) {
                    firAnnotation2 = firAnnotation3;
                }
                checkAnnotationTarget(checkerContext, diagnosticReporter, firAnnotationContainer, firAnnotation3);
            }
        }
        boolean z = firAnnotationContainer instanceof FirCallableDeclaration;
        if (z && (receiverParameter = ((FirCallableDeclaration) firAnnotationContainer).getReceiverParameter()) != null) {
            Iterator<FirAnnotation> it = receiverParameter.getAnnotations().iterator();
            while (it.hasNext()) {
                reportIfMfvc(checkerContext, diagnosticReporter, it.next(), "receivers", FirTypeUtilsKt.getConeType(receiverParameter.getTypeRef()));
            }
        }
        if (firAnnotation2 != null) {
            checkDeprecatedCalls(checkerContext, diagnosticReporter, firAnnotation2, firAnnotation);
        }
        checkDeclaredRepeatedAnnotations(checkerContext, diagnosticReporter, firAnnotationContainer);
        if (!z) {
            if (firAnnotationContainer instanceof FirTypeAlias) {
                checkAllRepeatedAnnotations(checkerContext, diagnosticReporter, ((FirTypeAlias) firAnnotationContainer).getExpandedTypeRef());
                return;
            }
            return;
        }
        if (firAnnotationContainer instanceof FirProperty) {
            checkRepeatedAnnotationsInProperty(checkerContext, diagnosticReporter, (FirProperty) firAnnotationContainer);
        }
        if (firAnnotationContainer instanceof FirValueParameter) {
            checkPossibleMigrationToPropertyOrField(checkerContext, diagnosticReporter, (FirValueParameter) firAnnotationContainer);
        }
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firAnnotationContainer;
        KtSourceElement source = firCallableDeclaration.getSource();
        if ((source != null ? source.getKind() : null) instanceof KtRealSourceElementKind) {
            KtSourceElement source2 = firCallableDeclaration.getReturnTypeRef().getSource();
            if ((source2 != null ? source2.getKind() : null) instanceof KtRealSourceElementKind) {
                checkAllRepeatedAnnotations(checkerContext, diagnosticReporter, firCallableDeclaration.getReturnTypeRef());
            }
        }
    }

    private final void checkAnnotationTarget(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotationContainer firAnnotationContainer, FirAnnotation firAnnotation) {
        String description;
        AnnotationTargetList actualTargetList = FirHelpersKt.getActualTargetList(checkerContext, firAnnotationContainer);
        Set<KotlinTarget> allowedAnnotationTargets = FirAnnotationHelpersKt.getAllowedAnnotationTargets(firAnnotation, checkerContext.getSession());
        AnnotationUseSiteTarget useSiteTarget = firAnnotation.getUseSiteTarget();
        if (useSiteTarget != null) {
            checkAnnotationUseSiteTarget(checkerContext, diagnosticReporter, firAnnotationContainer, firAnnotation, useSiteTarget, allowedAnnotationTargets);
        }
        if (checkAnnotationTarget$check(allowedAnnotationTargets, useSiteTarget, actualTargetList.getDefaultTargets()) || checkAnnotationTarget$check(allowedAnnotationTargets, useSiteTarget, actualTargetList.getCanBeSubstituted()) || checkAnnotationTarget$checkWithUseSiteTargets(useSiteTarget, actualTargetList, allowedAnnotationTargets)) {
            if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.JvmInlineMultiFieldValueClasses)) {
                checkMultiFieldValueClassAnnotationRestrictions(checkerContext, diagnosticReporter, firAnnotationContainer, firAnnotation);
                return;
            }
            return;
        }
        KotlinTarget kotlinTarget = (KotlinTarget) kotlin.collections.CollectionsKt.firstOrNull(actualTargetList.getDefaultTargets());
        if (kotlinTarget == null || (description = kotlinTarget.getDescription()) == null) {
            description = "unidentified target";
        }
        String str = description;
        if ((firAnnotationContainer instanceof FirBackingField) && actualTargetList == AnnotationTargetLists.INSTANCE.getT_MEMBER_PROPERTY_IN_ANNOTATION() && LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ForbidFieldAnnotationsOnAnnotationParameters)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getWRONG_ANNOTATION_TARGET_WARNING(), (Object) str, (Object) allowedAnnotationTargets, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        } else if (useSiteTarget == null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getWRONG_ANNOTATION_TARGET(), (Object) str, (Object) allowedAnnotationTargets, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        } else if (useSiteTarget != AnnotationUseSiteTarget.ALL) {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory3<String, String, Set<KotlinTarget>>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getWRONG_ANNOTATION_TARGET_WITH_USE_SITE_TARGET()), str, useSiteTarget.getRenderName(), allowedAnnotationTargets, (64 & 64) != 0 ? null : null);
        }
    }

    private static final boolean checkAnnotationTarget$check(Set<? extends KotlinTarget> set, AnnotationUseSiteTarget annotationUseSiteTarget, List<? extends KotlinTarget> list) {
        List<? extends KotlinTarget> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return false;
        }
        for (KotlinTarget kotlinTarget : list2) {
            if (set.contains(kotlinTarget) && (annotationUseSiteTarget == null || KotlinTarget.INSTANCE.getUSE_SITE_MAPPING().get(annotationUseSiteTarget) == kotlinTarget)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean checkAnnotationTarget$checkWithUseSiteTargets(AnnotationUseSiteTarget annotationUseSiteTarget, AnnotationTargetList annotationTargetList, Set<? extends KotlinTarget> set) {
        if (annotationUseSiteTarget == null) {
            return false;
        }
        KotlinTarget kotlinTarget = KotlinTarget.INSTANCE.getUSE_SITE_MAPPING().get(annotationUseSiteTarget);
        List<KotlinTarget> onlyWithUseSiteTarget = annotationTargetList.getOnlyWithUseSiteTarget();
        if ((onlyWithUseSiteTarget instanceof Collection) && onlyWithUseSiteTarget.isEmpty()) {
            return false;
        }
        for (KotlinTarget kotlinTarget2 : onlyWithUseSiteTarget) {
            if (set.contains(kotlinTarget2) && kotlinTarget2 == kotlinTarget) {
                return true;
            }
        }
        return false;
    }

    private final void checkAnnotationUseSiteTarget(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotationContainer firAnnotationContainer, FirAnnotation firAnnotation, AnnotationUseSiteTarget annotationUseSiteTarget, Set<? extends KotlinTarget> set) {
        KtDiagnosticFactory1<String> inapplicable_target_on_property_warning;
        KtSourceElement source = firAnnotation.getSource();
        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.FromUseSiteTarget.INSTANCE)) {
            return;
        }
        switch (WhenMappings.$EnumSwitchMapping$0[annotationUseSiteTarget.ordinal()]) {
            case 1:
                if (firAnnotationContainer instanceof FirBackingField) {
                    FirPropertySymbol propertySymbol = ((FirBackingField) firAnnotationContainer).getPropertySymbol();
                    if (propertySymbol.getDelegateFieldSymbol() != null && !DeclarationAttributesKt.getHasBackingField(propertySymbol)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getINAPPLICABLE_TARGET_PROPERTY_HAS_NO_BACKING_FIELD(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        break;
                    }
                }
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                if ((firAnnotationContainer instanceof FirBackingField) && ((FirBackingField) firAnnotationContainer).getPropertySymbol().getDelegateFieldSymbol() == null) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getINAPPLICABLE_TARGET_PROPERTY_HAS_NO_DELEGATE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    break;
                }
                break;
            case 3:
                KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory3<String, String, Set<? extends KotlinTarget>>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getWRONG_ANNOTATION_TARGET_WITH_USE_SITE_TARGET()), "declaration", annotationUseSiteTarget.getRenderName(), set, (64 & 64) != 0 ? null : null);
                break;
            case 4:
                if (!(firAnnotationContainer instanceof FirFile)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getINAPPLICABLE_FILE_TARGET(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
                break;
            case 5:
            case 6:
                boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ProhibitUseSiteGetTargetAnnotations);
                if (zIsEnabled) {
                    inapplicable_target_on_property_warning = FirErrors.INSTANCE.getINAPPLICABLE_TARGET_ON_PROPERTY();
                } else if (zIsEnabled) {
                    bu8.a();
                } else {
                    inapplicable_target_on_property_warning = FirErrors.INSTANCE.getINAPPLICABLE_TARGET_ON_PROPERTY_WARNING();
                }
                checkPropertyGetter(checkerContext, diagnosticReporter, firAnnotationContainer, firAnnotation, annotationUseSiteTarget, inapplicable_target_on_property_warning);
                break;
            case 7:
            case 9:
                FirErrors firErrors = FirErrors.INSTANCE;
                if (!checkPropertyGetter(checkerContext, diagnosticReporter, firAnnotationContainer, firAnnotation, annotationUseSiteTarget, firErrors.getINAPPLICABLE_TARGET_ON_PROPERTY()) && !((FirProperty) firAnnotationContainer).getIsVar()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) firErrors.getINAPPLICABLE_TARGET_PROPERTY_IMMUTABLE(), (Object) annotationUseSiteTarget.getRenderName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    break;
                }
                break;
            case 8:
                if (!(firAnnotationContainer instanceof FirValueParameter)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getINAPPLICABLE_PARAM_TARGET(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else if (!FirHelpersKt.isPrimaryConstructor((FirBasedSymbol) kotlin.collections.CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations()))) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getINAPPLICABLE_PARAM_TARGET(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                } else {
                    KtSourceElement source2 = ((FirValueParameter) firAnnotationContainer).getSource();
                    if (source2 == null || !LightTreePositioningStrategiesKt.hasValOrVar(source2)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getREDUNDANT_ANNOTATION_TARGET(), (Object) annotationUseSiteTarget.getRenderName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    }
                }
                break;
            case 10:
                LanguageFeature languageFeature = LanguageFeature.AnnotationAllUseSiteTarget;
                if (!LanguageVersionUtilsKt.isEnabled(checkerContext, languageFeature)) {
                    if (!(firAnnotationContainer instanceof FirValueParameter) || ClassMembersKt.getCorrespondingProperty((FirValueParameter) firAnnotationContainer) == null) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNSUPPORTED_FEATURE(), (Object) TuplesKt.to(languageFeature, checkerContext.get$languageVersionSettings()), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    }
                } else if (firAnnotationContainer instanceof FirValueParameter) {
                    FirValueParameter firValueParameter = (FirValueParameter) firAnnotationContainer;
                    if (ClassMembersKt.getCorrespondingProperty(firValueParameter) == null) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINAPPLICABLE_ALL_TARGET(), (Object) (firValueParameter.getContainingDeclarationSymbol() instanceof FirConstructorSymbol ? "constructor parameters without corresponding property (consider adding val/var)" : "value parameters, only properties are allowed"), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    }
                } else if (!(firAnnotationContainer instanceof FirProperty)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINAPPLICABLE_ALL_TARGET(), (Object) "elements other than properties", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                } else {
                    FirProperty firProperty = (FirProperty) firAnnotationContainer;
                    if (firProperty.getSymbol() instanceof FirLocalPropertySymbol) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINAPPLICABLE_ALL_TARGET(), (Object) "local properties, only member or top-level properties are allowed", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    } else if (firProperty.getDelegate() != null) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getINAPPLICABLE_ALL_TARGET(), (Object) "delegated properties", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    } else if (!set.contains(KotlinTarget.PROPERTY)) {
                        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory3<String, String, Set<? extends KotlinTarget>>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getWRONG_ANNOTATION_TARGET_WITH_USE_SITE_TARGET()), "property", annotationUseSiteTarget.getRenderName(), set, (64 & 64) != 0 ? null : null);
                    }
                }
                break;
            default:
                bu8.a();
                break;
        }
    }

    private final void checkDeclaredRepeatedAnnotations(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotationContainer firAnnotationContainer) {
        FirAnnotationHelpersKt.checkRepeatedAnnotation(checkerContext, diagnosticReporter, firAnnotationContainer, firAnnotationContainer.getAnnotations(), (Map<FirAnnotation, ? extends KtSourceElement>) CollectionsKt.keysToMap(firAnnotationContainer.getAnnotations(), new Function1() { // from class: fy4
            public final Object invoke(Object obj) {
                return FirAnnotationChecker.c((FirAnnotation) obj);
            }
        }), (KtSourceElement) null);
    }

    private final void checkDeprecatedCalls(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotation firAnnotation, FirAnnotation firAnnotation2) {
        FirFileSymbol containingFileSymbol = checkerContext.getContainingFileSymbol();
        if (containingFileSymbol != null && !UtilsKt.getPackageFqName(containingFileSymbol).startsWith(StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE().shortName())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getDEPRECATED_SINCE_KOTLIN_OUTSIDE_KOTLIN_SUBPACKAGE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        if (firAnnotation2 == null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getDEPRECATED_SINCE_KOTLIN_WITHOUT_DEPRECATED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return;
        }
        Iterator<Name> it = firAnnotation2.getArgumentMapping().getMapping().keySet().iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(it.next().getIdentifier(), "level")) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getDEPRECATED_SINCE_KOTLIN_WITH_DEPRECATED_LEVEL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:59:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:61:0x00d1  */
    /* JADX WARN: Code duplicated, block: B:62:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:64:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:65:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:67:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:68:0x010a  */
    /* JADX WARN: Code duplicated, block: B:70:0x010e  */
    /* JADX WARN: Code duplicated, block: B:87:? A[RETURN, SYNTHETIC] */
    private final void checkMultiFieldValueClassAnnotationRestrictions(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotationContainer firAnnotationContainer, FirAnnotation firAnnotation) {
        FirPropertyAccessor firPropertyAccessor;
        Pair pair;
        FirTypeRef returnTypeRef;
        ConeKotlinType coneType;
        FirPropertySymbol propertySymbol;
        FirExpression delegate;
        ConeKotlinType resolvedType;
        FirReceiverParameter receiverParameter;
        FirTypeRef typeRef;
        ConeKotlinType coneType2;
        AnnotationUseSiteTarget useSiteTarget = firAnnotation.getUseSiteTarget();
        switch (useSiteTarget == null ? -1 : WhenMappings.$EnumSwitchMapping$0[useSiteTarget.ordinal()]) {
            case FixStackAnalyzer.DEAD_CODE_STACK_SIZE /* -1 */:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                if (firAnnotationContainer instanceof FirProperty) {
                    FirProperty firProperty = (FirProperty) firAnnotationContainer;
                    if (firProperty.getSymbol() instanceof FirRegularPropertySymbol) {
                        Set<KotlinTarget> allowedAnnotationTargets = FirAnnotationHelpersKt.getAllowedAnnotationTargets(firAnnotation, checkerContext.getSession());
                        if ((Intrinsics.areEqual(DeclarationAttributesKt.getFromPrimaryConstructor(firProperty), Boolean.TRUE) && allowedAnnotationTargets.contains(KotlinTarget.VALUE_PARAMETER)) || allowedAnnotationTargets.contains(KotlinTarget.PROPERTY) || !allowedAnnotationTargets.contains(KotlinTarget.FIELD)) {
                            return;
                        } else {
                            pair = TuplesKt.to("fields", FirTypeUtilsKt.getConeType(firProperty.getReturnTypeRef()));
                        }
                    } else if (firAnnotationContainer instanceof FirField) {
                        pair = TuplesKt.to("fields", FirTypeUtilsKt.getConeType(((FirField) firAnnotationContainer).getReturnTypeRef()));
                    } else if (firAnnotationContainer instanceof FirValueParameter) {
                        pair = TuplesKt.to("parameters", FirTypeUtilsKt.getConeType(((FirValueParameter) firAnnotationContainer).getReturnTypeRef()));
                    } else if (!(firAnnotationContainer instanceof FirVariable)) {
                        pair = TuplesKt.to("variables", FirTypeUtilsKt.getConeType(((FirVariable) firAnnotationContainer).getReturnTypeRef()));
                    } else {
                        if (!(firAnnotationContainer instanceof FirPropertyAccessor)) {
                            return;
                        }
                        firPropertyAccessor = (FirPropertyAccessor) firAnnotationContainer;
                        if (firPropertyAccessor.getIsGetter() || !checkMultiFieldValueClassAnnotationRestrictions$hasNoReceivers(firPropertyAccessor)) {
                            return;
                        } else {
                            pair = TuplesKt.to("getters", FirTypeUtilsKt.getConeType(firPropertyAccessor.getReturnTypeRef()));
                        }
                    }
                } else if (firAnnotationContainer instanceof FirField) {
                    pair = TuplesKt.to("fields", FirTypeUtilsKt.getConeType(((FirField) firAnnotationContainer).getReturnTypeRef()));
                } else if (firAnnotationContainer instanceof FirValueParameter) {
                    pair = TuplesKt.to("parameters", FirTypeUtilsKt.getConeType(((FirValueParameter) firAnnotationContainer).getReturnTypeRef()));
                } else {
                    if (!(firAnnotationContainer instanceof FirVariable)) {
                        if (!(firAnnotationContainer instanceof FirPropertyAccessor)) {
                            firPropertyAccessor = (FirPropertyAccessor) firAnnotationContainer;
                            if (firPropertyAccessor.getIsGetter()) {
                                return;
                            } else {
                                return;
                            }
                        }
                        return;
                    }
                    pair = TuplesKt.to("variables", FirTypeUtilsKt.getConeType(((FirVariable) firAnnotationContainer).getReturnTypeRef()));
                }
                break;
            case MavenComparableVersion.Item.INTEGER_ITEM /* 0 */:
            default:
                bu8.a();
                return;
            case 1:
                FirBackingField firBackingField = firAnnotationContainer instanceof FirBackingField ? (FirBackingField) firAnnotationContainer : null;
                if (firBackingField == null || (returnTypeRef = firBackingField.getReturnTypeRef()) == null || (coneType = FirTypeUtilsKt.getConeType(returnTypeRef)) == null) {
                    return;
                } else {
                    pair = TuplesKt.to("fields", coneType);
                }
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                FirBackingField firBackingField2 = firAnnotationContainer instanceof FirBackingField ? (FirBackingField) firAnnotationContainer : null;
                if (firBackingField2 == null || (propertySymbol = firBackingField2.getPropertySymbol()) == null || (delegate = propertySymbol.getDelegate()) == null || (resolvedType = FirTypeUtilsKt.getResolvedType(delegate)) == null) {
                    return;
                } else {
                    pair = TuplesKt.to("delegate fields", resolvedType);
                }
                break;
            case 3:
                FirCallableDeclaration firCallableDeclaration = firAnnotationContainer instanceof FirCallableDeclaration ? (FirCallableDeclaration) firAnnotationContainer : null;
                if (firCallableDeclaration == null || (receiverParameter = firCallableDeclaration.getReceiverParameter()) == null || (typeRef = receiverParameter.getTypeRef()) == null || (coneType2 = FirTypeUtilsKt.getConeType(typeRef)) == null) {
                    return;
                } else {
                    pair = TuplesKt.to("receivers", coneType2);
                }
                break;
            case 10:
                return;
        }
        reportIfMfvc(checkerContext, diagnosticReporter, firAnnotation, (String) pair.component1(), (ConeKotlinType) pair.component2());
    }

    private static final boolean checkMultiFieldValueClassAnnotationRestrictions$hasNoReceivers(FirPropertyAccessor firPropertyAccessor) {
        if (!firPropertyAccessor.getContextParameters().isEmpty()) {
            return false;
        }
        FirReceiverParameter receiverParameter = firPropertyAccessor.getReceiverParameter();
        return ((receiverParameter != null ? receiverParameter.getTypeRef() : null) != null || FirSymbolStatusUtilsKt.isExtension(firPropertyAccessor.getPropertySymbol()) || FirCallableSymbolKt.getHasContextParameters(firPropertyAccessor.getPropertySymbol())) ? false : true;
    }

    private final void checkPossibleMigrationToPropertyOrField(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirValueParameter firValueParameter) {
        FirProperty correspondingProperty;
        FirSession session = checkerContext.getSession();
        if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.AnnotationDefaultTargetMigrationWarning) || LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.PropertyParamAnnotationDefaultTargetMode) || (correspondingProperty = ClassMembersKt.getCorrespondingProperty(firValueParameter)) == null) {
            return;
        }
        for (FirAnnotation firAnnotation : firValueParameter.getAnnotations()) {
            if (firAnnotation.getUseSiteTarget() == null && requiresMigrationToPropertyOrFieldWarning(firAnnotation, session)) {
                Set<AnnotationUseSiteTarget> setUseSiteTargetsFromMetaAnnotation = AnnotationTargetUtilsKt.useSiteTargetsFromMetaAnnotation(firAnnotation, session);
                AnnotationUseSiteTarget annotationUseSiteTarget = AnnotationUseSiteTarget.PROPERTY;
                boolean zContains = setUseSiteTargetsFromMetaAnnotation.contains(annotationUseSiteTarget);
                AnnotationUseSiteTarget annotationUseSiteTarget2 = AnnotationUseSiteTarget.FIELD;
                boolean zContains2 = setUseSiteTargetsFromMetaAnnotation.contains(annotationUseSiteTarget2);
                if (zContains || zContains2) {
                    if (zContains) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getANNOTATION_WILL_BE_APPLIED_ALSO_TO_PROPERTY_OR_FIELD(), (Object) annotationUseSiteTarget.getRenderName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                    } else if (correspondingProperty.getBackingField() != null) {
                        Object orNull = kotlin.collections.CollectionsKt.getOrNull(checkerContext.getContainingDeclarations(), checkerContext.getContainingDeclarations().size() - 2);
                        FirClassSymbol firClassSymbol = orNull instanceof FirClassSymbol ? (FirClassSymbol) orNull : null;
                        if ((firClassSymbol != null ? firClassSymbol.getClassKind() : null) != ClassKind.ANNOTATION_CLASS) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getANNOTATION_WILL_BE_APPLIED_ALSO_TO_PROPERTY_OR_FIELD(), (Object) annotationUseSiteTarget2.getRenderName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                        }
                    }
                }
            }
        }
    }

    private final boolean checkPropertyGetter(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotationContainer firAnnotationContainer, FirAnnotation firAnnotation, AnnotationUseSiteTarget annotationUseSiteTarget, KtDiagnosticFactory1<String> ktDiagnosticFactory1) {
        boolean z = !(firAnnotationContainer instanceof FirProperty) || (((FirProperty) firAnnotationContainer).getSymbol() instanceof FirLocalPropertySymbol);
        if (z) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) ktDiagnosticFactory1, (Object) annotationUseSiteTarget.getRenderName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        return z;
    }

    private final void checkRepeatedAnnotationsInProperty(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirProperty firProperty) {
        List<FirValueParameter> valueParameters;
        AnnotationUseSiteTarget annotationUseSiteTarget = AnnotationUseSiteTarget.PROPERTY_GETTER;
        FirPropertyAccessor getter = firProperty.getGetter();
        Pair pair = TuplesKt.to(annotationUseSiteTarget, getter != null ? checkRepeatedAnnotationsInProperty$getAnnotationTypes(getter) : null);
        AnnotationUseSiteTarget annotationUseSiteTarget2 = AnnotationUseSiteTarget.PROPERTY_SETTER;
        FirPropertyAccessor setter = firProperty.getSetter();
        Pair pair2 = TuplesKt.to(annotationUseSiteTarget2, setter != null ? checkRepeatedAnnotationsInProperty$getAnnotationTypes(setter) : null);
        AnnotationUseSiteTarget annotationUseSiteTarget3 = AnnotationUseSiteTarget.SETTER_PARAMETER;
        FirPropertyAccessor setter2 = firProperty.getSetter();
        Map mapMapOf = MapsKt.mapOf(new Pair[]{pair, pair2, TuplesKt.to(annotationUseSiteTarget3, checkRepeatedAnnotationsInProperty$getAnnotationTypes((setter2 == null || (valueParameters = setter2.getValueParameters()) == null) ? null : (FirValueParameter) kotlin.collections.CollectionsKt.single(valueParameters)))});
        for (FirAnnotation firAnnotation : firProperty.getAnnotations()) {
            AnnotationUseSiteTarget useSiteTarget = firAnnotation.getUseSiteTarget();
            if (useSiteTarget == null) {
                useSiteTarget = FirAnnotationHelpersKt.getDefaultUseSiteTarget(checkerContext, firProperty, firAnnotation);
            }
            List list = (List) mapMapOf.get(useSiteTarget);
            if (list != null && list.contains(FirTypeUtilsKt.getConeType(firAnnotation.getAnnotationTypeRef())) && !FirAnnotationHelpersKt.isRepeatable(firAnnotation, checkerContext.getSession())) {
                KtSourceElement source = firAnnotation.getSource();
                if (!((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), FirErrors.INSTANCE.getREPEATED_ANNOTATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
    }

    private static final List<ConeKotlinType> checkRepeatedAnnotationsInProperty$getAnnotationTypes(FirAnnotationContainer firAnnotationContainer) {
        List<FirAnnotation> annotations;
        if (firAnnotationContainer == null || (annotations = firAnnotationContainer.getAnnotations()) == null) {
            return kotlin.collections.CollectionsKt.emptyList();
        }
        List<FirAnnotation> list = annotations;
        ArrayList arrayList = new ArrayList(kotlin.collections.CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(FirTypeUtilsKt.getConeType(((FirAnnotation) it.next()).getAnnotationTypeRef()));
        }
        return arrayList;
    }

    private final void reportIfMfvc(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirAnnotation firAnnotation, String str, ConeKotlinType coneKotlinType) {
        if (DeclarationUtilsKt.needsMultiFieldValueClassFlattening(coneKotlinType, checkerContext.getSession())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firAnnotation.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getANNOTATION_ON_ILLEGAL_MULTI_FIELD_VALUE_CLASS_TYPED_TARGET(), (Object) str, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    private final boolean requiresMigrationToPropertyOrFieldWarning(FirAnnotation firAnnotation, FirSession firSession) {
        FirClassLikeSymbol<?> annotationClassLikeSymbol = FirAnnotationUtilsKt.toAnnotationClassLikeSymbol(firAnnotation, firSession);
        if (kotlin.collections.CollectionsKt.contains(STANDARD_ANNOTATION_IDS_WITHOUT_NECESSARY_MIGRATION, annotationClassLikeSymbol != null ? annotationClassLikeSymbol.getClassId() : null)) {
            return false;
        }
        return annotationClassLikeSymbol == null || !FirOptInUsageBaseChecker.INSTANCE.isExperimentalMarker(annotationClassLikeSymbol, firSession);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirDeclaration firDeclaration) {
        FirReceiverParameter receiverParameter;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firDeclaration.getClass();
        if (firDeclaration instanceof FirDanglingModifierList) {
            return;
        }
        checkAnnotationContainer(checkerContext, diagnosticReporter, firDeclaration);
        if (!(firDeclaration instanceof FirCallableDeclaration) || (receiverParameter = ((FirCallableDeclaration) firDeclaration).getReceiverParameter()) == null) {
            return;
        }
        INSTANCE.checkAnnotationContainer(checkerContext, diagnosticReporter, receiverParameter);
    }
}
