package com.intellij.util.text;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.text.CharSequenceWithStringHash;
import com.intellij.openapi.util.text.Strings;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ImmutableText extends ImmutableCharSequence implements CharSequenceWithStringHash, CharArrayExternalizable {
    private static final ImmutableText EMPTY = new ImmutableText("");
    private transient int hash;
    private InnerLeaf myLastLeaf;
    public final CharSequence myNode;

    public static final class CompositeNode implements CharSequence {
        final int count;
        public final CharSequence head;
        public final CharSequence tail;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = (i == 2 || i == 3 || i == 5 || i == 6 || i == 7 || i == 8) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
            Object[] objArr = new Object[(i == 2 || i == 3 || i == 5 || i == 6 || i == 7 || i == 8) ? 2 : 3];
            switch (i) {
                case 1:
                    objArr[0] = "tail";
                    break;
                case 2:
                case 3:
                case 5:
                case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                case 8:
                    objArr[0] = "com/intellij/util/text/ImmutableText$CompositeNode";
                    break;
                case 4:
                    objArr[0] = "dest";
                    break;
                default:
                    objArr[0] = "head";
                    break;
            }
            if (i == 2) {
                objArr[1] = "rightRotation";
            } else if (i == 3) {
                objArr[1] = "leftRotation";
            } else if (i == 5 || i == 6 || i == 7 || i == 8) {
                objArr[1] = "subSequence";
            } else {
                objArr[1] = "com/intellij/util/text/ImmutableText$CompositeNode";
            }
            switch (i) {
                case 2:
                case 3:
                case 5:
                case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                case 8:
                    break;
                case 4:
                    objArr[2] = "getChars";
                    break;
                default:
                    objArr[2] = "<init>";
                    break;
            }
            String str2 = String.format(str, objArr);
            if (i != 2 && i != 3 && i != 5 && i != 6 && i != 7 && i != 8) {
                throw new IllegalArgumentException(str2);
            }
            throw new IllegalStateException(str2);
        }

        public CompositeNode(CharSequence charSequence, CharSequence charSequence2) {
            if (charSequence == null) {
                $$$reportNull$$$0(0);
            }
            if (charSequence2 == null) {
                $$$reportNull$$$0(1);
            }
            this.count = charSequence.length() + charSequence2.length();
            this.head = charSequence;
            this.tail = charSequence2;
        }

        @Override // java.lang.CharSequence
        public char charAt(int i) {
            CharSequence charSequence;
            int length = this.head.length();
            if (i < length) {
                charSequence = this.head;
            } else {
                charSequence = this.tail;
                i -= length;
            }
            return charSequence.charAt(i);
        }

        public void getChars(int i, int i2, char[] cArr, int i3) {
            if (cArr == null) {
                $$$reportNull$$$0(4);
            }
            int length = this.head.length();
            if (i2 <= length) {
                ImmutableText.getChars(this.head, i, i2, cArr, i3);
            } else if (i >= length) {
                ImmutableText.getChars(this.tail, i - length, i2 - length, cArr, i3);
            } else {
                ImmutableText.getChars(this.head, i, length, cArr, i3);
                ImmutableText.getChars(this.tail, 0, i2 - length, cArr, (i3 + length) - i);
            }
        }

        public CompositeNode leftRotation() {
            CharSequence charSequence = this.tail;
            if (!(charSequence instanceof CompositeNode)) {
                return this;
            }
            CompositeNode compositeNode = (CompositeNode) charSequence;
            CharSequence charSequence2 = compositeNode.head;
            return new CompositeNode(new CompositeNode(this.head, charSequence2), compositeNode.tail);
        }

        @Override // java.lang.CharSequence
        public int length() {
            return this.count;
        }

        public CompositeNode rightRotation() {
            CharSequence charSequence = this.head;
            if (!(charSequence instanceof CompositeNode)) {
                return this;
            }
            CompositeNode compositeNode = (CompositeNode) charSequence;
            return new CompositeNode(compositeNode.head, new CompositeNode(compositeNode.tail, this.tail));
        }

        @Override // java.lang.CharSequence
        public CharSequence subSequence(int i, int i2) {
            int length = this.head.length();
            if (i2 <= length) {
                CharSequence charSequenceSubSequence = this.head.subSequence(i, i2);
                if (charSequenceSubSequence == null) {
                    $$$reportNull$$$0(5);
                }
                return charSequenceSubSequence;
            }
            if (i >= length) {
                CharSequence charSequenceSubSequence2 = this.tail.subSequence(i - length, i2 - length);
                if (charSequenceSubSequence2 == null) {
                    $$$reportNull$$$0(6);
                }
                return charSequenceSubSequence2;
            }
            if (i == 0 && i2 == this.count) {
                return this;
            }
            int i3 = i2 - i;
            if (i3 < 64) {
                char[] cArr = new char[i3];
                ImmutableText.getChars(this.head, i, length, cArr, 0);
                ImmutableText.getChars(this.tail, 0, i2 - length, cArr, length - i);
                return new String(cArr);
            }
            CharSequence charSequenceConcatNodes = ImmutableText.concatNodes(this.head.subSequence(i, length), this.tail.subSequence(0, i2 - length));
            if (charSequenceConcatNodes == null) {
                $$$reportNull$$$0(8);
            }
            return charSequenceConcatNodes;
        }

        @Override // java.lang.CharSequence
        public String toString() {
            int length = length();
            char[] cArr = new char[length];
            getChars(0, length, cArr, 0);
            return new String(cArr);
        }
    }

    public static final class InnerLeaf {
        final int end;
        final CharSequence leafNode;
        final int start;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "leafNode", "com/intellij/util/text/ImmutableText$InnerLeaf", "<init>"));
        }

        private InnerLeaf(CharSequence charSequence, int i, int i2) {
            if (charSequence == null) {
                $$$reportNull$$$0(0);
            }
            this.leafNode = charSequence;
            this.start = i;
            this.end = i2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0024  */
    /* JADX WARN: Code duplicated, block: B:60:0x00d3  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        if (i != 2 && i != 7 && i != 22 && i != 25 && i != 4 && i != 5 && i != 11 && i != 12) {
            switch (i) {
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                    str = "@NotNull method %s.%s must not return null";
                    break;
                default:
                    str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                    break;
            }
        } else {
            str = "@NotNull method %s.%s must not return null";
        }
        if (i != 2 && i != 7 && i != 22 && i != 25 && i != 4 && i != 5 && i != 11 && i != 12) {
            switch (i) {
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
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
                objArr[0] = "obj";
                break;
            case 2:
            case 4:
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 22:
            case 25:
                objArr[0] = "com/intellij/util/text/ImmutableText";
                break;
            case 3:
                objArr[0] = "str";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            default:
                objArr[0] = "node";
                break;
            case 8:
                objArr[0] = "that";
                break;
            case 9:
                objArr[0] = "sequence";
                break;
            case 10:
            case 13:
                objArr[0] = "seq";
                break;
            case 19:
            case 21:
                objArr[0] = "dest";
                break;
            case 20:
                objArr[0] = "cs";
                break;
            case 23:
                objArr[0] = "node1";
                break;
            case 24:
                objArr[0] = "node2";
                break;
            case 26:
                objArr[0] = "shorter";
                break;
            case 27:
                objArr[0] = "longer";
                break;
        }
        if (i == 2) {
            objArr[1] = "valueOf";
        } else if (i == 7) {
            objArr[1] = "nodeOf";
        } else if (i == 22) {
            objArr[1] = "toString";
        } else if (i == 25) {
            objArr[1] = "concatNodes";
        } else if (i == 4) {
            objArr[1] = "valueOf";
        } else if (i == 5) {
            objArr[1] = "ensureChunked";
        } else if (i != 11 && i != 12) {
            switch (i) {
                case 14:
                    objArr[1] = "insert";
                    break;
                case 15:
                    objArr[1] = "delete";
                    break;
                case 16:
                    objArr[1] = "subSequence";
                    break;
                case 17:
                case 18:
                    objArr[1] = "subtext";
                    break;
                default:
                    objArr[1] = "com/intellij/util/text/ImmutableText";
                    break;
            }
        } else {
            objArr[1] = "replace";
        }
        switch (i) {
            case 1:
            case 3:
                objArr[2] = "valueOf";
                break;
            case 2:
            case 4:
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 11:
            case 12:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 22:
            case 25:
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "nodeOf";
                break;
            case 8:
            case 9:
                objArr[2] = "concat";
                break;
            case 10:
                objArr[2] = "replace";
                break;
            case 13:
                objArr[2] = "insert";
                break;
            case 19:
            case 20:
            case 21:
                objArr[2] = "getChars";
                break;
            case 23:
            case 24:
                objArr[2] = "concatNodes";
                break;
            case 26:
            case 27:
                objArr[2] = "shouldRebalance";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 7 && i != 22 && i != 25 && i != 4 && i != 5 && i != 11 && i != 12) {
            switch (i) {
                case 14:
                case 15:
                case 16:
                case 17:
                case 18:
                    break;
                default:
                    throw new IllegalArgumentException(str2);
            }
        }
        throw new IllegalStateException(str2);
    }

    private ImmutableText(CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(0);
        }
        this.myNode = charSequence;
    }

    private ImmutableText concat(ImmutableText immutableText) {
        if (immutableText == null) {
            $$$reportNull$$$0(8);
        }
        if (immutableText.length() == 0) {
            return this;
        }
        return length() == 0 ? immutableText : new ImmutableText(concatNodes(ensureChunked(), immutableText.ensureChunked()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CharSequence concatNodes(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null) {
            $$$reportNull$$$0(23);
        }
        if (charSequence2 == null) {
            $$$reportNull$$$0(24);
        }
        if (charSequence.length() + charSequence2.length() <= 64) {
            return charSequence.toString().concat(charSequence2.toString());
        }
        if (shouldRebalance(charSequence, charSequence2)) {
            do {
                CompositeNode compositeNode = (CompositeNode) charSequence2;
                if (compositeNode.head.length() > compositeNode.tail.length()) {
                    charSequence2 = compositeNode.rightRotation();
                }
                CompositeNode compositeNode2 = (CompositeNode) charSequence2;
                charSequence = concatNodes(charSequence, compositeNode2.head);
                charSequence2 = compositeNode2.tail;
            } while (shouldRebalance(charSequence, charSequence2));
        } else if (shouldRebalance(charSequence2, charSequence)) {
            do {
                CompositeNode compositeNode3 = (CompositeNode) charSequence;
                if (compositeNode3.tail.length() > compositeNode3.head.length()) {
                    charSequence = compositeNode3.leftRotation();
                }
                CompositeNode compositeNode4 = (CompositeNode) charSequence;
                charSequence2 = concatNodes(compositeNode4.tail, charSequence2);
                charSequence = compositeNode4.head;
            } while (shouldRebalance(charSequence2, charSequence));
        }
        return new CompositeNode(charSequence, charSequence2);
    }

    private CharSequence ensureChunked() {
        if (length() > 64) {
            CharSequence charSequence = this.myNode;
            if (!(charSequence instanceof CompositeNode)) {
                return nodeOf(charSequence, 0, length());
            }
        }
        CharSequence charSequence2 = this.myNode;
        if (charSequence2 == null) {
            $$$reportNull$$$0(5);
        }
        return charSequence2;
    }

    private InnerLeaf findLeaf(int i) {
        if (i < 0) {
            throw outOfRange(i);
        }
        CharSequence charSequence = this.myNode;
        int length = charSequence.length();
        int i2 = 0;
        while (i < length) {
            if (!(charSequence instanceof CompositeNode)) {
                return new InnerLeaf(charSequence, i2, length + i2);
            }
            CompositeNode compositeNode = (CompositeNode) charSequence;
            int length2 = compositeNode.head.length();
            if (i < length2) {
                charSequence = compositeNode.head;
                length = length2;
            } else {
                i2 += length2;
                i -= length2;
                charSequence = compositeNode.tail;
                length -= length2;
            }
        }
        throw outOfRange(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static void getChars(CharSequence charSequence, int i, int i2, char[] cArr, int i3) {
        if (charSequence == 0) {
            $$$reportNull$$$0(20);
        }
        if (cArr == null) {
            $$$reportNull$$$0(21);
        }
        if (charSequence instanceof String) {
            ((String) charSequence).getChars(i, i2, cArr, i3);
        } else if (charSequence instanceof ByteArrayCharSequence) {
            ((ByteArrayCharSequence) charSequence).getChars(i, i2, cArr, i3);
        } else {
            ((CompositeNode) charSequence).getChars(i, i2, cArr, i3);
        }
    }

    private static CharSequence nodeOf(CharSequence charSequence, int i, int i2) {
        if (charSequence == null) {
            $$$reportNull$$$0(6);
        }
        if (i2 > 64) {
            int i3 = ((i2 + 64) >> 1) & (-64);
            return new CompositeNode(nodeOf(charSequence, i, i3), nodeOf(charSequence, i + i3, i2 - i3));
        }
        String string = charSequence.subSequence(i, i2 + i).toString();
        if (string == null) {
            $$$reportNull$$$0(7);
        }
        return string;
    }

    private IndexOutOfBoundsException outOfRange(int i) {
        return new IndexOutOfBoundsException("Index out of range: " + i + "; length: " + length());
    }

    private static boolean shouldRebalance(CharSequence charSequence, CharSequence charSequence2) {
        if (charSequence == null) {
            $$$reportNull$$$0(26);
        }
        if (charSequence2 == null) {
            $$$reportNull$$$0(27);
        }
        return (charSequence.length() << 1) < charSequence2.length() && (charSequence2 instanceof CompositeNode);
    }

    private static ImmutableText valueOf(CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(3);
        }
        if (charSequence instanceof ByteArrayCharSequence) {
            return new ImmutableText(charSequence);
        }
        if (charSequence.length() != 0) {
            return new ImmutableText(charSequence.toString());
        }
        ImmutableText immutableText = EMPTY;
        if (immutableText == null) {
            $$$reportNull$$$0(4);
        }
        return immutableText;
    }

    @Override // java.lang.CharSequence
    public char charAt(int i) {
        InnerLeaf innerLeafFindLeaf = this.myLastLeaf;
        if (innerLeafFindLeaf == null || i < innerLeafFindLeaf.start || i >= innerLeafFindLeaf.end) {
            innerLeafFindLeaf = findLeaf(i);
            this.myLastLeaf = innerLeafFindLeaf;
        }
        return innerLeafFindLeaf.leafNode.charAt(i - innerLeafFindLeaf.start);
    }

    @Override // com.intellij.util.text.ImmutableCharSequence
    public ImmutableText delete(int i, int i2) {
        if (i == i2) {
            return this;
        }
        if (i <= i2) {
            return subtext(0, i).concat(subtext(i2));
        }
        qc6.a();
        return null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ImmutableText)) {
            return false;
        }
        ImmutableText immutableText = (ImmutableText) obj;
        return immutableText.length() == length() && CharArrayUtil.regionMatches(this, 0, immutableText);
    }

    public int hashCode() {
        int i = this.hash;
        if (i != 0) {
            return i;
        }
        int iStringHashCode = Strings.stringHashCode(this, 0, length());
        this.hash = iStringHashCode;
        return iStringHashCode;
    }

    @Override // com.intellij.util.text.ImmutableCharSequence
    public ImmutableText insert(int i, CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(13);
        }
        return charSequence.length() == 0 ? this : subtext(0, i).concat(valueOf(charSequence)).concat(subtext(i));
    }

    @Override // java.lang.CharSequence
    public int length() {
        return this.myNode.length();
    }

    @Override // com.intellij.util.text.ImmutableCharSequence
    public ImmutableCharSequence replace(int i, int i2, CharSequence charSequence) {
        if (charSequence == null) {
            $$$reportNull$$$0(10);
        }
        if (i == i2) {
            ImmutableText immutableTextInsert = insert(i, charSequence);
            if (immutableTextInsert == null) {
                $$$reportNull$$$0(11);
            }
            return immutableTextInsert;
        }
        if (charSequence.length() == 0) {
            ImmutableText immutableTextDelete = delete(i, i2);
            if (immutableTextDelete == null) {
                $$$reportNull$$$0(12);
            }
            return immutableTextDelete;
        }
        if (i <= i2) {
            return subtext(0, i).concat(valueOf(charSequence)).concat(subtext(i2));
        }
        qc6.a();
        return null;
    }

    @Override // java.lang.CharSequence
    public CharSequence subSequence(int i, int i2) {
        return (i == 0 && i2 == length()) ? this : new CharSequenceSubSequence(this, i, i2);
    }

    @Override // com.intellij.util.text.ImmutableCharSequence
    public ImmutableText subtext(int i, int i2) {
        if (i < 0 || i > i2 || i2 > length()) {
            qc6.a();
            return null;
        }
        if (i == 0 && i2 == length()) {
            return this;
        }
        if (i != i2) {
            return new ImmutableText(this.myNode.subSequence(i, i2));
        }
        ImmutableText immutableText = EMPTY;
        if (immutableText == null) {
            $$$reportNull$$$0(18);
        }
        return immutableText;
    }

    @Override // com.intellij.util.text.ImmutableCharSequence, java.lang.CharSequence
    public String toString() {
        String string = this.myNode.toString();
        if (string == null) {
            $$$reportNull$$$0(22);
        }
        return string;
    }

    @Override // com.intellij.util.text.CharArrayExternalizable
    public void getChars(int i, int i2, char[] cArr, int i3) {
        if (cArr == null) {
            $$$reportNull$$$0(19);
        }
        getChars(this.myNode, i, i2, cArr, i3);
    }

    public static ImmutableText valueOf(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(1);
        }
        if (obj instanceof ImmutableText) {
            return (ImmutableText) obj;
        }
        return obj instanceof CharSequence ? valueOf((CharSequence) obj) : valueOf((CharSequence) String.valueOf(obj));
    }

    private ImmutableText subtext(int i) {
        return subtext(i, length());
    }
}
