package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.java_cup.internal.runtime.Symbol;
import com.sun.java_cup.internal.runtime.lr_parser;
import com.sun.org.apache.xpath.internal.XPath;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class parser_actions {
    private final XPathParser parser;

    public parser_actions(XPathParser xPathParser) {
        this.parser = xPathParser;
    }

    public final Symbol parser_do_action(int i, lr_parser lr_parserVar, Stack<Symbol> stack, int i2) throws Exception {
        int i3;
        Object parentLocationPath;
        ParentLocationPath parentLocationPath2;
        Step step;
        Object realExpr;
        Object unresolvedRef;
        Object namespaceUriCall;
        Object functionCall;
        switch (i) {
            case 0:
                int i4 = i2 - 1;
                Symbol symbol = new Symbol(0, stack.get(i4).left, stack.get(i2).right, (SyntaxTreeNode) stack.get(i4).value);
                lr_parserVar.done_parsing();
                return symbol;
            case 1:
                return new Symbol(1, stack.get(i2 - 1).left, stack.get(i2).right, (Pattern) stack.get(i2).value);
            case 2:
                return new Symbol(1, stack.get(i2 - 1).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 3:
                return new Symbol(28, stack.get(i2).left, stack.get(i2).right, (Pattern) stack.get(i2).value);
            case 4:
                int i5 = i2 - 2;
                return new Symbol(28, stack.get(i5).left, stack.get(i2).right, new AlternativePattern((Pattern) stack.get(i5).value, (Pattern) stack.get(i2).value));
            case 5:
                return new Symbol(29, stack.get(i2).left, stack.get(i2).right, new AbsolutePathPattern(null));
            case 6:
                return new Symbol(29, stack.get(i2 - 1).left, stack.get(i2).right, new AbsolutePathPattern((RelativePathPattern) stack.get(i2).value));
            case 7:
                return new Symbol(29, stack.get(i2).left, stack.get(i2).right, (IdKeyPattern) stack.get(i2).value);
            case 8:
                int i6 = i2 - 2;
                return new Symbol(29, stack.get(i6).left, stack.get(i2).right, new ParentPattern((IdKeyPattern) stack.get(i6).value, (RelativePathPattern) stack.get(i2).value));
            case 9:
                int i7 = i2 - 2;
                return new Symbol(29, stack.get(i7).left, stack.get(i2).right, new AncestorPattern((IdKeyPattern) stack.get(i7).value, (RelativePathPattern) stack.get(i2).value));
            case 10:
                return new Symbol(29, stack.get(i2 - 1).left, stack.get(i2).right, new AncestorPattern((RelativePathPattern) stack.get(i2).value));
            case 11:
                return new Symbol(29, stack.get(i2).left, stack.get(i2).right, (RelativePathPattern) stack.get(i2).value);
            case 12:
                IdPattern idPattern = new IdPattern((String) stack.get(i2 - 1).value);
                this.parser.setHasIdCall(true);
                return new Symbol(27, stack.get(i2 - 3).left, stack.get(i2).right, idPattern);
            case 13:
                return new Symbol(27, stack.get(i2 - 5).left, stack.get(i2).right, new KeyPattern((String) stack.get(i2 - 3).value, (String) stack.get(i2 - 1).value));
            case 14:
                return new Symbol(30, stack.get(i2 - 3).left, stack.get(i2).right, new ProcessingInstructionPattern((String) stack.get(i2 - 1).value));
            case 15:
                return new Symbol(31, stack.get(i2).left, stack.get(i2).right, (StepPattern) stack.get(i2).value);
            case 16:
                int i8 = i2 - 2;
                return new Symbol(31, stack.get(i8).left, stack.get(i2).right, new ParentPattern((StepPattern) stack.get(i8).value, (RelativePathPattern) stack.get(i2).value));
            case 17:
                int i9 = i2 - 2;
                return new Symbol(31, stack.get(i9).left, stack.get(i2).right, new AncestorPattern((StepPattern) stack.get(i9).value, (RelativePathPattern) stack.get(i2).value));
            case 18:
                return new Symbol(32, stack.get(i2).left, stack.get(i2).right, this.parser.createStepPattern(3, stack.get(i2).value, null));
            case 19:
                int i10 = i2 - 1;
                return new Symbol(32, stack.get(i10).left, stack.get(i2).right, this.parser.createStepPattern(3, stack.get(i10).value, (ArrayList) stack.get(i2).value));
            case 20:
                return new Symbol(32, stack.get(i2).left, stack.get(i2).right, (StepPattern) stack.get(i2).value);
            case 21:
                int i11 = i2 - 1;
                return new Symbol(32, stack.get(i11).left, stack.get(i2).right, (ProcessingInstructionPattern) ((StepPattern) stack.get(i11).value).setPredicates((ArrayList) stack.get(i2).value));
            case 22:
                int i12 = i2 - 1;
                return new Symbol(32, stack.get(i12).left, stack.get(i2).right, this.parser.createStepPattern(((Integer) stack.get(i12).value).intValue(), stack.get(i2).value, null));
            case 23:
                int i13 = i2 - 2;
                return new Symbol(32, stack.get(i13).left, stack.get(i2).right, this.parser.createStepPattern(((Integer) stack.get(i13).value).intValue(), stack.get(i2 - 1).value, (ArrayList) stack.get(i2).value));
            case 24:
                return new Symbol(32, stack.get(i2 - 1).left, stack.get(i2).right, (StepPattern) stack.get(i2).value);
            case 25:
                return new Symbol(32, stack.get(i2 - 2).left, stack.get(i2).right, (ProcessingInstructionPattern) ((StepPattern) stack.get(i2 - 1).value).setPredicates((ArrayList) stack.get(i2).value));
            case 26:
                return new Symbol(33, stack.get(i2).left, stack.get(i2).right, stack.get(i2).value);
            case 27:
                return new Symbol(33, stack.get(i2).left, stack.get(i2).right, -1);
            case 28:
                return new Symbol(33, stack.get(i2).left, stack.get(i2).right, 3);
            case 29:
                return new Symbol(33, stack.get(i2).left, stack.get(i2).right, 8);
            case 30:
                return new Symbol(33, stack.get(i2).left, stack.get(i2).right, 7);
            case 31:
                return new Symbol(34, stack.get(i2).left, stack.get(i2).right, null);
            case 32:
                return new Symbol(34, stack.get(i2).left, stack.get(i2).right, (QName) stack.get(i2).value);
            case 33:
                return new Symbol(42, stack.get(i2).left, stack.get(i2).right, 2);
            case 34:
                return new Symbol(42, stack.get(i2 - 1).left, stack.get(i2).right, 3);
            case 35:
                return new Symbol(42, stack.get(i2 - 1).left, stack.get(i2).right, 2);
            case 36:
                Expression expression = (Expression) stack.get(i2).value;
                ArrayList arrayList = new ArrayList();
                arrayList.add(expression);
                return new Symbol(35, stack.get(i2).left, stack.get(i2).right, arrayList);
            case 37:
                int i14 = i2 - 1;
                Expression expression2 = (Expression) stack.get(i14).value;
                ArrayList arrayList2 = (ArrayList) stack.get(i2).value;
                arrayList2.add(0, expression2);
                return new Symbol(35, stack.get(i14).left, stack.get(i2).right, arrayList2);
            case 38:
                return new Symbol(5, stack.get(i2 - 2).left, stack.get(i2).right, new Predicate((Expression) stack.get(i2 - 1).value));
            case 39:
                return new Symbol(2, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 40:
                return new Symbol(8, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 41:
                int i15 = i2 - 2;
                return new Symbol(8, stack.get(i15).left, stack.get(i2).right, new LogicalExpr(0, (Expression) stack.get(i15).value, (Expression) stack.get(i2).value));
            case 42:
                return new Symbol(9, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 43:
                int i16 = i2 - 2;
                return new Symbol(9, stack.get(i16).left, stack.get(i2).right, new LogicalExpr(1, (Expression) stack.get(i16).value, (Expression) stack.get(i2).value));
            case 44:
                return new Symbol(10, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 45:
                int i17 = i2 - 2;
                return new Symbol(10, stack.get(i17).left, stack.get(i2).right, new EqualityExpr(0, (Expression) stack.get(i17).value, (Expression) stack.get(i2).value));
            case 46:
                int i18 = i2 - 2;
                return new Symbol(10, stack.get(i18).left, stack.get(i2).right, new EqualityExpr(1, (Expression) stack.get(i18).value, (Expression) stack.get(i2).value));
            case 47:
                return new Symbol(11, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 48:
                int i19 = i2 - 2;
                return new Symbol(11, stack.get(i19).left, stack.get(i2).right, new RelationalExpr(3, (Expression) stack.get(i19).value, (Expression) stack.get(i2).value));
            case 49:
                int i20 = i2 - 2;
                return new Symbol(11, stack.get(i20).left, stack.get(i2).right, new RelationalExpr(2, (Expression) stack.get(i20).value, (Expression) stack.get(i2).value));
            case 50:
                int i21 = i2 - 2;
                return new Symbol(11, stack.get(i21).left, stack.get(i2).right, new RelationalExpr(5, (Expression) stack.get(i21).value, (Expression) stack.get(i2).value));
            case 51:
                int i22 = i2 - 2;
                return new Symbol(11, stack.get(i22).left, stack.get(i2).right, new RelationalExpr(4, (Expression) stack.get(i22).value, (Expression) stack.get(i2).value));
            case 52:
                return new Symbol(12, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 53:
                int i23 = i2 - 2;
                return new Symbol(12, stack.get(i23).left, stack.get(i2).right, new BinOpExpr(0, (Expression) stack.get(i23).value, (Expression) stack.get(i2).value));
            case 54:
                int i24 = i2 - 2;
                return new Symbol(12, stack.get(i24).left, stack.get(i2).right, new BinOpExpr(1, (Expression) stack.get(i24).value, (Expression) stack.get(i2).value));
            case 55:
                return new Symbol(13, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 56:
                int i25 = i2 - 2;
                return new Symbol(13, stack.get(i25).left, stack.get(i2).right, new BinOpExpr(2, (Expression) stack.get(i25).value, (Expression) stack.get(i2).value));
            case 57:
                int i26 = i2 - 2;
                return new Symbol(13, stack.get(i26).left, stack.get(i2).right, new BinOpExpr(3, (Expression) stack.get(i26).value, (Expression) stack.get(i2).value));
            case 58:
                int i27 = i2 - 2;
                return new Symbol(13, stack.get(i27).left, stack.get(i2).right, new BinOpExpr(4, (Expression) stack.get(i27).value, (Expression) stack.get(i2).value));
            case 59:
                return new Symbol(14, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 60:
                return new Symbol(14, stack.get(i2 - 1).left, stack.get(i2).right, new UnaryOpExpr((Expression) stack.get(i2).value));
            case 61:
                return new Symbol(18, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 62:
                int i28 = i2 - 2;
                return new Symbol(18, stack.get(i28).left, stack.get(i2).right, new UnionPathExpr((Expression) stack.get(i28).value, (Expression) stack.get(i2).value));
            case 63:
                return new Symbol(19, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 64:
                return new Symbol(19, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 65:
                int i29 = i2 - 2;
                return new Symbol(19, stack.get(i29).left, stack.get(i2).right, new FilterParentPath((Expression) stack.get(i29).value, (Expression) stack.get(i2).value));
            case 66:
                int i30 = i2 - 2;
                Expression expression3 = (Expression) stack.get(i30).value;
                Expression expression4 = (Expression) stack.get(i2).value;
                FilterParentPath filterParentPath = new FilterParentPath(new FilterParentPath(expression3, new Step(5, ((expression4 instanceof Step) && this.parser.isElementAxis(((Step) expression4).getAxis())) ? 1 : -1, null)), expression4);
                if (!(expression3 instanceof KeyCall)) {
                    filterParentPath.setDescendantAxis();
                }
                return new Symbol(19, stack.get(i30).left, stack.get(i2).right, filterParentPath);
            case 67:
                return new Symbol(4, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 68:
                return new Symbol(4, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 69:
                return new Symbol(21, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 70:
                int i31 = i2 - 2;
                Expression parentLocationPath3 = (Expression) stack.get(i31).value;
                Expression expression5 = (Expression) stack.get(i2).value;
                if ((parentLocationPath3 instanceof Step) && ((Step) parentLocationPath3).isAbbreviatedDot()) {
                    parentLocationPath3 = expression5;
                } else if (!((Step) expression5).isAbbreviatedDot()) {
                    parentLocationPath3 = new ParentLocationPath((RelativeLocationPath) parentLocationPath3, expression5);
                }
                return new Symbol(21, stack.get(i31).left, stack.get(i2).right, parentLocationPath3);
            case 71:
                return new Symbol(21, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 72:
                return new Symbol(23, stack.get(i2).left, stack.get(i2).right, new AbsoluteLocationPath());
            case 73:
                return new Symbol(23, stack.get(i2 - 1).left, stack.get(i2).right, new AbsoluteLocationPath((Expression) stack.get(i2).value));
            case 74:
                return new Symbol(23, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 75:
                int i32 = i2 - 2;
                Expression expression6 = (Expression) stack.get(i32).value;
                Step step2 = (Step) ((Expression) stack.get(i2).value);
                int axis = step2.getAxis();
                int nodeType = step2.getNodeType();
                List<Predicate> predicates = step2.getPredicates();
                if (axis == 3) {
                    i3 = 2;
                    if (nodeType != 2) {
                        if (predicates == null) {
                            step2.setAxis(4);
                            if (!(expression6 instanceof Step) || !((Step) expression6).isAbbreviatedDot()) {
                                parentLocationPath = step2;
                                parentLocationPath2 = new ParentLocationPath((RelativeLocationPath) expression6, step2);
                                parentLocationPath = parentLocationPath2;
                            }
                        } else if ((expression6 instanceof Step) && ((Step) expression6).isAbbreviatedDot()) {
                            parentLocationPath2 = new ParentLocationPath(new Step(5, 1, null), step2);
                            parentLocationPath = parentLocationPath2;
                        } else {
                            parentLocationPath = new ParentLocationPath((RelativeLocationPath) expression6, new ParentLocationPath(new Step(5, 1, null), step2));
                        }
                    }
                    parentLocationPath = step2;
                    return new Symbol(22, stack.get(i32).left, stack.get(i2).right, parentLocationPath);
                }
                i3 = 2;
                parentLocationPath = (axis == i3 || nodeType == i3) ? new ParentLocationPath((RelativeLocationPath) expression6, new ParentLocationPath(new Step(5, 1, null), step2)) : new ParentLocationPath((RelativeLocationPath) expression6, new ParentLocationPath(new Step(5, -1, null), step2));
                parentLocationPath = step2;
                return new Symbol(22, stack.get(i32).left, stack.get(i2).right, parentLocationPath);
            case 76:
                Expression expression7 = (Expression) stack.get(i2).value;
                return new Symbol(24, stack.get(i2 - 1).left, stack.get(i2).right, new AbsoluteLocationPath(this.parser.insertStep(new Step(5, ((expression7 instanceof Step) && this.parser.isElementAxis(((Step) expression7).getAxis())) ? 1 : -1, null), (RelativeLocationPath) expression7)));
            case 77:
                Object obj = stack.get(i2).value;
                return new Symbol(7, stack.get(i2).left, stack.get(i2).right, obj instanceof Step ? (Step) obj : new Step(3, this.parser.findNodeType(3, obj), null));
            case 78:
                int i33 = i2 - 1;
                Object obj2 = stack.get(i33).value;
                ArrayList arrayList3 = (ArrayList) stack.get(i2).value;
                if (obj2 instanceof Step) {
                    step = (Step) obj2;
                    step.addPredicates(arrayList3);
                } else {
                    step = new Step(3, this.parser.findNodeType(3, obj2), arrayList3);
                }
                return new Symbol(7, stack.get(i33).left, stack.get(i2).right, step);
            case 79:
                int i34 = i2 - 2;
                Integer num = (Integer) stack.get(i34).value;
                return new Symbol(7, stack.get(i34).left, stack.get(i2).right, new Step(num.intValue(), this.parser.findNodeType(num.intValue(), stack.get(i2 - 1).value), (ArrayList) stack.get(i2).value));
            case 80:
                int i35 = i2 - 1;
                Integer num2 = (Integer) stack.get(i35).value;
                return new Symbol(7, stack.get(i35).left, stack.get(i2).right, new Step(num2.intValue(), this.parser.findNodeType(num2.intValue(), stack.get(i2).value), null));
            case 81:
                return new Symbol(7, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 82:
                int i36 = i2 - 1;
                return new Symbol(41, stack.get(i36).left, stack.get(i2).right, (Integer) stack.get(i36).value);
            case 83:
                return new Symbol(41, stack.get(i2).left, stack.get(i2).right, 2);
            case 84:
                return new Symbol(40, stack.get(i2).left, stack.get(i2).right, 0);
            case 85:
                return new Symbol(40, stack.get(i2).left, stack.get(i2).right, 1);
            case 86:
                return new Symbol(40, stack.get(i2).left, stack.get(i2).right, 2);
            case 87:
                return new Symbol(40, stack.get(i2).left, stack.get(i2).right, 3);
            case 88:
                return new Symbol(40, stack.get(i2).left, stack.get(i2).right, 4);
            case 89:
                return new Symbol(40, stack.get(i2).left, stack.get(i2).right, 5);
            case 90:
                return new Symbol(40, stack.get(i2).left, stack.get(i2).right, 6);
            case 91:
                return new Symbol(40, stack.get(i2).left, stack.get(i2).right, 7);
            case 92:
                return new Symbol(40, stack.get(i2).left, stack.get(i2).right, 9);
            case 93:
                return new Symbol(40, stack.get(i2).left, stack.get(i2).right, 10);
            case 94:
                return new Symbol(40, stack.get(i2).left, stack.get(i2).right, 11);
            case 95:
                return new Symbol(40, stack.get(i2).left, stack.get(i2).right, 12);
            case 96:
                return new Symbol(40, stack.get(i2).left, stack.get(i2).right, 13);
            case 97:
                return new Symbol(20, stack.get(i2).left, stack.get(i2).right, new Step(13, -1, null));
            case 98:
                return new Symbol(20, stack.get(i2).left, stack.get(i2).right, new Step(10, -1, null));
            case 99:
                return new Symbol(6, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 100:
                int i37 = i2 - 1;
                return new Symbol(6, stack.get(i37).left, stack.get(i2).right, new FilterExpr((Expression) stack.get(i37).value, (ArrayList) stack.get(i2).value));
            case 101:
                return new Symbol(17, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 102:
                return new Symbol(17, stack.get(i2 - 2).left, stack.get(i2).right, (Expression) stack.get(i2 - 1).value);
            case 103:
                String str = (String) stack.get(i2).value;
                int iLastIndexOf = str.lastIndexOf(58);
                String strLookupNamespace = iLastIndexOf > 0 ? this.parser._symbolTable.lookupNamespace(str.substring(0, iLastIndexOf)) : null;
                return new Symbol(17, stack.get(i2).left, stack.get(i2).right, strLookupNamespace == null ? new LiteralExpr(str) : new LiteralExpr(str, strLookupNamespace));
            case 104:
                Long l = (Long) stack.get(i2).value;
                if (l.longValue() < -2147483648L || l.longValue() > 2147483647L) {
                    realExpr = new RealExpr(l.longValue());
                } else if (l.doubleValue() == XPath.MATCH_SCORE_QNAME) {
                    realExpr = new RealExpr(l.doubleValue());
                } else {
                    realExpr = (l.intValue() != 0 && l.doubleValue() == XPath.MATCH_SCORE_QNAME) ? new RealExpr(l.doubleValue()) : new IntExpr(l.intValue());
                }
                return new Symbol(17, stack.get(i2).left, stack.get(i2).right, realExpr);
            case 105:
                return new Symbol(17, stack.get(i2).left, stack.get(i2).right, new RealExpr(((Double) stack.get(i2).value).doubleValue()));
            case 106:
                return new Symbol(17, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 107:
                QName qName = (QName) stack.get(i2).value;
                SyntaxTreeNode syntaxTreeNodeLookupName = this.parser.lookupName(qName);
                if (syntaxTreeNodeLookupName == null) {
                    unresolvedRef = null;
                } else if (syntaxTreeNodeLookupName instanceof Variable) {
                    unresolvedRef = new VariableRef((Variable) syntaxTreeNodeLookupName);
                } else {
                    unresolvedRef = syntaxTreeNodeLookupName instanceof Param ? new ParameterRef((Param) syntaxTreeNodeLookupName) : new UnresolvedRef(qName);
                }
                if (syntaxTreeNodeLookupName == null) {
                    unresolvedRef = new UnresolvedRef(qName);
                }
                return new Symbol(15, stack.get(i2 - 1).left, stack.get(i2).right, unresolvedRef);
            case 108:
                int i38 = i2 - 2;
                QName qName2 = (QName) stack.get(i38).value;
                if (qName2 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_CURRENT_STRING)) {
                    namespaceUriCall = new CurrentCall(qName2);
                } else if (qName2 == this.parser.getQNameIgnoreDefaultNs("number")) {
                    namespaceUriCall = new NumberCall(qName2, XPathParser.EmptyArgs);
                } else if (qName2 == this.parser.getQNameIgnoreDefaultNs("string")) {
                    namespaceUriCall = new StringCall(qName2, XPathParser.EmptyArgs);
                } else if (qName2 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_CONCAT_STRING)) {
                    namespaceUriCall = new ConcatCall(qName2, XPathParser.EmptyArgs);
                } else if (qName2 == this.parser.getQNameIgnoreDefaultNs("true")) {
                    namespaceUriCall = new BooleanExpr(true);
                } else if (qName2 == this.parser.getQNameIgnoreDefaultNs("false")) {
                    namespaceUriCall = new BooleanExpr(false);
                } else if (qName2 == this.parser.getQNameIgnoreDefaultNs("name")) {
                    namespaceUriCall = new NameCall(qName2);
                } else if (qName2 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_GENERATE_ID_STRING)) {
                    namespaceUriCall = new GenerateIdCall(qName2, XPathParser.EmptyArgs);
                } else if (qName2 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_STRING_LENGTH_STRING)) {
                    namespaceUriCall = new StringLengthCall(qName2, XPathParser.EmptyArgs);
                } else if (qName2 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_POSITION_STRING)) {
                    namespaceUriCall = new PositionCall(qName2);
                } else if (qName2 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_LAST_STRING)) {
                    namespaceUriCall = new LastCall(qName2);
                } else if (qName2 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_LOCAL_PART_STRING)) {
                    namespaceUriCall = new LocalNameCall(qName2);
                } else {
                    namespaceUriCall = qName2 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_NAMESPACE_STRING) ? new NamespaceUriCall(qName2) : new FunctionCall(qName2, XPathParser.EmptyArgs);
                }
                return new Symbol(16, stack.get(i38).left, stack.get(i2).right, namespaceUriCall);
            case 109:
                int i39 = i2 - 3;
                QName qName3 = (QName) stack.get(i39).value;
                ArrayList arrayList4 = (ArrayList) stack.get(i2 - 1).value;
                if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_CONCAT_STRING)) {
                    functionCall = new ConcatCall(qName3, arrayList4);
                } else if (qName3 == this.parser.getQNameIgnoreDefaultNs("number")) {
                    functionCall = new NumberCall(qName3, arrayList4);
                } else {
                    QName qNameIgnoreDefaultNs = this.parser.getQNameIgnoreDefaultNs(Constants.DOCUMENT_PNAME);
                    XPathParser xPathParser = this.parser;
                    if (qName3 == qNameIgnoreDefaultNs) {
                        xPathParser.setMultiDocument(true);
                        functionCall = new DocumentCall(qName3, arrayList4);
                    } else if (qName3 == xPathParser.getQNameIgnoreDefaultNs("string")) {
                        functionCall = new StringCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs("boolean")) {
                        functionCall = new BooleanCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs("name")) {
                        functionCall = new NameCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_GENERATE_ID_STRING)) {
                        functionCall = new GenerateIdCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_NOT_STRING)) {
                        functionCall = new NotCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs("format-number")) {
                        functionCall = new FormatNumberCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_UNPARSED_ENTITY_URI_STRING)) {
                        functionCall = new UnparsedEntityUriCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs("key")) {
                        functionCall = new KeyCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs("id")) {
                        KeyCall keyCall = new KeyCall(qName3, arrayList4);
                        this.parser.setHasIdCall(true);
                        functionCall = keyCall;
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_CEILING_STRING)) {
                        functionCall = new CeilingCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_ROUND_STRING)) {
                        functionCall = new RoundCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_FLOOR_STRING)) {
                        functionCall = new FloorCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_CONTAINS_STRING)) {
                        functionCall = new ContainsCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_STRING_LENGTH_STRING)) {
                        functionCall = new StringLengthCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_STARTS_WITH_STRING)) {
                        functionCall = new StartsWithCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_EXT_FUNCTION_AVAILABLE_STRING)) {
                        functionCall = new FunctionAvailableCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_EXT_ELEM_AVAILABLE_STRING)) {
                        functionCall = new ElementAvailableCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_LOCAL_PART_STRING)) {
                        functionCall = new LocalNameCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs("lang")) {
                        functionCall = new LangCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQNameIgnoreDefaultNs(Keywords.FUNC_NAMESPACE_STRING)) {
                        functionCall = new NamespaceUriCall(qName3, arrayList4);
                    } else if (qName3 == this.parser.getQName(Constants.TRANSLET_URI, "xsltc", "cast")) {
                        functionCall = new CastCall(qName3, arrayList4);
                    } else if (qName3.getLocalPart().equals("nodeset") || qName3.getLocalPart().equals("node-set")) {
                        this.parser.setCallsNodeset(true);
                        functionCall = new FunctionCall(qName3, arrayList4);
                    } else {
                        functionCall = new FunctionCall(qName3, arrayList4);
                    }
                }
                return new Symbol(16, stack.get(i39).left, stack.get(i2).right, functionCall);
            case 110:
                Expression expression8 = (Expression) stack.get(i2).value;
                ArrayList arrayList5 = new ArrayList();
                arrayList5.add(expression8);
                return new Symbol(36, stack.get(i2).left, stack.get(i2).right, arrayList5);
            case 111:
                int i40 = i2 - 2;
                Expression expression9 = (Expression) stack.get(i40).value;
                ArrayList arrayList6 = (ArrayList) stack.get(i2).value;
                arrayList6.add(0, expression9);
                return new Symbol(36, stack.get(i40).left, stack.get(i2).right, arrayList6);
            case 112:
                return new Symbol(38, stack.get(i2).left, stack.get(i2).right, (QName) stack.get(i2).value);
            case 113:
                return new Symbol(39, stack.get(i2).left, stack.get(i2).right, (QName) stack.get(i2).value);
            case 114:
                return new Symbol(3, stack.get(i2).left, stack.get(i2).right, (Expression) stack.get(i2).value);
            case 115:
                return new Symbol(25, stack.get(i2).left, stack.get(i2).right, stack.get(i2).value);
            case 116:
                return new Symbol(25, stack.get(i2).left, stack.get(i2).right, -1);
            case 117:
                return new Symbol(25, stack.get(i2).left, stack.get(i2).right, 3);
            case 118:
                return new Symbol(25, stack.get(i2).left, stack.get(i2).right, 8);
            case 119:
                EqualityExpr equalityExpr = new EqualityExpr(0, new NameCall(this.parser.getQNameIgnoreDefaultNs("name")), new LiteralExpr((String) stack.get(i2 - 1).value));
                ArrayList arrayList7 = new ArrayList();
                arrayList7.add(new Predicate(equalityExpr));
                return new Symbol(25, stack.get(i2 - 3).left, stack.get(i2).right, new Step(3, 7, arrayList7));
            case 120:
                return new Symbol(25, stack.get(i2).left, stack.get(i2).right, 7);
            case 121:
                return new Symbol(26, stack.get(i2).left, stack.get(i2).right, null);
            case 122:
                return new Symbol(26, stack.get(i2).left, stack.get(i2).right, (QName) stack.get(i2).value);
            case 123:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs((String) stack.get(i2).value));
            case 124:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("div"));
            case 125:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("mod"));
            case 126:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("key"));
            case 127:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("child"));
            case 128:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("ancestor-or-self"));
            case 129:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("attribute"));
            case 130:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("child"));
            case 131:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("decendant"));
            case 132:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("decendant-or-self"));
            case 133:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("following"));
            case 134:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("following-sibling"));
            case 135:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs(com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_NAMESPACE));
            case 136:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("parent"));
            case 137:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("preceding"));
            case 138:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("preceding-sibling"));
            case 139:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("self"));
            case 140:
                return new Symbol(37, stack.get(i2).left, stack.get(i2).right, this.parser.getQNameIgnoreDefaultNs("id"));
            default:
                throw new Exception("Invalid action number found in internal parse table");
        }
    }
}
