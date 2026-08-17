package com.intellij.util.indexing.impl;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.ObjectUtils;
import com.intellij.util.SystemProperties;
import com.intellij.util.containers.SingletonIterator;
import com.intellij.util.indexing.ValueContainer;
import com.intellij.util.indexing.containers.ChangeBufferingList;
import com.intellij.util.indexing.containers.IntIdsIterator;
import com.intellij.util.indexing.impl.ValueContainerImpl;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class ValueContainerImpl<Value> extends UpdatableValueContainer<Value> implements Cloneable {
    private static final boolean DO_EXPENSIVE_CHECKS;
    public static final IntIdsIterator EMPTY_ITERATOR;
    protected static final Logger LOG = Logger.getInstance(ValueContainerImpl.class);
    private static final boolean USE_SYNCHRONIZED_VALUE_CONTAINER;
    private Object myInputIdMapping;
    private Object myInputIdMappingValue;

    public static final class EmptyValueIterator<Value> implements InvertedIndexValueIterator<Value> {
        private static final EmptyValueIterator<Object> INSTANCE = new EmptyValueIterator<>();

        private EmptyValueIterator() {
        }

        @Override // com.intellij.util.indexing.ValueContainer.ValueIterator
        public ValueContainer.IntIterator getInputIdsIterator() {
            throw new IllegalStateException();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        @Override // java.util.Iterator
        public Value next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }
    }

    public static final class SingleValueIterator implements IntIdsIterator {
        private final int myValue;
        private boolean myValueRead;

        private SingleValueIterator(int i) {
            this.myValue = i;
        }

        @Override // com.intellij.util.indexing.containers.IntIdsIterator
        public IntIdsIterator createCopyInInitialState() {
            return new SingleValueIterator(this.myValue);
        }

        @Override // com.intellij.util.indexing.ValueContainer.IntIterator
        public boolean hasNext() {
            return !this.myValueRead;
        }

        @Override // com.intellij.util.indexing.ValueContainer.IntIterator
        public int next() {
            this.myValueRead = true;
            return this.myValue;
        }
    }

    public static final class ValueToInputMap<Value> extends Object2ObjectOpenHashMap<Value, Object> {
        /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ValueToInputMap<Value> m1878clone() {
            return (ValueToInputMap) super.clone();
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                i2 = 2;
                break;
            default:
                i2 = 3;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[0] = "com/intellij/util/indexing/impl/ValueContainerImpl";
                break;
            case 9:
            case 11:
                objArr[0] = "out";
                break;
            case 10:
            case 14:
                objArr[0] = "externalizer";
                break;
            case 12:
                objArr[0] = "fileSetObject";
                break;
            case 13:
                objArr[0] = "stream";
                break;
            case 15:
                objArr[0] = "remapping";
                break;
            default:
                objArr[0] = "fileSet";
                break;
        }
        switch (i) {
            case 1:
                objArr[1] = "getDebugMessage";
                break;
            case 2:
                objArr[1] = "wrapValue";
                break;
            case 3:
                objArr[1] = "getValueIterator";
                break;
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[1] = "getPredicateOutOfFileSetObject";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[1] = "getIntIteratorOutOfFileSetObject";
                break;
            default:
                objArr[1] = "com/intellij/util/indexing/impl/ValueContainerImpl";
                break;
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                break;
            case 9:
            case 10:
                objArr[2] = "saveTo";
                break;
            case 11:
            case 12:
                objArr[2] = "storeFileSet";
                break;
            case 13:
            case 14:
            case 15:
                objArr[2] = "readFrom";
                break;
            default:
                objArr[2] = "resetFileSetForValue";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    static {
        DO_EXPENSIVE_CHECKS = (IndexDebugProperties.IS_UNIT_TEST_MODE || IndexDebugProperties.EXTRA_SANITY_CHECKS) && !IndexDebugProperties.IS_IN_STRESS_TESTS;
        USE_SYNCHRONIZED_VALUE_CONTAINER = SystemProperties.getBooleanProperty("idea.use.synchronized.value.container", false);
        EMPTY_ITERATOR = new IntIdsIterator() { // from class: com.intellij.util.indexing.impl.ValueContainerImpl.2
            @Override // com.intellij.util.indexing.containers.IntIdsIterator
            public IntIdsIterator createCopyInInitialState() {
                return this;
            }

            @Override // com.intellij.util.indexing.ValueContainer.IntIterator
            public boolean hasNext() {
                return false;
            }

            @Override // com.intellij.util.indexing.ValueContainer.IntIterator
            public int next() {
                return 0;
            }
        };
    }

    public static /* synthetic */ boolean a(StringBuilder sb, int i, Object obj) {
        sb.append(i);
        sb.append(" <-> '");
        sb.append(obj);
        sb.append("'\n");
        return true;
    }

    private Value asValue() {
        return (Value) this.myInputIdMapping;
    }

    public static /* synthetic */ void b(ValueToInputMap valueToInputMap, Object obj, Object obj2) {
        if (obj2 instanceof ChangeBufferingList) {
            valueToInputMap.put(obj, ((ChangeBufferingList) obj2).clone());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ValueContainer.IntIterator getIntIteratorOutOfFileSetObject(Object obj) {
        if (obj == null) {
            IntIdsIterator intIdsIterator = EMPTY_ITERATOR;
            if (intIdsIterator == null) {
                $$$reportNull$$$0(7);
            }
            return intIdsIterator;
        }
        if (obj instanceof Integer) {
            return new SingleValueIterator(((Integer) obj).intValue());
        }
        IntIdsIterator intIdsIteratorIntIterator = ((ChangeBufferingList) obj).intIterator();
        if (intIdsIteratorIntIterator == null) {
            $$$reportNull$$$0(8);
        }
        return intIdsIteratorIntIterator;
    }

    public static <Value> Value unwrap(Value value) {
        if (value == ObjectUtils.NULL) {
            return null;
        }
        return value;
    }

    public static <Value> Value wrapValue(Value value) {
        if (value == null) {
            value = (Value) ObjectUtils.NULL;
        }
        if (value == null) {
            $$$reportNull$$$0(2);
        }
        return value;
    }

    public ValueToInputMap<Value> asMapping() {
        Object obj = this.myInputIdMapping;
        if (obj instanceof ValueToInputMap) {
            return (ValueToInputMap) obj;
        }
        return null;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public ValueContainerImpl<Value> m1876clone() {
        try {
            ValueContainerImpl<Value> valueContainerImpl = (ValueContainerImpl) super.clone();
            ValueToInputMap<Value> valueToInputMapAsMapping = asMapping();
            if (valueToInputMapAsMapping != null) {
                final ValueToInputMap<Value> valueToInputMapM1878clone = valueToInputMapAsMapping.m1878clone();
                valueToInputMapM1878clone.forEach(new BiConsumer() { // from class: o9f
                    @Override // java.util.function.BiConsumer
                    public final void accept(Object obj, Object obj2) {
                        ValueContainerImpl.b(valueToInputMapM1878clone, obj, obj2);
                    }
                });
                valueContainerImpl.myInputIdMapping = valueToInputMapM1878clone;
                return valueContainerImpl;
            }
            Object obj = this.myInputIdMappingValue;
            if (obj instanceof ChangeBufferingList) {
                valueContainerImpl.myInputIdMappingValue = ((ChangeBufferingList) obj).clone();
            }
            return valueContainerImpl;
        } catch (CloneNotSupportedException e) {
            rc6.a(e);
            return null;
        }
    }

    @Override // com.intellij.util.indexing.ValueContainer
    public InvertedIndexValueIterator<Value> getValueIterator() {
        if (this.myInputIdMapping != null) {
            ValueToInputMap<Value> valueToInputMapAsMapping = asMapping();
            final Iterator singletonIterator = valueToInputMapAsMapping == null ? new SingletonIterator(new AbstractMap.SimpleImmutableEntry(wrapValue(asValue()), this.myInputIdMappingValue)) : valueToInputMapAsMapping.entrySet().iterator();
            return new InvertedIndexValueIterator<Value>() { // from class: com.intellij.util.indexing.impl.ValueContainerImpl.1
                private Object currentFileSet;
                private Value currentValue;

                private static /* synthetic */ void $$$reportNull$$$0(int i) {
                    Object[] objArr = new Object[2];
                    objArr[0] = "com/intellij/util/indexing/impl/ValueContainerImpl$1";
                    if (i != 1) {
                        objArr[1] = "getInputIdsIterator";
                    } else {
                        objArr[1] = "getValueAssociationPredicate";
                    }
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", objArr));
                }

                public Object getFileSetObject() {
                    if (this.currentValue != null) {
                        return this.currentFileSet;
                    }
                    g33.a();
                    return null;
                }

                @Override // com.intellij.util.indexing.ValueContainer.ValueIterator
                public ValueContainer.IntIterator getInputIdsIterator() {
                    ValueContainer.IntIterator intIteratorOutOfFileSetObject = ValueContainerImpl.getIntIteratorOutOfFileSetObject(getFileSetObject());
                    if (intIteratorOutOfFileSetObject == null) {
                        $$$reportNull$$$0(0);
                    }
                    return intIteratorOutOfFileSetObject;
                }

                @Override // java.util.Iterator
                public boolean hasNext() {
                    return singletonIterator.hasNext();
                }

                @Override // java.util.Iterator
                public Value next() {
                    Map.Entry entry = (Map.Entry) singletonIterator.next();
                    Value value = (Value) entry.getKey();
                    this.currentValue = value;
                    this.currentFileSet = entry.getValue();
                    return (Value) ValueContainerImpl.unwrap(value);
                }
            };
        }
        EmptyValueIterator emptyValueIterator = EmptyValueIterator.INSTANCE;
        if (emptyValueIterator == null) {
            $$$reportNull$$$0(3);
        }
        return emptyValueIterator;
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder();
        forEach(new ValueContainer.ContainerAction() { // from class: p9f
            @Override // com.intellij.util.indexing.ValueContainer.ContainerAction
            public final boolean perform(int i, Object obj) {
                return ValueContainerImpl.a(sb, i, obj);
            }
        });
        return sb.toString();
    }
}
