package com.sun.org.apache.xerces.internal.impl.xpath.regex;

import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class Op {
    static final int ANCHOR = 5;
    static final int BACKREFERENCE = 16;
    static final int CAPTURE = 15;
    static final int CHAR = 1;
    static final int CLOSURE = 7;
    static final int CONDITION = 26;
    static final boolean COUNT = false;
    static final int DOT = 0;
    static final int INDEPENDENT = 24;
    static final int LOOKAHEAD = 20;
    static final int LOOKBEHIND = 22;
    static final int MODIFIER = 25;
    static final int NEGATIVELOOKAHEAD = 21;
    static final int NEGATIVELOOKBEHIND = 23;
    static final int NONGREEDYCLOSURE = 8;
    static final int NONGREEDYQUESTION = 10;
    static final int NRANGE = 4;
    static final int QUESTION = 9;
    static final int RANGE = 3;
    static final int STRING = 6;
    static final int UNION = 11;
    static int nofinstances;
    Op next = null;
    final int type;

    public static class CharOp extends Op {
        final int charData;

        public CharOp(int i, int i2) {
            super(i);
            this.charData = i2;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Op
        public int getData() {
            return this.charData;
        }
    }

    public static class ChildOp extends Op {
        Op child;

        public ChildOp(int i) {
            super(i);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Op
        public Op getChild() {
            return this.child;
        }

        public void setChild(Op op) {
            this.child = op;
        }
    }

    public static class ConditionOp extends Op {
        final Op condition;
        final Op no;
        final int refNumber;
        final Op yes;

        public ConditionOp(int i, int i2, Op op, Op op2, Op op3) {
            super(i);
            this.refNumber = i2;
            this.condition = op;
            this.yes = op2;
            this.no = op3;
        }
    }

    public static class ModifierOp extends ChildOp {
        final int v1;
        final int v2;

        public ModifierOp(int i, int i2, int i3) {
            super(i);
            this.v1 = i2;
            this.v2 = i3;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Op
        public int getData() {
            return this.v1;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Op
        public int getData2() {
            return this.v2;
        }
    }

    public static class RangeOp extends Op {
        final Token tok;

        public RangeOp(int i, Token token) {
            super(i);
            this.tok = token;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Op
        public RangeToken getToken() {
            return (RangeToken) this.tok;
        }
    }

    public static class StringOp extends Op {
        final String string;

        public StringOp(int i, String str) {
            super(i);
            this.string = str;
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Op
        public String getString() {
            return this.string;
        }
    }

    public static class UnionOp extends Op {
        final ArrayList<Op> branches;

        public UnionOp(int i, int i2) {
            super(i);
            this.branches = new ArrayList<>(i2);
        }

        public void addElement(Op op) {
            this.branches.add(op);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Op
        public Op elementAt(int i) {
            return this.branches.get(i);
        }

        @Override // com.sun.org.apache.xerces.internal.impl.xpath.regex.Op
        public int size() {
            return this.branches.size();
        }
    }

    public Op(int i) {
        this.type = i;
    }

    public static CharOp createAnchor(int i) {
        return new CharOp(5, i);
    }

    public static CharOp createBackReference(int i) {
        return new CharOp(16, i);
    }

    public static CharOp createCapture(int i, Op op) {
        CharOp charOp = new CharOp(15, i);
        charOp.next = op;
        return charOp;
    }

    public static CharOp createChar(int i) {
        return new CharOp(1, i);
    }

    public static ChildOp createClosure(int i) {
        return new ModifierOp(7, i, -1);
    }

    public static ConditionOp createCondition(Op op, int i, Op op2, Op op3, Op op4) {
        ConditionOp conditionOp = new ConditionOp(26, i, op2, op3, op4);
        conditionOp.next = op;
        return conditionOp;
    }

    public static Op createDot() {
        return new Op(0);
    }

    public static ChildOp createIndependent(Op op, Op op2) {
        ChildOp childOp = new ChildOp(24);
        childOp.setChild(op2);
        childOp.next = op;
        return childOp;
    }

    public static ChildOp createLook(int i, Op op, Op op2) {
        ChildOp childOp = new ChildOp(i);
        childOp.setChild(op2);
        childOp.next = op;
        return childOp;
    }

    public static ModifierOp createModifier(Op op, Op op2, int i, int i2) {
        ModifierOp modifierOp = new ModifierOp(25, i, i2);
        modifierOp.setChild(op2);
        modifierOp.next = op;
        return modifierOp;
    }

    public static ChildOp createNonGreedyClosure() {
        return new ChildOp(8);
    }

    public static ChildOp createQuestion(boolean z) {
        return new ChildOp(z ? 10 : 9);
    }

    public static RangeOp createRange(Token token) {
        return new RangeOp(3, token);
    }

    public static StringOp createString(String str) {
        return new StringOp(6, str);
    }

    public static UnionOp createUnion(int i) {
        return new UnionOp(11, i);
    }

    public Op elementAt(int i) {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    public Op getChild() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    public int getData() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    public int getData2() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    public String getString() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    public RangeToken getToken() {
        throw new RuntimeException("Internal Error: type=" + this.type);
    }

    public int size() {
        return 0;
    }
}
