package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.LogicOperationKind;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirDoWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirWhileLoop;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirElseIfTrueCondition;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.renderer.FirCallNoArgumentsRenderer;
import org.jetbrains.kotlin.fir.renderer.FirRenderer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\bH\u0002\"\u0014\u0010\u0003\u001a\u00020\u00048BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"render", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "CfgRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer;", "getCfgRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirRenderer;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CFGNodeRendererKt {
    private static final FirRenderer getCfgRenderer() {
        return new FirRenderer(null, null, null, new FirCallNoArgumentsRenderer(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, false, 16777205, null);
    }

    public static final String render(CFGNode<?> cFGNode) {
        cFGNode.getClass();
        if (cFGNode instanceof FunctionEnterNode) {
            return "Enter function " + ((FunctionEnterNode) cFGNode).getOwner().getName();
        }
        if (cFGNode instanceof FunctionExitNode) {
            return "Exit function " + ((FunctionExitNode) cFGNode).getOwner().getName();
        }
        if (cFGNode instanceof LocalFunctionDeclarationNode) {
            return "Local function declaration";
        }
        if (cFGNode instanceof BlockEnterNode) {
            return "Enter block";
        }
        if (cFGNode instanceof BlockExitNode) {
            return "Exit block";
        }
        if (cFGNode instanceof WhenEnterNode) {
            return "Enter when";
        }
        if (cFGNode instanceof WhenBranchConditionEnterNode) {
            return "Enter when branch condition ".concat(((WhenBranchConditionEnterNode) cFGNode).getFir().getCondition() instanceof FirElseIfTrueCondition ? "\"else\"" : Argument.Delimiters.none);
        }
        if (cFGNode instanceof WhenBranchConditionExitNode) {
            return "Exit when branch condition";
        }
        if (cFGNode instanceof WhenBranchResultEnterNode) {
            return "Enter when branch result";
        }
        if (cFGNode instanceof WhenBranchResultExitNode) {
            return "Exit when branch result";
        }
        if (cFGNode instanceof WhenSyntheticElseBranchNode) {
            return "Synthetic else branch";
        }
        if (cFGNode instanceof WhenExitNode) {
            return "Exit when";
        }
        if (cFGNode instanceof LoopEnterNode) {
            return "Enter " + type(((LoopEnterNode) cFGNode).getFir()) + " loop";
        }
        if (cFGNode instanceof LoopBlockEnterNode) {
            return "Enter loop block";
        }
        if (cFGNode instanceof LoopBlockExitNode) {
            return "Exit loop block";
        }
        if (cFGNode instanceof LoopConditionEnterNode) {
            return "Enter loop condition";
        }
        if (cFGNode instanceof LoopConditionExitNode) {
            return "Exit loop condition";
        }
        if (cFGNode instanceof LoopExitNode) {
            return "Exit " + type(((LoopExitNode) cFGNode).getFir()) + " loop";
        }
        if (cFGNode instanceof QualifiedAccessNode) {
            return "Access variable " + FirRenderer.renderElementAsString$default(getCfgRenderer(), ((QualifiedAccessNode) cFGNode).getFir().getCalleeReference(), false, 2, null);
        }
        if (cFGNode instanceof ResolvedQualifierNode) {
            return "Access qualifier " + ((ResolvedQualifierNode) cFGNode).getFir().getClassId();
        }
        if (cFGNode instanceof ComparisonExpressionNode) {
            return "Comparison " + ((ComparisonExpressionNode) cFGNode).getFir().getOperation().getOperator();
        }
        if (cFGNode instanceof TypeOperatorCallNode) {
            return "Type operator: \"" + FirRenderer.renderElementAsString$default(getCfgRenderer(), ((TypeOperatorCallNode) cFGNode).getFir(), false, 2, null) + '\"';
        }
        if (cFGNode instanceof SmartCastExpressionExitNode) {
            return "Smart cast: \"" + FirRenderer.renderElementAsString$default(getCfgRenderer(), ((SmartCastExpressionExitNode) cFGNode).getFir(), false, 2, null) + '\"';
        }
        if (cFGNode instanceof EqualityOperatorCallNode) {
            return "Equality operator " + ((EqualityOperatorCallNode) cFGNode).getFir().getOperation().getOperator();
        }
        if (cFGNode instanceof JumpNode) {
            return "Jump: " + UtilsKt.render(((JumpNode) cFGNode).getFir());
        }
        if (cFGNode instanceof StubNode) {
            return "Stub";
        }
        if (cFGNode instanceof CheckNotNullCallNode) {
            return "Check not null: " + FirRenderer.renderElementAsString$default(getCfgRenderer(), ((CheckNotNullCallNode) cFGNode).getFir(), false, 2, null);
        }
        if (cFGNode instanceof LiteralExpressionNode) {
            return "Const: " + UtilsKt.render(((LiteralExpressionNode) cFGNode).getFir());
        }
        if (cFGNode instanceof VariableDeclarationEnterNode) {
            return "Enter variable declaration: " + getCfgRenderer().renderAsCallableDeclarationString(((VariableDeclarationEnterNode) cFGNode).getFir());
        }
        if (cFGNode instanceof VariableDeclarationExitNode) {
            return "Exit variable declaration: " + getCfgRenderer().renderAsCallableDeclarationString(((VariableDeclarationExitNode) cFGNode).getFir());
        }
        if (cFGNode instanceof VariableAssignmentNode) {
            StringBuilder sb = new StringBuilder("Assignment: ");
            FirReference calleeReference = ReferenceUtilsKt.getCalleeReference(((VariableAssignmentNode) cFGNode).getFir());
            sb.append(calleeReference != null ? FirRenderer.renderElementAsString$default(getCfgRenderer(), calleeReference, false, 2, null) : null);
            return sb.toString();
        }
        if (cFGNode instanceof FunctionCallArgumentsEnterNode) {
            return "Function call arguments enter";
        }
        if (cFGNode instanceof FunctionCallArgumentsExitNode) {
            return "Function call arguments exit";
        }
        if (cFGNode instanceof FunctionCallEnterNode) {
            return "Function call enter: " + FirRenderer.renderElementAsString$default(getCfgRenderer(), ((FunctionCallEnterNode) cFGNode).getFir(), false, 2, null);
        }
        if (cFGNode instanceof FunctionCallExitNode) {
            return "Function call exit: " + FirRenderer.renderElementAsString$default(getCfgRenderer(), ((FunctionCallExitNode) cFGNode).getFir(), false, 2, null);
        }
        if (cFGNode instanceof DelegatedConstructorCallNode) {
            return "Delegated constructor call: " + FirRenderer.renderElementAsString$default(getCfgRenderer(), ((DelegatedConstructorCallNode) cFGNode).getFir(), false, 2, null);
        }
        if (cFGNode instanceof StringConcatenationCallNode) {
            return "String concatenation call: " + FirRenderer.renderElementAsString$default(getCfgRenderer(), ((StringConcatenationCallNode) cFGNode).getFir(), false, 2, null);
        }
        if (cFGNode instanceof ThrowExceptionNode) {
            return "Throw: " + FirRenderer.renderElementAsString$default(getCfgRenderer(), ((ThrowExceptionNode) cFGNode).getFir(), false, 2, null);
        }
        if (cFGNode instanceof TryExpressionEnterNode) {
            return "Try expression enter";
        }
        if (cFGNode instanceof TryMainBlockEnterNode) {
            return "Try main block enter";
        }
        if (cFGNode instanceof TryMainBlockExitNode) {
            return "Try main block exit";
        }
        if (cFGNode instanceof CatchClauseEnterNode) {
            return "Catch enter";
        }
        if (cFGNode instanceof CatchClauseExitNode) {
            return "Catch exit";
        }
        if (cFGNode instanceof FinallyBlockEnterNode) {
            return "Enter finally";
        }
        if (cFGNode instanceof FinallyBlockExitNode) {
            return "Exit finally";
        }
        if (cFGNode instanceof TryExpressionExitNode) {
            return "Try expression exit";
        }
        if (cFGNode instanceof BooleanOperatorEnterNode) {
            return "Enter ".concat(((BooleanOperatorEnterNode) cFGNode).getFir().getKind() == LogicOperationKind.AND ? "&&" : "||");
        }
        if (cFGNode instanceof BooleanOperatorExitLeftOperandNode) {
            return "Exit left part of ".concat(((BooleanOperatorExitLeftOperandNode) cFGNode).getFir().getKind() == LogicOperationKind.AND ? "&&" : "||");
        }
        if (cFGNode instanceof BooleanOperatorEnterRightOperandNode) {
            return "Enter right part of ".concat(((BooleanOperatorEnterRightOperandNode) cFGNode).getFir().getKind() == LogicOperationKind.AND ? "&&" : "||");
        }
        if (cFGNode instanceof BooleanOperatorExitNode) {
            return "Exit ".concat(((BooleanOperatorExitNode) cFGNode).getFir().getKind() == LogicOperationKind.AND ? "&&" : "||");
        }
        if (cFGNode instanceof PropertyInitializerEnterNode) {
            return "Enter property";
        }
        if (cFGNode instanceof PropertyInitializerExitNode) {
            return "Exit property";
        }
        if (cFGNode instanceof DelegateExpressionExitNode) {
            return "Exit property delegate";
        }
        if (cFGNode instanceof FieldInitializerEnterNode) {
            return "Enter field";
        }
        if (cFGNode instanceof FieldInitializerExitNode) {
            return "Exit field";
        }
        if (cFGNode instanceof InitBlockEnterNode) {
            return "Enter init block";
        }
        if (cFGNode instanceof InitBlockExitNode) {
            return "Exit init block";
        }
        if (cFGNode instanceof EnterSafeCallNode) {
            return "Enter safe call";
        }
        if (cFGNode instanceof ExitSafeCallNode) {
            return "Exit safe call";
        }
        if (cFGNode instanceof WhenSubjectExpressionExitNode) {
            return "Exit $subj";
        }
        if (cFGNode instanceof SplitPostponedLambdasNode) {
            return "Postponed enter to lambda";
        }
        if (cFGNode instanceof PostponedLambdaExitNode) {
            return "Postponed exit from lambda";
        }
        if (cFGNode instanceof MergePostponedLambdaExitsNode) {
            return "Merge postponed lambda exits";
        }
        if (cFGNode instanceof AnonymousFunctionExpressionNode) {
            return "Exit anonymous function expression";
        }
        if (cFGNode instanceof AnonymousFunctionCaptureNode) {
            return "Anonymous function capture";
        }
        if (cFGNode instanceof FileEnterNode) {
            return "Enter file " + ((FileEnterNode) cFGNode).getFir().getName();
        }
        if (cFGNode instanceof FileExitNode) {
            return "Exit file " + ((FileExitNode) cFGNode).getFir().getName();
        }
        if (cFGNode instanceof ClassEnterNode) {
            return "Enter class " + ((ClassEnterNode) cFGNode).getOwner().getName();
        }
        if (cFGNode instanceof ClassExitNode) {
            return "Exit class " + ((ClassExitNode) cFGNode).getOwner().getName();
        }
        if (cFGNode instanceof LocalClassExitNode) {
            return "Local class declaration";
        }
        if (cFGNode instanceof AnonymousObjectEnterNode) {
            return "Enter anonymous object";
        }
        if (cFGNode instanceof AnonymousObjectExpressionExitNode) {
            return "Exit anonymous object expression";
        }
        if (cFGNode instanceof ScriptEnterNode) {
            return "Enter class " + ((ScriptEnterNode) cFGNode).getFir().getName();
        }
        if (cFGNode instanceof ScriptExitNode) {
            return "Exit class " + ((ScriptExitNode) cFGNode).getFir().getName();
        }
        if (cFGNode instanceof CodeFragmentEnterNode) {
            return "Enter code fragment";
        }
        if (cFGNode instanceof CodeFragmentExitNode) {
            return "Exit code fragment";
        }
        if (cFGNode instanceof FakeExpressionEnterNode) {
            return "Enter fake expression";
        }
        if (cFGNode instanceof EnterValueParameterNode) {
            return "Enter default value of " + ((EnterValueParameterNode) cFGNode).getFir().getName();
        }
        if (cFGNode instanceof EnterDefaultArgumentsNode) {
            return "Enter default value of " + ((EnterDefaultArgumentsNode) cFGNode).getFir().getName();
        }
        if (cFGNode instanceof ExitDefaultArgumentsNode) {
            return "Exit default value of " + ((ExitDefaultArgumentsNode) cFGNode).getFir().getName();
        }
        if (cFGNode instanceof ExitValueParameterNode) {
            return "Exit default value of " + ((ExitValueParameterNode) cFGNode).getFir().getName();
        }
        if (cFGNode instanceof ElvisLhsExitNode) {
            return "Exit lhs of ?:";
        }
        if (cFGNode instanceof ElvisLhsIsNotNullNode) {
            return "Lhs of ?: is not null";
        }
        if (cFGNode instanceof ElvisRhsEnterNode) {
            return "Enter rhs of ?:";
        }
        if (cFGNode instanceof ElvisExitNode) {
            return "Exit ?:";
        }
        if (cFGNode instanceof CallableReferenceNode) {
            return "Callable reference: " + FirRenderer.renderElementAsString$default(getCfgRenderer(), ((CallableReferenceNode) cFGNode).getFir(), false, 2, null);
        }
        if (cFGNode instanceof GetClassCallNode) {
            return "::class call";
        }
        bu8.a();
        return null;
    }

    private static final String type(FirLoop firLoop) {
        if (firLoop instanceof FirWhileLoop) {
            return "while";
        }
        if (firLoop instanceof FirDoWhileLoop) {
            return "do-while";
        }
        j2d.a();
        return null;
    }
}
