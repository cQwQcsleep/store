package org.jetbrains.kotlin.fir.java;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Pair;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElementFinder;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiPackage;
import com.intellij.psi.impl.compiled.ClsClassImpl;
import com.intellij.psi.impl.file.PsiPackageImpl;
import com.intellij.psi.impl.java.stubs.JavaClassReferenceListElementType;
import com.intellij.psi.impl.java.stubs.JavaStubElementTypes;
import com.intellij.psi.impl.java.stubs.PsiClassStub;
import com.intellij.psi.impl.java.stubs.impl.PsiAnnotationStubImpl;
import com.intellij.psi.impl.java.stubs.impl.PsiClassStubImpl;
import com.intellij.psi.impl.java.stubs.impl.PsiModifierListStubImpl;
import com.intellij.psi.impl.java.stubs.impl.PsiTypeParameterListStubImpl;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.stubs.StubElement;
import com.intellij.util.ArrayUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.PreprocessCommandLineArgumentsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.analysis.checkers.FirAnnotationHelpersKt;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.AnnotationTargetUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.java.FirJavaElementFinder;
import org.jetbrains.kotlin.fir.resolve.providers.FirProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.load.java.JvmAnnotationNames;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.FqNamesUtilKt;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.jvm.KotlinFinderMarker;
import org.jetbrains.kotlin.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;
import org.jetbrains.kotlin.utils.KotlinToJavaAnnotationTargetsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¤\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u00018B\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u0014H\u0016J#\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001fH\u0016¢\u0006\u0002\u0010 J#\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001fH\u0016¢\u0006\u0002\u0010\"J\u001a\u0010#\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\f\u0010$\u001a\u00020\u0014*\u00020\u0015H\u0002J(\u0010%\u001a\u0006\u0012\u0002\b\u00030&2\u0006\u0010'\u001a\u00020\u00152\u0006\u0010(\u001a\u00020)2\n\u0010*\u001a\u0006\u0012\u0002\b\u00030+H\u0002J \u0010,\u001a\u0006\u0012\u0002\b\u00030&2\u0006\u0010-\u001a\u00020.2\n\u0010*\u001a\u0006\u0012\u0002\b\u00030+H\u0002J\u0012\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00140\u000e*\u000200H\u0002J\u001e\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\f\u00105\u001a\b\u0012\u0004\u0012\u00020706H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\n\u001a\u00070\u000b¢\u0006\u0002\b\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R4\u0010\u0010\u001a(\u0012\u0004\u0012\u00020\u0012\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u000e0\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00069"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/FirJavaElementFinder;", "Lcom/intellij/psi/PsiElementFinder;", "Lorg/jetbrains/kotlin/resolve/jvm/KotlinFinderMarker;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "project", "Lcom/intellij/openapi/project/Project;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lcom/intellij/openapi/project/Project;)V", "psiManager", "Lcom/intellij/psi/PsiManager;", "Lorg/jetbrains/annotations/NotNull;", "firProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirProvider;", "fileCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/name/FqName;", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", Argument.Delimiters.none, "findPackage", "Lcom/intellij/psi/PsiPackage;", "qualifiedName", "getClasses", Argument.Delimiters.none, "Lcom/intellij/psi/PsiClass;", "psiPackage", "scope", "Lcom/intellij/psi/search/GlobalSearchScope;", "(Lcom/intellij/psi/PsiPackage;Lcom/intellij/psi/search/GlobalSearchScope;)[Lcom/intellij/psi/PsiClass;", "findClasses", "(Ljava/lang/String;Lcom/intellij/psi/search/GlobalSearchScope;)[Lcom/intellij/psi/PsiClass;", "findClass", "jvmName", "buildFileAsClassStub", "Lcom/intellij/psi/impl/java/stubs/PsiClassStub;", "firFile", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "parent", "Lcom/intellij/psi/stubs/StubElement;", "buildStub", "firClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "findTargets", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "buildFieldStubForConst", Argument.Delimiters.none, "firProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "classStub", "Lcom/intellij/psi/impl/java/stubs/impl/PsiClassStubImpl;", "Lcom/intellij/psi/impl/compiled/ClsClassImpl;", "FirPsiPackage", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaElementFinder extends PsiElementFinder implements FirSessionComponent, KotlinFinderMarker {
    private final FirCache fileCache;
    private final List<FirProvider> firProviders;
    private final PsiManager psiManager;
    private final FirSession session;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/FirJavaElementFinder$FirPsiPackage;", "Lcom/intellij/psi/impl/file/PsiPackageImpl;", "psiManager", "Lcom/intellij/psi/PsiManager;", "qualifiedName", Argument.Delimiters.none, "<init>", "(Lcom/intellij/psi/PsiManager;Ljava/lang/String;)V", "isValid", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class FirPsiPackage extends PsiPackageImpl {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FirPsiPackage(PsiManager psiManager, String str) {
            super(psiManager, str);
            psiManager.getClass();
            str.getClass();
        }

        public boolean isValid() {
            return true;
        }
    }

    public FirJavaElementFinder(FirSession firSession, Project project) {
        firSession.getClass();
        project.getClass();
        this.session = firSession;
        PsiManager psiManager = PsiManager.getInstance(project);
        psiManager.getClass();
        this.psiManager = psiManager;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(FirProviderKt.getFirProvider(firSession));
        List list = listCreateListBuilder;
        Iterator it = FirJavaElementFinderKt.collectAllDependentSourceSessions(firSession).iterator();
        while (it.hasNext()) {
            list.add(FirProviderKt.getFirProvider((FirSession) it.next()));
        }
        this.firProviders = CollectionsKt.build(listCreateListBuilder);
        this.fileCache = FirCachesFactoryKt.getFirCachesFactory(this.session).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.java.FirJavaElementFinder$fileCache$1
            public final Map<String, List<FirFile>> invoke(FqName fqName, Void r5) {
                fqName.getClass();
                List list2 = this.this$0.firProviders;
                ArrayList arrayList = new ArrayList();
                Iterator it2 = list2.iterator();
                while (it2.hasNext()) {
                    CollectionsKt.addAll(arrayList, ((FirProvider) it2.next()).getFirFilesByPackage(fqName));
                }
                FirJavaElementFinder firJavaElementFinder = this.this$0;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Object obj : arrayList) {
                    String strJvmName = firJavaElementFinder.jvmName((FirFile) obj);
                    Object arrayList2 = linkedHashMap.get(strJvmName);
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                        linkedHashMap.put(strJvmName, arrayList2);
                    }
                    ((List) arrayList2).add(obj);
                }
                return linkedHashMap;
            }
        });
    }

    private final void buildFieldStubForConst(FirProperty firProperty, PsiClassStubImpl<ClsClassImpl> classStub) {
        if (firProperty.getStatus().isConst()) {
            new PsiModifierListStubImpl(new FirJavaElementFinder$buildFieldStubForConst$psiField$1(classStub, firProperty, this, JavaStubElementTypes.FIELD), 25);
        }
    }

    private final PsiClassStub<?> buildFileAsClassStub(FirFile firFile, ClassId classId, StubElement<?> parent) {
        PsiClassStubImpl<ClsClassImpl> psiClassStubImpl = new PsiClassStubImpl<>(JavaStubElementTypes.CLASS, parent, classId.asSingleFqName().asString(), classId.getRelativeClassName().asString(), (String) null, PsiClassStubImpl.packFlags(false, false, false, false, false, false, false, false, false, false, false));
        List<FirDeclaration> declarations = firFile.getDeclarations();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declarations) {
            if (obj instanceof FirProperty) {
                arrayList.add(obj);
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            buildFieldStubForConst((FirProperty) it.next(), psiClassStubImpl);
        }
        new PsiModifierListStubImpl(psiClassStubImpl, 17);
        new PsiTypeParameterListStubImpl(psiClassStubImpl);
        JavaClassReferenceListElementType javaClassReferenceListElementType = JavaStubElementTypes.EXTENDS_LIST;
        javaClassReferenceListElementType.getClass();
        String[] strArr = ArrayUtil.EMPTY_STRING_ARRAY;
        strArr.getClass();
        FirJavaElementFinderKt.newReferenceList(javaClassReferenceListElementType, psiClassStubImpl, strArr);
        JavaClassReferenceListElementType javaClassReferenceListElementType2 = JavaStubElementTypes.IMPLEMENTS_LIST;
        javaClassReferenceListElementType2.getClass();
        strArr.getClass();
        FirJavaElementFinderKt.newReferenceList(javaClassReferenceListElementType2, psiClassStubImpl, strArr);
        return psiClassStubImpl;
    }

    private final PsiClassStub<?> buildStub(FirRegularClass firClass, StubElement<?> parent) {
        Collection collectionEmptyList;
        List<FirTypeRef> listResolveSupertypesOnAir;
        List<FirBasedSymbol<?>> declarationSymbols;
        PsiClassStubImpl psiClassStubImpl = new PsiClassStubImpl(JavaStubElementTypes.CLASS, parent, FirDeclarationUtilKt.getClassId(firClass).asSingleFqName().asString(), firClass.getName().getIdentifier(), (String) null, PsiClassStubImpl.packFlags(false, firClass.getClassKind() == ClassKind.INTERFACE, firClass.getClassKind() == ClassKind.ENUM_CLASS, false, false, firClass.getClassKind() == ClassKind.ANNOTATION_CLASS, false, false, false, false, false));
        List<FirDeclaration> declarations = firClass.getDeclarations();
        ArrayList arrayList = new ArrayList();
        for (Object obj : declarations) {
            if (obj instanceof FirProperty) {
                arrayList.add(obj);
            }
        }
        FirRegularClassSymbol companionObjectSymbol = firClass.getCompanionObjectSymbol();
        if (companionObjectSymbol == null || (declarationSymbols = companionObjectSymbol.getDeclarationSymbols()) == null) {
            collectionEmptyList = CollectionsKt.emptyList();
        } else {
            List<FirBasedSymbol<?>> list = declarationSymbols;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList2.add(((FirBasedSymbol) it.next()).getFir());
            }
            collectionEmptyList = new ArrayList();
            for (Object obj2 : arrayList2) {
                if (obj2 instanceof FirProperty) {
                    collectionEmptyList.add(obj2);
                }
            }
        }
        Iterator it2 = CollectionsKt.plus(arrayList, collectionEmptyList).iterator();
        while (it2.hasNext()) {
            buildFieldStubForConst((FirProperty) it2.next(), psiClassStubImpl);
        }
        PsiModifierListStubImpl psiModifierListStubImpl = new PsiModifierListStubImpl(psiClassStubImpl, FirJavaElementFinderKt.packFlags(firClass));
        if (firClass.getClassKind() == ClassKind.ANNOTATION_CLASS) {
            FirAnnotation targetAnnotation = FirAnnotationHelpersKt.getTargetAnnotation(firClass, this.session);
            List<String> listFindTargets = targetAnnotation != null ? findTargets(targetAnnotation) : null;
            if (listFindTargets != null) {
                StringBuilder sb = new StringBuilder();
                sb.append(PreprocessCommandLineArgumentsKt.ARGFILE_ARGUMENT + JvmAnnotationNames.TARGET_ANNOTATION + "({");
                ArrayList arrayList3 = new ArrayList();
                Iterator<T> it3 = listFindTargets.iterator();
                while (it3.hasNext()) {
                    String str = (String) KotlinToJavaAnnotationTargetsKt.getKOTLIN_TO_JAVA_ANNOTATION_TARGETS().get((String) it3.next());
                    if (str != null) {
                        arrayList3.add(str);
                    }
                }
                CollectionsKt.joinTo$default(arrayList3, sb, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: c95
                    public final Object invoke(Object obj3) {
                        return FirJavaElementFinder.buildStub$lambda$2$1((String) obj3);
                    }
                }, 62, (Object) null);
                sb.append("})");
                new PsiAnnotationStubImpl(psiModifierListStubImpl, sb.toString());
            }
        }
        List<FirTypeParameterRef> typeParameters = firClass.getTypeParameters();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj3 : typeParameters) {
            if (obj3 instanceof FirTypeParameter) {
                arrayList4.add(obj3);
            }
        }
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
        Iterator it4 = arrayList4.iterator();
        while (it4.hasNext()) {
            arrayList5.add(new Pair(((FirTypeParameter) it4.next()).getName().asString(), new String[]{"java.lang.Object"}));
        }
        FirJavaElementFinderKt.newTypeParameterList(psiClassStubImpl, arrayList5);
        List<FirTypeRef> superTypeRefs = firClass.getSuperTypeRefs();
        if (!(superTypeRefs instanceof Collection) || !superTypeRefs.isEmpty()) {
            Iterator<T> it5 = superTypeRefs.iterator();
            while (true) {
                if (!it5.hasNext()) {
                    listResolveSupertypesOnAir = firClass.getSuperTypeRefs();
                    break;
                }
                if (!(((FirTypeRef) it5.next()) instanceof FirResolvedTypeRef)) {
                    listResolveSupertypesOnAir = FirJavaElementFinderKt.resolveSupertypesOnAir(firClass, this.session);
                    break;
                }
            }
        } else {
            listResolveSupertypesOnAir = firClass.getSuperTypeRefs();
            break;
        }
        FirJavaElementFinderKt.addSupertypesReferencesLists(psiClassStubImpl, firClass, listResolveSupertypesOnAir, this.session);
        List<FirDeclaration> declarations2 = firClass.getDeclarations();
        ArrayList arrayList6 = new ArrayList();
        for (Object obj4 : declarations2) {
            if (obj4 instanceof FirRegularClass) {
                arrayList6.add(obj4);
            }
        }
        Iterator it6 = arrayList6.iterator();
        while (it6.hasNext()) {
            buildStub((FirRegularClass) it6.next(), psiClassStubImpl);
        }
        return psiClassStubImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence buildStub$lambda$2$1(String str) {
        str.getClass();
        return JvmAnnotationNames.ELEMENT_TYPE_ENUM + '.' + str;
    }

    public static FqName c(FqName fqName) {
        fqName.getClass();
        return FqNamesUtilKt.parentOrNull(fqName);
    }

    private final List<String> findTargets(FirAnnotation firAnnotation) {
        final List listCreateListBuilder = CollectionsKt.createListBuilder();
        AnnotationTargetUtilsKt.forEachAnnotationTarget(firAnnotation, this.session, new Function1() { // from class: b95
            public final Object invoke(Object obj) {
                return FirJavaElementFinder.findTargets$lambda$0$0(listCreateListBuilder, (Name) obj);
            }
        });
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit findTargets$lambda$0$0(List list, Name name) {
        name.getClass();
        String identifier = name.getIdentifier();
        identifier.getClass();
        list.add(identifier);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String jvmName(FirFile firFile) {
        FirAnnotation firAnnotationFindJvmNameAnnotation = JavaUtilsKt.findJvmNameAnnotation(firFile);
        FirExpression firExpressionFindArgumentByName$default = firAnnotationFindJvmNameAnnotation != null ? FirAnnotationUtilsKt.findArgumentByName$default(firAnnotationFindJvmNameAnnotation, StandardNames.NAME, false, 2, null) : null;
        FirLiteralExpression firLiteralExpression = firExpressionFindArgumentByName$default instanceof FirLiteralExpression ? (FirLiteralExpression) firExpressionFindArgumentByName$default : null;
        Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
        String str = value instanceof String ? (String) value : null;
        if (str != null) {
            return str;
        }
        return CapitalizeDecapitalizeKt.capitalizeAsciiOnly(StringsKt.removeSuffix(firFile.getName(), ".kt")) + "Kt";
    }

    public PsiClass findClass(String qualifiedName, GlobalSearchScope scope) {
        FirRegularClass firRegularClass;
        qualifiedName.getClass();
        scope.getClass();
        if (StringsKt.endsWith$default(qualifiedName, ".", false, 2, (Object) null)) {
            return null;
        }
        FqName fqName = new FqName(qualifiedName);
        for (FqName fqName2 : SequencesKt.generateSequence(fqName, new Function1() { // from class: a95
            public final Object invoke(Object obj) {
                return FirJavaElementFinder.c((FqName) obj);
            }
        })) {
            if (fqName2.isRoot()) {
                break;
            }
            ClassId classId = ClassId.Companion.topLevel(fqName2);
            Iterator<T> it = this.firProviders.iterator();
            do {
                if (!it.hasNext()) {
                    firRegularClass = null;
                    break;
                }
                FirClassLikeDeclaration firClassifierByFqName = ((FirProvider) it.next()).getFirClassifierByFqName(classId);
                firRegularClass = firClassifierByFqName instanceof FirRegularClass ? (FirRegularClass) firClassifierByFqName : null;
            } while (firRegularClass == null);
            if (firRegularClass != null) {
                PsiClass psi = buildStub(firRegularClass, FirJavaElementFinderKt.createJavaFileStub(classId.getPackageFqName(), this.psiManager)).getPsi();
                Iterator it2 = FqNamesUtilKt.tail(fqName, fqName2).pathSegments().iterator();
                while (it2.hasNext()) {
                    psi = psi.findInnerClassByName(((Name) it2.next()).getIdentifier(), false);
                    if (psi == null) {
                        return null;
                    }
                }
                return psi;
            }
        }
        ClassId classId2 = ClassId.Companion.topLevel(fqName);
        List list = (List) ((Map) this.fileCache.getValue(classId2.getPackageFqName(), null)).get(classId2.getRelativeClassName().asString());
        FirFile firFile = list != null ? (FirFile) CollectionsKt.singleOrNull(list) : null;
        if (firFile != null) {
            return buildFileAsClassStub(firFile, classId2, FirJavaElementFinderKt.createJavaFileStub(classId2.getPackageFqName(), this.psiManager)).getPsi();
        }
        return null;
    }

    public PsiClass[] findClasses(String qualifiedName, GlobalSearchScope scope) {
        qualifiedName.getClass();
        scope.getClass();
        PsiClass psiClassFindClass = findClass(qualifiedName, scope);
        return psiClassFindClass != null ? new PsiClass[]{psiClassFindClass} : new PsiClass[0];
    }

    public PsiPackage findPackage(String qualifiedName) {
        qualifiedName.getClass();
        FqName fqName = new FqName(qualifiedName);
        List<FirProvider> list = this.firProviders;
        if ((list instanceof Collection) && list.isEmpty()) {
            return null;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (((FirProvider) it.next()).getSymbolProvider().hasPackage(fqName)) {
                return new FirPsiPackage(this.psiManager, qualifiedName);
            }
        }
        return null;
    }

    public PsiClass[] getClasses(PsiPackage psiPackage, GlobalSearchScope scope) {
        psiPackage.getClass();
        scope.getClass();
        List<FirProvider> list = this.firProviders;
        ArrayList arrayList = new ArrayList();
        for (FirProvider firProvider : list) {
            String qualifiedName = psiPackage.getQualifiedName();
            qualifiedName.getClass();
            Set<Name> classNamesInPackage = firProvider.getClassNamesInPackage(new FqName(qualifiedName));
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it = classNamesInPackage.iterator();
            while (it.hasNext()) {
                PsiClass psiClassFindClass = findClass(psiPackage.getQualifiedName() + '.' + ((Name) it.next()).getIdentifier(), scope);
                if (psiClassFindClass != null) {
                    arrayList2.add(psiClassFindClass);
                }
            }
            CollectionsKt.addAll(arrayList, arrayList2);
        }
        return (PsiClass[]) arrayList.toArray(new PsiClass[0]);
    }
}
