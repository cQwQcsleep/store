package org.jetbrains.kotlin.fir.renderer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.renderer.ConeAttributeRenderer;
import org.jetbrains.kotlin.fir.types.ConeAttribute;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001:\u0003\t\n\u000bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007H&¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/ConeAttributeRenderer;", Argument.Delimiters.none, "<init>", "()V", "render", Argument.Delimiters.none, "attributes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "ToString", "ForReadability", "None", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ConeAttributeRenderer {

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007H\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/ConeAttributeRenderer$ForReadability;", "Lorg/jetbrains/kotlin/fir/renderer/ConeAttributeRenderer;", "<init>", "()V", "render", Argument.Delimiters.none, "attributes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ForReadability extends ConeAttributeRenderer {
        public static final ForReadability INSTANCE = new ForReadability();

        private ForReadability() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final CharSequence render$lambda$2$0(Pair pair) {
            pair.getClass();
            return (String) pair.component2();
        }

        @Override // org.jetbrains.kotlin.fir.renderer.ConeAttributeRenderer
        public String render(Iterable<? extends ConeAttribute<?>> attributes) {
            Pair pairJoinToString$default;
            attributes.getClass();
            ArrayList arrayList = new ArrayList();
            Iterator<? extends ConeAttribute<?>> it = attributes.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ConeAttribute<?> next = it.next();
                String strRenderForReadability = next.renderForReadability();
                pairJoinToString$default = strRenderForReadability != null ? TuplesKt.to(next, strRenderForReadability) : null;
                if (pairJoinToString$default != null) {
                    arrayList.add(pairJoinToString$default);
                }
            }
            List listSortedWith = CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: org.jetbrains.kotlin.fir.renderer.ConeAttributeRenderer$ForReadability$render$$inlined$sortedBy$1
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((ConeAttribute) ((Pair) t).component1()).getKey().getQualifiedName(), ((ConeAttribute) ((Pair) t2).component1()).getKey().getQualifiedName());
                }
            });
            pairJoinToString$default = listSortedWith.isEmpty() ? null : CollectionsKt.joinToString$default(listSortedWith, Argument.Delimiters.space, (CharSequence) null, Argument.Delimiters.space, 0, (CharSequence) null, new Function1() { // from class: mp2
                public final Object invoke(Object obj) {
                    return ConeAttributeRenderer.ForReadability.render$lambda$2$0((Pair) obj);
                }
            }, 26, (Object) null);
            return pairJoinToString$default == null ? Argument.Delimiters.none : pairJoinToString$default;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007H\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/ConeAttributeRenderer$None;", "Lorg/jetbrains/kotlin/fir/renderer/ConeAttributeRenderer;", "<init>", "()V", "render", Argument.Delimiters.none, "attributes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class None extends ConeAttributeRenderer {
        public static final None INSTANCE = new None();

        private None() {
        }

        @Override // org.jetbrains.kotlin.fir.renderer.ConeAttributeRenderer
        public String render(Iterable<? extends ConeAttribute<?>> attributes) {
            attributes.getClass();
            return Argument.Delimiters.none;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007H\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/ConeAttributeRenderer$ToString;", "Lorg/jetbrains/kotlin/fir/renderer/ConeAttributeRenderer;", "<init>", "()V", "render", Argument.Delimiters.none, "attributes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeAttribute;", "org.jetbrains.kotlin:cones"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ToString extends ConeAttributeRenderer {
        public static final ToString INSTANCE = new ToString();

        private ToString() {
        }

        @Override // org.jetbrains.kotlin.fir.renderer.ConeAttributeRenderer
        public String render(Iterable<? extends ConeAttribute<?>> attributes) {
            attributes.getClass();
            return CollectionsKt.joinToString$default(CollectionsKt.sortedWith(attributes, new Comparator() { // from class: org.jetbrains.kotlin.fir.renderer.ConeAttributeRenderer$ToString$render$$inlined$sortedBy$1
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt.compareValues(((ConeAttribute) t).getKey().getQualifiedName(), ((ConeAttribute) t2).getKey().getQualifiedName());
                }
            }), Argument.Delimiters.space, (CharSequence) null, Argument.Delimiters.space, 0, (CharSequence) null, (Function1) null, 58, (Object) null);
        }
    }

    public abstract String render(Iterable<? extends ConeAttribute<?>> attributes);
}
