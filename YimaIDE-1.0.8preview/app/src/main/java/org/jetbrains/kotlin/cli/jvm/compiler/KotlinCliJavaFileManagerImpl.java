package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.core.CoreJavaFileManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiClassOwner;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiJavaModule;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiModifierList;
import com.intellij.psi.PsiPackage;
import com.intellij.psi.PsiPackageStatement;
import com.intellij.psi.impl.file.PsiPackageImpl;
import com.intellij.psi.search.GlobalSearchScope;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.compiler.KotlinCliJavaFileManagerImpl;
import org.jetbrains.kotlin.cli.jvm.index.JavaRoot;
import org.jetbrains.kotlin.cli.jvm.index.JvmDependenciesIndex;
import org.jetbrains.kotlin.cli.jvm.index.SingleJavaFileRootsIndex;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.JavaClassFinder$Request;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.java.structure.impl.JavaClassImpl;
import org.jetbrains.kotlin.load.java.structure.impl.classFiles.BinaryClassSignatureParser;
import org.jetbrains.kotlin.load.java.structure.impl.classFiles.BinaryJavaClass;
import org.jetbrains.kotlin.load.java.structure.impl.classFiles.ClassifierResolutionContext;
import org.jetbrains.kotlin.load.java.structure.impl.classFiles.OtherKt;
import org.jetbrains.kotlin.load.java.structure.impl.source.JavaElementSourceFactory;
import org.jetbrains.kotlin.load.java.structure.impl.source.SingleFileRootPsiPackage;
import org.jetbrains.kotlin.load.kotlin.PackagePartProvider;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.jvm.KotlinCliJavaFileManager;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.util.PerformanceManagerKt;
import org.jetbrains.kotlin.util.PhaseSideType;
import org.jetbrains.kotlin.utils.CollectionsKt;
import org.jetbrains.kotlin.utils.SmartList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 G2\u00020\u00012\u00020\u0002:\u0001GB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J6\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u001a\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0016H\u0002J\u001a\u0010!\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0016H\u0002J\u0018\u0010&\u001a\u0004\u0018\u00010#2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0016J\u001a\u0010&\u001a\u0004\u0018\u00010#2\u0006\u0010'\u001a\u00020(2\u0006\u0010 \u001a\u00020\u0016H\u0016J\u0010\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u001dH\u0002J\u001a\u0010&\u001a\u0004\u0018\u00010\u001d2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0016H\u0016J%\u0010/\u001a\u00020\u001b2\u0006\u00100\u001a\u00020-2\u0012\u00101\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u001b02H\u0082\bJ#\u00103\u001a\b\u0012\u0004\u0012\u00020\u001d042\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0016H\u0016¢\u0006\u0002\u00105J\u0012\u00106\u001a\u0004\u0018\u0001072\u0006\u00108\u001a\u00020-H\u0016J\"\u00109\u001a\u0004\u0018\u00010\u00142\u0006\u0010:\u001a\u00020\u00142\u0006\u0010;\u001a\u00020-2\u0006\u0010<\u001a\u00020=H\u0002J\u0016\u0010>\u001a\u0004\u0018\u00010\u001d*\u00020\u00142\u0006\u0010;\u001a\u00020-H\u0002J\u0016\u0010?\u001a\b\u0012\u0004\u0012\u00020-0@2\u0006\u0010A\u001a\u00020\u0012H\u0016J\u001e\u0010B\u001a\b\u0012\u0004\u0012\u00020D0C2\u0006\u0010E\u001a\u00020-2\u0006\u0010.\u001a\u00020\u0016H\u0016J\u000e\u0010F\u001a\b\u0012\u0004\u0012\u00020-0CH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082.¢\u0006\u0002\n\u0000R\"\u0010\u0010\u001a\u0016\u0012\u0004\u0012\u00020\u0012\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00130\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0015\u001a\u00070\u0016¢\u0006\u0002\b\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0006\u0012\u0004\u0018\u00010#0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006H"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCliJavaFileManagerImpl;", "Lcom/intellij/core/CoreJavaFileManager;", "Lorg/jetbrains/kotlin/resolve/jvm/KotlinCliJavaFileManager;", "myPsiManager", "Lcom/intellij/psi/PsiManager;", "<init>", "(Lcom/intellij/psi/PsiManager;)V", "perfManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "index", "Lorg/jetbrains/kotlin/cli/jvm/index/JvmDependenciesIndex;", "singleJavaFileRootsIndex", "Lorg/jetbrains/kotlin/cli/jvm/index/SingleJavaFileRootsIndex;", "packagePartProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/kotlin/PackagePartProvider;", "topLevelClassesCache", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/utils/SmartList;", "Lcom/intellij/openapi/vfs/VirtualFile;", "allScope", "Lcom/intellij/psi/search/GlobalSearchScope;", "Lorg/jetbrains/annotations/NotNull;", "usePsiClassFilesReading", Argument.Delimiters.none, "initialize", Argument.Delimiters.none, "findPsiClass", "Lcom/intellij/psi/PsiClass;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "searchScope", "findVirtualFileForTopLevelClass", "binaryCache", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "signatureParsingComponent", "Lorg/jetbrains/kotlin/load/java/structure/impl/classFiles/BinaryClassSignatureParser;", "findClass", "request", "Lorg/jetbrains/kotlin/load/java/JavaClassFinder$Request;", "createJavaClassByPsiClass", "Lorg/jetbrains/kotlin/load/java/structure/impl/JavaClassImpl;", "psiClass", "qName", Argument.Delimiters.none, "scope", "forEachClassId", "fqName", "block", "Lkotlin/Function1;", "findClasses", Argument.Delimiters.none, "(Ljava/lang/String;Lcom/intellij/psi/search/GlobalSearchScope;)[Lcom/intellij/psi/PsiClass;", "findPackage", "Lcom/intellij/psi/PsiPackage;", "packageName", "findVirtualFileGivenPackage", "packageDir", "classNameWithInnerClasses", "rootType", "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot$RootType;", "findPsiClassInVirtualFile", "knownClassNamesInPackage", Argument.Delimiters.none, "packageFqName", "findModules", Argument.Delimiters.none, "Lcom/intellij/psi/PsiJavaModule;", "moduleName", "getNonTrivialPackagePrefixes", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinCliJavaFileManagerImpl extends CoreJavaFileManager implements KotlinCliJavaFileManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final Logger LOG;
    private final GlobalSearchScope allScope;
    private final Map<ClassId, JavaClass> binaryCache;
    private JvmDependenciesIndex index;
    private final PsiManager myPsiManager;
    private List<? extends PackagePartProvider> packagePartProviders;
    private PerformanceManager perfManager;
    private final BinaryClassSignatureParser signatureParsingComponent;
    private SingleJavaFileRootsIndex singleJavaFileRootsIndex;
    private final Map<FqName, SmartList<VirtualFile>> topLevelClassesCache;
    private boolean usePsiClassFilesReading;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JavaRoot.RootType.values().length];
            try {
                iArr[JavaRoot.RootType.BINARY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[JavaRoot.RootType.BINARY_SIG.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[JavaRoot.RootType.SOURCE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static {
        Logger logger = Logger.getInstance(KotlinCliJavaFileManagerImpl.class);
        logger.getClass();
        LOG = logger;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinCliJavaFileManagerImpl(PsiManager psiManager) {
        super(psiManager);
        psiManager.getClass();
        this.myPsiManager = psiManager;
        this.topLevelClassesCache = new Object2ObjectOpenHashMap();
        GlobalSearchScope globalSearchScopeAllScope = GlobalSearchScope.allScope(psiManager.getProject());
        globalSearchScopeAllScope.getClass();
        this.allScope = globalSearchScopeAllScope;
        this.binaryCache = new Object2ObjectOpenHashMap();
        this.signatureParsingComponent = new BinaryClassSignatureParser();
    }

    public static boolean a(Ref.BooleanRef booleanRef, VirtualFile virtualFile, JavaRoot.RootType rootType) {
        virtualFile.getClass();
        rootType.getClass();
        booleanRef.element = true;
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Code duplicated, block: B:16:0x0036  */
    public static PsiClass[] b(final KotlinCliJavaFileManagerImpl kotlinCliJavaFileManagerImpl, String str, final GlobalSearchScope globalSearchScope) throws UninitializedPropertyAccessException {
        PsiClass psiClassFindPsiClassInVirtualFile;
        JvmDependenciesIndex jvmDependenciesIndex;
        final ArrayList arrayList = new ArrayList(1);
        ClassId safeTopLevelClassId = KotlinCliJavaFileManagerImplKt.toSafeTopLevelClassId(str);
        if (safeTopLevelClassId != null) {
            while (true) {
                final String strAsString = safeTopLevelClassId.getRelativeClassName().asString();
                SingleJavaFileRootsIndex singleJavaFileRootsIndex = kotlinCliJavaFileManagerImpl.singleJavaFileRootsIndex;
                if (singleJavaFileRootsIndex == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("singleJavaFileRootsIndex");
                    singleJavaFileRootsIndex = null;
                }
                VirtualFile virtualFileFindJavaSourceClass = singleJavaFileRootsIndex.findJavaSourceClass(safeTopLevelClassId);
                if (virtualFileFindJavaSourceClass == null) {
                    psiClassFindPsiClassInVirtualFile = null;
                } else {
                    if (!globalSearchScope.contains(virtualFileFindJavaSourceClass)) {
                        virtualFileFindJavaSourceClass = null;
                    }
                    if (virtualFileFindJavaSourceClass != null) {
                        psiClassFindPsiClassInVirtualFile = kotlinCliJavaFileManagerImpl.findPsiClassInVirtualFile(virtualFileFindJavaSourceClass, strAsString);
                    } else {
                        psiClassFindPsiClassInVirtualFile = null;
                    }
                }
                CollectionsKt.addIfNotNull(arrayList, psiClassFindPsiClassInVirtualFile);
                JvmDependenciesIndex jvmDependenciesIndex2 = kotlinCliJavaFileManagerImpl.index;
                if (jvmDependenciesIndex2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("index");
                    jvmDependenciesIndex = null;
                } else {
                    jvmDependenciesIndex = jvmDependenciesIndex2;
                }
                JvmDependenciesIndex.traverseDirectoriesInPackage$default(jvmDependenciesIndex, safeTopLevelClassId.getPackageFqName(), null, new Function2() { // from class: ta8
                    public final Object invoke(Object obj, Object obj2) {
                        return Boolean.valueOf(KotlinCliJavaFileManagerImpl.findClasses$lambda$0$0$1(this.b, strAsString, arrayList, globalSearchScope, (VirtualFile) obj, (JavaRoot.RootType) obj2));
                    }
                }, 2, null);
                if (!arrayList.isEmpty()) {
                    return (PsiClass[]) arrayList.toArray(new PsiClass[0]);
                }
                FqName packageFqName = safeTopLevelClassId.getPackageFqName();
                if (!packageFqName.isRoot()) {
                    safeTopLevelClassId = new ClassId(packageFqName.parent(), new FqName(packageFqName.shortName().asString() + '.' + safeTopLevelClassId.getRelativeClassName().asString()), false);
                }
            }
        }
        PsiClass[] psiClassArr = PsiClass.EMPTY_ARRAY;
        psiClassArr.getClass();
        return psiClassArr;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public static PsiClass c(KotlinCliJavaFileManagerImpl kotlinCliJavaFileManagerImpl, ClassId classId, GlobalSearchScope globalSearchScope) throws UninitializedPropertyAccessException {
        VirtualFile virtualFileFindVirtualFileForTopLevelClass = kotlinCliJavaFileManagerImpl.findVirtualFileForTopLevelClass(classId, globalSearchScope);
        if (virtualFileFindVirtualFileForTopLevelClass != null) {
            return kotlinCliJavaFileManagerImpl.findPsiClassInVirtualFile(virtualFileFindVirtualFileForTopLevelClass, classId.getRelativeClassName().asString());
        }
        return null;
    }

    private final JavaClassImpl createJavaClassByPsiClass(PsiClass psiClass) {
        Project project = this.myPsiManager.getProject();
        project.getClass();
        return new JavaClassImpl(JavaElementSourceFactory.Companion.getInstance(project).createPsiSource((PsiElement) psiClass));
    }

    public static boolean e(ObjectOpenHashSet objectOpenHashSet, VirtualFile virtualFile, JavaRoot.RootType rootType) {
        virtualFile.getClass();
        rootType.getClass();
        VirtualFile[] children = virtualFile.getChildren();
        children.getClass();
        for (VirtualFile virtualFile2 : children) {
            if (Intrinsics.areEqual(virtualFile2.getExtension(), "class") || Intrinsics.areEqual(virtualFile2.getExtension(), "java") || Intrinsics.areEqual(virtualFile2.getExtension(), "sig")) {
                objectOpenHashSet.add(virtualFile2.getNameWithoutExtension());
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final JavaClass findClass$lambda$0$1(KotlinCliJavaFileManagerImpl kotlinCliJavaFileManagerImpl, ClassId classId) {
        classId.getClass();
        return kotlinCliJavaFileManagerImpl.findClass(classId, kotlinCliJavaFileManagerImpl.allScope);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean findClasses$lambda$0$0$1(KotlinCliJavaFileManagerImpl kotlinCliJavaFileManagerImpl, String str, ArrayList arrayList, GlobalSearchScope globalSearchScope, VirtualFile virtualFile, JavaRoot.RootType rootType) {
        virtualFile.getClass();
        rootType.getClass();
        VirtualFile virtualFileFindVirtualFileGivenPackage = kotlinCliJavaFileManagerImpl.findVirtualFileGivenPackage(virtualFile, str, rootType);
        PsiClass psiClassFindPsiClassInVirtualFile = null;
        if (virtualFileFindVirtualFileGivenPackage != null) {
            if (!globalSearchScope.contains(virtualFileFindVirtualFileGivenPackage)) {
                virtualFileFindVirtualFileGivenPackage = null;
            }
            if (virtualFileFindVirtualFileGivenPackage != null) {
                psiClassFindPsiClassInVirtualFile = kotlinCliJavaFileManagerImpl.findPsiClassInVirtualFile(virtualFileFindVirtualFileGivenPackage, str);
            }
        }
        if (psiClassFindPsiClassInVirtualFile == null) {
            return true;
        }
        arrayList.add(psiClassFindPsiClassInVirtualFile);
        return true;
    }

    private final PsiClass findPsiClass(final ClassId classId, final GlobalSearchScope searchScope) {
        return (PsiClass) PerformanceManagerKt.tryMeasureSideTime(this.perfManager, PhaseSideType.FindJavaClass, new Function0() { // from class: wa8
            public final Object invoke() {
                return KotlinCliJavaFileManagerImpl.c(this.b, classId, searchScope);
            }
        });
    }

    private final PsiClass findPsiClassInVirtualFile(VirtualFile virtualFile, String str) {
        PsiClassOwner psiClassOwnerFindFile = this.myPsiManager.findFile(virtualFile);
        PsiClassOwner psiClassOwner = psiClassOwnerFindFile instanceof PsiClassOwner ? psiClassOwnerFindFile : null;
        if (psiClassOwner == null) {
            return null;
        }
        return Companion.access$findClassInPsiFile(Companion, str, psiClassOwner);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final VirtualFile findVirtualFileForTopLevelClass(ClassId classId, GlobalSearchScope searchScope) throws UninitializedPropertyAccessException {
        final String strAsString = classId.getRelativeClassName().asString();
        FqName fqNameChild = classId.getPackageFqName().child((Name) kotlin.collections.CollectionsKt.first(classId.getRelativeClassName().pathSegments()));
        Map<FqName, SmartList<VirtualFile>> map = this.topLevelClassesCache;
        SmartList<VirtualFile> smartList = map.get(fqNameChild);
        Object obj = null;
        if (smartList == null) {
            ClassId classId2 = ClassId.Companion.topLevel(fqNameChild);
            SingleJavaFileRootsIndex singleJavaFileRootsIndex = this.singleJavaFileRootsIndex;
            if (singleJavaFileRootsIndex == null) {
                Intrinsics.throwUninitializedPropertyAccessException("singleJavaFileRootsIndex");
                singleJavaFileRootsIndex = null;
            }
            VirtualFile virtualFileFindJavaSourceClass = singleJavaFileRootsIndex.findJavaSourceClass(classId2);
            if (virtualFileFindJavaSourceClass != null) {
                smartList = new SmartList<>(virtualFileFindJavaSourceClass);
            } else {
                JvmDependenciesIndex jvmDependenciesIndex = this.index;
                if (jvmDependenciesIndex == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("index");
                    jvmDependenciesIndex = null;
                }
                smartList = new SmartList<>(JvmDependenciesIndex.findClasses$default(jvmDependenciesIndex, classId2, null, new Function2() { // from class: ua8
                    public final Object invoke(Object obj2, Object obj3) {
                        return KotlinCliJavaFileManagerImpl.findVirtualFileForTopLevelClass$lambda$0$1(this.b, strAsString, (VirtualFile) obj2, (JavaRoot.RootType) obj3);
                    }
                }, 2, null));
                if (smartList.isEmpty()) {
                    smartList = null;
                }
            }
            map.put(fqNameChild, smartList);
        }
        SmartList<VirtualFile> smartList2 = smartList;
        if (smartList2 == null) {
            return null;
        }
        for (Object obj2 : smartList2) {
            if (searchScope.contains((VirtualFile) obj2)) {
                obj = obj2;
                break;
            }
        }
        return (VirtualFile) obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final VirtualFile findVirtualFileForTopLevelClass$lambda$0$1(KotlinCliJavaFileManagerImpl kotlinCliJavaFileManagerImpl, String str, VirtualFile virtualFile, JavaRoot.RootType rootType) {
        virtualFile.getClass();
        rootType.getClass();
        return kotlinCliJavaFileManagerImpl.findVirtualFileGivenPackage(virtualFile, str, rootType);
    }

    private final VirtualFile findVirtualFileGivenPackage(VirtualFile packageDir, String classNameWithInnerClasses, JavaRoot.RootType rootType) {
        VirtualFile virtualFileFindChild;
        String strSubstringBefore$default = StringsKt.substringBefore$default(classNameWithInnerClasses, '.', (String) null, 2, (Object) null);
        int i = WhenMappings.$EnumSwitchMapping$0[rootType.ordinal()];
        if (i == 1) {
            virtualFileFindChild = packageDir.findChild(strSubstringBefore$default + ".class");
        } else if (i == 2) {
            virtualFileFindChild = packageDir.findChild(strSubstringBefore$default + ".sig");
        } else {
            if (i != 3) {
                bu8.a();
                return null;
            }
            virtualFileFindChild = packageDir.findChild(strSubstringBefore$default + ".java");
        }
        if (virtualFileFindChild == null) {
            return null;
        }
        if (virtualFileFindChild.isValid()) {
            return virtualFileFindChild;
        }
        LOG.error("Invalid child of valid parent: " + virtualFileFindChild.getPath() + "; " + packageDir.isValid() + " path=" + packageDir.getPath());
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public JavaClass findClass(JavaClassFinder$Request request, GlobalSearchScope searchScope) throws UninitializedPropertyAccessException {
        request.getClass();
        searchScope.getClass();
        ClassId classId = request.getClassId();
        byte[] previouslyFoundClassFileContent = request.getPreviouslyFoundClassFileContent();
        JavaClass outerClass = request.getOuterClass();
        VirtualFile virtualFileFindVirtualFileForTopLevelClass = findVirtualFileForTopLevelClass(classId, searchScope);
        JavaClass binaryJavaClass = null;
        if (virtualFileFindVirtualFileForTopLevelClass == null) {
            return null;
        }
        if (this.usePsiClassFilesReading || !(Intrinsics.areEqual(virtualFileFindVirtualFileForTopLevelClass.getExtension(), "class") || Intrinsics.areEqual(virtualFileFindVirtualFileForTopLevelClass.getExtension(), "sig"))) {
            PsiClass psiClassFindPsiClassInVirtualFile = findPsiClassInVirtualFile(virtualFileFindVirtualFileForTopLevelClass, classId.getRelativeClassName().asString());
            if (psiClassFindPsiClassInVirtualFile != null) {
                return createJavaClassByPsiClass(psiClassFindPsiClassInVirtualFile);
            }
            return null;
        }
        Map<ClassId, JavaClass> map = this.binaryCache;
        JavaClass javaClass = map.get(classId);
        if (javaClass == null) {
            ClassId outerClassId = classId.getOuterClassId();
            if (outerClassId != null) {
                if (outerClass == null) {
                    outerClass = findClass(outerClassId, searchScope);
                }
                if (outerClass instanceof BinaryJavaClass) {
                    binaryJavaClass = ((BinaryJavaClass) outerClass).findInnerClass(classId.getShortClassName(), previouslyFoundClassFileContent);
                } else if (outerClass != null) {
                    binaryJavaClass = outerClass.findInnerClass(classId.getShortClassName());
                }
            } else {
                if (previouslyFoundClassFileContent == null) {
                    previouslyFoundClassFileContent = virtualFileFindVirtualFileForTopLevelClass.contentsToByteArray();
                    previouslyFoundClassFileContent.getClass();
                }
                byte[] bArr = previouslyFoundClassFileContent;
                String nameWithoutExtension = virtualFileFindVirtualFileForTopLevelClass.getNameWithoutExtension();
                nameWithoutExtension.getClass();
                if (!StringsKt.contains$default(nameWithoutExtension, InlineCodegenUtilsKt.CAPTURED_FIELD_PREFIX, false, 2, (Object) null) || !OtherKt.isNotTopLevelClass(bArr)) {
                    binaryJavaClass = new BinaryJavaClass(virtualFileFindVirtualFileForTopLevelClass, classId.asSingleFqName(), new ClassifierResolutionContext(new Function1() { // from class: ya8
                        public final Object invoke(Object obj) {
                            return KotlinCliJavaFileManagerImpl.findClass$lambda$0$1(this.b, (ClassId) obj);
                        }
                    }), this.signatureParsingComponent, 0, (JavaClass) null, bArr, 16, (DefaultConstructorMarker) null);
                }
            }
            map.put(classId, binaryJavaClass);
            javaClass = binaryJavaClass;
        }
        return javaClass;
    }

    public PsiClass[] findClasses(final String qName, final GlobalSearchScope scope) {
        qName.getClass();
        scope.getClass();
        return (PsiClass[]) PerformanceManagerKt.tryMeasureSideTime(this.perfManager, PhaseSideType.FindJavaClass, new Function0() { // from class: xa8
            public final Object invoke() {
                return KotlinCliJavaFileManagerImpl.b(this.b, qName, scope);
            }
        });
    }

    public Collection<PsiJavaModule> findModules(String moduleName, GlobalSearchScope scope) {
        moduleName.getClass();
        scope.getClass();
        return SetsKt.emptySet();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public PsiPackage findPackage(String packageName) throws UninitializedPropertyAccessException {
        PsiPackageStatement packageStatement;
        packageName.getClass();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        FqName safeFqName = KotlinCliJavaFileManagerImplKt.toSafeFqName(packageName);
        PsiModifierList annotationList = null;
        if (safeFqName == null) {
            return null;
        }
        JvmDependenciesIndex jvmDependenciesIndex = this.index;
        if (jvmDependenciesIndex == null) {
            Intrinsics.throwUninitializedPropertyAccessException("index");
            jvmDependenciesIndex = null;
        }
        JvmDependenciesIndex.traverseDirectoriesInPackage$default(jvmDependenciesIndex, safeFqName, null, new Function2() { // from class: za8
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(KotlinCliJavaFileManagerImpl.a(booleanRef, (VirtualFile) obj, (JavaRoot.RootType) obj2));
            }
        }, 2, null);
        if (!booleanRef.element) {
            List<? extends PackagePartProvider> list = this.packagePartProviders;
            if (list == null) {
                Intrinsics.throwUninitializedPropertyAccessException("packagePartProviders");
                list = null;
            }
            List<? extends PackagePartProvider> list2 = list;
            boolean z = false;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    if (!((PackagePartProvider) it.next()).findPackageParts(packageName).isEmpty()) {
                        z = true;
                        break;
                    }
                }
            }
            booleanRef.element = z;
        }
        if (!booleanRef.element) {
            SingleJavaFileRootsIndex singleJavaFileRootsIndex = this.singleJavaFileRootsIndex;
            if (singleJavaFileRootsIndex == null) {
                Intrinsics.throwUninitializedPropertyAccessException("singleJavaFileRootsIndex");
                singleJavaFileRootsIndex = null;
            }
            if (singleJavaFileRootsIndex.hasPackage(safeFqName)) {
                ClassId classId = new ClassId(safeFqName, SingleJavaFileRootsIndex.INSTANCE.getPACKAGE_INFO_CLASS_NAME$org_jetbrains_kotlin_cli_base());
                SingleJavaFileRootsIndex singleJavaFileRootsIndex2 = this.singleJavaFileRootsIndex;
                if (singleJavaFileRootsIndex2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("singleJavaFileRootsIndex");
                    singleJavaFileRootsIndex2 = null;
                }
                VirtualFile virtualFileFindJavaSourceClass = singleJavaFileRootsIndex2.findJavaSourceClass(classId);
                PsiFile psiFileFindFile = virtualFileFindJavaSourceClass != null ? this.myPsiManager.findFile(virtualFileFindJavaSourceClass) : null;
                PsiJavaFile psiJavaFile = psiFileFindFile instanceof PsiJavaFile ? (PsiJavaFile) psiFileFindFile : null;
                if (psiJavaFile != null && (packageStatement = psiJavaFile.getPackageStatement()) != null) {
                    annotationList = packageStatement.getAnnotationList();
                }
                return new SingleFileRootPsiPackage(this.myPsiManager, packageName, annotationList);
            }
        }
        if (booleanRef.element) {
            return new PsiPackageImpl(packageName, this.myPsiManager) { // from class: org.jetbrains.kotlin.cli.jvm.compiler.KotlinCliJavaFileManagerImpl.findPackage.3
                public boolean isValid() {
                    return true;
                }
            };
        }
        return null;
    }

    public Collection<String> getNonTrivialPackagePrefixes() {
        return kotlin.collections.CollectionsKt.emptyList();
    }

    public final void initialize(JvmDependenciesIndex index, List<? extends PackagePartProvider> packagePartProviders, SingleJavaFileRootsIndex singleJavaFileRootsIndex, boolean usePsiClassFilesReading, PerformanceManager perfManager) {
        index.getClass();
        packagePartProviders.getClass();
        singleJavaFileRootsIndex.getClass();
        this.index = index;
        this.packagePartProviders = packagePartProviders;
        this.singleJavaFileRootsIndex = singleJavaFileRootsIndex;
        this.usePsiClassFilesReading = usePsiClassFilesReading;
        this.perfManager = perfManager;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public Set<String> knownClassNamesInPackage(FqName packageFqName) throws UninitializedPropertyAccessException {
        JvmDependenciesIndex jvmDependenciesIndex;
        packageFqName.getClass();
        final ObjectOpenHashSet objectOpenHashSet = new ObjectOpenHashSet();
        JvmDependenciesIndex jvmDependenciesIndex2 = this.index;
        SingleJavaFileRootsIndex singleJavaFileRootsIndex = null;
        if (jvmDependenciesIndex2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("index");
            jvmDependenciesIndex = null;
        } else {
            jvmDependenciesIndex = jvmDependenciesIndex2;
        }
        JvmDependenciesIndex.traverseDirectoriesInPackage$default(jvmDependenciesIndex, packageFqName, null, new Function2() { // from class: va8
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(KotlinCliJavaFileManagerImpl.e(objectOpenHashSet, (VirtualFile) obj, (JavaRoot.RootType) obj2));
            }
        }, 2, null);
        SingleJavaFileRootsIndex singleJavaFileRootsIndex2 = this.singleJavaFileRootsIndex;
        if (singleJavaFileRootsIndex2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("singleJavaFileRootsIndex");
        } else {
            singleJavaFileRootsIndex = singleJavaFileRootsIndex2;
        }
        for (ClassId classId : singleJavaFileRootsIndex.findJavaSourceClasses(packageFqName)) {
            classId.isNestedClass();
            objectOpenHashSet.add(classId.getShortClassName().asString());
        }
        return objectOpenHashSet;
    }

    public final JavaClass findClass(ClassId classId, GlobalSearchScope searchScope) {
        classId.getClass();
        searchScope.getClass();
        return findClass(new JavaClassFinder$Request(classId, null, null, 6, null), searchScope);
    }

    public PsiClass findClass(String qName, GlobalSearchScope scope) {
        qName.getClass();
        scope.getClass();
        ClassId safeTopLevelClassId = KotlinCliJavaFileManagerImplKt.toSafeTopLevelClassId(qName);
        if (safeTopLevelClassId == null) {
            return null;
        }
        while (true) {
            PsiClass psiClassFindPsiClass = findPsiClass(safeTopLevelClassId, scope);
            if (psiClassFindPsiClass != null) {
                return psiClassFindPsiClass;
            }
            FqName packageFqName = safeTopLevelClassId.getPackageFqName();
            if (packageFqName.isRoot()) {
                return null;
            }
            safeTopLevelClassId = new ClassId(packageFqName.parent(), new FqName(packageFqName.shortName().asString() + '.' + safeTopLevelClassId.getRelativeClassName().asString()), false);
        }
    }
}
