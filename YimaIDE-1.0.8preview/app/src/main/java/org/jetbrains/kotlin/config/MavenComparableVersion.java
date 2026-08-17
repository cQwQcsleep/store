package org.jetbrains.kotlin.config;

import java.io.PrintStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Stack;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class MavenComparableVersion implements Comparable<MavenComparableVersion> {
    private String canonical;
    private ListItem items;
    private String value;

    public interface Item {
        public static final int INTEGER_ITEM = 0;
        public static final int LIST_ITEM = 2;
        public static final int STRING_ITEM = 1;

        int compareTo(Item item);

        int getType();

        boolean isNull();
    }

    public static class ListItem extends ArrayList<Item> implements Item {
        private ListItem() {
        }

        @Override // org.jetbrains.kotlin.config.MavenComparableVersion.Item
        public int compareTo(Item item) {
            int iCompareTo;
            if (item == null) {
                if (size() == 0) {
                    return 0;
                }
                return get(0).compareTo(null);
            }
            int type = item.getType();
            if (type == 0) {
                return -1;
            }
            if (type == 1) {
                return 1;
            }
            if (type != 2) {
                ib0.a("invalid item: ", item.getClass());
                return 0;
            }
            Iterator<Item> it = iterator();
            Iterator<Item> it2 = ((ListItem) item).iterator();
            do {
                if (!it.hasNext() && !it2.hasNext()) {
                    return 0;
                }
                Item next = it.hasNext() ? it.next() : null;
                Item next2 = it2.hasNext() ? it2.next() : null;
                if (next == null) {
                    iCompareTo = next2 == null ? 0 : next2.compareTo(next) * (-1);
                } else {
                    iCompareTo = next.compareTo(next2);
                }
            } while (iCompareTo == 0);
            return iCompareTo;
        }

        @Override // org.jetbrains.kotlin.config.MavenComparableVersion.Item
        public int getType() {
            return 2;
        }

        @Override // org.jetbrains.kotlin.config.MavenComparableVersion.Item
        public boolean isNull() {
            return size() == 0;
        }

        public void normalize() {
            for (int size = size() - 1; size >= 0; size--) {
                Item item = get(size);
                if (item.isNull()) {
                    remove(size);
                } else if (!(item instanceof ListItem)) {
                    return;
                }
            }
        }

        @Override // java.util.AbstractCollection
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (Item item : this) {
                if (sb.length() > 0) {
                    sb.append(item instanceof ListItem ? '-' : '.');
                }
                sb.append(item);
            }
            return sb.toString();
        }
    }

    public static class StringItem implements Item {
        private static final Properties ALIASES;
        private static final String[] QUALIFIERS;
        private static final String RELEASE_VERSION_INDEX;
        private static final List<String> _QUALIFIERS;
        private String value;

        static {
            String[] strArr = {"alpha", "beta", "milestone", "rc", "snapshot", Argument.Delimiters.none, "sp"};
            QUALIFIERS = strArr;
            List<String> listAsList = Arrays.asList(strArr);
            _QUALIFIERS = listAsList;
            Properties properties = new Properties();
            ALIASES = properties;
            properties.put("ga", Argument.Delimiters.none);
            properties.put("final", Argument.Delimiters.none);
            properties.put("cr", "rc");
            RELEASE_VERSION_INDEX = String.valueOf(listAsList.indexOf(Argument.Delimiters.none));
        }

        public StringItem(String str, boolean z) {
            if (z && str.length() == 1) {
                char cCharAt = str.charAt(0);
                if (cCharAt == 'a') {
                    str = "alpha";
                } else if (cCharAt == 'b') {
                    str = "beta";
                } else if (cCharAt == 'm') {
                    str = "milestone";
                }
            }
            this.value = ALIASES.getProperty(str, str);
        }

        public static String comparableQualifier(String str) {
            List<String> list = _QUALIFIERS;
            int iIndexOf = list.indexOf(str);
            if (iIndexOf != -1) {
                return String.valueOf(iIndexOf);
            }
            return list.size() + "-" + str;
        }

        @Override // org.jetbrains.kotlin.config.MavenComparableVersion.Item
        public int compareTo(Item item) {
            if (item == null) {
                return comparableQualifier(this.value).compareTo(RELEASE_VERSION_INDEX);
            }
            int type = item.getType();
            if (type == 0) {
                return -1;
            }
            if (type == 1) {
                return comparableQualifier(this.value).compareTo(comparableQualifier(((StringItem) item).value));
            }
            if (type == 2) {
                return -1;
            }
            ib0.a("invalid item: ", item.getClass());
            return 0;
        }

        @Override // org.jetbrains.kotlin.config.MavenComparableVersion.Item
        public int getType() {
            return 1;
        }

        @Override // org.jetbrains.kotlin.config.MavenComparableVersion.Item
        public boolean isNull() {
            return comparableQualifier(this.value).compareTo(RELEASE_VERSION_INDEX) == 0;
        }

        public String toString() {
            return this.value;
        }
    }

    public MavenComparableVersion(String str) {
        parseVersion(str);
    }

    public static void main(String... strArr) {
        String str;
        System.out.println("Display parameters as parsed by Maven (in canonical form) and comparison result:");
        if (strArr.length == 0) {
            return;
        }
        int length = strArr.length;
        int i = 1;
        MavenComparableVersion mavenComparableVersion = null;
        int i2 = 0;
        while (i2 < length) {
            String str2 = strArr[i2];
            MavenComparableVersion mavenComparableVersion2 = new MavenComparableVersion(str2);
            if (mavenComparableVersion != null) {
                int iCompareTo = mavenComparableVersion.compareTo(mavenComparableVersion2);
                PrintStream printStream = System.out;
                StringBuilder sb = new StringBuilder("   ");
                sb.append(mavenComparableVersion.toString());
                sb.append(' ');
                if (iCompareTo == 0) {
                    str = "==";
                } else {
                    str = iCompareTo < 0 ? "<" : ">";
                }
                sb.append(str);
                sb.append(' ');
                sb.append(str2);
                printStream.println(sb.toString());
            }
            System.out.println(String.valueOf(i) + ". " + str2 + " == " + mavenComparableVersion2.getCanonical());
            i2++;
            mavenComparableVersion = mavenComparableVersion2;
            i++;
        }
    }

    private static Item parseItem(boolean z, String str) {
        return z ? new IntegerItem(str) : new StringItem(str, false);
    }

    @Override // java.lang.Comparable
    public int compareTo(MavenComparableVersion mavenComparableVersion) {
        return this.items.compareTo(mavenComparableVersion.items);
    }

    public boolean equals(Object obj) {
        return (obj instanceof MavenComparableVersion) && this.canonical.equals(((MavenComparableVersion) obj).canonical);
    }

    public String getCanonical() {
        return this.canonical;
    }

    public int hashCode() {
        return this.canonical.hashCode();
    }

    public final void parseVersion(String str) {
        this.value = str;
        this.items = new ListItem();
        String lowerCase = str.toLowerCase(Locale.ENGLISH);
        ListItem listItem = this.items;
        Stack stack = new Stack();
        stack.push(listItem);
        int i = 0;
        boolean z = false;
        for (int i2 = 0; i2 < lowerCase.length(); i2++) {
            char cCharAt = lowerCase.charAt(i2);
            if (cCharAt == '.') {
                if (i2 == i) {
                    listItem.add(IntegerItem.ZERO);
                } else {
                    listItem.add(parseItem(z, lowerCase.substring(i, i2)));
                }
                i = i2 + 1;
            } else if (cCharAt == '-') {
                if (i2 == i) {
                    listItem.add(IntegerItem.ZERO);
                } else {
                    listItem.add(parseItem(z, lowerCase.substring(i, i2)));
                }
                i = i2 + 1;
                ListItem listItem2 = new ListItem();
                listItem.add(listItem2);
                stack.push(listItem2);
                listItem = listItem2;
            } else if (Character.isDigit(cCharAt)) {
                if (!z && i2 > i) {
                    listItem.add(new StringItem(lowerCase.substring(i, i2), true));
                    ListItem listItem3 = new ListItem();
                    listItem.add(listItem3);
                    stack.push(listItem3);
                    listItem = listItem3;
                    i = i2;
                }
                z = true;
            } else {
                if (z && i2 > i) {
                    listItem.add(parseItem(true, lowerCase.substring(i, i2)));
                    ListItem listItem4 = new ListItem();
                    listItem.add(listItem4);
                    stack.push(listItem4);
                    listItem = listItem4;
                    i = i2;
                }
                z = false;
            }
        }
        if (lowerCase.length() > i) {
            listItem.add(parseItem(z, lowerCase.substring(i)));
        }
        while (!stack.isEmpty()) {
            ((ListItem) stack.pop()).normalize();
        }
        this.canonical = this.items.toString();
    }

    public String toString() {
        return this.value;
    }

    public static class IntegerItem implements Item {
        private static final BigInteger BIG_INTEGER_ZERO = new BigInteger("0");
        public static final IntegerItem ZERO = new IntegerItem();
        private final BigInteger value;

        public IntegerItem(String str) {
            this.value = new BigInteger(str);
        }

        @Override // org.jetbrains.kotlin.config.MavenComparableVersion.Item
        public int compareTo(Item item) {
            if (item == null) {
                return !BIG_INTEGER_ZERO.equals(this.value) ? 1 : 0;
            }
            int type = item.getType();
            if (type == 0) {
                return this.value.compareTo(((IntegerItem) item).value);
            }
            if (type == 1 || type == 2) {
                return 1;
            }
            ib0.a("invalid item: ", item.getClass());
            return 0;
        }

        @Override // org.jetbrains.kotlin.config.MavenComparableVersion.Item
        public int getType() {
            return 0;
        }

        @Override // org.jetbrains.kotlin.config.MavenComparableVersion.Item
        public boolean isNull() {
            return BIG_INTEGER_ZERO.equals(this.value);
        }

        public String toString() {
            return this.value.toString();
        }

        private IntegerItem() {
            this.value = BIG_INTEGER_ZERO;
        }
    }
}
