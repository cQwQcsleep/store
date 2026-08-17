package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J5\u0010\u0006\u001a\u0002H\u0007\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b0\n2\u0006\u0010\u000b\u001a\u0002H\bH\u0016¢\u0006\u0002\u0010\fJ3\u0010\r\u001a\u0002H\u000e\"\b\b\u0000\u0010\u000e*\u00020\u0000\"\u0004\b\u0001\u0010\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\b0\u00102\u0006\u0010\u000b\u001a\u0002H\bH\u0016¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0006\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0013H\u0016J5\u0010\u0014\u001a\u00020\u0012\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\b0\n2\u0006\u0010\u000b\u001a\u0002H\bH&¢\u0006\u0002\u0010\u0015J\u0010\u0010\u0014\u001a\u00020\u00122\u0006\u0010\t\u001a\u00020\u0013H\u0016J)\u0010\u0016\u001a\u00020\u0000\"\u0004\b\u0000\u0010\b2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\b0\u00102\u0006\u0010\u000b\u001a\u0002H\bH&¢\u0006\u0002\u0010\u0011R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0017À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirElement;", Argument.Delimiters.none, "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/visitors/FirVisitorVoid;", "acceptChildren", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirElement {
    /* JADX WARN: Multi-variable type inference failed */
    default <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitElement(this, data);
    }

    <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data);

    default void acceptChildren(FirVisitorVoid visitor) {
        visitor.getClass();
        acceptChildren(visitor, null);
    }

    KtSourceElement getSource();

    /* JADX WARN: Multi-variable type inference failed */
    default <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        E e = (E) transformer.transformElement(this, data);
        e.getClass();
        return e;
    }

    <D> FirElement transformChildren(FirTransformer<? super D> transformer, D data);

    default void accept(FirVisitorVoid visitor) {
        visitor.getClass();
        accept(visitor, null);
    }
}
