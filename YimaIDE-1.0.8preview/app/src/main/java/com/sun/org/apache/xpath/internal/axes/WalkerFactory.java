package com.sun.org.apache.xpath.internal.axes;

import com.sun.org.apache.xml.internal.dtm.DTMIterator;
import com.sun.org.apache.xpath.internal.Expression;
import com.sun.org.apache.xpath.internal.compiler.Compiler;
import com.sun.org.apache.xpath.internal.compiler.OpMap;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.patterns.ContextMatchStepPattern;
import com.sun.org.apache.xpath.internal.patterns.FunctionPattern;
import com.sun.org.apache.xpath.internal.patterns.StepPattern;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import javax.xml.transform.TransformerException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class WalkerFactory {
    public static final int BITMASK_TRAVERSES_OUTSIDE_SUBTREE = 234381312;
    public static final int BITS_COUNT = 255;
    public static final int BITS_RESERVED = 3840;
    public static final int BIT_ANCESTOR = 8192;
    public static final int BIT_ANCESTOR_OR_SELF = 16384;
    public static final int BIT_ANY_DESCENDANT_FROM_ROOT = 536870912;
    public static final int BIT_ATTRIBUTE = 32768;
    public static final int BIT_BACKWARDS_SELF = 268435456;
    public static final int BIT_CHILD = 65536;
    public static final int BIT_DESCENDANT = 131072;
    public static final int BIT_DESCENDANT_OR_SELF = 262144;
    public static final int BIT_FILTER = 67108864;
    public static final int BIT_FOLLOWING = 524288;
    public static final int BIT_FOLLOWING_SIBLING = 1048576;
    public static final int BIT_MATCH_PATTERN = Integer.MIN_VALUE;
    public static final int BIT_NAMESPACE = 2097152;
    public static final int BIT_NODETEST_ANY = 1073741824;
    public static final int BIT_PARENT = 4194304;
    public static final int BIT_PRECEDING = 8388608;
    public static final int BIT_PRECEDING_SIBLING = 16777216;
    public static final int BIT_PREDICATE = 4096;
    public static final int BIT_ROOT = 134217728;
    public static final int BIT_SELF = 33554432;
    static final boolean DEBUG_ITERATOR_CREATION = false;
    static final boolean DEBUG_PATTERN_CREATION = false;
    static final boolean DEBUG_WALKER_CREATION = false;

    /* JADX WARN: Code duplicated, block: B:38:0x0079  */
    private static int analyze(Compiler compiler, int i, int i2) throws TransformerException {
        int i3;
        int i4 = 0;
        int i5 = 0;
        do {
            int op = compiler.getOp(i);
            if (-1 != op) {
                i4++;
                if (analyzePredicate(compiler, i, op)) {
                    i5 |= 4096;
                }
                switch (op) {
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                        i3 = 67108864;
                        i5 |= i3;
                        if (1033 == compiler.getOp(i + 3)) {
                            i5 |= 1073741824;
                        }
                        i = compiler.getNextStepPos(i);
                        break;
                    default:
                        switch (op) {
                            case 37:
                                i5 |= 8192;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 38:
                                i5 |= 16384;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 39:
                                i3 = 32768;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 40:
                                i3 = 65536;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 41:
                                i3 = 131072;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 42:
                                if (2 == i4 && 134217728 == i5) {
                                    i5 |= 536870912;
                                }
                                i3 = 262144;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 43:
                                i3 = 524288;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 44:
                                i3 = 1048576;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 45:
                                i3 = 4194304;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 46:
                                i3 = 8388608;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 47:
                                i3 = 16777216;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 48:
                                i3 = 33554432;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 49:
                                i3 = 2097152;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 50:
                                i5 |= 134217728;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 51:
                                i3 = -2147450880;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 52:
                                i3 = -2147475456;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 53:
                                i3 = -2143289344;
                                i5 |= i3;
                                if (1033 == compiler.getOp(i + 3)) {
                                    i5 |= 1073741824;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            default:
                                f63.a(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[]{Integer.toString(op)}));
                                return 0;
                        }
                        break;
                }
            }
            return (i4 & 255) | i5;
        } while (i >= 0);
        return (i4 & 255) | i5;
    }

    public static boolean analyzePredicate(Compiler compiler, int i, int i2) throws TransformerException {
        switch (i2) {
            case 22:
            case 23:
            case 24:
            case 25:
                compiler.getArgLength(i);
                break;
            default:
                compiler.getArgLengthOfStep(i);
                break;
        }
        return compiler.countPredicates(compiler.getFirstPredicateOpPos(i)) > 0;
    }

    public static boolean canCrissCross(int i) {
        if (walksSelfOnly(i)) {
            return false;
        }
        return ((walksDownOnly(i) && !canSkipSubtrees(i)) || walksChildrenAndExtraAndSelfOnly(i) || walksDescendantsAndExtraAndSelfOnly(i) || walksUpOnly(i) || walksExtraNodesOnly(i) || !walksSubtree(i) || (!walksSideways(i) && !walksUp(i) && !canSkipSubtrees(i))) ? false : true;
    }

    public static boolean canSkipSubtrees(int i) {
        return walksSideways(i) | isSet(i, 65536);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:29:0x0077  */
    private static StepPattern createDefaultStepPattern(Compiler compiler, int i, MatchPatternIterator matchPatternIterator, int i2, StepPattern stepPattern, StepPattern stepPattern2) throws TransformerException {
        Expression expressionCompileExpression;
        StepPattern functionPattern;
        int i3;
        int i4;
        int i5;
        int i6;
        int op = compiler.getOp(i);
        compiler.getWhatToShow(i);
        switch (op) {
            case 22:
            case 23:
            case 24:
            case 25:
                switch (op) {
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                        expressionCompileExpression = compiler.compileExpression(i);
                        break;
                    default:
                        expressionCompileExpression = compiler.compileExpression(i + 2);
                        break;
                }
                i3 = 20;
                functionPattern = new FunctionPattern(expressionCompileExpression, 20, 20);
                i5 = i3;
                i4 = i5;
                if (functionPattern == null) {
                    functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                }
                functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                return functionPattern;
            default:
                i5 = 12;
                i4 = 7;
                functionPattern = null;
                switch (op) {
                    case 37:
                        i5 = 4;
                        i4 = 0;
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    case 38:
                        i5 = 5;
                        i4 = 1;
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    case 39:
                        i6 = 2;
                        i4 = i6;
                        i5 = 10;
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    case 40:
                        i4 = 3;
                        i5 = 10;
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    case 41:
                        i4 = 4;
                        i5 = 0;
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    case 42:
                        i4 = 5;
                        i5 = 1;
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    case 43:
                        i5 = 11;
                        i4 = 6;
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    case 44:
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    case 45:
                        i5 = 3;
                        i4 = 10;
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    case 46:
                        i4 = 11;
                        i5 = 6;
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    case 47:
                        i4 = 12;
                        i5 = 7;
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    case 48:
                        i3 = 13;
                        i5 = i3;
                        i4 = i5;
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    case 49:
                        i6 = 9;
                        i4 = i6;
                        i5 = 10;
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    case 50:
                        i3 = 19;
                        functionPattern = new StepPattern(1280, 19, 19);
                        i5 = i3;
                        i4 = i5;
                        if (functionPattern == null) {
                            functionPattern = new StepPattern(compiler.getWhatToShow(i), compiler.getStepNS(i), compiler.getStepLocalName(i), i5, i4);
                        }
                        functionPattern.setPredicates(compiler.getCompiledPredicates(compiler.getFirstPredicateOpPos(i)));
                        return functionPattern;
                    default:
                        f63.a(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[]{Integer.toString(op)}));
                        return null;
                }
        }
    }

    private static AxesWalker createDefaultWalker(Compiler compiler, int i, WalkingIterator walkingIterator, int i2) {
        AxesWalker filterExprWalker;
        int op = compiler.getOp(i);
        boolean z = true;
        switch (op) {
            case 22:
            case 23:
            case 24:
            case 25:
                filterExprWalker = new FilterExprWalker(walkingIterator);
                break;
            default:
                switch (op) {
                    case 37:
                        filterExprWalker = new ReverseAxesWalker(walkingIterator, 0);
                        break;
                    case 38:
                        filterExprWalker = new ReverseAxesWalker(walkingIterator, 1);
                        break;
                    case 39:
                        filterExprWalker = new AxesWalker(walkingIterator, 2);
                        break;
                    case 40:
                        filterExprWalker = new AxesWalker(walkingIterator, 3);
                        break;
                    case 41:
                        filterExprWalker = new AxesWalker(walkingIterator, 4);
                        break;
                    case 42:
                        filterExprWalker = new AxesWalker(walkingIterator, 5);
                        break;
                    case 43:
                        filterExprWalker = new AxesWalker(walkingIterator, 6);
                        break;
                    case 44:
                        filterExprWalker = new AxesWalker(walkingIterator, 7);
                        break;
                    case 45:
                        filterExprWalker = new ReverseAxesWalker(walkingIterator, 10);
                        break;
                    case 46:
                        filterExprWalker = new ReverseAxesWalker(walkingIterator, 11);
                        break;
                    case 47:
                        filterExprWalker = new ReverseAxesWalker(walkingIterator, 12);
                        break;
                    case 48:
                        filterExprWalker = new AxesWalker(walkingIterator, 13);
                        break;
                    case 49:
                        filterExprWalker = new AxesWalker(walkingIterator, 9);
                        break;
                    case 50:
                        filterExprWalker = new AxesWalker(walkingIterator, 19);
                        break;
                    default:
                        f63.a(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[]{Integer.toString(op)}));
                        return null;
                }
                z = false;
                break;
        }
        if (z) {
            filterExprWalker.initNodeTest(-1);
            return filterExprWalker;
        }
        int whatToShow = compiler.getWhatToShow(i);
        if ((whatToShow & 4163) == 0 || whatToShow == -1) {
            filterExprWalker.initNodeTest(whatToShow);
            return filterExprWalker;
        }
        filterExprWalker.initNodeTest(whatToShow, compiler.getStepNS(i), compiler.getStepLocalName(i));
        return filterExprWalker;
    }

    public static void diagnoseIterator(String str, int i, Compiler compiler) {
        System.out.println(compiler.toString() + ", " + str + ", " + Integer.toBinaryString(i) + ", " + getAnalysisString(i));
    }

    public static boolean functionProximateOrContainsProximate(Compiler compiler, int i) {
        int op = (compiler.getOp(i + 1) + i) - 1;
        int firstChildPos = OpMap.getFirstChildPos(i);
        int op2 = compiler.getOp(firstChildPos);
        if (op2 == 1 || op2 == 2) {
            return true;
        }
        int nextOpPos = firstChildPos + 1;
        while (nextOpPos < op) {
            int i2 = nextOpPos + 2;
            compiler.getOp(i2);
            if (isProximateInnerExpr(compiler, i2)) {
                return true;
            }
            nextOpPos = compiler.getNextOpPos(nextOpPos);
        }
        return false;
    }

    public static int getAnalysisBitFromAxes(int i) {
        switch (i) {
            case 0:
                return 8192;
            case 1:
                return 16384;
            case 2:
                return 32768;
            case 3:
                return 65536;
            case 4:
                return 131072;
            case 5:
                return 262144;
            case 6:
                return 524288;
            case 7:
                return 1048576;
            case 8:
            case 9:
                return 2097152;
            case 10:
                return 4194304;
            case 11:
                return 8388608;
            case 12:
                return 16777216;
            case 13:
                return 33554432;
            case 14:
                return 262144;
            case 15:
            default:
                return 67108864;
            case 16:
            case 17:
            case 18:
                return 536870912;
            case 19:
                return 134217728;
        }
    }

    public static String getAnalysisString(int i) {
        StringBuffer stringBuffer = new StringBuffer("count: ");
        stringBuffer.append(getStepCount(i));
        stringBuffer.append(' ');
        if ((1073741824 & i) != 0) {
            stringBuffer.append("NTANY|");
        }
        if ((i & 4096) != 0) {
            stringBuffer.append("PRED|");
        }
        if ((i & 8192) != 0) {
            stringBuffer.append("ANC|");
        }
        if ((i & 16384) != 0) {
            stringBuffer.append("ANCOS|");
        }
        if ((32768 & i) != 0) {
            stringBuffer.append("ATTR|");
        }
        if ((65536 & i) != 0) {
            stringBuffer.append("CH|");
        }
        if ((131072 & i) != 0) {
            stringBuffer.append("DESC|");
        }
        if ((262144 & i) != 0) {
            stringBuffer.append("DESCOS|");
        }
        if ((524288 & i) != 0) {
            stringBuffer.append("FOL|");
        }
        if ((1048576 & i) != 0) {
            stringBuffer.append("FOLS|");
        }
        if ((2097152 & i) != 0) {
            stringBuffer.append("NS|");
        }
        if ((4194304 & i) != 0) {
            stringBuffer.append("P|");
        }
        if ((8388608 & i) != 0) {
            stringBuffer.append("PREC|");
        }
        if ((16777216 & i) != 0) {
            stringBuffer.append("PRECS|");
        }
        if ((33554432 & i) != 0) {
            stringBuffer.append(".|");
        }
        if ((67108864 & i) != 0) {
            stringBuffer.append("FLT|");
        }
        if ((i & 134217728) != 0) {
            stringBuffer.append("R|");
        }
        return stringBuffer.toString();
    }

    public static int getAxisFromStep(Compiler compiler, int i) throws TransformerException {
        int op = compiler.getOp(i);
        switch (op) {
            case 22:
            case 23:
            case 24:
            case 25:
                return 20;
            default:
                switch (op) {
                    case 37:
                        return 0;
                    case 38:
                        return 1;
                    case 39:
                        return 2;
                    case 40:
                        return 3;
                    case 41:
                        return 4;
                    case 42:
                        return 5;
                    case 43:
                        return 6;
                    case 44:
                        return 7;
                    case 45:
                        return 10;
                    case 46:
                        return 11;
                    case 47:
                        return 12;
                    case 48:
                        return 13;
                    case 49:
                        return 9;
                    case 50:
                        return 19;
                    default:
                        f63.a(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[]{Integer.toString(op)}));
                        return 0;
                }
        }
    }

    public static int getStepCount(int i) {
        return i & 255;
    }

    public static boolean hasPredicate(int i) {
        return (i & 4096) != 0;
    }

    public static boolean isAbsolute(int i) {
        return isSet(i, 201326592);
    }

    public static boolean isDownwardAxisOfMany(int i) {
        return 5 == i || 4 == i || 6 == i || 11 == i;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:37:0x005c A[SYNTHETIC] */
    private static boolean isNaturalDocOrder(Compiler compiler, int i, int i2, int i3) throws TransformerException {
        if (canCrissCross(i3) || isSet(i3, 2097152)) {
            return false;
        }
        if (isSet(i3, 1572864) && isSet(i3, 25165824)) {
            return false;
        }
        int i4 = 0;
        boolean z = false;
        do {
            int op = compiler.getOp(i);
            if (-1 != op) {
                switch (op) {
                    default:
                        switch (op) {
                            case 37:
                            case 38:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 49:
                            case 52:
                            case 53:
                                break;
                            case 39:
                            case 51:
                                if (z) {
                                    return false;
                                }
                                if (compiler.getStepLocalName(i).equals("*")) {
                                    z = true;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            case 40:
                            case 48:
                            case 50:
                                if (z) {
                                    return false;
                                }
                                i = compiler.getNextStepPos(i);
                                break;
                            default:
                                f63.a(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[]{Integer.toString(op)}));
                                return false;
                        }
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                        if (i4 > 0) {
                            return false;
                        }
                        i4++;
                        if (z) {
                            return false;
                        }
                        i = compiler.getNextStepPos(i);
                        break;
                }
            }
            return true;
        } while (i >= 0);
        return true;
    }

    public static boolean isOneStep(int i) {
        return (i & 255) == 1;
    }

    /* JADX WARN: Code duplicated, block: B:35:0x005b  */
    /* JADX WARN: Code duplicated, block: B:37:0x0061  */
    /* JADX WARN: Code duplicated, block: B:55:0x006b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:57:0x0068 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    private static boolean isOptimizableForDescendantIterator(Compiler compiler, int i, int i2) throws TransformerException {
        int nextStepPos;
        int i3 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int stepTestType = 1033;
        while (true) {
            int op = compiler.getOp(i);
            if (-1 != op) {
                if ((stepTestType != 1033 && stepTestType != 35) || (i3 = i3 + 1) > 3 || mightBeProximate(compiler, i, op)) {
                    return false;
                }
                switch (op) {
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                        return false;
                    default:
                        switch (op) {
                            case 37:
                            case 38:
                            case 39:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 49:
                            case 51:
                            case 52:
                            case 53:
                                return false;
                            case 40:
                                if (!z && (!z2 || !z3)) {
                                    return false;
                                }
                                stepTestType = compiler.getStepTestType(i);
                                nextStepPos = compiler.getNextStepPos(i);
                                if (nextStepPos < 0) {
                                    if (-1 == compiler.getOp(nextStepPos) && compiler.countPredicates(i) > 0) {
                                        return false;
                                    }
                                    i = nextStepPos;
                                }
                                break;
                            case 42:
                                z = true;
                            case 41:
                                if (3 == i3) {
                                    return false;
                                }
                                z2 = true;
                                stepTestType = compiler.getStepTestType(i);
                                nextStepPos = compiler.getNextStepPos(i);
                                if (nextStepPos < 0) {
                                    if (-1 == compiler.getOp(nextStepPos)) {
                                    }
                                    i = nextStepPos;
                                }
                                break;
                            case 48:
                                if (1 != i3) {
                                    return false;
                                }
                                z3 = true;
                                stepTestType = compiler.getStepTestType(i);
                                nextStepPos = compiler.getNextStepPos(i);
                                if (nextStepPos < 0) {
                                    if (-1 == compiler.getOp(nextStepPos)) {
                                    }
                                    i = nextStepPos;
                                }
                                break;
                            case 50:
                                if (1 != i3) {
                                    return false;
                                }
                                stepTestType = compiler.getStepTestType(i);
                                nextStepPos = compiler.getNextStepPos(i);
                                if (nextStepPos < 0) {
                                    if (-1 == compiler.getOp(nextStepPos)) {
                                    }
                                    i = nextStepPos;
                                }
                                break;
                            default:
                                f63.a(XPATHMessages.createXPATHMessage("ER_NULL_ERROR_HANDLER", new Object[]{Integer.toString(op)}));
                                return false;
                        }
                        break;
                }
            }
        }
        return true;
    }

    public static boolean isProximateInnerExpr(Compiler compiler, int i) {
        int op = compiler.getOp(i);
        int i2 = i + 2;
        if (op == 21 || op == 22) {
            return false;
        }
        switch (op) {
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                int firstChildPos = OpMap.getFirstChildPos(op);
                return isProximateInnerExpr(compiler, firstChildPos) || isProximateInnerExpr(compiler, compiler.getNextOpPos(firstChildPos));
            default:
                switch (op) {
                    case 25:
                        return functionProximateOrContainsProximate(compiler, i);
                    case 26:
                        return isProximateInnerExpr(compiler, i2);
                    case 27:
                    case 28:
                        return false;
                    default:
                        return true;
                }
        }
    }

    public static boolean isSet(int i, int i2) {
        return (i & i2) != 0;
    }

    public static boolean isWild(int i) {
        return (i & 1073741824) != 0;
    }

    public static AxesWalker loadOneWalker(WalkingIterator walkingIterator, Compiler compiler, int i) throws TransformerException {
        int op = compiler.getOp(i);
        if (op == -1) {
            return null;
        }
        AxesWalker axesWalkerCreateDefaultWalker = createDefaultWalker(compiler, op, walkingIterator, 0);
        axesWalkerCreateDefaultWalker.init(compiler, i, op);
        return axesWalkerCreateDefaultWalker;
    }

    public static StepPattern loadSteps(MatchPatternIterator matchPatternIterator, Compiler compiler, int i, int i2) throws TransformerException {
        int iAnalyze = analyze(compiler, i, i2);
        int nextStepPos = i;
        StepPattern stepPatternCreateDefaultStepPattern = null;
        StepPattern stepPattern = null;
        StepPattern stepPattern2 = null;
        while (-1 != compiler.getOp(nextStepPos)) {
            MatchPatternIterator matchPatternIterator2 = matchPatternIterator;
            Compiler compiler2 = compiler;
            stepPatternCreateDefaultStepPattern = createDefaultStepPattern(compiler2, nextStepPos, matchPatternIterator2, iAnalyze, stepPattern, stepPattern2);
            if (stepPattern == null) {
                stepPattern = stepPatternCreateDefaultStepPattern;
            } else {
                stepPatternCreateDefaultStepPattern.setRelativePathPattern(stepPattern2);
            }
            nextStepPos = compiler2.getNextStepPos(nextStepPos);
            if (nextStepPos < 0) {
                break;
            }
            stepPattern2 = stepPatternCreateDefaultStepPattern;
            compiler = compiler2;
            matchPatternIterator = matchPatternIterator2;
        }
        int i3 = 13;
        StepPattern relativePathPattern = stepPatternCreateDefaultStepPattern;
        StepPattern stepPattern3 = relativePathPattern;
        while (relativePathPattern != null) {
            int axis = relativePathPattern.getAxis();
            relativePathPattern.setAxis(i3);
            int whatToShow = relativePathPattern.getWhatToShow();
            if (whatToShow == 2 || whatToShow == 4096) {
                int i4 = whatToShow == 2 ? 2 : 9;
                if (isDownwardAxisOfMany(i3)) {
                    StepPattern stepPattern4 = new StepPattern(whatToShow, relativePathPattern.getNamespace(), relativePathPattern.getLocalName(), i4, 0);
                    XNumber staticScore = relativePathPattern.getStaticScore();
                    relativePathPattern.setNamespace(null);
                    relativePathPattern.setLocalName("*");
                    stepPattern4.setPredicates(relativePathPattern.getPredicates());
                    relativePathPattern.setPredicates(null);
                    relativePathPattern.setWhatToShow(1);
                    StepPattern relativePathPattern2 = relativePathPattern.getRelativePathPattern();
                    relativePathPattern.setRelativePathPattern(stepPattern4);
                    stepPattern4.setRelativePathPattern(relativePathPattern2);
                    stepPattern4.setStaticScore(staticScore);
                    if (11 == relativePathPattern.getAxis()) {
                        relativePathPattern.setAxis(15);
                    } else if (4 == relativePathPattern.getAxis()) {
                        relativePathPattern.setAxis(5);
                    }
                    relativePathPattern = stepPattern4;
                } else if (3 == relativePathPattern.getAxis()) {
                    relativePathPattern.setAxis(2);
                }
            }
            stepPattern3 = relativePathPattern;
            relativePathPattern = relativePathPattern.getRelativePathPattern();
            i3 = axis;
        }
        if (i3 < 16) {
            ContextMatchStepPattern contextMatchStepPattern = new ContextMatchStepPattern(i3, 13);
            XNumber staticScore2 = stepPattern3.getStaticScore();
            stepPattern3.setRelativePathPattern(contextMatchStepPattern);
            stepPattern3.setStaticScore(staticScore2);
            contextMatchStepPattern.setStaticScore(staticScore2);
        }
        return stepPatternCreateDefaultStepPattern;
    }

    public static AxesWalker loadWalkers(WalkingIterator walkingIterator, Compiler compiler, int i, int i2) throws TransformerException {
        int iAnalyze = analyze(compiler, i, i2);
        AxesWalker axesWalker = null;
        AxesWalker axesWalker2 = null;
        while (true) {
            int op = compiler.getOp(i);
            if (-1 == op) {
                return axesWalker;
            }
            AxesWalker axesWalkerCreateDefaultWalker = createDefaultWalker(compiler, i, walkingIterator, iAnalyze);
            axesWalkerCreateDefaultWalker.init(compiler, i, op);
            axesWalkerCreateDefaultWalker.exprSetParent(walkingIterator);
            if (axesWalker == null) {
                axesWalker = axesWalkerCreateDefaultWalker;
            } else {
                axesWalker2.setNextWalker(axesWalkerCreateDefaultWalker);
                axesWalkerCreateDefaultWalker.setPrevWalker(axesWalker2);
            }
            i = compiler.getNextStepPos(i);
            if (i < 0) {
                return axesWalker;
            }
            axesWalker2 = axesWalkerCreateDefaultWalker;
        }
    }

    public static boolean mightBeProximate(Compiler compiler, int i, int i2) throws TransformerException {
        switch (i2) {
            case 22:
            case 23:
            case 24:
            case 25:
                compiler.getArgLength(i);
                break;
            default:
                compiler.getArgLengthOfStep(i);
                break;
        }
        int firstPredicateOpPos = compiler.getFirstPredicateOpPos(i);
        while (29 == compiler.getOp(firstPredicateOpPos)) {
            int i3 = firstPredicateOpPos + 2;
            int op = compiler.getOp(i3);
            if (op == 25) {
                if (functionProximateOrContainsProximate(compiler, i3)) {
                    return true;
                }
            } else if (op != 28) {
                switch (op) {
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                        int firstChildPos = OpMap.getFirstChildPos(i3);
                        int nextOpPos = compiler.getNextOpPos(firstChildPos);
                        if (isProximateInnerExpr(compiler, firstChildPos) || isProximateInnerExpr(compiler, nextOpPos)) {
                            return true;
                        }
                        break;
                    default:
                        return true;
                }
            } else {
                continue;
            }
            firstPredicateOpPos = compiler.getNextOpPos(firstPredicateOpPos);
        }
        return false;
    }

    public static DTMIterator newDTMIterator(Compiler compiler, int i, boolean z) throws TransformerException {
        LocPathIterator walkingIterator;
        int firstChildPos = OpMap.getFirstChildPos(i);
        int iAnalyze = analyze(compiler, firstChildPos, 0);
        boolean zIsOneStep = isOneStep(iAnalyze);
        if (zIsOneStep && walksSelfOnly(iAnalyze) && isWild(iAnalyze) && !hasPredicate(iAnalyze)) {
            walkingIterator = new SelfIteratorNoPredicate(compiler, i, iAnalyze);
        } else if (walksChildrenOnly(iAnalyze) && zIsOneStep) {
            walkingIterator = (!isWild(iAnalyze) || hasPredicate(iAnalyze)) ? new ChildTestIterator(compiler, i, iAnalyze) : new ChildIterator(compiler, i, iAnalyze);
        } else if (zIsOneStep && walksAttributes(iAnalyze)) {
            walkingIterator = new AttributeIterator(compiler, i, iAnalyze);
        } else if (zIsOneStep && !walksFilteredList(iAnalyze)) {
            walkingIterator = (walksNamespaces(iAnalyze) || !(walksInDocOrder(iAnalyze) || isSet(iAnalyze, 4194304))) ? new OneStepIterator(compiler, i, iAnalyze) : new OneStepIteratorForward(compiler, i, iAnalyze);
        } else if (isOptimizableForDescendantIterator(compiler, firstChildPos, 0)) {
            walkingIterator = new DescendantIterator(compiler, i, iAnalyze);
        } else {
            walkingIterator = isNaturalDocOrder(compiler, firstChildPos, 0, iAnalyze) ? new WalkingIterator(compiler, i, iAnalyze, true) : new WalkingIteratorSorted(compiler, i, iAnalyze, true);
        }
        walkingIterator.setIsTopLevel(z);
        return walkingIterator;
    }

    public static boolean walksAncestors(int i) {
        return isSet(i, 24576);
    }

    public static boolean walksAttributes(int i) {
        return (i & 32768) != 0;
    }

    public static boolean walksChildren(int i) {
        return (i & 65536) != 0;
    }

    public static boolean walksChildrenAndExtraAndSelfOnly(int i) {
        if (!walksChildren(i) || walksDescendants(i) || walksUp(i) || walksSideways(i)) {
            return false;
        }
        return !isAbsolute(i) || isSet(i, 134217728);
    }

    public static boolean walksChildrenOnly(int i) {
        if (!walksChildren(i) || isSet(i, 33554432) || walksExtraNodes(i) || walksDescendants(i) || walksUp(i) || walksSideways(i)) {
            return false;
        }
        return !isAbsolute(i) || isSet(i, 134217728);
    }

    public static boolean walksDescendants(int i) {
        return isSet(i, 393216);
    }

    public static boolean walksDescendantsAndExtraAndSelfOnly(int i) {
        if (walksChildren(i) || !walksDescendants(i) || walksUp(i) || walksSideways(i)) {
            return false;
        }
        return !isAbsolute(i) || isSet(i, 134217728);
    }

    public static boolean walksDownExtraOnly(int i) {
        return (!walksSubtree(i) || !walksExtraNodes(i) || walksUp(i) || walksSideways(i) || isAbsolute(i)) ? false : true;
    }

    public static boolean walksDownOnly(int i) {
        return (!walksSubtree(i) || walksUp(i) || walksSideways(i) || isAbsolute(i)) ? false : true;
    }

    public static boolean walksExtraNodes(int i) {
        return isSet(i, 2129920);
    }

    public static boolean walksExtraNodesOnly(int i) {
        return (!walksExtraNodes(i) || isSet(i, 33554432) || walksSubtree(i) || walksUp(i) || walksSideways(i) || isAbsolute(i)) ? false : true;
    }

    public static boolean walksFilteredList(int i) {
        return isSet(i, 67108864);
    }

    public static boolean walksFollowingOnlyMaybeAbsolute(int i) {
        return (!isSet(i, 35127296) || walksSubtree(i) || walksUp(i) || walksSideways(i)) ? false : true;
    }

    public static boolean walksInDocOrder(int i) {
        return (walksSubtreeOnlyMaybeAbsolute(i) || walksExtraNodesOnly(i) || walksFollowingOnlyMaybeAbsolute(i)) && !isSet(i, 67108864);
    }

    public static boolean walksNamespaces(int i) {
        return (i & 2097152) != 0;
    }

    public static boolean walksSelfOnly(int i) {
        return (!isSet(i, 33554432) || walksSubtree(i) || walksUp(i) || walksSideways(i) || isAbsolute(i)) ? false : true;
    }

    public static boolean walksSideways(int i) {
        return isSet(i, 26738688);
    }

    public static boolean walksSubtree(int i) {
        return isSet(i, 458752);
    }

    public static boolean walksSubtreeOnly(int i) {
        return walksSubtreeOnlyMaybeAbsolute(i) && !isAbsolute(i);
    }

    public static boolean walksSubtreeOnlyFromRootOrContext(int i) {
        return (!walksSubtree(i) || walksExtraNodes(i) || walksUp(i) || walksSideways(i) || isSet(i, 67108864)) ? false : true;
    }

    public static boolean walksSubtreeOnlyMaybeAbsolute(int i) {
        return (!walksSubtree(i) || walksExtraNodes(i) || walksUp(i) || walksSideways(i)) ? false : true;
    }

    public static boolean walksUp(int i) {
        return isSet(i, 4218880);
    }

    public static boolean walksUpOnly(int i) {
        return (walksSubtree(i) || !walksUp(i) || walksSideways(i) || isAbsolute(i)) ? false : true;
    }

    public static boolean isNaturalDocOrder(int i) {
        return (canCrissCross(i) || isSet(i, 2097152) || walksFilteredList(i) || !walksInDocOrder(i)) ? false : true;
    }
}
