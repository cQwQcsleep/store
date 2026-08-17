package com.sun.org.apache.xpath.internal.compiler;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xml.internal.utils.LocaleUtility;
import com.sun.org.apache.xml.internal.utils.ObjectVector;
import com.sun.org.apache.xml.internal.utils.PrefixResolver;
import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.XPathProcessorException;
import com.sun.org.apache.xpath.internal.objects.XNumber;
import com.sun.org.apache.xpath.internal.objects.XString;
import com.sun.org.apache.xpath.internal.res.XPATHMessages;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;
import jdk.xml.internal.XMLSecurityManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class XPathParser {
    public static final String CONTINUE_AFTER_FATAL_ERROR = "CONTINUE_AFTER_FATAL_ERROR";
    protected static final int FILTER_MATCH_FAILED = 0;
    protected static final int FILTER_MATCH_PREDICATES = 2;
    protected static final int FILTER_MATCH_PRIMARY = 1;
    private int countPredicate;
    private ErrorListener m_errorListener;
    private FunctionTable m_functionTable;
    PrefixResolver m_namespaceContext;
    private OpMap m_ops;
    SourceLocator m_sourceLocator;
    transient String m_token;
    XMLSecurityManager m_xmlSecMgr;
    transient char m_tokenChar = 0;
    int m_queueMark = 0;

    public XPathParser(ErrorListener errorListener, SourceLocator sourceLocator, XMLSecurityManager xMLSecurityManager) {
        this.m_errorListener = errorListener;
        this.m_sourceLocator = sourceLocator;
        this.m_xmlSecMgr = xMLSecurityManager;
    }

    private void assertion(boolean z, String str) {
        if (z) {
            return;
        }
        f63.a(XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[]{str}));
    }

    private final void consumeExpected(char c) throws TransformerException {
        if (tokenIs(c)) {
            nextToken();
        } else {
            error("ER_EXPECTED_BUT_FOUND", new Object[]{String.valueOf(c), this.m_token});
            throw new XPathProcessorException(CONTINUE_AFTER_FATAL_ERROR);
        }
    }

    private final String getTokenRelative(int i) {
        int i2 = this.m_queueMark + i;
        if (i2 <= 0 || i2 >= this.m_ops.getTokenQueueSize()) {
            return null;
        }
        return (String) this.m_ops.m_tokenQueue.elementAt(i2);
    }

    private final boolean lookbehind(char c, int i) {
        char cCharAt;
        int i2 = this.m_queueMark - (i + 1);
        if (i2 >= 0) {
            String str = (String) this.m_ops.m_tokenQueue.elementAt(i2);
            if (str.length() == 1 && (cCharAt = str.charAt(0)) != '|' && cCharAt == c) {
                return true;
            }
        }
        return false;
    }

    private final boolean lookbehindHasToken(int i) {
        int i2 = this.m_queueMark;
        if (i2 - i <= 0) {
            return false;
        }
        String str = (String) this.m_ops.m_tokenQueue.elementAt(i2 - (i - 1));
        return (str == null ? '|' : str.charAt(0)) != '|';
    }

    private final void nextToken() {
        if (this.m_queueMark >= this.m_ops.getTokenQueueSize()) {
            this.m_token = null;
            this.m_tokenChar = (char) 0;
            return;
        }
        ObjectVector objectVector = this.m_ops.m_tokenQueue;
        int i = this.m_queueMark;
        this.m_queueMark = i + 1;
        String str = (String) objectVector.elementAt(i);
        this.m_token = str;
        this.m_tokenChar = str.charAt(0);
    }

    private final void prevToken() {
        int i = this.m_queueMark;
        if (i <= 0) {
            this.m_token = null;
            this.m_tokenChar = (char) 0;
            return;
        }
        int i2 = i - 1;
        this.m_queueMark = i2;
        String str = (String) this.m_ops.m_tokenQueue.elementAt(i2);
        this.m_token = str;
        this.m_tokenChar = str.charAt(0);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x00a0 A[LOOP:0: B:23:0x0098->B:25:0x00a0, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:32:0x00bc  */
    public boolean AbbreviatedNodeTestStep(boolean z) throws TransformerException {
        int op;
        boolean z2;
        int op2 = this.m_ops.getOp(1);
        int i = 51;
        if (!tokenIs('@')) {
            if (lookahead("::", 1)) {
                if (tokenIs("attribute")) {
                    appendOp(2, 51);
                    op = -1;
                } else if (tokenIs("child")) {
                    op = this.m_ops.getOp(1);
                    appendOp(2, 53);
                    i = 53;
                } else {
                    error("ER_AXES_NOT_ALLOWED", new Object[]{this.m_token});
                    op = -1;
                    i = -1;
                }
                nextToken();
                nextToken();
            } else if (tokenIs('/')) {
                if (!z) {
                    error("ER_EXPECTED_STEP_PATTERN", null);
                }
                appendOp(2, 52);
                nextToken();
                i = 52;
            } else {
                op = this.m_ops.getOp(1);
                appendOp(2, 53);
                i = 53;
            }
            OpMap opMap = this.m_ops;
            opMap.setOp(1, opMap.getOp(1) + 1);
            NodeTest(i);
            OpMap opMap2 = this.m_ops;
            int i2 = op2 + 1;
            opMap2.setOp(op2 + 2, opMap2.getOp(1) - op2);
            while (tokenIs('[')) {
                Predicate();
            }
            if (op <= -1 && tokenIs('/') && lookahead('/', 1)) {
                this.m_ops.setOp(op, 52);
                nextToken();
                z2 = true;
            } else {
                z2 = false;
            }
            OpMap opMap3 = this.m_ops;
            opMap3.setOp(i2, opMap3.getOp(1) - op2);
            return z2;
        }
        appendOp(2, 51);
        nextToken();
        op = -1;
        OpMap opMap4 = this.m_ops;
        opMap4.setOp(1, opMap4.getOp(1) + 1);
        NodeTest(i);
        OpMap opMap5 = this.m_ops;
        int i3 = op2 + 1;
        opMap5.setOp(op2 + 2, opMap5.getOp(1) - op2);
        while (tokenIs('[')) {
            Predicate();
        }
        if (op <= -1) {
            z2 = false;
        } else {
            z2 = false;
        }
        OpMap opMap6 = this.m_ops;
        opMap6.setOp(i3, opMap6.getOp(1) - op2);
        return z2;
    }

    public int AdditiveExpr(int i) throws TransformerException {
        int iAdditiveExpr;
        int op = this.m_ops.getOp(1);
        if (-1 == i) {
            i = op;
        }
        MultiplicativeExpr(-1);
        if (this.m_token != null) {
            if (tokenIs('+')) {
                nextToken();
                insertOp(i, 2, 10);
                int op2 = this.m_ops.getOp(1) - i;
                iAdditiveExpr = AdditiveExpr(i);
                OpMap opMap = this.m_ops;
                opMap.setOp(iAdditiveExpr + 1, opMap.getOp(iAdditiveExpr + op2 + 1) + op2);
            } else if (tokenIs(LocaleUtility.IETF_SEPARATOR)) {
                nextToken();
                insertOp(i, 2, 11);
                int op3 = this.m_ops.getOp(1) - i;
                iAdditiveExpr = AdditiveExpr(i);
                OpMap opMap2 = this.m_ops;
                opMap2.setOp(iAdditiveExpr + 1, opMap2.getOp(iAdditiveExpr + op3 + 1) + op3);
            }
            return iAdditiveExpr + 2;
        }
        return i;
    }

    public void AndExpr() throws TransformerException {
        int op = this.m_ops.getOp(1);
        EqualityExpr(-1);
        if (this.m_token == null || !tokenIs("and")) {
            return;
        }
        nextToken();
        insertOp(op, 2, 3);
        AndExpr();
        OpMap opMap = this.m_ops;
        opMap.setOp(op + 1, opMap.getOp(1) - op);
    }

    public void Argument() throws TransformerException {
        int op = this.m_ops.getOp(1);
        appendOp(2, 26);
        Expr();
        OpMap opMap = this.m_ops;
        opMap.setOp(op + 1, opMap.getOp(1) - op);
    }

    public int AxisName() throws TransformerException {
        Integer axisName = Keywords.getAxisName(this.m_token);
        if (axisName == null) {
            error("ER_ILLEGAL_AXIS_NAME", new Object[]{this.m_token});
        }
        int iIntValue = axisName.intValue();
        appendOp(2, iIntValue);
        return iIntValue;
    }

    public void Basis() throws TransformerException {
        int iAxisName;
        int op = this.m_ops.getOp(1);
        if (lookahead("::", 1)) {
            iAxisName = AxisName();
            nextToken();
            nextToken();
        } else if (tokenIs('@')) {
            iAxisName = 39;
            appendOp(2, 39);
            nextToken();
        } else {
            iAxisName = 40;
            appendOp(2, 40);
        }
        OpMap opMap = this.m_ops;
        opMap.setOp(1, opMap.getOp(1) + 1);
        NodeTest(iAxisName);
        OpMap opMap2 = this.m_ops;
        opMap2.setOp(op + 2, opMap2.getOp(1) - op);
    }

    public void BooleanExpr() throws TransformerException {
        int op = this.m_ops.getOp(1);
        appendOp(2, 18);
        Expr();
        int op2 = this.m_ops.getOp(1) - op;
        if (op2 == 2) {
            error("ER_BOOLEAN_ARG_NO_LONGER_OPTIONAL", null);
        }
        this.m_ops.setOp(op + 1, op2);
    }

    public int EqualityExpr(int i) throws TransformerException {
        int iEqualityExpr;
        int op = this.m_ops.getOp(1);
        if (-1 == i) {
            i = op;
        }
        RelationalExpr(-1);
        if (this.m_token != null) {
            if (tokenIs('!') && lookahead('=', 1)) {
                nextToken();
                nextToken();
                insertOp(i, 2, 4);
                int op2 = this.m_ops.getOp(1) - i;
                iEqualityExpr = EqualityExpr(i);
                OpMap opMap = this.m_ops;
                opMap.setOp(iEqualityExpr + 1, opMap.getOp(iEqualityExpr + op2 + 1) + op2);
            } else if (tokenIs('=')) {
                nextToken();
                insertOp(i, 2, 5);
                int op3 = this.m_ops.getOp(1) - i;
                iEqualityExpr = EqualityExpr(i);
                OpMap opMap2 = this.m_ops;
                opMap2.setOp(iEqualityExpr + 1, opMap2.getOp(iEqualityExpr + op3 + 1) + op3);
            }
            return iEqualityExpr + 2;
        }
        return i;
    }

    public void Expr() throws TransformerException {
        OrExpr();
    }

    public int FilterExpr() throws TransformerException {
        int op = this.m_ops.getOp(1);
        if (!PrimaryExpr()) {
            return 0;
        }
        if (!tokenIs('[')) {
            return 1;
        }
        insertOp(op, 2, 28);
        while (tokenIs('[')) {
            Predicate();
        }
        return 2;
    }

    public boolean FunctionCall() throws TransformerException {
        int op = this.m_ops.getOp(1);
        if (!lookahead(':', 1)) {
            int functionToken = getFunctionToken(this.m_token);
            if (-1 == functionToken) {
                error("ER_COULDNOT_FIND_FUNCTION", new Object[]{this.m_token});
            }
            switch (functionToken) {
                case OpCodes.NODETYPE_COMMENT /* 1030 */:
                case OpCodes.NODETYPE_TEXT /* 1031 */:
                case OpCodes.NODETYPE_PI /* 1032 */:
                case OpCodes.NODETYPE_NODE /* 1033 */:
                    return false;
                default:
                    appendOp(3, 25);
                    this.m_ops.setOp(op + 2, functionToken);
                    nextToken();
                    break;
            }
        } else {
            appendOp(4, 24);
            this.m_ops.setOp(op + 2, this.m_queueMark - 1);
            nextToken();
            consumeExpected(':');
            this.m_ops.setOp(op + 3, this.m_queueMark - 1);
            nextToken();
        }
        consumeExpected('(');
        while (!tokenIs(')') && this.m_token != null) {
            if (tokenIs(',')) {
                error("ER_FOUND_COMMA_BUT_NO_PRECEDING_ARG", null);
            }
            Argument();
            if (!tokenIs(')')) {
                consumeExpected(',');
                if (tokenIs(')')) {
                    error("ER_FOUND_COMMA_BUT_NO_FOLLOWING_ARG", null);
                }
            }
        }
        consumeExpected(')');
        OpMap opMap = this.m_ops;
        opMap.setOp(opMap.getOp(1), -1);
        OpMap opMap2 = this.m_ops;
        opMap2.setOp(1, opMap2.getOp(1) + 1);
        OpMap opMap3 = this.m_ops;
        opMap3.setOp(op + 1, opMap3.getOp(1) - op);
        return true;
    }

    public void IdKeyPattern() throws TransformerException {
        FunctionCall();
    }

    public void Literal() throws TransformerException {
        int length = this.m_token.length() - 1;
        char c = this.m_tokenChar;
        char cCharAt = this.m_token.charAt(length);
        if ((c != '\"' || cCharAt != '\"') && (c != '\'' || cCharAt != '\'')) {
            error("ER_PATTERN_LITERAL_NEEDS_BE_QUOTED", new Object[]{this.m_token});
            return;
        }
        int i = this.m_queueMark - 1;
        this.m_ops.m_tokenQueue.setElementAt(null, i);
        this.m_ops.m_tokenQueue.setElementAt(new XString(this.m_token.substring(1, length)), i);
        OpMap opMap = this.m_ops;
        opMap.setOp(opMap.getOp(1), i);
        OpMap opMap2 = this.m_ops;
        opMap2.setOp(1, opMap2.getOp(1) + 1);
        nextToken();
    }

    public void LocationPath() throws TransformerException {
        int op = this.m_ops.getOp(1);
        appendOp(2, 28);
        boolean z = tokenIs('/');
        if (z) {
            appendOp(4, 50);
            OpMap opMap = this.m_ops;
            opMap.setOp(opMap.getOp(1) - 2, 4);
            OpMap opMap2 = this.m_ops;
            opMap2.setOp(opMap2.getOp(1) - 1, 35);
            nextToken();
        } else if (this.m_token == null) {
            error("ER_EXPECTED_LOC_PATH_AT_END_EXPR", null);
        }
        if (this.m_token != null && !RelativeLocationPath() && !z) {
            error("ER_EXPECTED_LOC_PATH", new Object[]{this.m_token});
        }
        OpMap opMap3 = this.m_ops;
        opMap3.setOp(opMap3.getOp(1), -1);
        OpMap opMap4 = this.m_ops;
        opMap4.setOp(1, opMap4.getOp(1) + 1);
        OpMap opMap5 = this.m_ops;
        opMap5.setOp(op + 1, opMap5.getOp(1) - op);
    }

    public void LocationPathPattern() throws TransformerException {
        char c;
        int op = this.m_ops.getOp(1);
        appendOp(2, 31);
        if (lookahead('(', 1) && (tokenIs("id") || tokenIs("key"))) {
            IdKeyPattern();
            if (tokenIs('/')) {
                nextToken();
                if (tokenIs('/')) {
                    appendOp(4, 52);
                    nextToken();
                } else {
                    appendOp(4, 53);
                }
                OpMap opMap = this.m_ops;
                opMap.setOp(opMap.getOp(1) - 2, 4);
                OpMap opMap2 = this.m_ops;
                opMap2.setOp(opMap2.getOp(1) - 1, OpCodes.NODETYPE_FUNCTEST);
                c = 2;
            } else {
                c = 0;
            }
        } else if (tokenIs('/')) {
            if (lookahead('/', 1)) {
                appendOp(4, 52);
                nextToken();
                c = 2;
            } else {
                appendOp(4, 50);
                c = 1;
            }
            OpMap opMap3 = this.m_ops;
            opMap3.setOp(opMap3.getOp(1) - 2, 4);
            OpMap opMap4 = this.m_ops;
            opMap4.setOp(opMap4.getOp(1) - 1, 35);
            nextToken();
        } else {
            c = 2;
        }
        if (c != 0) {
            if (!tokenIs('|') && this.m_token != null) {
                RelativePathPattern();
            } else if (c == 2) {
                error("ER_EXPECTED_REL_PATH_PATTERN", null);
            }
        }
        OpMap opMap5 = this.m_ops;
        opMap5.setOp(opMap5.getOp(1), -1);
        OpMap opMap6 = this.m_ops;
        opMap6.setOp(1, opMap6.getOp(1) + 1);
        OpMap opMap7 = this.m_ops;
        opMap7.setOp(op + 1, opMap7.getOp(1) - op);
    }

    public int MultiplicativeExpr(int i) throws TransformerException {
        int iMultiplicativeExpr;
        int op = this.m_ops.getOp(1);
        if (-1 == i) {
            i = op;
        }
        UnaryExpr();
        if (this.m_token != null) {
            if (tokenIs('*')) {
                nextToken();
                insertOp(i, 2, 12);
                int op2 = this.m_ops.getOp(1) - i;
                iMultiplicativeExpr = MultiplicativeExpr(i);
                OpMap opMap = this.m_ops;
                opMap.setOp(iMultiplicativeExpr + 1, opMap.getOp(iMultiplicativeExpr + op2 + 1) + op2);
            } else if (tokenIs("div")) {
                nextToken();
                insertOp(i, 2, 13);
                int op3 = this.m_ops.getOp(1) - i;
                iMultiplicativeExpr = MultiplicativeExpr(i);
                OpMap opMap2 = this.m_ops;
                opMap2.setOp(iMultiplicativeExpr + 1, opMap2.getOp(iMultiplicativeExpr + op3 + 1) + op3);
            } else if (tokenIs("mod")) {
                nextToken();
                insertOp(i, 2, 14);
                int op4 = this.m_ops.getOp(1) - i;
                iMultiplicativeExpr = MultiplicativeExpr(i);
                OpMap opMap3 = this.m_ops;
                opMap3.setOp(iMultiplicativeExpr + 1, opMap3.getOp(iMultiplicativeExpr + op4 + 1) + op4);
            } else if (tokenIs("quo")) {
                nextToken();
                insertOp(i, 2, 15);
                int op5 = this.m_ops.getOp(1) - i;
                iMultiplicativeExpr = MultiplicativeExpr(i);
                OpMap opMap4 = this.m_ops;
                opMap4.setOp(iMultiplicativeExpr + 1, opMap4.getOp(iMultiplicativeExpr + op5 + 1) + op5);
            }
            return iMultiplicativeExpr + 2;
        }
        return i;
    }

    public void NCName() {
        OpMap opMap = this.m_ops;
        opMap.setOp(opMap.getOp(1), this.m_queueMark - 1);
        OpMap opMap2 = this.m_ops;
        opMap2.setOp(1, opMap2.getOp(1) + 1);
        nextToken();
    }

    public void NodeTest(int i) throws TransformerException {
        if (lookahead('(', 1)) {
            Integer nodeType = Keywords.getNodeType(this.m_token);
            if (nodeType == null) {
                error("ER_UNKNOWN_NODETYPE", new Object[]{this.m_token});
                return;
            }
            nextToken();
            int iIntValue = nodeType.intValue();
            OpMap opMap = this.m_ops;
            opMap.setOp(opMap.getOp(1), iIntValue);
            OpMap opMap2 = this.m_ops;
            opMap2.setOp(1, opMap2.getOp(1) + 1);
            consumeExpected('(');
            if (1032 == iIntValue && !tokenIs(')')) {
                Literal();
            }
            consumeExpected(')');
            return;
        }
        OpMap opMap3 = this.m_ops;
        opMap3.setOp(opMap3.getOp(1), 34);
        OpMap opMap4 = this.m_ops;
        opMap4.setOp(1, opMap4.getOp(1) + 1);
        if (lookahead(':', 1)) {
            boolean z = tokenIs('*');
            OpMap opMap5 = this.m_ops;
            if (z) {
                opMap5.setOp(opMap5.getOp(1), -3);
            } else {
                opMap5.setOp(opMap5.getOp(1), this.m_queueMark - 1);
                if (!Character.isLetter(this.m_tokenChar) && !tokenIs('_')) {
                    error("ER_EXPECTED_NODE_TEST", null);
                }
            }
            nextToken();
            consumeExpected(':');
        } else {
            OpMap opMap6 = this.m_ops;
            opMap6.setOp(opMap6.getOp(1), -2);
        }
        OpMap opMap7 = this.m_ops;
        opMap7.setOp(1, opMap7.getOp(1) + 1);
        boolean z2 = tokenIs('*');
        OpMap opMap8 = this.m_ops;
        if (z2) {
            opMap8.setOp(opMap8.getOp(1), -3);
        } else {
            opMap8.setOp(opMap8.getOp(1), this.m_queueMark - 1);
            if (!Character.isLetter(this.m_tokenChar) && !tokenIs('_')) {
                error("ER_EXPECTED_NODE_TEST", null);
            }
        }
        OpMap opMap9 = this.m_ops;
        opMap9.setOp(1, opMap9.getOp(1) + 1);
        nextToken();
    }

    public void Number() throws TransformerException {
        double dDoubleValue;
        String str = this.m_token;
        if (str == null) {
            return;
        }
        try {
            if (str.indexOf(101) > -1 || this.m_token.indexOf(69) > -1) {
                throw new NumberFormatException();
            }
            dDoubleValue = Double.valueOf(this.m_token).doubleValue();
            this.m_ops.m_tokenQueue.setElementAt(new XNumber(dDoubleValue), this.m_queueMark - 1);
            OpMap opMap = this.m_ops;
            opMap.setOp(opMap.getOp(1), this.m_queueMark - 1);
            OpMap opMap2 = this.m_ops;
            opMap2.setOp(1, opMap2.getOp(1) + 1);
            nextToken();
        } catch (NumberFormatException unused) {
            error("ER_COULDNOT_BE_FORMATTED_TO_NUMBER", new Object[]{this.m_token});
            dDoubleValue = XPath.MATCH_SCORE_QNAME;
        }
    }

    public void NumberExpr() throws TransformerException {
        int op = this.m_ops.getOp(1);
        appendOp(2, 19);
        Expr();
        OpMap opMap = this.m_ops;
        opMap.setOp(op + 1, opMap.getOp(1) - op);
    }

    public void OrExpr() throws TransformerException {
        int op = this.m_ops.getOp(1);
        AndExpr();
        if (this.m_token == null || !tokenIs("or")) {
            return;
        }
        nextToken();
        insertOp(op, 2, 2);
        OrExpr();
        OpMap opMap = this.m_ops;
        opMap.setOp(op + 1, opMap.getOp(1) - op);
    }

    public void PathExpr() throws TransformerException {
        int op = this.m_ops.getOp(1);
        int iFilterExpr = FilterExpr();
        if (iFilterExpr == 0) {
            LocationPath();
            return;
        }
        boolean z = iFilterExpr == 2;
        if (tokenIs('/')) {
            nextToken();
            if (!z) {
                insertOp(op, 2, 28);
                z = true;
            }
            if (!RelativeLocationPath()) {
                error("ER_EXPECTED_REL_LOC_PATH", null);
            }
        }
        if (z) {
            OpMap opMap = this.m_ops;
            opMap.setOp(opMap.getOp(1), -1);
            OpMap opMap2 = this.m_ops;
            opMap2.setOp(1, opMap2.getOp(1) + 1);
            OpMap opMap3 = this.m_ops;
            opMap3.setOp(op + 1, opMap3.getOp(1) - op);
        }
    }

    public void Pattern() throws TransformerException {
        while (true) {
            LocationPathPattern();
            if (!tokenIs('|')) {
                return;
            } else {
                nextToken();
            }
        }
    }

    public void Predicate() throws TransformerException {
        if (tokenIs('[')) {
            this.countPredicate++;
            nextToken();
            PredicateExpr();
            this.countPredicate--;
            consumeExpected(']');
        }
    }

    public void PredicateExpr() throws TransformerException {
        int op = this.m_ops.getOp(1);
        appendOp(2, 29);
        Expr();
        OpMap opMap = this.m_ops;
        opMap.setOp(opMap.getOp(1), -1);
        OpMap opMap2 = this.m_ops;
        opMap2.setOp(1, opMap2.getOp(1) + 1);
        OpMap opMap3 = this.m_ops;
        opMap3.setOp(op + 1, opMap3.getOp(1) - op);
    }

    public boolean PrimaryExpr() throws TransformerException {
        int op = this.m_ops.getOp(1);
        char c = this.m_tokenChar;
        if (c == '\'' || c == '\"') {
            appendOp(2, 21);
            Literal();
            OpMap opMap = this.m_ops;
            opMap.setOp(op + 1, opMap.getOp(1) - op);
            return true;
        }
        if (c == '$') {
            nextToken();
            appendOp(2, 22);
            QName();
            OpMap opMap2 = this.m_ops;
            opMap2.setOp(op + 1, opMap2.getOp(1) - op);
            return true;
        }
        if (c == '(') {
            nextToken();
            appendOp(2, 23);
            Expr();
            consumeExpected(')');
            OpMap opMap3 = this.m_ops;
            opMap3.setOp(op + 1, opMap3.getOp(1) - op);
            return true;
        }
        String str = this.m_token;
        if (str == null || !(('.' == c && str.length() > 1 && Character.isDigit(this.m_token.charAt(1))) || Character.isDigit(this.m_tokenChar))) {
            if (lookahead('(', 1) || (lookahead(':', 1) && lookahead('(', 3))) {
                return FunctionCall();
            }
            return false;
        }
        appendOp(2, 27);
        Number();
        OpMap opMap4 = this.m_ops;
        opMap4.setOp(op + 1, opMap4.getOp(1) - op);
        return true;
    }

    public void QName() throws TransformerException {
        boolean zLookahead = lookahead(':', 1);
        OpMap opMap = this.m_ops;
        if (zLookahead) {
            opMap.setOp(opMap.getOp(1), this.m_queueMark - 1);
            OpMap opMap2 = this.m_ops;
            opMap2.setOp(1, opMap2.getOp(1) + 1);
            nextToken();
            consumeExpected(':');
        } else {
            opMap.setOp(opMap.getOp(1), -2);
            OpMap opMap3 = this.m_ops;
            opMap3.setOp(1, opMap3.getOp(1) + 1);
        }
        OpMap opMap4 = this.m_ops;
        opMap4.setOp(opMap4.getOp(1), this.m_queueMark - 1);
        OpMap opMap5 = this.m_ops;
        opMap5.setOp(1, opMap5.getOp(1) + 1);
        nextToken();
    }

    public int RelationalExpr(int i) throws TransformerException {
        int iRelationalExpr;
        int op = this.m_ops.getOp(1);
        if (-1 == i) {
            i = op;
        }
        AdditiveExpr(-1);
        if (this.m_token != null) {
            if (tokenIs('<')) {
                nextToken();
                if (tokenIs('=')) {
                    nextToken();
                    insertOp(i, 2, 6);
                } else {
                    insertOp(i, 2, 7);
                }
                int op2 = this.m_ops.getOp(1) - i;
                iRelationalExpr = RelationalExpr(i);
                OpMap opMap = this.m_ops;
                opMap.setOp(iRelationalExpr + 1, opMap.getOp(iRelationalExpr + op2 + 1) + op2);
            } else if (tokenIs('>')) {
                nextToken();
                if (tokenIs('=')) {
                    nextToken();
                    insertOp(i, 2, 8);
                } else {
                    insertOp(i, 2, 9);
                }
                int op3 = this.m_ops.getOp(1) - i;
                iRelationalExpr = RelationalExpr(i);
                OpMap opMap2 = this.m_ops;
                opMap2.setOp(iRelationalExpr + 1, opMap2.getOp(iRelationalExpr + op3 + 1) + op3);
            }
            return iRelationalExpr + 2;
        }
        return i;
    }

    public boolean RelativeLocationPath() throws TransformerException {
        if (!Step()) {
            return false;
        }
        while (tokenIs('/')) {
            nextToken();
            if (!Step()) {
                error("ER_EXPECTED_LOC_STEP", null);
            }
        }
        return true;
    }

    public void RelativePathPattern() throws TransformerException {
        boolean zStepPattern = StepPattern(false);
        while (tokenIs('/')) {
            nextToken();
            zStepPattern = StepPattern(!zStepPattern);
        }
    }

    public boolean Step() throws TransformerException {
        String str;
        int op = this.m_ops.getOp(1);
        boolean z = tokenIs('/');
        if (z) {
            nextToken();
            appendOp(2, 42);
            OpMap opMap = this.m_ops;
            opMap.setOp(1, opMap.getOp(1) + 1);
            OpMap opMap2 = this.m_ops;
            opMap2.setOp(opMap2.getOp(1), OpCodes.NODETYPE_NODE);
            OpMap opMap3 = this.m_ops;
            opMap3.setOp(1, opMap3.getOp(1) + 1);
            OpMap opMap4 = this.m_ops;
            opMap4.setOp(op + 2, opMap4.getOp(1) - op);
            OpMap opMap5 = this.m_ops;
            opMap5.setOp(op + 1, opMap5.getOp(1) - op);
            op = this.m_ops.getOp(1);
        }
        if (tokenIs(Constants.ATTRVAL_THIS)) {
            nextToken();
            if (tokenIs('[')) {
                error("ER_PREDICATE_ILLEGAL_SYNTAX", null);
            }
            appendOp(4, 48);
            OpMap opMap6 = this.m_ops;
            opMap6.setOp(opMap6.getOp(1) - 2, 4);
            OpMap opMap7 = this.m_ops;
            opMap7.setOp(opMap7.getOp(1) - 1, OpCodes.NODETYPE_NODE);
        } else if (tokenIs(Constants.ATTRVAL_PARENT)) {
            nextToken();
            appendOp(4, 45);
            OpMap opMap8 = this.m_ops;
            opMap8.setOp(opMap8.getOp(1) - 2, 4);
            OpMap opMap9 = this.m_ops;
            opMap9.setOp(opMap9.getOp(1) - 1, OpCodes.NODETYPE_NODE);
        } else {
            if (!tokenIs('*') && !tokenIs('@') && !tokenIs('_') && ((str = this.m_token) == null || !Character.isLetter(str.charAt(0)))) {
                if (z) {
                    error("ER_EXPECTED_LOC_STEP", null);
                }
                return false;
            }
            Basis();
            while (tokenIs('[')) {
                Predicate();
            }
            OpMap opMap10 = this.m_ops;
            opMap10.setOp(op + 1, opMap10.getOp(1) - op);
        }
        return true;
    }

    public boolean StepPattern(boolean z) throws TransformerException {
        return AbbreviatedNodeTestStep(z);
    }

    public void StringExpr() throws TransformerException {
        int op = this.m_ops.getOp(1);
        appendOp(2, 17);
        Expr();
        OpMap opMap = this.m_ops;
        opMap.setOp(op + 1, opMap.getOp(1) - op);
    }

    public void UnaryExpr() throws TransformerException {
        boolean z;
        int op = this.m_ops.getOp(1);
        if (this.m_tokenChar == '-') {
            nextToken();
            appendOp(2, 16);
            z = true;
        } else {
            z = false;
        }
        UnionExpr();
        if (z) {
            OpMap opMap = this.m_ops;
            opMap.setOp(op + 1, opMap.getOp(1) - op);
        }
    }

    public void UnionExpr() throws TransformerException {
        int op = this.m_ops.getOp(1);
        boolean z = false;
        while (true) {
            PathExpr();
            if (!tokenIs('|')) {
                OpMap opMap = this.m_ops;
                opMap.setOp(op + 1, opMap.getOp(1) - op);
                return;
            } else {
                if (!z) {
                    insertOp(op, 2, 20);
                    z = true;
                }
                nextToken();
            }
        }
    }

    public void appendOp(int i, int i2) {
        int op = this.m_ops.getOp(1);
        this.m_ops.setOp(op, i2);
        this.m_ops.setOp(op + 1, i);
        this.m_ops.setOp(1, op + i);
    }

    public String dumpRemainingTokenQueue() {
        int i = this.m_queueMark;
        if (i >= this.m_ops.getTokenQueueSize()) {
            return "";
        }
        String str = "\n Remaining tokens: (";
        while (i < this.m_ops.getTokenQueueSize()) {
            int i2 = i + 1;
            str = str + " '" + ((String) this.m_ops.m_tokenQueue.elementAt(i)) + "'";
            i = i2;
        }
        return str.concat(")");
    }

    public void error(String str, Object[] objArr) throws TransformerException {
        String strCreateXPATHMessage = XPATHMessages.createXPATHMessage(str, objArr);
        ErrorListener errorListener = getErrorListener();
        TransformerException transformerException = new TransformerException(strCreateXPATHMessage, this.m_sourceLocator);
        if (errorListener == null) {
            throw transformerException;
        }
        errorListener.fatalError(transformerException);
    }

    public ErrorListener getErrorListener() {
        return this.m_errorListener;
    }

    public final int getFunctionToken(String str) {
        try {
            Integer numLookupNodeTest = Keywords.lookupNodeTest(str);
            if (numLookupNodeTest == null) {
                numLookupNodeTest = this.m_functionTable.getFunctionID(str);
            }
            return numLookupNodeTest.intValue();
        } catch (ClassCastException | NullPointerException unused) {
            return -1;
        }
    }

    public void initMatchPattern(Compiler compiler, String str, PrefixResolver prefixResolver) throws TransformerException {
        this.m_ops = compiler;
        this.m_namespaceContext = prefixResolver;
        this.m_functionTable = compiler.getFunctionTable();
        new Lexer(compiler, prefixResolver, this, this.m_xmlSecMgr).tokenize(str);
        this.m_ops.setOp(0, 30);
        this.m_ops.setOp(1, 2);
        nextToken();
        try {
            Pattern();
        } catch (StackOverflowError unused) {
            error("ER_PREDICATE_TOO_MANY_OPEN", new Object[]{this.m_token, Integer.valueOf(this.m_queueMark), Integer.valueOf(this.countPredicate)});
        }
        if (this.m_token != null) {
            String strConcat = "";
            while (this.m_token != null) {
                strConcat = strConcat + "'" + this.m_token + "'";
                nextToken();
                if (this.m_token != null) {
                    strConcat = strConcat.concat(", ");
                }
            }
            error("ER_EXTRA_ILLEGAL_TOKENS", new Object[]{strConcat});
        }
        OpMap opMap = this.m_ops;
        opMap.setOp(opMap.getOp(1), -1);
        OpMap opMap2 = this.m_ops;
        opMap2.setOp(1, opMap2.getOp(1) + 1);
        this.m_ops.shrink();
    }

    public void initXPath(Compiler compiler, String str, PrefixResolver prefixResolver) throws TransformerException {
        this.m_ops = compiler;
        this.m_namespaceContext = prefixResolver;
        this.m_functionTable = compiler.getFunctionTable();
        new Lexer(compiler, prefixResolver, this, this.m_xmlSecMgr).tokenize(str);
        this.m_ops.setOp(0, 1);
        this.m_ops.setOp(1, 2);
        try {
            nextToken();
            Expr();
            if (this.m_token != null) {
                String str2 = "";
                while (this.m_token != null) {
                    str2 = str2 + "'" + this.m_token + "'";
                    nextToken();
                    if (this.m_token != null) {
                        str2 = str2 + ", ";
                    }
                }
                error("ER_EXTRA_ILLEGAL_TOKENS", new Object[]{str2});
            }
        } catch (XPathProcessorException e) {
            if (!CONTINUE_AFTER_FATAL_ERROR.equals(e.getMessage())) {
                throw e;
            }
            initXPath(compiler, "/..", prefixResolver);
        } catch (StackOverflowError unused) {
            error("ER_PREDICATE_TOO_MANY_OPEN", new Object[]{this.m_token, Integer.valueOf(this.m_queueMark), Integer.valueOf(this.countPredicate)});
        }
        compiler.shrink();
    }

    public void insertOp(int i, int i2, int i3) {
        int op = this.m_ops.getOp(1);
        int i4 = op - 1;
        while (true) {
            OpMap opMap = this.m_ops;
            if (i4 < i) {
                opMap.setOp(i, i3);
                this.m_ops.setOp(1, op + i2);
                return;
            } else {
                opMap.setOp(i4 + i2, opMap.getOp(i4));
                i4--;
            }
        }
    }

    public final boolean lookahead(char c, int i) {
        int i2 = this.m_queueMark + i;
        if (i2 <= this.m_ops.getTokenQueueSize() && i2 > 0 && this.m_ops.getTokenQueueSize() != 0) {
            String str = (String) this.m_ops.m_tokenQueue.elementAt(i2 - 1);
            if (str.length() == 1 && str.charAt(0) == c) {
                return true;
            }
        }
        return false;
    }

    public void setErrorHandler(ErrorListener errorListener) {
        this.m_errorListener = errorListener;
    }

    public final boolean tokenIs(String str) {
        String str2 = this.m_token;
        if (str2 != null) {
            return str2.equals(str);
        }
        return str == null;
    }

    public void warn(String str, Object[] objArr) throws TransformerException {
        String strCreateXPATHWarning = XPATHMessages.createXPATHWarning(str, objArr);
        ErrorListener errorListener = getErrorListener();
        if (errorListener != null) {
            errorListener.warning(new TransformerException(strCreateXPATHWarning, this.m_sourceLocator));
        } else {
            System.err.println(strCreateXPATHWarning);
        }
    }

    public final boolean tokenIs(char c) {
        return this.m_token != null && this.m_tokenChar == c;
    }

    private final void consumeExpected(String str) throws TransformerException {
        if (tokenIs(str)) {
            nextToken();
        } else {
            error("ER_EXPECTED_BUT_FOUND", new Object[]{str, this.m_token});
            throw new XPathProcessorException(CONTINUE_AFTER_FATAL_ERROR);
        }
    }

    private final boolean lookahead(String str, int i) {
        if (this.m_queueMark + i > this.m_ops.getTokenQueueSize()) {
            return str == null;
        }
        String str2 = (String) this.m_ops.m_tokenQueue.elementAt(this.m_queueMark + (i - 1));
        if (str2 != null) {
            return str2.equals(str);
        }
        return str == null;
    }
}
