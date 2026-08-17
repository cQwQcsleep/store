package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.Arrays;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u001a+\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0012\u0010\u0003\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0004\"\u0002H\u0002¢\u0006\u0002\u0010\u0005\u001a\u001d\u0010\n\u001a\u0004\u0018\u0001H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0001¢\u0006\u0002\u0010\u000b\"\u0019\u0010\u0006\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00018F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\b\"\u0019\u0010\t\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00018F¢\u0006\u0006\u001a\u0004\b\t\u0010\b¨\u0006\f"}, d2 = {"stackOf", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;", "T", "values", Argument.Delimiters.none, "([Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;", "isEmpty", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;)Z", "isNotEmpty", "topOrNull", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/Stack;)Ljava/lang/Object;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class StackKt {
    public static final boolean isEmpty(Stack<?> stack) {
        stack.getClass();
        return stack.getSize() == 0;
    }

    public static final boolean isNotEmpty(Stack<?> stack) {
        stack.getClass();
        return stack.getSize() != 0;
    }

    public static final <T> Stack<T> stackOf(T... tArr) {
        tArr.getClass();
        return new StackImpl(Arrays.copyOf(tArr, tArr.length));
    }

    public static final <T> T topOrNull(Stack<T> stack) {
        stack.getClass();
        if (stack.getSize() == 0) {
            return null;
        }
        return stack.top();
    }
}
