package org.jetbrains.kotlin.cli.js.klib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.context.ModuleContext;
import org.jetbrains.kotlin.descriptors.PackageFragmentProvider;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.incremental.js.IncrementalDataProvider;
import org.jetbrains.kotlin.incremental.js.TranslationResultValue;
import org.jetbrains.kotlin.ir.backend.js.KlibKt;
import org.jetbrains.kotlin.js.analyze.AbstractTopDownAnalyzerFacadeForWeb;
import org.jetbrains.kotlin.js.resolve.JsPlatformAnalyzerServices;
import org.jetbrains.kotlin.library.metadata.KlibMetadataModuleDescriptorFactory;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.platform.js.JsPlatforms;
import org.jetbrains.kotlin.resolve.KlibCompilerDeserializationConfiguration;
import org.jetbrains.kotlin.resolve.PlatformDependentAnalyzerServices;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0014R\u0014\u0010\u0004\u001a\u00020\u0005X\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0094\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/cli/js/klib/TopDownAnalyzerFacadeForJSIR;", "Lorg/jetbrains/kotlin/js/analyze/AbstractTopDownAnalyzerFacadeForWeb;", "<init>", "()V", "analyzerServices", "Lorg/jetbrains/kotlin/resolve/PlatformDependentAnalyzerServices;", "getAnalyzerServices", "()Lorg/jetbrains/kotlin/resolve/PlatformDependentAnalyzerServices;", "platform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "getPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "loadIncrementalCacheMetadata", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentProvider;", "incrementalData", "Lorg/jetbrains/kotlin/incremental/js/IncrementalDataProvider;", "moduleContext", "Lorg/jetbrains/kotlin/context/ModuleContext;", "lookupTracker", "Lorg/jetbrains/kotlin/incremental/components/LookupTracker;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "org.jetbrains.kotlin:cli-js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TopDownAnalyzerFacadeForJSIR extends AbstractTopDownAnalyzerFacadeForWeb {
    public static final TopDownAnalyzerFacadeForJSIR INSTANCE = new TopDownAnalyzerFacadeForJSIR();
    private static final PlatformDependentAnalyzerServices analyzerServices = JsPlatformAnalyzerServices.INSTANCE;
    private static final TargetPlatform platform = JsPlatforms.INSTANCE.getDefaultJsPlatform();

    private TopDownAnalyzerFacadeForJSIR() {
    }

    @Override // org.jetbrains.kotlin.js.analyze.AbstractTopDownAnalyzerFacadeForWeb
    public PlatformDependentAnalyzerServices getAnalyzerServices() {
        return analyzerServices;
    }

    @Override // org.jetbrains.kotlin.js.analyze.AbstractTopDownAnalyzerFacadeForWeb
    public TargetPlatform getPlatform() {
        return platform;
    }

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
}
