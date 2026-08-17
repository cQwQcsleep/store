package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;
import com.intellij.openapi.util.Ref;
import com.intellij.util.Consumer;
import com.intellij.util.Function;
import com.intellij.util.Functions;
import com.intellij.util.NotNullFunction;
import com.intellij.util.PairFunction;
import com.intellij.util.Processor;
import com.intellij.util.containers.JBIterable;
import com.intellij.util.containers.JBIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class JBIterable<E> implements Iterable<E> {
    private static final JBIterable<?> EMPTY = new Empty();
    private final Object content;

    /* JADX INFO: renamed from: com.intellij.util.containers.JBIterable$7, reason: invalid class name */
    public class AnonymousClass7 extends JBIterator<JBIterable<E>> {
        JBIterator<E> it;
        int st;
        E stored;
        final /* synthetic */ Condition val$condition;
        final /* synthetic */ Split val$mode;
        final /* synthetic */ Iterator val$orig;

        public AnonymousClass7(Iterator it, Split split, Condition condition) {
            this.val$orig = it;
            this.val$mode = split;
            this.val$condition = condition;
        }

        /* JADX WARN: Code duplicated, block: B:23:0x003b  */
        public static /* synthetic */ boolean b(AnonymousClass7 anonymousClass7, Condition condition, Split split, Object obj) {
            int i;
            anonymousClass7.getClass();
            boolean zValue = condition.value(obj);
            int i2 = anonymousClass7.st;
            boolean z = true;
            if (i2 < 0 && zValue) {
                i = -2;
            } else if (i2 <= 0 || zValue) {
                i = zValue ? -1 : 1;
            } else {
                i = 2;
            }
            anonymousClass7.st = i;
            int i3 = AnonymousClass8.$SwitchMap$com$intellij$util$containers$JBIterable$Split[split.ordinal()];
            if (i3 == 1) {
                int i4 = anonymousClass7.st;
                if (i4 == -2 || (i4 == 1 && i2 != 0)) {
                    z = false;
                }
            } else if (i3 == 2) {
                int i5 = anonymousClass7.st;
                if (i5 == -2 || i5 == -1) {
                    z = false;
                }
            } else if (i3 != 3) {
                if (i3 != 4) {
                    int i6 = anonymousClass7.st;
                    if (i3 != 5) {
                        throw new AssertionError(i6);
                    }
                    if (i6 <= 0) {
                        z = false;
                    }
                } else if ((i2 < 0 || anonymousClass7.st <= 0) && (i2 > 0 || anonymousClass7.st >= 0)) {
                    z = false;
                }
            } else if (i2 < 0 || anonymousClass7.st <= 0) {
                z = false;
            }
            if (z || split == Split.OFF) {
                obj = (E) null;
            }
            anonymousClass7.stored = (E) obj;
            return z;
        }

        @Override // com.intellij.util.containers.JBIterator
        public JBIterable<E> nextImpl() {
            JBIterator<E> jBIterator;
            Split split;
            do {
                jBIterator = this.it;
                if (jBIterator == null) {
                    break;
                }
            } while (jBIterator.advance());
            this.it = null;
            if (this.stored == null && !this.val$orig.hasNext()) {
                if (this.st >= 0 || (split = this.val$mode) == Split.BEFORE || split == Split.GROUP) {
                    return stop();
                }
                this.st = 1;
                return JBIterable.empty();
            }
            if (this.st == -2 && this.val$mode == Split.AROUND) {
                this.st = -1;
                return JBIterable.empty();
            }
            E e = this.stored;
            this.stored = null;
            JBIterable jBIterableOf = JBIterable.of(e);
            JBIterator<E> jBIteratorWrap = JBIterator.wrap(this.val$orig);
            this.it = jBIteratorWrap;
            final Condition condition = this.val$condition;
            final Split split2 = this.val$mode;
            return jBIterableOf.append((Iterable) JBIterable.once(jBIteratorWrap.takeWhile(new Condition() { // from class: com.intellij.util.containers.j
                public final boolean value(Object obj) {
                    return JBIterable.AnonymousClass7.b(this.b, condition, split2, obj);
                }
            })));
        }
    }

    /* JADX INFO: renamed from: com.intellij.util.containers.JBIterable$8, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] $SwitchMap$com$intellij$util$containers$JBIterable$Split;

        static {
            int[] iArr = new int[Split.values().length];
            $SwitchMap$com$intellij$util$containers$JBIterable$Split = iArr;
            try {
                iArr[Split.AFTER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$intellij$util$containers$JBIterable$Split[Split.BEFORE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$intellij$util$containers$JBIterable$Split[Split.AROUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$intellij$util$containers$JBIterable$Split[Split.GROUP.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$intellij$util$containers$JBIterable$Split[Split.OFF.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public static final class Appended<E> extends JBIterable<E> {
        final Iterable<? extends E> iterable;
        final Appended<? extends E> parent;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i != 1) {
                objArr[0] = "iterable";
            } else {
                objArr[0] = "com/intellij/util/containers/JBIterable$Appended";
            }
            if (i != 1) {
                objArr[1] = "com/intellij/util/containers/JBIterable$Appended";
            } else {
                objArr[1] = "getIterables";
            }
            if (i != 1) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(str2);
            }
        }

        public Appended(Iterable<? extends E> iterable, Appended<? extends E> appended) {
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            this.iterable = iterable;
            this.parent = appended;
        }

        private Iterable<? extends E>[] getIterables() {
            int i = 0;
            int i2 = 0;
            for (Appended<? extends E> appended = (Appended<? extends E>) this; appended != null; appended = appended.parent) {
                i2++;
            }
            Iterable<? extends E>[] iterableArr = new Iterable[i2];
            while (this != null) {
                i++;
                iterableArr[i2 - i] = this.iterable;
                this = this.parent;
            }
            return iterableArr;
        }

        @Override // java.lang.Iterable
        public Iterator<E> iterator() {
            return new FlattenFun.FlattenIt(Arrays.asList(getIterables()).iterator(), Functions.identity());
        }
    }

    public static final class Empty extends JBIterable<Object> {
        private Empty() {
        }

        @Override // java.lang.Iterable
        public Iterator<Object> iterator() {
            return Collections.emptyIterator();
        }
    }

    public static final class FlattenFun<E, T> implements NotNullFunction<Iterator<? extends E>, Iterator<? extends T>> {
        final Function<? super E, ? extends Iterable<? extends T>> function;

        public static final class FlattenIt<E, T> extends JBIterator<T> {
            Iterator<? extends T> cur;
            final Function<? super E, ? extends Iterable<? extends T>> function;
            final Iterator<? extends E> original;

            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                if (i != 1) {
                    objArr[0] = "iterator";
                } else {
                    objArr[0] = "fun";
                }
                objArr[1] = "com/intellij/util/containers/JBIterable$FlattenFun$FlattenIt";
                objArr[2] = "<init>";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            public FlattenIt(Iterator<? extends E> it, Function<? super E, ? extends Iterable<? extends T>> function) {
                if (it == null) {
                    $$$reportNull$$$0(0);
                }
                if (function == null) {
                    $$$reportNull$$$0(1);
                }
                this.original = it;
                this.function = function;
            }

            @Override // com.intellij.util.containers.JBIterator
            public T nextImpl() {
                Iterator<? extends T> it = this.cur;
                if (it != null && it.hasNext()) {
                    return this.cur.next();
                }
                if (!this.original.hasNext()) {
                    return stop();
                }
                Iterable<? extends T> iterableFun = this.function.fun(this.original.next());
                this.cur = iterableFun == null ? null : iterableFun.iterator();
                return skip();
            }
        }

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "function";
            } else {
                objArr[0] = "iterator";
            }
            objArr[1] = "com/intellij/util/containers/JBIterable$FlattenFun";
            if (i != 1) {
                objArr[2] = "<init>";
            } else {
                objArr[2] = "fun";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public FlattenFun(Function<? super E, ? extends Iterable<? extends T>> function) {
            if (function == null) {
                $$$reportNull$$$0(0);
            }
            this.function = function;
        }

        @Override // com.intellij.util.NotNullFunction, com.intellij.util.NullableFunction, com.intellij.util.Function
        public Iterator<T> fun(Iterator<? extends E> it) {
            if (it == null) {
                $$$reportNull$$$0(1);
            }
            return new FlattenIt(it, (Function) Stateful.copy(this.function));
        }
    }

    public static final class Intercepted<E, T> extends JBIterable<T> {
        private final Function<? super Iterator<? extends E>, ? extends Iterator<? extends T>> interceptor;
        final JBIterable<? extends E> original;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "original";
            } else {
                objArr[0] = "interceptor";
            }
            objArr[1] = "com/intellij/util/containers/JBIterable$Intercepted";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public Intercepted(JBIterable<? extends E> jBIterable, Function<? super Iterator<? extends E>, ? extends Iterator<? extends T>> function) {
            if (jBIterable == null) {
                $$$reportNull$$$0(0);
            }
            if (function == null) {
                $$$reportNull$$$0(1);
            }
            this.original = jBIterable;
            this.interceptor = function;
        }

        @Override // java.lang.Iterable
        public Iterator<T> iterator() {
            return this.interceptor.fun(this.original.iterator());
        }
    }

    public static final class Multi<E> extends JBIterable<E> {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "iterable", "com/intellij/util/containers/JBIterable$Multi", "<init>"));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Multi(Iterable<? extends E> iterable) {
            super((Iterable) iterable);
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
        }

        @Override // java.lang.Iterable
        public Iterator<E> iterator() {
            Iterable<E> iterableAsIterable = asIterable();
            Objects.requireNonNull(iterableAsIterable);
            return JBIterator.from(iterableAsIterable.iterator());
        }
    }

    public static abstract class SCond<T> extends Stateful<SCond<T>> implements Condition<T> {
    }

    public static final class Single<E> extends JBIterable<E> {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "content", "com/intellij/util/containers/JBIterable$Single", "<init>"));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Single(E e) {
            super(e);
            if (e == null) {
                $$$reportNull$$$0(0);
            }
        }

        @Override // java.lang.Iterable
        public Iterator<E> iterator() {
            return new SingletonIterator(asElement());
        }
    }

    public enum Split {
        AFTER,
        BEFORE,
        AROUND,
        OFF,
        GROUP
    }

    public static abstract class Stateful<Self extends Stateful<?>> implements Cloneable {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = (i == 1 || i == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
            Object[] objArr = new Object[(i == 1 || i == 2) ? 2 : 3];
            if (i == 1 || i == 2) {
                objArr[0] = "com/intellij/util/containers/JBIterable$Stateful";
            } else {
                objArr[0] = "o";
            }
            if (i == 1 || i == 2) {
                objArr[1] = "copy";
            } else {
                objArr[1] = "com/intellij/util/containers/JBIterable$Stateful";
            }
            if (i != 1 && i != 2) {
                objArr[2] = "copy";
            }
            String str2 = String.format(str, objArr);
            if (i != 1 && i != 2) {
                throw new IllegalArgumentException(str2);
            }
            throw new IllegalStateException(str2);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static <T> T copy(T t) {
            if (t == 0) {
                $$$reportNull$$$0(0);
            }
            if (!(t instanceof Stateful)) {
                if (t == 0) {
                    $$$reportNull$$$0(1);
                }
                return t;
            }
            T t2 = (T) ((Stateful) t).m1875clone();
            if (t2 == null) {
                $$$reportNull$$$0(2);
            }
            return t2;
        }

        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
        public Self m1875clone() {
            try {
                return (Self) super.clone();
            } catch (CloneNotSupportedException e) {
                x01.a(e);
                return null;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x002e A[FALL_THROUGH] */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 2 && i != 5 && i != 7 && i != 30 && i != 34 && i != 52 && i != 48 && i != 49) {
            switch (i) {
                default:
                    switch (i) {
                        default:
                            switch (i) {
                                default:
                                    switch (i) {
                                        case 54:
                                        case 55:
                                        case 56:
                                            break;
                                        default:
                                            str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                                            break;
                                    }
                                case 43:
                                case 44:
                                case 45:
                                    str = "@NotNull method %s.%s must not return null";
                                    break;
                            }
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                            str = "@NotNull method %s.%s must not return null";
                            break;
                    }
                case 10:
                case 11:
                case 12:
                    str = "@NotNull method %s.%s must not return null";
                    break;
            }
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i != 2 && i != 5 && i != 7 && i != 30 && i != 34 && i != 52 && i != 48 && i != 49) {
            switch (i) {
                case 10:
                case 11:
                case 12:
                    i2 = 2;
                    break;
                default:
                    switch (i) {
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                            i2 = 2;
                            break;
                        default:
                            switch (i) {
                                case 43:
                                case 44:
                                case 45:
                                    i2 = 2;
                                    break;
                                default:
                                    switch (i) {
                                        case 54:
                                        case 55:
                                        case 56:
                                            i2 = 2;
                                            break;
                                        default:
                                            i2 = 3;
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            i2 = 2;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 2:
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 10:
            case 11:
            case 12:
            case 30:
            case 34:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 43:
            case 44:
            case 45:
            case 48:
            case 49:
            case 52:
            case 54:
            case 55:
            case 56:
                objArr[0] = "com/intellij/util/containers/JBIterable";
                break;
            case 3:
            case 4:
                objArr[0] = "generator";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "iterator";
                break;
            case 8:
                objArr[0] = "processor";
                break;
            case 9:
                objArr[0] = "consumer";
                break;
            case 13:
                objArr[0] = "fun";
                break;
            case 14:
                objArr[0] = "elements";
                break;
            case 15:
            case 17:
            case 18:
            case 26:
            case 27:
                objArr[0] = "condition";
                break;
            case 16:
                objArr[0] = "type";
                break;
            case 19:
            case 20:
            case 21:
            case 23:
            case 24:
            case 25:
            case 28:
            case 29:
                objArr[0] = "function";
                break;
            case 22:
                objArr[0] = "identity";
                break;
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
                objArr[0] = "mode";
                break;
            case 32:
                objArr[0] = "separator";
                break;
            case 33:
            case 53:
                objArr[0] = "collection";
                break;
            case 35:
                objArr[0] = "comparator";
                break;
            case 42:
                objArr[0] = "array";
                break;
            case 46:
            case 51:
                objArr[0] = "toKey";
                break;
            case 47:
            case 50:
                objArr[0] = "toValue";
                break;
            default:
                objArr[0] = "content";
                break;
        }
        if (i == 2) {
            objArr[1] = "from";
        } else if (i == 5) {
            objArr[1] = "empty";
        } else if (i == 7) {
            objArr[1] = "typedIterator";
        } else if (i == 30) {
            objArr[1] = "flatMap";
        } else if (i == 34) {
            objArr[1] = "collect";
        } else if (i == 52) {
            objArr[1] = "toStream";
        } else if (i != 48 && i != 49) {
            switch (i) {
                case 10:
                    objArr[1] = "toString";
                    break;
                case 11:
                    objArr[1] = "repeat";
                    break;
                case 12:
                    objArr[1] = "append";
                    break;
                default:
                    switch (i) {
                        case 36:
                        case 37:
                        case 38:
                            objArr[1] = "toList";
                            break;
                        case 39:
                        case 40:
                        case 41:
                            objArr[1] = "toSet";
                            break;
                        default:
                            switch (i) {
                                case 43:
                                case 44:
                                case 45:
                                    objArr[1] = "toArray";
                                    break;
                                default:
                                    switch (i) {
                                        case 54:
                                        case 55:
                                        case 56:
                                            objArr[1] = "addAllTo";
                                            break;
                                        default:
                                            objArr[1] = "com/intellij/util/containers/JBIterable";
                                            break;
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        } else {
            objArr[1] = "toMap";
        }
        switch (i) {
            case 2:
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 10:
            case 11:
            case 12:
            case 30:
            case 34:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 43:
            case 44:
            case 45:
            case 48:
            case 49:
            case 52:
            case 54:
            case 55:
            case 56:
                break;
            case 3:
            case 4:
                objArr[2] = "generate";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "once";
                break;
            case 8:
                objArr[2] = "processEach";
                break;
            case 9:
                objArr[2] = "consumeEach";
                break;
            case 13:
            case 14:
                objArr[2] = "append";
                break;
            case 15:
            case 16:
                objArr[2] = "filter";
                break;
            case 17:
                objArr[2] = "takeWhile";
                break;
            case 18:
                objArr[2] = "skipWhile";
                break;
            case 19:
                objArr[2] = "map";
                break;
            case 20:
                objArr[2] = "transform";
                break;
            case 21:
                objArr[2] = "flatten";
                break;
            case 22:
                objArr[2] = "unique";
                break;
            case 23:
                objArr[2] = "intercept";
                break;
            case 24:
            case 25:
                objArr[2] = "reduce";
                break;
            case 26:
                objArr[2] = "find";
                break;
            case 27:
                objArr[2] = "indexOf";
                break;
            case 28:
                objArr[2] = "filterMap";
                break;
            case 29:
                objArr[2] = "flatMap";
                break;
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 32:
                objArr[2] = "split";
                break;
            case 33:
                objArr[2] = "collect";
                break;
            case 35:
                objArr[2] = "sort";
                break;
            case 42:
                objArr[2] = "toArray";
                break;
            case 46:
            case 47:
            case 50:
                objArr[2] = "toMap";
                break;
            case 51:
                objArr[2] = "toReverseMap";
                break;
            case 53:
                objArr[2] = "addAllTo";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 5 && i != 7 && i != 30 && i != 34 && i != 52 && i != 48 && i != 49) {
            switch (i) {
                case 10:
                case 11:
                case 12:
                    break;
                default:
                    switch (i) {
                        case 36:
                        case 37:
                        case 38:
                        case 39:
                        case 40:
                        case 41:
                            break;
                        default:
                            switch (i) {
                                case 43:
                                case 44:
                                case 45:
                                    break;
                                default:
                                    switch (i) {
                                        case 54:
                                        case 55:
                                        case 56:
                                            break;
                                        default:
                                            throw new IllegalArgumentException(str2);
                                    }
                                    break;
                            }
                            break;
                    }
                    break;
            }
        }
        throw new IllegalStateException(str2);
    }

    public JBIterable(E e) {
        if (e == null) {
            $$$reportNull$$$0(0);
        }
        this.content = e;
    }

    private Collection<E> asCollection() {
        Object obj = this.content;
        if (obj instanceof Collection) {
            return (Collection) obj;
        }
        return null;
    }

    private List<E> asRandomAccess() {
        Object obj = this.content;
        if (obj instanceof RandomAccess) {
            return (List) obj;
        }
        return null;
    }

    public static /* synthetic */ Iterator c(JBIterable jBIterable, Condition condition, Split split, Iterator it) {
        jBIterable.getClass();
        return new AnonymousClass7(it, split, (Condition) Stateful.copy(condition));
    }

    public static <E> JBIterable<E> create(final Supplier<? extends Iterator<E>> supplier) {
        return supplier == null ? empty() : new JBIterable<E>() { // from class: com.intellij.util.containers.JBIterable.1
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/containers/JBIterable$1", "iterator"));
            }

            @Override // java.lang.Iterable
            public Iterator<E> iterator() {
                Iterator<E> it = (Iterator) supplier.get();
                if (it == null) {
                    $$$reportNull$$$0(0);
                }
                return it;
            }
        };
    }

    public static <E> JBIterable<E> empty() {
        JBIterable<E> jBIterable = (JBIterable<E>) EMPTY;
        if (jBIterable == null) {
            $$$reportNull$$$0(5);
        }
        return jBIterable;
    }

    public static <E> JBIterable<E> from(Iterable<? extends E> iterable) {
        if (iterable == null || iterable == EMPTY) {
            return empty();
        }
        if (iterable instanceof JBIterable) {
            return (JBIterable) iterable;
        }
        return ((iterable instanceof Collection) && ((Collection) iterable).isEmpty()) ? empty() : new Multi(iterable);
    }

    public static <E> JBIterable<E> generate(final E e, final Function<? super E, ? extends E> function) {
        if (function == null) {
            $$$reportNull$$$0(3);
        }
        return e == null ? empty() : new JBIterable<E>() { // from class: com.intellij.util.containers.JBIterable.2
            @Override // java.lang.Iterable
            public Iterator<E> iterator() {
                final Function function2 = (Function) Stateful.copy(function);
                return new JBIterator<E>() { // from class: com.intellij.util.containers.JBIterable.2.1
                    E cur;

                    {
                        this.cur = (E) e;
                    }

                    @Override // com.intellij.util.containers.JBIterator
                    public E nextImpl() {
                        E e2 = this.cur;
                        if (e2 == null) {
                            return stop();
                        }
                        this.cur = (E) function2.fun(e2);
                        return e2;
                    }
                };
            }
        };
    }

    public static /* synthetic */ Iterator j(JBIterable jBIterable, final int i, final Iterator it) {
        jBIterable.getClass();
        return new JBIterator<JBIterable<E>>() { // from class: com.intellij.util.containers.JBIterable.6
            JBIterator<E> it;

            @Override // com.intellij.util.containers.JBIterator
            public JBIterable<E> nextImpl() {
                JBIterator<E> jBIterator;
                do {
                    jBIterator = this.it;
                    if (jBIterator == null) {
                        break;
                    }
                } while (jBIterator.advance());
                this.it = null;
                if (!it.hasNext()) {
                    return stop();
                }
                JBIterator<E> jBIteratorWrap = JBIterator.wrap(it);
                this.it = jBIteratorWrap;
                return JBIterable.once(jBIteratorWrap.take(i));
            }
        };
    }

    public static /* synthetic */ List n(int i, boolean z, JBIterable jBIterable) {
        List list = (List) jBIterable.addAllTo(new ArrayList(i));
        if (!z || list.size() >= i) {
            return list;
        }
        return null;
    }

    public static /* synthetic */ Iterator o(Iterator it) {
        Ref ref = (Ref) it.next();
        Iterator it2 = (Iterator) ref.get();
        if (it2 != null) {
            ref.set((Object) null);
            return it2;
        }
        a9g.a();
        return null;
    }

    @SafeVarargs
    public static <E> JBIterable<E> of(E... eArr) {
        return (eArr == null || eArr.length == 0) ? empty() : from(Arrays.asList(eArr));
    }

    public static <E> JBIterable<E> once(Iterator<? extends E> it) {
        if (it == null) {
            $$$reportNull$$$0(6);
        }
        return of(Ref.create(it)).intercept(new Function() { // from class: y97
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return JBIterable.o((Iterator) obj);
            }
        });
    }

    public static /* synthetic */ Iterator p(JBIterable jBIterable, final Object obj, final Iterator it) {
        jBIterable.getClass();
        return new JBIterator<E>() { // from class: com.intellij.util.containers.JBIterable.5
            boolean flag;

            @Override // com.intellij.util.containers.JBIterator
            public E nextImpl() {
                if (!it.hasNext()) {
                    return stop();
                }
                boolean z = this.flag;
                this.flag = !z;
                return !z ? (E) it.next() : (E) obj;
            }
        };
    }

    public final <C extends Collection<? super E>> C addAllTo(C c) {
        if (c == null) {
            $$$reportNull$$$0(53);
        }
        if (this == EMPTY) {
            if (c == null) {
                $$$reportNull$$$0(54);
            }
            return c;
        }
        E eAsElement = asElement();
        if (eAsElement != null) {
            c.add(eAsElement);
            return c;
        }
        Collection<E> collectionAsCollection = asCollection();
        if (collectionAsCollection != null) {
            c.addAll(collectionAsCollection);
        } else {
            Iterator<E> it = iterator();
            while (it.hasNext()) {
                c.add(it.next());
            }
        }
        if (c == null) {
            $$$reportNull$$$0(56);
        }
        return c;
    }

    public final <T> JBIterable<E> append(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends E>> function) {
        if (function == null) {
            $$$reportNull$$$0(13);
        }
        if (iterable == null) {
            return this;
        }
        return this == EMPTY ? from(iterable).flatten(function) : append((Iterable) from(iterable).flatten(function));
    }

    public E asElement() {
        if (this instanceof Single) {
            return (E) this.content;
        }
        return null;
    }

    public Iterable<E> asIterable() {
        Object obj = this.content;
        if (obj instanceof Iterable) {
            return (Iterable) obj;
        }
        return null;
    }

    public final JBIterable<E> collect() {
        return this.content instanceof Collection ? this : collect(new ArrayList());
    }

    public final void consumeEach(Consumer<? super E> consumer) {
        if (consumer == null) {
            $$$reportNull$$$0(9);
        }
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            consumer.consume(it.next());
        }
    }

    public final boolean contains(Object obj) {
        if (this == EMPTY) {
            return false;
        }
        E eAsElement = asElement();
        if (eAsElement != null) {
            return Comparing.equal(eAsElement, obj);
        }
        Collection<E> collectionAsCollection = asCollection();
        if (collectionAsCollection != null) {
            return collectionAsCollection.contains(obj);
        }
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            if (Comparing.equal(it.next(), obj)) {
                return true;
            }
        }
        return false;
    }

    public final JBIterable<E> filter(final Condition<? super E> condition) {
        if (condition == null) {
            $$$reportNull$$$0(15);
        }
        return (JBIterable<E>) intercept(new Function() { // from class: u97
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return JBIterator.from((Iterator) obj).filter((Condition) JBIterable.Stateful.copy(condition));
            }
        });
    }

    public final <T> JBIterable<T> filterMap(final Function<? super E, ? extends T> function) {
        if (function == null) {
            $$$reportNull$$$0(28);
        }
        return intercept(new Function() { // from class: t97
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return JBIterator.from((Iterator) obj).filterMap((Function) JBIterable.Stateful.copy(function));
            }
        });
    }

    public final JBIterable<E> filterNotNull() {
        return filter(new Condition() { // from class: x97
            public final boolean value(Object obj) {
                return Objects.nonNull(obj);
            }
        });
    }

    public final E find(Condition<? super E> condition) {
        if (condition == null) {
            $$$reportNull$$$0(26);
        }
        return filter(condition).first();
    }

    public final E first() {
        if (this == EMPTY) {
            return null;
        }
        E eAsElement = asElement();
        if (eAsElement != null) {
            return eAsElement;
        }
        List<E> listAsRandomAccess = asRandomAccess();
        if (listAsRandomAccess != null) {
            if (listAsRandomAccess.isEmpty()) {
                return null;
            }
            return listAsRandomAccess.get(0);
        }
        Iterator<E> it = iterator();
        if (it.hasNext()) {
            return it.next();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <T> JBIterable<T> flatMap(Function<? super E, ? extends Iterable<? extends T>> function) {
        if (function == 0) {
            $$$reportNull$$$0(29);
        }
        JBIterable<T> jBIterableFlatten = map(function).flatten(Functions.identity());
        if (jBIterableFlatten == null) {
            $$$reportNull$$$0(30);
        }
        return jBIterableFlatten;
    }

    public <T> JBIterable<T> flatten(Function<? super E, ? extends Iterable<? extends T>> function) {
        if (function == null) {
            $$$reportNull$$$0(21);
        }
        return intercept(new FlattenFun(function));
    }

    public final E get(int i) {
        if (this == EMPTY) {
            return null;
        }
        E eAsElement = asElement();
        if (eAsElement != null) {
            if (i == 0) {
                return eAsElement;
            }
            return null;
        }
        List<E> listAsRandomAccess = asRandomAccess();
        if (listAsRandomAccess == null) {
            return skip(i).first();
        }
        if (i >= listAsRandomAccess.size()) {
            return null;
        }
        return listAsRandomAccess.get(i);
    }

    public final int indexOf(Condition<? super E> condition) {
        if (condition == null) {
            $$$reportNull$$$0(27);
        }
        Iterator<E> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            if (condition.value(it.next())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public final <T> JBIterable<T> intercept(Function<? super Iterator<? extends E>, ? extends Iterator<? extends T>> function) {
        if (function == null) {
            $$$reportNull$$$0(23);
        }
        if (this == EMPTY) {
            return empty();
        }
        if (!(this instanceof Intercepted)) {
            return new Intercepted(this, function);
        }
        Intercepted intercepted = (Intercepted) this;
        return new Intercepted(intercepted.original, Functions.compose(intercepted.interceptor, function));
    }

    public final boolean isEmpty() {
        if (this == EMPTY) {
            return true;
        }
        if (asElement() != null) {
            return false;
        }
        Collection<E> collectionAsCollection = asCollection();
        return collectionAsCollection != null ? collectionAsCollection.isEmpty() : !iterator().hasNext();
    }

    public final boolean isNotEmpty() {
        return !isEmpty();
    }

    public final JBIterable<E> join(final E e) {
        return (JBIterable<E>) intercept(new Function() { // from class: w97
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return JBIterable.p(this.b, e, (Iterator) obj);
            }
        });
    }

    public final E last() {
        E next = null;
        if (this == EMPTY) {
            return null;
        }
        E eAsElement = asElement();
        if (eAsElement != null) {
            return eAsElement;
        }
        List<E> listAsRandomAccess = asRandomAccess();
        if (listAsRandomAccess != null) {
            if (listAsRandomAccess.isEmpty()) {
                return null;
            }
            return listAsRandomAccess.get(listAsRandomAccess.size() - 1);
        }
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            next = it.next();
        }
        return next;
    }

    public final <T> JBIterable<T> map(final Function<? super E, ? extends T> function) {
        if (function == null) {
            $$$reportNull$$$0(19);
        }
        return intercept(new Function() { // from class: v97
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return JBIterator.from((Iterator) obj).map((Function) JBIterable.Stateful.copy(function));
            }
        });
    }

    public final boolean processEach(Processor<? super E> processor) {
        if (processor == null) {
            $$$reportNull$$$0(8);
        }
        return ContainerUtil.process(this, processor);
    }

    public final E reduce(PairFunction<? super E, ? super E, ? extends E> pairFunction) {
        if (pairFunction == null) {
            $$$reportNull$$$0(25);
        }
        boolean z = true;
        E eFun = null;
        for (E e : this) {
            if (z) {
                z = false;
                eFun = e;
            } else {
                eFun = pairFunction.fun(eFun, e);
            }
        }
        return eFun;
    }

    public final JBIterable<E> repeat(int i) {
        JBIterable<E> jBIterable = (JBIterable<E>) generate(this, Functions.id()).take(i).flatten(Functions.id());
        if (jBIterable == null) {
            $$$reportNull$$$0(11);
        }
        return jBIterable;
    }

    public final E single() {
        if (this == EMPTY) {
            return null;
        }
        E eAsElement = asElement();
        if (eAsElement != null) {
            return eAsElement;
        }
        List<E> listAsRandomAccess = asRandomAccess();
        if (listAsRandomAccess != null) {
            if (listAsRandomAccess.size() != 1) {
                return null;
            }
            return listAsRandomAccess.get(0);
        }
        Iterator<E> it = iterator();
        E next = it.hasNext() ? it.next() : null;
        if (it.hasNext()) {
            return null;
        }
        return next;
    }

    public final int size() {
        int i = 0;
        if (this == EMPTY) {
            return 0;
        }
        if (asElement() != null) {
            return 1;
        }
        Collection<E> collectionAsCollection = asCollection();
        if (collectionAsCollection != null) {
            return collectionAsCollection.size();
        }
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            it.next();
            i++;
        }
        return i;
    }

    public final JBIterable<E> skip(final int i) {
        return (JBIterable<E>) intercept(new Function() { // from class: q97
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return JBIterator.from((Iterator) obj).skip(i);
            }
        });
    }

    public final JBIterable<E> skipWhile(final Condition<? super E> condition) {
        if (condition == null) {
            $$$reportNull$$$0(18);
        }
        return (JBIterable<E>) intercept(new Function() { // from class: s97
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return JBIterator.from((Iterator) obj).skipWhile((Condition) JBIterable.Stateful.copy(condition));
            }
        });
    }

    public final JBIterable<E> sort(Comparator<? super E> comparator) {
        if (comparator == null) {
            $$$reportNull$$$0(35);
        }
        ArrayList arrayList = (ArrayList) addAllTo(new ArrayList());
        arrayList.sort(comparator);
        return from(arrayList);
    }

    public final JBIterable<JBIterable<E>> split(final Split split, final Condition<? super E> condition) {
        if (split == null) {
            $$$reportNull$$$0(31);
        }
        if (condition == null) {
            $$$reportNull$$$0(32);
        }
        return (JBIterable<JBIterable<E>>) intercept(new Function() { // from class: aa7
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return JBIterable.c(this.b, condition, split, (Iterator) obj);
            }
        });
    }

    public final JBIterable<E> take(final int i) {
        return (JBIterable<E>) intercept(new Function() { // from class: p97
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return JBIterator.from((Iterator) obj).take(i);
            }
        });
    }

    public final JBIterable<E> takeWhile(final Condition<? super E> condition) {
        if (condition == null) {
            $$$reportNull$$$0(17);
        }
        return (JBIterable<E>) intercept(new Function() { // from class: r97
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return JBIterator.from((Iterator) obj).takeWhile((Condition) JBIterable.Stateful.copy(condition));
            }
        });
    }

    public final E[] toArray(E[] eArr) {
        if (eArr == null) {
            $$$reportNull$$$0(42);
        }
        if (this == EMPTY) {
            if (eArr == null) {
                $$$reportNull$$$0(43);
            }
            return eArr;
        }
        E eAsElement = asElement();
        if (eAsElement != null) {
            E[] eArr2 = (E[]) Collections.singletonList(eAsElement).toArray(eArr);
            if (eArr2 == null) {
                $$$reportNull$$$0(44);
            }
            return eArr2;
        }
        E[] eArr3 = (E[]) ContainerUtil.newArrayList(this).toArray(eArr);
        if (eArr3 == null) {
            $$$reportNull$$$0(45);
        }
        return eArr3;
    }

    public final List<E> toList() {
        if (this == EMPTY) {
            List<E> list = Collections.EMPTY_LIST;
            if (list == null) {
                $$$reportNull$$$0(36);
            }
            return list;
        }
        E eAsElement = asElement();
        if (eAsElement != null) {
            List<E> listSingletonList = Collections.singletonList(eAsElement);
            if (listSingletonList == null) {
                $$$reportNull$$$0(37);
            }
            return listSingletonList;
        }
        ArrayList arrayListNewArrayList = ContainerUtil.newArrayList(this);
        List<E> listUnmodifiableList = arrayListNewArrayList.isEmpty() ? Collections.EMPTY_LIST : Collections.unmodifiableList(arrayListNewArrayList);
        if (listUnmodifiableList == null) {
            $$$reportNull$$$0(38);
        }
        return listUnmodifiableList;
    }

    public final <K, V> Map<K, V> toMap(Convertor<? super E, ? extends K> convertor, Convertor<? super E, ? extends V> convertor2) {
        if (convertor == null) {
            $$$reportNull$$$0(46);
        }
        if (convertor2 == null) {
            $$$reportNull$$$0(47);
        }
        if (this == EMPTY) {
            Map<K, V> map = Collections.EMPTY_MAP;
            if (map == null) {
                $$$reportNull$$$0(48);
            }
            return map;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (E e : this) {
            linkedHashMap.put(convertor.convert(e), convertor2.convert(e));
        }
        Map<K, V> mapUnmodifiableMap = linkedHashMap.isEmpty() ? Collections.EMPTY_MAP : Collections.unmodifiableMap(linkedHashMap);
        if (mapUnmodifiableMap == null) {
            $$$reportNull$$$0(49);
        }
        return mapUnmodifiableMap;
    }

    public final <K> Map<K, E> toReverseMap(Convertor<? super E, ? extends K> convertor) {
        if (convertor == null) {
            $$$reportNull$$$0(51);
        }
        return (Map<K, E>) toMap(convertor, Convertor.self());
    }

    public final Set<E> toSet() {
        if (this == EMPTY) {
            Set<E> set = Collections.EMPTY_SET;
            if (set == null) {
                $$$reportNull$$$0(39);
            }
            return set;
        }
        E eAsElement = asElement();
        if (eAsElement != null) {
            Set<E> setSingleton = Collections.singleton(eAsElement);
            if (setSingleton == null) {
                $$$reportNull$$$0(40);
            }
            return setSingleton;
        }
        LinkedHashSet linkedHashSetNewLinkedHashSet = ContainerUtil.newLinkedHashSet(this);
        Set<E> setUnmodifiableSet = linkedHashSetNewLinkedHashSet.isEmpty() ? Collections.EMPTY_SET : Collections.unmodifiableSet(linkedHashSetNewLinkedHashSet);
        if (setUnmodifiableSet == null) {
            $$$reportNull$$$0(41);
        }
        return setUnmodifiableSet;
    }

    public final Stream<E> toStream() {
        Stream<E> stream = StreamSupport.stream(spliterator(), false);
        if (stream == null) {
            $$$reportNull$$$0(52);
        }
        return stream;
    }

    public String toString() {
        Object obj = this.content;
        return obj == this ? JBIterable.class.getSimpleName() : String.valueOf(obj);
    }

    public final <T> JBIterable<T> transform(Function<? super E, ? extends T> function) {
        if (function == null) {
            $$$reportNull$$$0(20);
        }
        return map(function);
    }

    public <T extends Iterator<E>> T typedIterator() {
        Iterator<E> it = iterator();
        if (it == null) {
            $$$reportNull$$$0(7);
        }
        return it;
    }

    public final JBIterable<E> unique(final Function<? super E, ?> function) {
        if (function == null) {
            $$$reportNull$$$0(22);
        }
        return filter(new SCond<E>() { // from class: com.intellij.util.containers.JBIterable.4
            Set<Object> visited;

            public boolean value(E e) {
                if (this.visited == null) {
                    this.visited = new HashSet();
                }
                return this.visited.add(function.fun(e));
            }
        });
    }

    public JBIterable() {
        this.content = this;
    }

    public JBIterable(Iterable<? extends E> iterable) {
        if (iterable == null) {
            $$$reportNull$$$0(1);
        }
        this.content = iterable;
    }

    public final JBIterable<E> collect(Collection<E> collection) {
        if (collection == null) {
            $$$reportNull$$$0(33);
        }
        return from(addAllTo(collection));
    }

    public final <T> JBIterable<T> filter(Class<T> cls) {
        if (cls == null) {
            $$$reportNull$$$0(16);
        }
        return filter(Conditions.instanceOf(cls));
    }

    public final JBIterable<E> unique() {
        return unique(Functions.identity());
    }

    public static <E> JBIterable<E> generate(final E e, final E e2, final PairFunction<? super E, ? super E, ? extends E> pairFunction) {
        if (pairFunction == null) {
            $$$reportNull$$$0(4);
        }
        if (e == null) {
            return empty();
        }
        return new JBIterable<E>() { // from class: com.intellij.util.containers.JBIterable.3
            @Override // java.lang.Iterable
            public Iterator<E> iterator() {
                return new JBIterator<E>() { // from class: com.intellij.util.containers.JBIterable.3.1
                    E cur1;
                    E cur2;

                    {
                        this.cur1 = (E) e;
                        this.cur2 = (E) e2;
                    }

                    @Override // com.intellij.util.containers.JBIterator
                    public E nextImpl() {
                        E e3 = this.cur1;
                        E e4 = this.cur2;
                        this.cur1 = e4;
                        this.cur2 = (E) pairFunction.fun(e3, e4);
                        return e3 == null ? stop() : e3;
                    }
                };
            }
        };
    }

    public static <E> JBIterable<E> of(E e) {
        if (e == null) {
            return empty();
        }
        return new Single(e);
    }

    public final JBIterable<JBIterable<E>> split(final int i) {
        if (i > 0) {
            return (JBIterable<JBIterable<E>>) intercept(new Function() { // from class: o97
                @Override // com.intellij.util.Function
                public final Object fun(Object obj) {
                    return JBIterable.j(this.b, i, (Iterator) obj);
                }
            });
        }
        zqc.a(i, " <= 0");
        return null;
    }

    public final JBIterable<List<E>> split(final int i, final boolean z) {
        return (JBIterable<List<E>>) split(i).filterMap(new Function() { // from class: z97
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return JBIterable.n(i, z, (JBIterable) obj);
            }
        });
    }

    public final <T> T reduce(T t, PairFunction<? super T, ? super E, ? extends T> pairFunction) {
        if (pairFunction == null) {
            $$$reportNull$$$0(24);
        }
        Iterator<E> it = iterator();
        while (it.hasNext()) {
            t = pairFunction.fun(t, it.next());
        }
        return t;
    }

    public final JBIterable<E> append(Iterable<? extends E> iterable) {
        JBIterable<?> jBIterable;
        if (iterable == null || iterable == (jBIterable = EMPTY)) {
            return this;
        }
        if (this == jBIterable) {
            return from(iterable);
        }
        return new Appended(iterable, this instanceof Appended ? (Appended) this : new Appended(this, null));
    }

    public final JBIterable<E> append(E[] eArr) {
        if (eArr == null) {
            $$$reportNull$$$0(14);
        }
        return this == EMPTY ? of((Object[]) eArr) : append((Iterable) of((Object[]) eArr));
    }

    public final JBIterable<E> append(E e) {
        if (e == null) {
            return this;
        }
        return this == EMPTY ? of(e) : append((Iterable) of(e));
    }

    public final <V> Map<E, V> toMap(Convertor<? super E, ? extends V> convertor) {
        if (convertor == null) {
            $$$reportNull$$$0(50);
        }
        return (Map<E, V>) toMap(Convertor.self(), convertor);
    }
}
