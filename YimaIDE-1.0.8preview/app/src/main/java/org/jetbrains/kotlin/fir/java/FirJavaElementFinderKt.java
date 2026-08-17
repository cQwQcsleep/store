package org.jetbrains.kotlin.fir.java;

import com.intellij.openapi.util.Pair;
import com.intellij.psi.DummyHolderViewProvider;
import com.intellij.psi.PsiManager;
import com.intellij.psi.impl.compiled.ClsFileImpl;
import com.intellij.psi.impl.java.stubs.ClsStubPsiFactory;
import com.intellij.psi.impl.java.stubs.JavaClassReferenceListElementType;
import com.intellij.psi.impl.java.stubs.JavaStubElementTypes;
import com.intellij.psi.impl.java.stubs.PsiJavaFileStub;
import com.intellij.psi.impl.java.stubs.impl.PsiClassReferenceListStubImpl;
import com.intellij.psi.impl.java.stubs.impl.PsiClassStubImpl;
import com.intellij.psi.impl.java.stubs.impl.PsiJavaFileStubImpl;
import com.intellij.psi.impl.java.stubs.impl.PsiTypeParameterListStubImpl;
import com.intellij.psi.impl.java.stubs.impl.PsiTypeParameterStubImpl;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.ArrayUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.PrimitiveType;
import org.jetbrains.kotlin.builtins.jvm.JavaToKotlinClassMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.java.FirJavaElementFinderKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypeResolverVisitor;
import org.jetbrains.kotlin.fir.resolve.transformers.SupertypeComputationSession;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntegerLiteralType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeStubType;
import org.jetbrains.kotlin.fir.types.ConeTypeConstructorMarker;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNameUnsafe;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000~\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0012\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0005H\u0002\u001a\u001a\u0010\u0007\u001a\u00020\b*\u00020\u00052\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nH\u0002\u001a$\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0003H\u0002\u001a.\u0010\u0010\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0012\u001a\u00020\u00032\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a/\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00162\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002¢\u0006\u0002\u0010\u001c\u001a4\u0010\u001d\u001a\u00020\b2\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00182\u001e\u0010\u001e\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u001b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u001a0\u001f0\u0001H\u0002\u001a\u0018\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002\u001a\u0014\u0010'\u001a\u00020\u001b*\u00020(2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0014\u0010'\u001a\u00020\u001b*\u00020)2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0014\u0010*\u001a\u00020\u001b*\u00020)2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0014\u0010'\u001a\u00020\u001b*\u00020+2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\"\u000e\u0010&\u001a\u00020\u001bX\u0082T¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"resolveSupertypesOnAir", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "collectAllDependentSourceSessions", "collectAllDependentSourceSessionsTo", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "dependencies", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirModuleData;", "packFlags", Argument.Delimiters.none, "addSupertypesReferencesLists", "Lcom/intellij/psi/impl/java/stubs/impl/PsiClassStubImpl;", "firRegularClass", "superTypeRefs", "newReferenceList", ModuleXmlParser.TYPE, "Lcom/intellij/psi/impl/java/stubs/JavaClassReferenceListElementType;", "parent", "Lcom/intellij/psi/stubs/StubElement;", "types", Argument.Delimiters.none, Argument.Delimiters.none, "(Lcom/intellij/psi/impl/java/stubs/JavaClassReferenceListElementType;Lcom/intellij/psi/stubs/StubElement;[Ljava/lang/String;)V", "newTypeParameterList", "parameters", "Lcom/intellij/openapi/util/Pair;", "createJavaFileStub", "Lcom/intellij/psi/impl/java/stubs/PsiJavaFileStub;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "psiManager", "Lcom/intellij/psi/PsiManager;", "ERROR_TYPE_STUB", "mapToCanonicalString", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "mapToCanonicalNoExpansionString", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "org.jetbrains.kotlin:fir-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaElementFinderKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Modality.values().length];
            try {
                iArr[Modality.FINAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Modality.ABSTRACT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ProjectionKind.values().length];
            try {
                iArr2[ProjectionKind.STAR.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[ProjectionKind.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[ProjectionKind.OUT.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[ProjectionKind.INVARIANT.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void addSupertypesReferencesLists(PsiClassStubImpl<?> psiClassStubImpl, FirRegularClass firRegularClass, List<? extends FirTypeRef> list, FirSession firSession) {
        FirRegularClassSymbol regularClassSymbol;
        List<? extends FirTypeRef> list2 = list;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                if (!(((FirTypeRef) it.next()) instanceof FirResolvedTypeRef)) {
                    wec.a("Supertypes for light class ", psiClassStubImpl.getQualifiedName(), " are being added too early");
                    return;
                }
            }
        }
        boolean z = firRegularClass.getClassKind() == ClassKind.INTERFACE;
        ArrayList arrayList = new ArrayList();
        Iterator<? extends FirTypeRef> it2 = list.iterator();
        String str = null;
        while (it2.hasNext()) {
            FirResolvedTypeRef firResolvedTypeRef = (FirTypeRef) it2.next();
            FirResolvedTypeRef firResolvedTypeRef2 = firResolvedTypeRef instanceof FirResolvedTypeRef ? firResolvedTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef2 != null ? firResolvedTypeRef2.getConeType() : null;
            if (!(coneType instanceof ConeClassLikeType)) {
                coneType = null;
            }
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType;
            if (coneClassLikeType != null && (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeType, firSession)) != null) {
                String strMapToCanonicalString = mapToCanonicalString(coneClassLikeType, firSession);
                if (z || regularClassSymbol.getClassKind() == ClassKind.INTERFACE) {
                    arrayList.add(strMapToCanonicalString);
                } else {
                    str = strMapToCanonicalString;
                }
            }
        }
        if (psiClassStubImpl.isInterface()) {
            if (!arrayList.isEmpty() && psiClassStubImpl.isAnnotationType()) {
                arrayList.remove("java.lang.annotation.Annotation");
            }
            JavaClassReferenceListElementType javaClassReferenceListElementType = JavaStubElementTypes.EXTENDS_LIST;
            javaClassReferenceListElementType.getClass();
            String[] stringArray = ArrayUtil.toStringArray(arrayList);
            stringArray.getClass();
            newReferenceList(javaClassReferenceListElementType, psiClassStubImpl, stringArray);
            JavaClassReferenceListElementType javaClassReferenceListElementType2 = JavaStubElementTypes.IMPLEMENTS_LIST;
            javaClassReferenceListElementType2.getClass();
            String[] strArr = ArrayUtil.EMPTY_STRING_ARRAY;
            strArr.getClass();
            newReferenceList(javaClassReferenceListElementType2, psiClassStubImpl, strArr);
            return;
        }
        if (str == null || Intrinsics.areEqual("java/lang/Object", str) || (psiClassStubImpl.isEnum() && Intrinsics.areEqual("java/lang/Enum", str))) {
            JavaClassReferenceListElementType javaClassReferenceListElementType3 = JavaStubElementTypes.EXTENDS_LIST;
            javaClassReferenceListElementType3.getClass();
            String[] strArr2 = ArrayUtil.EMPTY_STRING_ARRAY;
            strArr2.getClass();
            newReferenceList(javaClassReferenceListElementType3, psiClassStubImpl, strArr2);
        } else {
            JavaClassReferenceListElementType javaClassReferenceListElementType4 = JavaStubElementTypes.EXTENDS_LIST;
            javaClassReferenceListElementType4.getClass();
            newReferenceList(javaClassReferenceListElementType4, psiClassStubImpl, new String[]{str});
        }
        JavaClassReferenceListElementType javaClassReferenceListElementType5 = JavaStubElementTypes.IMPLEMENTS_LIST;
        javaClassReferenceListElementType5.getClass();
        String[] stringArray2 = ArrayUtil.toStringArray(arrayList);
        stringArray2.getClass();
        newReferenceList(javaClassReferenceListElementType5, psiClassStubImpl, stringArray2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<FirSession> collectAllDependentSourceSessions(FirSession firSession) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        collectAllDependentSourceSessionsTo(firSession, linkedHashSet);
        return CollectionsKt.toList(linkedHashSet);
    }

    private static final void collectAllDependentSourceSessionsTo(Set<FirSession> set, Collection<? extends FirModuleData> collection) {
        Iterator<? extends FirModuleData> it = collection.iterator();
        while (it.hasNext()) {
            FirSession session = it.next().getSession();
            if (session.getKind() == FirSession.Kind.Source && set.add(session)) {
                collectAllDependentSourceSessionsTo(session, set);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PsiJavaFileStub createJavaFileStub(final FqName fqName, PsiManager psiManager) {
        final PsiJavaFileStubImpl psiJavaFileStubImpl = new PsiJavaFileStubImpl(fqName.asString(), true);
        psiJavaFileStubImpl.setPsiFactory(ClsStubPsiFactory.INSTANCE);
        final DummyHolderViewProvider dummyHolderViewProvider = new DummyHolderViewProvider(psiManager);
        psiJavaFileStubImpl.setPsi(new ClsFileImpl(dummyHolderViewProvider) { // from class: org.jetbrains.kotlin.fir.java.FirJavaElementFinderKt$createJavaFileStub$fakeFile$1
            public String getPackageName() {
                return fqName.asString();
            }

            public boolean isPhysical() {
                return false;
            }

            /* JADX INFO: renamed from: getStub, reason: from getter and merged with bridge method [inline-methods] */
            public PsiJavaFileStubImpl m548getStub() {
                return psiJavaFileStubImpl;
            }
        });
        return psiJavaFileStubImpl;
    }

    private static final String mapToCanonicalNoExpansionString(ConeClassLikeType coneClassLikeType, final FirSession firSession) {
        FqNameUnsafe fqNameUnsafeAsSingleFqName;
        String strMapToCanonicalString = "java.lang.Object";
        if (Intrinsics.areEqual(coneClassLikeType.getLookupTag().getClassId(), StandardClassIds.INSTANCE.getArray())) {
            StringBuilder sb = new StringBuilder();
            ConeKotlinTypeProjection coneKotlinTypeProjection = coneClassLikeType.getTypeArguments()[0];
            if (!(coneKotlinTypeProjection instanceof ConeStarProjection)) {
                if (!(coneKotlinTypeProjection instanceof ConeKotlinTypeProjection)) {
                    bu8.a();
                    return null;
                }
                if (coneKotlinTypeProjection.getKind() == ProjectionKind.IN) {
                    strMapToCanonicalString = "java.lang.Void";
                } else {
                    ConeKotlinType type = coneKotlinTypeProjection.getType();
                    type.getClass();
                    strMapToCanonicalString = mapToCanonicalString((ConeClassLikeType) type, firSession);
                }
            }
            sb.append(strMapToCanonicalString);
            sb.append("[]");
            return sb.toString();
        }
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
        ConeTypeConstructorMarker coneTypeConstructorMarker = (ConeTypeConstructorMarker) typeContext.m691typeConstructor((RigidTypeMarker) coneClassLikeType);
        PrimitiveType primitiveType = typeContext.getPrimitiveType(coneTypeConstructorMarker);
        if (primitiveType != null) {
            return JvmPrimitiveType.get(primitiveType).getWrapperFqName().asString();
        }
        PrimitiveType primitiveArrayType = typeContext.getPrimitiveArrayType(coneTypeConstructorMarker);
        if (primitiveArrayType != null) {
            return JvmPrimitiveType.get(primitiveArrayType).getJavaKeywordName() + "[]";
        }
        FqNameUnsafe classFqNameUnsafe = typeContext.getClassFqNameUnsafe(coneTypeConstructorMarker);
        if (classFqNameUnsafe == null) {
            return "java.lang.Object";
        }
        ClassId classIdMapKotlinToJava = JavaToKotlinClassMap.INSTANCE.mapKotlinToJava(classFqNameUnsafe);
        if (classIdMapKotlinToJava != null && (fqNameUnsafeAsSingleFqName = classIdMapKotlinToJava.asSingleFqName()) != null) {
            classFqNameUnsafe = fqNameUnsafeAsSingleFqName;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(classFqNameUnsafe);
        ConeTypeProjection[] typeArguments = coneClassLikeType.getTypeArguments();
        ConeTypeProjection[] coneTypeProjectionArr = !(typeArguments.length == 0) ? typeArguments : null;
        String strJoinToString$default = coneTypeProjectionArr != null ? ArraysKt.joinToString$default(coneTypeProjectionArr, ", ", "<", ">", 0, (CharSequence) null, new Function1() { // from class: e95
            public final Object invoke(Object obj) {
                return FirJavaElementFinderKt.mapToCanonicalNoExpansionString$lambda$0$3(firSession, (ConeTypeProjection) obj);
            }
        }, 24, (Object) null) : null;
        if (strJoinToString$default == null) {
            strJoinToString$default = Argument.Delimiters.none;
        }
        sb2.append(strJoinToString$default);
        return sb2.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence mapToCanonicalNoExpansionString$lambda$0$3(FirSession firSession, ConeTypeProjection coneTypeProjection) {
        coneTypeProjection.getClass();
        return mapToCanonicalString(coneTypeProjection, firSession);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final String mapToCanonicalString(ConeKotlinType coneKotlinType, FirSession firSession) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (coneKotlinType instanceof ConeClassLikeType) {
            return mapToCanonicalString((ConeClassLikeType) coneKotlinType, firSession);
        }
        if (!(coneKotlinType instanceof ConeTypeVariableType) && !(coneKotlinType instanceof ConeFlexibleType) && !(coneKotlinType instanceof ConeCapturedType) && !(coneKotlinType instanceof ConeDefinitelyNotNullType) && !(coneKotlinType instanceof ConeIntersectionType) && !(coneKotlinType instanceof ConeStubType) && !(coneKotlinType instanceof ConeIntegerLiteralType)) {
            if (!(coneKotlinType instanceof ConeLookupTagBasedType)) {
                bu8.a();
                return null;
            }
            String strAsString = ((ConeLookupTagBasedType) coneKotlinType).getLookupTag().getName().asString();
            strAsString.getClass();
            return strAsString;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unexpected type: " + coneKotlinType.getClass(), (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withConeTypeEntry(exceptionAttachmentBuilder, ModuleXmlParser.TYPE, coneKotlinType);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void newReferenceList(JavaClassReferenceListElementType javaClassReferenceListElementType, StubElement<?> stubElement, String[] strArr) {
        new PsiClassReferenceListStubImpl(javaClassReferenceListElementType, stubElement, strArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void newTypeParameterList(StubElement<?> stubElement, List<? extends Pair<String, String[]>> list) {
        PsiTypeParameterListStubImpl psiTypeParameterListStubImpl = new PsiTypeParameterListStubImpl(stubElement);
        for (Pair<String, String[]> pair : list) {
            PsiTypeParameterStubImpl psiTypeParameterStubImpl = new PsiTypeParameterStubImpl(psiTypeParameterListStubImpl, (String) pair.first);
            JavaClassReferenceListElementType javaClassReferenceListElementType = JavaStubElementTypes.EXTENDS_BOUND_LIST;
            javaClassReferenceListElementType.getClass();
            Object obj = pair.second;
            obj.getClass();
            newReferenceList(javaClassReferenceListElementType, psiTypeParameterStubImpl, (String[]) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int packFlags(FirRegularClass firRegularClass) {
        int i;
        int i2;
        Visibility visibility = firRegularClass.getStatus().getVisibility();
        if (Intrinsics.areEqual(visibility, Visibilities.Private.INSTANCE)) {
            i = 2;
        } else if (Intrinsics.areEqual(visibility, Visibilities.Protected.INSTANCE)) {
            i = 4;
        } else {
            i = Intrinsics.areEqual(visibility, Visibilities.Public.INSTANCE) ? 1 : 4096;
        }
        Modality modality = firRegularClass.getStatus().getModality();
        int i3 = modality == null ? -1 : WhenMappings.$EnumSwitchMapping$0[modality.ordinal()];
        if (i3 != 1) {
            i2 = i3 != 2 ? 0 : 1024;
        } else {
            i2 = 16;
        }
        int i4 = i | i2;
        return (!FirDeclarationUtilKt.getClassId(firRegularClass).isNestedClass() || firRegularClass.getStatus().isInner()) ? i4 : i4 | 8;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<FirTypeRef> resolveSupertypesOnAir(FirRegularClass firRegularClass, FirSession firSession) {
        FirSupertypeResolverVisitor firSupertypeResolverVisitor = new FirSupertypeResolverVisitor(firSession, new SupertypeComputationSession(), new ScopeSession(), null, null, null, null, 120, null);
        FirFile firClassifierContainerFile = FirProviderKt.getFirProvider(firSession).getFirClassifierContainerFile(firRegularClass.getSymbol());
        FirFile useSiteFile = firSupertypeResolverVisitor.getUseSiteFile();
        try {
            firSupertypeResolverVisitor.setUseSiteFile(firClassifierContainerFile);
            return firSupertypeResolverVisitor.resolveSpecificClassLikeSupertypes(firRegularClass, firRegularClass.getSuperTypeRefs(), true);
        } finally {
            firSupertypeResolverVisitor.setUseSiteFile(useSiteFile);
        }
    }

    private static final void collectAllDependentSourceSessionsTo(FirSession firSession, Set<FirSession> set) {
        FirModuleData moduleData = FirModuleDataKt.getModuleData(firSession);
        collectAllDependentSourceSessionsTo(set, moduleData.getDependencies());
        collectAllDependentSourceSessionsTo(set, moduleData.getFriendDependencies());
        collectAllDependentSourceSessionsTo(set, moduleData.getDependsOnDependencies());
    }

    private static final String mapToCanonicalString(ConeClassLikeType coneClassLikeType, FirSession firSession) {
        return coneClassLikeType instanceof ConeErrorType ? "java.lang.Object" : mapToCanonicalNoExpansionString(TypeExpansionUtilsKt.fullyExpandedType$default(coneClassLikeType, firSession, (Function1) null, 2, (Object) null), firSession);
    }

    private static final String mapToCanonicalString(ConeTypeProjection coneTypeProjection, FirSession firSession) {
        String str;
        if (coneTypeProjection instanceof ConeStarProjection) {
            return "?";
        }
        if (coneTypeProjection instanceof ConeKotlinTypeProjection) {
            int i = WhenMappings.$EnumSwitchMapping$1[coneTypeProjection.getKind().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    str = "? super ";
                } else if (i == 3) {
                    str = "? extends ";
                } else if (i == 4) {
                    str = Argument.Delimiters.none;
                } else {
                    bu8.a();
                    return null;
                }
                return str + mapToCanonicalString(((ConeKotlinTypeProjection) coneTypeProjection).getType(), firSession);
            }
            k2d.a("Should be handled in the case above");
            return null;
        }
        bu8.a();
        return null;
    }
}
