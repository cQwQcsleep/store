package org.jetbrains.kotlin.cli.jvm.modules;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.StandardFileSystems;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.psi.search.GlobalSearchScope;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.analyzer.CompilationErrorException;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.modules.CliJavaModuleFinder;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.resolve.jvm.KotlinCliJavaFileManager;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModule;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleFinder;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u00002\u00020\u0001B3\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\u000e\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u0016J\u0012\u0010=\u001a\u0004\u0018\u00010\u00162\u0006\u0010>\u001a\u00020\u0015H\u0016J\u001c\u0010?\u001a\u0004\u0018\u00010:2\u0006\u0010@\u001a\u00020\u00102\b\b\u0002\u0010A\u001a\u00020-H\u0002J\u0016\u0010B\u001a\b\u0012\u0004\u0012\u00020D0C2\u0006\u0010E\u001a\u00020FH\u0002J \u0010B\u001a\u00020D2\u0006\u0010G\u001a\u00020-2\u0006\u0010H\u001a\u00020-2\u0006\u0010E\u001a\u00020FH\u0002J\u0010\u0010I\u001a\u00020\u00152\u0006\u0010J\u001a\u00020\u000bH\u0002J\u0018\u0010K\u001a\u00020-2\u0006\u0010L\u001a\u00020\u00152\u0006\u0010J\u001a\u00020\u000bH\u0002J\u0014\u0010U\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00100(H\u0002J\u0012\u0010V\u001a\u0004\u0018\u00010\u00102\u0006\u0010W\u001a\u00020\u0015H\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000eR\u0015\u0010\u000f\u001a\t\u0018\u00010\u0010¢\u0006\u0002\b\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0015\u0010\u0012\u001a\t\u0018\u00010\u0010¢\u0006\u0002\b\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u0013\u001a\u001e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014j\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0016`\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0018\u001a\u00070\u0019¢\u0006\u0002\b\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001b\u001a\u0004\u0018\u00010\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001dR\u001d\u0010 \u001a\u0004\u0018\u00010\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u001f\u001a\u0004\b!\u0010\u001dR\u001b\u0010#\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b&\u0010\u001f\u001a\u0004\b$\u0010%R'\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00100(8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\u001f\u001a\u0004\b)\u0010*R\u0014\u0010,\u001a\u00020-8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010.R\u0014\u0010/\u001a\u00020-8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b0\u0010.R\u0017\u00104\u001a\b\u0012\u0004\u0012\u00020\u0016058F¢\u0006\u0006\u001a\u0004\b6\u00107R\u001a\u00108\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020:09X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010;\u001a\b\u0012\u0004\u0012\u00020:058F¢\u0006\u0006\u001a\u0004\b<\u00107R\u001b\u0010M\u001a\u00020D8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bP\u0010\u001f\u001a\u0004\bN\u0010OR!\u0010Q\u001a\b\u0012\u0004\u0012\u00020\u00100C8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bT\u0010\u001f\u001a\u0004\bR\u0010S¨\u0006X"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/modules/CliJavaModuleFinder;", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleFinder;", "jdkHome", "Ljava/io/File;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "javaFileManager", "Lorg/jetbrains/kotlin/resolve/jvm/KotlinCliJavaFileManager;", "project", "Lcom/intellij/openapi/project/Project;", "jdkRelease", Argument.Delimiters.none, "<init>", "(Ljava/io/File;Lorg/jetbrains/kotlin/config/CompilerConfiguration;Lorg/jetbrains/kotlin/resolve/jvm/KotlinCliJavaFileManager;Lcom/intellij/openapi/project/Project;Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "jrtFileSystemRoot", "Lcom/intellij/openapi/vfs/VirtualFile;", "Lorg/jetbrains/annotations/Nullable;", "modulesRoot", "userModules", "Ljava/util/LinkedHashMap;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModule;", "Lkotlin/collections/LinkedHashMap;", "allScope", "Lcom/intellij/psi/search/GlobalSearchScope;", "Lorg/jetbrains/annotations/NotNull;", "ctSymFile", "getCtSymFile", "()Lcom/intellij/openapi/vfs/VirtualFile;", "ctSymFile$delegate", "Lkotlin/Lazy;", "ctSymRootFolder", "getCtSymRootFolder", "ctSymRootFolder$delegate", "compilationJdkVersion", "getCompilationJdkVersion", "()I", "compilationJdkVersion$delegate", "ctSymModules", Argument.Delimiters.none, "getCtSymModules", "()Ljava/util/Map;", "ctSymModules$delegate", "isCompilationJDK12OrLater", Argument.Delimiters.none, "()Z", "useLastJdkApi", "getUseLastJdkApi", "addUserModule", Argument.Delimiters.none, ModuleXmlParser.MODULE, "allObservableModules", "Lkotlin/sequences/Sequence;", "getAllObservableModules", "()Lkotlin/sequences/Sequence;", "systemModulesCache", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModule$Explicit;", "systemModules", "getSystemModules", "findModule", ModuleXmlParser.NAME, "findSystemModule", "moduleRoot", "useSig", "createModuleFromSignature", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModule$Root;", "moduleInfo", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModuleInfo;", "filterPackages", "filterModules", "codeFor", "release", "matchesRelease", "fileName", "nonModuleRoot", "getNonModuleRoot", "()Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModule$Root;", "nonModuleRoot$delegate", "listFoldersForRelease", "getListFoldersForRelease", "()Ljava/util/List;", "listFoldersForRelease$delegate", "collectModuleRoots", "reportError", "message", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliJavaModuleFinder implements JavaModuleFinder {
    private final GlobalSearchScope allScope;

    /* JADX INFO: renamed from: compilationJdkVersion$delegate, reason: from kotlin metadata */
    private final Lazy compilationJdkVersion;
    private final CompilerConfiguration configuration;

    /* JADX INFO: renamed from: ctSymFile$delegate, reason: from kotlin metadata */
    private final Lazy ctSymFile;

    /* JADX INFO: renamed from: ctSymModules$delegate, reason: from kotlin metadata */
    private final Lazy ctSymModules;

    /* JADX INFO: renamed from: ctSymRootFolder$delegate, reason: from kotlin metadata */
    private final Lazy ctSymRootFolder;
    private final KotlinCliJavaFileManager javaFileManager;
    private final File jdkHome;
    private final Integer jdkRelease;
    private final VirtualFile jrtFileSystemRoot;

    /* JADX INFO: renamed from: listFoldersForRelease$delegate, reason: from kotlin metadata */
    private final Lazy listFoldersForRelease;
    private final VirtualFile modulesRoot;

    /* JADX INFO: renamed from: nonModuleRoot$delegate, reason: from kotlin metadata */
    private final Lazy nonModuleRoot;
    private final Map<String, JavaModule.Explicit> systemModulesCache;
    private final LinkedHashMap<String, JavaModule> userModules;

    public CliJavaModuleFinder(File file, CompilerConfiguration compilerConfiguration, KotlinCliJavaFileManager kotlinCliJavaFileManager, Project project, Integer num) {
        String path;
        VirtualFileSystem fileSystem;
        compilerConfiguration.getClass();
        kotlinCliJavaFileManager.getClass();
        project.getClass();
        this.jdkHome = file;
        this.configuration = compilerConfiguration;
        this.javaFileManager = kotlinCliJavaFileManager;
        this.jdkRelease = num;
        VirtualFile virtualFileFindFileByPath = (file == null || (path = file.getPath()) == null || (fileSystem = VirtualFileManager.getInstance().getFileSystem("jrt")) == null) ? null : fileSystem.findFileByPath(path.concat("!/"));
        this.jrtFileSystemRoot = virtualFileFindFileByPath;
        this.modulesRoot = virtualFileFindFileByPath != null ? virtualFileFindFileByPath.findChild(ModuleXmlParser.MODULES) : null;
        this.userModules = new LinkedHashMap<>();
        GlobalSearchScope globalSearchScopeAllScope = GlobalSearchScope.allScope(project);
        globalSearchScopeAllScope.getClass();
        this.allScope = globalSearchScopeAllScope;
        this.ctSymFile = LazyKt.lazy(new Function0() { // from class: vz1
            public final Object invoke() {
                return CliJavaModuleFinder.h(this.b);
            }
        });
        this.ctSymRootFolder = LazyKt.lazy(new Function0() { // from class: wz1
            public final Object invoke() {
                return CliJavaModuleFinder.e(this.b);
            }
        });
        this.compilationJdkVersion = LazyKt.lazy(new Function0() { // from class: xz1
            public final Object invoke() {
                return Integer.valueOf(CliJavaModuleFinder.b(this.b));
            }
        });
        this.ctSymModules = LazyKt.lazy(new Function0() { // from class: yz1
            public final Object invoke() {
                return CliJavaModuleFinder.f(this.b);
            }
        });
        this.systemModulesCache = new LinkedHashMap();
        this.nonModuleRoot = LazyKt.lazy(new Function0() { // from class: zz1
            public final Object invoke() {
                return CliJavaModuleFinder.c(this.b);
            }
        });
        this.listFoldersForRelease = LazyKt.lazy(new Function0() { // from class: a02
            public final Object invoke() {
                return CliJavaModuleFinder.d(this.b);
            }
        });
    }

    public static JavaModule.Explicit a(CliJavaModuleFinder cliJavaModuleFinder, VirtualFile virtualFile) {
        virtualFile.getClass();
        return cliJavaModuleFinder.findSystemModule(virtualFile, true);
    }

    public static int b(CliJavaModuleFinder cliJavaModuleFinder) {
        VirtualFile[] children;
        int iIntValue;
        int iIntValue2;
        VirtualFile ctSymRootFolder = cliJavaModuleFinder.getCtSymRootFolder();
        if (ctSymRootFolder == null || (children = ctSymRootFolder.getChildren()) == null) {
            return -1;
        }
        if (children.length == 0) {
            z0e.a();
            return 0;
        }
        VirtualFile virtualFile = children[0];
        if (Intrinsics.areEqual(virtualFile.getName(), "META-INF")) {
            iIntValue = -1;
        } else {
            String name = virtualFile.getName();
            name.getClass();
            String strSubstringBeforeLast$default = StringsKt.substringBeforeLast$default(name, "-modules", (String) null, 2, (Object) null);
            if (strSubstringBeforeLast$default.length() == 0) {
                z0e.a();
                return 0;
            }
            Integer intOrNull = StringsKt.toIntOrNull(String.valueOf(strSubstringBeforeLast$default.charAt(0)), 36);
            iIntValue = intOrNull != null ? intOrNull.intValue() : -1;
            int lastIndex = StringsKt.getLastIndex(strSubstringBeforeLast$default);
            if (1 <= lastIndex) {
                int i = 1;
                while (true) {
                    Integer intOrNull2 = StringsKt.toIntOrNull(String.valueOf(strSubstringBeforeLast$default.charAt(i)), 36);
                    int iIntValue3 = intOrNull2 != null ? intOrNull2.intValue() : -1;
                    if (iIntValue < iIntValue3) {
                        iIntValue = iIntValue3;
                    }
                    if (i == lastIndex) {
                        break;
                    }
                    i++;
                }
            }
        }
        int lastIndex2 = ArraysKt.getLastIndex(children);
        if (1 > lastIndex2) {
            return iIntValue;
        }
        int i2 = 1;
        while (true) {
            VirtualFile virtualFile2 = children[i2];
            if (Intrinsics.areEqual(virtualFile2.getName(), "META-INF")) {
                iIntValue2 = -1;
            } else {
                String name2 = virtualFile2.getName();
                name2.getClass();
                String strSubstringBeforeLast$default2 = StringsKt.substringBeforeLast$default(name2, "-modules", (String) null, 2, (Object) null);
                if (strSubstringBeforeLast$default2.length() == 0) {
                    z0e.a();
                    return 0;
                }
                Integer intOrNull3 = StringsKt.toIntOrNull(String.valueOf(strSubstringBeforeLast$default2.charAt(0)), 36);
                iIntValue2 = intOrNull3 != null ? intOrNull3.intValue() : -1;
                int lastIndex3 = StringsKt.getLastIndex(strSubstringBeforeLast$default2);
                if (1 <= lastIndex3) {
                    int i3 = 1;
                    while (true) {
                        Integer intOrNull4 = StringsKt.toIntOrNull(String.valueOf(strSubstringBeforeLast$default2.charAt(i3)), 36);
                        int iIntValue4 = intOrNull4 != null ? intOrNull4.intValue() : -1;
                        if (iIntValue2 < iIntValue4) {
                            iIntValue2 = iIntValue4;
                        }
                        if (i3 == lastIndex3) {
                            break;
                        }
                        i3++;
                    }
                }
            }
            if (iIntValue < iIntValue2) {
                iIntValue = iIntValue2;
            }
            if (i2 == lastIndex2) {
                return iIntValue;
            }
            i2++;
        }
    }

    public static JavaModule.Root c(CliJavaModuleFinder cliJavaModuleFinder) {
        return cliJavaModuleFinder.createModuleFromSignature(false, false, new JavaModuleInfo("*", CollectionsKt.emptyList(), CollectionsKt.emptyList(), CollectionsKt.emptyList()));
    }

    private final String codeFor(int release) {
        String string = Integer.toString(release, CharsKt.checkRadix(36));
        string.getClass();
        String upperCase = string.toUpperCase(Locale.ROOT);
        upperCase.getClass();
        return upperCase;
    }

    private final Map<String, VirtualFile> collectModuleRoots() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (isCompilationJDK12OrLater()) {
            for (VirtualFile virtualFile : getListFoldersForRelease()) {
                if (virtualFile.findChild("module-info.sig") != null) {
                    linkedHashMap.put(virtualFile.getName(), virtualFile);
                }
            }
        } else {
            Integer num = this.jdkRelease;
            num.getClass();
            if (num.intValue() > 8 && getCtSymRootFolder() != null) {
                VirtualFile ctSymRootFolder = getCtSymRootFolder();
                ctSymRootFolder.getClass();
                StringBuilder sb = new StringBuilder();
                sb.append(codeFor(this.jdkRelease.intValue()));
                sb.append(!isCompilationJDK12OrLater() ? "-modules" : Argument.Delimiters.none);
                VirtualFile virtualFileFindChild = ctSymRootFolder.findChild(sb.toString());
                if (virtualFileFindChild != null) {
                    VirtualFile[] children = virtualFileFindChild.getChildren();
                    children.getClass();
                    for (VirtualFile virtualFile2 : children) {
                        linkedHashMap.put(virtualFile2.getName(), virtualFile2);
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder("Can't find modules signatures in `ct.sym` file for `-Xjdk-release=");
                    sb2.append(this.jdkRelease.intValue());
                    sb2.append("` in ");
                    VirtualFile ctSymFile = getCtSymFile();
                    ctSymFile.getClass();
                    sb2.append(ctSymFile.getPath());
                    reportError(sb2.toString());
                }
            }
        }
        return linkedHashMap;
    }

    private final JavaModule.Root createModuleFromSignature(boolean filterPackages, boolean filterModules, JavaModuleInfo moduleInfo) {
        Map map;
        List listFoldersForRelease;
        if (filterPackages) {
            map = new HashMap();
            Iterator it = moduleInfo.getExports().iterator();
            while (it.hasNext()) {
                Iterator it2 = SequencesKt.generateSequence(((JavaModuleInfo.Exports) it.next()).getPackageFqName(), new Function1() { // from class: c02
                    public final Object invoke(Object obj) {
                        return CliJavaModuleFinder.createModuleFromSignature$lambda$0$0$0((FqName) obj);
                    }
                }).iterator();
                while (it2.hasNext()) {
                    map.put(((FqName) it2.next()).asString(), Boolean.FALSE);
                }
            }
            Iterator it3 = moduleInfo.getExports().iterator();
            while (it3.hasNext()) {
                map.put(((JavaModuleInfo.Exports) it3.next()).getPackageFqName().asString(), Boolean.TRUE);
            }
        } else {
            map = MapsKt.emptyMap();
        }
        Map map2 = map;
        if (filterModules) {
            List<VirtualFile> listFoldersForRelease2 = getListFoldersForRelease();
            listFoldersForRelease = new ArrayList();
            for (Object obj : listFoldersForRelease2) {
                if (Intrinsics.areEqual(((VirtualFile) obj).getName(), moduleInfo.getModuleName())) {
                    listFoldersForRelease.add(obj);
                }
            }
        } else {
            listFoldersForRelease = getListFoldersForRelease();
        }
        List list = listFoldersForRelease;
        VirtualFile ctSymRootFolder = getCtSymRootFolder();
        if (ctSymRootFolder == null) {
            ctSymRootFolder = getCtSymFile();
        }
        return new JavaModule.Root(new CtSymDirectoryContainer(ctSymRootFolder, list, map2, Argument.Delimiters.none, moduleInfo.getModuleName(), !filterPackages), true, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FqName createModuleFromSignature$lambda$0$0$0(FqName fqName) {
        fqName.getClass();
        if (fqName.isRoot()) {
            return null;
        }
        return fqName.parent();
    }

    public static List d(CliJavaModuleFinder cliJavaModuleFinder) {
        List listListOf;
        if (cliJavaModuleFinder.getCtSymRootFolder() == null) {
            return CollectionsKt.emptyList();
        }
        VirtualFile ctSymRootFolder = cliJavaModuleFinder.getCtSymRootFolder();
        ctSymRootFolder.getClass();
        VirtualFile[] children = ctSymRootFolder.getChildren();
        children.getClass();
        ArrayList<VirtualFile> arrayList = new ArrayList();
        for (VirtualFile virtualFile : children) {
            String name = virtualFile.getName();
            name.getClass();
            Integer num = cliJavaModuleFinder.jdkRelease;
            num.getClass();
            if (cliJavaModuleFinder.matchesRelease(name, num.intValue())) {
                arrayList.add(virtualFile);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (VirtualFile virtualFile2 : arrayList) {
            if (cliJavaModuleFinder.isCompilationJDK12OrLater()) {
                VirtualFile[] children2 = virtualFile2.getChildren();
                children2.getClass();
                listListOf = ArraysKt.toList(children2);
            } else {
                listListOf = CollectionsKt.listOf(virtualFile2);
            }
            CollectionsKt.addAll(arrayList2, listListOf);
        }
        if (arrayList2.isEmpty()) {
            StringBuilder sb = new StringBuilder("'-Xjdk-release=");
            sb.append(cliJavaModuleFinder.jdkRelease);
            sb.append("' option is not supported by used JDK: ");
            File file = cliJavaModuleFinder.jdkHome;
            sb.append(file != null ? file.getPath() : null);
            cliJavaModuleFinder.reportError(sb.toString());
        }
        return arrayList2;
    }

    public static VirtualFile e(CliJavaModuleFinder cliJavaModuleFinder) {
        if (cliJavaModuleFinder.getCtSymFile() == null) {
            return null;
        }
        VirtualFileSystem virtualFileSystemJar = StandardFileSystems.jar();
        if (virtualFileSystemJar != null) {
            StringBuilder sb = new StringBuilder();
            VirtualFile ctSymFile = cliJavaModuleFinder.getCtSymFile();
            sb.append(ctSymFile != null ? ctSymFile.getPath() : null);
            sb.append("!/");
            VirtualFile virtualFileFindFileByPath = virtualFileSystemJar.findFileByPath(sb.toString());
            if (virtualFileFindFileByPath != null) {
                return virtualFileFindFileByPath;
            }
        }
        StringBuilder sb2 = new StringBuilder("Can't open `ct.sym` as jar file, file path: ");
        VirtualFile ctSymFile2 = cliJavaModuleFinder.getCtSymFile();
        sb2.append(ctSymFile2 != null ? ctSymFile2.getPath() : null);
        sb2.append(' ');
        return cliJavaModuleFinder.reportError(sb2.toString());
    }

    public static Map f(CliJavaModuleFinder cliJavaModuleFinder) {
        return cliJavaModuleFinder.collectModuleRoots();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.analyzer.CompilationErrorException */
    private final JavaModule.Explicit findSystemModule(VirtualFile moduleRoot, boolean useSig) throws CompilationErrorException {
        List<JavaModule.Root> listCreateModuleFromSignature;
        VirtualFile virtualFileFindChild = moduleRoot.findChild(useSig ? "module-info.sig" : "module-info.class");
        if (virtualFileFindChild == null) {
            return null;
        }
        try {
            JavaModuleInfo javaModuleInfo = JavaModuleInfo.Companion.read(virtualFileFindChild, this.javaFileManager, this.allScope);
            if (javaModuleInfo == null) {
                return null;
            }
            Map<String, JavaModule.Explicit> map = this.systemModulesCache;
            String moduleName = javaModuleInfo.getModuleName();
            JavaModule.Explicit explicit = map.get(moduleName);
            if (explicit == null) {
                if (getUseLastJdkApi()) {
                    listCreateModuleFromSignature = CollectionsKt.listOf(new JavaModule.Root(moduleRoot, true, useSig));
                } else {
                    if (!useSig) {
                        b88.a("Can't find ", moduleRoot.getPath(), " module");
                        return null;
                    }
                    listCreateModuleFromSignature = createModuleFromSignature(javaModuleInfo);
                }
                explicit = new JavaModule.Explicit(javaModuleInfo, listCreateModuleFromSignature, virtualFileFindChild, !getUseLastJdkApi() && useSig);
                map.put(moduleName, explicit);
            }
            return explicit;
        } catch (JavaModuleInfo.FileReadingException e) {
            reportError(e.getMessage() + "\nCaused by: " + e.getCause().getClass().getName() + ": " + e.getCause().getMessage());
            throw new CompilationErrorException();
        }
    }

    public static /* synthetic */ JavaModule.Explicit findSystemModule$default(CliJavaModuleFinder cliJavaModuleFinder, VirtualFile virtualFile, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return cliJavaModuleFinder.findSystemModule(virtualFile, z);
    }

    private final int getCompilationJdkVersion() {
        return ((Number) this.compilationJdkVersion.getValue()).intValue();
    }

    private final VirtualFile getCtSymFile() {
        return (VirtualFile) this.ctSymFile.getValue();
    }

    private final VirtualFile getCtSymRootFolder() {
        return (VirtualFile) this.ctSymRootFolder.getValue();
    }

    private final List<VirtualFile> getListFoldersForRelease() {
        return (List) this.listFoldersForRelease.getValue();
    }

    private final boolean getUseLastJdkApi() {
        Integer num = this.jdkRelease;
        if (num != null) {
            return num != null && num.intValue() == getCompilationJdkVersion();
        }
        return true;
    }

    public static VirtualFile h(CliJavaModuleFinder cliJavaModuleFinder) {
        if (cliJavaModuleFinder.jdkHome == null) {
            return cliJavaModuleFinder.reportError("JDK_HOME path is not specified in compiler configuration");
        }
        VirtualFile virtualFileFindFileByPath = StandardFileSystems.local().findFileByPath(cliJavaModuleFinder.jdkHome.getPath());
        if (virtualFileFindFileByPath == null) {
            return cliJavaModuleFinder.reportError("Can't create virtual file for JDK root under " + cliJavaModuleFinder.jdkHome.getPath());
        }
        VirtualFile virtualFileFindChild = virtualFileFindFileByPath.findChild("lib");
        if (virtualFileFindChild == null) {
            return cliJavaModuleFinder.reportError("Can't find `lib` folder under JDK root: " + cliJavaModuleFinder.jdkHome.getPath());
        }
        VirtualFile virtualFileFindChild2 = virtualFileFindChild.findChild("ct.sym");
        if (virtualFileFindChild2 != null) {
            return virtualFileFindChild2;
        }
        return cliJavaModuleFinder.reportError("This JDK does not have the 'ct.sym' file required for the '-Xjdk-release=" + cliJavaModuleFinder.jdkRelease + "' option: " + cliJavaModuleFinder.jdkHome.getPath());
    }

    private final boolean isCompilationJDK12OrLater() {
        return getCompilationJdkVersion() >= 12;
    }

    private final boolean matchesRelease(String fileName, int release) {
        return !StringsKt.contains$default(fileName, "-", false, 2, (Object) null) && StringsKt.contains$default(fileName, codeFor(release), false, 2, (Object) null);
    }

    private final VirtualFile reportError(String message) {
        CliDiagnosticReportingKt.report$default(this.configuration, CliDiagnostics.INSTANCE.getJAVA_MODULE_RESOLUTION_ERROR(), message, null, 4, null);
        return null;
    }

    public final void addUserModule(JavaModule module) {
        module.getClass();
        this.userModules.putIfAbsent(module.getName(), module);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.analyzer.CompilationErrorException */
    @Override // org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleFinder
    public JavaModule findModule(String name) throws CompilationErrorException {
        VirtualFile virtualFileFindChild;
        name.getClass();
        JavaModule.Explicit explicitFindSystemModule = null;
        if (getUseLastJdkApi()) {
            VirtualFile virtualFile = this.modulesRoot;
            if (virtualFile != null && (virtualFileFindChild = virtualFile.findChild(name)) != null) {
                explicitFindSystemModule = findSystemModule$default(this, virtualFileFindChild, false, 2, null);
            }
        } else {
            VirtualFile virtualFile2 = getCtSymModules().get(name);
            if (virtualFile2 != null) {
                explicitFindSystemModule = findSystemModule(virtualFile2, true);
            }
        }
        return explicitFindSystemModule == null ? this.userModules.get(name) : explicitFindSystemModule;
    }

    public final Sequence<JavaModule> getAllObservableModules() {
        Sequence<JavaModule.Explicit> systemModules = getSystemModules();
        Collection<JavaModule> collectionValues = this.userModules.values();
        collectionValues.getClass();
        return SequencesKt.plus(systemModules, collectionValues);
    }

    public final Map<String, VirtualFile> getCtSymModules() {
        return (Map) this.ctSymModules.getValue();
    }

    public final JavaModule.Root getNonModuleRoot() {
        return (JavaModule.Root) this.nonModuleRoot.getValue();
    }

    public final Sequence<JavaModule.Explicit> getSystemModules() {
        if (!getUseLastJdkApi()) {
            return SequencesKt.mapNotNull(CollectionsKt.asSequence(getCtSymModules().values()), new Function1() { // from class: b02
                public final Object invoke(Object obj) {
                    return CliJavaModuleFinder.a(this.b, (VirtualFile) obj);
                }
            });
        }
        VirtualFile virtualFile = this.modulesRoot;
        VirtualFile[] children = virtualFile != null ? virtualFile.getChildren() : null;
        if (children == null) {
            children = new VirtualFile[0];
        }
        return SequencesKt.mapNotNull(ArraysKt.asSequence(children), new CliJavaModuleFinder$systemModules$1(this));
    }

    private final List<JavaModule.Root> createModuleFromSignature(JavaModuleInfo moduleInfo) {
        return CollectionsKt.listOf(createModuleFromSignature(!isCompilationJDK12OrLater(), isCompilationJDK12OrLater(), moduleInfo));
    }
}
