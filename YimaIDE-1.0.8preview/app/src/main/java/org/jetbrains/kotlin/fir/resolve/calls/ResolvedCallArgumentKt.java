package org.jetbrains.kotlin.fir.resolve.calls;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u001a6\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0004\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00060\b*4\u0010\u0000\u001a\u0004\b\u0000\u0010\u0001\"\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u00022\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0002¨\u0006\t"}, d2 = {"CallableReferenceMappedArguments", "T", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument;", "map", "R", "block", "Lkotlin/Function1;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ResolvedCallArgumentKt {
    public static final <T, R> ResolvedCallArgument<R> map(ResolvedCallArgument<? extends T> resolvedCallArgument, Function1<? super T, ? extends R> function1) {
        resolvedCallArgument.getClass();
        function1.getClass();
        if (resolvedCallArgument instanceof ResolvedCallArgument.SimpleArgument) {
            return new ResolvedCallArgument.SimpleArgument(function1.invoke(((ResolvedCallArgument.SimpleArgument) resolvedCallArgument).getCallArgument()));
        }
        if (!(resolvedCallArgument instanceof ResolvedCallArgument.VarargArgument)) {
            if (resolvedCallArgument instanceof ResolvedCallArgument.DefaultArgument) {
                return ResolvedCallArgument.DefaultArgument.INSTANCE;
            }
            bu8.a();
            return null;
        }
        List<T> arguments = ((ResolvedCallArgument.VarargArgument) resolvedCallArgument).getArguments();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        Iterator<T> it = arguments.iterator();
        while (it.hasNext()) {
            arrayList.add(function1.invoke(it.next()));
        }
        return new ResolvedCallArgument.VarargArgument(arrayList);
    }
}
