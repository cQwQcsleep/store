package com.sun.org.apache.xpath.internal.compiler;

import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xml.internal.utils.QName;
import com.sun.org.apache.xml.internal.utils.SAXSourceLocator;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.axes.UnionPathIterator;
import com.sun.org.apache.xpath.internal.axes.WalkerFactory;
import com.sun.org.apache.xpath.internal.functions.FuncExtFunction;
import com.sun.org.apache.xpath.internal.functions.FuncExtFunctionAvailable;
import com.sun.org.apache.xpath.internal.functions.Function;
import com.sun.org.apache.xpath.internal.functions.WrongNumberArgsException;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XString;
import com.sun.org.apache.xpath.internal.operations.And;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.org.apache.xpath.internal.operations.Div;
import com.sun.org.apache.xpath.internal.operations.Equals;
import com.sun.org.apache.xpath.internal.operations.Gt;
import com.sun.org.apache.xpath.internal.operations.Gte;
import com.sun.org.apache.xpath.internal.operations.Lt;
import com.sun.org.apache.xpath.internal.operations.Lte;
import com.sun.org.apache.xpath.internal.operations.Minus;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.org.apache.xpath.internal.operations.Mult;
import com.sun.org.apache.xpath.internal.operations.Neg;
import com.sun.org.apache.xpath.internal.operations.NotEquals;
import com.sun.org.apache.xpath.internal.operations.Number;
import com.sun.org.apache.xpath.internal.operations.Operation;
import com.sun.org.apache.xpath.internal.operations.Or;
import com.sun.org.apache.xpath.internal.operations.Plus;
import com.sun.org.apache.xpath.internal.operations.String;
import com.sun.org.apache.xpath.internal.operations.UnaryOperation;
import com.sun.org.apache.xpath.internal.operations.Variable;
import com.sun.org.apache.xpath.internal.patterns.FunctionPattern;
import com.sun.org.apache.xpath.internal.patterns.StepPattern;
import com.sun.org.apache.xpath.internal.patterns.UnionPattern;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;
import org.eclipse.jdt.internal.compiler.classfmt.ClassFileConstants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Compiler extends OpMap {
    private static final boolean DEBUG = false;
    private static long s_nextMethodId;
    int countOp;
    private int locPathDepth;
    private PrefixResolver m_currentPrefixResolver;
    ErrorListener m_errorHandler;
    private FunctionTable m_functionTable;
    SourceLocator m_locator;

    public Compiler(ErrorListener errorListener, SourceLocator sourceLocator, FunctionTable functionTable) {
        this.locPathDepth = -1;
        this.m_currentPrefixResolver = null;
        this.m_errorHandler = errorListener;
        this.m_locator = sourceLocator;
        this.m_functionTable = functionTable;
    }

    private Expression compile(int i) throws TransformerException {
        switch (getOp(i)) {
            case 1:
                return compile(i + 2);
            case 2:
                return or(i);
            case 3:
                return and(i);
            case 4:
                return notequals(i);
            case 5:
                return equals(i);
            case 6:
                return lte(i);
            case 7:
                return lt(i);
            case 8:
                return gte(i);
            case 9:
                return gt(i);
            case 10:
                return plus(i);
            case 11:
                return minus(i);
            case 12:
                return mult(i);
            case 13:
                return div(i);
            case 14:
                return mod(i);
            case 15:
                error("ER_UNKNOWN_OPCODE", new Object[]{"quo"});
                return null;
            case 16:
                return neg(i);
            case 17:
                return string(i);
            case 18:
                return bool(i);
            case 19:
                return number(i);
            case 20:
                return union(i);
            case 21:
                return literal(i);
            case 22:
                return variable(i);
            case 23:
                return group(i);
            case 24:
                return compileExtension(i);
            case 25:
                return compileFunction(i);
            case 26:
                return arg(i);
            case 27:
                return numberlit(i);
            case 28:
                return locationPath(i);
            case 29:
                return null;
            case 30:
                return matchPattern(i + 2);
            case 31:
                return locationPathPattern(i);
            default:
                error("ER_UNKNOWN_OPCODE", new Object[]{Integer.toString(getOp(i))});
                return null;
        }
    }

    private Expression compileExtension(int i) throws TransformerException {
        int op = (getOp(i + 1) + i) - 1;
        int firstChildPos = OpMap.getFirstChildPos(i);
        String str = (String) getTokenQueue().elementAt(getOp(firstChildPos));
        String str2 = (String) getTokenQueue().elementAt(getOp(firstChildPos + 1));
        int i2 = firstChildPos + 2;
        FuncExtFunction funcExtFunction = new FuncExtFunction(str, str2, String.valueOf(getNextMethodId()));
        int i3 = 0;
        while (i2 < op) {
            try {
                int nextOpPos = getNextOpPos(i2);
                funcExtFunction.setArg(compile(i2), i3);
                i3++;
                i2 = nextOpPos;
            } catch (WrongNumberArgsException unused) {
            }
        }
        return funcExtFunction;
    }

    private Expression compileOperation(Operation operation, int i) throws TransformerException {
        this.countOp++;
        int firstChildPos = OpMap.getFirstChildPos(i);
        operation.setLeftRight(compile(firstChildPos), compile(getNextOpPos(firstChildPos)));
        return operation;
    }

    private void compilePredicates(int i, Expression[] expressionArr) throws TransformerException {
        int i2 = 0;
        while (29 == getOp(i)) {
            expressionArr[i2] = predicate(i);
            i = getNextOpPos(i);
            i2++;
        }
    }

    private Expression compileUnary(UnaryOperation unaryOperation, int i) throws TransformerException {
        unaryOperation.setRight(compile(OpMap.getFirstChildPos(i)));
        return unaryOperation;
    }

    private synchronized long getNextMethodId() {
        long j;
        try {
            if (s_nextMethodId == ClassFileConstants.JDK_DEFERRED) {
                s_nextMethodId = 0L;
            }
            j = s_nextMethodId;
            s_nextMethodId = 1 + j;
        } catch (Throwable th) {
            throw th;
        }
        return j;
    }

    public Expression and(int i) throws TransformerException {
        return compileOperation(new And(), i);
    }

    public Expression arg(int i) throws TransformerException {
        return compile(i + 2);
    }

    public void assertion(boolean z, String str) {
        if (z) {
            return;
        }
        f63.a(XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[]{str}));
    }

    public Expression bool(int i) throws TransformerException {
        return compileUnary(new Bool(), i);
    }

    public Expression compileExpression(int i) throws TransformerException {
        try {
            this.countOp = 0;
            return compile(i);
        } catch (StackOverflowError unused) {
            error("ER_COMPILATION_TOO_MANY_OPERATION", new Object[]{Integer.valueOf(this.countOp)});
            return null;
        }
    }

    public Expression compileFunction(int i) throws TransformerException {
        int op = (getOp(i + 1) + i) - 1;
        int firstChildPos = OpMap.getFirstChildPos(i);
        int op2 = getOp(firstChildPos);
        int nextOpPos = firstChildPos + 1;
        if (-1 == op2) {
            error("ER_FUNCTION_TOKEN_NOT_FOUND", null);
            return null;
        }
        Function function = this.m_functionTable.getFunction(op2);
        if (function instanceof FuncExtFunctionAvailable) {
            ((FuncExtFunctionAvailable) function).setFunctionTable(this.m_functionTable);
        }
        function.postCompileStep(this);
        int i2 = 0;
        while (nextOpPos < op) {
            try {
                function.setArg(compile(nextOpPos), i2);
                nextOpPos = getNextOpPos(nextOpPos);
                i2++;
            } catch (WrongNumberArgsException e) {
                this.m_errorHandler.fatalError(new TransformerException(XPATHMessages.createXPATHMessage("ER_ONLY_ALLOWS", new Object[]{this.m_functionTable.getFunctionName(op2), e.getMessage()}), this.m_locator));
                return function;
            }
        }
        function.checkNumberArgs(i2);
        return function;
    }

    public int countPredicates(int i) throws TransformerException {
        int i2 = 0;
        while (29 == getOp(i)) {
            i2++;
            i = getNextOpPos(i);
        }
        return i2;
    }

    public Expression div(int i) throws TransformerException {
        return compileOperation(new Div(), i);
    }

    public Expression equals(int i) throws TransformerException {
        return compileOperation(new Equals(), i);
    }

    @Override // com.sun.org.apache.xpath.internal.compiler.OpMap
    public void error(String str, Object[] objArr) throws TransformerException {
        String strCreateXPATHMessage = XPATHMessages.createXPATHMessage(str, objArr);
        ErrorListener errorListener = this.m_errorHandler;
        if (errorListener == null) {
            throw new TransformerException(strCreateXPATHMessage, (SAXSourceLocator) this.m_locator);
        }
        errorListener.fatalError(new TransformerException(strCreateXPATHMessage, this.m_locator));
    }

    public Expression[] getCompiledPredicates(int i) throws TransformerException {
        int iCountPredicates = countPredicates(i);
        if (iCountPredicates <= 0) {
            return null;
        }
        Expression[] expressionArr = new Expression[iCountPredicates];
        compilePredicates(i, expressionArr);
        return expressionArr;
    }

    public FunctionTable getFunctionTable() {
        return this.m_functionTable;
    }

    public int getLocationPathDepth() {
        return this.locPathDepth;
    }

    public PrefixResolver getNamespaceContext() {
        return this.m_currentPrefixResolver;
    }

    public int getWhatToShow(int i) {
        int op = getOp(i);
        int op2 = getOp(i + 3);
        if (op2 == 34) {
            if (op != 39) {
                if (op == 49) {
                    return 4096;
                }
                if (op != 51) {
                    return 1;
                }
            }
            return 2;
        }
        if (op2 == 35) {
            return 1280;
        }
        switch (op2) {
            case OpCodes.NODETYPE_COMMENT /* 1030 */:
                return 128;
            case OpCodes.NODETYPE_TEXT /* 1031 */:
                return 12;
            case OpCodes.NODETYPE_PI /* 1032 */:
                return 64;
            case OpCodes.NODETYPE_NODE /* 1033 */:
                if (op != 38) {
                    if (op != 39) {
                        if (op != 42) {
                            if (op != 51) {
                                if (op != 48) {
                                    if (op != 49) {
                                        return getOp(0) == 30 ? -1283 : -3;
                                    }
                                    return 4096;
                                }
                            }
                        }
                    }
                    return 2;
                }
                return -1;
            case OpCodes.NODETYPE_FUNCTEST /* 1034 */:
                return 65536;
            default:
                return -1;
        }
    }

    public Expression group(int i) throws TransformerException {
        return compile(i + 2);
    }

    public Expression gt(int i) throws TransformerException {
        return compileOperation(new Gt(), i);
    }

    public Expression gte(int i) throws TransformerException {
        return compileOperation(new Gte(), i);
    }

    public Expression literal(int i) {
        return (XString) getTokenQueue().elementAt(getOp(OpMap.getFirstChildPos(i)));
    }

    public Expression locationPath(int i) throws TransformerException {
        int i2 = this.locPathDepth + 1;
        this.locPathDepth = i2;
        try {
            return (Expression) WalkerFactory.newDTMIterator(this, i, i2 == 0);
        } finally {
            this.locPathDepth--;
        }
    }

    public Expression locationPathPattern(int i) throws TransformerException {
        return stepPattern(OpMap.getFirstChildPos(i), 0, null);
    }

    public Expression lt(int i) throws TransformerException {
        return compileOperation(new Lt(), i);
    }

    public Expression lte(int i) throws TransformerException {
        return compileOperation(new Lte(), i);
    }

    public Expression matchPattern(int i) throws TransformerException {
        this.locPathDepth++;
        int i2 = 0;
        int nextOpPos = i;
        int i3 = 0;
        while (getOp(nextOpPos) == 31) {
            try {
                nextOpPos = getNextOpPos(nextOpPos);
                i3++;
            } catch (Throwable th) {
                this.locPathDepth--;
                throw th;
            }
        }
        if (i3 == 1) {
            Expression expressionCompile = compile(i);
            this.locPathDepth--;
            return expressionCompile;
        }
        UnionPattern unionPattern = new UnionPattern();
        StepPattern[] stepPatternArr = new StepPattern[i3];
        while (getOp(i) == 31) {
            int nextOpPos2 = getNextOpPos(i);
            stepPatternArr[i2] = (StepPattern) compile(i);
            i2++;
            i = nextOpPos2;
        }
        unionPattern.setPatterns(stepPatternArr);
        this.locPathDepth--;
        return unionPattern;
    }

    public Expression minus(int i) throws TransformerException {
        return compileOperation(new Minus(), i);
    }

    public Expression mod(int i) throws TransformerException {
        return compileOperation(new Mod(), i);
    }

    public Expression mult(int i) throws TransformerException {
        return compileOperation(new Mult(), i);
    }

    public Expression neg(int i) throws TransformerException {
        return compileUnary(new Neg(), i);
    }

    public Expression notequals(int i) throws TransformerException {
        return compileOperation(new NotEquals(), i);
    }

    public Expression number(int i) throws TransformerException {
        return compileUnary(new Number(), i);
    }

    public Expression numberlit(int i) {
        return (XNumber) getTokenQueue().elementAt(getOp(OpMap.getFirstChildPos(i)));
    }

    public Expression or(int i) throws TransformerException {
        return compileOperation(new Or(), i);
    }

    public Expression plus(int i) throws TransformerException {
        return compileOperation(new Plus(), i);
    }

    public Expression predicate(int i) throws TransformerException {
        return compile(i + 2);
    }

    public void setNamespaceContext(PrefixResolver prefixResolver) {
        this.m_currentPrefixResolver = prefixResolver;
    }

    public StepPattern stepPattern(int i, int i2, StepPattern stepPattern) throws TransformerException {
        int op;
        StepPattern functionPattern;
        int firstChildPosOfStep;
        int op2 = getOp(i);
        if (-1 == op2) {
            return null;
        }
        int nextOpPos = getNextOpPos(i);
        if (op2 != 25) {
            switch (op2) {
                case 50:
                    op = getArgLengthOfStep(i);
                    i = OpMap.getFirstChildPosOfStep(i);
                    functionPattern = new StepPattern(1280, 10, 3);
                    break;
                case 51:
                    op = getArgLengthOfStep(i);
                    firstChildPosOfStep = OpMap.getFirstChildPosOfStep(i);
                    functionPattern = new StepPattern(2, getStepNS(i), getStepLocalName(i), 10, 2);
                    i = firstChildPosOfStep;
                    break;
                case 52:
                    op = getArgLengthOfStep(i);
                    firstChildPosOfStep = OpMap.getFirstChildPosOfStep(i);
                    getWhatToShow(i);
                    functionPattern = new StepPattern(getWhatToShow(i), getStepNS(i), getStepLocalName(i), 0, 3);
                    i = firstChildPosOfStep;
                    break;
                case 53:
                    op = getArgLengthOfStep(i);
                    firstChildPosOfStep = OpMap.getFirstChildPosOfStep(i);
                    functionPattern = new StepPattern(getWhatToShow(i), getStepNS(i), getStepLocalName(i), 10, 3);
                    i = firstChildPosOfStep;
                    break;
                default:
                    error("ER_UNKNOWN_MATCH_OPERATION", null);
                    return null;
            }
        } else {
            op = getOp(i + 1);
            functionPattern = new FunctionPattern(compileFunction(i), 10, 3);
        }
        functionPattern.setPredicates(getCompiledPredicates(i + op));
        if (stepPattern != null) {
            functionPattern.setRelativePathPattern(stepPattern);
        }
        StepPattern stepPattern2 = stepPattern(nextOpPos, i2 + 1, functionPattern);
        return stepPattern2 != null ? stepPattern2 : functionPattern;
    }

    public Expression string(int i) throws TransformerException {
        return compileUnary(new String(), i);
    }

    public Expression union(int i) throws TransformerException {
        this.locPathDepth++;
        try {
            return UnionPathIterator.createUnionIterator(this, i);
        } finally {
            this.locPathDepth--;
        }
    }

    public Expression variable(int i) throws TransformerException {
        Variable variable = new Variable();
        int firstChildPos = OpMap.getFirstChildPos(i);
        int op = getOp(firstChildPos);
        variable.setQName(new QName(-2 == op ? null : (String) getTokenQueue().elementAt(op), (String) getTokenQueue().elementAt(getOp(firstChildPos + 1))));
        return variable;
    }

    public void warn(String str, Object[] objArr) throws TransformerException {
        String strCreateXPATHWarning = XPATHMessages.createXPATHWarning(str, objArr);
        ErrorListener errorListener = this.m_errorHandler;
        if (errorListener != null) {
            errorListener.warning(new TransformerException(strCreateXPATHWarning, this.m_locator));
            return;
        }
        System.out.println(strCreateXPATHWarning + "; file " + this.m_locator.getSystemId() + "; line " + this.m_locator.getLineNumber() + "; column " + this.m_locator.getColumnNumber());
    }

    public Compiler() {
        this.locPathDepth = -1;
        this.m_currentPrefixResolver = null;
        this.m_errorHandler = null;
        this.m_locator = null;
    }
}
