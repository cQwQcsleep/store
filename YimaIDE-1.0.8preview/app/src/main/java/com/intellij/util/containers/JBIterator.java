package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.Function;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class JBIterator<E> implements Iterator<E> {
    private static final Function.Mono CURSOR_NEXT = new Function.Mono<JBIterator<?>>() { // from class: com.intellij.util.containers.JBIterator.2
        @Override // com.intellij.util.Function
        public JBIterator<?> fun(JBIterator<?> jBIterator) {
            Objects.requireNonNull(jBIterator);
            return (JBIterator) jBIterator.addOp(false, new CursorOp());
        }
    };
    private Object myCurrent;
    private Op myFirstOp;
    private Op myLastOp;
    private Object myNext;

    public static final class CountDown<A> implements Condition<A> {
        int cur;

        public CountDown(int i) {
            this.cur = i;
        }

        public boolean value(A a) {
            int i = this.cur;
            if (i <= 0) {
                return false;
            }
            this.cur = i - 1;
            return true;
        }
    }

    public final class CursorOp extends Op<Void> {
        boolean advanced;

        public CursorOp() {
            super(null);
        }

        public void advance(Object obj) {
            if (this.advanced || !(obj instanceof JBIterator)) {
                return;
            }
            ((JBIterator) obj).advance();
            this.advanced = true;
        }

        @Override // com.intellij.util.containers.JBIterator.Op
        public Object apply(Object obj) {
            JBIterator jBIterator = (JBIterator) obj;
            boolean z = this.nextOp != null;
            this.advanced = z;
            return (!z ? jBIterator.hasNext() : jBIterator.advance()) ? JBIterator.this.stop() : jBIterator;
        }
    }

    public enum Do {
        INIT,
        STOP,
        SKIP
    }

    public final class FilterMapOp<E, T> extends Op<Function<? super E, ? extends T>> {
        public FilterMapOp(Function<? super E, ? extends T> function) {
            super(function);
        }

        @Override // com.intellij.util.containers.JBIterator.Op
        public Object apply(Object obj) {
            Object objFun = ((Function) this.impl).fun(obj);
            return objFun != null ? objFun : JBIterator.this.skip();
        }
    }

    public final class FilterOp<E> extends Op<Condition<? super E>> {
        public FilterOp(Condition<? super E> condition) {
            super(condition);
        }

        @Override // com.intellij.util.containers.JBIterator.Op
        public Object apply(Object obj) {
            return ((Condition) this.impl).value(obj) ? obj : JBIterator.this.skip();
        }
    }

    public static final class MapOp<E, T> extends Op<Function<? super E, ? extends T>> {
        public MapOp(Function<? super E, ? extends T> function) {
            super(function);
        }

        @Override // com.intellij.util.containers.JBIterator.Op
        public Object apply(Object obj) {
            return ((Function) this.impl).fun(obj);
        }
    }

    public static final class NextOp extends Op<Void> {
        public NextOp() {
            super(null);
        }

        @Override // com.intellij.util.containers.JBIterator.Op
        public Object apply(Object obj) {
            return obj;
        }
    }

    public static class Op<T> {
        final T impl;
        Op nextOp;

        public Op(T t) {
            this.impl = t;
        }

        public Object apply(Object obj) {
            throw new UnsupportedOperationException();
        }

        public String toString() {
            T t = this.impl;
            if (t != null) {
                this = (Op<T>) t;
            }
            return JBIterator.toShortString(this);
        }
    }

    public final class SkipOp<E> extends Op<Condition<? super E>> {
        boolean active;

        public SkipOp(Condition<? super E> condition) {
            super(condition);
            this.active = true;
        }

        @Override // com.intellij.util.containers.JBIterator.Op
        public Object apply(Object obj) {
            if (this.active && ((Condition) this.impl).value(obj)) {
                return JBIterator.this.skip();
            }
            this.active = false;
            return obj;
        }
    }

    public final class WhileOp<E> extends Op<Condition<? super E>> {
        public WhileOp(Condition<? super E> condition) {
            super(condition);
        }

        @Override // com.intellij.util.containers.JBIterator.Op
        public Object apply(Object obj) {
            return ((Condition) this.impl).value(obj) ? obj : JBIterator.this.stop();
        }
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0011  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 1 && i != 15 && i != 16) {
            switch (i) {
                case 10:
                case 11:
                case 12:
                case 13:
                    str = "@NotNull method %s.%s must not return null";
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i != 1 && i != 15 && i != 16) {
            switch (i) {
                case 10:
                case 11:
                case 12:
                case 13:
                    i2 = 2;
                    break;
                default:
                    i2 = 3;
                    break;
            }
        } else {
            i2 = 2;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 10:
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
                objArr[0] = "com/intellij/util/containers/JBIterator";
                break;
            case 2:
            case 3:
                objArr[0] = "it";
                break;
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "function";
                break;
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[0] = "condition";
                break;
            case 9:
                objArr[0] = "op";
                break;
            case 14:
                objArr[0] = "o";
                break;
            default:
                objArr[0] = "iterator";
                break;
        }
        if (i == 1) {
            objArr[1] = "cursor";
        } else if (i != 15 && i != 16) {
            switch (i) {
                case 10:
                    objArr[1] = "addOp";
                    break;
                case 11:
                    objArr[1] = "toList";
                    break;
                case 12:
                    objArr[1] = "getTransformations";
                    break;
                case 13:
                    objArr[1] = "operationsImpl";
                    break;
                default:
                    objArr[1] = "com/intellij/util/containers/JBIterator";
                    break;
            }
        } else {
            objArr[1] = "toShortString";
        }
        switch (i) {
            case 1:
            case 10:
            case 11:
            case 12:
            case 13:
            case 15:
            case 16:
                break;
            case 2:
                objArr[2] = "from";
                break;
            case 3:
                objArr[2] = "wrap";
                break;
            case 4:
                objArr[2] = "map";
                break;
            case 5:
                objArr[2] = "filter";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "filterMap";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "takeWhile";
                break;
            case 8:
                objArr[2] = "skipWhile";
                break;
            case 9:
                objArr[2] = "addOp";
                break;
            case 14:
                objArr[2] = "toShortString";
                break;
            default:
                objArr[2] = "cursor";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 15 && i != 16) {
            switch (i) {
                case 10:
                case 11:
                case 12:
                case 13:
                    break;
                default:
                    throw new IllegalArgumentException(str2);
            }
        }
        throw new IllegalStateException(str2);
    }

    public JBIterator() {
        Do r0 = Do.INIT;
        this.myCurrent = r0;
        this.myNext = r0;
        NextOp nextOp = new NextOp();
        this.myFirstOp = nextOp;
        this.myLastOp = nextOp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public <T> T addOp(boolean z, Op op) {
        if (op == null) {
            $$$reportNull$$$0(9);
        }
        if (op.impl == null) {
            this.myLastOp = op;
            this.myFirstOp = op;
            return this;
        }
        if (z) {
            this.myLastOp.nextOp = op;
            this.myLastOp = op;
            return this;
        }
        op.nextOp = this.myFirstOp;
        this.myFirstOp = op;
        return this;
    }

    public static <E> JBIterator<E> from(Iterator<? extends E> it) {
        if (it == null) {
            $$$reportNull$$$0(2);
        }
        return it instanceof JBIterator ? (JBIterator) it : wrap(it);
    }

    private JBIterable<Op> operationsImpl() {
        JBIterable<Op> jBIterableGenerate = JBIterable.generate(this.myFirstOp, new Function() { // from class: com.intellij.util.containers.k
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return ((JBIterator.Op) obj).nextOp;
            }
        });
        if (jBIterableGenerate == null) {
            $$$reportNull$$$0(13);
        }
        return jBIterableGenerate;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void peekNext() {
        Object obj = this.myNext;
        Object objApply = Do.INIT;
        if (obj != objApply) {
            return;
        }
        while (true) {
            Op op = this.myFirstOp;
            while (true) {
                if (op == null) {
                    this.myNext = objApply;
                    return;
                }
                if (op.impl == 0) {
                    objApply = nextImpl();
                }
                objApply = op.apply(objApply);
                Object obj2 = this.myNext;
                if (obj2 == Do.STOP) {
                    return;
                }
                if (obj2 == Do.SKIP) {
                    objApply = Do.INIT;
                    this.myNext = objApply;
                    if (op.impl == 0) {
                        Op op2 = this.myFirstOp;
                        while (true) {
                            T t = op2.impl;
                            if (!(t instanceof CountDown)) {
                                break;
                            }
                            ((CountDown) t).cur++;
                            op2 = op2.nextOp;
                        }
                    }
                    op = null;
                }
                if (op == null) {
                    break;
                } else {
                    op = op.nextOp;
                }
            }
        }
    }

    public static String toShortString(Object obj) {
        int i;
        if (obj == null) {
            $$$reportNull$$$0(14);
        }
        String name = obj.getClass().getName();
        int iLastIndexOf = name.lastIndexOf(36);
        return (iLastIndexOf <= 0 || (i = iLastIndexOf + 1) >= name.length() || !StringUtil.isJavaIdentifierStart(name.charAt(i))) ? name.substring(name.lastIndexOf(46) + 1) : name.substring(i);
    }

    public static <E> JBIterator<E> wrap(final Iterator<? extends E> it) {
        if (it == null) {
            $$$reportNull$$$0(3);
        }
        return new JBIterator<E>() { // from class: com.intellij.util.containers.JBIterator.1
            @Override // com.intellij.util.containers.JBIterator
            public E nextImpl() {
                return it.hasNext() ? (E) it.next() : stop();
            }
        };
    }

    public final boolean advance() {
        Do r0 = Do.INIT;
        this.myCurrent = r0;
        peekNext();
        Object obj = this.myNext;
        if (obj == Do.STOP) {
            return false;
        }
        this.myCurrent = obj;
        this.myNext = r0;
        Op op = this.myFirstOp;
        if (op instanceof CursorOp) {
            ((CursorOp) op).advance(obj);
        }
        currentChanged();
        return true;
    }

    public final E current() {
        E e = (E) this.myCurrent;
        if (e != Do.INIT) {
            return e;
        }
        z0e.a();
        return null;
    }

    public void currentChanged() {
    }

    public final JBIterator<E> filter(Condition<? super E> condition) {
        if (condition == null) {
            $$$reportNull$$$0(5);
        }
        return (JBIterator) addOp(true, new FilterOp(condition));
    }

    public final <T> JBIterator<T> filterMap(Function<? super E, ? extends T> function) {
        if (function == null) {
            $$$reportNull$$$0(6);
        }
        return (JBIterator) addOp(true, new FilterMapOp(function));
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        peekNext();
        return this.myNext != Do.STOP;
    }

    public final <T> JBIterator<T> map(Function<? super E, ? extends T> function) {
        if (function == null) {
            $$$reportNull$$$0(4);
        }
        return (JBIterator) addOp(true, new MapOp(function));
    }

    @Override // java.util.Iterator
    public final E next() {
        advance();
        return current();
    }

    public abstract E nextImpl();

    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final JBIterator<E> skip(int i) {
        return skipWhile(new CountDown(i));
    }

    public final JBIterator<E> skipWhile(Condition<? super E> condition) {
        if (condition == null) {
            $$$reportNull$$$0(8);
        }
        return (JBIterator) addOp(true, new SkipOp(condition));
    }

    public final E stop() {
        this.myNext = Do.STOP;
        return null;
    }

    public final JBIterator<E> take(int i) {
        return (JBIterator) addOp(!(this.myLastOp instanceof NextOp), new WhileOp(new CountDown(i)));
    }

    public final JBIterator<E> takeWhile(Condition<? super E> condition) {
        if (condition == null) {
            $$$reportNull$$$0(7);
        }
        return (JBIterator) addOp(true, new WhileOp(condition));
    }

    public String toString() {
        String str;
        List<Op> list = operationsImpl().toList();
        StringBuilder sb = new StringBuilder("{cur=");
        sb.append(this.myCurrent);
        sb.append("; next=");
        sb.append(this.myNext);
        if (list.size() < 2) {
            str = "";
        } else {
            str = "; ops=" + list;
        }
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }

    public final E skip() {
        this.myNext = Do.SKIP;
        return null;
    }
}
