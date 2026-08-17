package org.jetbrains.kotlin.fir;

import java.io.File;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticUtils;
import org.jetbrains.kotlin.diagnostics.PsiDiagnosticUtils;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.incremental.components.ICFileMappingTracker;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.incremental.components.Position;
import org.jetbrains.kotlin.incremental.components.ScopeKind;
import org.jetbrains.kotlin.utils.SmartList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t¢\u0006\u0004\b\f\u0010\rJ2\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\nH\u0016J,\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000b2\b\u0010\u0017\u001a\u0004\u0018\u00010\n2\b\u0010\u0018\u001a\u0004\u0018\u00010\nH\u0016J\u0014\u0010\u001a\u001a\u00020\u00132\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001cH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/IncrementalPassThroughLookupTrackerComponent;", "Lorg/jetbrains/kotlin/fir/FirLookupTrackerComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "lookupTracker", "Lorg/jetbrains/kotlin/incremental/components/LookupTracker;", "fileMappingTracker", "Lorg/jetbrains/kotlin/incremental/components/ICFileMappingTracker;", "sourceToFilePath", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/KtSourceElement;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/incremental/components/LookupTracker;Lorg/jetbrains/kotlin/incremental/components/ICFileMappingTracker;Lkotlin/jvm/functions/Function1;)V", "requiresPosition", Argument.Delimiters.none, "sourceToFilePathsCache", "Ljava/util/concurrent/ConcurrentHashMap;", "recordLookup", Argument.Delimiters.none, ModuleXmlParser.NAME, "inScopes", Argument.Delimiters.none, "source", "fileSource", "inScope", "recordDirtyDeclaration", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IncrementalPassThroughLookupTrackerComponent extends FirLookupTrackerComponent {
    private final ICFileMappingTracker fileMappingTracker;
    private final LookupTracker lookupTracker;
    private final boolean requiresPosition;
    private final FirSession session;
    private final Function1<KtSourceElement, String> sourceToFilePath;
    private final ConcurrentHashMap<KtSourceElement, String> sourceToFilePathsCache;

    public IncrementalPassThroughLookupTrackerComponent(FirSession firSession, LookupTracker lookupTracker, ICFileMappingTracker iCFileMappingTracker, Function1<? super KtSourceElement, String> function1) {
        firSession.getClass();
        lookupTracker.getClass();
        function1.getClass();
        this.session = firSession;
        this.lookupTracker = lookupTracker;
        this.fileMappingTracker = iCFileMappingTracker;
        this.sourceToFilePath = function1;
        this.requiresPosition = lookupTracker.getRequiresPosition();
        this.sourceToFilePathsCache = new ConcurrentHashMap<>();
    }

    @Override // org.jetbrains.kotlin.fir.FirLookupTrackerComponent
    public void recordDirtyDeclaration(FirBasedSymbol<?> symbol) {
        FirFile containingFile;
        KtSourceFile sourceFile;
        String path;
        symbol.getClass();
        if (this.fileMappingTracker == null || (containingFile = FirProviderUtilsKt.getContainingFile(FirProviderKt.getFirProvider(this.session), symbol)) == null || (sourceFile = containingFile.getSourceFile()) == null || (path = sourceFile.getPath()) == null) {
            return;
        }
        this.fileMappingTracker.recordSourceReferencedByCompilerPlugin(new File(path));
    }

    @Override // org.jetbrains.kotlin.fir.FirLookupTrackerComponent
    public void recordLookup(String name, Iterable<String> inScopes, KtSourceElement source, KtSourceElement fileSource) {
        Position no_position;
        name.getClass();
        inScopes.getClass();
        if (fileSource == null) {
            fileSource = source instanceof KtPsiSourceElement ? (KtPsiSourceElement) source : null;
            if (fileSource == null) {
                return;
            }
        }
        ConcurrentHashMap<KtSourceElement, String> concurrentHashMap = this.sourceToFilePathsCache;
        String str = concurrentHashMap.get(fileSource);
        if (str == null) {
            str = (String) this.sourceToFilePath.invoke(fileSource);
            if (str == null) {
                return;
            }
            String strPutIfAbsent = concurrentHashMap.putIfAbsent(fileSource, str);
            if (strPutIfAbsent != null) {
                str = strPutIfAbsent;
            }
        }
        String str2 = str;
        if (this.requiresPosition && source != null && (source instanceof KtPsiSourceElement)) {
            KtPsiSourceElement ktPsiSourceElement = (KtPsiSourceElement) source;
            PsiDiagnosticUtils.LineAndColumn lineAndColumnInPsiFile = DiagnosticUtils.getLineAndColumnInPsiFile(ktPsiSourceElement.getPsi().getContainingFile(), ktPsiSourceElement.getPsi().getTextRange());
            no_position = new Position(lineAndColumnInPsiFile.getLine(), lineAndColumnInPsiFile.getColumn());
        } else {
            no_position = Position.Companion.getNO_POSITION();
        }
        Position position = no_position;
        Iterator<String> it = inScopes.iterator();
        while (it.hasNext()) {
            this.lookupTracker.record(str2, position, it.next(), ScopeKind.PACKAGE, name);
        }
    }

    @Override // org.jetbrains.kotlin.fir.FirLookupTrackerComponent
    public void recordLookup(String name, String inScope, KtSourceElement source, KtSourceElement fileSource) {
        name.getClass();
        inScope.getClass();
        recordLookup(name, (Iterable<String>) new SmartList(inScope), source, fileSource);
    }
}
