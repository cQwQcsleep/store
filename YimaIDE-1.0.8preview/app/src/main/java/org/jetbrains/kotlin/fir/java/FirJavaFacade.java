package org.jetbrains.kotlin.fir.java;

import com.intellij.openapi.vfs.VirtualFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.sequences.Sequence;
import org.jetbrains.kotlin.builtins.jvm.JavaToKotlinClassMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.EffectiveVisibilityUtilsKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.SealedClassInheritorsKt;
import org.jetbrains.kotlin.fir.declarations.builder.FirOuterClassTypeParameterRefBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.java.FirJavaFacade;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaClass;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaClassBuilder;
import org.jetbrains.kotlin.fir.java.enhancement.FirLazyJavaAnnotationList;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.load.java.JavaClassFinder;
import org.jetbrains.kotlin.load.java.JavaClassFinder$Request;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.java.structure.JavaClassifierType;
import org.jetbrains.kotlin.load.java.structure.JavaElementsKt;
import org.jetbrains.kotlin.load.java.structure.JavaPackage;
import org.jetbrains.kotlin.load.java.structure.JavaTypeParameter;
import org.jetbrains.kotlin.load.java.structure.impl.VirtualFileBoundJavaClass;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 (2\u00020\u0001:\u0001(B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\nJ\u000e\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u00112\u0006\u0010\u0010\u001a\u00020\nJ\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0014H&J \u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010#2\u0006\u0010\u001f\u001a\u00020\u0014J2\u0010%\u001a\u00020!2\u0006\u0010\u001f\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010#2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010&\u001a\u00020'H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\b\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R9\u0010\r\u001a-\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u000e\u0012\b\b\u000f\u0012\u0004\b\b(\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011\u0012\u0006\u0012\u0004\u0018\u00010\f0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/FirJavaFacade;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "classFinder", "Lorg/jetbrains/kotlin/load/java/JavaClassFinder;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/load/java/JavaClassFinder;)V", "packageCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/load/java/structure/JavaPackage;", Argument.Delimiters.none, "knownClassNamesInPackage", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "packageFqName", Argument.Delimiters.none, Argument.Delimiters.none, "findClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "knownContent", Argument.Delimiters.none, "hasPackage", Argument.Delimiters.none, "fqName", "hasTopLevelClassOf", "getModuleDataForClass", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "javaClass", "convertJavaClassToFir", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "parentClassSymbol", "createFirJavaClass", "classJavaTypeParameterStack", "Lorg/jetbrains/kotlin/fir/java/MutableJavaTypeParameterStack;", "Companion", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirJavaFacade {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Name VALUE_METHOD_NAME;
    private final JavaClassFinder classFinder;
    private final FirCache knownClassNamesInPackage;
    private final FirCache packageCache;

    static {
        Name nameIdentifier = Name.identifier("value");
        nameIdentifier.getClass();
        VALUE_METHOD_NAME = nameIdentifier;
    }

    public FirJavaFacade(FirSession firSession, final JavaClassFinder javaClassFinder) {
        firSession.getClass();
        javaClassFinder.getClass();
        this.classFinder = javaClassFinder;
        this.packageCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.java.FirJavaFacade$special$$inlined$createCache$1
            public final JavaPackage invoke(FqName fqName, Void r3) {
                fqName.getClass();
                FqName fqName2 = fqName;
                Set<String> setKnownClassNamesInPackage = this.this$0.knownClassNamesInPackage(fqName2);
                return this.this$0.classFinder.findPackage(fqName2, setKnownClassNamesInPackage != null ? setKnownClassNamesInPackage.contains("package-info") : true);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((FqName) obj, (Void) obj2);
            }
        });
        this.knownClassNamesInPackage = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.java.FirJavaFacade$special$$inlined$createCache$2
            public final Set<? extends String> invoke(FqName fqName, Void r2) {
                fqName.getClass();
                return javaClassFinder.knownClassNamesInPackage(fqName);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((FqName) obj, (Void) obj2);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:9:0x00a3  */
    /* JADX WARN: Multi-variable type inference failed */
    private final FirJavaClass createFirJavaClass(final JavaClass javaClass, FirRegularClassSymbol classSymbol, FirRegularClassSymbol parentClassSymbol, ClassId classId, MutableJavaTypeParameterStack classJavaTypeParameterStack) throws KotlinIllegalArgumentExceptionWithAttachments {
        EffectiveVisibility effectiveVisibility;
        VirtualFile virtualFile;
        FirModuleData moduleDataForClass = getModuleDataForClass(javaClass);
        FirSession session = moduleDataForClass.getSession();
        FirJavaClassBuilder firJavaClassBuilder = new FirJavaClassBuilder();
        firJavaClassBuilder.setJavaClass(javaClass);
        firJavaClassBuilder.setContainingClassSymbol(parentClassSymbol);
        firJavaClassBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        firJavaClassBuilder.setAnnotationList(new FirLazyJavaAnnotationList(javaClass, moduleDataForClass));
        firJavaClassBuilder.setSource(FirJavaFacadeKt.toSourceElement$default(javaClass, null, 1, null));
        firJavaClassBuilder.setModuleData(moduleDataForClass);
        firJavaClassBuilder.setSymbol(classSymbol);
        firJavaClassBuilder.setName(javaClass.getName());
        firJavaClassBuilder.setFromSource(javaClass.isFromSource());
        Visibility visibility = javaClass.getVisibility();
        firJavaClassBuilder.setVisibility(visibility);
        firJavaClassBuilder.setClassKind(JavaUtilsKt.getClassKind(javaClass));
        firJavaClassBuilder.setJavaPackage((JavaPackage) this.packageCache.getValue(classSymbol.getClassId().getPackageFqName(), null));
        firJavaClassBuilder.setJavaTypeParameterStack(classJavaTypeParameterStack);
        CollectionsKt.addAll(firJavaClassBuilder.getExistingNestedClassifierNames(), javaClass.getInnerClassNames());
        firJavaClassBuilder.setScopeProvider(JavaScopeProvider.INSTANCE);
        EffectiveVisibility effectiveVisibility$default = EffectiveVisibilityUtilsKt.toEffectiveVisibility$default(visibility, parentClassSymbol != null ? parentClassSymbol.getLookupTag() : null, true, false, 4, (Object) null);
        if (parentClassSymbol != null) {
            E fir = parentClassSymbol.getFir();
            fir.getClass();
            effectiveVisibility = ((FirJavaClass) fir).getOriginalStatus().getEffectiveVisibility();
            if (effectiveVisibility == null) {
                effectiveVisibility = EffectiveVisibility.Public.INSTANCE;
            }
        } else {
            effectiveVisibility = EffectiveVisibility.Public.INSTANCE;
        }
        EffectiveVisibility effectiveVisibilityLowerBound = effectiveVisibility.lowerBound(effectiveVisibility$default, TypeComponentsKt.getTypeContext(session));
        List<JavaTypeParameter> typeParameters = javaClass.getTypeParameters();
        List<FirTypeParameterRef> typeParameters2 = firJavaClassBuilder.getTypeParameters();
        for (JavaTypeParameter javaTypeParameter : typeParameters) {
            FirTypeParameter firTypeParameter = FirJavaFacadeKt.toFirTypeParameter(javaTypeParameter, classSymbol, moduleDataForClass);
            classJavaTypeParameterStack.addParameter(javaTypeParameter, firTypeParameter.getSymbol());
            typeParameters2.add(firTypeParameter);
        }
        boolean zIsStatic = javaClass.isStatic();
        if (!zIsStatic && parentClassSymbol != null) {
            List<FirTypeParameterRef> typeParameters3 = firJavaClassBuilder.getTypeParameters();
            E fir2 = parentClassSymbol.getFir();
            fir2.getClass();
            List<FirTypeParameterRef> nonEnhancedTypeParameters = ((FirJavaClass) fir2).getNonEnhancedTypeParameters();
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(nonEnhancedTypeParameters, 10));
            for (FirTypeParameterRef firTypeParameterRef : nonEnhancedTypeParameters) {
                FirOuterClassTypeParameterRefBuilder firOuterClassTypeParameterRefBuilder = new FirOuterClassTypeParameterRefBuilder();
                firOuterClassTypeParameterRefBuilder.setSymbol(firTypeParameterRef.getSymbol());
                arrayList.add(firOuterClassTypeParameterRefBuilder.build());
            }
            CollectionsKt.addAll(typeParameters3, arrayList);
        }
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(visibility, JavaUtilsKt.getModality(javaClass), effectiveVisibilityLowerBound);
        firResolvedDeclarationStatusImpl.setInner(classId.isNestedClass() && !zIsStatic);
        firResolvedDeclarationStatusImpl.setFun(firJavaClassBuilder.getClassKind() == ClassKind.INTERFACE);
        firJavaClassBuilder.setStatus(firResolvedDeclarationStatusImpl);
        firJavaClassBuilder.setDeclarationList(new FirLazyJavaDeclarationList(javaClass, classSymbol, firJavaClassBuilder.getJavaPackage()));
        FirJavaClass firJavaClassBuild = firJavaClassBuilder.mo289build();
        if (firJavaClassBuild.getOriginalStatus().getModality() == Modality.SEALED) {
            SealedClassInheritorsKt.setSealedClassInheritors(firJavaClassBuild, (Function0<? extends List<ClassId>>) new Function0() { // from class: f95
                public final Object invoke() {
                    return FirJavaFacade.createFirJavaClass$lambda$1$0(javaClass);
                }
            });
            if (firJavaClassBuild.getClassKind() == ClassKind.CLASS && !javaClass.isAbstract()) {
                ClassMembersKt.setJavaNonAbstractSealed(firJavaClassBuild, Boolean.TRUE);
            }
        }
        if (javaClass.isRecord()) {
            ClassMembersKt.setJavaRecord(firJavaClassBuild, Boolean.TRUE);
        }
        if ((javaClass instanceof VirtualFileBoundJavaClass) && (virtualFile = ((VirtualFileBoundJavaClass) javaClass).getVirtualFile()) != null) {
            DeclarationAttributesKt.setSourceElement(firJavaClassBuild, new VirtualFileBasedSourceElement(virtualFile));
        }
        return firJavaClassBuild;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List createFirJavaClass$lambda$1$0(JavaClass javaClass) {
        Sequence permittedTypes = javaClass.getPermittedTypes();
        ArrayList arrayList = new ArrayList();
        Iterator it = permittedTypes.iterator();
        while (it.hasNext()) {
            JavaClass classifier = ((JavaClassifierType) it.next()).getClassifier();
            ClassId classId = null;
            JavaClass javaClass2 = classifier instanceof JavaClass ? classifier : null;
            if (javaClass2 != null) {
                JavaToKotlinClassMap javaToKotlinClassMap = JavaToKotlinClassMap.INSTANCE;
                FqName fqName = javaClass2.getFqName();
                fqName.getClass();
                ClassId classIdMapJavaToKotlin = javaToKotlinClassMap.mapJavaToKotlin(fqName);
                classId = classIdMapJavaToKotlin == null ? JavaElementsKt.getClassId(javaClass2) : classIdMapJavaToKotlin;
            }
            if (classId != null) {
                arrayList.add(classId);
            }
        }
        return arrayList;
    }

    public static /* synthetic */ JavaClass findClass$default(FirJavaFacade firJavaFacade, ClassId classId, byte[] bArr, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: findClass");
            return null;
        }
        if ((i & 2) != 0) {
            bArr = null;
        }
        return firJavaFacade.findClass(classId, bArr);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public final FirJavaClass convertJavaClassToFir(FirRegularClassSymbol classSymbol, FirRegularClassSymbol parentClassSymbol, JavaClass javaClass) throws KotlinIllegalArgumentExceptionWithAttachments {
        classSymbol.getClass();
        javaClass.getClass();
        ClassId classId = classSymbol.getClassId();
        MutableJavaTypeParameterStack mutableJavaTypeParameterStack = new MutableJavaTypeParameterStack();
        if (parentClassSymbol != null) {
            E fir = parentClassSymbol.getFir();
            fir.getClass();
            mutableJavaTypeParameterStack.addStack(((FirJavaClass) fir).getClassJavaTypeParameterStack());
        }
        return createFirJavaClass(javaClass, classSymbol, parentClassSymbol, classId, mutableJavaTypeParameterStack);
    }

    public final JavaClass findClass(ClassId classId, byte[] knownContent) {
        classId.getClass();
        JavaClass javaClassFindClass = this.classFinder.findClass(new JavaClassFinder$Request(classId, knownContent, null, 4, null));
        if (javaClassFindClass == null) {
            return null;
        }
        if (javaClassFindClass.isFromSource() || !JavaUtilsKt.hasMetadataAnnotation(javaClassFindClass)) {
            return javaClassFindClass;
        }
        return null;
    }

    public abstract FirModuleData getModuleDataForClass(JavaClass javaClass);

    public final boolean hasPackage(FqName fqName) {
        fqName.getClass();
        return this.packageCache.getValue(fqName, null) != null;
    }

    public final boolean hasTopLevelClassOf(ClassId classId) {
        classId.getClass();
        Set<String> setKnownClassNamesInPackage = knownClassNamesInPackage(classId.getPackageFqName());
        if (setKnownClassNamesInPackage == null) {
            return true;
        }
        return setKnownClassNamesInPackage.contains(FirJavaFacadeKt.topLevelName(classId.getRelativeClassName()));
    }

    public final Set<String> knownClassNamesInPackage(FqName packageFqName) {
        packageFqName.getClass();
        if (this.classFinder.canComputeKnownClassNamesInPackage()) {
            return (Set) this.knownClassNamesInPackage.getValue(packageFqName, null);
        }
        return null;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/FirJavaFacade$Companion;", Argument.Delimiters.none, "<init>", "()V", "VALUE_METHOD_NAME", "Lorg/jetbrains/kotlin/name/Name;", "getVALUE_METHOD_NAME", "()Lorg/jetbrains/kotlin/name/Name;", "PACKAGE_INFO_CLASS_NAME", Argument.Delimiters.none, "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Name getVALUE_METHOD_NAME() {
            return FirJavaFacade.VALUE_METHOD_NAME;
        }

        private Companion() {
        }
    }
}
