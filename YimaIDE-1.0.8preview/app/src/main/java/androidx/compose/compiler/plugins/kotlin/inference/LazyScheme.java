package androidx.compose.compiler.plugins.kotlin.inference;

import androidx.compose.compiler.plugins.kotlin.inference.LazyScheme;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u0000 -2\u00020\u0001:\u0001-B)\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u001d\u001a\u00020\u0003J\u0006\u0010\u001e\u001a\u00020\u001fJ\u001a\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\"0!J\n\u0010$\u001a\u00020%H\u0096\u0080\u0004R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00000\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0013\u0010\u0018\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u001b\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0013R\u0014\u0010&\u001a\u00020%8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0014\u0010)\u001a\u00020%8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010(R\u0014\u0010+\u001a\u00020%8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b,\u0010(¨\u0006."}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/LazyScheme;", "", "scheme", "Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "context", "", "Landroidx/compose/compiler/plugins/kotlin/inference/Binding;", "bindings", "Landroidx/compose/compiler/plugins/kotlin/inference/Bindings;", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;Ljava/util/List;Landroidx/compose/compiler/plugins/kotlin/inference/Bindings;)V", "getBindings", "()Landroidx/compose/compiler/plugins/kotlin/inference/Bindings;", "target", "getTarget", "()Landroidx/compose/compiler/plugins/kotlin/inference/Binding;", "anyParameters", "", "getAnyParameters", "()Z", "parameters", "", "getParameters", "()Ljava/util/List;", "result", "getResult", "()Landroidx/compose/compiler/plugins/kotlin/inference/LazyScheme;", "closed", "getClosed", "toScheme", "toCallBindings", "Landroidx/compose/compiler/plugins/kotlin/inference/CallBindings;", "onChange", "Lkotlin/Function0;", "", "callback", "toString", "", "targetStr", "getTargetStr", "()Ljava/lang/String;", "parametersStr", "getParametersStr", "resultStr", "getResultStr", "Companion", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class LazyScheme {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final boolean anyParameters;
    private final Bindings bindings;
    private final List<LazyScheme> parameters;
    private final LazyScheme result;
    private final Binding target;

    public LazyScheme(Scheme scheme, List<Binding> list, Bindings bindings) {
        scheme.getClass();
        list.getClass();
        bindings.getClass();
        this.bindings = bindings;
        this.target = scheme.getTarget().toBinding$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(bindings, list);
        this.anyParameters = scheme.getAnyParameters();
        List<Scheme> parameters = scheme.getParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
        Iterator<T> it = parameters.iterator();
        while (it.hasNext()) {
            arrayList.add(new LazyScheme((Scheme) it.next(), list, this.bindings));
        }
        this.parameters = arrayList;
        Scheme result = scheme.getResult();
        this.result = result != null ? new LazyScheme(result, list, this.bindings) : null;
    }

    public static Unit a(LazyScheme lazyScheme, Ref.ObjectRef objectRef, Function0 function0) {
        Scheme scheme = lazyScheme.toScheme();
        if (!Intrinsics.areEqual(scheme, objectRef.element)) {
            function0.invoke();
            objectRef.element = scheme;
        }
        return Unit.INSTANCE;
    }

    private final String getParametersStr() {
        if (this.parameters.isEmpty()) {
            return "";
        }
        return ", " + CollectionsKt.joinToString$default(this.parameters, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    private final String getResultStr() {
        LazyScheme lazyScheme = this.result;
        if (lazyScheme == null) {
            return "";
        }
        return ":" + lazyScheme;
    }

    private final String getTargetStr() {
        String token = this.target.getToken();
        return token == null ? String.valueOf(this.target.getValue().getIndex()) : token;
    }

    private static final Item toScheme$itemOf(Map<Value, Integer> map, Binding binding) {
        String token = binding.getToken();
        if (token != null) {
            return new Token(token);
        }
        Integer num = map.get(binding.getValue());
        DefaultConstructorMarker defaultConstructorMarker = null;
        int i = 2;
        boolean z = false;
        return num != null ? new Open(num.intValue(), z, i, defaultConstructorMarker) : new Open(-1, z, i, defaultConstructorMarker);
    }

    private static final void toScheme$mapValues(Map<Value, Integer> map, Ref.IntRef intRef, LazyScheme lazyScheme) {
        Binding binding = lazyScheme.target;
        if (binding.getToken() == null) {
            Value value = binding.getValue();
            Integer num = map.get(value);
            if (num != null && num.intValue() == -1) {
                int i = intRef.element;
                intRef.element = i + 1;
                map.put(value, Integer.valueOf(i));
            } else if (num == null) {
                map.put(value, -1);
            }
        }
        Iterator<T> it = lazyScheme.parameters.iterator();
        while (it.hasNext()) {
            toScheme$mapValues(map, intRef, (LazyScheme) it.next());
        }
        LazyScheme lazyScheme2 = lazyScheme.result;
        if (lazyScheme2 != null) {
            toScheme$mapValues(map, intRef, lazyScheme2);
        }
    }

    private static final Scheme toScheme$schemeOf(Map<Value, Integer> map, LazyScheme lazyScheme) {
        Item scheme$itemOf = toScheme$itemOf(map, lazyScheme.target);
        List<LazyScheme> list = lazyScheme.parameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(toScheme$schemeOf(map, (LazyScheme) it.next()));
        }
        LazyScheme lazyScheme2 = lazyScheme.result;
        return new Scheme(scheme$itemOf, arrayList, lazyScheme2 != null ? toScheme$schemeOf(map, lazyScheme2) : null, lazyScheme.anyParameters);
    }

    public final boolean getAnyParameters() {
        return this.anyParameters;
    }

    public final Bindings getBindings() {
        return this.bindings;
    }

    public final boolean getClosed() {
        if (this.target.getToken() == null) {
            return false;
        }
        LazyScheme lazyScheme = this.result;
        if (lazyScheme != null && !lazyScheme.getClosed()) {
            return false;
        }
        List<LazyScheme> list = this.parameters;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (!((LazyScheme) it.next()).getClosed()) {
                return false;
            }
        }
        return true;
    }

    public final List<LazyScheme> getParameters() {
        return this.parameters;
    }

    public final LazyScheme getResult() {
        return this.result;
    }

    public final Binding getTarget() {
        return this.target;
    }

    public final Function0<Unit> onChange(final Function0<Unit> callback) {
        callback.getClass();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = toScheme();
        return this.bindings.onChange(new Function0() { // from class: kw8
            public final Object invoke() {
                return LazyScheme.a(this.b, objectRef, callback);
            }
        });
    }

    public final CallBindings toCallBindings() {
        Binding binding = this.target;
        List<LazyScheme> list = this.parameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((LazyScheme) it.next()).toCallBindings());
        }
        LazyScheme lazyScheme = this.result;
        return new CallBindings(binding, arrayList, lazyScheme != null ? lazyScheme.toCallBindings() : null, this.anyParameters);
    }

    public final Scheme toScheme() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        toScheme$mapValues(linkedHashMap, new Ref.IntRef(), this);
        return toScheme$schemeOf(linkedHashMap, this);
    }

    public String toString() {
        return "[" + getTargetStr() + getParametersStr() + getResultStr() + ']';
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/LazyScheme$Companion;", "", "<init>", "()V", "open", "Landroidx/compose/compiler/plugins/kotlin/inference/LazyScheme;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LazyScheme open() {
            Open open = new Open(-1, false, 2, null);
            return new LazyScheme(new Scheme(open, null, new Scheme(open, null, null, false, 14, null), false, 10, null), null, null, 6, null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ LazyScheme(Scheme scheme, List list, Bindings bindings, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(scheme, (i & 2) != 0 ? new ArrayList() : list, (i & 4) != 0 ? new Bindings() : bindings);
    }
}
