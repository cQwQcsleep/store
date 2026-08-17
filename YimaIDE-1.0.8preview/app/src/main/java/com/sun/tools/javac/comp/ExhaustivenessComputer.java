package com.sun.tools.javac.comp;

import com.sun.jna.platform.win32.WinError;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.ExhaustivenessComputer;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Pair;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ExhaustivenessComputer {
    protected static final Context.Key<ExhaustivenessComputer> exhaustivenessKey = new Context.Key<>();
    private final Check chk;
    private final Infer infer;
    private final Map<Pair<Type, Type>, Boolean> isSubtypeCache = new HashMap();
    private final Symtab syms;
    private final Types types;

    /* JADX INFO: renamed from: com.sun.tools.javac.comp.ExhaustivenessComputer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;

        static {
            int[] iArr = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr;
            try {
                iArr[TypeTag.CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.TYPEVAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public interface PatternDescription {
        Set<PatternDescription> sourcePatterns();
    }

    public ExhaustivenessComputer(Context context) {
        context.put(exhaustivenessKey, this);
        this.syms = Symtab.instance(context);
        this.types = Types.instance(context);
        this.chk = Check.instance(context);
        this.infer = Infer.instance(context);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Set<Symbol> allPermittedSubTypes(Symbol.TypeSymbol typeSymbol, Predicate<Symbol.ClassSymbol> predicate) {
        HashSet hashSet = new HashSet();
        List listBaseClasses = baseClasses(typeSymbol);
        while (listBaseClasses.nonEmpty()) {
            Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) listBaseClasses.head;
            listBaseClasses = listBaseClasses.tail;
            classSymbol.complete();
            if (classSymbol.isSealed() && classSymbol.isAbstract()) {
                Iterator<Type> it = classSymbol.getPermittedSubclasses().iterator();
                while (it.hasNext()) {
                    Symbol.ClassSymbol classSymbol2 = (Symbol.ClassSymbol) it.next().tsym;
                    if (predicate.test(classSymbol2)) {
                        listBaseClasses = listBaseClasses.prepend(classSymbol2);
                        hashSet.add(classSymbol2);
                    }
                }
            }
        }
        return hashSet;
    }

    public static /* synthetic */ boolean b(final ExhaustivenessComputer exhaustivenessComputer, Set set, final BindingPattern bindingPattern, final Symbol symbol) {
        exhaustivenessComputer.getClass();
        return set.stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.q0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ExhaustivenessComputer.e(this.b, bindingPattern, (Symbol) obj);
            }
        }).filter(new Predicate() { // from class: je4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ExhaustivenessComputer.l(this.b, symbol, (Symbol) obj);
            }
        }).findAny().isPresent();
    }

    private List<Symbol.ClassSymbol> baseClasses(Symbol.TypeSymbol typeSymbol) {
        if (typeSymbol instanceof Symbol.ClassSymbol) {
            return List.of((Symbol.ClassSymbol) typeSymbol);
        }
        if (!(typeSymbol instanceof Symbol.TypeVariableSymbol)) {
            return List.nil();
        }
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Type> it = ((Symbol.TypeVariableSymbol) typeSymbol).getBounds().iterator();
        while (it.hasNext()) {
            listBuffer.appendList(baseClasses(it.next().tsym));
        }
        return listBuffer.toList();
    }

    public static /* synthetic */ Set c(Symbol symbol, Symbol symbol2) {
        final HashSet hashSet = new HashSet();
        symbol.owner.members().getSymbols(new Predicate() { // from class: fe4
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ExhaustivenessComputer.s((Symbol) obj);
            }
        }).forEach(new Consumer() { // from class: ge4
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                hashSet.add((Symbol) obj);
            }
        });
        return hashSet;
    }

    private boolean checkCovered(Type type, Iterable<PatternDescription> iterable) {
        for (Type type2 : components(type)) {
            Iterator<PatternDescription> it = iterable.iterator();
            while (it.hasNext()) {
                if (isBpCovered(type2, it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<Type> components(Type type) {
        int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
        if (i != 1) {
            return i != 2 ? List.of(this.types.erasure(type)) : components(((Type.TypeVar) type).getUpperBound());
        }
        if (type.isCompound()) {
            return type.isIntersection() ? (List) ((Type.IntersectionClassType) type).getComponents().stream().flatMap(new Function() { // from class: ie4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.components((Type) obj).stream();
                }
            }).collect(List.collector()) : List.nil();
        }
        return List.of(this.types.erasure(type));
    }

    public static /* synthetic */ boolean d(PatternDescription patternDescription) {
        return patternDescription instanceof RecordPattern;
    }

    public static /* synthetic */ boolean e(ExhaustivenessComputer exhaustivenessComputer, BindingPattern bindingPattern, Symbol symbol) {
        exhaustivenessComputer.getClass();
        return exhaustivenessComputer.isSubtypeErasure(symbol.type, bindingPattern.type);
    }

    public static /* synthetic */ PatternDescription g(int i, RecordPattern recordPattern) {
        return recordPattern.nested[i];
    }

    public static ExhaustivenessComputer instance(Context context) {
        ExhaustivenessComputer exhaustivenessComputer = (ExhaustivenessComputer) context.get(exhaustivenessKey);
        return exhaustivenessComputer == null ? new ExhaustivenessComputer(context) : exhaustivenessComputer;
    }

    private boolean isBpCovered(Type type, PatternDescription patternDescription) {
        if (patternDescription instanceof BindingPattern) {
            BindingPattern bindingPattern = (BindingPattern) patternDescription;
            Type typeErasure = this.types.erasure(type);
            Type typeErasure2 = this.types.erasure(bindingPattern.type);
            if (typeErasure.isPrimitive()) {
                return this.types.isUnconditionallyExactTypeBased(typeErasure, typeErasure2);
            }
            if (bindingPattern.type.isPrimitive()) {
                Types types = this.types;
                if (types.isUnconditionallyExactTypeBased(types.unboxedType(typeErasure), bindingPattern.type)) {
                    return true;
                }
            }
            if (this.types.isSubtype(typeErasure, typeErasure2)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSubtypeErasure(final Type type, final Type type2) {
        return this.isSubtypeCache.computeIfAbsent(Pair.of(type, type2), new Function() { // from class: he4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ExhaustivenessComputer.t(this.b, type, type2, (Pair) obj);
            }
        }).booleanValue();
    }

    public static /* synthetic */ boolean j(PatternDescription patternDescription) {
        return patternDescription instanceof BindingPattern;
    }

    public static /* synthetic */ boolean l(ExhaustivenessComputer exhaustivenessComputer, Symbol symbol, Symbol symbol2) {
        exhaustivenessComputer.getClass();
        return exhaustivenessComputer.isSubtypeErasure(symbol2.type, symbol.type);
    }

    public static /* synthetic */ Symbol.ClassSymbol m(RecordPattern recordPattern) {
        return (Symbol.ClassSymbol) recordPattern.recordType.tsym;
    }

    private boolean nestedComponentsEquivalent(RecordPattern recordPattern, RecordPattern recordPattern2, int i, boolean z) {
        for (int i2 = 0; i2 < recordPattern.nested.length; i2++) {
            if (i2 != i && !recordPattern.nested[i2].equals(recordPattern2.nested[i2])) {
                if (z) {
                    return false;
                }
                PatternDescription patternDescription = recordPattern2.nested[i2];
                if (patternDescription instanceof BindingPattern) {
                    BindingPattern bindingPattern = (BindingPattern) patternDescription;
                    PatternDescription patternDescription2 = recordPattern.nested[i2];
                    if (!(patternDescription2 instanceof BindingPattern)) {
                        PatternDescription patternDescription3 = recordPattern.nested[i2];
                        if (patternDescription3 instanceof RecordPattern) {
                            RecordPattern recordPattern3 = (RecordPattern) patternDescription3;
                            ArrayList arrayList = new ArrayList(bindingPattern.sourcePatterns());
                            while (!arrayList.isEmpty()) {
                                PatternDescription patternDescription4 = (PatternDescription) arrayList.remove(arrayList.size() - 1);
                                if (!recordPattern3.equals(patternDescription4)) {
                                    arrayList.addAll(patternDescription4.sourcePatterns());
                                }
                            }
                        }
                    } else if (!isSubtypeErasure(((BindingPattern) patternDescription2).type, bindingPattern.type)) {
                        return false;
                    }
                }
                return false;
            }
        }
        return true;
    }

    public static /* synthetic */ RecordPattern p(PatternDescription patternDescription) {
        return (RecordPattern) patternDescription;
    }

    public static /* synthetic */ boolean q(PatternDescription patternDescription) {
        return patternDescription instanceof BindingPattern;
    }

    public static /* synthetic */ boolean r(ExhaustivenessComputer exhaustivenessComputer, Type type, Symbol.ClassSymbol classSymbol) {
        exhaustivenessComputer.getClass();
        Type typeInstantiatePatternType = classSymbol.type.allparams().isEmpty() ? classSymbol.type : exhaustivenessComputer.infer.instantiatePatternType(type, classSymbol);
        return typeInstantiatePatternType != null && exhaustivenessComputer.types.isCastable(type, typeInstantiatePatternType);
    }

    private Set<PatternDescription> reduceBindingPatterns(final Type type, Set<PatternDescription> set) {
        Set set2 = (Set) set.stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.m0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ExhaustivenessComputer.q((ExhaustivenessComputer.PatternDescription) obj);
            }
        }).map(new Function() { // from class: com.sun.tools.javac.comp.n0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ExhaustivenessComputer.BindingPattern) ((ExhaustivenessComputer.PatternDescription) obj)).type.tsym;
            }
        }).collect(Collectors.toSet());
        for (PatternDescription patternDescription : set) {
            if (patternDescription instanceof BindingPattern) {
                HashSet hashSet = new HashSet();
                Iterator<Type> it = this.types.directSupertypes(((BindingPattern) patternDescription).type).iterator();
                while (it.hasNext()) {
                    Symbol.ClassSymbol classSymbol = (Symbol.ClassSymbol) this.types.erasure(it.next()).tsym;
                    classSymbol.complete();
                    if (classSymbol.isSealed() && classSymbol.isAbstract() && !set2.contains(classSymbol)) {
                        final Type type2 = classSymbol.type;
                        if (!components(type).stream().noneMatch(new Predicate() { // from class: ce4
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj) {
                                return this.b.isSubtypeErasure(type2, (Type) obj);
                            }
                        })) {
                            final Set<Symbol> setAllPermittedSubTypes = allPermittedSubTypes(classSymbol, new Predicate() { // from class: de4
                                @Override // java.util.function.Predicate
                                public final boolean test(Object obj) {
                                    return ExhaustivenessComputer.r(this.b, type, (Symbol.ClassSymbol) obj);
                                }
                            });
                            HashSet hashSet2 = new HashSet(setAllPermittedSubTypes);
                            for (PatternDescription patternDescription2 : set) {
                                if (patternDescription2 instanceof BindingPattern) {
                                    final BindingPattern bindingPattern = (BindingPattern) patternDescription2;
                                    hashSet2.removeIf(new Predicate() { // from class: com.sun.tools.javac.comp.o0
                                        @Override // java.util.function.Predicate
                                        public final boolean test(Object obj) {
                                            return ExhaustivenessComputer.u(this.b, bindingPattern, (Symbol) obj);
                                        }
                                    });
                                    if (bindingPattern.type.tsym.isAbstract()) {
                                        hashSet2.removeIf(new Predicate() { // from class: com.sun.tools.javac.comp.p0
                                            @Override // java.util.function.Predicate
                                            public final boolean test(Object obj) {
                                                return ExhaustivenessComputer.b(this.b, setAllPermittedSubTypes, bindingPattern, (Symbol) obj);
                                            }
                                        });
                                    }
                                }
                            }
                            if (hashSet2.isEmpty()) {
                                hashSet.add(new BindingPattern(classSymbol.type));
                            }
                        }
                    }
                }
                if (!hashSet.isEmpty()) {
                    HashSet hashSet3 = new HashSet(set);
                    hashSet3.addAll(hashSet);
                    return hashSet3;
                }
            }
        }
        return set;
    }

    private Set<PatternDescription> reduceNestedPatterns(Set<PatternDescription> set, final boolean z) {
        Iterator it;
        Iterator it2 = ((Map) set.stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.r0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ExhaustivenessComputer.d((ExhaustivenessComputer.PatternDescription) obj);
            }
        }).map(new Function() { // from class: com.sun.tools.javac.comp.s0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ExhaustivenessComputer.p((ExhaustivenessComputer.PatternDescription) obj);
            }
        }).collect(Collectors.groupingBy(new Function() { // from class: com.sun.tools.javac.comp.t0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ExhaustivenessComputer.m((ExhaustivenessComputer.RecordPattern) obj);
            }
        }))).entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry entry = (Map.Entry) it2.next();
            final int size = ((Symbol.ClassSymbol) entry.getKey()).getRecordComponents().size();
            HashSet hashSet = new HashSet((Collection) entry.getValue());
            int i = 0;
            final int i2 = 0;
            while (i2 < size) {
                Iterator it3 = ((Map) hashSet.stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.u0
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return ExhaustivenessComputer.v(size, (ExhaustivenessComputer.RecordPattern) obj);
                    }
                }).collect(Collectors.groupingBy(new Function() { // from class: com.sun.tools.javac.comp.v0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return Integer.valueOf(z ? ((ExhaustivenessComputer.RecordPattern) obj).hashCode(i2) : 0);
                    }
                }))).values().iterator();
                while (it3.hasNext()) {
                    RecordPattern[] recordPatternArr = (RecordPattern[]) ((java.util.List) it3.next()).toArray(new RecordPattern[i]);
                    int i3 = i;
                    while (i3 < recordPatternArr.length) {
                        RecordPattern recordPattern = recordPatternArr[i3];
                        ListBuffer listBuffer = new ListBuffer();
                        listBuffer.append(recordPattern);
                        int i4 = i;
                        while (i4 < recordPatternArr.length) {
                            if (i3 == i4) {
                                it = it2;
                            } else {
                                RecordPattern recordPattern2 = recordPatternArr[i4];
                                it = it2;
                                if (recordPattern.recordType.tsym == recordPattern2.recordType.tsym && this.nestedComponentsEquivalent(recordPattern, recordPattern2, i2, z)) {
                                    listBuffer.append(recordPattern2);
                                }
                            }
                            i4++;
                            it2 = it;
                        }
                        Iterator it4 = it2;
                        Set<PatternDescription> set2 = (Set) listBuffer.stream().map(new Function() { // from class: com.sun.tools.javac.comp.j0
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return ExhaustivenessComputer.g(i2, (ExhaustivenessComputer.RecordPattern) obj);
                            }
                        }).collect(Collectors.toSet());
                        Set<PatternDescription> setReduceBindingPatterns = this.reduceBindingPatterns(recordPattern.fullComponentTypes()[i2], this.removeCoveredRecordPatterns(this.reduceRecordPatterns(this.reduceNestedPatterns(set2, z))));
                        if (!set2.equals(setReduceBindingPatterns)) {
                            if (z) {
                                hashSet.removeAll(listBuffer);
                            }
                            for (PatternDescription patternDescription : setReduceBindingPatterns) {
                                PatternDescription[] patternDescriptionArr = (PatternDescription[]) Arrays.copyOf(recordPattern.nested, recordPattern.nested.length);
                                patternDescriptionArr[i2] = patternDescription;
                                hashSet.add(new RecordPattern(recordPattern.recordType(), recordPattern.fullComponentTypes(), patternDescriptionArr, new HashSet(listBuffer)));
                            }
                        }
                        i3++;
                        this = this;
                        z = z;
                        it2 = it4;
                        i = 0;
                    }
                    this = this;
                    z = z;
                }
                i2++;
                this = this;
                z = z;
                i = 0;
            }
            Iterator it5 = it2;
            if (!hashSet.equals(new HashSet((Collection) entry.getValue()))) {
                HashSet hashSet2 = new HashSet(set);
                hashSet2.removeAll((Collection) entry.getValue());
                hashSet2.addAll(hashSet);
                return hashSet2;
            }
            it2 = it5;
        }
        return set;
    }

    private PatternDescription reduceRecordPattern(PatternDescription patternDescription) {
        if (patternDescription instanceof RecordPattern) {
            RecordPattern recordPattern = (RecordPattern) patternDescription;
            Type[] typeArrFullComponentTypes = recordPattern.fullComponentTypes();
            if (typeArrFullComponentTypes.length != recordPattern.nested.length) {
                return patternDescription;
            }
            PatternDescription[] patternDescriptionArr = null;
            boolean zCheckCovered = true;
            for (int i = 0; i < typeArrFullComponentTypes.length; i++) {
                PatternDescription patternDescriptionReduceRecordPattern = reduceRecordPattern(recordPattern.nested[i]);
                if (patternDescriptionReduceRecordPattern != recordPattern.nested[i]) {
                    if (patternDescriptionArr == null) {
                        patternDescriptionArr = (PatternDescription[]) Arrays.copyOf(recordPattern.nested, recordPattern.nested.length);
                    }
                    patternDescriptionArr[i] = patternDescriptionReduceRecordPattern;
                }
                zCheckCovered &= checkCovered(typeArrFullComponentTypes[i], List.of(patternDescriptionReduceRecordPattern));
            }
            if (zCheckCovered) {
                return new BindingPattern(recordPattern.recordType, Collections.unmodifiableSet(new HashSet(Arrays.asList(patternDescription))));
            }
            if (patternDescriptionArr != null) {
                return new RecordPattern(recordPattern.recordType, recordPattern.fullComponentTypes(), patternDescriptionArr, Collections.unmodifiableSet(new HashSet(Arrays.asList(patternDescription))));
            }
        }
        return patternDescription;
    }

    private Set<PatternDescription> reduceRecordPatterns(Set<PatternDescription> set) {
        RecordPattern recordPattern;
        PatternDescription patternDescriptionReduceRecordPattern;
        HashSet hashSet = new HashSet();
        boolean z = false;
        for (PatternDescription patternDescription : set) {
            if (!(patternDescription instanceof RecordPattern) || (patternDescriptionReduceRecordPattern = reduceRecordPattern((recordPattern = (RecordPattern) patternDescription))) == recordPattern) {
                hashSet.add(patternDescription);
            } else {
                hashSet.add(patternDescriptionReduceRecordPattern);
                z = true;
            }
        }
        return z ? hashSet : set;
    }

    private Set<PatternDescription> removeCoveredRecordPatterns(Set<PatternDescription> set) {
        Set set2 = (Set) set.stream().filter(new Predicate() { // from class: com.sun.tools.javac.comp.k0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ExhaustivenessComputer.j((ExhaustivenessComputer.PatternDescription) obj);
            }
        }).map(new Function() { // from class: com.sun.tools.javac.comp.l0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((ExhaustivenessComputer.BindingPattern) ((ExhaustivenessComputer.PatternDescription) obj)).type.tsym;
            }
        }).collect(Collectors.toSet());
        HashSet hashSet = new HashSet(set);
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            PatternDescription patternDescription = (PatternDescription) it.next();
            if ((patternDescription instanceof RecordPattern) && set2.contains(((RecordPattern) patternDescription).recordType.tsym)) {
                it.remove();
            }
        }
        return hashSet;
    }

    public static /* synthetic */ boolean s(Symbol symbol) {
        return symbol.kind == Kinds.Kind.VAR && symbol.isEnum();
    }

    public static /* synthetic */ Boolean t(ExhaustivenessComputer exhaustivenessComputer, Type type, Type type2, Pair pair) {
        Types types = exhaustivenessComputer.types;
        return Boolean.valueOf(types.isSubtype(types.erasure(type), exhaustivenessComputer.types.erasure(type2)));
    }

    public static /* synthetic */ boolean u(ExhaustivenessComputer exhaustivenessComputer, BindingPattern bindingPattern, Symbol symbol) {
        exhaustivenessComputer.getClass();
        return exhaustivenessComputer.isSubtypeErasure(symbol.type, bindingPattern.type);
    }

    public static /* synthetic */ boolean v(int i, RecordPattern recordPattern) {
        return recordPattern.nested.length == i;
    }

    public boolean exhausts(JCTree.JCExpression jCExpression, List<JCTree.JCCase> list) {
        boolean z;
        Set<PatternDescription> hashSet = new HashSet<>();
        HashMap map = new HashMap();
        HashSet hashSet2 = new HashSet(Collections.unmodifiableSet(new HashSet(Arrays.asList(0, 1))));
        for (JCTree.JCCase jCCase : list) {
            if (TreeInfo.unguardedCase(jCCase)) {
                for (JCTree.JCCaseLabel jCCaseLabel : jCCase.labels) {
                    if (jCCaseLabel instanceof JCTree.JCPatternCaseLabel) {
                        JCTree.JCPatternCaseLabel jCPatternCaseLabel = (JCTree.JCPatternCaseLabel) jCCaseLabel;
                        Iterator<Type> it = components(jCExpression.type).iterator();
                        while (it.hasNext()) {
                            hashSet.add(makePatternDescription(it.next(), jCPatternCaseLabel.pat));
                        }
                    } else if (jCCaseLabel instanceof JCTree.JCConstantCaseLabel) {
                        boolean zHasTag = this.types.unboxedTypeOrType(jCExpression.type).hasTag(TypeTag.BOOLEAN);
                        JCTree.JCExpression jCExpression2 = ((JCTree.JCConstantCaseLabel) jCCaseLabel).expr;
                        if (zHasTag) {
                            hashSet2.remove(((JCTree.JCLiteral) jCExpression2).value);
                        } else {
                            final Symbol symbol = TreeInfo.symbol(jCExpression2);
                            if (symbol != null && symbol.isEnum()) {
                                ((Set) map.computeIfAbsent(symbol.owner, new Function() { // from class: be4
                                    @Override // java.util.function.Function
                                    public final Object apply(Object obj) {
                                        return ExhaustivenessComputer.c(symbol, (Symbol) obj);
                                    }
                                })).remove(symbol);
                            }
                        }
                    }
                }
            }
        }
        if (this.types.unboxedTypeOrType(jCExpression.type).hasTag(TypeTag.BOOLEAN) && hashSet2.isEmpty()) {
            return true;
        }
        for (Map.Entry entry : map.entrySet()) {
            if (((Set) entry.getValue()).isEmpty()) {
                hashSet.add(new BindingPattern(((Symbol) entry.getKey()).type));
            }
        }
        HashSet hashSet3 = new HashSet();
        boolean z2 = true;
        boolean z3 = true;
        while (true) {
            Type type = jCExpression.type;
            if (!z2) {
                boolean zCheckCovered = checkCovered(type, hashSet);
                this.isSubtypeCache.clear();
                return zCheckCovered;
            }
            try {
                try {
                    Set<PatternDescription> setRemoveCoveredRecordPatterns = removeCoveredRecordPatterns(reduceRecordPatterns(reduceNestedPatterns(reduceBindingPatterns(type, hashSet), z3)));
                    boolean zEquals = setRemoveCoveredRecordPatterns.equals(hashSet);
                    boolean z4 = !zEquals;
                    if (checkCovered(jCExpression.type, hashSet)) {
                        this.isSubtypeCache.clear();
                        return true;
                    }
                    if (zEquals) {
                        z = z3 && hashSet3.add(setRemoveCoveredRecordPatterns);
                        z3 = false;
                    } else {
                        z = z4;
                        z3 = true;
                    }
                    z2 = z;
                    hashSet = setRemoveCoveredRecordPatterns;
                } catch (Symbol.CompletionFailure e) {
                    this.chk.completionError(jCExpression.pos(), e);
                }
            } catch (Throwable th) {
                this.isSubtypeCache.clear();
                throw th;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PatternDescription makePatternDescription(Type type, JCTree.JCPattern jCPattern) {
        if (jCPattern instanceof JCTree.JCBindingPattern) {
            JCTree.JCBindingPattern jCBindingPattern = (JCTree.JCBindingPattern) jCPattern;
            if (type.isPrimitive() || !this.types.isSubtype(type, jCBindingPattern.type)) {
                type = jCBindingPattern.type;
            }
            return new BindingPattern(type);
        }
        if (!(jCPattern instanceof JCTree.JCRecordPattern)) {
            if (jCPattern instanceof JCTree.JCAnyPattern) {
                return new BindingPattern(type);
            }
            throw Assert.error();
        }
        final JCTree.JCRecordPattern jCRecordPattern = (JCTree.JCRecordPattern) jCPattern;
        int i = 0;
        Type[] typeArr = !jCRecordPattern.type.isErroneous() ? (Type[]) ((Symbol.ClassSymbol) jCRecordPattern.type.tsym).getRecordComponents().map(new Function() { // from class: ae4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.types.memberType(jCRecordPattern.type, (Symbol.RecordComponent) obj);
            }
        }).toArray(new Type[0]) : (Type[]) jCRecordPattern.nested.map(new Function() { // from class: ee4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.types.createErrorType(((JCTree.JCPattern) obj).type);
            }
        }).toArray(new Type[0]);
        PatternDescription[] patternDescriptionArr = new PatternDescription[jCRecordPattern.nested.size()];
        List list = jCRecordPattern.nested;
        while (list.nonEmpty()) {
            patternDescriptionArr[i] = makePatternDescription(this.types.erasure(i < typeArr.length ? typeArr[i] : this.syms.errType), (JCTree.JCPattern) list.head);
            list = list.tail;
            i++;
        }
        return new RecordPattern(jCRecordPattern.type, typeArr, patternDescriptionArr);
    }

    public static final class BindingPattern implements PatternDescription {
        private final Set<PatternDescription> sourcePatterns;
        private final Type type;

        public BindingPattern(Type type) {
            this(type, Collections.unmodifiableSet(new HashSet(Arrays.asList(new PatternDescription[0]))));
        }

        public boolean equals(Object obj) {
            return (obj instanceof BindingPattern) && this.type.tsym == ((BindingPattern) obj).type.tsym;
        }

        public int hashCode() {
            return this.type.tsym.hashCode();
        }

        @Override // com.sun.tools.javac.comp.ExhaustivenessComputer.PatternDescription
        public Set<PatternDescription> sourcePatterns() {
            return this.sourcePatterns;
        }

        public String toString() {
            return this.type.tsym + " _";
        }

        public Type type() {
            return this.type;
        }

        public BindingPattern(Type type, Set<PatternDescription> set) {
            this.type = type;
            this.sourcePatterns = set;
        }
    }

    public static final class RecordPattern implements PatternDescription {
        private final int _hashCode;
        private final Type[] fullComponentTypes;
        private final PatternDescription[] nested;
        private final Type recordType;
        private final Set<PatternDescription> sourcePatterns;

        public RecordPattern(Type type, Type[] typeArr, PatternDescription[] patternDescriptionArr) {
            this(type, typeArr, patternDescriptionArr, Collections.unmodifiableSet(new HashSet(Arrays.asList(new PatternDescription[0]))));
        }

        public static int hashCode(int i, Type type, PatternDescription... patternDescriptionArr) {
            int iHashCode = WinError.ERROR_NO_SIGNAL_SENT + type.tsym.hashCode();
            for (int i2 = 0; i2 < patternDescriptionArr.length; i2++) {
                if (i2 != i) {
                    iHashCode = (iHashCode * 41) + patternDescriptionArr[i2].hashCode();
                }
            }
            return iHashCode;
        }

        public int _hashCode() {
            return this._hashCode;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof RecordPattern)) {
                return false;
            }
            RecordPattern recordPattern = (RecordPattern) obj;
            return this.recordType.tsym == recordPattern.recordType.tsym && Arrays.equals(this.nested, recordPattern.nested);
        }

        public Type[] fullComponentTypes() {
            return this.fullComponentTypes;
        }

        public PatternDescription[] nested() {
            return this.nested;
        }

        public Type recordType() {
            return this.recordType;
        }

        @Override // com.sun.tools.javac.comp.ExhaustivenessComputer.PatternDescription
        public Set<PatternDescription> sourcePatterns() {
            return this.sourcePatterns;
        }

        public String toString() {
            return this.recordType.tsym + "(" + ((String) Arrays.stream(this.nested).map(new Function() { // from class: com.sun.tools.javac.comp.w0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((ExhaustivenessComputer.PatternDescription) obj).toString();
                }
            }).collect(Collectors.joining(", "))) + ")";
        }

        public RecordPattern(Type type, int i, Type[] typeArr, PatternDescription[] patternDescriptionArr, Set<PatternDescription> set) {
            this.recordType = type;
            this._hashCode = i;
            this.fullComponentTypes = typeArr;
            this.nested = patternDescriptionArr;
            this.sourcePatterns = set;
        }

        public RecordPattern(Type type, Type[] typeArr, PatternDescription[] patternDescriptionArr, Set<PatternDescription> set) {
            this(type, hashCode(-1, type, patternDescriptionArr), typeArr, patternDescriptionArr, set);
        }

        public int hashCode(int i) {
            return hashCode(i, this.recordType, this.nested);
        }

        public int hashCode() {
            return this._hashCode;
        }
    }
}
