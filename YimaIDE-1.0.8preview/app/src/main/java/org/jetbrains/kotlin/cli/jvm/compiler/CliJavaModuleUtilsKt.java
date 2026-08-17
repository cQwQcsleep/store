package org.jetbrains.kotlin.cli.jvm.compiler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.index.JavaRoot;
import org.jetbrains.kotlin.cli.jvm.modules.CliJavaModuleFinder;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModule;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleInfo;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003\u001a\u0010\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001*\u00020\u0006¨\u0006\u0007"}, d2 = {"getJavaModuleRoots", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/index/JavaRoot;", "Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModule;", "computeDefaultRootModules", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/jvm/modules/CliJavaModuleFinder;", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CliJavaModuleUtilsKt {
    public static final List<String> computeDefaultRootModules(CliJavaModuleFinder cliJavaModuleFinder) {
        cliJavaModuleFinder.getClass();
        ArrayList arrayList = new ArrayList();
        Sequence<JavaModule.Explicit> systemModules = cliJavaModuleFinder.getSystemModules();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : systemModules) {
            linkedHashMap.put(((JavaModule) obj).getName(), obj);
        }
        boolean zContainsKey = linkedHashMap.containsKey("java.se");
        if (zContainsKey) {
            arrayList.add("java.se");
        }
        if (!zContainsKey) {
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                String str = (String) entry.getKey();
                JavaModule.Explicit explicit = (JavaModule.Explicit) entry.getValue();
                if (StringsKt.startsWith$default(str, "java.", false, 2, (Object) null) && computeDefaultRootModules$exportsAtLeastOnePackageUnqualified(explicit)) {
                    arrayList.add(str);
                }
            }
        }
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            String str2 = (String) entry2.getKey();
            JavaModule.Explicit explicit2 = (JavaModule.Explicit) entry2.getValue();
            if (!StringsKt.startsWith$default(str2, "java.", false, 2, (Object) null) && computeDefaultRootModules$exportsAtLeastOnePackageUnqualified(explicit2)) {
                arrayList.add(str2);
            }
        }
        return arrayList;
    }

    private static final boolean computeDefaultRootModules$exportsAtLeastOnePackageUnqualified(JavaModule.Explicit explicit) {
        List exports = explicit.getModuleInfo().getExports();
        if ((exports instanceof Collection) && exports.isEmpty()) {
            return false;
        }
        Iterator it = exports.iterator();
        while (it.hasNext()) {
            if (((JavaModuleInfo.Exports) it.next()).getToModules().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    public static final List<JavaRoot> getJavaModuleRoots(JavaModule javaModule) {
        javaModule.getClass();
        List<JavaModule.Root> moduleRoots = javaModule.getModuleRoots();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(moduleRoots, 10));
        for (JavaModule.Root root : moduleRoots) {
            arrayList.add(new JavaRoot(root.component1(), root.component3() ? JavaRoot.RootType.BINARY_SIG : root.component2() ? JavaRoot.RootType.BINARY : JavaRoot.RootType.SOURCE, null, 4, null));
        }
        return arrayList;
    }
}
