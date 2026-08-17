package org.jetbrains.kotlin.js.artifacts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import org.jetbrains.kotlin.library.components.KlibMetadataConstants;
import org.jetbrains.kotlin.utils.CollectionUtilKt;
import org.jetbrains.kotlin.utils.MainFunctionCandidate;
import org.jetbrains.kotlin.utils.MainFunctionDetectionKt$pickMainFunctionFromCandidates$;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0014\n\u0002\u0010 \n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u00042\u00020\u0005J\u0013\u0010\u001c\u001a\u0004\u0018\u00018\u0003*\u00028\u0002H&¢\u0006\u0002\u0010\u001dJ\u0017\u0010\"\u001a\u00028\u0002*\b\u0012\u0004\u0012\u00028\u00020#H&¢\u0006\u0002\u0010$J\u001b\u0010%\u001a\u0004\u0018\u00018\u0002*\u00028\u00012\u0006\u0010&\u001a\u00028\u0000H&¢\u0006\u0002\u0010'JM\u0010(\u001a\u00028\u0002*\u00028\u00002\b\u0010)\u001a\u0004\u0018\u00010\u00072\b\u0010 \u001a\u0004\u0018\u00010\u00072\u001c\u0010*\u001a\u0018\u0012\u0004\u0012\u00020\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070#0+j\u0002`,2\b\u0010-\u001a\u0004\u0018\u00010\u0007H&¢\u0006\u0002\u0010.J\u001c\u0010/\u001a\b\u0012\u0004\u0012\u00028\u00020#2\f\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000#H\u0016R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0016\u0010\n\u001a\u00020\u000b*\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\fR\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u000e*\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u00020\u0007*\u00028\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0014\u001a\u00020\u000b*\u00028\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\fR\u0016\u0010\u0016\u001a\u00020\u000b*\u00028\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\fR\u0016\u0010\u0018\u001a\u00020\u0007*\u00028\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u0007*\u00028\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0013R\u0016\u0010\u001e\u001a\u00020\u0007*\u00028\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0013R\u0016\u0010 \u001a\u00020\u0007*\u00028\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u00061À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/js/artifacts/PerFileGenerator;", "Module", "File", "Artifact", "TestEnvironment", "", "mainModuleName", "", "getMainModuleName", "()Ljava/lang/String;", "isMain", "", "(Ljava/lang/Object;)Z", "fileList", "", "getFileList", "(Ljava/lang/Object;)Ljava/lang/Iterable;", "artifactName", "getArtifactName", "(Ljava/lang/Object;)Ljava/lang/String;", "hasEffect", "getHasEffect", "hasExport", "getHasExport", "packageFqn", "getPackageFqn", "mainFunction", "getMainFunction", "takeTestEnvironmentOwnership", "(Ljava/lang/Object;)Ljava/lang/Object;", "testFunctionTag", "getTestFunctionTag", "suiteFunctionTag", "getSuiteFunctionTag", "merge", "", "(Ljava/util/List;)Ljava/lang/Object;", "generateArtifact", KlibMetadataConstants.KLIB_MODULE_METADATA_FILE_NAME, "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "generateProxyArtifact", "mainFunctionTag", "testFunctions", "", "Lorg/jetbrains/kotlin/js/artifacts/CachedTestFunctionsWithTheirPackage;", "moduleNameForEffects", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)Ljava/lang/Object;", "generatePerFileArtifacts", "modules", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PerFileGenerator<Module, File, Artifact, TestEnvironment> {
    Artifact generateArtifact(File file, Module module);

    /* JADX WARN: Code duplicated, block: B:45:0x00e5  */
    /* JADX WARN: Multi-variable type inference failed */
    default List<Artifact> generatePerFileArtifacts(List<? extends Module> modules) {
        String mainFunction;
        Object next;
        modules.getClass();
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        boolean z = false;
        for (Module module : modules) {
            boolean z2 = z;
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            Iterable fileList = getFileList(module);
            ArrayList arrayList = new ArrayList();
            Iterator it = fileList.iterator();
            String suiteFunctionTag = null;
            boolean z3 = false;
            boolean z4 = false;
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object objGenerateArtifact = generateArtifact(it.next(), module);
                if (objGenerateArtifact == null) {
                    objGenerateArtifact = null;
                } else {
                    if (getHasExport(objGenerateArtifact)) {
                        z4 = true;
                    }
                    if (getHasEffect(objGenerateArtifact)) {
                        z3 = true;
                    }
                    Object objTakeTestEnvironmentOwnership = takeTestEnvironmentOwnership(objGenerateArtifact);
                    if (objTakeTestEnvironmentOwnership != null) {
                        CollectionUtilKt.putToMultiMap(linkedHashMap, getPackageFqn(objGenerateArtifact), getTestFunctionTag(objTakeTestEnvironmentOwnership));
                        suiteFunctionTag = getSuiteFunctionTag(objTakeTestEnvironmentOwnership);
                    }
                    CollectionUtilKt.putToMultiMap(mapCreateMapBuilder, getArtifactName(objGenerateArtifact), objGenerateArtifact);
                }
                if (objGenerateArtifact != null) {
                    arrayList.add(objGenerateArtifact);
                }
            }
            boolean z5 = z3 ? true : z2;
            if (isMain(module)) {
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
                for (Object obj : arrayList) {
                    arrayList2.add(TuplesKt.to(obj, new MainFunctionCandidate(getPackageFqn(obj), getMainFunction(obj))));
                }
                Iterator it2 = CollectionsKt.sortedWith(arrayList2, new MainFunctionDetectionKt$pickMainFunctionFromCandidates$.inlined.sortedBy.1()).iterator();
                do {
                    if (!it2.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it2.next();
                } while (((MainFunctionCandidate) ((Pair) next).getSecond()).getMainFunctionTag() == null);
                Pair pair = (Pair) next;
                Object first = pair != null ? pair.getFirst() : null;
                if (first != null) {
                    mainFunction = getMainFunction(first);
                } else {
                    mainFunction = null;
                }
            } else {
                mainFunction = null;
            }
            if (mainFunction != null || z4 || z3 || suiteFunctionTag != null || (isMain(module) && z5)) {
                Object objGenerateProxyArtifact = generateProxyArtifact(module, mainFunction, suiteFunctionTag, linkedHashMap, (isMain(module) || !z3) ? null : getMainModuleName());
                if (objGenerateProxyArtifact != null) {
                    CollectionUtilKt.putToMultiMap(mapCreateMapBuilder, getArtifactName(objGenerateProxyArtifact), objGenerateProxyArtifact);
                }
            }
            z = z5;
        }
        Collection collectionValues = MapsKt.build(mapCreateMapBuilder).values();
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionValues, 10));
        Iterator it3 = collectionValues.iterator();
        while (it3.hasNext()) {
            arrayList3.add(merge((List) it3.next()));
        }
        return arrayList3;
    }

    Artifact generateProxyArtifact(Module module, String str, String str2, Map<String, ? extends List<String>> map, String str3);

    String getArtifactName(Artifact artifact);

    Iterable<File> getFileList(Module module);

    boolean getHasEffect(Artifact artifact);

    boolean getHasExport(Artifact artifact);

    String getMainFunction(Artifact artifact);

    String getMainModuleName();

    String getPackageFqn(Artifact artifact);

    String getSuiteFunctionTag(TestEnvironment testenvironment);

    String getTestFunctionTag(TestEnvironment testenvironment);

    boolean isMain(Module module);

    Artifact merge(List<? extends Artifact> list);

    TestEnvironment takeTestEnvironmentOwnership(Artifact artifact);
}
