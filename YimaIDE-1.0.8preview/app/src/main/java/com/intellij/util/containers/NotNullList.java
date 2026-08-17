package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class NotNullList<E> extends ArrayList<E> {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 4 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 4 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "e";
        } else if (i == 2 || i == 3) {
            objArr[0] = "element";
        } else if (i != 4) {
            objArr[0] = "c";
        } else {
            objArr[0] = "com/intellij/util/containers/NotNullList";
        }
        if (i != 4) {
            objArr[1] = "com/intellij/util/containers/NotNullList";
        } else {
            objArr[1] = "get";
        }
        switch (i) {
            case 1:
            case 2:
                objArr[2] = "add";
                break;
            case 3:
                objArr[2] = "set";
                break;
            case 4:
                break;
            case 5:
                objArr[2] = "checkNotNullCollection";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "addAll";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 4) {
            throw new IllegalStateException(str2);
        }
    }

    public NotNullList(int i) {
        super(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkNotNullCollection(Collection<? extends E> collection) {
        if (collection == null) {
            $$$reportNull$$$0(5);
        }
        Iterator<? extends E> it = collection.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                aca.a("null element in the collection: ", collection);
                return;
            }
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean add(E e) {
        if (e == null) {
            $$$reportNull$$$0(1);
        }
        return super.add(e);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public boolean addAll(int i, Collection<? extends E> collection) {
        if (collection == null) {
            $$$reportNull$$$0(6);
        }
        checkNotNullCollection(collection);
        return super.addAll(i, collection);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public E get(int i) {
        E e = (E) super.get(i);
        if (e == null) {
            $$$reportNull$$$0(4);
        }
        return e;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public E set(int i, E e) {
        if (e == null) {
            $$$reportNull$$$0(3);
        }
        return (E) super.set(i, e);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public List<E> subList(int i, int i2) {
        final List<E> listSubList = super.subList(i, i2);
        return new AbstractList<E>() { // from class: com.intellij.util.containers.NotNullList.1
            /* JADX WARN: Code duplicated, block: B:32:0x0050  */
            /* JADX WARN: Code duplicated, block: B:40:0x0066  */
            private static /* synthetic */ void $$$reportNull$$$0(int i3) {
                String str = (i3 == 1 || i3 == 2 || i3 == 3 || i3 == 5) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
                Object[] objArr = new Object[(i3 == 1 || i3 == 2 || i3 == 3 || i3 == 5) ? 3 : 2];
                if (i3 == 1) {
                    objArr[0] = "e";
                } else if (i3 == 2 || i3 == 3) {
                    objArr[0] = "element";
                } else if (i3 != 5) {
                    objArr[0] = "com/intellij/util/containers/NotNullList$1";
                } else {
                    objArr[0] = "c";
                }
                if (i3 == 1 || i3 == 2 || i3 == 3) {
                    objArr[1] = "com/intellij/util/containers/NotNullList$1";
                } else if (i3 == 4) {
                    objArr[1] = "subList";
                } else if (i3 != 5) {
                    objArr[1] = "get";
                } else {
                    objArr[1] = "com/intellij/util/containers/NotNullList$1";
                }
                if (i3 == 1) {
                    objArr[2] = "add";
                } else if (i3 == 2) {
                    objArr[2] = "set";
                } else if (i3 == 3) {
                    objArr[2] = "add";
                } else if (i3 == 5) {
                    objArr[2] = "addAll";
                }
                String str2 = String.format(str, objArr);
                if (i3 != 1 && i3 != 2 && i3 != 3 && i3 != 5) {
                    throw new IllegalStateException(str2);
                }
                throw new IllegalArgumentException(str2);
            }

            @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
            public boolean add(E e) {
                if (e == null) {
                    $$$reportNull$$$0(1);
                }
                return listSubList.add(e);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public boolean addAll(Collection<? extends E> collection) {
                if (collection == null) {
                    $$$reportNull$$$0(5);
                }
                NotNullList.this.checkNotNullCollection(collection);
                return listSubList.addAll(collection);
            }

            @Override // java.util.AbstractList, java.util.List
            public E get(int i3) {
                E e = (E) listSubList.get(i3);
                if (e == null) {
                    $$$reportNull$$$0(0);
                }
                return e;
            }

            @Override // java.util.AbstractList, java.util.List
            public E set(int i3, E e) {
                if (e == null) {
                    $$$reportNull$$$0(2);
                }
                return (E) listSubList.set(i3, e);
            }

            @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
            public int size() {
                return listSubList.size();
            }

            @Override // java.util.AbstractList, java.util.List
            public List<E> subList(int i3, int i4) {
                List<E> listSubList2 = listSubList.subList(i3, i4);
                if (listSubList2 == null) {
                    $$$reportNull$$$0(4);
                }
                return listSubList2;
            }

            @Override // java.util.AbstractList, java.util.List
            public void add(int i3, E e) {
                if (e == null) {
                    $$$reportNull$$$0(3);
                }
                listSubList.add(i3, e);
            }

            @Override // java.util.AbstractList, java.util.List
            public boolean addAll(int i3, Collection<? extends E> collection) {
                NotNullList.this.checkNotNullCollection(collection);
                return listSubList.addAll(i3, collection);
            }
        };
    }

    public NotNullList() {
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public void add(int i, E e) {
        if (e == null) {
            $$$reportNull$$$0(2);
        }
        super.add(i, e);
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public boolean addAll(Collection<? extends E> collection) {
        checkNotNullCollection(collection);
        return super.addAll(collection);
    }
}
