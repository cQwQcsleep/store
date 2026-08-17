package org.jetbrains.kotlin.fir.pipeline;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.backend.common.actualizer.IrExtraActualDeclarationExtractor;
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.backend.Fir2IrCommonMemberStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.ir.IrBuiltIns;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.types.IrTypeSystemContext;
import org.jetbrains.kotlin.ir.util.KotlinMangler;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0010\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u009f\u0001\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0018\u0010\u001c\u001a\u0014\u0012\u0004\u0012\u00020\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u00020\u00172\b\b\u0002\u0010\u001f\u001a\u00020 2\u0014\b\u0002\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u00010\u0017¢\u0006\u0004\b#\u0010$¨\u0006%"}, d2 = {"runPlatformCheckers", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/pipeline/SingleModuleFrontendOutput;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "convertToIrAndActualize", "Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "Lorg/jetbrains/kotlin/fir/pipeline/AllModulesFrontendOutput;", "fir2IrExtensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "fir2IrConfiguration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "irGeneratorExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/backend/common/extensions/IrGenerationExtension;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "kotlinBuiltIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "typeSystemContextProvider", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/ir/IrBuiltIns;", "Lorg/jetbrains/kotlin/ir/types/IrTypeSystemContext;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "extraActualDeclarationExtractorsInitializer", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "Lorg/jetbrains/kotlin/backend/common/actualizer/IrExtraActualDeclarationExtractor;", "commonMemberStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;", "irModuleFragmentPostCompute", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "convertToIrAndActualize-MT2kVtw", "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;Ljava/util/Collection;Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;Lkotlin/jvm/functions/Function1;Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;Lkotlin/jvm/functions/Function1;Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/pipeline/Fir2IrActualizedResult;", "org.jetbrains.kotlin:entrypoint"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConvertToIrKt {
    public static Unit a(IrModuleFragment irModuleFragment) {
        irModuleFragment.getClass();
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: convertToIrAndActualize-MT2kVtw, reason: not valid java name */
    public static final Fir2IrActualizedResult m579convertToIrAndActualizeMT2kVtw(List<? extends SingleModuleFrontendOutput> list, Fir2IrExtensions fir2IrExtensions, Fir2IrConfiguration fir2IrConfiguration, Collection<? extends IrGenerationExtension> collection, KotlinMangler.IrMangler irMangler, Fir2IrVisibilityConverter fir2IrVisibilityConverter, KotlinBuiltIns kotlinBuiltIns, Function1<? super IrBuiltIns, ? extends IrTypeSystemContext> function1, IrSpecialAnnotationsProvider irSpecialAnnotationsProvider, Function1<? super Fir2IrComponents, ? extends List<? extends IrExtraActualDeclarationExtractor>> function2, Fir2IrCommonMemberStorage fir2IrCommonMemberStorage, Function1<? super IrModuleFragment, Unit> function3) {
        list.getClass();
        fir2IrExtensions.getClass();
        fir2IrConfiguration.getClass();
        collection.getClass();
        irMangler.getClass();
        fir2IrVisibilityConverter.getClass();
        kotlinBuiltIns.getClass();
        function1.getClass();
        function2.getClass();
        fir2IrCommonMemberStorage.getClass();
        function3.getClass();
        return new Fir2IrPipeline(list, fir2IrExtensions, fir2IrConfiguration, collection, irMangler, fir2IrVisibilityConverter, kotlinBuiltIns, function1, irSpecialAnnotationsProvider, function2, fir2IrCommonMemberStorage, function3).convertToIrAndActualize();
    }

    public static final void runPlatformCheckers(List<SingleModuleFrontendOutput> list, BaseDiagnosticsCollector baseDiagnosticsCollector) {
        list.getClass();
        baseDiagnosticsCollector.getClass();
        SingleModuleFrontendOutput singleModuleFrontendOutput = (SingleModuleFrontendOutput) CollectionsKt.last(list);
        FirSession session = singleModuleFrontendOutput.getSession();
        if (((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(session).getFlag(AnalysisFlags.INSTANCE.getHeaderMode())).booleanValue()) {
            return;
        }
        ScopeSession scopeSession = singleModuleFrontendOutput.getScopeSession();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt.addAll(arrayList, ((SingleModuleFrontendOutput) it.next()).getFir());
        }
        AnalyseKt.runCheckers(session, scopeSession, arrayList, baseDiagnosticsCollector, MppCheckerKind.Platform);
    }
}
