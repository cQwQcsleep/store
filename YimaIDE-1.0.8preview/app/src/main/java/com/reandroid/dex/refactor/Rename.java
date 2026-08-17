package com.reandroid.dex.refactor;

import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyPair;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.StringsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Rename<T extends Key, R extends Key> {
    private final Map<KeyPair<?, ?>, KeyPair<T, R>> keyPairMap = new HashMap();
    private final Map<KeyPair<?, ?>, KeyPair<T, R>> flippedKeyMap = new HashMap();
    private final Set<KeyPair<?, ?>> lockedKeys = new HashSet();
    private final Set<KeyPair<?, ?>> lockedFlippedKeys = new HashSet();

    /* JADX WARN: Code duplicated, block: B:29:0x0054  */
    /* JADX WARN: Multi-variable type inference failed */
    private void addToSet(KeyPair<T, R> keyPair) {
        if (keyPair == null || !keyPair.isValid()) {
            return;
        }
        boolean z = true;
        boolean z2 = this.lockedKeys.contains(keyPair) || this.lockedFlippedKeys.contains(keyPair);
        KeyPair keyPairFlip = keyPair.flip();
        if (this.lockedFlippedKeys.contains(keyPairFlip) || this.lockedKeys.contains(keyPairFlip)) {
            if (z2) {
                return;
            } else {
                z2 = true;
            }
        }
        if (z2) {
            z = z2;
        } else {
            KeyPair<T, R> keyPair2 = this.keyPairMap.get(keyPair);
            if (keyPair2 == null) {
                if (this.flippedKeyMap.get(keyPairFlip) == null) {
                    z = z2;
                }
            } else if (keyPair.equalsBoth(keyPair2)) {
                return;
            }
        }
        if (z) {
            lockKey(keyPair, keyPairFlip);
        } else {
            this.keyPairMap.put(keyPair, keyPair);
            this.flippedKeyMap.put(keyPairFlip, keyPair);
        }
        onChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void lockKey(KeyPair<T, R> keyPair, KeyPair<R, T> keyPair2) {
        this.lockedKeys.add(keyPair);
        this.lockedFlippedKeys.add(keyPair2);
        KeyPair<T, R> keyPairRemove = this.keyPairMap.remove(keyPair);
        if (keyPairRemove == null) {
            keyPairRemove = this.keyPairMap.remove(keyPair2);
        }
        KeyPair<T, R> keyPairRemove2 = this.flippedKeyMap.remove(keyPair2);
        if (keyPairRemove2 == null) {
            keyPairRemove2 = this.flippedKeyMap.remove(keyPair);
        }
        if (keyPairRemove != null && !keyPairRemove.equalsBoth(keyPair)) {
            lockKey(keyPairRemove, keyPairRemove.flip());
        }
        if (keyPairRemove2 == null || keyPairRemove2.equalsBoth(keyPair)) {
            return;
        }
        lockKey(keyPairRemove2, keyPairRemove2.flip());
    }

    public void add(T t, R r) {
        add(new KeyPair<>(t, r));
    }

    public void addAll(Iterator<KeyPair<T, R>> it) {
        while (it.hasNext()) {
            add(it.next());
        }
    }

    public abstract int apply(DexClassRepository dexClassRepository);

    public void close() {
        this.keyPairMap.clear();
        this.flippedKeyMap.clear();
        this.lockedKeys.clear();
        this.lockedFlippedKeys.clear();
    }

    public boolean containsDeclaration(DexClassRepository dexClassRepository, R r) {
        return dexClassRepository.getDexDeclaration(r) != null;
    }

    public KeyPair<T, R> get(Key key) {
        return this.keyPairMap.get(new KeyPair(key, null));
    }

    public KeyPair<T, R> getFlipped(Key key) {
        return this.flippedKeyMap.get(new KeyPair(key, null));
    }

    public Set<KeyPair<T, R>> getKeyPairSet() {
        return (Set) ObjectsUtil.cast(this.keyPairMap.keySet());
    }

    public R getReplace(Key key) {
        KeyPair<T, R> keyPair = get(key);
        if (keyPair != null) {
            return (R) keyPair.getSecond();
        }
        return null;
    }

    public boolean isLocked(KeyPair<T, R> keyPair) {
        if (keyPair == null) {
            return false;
        }
        Object objFlip = keyPair.flip();
        return this.lockedKeys.contains(keyPair) || this.lockedFlippedKeys.contains(keyPair) || this.lockedKeys.contains(objFlip) || this.lockedFlippedKeys.contains(objFlip);
    }

    public List<KeyPair<T, R>> listLocked() {
        return (List) ObjectsUtil.cast(new ArrayCollection(this.lockedKeys));
    }

    public void lock(KeyPair<T, R> keyPair) {
        if (keyPair == null || !keyPair.isValid()) {
            return;
        }
        lockKey(keyPair, keyPair.flip());
    }

    public void lockAll(Iterable<? extends KeyPair<T, R>> iterable) {
        if (iterable != null) {
            Iterator<? extends KeyPair<T, R>> it = iterable.iterator();
            while (it.hasNext()) {
                lock(it.next());
            }
        }
    }

    public void onChanged() {
    }

    public <E extends Key> E replaceInKey(E e) {
        Key replace = getReplace(e);
        if (replace != null) {
            return e.getClass() == replace.getClass() ? (E) ObjectsUtil.cast(replace) : e;
        }
        Iterator<? extends Key> itMentionedKeys = e.mentionedKeys();
        while (itMentionedKeys.hasNext()) {
            Key next = itMentionedKeys.next();
            Key replace2 = getReplace(next);
            if (replace2 != null) {
                e = (E) e.replaceKey(next, replace2);
            }
        }
        return (E) ObjectsUtil.cast(e);
    }

    public int size() {
        return this.keyPairMap.size();
    }

    public List<KeyPair<T, R>> toList(Comparator<KeyPair<? super T, ? super R>> comparator) {
        ArrayCollection arrayCollection = new ArrayCollection(getKeyPairSet());
        if (comparator != null) {
            arrayCollection.sort(comparator);
        }
        return arrayCollection;
    }

    public String toString() {
        return StringsUtil.join((Iterable<?>) toList(), (Object) '\n');
    }

    public void unlock(KeyPair<T, R> keyPair) {
        if (keyPair != null) {
            Object objFlip = keyPair.flip();
            this.lockedKeys.remove(keyPair);
            this.lockedFlippedKeys.remove(objFlip);
            add(keyPair);
        }
    }

    public void validate(DexClassRepository dexClassRepository) {
        for (KeyPair<T, R> keyPair : toList()) {
            if (containsDeclaration(dexClassRepository, keyPair.getSecond())) {
                lock(keyPair);
            }
        }
    }

    public void add(KeyPair<T, R> keyPair) {
        addToSet(keyPair);
    }

    public List<KeyPair<T, R>> toList() {
        return toList(CompareUtil.getComparableComparator());
    }

    public void addAll(Collection<KeyPair<T, R>> collection) {
        addAll(collection.iterator());
    }
}
