package org.jetbrains.kotlin.fir.renderer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationDataRegistry;
import org.jetbrains.kotlin.fir.renderer.FirDeclarationRendererWithAttributes;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\u00020\u0005*\u00020\u0006H\u0014J \u0010\u0007\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t0\b*\u00020\u0006H\u0002J\u001a\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\r0\t0\bH\u0014J\f\u0010\u000e\u001a\u00020\n*\u00020\u000bH\u0002¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirDeclarationRendererWithAttributes;", "Lorg/jetbrains/kotlin/fir/renderer/FirDeclarationRenderer;", "<init>", "()V", "renderDeclarationAttributes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getAttributesWithValues", Argument.Delimiters.none, "Lkotlin/Pair;", Argument.Delimiters.none, Argument.Delimiters.none, "attributeTypesToIds", Argument.Delimiters.none, "renderAsDeclarationAttributeValue", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirDeclarationRendererWithAttributes extends FirDeclarationRenderer {
    /* JADX WARN: Illegal instructions before constructor call */
    public FirDeclarationRendererWithAttributes() {
        String str = null;
        super(str, false, 3, str);
    }

    public static CharSequence a(Pair pair) {
        pair.getClass();
        return ((String) pair.component1()) + '=' + ((String) pair.component2());
    }

    private final List<Pair<String, Object>> getAttributesWithValues(FirDeclaration firDeclaration) {
        List<Pair> listSortedWith = CollectionsKt.sortedWith(attributeTypesToIds(), new Comparator() { // from class: org.jetbrains.kotlin.fir.renderer.FirDeclarationRendererWithAttributes$getAttributesWithValues$$inlined$sortedBy$1
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues((String) ((Pair) t).getFirst(), (String) ((Pair) t2).getFirst());
            }
        });
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listSortedWith, 10));
        for (Pair pair : listSortedWith) {
            arrayList.add(TuplesKt.to((String) pair.component1(), firDeclaration.getAttributes().get(((Number) pair.component2()).intValue())));
        }
        return arrayList;
    }

    private final String renderAsDeclarationAttributeValue(Object obj) {
        if (obj instanceof List) {
            Iterable iterable = (Iterable) obj;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            Iterator it = iterable.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                arrayList.add(next != null ? renderAsDeclarationAttributeValue(next) : null);
            }
            return arrayList.toString();
        }
        if (!(obj instanceof Map)) {
            if (obj instanceof FirCallableSymbol) {
                return ((FirCallableSymbol) obj).callableIdAsString();
            }
            if (obj instanceof FirClassLikeSymbol) {
                return ((FirClassLikeSymbol) obj).getClassId().asString();
            }
            if (obj instanceof FirCallableDeclaration) {
                return ((FirCallableDeclaration) obj).getSymbol().callableIdAsString();
            }
            return obj instanceof KtSourceElement ? "KtSourceElement" : obj.toString();
        }
        Map map = (Map) obj;
        ArrayList arrayList2 = new ArrayList(map.size());
        for (Map.Entry entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            arrayList2.add(TuplesKt.to(key != null ? renderAsDeclarationAttributeValue(key) : null, value != null ? renderAsDeclarationAttributeValue(value) : null));
        }
        return MapsKt.toMap(arrayList2).toString();
    }

    public List<Pair<String, Integer>> attributeTypesToIds() {
        Set<Map.Entry> setEntrySet = FirDeclarationDataRegistry.INSTANCE.allValuesThreadUnsafeForRendering().entrySet();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(setEntrySet, 10));
        for (Map.Entry entry : setEntrySet) {
            arrayList.add(TuplesKt.to(StringsKt.substringAfterLast$default((String) entry.getKey(), ".", (String) null, 2, (Object) null), entry.getValue()));
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirDeclarationRenderer
    public void renderDeclarationAttributes(FirDeclaration firDeclaration) {
        firDeclaration.getClass();
        if (firDeclaration.getAttributes().isNotEmpty()) {
            List<Pair<String, Object>> attributesWithValues = getAttributesWithValues(firDeclaration);
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = attributesWithValues.iterator();
            while (it.hasNext()) {
                Pair pair = (Pair) it.next();
                String str = (String) pair.component1();
                Object objComponent2 = pair.component2();
                if (objComponent2 instanceof Lazy) {
                    objComponent2 = ((Lazy) objComponent2).getValue();
                }
                Pair pair2 = objComponent2 == null ? null : TuplesKt.to(str, renderAsDeclarationAttributeValue(objComponent2));
                if (pair2 != null) {
                    arrayList.add(pair2);
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            String strJoinToString$default = CollectionsKt.joinToString$default(arrayList, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: r15
                public final Object invoke(Object obj) {
                    return FirDeclarationRendererWithAttributes.a((Pair) obj);
                }
            }, 31, (Object) null);
            getPrinter().print("[" + strJoinToString$default + "] ");
        }
    }
}
