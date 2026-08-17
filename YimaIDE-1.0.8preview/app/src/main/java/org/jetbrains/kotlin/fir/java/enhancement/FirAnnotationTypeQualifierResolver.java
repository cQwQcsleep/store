package org.jetbrains.kotlin.fir.java.enhancement;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.declarations.EnumValueArgumentInfo;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.java.JavaAnnotationsMappingKt;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaClass;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.load.java.AbstractAnnotationTypeQualifierResolver;
import org.jetbrains.kotlin.load.java.JavaModuleAnnotationsProvider;
import org.jetbrains.kotlin.load.java.JavaTypeEnhancementState;
import org.jetbrains.kotlin.load.java.JavaTypeQualifiersByElementType;
import org.jetbrains.kotlin.load.java.JvmAnnotationNames;
import org.jetbrains.kotlin.load.java.structure.JavaAnnotationOwner;
import org.jetbrains.kotlin.load.java.structure.impl.JavaElementImpl;
import org.jetbrains.kotlin.load.java.structure.impl.source.SingleFileRootPsiPackage;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0010*\u00020\u00022\u0006\u0010\u001d\u001a\u00020\rH\u0014J\u0012\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001f*\u00020 H\u0002J\u0010\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020$R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u000eR\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00020\u0010*\u00020\u00028TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u0013\u001a\u00020\u0014*\u00020\u00028TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u0018*\u00020\u00028TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/enhancement/FirAnnotationTypeQualifierResolver;", "Lorg/jetbrains/kotlin/load/java/AbstractAnnotationTypeQualifierResolver;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "javaTypeEnhancementState", "Lorg/jetbrains/kotlin/load/java/JavaTypeEnhancementState;", "javaModuleAnnotationsProvider", "Lorg/jetbrains/kotlin/load/java/JavaModuleAnnotationsProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/load/java/JavaTypeEnhancementState;Lorg/jetbrains/kotlin/load/java/JavaModuleAnnotationsProvider;)V", "isK2", Argument.Delimiters.none, "()Z", "metaAnnotations", Argument.Delimiters.none, "getMetaAnnotations", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)Ljava/lang/Iterable;", "key", Argument.Delimiters.none, "getKey", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)Ljava/lang/Object;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "getFqName", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;)Lorg/jetbrains/kotlin/name/FqName;", "enumArguments", Argument.Delimiters.none, "onlyValue", "toEnumNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "extractDefaultQualifiers", "Lorg/jetbrains/kotlin/load/java/JavaTypeQualifiersByElementType;", "firClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnnotationTypeQualifierResolver extends AbstractAnnotationTypeQualifierResolver<FirAnnotation> implements FirSessionComponent {
    private final JavaModuleAnnotationsProvider javaModuleAnnotationsProvider;
    private final FirSession session;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirAnnotationTypeQualifierResolver(FirSession firSession, JavaTypeEnhancementState javaTypeEnhancementState, JavaModuleAnnotationsProvider javaModuleAnnotationsProvider) {
        super(javaTypeEnhancementState);
        firSession.getClass();
        javaTypeEnhancementState.getClass();
        javaModuleAnnotationsProvider.getClass();
        this.session = firSession;
        this.javaModuleAnnotationsProvider = javaModuleAnnotationsProvider;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<String> toEnumNames(FirExpression firExpression) {
        Name enumEntryName;
        if (firExpression instanceof FirCollectionLiteral) {
            List<FirExpression> arguments = ((FirCall) firExpression).getArgumentList().getArguments();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = arguments.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, toEnumNames((FirExpression) it.next()));
            }
            return arrayList;
        }
        if (!(firExpression instanceof FirVarargArgumentsExpression)) {
            EnumValueArgumentInfo enumValueArgumentInfoExtractEnumValueArgumentInfo = FirAnnotationUtilsKt.extractEnumValueArgumentInfo(firExpression);
            return CollectionsKt.listOfNotNull((enumValueArgumentInfoExtractEnumValueArgumentInfo == null || (enumEntryName = enumValueArgumentInfoExtractEnumValueArgumentInfo.getEnumEntryName()) == null) ? null : enumEntryName.asString());
        }
        List<FirExpression> arguments2 = ((FirVarargArgumentsExpression) firExpression).getArguments();
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it2 = arguments2.iterator();
        while (it2.hasNext()) {
            CollectionsKt.addAll(arrayList2, toEnumNames((FirExpression) it2.next()));
        }
        return arrayList2;
    }

    public Iterable<String> enumArguments(FirAnnotation firAnnotation, boolean z) {
        firAnnotation.getClass();
        Collection<FirExpression> collectionValues = firAnnotation.getArgumentMapping().getMapping().values();
        ArrayList arrayList = new ArrayList();
        for (FirExpression firExpression : collectionValues) {
            CollectionsKt.addAll(arrayList, (z && (firExpression instanceof FirNamedArgumentExpression) && !Intrinsics.areEqual(((FirNamedArgumentExpression) firExpression).getName(), JvmAnnotationNames.DEFAULT_ANNOTATION_MEMBER_NAME)) ? CollectionsKt.emptyList() : toEnumNames(firExpression));
        }
        return arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x009a  */
    /* JADX WARN: Multi-variable type inference failed */
    public final JavaTypeQualifiersByElementType extractDefaultQualifiers(FirRegularClass firClass) {
        FirAnnotationTypeQualifierResolver firAnnotationTypeQualifierResolver;
        JavaTypeQualifiersByElementType javaTypeQualifiersByElementTypeExtractAndMergeDefaultQualifiers$default;
        JavaTypeQualifiersByElementType javaTypeQualifiersByElementType;
        JavaElementImpl javaPackage;
        boolean z;
        firClass.getClass();
        ClassId classId = firClass.getSymbol().getClassId();
        ClassId outerClassId = classId.getOuterClassId();
        JavaTypeQualifiersByElementType javaTypeQualifiersByElementTypeExtractAndMergeDefaultQualifiers = null;
        if (outerClassId == null) {
            KtSourceElement source = firClass.getSource();
            KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null;
            List annotationsForModuleOwnerOfClass = this.javaModuleAnnotationsProvider.getAnnotationsForModuleOwnerOfClass(classId);
            if (annotationsForModuleOwnerOfClass != null) {
                firAnnotationTypeQualifierResolver = this;
                javaTypeQualifiersByElementTypeExtractAndMergeDefaultQualifiers$default = AbstractAnnotationTypeQualifierResolver.extractAndMergeDefaultQualifiers$default(firAnnotationTypeQualifierResolver, (JavaTypeQualifiersByElementType) null, JavaAnnotationsMappingKt.convertAnnotationsToFir(annotationsForModuleOwnerOfClass, this.session, ktSourceElementFakeElement$default), false, 4, (Object) null);
            } else {
                firAnnotationTypeQualifierResolver = this;
                javaTypeQualifiersByElementTypeExtractAndMergeDefaultQualifiers$default = null;
            }
            FirJavaClass firJavaClass = firClass instanceof FirJavaClass ? (FirJavaClass) firClass : null;
            if (firJavaClass != null && (javaPackage = firJavaClass.getJavaPackage()) != null) {
                if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(firAnnotationTypeQualifierResolver.session).supportsFeature(LanguageFeature.CheckPackageInfoNullnessAnnotations)) {
                    z = false;
                } else {
                    JavaElementImpl javaElementImpl = javaPackage instanceof JavaElementImpl ? javaPackage : null;
                    if ((javaElementImpl != null ? javaElementImpl.getPsi() : null) instanceof SingleFileRootPsiPackage) {
                        z = true;
                    } else {
                        z = false;
                    }
                }
                javaTypeQualifiersByElementTypeExtractAndMergeDefaultQualifiers = firAnnotationTypeQualifierResolver.extractAndMergeDefaultQualifiers(javaTypeQualifiersByElementTypeExtractAndMergeDefaultQualifiers$default, JavaAnnotationsMappingKt.convertAnnotationsToFir((JavaAnnotationOwner) javaPackage, firAnnotationTypeQualifierResolver.session, ktSourceElementFakeElement$default), z);
            }
            if (javaTypeQualifiersByElementTypeExtractAndMergeDefaultQualifiers == null) {
                javaTypeQualifiersByElementType = javaTypeQualifiersByElementTypeExtractAndMergeDefaultQualifiers$default;
            }
            return AbstractAnnotationTypeQualifierResolver.extractAndMergeDefaultQualifiers$default(firAnnotationTypeQualifierResolver, javaTypeQualifiersByElementType, firClass.getAnnotations(), false, 4, (Object) null);
        }
        FirClassLikeSymbol<?> classLikeSymbolByClassId = FirSymbolProviderKt.getSymbolProvider(this.session).getClassLikeSymbolByClassId(outerClassId);
        FirClassLikeDeclaration firClassLikeDeclaration = classLikeSymbolByClassId != null ? (FirClassLikeDeclaration) classLikeSymbolByClassId.getFir() : null;
        FirRegularClass firRegularClass = firClassLikeDeclaration instanceof FirRegularClass ? (FirRegularClass) firClassLikeDeclaration : null;
        javaTypeQualifiersByElementTypeExtractAndMergeDefaultQualifiers = firRegularClass != null ? extractDefaultQualifiers(firRegularClass) : null;
        firAnnotationTypeQualifierResolver = this;
        javaTypeQualifiersByElementType = javaTypeQualifiersByElementTypeExtractAndMergeDefaultQualifiers;
        return AbstractAnnotationTypeQualifierResolver.extractAndMergeDefaultQualifiers$default(firAnnotationTypeQualifierResolver, javaTypeQualifiersByElementType, firClass.getAnnotations(), false, 4, (Object) null);
    }

    public FqName getFqName(FirAnnotation firAnnotation) {
        ConeClassLikeLookupTag lookupTag;
        ClassId classId;
        firAnnotation.getClass();
        FirResolvedTypeRef annotationTypeRef = firAnnotation.getAnnotationTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
        if (coneClassLikeType == null || (lookupTag = coneClassLikeType.getLookupTag()) == null || (classId = lookupTag.getClassId()) == null) {
            return null;
        }
        return classId.asSingleFqName();
    }

    public Object getKey(FirAnnotation firAnnotation) {
        firAnnotation.getClass();
        FirResolvedTypeRef annotationTypeRef = firAnnotation.getAnnotationTypeRef();
        FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
        coneClassLikeType.getClass();
        return coneClassLikeType.getLookupTag();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public Iterable<FirAnnotation> getMetaAnnotations(FirAnnotation firAnnotation) {
        ConeClassLikeLookupTag lookupTag;
        FirClassLikeSymbol<?> symbol;
        FirClassLikeDeclaration firClassLikeDeclaration;
        firAnnotation.getClass();
        FirResolvedTypeRef annotationTypeRef = firAnnotation.getAnnotationTypeRef();
        List<FirAnnotation> listEmptyList = null;
        FirResolvedTypeRef firResolvedTypeRef = annotationTypeRef instanceof FirResolvedTypeRef ? annotationTypeRef : null;
        ConeKotlinType coneType = firResolvedTypeRef != null ? firResolvedTypeRef.getConeType() : null;
        ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
        if (coneClassLikeType != null && (lookupTag = coneClassLikeType.getLookupTag()) != null && (symbol = ToSymbolUtilsKt.toSymbol(lookupTag, this.session)) != null && (firClassLikeDeclaration = (FirClassLikeDeclaration) symbol.getFir()) != null) {
            listEmptyList = firClassLikeDeclaration.getAnnotations();
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        return listEmptyList;
    }

    public boolean isK2() {
        return true;
    }
}
