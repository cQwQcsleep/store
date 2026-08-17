package org.jetbrains.kotlin.fir.extensions.predicate;

import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u000e\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00002\u00020\u0002:\t\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001aJ;\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0001\u0010\f\"\u0004\b\u0002\u0010\r2\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\r0\u000f2\u0006\u0010\u0010\u001a\u0002H\rH&¢\u0006\u0002\u0010\u0011R\u001c\u0010\u0003\u001a\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\b\u0082\u0001\u0006\u001b\u001c\u001d\u001e\u001f ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006!À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "P", Argument.Delimiters.none, "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "getAnnotations", "()Ljava/util/Set;", "metaAnnotations", "getMetaAnnotations", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Or", "And", "Annotated", "AnnotatedWith", "AncestorAnnotatedWith", "ParentAnnotatedWith", "HasAnnotatedWith", "MetaAnnotatedWith", "BuilderContext", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$And;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Annotated;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$MetaAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Or;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface AbstractPredicate<P extends AbstractPredicate<P>> {

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J;\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0002\u0010\u0005\"\u0004\b\u0003\u0010\u00062\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00060\b2\u0006\u0010\t\u001a\u0002H\u0006H\u0016¢\u0006\u0002\u0010\n\u0082\u0001\u0002\u000b\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AncestorAnnotatedWith;", "P", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Annotated;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate$AncestorAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$AncestorAnnotatedWith;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface AncestorAnnotatedWith<P extends AbstractPredicate<P>> extends Annotated<P> {
        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.Annotated, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        default <R, D> R accept(PredicateVisitor<P, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitAncestorAnnotatedWith(this, data);
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0002J;\u0010\b\u001a\u0002H\t\"\u0004\b\u0002\u0010\t\"\u0004\b\u0003\u0010\n2\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\f2\u0006\u0010\r\u001a\u0002H\nH\u0016¢\u0006\u0002\u0010\u000eR\u0012\u0010\u0003\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005\u0082\u0001\u0002\u000f\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$And;", "P", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "a", "getA", "()Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "b", "getB", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate$And;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$And;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface And<P extends AbstractPredicate<P>> extends AbstractPredicate<P> {
        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        default <R, D> R accept(PredicateVisitor<P, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitAnd(this, data);
        }

        P getA();

        P getB();
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0002J;\u0010\u0003\u001a\u0002H\u0004\"\u0004\b\u0002\u0010\u0004\"\u0004\b\u0003\u0010\u00052\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00050\u00072\u0006\u0010\b\u001a\u0002H\u0005H\u0016¢\u0006\u0002\u0010\t\u0082\u0001\u0006\n\u000b\f\r\u000e\u000fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0010À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Annotated;", "P", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AncestorAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$HasAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$ParentAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate$Annotated;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$Annotated;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface Annotated<P extends AbstractPredicate<P>> extends AbstractPredicate<P> {
        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        default <R, D> R accept(PredicateVisitor<P, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitAnnotated(this, data);
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J;\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0002\u0010\u0005\"\u0004\b\u0003\u0010\u00062\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00060\b2\u0006\u0010\t\u001a\u0002H\u0006H\u0016¢\u0006\u0002\u0010\n\u0082\u0001\u0002\u000b\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AnnotatedWith;", "P", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Annotated;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate$AnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$AnnotatedWith;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface AnnotatedWith<P extends AbstractPredicate<P>> extends Annotated<P> {
        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.Annotated, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        default <R, D> R accept(PredicateVisitor<P, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitAnnotatedWith(this, data);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0002\b&\u0018\u0000*\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00028\u0001*\u00028\u00012\u0006\u0010\u0007\u001a\u00028\u0001H¦\u0004¢\u0006\u0002\u0010\bJ\u001a\u0010\t\u001a\u00028\u0001*\u00028\u00012\u0006\u0010\u0007\u001a\u00028\u0001H¦\u0004¢\u0006\u0002\u0010\bJ)\u0010\n\u001a\u00028\u00012\u001a\u0010\u000b\u001a\u000e\u0012\n\b\u0001\u0012\u00060\rj\u0002`\u000e0\f\"\u00060\rj\u0002`\u000eH&¢\u0006\u0002\u0010\u000fJ)\u0010\u0010\u001a\u00028\u00012\u001a\u0010\u000b\u001a\u000e\u0012\n\b\u0001\u0012\u00060\rj\u0002`\u000e0\f\"\u00060\rj\u0002`\u000eH&¢\u0006\u0002\u0010\u000fJ)\u0010\u0011\u001a\u00028\u00012\u001a\u0010\u000b\u001a\u000e\u0012\n\b\u0001\u0012\u00060\rj\u0002`\u000e0\f\"\u00060\rj\u0002`\u000eH&¢\u0006\u0002\u0010\u000fJ)\u0010\u0012\u001a\u00028\u00012\u001a\u0010\u000b\u001a\u000e\u0012\n\b\u0001\u0012\u00060\rj\u0002`\u000e0\f\"\u00060\rj\u0002`\u000eH&¢\u0006\u0002\u0010\u000fJ)\u0010\u0013\u001a\u00028\u00012\u001a\u0010\u000b\u001a\u000e\u0012\n\b\u0001\u0012\u00060\rj\u0002`\u000e0\f\"\u00060\rj\u0002`\u000eH&¢\u0006\u0002\u0010\u000fJ\u001f\u0010\n\u001a\u00028\u00012\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\u0014H&¢\u0006\u0002\u0010\u0015J\u001f\u0010\u0010\u001a\u00028\u00012\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\u0014H&¢\u0006\u0002\u0010\u0015J\u001f\u0010\u0011\u001a\u00028\u00012\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\u0014H&¢\u0006\u0002\u0010\u0015J\u001f\u0010\u0012\u001a\u00028\u00012\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\u0014H&¢\u0006\u0002\u0010\u0015J\u001f\u0010\u0013\u001a\u00028\u00012\u0010\u0010\u000b\u001a\f\u0012\b\u0012\u00060\rj\u0002`\u000e0\u0014H&¢\u0006\u0002\u0010\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$BuilderContext;", "P", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", Argument.Delimiters.none, "<init>", "()V", "or", "other", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;)Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "and", "annotated", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "([Lorg/jetbrains/kotlin/name/FqName;)Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "ancestorAnnotated", "parentAnnotated", "hasAnnotated", "annotatedOrUnder", Argument.Delimiters.none, "(Ljava/util/Collection;)Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class BuilderContext<P extends AbstractPredicate<P>> {
        public abstract P ancestorAnnotated(Collection<FqName> annotations);

        public abstract P ancestorAnnotated(FqName... annotations);

        public abstract P and(P p, P p2);

        public abstract P annotated(Collection<FqName> annotations);

        public abstract P annotated(FqName... annotations);

        public abstract P annotatedOrUnder(Collection<FqName> annotations);

        public abstract P annotatedOrUnder(FqName... annotations);

        public abstract P hasAnnotated(Collection<FqName> annotations);

        public abstract P hasAnnotated(FqName... annotations);

        public abstract P or(P p, P p2);

        public abstract P parentAnnotated(Collection<FqName> annotations);

        public abstract P parentAnnotated(FqName... annotations);
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J;\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0002\u0010\u0005\"\u0004\b\u0003\u0010\u00062\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00060\b2\u0006\u0010\t\u001a\u0002H\u0006H\u0016¢\u0006\u0002\u0010\n\u0082\u0001\u0002\u000b\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$HasAnnotatedWith;", "P", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Annotated;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate$HasAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$HasAnnotatedWith;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface HasAnnotatedWith<P extends AbstractPredicate<P>> extends Annotated<P> {
        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.Annotated, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        default <R, D> R accept(PredicateVisitor<P, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitHasAnnotatedWith(this, data);
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0002J;\u0010\u0007\u001a\u0002H\b\"\u0004\b\u0002\u0010\b\"\u0004\b\u0003\u0010\t2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\t0\u000b2\u0006\u0010\f\u001a\u0002H\tH\u0016¢\u0006\u0002\u0010\rR\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006\u0082\u0001\u0001\u000eø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$MetaAnnotatedWith;", "P", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "includeItself", Argument.Delimiters.none, "getIncludeItself", "()Z", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate$MetaAnnotatedWith;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface MetaAnnotatedWith<P extends AbstractPredicate<P>> extends AbstractPredicate<P> {
        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        default <R, D> R accept(PredicateVisitor<P, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitMetaAnnotatedWith(this, data);
        }

        boolean getIncludeItself();
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0002J;\u0010\b\u001a\u0002H\t\"\u0004\b\u0002\u0010\t\"\u0004\b\u0003\u0010\n2\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H\t\u0012\u0004\u0012\u0002H\n0\f2\u0006\u0010\r\u001a\u0002H\nH\u0016¢\u0006\u0002\u0010\u000eR\u0012\u0010\u0003\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005\u0082\u0001\u0002\u000f\u0010ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0011À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Or;", "P", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "a", "getA", "()Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "b", "getB", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate$Or;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$Or;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface Or<P extends AbstractPredicate<P>> extends AbstractPredicate<P> {
        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        default <R, D> R accept(PredicateVisitor<P, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitOr(this, data);
        }

        P getA();

        P getB();
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000*\u000e\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003J;\u0010\u0004\u001a\u0002H\u0005\"\u0004\b\u0002\u0010\u0005\"\u0004\b\u0003\u0010\u00062\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u0002H\u00060\b2\u0006\u0010\t\u001a\u0002H\u0006H\u0016¢\u0006\u0002\u0010\n\u0082\u0001\u0002\u000b\fø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$ParentAnnotatedWith;", "P", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Annotated;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/DeclarationPredicate$ParentAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$ParentAnnotatedWith;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface ParentAnnotatedWith<P extends AbstractPredicate<P>> extends Annotated<P> {
        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.Annotated, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        default <R, D> R accept(PredicateVisitor<P, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitParentAnnotatedWith(this, data);
        }
    }

    <R, D> R accept(PredicateVisitor<P, R, D> visitor, D data);

    Set<FqName> getAnnotations();

    Set<FqName> getMetaAnnotations();
}
