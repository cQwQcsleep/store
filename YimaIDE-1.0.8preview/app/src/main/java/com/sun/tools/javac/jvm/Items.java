package com.sun.tools.javac.jvm;

import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xpath.internal.XPath;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Assert;
import defpackage.u47;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Items {
    Code code;
    PoolWriter poolWriter;
    Symtab syms;
    Types types;
    private final Item[] stackItem = new Item[9];
    private final Item voidItem = new Item(8) { // from class: com.sun.tools.javac.jvm.Items.1
        @Override // com.sun.tools.javac.jvm.Items.Item
        public String toString() {
            return PsiKeyword.VOID;
        }
    };
    private final Item thisItem = new SelfItem(false);
    private final Item superItem = new SelfItem(true);

    public class AssignItem extends Item {
        Item lhs;

        public AssignItem(Item item) {
            super(item.typecode);
            this.lhs = item;
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void drop() {
            this.lhs.store();
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void duplicate() {
            load().duplicate();
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public Item load() {
            this.lhs.stash(this.typecode);
            this.lhs.store();
            return Items.this.stackItem[this.typecode];
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void stash(int i) {
            Assert.error();
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public String toString() {
            return "assign(lhs = " + this.lhs + ")";
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public int width() {
            return this.lhs.width() + Code.width(this.typecode);
        }
    }

    public class CondItem extends Item {
        Code.Chain falseJumps;
        int opcode;
        JCTree tree;
        Code.Chain trueJumps;

        public CondItem(int i, Code.Chain chain, Code.Chain chain2) {
            super(5);
            this.opcode = i;
            this.trueJumps = chain;
            this.falseJumps = chain2;
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void drop() {
            load().drop();
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void duplicate() {
            load().duplicate();
        }

        public boolean isFalse() {
            return this.trueJumps == null && this.opcode == 168;
        }

        public boolean isTrue() {
            return this.falseJumps == null && this.opcode == 167;
        }

        public Code.Chain jumpFalse() {
            if (this.tree == null) {
                return Code.mergeChains(this.falseJumps, Items.this.code.branch(Code.negate(this.opcode)));
            }
            int iCurCP = Items.this.code.curCP();
            Code.Chain chainMergeChains = Code.mergeChains(this.falseJumps, Items.this.code.branch(Code.negate(this.opcode)));
            Code code = Items.this.code;
            code.crt.put(this.tree, 256, iCurCP, code.curCP());
            return chainMergeChains;
        }

        public Code.Chain jumpTrue() {
            if (this.tree == null) {
                return Code.mergeChains(this.trueJumps, Items.this.code.branch(this.opcode));
            }
            int iCurCP = Items.this.code.curCP();
            Code.Chain chainMergeChains = Code.mergeChains(this.trueJumps, Items.this.code.branch(this.opcode));
            Code code = Items.this.code;
            code.crt.put(this.tree, 128, iCurCP, code.curCP());
            return chainMergeChains;
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public Item load() {
            Code.Chain chainBranch;
            Code.Chain chainJumpFalse = jumpFalse();
            if (isFalse()) {
                chainBranch = null;
            } else {
                Items.this.code.resolve(this.trueJumps);
                Items.this.code.emitop0(4);
                chainBranch = Items.this.code.branch(167);
            }
            if (chainJumpFalse != null) {
                Items.this.code.resolve(chainJumpFalse);
                Items.this.code.emitop0(3);
            }
            Items.this.code.resolve(chainBranch);
            return Items.this.stackItem[this.typecode];
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public CondItem mkCond() {
            return this;
        }

        public CondItem negate() {
            CondItem condItem = Items.this.new CondItem(Code.negate(this.opcode), this.falseJumps, this.trueJumps);
            condItem.tree = this.tree;
            return condItem;
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void stash(int i) {
            Assert.error();
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public String toString() {
            return "cond(" + Code.mnem(this.opcode) + ")";
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public int width() {
            throw new AssertionError();
        }
    }

    public class DynamicItem extends StaticItem {
        public DynamicItem(Symbol symbol) {
            super(symbol);
        }

        @Override // com.sun.tools.javac.jvm.Items.StaticItem, com.sun.tools.javac.jvm.Items.Item
        public Item invoke() {
            Assert.check(this.member.kind == Kinds.Kind.MTH);
            Type.MethodType methodType = (Type.MethodType) this.member.erasure(Items.this.types);
            int iTypecode = Code.typecode(methodType.restype);
            Items.this.code.emitInvokedynamic((Symbol.DynamicMethodSymbol) this.member, methodType);
            return Items.this.stackItem[iTypecode];
        }

        @Override // com.sun.tools.javac.jvm.Items.StaticItem, com.sun.tools.javac.jvm.Items.Item
        public Item load() {
            Assert.check(this.member.kind == Kinds.Kind.VAR);
            int iTypecode = Code.typecode(this.member.erasure(Items.this.types));
            Items.this.code.emitLdc((Symbol.DynamicVarSymbol) this.member);
            return Items.this.stackItem[iTypecode];
        }

        @Override // com.sun.tools.javac.jvm.Items.StaticItem, com.sun.tools.javac.jvm.Items.Item
        public void store() {
            Assert.error("this method shouldn't be invoked");
        }

        @Override // com.sun.tools.javac.jvm.Items.StaticItem, com.sun.tools.javac.jvm.Items.Item
        public String toString() {
            return "dynamic(" + this.member + ")";
        }
    }

    public class IndexedItem extends Item {
        public IndexedItem(Type type) {
            super(Code.typecode(type));
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void drop() {
            Items.this.code.emitop0(88);
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void duplicate() {
            Items.this.code.emitop0(92);
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public Item load() {
            Items.this.code.emitop0(this.typecode + 46);
            return Items.this.stackItem[this.typecode];
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void stash(int i) {
            Items.this.code.emitop0(((Code.width(i) - 1) * 3) + 91);
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void store() {
            Items.this.code.emitop0(this.typecode + 79);
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public String toString() {
            return "indexed(" + ByteCodes.typecodeNames[this.typecode] + ")";
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public int width() {
            return 2;
        }
    }

    public class LocalItem extends Item {
        int reg;
        Type type;

        public LocalItem(Type type, int i) {
            super(Code.typecode(type));
            Assert.check(i >= 0);
            this.type = type;
            this.reg = i;
        }

        public void incr(int i) {
            if (this.typecode == 0 && i >= -32768 && i <= 32767) {
                Items.this.code.emitop1w(132, this.reg, i);
                return;
            }
            load();
            Items items = Items.this;
            if (i >= 0) {
                items.makeImmediateItem(items.syms.intType, Integer.valueOf(i)).load();
                Items.this.code.emitop0(96);
            } else {
                items.makeImmediateItem(items.syms.intType, Integer.valueOf(-i)).load();
                Items.this.code.emitop0(100);
            }
            Items items2 = Items.this;
            items2.makeStackItem(items2.syms.intType).coerce(this.typecode);
            store();
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public Item load() {
            int i = this.reg;
            Items items = Items.this;
            if (i <= 3) {
                items.code.emitop0((Code.truncate(this.typecode) * 4) + 26 + this.reg);
            } else {
                items.code.emitop1w(Code.truncate(this.typecode) + 21, this.reg);
            }
            return Items.this.stackItem[this.typecode];
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void store() {
            int i = this.reg;
            Items items = Items.this;
            if (i <= 3) {
                items.code.emitop0((Code.truncate(this.typecode) * 4) + 59 + this.reg);
            } else {
                items.code.emitop1w(Code.truncate(this.typecode) + 54, this.reg);
            }
            Items.this.code.setDefined(this.reg);
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public String toString() {
            return "localItem(type=" + this.type + "; reg=" + this.reg + ")";
        }
    }

    public class MemberItem extends Item {
        Symbol member;
        boolean nonvirtual;

        public MemberItem(Symbol symbol, boolean z) {
            super(Code.typecode(symbol.erasure(Items.this.types)));
            this.member = symbol;
            this.nonvirtual = z;
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void drop() {
            Items.this.stackItem[4].drop();
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void duplicate() {
            Items.this.stackItem[4].duplicate();
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public Item invoke() {
            Type.MethodType methodType = (Type.MethodType) this.member.externalType(Items.this.types);
            int iTypecode = Code.typecode(methodType.restype);
            if ((this.member.owner.flags() & 512) == 0 || this.nonvirtual) {
                boolean z = this.nonvirtual;
                Items items = Items.this;
                if (z) {
                    items.code.emitInvokespecial(this.member, methodType);
                } else {
                    items.code.emitInvokevirtual(this.member, methodType);
                }
            } else {
                Items.this.code.emitInvokeinterface(this.member, methodType);
            }
            return Items.this.stackItem[iTypecode];
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public Item load() {
            Items.this.code.emitop2(180, this.member, new u47());
            return Items.this.stackItem[this.typecode];
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void stash(int i) {
            Items.this.stackItem[4].stash(i);
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void store() {
            Items.this.code.emitop2(181, this.member, new u47());
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public String toString() {
            StringBuilder sb = new StringBuilder("member(");
            sb.append(this.member);
            sb.append(this.nonvirtual ? " nonvirtual)" : ")");
            return sb.toString();
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public int width() {
            return 1;
        }
    }

    public class SelfItem extends Item {
        boolean isSuper;

        public SelfItem(boolean z) {
            super(4);
            this.isSuper = z;
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public Item load() {
            Items.this.code.emitop0(42);
            return Items.this.stackItem[this.typecode];
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public String toString() {
            return this.isSuper ? PsiKeyword.SUPER : PsiKeyword.THIS;
        }
    }

    public class StackItem extends Item {
        public StackItem(int i) {
            super(i);
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void drop() {
            Items.this.code.emitop0(width() == 2 ? 88 : 87);
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void duplicate() {
            Items.this.code.emitop0(width() == 2 ? 92 : 89);
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public Item load() {
            return this;
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void stash(int i) {
            Items.this.code.emitop0((width() == 2 ? 91 : 90) + ((Code.width(i) - 1) * 3));
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public String toString() {
            return "stack(" + ByteCodes.typecodeNames[this.typecode] + ")";
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public int width() {
            return Code.width(this.typecode);
        }
    }

    public class StaticItem extends Item {
        Symbol member;

        public StaticItem(Symbol symbol) {
            super(Code.typecode(symbol.erasure(Items.this.types)));
            this.member = symbol;
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public Item invoke() {
            Type.MethodType methodType = (Type.MethodType) this.member.erasure(Items.this.types);
            int iTypecode = Code.typecode(methodType.restype);
            Items.this.code.emitInvokestatic(this.member, methodType);
            return Items.this.stackItem[iTypecode];
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public Item load() {
            Items.this.code.emitop2(178, this.member, new u47());
            return Items.this.stackItem[this.typecode];
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public void store() {
            Items.this.code.emitop2(179, this.member, new u47());
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public String toString() {
            return "static(" + this.member + ")";
        }
    }

    public Items(PoolWriter poolWriter, Code code, Symtab symtab, Types types) {
        this.code = code;
        this.poolWriter = poolWriter;
        this.types = types;
        int i = 0;
        while (true) {
            Item[] itemArr = this.stackItem;
            if (i >= 8) {
                itemArr[8] = this.voidItem;
                this.syms = symtab;
                return;
            } else {
                itemArr[i] = new StackItem(i);
                i++;
            }
        }
    }

    public Item makeAssignItem(Item item) {
        return new AssignItem(item);
    }

    public CondItem makeCondItem(int i, Code.Chain chain, Code.Chain chain2) {
        return new CondItem(i, chain, chain2);
    }

    public Item makeDynamicItem(Symbol symbol) {
        return new DynamicItem(symbol);
    }

    public Item makeImmediateItem(Type type, Object obj) {
        return new ImmediateItem(type, obj);
    }

    public Item makeIndexedItem(Type type) {
        return new IndexedItem(type);
    }

    public LocalItem makeLocalItem(Symbol.VarSymbol varSymbol) {
        return new LocalItem(varSymbol.erasure(this.types), varSymbol.adr);
    }

    public Item makeMemberItem(Symbol symbol, boolean z) {
        return new MemberItem(symbol, z);
    }

    public Item makeStackItem(Type type) {
        return this.stackItem[Code.typecode(type)];
    }

    public Item makeStaticItem(Symbol symbol) {
        return new StaticItem(symbol);
    }

    public Item makeSuperItem() {
        return this.superItem;
    }

    public Item makeThisItem() {
        return this.thisItem;
    }

    public Item makeVoidItem() {
        return this.voidItem;
    }

    public CondItem makeCondItem(int i) {
        return makeCondItem(i, null, null);
    }

    private LocalItem makeLocalItem(Type type, int i) {
        return new LocalItem(type, i);
    }

    public class ImmediateItem extends Item {
        final PoolConstant.LoadableConstant value;

        public ImmediateItem(Type type, Object obj) {
            super(Code.typecode(type));
            switch (this.typecode) {
                case 0:
                case 5:
                case 6:
                case 7:
                    this.value = PoolConstant.LoadableConstant.Int(((Integer) obj).intValue());
                    return;
                case 1:
                    this.value = PoolConstant.LoadableConstant.Long(((Long) obj).longValue());
                    return;
                case 2:
                    this.value = PoolConstant.LoadableConstant.Float(((Float) obj).floatValue());
                    return;
                case 3:
                    this.value = PoolConstant.LoadableConstant.Double(((Double) obj).doubleValue());
                    return;
                case 4:
                    this.value = PoolConstant.LoadableConstant.String((String) obj);
                    return;
                default:
                    throw new UnsupportedOperationException("unsupported tag: " + this.typecode);
            }
        }

        private boolean isPosZero(double d) {
            return d == XPath.MATCH_SCORE_QNAME && 1.0d / d > XPath.MATCH_SCORE_QNAME;
        }

        private Number numericValue() {
            return (Number) ((PoolConstant.LoadableConstant.BasicConstant) this.value).data;
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public Item coerce(int i) {
            int i2 = this.typecode;
            if (i2 != i) {
                if (i != 0) {
                    if (i == 1) {
                        Items items = Items.this;
                        return items.new ImmediateItem(items.syms.longType, Long.valueOf(numericValue().longValue()));
                    }
                    if (i == 2) {
                        Items items2 = Items.this;
                        return items2.new ImmediateItem(items2.syms.floatType, Float.valueOf(numericValue().floatValue()));
                    }
                    if (i == 3) {
                        Items items3 = Items.this;
                        return items3.new ImmediateItem(items3.syms.doubleType, Double.valueOf(numericValue().doubleValue()));
                    }
                    if (i == 5) {
                        Items items4 = Items.this;
                        return items4.new ImmediateItem(items4.syms.byteType, Integer.valueOf((byte) numericValue().intValue()));
                    }
                    if (i == 6) {
                        Items items5 = Items.this;
                        return items5.new ImmediateItem(items5.syms.charType, Integer.valueOf((char) numericValue().intValue()));
                    }
                    if (i != 7) {
                        return super.coerce(i);
                    }
                    Items items6 = Items.this;
                    return items6.new ImmediateItem(items6.syms.shortType, Integer.valueOf((short) numericValue().intValue()));
                }
                if (Code.truncate(i2) != 0 || this.typecode == 6) {
                    Items items7 = Items.this;
                    return items7.new ImmediateItem(items7.syms.intType, Integer.valueOf(numericValue().intValue()));
                }
            }
            return this;
        }

        /* JADX WARN: Code duplicated, block: B:21:0x0066  */
        @Override // com.sun.tools.javac.jvm.Items.Item
        public Item load() {
            switch (this.typecode) {
                case 0:
                case 5:
                case 6:
                case 7:
                    int iIntValue = numericValue().intValue();
                    if (-1 <= iIntValue && iIntValue <= 5) {
                        Items.this.code.emitop0(iIntValue + 3);
                    } else if (-128 <= iIntValue && iIntValue <= 127) {
                        Items.this.code.emitop1(16, iIntValue);
                    } else if (-32768 <= iIntValue && iIntValue <= 32767) {
                        Items.this.code.emitop2(17, iIntValue);
                    } else {
                        Items.this.code.emitLdc(this.value);
                    }
                    break;
                case 1:
                    long jLongValue = numericValue().longValue();
                    if (jLongValue == 0 || jLongValue == 1) {
                        Items.this.code.emitop0(((int) jLongValue) + 9);
                    } else {
                        Items.this.code.emitLdc(this.value);
                    }
                    break;
                case 2:
                    float fFloatValue = numericValue().floatValue();
                    if (!isPosZero(fFloatValue)) {
                        double d = fFloatValue;
                        if (d == 1.0d || d == 2.0d) {
                            Items.this.code.emitop0(((int) fFloatValue) + 11);
                        } else {
                            Items.this.code.emitLdc(this.value);
                        }
                    } else {
                        Items.this.code.emitop0(((int) fFloatValue) + 11);
                    }
                    break;
                case 3:
                    double dDoubleValue = numericValue().doubleValue();
                    if (isPosZero(dDoubleValue) || dDoubleValue == 1.0d) {
                        Items.this.code.emitop0(((int) dDoubleValue) + 14);
                    } else {
                        Items.this.code.emitLdc(this.value);
                    }
                    break;
                case 4:
                    Items.this.code.emitLdc(this.value);
                    break;
                default:
                    Assert.error();
                    break;
            }
            return Items.this.stackItem[this.typecode];
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public CondItem mkCond() {
            return Items.this.makeCondItem(numericValue().intValue() != 0 ? 167 : 168);
        }

        @Override // com.sun.tools.javac.jvm.Items.Item
        public String toString() {
            return "immediate(" + this.value + ")";
        }

        private boolean isPosZero(float f) {
            return f == 0.0f && 1.0f / f > 0.0f;
        }
    }

    public abstract class Item {
        int typecode;

        public Item(int i) {
            this.typecode = i;
        }

        public Item coerce(int i) {
            if (this.typecode == i) {
                return this;
            }
            load();
            int iTruncate = Code.truncate(this.typecode);
            int iTruncate2 = Code.truncate(i);
            if (iTruncate != iTruncate2) {
                Items.this.code.emitop0((iTruncate * 3) + 133 + (iTruncate2 > iTruncate ? iTruncate2 - 1 : iTruncate2));
            }
            if (i != iTruncate2) {
                Items.this.code.emitop0(i + 140);
            }
            return Items.this.stackItem[i];
        }

        public void drop() {
        }

        public void duplicate() {
        }

        public Item invoke() {
            throw new AssertionError(this);
        }

        public Item load() {
            throw new AssertionError();
        }

        public CondItem mkCond() {
            load();
            return Items.this.makeCondItem(154);
        }

        public void stash(int i) {
            Items.this.stackItem[i].duplicate();
        }

        public void store() {
            throw new AssertionError("store unsupported: " + this);
        }

        public abstract String toString();

        public int width() {
            return 0;
        }

        public Item coerce(Type type) {
            return coerce(Code.typecode(type));
        }
    }
}
