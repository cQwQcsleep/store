package org.jetbrains.kotlin.fir.java.deserialization;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.SpecialJvmAnnotations;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirClassReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirEnumEntryDeserializedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationArgumentMappingBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirClassReferenceExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirEnumEntryDeserializedAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirGetClassCallBuilder;
import org.jetbrains.kotlin.fir.java.JavaUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinder;
import org.jetbrains.kotlin.load.kotlin.KotlinClassFinderKt;
import org.jetbrains.kotlin.load.kotlin.KotlinJvmBinaryClass;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.constants.ClassLiteralValue;
import org.jetbrains.kotlin.util.MetadataHelpersKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001:\u0001%B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bH\u0002J!\u0010\u0012\u001a\u00020\t2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014H\u0000¢\u0006\u0002\b\u0017J$\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0019\u001a\u00020\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u00150\u001cH\u0002J%\u0010\u001e\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0000¢\u0006\u0002\b\u001fJ\f\u0010 \u001a\u00020!*\u00020\u001aH\u0002J\u0018\u0010\"\u001a\u00020#2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u001dH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/deserialization/AnnotationsLoader;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "kotlinClassFinder", "Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/load/kotlin/KotlinClassFinder;)V", "loadAnnotation", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationArgumentVisitor;", "annotationClassId", "Lorg/jetbrains/kotlin/name/ClassId;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "isImplicitRepeatableContainer", Argument.Delimiters.none, "classId", "loadAnnotationMethodDefaultValue", "consumeResult", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", Argument.Delimiters.none, "loadAnnotationMethodDefaultValue$org_jetbrains_kotlin_fir_jvm", "isRepeatableWithImplicitContainer", "lookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "argumentMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "loadAnnotationIfNotSpecial", "loadAnnotationIfNotSpecial$org_jetbrains_kotlin_fir_jvm", "toDefaultResolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "createEnumEntryAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirEnumEntryDeserializedAccessExpression;", ModuleXmlParser.NAME, "AnnotationsLoaderVisitorImpl", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnnotationsLoader {
    private final KotlinClassFinder kotlinClassFinder;
    private final FirSession session;

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b¢\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH&J\u001c\u0010\u000e\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u000e\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\u0013H\u0002J\u001a\u0010\u0014\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u0013H\u0016J\"\u0010\u0015\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0007H\u0016J\u0014\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u00012\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001c\u001a\u00020\u0017H\u0016J\u0012\u0010\u001d\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002R\u0012\u0010\n\u001a\u00020\u000bX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/deserialization/AnnotationsLoader$AnnotationsLoaderVisitorImpl;", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationArgumentVisitor;", "<init>", "(Lorg/jetbrains/kotlin/fir/java/deserialization/AnnotationsLoader;)V", "visitExpression", Argument.Delimiters.none, ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "expr", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "visitNullNames", Argument.Delimiters.none, "getVisitNullNames", "()Z", "visit", "value", Argument.Delimiters.none, "toFirClassReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirClassReferenceExpression;", "Lorg/jetbrains/kotlin/resolve/constants/ClassLiteralValue;", "visitClassLiteral", "visitEnum", "enumClassId", "Lorg/jetbrains/kotlin/name/ClassId;", "enumEntryName", "visitArray", "Lorg/jetbrains/kotlin/load/kotlin/KotlinJvmBinaryClass$AnnotationArrayArgumentVisitor;", "visitAnnotation", "classId", "createConstant", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public abstract class AnnotationsLoaderVisitorImpl implements KotlinJvmBinaryClass.AnnotationArgumentVisitor {
        public AnnotationsLoaderVisitorImpl() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final FirExpression createConstant(Object value) {
            return JavaUtilsKt.createConstantOrError$default(value, AnnotationsLoader.this.session, null, 2, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final FirClassReferenceExpression toFirClassReferenceExpression(ClassLiteralValue classLiteralValue) {
            if (classLiteralValue.getClassId().isLocal()) {
                return null;
            }
            FirTypeRef defaultResolvedTypeRef = AnnotationsLoader.this.toDefaultResolvedTypeRef(TypeConstructionUtilsKt.toLookupTag(classLiteralValue.getClassId()));
            FirClassReferenceExpressionBuilder firClassReferenceExpressionBuilder = new FirClassReferenceExpressionBuilder();
            firClassReferenceExpressionBuilder.setClassTypeRef(defaultResolvedTypeRef);
            firClassReferenceExpressionBuilder.setConeTypeOrNull(TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getKClass(), new ConeKotlinType[]{defaultResolvedTypeRef.getConeType()}, false, null, 4, null));
            return firClassReferenceExpressionBuilder.mo289build();
        }

        public abstract boolean getVisitNullNames();

        public void visit(Name name, Object value) {
            visitExpression(name, createConstant(value));
        }

        public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(final Name name, ClassId classId) {
            classId.getClass();
            if (name == null && !getVisitNullNames()) {
                return null;
            }
            final ArrayList arrayList = new ArrayList();
            final KotlinJvmBinaryClass.AnnotationArgumentVisitor annotationArgumentVisitorLoadAnnotation = AnnotationsLoader.this.loadAnnotation(classId, arrayList);
            return new KotlinJvmBinaryClass.AnnotationArgumentVisitor(annotationArgumentVisitorLoadAnnotation, this, name, arrayList) { // from class: org.jetbrains.kotlin.fir.java.deserialization.AnnotationsLoader$AnnotationsLoaderVisitorImpl$visitAnnotation$1
                private final /* synthetic */ KotlinJvmBinaryClass.AnnotationArgumentVisitor $$delegate_0;
                final /* synthetic */ List<FirAnnotation> $list;
                final /* synthetic */ Name $name;
                final /* synthetic */ KotlinJvmBinaryClass.AnnotationArgumentVisitor $visitor;
                final /* synthetic */ AnnotationsLoader.AnnotationsLoaderVisitorImpl this$0;

                {
                    this.$visitor = annotationArgumentVisitorLoadAnnotation;
                    this.this$0 = this;
                    this.$name = name;
                    this.$list = arrayList;
                    this.$$delegate_0 = annotationArgumentVisitorLoadAnnotation;
                }

                public void visit(Name name2, Object value) {
                    this.$$delegate_0.visit(name2, value);
                }

                public KotlinJvmBinaryClass.AnnotationArgumentVisitor visitAnnotation(Name name2, ClassId classId2) {
                    classId2.getClass();
                    return this.$$delegate_0.visitAnnotation(name2, classId2);
                }

                public KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray(Name name2) {
                    return this.$$delegate_0.visitArray(name2);
                }

                public void visitClassLiteral(Name name2, ClassLiteralValue value) {
                    value.getClass();
                    this.$$delegate_0.visitClassLiteral(name2, value);
                }

                public void visitEnd() {
                    this.$visitor.visitEnd();
                    this.this$0.visitExpression(this.$name, (FirExpression) CollectionsKt.single(this.$list));
                }

                public void visitEnum(Name name2, ClassId enumClassId, Name enumEntryName) {
                    enumClassId.getClass();
                    enumEntryName.getClass();
                    this.$$delegate_0.visitEnum(name2, enumClassId, enumEntryName);
                }
            };
        }

        public KotlinJvmBinaryClass.AnnotationArrayArgumentVisitor visitArray(Name name) {
            if (name != null || getVisitNullNames()) {
                return new AnnotationsLoader$AnnotationsLoaderVisitorImpl$visitArray$1(this, AnnotationsLoader.this, name);
            }
            return null;
        }

        public void visitClassLiteral(Name name, ClassLiteralValue value) {
            value.getClass();
            FirClassReferenceExpression firClassReferenceExpression = toFirClassReferenceExpression(value);
            if (firClassReferenceExpression == null) {
                return;
            }
            FirGetClassCallBuilder firGetClassCallBuilder = new FirGetClassCallBuilder();
            firGetClassCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firClassReferenceExpression));
            firGetClassCallBuilder.setConeTypeOrNull(FirTypeUtilsKt.getResolvedType(firClassReferenceExpression));
            Unit unit = Unit.INSTANCE;
            visitExpression(name, firGetClassCallBuilder.mo289build());
        }

        public void visitEnum(Name name, ClassId enumClassId, Name enumEntryName) {
            enumClassId.getClass();
            enumEntryName.getClass();
            if (name != null || getVisitNullNames()) {
                visitExpression(name, AnnotationsLoader.this.createEnumEntryAccess(enumClassId, enumEntryName));
            }
        }

        public abstract void visitExpression(Name name, FirExpression expr);
    }

    public AnnotationsLoader(FirSession firSession, KotlinClassFinder kotlinClassFinder) {
        firSession.getClass();
        kotlinClassFinder.getClass();
        this.session = firSession;
        this.kotlinClassFinder = kotlinClassFinder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirEnumEntryDeserializedAccessExpression createEnumEntryAccess(ClassId classId, Name name) {
        FirEnumEntryDeserializedAccessExpressionBuilder firEnumEntryDeserializedAccessExpressionBuilder = new FirEnumEntryDeserializedAccessExpressionBuilder();
        firEnumEntryDeserializedAccessExpressionBuilder.setEnumClassId(classId);
        firEnumEntryDeserializedAccessExpressionBuilder.setEnumEntryName(name);
        return firEnumEntryDeserializedAccessExpressionBuilder.mo289build();
    }

    private final boolean isImplicitRepeatableContainer(ClassId classId) {
        KotlinJvmBinaryClass kotlinJvmBinaryClassFindKotlinClass;
        return classId.getOuterClassId() != null && Intrinsics.areEqual(classId.getShortClassName().asString(), "Container") && (kotlinJvmBinaryClassFindKotlinClass = KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId, MetadataVersion.INSTANCE)) != null && SpecialJvmAnnotations.INSTANCE.isAnnotatedWithContainerMetaAnnotation(kotlinJvmBinaryClassFindKotlinClass);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isRepeatableWithImplicitContainer(ConeClassLikeLookupTag lookupTag, Map<Name, ? extends FirExpression> argumentMap) {
        KotlinJvmBinaryClass kotlinJvmBinaryClassFindKotlinClass;
        ClassId classId = lookupTag.getClassId();
        SpecialJvmAnnotations specialJvmAnnotations = SpecialJvmAnnotations.INSTANCE;
        if (!Intrinsics.areEqual(classId, specialJvmAnnotations.getJAVA_LANG_ANNOTATION_REPEATABLE())) {
            return false;
        }
        FirExpression firExpression = argumentMap.get(StandardClassIds$Annotations.ParameterNames.INSTANCE.getValue());
        FirGetClassCall firGetClassCall = firExpression instanceof FirGetClassCall ? (FirGetClassCall) firExpression : null;
        if (firGetClassCall == null) {
            return false;
        }
        FirExpression argument = firGetClassCall.getArgument();
        FirClassReferenceExpression firClassReferenceExpression = argument instanceof FirClassReferenceExpression ? (FirClassReferenceExpression) argument : null;
        if (firClassReferenceExpression == null) {
            return false;
        }
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firClassReferenceExpression.getClassTypeRef());
        ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
        if (coneClassLikeType == null) {
            return false;
        }
        ClassId classId2 = coneClassLikeType.getLookupTag().getClassId();
        return classId2.getOuterClassId() != null && Intrinsics.areEqual(classId2.getShortClassName().asString(), "Container") && (kotlinJvmBinaryClassFindKotlinClass = KotlinClassFinderKt.findKotlinClass(this.kotlinClassFinder, classId2, MetadataHelpersKt.toJvmMetadataVersion(FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.session).getLanguageVersion()))) != null && specialJvmAnnotations.isAnnotatedWithContainerMetaAnnotation(kotlinJvmBinaryClassFindKotlinClass);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final KotlinJvmBinaryClass.AnnotationArgumentVisitor loadAnnotation(ClassId annotationClassId, final List<FirAnnotation> result) {
        final ConeClassLikeLookupTagImpl lookupTag = TypeConstructionUtilsKt.toLookupTag(annotationClassId);
        final boolean zIsImplicitRepeatableContainer = isImplicitRepeatableContainer(annotationClassId);
        return new AnnotationsLoaderVisitorImpl() { // from class: org.jetbrains.kotlin.fir.java.deserialization.AnnotationsLoader.loadAnnotation.1
            private final Map<Name, FirExpression> argumentMap;
            private final boolean visitNullNames;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.argumentMap = new LinkedHashMap();
            }

            @Override // org.jetbrains.kotlin.fir.java.deserialization.AnnotationsLoader.AnnotationsLoaderVisitorImpl
            public boolean getVisitNullNames() {
                return this.visitNullNames;
            }

            public void visitEnd() {
                if (AnnotationsLoader.this.isRepeatableWithImplicitContainer(lookupTag, this.argumentMap) || zIsImplicitRepeatableContainer) {
                    return;
                }
                List<FirAnnotation> list = result;
                AnnotationsLoader annotationsLoader = AnnotationsLoader.this;
                ConeClassLikeLookupTagImpl coneClassLikeLookupTagImpl = lookupTag;
                FirAnnotationBuilder firAnnotationBuilder = new FirAnnotationBuilder();
                firAnnotationBuilder.setAnnotationTypeRef(annotationsLoader.toDefaultResolvedTypeRef(coneClassLikeLookupTagImpl));
                FirAnnotationArgumentMappingBuilder firAnnotationArgumentMappingBuilder = new FirAnnotationArgumentMappingBuilder();
                firAnnotationArgumentMappingBuilder.getMapping().putAll(this.argumentMap);
                firAnnotationBuilder.setArgumentMapping(firAnnotationArgumentMappingBuilder.build());
                list.add(firAnnotationBuilder.mo289build());
            }

            @Override // org.jetbrains.kotlin.fir.java.deserialization.AnnotationsLoader.AnnotationsLoaderVisitorImpl
            public void visitExpression(Name name, FirExpression expr) {
                List<FirExpression> arguments;
                expr.getClass();
                if (name == null) {
                    return;
                }
                if (!zIsImplicitRepeatableContainer || !Intrinsics.areEqual(name, StandardClassIds$Annotations.ParameterNames.INSTANCE.getValue())) {
                    this.argumentMap.put(name, expr);
                    return;
                }
                FirCollectionLiteral firCollectionLiteral = expr instanceof FirCollectionLiteral ? (FirCollectionLiteral) expr : null;
                if (firCollectionLiteral == null || (arguments = firCollectionLiteral.getArgumentList().getArguments()) == null) {
                    return;
                }
                List<FirAnnotation> list = result;
                for (Object obj : arguments) {
                    if (obj instanceof FirAnnotation) {
                        list.add(obj);
                    }
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirResolvedTypeRef toDefaultResolvedTypeRef(ConeClassLikeLookupTag coneClassLikeLookupTag) {
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setConeType(TypeConstructionUtilsKt.constructClassType$default(coneClassLikeLookupTag, null, false, null, 7, null));
        return firResolvedTypeRefBuilder.build();
    }

    public final KotlinJvmBinaryClass.AnnotationArgumentVisitor loadAnnotationIfNotSpecial$org_jetbrains_kotlin_fir_jvm(ClassId annotationClassId, List<FirAnnotation> result) {
        annotationClassId.getClass();
        result.getClass();
        if (SpecialJvmAnnotations.INSTANCE.getSPECIAL_ANNOTATIONS().contains(annotationClassId)) {
            return null;
        }
        return loadAnnotation(annotationClassId, result);
    }

    public final KotlinJvmBinaryClass.AnnotationArgumentVisitor loadAnnotationMethodDefaultValue$org_jetbrains_kotlin_fir_jvm(final Function1<? super FirExpression, Unit> consumeResult) {
        consumeResult.getClass();
        return new AnnotationsLoaderVisitorImpl(this) { // from class: org.jetbrains.kotlin.fir.java.deserialization.AnnotationsLoader$loadAnnotationMethodDefaultValue$1
            private FirExpression defaultValue;
            private final boolean visitNullNames;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super();
                this.visitNullNames = true;
            }

            public final FirExpression getDefaultValue() {
                return this.defaultValue;
            }

            @Override // org.jetbrains.kotlin.fir.java.deserialization.AnnotationsLoader.AnnotationsLoaderVisitorImpl
            public boolean getVisitNullNames() {
                return this.visitNullNames;
            }

            public final void setDefaultValue(FirExpression firExpression) {
                this.defaultValue = firExpression;
            }

            public void visitEnd() {
                FirExpression firExpression = this.defaultValue;
                if (firExpression != null) {
                    consumeResult.invoke(firExpression);
                }
            }

            @Override // org.jetbrains.kotlin.fir.java.deserialization.AnnotationsLoader.AnnotationsLoaderVisitorImpl
            public void visitExpression(Name name, FirExpression expr) {
                expr.getClass();
                this.defaultValue = expr;
            }
        };
    }
}
