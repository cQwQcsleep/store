package org.jetbrains.kotlin.fir.resolve.transformers;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirImportTrackerComponent;
import org.jetbrains.kotlin.fir.FirImportTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponent;
import org.jetbrains.kotlin.fir.FirLookupTrackerComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.builder.FirResolvedImportBuilder;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u0019\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\tJ)\u0010\f\u001a\u0002H\r\"\b\b\u0000\u0010\r*\u00020\u000e2\u0006\u0010\u000f\u001a\u0002H\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002H\u0016¢\u0006\u0002\u0010\u0011J\u001a\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002H\u0016J\u0018\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\u001d2\u0006\u0010!\u001a\u00020\u0019H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u001b\u001a\u00020\u001c*\u00020\u001d8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001e¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirImportResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirAbstractTreeTransformer;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "phase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "symbolProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "currentFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "transformFile", "file", "transformImport", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "import", "isAcceptable", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "(Lorg/jetbrains/kotlin/name/FqName;)Z", "transformImportForFqName", "fqName", "delegate", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirImportResolveTransformer extends FirAbstractTreeTransformer<Object> {
    private FirFile currentFile;
    private final FirSession session;
    private final FirSymbolProvider symbolProvider;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirImportResolveTransformer(FirSession firSession, FirResolvePhase firResolvePhase) {
        super(firResolvePhase);
        firSession.getClass();
        firResolvePhase.getClass();
        this.session = firSession;
        this.symbolProvider = FirSymbolProviderKt.getSymbolProvider(firSession);
    }

    private final FirImport transformImportForFqName(FqName fqName, FirImport delegate) {
        PackageAndClass packageAndClassFindLongestExistingPackage = ImportUtilsKt.findLongestExistingPackage(this.symbolProvider, fqName);
        FqName packageFqName = packageAndClassFindLongestExistingPackage.getPackageFqName();
        FqName relativeClassFqName = packageAndClassFindLongestExistingPackage.getRelativeClassFqName();
        FirResolvedImportBuilder firResolvedImportBuilder = new FirResolvedImportBuilder();
        firResolvedImportBuilder.setDelegate(delegate);
        firResolvedImportBuilder.setPackageFqName(packageFqName);
        firResolvedImportBuilder.setRelativeParentClassName(relativeClassFqName);
        return firResolvedImportBuilder.build();
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public final FirSession getSession() {
        return this.session;
    }

    public boolean isAcceptable(FqName fqName) {
        fqName.getClass();
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirAbstractTreeTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, Object data) {
        element.getClass();
        return element;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirAbstractPhaseTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFile transformFile(FirFile file, Object data) {
        file.getClass();
        checkSessionConsistency(file);
        try {
            FirFile firFile = this.currentFile;
            this.currentFile = file;
            try {
                file.transformChildren(this, null);
                return file;
            } finally {
                this.currentFile = firFile;
            }
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(file.getModuleData().getSession()).handleExceptionOnFileAnalysis(file, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirImport transformImport(FirImport firImport, Object data) {
        FirLookupTrackerComponent lookupTracker;
        FirImportTrackerComponent importTracker;
        String strAsString;
        firImport.getClass();
        FqName importedFqName = firImport.getImportedFqName();
        if (importedFqName != null) {
            if (importedFqName.isRoot()) {
                importedFqName = null;
            }
            if (importedFqName != null && isAcceptable(importedFqName)) {
                FirFile firFile = this.currentFile;
                if (firFile != null && (importTracker = FirImportTrackerComponentKt.getImportTracker(this.session)) != null) {
                    FqName importedFqName2 = firImport.getImportedFqName();
                    if (importedFqName2 == null || (strAsString = importedFqName2.asString()) == null) {
                        strAsString = null;
                    } else if (firImport.getIsAllUnder()) {
                        strAsString = strAsString.concat(".*");
                    }
                    KtSourceFile sourceFile = firFile.getSourceFile();
                    FirImportTrackerComponentKt.reportImportDirectives(importTracker, sourceFile != null ? sourceFile.getPath() : null, strAsString);
                }
                if (firImport.getIsAllUnder()) {
                    return transformImportForFqName(importedFqName, firImport);
                }
                FirFile firFile2 = this.currentFile;
                if (firFile2 != null && (lookupTracker = FirLookupTrackerComponentKt.getLookupTracker(this.session)) != null) {
                    FirLookupTrackerComponentKt.recordFqNameLookup(lookupTracker, importedFqName, firImport.getSource(), firFile2.getSource());
                }
                return transformImportForFqName(importedFqName.parent(), firImport);
            }
        }
        return firImport;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FirImportResolveTransformer(FirSession firSession) {
        this(firSession, FirResolvePhase.IMPORTS);
        firSession.getClass();
    }
}
