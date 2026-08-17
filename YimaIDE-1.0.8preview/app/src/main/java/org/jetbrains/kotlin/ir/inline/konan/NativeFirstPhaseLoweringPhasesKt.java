package org.jetbrains.kotlin.ir.inline.konan;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.backend.common.LoweringContext;
import org.jetbrains.kotlin.backend.common.PreSerializationLoweringContext;
import org.jetbrains.kotlin.backend.common.lower.UpgradeCallableReferences;
import org.jetbrains.kotlin.backend.common.phaser.PhaseBuildersKt;
import org.jetbrains.kotlin.backend.common.phaser.PhaseFactoriesKt;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.phaser.NamedCompilerPhase;
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment;
import org.jetbrains.kotlin.ir.inline.CommonLoweringPhasesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a&\u0010\n\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00050\u000b2\u0006\u0010\r\u001a\u00020\u000e\" \u0010\u0004\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"/\u0010\u0007\u001a#\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u0002\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"createUpgradeCallableReferencesPhase", "Lorg/jetbrains/kotlin/backend/common/lower/UpgradeCallableReferences;", "context", "Lorg/jetbrains/kotlin/backend/common/LoweringContext;", "upgradeCallableReferencesPhase", "Lorg/jetbrains/kotlin/config/phaser/NamedCompilerPhase;", "Lorg/jetbrains/kotlin/ir/declarations/IrModuleFragment;", "assertionWrapperPhase", "Lkotlin/ParameterName;", "name", "nativeLoweringsOfTheFirstPhase", "", "Lorg/jetbrains/kotlin/backend/common/PreSerializationLoweringContext;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "org.jetbrains.kotlin:ir.inline"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class NativeFirstPhaseLoweringPhasesKt {
    private static final NamedCompilerPhase<LoweringContext, IrModuleFragment, IrModuleFragment> upgradeCallableReferencesPhase = PhaseBuildersKt.makeIrModulePhase$default(NativeFirstPhaseLoweringPhasesKt$upgradeCallableReferencesPhase$1.INSTANCE, "UpgradeCallableReferences", (Set) null, (Set) null, (Set) null, 28, (Object) null);
    private static final NamedCompilerPhase<LoweringContext, IrModuleFragment, IrModuleFragment> assertionWrapperPhase = PhaseBuildersKt.makeIrModulePhase$default(NativeFirstPhaseLoweringPhasesKt$assertionWrapperPhase$1.INSTANCE, "AssertionWrapperLowering", (Set) null, (Set) null, (Set) null, 28, (Object) null);

    /* JADX INFO: Access modifiers changed from: private */
    public static final UpgradeCallableReferences createUpgradeCallableReferencesPhase(LoweringContext loweringContext) {
        return new UpgradeCallableReferences(loweringContext, false, false, false, false, false, false, false, 254, (DefaultConstructorMarker) null);
    }

    public static final List<NamedCompilerPhase<PreSerializationLoweringContext, IrModuleFragment, IrModuleFragment>> nativeLoweringsOfTheFirstPhase(LanguageVersionSettings languageVersionSettings) {
        languageVersionSettings.getClass();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        if (languageVersionSettings.supportsFeature(LanguageFeature.IrRichCallableReferencesInKlibs)) {
            listCreateListBuilder.add(NativeFirstPhaseLoweringPhasesKt$nativeLoweringsOfTheFirstPhase$phases$1$1.INSTANCE);
        }
        if (languageVersionSettings.supportsFeature(LanguageFeature.IrIntraModuleInlinerBeforeKlibSerialization)) {
            listCreateListBuilder.add(NativeFirstPhaseLoweringPhasesKt$nativeLoweringsOfTheFirstPhase$phases$1$2.INSTANCE);
        }
        CollectionsKt.addAll(listCreateListBuilder, CommonLoweringPhasesKt.loweringsOfTheFirstPhase(languageVersionSettings));
        Function1[] function1Arr = (Function1[]) CollectionsKt.build(listCreateListBuilder).toArray(new Function1[0]);
        return PhaseFactoriesKt.createModulePhases((Function1[]) Arrays.copyOf(function1Arr, function1Arr.length));
    }
}
