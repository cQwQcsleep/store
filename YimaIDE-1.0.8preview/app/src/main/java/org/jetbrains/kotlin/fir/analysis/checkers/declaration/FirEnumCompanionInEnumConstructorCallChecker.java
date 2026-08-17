package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImplKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeWithSubgraphs;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionCallExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.QualifiedAccessNode;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ=\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0014J'\u0010\u0015\u001a\u0004\u0018\u00010\u0012*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0019¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirEnumCompanionInEnumConstructorCallChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "analyzeGraph", "graph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "companionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "enumClass", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)V", "getClassSymbol", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "qualifiedAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirEnumCompanionInEnumConstructorCallChecker extends FirDeclarationChecker<FirClass> {
    public static final FirEnumCompanionInEnumConstructorCallChecker INSTANCE = new FirEnumCompanionInEnumConstructorCallChecker();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ClassKind.values().length];
            try {
                iArr[ClassKind.ENUM_CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ClassKind.ENUM_ENTRY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ControlFlowGraph.Kind.values().length];
            try {
                iArr2[ControlFlowGraph.Kind.AnonymousFunctionCalledInPlace.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[ControlFlowGraph.Kind.PropertyInitializer.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[ControlFlowGraph.Kind.ClassInitializer.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[ControlFlowGraph.Kind.Class.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private FirEnumCompanionInEnumConstructorCallChecker() {
        super(MppCheckerKind.Common);
    }

    private final void analyzeGraph(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ControlFlowGraph controlFlowGraph, FirRegularClassSymbol firRegularClassSymbol, FirRegularClassSymbol firRegularClassSymbol2) {
        FirQualifiedAccessExpression fir;
        Object next;
        FirEnumCompanionInEnumConstructorCallChecker firEnumCompanionInEnumConstructorCallChecker;
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        FirRegularClassSymbol firRegularClassSymbol3;
        FirRegularClassSymbol firRegularClassSymbol4;
        for (CFGNode<?> cFGNode : controlFlowGraph.getNodes()) {
            if (cFGNode instanceof CFGNodeWithSubgraphs) {
                for (ControlFlowGraph controlFlowGraph2 : ((CFGNodeWithSubgraphs) cFGNode).getSubGraphs()) {
                    int i = WhenMappings.$EnumSwitchMapping$1[controlFlowGraph2.getKind().ordinal()];
                    if (i == 1 || i == 2 || i == 3) {
                        firEnumCompanionInEnumConstructorCallChecker = this;
                        checkerContext2 = checkerContext;
                        diagnosticReporter2 = diagnosticReporter;
                        firRegularClassSymbol3 = firRegularClassSymbol;
                        firRegularClassSymbol4 = firRegularClassSymbol2;
                        firEnumCompanionInEnumConstructorCallChecker.analyzeGraph(checkerContext2, diagnosticReporter2, controlFlowGraph2, firRegularClassSymbol3, firRegularClassSymbol4);
                        this = firEnumCompanionInEnumConstructorCallChecker;
                        checkerContext = checkerContext2;
                        diagnosticReporter = diagnosticReporter2;
                        firRegularClassSymbol = firRegularClassSymbol3;
                        firRegularClassSymbol2 = firRegularClassSymbol4;
                    } else {
                        if (i != 4) {
                            firEnumCompanionInEnumConstructorCallChecker = this;
                            checkerContext2 = checkerContext;
                            diagnosticReporter2 = diagnosticReporter;
                            firRegularClassSymbol3 = firRegularClassSymbol;
                            firRegularClassSymbol4 = firRegularClassSymbol2;
                        } else if (controlFlowGraph2.getDeclaration() instanceof FirAnonymousObject) {
                            firEnumCompanionInEnumConstructorCallChecker = this;
                            checkerContext2 = checkerContext;
                            diagnosticReporter2 = diagnosticReporter;
                            firRegularClassSymbol3 = firRegularClassSymbol;
                            firRegularClassSymbol4 = firRegularClassSymbol2;
                            firEnumCompanionInEnumConstructorCallChecker.analyzeGraph(checkerContext2, diagnosticReporter2, controlFlowGraph2, firRegularClassSymbol3, firRegularClassSymbol4);
                        }
                        this = firEnumCompanionInEnumConstructorCallChecker;
                        checkerContext = checkerContext2;
                        diagnosticReporter = diagnosticReporter2;
                        firRegularClassSymbol = firRegularClassSymbol3;
                        firRegularClassSymbol2 = firRegularClassSymbol4;
                    }
                }
            }
            CheckerContext checkerContext3 = checkerContext;
            DiagnosticReporter diagnosticReporter3 = diagnosticReporter;
            firRegularClassSymbol = firRegularClassSymbol;
            FirRegularClassSymbol firRegularClassSymbol5 = firRegularClassSymbol2;
            if (cFGNode instanceof QualifiedAccessNode) {
                fir = ((QualifiedAccessNode) cFGNode).getFir();
            } else {
                if (cFGNode instanceof FunctionCallExitNode) {
                    fir = ((FunctionCallExitNode) cFGNode).getFir();
                }
                checkerContext = checkerContext3;
                diagnosticReporter = diagnosticReporter3;
                firRegularClassSymbol2 = firRegularClassSymbol5;
            }
            Iterator<T> it = FirExpressionUtilKt.getAllReceiverExpressions(fir).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(INSTANCE.getClassSymbol(checkerContext3, FirExpressionUtilKt.unwrapSmartcastExpression((FirExpression) next), fir), firRegularClassSymbol));
            FirExpression firExpression = (FirExpression) next;
            if (firExpression != null) {
                KtSourceElement source = firExpression.getSource();
                if (source == null) {
                    source = fir.getSource();
                }
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext3, diagnosticReporter3, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNINITIALIZED_ENUM_COMPANION(), (Object) firRegularClassSymbol5, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                checkerContext = checkerContext3;
                diagnosticReporter = diagnosticReporter3;
                firRegularClassSymbol2 = firRegularClassSymbol5;
            } else {
                checkerContext = checkerContext3;
                diagnosticReporter = diagnosticReporter3;
                firRegularClassSymbol2 = firRegularClassSymbol5;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0059  */
    private final FirRegularClassSymbol getClassSymbol(CheckerContext checkerContext, FirExpression firExpression, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        FirThisOwnerSymbol<?> boundSymbol;
        if (!(firExpression instanceof FirResolvedQualifier)) {
            FirReference reference = ReferenceUtilsKt.toReference(firExpression, checkerContext.getSession());
            FirThisReference firThisReference = reference instanceof FirThisReference ? (FirThisReference) reference : null;
            if (firThisReference != null) {
                boundSymbol = firThisReference.getBoundSymbol();
            } else {
                boundSymbol = null;
            }
        } else if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.FixedUninitializedEnumCompanionCheck)) {
            boundSymbol = FirHelpersKt.resolvedSymbolOrCompanionSymbol(checkerContext, (FirResolvedQualifier) firExpression);
        } else {
            FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firQualifiedAccessExpression);
            if (resolvedCallableSymbol != null && resolvedCallableSymbol.getRawStatus().isStatic()) {
                KtSourceElement source = ((FirResolvedQualifier) firExpression).getSource();
                if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.ImplicitReceiver) {
                    boundSymbol = null;
                }
            }
            boundSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, FirTypeUtilsKt.getResolvedType(firExpression));
        }
        if (boundSymbol instanceof FirRegularClassSymbol) {
            return (FirRegularClassSymbol) boundSymbol;
        }
        return null;
    }

    /* JADX WARN: Code duplicated, block: B:38:0x0091  */
    /* JADX WARN: Code duplicated, block: B:45:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:53:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:54:? A[RETURN, SYNTHETIC] */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        FirRegularClassSymbol symbol;
        FirRegularClassSymbol firRegularClassSymbol;
        FirRegularClassSymbol resolvedCompanionObjectSymbol;
        FirControlFlowGraphReference controlFlowGraphReference;
        ControlFlowGraph controlFlowGraph;
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny;
        ControlFlowGraph controlFlowGraph2;
        FirControlFlowGraphReference resolvedControlFlowGraphReference;
        Object next;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[firClass.getClassKind().ordinal()];
        ControlFlowGraph controlFlowGraph3 = null;
        if (i != 1) {
            if (i != 2) {
                firRegularClassSymbol = null;
            } else {
                List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
                if (!(containingDeclarations instanceof List)) {
                    Iterator it = CollectionsKt.reversed(containingDeclarations).iterator();
                    do {
                        if (!it.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it.next();
                    } while (!(next instanceof FirRegularClassSymbol));
                } else {
                    int size = containingDeclarations.size() - 1;
                    if (size < 0) {
                        next = null;
                        break;
                    }
                    while (true) {
                        int i2 = size - 1;
                        next = containingDeclarations.get(size);
                        if (next instanceof FirRegularClassSymbol) {
                            break;
                        }
                        if (i2 < 0) {
                            next = null;
                            break;
                        }
                        size = i2;
                    }
                }
                symbol = (FirRegularClassSymbol) next;
            }
            if (firRegularClassSymbol != null || (resolvedCompanionObjectSymbol = firRegularClassSymbol.getResolvedCompanionObjectSymbol()) == null || (controlFlowGraphReference = firClass.getControlFlowGraphReference()) == null || (controlFlowGraph = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference)) == null) {
                return;
            }
            analyzeGraph(checkerContext, diagnosticReporter, controlFlowGraph, resolvedCompanionObjectSymbol, firRegularClassSymbol);
            if (firClass.getClassKind() == ClassKind.ENUM_ENTRY) {
                firConstructorSymbolPrimaryConstructorIfAny = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.primaryConstructorIfAny(firClass, checkerContext.getSession());
                if (firConstructorSymbolPrimaryConstructorIfAny != null && (resolvedControlFlowGraphReference = firConstructorSymbolPrimaryConstructorIfAny.getResolvedControlFlowGraphReference()) != null) {
                    controlFlowGraph3 = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(resolvedControlFlowGraphReference);
                }
                controlFlowGraph2 = controlFlowGraph3;
                if (controlFlowGraph2 != null) {
                    analyzeGraph(checkerContext, diagnosticReporter, controlFlowGraph2, resolvedCompanionObjectSymbol, firRegularClassSymbol);
                }
            }
        }
        symbol = ((FirRegularClass) firClass).getSymbol();
        firRegularClassSymbol = symbol;
        if (firRegularClassSymbol != null) {
            return;
        }
        analyzeGraph(checkerContext, diagnosticReporter, controlFlowGraph, resolvedCompanionObjectSymbol, firRegularClassSymbol);
        if (firClass.getClassKind() == ClassKind.ENUM_ENTRY) {
            firConstructorSymbolPrimaryConstructorIfAny = org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.primaryConstructorIfAny(firClass, checkerContext.getSession());
            if (firConstructorSymbolPrimaryConstructorIfAny != null) {
                controlFlowGraph3 = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(resolvedControlFlowGraphReference);
            }
            controlFlowGraph2 = controlFlowGraph3;
            if (controlFlowGraph2 != null) {
                analyzeGraph(checkerContext, diagnosticReporter, controlFlowGraph2, resolvedCompanionObjectSymbol, firRegularClassSymbol);
            }
        }
    }
}
