package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.optimization.FirAggressivePruningProcessor;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirBodyResolveProcessor;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirImplicitTypeBodyResolveProcessor;
import org.jetbrains.kotlin.fir.resolve.transformers.contracts.FirContractResolveProcessor;
import org.jetbrains.kotlin.fir.resolve.transformers.mpp.FirExpectActualMatcherProcessor;
import org.jetbrains.kotlin.fir.resolve.transformers.plugin.FirAnnotationArgumentsProcessor;
import org.jetbrains.kotlin.fir.resolve.transformers.plugin.FirCompanionGenerationProcessor;
import org.jetbrains.kotlin.fir.resolve.transformers.plugin.FirCompilerRequiredAnnotationsResolveProcessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001aD\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\b0\u0001\"\b\b\u0000\u0010\b*\u00020\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u001d\u0010\t\u001a\u0019\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\b0\n¢\u0006\u0002\b\fH\u0082\b\u001a\u001a\u0010\r\u001a\u00020\u0002*\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u000e"}, d2 = {"createAllCompilerResolveProcessors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirResolveProcessor;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "createAllResolveProcessors", "T", "creator", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "Lkotlin/ExtensionFunctionType;", "createCompilerProcessorByPhase", "org.jetbrains.kotlin:resolve"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTotalResolveProcessorKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FirResolvePhase.values().length];
            try {
                iArr[FirResolvePhase.RAW_FIR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirResolvePhase.COMPILER_REQUIRED_ANNOTATIONS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FirResolvePhase.COMPANION_GENERATION.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FirResolvePhase.IMPORTS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[FirResolvePhase.SUPER_TYPES.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[FirResolvePhase.SEALED_CLASS_INHERITORS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[FirResolvePhase.TYPES.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[FirResolvePhase.STATUS.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[FirResolvePhase.CONTRACTS.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[FirResolvePhase.IMPLICIT_TYPES_BODY_RESOLVE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[FirResolvePhase.CONSTANT_EVALUATION.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[FirResolvePhase.ANNOTATION_ARGUMENTS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[FirResolvePhase.BODY_RESOLVE.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[FirResolvePhase.EXPECT_ACTUAL_MATCHING.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final List<FirResolveProcessor> createAllCompilerResolveProcessors(FirSession firSession, ScopeSession scopeSession) {
        firSession.getClass();
        if (scopeSession == null) {
            scopeSession = new ScopeSession();
        }
        EnumEntries<FirResolvePhase> entries = FirResolvePhase.getEntries();
        ArrayList arrayList = new ArrayList();
        for (Object obj : entries) {
            if (!((FirResolvePhase) obj).getNoProcessor()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(createCompilerProcessorByPhase((FirResolvePhase) it.next(), firSession, scopeSession));
        }
        return arrayList2;
    }

    public static /* synthetic */ List createAllCompilerResolveProcessors$default(FirSession firSession, ScopeSession scopeSession, int i, Object obj) {
        if ((i & 2) != 0) {
            scopeSession = null;
        }
        return createAllCompilerResolveProcessors(firSession, scopeSession);
    }

    public static final FirResolveProcessor createCompilerProcessorByPhase(FirResolvePhase firResolvePhase, FirSession firSession, ScopeSession scopeSession) {
        firResolvePhase.getClass();
        firSession.getClass();
        scopeSession.getClass();
        switch (WhenMappings.$EnumSwitchMapping$0[firResolvePhase.ordinal()]) {
            case 1:
                w01.a("Raw FIR building phase does not have a transformer");
                return null;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                return new FirCompilerRequiredAnnotationsResolveProcessor(firSession, scopeSession);
            case 3:
                return new FirCompanionGenerationProcessor(firSession, scopeSession);
            case 4:
                return new FirImportResolveProcessor(firSession, scopeSession);
            case 5:
                return new FirSupertypeResolverProcessor(firSession, scopeSession);
            case 6:
                return new FirSealedClassInheritorsProcessor(firSession, scopeSession);
            case 7:
                return new FirTypeResolveProcessor(firSession, scopeSession);
            case 8:
                return new FirStatusResolveProcessor(firSession, scopeSession);
            case 9:
                return new FirContractResolveProcessor(firSession, scopeSession);
            case 10:
                return new FirImplicitTypeBodyResolveProcessor(firSession, scopeSession);
            case 11:
                return new FirConstantEvaluationProcessor(firSession, scopeSession);
            case 12:
                return new FirAnnotationArgumentsProcessor(firSession, scopeSession);
            case 13:
                FirBodyResolveProcessor firBodyResolveProcessor = new FirBodyResolveProcessor(firSession, scopeSession);
                return ((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).getFlag(AnalysisFlags.INSTANCE.getHeaderMode())).booleanValue() ? new FirAggressivePruningProcessor(firBodyResolveProcessor) : firBodyResolveProcessor;
            case 14:
                return new FirExpectActualMatcherProcessor(firSession, scopeSession);
            default:
                bu8.a();
                return null;
        }
    }
}
