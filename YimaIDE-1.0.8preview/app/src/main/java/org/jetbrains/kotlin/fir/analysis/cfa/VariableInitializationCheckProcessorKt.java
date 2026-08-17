package org.jetbrains.kotlin.fir.analysis.cfa;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.InlineStatus;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\t\u001a\u00020\u0001*\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\fH\u0002\u001a/\u0010\u0010\u001a\u00020\u00112\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u000eR\u00020\u0012j\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0002\u0010\u0017\u001a\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0006*\u0006\u0012\u0002\b\u00030\u0015H\u0002\u001a\u0010\u0010\u0019\u001a\u00020\u001a*\u0006\u0012\u0002\b\u00030\fH\u0002\"\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0018\u0010\u0005\u001a\u00020\u0001*\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b\"\u0019\u0010\r\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u000e8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000f¨\u0006\u001b"}, d2 = {"doNotReportUninitializedVariableForInitialization", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph$Kind;", "getDoNotReportUninitializedVariableForInitialization", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph$Kind;)Z", "evaluatedInline", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getEvaluatedInline", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Z", "isInline", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "until", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isCapturedByValue", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;)Z", "buildRecursionErrorMessage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "problemNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "symbol", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;)Ljava/lang/String;", "firstGraphDeclaration", "getDebugFqName", "Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class VariableInitializationCheckProcessorKt {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ControlFlowGraph.Kind.values().length];
            try {
                iArr[ControlFlowGraph.Kind.Function.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ControlFlowGraph.Kind.AnonymousFunction.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ControlFlowGraph.Kind.LocalFunction.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final String buildRecursionErrorMessage(CheckerContext checkerContext, CFGNode<?> cFGNode, FirVariableSymbol<?> firVariableSymbol) {
        checkerContext.getClass();
        cFGNode.getClass();
        firVariableSymbol.getClass();
        StringBuilder sb = new StringBuilder("Node has already been visited and could result in infinite recursion.\n\nFile Path: ");
        KtSourceFile containingFile = checkerContext.getContainingFile();
        sb.append(containingFile != null ? containingFile.getPath() : null);
        sb.append("\nVariable: ");
        sb.append(getDebugFqName(firVariableSymbol));
        sb.append("\nDeclarations:\n");
        FirDeclaration firDeclarationFirstGraphDeclaration = firstGraphDeclaration(cFGNode);
        if (firDeclarationFirstGraphDeclaration != null) {
            sb.append("- ");
            sb.append(getDebugFqName(firDeclarationFirstGraphDeclaration.getSymbol()));
            sb.append(" (graph declaration)\n");
        }
        for (FirBasedSymbol<?> firBasedSymbol : checkerContext.getContainingDeclarations()) {
            sb.append("- ");
            sb.append(getDebugFqName(firBasedSymbol));
            sb.append('\n');
        }
        return sb.toString();
    }

    private static final FirDeclaration firstGraphDeclaration(CFGNode<?> cFGNode) {
        FirDeclaration declaration = cFGNode.getOwner().getDeclaration();
        if (declaration != null) {
            return declaration;
        }
        Iterator<T> it = cFGNode.getOwner().getEnterNode().getPreviousNodes().iterator();
        while (it.hasNext()) {
            FirDeclaration firDeclarationFirstGraphDeclaration = firstGraphDeclaration((CFGNode) it.next());
            if (firDeclarationFirstGraphDeclaration != null) {
                return firDeclarationFirstGraphDeclaration;
            }
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final FqName getDebugFqName(FirBasedSymbol<?> firBasedSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirDeclaration fir = firBasedSymbol.getFir();
        if (fir instanceof FirFile) {
            FirFile firFile = (FirFile) fir;
            FqName packageFqName = UtilsKt.getPackageFqName(firFile);
            Name nameIdentifier = Name.identifier(firFile.getName());
            nameIdentifier.getClass();
            return packageFqName.child(nameIdentifier);
        }
        if (fir instanceof FirScript) {
            return ((FirScript) fir).getSymbol().getFqName();
        }
        if (fir instanceof FirReplSnippet) {
            return ((FirReplSnippet) fir).getSnippetClass().getSymbol().getClassId().asSingleFqName();
        }
        if (fir instanceof FirClassLikeDeclaration) {
            return ((FirClassLikeDeclaration) fir).getSymbol().getClassId().asSingleFqName();
        }
        if (fir instanceof FirTypeParameter) {
            FirTypeParameter firTypeParameter = (FirTypeParameter) fir;
            return getDebugFqName(firTypeParameter.getContainingDeclarationSymbol()).child(firTypeParameter.getName());
        }
        if (fir instanceof FirAnonymousInitializer) {
            FqName debugFqName = getDebugFqName(((FirAnonymousInitializer) fir).getContainingDeclarationSymbol());
            Name nameSpecial = Name.special("<init>");
            nameSpecial.getClass();
            return debugFqName.child(nameSpecial);
        }
        if (fir instanceof FirCallableDeclaration) {
            return ((FirCallableDeclaration) fir).getSymbol().getCallableIdForRendering().asFqNameForDebugInfo();
        }
        if (fir instanceof FirCodeFragment) {
            FqName.Companion companion = FqName.Companion;
            Name nameSpecial2 = Name.special("<fragment>");
            nameSpecial2.getClass();
            return companion.topLevel(nameSpecial2);
        }
        if (fir instanceof FirDanglingModifierList) {
            FqName.Companion companion2 = FqName.Companion;
            Name nameSpecial3 = Name.special("<dangling>");
            nameSpecial3.getClass();
            return companion2.topLevel(nameSpecial3);
        }
        if (!(fir instanceof FirReceiverParameter)) {
            bu8.a();
            return null;
        }
        FqName.Companion companion3 = FqName.Companion;
        Name nameSpecial4 = Name.special("<extension-receiver-parameter>");
        nameSpecial4.getClass();
        return companion3.topLevel(nameSpecial4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getDoNotReportUninitializedVariableForInitialization(ControlFlowGraph.Kind kind) {
        int i = WhenMappings.$EnumSwitchMapping$0[kind.ordinal()];
        return i == 1 || i == 2 || i == 3;
    }

    private static final boolean getEvaluatedInline(FirDeclaration firDeclaration) {
        if (firDeclaration instanceof FirAnonymousFunction) {
            return ((FirAnonymousFunction) firDeclaration).getInlineStatus() == InlineStatus.Inline;
        }
        if (firDeclaration instanceof FirConstructor) {
            return true;
        }
        return ((firDeclaration instanceof FirFunction) || (firDeclaration instanceof FirClass)) ? false : true;
    }

    public static final boolean isCapturedByValue(FirVariableSymbol<?> firVariableSymbol) {
        firVariableSymbol.getClass();
        return firVariableSymbol.isVal() && (firVariableSymbol instanceof FirLocalPropertySymbol);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isInline(ControlFlowGraph controlFlowGraph, FirBasedSymbol<?> firBasedSymbol) {
        FirDeclaration declaration = controlFlowGraph.getDeclaration();
        if (Intrinsics.areEqual(declaration != null ? declaration.getSymbol() : null, firBasedSymbol)) {
            return true;
        }
        if (declaration == null || !getEvaluatedInline(declaration)) {
            return false;
        }
        List<CFGNode<?>> previousNodes = controlFlowGraph.getEnterNode().getPreviousNodes();
        if ((previousNodes instanceof Collection) && previousNodes.isEmpty()) {
            return true;
        }
        Iterator<T> it = previousNodes.iterator();
        while (it.hasNext()) {
            if (!isInline(((CFGNode) it.next()).getOwner(), firBasedSymbol)) {
                return false;
            }
        }
        return true;
    }
}
