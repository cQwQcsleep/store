package org.jetbrains.kotlin.fir.extensions.predicate;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u0002*\u0004\b\u0001\u0010\u0003*\u0004\b\u0002\u0010\u00042\u00020\u0005B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J#\u0010\b\u001a\u00028\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00022\u0006\u0010\n\u001a\u00028\u0002H&¢\u0006\u0002\u0010\u000bJ#\u0010\f\u001a\u00028\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\r2\u0006\u0010\n\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u000eJ#\u0010\u000f\u001a\u00028\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00102\u0006\u0010\n\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0011J#\u0010\u0012\u001a\u00028\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00132\u0006\u0010\n\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0014J#\u0010\u0015\u001a\u00028\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\u0006\u0010\n\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u0017J#\u0010\u0018\u001a\u00028\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u00192\u0006\u0010\n\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u001aJ#\u0010\u001b\u001a\u00028\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u001c2\u0006\u0010\n\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010\u001dJ#\u0010\u001e\u001a\u00028\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\u001f2\u0006\u0010\n\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010 J#\u0010!\u001a\u00028\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\"2\u0006\u0010\n\u001a\u00028\u0002H\u0016¢\u0006\u0002\u0010#¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "P", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "R", "D", Argument.Delimiters.none, "<init>", "()V", "visitPredicate", "predicate", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnd", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$And;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$And;Ljava/lang/Object;)Ljava/lang/Object;", "visitOr", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Or;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Or;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnnotated", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Annotated;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Annotated;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnnotatedWith", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AnnotatedWith;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AnnotatedWith;Ljava/lang/Object;)Ljava/lang/Object;", "visitAncestorAnnotatedWith", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AncestorAnnotatedWith;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AncestorAnnotatedWith;Ljava/lang/Object;)Ljava/lang/Object;", "visitParentAnnotatedWith", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$ParentAnnotatedWith;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$ParentAnnotatedWith;Ljava/lang/Object;)Ljava/lang/Object;", "visitHasAnnotatedWith", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$HasAnnotatedWith;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$HasAnnotatedWith;Ljava/lang/Object;)Ljava/lang/Object;", "visitMetaAnnotatedWith", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$MetaAnnotatedWith;", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$MetaAnnotatedWith;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class PredicateVisitor<P extends AbstractPredicate<P>, R, D> {
    public R visitAncestorAnnotatedWith(AbstractPredicate.AncestorAnnotatedWith<P> predicate, D data) {
        predicate.getClass();
        return visitAnnotated(predicate, data);
    }

    public R visitAnd(AbstractPredicate.And<P> predicate, D data) {
        predicate.getClass();
        return visitPredicate(predicate, data);
    }

    public R visitAnnotated(AbstractPredicate.Annotated<P> predicate, D data) {
        predicate.getClass();
        return visitPredicate(predicate, data);
    }

    public R visitAnnotatedWith(AbstractPredicate.AnnotatedWith<P> predicate, D data) {
        predicate.getClass();
        return visitAnnotated(predicate, data);
    }

    public R visitHasAnnotatedWith(AbstractPredicate.HasAnnotatedWith<P> predicate, D data) {
        predicate.getClass();
        return visitAnnotated(predicate, data);
    }

    public R visitMetaAnnotatedWith(AbstractPredicate.MetaAnnotatedWith<P> predicate, D data) {
        predicate.getClass();
        return visitPredicate(predicate, data);
    }

    public R visitOr(AbstractPredicate.Or<P> predicate, D data) {
        predicate.getClass();
        return visitPredicate(predicate, data);
    }

    public R visitParentAnnotatedWith(AbstractPredicate.ParentAnnotatedWith<P> predicate, D data) {
        predicate.getClass();
        return visitAnnotated(predicate, data);
    }

    public abstract R visitPredicate(AbstractPredicate<P> predicate, D data);
}
