package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import defpackage.oh3;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.FirSourceUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirImportsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirDeprecationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.ConeDiagnosticToFirDiagnosticKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.declarations.ImplicitReceiverUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.resolve.transformers.ImportUtilsKt;
import org.jetbrains.kotlin.fir.resolve.transformers.PackageResolutionResult;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.expressions.OperatorConventions;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u00019B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011J-\u0010\u0012\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011J-\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011J!\u0010\u0014\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u0016H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0017J3\u0010\u0018\u001a\u00020\u00072\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00100\u001aH\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001bJ-\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u001dH\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u001eJ\u001d\u0010\u001f\u001a\u00020\u0015*\u00020\u001dH\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010 J#\u0010!\u001a\b\u0012\u0002\b\u0003\u0018\u00010\"*\u00020#H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010$J\u001f\u0010%\u001a\u0004\u0018\u00010&*\u00020#H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010'J9\u0010(\u001a\u00020\u0015*\u00020&2\u0006\u0010)\u001a\u00020*2\u0012\u0010+\u001a\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00150,H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010.J%\u0010/\u001a\u000200*\u00020&2\u0006\u0010)\u001a\u00020*H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00101JJ\u00102\u001a\u0002002\f\u00103\u001a\b\u0012\u0004\u0012\u0002040\u001a2\u0006\u0010)\u001a\u00020*2\u0018\b\u0004\u00105\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u000306\u0012\u0004\u0012\u00020\u00150,H\u0082\bR\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00107J-\u00108\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0011¨\u0006:"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFileChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFile;)V", "checkAllUnderFromEnumEntry", "import", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirImport;)V", "checkAllUnderFromObject", "checkCanBeImported", "isVisible", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "checkConflictingImports", "imports", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Ljava/util/List;)V", "checkOperatorRename", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;)V", "resolvesToClass", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;)Z", "resolveToClassLike", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/name/ClassId;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/name/ClassId;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "resolveToClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/name/ClassId;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "hasFunction", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "predicate", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/name/Name;Lkotlin/jvm/functions/Function1;)Z", "getImportStatusOfCallableMembers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/name/Name;)Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus;", "getImportStatus", "scopes", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "isApplicable", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/List;Lorg/jetbrains/kotlin/name/Name;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus;", "checkImportApiStatus", "ImportStatus", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirImportsChecker extends FirDeclarationChecker<FirFile> {
    public static final FirImportsChecker INSTANCE = new FirImportsChecker();

    private FirImportsChecker() {
        super(MppCheckerKind.Common);
    }

    public static Unit b(Ref.BooleanRef booleanRef, Function1 function1, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (!booleanRef.element) {
            booleanRef.element = ((Boolean) function1.invoke(firNamedFunctionSymbol)).booleanValue();
        }
        return Unit.INSTANCE;
    }

    public static boolean c(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return firNamedFunctionSymbol.getResolvedStatus().isOperator();
    }

    private final void checkAllUnderFromEnumEntry(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirImport firImport) {
        FirRegularClassSymbol firRegularClassSymbolResolveToClass;
        FqName importedFqName = firImport.getImportedFqName();
        if (importedFqName == null || importedFqName.isRoot() || importedFqName.parent().isRoot() || (firRegularClassSymbolResolveToClass = resolveToClass(checkerContext, ClassId.Companion.topLevel(importedFqName.parent()))) == null || firRegularClassSymbolResolveToClass.getClassKind() != ClassKind.ENUM_CLASS) {
            return;
        }
        List<FirEnumEntrySymbol> listCollectEnumEntries = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.collectEnumEntries(firRegularClassSymbolResolveToClass, checkerContext.getSession());
        if ((listCollectEnumEntries instanceof Collection) && listCollectEnumEntries.isEmpty()) {
            return;
        }
        Iterator<T> it = listCollectEnumEntries.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((FirEnumEntrySymbol) it.next()).getCallableId().getCallableName(), importedFqName.shortName())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firImport.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCANNOT_ALL_UNDER_IMPORT_FROM_SINGLETON(), (Object) firRegularClassSymbolResolveToClass.getClassId().getShortClassName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            }
        }
    }

    private final void checkAllUnderFromObject(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirImport firImport) {
        FqName importedFqName = firImport.getImportedFqName();
        if (importedFqName == null || importedFqName.isRoot()) {
            return;
        }
        PackageResolutionResult packageResolutionResultResolveToPackageOrClass = ImportUtilsKt.resolveToPackageOrClass(FirSymbolProviderKt.getSymbolProvider(checkerContext.getSession()), importedFqName);
        if (!(packageResolutionResultResolveToPackageOrClass instanceof PackageResolutionResult.PackageOrClass)) {
            if (packageResolutionResultResolveToPackageOrClass instanceof PackageResolutionResult.Error) {
                return;
            }
            bu8.a();
            return;
        }
        FirClassLikeSymbol<?> classSymbol = ((PackageResolutionResult.PackageOrClass) packageResolutionResultResolveToPackageOrClass).getClassSymbol();
        if (classSymbol == null) {
            return;
        }
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.fullyExpandedClass(checkerContext, classSymbol);
        if (firRegularClassSymbolFullyExpandedClass != null && firRegularClassSymbolFullyExpandedClass.getClassKind() == ClassKind.OBJECT) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firImport.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCANNOT_ALL_UNDER_IMPORT_FROM_SINGLETON(), (Object) firRegularClassSymbolFullyExpandedClass.getClassId().getShortClassName(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
        if (isVisible(checkerContext, classSymbol)) {
            return;
        }
        KtSourceElement lastImportedFqNameSegmentSource = FirSourceUtilsKt.getLastImportedFqNameSegmentSource(firImport);
        if (lastImportedFqNameSegmentSource != null) {
            diagnosticReporter.report(ConeDiagnosticToFirDiagnosticKt.toInvisibleReferenceDiagnostic(classSymbol, lastImportedFqNameSegmentSource, checkerContext.getSession()), checkerContext);
        } else {
            oh3.a("`", firImport.getSource(), "` does not contain `", importedFqName, 96);
        }
    }

    private final void checkCanBeImported(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirImport firImport) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        FqName importedFqName = firImport.getImportedFqName();
        if (importedFqName == null) {
            return;
        }
        Name nameShortName = importedFqName.shortName();
        if (nameShortName.isSpecial()) {
            return;
        }
        String identifier = nameShortName.getIdentifier();
        identifier.getClass();
        if (identifier.length() == 0) {
            return;
        }
        FirSymbolProvider symbolProvider = FirSymbolProviderKt.getSymbolProvider(checkerContext.getSession());
        FirBasedSymbol<?> firBasedSymbol = null;
        FirResolvedImport firResolvedImport = firImport instanceof FirResolvedImport ? (FirResolvedImport) firImport : null;
        ClassId resolvedParentClassId = firResolvedImport != null ? firResolvedImport.getResolvedParentClassId() : null;
        if (resolvedParentClassId == null) {
            FirRegularClassSymbol firRegularClassSymbolResolveToClass = resolveToClass(checkerContext, ClassId.Companion.topLevel(importedFqName));
            if (firRegularClassSymbolResolveToClass != null) {
                if (INSTANCE.isVisible(checkerContext, firRegularClassSymbolResolveToClass)) {
                    return;
                } else {
                    firBasedSymbol = firRegularClassSymbolResolveToClass;
                }
            }
            for (FirCallableSymbol<?> firCallableSymbol : symbolProvider.getTopLevelCallableSymbols(importedFqName.parent(), nameShortName)) {
                if (isVisible(checkerContext, firCallableSymbol)) {
                    return;
                }
                if (firBasedSymbol == null) {
                    firBasedSymbol = firCallableSymbol;
                }
            }
            if (firBasedSymbol != null) {
                KtSourceElement sourceForImportSegment = FirSourceUtilsKt.getSourceForImportSegment(firImport, 0);
                if (sourceForImportSegment == null) {
                    sourceForImportSegment = firImport.getSource();
                }
                diagnosticReporter.report(ConeDiagnosticToFirDiagnosticKt.toInvisibleReferenceDiagnostic(firBasedSymbol, sourceForImportSegment, checkerContext.getSession()), checkerContext);
                return;
            }
            if (symbolProvider.hasPackage(importedFqName)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firImport.getSource(), FirErrors.INSTANCE.getPACKAGE_CANNOT_BE_IMPORTED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            } else {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firImport.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNRESOLVED_IMPORT(), (Object) nameShortName.asString(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            }
        }
        FirClassLikeSymbol<?> firClassLikeSymbolResolveToClassLike = resolveToClassLike(checkerContext, resolvedParentClassId);
        if (firClassLikeSymbolResolveToClassLike == null || (firRegularClassSymbolFullyExpandedClass = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.fullyExpandedClass(checkerContext, firClassLikeSymbolResolveToClassLike)) == null) {
            return;
        }
        boolean z = firClassLikeSymbolResolveToClassLike instanceof FirTypeAliasSymbol;
        if (z) {
            checkCanBeImported$reportInvisibleParentClasses(checkerContext, firImport, diagnosticReporter, firClassLikeSymbolResolveToClassLike, 1);
        }
        checkCanBeImported$reportInvisibleParentClassesRecursively(checkerContext, firImport, diagnosticReporter, firRegularClassSymbolFullyExpandedClass, 1);
        ImportStatus importStatusOfCallableMembers = getImportStatusOfCallableMembers(checkerContext, firRegularClassSymbolFullyExpandedClass, nameShortName);
        if (Intrinsics.areEqual(importStatusOfCallableMembers, ImportStatus.OK.INSTANCE)) {
            if (z) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirResolvedImport) firImport).getSource(), (KtDiagnosticFactoryForDeprecation2) FirErrors.INSTANCE.getTYPEALIAS_AS_CALLABLE_QUALIFIER_IN_IMPORT(), (Object) ((FirTypeAliasSymbol) firClassLikeSymbolResolveToClassLike).getName(), (Object) firRegularClassSymbolFullyExpandedClass.getName(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        } else if (importStatusOfCallableMembers instanceof ImportStatus.Invisible) {
            diagnosticReporter.report(ConeDiagnosticToFirDiagnosticKt.toInvisibleReferenceDiagnostic(((ImportStatus.Invisible) importStatusOfCallableMembers).getSymbol(), FirSourceUtilsKt.getSourceForImportSegment(firImport, 0), checkerContext.getSession()), checkerContext);
        } else {
            if (symbolProvider.getClassLikeSymbolByClassId(firRegularClassSymbolFullyExpandedClass.getClassId().createNestedClassId(nameShortName)) != null) {
                return;
            }
            if (Intrinsics.areEqual(importStatusOfCallableMembers, ImportStatus.Unresolved.INSTANCE)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirResolvedImport) firImport).getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNRESOLVED_IMPORT(), (Object) nameShortName.asString(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            } else {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirResolvedImport) firImport).getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getCANNOT_BE_IMPORTED(), (Object) nameShortName, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    private static final void checkCanBeImported$reportInvisibleParentClasses(CheckerContext checkerContext, FirImport firImport, DiagnosticReporter diagnosticReporter, FirClassLikeSymbol<?> firClassLikeSymbol, int i) {
        if (INSTANCE.isVisible(checkerContext, firClassLikeSymbol)) {
            return;
        }
        diagnosticReporter.report(ConeDiagnosticToFirDiagnosticKt.toInvisibleReferenceDiagnostic(firClassLikeSymbol, FirSourceUtilsKt.getSourceForImportSegment(firImport, i), checkerContext.getSession()), checkerContext);
    }

    private static final void checkCanBeImported$reportInvisibleParentClassesRecursively(CheckerContext checkerContext, FirImport firImport, DiagnosticReporter diagnosticReporter, FirRegularClassSymbol firRegularClassSymbol, int i) {
        FirRegularClassSymbol firRegularClassSymbolResolveToClass;
        checkCanBeImported$reportInvisibleParentClasses(checkerContext, firImport, diagnosticReporter, firRegularClassSymbol, i);
        ClassId outerClassId = firRegularClassSymbol.getClassId().getOuterClassId();
        if (outerClassId == null || (firRegularClassSymbolResolveToClass = INSTANCE.resolveToClass(checkerContext, outerClassId)) == null) {
            return;
        }
        checkCanBeImported$reportInvisibleParentClassesRecursively(checkerContext, firImport, diagnosticReporter, firRegularClassSymbolResolveToClass, i + 1);
    }

    private final void checkConflictingImports(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, List<? extends FirImport> list) {
        KtSourceElement source;
        String identifierOrNullIfSpecial;
        KtSourceElementKind kind;
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof FirResolvedImport) {
                FirResolvedImport firResolvedImport = (FirResolvedImport) obj;
                if (!firResolvedImport.getIsAllUnder() && ((source = firResolvedImport.getSource()) == null || (kind = source.getKind()) == null || !kind.getShouldSkipErrorTypeReporting())) {
                    Name importedName = firResolvedImport.getImportedName();
                    if (importedName != null && (identifierOrNullIfSpecial = importedName.getIdentifierOrNullIfSpecial()) != null && identifierOrNullIfSpecial.length() > 0 && INSTANCE.resolvesToClass(checkerContext, firResolvedImport)) {
                        arrayList.add(obj);
                    }
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            KtSourceElement source2 = ((FirResolvedImport) obj2).getSource();
            if (!Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitImport.INSTANCE)) {
                arrayList2.add(obj2);
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj3 : arrayList2) {
            FirResolvedImport firResolvedImport2 = (FirResolvedImport) obj3;
            Name aliasName = firResolvedImport2.getAliasName();
            if (aliasName == null) {
                aliasName = firResolvedImport2.getImportedName();
                aliasName.getClass();
            }
            Object arrayList3 = linkedHashMap.get(aliasName);
            if (arrayList3 == null) {
                arrayList3 = new ArrayList();
                linkedHashMap.put(aliasName, arrayList3);
            }
            ((List) arrayList3).add(obj3);
        }
        Collection collectionValues = linkedHashMap.values();
        ArrayList arrayList4 = new ArrayList();
        for (Object obj4 : collectionValues) {
            if (((List) obj4).size() > 1) {
                arrayList4.add(obj4);
            }
        }
        Iterator it = arrayList4.iterator();
        while (it.hasNext()) {
            for (FirResolvedImport firResolvedImport3 : (List) it.next()) {
                KtSourceElement source3 = firResolvedImport3.getSource();
                KtDiagnosticFactory1<Name> conflicting_import = FirErrors.INSTANCE.getCONFLICTING_IMPORT();
                Name importedName2 = firResolvedImport3.getImportedName();
                importedName2.getClass();
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source3, (KtDiagnosticFactory1) conflicting_import, (Object) importedName2, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
        }
    }

    private final void checkImportApiStatus(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirImport firImport) {
        FqName importedFqName = firImport.getImportedFqName();
        if (importedFqName == null || importedFqName.isRoot()) {
            return;
        }
        String strAsString = importedFqName.shortName().asString();
        strAsString.getClass();
        if (strAsString.length() == 0) {
            return;
        }
        FirResolvedImport firResolvedImport = firImport instanceof FirResolvedImport ? (FirResolvedImport) firImport : null;
        ClassId resolvedParentClassId = firResolvedImport != null ? firResolvedImport.getResolvedParentClassId() : null;
        if (resolvedParentClassId == null && LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.NoDeprecationOnImportStatements)) {
            return;
        }
        if (resolvedParentClassId == null) {
            resolvedParentClassId = ClassId.Companion.topLevel(importedFqName);
        }
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(checkerContext, resolvedParentClassId);
        if (symbol == null) {
            return;
        }
        FirDeprecationChecker.reportApiStatusIfNeeded$org_jetbrains_kotlin_checkers$default(FirDeprecationChecker.INSTANCE, checkerContext, diagnosticReporter, firImport.getSource(), symbol, null, 16, null);
    }

    private final void checkOperatorRename(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedImport firResolvedImport) {
        Name importedName;
        Name aliasName = firResolvedImport.getAliasName();
        if (aliasName == null || (importedName = firResolvedImport.getImportedName()) == null || !OperatorConventions.isConventionName(aliasName)) {
            return;
        }
        if (Intrinsics.areEqual(aliasName, OperatorNameConventions.OF) && LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.CollectionLiterals)) {
            return;
        }
        if (Intrinsics.areEqual(aliasName, OperatorNameConventions.PROVIDE_DELEGATE) && LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.TreatProvideDelegateAsConventionName)) {
            return;
        }
        ClassId resolvedParentClassId = firResolvedImport.getResolvedParentClassId();
        if (resolvedParentClassId == null) {
            List<FirNamedFunctionSymbol> topLevelFunctionSymbols = FirSymbolProviderKt.getSymbolProvider(checkerContext.getSession()).getTopLevelFunctionSymbols(firResolvedImport.getPackageFqName(), importedName);
            if ((topLevelFunctionSymbols instanceof Collection) && topLevelFunctionSymbols.isEmpty()) {
                return;
            }
            Iterator<T> it = topLevelFunctionSymbols.iterator();
            while (it.hasNext()) {
                if (((FirNamedFunctionSymbol) it.next()).getResolvedStatus().isOperator()) {
                }
            }
            return;
        }
        FirRegularClassSymbol firRegularClassSymbolResolveToClass = resolveToClass(checkerContext, resolvedParentClassId);
        if (firRegularClassSymbolResolveToClass == null || !firRegularClassSymbolResolveToClass.getClassKind().isSingleton() || !hasFunction(checkerContext, firRegularClassSymbolResolveToClass, importedName, new Function1() { // from class: r85
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirImportsChecker.c((FirNamedFunctionSymbol) obj));
            }
        })) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedImport.getSource(), FirErrors.INSTANCE.getOPERATOR_RENAMED_ON_IMPORT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }

    private final ImportStatus getImportStatusOfCallableMembers(final CheckerContext checkerContext, FirRegularClassSymbol firRegularClassSymbol, Name name) {
        if (firRegularClassSymbol.getClassKind().isSingleton()) {
            List<FirContainingNamesAwareScope> listListOf = CollectionsKt.listOf(new FirContainingNamesAwareScope[]{FirDeclaredMemberScopeProviderKt.declaredMemberScope(checkerContext.getSession(), firRegularClassSymbol, (FirResolvePhase) null), FirHelpersKt.unsubstitutedScope(checkerContext, firRegularClassSymbol)});
            final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            for (FirContainingNamesAwareScope firContainingNamesAwareScope : listListOf) {
                firContainingNamesAwareScope.processFunctionsByName(name, new Function1<FirNamedFunctionSymbol, Unit>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirImportsChecker$getImportStatusOfCallableMembers$$inlined$getImportStatus$1
                    public final void invoke(FirNamedFunctionSymbol firNamedFunctionSymbol) {
                        firNamedFunctionSymbol.getClass();
                        if (FirImportsChecker.INSTANCE.isVisible(checkerContext, firNamedFunctionSymbol)) {
                            booleanRef.element = true;
                        }
                        objectRef.element = firNamedFunctionSymbol;
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((FirNamedFunctionSymbol) obj);
                        return Unit.INSTANCE;
                    }
                });
                if (booleanRef.element) {
                    return ImportStatus.OK.INSTANCE;
                }
                firContainingNamesAwareScope.processPropertiesByName(name, new Function1<FirVariableSymbol<?>, Unit>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirImportsChecker$getImportStatusOfCallableMembers$$inlined$getImportStatus$2
                    public final void invoke(FirVariableSymbol<?> firVariableSymbol) {
                        firVariableSymbol.getClass();
                        if (FirImportsChecker.INSTANCE.isVisible(checkerContext, firVariableSymbol)) {
                            booleanRef.element = true;
                        }
                        objectRef.element = firVariableSymbol;
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((FirVariableSymbol<?>) obj);
                        return Unit.INSTANCE;
                    }
                });
                if (booleanRef.element) {
                    return ImportStatus.OK.INSTANCE;
                }
            }
            Object obj = objectRef.element;
            if (((FirCallableSymbol) obj) != null) {
                return new ImportStatus.Invisible((FirCallableSymbol) obj);
            }
            return obj != null ? ImportStatus.CannotBeImported.INSTANCE : ImportStatus.Unresolved.INSTANCE;
        }
        List<FirContainingNamesAwareScope> listListOfNotNull = CollectionsKt.listOfNotNull(new FirContainingNamesAwareScope[]{FirDeclaredMemberScopeProviderKt.declaredMemberScope(checkerContext.getSession(), firRegularClassSymbol, (FirResolvePhase) null), ImplicitReceiverUtilsKt.staticScope(firRegularClassSymbol, checkerContext.getSessionHolder()), FirHelpersKt.unsubstitutedScope(checkerContext, firRegularClassSymbol)});
        final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        for (FirContainingNamesAwareScope firContainingNamesAwareScope2 : listListOfNotNull) {
            firContainingNamesAwareScope2.processFunctionsByName(name, new Function1<FirNamedFunctionSymbol, Unit>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirImportsChecker$getImportStatusOfCallableMembers$$inlined$getImportStatus$3
                public final void invoke(FirNamedFunctionSymbol firNamedFunctionSymbol) {
                    firNamedFunctionSymbol.getClass();
                    if (FirImportsChecker.INSTANCE.isVisible(checkerContext, firNamedFunctionSymbol) && firNamedFunctionSymbol.getRawStatus().isStatic()) {
                        booleanRef2.element = true;
                    }
                    objectRef2.element = firNamedFunctionSymbol;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((FirNamedFunctionSymbol) obj2);
                    return Unit.INSTANCE;
                }
            });
            if (booleanRef2.element) {
                return ImportStatus.OK.INSTANCE;
            }
            firContainingNamesAwareScope2.processPropertiesByName(name, new Function1<FirVariableSymbol<?>, Unit>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirImportsChecker$getImportStatusOfCallableMembers$$inlined$getImportStatus$4
                public final void invoke(FirVariableSymbol<?> firVariableSymbol) {
                    firVariableSymbol.getClass();
                    if (FirImportsChecker.INSTANCE.isVisible(checkerContext, firVariableSymbol) && firVariableSymbol.getRawStatus().isStatic()) {
                        booleanRef2.element = true;
                    }
                    objectRef2.element = firVariableSymbol;
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj2) {
                    invoke((FirVariableSymbol<?>) obj2);
                    return Unit.INSTANCE;
                }
            });
            if (booleanRef2.element) {
                return ImportStatus.OK.INSTANCE;
            }
        }
        FirCallableSymbol firCallableSymbol = (FirCallableSymbol) objectRef2.element;
        if (firCallableSymbol == null || !firCallableSymbol.getRawStatus().isStatic()) {
            return objectRef2.element != null ? ImportStatus.CannotBeImported.INSTANCE : ImportStatus.Unresolved.INSTANCE;
        }
        return new ImportStatus.Invisible((FirCallableSymbol) objectRef2.element);
    }

    private final boolean hasFunction(CheckerContext checkerContext, FirRegularClassSymbol firRegularClassSymbol, Name name, final Function1<? super FirNamedFunctionSymbol, Boolean> function1) {
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        FirDeclaredMemberScopeProviderKt.declaredMemberScope(checkerContext.getSession(), firRegularClassSymbol, (FirResolvePhase) null).processFunctionsByName(name, new Function1() { // from class: q85
            public final Object invoke(Object obj) {
                return FirImportsChecker.b(booleanRef, function1, (FirNamedFunctionSymbol) obj);
            }
        });
        return booleanRef.element;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isVisible(CheckerContext checkerContext, FirBasedSymbol<?> firBasedSymbol) {
        Visibility visibility;
        FirFileSymbol containingFileSymbol = checkerContext.getContainingFileSymbol();
        if (containingFileSymbol == null) {
            return false;
        }
        if (firBasedSymbol instanceof FirCallableSymbol) {
            visibility = ((FirCallableSymbol) firBasedSymbol).getResolvedStatus().getVisibility();
        } else {
            if (!(firBasedSymbol instanceof FirClassLikeSymbol)) {
                return false;
            }
            visibility = ((FirClassLikeSymbol) firBasedSymbol).getRawStatus().getVisibility();
        }
        if (!Intrinsics.areEqual(visibility, Visibilities.Unknown.INSTANCE) && !visibility.mustCheckInImports()) {
            return true;
        }
        if (!Intrinsics.areEqual(visibility, Visibilities.Private.INSTANCE) && !Intrinsics.areEqual(visibility, Visibilities.PrivateToThis.INSTANCE)) {
            return FirVisibilityCheckerKt.isVisible(FirVisibilityCheckerKt.getVisibilityChecker(checkerContext.getSession()), firBasedSymbol, checkerContext.getSession(), containingFileSymbol, CollectionsKt.emptyList(), null, true);
        }
        FirFile containingFile = FirProviderUtilsKt.getContainingFile(FirProviderKt.getFirProvider(checkerContext.getSession()), firBasedSymbol);
        return Intrinsics.areEqual(containingFileSymbol, containingFile != null ? containingFile.getSymbol() : null);
    }

    private final FirRegularClassSymbol resolveToClass(CheckerContext checkerContext, ClassId classId) {
        FirClassLikeSymbol<?> firClassLikeSymbolResolveToClassLike = resolveToClassLike(checkerContext, classId);
        if (firClassLikeSymbolResolveToClassLike == null) {
            return null;
        }
        if (firClassLikeSymbolResolveToClassLike instanceof FirRegularClassSymbol) {
            return (FirRegularClassSymbol) firClassLikeSymbolResolveToClassLike;
        }
        if (firClassLikeSymbolResolveToClassLike instanceof FirTypeAliasSymbol) {
            return org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.fullyExpandedClass(checkerContext, firClassLikeSymbolResolveToClassLike);
        }
        if (firClassLikeSymbolResolveToClassLike instanceof FirAnonymousObjectSymbol) {
            return null;
        }
        bu8.a();
        return null;
    }

    private final FirClassLikeSymbol<?> resolveToClassLike(CheckerContext checkerContext, ClassId classId) {
        return FirSymbolProviderKt.getSymbolProvider(checkerContext.getSession()).getClassLikeSymbolByClassId(classId);
    }

    private final boolean resolvesToClass(CheckerContext checkerContext, FirResolvedImport firResolvedImport) {
        Name importedName;
        if (firResolvedImport.getResolvedParentClassId() == null) {
            FqName importedFqName = firResolvedImport.getImportedFqName();
            return (importedFqName == null || importedFqName.isRoot() || resolveToClass(checkerContext, ClassId.Companion.topLevel(importedFqName)) == null) ? false : true;
        }
        if (firResolvedImport.getIsAllUnder()) {
            return true;
        }
        ClassId resolvedParentClassId = firResolvedImport.getResolvedParentClassId();
        resolvedParentClassId.getClass();
        FqName relativeParentClassName = firResolvedImport.getRelativeParentClassName();
        return (relativeParentClassName == null || (importedName = firResolvedImport.getImportedName()) == null || resolveToClass(checkerContext, new ClassId(resolvedParentClassId.getPackageFqName(), relativeParentClassName.child(importedName), false)) == null) ? false : true;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFile firFile) {
        KtSourceElementKind kind;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFile.getClass();
        for (FirImport firImport : firFile.getImports()) {
            KtSourceElement source = firImport.getSource();
            if (source == null || (kind = source.getKind()) == null || !kind.getShouldSkipErrorTypeReporting()) {
                if (!firImport.getIsAllUnder()) {
                    FirImportsChecker firImportsChecker = INSTANCE;
                    firImportsChecker.checkCanBeImported(checkerContext, diagnosticReporter, firImport);
                    if (firImport instanceof FirResolvedImport) {
                        firImportsChecker.checkOperatorRename(checkerContext, diagnosticReporter, (FirResolvedImport) firImport);
                    }
                } else if (firImport instanceof FirResolvedImport) {
                    INSTANCE.checkAllUnderFromObject(checkerContext, diagnosticReporter, firImport);
                } else {
                    INSTANCE.checkAllUnderFromEnumEntry(checkerContext, diagnosticReporter, firImport);
                }
                INSTANCE.checkImportApiStatus(checkerContext, diagnosticReporter, firImport);
            }
        }
        checkConflictingImports(checkerContext, diagnosticReporter, firFile.getImports());
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus;", Argument.Delimiters.none, "<init>", "()V", "OK", "Invisible", "CannotBeImported", "Unresolved", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus$CannotBeImported;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus$Invisible;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus$OK;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus$Unresolved;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class ImportStatus {

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus$CannotBeImported;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class CannotBeImported extends ImportStatus {
            public static final CannotBeImported INSTANCE = new CannotBeImported();

            private CannotBeImported() {
                super(null);
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof CannotBeImported);
            }

            public int hashCode() {
                return 1558473421;
            }

            public String toString() {
                return "CannotBeImported";
            }
        }

        @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0013\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u0017\u0010\t\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0001J\u0014\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0083\u0004J\n\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004J\n\u0010\u0010\u001a\u00020\u0011HÖ\u0081\u0004R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus$Invisible;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "component1", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class Invisible extends ImportStatus {
            private final FirCallableSymbol<?> symbol;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Invisible(FirCallableSymbol<?> firCallableSymbol) {
                super(null);
                firCallableSymbol.getClass();
                this.symbol = firCallableSymbol;
            }

            /* JADX WARN: Multi-variable type inference failed */
            public static /* synthetic */ Invisible copy$default(Invisible invisible, FirCallableSymbol firCallableSymbol, int i, Object obj) {
                if ((i & 1) != 0) {
                    firCallableSymbol = invisible.symbol;
                }
                return invisible.copy(firCallableSymbol);
            }

            public final FirCallableSymbol<?> component1() {
                return this.symbol;
            }

            public final Invisible copy(FirCallableSymbol<?> symbol) {
                symbol.getClass();
                return new Invisible(symbol);
            }

            public boolean equals(Object other) {
                if (this == other) {
                    return true;
                }
                return (other instanceof Invisible) && Intrinsics.areEqual(this.symbol, ((Invisible) other).symbol);
            }

            public final FirCallableSymbol<?> getSymbol() {
                return this.symbol;
            }

            public int hashCode() {
                return this.symbol.hashCode();
            }

            public String toString() {
                return "Invisible(symbol=" + this.symbol + ')';
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus$OK;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class OK extends ImportStatus {
            public static final OK INSTANCE = new OK();

            private OK() {
                super(null);
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof OK);
            }

            public int hashCode() {
                return -1967623169;
            }

            public String toString() {
                return "OK";
            }
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÖ\u0083\u0004J\n\u0010\b\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\n\u001a\u00020\u000bHÖ\u0081\u0004¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus$Unresolved;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirImportsChecker$ImportStatus;", "<init>", "()V", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* data */ class Unresolved extends ImportStatus {
            public static final Unresolved INSTANCE = new Unresolved();

            private Unresolved() {
                super(null);
            }

            public boolean equals(Object other) {
                return this == other || (other instanceof Unresolved);
            }

            public int hashCode() {
                return -1417964332;
            }

            public String toString() {
                return "Unresolved";
            }
        }

        public /* synthetic */ ImportStatus(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private ImportStatus() {
        }
    }
}
