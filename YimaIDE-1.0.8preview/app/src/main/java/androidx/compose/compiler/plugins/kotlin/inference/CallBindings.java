package androidx.compose.compiler.plugins.kotlin.inference;

import androidx.compose.compiler.plugins.kotlin.inference.CallBindings;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0000\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\n\u0010\u0013\u001a\u00020\u0014H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00000\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/inference/CallBindings;", "", "target", "Landroidx/compose/compiler/plugins/kotlin/inference/Binding;", "parameters", "", "result", "anyParameters", "", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/inference/Binding;Ljava/util/List;Landroidx/compose/compiler/plugins/kotlin/inference/CallBindings;Z)V", "getTarget", "()Landroidx/compose/compiler/plugins/kotlin/inference/Binding;", "getParameters", "()Ljava/util/List;", "getResult", "()Landroidx/compose/compiler/plugins/kotlin/inference/CallBindings;", "getAnyParameters", "()Z", "toString", "", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class CallBindings {
    private final boolean anyParameters;
    private final List<CallBindings> parameters;
    private final CallBindings result;
    private final Binding target;

    public CallBindings(Binding binding, List<CallBindings> list, CallBindings callBindings, boolean z) {
        binding.getClass();
        list.getClass();
        this.target = binding;
        this.parameters = list;
        this.result = callBindings;
        this.anyParameters = z;
    }

    public static CharSequence a(CallBindings callBindings) {
        callBindings.getClass();
        return callBindings.toString();
    }

    public final boolean getAnyParameters() {
        return this.anyParameters;
    }

    public final List<CallBindings> getParameters() {
        return this.parameters;
    }

    public final CallBindings getResult() {
        return this.result;
    }

    public final Binding getTarget() {
        return this.target;
    }

    public String toString() {
        String str;
        String str2 = "";
        if (this.parameters.isEmpty()) {
            str = "";
        } else {
            str = ", " + CollectionsKt.joinToString$default(this.parameters, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: da1
                public final Object invoke(Object obj) {
                    return CallBindings.a((CallBindings) obj);
                }
            }, 30, (Object) null);
        }
        String str3 = this.anyParameters ? "*" : "";
        CallBindings callBindings = this.result;
        if (callBindings != null) {
            str2 = "-> " + callBindings;
        }
        return "[" + this.target + str3 + str + str2 + ']';
    }

    public /* synthetic */ CallBindings(Binding binding, List list, CallBindings callBindings, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(binding, (i & 2) != 0 ? CollectionsKt.emptyList() : list, callBindings, z);
    }
}
