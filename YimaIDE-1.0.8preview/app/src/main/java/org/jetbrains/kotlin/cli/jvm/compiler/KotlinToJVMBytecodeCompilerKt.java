package org.jetbrains.kotlin.cli.jvm.compiler;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.config.ContentRootsKt;
import org.jetbrains.kotlin.cli.jvm.config.JvmClasspathRoot;
import org.jetbrains.kotlin.cli.jvm.config.JvmContentRootsKt;
import org.jetbrains.kotlin.cli.jvm.config.JvmModulePathRoot;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.HmppCliModuleKt;
import org.jetbrains.kotlin.config.HmppCliModuleStructure;
import org.jetbrains.kotlin.config.JVMConfigurationKeys;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.modules.JavaRootPath;
import org.jetbrains.kotlin.modules.Module;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¨\u0006\b"}, d2 = {"configureSourceRoots", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "chunk", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/modules/Module;", "buildFile", "Ljava/io/File;", "org.jetbrains.kotlin:cli-jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinToJVMBytecodeCompilerKt {
    public static final void configureSourceRoots(CompilerConfiguration compilerConfiguration, List<? extends Module> list, File file) {
        compilerConfiguration.getClass();
        list.getClass();
        HmppCliModuleStructure hmppCliModuleStructure = (HmppCliModuleStructure) compilerConfiguration.get(CommonConfigurationKeys.HMPP_MODULE_STRUCTURE);
        for (Module module : list) {
            Set set = CollectionsKt.toSet(CliCompilerUtilsKt.getBuildFilePaths(file, module.getCommonSourceFiles()));
            for (String str : CliCompilerUtilsKt.getBuildFilePaths(file, module.getSourceFiles())) {
                ContentRootsKt.addKotlinSourceRoot(compilerConfiguration, str, hmppCliModuleStructure != null ? HmppCliModuleKt.isFromCommonModule(hmppCliModuleStructure, str) : set.contains(str), hmppCliModuleStructure != null ? HmppCliModuleKt.getModuleNameForSource(hmppCliModuleStructure, str) : null);
            }
        }
        Iterator<? extends Module> it = list.iterator();
        while (it.hasNext()) {
            for (JavaRootPath javaRootPath : it.next().getJavaSourceRoots()) {
                JvmContentRootsKt.addJavaSourceRoot(compilerConfiguration, new File(javaRootPath.getPath()), javaRootPath.getPackagePrefix());
            }
        }
        List<? extends Module> list2 = list;
        boolean z = false;
        if (!(list2 instanceof Collection) || !list2.isEmpty()) {
            Iterator<T> it2 = list2.iterator();
            loop7: while (it2.hasNext()) {
                List<JavaRootPath> javaSourceRoots = ((Module) it2.next()).getJavaSourceRoots();
                if (!(javaSourceRoots instanceof Collection) || !javaSourceRoots.isEmpty()) {
                    Iterator<T> it3 = javaSourceRoots.iterator();
                    while (true) {
                        if (it3.hasNext()) {
                            JavaRootPath javaRootPath2 = (JavaRootPath) it3.next();
                            String path = javaRootPath2.getPath();
                            String packagePrefix = javaRootPath2.getPackagePrefix();
                            File file2 = new File(path);
                            if (packagePrefix == null) {
                                if (!Intrinsics.areEqual(file2.getName(), "module-info.java")) {
                                    if (file2.isDirectory()) {
                                        File[] fileArrListFiles = file2.listFiles();
                                        fileArrListFiles.getClass();
                                        int length = fileArrListFiles.length;
                                        int i = 0;
                                        while (true) {
                                            if (i >= length) {
                                                continue;
                                            } else if (!Intrinsics.areEqual(fileArrListFiles[i].getName(), "module-info.java")) {
                                                i++;
                                            }
                                        }
                                    } else {
                                        continue;
                                    }
                                }
                                z = true;
                                break loop7;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        }
        Iterator<? extends Module> it4 = list.iterator();
        while (it4.hasNext()) {
            for (String str2 : it4.next().getClasspathRoots()) {
                if (z) {
                    compilerConfiguration.add(CLIConfigurationKeys.CONTENT_ROOTS, new JvmModulePathRoot(new File(str2)));
                }
                compilerConfiguration.add(CLIConfigurationKeys.CONTENT_ROOTS, new JvmClasspathRoot(new File(str2)));
            }
        }
        Iterator<? extends Module> it5 = list.iterator();
        while (it5.hasNext()) {
            String modularJdkRoot = it5.next().getModularJdkRoot();
            if (modularJdkRoot != null) {
                compilerConfiguration.put(JVMConfigurationKeys.JDK_HOME, new File(modularJdkRoot));
                break;
            }
        }
        compilerConfiguration.addAll(JVMConfigurationKeys.MODULES, list);
    }

    public static /* synthetic */ void configureSourceRoots$default(CompilerConfiguration compilerConfiguration, List list, File file, int i, Object obj) {
        if ((i & 2) != 0) {
            file = null;
        }
        configureSourceRoots(compilerConfiguration, list, file);
    }
}
