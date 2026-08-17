package org.jetbrains.kotlin.fir.extensions.predicate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00000\u0001:\t\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003J;\u0010\f\u001a\u0002H\r\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u000e2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0000\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u000e0\u00102\u0006\u0010\u0011\u001a\u0002H\u000eH&¢\u0006\u0002\u0010\u0012R\u001c\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u00058F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\t\u0082\u0001\u0003\u001c\u001d\u001e¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate;", "<init>", "()V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "getAnnotations", "()Ljava/util/Set;", "metaAnnotations", "getMetaAnnotations", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Or", "And", "Annotated", "AnnotatedWith", "AncestorAnnotatedWith", "ParentAnnotatedWith", "HasAnnotatedWith", "BuilderContext", "Companion", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$And;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$Annotated;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$Or;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class LookupPredicate implements AbstractPredicate<LookupPredicate> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0019\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ;\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\f2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f0\u000e2\u0006\u0010\u000f\u001a\u0002H\fH\u0016¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$AncestorAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$Annotated;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AncestorAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "<init>", "(Ljava/util/Set;)V", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AncestorAnnotatedWith extends Annotated implements AbstractPredicate.AncestorAnnotatedWith<LookupPredicate> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AncestorAnnotatedWith(Set<FqName> set) {
            super(set, null);
            set.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate.Annotated, org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        public <R, D> R accept(PredicateVisitor<LookupPredicate, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitAncestorAnnotatedWith(this, data);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0019\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ;\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\f2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f0\u000e2\u0006\u0010\u000f\u001a\u0002H\fH\u0016¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$AnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$Annotated;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$AnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "<init>", "(Ljava/util/Set;)V", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnnotatedWith extends Annotated implements AbstractPredicate.AnnotatedWith<LookupPredicate> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnnotatedWith(Set<FqName> set) {
            super(set, null);
            set.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate.Annotated, org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        public <R, D> R accept(PredicateVisitor<LookupPredicate, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitAnnotatedWith(this, data);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0019\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ;\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\f2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f0\u000e2\u0006\u0010\u000f\u001a\u0002H\fH\u0016¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$HasAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$Annotated;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$HasAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "<init>", "(Ljava/util/Set;)V", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class HasAnnotatedWith extends Annotated implements AbstractPredicate.HasAnnotatedWith<LookupPredicate> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public HasAnnotatedWith(Set<FqName> set) {
            super(set, null);
            set.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate.Annotated, org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        public <R, D> R accept(PredicateVisitor<LookupPredicate, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitHasAnnotatedWith(this, data);
        }
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0019\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u00060\u0006j\u0002`\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ;\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b\"\u0004\b\u0001\u0010\f2\u0018\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\f0\u000e2\u0006\u0010\u000f\u001a\u0002H\fH\u0016¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$ParentAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$Annotated;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$ParentAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "<init>", "(Ljava/util/Set;)V", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ParentAnnotatedWith extends Annotated implements AbstractPredicate.ParentAnnotatedWith<LookupPredicate> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ParentAnnotatedWith(Set<FqName> set) {
            super(set, null);
            set.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate.Annotated, org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        public <R, D> R accept(PredicateVisitor<LookupPredicate, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitParentAnnotatedWith(this, data);
        }
    }

    public /* synthetic */ LookupPredicate(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
    public abstract <R, D> R accept(PredicateVisitor<LookupPredicate, R, D> visitor, D data);

    @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
    public abstract Set<FqName> getAnnotations();

    @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
    public final Set<FqName> getMetaAnnotations() {
        return SetsKt.emptySet();
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0002\b\tH\u0086\bø\u0001\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "init", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$BuilderContext;", "Lkotlin/ExtensionFunctionType;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LookupPredicate create(Function1<? super BuilderContext, ? extends LookupPredicate> init) {
            init.getClass();
            return (LookupPredicate) init.invoke(BuilderContext.INSTANCE);
        }

        private Companion() {
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J;\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u00122\u0018\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00120\u00142\u0006\u0010\u0015\u001a\u0002H\u0012H\u0016¢\u0006\u0002\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u0001X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0001X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u001e\u0010\n\u001a\f\u0012\b\u0012\u00060\fj\u0002`\r0\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$And;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$And;", "a", "b", "<init>", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;)V", "getA", "()Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "getB", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "getAnnotations", "()Ljava/util/Set;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class And extends LookupPredicate implements AbstractPredicate.And<LookupPredicate> {
        private final LookupPredicate a;
        private final Set<FqName> annotations;
        private final LookupPredicate b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public And(LookupPredicate lookupPredicate, LookupPredicate lookupPredicate2) {
            super(null);
            lookupPredicate.getClass();
            lookupPredicate2.getClass();
            this.a = lookupPredicate;
            this.b = lookupPredicate2;
            this.annotations = SetsKt.plus(getA().getAnnotations(), getB().getAnnotations());
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        public <R, D> R accept(PredicateVisitor<LookupPredicate, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitAnd(this, data);
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        public Set<FqName> getAnnotations() {
            return this.annotations;
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.And
        public LookupPredicate getA() {
            return this.a;
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.And
        public LookupPredicate getB() {
            return this.b;
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0001¢\u0006\u0004\b\u0005\u0010\u0006J;\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u0011\"\u0004\b\u0001\u0010\u00122\u0018\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\u0011\u0012\u0004\u0012\u0002H\u00120\u00142\u0006\u0010\u0015\u001a\u0002H\u0012H\u0016¢\u0006\u0002\u0010\u0016R\u0014\u0010\u0003\u001a\u00020\u0001X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0004\u001a\u00020\u0001X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u001e\u0010\n\u001a\f\u0012\b\u0012\u00060\fj\u0002`\r0\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$Or;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Or;", "a", "b", "<init>", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;)V", "getA", "()Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "getB", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "getAnnotations", "()Ljava/util/Set;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Or extends LookupPredicate implements AbstractPredicate.Or<LookupPredicate> {
        private final LookupPredicate a;
        private final Set<FqName> annotations;
        private final LookupPredicate b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Or(LookupPredicate lookupPredicate, LookupPredicate lookupPredicate2) {
            super(null);
            lookupPredicate.getClass();
            lookupPredicate2.getClass();
            this.a = lookupPredicate;
            this.b = lookupPredicate2;
            this.annotations = SetsKt.plus(getA().getAnnotations(), getB().getAnnotations());
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        public <R, D> R accept(PredicateVisitor<LookupPredicate, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitOr(this, data);
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        public Set<FqName> getAnnotations() {
            return this.annotations;
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.Or
        public LookupPredicate getA() {
            return this.a;
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.Or
        public LookupPredicate getB() {
            return this.b;
        }
    }

    private LookupPredicate() {
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0000\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0096\u0004J\u0015\u0010\u0007\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0096\u0004J)\u0010\b\u001a\u00020\u00022\u001a\u0010\t\u001a\u000e\u0012\n\b\u0001\u0012\u00060\u000bj\u0002`\f0\n\"\u00060\u000bj\u0002`\fH\u0016¢\u0006\u0002\u0010\rJ)\u0010\u000e\u001a\u00020\u00022\u001a\u0010\t\u001a\u000e\u0012\n\b\u0001\u0012\u00060\u000bj\u0002`\f0\n\"\u00060\u000bj\u0002`\fH\u0016¢\u0006\u0002\u0010\rJ)\u0010\u000f\u001a\u00020\u00022\u001a\u0010\t\u001a\u000e\u0012\n\b\u0001\u0012\u00060\u000bj\u0002`\f0\n\"\u00060\u000bj\u0002`\fH\u0016¢\u0006\u0002\u0010\rJ)\u0010\u0010\u001a\u00020\u00022\u001a\u0010\t\u001a\u000e\u0012\n\b\u0001\u0012\u00060\u000bj\u0002`\f0\n\"\u00060\u000bj\u0002`\fH\u0016¢\u0006\u0002\u0010\rJ)\u0010\u0011\u001a\u00020\u00022\u001a\u0010\t\u001a\u000e\u0012\n\b\u0001\u0012\u00060\u000bj\u0002`\f0\n\"\u00060\u000bj\u0002`\fH\u0016¢\u0006\u0002\u0010\rJ\u001a\u0010\b\u001a\u00020\u00022\u0010\u0010\t\u001a\f\u0012\b\u0012\u00060\u000bj\u0002`\f0\u0012H\u0016J\u001a\u0010\u000e\u001a\u00020\u00022\u0010\u0010\t\u001a\f\u0012\b\u0012\u00060\u000bj\u0002`\f0\u0012H\u0016J\u001a\u0010\u000f\u001a\u00020\u00022\u0010\u0010\t\u001a\f\u0012\b\u0012\u00060\u000bj\u0002`\f0\u0012H\u0016J\u001a\u0010\u0010\u001a\u00020\u00022\u0010\u0010\t\u001a\f\u0012\b\u0012\u00060\u000bj\u0002`\f0\u0012H\u0016J\u001a\u0010\u0011\u001a\u00020\u00022\u0010\u0010\t\u001a\f\u0012\b\u0012\u00060\u000bj\u0002`\f0\u0012H\u0016¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$BuilderContext;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$BuilderContext;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "<init>", "()V", "or", "other", "and", "annotated", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "([Lorg/jetbrains/kotlin/name/FqName;)Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "ancestorAnnotated", "parentAnnotated", "hasAnnotated", "annotatedOrUnder", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class BuilderContext extends AbstractPredicate.BuilderContext<LookupPredicate> {
        public static final BuilderContext INSTANCE = new BuilderContext();

        private BuilderContext() {
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public LookupPredicate ancestorAnnotated(Collection<FqName> annotations) {
            annotations.getClass();
            return new AncestorAnnotatedWith(CollectionsKt.toSet(annotations));
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public LookupPredicate and(LookupPredicate lookupPredicate, LookupPredicate lookupPredicate2) {
            lookupPredicate.getClass();
            lookupPredicate2.getClass();
            return new And(lookupPredicate, lookupPredicate2);
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public LookupPredicate annotated(Collection<FqName> annotations) {
            annotations.getClass();
            return new AnnotatedWith(CollectionsKt.toSet(annotations));
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public LookupPredicate annotatedOrUnder(FqName... annotations) {
            annotations.getClass();
            return or(annotated((FqName[]) Arrays.copyOf(annotations, annotations.length)), ancestorAnnotated((FqName[]) Arrays.copyOf(annotations, annotations.length)));
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public LookupPredicate hasAnnotated(Collection<FqName> annotations) {
            annotations.getClass();
            return new HasAnnotatedWith(CollectionsKt.toSet(annotations));
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public LookupPredicate or(LookupPredicate lookupPredicate, LookupPredicate lookupPredicate2) {
            lookupPredicate.getClass();
            lookupPredicate2.getClass();
            return new Or(lookupPredicate, lookupPredicate2);
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public LookupPredicate parentAnnotated(Collection<FqName> annotations) {
            annotations.getClass();
            return new ParentAnnotatedWith(CollectionsKt.toSet(annotations));
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public LookupPredicate ancestorAnnotated(FqName... annotations) {
            annotations.getClass();
            return ancestorAnnotated((Collection<FqName>) ArraysKt.toList(annotations));
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public LookupPredicate annotated(FqName... annotations) {
            annotations.getClass();
            return annotated((Collection<FqName>) ArraysKt.toList(annotations));
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public LookupPredicate hasAnnotated(FqName... annotations) {
            annotations.getClass();
            return hasAnnotated((Collection<FqName>) ArraysKt.toList(annotations));
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public LookupPredicate parentAnnotated(FqName... annotations) {
            annotations.getClass();
            return parentAnnotated((Collection<FqName>) ArraysKt.toList(annotations));
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public /* bridge */ /* synthetic */ AbstractPredicate ancestorAnnotated(Collection collection) {
            return ancestorAnnotated((Collection<FqName>) collection);
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public /* bridge */ /* synthetic */ AbstractPredicate annotated(Collection collection) {
            return annotated((Collection<FqName>) collection);
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public /* bridge */ /* synthetic */ AbstractPredicate hasAnnotated(Collection collection) {
            return hasAnnotated((Collection<FqName>) collection);
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public /* bridge */ /* synthetic */ AbstractPredicate parentAnnotated(Collection collection) {
            return parentAnnotated((Collection<FqName>) collection);
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public /* bridge */ /* synthetic */ AbstractPredicate annotatedOrUnder(Collection collection) {
            return annotatedOrUnder((Collection<FqName>) collection);
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate.BuilderContext
        public LookupPredicate annotatedOrUnder(Collection<FqName> annotations) {
            annotations.getClass();
            return or(annotated(annotations), ancestorAnnotated(annotations));
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u001b\b\u0004\u0012\u0010\u0010\u0003\u001a\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0004¢\u0006\u0004\b\u0007\u0010\bJ;\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f\"\u0004\b\u0001\u0010\r2\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u0002H\f\u0012\u0004\u0012\u0002H\r0\u000f2\u0006\u0010\u0010\u001a\u0002H\rH\u0016¢\u0006\u0002\u0010\u0011R\u001b\u0010\u0003\u001a\f\u0012\b\u0012\u00060\u0005j\u0002`\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0001\u0004\u0012\u0013\u0014\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$Annotated;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/AbstractPredicate$Annotated;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/extensions/AnnotationFqn;", "<init>", "(Ljava/util/Set;)V", "getAnnotations", "()Ljava/util/Set;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/extensions/predicate/PredicateVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$AncestorAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$AnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$HasAnnotatedWith;", "Lorg/jetbrains/kotlin/fir/extensions/predicate/LookupPredicate$ParentAnnotatedWith;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class Annotated extends LookupPredicate implements AbstractPredicate.Annotated<LookupPredicate> {
        private final Set<FqName> annotations;

        private Annotated(Set<FqName> set) {
            super(null);
            this.annotations = set;
            if (set.isEmpty()) {
                w01.a("Annotations should be not empty");
                throw null;
            }
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        public <R, D> R accept(PredicateVisitor<LookupPredicate, R, D> visitor, D data) {
            visitor.getClass();
            return visitor.visitAnnotated(this, data);
        }

        @Override // org.jetbrains.kotlin.fir.extensions.predicate.LookupPredicate, org.jetbrains.kotlin.fir.extensions.predicate.AbstractPredicate
        public final Set<FqName> getAnnotations() {
            return this.annotations;
        }

        public /* synthetic */ Annotated(Set set, DefaultConstructorMarker defaultConstructorMarker) {
            this(set);
        }
    }
}
