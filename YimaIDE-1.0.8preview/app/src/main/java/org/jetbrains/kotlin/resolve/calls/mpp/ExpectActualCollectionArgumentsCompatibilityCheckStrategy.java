package org.jetbrains.kotlin.resolve.calls.mpp;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\f\rB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003JD\u0010\u0004\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\b2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00050\u000bH&\u0082\u0001\u0002\u000e\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualCollectionArgumentsCompatibilityCheckStrategy;", "", "<init>", "()V", "areCompatible", "", "T", "expectArg", "", "actualArg", "elementsEqual", "Lkotlin/Function2;", "Default", "ExpectIsSubsetOfActual", "Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualCollectionArgumentsCompatibilityCheckStrategy$Default;", "Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualCollectionArgumentsCompatibilityCheckStrategy$ExpectIsSubsetOfActual;", "org.jetbrains.kotlin:resolution.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public abstract class ExpectActualCollectionArgumentsCompatibilityCheckStrategy {

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JD\u0010\u0004\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\b2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00050\u000bH\u0016J\u0014\u0010\f\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualCollectionArgumentsCompatibilityCheckStrategy$Default;", "Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualCollectionArgumentsCompatibilityCheckStrategy;", "<init>", "()V", "areCompatible", "", "T", "expectArg", "", "actualArg", "elementsEqual", "Lkotlin/Function2;", "equals", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:resolution.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final /* data */ class Default extends ExpectActualCollectionArgumentsCompatibilityCheckStrategy {
        public static final Default INSTANCE = new Default();

        private Default() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.resolve.calls.mpp.ExpectActualCollectionArgumentsCompatibilityCheckStrategy
        public <T> boolean areCompatible(Collection<? extends T> expectArg, Collection<? extends T> actualArg, Function2<? super T, ? super T, Boolean> elementsEqual) {
            expectArg.getClass();
            actualArg.getClass();
            elementsEqual.getClass();
            if (expectArg.size() != actualArg.size()) {
                return false;
            }
            List<Pair> listZip = CollectionsKt.zip(expectArg, actualArg);
            if ((listZip instanceof Collection) && listZip.isEmpty()) {
                return true;
            }
            for (Pair pair : listZip) {
                if (!((Boolean) elementsEqual.invoke(pair.component1(), pair.component2())).booleanValue()) {
                    return false;
                }
            }
            return true;
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof Default);
        }

        public int hashCode() {
            return -100205080;
        }

        public String toString() {
            return "Default";
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÀ\n\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JD\u0010\u0004\u001a\u00020\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\b2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u0002H\u0006\u0012\u0004\u0012\u00020\u00050\u000bH\u0016J\u0014\u0010\f\u001a\u00020\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u000eHÖ\u0083\u0004J\n\u0010\u000f\u001a\u00020\u0010HÖ\u0081\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualCollectionArgumentsCompatibilityCheckStrategy$ExpectIsSubsetOfActual;", "Lorg/jetbrains/kotlin/resolve/calls/mpp/ExpectActualCollectionArgumentsCompatibilityCheckStrategy;", "<init>", "()V", "areCompatible", "", "T", "expectArg", "", "actualArg", "elementsEqual", "Lkotlin/Function2;", "equals", "other", "", "hashCode", "", "toString", "", "org.jetbrains.kotlin:resolution.common"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final /* data */ class ExpectIsSubsetOfActual extends ExpectActualCollectionArgumentsCompatibilityCheckStrategy {
        public static final ExpectIsSubsetOfActual INSTANCE = new ExpectIsSubsetOfActual();

        private ExpectIsSubsetOfActual() {
            super(null);
        }

        @Override // org.jetbrains.kotlin.resolve.calls.mpp.ExpectActualCollectionArgumentsCompatibilityCheckStrategy
        public <T> boolean areCompatible(Collection<? extends T> expectArg, Collection<? extends T> actualArg, Function2<? super T, ? super T, Boolean> elementsEqual) {
            expectArg.getClass();
            actualArg.getClass();
            elementsEqual.getClass();
            Collection<? extends T> collection = expectArg;
            if (collection.isEmpty()) {
                return true;
            }
            for (T t : collection) {
                Collection<? extends T> collection2 = actualArg;
                if (collection2.isEmpty()) {
                    return false;
                }
                Iterator<T> it = collection2.iterator();
                while (it.hasNext()) {
                    if (((Boolean) elementsEqual.invoke(t, it.next())).booleanValue()) {
                    }
                }
                return false;
            }
            return true;
        }

        public boolean equals(Object other) {
            return this == other || (other instanceof ExpectIsSubsetOfActual);
        }

        public int hashCode() {
            return -1409631677;
        }

        public String toString() {
            return "ExpectIsSubsetOfActual";
        }
    }

    public /* synthetic */ ExpectActualCollectionArgumentsCompatibilityCheckStrategy(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract <T> boolean areCompatible(Collection<? extends T> expectArg, Collection<? extends T> actualArg, Function2<? super T, ? super T, Boolean> elementsEqual);

    private ExpectActualCollectionArgumentsCompatibilityCheckStrategy() {
    }
}
