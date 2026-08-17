package org.jetbrains.kotlin.fir.pipeline;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function2;
import kotlin.text.Charsets;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.KtSourceFileLinesMapping;
import org.jetbrains.kotlin.KtSourceFileLinesMappingKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.impl.BaseDiagnosticsCollector;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.builder.PsiRawFirBuilder;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.lightTree.LightTree2Fir;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.providers.impl.FirProviderImpl;
import org.jetbrains.kotlin.fir.session.SourcesToPathsMapper;
import org.jetbrains.kotlin.fir.session.SourcesToPathsMapperKt;
import org.jetbrains.kotlin.psi.KtFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001aD\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u001a\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\n\u001a\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0005\u001a$\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00012\u0006\u0010\u0007\u001a\u00020\u0013\u001a$\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u0013\u001a@\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u00132\u001a\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\n¨\u0006\u0018"}, d2 = {"buildFirViaLightTree", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/FirSession;", "files", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtSourceFile;", "diagnosticsReporter", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reportFilesAndLines", "Lkotlin/Function2;", Argument.Delimiters.none, Argument.Delimiters.none, "buildFirFromKtFiles", "ktFiles", "Lorg/jetbrains/kotlin/psi/KtFile;", "buildResolveAndCheckFirFromKtFiles", "Lorg/jetbrains/kotlin/fir/pipeline/SingleModuleFrontendOutput;", "session", "Lorg/jetbrains/kotlin/diagnostics/impl/BaseDiagnosticsCollector;", "resolveAndCheckFir", "firFiles", "buildResolveAndCheckFirViaLightTree", "countFilesAndLines", "org.jetbrains.kotlin:entrypoint"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUtilsKt {
    public static final List<FirFile> buildFirFromKtFiles(FirSession firSession, Collection<? extends KtFile> collection) {
        firSession.getClass();
        collection.getClass();
        FirProvider firProvider = FirProviderKt.getFirProvider(firSession);
        firProvider.getClass();
        FirProviderImpl firProviderImpl = (FirProviderImpl) firProvider;
        PsiRawFirBuilder psiRawFirBuilder = new PsiRawFirBuilder(firSession, firProviderImpl.getKotlinScopeProvider(), null, 4, null);
        Collection<? extends KtFile> collection2 = collection;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        Iterator<T> it = collection2.iterator();
        while (it.hasNext()) {
            FirFile firFileBuildFirFile = psiRawFirBuilder.buildFirFile((KtFile) it.next());
            firProviderImpl.recordFile(firFileBuildFirFile);
            arrayList.add(firFileBuildFirFile);
        }
        return arrayList;
    }

    public static final List<FirFile> buildFirViaLightTree(FirSession firSession, Collection<? extends KtSourceFile> collection, DiagnosticReporter diagnosticReporter, Function2<? super Integer, ? super Integer, Unit> function2) {
        firSession.getClass();
        collection.getClass();
        FirProvider firProvider = FirProviderKt.getFirProvider(firSession);
        firProvider.getClass();
        FirProviderImpl firProviderImpl = (FirProviderImpl) firProvider;
        SourcesToPathsMapper sourcesToPathsMapper = SourcesToPathsMapperKt.getSourcesToPathsMapper(firSession);
        LightTree2Fir lightTree2Fir = new LightTree2Fir(firSession, firProviderImpl.getKotlinScopeProvider(), diagnosticReporter);
        int linesCount = 0;
        boolean z = function2 != null;
        Collection<? extends KtSourceFile> collection2 = collection;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(collection2, 10));
        for (KtSourceFile ktSourceFile : collection2) {
            InputStreamReader inputStreamReader = new InputStreamReader(ktSourceFile.getContentsAsStream(), Charsets.UTF_8);
            try {
                Pair<CharSequence, KtSourceFileLinesMapping> sourceFileWithMapping = KtSourceFileLinesMappingKt.readSourceFileWithMapping(inputStreamReader);
                CloseableKt.closeFinally(inputStreamReader, (Throwable) null);
                CharSequence charSequence = (CharSequence) sourceFileWithMapping.component1();
                KtSourceFileLinesMapping ktSourceFileLinesMapping = (KtSourceFileLinesMapping) sourceFileWithMapping.component2();
                if (z) {
                    linesCount += ktSourceFileLinesMapping.getLinesCount();
                }
                FirFile firFileBuildFirFile = lightTree2Fir.buildFirFile(charSequence, ktSourceFile, ktSourceFileLinesMapping);
                firProviderImpl.recordFile(firFileBuildFirFile);
                KtSourceElement source = firFileBuildFirFile.getSource();
                source.getClass();
                String path = ktSourceFile.getPath();
                if (path == null) {
                    path = ktSourceFile.getName();
                }
                sourcesToPathsMapper.registerFileSource(source, path);
                arrayList.add(firFileBuildFirFile);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(inputStreamReader, th);
                    throw th2;
                }
            }
        }
        if (function2 != null) {
            function2.invoke(Integer.valueOf(collection.size()), Integer.valueOf(linesCount));
        }
        return arrayList;
    }

    public static final SingleModuleFrontendOutput buildResolveAndCheckFirFromKtFiles(FirSession firSession, List<? extends KtFile> list, BaseDiagnosticsCollector baseDiagnosticsCollector) {
        firSession.getClass();
        list.getClass();
        baseDiagnosticsCollector.getClass();
        return resolveAndCheckFir(firSession, buildFirFromKtFiles(firSession, list), baseDiagnosticsCollector);
    }

    public static final SingleModuleFrontendOutput buildResolveAndCheckFirViaLightTree(FirSession firSession, Collection<? extends KtSourceFile> collection, BaseDiagnosticsCollector baseDiagnosticsCollector, Function2<? super Integer, ? super Integer, Unit> function2) {
        firSession.getClass();
        collection.getClass();
        baseDiagnosticsCollector.getClass();
        return resolveAndCheckFir(firSession, buildFirViaLightTree(firSession, collection, baseDiagnosticsCollector, function2), baseDiagnosticsCollector);
    }

    public static final SingleModuleFrontendOutput resolveAndCheckFir(FirSession firSession, List<? extends FirFile> list, BaseDiagnosticsCollector baseDiagnosticsCollector) {
        firSession.getClass();
        list.getClass();
        baseDiagnosticsCollector.getClass();
        Pair<ScopeSession, List<FirFile>> pairRunResolution = AnalyseKt.runResolution(firSession, list);
        ScopeSession scopeSession = (ScopeSession) pairRunResolution.component1();
        List list2 = (List) pairRunResolution.component2();
        if (!((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).getFlag(AnalysisFlags.INSTANCE.getHeaderMode())).booleanValue()) {
            AnalyseKt.runCheckers(firSession, scopeSession, list2, baseDiagnosticsCollector, MppCheckerKind.Common);
        }
        return new SingleModuleFrontendOutput(firSession, scopeSession, list2);
    }
}
