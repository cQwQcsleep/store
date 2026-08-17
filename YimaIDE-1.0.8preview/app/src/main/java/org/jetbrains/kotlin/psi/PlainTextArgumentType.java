package org.jetbrains.kotlin.psi;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B)\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nR\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/psi/PlainTextArgumentType;", "T", "", "Lorg/jetbrains/kotlin/psi/ArgumentType;", "klass", "Ljava/lang/Class;", "toPlainText", "Lkotlin/Function1;", "", "<init>", "(Ljava/lang/Class;Lkotlin/jvm/functions/Function1;)V", "getToPlainText", "()Lkotlin/jvm/functions/Function1;", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
final class PlainTextArgumentType<T> extends ArgumentType<T> {
    private final Function1<T, String> toPlainText;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PlainTextArgumentType(Class<T> cls, Function1<? super T, String> function1) {
        super(cls);
        cls.getClass();
        function1.getClass();
        this.toPlainText = function1;
    }

    public final Function1<T, String> getToPlainText() {
        return this.toPlainText;
    }
}
