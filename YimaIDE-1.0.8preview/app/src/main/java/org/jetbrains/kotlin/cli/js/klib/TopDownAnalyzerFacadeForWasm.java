package org.jetbrains.kotlin.cli.js.klib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.K1DeprecationKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.context.ModuleContext;
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.incremental.js.IncrementalDataProvider;
import org.jetbrains.kotlin.incremental.js.TranslationResultValue;
import org.jetbrains.kotlin.ir.backend.js.KlibKt;
import org.jetbrains.kotlin.js.analyze.AbstractTopDownAnalyzerFacadeForWeb;
import org.jetbrains.kotlin.library.metadata.KlibMetadataModuleDescriptorFactory;
import org.jetbrains.kotlin.platform.wasm.WasmTarget;
import org.jetbrains.kotlin.resolve.KlibCompilerDeserializationConfiguration;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0014¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/cli/js/klib/TopDownAnalyzerFacadeForWasm;", "Lorg/jetbrains/kotlin/js/analyze/AbstractTopDownAnalyzerFacadeForWeb;", "<init>", "()V", "loadIncrementalCacheMetadata", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;", "incrementalData", "Lorg/jetbrains/kotlin/incremental/js/IncrementalDataProvider;", "moduleContext", "Lorg/jetbrains/kotlin/context/ModuleContext;", "lookupTracker", "Lorg/jetbrains/kotlin/incremental/components/LookupTracker;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "Companion", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class TopDownAnalyzerFacadeForWasm extends AbstractTopDownAnalyzerFacadeForWeb {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Override // org.jetbrains.kotlin.js.analyze.AbstractTopDownAnalyzerFacadeForWeb
    public PackageFragmentProvider loadIncrementalCacheMetadata(IncrementalDataProvider incrementalData, ModuleContext moduleContext, LookupTracker lookupTracker, LanguageVersionSettings languageVersionSettings) {
        incrementalData.getClass();
        moduleContext.getClass();
        lookupTracker.getClass();
        languageVersionSettings.getClass();
        KlibMetadataModuleDescriptorFactory defaultDeserializedDescriptorFactory = KlibKt.getJsFactories().getDefaultDeserializedDescriptorFactory();
        Collection collectionValues = incrementalData.getCompiledPackageParts().values();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionValues, 10));
        Iterator it = collectionValues.iterator();
        while (it.hasNext()) {
            arrayList.add(((TranslationResultValue) it.next()).getMetadata());
        }
        return defaultDeserializedDescriptorFactory.createCachedPackageFragmentProvider(arrayList, moduleContext.getStorageManager(), moduleContext.getModule(), new KlibCompilerDeserializationConfiguration(languageVersionSettings), lookupTracker);
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007b\u0018\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\n\b\u000b\u0012\u0006\b\n0\f8\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/cli/js/klib/TopDownAnalyzerFacadeForWasm$Companion;", Argument.Delimiters.none, "<init>", "()V", "facadeFor", "Lorg/jetbrains/kotlin/cli/js/klib/TopDownAnalyzerFacadeForWasm;", "target", "Lorg/jetbrains/kotlin/platform/wasm/WasmTarget;", "Lkotlin/Deprecated;", "message", K1DeprecationKt.K1_DEPRECATION_WARNING, "level", "Lkotlin/DeprecationLevel;", "ERROR", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {

        @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[WasmTarget.values().length];
                try {
                    iArr[WasmTarget.WASI.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(level = DeprecationLevel.ERROR, message = K1DeprecationKt.K1_DEPRECATION_WARNING)
        public final TopDownAnalyzerFacadeForWasm facadeFor(WasmTarget target) {
            return (target == null ? -1 : WhenMappings.$EnumSwitchMapping$0[target.ordinal()]) == 1 ? TopDownAnalyzerFacadeForWasmWasi.INSTANCE : TopDownAnalyzerFacadeForWasmJs.INSTANCE;
        }

        private Companion() {
        }
    }
}
