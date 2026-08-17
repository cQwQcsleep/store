package it.unimi.dsi.fastutil.chars;

import it.unimi.dsi.fastutil.objects.ObjectSortedSet;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface Char2ShortSortedMap extends Char2ShortMap, SortedMap<Character, Short> {
    @Override // it.unimi.dsi.fastutil.chars.Char2ShortMap
    ObjectSortedSet<Char2ShortMap.Entry> char2ShortEntrySet();

    @Override // java.util.SortedMap
    /* JADX INFO: renamed from: comparator, reason: merged with bridge method [inline-methods] */
    Comparator<? super Character> comparator2();

    char firstCharKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Character firstKey() {
        return Character.valueOf(firstCharKey());
    }

    Char2ShortSortedMap headMap(char c);

    @Override // java.util.SortedMap
    @Deprecated
    default Char2ShortSortedMap headMap(Character ch) {
        return headMap(ch.charValue());
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2ShortMap, java.util.Map
    Set<Character> keySet();

    char lastCharKey();

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.util.SortedMap
    @Deprecated
    default Character lastKey() {
        return Character.valueOf(lastCharKey());
    }

    Char2ShortSortedMap subMap(char c, char c2);

    @Override // java.util.SortedMap
    @Deprecated
    default Char2ShortSortedMap subMap(Character ch, Character ch2) {
        return subMap(ch.charValue(), ch2.charValue());
    }

    Char2ShortSortedMap tailMap(char c);

    @Override // java.util.SortedMap
    @Deprecated
    default Char2ShortSortedMap tailMap(Character ch) {
        return tailMap(ch.charValue());
    }

    @Override // it.unimi.dsi.fastutil.chars.Char2ShortMap, java.util.Map
    /* JADX INFO: renamed from: values */
    Collection<Short> values2();

    @Override // it.unimi.dsi.fastutil.chars.Char2ShortMap, java.util.Map
    @Deprecated
    default Set<Map.Entry<Character, Short>> entrySet() {
        return char2ShortEntrySet();
    }
}
