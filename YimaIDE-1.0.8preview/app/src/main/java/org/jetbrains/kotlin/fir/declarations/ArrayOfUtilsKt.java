package org.jetbrains.kotlin.fir.declarations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a\u001e\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00022\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0002\"\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"isArrayOfFunction", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "argumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "arrayOfNames", Argument.Delimiters.none, Argument.Delimiters.none, "isArrayOf", "function", "arguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "org.jetbrains.kotlin:semantics"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ArrayOfUtilsKt {
    private static final Set<String> arrayOfNames;

    static {
        HashSet hashSetHashSetOf = SetsKt.hashSetOf(new String[]{"kotlin/arrayOf"});
        HashSet hashSetHashSetOf2 = SetsKt.hashSetOf(new String[]{"boolean", "byte", "char", "double", "float", "int", "long", "short", "ubyte", "uint", "ulong", "ushort"});
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(hashSetHashSetOf2, 10));
        Iterator it = hashSetHashSetOf2.iterator();
        while (it.hasNext()) {
            arrayList.add("kotlin/" + ((String) it.next()) + "ArrayOf");
        }
        arrayOfNames = SetsKt.plus(hashSetHashSetOf, arrayList);
    }

    private static final boolean isArrayOf(FirNamedFunctionSymbol firNamedFunctionSymbol, List<? extends FirExpression> list) {
        String string = firNamedFunctionSymbol.getCallableId().toString();
        if (Intrinsics.areEqual(string, "kotlin/emptyArray")) {
            return firNamedFunctionSymbol.getValueParameterSymbols().isEmpty() && list.isEmpty();
        }
        return arrayOfNames.contains(string) && firNamedFunctionSymbol.getValueParameterSymbols().size() == 1 && firNamedFunctionSymbol.getValueParameterSymbols().get(0).isVararg() && list.size() <= 1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isArrayOfFunction(FirNamedFunctionSymbol firNamedFunctionSymbol, FirSession firSession, FirArgumentList firArgumentList) {
        ConeKotlinType coneKotlinTypeFullyExpandedType$default;
        firNamedFunctionSymbol.getClass();
        firSession.getClass();
        firArgumentList.getClass();
        ConeKotlinType coneTypeOrNull = FirTypeUtilsKt.getConeTypeOrNull(((FirNamedFunction) firNamedFunctionSymbol.getFir()).getReturnTypeRef());
        return coneTypeOrNull != null && (coneKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(coneTypeOrNull, firSession, (Function1) null, 2, (Object) null)) != null && ConeBuiltinTypeUtilsKt.isArrayType(coneKotlinTypeFullyExpandedType$default) && isArrayOf(firNamedFunctionSymbol, firArgumentList.getArguments()) && firNamedFunctionSymbol.getReceiverParameterSymbol() == null;
    }
}
