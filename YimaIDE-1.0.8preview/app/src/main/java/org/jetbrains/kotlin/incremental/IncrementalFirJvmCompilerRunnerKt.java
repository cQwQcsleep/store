package org.jetbrains.kotlin.incremental;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JVMCompilerArguments;
import org.jetbrains.kotlin.cli.common.config.ContentRootsKt;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRootsKt;
import org.jetbrains.kotlin.cli.jvm.config.JvmModulePathRoot;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.HmppCliModuleKt;
import org.jetbrains.kotlin.config.HmppCliModuleStructure;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a2\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002\u001a\u0012\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000e*\u00020\u0004H\u0002¨\u0006\u000f"}, d2 = {"configureBaseRoots", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "args", "Lorg/jetbrains/kotlin/cli/common/arguments/K2JVMCompilerArguments;", "configureSourceRootsFromSources", "allSources", Argument.Delimiters.none, "Ljava/io/File;", ModuleXmlParser.COMMON_SOURCES, Argument.Delimiters.none, "javaPackagePrefix", Argument.Delimiters.none, "javaSources", Argument.Delimiters.none, "org.jetbrains.kotlin:incremental-compilation-impl"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IncrementalFirJvmCompilerRunnerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void configureBaseRoots(CompilerConfiguration compilerConfiguration, K2JVMCompilerArguments k2JVMCompilerArguments) {
        File[] fileArrListFiles;
        boolean z = false;
        for (String str : k2JVMCompilerArguments.getJavaSourceRoots()) {
            File file = new File(str);
            String javaPackagePrefix = k2JVMCompilerArguments.getJavaPackagePrefix();
            JvmContentRootsKt.addJavaSourceRoot(compilerConfiguration, file, javaPackagePrefix);
            if (!z && javaPackagePrefix == null) {
                if (Intrinsics.areEqual(file.getName(), "module-info.java")) {
                    z = true;
                    break;
                }
                if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                    for (File file2 : fileArrListFiles) {
                        if (Intrinsics.areEqual(file2.getName(), "module-info.java")) {
                            z = true;
                            break;
                            break;
                        }
                    }
                }
            }
        }
        String classpath = k2JVMCompilerArguments.getClasspath();
        if (classpath != null) {
            String str2 = File.pathSeparator;
            str2.getClass();
            List<String> listSplit$default = StringsKt.split$default(classpath, new String[]{str2}, false, 0, 6, (Object) null);
            if (listSplit$default != null) {
                for (String str3 : listSplit$default) {
                    compilerConfiguration.add(CLIConfigurationKeys.CONTENT_ROOTS, z ? new JvmModulePathRoot(new File(str3)) : new JvmClasspathRoot(new File(str3)));
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void configureSourceRootsFromSources(CompilerConfiguration compilerConfiguration, Collection<? extends File> collection, Set<? extends File> set, String str) {
        HmppCliModuleStructure hmppCliModuleStructure = (HmppCliModuleStructure) compilerConfiguration.get(CommonConfigurationKeys.HMPP_MODULE_STRUCTURE);
        for (File file : collection) {
            String name = file.getName();
            name.getClass();
            if (StringsKt.endsWith$default(name, ".java", false, 2, (Object) null)) {
                JvmContentRootsKt.addJavaSourceRoot(compilerConfiguration, file, str);
            } else {
                String path = file.getPath();
                path.getClass();
                ContentRootsKt.addKotlinSourceRoot(compilerConfiguration, path, set.contains(file), hmppCliModuleStructure != null ? HmppCliModuleKt.getModuleNameForSource(hmppCliModuleStructure, path) : null);
                if (file.isDirectory()) {
                    JvmContentRootsKt.addJavaSourceRoot(compilerConfiguration, file, str);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<File> javaSources(K2JVMCompilerArguments k2JVMCompilerArguments) {
        List<String> freeArgs = k2JVMCompilerArguments.getFreeArgs();
        ArrayList arrayList = new ArrayList();
        for (Object obj : freeArgs) {
            if (StringsKt.endsWith$default((String) obj, ".java", false, 2, (Object) null)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new File((String) it.next()));
        }
        return arrayList2;
    }
}
