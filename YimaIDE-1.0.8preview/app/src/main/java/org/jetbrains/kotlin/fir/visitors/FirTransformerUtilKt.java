package org.jetbrains.kotlin.fir.visitors;

import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirPureAbstractElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u001a5\u0010\u0000\u001a\u0002H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\u0004\b\u0001\u0010\u0003*\u0002H\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u0006\u001a\u0002H\u0003¢\u0006\u0002\u0010\u0007\u001a;\u0010\b\u001a\u00020\t\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\n2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u0006\u001a\u0002H\u0003¢\u0006\u0002\u0010\u000b\u001a=\u0010\b\u001a\u00020\t\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\f2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00052\u0006\u0010\u0006\u001a\u0002H\u0003¢\u0006\u0004\b\r\u0010\u000b\u001aN\u0010\b\u001a\u00020\t\"\b\b\u0000\u0010\u0001*\u00020\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\n2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00030\u00052\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00110\u000fH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"}, d2 = {"transformSingle", "T", "Lorg/jetbrains/kotlin/fir/FirElement;", "D", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformInplace", Argument.Delimiters.none, Argument.Delimiters.none, "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)V", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "transformInplace-aLnlfrU", "dataProducer", "Lkotlin/Function1;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/visitors/TransformData;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTransformerUtilKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <T extends FirElement, D> void transformInplace(List<T> list, FirTransformer<? super D> firTransformer, Function1<? super Integer, ? extends TransformData<? extends D>> function1) {
        list.getClass();
        firTransformer.getClass();
        function1.getClass();
        ListIterator<T> listIterator = list.listIterator();
        int i = 0;
        while (listIterator.hasNext()) {
            Object next = listIterator.next();
            next.getClass();
            FirPureAbstractElement firPureAbstractElement = (FirPureAbstractElement) next;
            int i2 = i + 1;
            TransformData transformData = (TransformData) function1.invoke(Integer.valueOf(i));
            if (transformData instanceof TransformData.Data) {
                FirElement firElementTransform = firPureAbstractElement.transform(firTransformer, ((TransformData.Data) transformData).getValue());
                if (firElementTransform != firPureAbstractElement) {
                    listIterator.set(firElementTransform);
                }
            } else if (!Intrinsics.areEqual(transformData, TransformData.Nothing.INSTANCE)) {
                bu8.a();
                return;
            }
            i = i2;
        }
    }

    /* JADX INFO: renamed from: transformInplace-aLnlfrU, reason: not valid java name */
    public static final <T extends FirElement, D> void m709transformInplaceaLnlfrU(List<T> list, FirTransformer<? super D> firTransformer, D d) {
        firTransformer.getClass();
        if (list != null) {
            transformInplace(list, firTransformer, d);
        }
    }

    public static final <T extends FirElement, D> T transformSingle(T t, FirTransformer<? super D> firTransformer, D d) {
        t.getClass();
        firTransformer.getClass();
        return (T) ((FirPureAbstractElement) t).transform(firTransformer, d);
    }

    public static final <T extends FirElement, D> void transformInplace(List<T> list, FirTransformer<? super D> firTransformer, D d) {
        list.getClass();
        firTransformer.getClass();
        ListIterator<T> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            Object next = listIterator.next();
            next.getClass();
            FirPureAbstractElement firPureAbstractElement = (FirPureAbstractElement) next;
            FirElement firElementTransform = firPureAbstractElement.transform(firTransformer, d);
            if (firElementTransform != firPureAbstractElement) {
                listIterator.set(firElementTransform);
            }
        }
    }
}
