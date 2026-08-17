package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Infer.GraphSolver;
import com.sun.tools.javac.comp.InferenceContext;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Assert;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.ListBuffer;
import com.sun.tools.javac.util.Warner;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InferenceContext {
    Types.TypeMapping<Void> asTypeVarFun;
    Map<JCTree, Type> captureTypeCache;
    Map<Infer.FreeTypeListener, List<Type>> freeTypeListeners;
    Infer infer;
    List<Type> inferencevars;
    InferenceContext parentIC;
    Types types;
    List<Type> undetvars;

    public InferenceContext(Infer infer, List<Type> list, List<Type> list2) {
        this.freeTypeListeners = new LinkedHashMap();
        this.asTypeVarFun = new Type.StructuralTypeMapping<Void>() { // from class: com.sun.tools.javac.comp.InferenceContext.1
            @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
            public Type visitUndetVar(Type.UndetVar undetVar, Void r2) {
                return undetVar.qtype;
            }
        };
        this.captureTypeCache = new LinkedHashMap();
        this.inferencevars = list;
        this.undetvars = list2;
        this.infer = infer;
        this.types = infer.types;
    }

    public static /* synthetic */ void a(InferenceContext inferenceContext, Type type, ReachabilityVisitor reachabilityVisitor, Warner warner, InferenceContext inferenceContext2) {
        inferenceContext.getClass();
        Type typeAsInstType = inferenceContext2.asInstType(type);
        Iterator<Type> it = reachabilityVisitor.minMap.get(type).iterator();
        while (it.hasNext()) {
            ((Type.UndetVar) inferenceContext.asUndetVar(it.next())).setInst(typeAsInstType);
        }
        inferenceContext.infer.doIncorporation(inferenceContext, warner);
        inferenceContext.notifyChange();
    }

    public static /* synthetic */ boolean b(Type.UndetVar undetVar) {
        return undetVar.getInst() == null;
    }

    public static /* synthetic */ boolean e(Type.UndetVar undetVar) {
        return undetVar.getInst() != null;
    }

    public static /* synthetic */ void f(InferenceContext inferenceContext, List list, Warner warner, InferenceContext inferenceContext2) {
        inferenceContext.solve((List<Type>) list, warner);
        inferenceContext.notifyChange();
    }

    private List<Type> filterVars(Predicate<Type.UndetVar> predicate) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Type> it = this.undetvars.iterator();
        while (it.hasNext()) {
            Type.UndetVar undetVar = (Type.UndetVar) it.next();
            if (predicate.test(undetVar)) {
                listBuffer.append(undetVar.qtype);
            }
        }
        return listBuffer.toList();
    }

    public static /* synthetic */ boolean g(List list, Type type) {
        return !list.contains(type);
    }

    private void solve(Infer.GraphStrategy graphStrategy, Warner warner) {
        Infer infer = this.infer;
        Objects.requireNonNull(infer);
        infer.new GraphSolver(this, warner).solve(graphStrategy);
    }

    public void addFreeTypeListener(List<Type> list, Infer.FreeTypeListener freeTypeListener) {
        this.freeTypeListeners.put(freeTypeListener, freeVarsIn(list));
    }

    public void addVar(Type.TypeVar typeVar) {
        this.undetvars = this.undetvars.prepend(this.infer.fromTypeVarFun.apply((Type) typeVar));
        this.inferencevars = this.inferencevars.prepend(typeVar);
    }

    public Type asInstType(Type type) {
        ListBuffer listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = new ListBuffer();
        listBuffer.addAll(this.inferencevars);
        listBuffer2.addAll(instTypes());
        for (InferenceContext inferenceContext = this.parentIC; inferenceContext != null; inferenceContext = inferenceContext.parentIC) {
            listBuffer.addAll(inferenceContext.inferencevars);
            listBuffer2.addAll(inferenceContext.instTypes());
        }
        return this.types.subst(type, listBuffer.toList(), listBuffer2.toList());
    }

    public List<Type> asInstTypes(List<Type> list) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            listBuffer.append(asInstType(it.next()));
        }
        return listBuffer.toList();
    }

    public final Type asTypeVar(Type type) {
        return this.asTypeVarFun.apply(type);
    }

    public final Type asUndetVar(Type type) {
        return this.types.subst(type, this.inferencevars, this.undetvars);
    }

    public final List<Type> asUndetVars(List<Type> list) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            listBuffer.append(asUndetVar(it.next()));
        }
        return listBuffer.toList();
    }

    public final List<Type> boundedVars() {
        return filterVars(new Predicate() { // from class: pn6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                Type.UndetVar undetVar = (Type.UndetVar) obj;
                return undetVar.getBounds(Type.UndetVar.InferenceBound.UPPER).diff(undetVar.getDeclaredBounds()).appendList(undetVar.getBounds(Type.UndetVar.InferenceBound.EQ, Type.UndetVar.InferenceBound.LOWER)).nonEmpty();
            }
        });
    }

    public Type cachedCapture(JCTree jCTree, Type type, boolean z) {
        Type type2 = this.captureTypeCache.get(jCTree);
        if (type2 != null) {
            return type2;
        }
        Type typeCapture = this.types.capture(type);
        if (typeCapture != type && !z) {
            this.captureTypeCache.put(jCTree, typeCapture);
        }
        return typeCapture;
    }

    public void dupTo(InferenceContext inferenceContext, boolean z) {
        List<Type> list = inferenceContext.inferencevars;
        inferenceContext.inferencevars = list.appendList(this.inferencevars.diff(list));
        List<Type> listSave = z ? save() : this.undetvars;
        List<Type> list2 = inferenceContext.undetvars;
        inferenceContext.undetvars = list2.appendList(listSave.diff(list2));
        Iterator<Type> it = this.inferencevars.iterator();
        while (it.hasNext()) {
            inferenceContext.freeTypeListeners.put(new Infer.FreeTypeListener() { // from class: com.sun.tools.javac.comp.f1
                @Override // com.sun.tools.javac.comp.Infer.FreeTypeListener
                public final void typesInferred(InferenceContext inferenceContext2) {
                    this.a.notifyChange();
                }
            }, List.of(it.next()));
        }
    }

    public final boolean free(List<Type> list) {
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            if (free(it.next())) {
                return true;
            }
        }
        return false;
    }

    public final List<Type> freeVarsIn(List<Type> list) {
        ListBuffer<Type> listBuffer = new ListBuffer();
        Iterator<Type> it = list.iterator();
        while (it.hasNext()) {
            listBuffer.appendList(freeVarsIn(it.next()));
        }
        ListBuffer listBuffer2 = new ListBuffer();
        for (Type type : listBuffer) {
            if (!listBuffer2.contains(type)) {
                listBuffer2.add(type);
            }
        }
        return listBuffer2.toList();
    }

    public List<Type> inferenceVars() {
        return this.inferencevars;
    }

    public List<Type> instTypes() {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Type> it = this.undetvars.iterator();
        while (it.hasNext()) {
            Type.UndetVar undetVar = (Type.UndetVar) it.next();
            listBuffer.append(undetVar.getInst() != null ? undetVar.getInst() : undetVar.qtype);
        }
        return listBuffer.toList();
    }

    public List<Type> instvars() {
        return filterVars(new Predicate() { // from class: mn6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InferenceContext.e((Type.UndetVar) obj);
            }
        });
    }

    public InferenceContext min(List<Type> list, boolean z, final Warner warner) {
        if (list.length() != this.inferencevars.length()) {
            for (Type type : this.inferencevars) {
                if (!type.hasTag(TypeTag.TYPEVAR) || !((Type.TypeVar) type).isCaptured()) {
                }
            }
            final ReachabilityVisitor reachabilityVisitor = new ReachabilityVisitor();
            reachabilityVisitor.scan(list);
            if (reachabilityVisitor.min.size() != this.inferencevars.length()) {
                List<Type> listFrom = List.from(reachabilityVisitor.min);
                final List<Type> listDiff = this.inferencevars.diff(listFrom);
                ListBuffer listBuffer = new ListBuffer();
                Iterator<Type> it = listFrom.iterator();
                while (it.hasNext()) {
                    Type.UndetVar undetVar = (Type.UndetVar) asUndetVar(it.next());
                    Assert.check(undetVar.incorporationActions.isEmpty());
                    Type.UndetVar undetVarDup = undetVar.dup(this.types);
                    for (Type.UndetVar.InferenceBound inferenceBound : Type.UndetVar.InferenceBound.values()) {
                        undetVarDup.setBounds(inferenceBound, (List) undetVar.getBounds(inferenceBound).stream().filter(new Predicate() { // from class: nn6
                            @Override // java.util.function.Predicate
                            public final boolean test(Object obj) {
                                return InferenceContext.g(listDiff, (Type) obj);
                            }
                        }).collect(List.collector()));
                    }
                    listBuffer.add(undetVarDup);
                }
                InferenceContext inferenceContext = new InferenceContext(this.infer, listFrom, listBuffer.toList());
                for (final Type type2 : inferenceContext.inferencevars) {
                    inferenceContext.addFreeTypeListener(List.of(type2), new Infer.FreeTypeListener() { // from class: com.sun.tools.javac.comp.d1
                        @Override // com.sun.tools.javac.comp.Infer.FreeTypeListener
                        public final void typesInferred(InferenceContext inferenceContext2) {
                            InferenceContext.a(this.a, type2, reachabilityVisitor, warner, inferenceContext2);
                        }
                    });
                }
                if (z) {
                    final List<Type> listDiff2 = listDiff.diff(List.from(reachabilityVisitor.equiv));
                    inferenceContext.addFreeTypeListener(listFrom, new Infer.FreeTypeListener() { // from class: com.sun.tools.javac.comp.e1
                        @Override // com.sun.tools.javac.comp.Infer.FreeTypeListener
                        public final void typesInferred(InferenceContext inferenceContext2) {
                            InferenceContext.f(this.a, listDiff2, warner, inferenceContext2);
                        }
                    });
                }
                return inferenceContext;
            }
        }
        return this;
    }

    public void notifyChange(List<Type> list) {
        Infer.InferenceException inferenceException = null;
        for (Map.Entry entry : new LinkedHashMap(this.freeTypeListeners).entrySet()) {
            if (!Type.containsAny((List) entry.getValue(), this.inferencevars.diff(list))) {
                try {
                    ((Infer.FreeTypeListener) entry.getKey()).typesInferred(this);
                    this.freeTypeListeners.remove(entry.getKey());
                } catch (Infer.InferenceException e) {
                    if (inferenceException == null) {
                        inferenceException = e;
                    }
                }
            }
        }
        if (inferenceException != null) {
            throw inferenceException;
        }
    }

    public List<Type> restvars() {
        return filterVars(new Predicate() { // from class: on6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InferenceContext.b((Type.UndetVar) obj);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [com.sun.tools.javac.util.List<com.sun.tools.javac.code.Type>] */
    /* JADX WARN: Type inference failed for: r8v1, types: [com.sun.tools.javac.util.List] */
    /* JADX WARN: Type inference failed for: r8v4, types: [com.sun.tools.javac.util.List<A>] */
    /* JADX WARN: Type inference failed for: r8v5 */
    public void rollback(List<Type> list) {
        Assert.check(list != 0);
        ListBuffer listBuffer = new ListBuffer();
        ListBuffer listBuffer2 = new ListBuffer();
        while (list.nonEmpty() && this.undetvars.nonEmpty()) {
            List<Type> list2 = this.undetvars;
            Type.UndetVar undetVar = (Type.UndetVar) list2.head;
            Type.UndetVar undetVar2 = (Type.UndetVar) list.head;
            if (undetVar.qtype == undetVar2.qtype) {
                undetVar2.dupTo(undetVar, this.types);
                this.undetvars = this.undetvars.tail;
                list = list.tail;
                listBuffer.add(undetVar);
                listBuffer2.add(undetVar.qtype);
            } else {
                this.undetvars = list2.tail;
            }
        }
        this.undetvars = listBuffer.toList();
        this.inferencevars = listBuffer2.toList();
    }

    public List<Type> save() {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Type> it = this.undetvars.iterator();
        while (it.hasNext()) {
            listBuffer.add(((Type.UndetVar) it.next()).dup(this.infer.types));
        }
        return listBuffer.toList();
    }

    public void solveAny(List<Type> list, Warner warner) {
        Infer infer = this.infer;
        Objects.requireNonNull(infer);
        solve(new Infer.BestLeafSolver(infer, list.intersect(restvars())) { // from class: com.sun.tools.javac.comp.InferenceContext.4
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(list);
                Objects.requireNonNull(infer);
            }

            @Override // com.sun.tools.javac.comp.Infer.GraphStrategy
            public boolean done() {
                return InferenceContext.this.instvars().intersect(this.varsToSolve).nonEmpty();
            }
        }, warner);
    }

    public List<Type> solveBasic(List<Type> list, EnumSet<Infer.InferenceStep> enumSet) {
        ListBuffer listBuffer = new ListBuffer();
        Iterator<Type> it = list.intersect(restvars()).iterator();
        while (it.hasNext()) {
            Type.UndetVar undetVar = (Type.UndetVar) asUndetVar(it.next());
            for (Infer.InferenceStep inferenceStep : enumSet) {
                if (inferenceStep.accepts(undetVar, this)) {
                    undetVar.setInst(inferenceStep.solve(undetVar, this));
                    listBuffer.add(undetVar.qtype);
                    break;
                }
            }
        }
        return listBuffer.toList();
    }

    public String toString() {
        String str = "Inference vars: " + this.inferencevars + "\nUndet vars: " + this.undetvars + '\n';
        if (this.parentIC == null) {
            return str;
        }
        return str + "\nParent : " + this.parentIC.toString();
    }

    public List<Type> undetVars() {
        return this.undetvars;
    }

    public Type update(Type type) {
        return type;
    }

    public class ReachabilityVisitor extends Types.UnaryVisitor<Void> {
        Set<Type> equiv = new LinkedHashSet();
        Set<Type> min = new LinkedHashSet();
        Map<Type, Set<Type>> minMap = new LinkedHashMap();

        public ReachabilityVisitor() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v2, types: [java.util.AbstractCollection, java.util.Collection] */
        /* JADX WARN: Type inference failed for: r5v4 */
        /* JADX WARN: Type inference failed for: r5v5 */
        /* JADX WARN: Type inference failed for: r6v2, types: [java.util.AbstractCollection, java.util.Collection] */
        /* JADX WARN: Type inference failed for: r6v5 */
        /* JADX WARN: Type inference failed for: r6v6 */
        public boolean isEquiv(Type.UndetVar undetVar, Type type, Type.UndetVar.InferenceBound inferenceBound) {
            ?? Diff;
            Type.UndetVar undetVar2 = (Type.UndetVar) InferenceContext.this.asUndetVar(type);
            for (Type.UndetVar.InferenceBound inferenceBound2 : Type.UndetVar.InferenceBound.values()) {
                List<Type> bounds = undetVar.getBounds(inferenceBound2);
                if (inferenceBound2 == inferenceBound) {
                    Diff = bounds;
                    Diff = bounds.diff(List.of(type));
                }
                Diff = bounds;
                List<Type> bounds2 = undetVar2.getBounds(inferenceBound2);
                ?? Diff2 = bounds2;
                if (inferenceBound2 == inferenceBound.complement()) {
                    Diff2 = bounds2.diff(List.of(undetVar.qtype));
                }
                if (!Diff.containsAll(Diff2) || !Diff2.containsAll(Diff)) {
                    return false;
                }
            }
            return true;
        }

        public void scan(List<Type> list) {
            list.forEach(new Consumer() { // from class: com.sun.tools.javac.comp.g1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    this.b.visit((Type) obj);
                }
            });
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitArrayType(Type.ArrayType arrayType, Void r2) {
            return visit(arrayType.elemtype);
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitClassType(Type.ClassType classType, Void r2) {
            visit(classType.getEnclosingType());
            Iterator<Type> it = classType.getTypeArguments().iterator();
            while (it.hasNext()) {
                visit(it.next());
            }
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitTypeVar(Type.TypeVar typeVar, Void r3) {
            Type typeAsUndetVar = InferenceContext.this.asUndetVar(typeVar);
            if (typeAsUndetVar.hasTag(TypeTag.UNDETVAR)) {
                visitUndetVar((Type.UndetVar) typeAsUndetVar, (Void) null);
            }
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.SimpleVisitor, com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitUndetVar(Type.UndetVar undetVar, Void r10) {
            if (!this.min.add(undetVar.qtype)) {
                return null;
            }
            Set<Type> orDefault = this.minMap.getOrDefault(undetVar.qtype, new LinkedHashSet(Collections.singleton(undetVar.qtype)));
            for (Type.UndetVar.InferenceBound inferenceBound : Type.UndetVar.InferenceBound.values()) {
                for (Type type : undetVar.getBounds(inferenceBound)) {
                    Type typeAsUndetVar = InferenceContext.this.asUndetVar(type);
                    if (!typeAsUndetVar.hasTag(TypeTag.UNDETVAR)) {
                        visit(typeAsUndetVar);
                    } else if (isEquiv(undetVar, type, inferenceBound)) {
                        orDefault.add(type);
                        this.equiv.add(type);
                    } else {
                        visit(typeAsUndetVar);
                    }
                }
            }
            this.minMap.put(undetVar.qtype, orDefault);
            return null;
        }

        @Override // com.sun.tools.javac.code.Types.DefaultTypeVisitor, com.sun.tools.javac.code.Type.Visitor
        public Void visitWildcardType(Type.WildcardType wildcardType, Void r2) {
            return visit(wildcardType.type);
        }

        @Override // com.sun.tools.javac.code.Type.Visitor
        public Void visitType(Type type, Void r2) {
            return null;
        }
    }

    public void solve(Warner warner) {
        Infer infer = this.infer;
        Objects.requireNonNull(infer);
        solve(new Infer.LeafSolver(infer) { // from class: com.sun.tools.javac.comp.InferenceContext.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                Objects.requireNonNull(infer);
            }

            @Override // com.sun.tools.javac.comp.Infer.GraphStrategy
            public boolean done() {
                return InferenceContext.this.restvars().isEmpty();
            }
        }, warner);
    }

    public void solve(List<Type> list, Warner warner) {
        Infer infer = this.infer;
        Objects.requireNonNull(infer);
        solve(new Infer.BestLeafSolver(this, infer, list, list) { // from class: com.sun.tools.javac.comp.InferenceContext.3
            final /* synthetic */ InferenceContext this$0;
            final /* synthetic */ List val$vars;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(list);
                this.val$vars = list;
                this.this$0 = this;
                Objects.requireNonNull(infer);
            }

            @Override // com.sun.tools.javac.comp.Infer.GraphStrategy
            public boolean done() {
                InferenceContext inferenceContext = this.this$0;
                return !inferenceContext.free(inferenceContext.asInstTypes(this.val$vars));
            }
        }, warner);
    }

    public final boolean free(Type type) {
        return type.containsAny(this.inferencevars);
    }

    public InferenceContext(Infer infer, List<Type> list) {
        this(infer, list, list.map(infer.fromTypeVarFun));
    }

    public final List<Type> freeVarsIn(Type type) {
        ListBuffer listBuffer = new ListBuffer();
        for (Type type2 : inferenceVars()) {
            if (type.contains(type2)) {
                listBuffer.add(type2);
            }
        }
        return listBuffer.toList();
    }

    public void dupTo(InferenceContext inferenceContext) {
        dupTo(inferenceContext, false);
    }

    public void notifyChange() {
        notifyChange(this.inferencevars.diff(restvars()));
    }
}
