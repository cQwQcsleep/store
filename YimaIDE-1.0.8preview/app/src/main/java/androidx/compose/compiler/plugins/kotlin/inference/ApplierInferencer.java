package androidx.compose.compiler.plugins.kotlin.inference;

import androidx.compose.compiler.plugins.kotlin.inference.ApplierInferencer;
import androidx.compose.compiler.plugins.kotlin.inference.Binding;
import androidx.compose.compiler.plugins.kotlin.inference.Bindings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0004\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003BE\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u000b¢\u0006\u0004\b\f\u0010\rJ\u001b\u0010\u0014\u001a\u00020\u0015*\u00028\u00012\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0002¢\u0006\u0002\u0010\u0018J$\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u0016\u001a\u00020\u00172\u000e\b\u0002\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0011H\u0002J+\u0010\"\u001a\u00020\u0013*\u00020\u00172\b\u0010#\u001a\u0004\u0018\u00018\u00012\u0006\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010&JC\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00028\u00012,\u0010)\u001a(\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001d\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00028\u0001\u0012\u0006\u0012\u0004\u0018\u00010\u001a0+\u0012\u0004\u0012\u00020,0*H\u0002¢\u0006\u0002\u0010-J\u001b\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u00028\u00012\u0006\u00100\u001a\u00028\u0001¢\u0006\u0002\u00101J)\u00102\u001a\u00020\u00132\u0006\u0010#\u001a\u00028\u00012\u0006\u00103\u001a\u00028\u00012\f\u00104\u001a\b\u0012\u0004\u0012\u00028\u000105¢\u0006\u0002\u00106J\u0013\u00107\u001a\u00020\u001b2\u0006\u0010(\u001a\u00028\u0001¢\u0006\u0002\u00108R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u001e\u001a\u00020\u001f*\u00020\u001d8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010!¨\u00069"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/ApplierInferencer;", "Type", "Node", "", "typeAdapter", "Landroidx/compose/compiler/plugins/kotlin/inference/TypeAdapter;", "nodeAdapter", "Landroidx/compose/compiler/plugins/kotlin/inference/NodeAdapter;", "lazySchemeStorage", "Landroidx/compose/compiler/plugins/kotlin/inference/LazySchemeStorage;", "errorReporter", "Landroidx/compose/compiler/plugins/kotlin/inference/ErrorReporter;", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/inference/TypeAdapter;Landroidx/compose/compiler/plugins/kotlin/inference/NodeAdapter;Landroidx/compose/compiler/plugins/kotlin/inference/LazySchemeStorage;Landroidx/compose/compiler/plugins/kotlin/inference/ErrorReporter;)V", "inProgress", "", "pending", "", "Lkotlin/Function0;", "", "toLazyScheme", "Landroidx/compose/compiler/plugins/kotlin/inference/LazyScheme;", "bindings", "Landroidx/compose/compiler/plugins/kotlin/inference/Bindings;", "(Ljava/lang/Object;Landroidx/compose/compiler/plugins/kotlin/inference/Bindings;)Landroidx/compose/compiler/plugins/kotlin/inference/LazyScheme;", "toCallBindings", "Landroidx/compose/compiler/plugins/kotlin/inference/CallBindings;", "Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "context", "Landroidx/compose/compiler/plugins/kotlin/inference/Binding;", "safeToken", "", "getSafeToken", "(Landroidx/compose/compiler/plugins/kotlin/inference/Binding;)Ljava/lang/String;", "unify", "call", "a", "b", "(Landroidx/compose/compiler/plugins/kotlin/inference/Bindings;Ljava/lang/Object;Landroidx/compose/compiler/plugins/kotlin/inference/CallBindings;Landroidx/compose/compiler/plugins/kotlin/inference/CallBindings;)Z", "restartable", "node", "block", "Lkotlin/Function3;", "Lkotlin/Function1;", "", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Z", "visitVariable", "variable", "initializer", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "visitCall", "target", "arguments", "", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/util/List;)Z", "toFinalScheme", "(Ljava/lang/Object;)Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ApplierInferencer<Type, Node> {
    private final ErrorReporter<Node> errorReporter;
    private final Set<Node> inProgress;
    private final LazySchemeStorage<Node> lazySchemeStorage;
    private final NodeAdapter<Type, Node> nodeAdapter;
    private final List<Function0<Boolean>> pending;
    private final TypeAdapter<Type> typeAdapter;

    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[NodeKind.values().length];
            try {
                iArr[NodeKind.ParameterReference.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[NodeKind.Lambda.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[NodeKind.Variable.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[NodeKind.Expression.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[NodeKind.Function.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.compiler.plugins.kotlin.inference.ApplierInferencer$restartable$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 4, 0}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Node, CallBindings> {
        final /* synthetic */ Bindings $bindings;
        final /* synthetic */ Function3<Bindings, Binding, Function1<? super Node, CallBindings>, Unit> $block;
        final /* synthetic */ Node $node;
        final /* synthetic */ ApplierInferencer<Type, Node> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(ApplierInferencer<Type, Node> applierInferencer, Bindings bindings, Node node, Function3<? super Bindings, ? super Binding, ? super Function1<? super Node, CallBindings>, Unit> function3) {
            super(1, Intrinsics.Kotlin.class, "callBindingsOf", "restartable$callBindingsOf(Landroidx/compose/compiler/plugins/kotlin/inference/ApplierInferencer;Landroidx/compose/compiler/plugins/kotlin/inference/Bindings;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;Ljava/lang/Object;)Landroidx/compose/compiler/plugins/kotlin/inference/CallBindings;", 0);
            this.this$0 = applierInferencer;
            this.$bindings = bindings;
            this.$node = node;
            this.$block = function3;
        }

        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final CallBindings m275invoke(Node node) {
            return ApplierInferencer.restartable$callBindingsOf(this.this$0, this.$bindings, this.$node, this.$block, node);
        }
    }

    public ApplierInferencer(TypeAdapter<Type> typeAdapter, NodeAdapter<Type, Node> nodeAdapter, LazySchemeStorage<Node> lazySchemeStorage, ErrorReporter<Node> errorReporter) {
        typeAdapter.getClass();
        nodeAdapter.getClass();
        lazySchemeStorage.getClass();
        errorReporter.getClass();
        this.typeAdapter = typeAdapter;
        this.nodeAdapter = nodeAdapter;
        this.lazySchemeStorage = lazySchemeStorage;
        this.errorReporter = errorReporter;
        this.inProgress = new LinkedHashSet();
        this.pending = new ArrayList();
    }

    public static Unit b(final ApplierInferencer applierInferencer, final Object obj, Ref.ObjectRef objectRef, final Function3 function3) {
        if (!applierInferencer.inProgress.contains(obj)) {
            ((Function0) objectRef.element).invoke();
            applierInferencer.pending.add(new Function0() { // from class: yd0
                public final Object invoke() {
                    return Boolean.valueOf(this.b.restartable(obj, function3));
                }
            });
        }
        return Unit.INSTANCE;
    }

    public static Unit c() {
        return Unit.INSTANCE;
    }

    public static Unit d(Object obj, Object obj2, ApplierInferencer applierInferencer, Bindings bindings, Binding binding, Function1 function1) {
        CallBindings callBindings;
        bindings.getClass();
        binding.getClass();
        function1.getClass();
        CallBindings callBindings2 = (CallBindings) function1.invoke(obj);
        if (callBindings2 != null && (callBindings = (CallBindings) function1.invoke(obj2)) != null) {
            applierInferencer.unify(bindings, obj2, callBindings, callBindings2);
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    public static Unit e(Object obj, ApplierInferencer applierInferencer, List list, Object obj2, Bindings bindings, Binding binding, Function1 function1) {
        bindings.getClass();
        binding.getClass();
        function1.getClass();
        CallBindings callBindings = (CallBindings) function1.invoke(obj);
        if (callBindings == null) {
            applierInferencer.errorReporter.log(obj2, "Cannot find target");
            return Unit.INSTANCE;
        }
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add((CallBindings) function1.invoke(it.next()));
        }
        if (!arrayList.isEmpty()) {
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                if (((CallBindings) it2.next()) == null) {
                    applierInferencer.errorReporter.log(obj2, "Cannot determine a parameter scheme");
                    return Unit.INSTANCE;
                }
            }
        }
        int i = 0;
        CallBindings callBindings2 = new CallBindings(binding, CollectionsKt.filterNotNull(arrayList), callBindings.getResult() != null ? (CallBindings) function1.invoke(obj2) : null, false);
        applierInferencer.unify(bindings, obj2, callBindings2, callBindings);
        if (callBindings2.getParameters().size() == list.size()) {
            for (Object obj3 : list) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                if (applierInferencer.nodeAdapter.kindOf((Node) obj3) == NodeKind.Lambda) {
                    Binding target = callBindings2.getParameters().get(i).getTarget();
                    if (target.getToken() == null) {
                        bindings.unify(target, binding);
                    }
                }
                i = i2;
            }
        }
        for (Pair pair : CollectionsKt.zip(callBindings2.getParameters(), list)) {
            CallBindings callBindings3 = (CallBindings) pair.component1();
            Object objComponent2 = pair.component2();
            if (applierInferencer.nodeAdapter.kindOf((Node) objComponent2) == NodeKind.Lambda && callBindings3.getTarget().getToken() != null) {
                LazyScheme lazyScheme$default = toLazyScheme$default(applierInferencer, objComponent2, null, 1, null);
                if (lazyScheme$default.getTarget().getToken() == null) {
                    lazyScheme$default.getBindings().unify(lazyScheme$default.getTarget(), callBindings3.getTarget());
                }
            }
        }
        return Unit.INSTANCE;
    }

    private final String getSafeToken(Binding binding) {
        String token = binding.getToken();
        return token == null ? "unbound" : token;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean restartable(Node node, Function3<? super Bindings, ? super Binding, ? super Function1<? super Node, CallBindings>, Unit> block) {
        if (this.inProgress.contains(node)) {
            return false;
        }
        this.inProgress.add(node);
        try {
            LazyScheme lazyScheme$default = toLazyScheme$default(this, this.nodeAdapter.containerOf(node), null, 1, null);
            Bindings bindings = lazyScheme$default.getBindings();
            block.invoke(bindings, lazyScheme$default.getTarget(), new AnonymousClass1(this, bindings, node, block));
            if (!this.pending.isEmpty()) {
                ArrayList arrayList = new ArrayList();
                while (!this.pending.isEmpty()) {
                    List<Function0<Boolean>> list = this.pending;
                    Function0<Boolean> function0Remove = list.remove(CollectionsKt.getLastIndex(list));
                    if (!((Boolean) function0Remove.invoke()).booleanValue()) {
                        arrayList.add(function0Remove);
                    }
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    this.pending.add((Function0) it.next());
                }
            }
            return true;
        } finally {
            this.inProgress.remove(node);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <Node, Type> CallBindings restartable$callBindingsOf(ApplierInferencer<Type, Node> applierInferencer, Bindings bindings, Node node, Function3<? super Bindings, ? super Binding, ? super Function1<? super Node, CallBindings>, Unit> function3, Node node2) {
        int i = WhenMappings.$EnumSwitchMapping$0[((ApplierInferencer) applierInferencer).nodeAdapter.kindOf(node2).ordinal()];
        if (i == 1) {
            Node nodeContainerOf = ((ApplierInferencer) applierInferencer).nodeAdapter.containerOf(node2);
            LazyScheme lazyScheme$default = toLazyScheme$default(applierInferencer, nodeContainerOf, null, 1, null);
            int iSchemeParameterIndexOf = ((ApplierInferencer) applierInferencer).nodeAdapter.schemeParameterIndexOf(node2, nodeContainerOf);
            if (iSchemeParameterIndexOf < 0 || iSchemeParameterIndexOf >= lazyScheme$default.getParameters().size()) {
                return null;
            }
            return lazyScheme$default.getParameters().get(iSchemeParameterIndexOf).toCallBindings();
        }
        if (i == 2 || i == 3 || i == 4) {
            return restartable$observed(bindings, applierInferencer, node, function3, applierInferencer.toLazyScheme(node2, bindings)).toCallBindings();
        }
        if (i == 5) {
            return toCallBindings$default(applierInferencer, restartable$schemeOf(applierInferencer, bindings, node, function3, node2), bindings, null, 2, null);
        }
        bu8.a();
        return null;
    }

    private static final <Type, Node> LazyScheme restartable$observed(Bindings bindings, final ApplierInferencer<Type, Node> applierInferencer, final Node node, final Function3<? super Bindings, ? super Binding, ? super Function1<? super Node, CallBindings>, Unit> function3, LazyScheme lazyScheme) {
        if (!Intrinsics.areEqual(lazyScheme.getBindings(), bindings) && !lazyScheme.getClosed()) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new Function0() { // from class: zd0
                public final Object invoke() {
                    return ApplierInferencer.c();
                }
            };
            objectRef.element = lazyScheme.onChange(new Function0() { // from class: ae0
                public final Object invoke() {
                    return ApplierInferencer.b(this.b, node, objectRef, function3);
                }
            });
        }
        return lazyScheme;
    }

    private static final <Node, Type> Scheme restartable$schemeOf(ApplierInferencer<Type, Node> applierInferencer, Bindings bindings, Node node, Function3<? super Bindings, ? super Binding, ? super Function1<? super Node, CallBindings>, Unit> function3, Node node2) {
        return restartable$observed(bindings, applierInferencer, node, function3, toLazyScheme$default(applierInferencer, node2, null, 1, null)).toScheme();
    }

    private final CallBindings toCallBindings(Scheme scheme, Bindings bindings, List<Binding> list) {
        Binding binding$org_jetbrains_kotlin_kotlin_compose_compiler_plugin = scheme.getTarget().toBinding$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(bindings, list);
        List<Scheme> parameters = scheme.getParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
        Iterator<T> it = parameters.iterator();
        while (it.hasNext()) {
            arrayList.add(toCallBindings((Scheme) it.next(), bindings, list));
        }
        Scheme result = scheme.getResult();
        return new CallBindings(binding$org_jetbrains_kotlin_kotlin_compose_compiler_plugin, arrayList, result != null ? toCallBindings(result, bindings, list) : null, scheme.getAnyParameters());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ CallBindings toCallBindings$default(ApplierInferencer applierInferencer, Scheme scheme, Bindings bindings, List list, int i, Object obj) {
        if ((i & 2) != 0) {
            list = new ArrayList();
        }
        return applierInferencer.toCallBindings(scheme, bindings, list);
    }

    private final LazyScheme toLazyScheme(Node node, Bindings bindings) {
        LazyScheme lazyScheme$lambda$0$declaredSchemeOf;
        LazySchemeStorage<Node> lazySchemeStorage = this.lazySchemeStorage;
        LazyScheme lazyScheme = lazySchemeStorage.getLazyScheme(node);
        if (lazyScheme != null) {
            return lazyScheme;
        }
        Node nodeReferencedContainerOf = this.nodeAdapter.referencedContainerOf(node);
        if (nodeReferencedContainerOf != null) {
            LazySchemeStorage<Node> lazySchemeStorage2 = this.lazySchemeStorage;
            lazyScheme$lambda$0$declaredSchemeOf = lazySchemeStorage2.getLazyScheme(nodeReferencedContainerOf);
            if (lazyScheme$lambda$0$declaredSchemeOf == null) {
                lazyScheme$lambda$0$declaredSchemeOf = toLazyScheme$lambda$0$declaredSchemeOf(this, bindings, nodeReferencedContainerOf);
                lazySchemeStorage2.storeLazyScheme(nodeReferencedContainerOf, lazyScheme$lambda$0$declaredSchemeOf);
            }
        } else {
            lazyScheme$lambda$0$declaredSchemeOf = toLazyScheme$lambda$0$declaredSchemeOf(this, bindings, node);
        }
        lazySchemeStorage.storeLazyScheme(node, lazyScheme$lambda$0$declaredSchemeOf);
        return lazyScheme$lambda$0$declaredSchemeOf;
    }

    public static /* synthetic */ LazyScheme toLazyScheme$default(ApplierInferencer applierInferencer, Object obj, Bindings bindings, int i, Object obj2) {
        if ((i & 1) != 0) {
            bindings = new Bindings();
        }
        return applierInferencer.toLazyScheme(obj, bindings);
    }

    private static final <Node, Type> LazyScheme toLazyScheme$lambda$0$declaredSchemeOf(final ApplierInferencer<Type, Node> applierInferencer, Bindings bindings, Node node) {
        final Type typeTypeOf = ((ApplierInferencer) applierInferencer).nodeAdapter.typeOf(node);
        if (typeTypeOf == null) {
            return LazyScheme.INSTANCE.open();
        }
        final LazyScheme lazyScheme = new LazyScheme(((ApplierInferencer) applierInferencer).typeAdapter.declaredSchemaOf(typeTypeOf), null, bindings, 2, null);
        if (((ApplierInferencer) applierInferencer).typeAdapter.currentInferredSchemeOf(typeTypeOf) != null) {
            lazyScheme.onChange(new Function0() { // from class: xd0
                public final Object invoke() {
                    return ApplierInferencer.toLazyScheme$lambda$0$declaredSchemeOf$0$0(this.b, typeTypeOf, lazyScheme);
                }
            });
        }
        return lazyScheme;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit toLazyScheme$lambda$0$declaredSchemeOf$0$0(ApplierInferencer applierInferencer, Object obj, LazyScheme lazyScheme) {
        Scheme schemeCurrentInferredSchemeOf = applierInferencer.typeAdapter.currentInferredSchemeOf(obj);
        Scheme scheme = lazyScheme.toScheme();
        if (!Intrinsics.areEqual(scheme, schemeCurrentInferredSchemeOf)) {
            applierInferencer.typeAdapter.updatedInferredScheme(obj, scheme);
        }
        return Unit.INSTANCE;
    }

    private final boolean unify(Bindings bindings, Node node, CallBindings callBindings, CallBindings callBindings2) {
        int size;
        if (!bindings.unify(callBindings.getTarget(), callBindings2.getTarget())) {
            if (node != null) {
                this.errorReporter.reportCallError(node, getSafeToken(callBindings.getTarget()), getSafeToken(callBindings2.getTarget()));
            }
            return false;
        }
        if (callBindings.getParameters().size() != callBindings2.getParameters().size()) {
            if (node != null) {
                this.errorReporter.log(node, "Type disagreement " + callBindings + " <=> " + callBindings2);
            }
            size = (callBindings.getParameters().size() > callBindings2.getParameters().size() ? callBindings2.getParameters() : callBindings.getParameters()).size();
        } else {
            size = callBindings.getParameters().size();
        }
        for (int i = 0; i < size; i++) {
            CallBindings callBindings3 = callBindings.getParameters().get(i);
            CallBindings callBindings4 = callBindings2.getParameters().get(i);
            if (!unify(bindings, null, callBindings3, callBindings4) && node != null) {
                String token = callBindings3.getTarget().getToken();
                String token2 = callBindings4.getTarget().getToken();
                if (token == null || token2 == null) {
                    unify(bindings, node, callBindings3, callBindings4);
                } else {
                    ErrorReporter<Node> errorReporter = this.errorReporter;
                    String token3 = callBindings4.getTarget().getToken();
                    token3.getClass();
                    String token4 = callBindings3.getTarget().getToken();
                    token4.getClass();
                    errorReporter.reportParameterError(node, i, token3, token4);
                }
            }
        }
        CallBindings result = callBindings.getResult();
        CallBindings result2 = callBindings2.getResult();
        if (result == null || result2 == null) {
            return true;
        }
        return unify(bindings, null, result, result2);
    }

    public final Scheme toFinalScheme(Node node) {
        return toLazyScheme$default(this, node, null, 1, null).toScheme();
    }

    public final boolean visitCall(final Node call, final Node target, final List<? extends Node> arguments) {
        arguments.getClass();
        return restartable(call, new Function3() { // from class: be0
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ApplierInferencer.e(target, this, arguments, call, (Bindings) obj, (Binding) obj2, (Function1) obj3);
            }
        });
    }

    public final boolean visitVariable(final Node variable, final Node initializer) {
        return restartable(variable, new Function3() { // from class: wd0
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ApplierInferencer.d(initializer, variable, this, (Bindings) obj, (Binding) obj2, (Function1) obj3);
            }
        });
    }
}
