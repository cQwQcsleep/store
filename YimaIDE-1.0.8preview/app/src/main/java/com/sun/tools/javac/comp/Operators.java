package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Operators;
import com.sun.tools.javac.jvm.ByteCodes;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import defpackage.bqa;
import defpackage.vpa;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Operators {
    protected static final Context.Key<Operators> operatorsKey = new Context.Key<>();
    private final Log log;
    private final Names names;
    public final Symbol.OperatorSymbol noOpSymbol;
    private final Symtab syms;
    private final Types types;
    private Map<Name, List<UnaryOperatorHelper>> unaryOperators = new HashMap(JCTree.Tag.getNumberOfOperators());
    private Map<Name, List<BinaryOperatorHelper>> binaryOperators = new HashMap(JCTree.Tag.getNumberOfOperators());
    private Name[] opname = new Name[JCTree.Tag.getNumberOfOperators()];

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.Operators$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;

        static {
            int[] iArr = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr;
            try {
                iArr[TypeTag.BYTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.SHORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.CHAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public class BinaryBooleanOperator extends BinaryOperatorHelper {
        public BinaryBooleanOperator(JCTree.Tag tag) {
            super(tag);
        }

        @Override // com.sun.tools.javac.comp.Operators.BinaryOperatorHelper
        public Symbol.OperatorSymbol resolve(Type type, Type type2) {
            return doLookup(Operators.this.syms.booleanType, Operators.this.syms.booleanType);
        }

        @Override // java.util.function.BiPredicate
        public boolean test(Type type, Type type2) {
            Type typeUnboxedTypeOrType = Operators.this.types.unboxedTypeOrType(type);
            TypeTag typeTag = TypeTag.BOOLEAN;
            return typeUnboxedTypeOrType.hasTag(typeTag) && Operators.this.types.unboxedTypeOrType(type2).hasTag(typeTag);
        }
    }

    public class BinaryEqualityOperator extends BinaryOperatorHelper {
        public BinaryEqualityOperator(JCTree.Tag tag) {
            super(tag);
        }

        private ComparisonKind getKind(Type type, Type type2) {
            boolean zIsPrimitive = type.isPrimitive();
            boolean zIsPrimitive2 = type2.isPrimitive();
            if (zIsPrimitive && zIsPrimitive2) {
                return ComparisonKind.NUMERIC_OR_BOOLEAN;
            }
            if (zIsPrimitive) {
                return Operators.this.unaryPromotion(type2).isPrimitive() ? ComparisonKind.NUMERIC_OR_BOOLEAN : ComparisonKind.INVALID;
            }
            if (zIsPrimitive2) {
                return Operators.this.unaryPromotion(type).isPrimitive() ? ComparisonKind.NUMERIC_OR_BOOLEAN : ComparisonKind.INVALID;
            }
            return (type.isNullOrReference() && type2.isNullOrReference()) ? ComparisonKind.REFERENCE : ComparisonKind.INVALID;
        }

        @Override // com.sun.tools.javac.comp.Operators.BinaryOperatorHelper
        public Symbol.OperatorSymbol resolve(Type type, Type type2) {
            ComparisonKind kind = getKind(type, type2);
            ComparisonKind comparisonKind = ComparisonKind.NUMERIC_OR_BOOLEAN;
            Operators operators = Operators.this;
            Type typeBinaryPromotion = kind == comparisonKind ? operators.binaryPromotion(type, type2) : operators.syms.objectType;
            return doLookup(typeBinaryPromotion, typeBinaryPromotion);
        }

        @Override // java.util.function.BiPredicate
        public boolean test(Type type, Type type2) {
            return getKind(type, type2) != ComparisonKind.INVALID;
        }
    }

    public abstract class BinaryOperatorHelper extends OperatorHelper implements BiPredicate<Type, Type> {
        public BinaryOperatorHelper(JCTree.Tag tag) {
            super(tag);
        }

        public final BinaryOperatorHelper addBinaryOperator(final OperatorType operatorType, final OperatorType operatorType2, final OperatorType operatorType3, final int... iArr) {
            this.operatorSuppliers = this.operatorSuppliers.prepend(new Supplier() { // from class: com.sun.tools.javac.comp.a2
                @Override // java.util.function.Supplier
                public final Object get() {
                    Operators.BinaryOperatorHelper binaryOperatorHelper = this.b;
                    return Operators.this.makeOperator(binaryOperatorHelper.name, List.of(operatorType, operatorType2), operatorType3, iArr);
                }
            });
            return this;
        }

        public final Symbol.OperatorSymbol doLookup(final Type type, final Type type2) {
            return doLookup(new Predicate() { // from class: com.sun.tools.javac.comp.b2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.isBinaryOperatorApplicable((Symbol.OperatorSymbol) obj, type, type2);
                }
            });
        }

        public boolean isBinaryOperatorApplicable(Symbol.OperatorSymbol operatorSymbol, Type type, Type type2) {
            List<Type> listMo71getParameterTypes = operatorSymbol.type.mo71getParameterTypes();
            return Operators.this.types.isSameType(listMo71getParameterTypes.head, type) && Operators.this.types.isSameType(listMo71getParameterTypes.tail.head, type2);
        }

        public abstract Symbol.OperatorSymbol resolve(Type type, Type type2);
    }

    public class BinaryShiftOperator extends BinaryOperatorHelper {
        public BinaryShiftOperator(JCTree.Tag tag) {
            super(tag);
        }

        @Override // com.sun.tools.javac.comp.Operators.BinaryOperatorHelper
        public Symbol.OperatorSymbol resolve(Type type, Type type2) {
            return doLookup(Operators.this.unaryPromotion(type), Operators.this.unaryPromotion(type2));
        }

        @Override // java.util.function.BiPredicate
        public boolean test(Type type, Type type2) {
            TypeTag tag = Operators.this.unaryPromotion(type).getTag();
            TypeTag tag2 = Operators.this.unaryPromotion(type2).getTag();
            TypeTag typeTag = TypeTag.LONG;
            if (tag == typeTag || tag == TypeTag.INT) {
                return tag2 == typeTag || tag2 == TypeTag.INT;
            }
            return false;
        }
    }

    public class BinaryStringOperator extends BinaryOperatorHelper {
        public BinaryStringOperator(JCTree.Tag tag) {
            super(tag);
        }

        private Type stringPromotion(Type type) {
            if (type.isPrimitive()) {
                return Operators.this.unaryPromotion(type);
            }
            if (type.hasTag(TypeTag.VOID) || type.hasTag(TypeTag.BOT) || Operators.this.types.isSameType(type, Operators.this.syms.stringType)) {
                return type;
            }
            return type.hasTag(TypeTag.TYPEVAR) ? stringPromotion(type.getUpperBound()) : Operators.this.syms.objectType;
        }

        @Override // com.sun.tools.javac.comp.Operators.BinaryOperatorHelper
        public Symbol.OperatorSymbol resolve(Type type, Type type2) {
            return doLookup(stringPromotion(type), stringPromotion(type2));
        }

        @Override // java.util.function.BiPredicate
        public boolean test(Type type, Type type2) {
            boolean z = Operators.this.types.isSameType(type, Operators.this.syms.stringType) || Operators.this.types.isSameType(type2, Operators.this.syms.stringType);
            TypeTag typeTag = TypeTag.VOID;
            return z && !(type.hasTag(typeTag) || type2.hasTag(typeTag));
        }
    }

    public enum ComparisonKind {
        NUMERIC_OR_BOOLEAN,
        REFERENCE,
        INVALID
    }

    public abstract class OperatorHelper {
        final Name name;
        Optional<Symbol.OperatorSymbol[]> alternatives = Optional.empty();
        List<Supplier<Symbol.OperatorSymbol>> operatorSuppliers = List.nil();

        public OperatorHelper(JCTree.Tag tag) {
            this.name = Operators.this.operatorName(tag);
        }

        public static /* synthetic */ Symbol.OperatorSymbol[] c(int i) {
            return new Symbol.OperatorSymbol[i];
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Symbol.OperatorSymbol[] initOperators() {
            Symbol.OperatorSymbol[] operatorSymbolArr = (Symbol.OperatorSymbol[]) this.operatorSuppliers.stream().map(new Function() { // from class: cqa
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return (Symbol.OperatorSymbol) ((Supplier) obj).get();
                }
            }).toArray(new IntFunction() { // from class: com.sun.tools.javac.comp.c2
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return Operators.OperatorHelper.c(i);
                }
            });
            this.alternatives = Optional.of(operatorSymbolArr);
            this.operatorSuppliers = null;
            return operatorSymbolArr;
        }

        public final Symbol.OperatorSymbol doLookup(Predicate<Symbol.OperatorSymbol> predicate) {
            return (Symbol.OperatorSymbol) Stream.of((Object[]) this.alternatives.orElseGet(new Supplier() { // from class: com.sun.tools.javac.comp.d2
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.b.initOperators();
                }
            })).filter(predicate).findFirst().orElse(Operators.this.noOpSymbol);
        }
    }

    public enum OperatorType {
        BYTE(new Function() { // from class: com.sun.tools.javac.comp.e2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Symtab) obj).byteType;
            }
        }),
        SHORT(new Function() { // from class: com.sun.tools.javac.comp.h2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Symtab) obj).shortType;
            }
        }),
        INT(new Function() { // from class: com.sun.tools.javac.comp.i2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Symtab) obj).intType;
            }
        }),
        LONG(new Function() { // from class: com.sun.tools.javac.comp.j2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Symtab) obj).longType;
            }
        }),
        FLOAT(new Function() { // from class: com.sun.tools.javac.comp.k2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Symtab) obj).floatType;
            }
        }),
        DOUBLE(new Function() { // from class: com.sun.tools.javac.comp.l2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Symtab) obj).doubleType;
            }
        }),
        CHAR(new Function() { // from class: com.sun.tools.javac.comp.m2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Symtab) obj).charType;
            }
        }),
        BOOLEAN(new Function() { // from class: com.sun.tools.javac.comp.n2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Symtab) obj).booleanType;
            }
        }),
        OBJECT(new Function() { // from class: com.sun.tools.javac.comp.o2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Symtab) obj).objectType;
            }
        }),
        STRING(new Function() { // from class: com.sun.tools.javac.comp.f2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Symtab) obj).stringType;
            }
        }),
        BOT(new Function() { // from class: com.sun.tools.javac.comp.g2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Symtab) obj).botType;
            }
        });

        final Function<Symtab, Type> asTypeFunc;

        OperatorType(Function function) {
            this.asTypeFunc = function;
        }

        public Type asType(Symtab symtab) {
            return this.asTypeFunc.apply(symtab);
        }
    }

    public class UnaryBooleanOperator extends UnaryOperatorHelper {
        public UnaryBooleanOperator(JCTree.Tag tag) {
            super(tag);
        }

        @Override // com.sun.tools.javac.comp.Operators.UnaryOperatorHelper
        public Symbol.OperatorSymbol resolve(Type type) {
            return doLookup(Operators.this.syms.booleanType);
        }

        @Override // java.util.function.Predicate
        public boolean test(Type type) {
            return Operators.this.types.unboxedTypeOrType(type).hasTag(TypeTag.BOOLEAN);
        }
    }

    public abstract class UnaryOperatorHelper extends OperatorHelper implements Predicate<Type> {
        public UnaryOperatorHelper(JCTree.Tag tag) {
            super(tag);
        }

        public final UnaryOperatorHelper addUnaryOperator(final OperatorType operatorType, final OperatorType operatorType2, final int... iArr) {
            this.operatorSuppliers = this.operatorSuppliers.prepend(new Supplier() { // from class: com.sun.tools.javac.comp.p2
                @Override // java.util.function.Supplier
                public final Object get() {
                    Operators.UnaryOperatorHelper unaryOperatorHelper = this.b;
                    return Operators.this.makeOperator(unaryOperatorHelper.name, List.of(operatorType), operatorType2, iArr);
                }
            });
            return this;
        }

        public final Symbol.OperatorSymbol doLookup(final Type type) {
            return doLookup(new Predicate() { // from class: com.sun.tools.javac.comp.q2
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return this.b.isUnaryOperatorApplicable((Symbol.OperatorSymbol) obj, type);
                }
            });
        }

        public boolean isUnaryOperatorApplicable(Symbol.OperatorSymbol operatorSymbol, Type type) {
            return Operators.this.types.isSameType(operatorSymbol.type.mo71getParameterTypes().head, type);
        }

        public abstract Symbol.OperatorSymbol resolve(Type type);
    }

    public class UnaryPrefixPostfixOperator extends UnaryNumericOperator {
        public UnaryPrefixPostfixOperator(JCTree.Tag tag) {
            super(Operators.this, tag);
        }

        @Override // com.sun.tools.javac.comp.Operators.UnaryNumericOperator, com.sun.tools.javac.comp.Operators.UnaryOperatorHelper
        public Symbol.OperatorSymbol resolve(Type type) {
            return doLookup(Operators.this.types.unboxedTypeOrType(type));
        }
    }

    public Operators(Context context) {
        context.put(operatorsKey, this);
        Symtab symtabInstance = Symtab.instance(context);
        this.syms = symtabInstance;
        Names namesInstance = Names.instance(context);
        this.names = namesInstance;
        this.log = Log.instance(context);
        this.types = Types.instance(context);
        this.noOpSymbol = new Symbol.OperatorSymbol(namesInstance.empty, Type.noType, -1, symtabInstance.noSymbol);
        initOperatorNames();
        initUnaryOperators();
        initBinaryOperators();
    }

    public static /* synthetic */ boolean a(Type type) {
        return type.isErroneous() || type.hasTag(TypeTag.NONE);
    }

    private void initBinaryOperators() {
        Map<Name, List<BinaryOperatorHelper>> map = this.binaryOperators;
        JCTree.Tag tag = JCTree.Tag.PLUS;
        BinaryStringOperator binaryStringOperator = new BinaryStringOperator(tag);
        OperatorType operatorType = OperatorType.STRING;
        OperatorType operatorType2 = OperatorType.OBJECT;
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator = binaryStringOperator.addBinaryOperator(operatorType, operatorType2, operatorType, 256).addBinaryOperator(operatorType2, operatorType, operatorType, 256).addBinaryOperator(operatorType, operatorType, operatorType, 256);
        OperatorType operatorType3 = OperatorType.INT;
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator2 = binaryOperatorHelperAddBinaryOperator.addBinaryOperator(operatorType, operatorType3, operatorType, 256);
        OperatorType operatorType4 = OperatorType.LONG;
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator3 = binaryOperatorHelperAddBinaryOperator2.addBinaryOperator(operatorType, operatorType4, operatorType, 256);
        OperatorType operatorType5 = OperatorType.FLOAT;
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator4 = binaryOperatorHelperAddBinaryOperator3.addBinaryOperator(operatorType, operatorType5, operatorType, 256);
        OperatorType operatorType6 = OperatorType.DOUBLE;
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator5 = binaryOperatorHelperAddBinaryOperator4.addBinaryOperator(operatorType, operatorType6, operatorType, 256);
        OperatorType operatorType7 = OperatorType.BOOLEAN;
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator6 = binaryOperatorHelperAddBinaryOperator5.addBinaryOperator(operatorType, operatorType7, operatorType, 256);
        OperatorType operatorType8 = OperatorType.BOT;
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator7 = binaryOperatorHelperAddBinaryOperator6.addBinaryOperator(operatorType, operatorType8, operatorType, 256).addBinaryOperator(operatorType3, operatorType, operatorType, 256).addBinaryOperator(operatorType4, operatorType, operatorType, 256).addBinaryOperator(operatorType5, operatorType, operatorType, 256).addBinaryOperator(operatorType6, operatorType, operatorType, 256).addBinaryOperator(operatorType7, operatorType, operatorType, 256).addBinaryOperator(operatorType8, operatorType, operatorType, 256);
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator8 = new BinaryNumericOperator(this, tag).addBinaryOperator(operatorType6, operatorType6, operatorType6, 99).addBinaryOperator(operatorType5, operatorType5, operatorType5, 98).addBinaryOperator(operatorType4, operatorType4, operatorType4, 97).addBinaryOperator(operatorType3, operatorType3, operatorType3, 96);
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator9 = new BinaryNumericOperator(this, JCTree.Tag.MINUS).addBinaryOperator(operatorType6, operatorType6, operatorType6, 103).addBinaryOperator(operatorType5, operatorType5, operatorType5, 102).addBinaryOperator(operatorType4, operatorType4, operatorType4, 101).addBinaryOperator(operatorType3, operatorType3, operatorType3, 100);
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator10 = new BinaryNumericOperator(this, JCTree.Tag.MUL).addBinaryOperator(operatorType6, operatorType6, operatorType6, 107).addBinaryOperator(operatorType5, operatorType5, operatorType5, 106).addBinaryOperator(operatorType4, operatorType4, operatorType4, 105).addBinaryOperator(operatorType3, operatorType3, operatorType3, 104);
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator11 = new BinaryNumericOperator(this, JCTree.Tag.DIV).addBinaryOperator(operatorType6, operatorType6, operatorType6, 111).addBinaryOperator(operatorType5, operatorType5, operatorType5, 110).addBinaryOperator(operatorType4, operatorType4, operatorType4, 109).addBinaryOperator(operatorType3, operatorType3, operatorType3, 108);
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator12 = new BinaryNumericOperator(this, JCTree.Tag.MOD).addBinaryOperator(operatorType6, operatorType6, operatorType6, 115).addBinaryOperator(operatorType5, operatorType5, operatorType5, 114).addBinaryOperator(operatorType4, operatorType4, operatorType4, 113).addBinaryOperator(operatorType3, operatorType3, operatorType3, 112);
        JCTree.Tag tag2 = JCTree.Tag.BITAND;
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator13 = new BinaryBooleanOperator(tag2).addBinaryOperator(operatorType7, operatorType7, operatorType7, 126);
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator14 = new BinaryNumericOperator(tag2, new vpa()).addBinaryOperator(operatorType4, operatorType4, operatorType4, 127).addBinaryOperator(operatorType3, operatorType3, operatorType3, 126);
        JCTree.Tag tag3 = JCTree.Tag.BITOR;
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator15 = new BinaryBooleanOperator(tag3).addBinaryOperator(operatorType7, operatorType7, operatorType7, 128);
        BinaryOperatorHelper binaryOperatorHelperAddBinaryOperator16 = new BinaryNumericOperator(tag3, new vpa()).addBinaryOperator(operatorType4, operatorType4, operatorType4, 129).addBinaryOperator(operatorType3, operatorType3, operatorType3, 128);
        JCTree.Tag tag4 = JCTree.Tag.BITXOR;
        initOperators(map, binaryOperatorHelperAddBinaryOperator7, binaryOperatorHelperAddBinaryOperator8, binaryOperatorHelperAddBinaryOperator9, binaryOperatorHelperAddBinaryOperator10, binaryOperatorHelperAddBinaryOperator11, binaryOperatorHelperAddBinaryOperator12, binaryOperatorHelperAddBinaryOperator13, binaryOperatorHelperAddBinaryOperator14, binaryOperatorHelperAddBinaryOperator15, binaryOperatorHelperAddBinaryOperator16, new BinaryBooleanOperator(tag4).addBinaryOperator(operatorType7, operatorType7, operatorType7, 130), new BinaryNumericOperator(tag4, new vpa()).addBinaryOperator(operatorType4, operatorType4, operatorType4, 131).addBinaryOperator(operatorType3, operatorType3, operatorType3, 130), new BinaryShiftOperator(JCTree.Tag.SL).addBinaryOperator(operatorType3, operatorType3, operatorType3, 120).addBinaryOperator(operatorType3, operatorType4, operatorType3, ByteCodes.ishll).addBinaryOperator(operatorType4, operatorType3, operatorType4, 121).addBinaryOperator(operatorType4, operatorType4, operatorType4, ByteCodes.lshll), new BinaryShiftOperator(JCTree.Tag.SR).addBinaryOperator(operatorType3, operatorType3, operatorType3, 122).addBinaryOperator(operatorType3, operatorType4, operatorType3, ByteCodes.ishrl).addBinaryOperator(operatorType4, operatorType3, operatorType4, 123).addBinaryOperator(operatorType4, operatorType4, operatorType4, ByteCodes.lshrl), new BinaryShiftOperator(JCTree.Tag.USR).addBinaryOperator(operatorType3, operatorType3, operatorType3, 124).addBinaryOperator(operatorType3, operatorType4, operatorType3, 274).addBinaryOperator(operatorType4, operatorType3, operatorType4, 125).addBinaryOperator(operatorType4, operatorType4, operatorType4, 275), new BinaryNumericOperator(this, JCTree.Tag.LT).addBinaryOperator(operatorType6, operatorType6, operatorType7, 152, 155).addBinaryOperator(operatorType5, operatorType5, operatorType7, 150, 155).addBinaryOperator(operatorType4, operatorType4, operatorType7, 148, 155).addBinaryOperator(operatorType3, operatorType3, operatorType7, 161), new BinaryNumericOperator(this, JCTree.Tag.GT).addBinaryOperator(operatorType6, operatorType6, operatorType7, 151, 157).addBinaryOperator(operatorType5, operatorType5, operatorType7, 149, 157).addBinaryOperator(operatorType4, operatorType4, operatorType7, 148, 157).addBinaryOperator(operatorType3, operatorType3, operatorType7, 163), new BinaryNumericOperator(this, JCTree.Tag.LE).addBinaryOperator(operatorType6, operatorType6, operatorType7, 152, 158).addBinaryOperator(operatorType5, operatorType5, operatorType7, 150, 158).addBinaryOperator(operatorType4, operatorType4, operatorType7, 148, 158).addBinaryOperator(operatorType3, operatorType3, operatorType7, 164), new BinaryNumericOperator(this, JCTree.Tag.GE).addBinaryOperator(operatorType6, operatorType6, operatorType7, 151, 156).addBinaryOperator(operatorType5, operatorType5, operatorType7, 149, 156).addBinaryOperator(operatorType4, operatorType4, operatorType7, 148, 156).addBinaryOperator(operatorType3, operatorType3, operatorType7, 162), new BinaryEqualityOperator(JCTree.Tag.EQ).addBinaryOperator(operatorType2, operatorType2, operatorType7, 165).addBinaryOperator(operatorType7, operatorType7, operatorType7, 159).addBinaryOperator(operatorType6, operatorType6, operatorType7, 151, 153).addBinaryOperator(operatorType5, operatorType5, operatorType7, 149, 153).addBinaryOperator(operatorType4, operatorType4, operatorType7, 148, 153).addBinaryOperator(operatorType3, operatorType3, operatorType7, 159), new BinaryEqualityOperator(JCTree.Tag.NE).addBinaryOperator(operatorType2, operatorType2, operatorType7, 166).addBinaryOperator(operatorType7, operatorType7, operatorType7, 160).addBinaryOperator(operatorType6, operatorType6, operatorType7, 151, 154).addBinaryOperator(operatorType5, operatorType5, operatorType7, 149, 154).addBinaryOperator(operatorType4, operatorType4, operatorType7, 148, 154).addBinaryOperator(operatorType3, operatorType3, operatorType7, 160), new BinaryBooleanOperator(JCTree.Tag.AND).addBinaryOperator(operatorType7, operatorType7, operatorType7, 258), new BinaryBooleanOperator(JCTree.Tag.OR).addBinaryOperator(operatorType7, operatorType7, operatorType7, 259));
    }

    private void initOperatorNames() {
        setOperatorName(JCTree.Tag.POS, "+");
        setOperatorName(JCTree.Tag.NEG, "-");
        setOperatorName(JCTree.Tag.NOT, "!");
        setOperatorName(JCTree.Tag.COMPL, "~");
        setOperatorName(JCTree.Tag.PREINC, "++");
        setOperatorName(JCTree.Tag.PREDEC, "--");
        setOperatorName(JCTree.Tag.POSTINC, "++");
        setOperatorName(JCTree.Tag.POSTDEC, "--");
        setOperatorName(JCTree.Tag.NULLCHK, "<*nullchk*>");
        setOperatorName(JCTree.Tag.OR, "||");
        setOperatorName(JCTree.Tag.AND, "&&");
        setOperatorName(JCTree.Tag.EQ, "==");
        setOperatorName(JCTree.Tag.NE, "!=");
        setOperatorName(JCTree.Tag.LT, "<");
        setOperatorName(JCTree.Tag.GT, ">");
        setOperatorName(JCTree.Tag.LE, "<=");
        setOperatorName(JCTree.Tag.GE, ">=");
        setOperatorName(JCTree.Tag.BITOR, "|");
        setOperatorName(JCTree.Tag.BITXOR, "^");
        setOperatorName(JCTree.Tag.BITAND, "&");
        setOperatorName(JCTree.Tag.SL, "<<");
        setOperatorName(JCTree.Tag.SR, ">>");
        setOperatorName(JCTree.Tag.USR, ">>>");
        setOperatorName(JCTree.Tag.PLUS, "+");
        setOperatorName(JCTree.Tag.MINUS, this.names.hyphen);
        setOperatorName(JCTree.Tag.MUL, this.names.asterisk);
        setOperatorName(JCTree.Tag.DIV, this.names.slash);
        setOperatorName(JCTree.Tag.MOD, "%");
    }

    @SafeVarargs
    private final <O extends OperatorHelper> void initOperators(Map<Name, List<O>> map, O... oArr) {
        for (O o : oArr) {
            Name name = o.name;
            map.put(name, map.getOrDefault(name, List.nil()).prepend(o));
        }
    }

    private void initUnaryOperators() {
        Map<Name, List<UnaryOperatorHelper>> map = this.unaryOperators;
        UnaryNumericOperator unaryNumericOperator = new UnaryNumericOperator(this, JCTree.Tag.POS);
        OperatorType operatorType = OperatorType.DOUBLE;
        UnaryOperatorHelper unaryOperatorHelperAddUnaryOperator = unaryNumericOperator.addUnaryOperator(operatorType, operatorType, 0);
        OperatorType operatorType2 = OperatorType.FLOAT;
        UnaryOperatorHelper unaryOperatorHelperAddUnaryOperator2 = unaryOperatorHelperAddUnaryOperator.addUnaryOperator(operatorType2, operatorType2, 0);
        OperatorType operatorType3 = OperatorType.LONG;
        UnaryOperatorHelper unaryOperatorHelperAddUnaryOperator3 = unaryOperatorHelperAddUnaryOperator2.addUnaryOperator(operatorType3, operatorType3, 0);
        OperatorType operatorType4 = OperatorType.INT;
        UnaryOperatorHelper unaryOperatorHelperAddUnaryOperator4 = unaryOperatorHelperAddUnaryOperator3.addUnaryOperator(operatorType4, operatorType4, 0);
        UnaryOperatorHelper unaryOperatorHelperAddUnaryOperator5 = new UnaryNumericOperator(this, JCTree.Tag.NEG).addUnaryOperator(operatorType, operatorType, 119).addUnaryOperator(operatorType2, operatorType2, 118).addUnaryOperator(operatorType3, operatorType3, 117).addUnaryOperator(operatorType4, operatorType4, 116);
        UnaryOperatorHelper unaryOperatorHelperAddUnaryOperator6 = new UnaryNumericOperator(JCTree.Tag.COMPL, new vpa()).addUnaryOperator(operatorType3, operatorType3, 131).addUnaryOperator(operatorType4, operatorType4, 130);
        UnaryOperatorHelper unaryOperatorHelperAddUnaryOperator7 = new UnaryPrefixPostfixOperator(JCTree.Tag.POSTINC).addUnaryOperator(operatorType, operatorType, 99).addUnaryOperator(operatorType2, operatorType2, 98).addUnaryOperator(operatorType3, operatorType3, 97).addUnaryOperator(operatorType4, operatorType4, 96);
        OperatorType operatorType5 = OperatorType.CHAR;
        UnaryOperatorHelper unaryOperatorHelperAddUnaryOperator8 = unaryOperatorHelperAddUnaryOperator7.addUnaryOperator(operatorType5, operatorType5, 96);
        OperatorType operatorType6 = OperatorType.SHORT;
        UnaryOperatorHelper unaryOperatorHelperAddUnaryOperator9 = unaryOperatorHelperAddUnaryOperator8.addUnaryOperator(operatorType6, operatorType6, 96);
        OperatorType operatorType7 = OperatorType.BYTE;
        UnaryOperatorHelper unaryOperatorHelperAddUnaryOperator10 = unaryOperatorHelperAddUnaryOperator9.addUnaryOperator(operatorType7, operatorType7, 96);
        UnaryOperatorHelper unaryOperatorHelperAddUnaryOperator11 = new UnaryPrefixPostfixOperator(JCTree.Tag.POSTDEC).addUnaryOperator(operatorType, operatorType, 103).addUnaryOperator(operatorType2, operatorType2, 102).addUnaryOperator(operatorType3, operatorType3, 101).addUnaryOperator(operatorType4, operatorType4, 100).addUnaryOperator(operatorType5, operatorType5, 100).addUnaryOperator(operatorType6, operatorType6, 100).addUnaryOperator(operatorType7, operatorType7, 100);
        UnaryBooleanOperator unaryBooleanOperator = new UnaryBooleanOperator(JCTree.Tag.NOT);
        OperatorType operatorType8 = OperatorType.BOOLEAN;
        UnaryOperatorHelper unaryOperatorHelperAddUnaryOperator12 = unaryBooleanOperator.addUnaryOperator(operatorType8, operatorType8, 257);
        UnaryReferenceOperator unaryReferenceOperator = new UnaryReferenceOperator(JCTree.Tag.NULLCHK);
        OperatorType operatorType9 = OperatorType.OBJECT;
        initOperators(map, unaryOperatorHelperAddUnaryOperator4, unaryOperatorHelperAddUnaryOperator5, unaryOperatorHelperAddUnaryOperator6, unaryOperatorHelperAddUnaryOperator10, unaryOperatorHelperAddUnaryOperator11, unaryOperatorHelperAddUnaryOperator12, unaryReferenceOperator.addUnaryOperator(operatorType9, operatorType9, 276));
    }

    public static Operators instance(Context context) {
        Operators operators = (Operators) context.get(operatorsKey);
        return operators == null ? new Operators(context) : operators;
    }

    public static /* synthetic */ boolean j(Operators operators, Symbol.OperatorSymbol operatorSymbol) {
        return operatorSymbol != operators.noOpSymbol;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Symbol.OperatorSymbol makeOperator(Name name, List<OperatorType> list, OperatorType operatorType, int... iArr) {
        return new Symbol.OperatorSymbol(name, new Type.MethodType((List) list.stream().map(new Function() { // from class: com.sun.tools.javac.comp.x1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Operators.OperatorType) obj).asType(this.b.syms);
            }
        }).collect(List.collector()), operatorType.asType(this.syms), List.nil(), this.syms.methodClass), mergeOpcodes(iArr), this.syms.noSymbol);
    }

    private int mergeOpcodes(int... iArr) {
        int length = iArr.length;
        Assert.check(length == 1 || length == 2);
        return length == 1 ? iArr[0] : (iArr[0] << 9) | iArr[1];
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Symbol.OperatorSymbol reportErrorIfNeeded(JCDiagnostic.DiagnosticPosition diagnosticPosition, JCTree.Tag tag, Type... typeArr) {
        if (Stream.of((Object[]) typeArr).noneMatch(new Predicate() { // from class: ypa
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Operators.a((Type) obj);
            }
        })) {
            Name nameOperatorName = operatorName(tag);
            this.log.error(diagnosticPosition, typeArr.length == 1 ? CompilerProperties.Errors.OperatorCantBeApplied(nameOperatorName, typeArr[0]) : CompilerProperties.Errors.OperatorCantBeApplied1(nameOperatorName, typeArr[0], typeArr[1]));
        }
        return this.noOpSymbol;
    }

    private <O> Symbol.OperatorSymbol resolve(JCTree.Tag tag, Map<Name, List<O>> map, Predicate<O> predicate, Function<O, Symbol.OperatorSymbol> function, Supplier<Symbol.OperatorSymbol> supplier) {
        return (Symbol.OperatorSymbol) map.get(operatorName(tag)).stream().filter(predicate).map(function).findFirst().orElseGet(supplier);
    }

    private void setOperatorName(JCTree.Tag tag, String str) {
        setOperatorName(tag, this.names.fromString(str));
    }

    public Type binaryPromotion(Type type, Type type2) {
        Type typeUnboxedTypeOrType = this.types.unboxedTypeOrType(type);
        Type typeUnboxedTypeOrType2 = this.types.unboxedTypeOrType(type2);
        if (!typeUnboxedTypeOrType.isNumeric() || !typeUnboxedTypeOrType2.isNumeric()) {
            return this.types.isSameType(typeUnboxedTypeOrType, typeUnboxedTypeOrType2) ? typeUnboxedTypeOrType : this.syms.objectType;
        }
        TypeTag typeTag = TypeTag.DOUBLE;
        if (typeUnboxedTypeOrType.hasTag(typeTag) || typeUnboxedTypeOrType2.hasTag(typeTag)) {
            return this.syms.doubleType;
        }
        TypeTag typeTag2 = TypeTag.FLOAT;
        if (typeUnboxedTypeOrType.hasTag(typeTag2) || typeUnboxedTypeOrType2.hasTag(typeTag2)) {
            return this.syms.floatType;
        }
        TypeTag typeTag3 = TypeTag.LONG;
        return (typeUnboxedTypeOrType.hasTag(typeTag3) || typeUnboxedTypeOrType2.hasTag(typeTag3)) ? this.syms.longType : this.syms.intType;
    }

    public Symbol.OperatorSymbol lookupBinaryOp(final Predicate<Symbol.OperatorSymbol> predicate) {
        return (Symbol.OperatorSymbol) this.binaryOperators.values().stream().flatMap(new Function() { // from class: zpa
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((List) obj).stream();
            }
        }).map(new Function() { // from class: com.sun.tools.javac.comp.y1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Operators.BinaryOperatorHelper) obj).doLookup(predicate);
            }
        }).distinct().filter(new Predicate() { // from class: aqa
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Operators.j(this.b, (Symbol.OperatorSymbol) obj);
            }
        }).findFirst().get();
    }

    public Name operatorName(JCTree.Tag tag) {
        return this.opname[tag.operatorIndex()];
    }

    public Symbol.OperatorSymbol resolveBinary(final JCDiagnostic.DiagnosticPosition diagnosticPosition, final JCTree.Tag tag, final Type type, final Type type2) {
        return resolve(tag, this.binaryOperators, new Predicate() { // from class: com.sun.tools.javac.comp.v1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Operators.BinaryOperatorHelper) obj).test(type, type2);
            }
        }, new Function() { // from class: com.sun.tools.javac.comp.w1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Operators.BinaryOperatorHelper) obj).resolve(type, type2);
            }
        }, new Supplier() { // from class: xpa
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.b.reportErrorIfNeeded(diagnosticPosition, tag, type, type2);
            }
        });
    }

    public Symbol.OperatorSymbol resolveUnary(final JCDiagnostic.DiagnosticPosition diagnosticPosition, final JCTree.Tag tag, final Type type) {
        return resolve(tag, this.unaryOperators, new Predicate() { // from class: com.sun.tools.javac.comp.z1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Operators.UnaryOperatorHelper) obj).test(type);
            }
        }, new Function() { // from class: com.sun.tools.javac.comp.u1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Operators.UnaryOperatorHelper) obj).resolve(type);
            }
        }, new Supplier() { // from class: wpa
            @Override // java.util.function.Supplier
            public final Object get() {
                return this.b.reportErrorIfNeeded(diagnosticPosition, tag, type);
            }
        });
    }

    public Type unaryPromotion(Type type) {
        Type typeUnboxedTypeOrType = this.types.unboxedTypeOrType(type);
        int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[typeUnboxedTypeOrType.getTag().ordinal()];
        return (i == 1 || i == 2 || i == 3) ? this.syms.intType : typeUnboxedTypeOrType;
    }

    public class UnaryReferenceOperator extends UnaryOperatorHelper {
        public UnaryReferenceOperator(JCTree.Tag tag) {
            super(tag);
        }

        @Override // com.sun.tools.javac.comp.Operators.UnaryOperatorHelper
        public Symbol.OperatorSymbol resolve(Type type) {
            return doLookup(Operators.this.syms.objectType);
        }

        @Override // java.util.function.Predicate
        public boolean test(Type type) {
            return type.isNullOrReference();
        }
    }

    public class BinaryNumericOperator extends BinaryOperatorHelper {
        Predicate<Type> numericTest;

        public BinaryNumericOperator(Operators operators, JCTree.Tag tag) {
            this(tag, new bqa());
        }

        @Override // com.sun.tools.javac.comp.Operators.BinaryOperatorHelper
        public Symbol.OperatorSymbol resolve(Type type, Type type2) {
            Type typeBinaryPromotion = Operators.this.binaryPromotion(type, type2);
            return doLookup(typeBinaryPromotion, typeBinaryPromotion);
        }

        @Override // java.util.function.BiPredicate
        public boolean test(Type type, Type type2) {
            return this.numericTest.test(Operators.this.unaryPromotion(type)) && this.numericTest.test(Operators.this.unaryPromotion(type2));
        }

        public BinaryNumericOperator(JCTree.Tag tag, Predicate<Type> predicate) {
            super(tag);
            this.numericTest = predicate;
        }
    }

    public class UnaryNumericOperator extends UnaryOperatorHelper {
        Predicate<Type> numericTest;

        public UnaryNumericOperator(Operators operators, JCTree.Tag tag) {
            this(tag, new bqa());
        }

        @Override // com.sun.tools.javac.comp.Operators.UnaryOperatorHelper
        public Symbol.OperatorSymbol resolve(Type type) {
            return doLookup(Operators.this.unaryPromotion(type));
        }

        @Override // java.util.function.Predicate
        public boolean test(Type type) {
            return this.numericTest.test(Operators.this.unaryPromotion(type));
        }

        public UnaryNumericOperator(JCTree.Tag tag, Predicate<Type> predicate) {
            super(tag);
            this.numericTest = predicate;
        }
    }

    private void setOperatorName(JCTree.Tag tag, Name name) {
        this.opname[tag.operatorIndex()] = name;
    }
}
