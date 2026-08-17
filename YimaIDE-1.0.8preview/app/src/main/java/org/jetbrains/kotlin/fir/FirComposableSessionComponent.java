package org.jetbrains.kotlin.fir;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirComposableSessionComponent;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002:\u0001\u000fJ\u0019\u0010\u0003\u001a\u00028\u00002\u0006\u0010\u0004\u001a\u00028\u0000H\u0017b\u0002\b\u0006¢\u0006\u0002\u0010\u0005J \u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bH'b\u0002\b\u0006R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b8VX\u0096\u0004¢\u0006\f\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "T", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "compose", "other", "(Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;)Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "components", Argument.Delimiters.none, "getComponents$annotations", "()V", "getComponents", "()Ljava/util/List;", "createComposed", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "Composed", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirComposableSessionComponent<T extends FirComposableSessionComponent<T>> extends FirSessionComponent {

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\bf\u0018\u0000*\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0002R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "T", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "components", Argument.Delimiters.none, "getComponents", "()Ljava/util/List;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface Composed<T extends FirComposableSessionComponent<T>> extends FirComposableSessionComponent<T> {
        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        List<T> getComponents();
    }

    static /* synthetic */ void getComponents$annotations() {
    }

    @SessionConfiguration
    default T compose(T other) {
        other.getClass();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.addAll(getComponents());
        listCreateListBuilder.addAll(other.getComponents());
        List<? extends T> listDistinct = CollectionsKt.distinct(CollectionsKt.build(listCreateListBuilder));
        T t = (T) CollectionsKt.singleOrNull(listDistinct);
        if (t != null) {
            return t;
        }
        Composed<T> composedCreateComposed = createComposed(listDistinct);
        composedCreateComposed.getClass();
        return composedCreateComposed;
    }

    @SessionConfiguration
    Composed<T> createComposed(List<? extends T> components);

    default List<T> getComponents() {
        return CollectionsKt.listOf(this);
    }
}
