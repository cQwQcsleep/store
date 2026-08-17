package org.jetbrains.kotlin.codegen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.backend.common.output.OutputFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.ClassFileUtilsKt;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.kotlin.ModuleMappingUtilKt;
import org.jetbrains.kotlin.load.kotlin.incremental.components.IncrementalCache;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.metadata.jvm.deserialization.ModuleMapping;
import org.jetbrains.kotlin.metadata.jvm.deserialization.PackageParts;
import org.jetbrains.kotlin.resolve.JvmCompilerDeserializationConfiguration;
import org.jetbrains.kotlin.resolve.jvm.JvmClassName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0003\u001a\f\u0010\u0004\u001a\u0004\u0018\u00010\u0002*\u00020\u0003\u001a\u0016\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006*\b\u0012\u0004\u0012\u00020\u00020\u0006\u001a\u001e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0006*\b\u0012\u0004\u0012\u00020\b0\u00012\u0006\u0010\t\u001a\u00020\n\u001a \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\b0\u0006*\b\u0012\u0004\u0012\u00020\b0\u00012\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\f\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\n¨\u0006\u000e"}, d2 = {"getClassFiles", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/backend/common/output/OutputFile;", "Lorg/jetbrains/kotlin/codegen/ClassFileFactory;", "getKotlinModuleFile", "filterClassFiles", Argument.Delimiters.none, "addCompiledPartsAndSort", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/PackageParts;", "state", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "addCompiledParts", "loadCompiledModule", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/ModuleMapping;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ClassFileUtilsKt {
    public static Unit a(MetadataVersion metadataVersion) {
        metadataVersion.getClass();
        throw new IllegalStateException("Version of the generated module cannot be incompatible: " + metadataVersion);
    }

    private static final List<PackageParts> addCompiledParts(Iterable<PackageParts> iterable, GenerationState generationState) {
        Collection<String> obsoletePackageParts;
        ModuleMapping moduleMappingLoadCompiledModule = loadCompiledModule(generationState);
        if (moduleMappingLoadCompiledModule == null) {
            return CollectionsKt.toList(iterable);
        }
        IncrementalCache incrementalCacheForThisTarget = generationState.getIncrementalCacheForThisTarget();
        if (incrementalCacheForThisTarget != null && (obsoletePackageParts = incrementalCacheForThisTarget.getObsoletePackageParts()) != null) {
            for (String str : obsoletePackageParts) {
                PackageParts packagePartsFindPackageParts = moduleMappingLoadCompiledModule.findPackageParts(JvmClassName.byInternalName(str).getPackageFqName().asString());
                if (packagePartsFindPackageParts != null) {
                    packagePartsFindPackageParts.removePart(str);
                }
            }
        }
        List listPlus = CollectionsKt.plus(iterable, moduleMappingLoadCompiledModule.getPackageFqName2Parts().values());
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : listPlus) {
            String packageFqName = ((PackageParts) obj).getPackageFqName();
            Object arrayList = linkedHashMap.get(packageFqName);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(packageFqName, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str2 = (String) entry.getKey();
            List list = (List) entry.getValue();
            PackageParts packageParts = new PackageParts(str2);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                packageParts.plusAssign((PackageParts) it.next());
            }
            arrayList2.add(packageParts);
        }
        return arrayList2;
    }

    public static final List<PackageParts> addCompiledPartsAndSort(Iterable<PackageParts> iterable, GenerationState generationState) {
        iterable.getClass();
        generationState.getClass();
        return CollectionsKt.sortedWith(addCompiledParts(iterable, generationState), new Comparator() { // from class: org.jetbrains.kotlin.codegen.ClassFileUtilsKt$addCompiledPartsAndSort$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(((PackageParts) t).getPackageFqName(), ((PackageParts) t2).getPackageFqName());
            }
        });
    }

    public static final List<OutputFile> filterClassFiles(List<? extends OutputFile> list) {
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (StringsKt.endsWith$default(((OutputFile) obj).getRelativePath(), ".class", false, 2, (Object) null)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Iterable<OutputFile> getClassFiles(ClassFileFactory classFileFactory) {
        classFileFactory.getClass();
        return filterClassFiles(classFileFactory.asList());
    }

    public static final OutputFile getKotlinModuleFile(ClassFileFactory classFileFactory) {
        classFileFactory.getClass();
        List<OutputFile> listAsList = classFileFactory.asList();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listAsList) {
            if (StringsKt.endsWith$default(((OutputFile) obj).getRelativePath(), ".kotlin_module", false, 2, (Object) null)) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size();
        if (size != 0) {
            if (size == 1) {
                return (OutputFile) CollectionsKt.single(arrayList);
            }
            k2d.a("Module has non-unique .kotlin_metadata file");
        }
        return null;
    }

    public static final ModuleMapping loadCompiledModule(GenerationState generationState) {
        byte[] moduleMappingData;
        generationState.getClass();
        IncrementalCache incrementalCacheForThisTarget = generationState.getIncrementalCacheForThisTarget();
        if (incrementalCacheForThisTarget == null || (moduleMappingData = incrementalCacheForThisTarget.getModuleMappingData()) == null) {
            return null;
        }
        return ModuleMappingUtilKt.loadModuleMapping(ModuleMapping.Companion, moduleMappingData, "<incremental>", new JvmCompilerDeserializationConfiguration(generationState.getConfig().getLanguageVersionSettings()), new Function1() { // from class: wu1
            public final Object invoke(Object obj) {
                return ClassFileUtilsKt.a((MetadataVersion) obj);
            }
        });
    }
}
