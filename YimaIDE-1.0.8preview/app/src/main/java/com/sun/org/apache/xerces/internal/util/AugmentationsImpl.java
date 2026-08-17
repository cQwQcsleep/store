package com.sun.org.apache.xerces.internal.util;

import com.sun.org.apache.xerces.internal.xni.Augmentations;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class AugmentationsImpl implements Augmentations {
    private AugmentationsItemsContainer fAugmentationsContainer = new SmallContainer();

    public abstract class AugmentationsItemsContainer {
        public AugmentationsItemsContainer() {
        }

        public abstract void clear();

        public abstract AugmentationsItemsContainer expand();

        public abstract Object getItem(Object obj);

        public abstract boolean isFull();

        public abstract Enumeration<Object> keys();

        public abstract Object putItem(Object obj, Object obj2);

        public abstract Object removeItem(Object obj);
    }

    public class LargeContainer extends AugmentationsItemsContainer {
        final Map<Object, Object> fAugmentations;

        public LargeContainer() {
            super();
            this.fAugmentations = new HashMap();
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public void clear() {
            this.fAugmentations.clear();
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public AugmentationsItemsContainer expand() {
            return this;
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public Object getItem(Object obj) {
            return this.fAugmentations.get(obj);
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public boolean isFull() {
            return false;
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public Enumeration<Object> keys() {
            return Collections.enumeration(this.fAugmentations.keySet());
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public Object putItem(Object obj, Object obj2) {
            return this.fAugmentations.put(obj, obj2);
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public Object removeItem(Object obj) {
            return this.fAugmentations.remove(obj);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("LargeContainer");
            for (Object obj : this.fAugmentations.keySet()) {
                sb.append("\nkey == ");
                sb.append(obj);
                sb.append("; value == ");
                sb.append(this.fAugmentations.get(obj));
            }
            return sb.toString();
        }
    }

    public class SmallContainer extends AugmentationsItemsContainer {
        static final int SIZE_LIMIT = 10;
        final Object[] fAugmentations;
        int fNumEntries;

        public class SmallContainerKeyEnumeration implements Enumeration<Object> {
            Object[] enumArray;
            int next = 0;

            public SmallContainerKeyEnumeration() {
                this.enumArray = new Object[SmallContainer.this.fNumEntries];
                for (int i = 0; i < SmallContainer.this.fNumEntries; i++) {
                    this.enumArray[i] = SmallContainer.this.fAugmentations[i * 2];
                }
            }

            @Override // java.util.Enumeration
            public boolean hasMoreElements() {
                return this.next < this.enumArray.length;
            }

            @Override // java.util.Enumeration
            public Object nextElement() {
                int i = this.next;
                Object[] objArr = this.enumArray;
                if (i >= objArr.length) {
                    z0e.a();
                    return null;
                }
                Object obj = objArr[i];
                objArr[i] = null;
                this.next = i + 1;
                return obj;
            }
        }

        public SmallContainer() {
            super();
            this.fAugmentations = new Object[20];
            this.fNumEntries = 0;
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public void clear() {
            for (int i = 0; i < this.fNumEntries * 2; i += 2) {
                Object[] objArr = this.fAugmentations;
                objArr[i] = null;
                objArr[i + 1] = null;
            }
            this.fNumEntries = 0;
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public AugmentationsItemsContainer expand() {
            LargeContainer largeContainer = AugmentationsImpl.this.new LargeContainer();
            for (int i = 0; i < this.fNumEntries * 2; i += 2) {
                Object[] objArr = this.fAugmentations;
                largeContainer.putItem(objArr[i], objArr[i + 1]);
            }
            return largeContainer;
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public Object getItem(Object obj) {
            for (int i = 0; i < this.fNumEntries * 2; i += 2) {
                if (this.fAugmentations[i].equals(obj)) {
                    return this.fAugmentations[i + 1];
                }
            }
            return null;
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public boolean isFull() {
            return this.fNumEntries == 10;
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public Enumeration<Object> keys() {
            return new SmallContainerKeyEnumeration();
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public Object putItem(Object obj, Object obj2) {
            int i = 0;
            while (true) {
                int i2 = this.fNumEntries;
                int i3 = i2 * 2;
                Object[] objArr = this.fAugmentations;
                if (i >= i3) {
                    objArr[i2 * 2] = obj;
                    objArr[(i2 * 2) + 1] = obj2;
                    this.fNumEntries = i2 + 1;
                    return null;
                }
                if (objArr[i].equals(obj)) {
                    Object[] objArr2 = this.fAugmentations;
                    int i4 = i + 1;
                    Object obj3 = objArr2[i4];
                    objArr2[i4] = obj2;
                    return obj3;
                }
                i += 2;
            }
        }

        @Override // com.sun.org.apache.xerces.internal.util.AugmentationsImpl.AugmentationsItemsContainer
        public Object removeItem(Object obj) {
            int i = 0;
            while (i < this.fNumEntries * 2) {
                if (this.fAugmentations[i].equals(obj)) {
                    Object obj2 = this.fAugmentations[i + 1];
                    while (true) {
                        int i2 = this.fNumEntries;
                        int i3 = (i2 * 2) - 2;
                        Object[] objArr = this.fAugmentations;
                        if (i >= i3) {
                            objArr[(i2 * 2) - 2] = null;
                            objArr[(i2 * 2) - 1] = null;
                            this.fNumEntries = i2 - 1;
                            return obj2;
                        }
                        int i4 = i + 2;
                        objArr[i] = objArr[i4];
                        objArr[i + 1] = objArr[i + 3];
                        i = i4;
                    }
                } else {
                    i += 2;
                }
            }
            return null;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("SmallContainer - fNumEntries == ");
            sb.append(this.fNumEntries);
            for (int i = 0; i < 20; i += 2) {
                sb.append("\nfAugmentations[");
                sb.append(i);
                sb.append("] == ");
                sb.append(this.fAugmentations[i]);
                sb.append("; fAugmentations[");
                int i2 = i + 1;
                sb.append(i2);
                sb.append("] == ");
                sb.append(this.fAugmentations[i2]);
            }
            return sb.toString();
        }
    }

    @Override // com.sun.org.apache.xerces.internal.xni.Augmentations
    public Object getItem(String str) {
        return this.fAugmentationsContainer.getItem(str);
    }

    @Override // com.sun.org.apache.xerces.internal.xni.Augmentations
    public Enumeration<Object> keys() {
        return this.fAugmentationsContainer.keys();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.Augmentations
    public Object putItem(String str, Object obj) {
        Object objPutItem = this.fAugmentationsContainer.putItem(str, obj);
        if (objPutItem == null && this.fAugmentationsContainer.isFull()) {
            this.fAugmentationsContainer = this.fAugmentationsContainer.expand();
        }
        return objPutItem;
    }

    @Override // com.sun.org.apache.xerces.internal.xni.Augmentations
    public void removeAllItems() {
        this.fAugmentationsContainer.clear();
    }

    @Override // com.sun.org.apache.xerces.internal.xni.Augmentations
    public Object removeItem(String str) {
        return this.fAugmentationsContainer.removeItem(str);
    }

    public String toString() {
        return this.fAugmentationsContainer.toString();
    }
}
