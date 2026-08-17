package org.jetbrains.kotlin.cli.jvm.config;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.CliDiagnosticReportingKt;
import org.jetbrains.kotlin.cli.CliDiagnostics;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.config.ContentRoot;
import org.jetbrains.kotlin.cli.common.config.KotlinSourceRoot;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRootsKt;
import org.jetbrains.kotlin.cli.jvm.modules.CoreJrtFileSystem;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.CompilerConfigurationKey;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.PathUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0018\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u001a\u0018\u0010\b\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007\u001a\u0010\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\u00020\u0002\u001a \u0010\u0011\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007\u001a&\u0010\u0014\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00040\u00072\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0007\u001a\n\u0010\u0019\u001a\u00020\u0001*\u00020\u0002\"\u001b\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b\"\u001b\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0007*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000b\"\u001b\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u0016*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001a"}, d2 = {"addJvmClasspathRoot", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "file", "Ljava/io/File;", "addJvmClasspathRoots", "files", Argument.Delimiters.none, "addJvmSdkRoots", "jvmClasspathRoots", "getJvmClasspathRoots", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/util/List;", "jvmClasspathNioRoots", "Lkotlin/sequences/Sequence;", "Ljava/nio/file/Path;", "jvmModularRoots", "getJvmModularRoots", "addJavaSourceRoot", ModuleXmlParser.JAVA_SOURCE_PACKAGE_PREFIX, Argument.Delimiters.none, "addJavaSourceRoots", ModuleXmlParser.JAVA_SOURCE_ROOTS, Argument.Delimiters.none, "getJavaSourceRoots", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/util/Set;", "configureJdkClasspathRoots", "kotlin-compiler"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 3, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmContentRootsKt {
    public static Path a(ContentRoot contentRoot) {
        contentRoot.getClass();
        if (contentRoot instanceof JvmClasspathRoot) {
            return ((JvmClasspathRoot) contentRoot).getFile().toPath();
        }
        if (contentRoot instanceof VirtualJvmClasspathRoot) {
            return ((VirtualJvmClasspathRoot) contentRoot).getFile().toNioPath();
        }
        return null;
    }

    public static final void addJavaSourceRoot(CompilerConfiguration compilerConfiguration, File file, String str) {
        compilerConfiguration.getClass();
        file.getClass();
        compilerConfiguration.add(CLIConfigurationKeys.CONTENT_ROOTS, new JavaSourceRoot(file, str));
    }

    public static /* synthetic */ void addJavaSourceRoot$default(CompilerConfiguration compilerConfiguration, File file, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        addJavaSourceRoot(compilerConfiguration, file, str);
    }

    public static final void addJavaSourceRoots(CompilerConfiguration compilerConfiguration, List<? extends File> list, String str) {
        compilerConfiguration.getClass();
        list.getClass();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            addJavaSourceRoot(compilerConfiguration, (File) it.next(), str);
        }
    }

    public static /* synthetic */ void addJavaSourceRoots$default(CompilerConfiguration compilerConfiguration, List list, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        addJavaSourceRoots(compilerConfiguration, list, str);
    }

    public static final void addJvmClasspathRoot(CompilerConfiguration compilerConfiguration, File file) {
        compilerConfiguration.getClass();
        file.getClass();
        compilerConfiguration.add(CLIConfigurationKeys.CONTENT_ROOTS, new JvmClasspathRoot(file));
    }

    public static final void addJvmClasspathRoots(CompilerConfiguration compilerConfiguration, List<? extends File> list) {
        compilerConfiguration.getClass();
        list.getClass();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            addJvmClasspathRoot(compilerConfiguration, (File) it.next());
        }
    }

    public static final void addJvmSdkRoots(CompilerConfiguration compilerConfiguration, List<? extends File> list) {
        compilerConfiguration.getClass();
        list.getClass();
        CompilerConfigurationKey<List<ContentRoot>> compilerConfigurationKey = CLIConfigurationKeys.CONTENT_ROOTS;
        List<? extends File> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(new JvmClasspathRoot((File) it.next(), true));
        }
        compilerConfiguration.addAll(compilerConfigurationKey, 0, arrayList);
    }

    public static final void configureJdkClasspathRoots(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        if (compilerConfiguration.getBoolean(JVMConfigurationKeys.NO_JDK)) {
            return;
        }
        File file = (File) compilerConfiguration.get(JVMConfigurationKeys.JDK_HOME);
        if (file == null) {
            file = new File(System.getProperty("java.home"));
        }
        List jdkClassesRootsFromJdkOrJre = PathUtil.getJdkClassesRootsFromJdkOrJre(file);
        if (CoreJrtFileSystem.INSTANCE.isModularJdk(file)) {
            return;
        }
        if (!jdkClassesRootsFromJdkOrJre.isEmpty()) {
            addJvmSdkRoots(compilerConfiguration, jdkClassesRootsFromJdkOrJre);
            return;
        }
        CliDiagnosticReportingKt.report$default(compilerConfiguration, CliDiagnostics.INSTANCE.getROOTS_RESOLUTION_ERROR(), "No class roots are found in the JDK path: " + file, null, 4, null);
    }

    public static final Set<String> getJavaSourceRoots(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        List<ContentRoot> list = compilerConfiguration.getList(CLIConfigurationKeys.CONTENT_ROOTS);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (ContentRoot contentRoot : list) {
            String path = contentRoot instanceof KotlinSourceRoot ? ((KotlinSourceRoot) contentRoot).getPath() : contentRoot instanceof JavaSourceRoot ? ((JavaSourceRoot) contentRoot).getFile().getPath() : null;
            if (path != null) {
                linkedHashSet.add(path);
            }
        }
        return linkedHashSet;
    }

    public static final List<File> getJvmClasspathRoots(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        List list = compilerConfiguration.getList(CLIConfigurationKeys.CONTENT_ROOTS);
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof JvmClasspathRoot) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((JvmContentRoot) it.next()).getFile());
        }
        return arrayList2;
    }

    public static final List<File> getJvmModularRoots(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        List list = compilerConfiguration.getList(CLIConfigurationKeys.CONTENT_ROOTS);
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof JvmModulePathRoot) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((JvmContentRoot) it.next()).getFile());
        }
        return arrayList2;
    }

    public static final Sequence<Path> jvmClasspathNioRoots(CompilerConfiguration compilerConfiguration) {
        compilerConfiguration.getClass();
        return SequencesKt.mapNotNull(CollectionsKt.asSequence(compilerConfiguration.getList(CLIConfigurationKeys.CONTENT_ROOTS)), new Function1() { // from class: fw7
            public final Object invoke(Object obj) {
                return JvmContentRootsKt.a((ContentRoot) obj);
            }
        });
    }

    public static final void addJavaSourceRoot(CompilerConfiguration compilerConfiguration, File file) {
        compilerConfiguration.getClass();
        file.getClass();
        addJavaSourceRoot$default(compilerConfiguration, file, null, 2, null);
    }

    public static final void addJavaSourceRoots(CompilerConfiguration compilerConfiguration, List<? extends File> list) {
        compilerConfiguration.getClass();
        list.getClass();
        addJavaSourceRoots$default(compilerConfiguration, list, null, 2, null);
    }
}
