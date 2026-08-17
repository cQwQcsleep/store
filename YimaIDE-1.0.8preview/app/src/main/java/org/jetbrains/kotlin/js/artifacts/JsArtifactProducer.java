package org.jetbrains.kotlin.js.artifacts;

import com.intellij.util.containers.UtilKt;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.js.config.JsGenerationGranularity;
import org.jetbrains.kotlin.library.components.KlibMetadataConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u00042\u00020\u0005:\u0001\u0013J\u001d\u0010\u0006\u001a\u00028\u00022\u0006\u0010\u0007\u001a\u00028\u00002\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\tJ3\u0010\n\u001a \u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00020\f\u0012\u0004\u0012\u00028\u00030\u000b2\u0006\u0010\b\u001a\u00028\u0000H&¢\u0006\u0002\u0010\rJ$\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/js/artifacts/JsArtifactProducer;", "Module", "File", "Artifact", "TestEnvironment", "", "singleModuleToArtifact", KlibMetadataConstants.KLIB_MODULE_METADATA_FILE_NAME, "mainModule", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "makePerFileGenerator", "Lorg/jetbrains/kotlin/js/artifacts/PerFileGenerator;", "Lorg/jetbrains/kotlin/js/artifacts/JsArtifactProducer$ArtifactModules;", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/js/artifacts/PerFileGenerator;", "generateArtifacts", "", "modules", "granularity", "Lorg/jetbrains/kotlin/js/config/JsGenerationGranularity;", "ArtifactModules", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface JsArtifactProducer<Module, File, Artifact, TestEnvironment> {

    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JsGenerationGranularity.values().length];
            try {
                iArr[JsGenerationGranularity.WHOLE_PROGRAM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[JsGenerationGranularity.PER_MODULE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[JsGenerationGranularity.PER_FILE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    default List<Artifact> generateArtifacts(List<? extends Module> modules, JsGenerationGranularity granularity) {
        modules.getClass();
        granularity.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[granularity.ordinal()];
        if (i == 1 || i == 2) {
            Object objLast = CollectionsKt.last(modules);
            List<? extends Module> list = modules;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(singleModuleToArtifact(it.next(), objLast));
            }
            return arrayList;
        }
        if (i != 3) {
            bu8.a();
            return null;
        }
        PerFileGenerator perFileGeneratorMakePerFileGenerator = makePerFileGenerator(CollectionsKt.last(modules));
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        for (Artifact artifact : perFileGeneratorMakePerFileGenerator.generatePerFileArtifacts(modules)) {
            Object objComponent1 = artifact.component1();
            Object objComponent2 = artifact.component2();
            listCreateListBuilder.add(objComponent1);
            UtilKt.addIfNotNull(listCreateListBuilder, objComponent2);
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    PerFileGenerator<Module, File, ArtifactModules<Artifact>, TestEnvironment> makePerFileGenerator(Module mainModule);

    Artifact singleModuleToArtifact(Module module, Module mainModule);

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0004\u0010\u00012\u00020\u0002B\u001b\u0012\u0006\u0010\u0003\u001a\u00028\u0004\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00018\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u000e\u0010\u000b\u001a\u00028\u0004HÆ\u0003¢\u0006\u0002\u0010\bJ\u0010\u0010\f\u001a\u0004\u0018\u00018\u0004HÆ\u0003¢\u0006\u0002\u0010\bJ*\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00040\u00002\b\b\u0002\u0010\u0003\u001a\u00028\u00042\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00018\u0004HÆ\u0001¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0013\u0010\u0003\u001a\u00028\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0004\u001a\u0004\u0018\u00018\u0004¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/js/artifacts/JsArtifactProducer$ArtifactModules;", "Artifact", "", "mainModule", "exportModule", "<init>", "(Ljava/lang/Object;Ljava/lang/Object;)V", "getMainModule", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getExportModule", "component1", "component2", "copy", "(Ljava/lang/Object;Ljava/lang/Object;)Lorg/jetbrains/kotlin/js/artifacts/JsArtifactProducer$ArtifactModules;", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:js.config"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class ArtifactModules<Artifact> {
        private final Artifact exportModule;
        private final Artifact mainModule;

        public /* synthetic */ ArtifactModules(Object obj, Object obj2, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(obj, (i & 2) != 0 ? null : obj2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ ArtifactModules copy$default(ArtifactModules artifactModules, Object obj, Object obj2, int i, Object obj3) {
            if ((i & 1) != 0) {
                obj = artifactModules.mainModule;
            }
            if ((i & 2) != 0) {
                obj2 = artifactModules.exportModule;
            }
            return artifactModules.copy(obj, obj2);
        }

        public final Artifact component1() {
            return this.mainModule;
        }

        public final Artifact component2() {
            return this.exportModule;
        }

        public final ArtifactModules<Artifact> copy(Artifact mainModule, Artifact exportModule) {
            return new ArtifactModules<>(mainModule, exportModule);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ArtifactModules)) {
                return false;
            }
            ArtifactModules artifactModules = (ArtifactModules) other;
            return Intrinsics.areEqual(this.mainModule, artifactModules.mainModule) && Intrinsics.areEqual(this.exportModule, artifactModules.exportModule);
        }

        public final Artifact getExportModule() {
            return this.exportModule;
        }

        public final Artifact getMainModule() {
            return this.mainModule;
        }

        public int hashCode() {
            Artifact artifact = this.mainModule;
            int iHashCode = (artifact == null ? 0 : artifact.hashCode()) * 31;
            Artifact artifact2 = this.exportModule;
            return iHashCode + (artifact2 != null ? artifact2.hashCode() : 0);
        }

        public String toString() {
            return "ArtifactModules(mainModule=" + this.mainModule + ", exportModule=" + this.exportModule + ')';
        }

        public ArtifactModules(Artifact artifact, Artifact artifact2) {
            this.mainModule = artifact;
            this.exportModule = artifact2;
        }
    }
}
