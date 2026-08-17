package com.android.apksig.internal.asn1;

import com.android.apksig.internal.asn1.ber.BerDataValue;
import com.android.apksig.internal.asn1.ber.BerDataValueFormatException;
import com.android.apksig.internal.asn1.ber.BerDataValueReader;
import com.android.apksig.internal.asn1.ber.BerEncoding;
import com.android.apksig.internal.asn1.ber.ByteBufferBerDataValueReader;
import com.android.apksig.internal.util.ByteBufferUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class Asn1BerParser {

    /* JADX INFO: renamed from: com.android.apksig.internal.asn1.Asn1BerParser$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$apksig$internal$asn1$Asn1Type;

        static {
            int[] iArr = new int[Asn1Type.values().length];
            $SwitchMap$com$android$apksig$internal$asn1$Asn1Type = iArr;
            try {
                iArr[Asn1Type.CHOICE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$android$apksig$internal$asn1$Asn1Type[Asn1Type.SEQUENCE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$android$apksig$internal$asn1$Asn1Type[Asn1Type.UNENCODED_CONTAINER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$android$apksig$internal$asn1$Asn1Type[Asn1Type.SET_OF.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$android$apksig$internal$asn1$Asn1Type[Asn1Type.SEQUENCE_OF.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$android$apksig$internal$asn1$Asn1Type[Asn1Type.INTEGER.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$android$apksig$internal$asn1$Asn1Type[Asn1Type.OBJECT_IDENTIFIER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$android$apksig$internal$asn1$Asn1Type[Asn1Type.UTC_TIME.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$android$apksig$internal$asn1$Asn1Type[Asn1Type.GENERALIZED_TIME.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$android$apksig$internal$asn1$Asn1Type[Asn1Type.BOOLEAN.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public static final class AnnotatedField {
        private final Asn1Field mAnnotation;
        private final int mBerTagClass;
        private final int mBerTagNumber;
        private final Asn1Type mDataType;
        private final Field mField;
        private final boolean mOptional;
        private final Asn1TagClass mTagClass;
        private final Asn1Tagging mTagging;

        public AnnotatedField(Field field, Asn1Field asn1Field) throws Asn1DecodingException {
            this.mField = field;
            this.mAnnotation = asn1Field;
            Asn1Type asn1TypeType = asn1Field.type();
            this.mDataType = asn1TypeType;
            Asn1TagClass asn1TagClassCls = asn1Field.cls();
            asn1TagClassCls = asn1TagClassCls == Asn1TagClass.AUTOMATIC ? asn1Field.tagNumber() != -1 ? Asn1TagClass.CONTEXT_SPECIFIC : Asn1TagClass.UNIVERSAL : asn1TagClassCls;
            this.mTagClass = asn1TagClassCls;
            this.mBerTagClass = BerEncoding.getTagClass(asn1TagClassCls);
            this.mBerTagNumber = asn1Field.tagNumber() != -1 ? asn1Field.tagNumber() : (asn1TypeType == Asn1Type.CHOICE || asn1TypeType == Asn1Type.ANY) ? -1 : BerEncoding.getTagNumber(asn1TypeType);
            Asn1Tagging asn1TaggingTagging = asn1Field.tagging();
            this.mTagging = asn1TaggingTagging;
            if ((asn1TaggingTagging != Asn1Tagging.EXPLICIT && asn1TaggingTagging != Asn1Tagging.IMPLICIT) || asn1Field.tagNumber() != -1) {
                this.mOptional = asn1Field.optional();
            } else {
                throw new Asn1DecodingException("Tag number must be specified when tagging mode is " + asn1TaggingTagging);
            }
        }

        public Asn1Field getAnnotation() {
            return this.mAnnotation;
        }

        public int getBerTagClass() {
            return this.mBerTagClass;
        }

        public int getBerTagNumber() {
            return this.mBerTagNumber;
        }

        public Field getField() {
            return this.mField;
        }

        public boolean isOptional() {
            return this.mOptional;
        }

        public void setValueFrom(BerDataValue berDataValue, Object obj) throws Asn1DecodingException {
            int tagClass = berDataValue.getTagClass();
            if (this.mBerTagNumber != -1) {
                int tagNumber = berDataValue.getTagNumber();
                if (tagClass != this.mBerTagClass || tagNumber != this.mBerTagNumber) {
                    throw new Asn1UnexpectedTagException("Tag mismatch. Expected: " + BerEncoding.tagClassAndNumberToString(this.mBerTagClass, this.mBerTagNumber) + ", but found " + BerEncoding.tagClassAndNumberToString(tagClass, tagNumber));
                }
            } else if (tagClass != this.mBerTagClass) {
                throw new Asn1UnexpectedTagException("Tag mismatch. Expected class: " + BerEncoding.tagClassToString(this.mBerTagClass) + ", but found " + BerEncoding.tagClassToString(tagClass));
            }
            if (this.mTagging == Asn1Tagging.EXPLICIT) {
                try {
                    berDataValue = berDataValue.contentsReader().readDataValue();
                } catch (BerDataValueFormatException e) {
                    throw new Asn1DecodingException("Failed to read contents of EXPLICIT data value", e);
                }
            }
            BerToJavaConverter.setFieldValue(obj, this.mField, this.mDataType, berDataValue);
        }
    }

    public static class Asn1UnexpectedTagException extends Asn1DecodingException {
        private static final long serialVersionUID = 1;

        public Asn1UnexpectedTagException(String str) {
            super(str);
        }
    }

    public static final class BerToJavaConverter {
        private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

        private BerToJavaConverter() {
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r6v23, types: [T, byte[]] */
        public static <T> T convert(Asn1Type asn1Type, BerDataValue berDataValue, Class<T> cls) throws Asn1DecodingException {
            if (ByteBuffer.class.equals(cls)) {
                return (T) berDataValue.getEncodedContents();
            }
            if (byte[].class.equals(cls)) {
                ByteBuffer encodedContents = berDataValue.getEncodedContents();
                if (!encodedContents.hasRemaining()) {
                    return (T) EMPTY_BYTE_ARRAY;
                }
                ?? r6 = (T) new byte[encodedContents.remaining()];
                encodedContents.get((byte[]) r6);
                return r6;
            }
            if (Asn1OpaqueObject.class.equals(cls)) {
                return (T) new Asn1OpaqueObject(berDataValue.getEncoded());
            }
            ByteBuffer encodedContents2 = berDataValue.getEncodedContents();
            int i = AnonymousClass1.$SwitchMap$com$android$apksig$internal$asn1$Asn1Type[asn1Type.ordinal()];
            if (i == 1) {
                Asn1Class asn1Class = (Asn1Class) cls.getDeclaredAnnotation(Asn1Class.class);
                if (asn1Class != null && asn1Class.type() == Asn1Type.CHOICE) {
                    return (T) Asn1BerParser.parseChoice(berDataValue, cls);
                }
            } else if (i != 2) {
                switch (i) {
                    case 6:
                        if (Integer.TYPE.equals(cls) || Integer.class.equals(cls)) {
                            return (T) Integer.valueOf(Asn1BerParser.integerToInt(encodedContents2));
                        }
                        if (Long.TYPE.equals(cls) || Long.class.equals(cls)) {
                            return (T) Long.valueOf(Asn1BerParser.integerToLong(encodedContents2));
                        }
                        if (BigInteger.class.equals(cls)) {
                            return (T) Asn1BerParser.integerToBigInteger(encodedContents2);
                        }
                        break;
                    case 7:
                        if (String.class.equals(cls)) {
                            return (T) Asn1BerParser.oidToString(encodedContents2);
                        }
                        break;
                    case 8:
                    case 9:
                        if (String.class.equals(cls)) {
                            return (T) new String(ByteBufferUtils.toByteArray(encodedContents2));
                        }
                        break;
                    case 10:
                        if (Boolean.TYPE.equals(cls)) {
                            if (encodedContents2.remaining() == 1) {
                                return (T) new Boolean(encodedContents2.get() != 0);
                            }
                            throw new Asn1DecodingException("Incorrect encoded size of boolean value: " + encodedContents2.remaining());
                        }
                        break;
                }
            } else {
                Asn1Class asn1Class2 = (Asn1Class) cls.getDeclaredAnnotation(Asn1Class.class);
                if (asn1Class2 != null && asn1Class2.type() == Asn1Type.SEQUENCE) {
                    return (T) Asn1BerParser.parseSequence(berDataValue, cls);
                }
            }
            StringBuilder sb = new StringBuilder("Unsupported conversion: ASN.1 ");
            sb.append(asn1Type);
            String name = cls.getName();
            sb.append(" to ");
            sb.append(name);
            throw new Asn1DecodingException(sb.toString());
        }

        public static void setFieldValue(Object obj, Field field, Asn1Type asn1Type, BerDataValue berDataValue) throws Asn1DecodingException {
            try {
                int i = AnonymousClass1.$SwitchMap$com$android$apksig$internal$asn1$Asn1Type[asn1Type.ordinal()];
                if (i != 4 && i != 5) {
                    field.set(obj, convert(asn1Type, berDataValue, field.getType()));
                } else if (Asn1OpaqueObject.class.equals(field.getType())) {
                    field.set(obj, convert(asn1Type, berDataValue, field.getType()));
                } else {
                    field.set(obj, Asn1BerParser.parseSetOf(berDataValue, Asn1BerParser.getElementType(field)));
                }
            } catch (ReflectiveOperationException e) {
                throw new Asn1DecodingException("Failed to set value of " + obj.getClass().getName() + "." + field.getName(), e);
            }
        }
    }

    private Asn1BerParser() {
    }

    public static /* synthetic */ int a(AnnotatedField annotatedField, AnnotatedField annotatedField2) {
        return annotatedField.getAnnotation().index() - annotatedField2.getAnnotation().index();
    }

    private static long decodeBase128UnsignedLong(ByteBuffer byteBuffer) throws Asn1DecodingException {
        long j = 0;
        if (!byteBuffer.hasRemaining()) {
            return 0L;
        }
        while (byteBuffer.hasRemaining()) {
            if (j > 72057594037927935L) {
                throw new Asn1DecodingException("Base-128 number too large");
            }
            byte b = byteBuffer.get();
            j = (j << 7) | ((long) (b & 127));
            if ((b & 128) == 0) {
                return j;
            }
        }
        throw new Asn1DecodingException("Truncated base-128 encoded input: missing terminating byte, with highest bit not set");
    }

    private static List<AnnotatedField> getAnnotatedFields(Class<?> cls) throws Asn1DecodingException {
        Field[] declaredFields = cls.getDeclaredFields();
        ArrayList arrayList = new ArrayList(declaredFields.length);
        for (Field field : declaredFields) {
            Asn1Field asn1Field = (Asn1Field) field.getDeclaredAnnotation(Asn1Field.class);
            if (asn1Field != null) {
                if (Modifier.isStatic(field.getModifiers())) {
                    throw new Asn1DecodingException(Asn1Field.class.getName() + " used on a static field: " + cls.getName() + "." + field.getName());
                }
                try {
                    arrayList.add(new AnnotatedField(field, asn1Field));
                } catch (Asn1DecodingException e) {
                    throw new Asn1DecodingException("Invalid ASN.1 annotation on " + cls.getName() + "." + field.getName(), e);
                }
            }
        }
        return arrayList;
    }

    private static Asn1Type getContainerAsn1Type(Class<?> cls) throws Asn1DecodingException {
        Asn1Class asn1Class = (Asn1Class) cls.getDeclaredAnnotation(Asn1Class.class);
        if (asn1Class == null) {
            throw new Asn1DecodingException(cls.getName() + " is not annotated with " + Asn1Class.class.getName());
        }
        int i = AnonymousClass1.$SwitchMap$com$android$apksig$internal$asn1$Asn1Type[asn1Class.type().ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return asn1Class.type();
        }
        throw new Asn1DecodingException("Unsupported ASN.1 container annotation type: " + asn1Class.type());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Class<?> getElementType(Field field) throws ClassNotFoundException, Asn1DecodingException {
        String typeName = field.getGenericType().getTypeName();
        int iIndexOf = typeName.indexOf(60);
        if (iIndexOf == -1) {
            throw new Asn1DecodingException("Not a container type: " + field.getGenericType());
        }
        int i = iIndexOf + 1;
        int iIndexOf2 = typeName.indexOf(62, i);
        if (iIndexOf2 != -1) {
            return Class.forName(typeName.substring(i, iIndexOf2));
        }
        throw new Asn1DecodingException("Not a container type: " + field.getGenericType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static BigInteger integerToBigInteger(ByteBuffer byteBuffer) {
        return !byteBuffer.hasRemaining() ? BigInteger.ZERO : new BigInteger(ByteBufferUtils.toByteArray(byteBuffer));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int integerToInt(ByteBuffer byteBuffer) throws Asn1DecodingException {
        BigInteger bigIntegerIntegerToBigInteger = integerToBigInteger(byteBuffer);
        if (bigIntegerIntegerToBigInteger.compareTo(BigInteger.valueOf(-2147483648L)) < 0 || bigIntegerIntegerToBigInteger.compareTo(BigInteger.valueOf(2147483647L)) > 0) {
            throw new Asn1DecodingException(String.format("INTEGER cannot be represented as int: %1$d (0x%1$x)", bigIntegerIntegerToBigInteger));
        }
        return bigIntegerIntegerToBigInteger.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long integerToLong(ByteBuffer byteBuffer) throws Asn1DecodingException {
        BigInteger bigIntegerIntegerToBigInteger = integerToBigInteger(byteBuffer);
        if (bigIntegerIntegerToBigInteger.compareTo(BigInteger.valueOf(Long.MIN_VALUE)) < 0 || bigIntegerIntegerToBigInteger.compareTo(BigInteger.valueOf(Long.MAX_VALUE)) > 0) {
            throw new Asn1DecodingException(String.format("INTEGER cannot be represented as long: %1$d (0x%1$x)", bigIntegerIntegerToBigInteger));
        }
        return bigIntegerIntegerToBigInteger.longValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String oidToString(ByteBuffer byteBuffer) throws Asn1DecodingException {
        if (!byteBuffer.hasRemaining()) {
            throw new Asn1DecodingException("Empty OBJECT IDENTIFIER");
        }
        long jDecodeBase128UnsignedLong = decodeBase128UnsignedLong(byteBuffer);
        int iMin = (int) Math.min(jDecodeBase128UnsignedLong / 40, 2L);
        StringBuilder sb = new StringBuilder();
        sb.append(Long.toString(iMin));
        sb.append('.');
        sb.append(Long.toString(jDecodeBase128UnsignedLong - ((long) (iMin * 40))));
        while (byteBuffer.hasRemaining()) {
            long jDecodeBase128UnsignedLong2 = decodeBase128UnsignedLong(byteBuffer);
            sb.append('.');
            sb.append(Long.toString(jDecodeBase128UnsignedLong2));
        }
        return sb.toString();
    }

    private static <T> T parse(BerDataValue berDataValue, Class<T> cls) throws Asn1DecodingException {
        if (berDataValue == null) {
            x0e.a("container == null");
            return null;
        }
        if (cls == null) {
            x0e.a("containerClass == null");
            return null;
        }
        Asn1Type containerAsn1Type = getContainerAsn1Type(cls);
        int i = AnonymousClass1.$SwitchMap$com$android$apksig$internal$asn1$Asn1Type[containerAsn1Type.ordinal()];
        if (i == 1) {
            return (T) parseChoice(berDataValue, cls);
        }
        if (i != 2) {
            if (i == 3) {
                return (T) parseSequence(berDataValue, cls, true);
            }
            throw new Asn1DecodingException("Parsing container " + containerAsn1Type + " not supported");
        }
        int tagNumber = BerEncoding.getTagNumber(containerAsn1Type);
        if (berDataValue.getTagClass() == 0 && berDataValue.getTagNumber() == tagNumber) {
            return (T) parseSequence(berDataValue, cls);
        }
        throw new Asn1UnexpectedTagException("Unexpected data value read as " + cls.getName() + ". Expected " + BerEncoding.tagClassAndNumberToString(0, tagNumber) + ", but read: " + BerEncoding.tagClassAndNumberToString(berDataValue.getTagClass(), berDataValue.getTagNumber()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T parseChoice(BerDataValue berDataValue, Class<T> cls) throws Asn1DecodingException {
        List<AnnotatedField> annotatedFields = getAnnotatedFields(cls);
        if (annotatedFields.isEmpty()) {
            throw new Asn1DecodingException("No fields annotated with " + Asn1Field.class.getName() + " in CHOICE class " + cls.getName());
        }
        int i = 0;
        while (i < annotatedFields.size() - 1) {
            AnnotatedField annotatedField = annotatedFields.get(i);
            int berTagNumber = annotatedField.getBerTagNumber();
            int berTagClass = annotatedField.getBerTagClass();
            i++;
            for (int i2 = i; i2 < annotatedFields.size(); i2++) {
                AnnotatedField annotatedField2 = annotatedFields.get(i2);
                int berTagNumber2 = annotatedField2.getBerTagNumber();
                int berTagClass2 = annotatedField2.getBerTagClass();
                if (berTagNumber == berTagNumber2 && berTagClass == berTagClass2) {
                    throw new Asn1DecodingException("CHOICE fields are indistinguishable because they have the same tag class and number: " + cls.getName() + "." + annotatedField.getField().getName() + " and ." + annotatedField2.getField().getName());
                }
            }
        }
        try {
            T tNewInstance = cls.getConstructor(null).newInstance(null);
            Iterator<AnnotatedField> it = annotatedFields.iterator();
            while (it.hasNext()) {
                try {
                    it.next().setValueFrom(berDataValue, tNewInstance);
                    return tNewInstance;
                } catch (Asn1UnexpectedTagException unused) {
                }
            }
            throw new Asn1DecodingException("No options of CHOICE " + cls.getName() + " matched");
        } catch (IllegalArgumentException | ReflectiveOperationException e) {
            throw new Asn1DecodingException("Failed to instantiate ".concat(cls.getName()), e);
        }
    }

    public static <T> List<T> parseImplicitSetOf(ByteBuffer byteBuffer, Class<T> cls) throws Asn1DecodingException {
        try {
            BerDataValue dataValue = new ByteBufferBerDataValueReader(byteBuffer).readDataValue();
            if (dataValue != null) {
                return parseSetOf(dataValue, cls);
            }
            throw new Asn1DecodingException("Empty input");
        } catch (BerDataValueFormatException e) {
            throw new Asn1DecodingException("Failed to decode top-level data value", e);
        }
    }

    private static <T> T parseSequence(BerDataValue berDataValue, Class<T> cls, boolean z) throws Asn1DecodingException {
        BerDataValue dataValue;
        List<AnnotatedField> annotatedFields = getAnnotatedFields(cls);
        Collections.sort(annotatedFields, new Comparator() { // from class: com.android.apksig.internal.asn1.a
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Asn1BerParser.a((Asn1BerParser.AnnotatedField) obj, (Asn1BerParser.AnnotatedField) obj2);
            }
        });
        if (annotatedFields.size() > 1) {
            AnnotatedField annotatedField = null;
            for (AnnotatedField annotatedField2 : annotatedFields) {
                if (annotatedField != null && annotatedField.getAnnotation().index() == annotatedField2.getAnnotation().index()) {
                    throw new Asn1DecodingException("Fields have the same index: " + cls.getName() + "." + annotatedField.getField().getName() + " and ." + annotatedField2.getField().getName());
                }
                annotatedField = annotatedField2;
            }
        }
        try {
            T tNewInstance = cls.getConstructor(null).newInstance(null);
            BerDataValueReader berDataValueReaderContentsReader = berDataValue.contentsReader();
            int i = 0;
            while (i < annotatedFields.size()) {
                if (z && i == 0) {
                    dataValue = berDataValue;
                } else {
                    try {
                        dataValue = berDataValueReaderContentsReader.readDataValue();
                    } catch (BerDataValueFormatException e) {
                        throw new Asn1DecodingException("Malformed data value", e);
                    }
                }
                if (dataValue == null) {
                    break;
                }
                for (int i2 = i; i2 < annotatedFields.size(); i2++) {
                    AnnotatedField annotatedField3 = annotatedFields.get(i2);
                    try {
                        if (annotatedField3.isOptional()) {
                            try {
                                annotatedField3.setValueFrom(dataValue, tNewInstance);
                            } catch (Asn1UnexpectedTagException unused) {
                            }
                        } else {
                            annotatedField3.setValueFrom(dataValue, tNewInstance);
                        }
                        i = i2 + 1;
                        break;
                    } catch (Asn1DecodingException e2) {
                        throw new Asn1DecodingException("Failed to parse " + cls.getName() + "." + annotatedField3.getField().getName(), e2);
                    }
                }
            }
            return tNewInstance;
        } catch (IllegalArgumentException | ReflectiveOperationException e3) {
            throw new Asn1DecodingException("Failed to instantiate ".concat(cls.getName()), e3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> List<T> parseSetOf(BerDataValue berDataValue, Class<T> cls) throws Asn1DecodingException {
        Object asn1OpaqueObject;
        ArrayList arrayList = new ArrayList();
        BerDataValueReader berDataValueReaderContentsReader = berDataValue.contentsReader();
        while (true) {
            try {
                BerDataValue dataValue = berDataValueReaderContentsReader.readDataValue();
                if (dataValue == null) {
                    return arrayList;
                }
                if (ByteBuffer.class.equals(cls)) {
                    asn1OpaqueObject = dataValue.getEncodedContents();
                } else {
                    asn1OpaqueObject = Asn1OpaqueObject.class.equals(cls) ? new Asn1OpaqueObject(dataValue.getEncoded()) : parse(dataValue, cls);
                }
                arrayList.add(asn1OpaqueObject);
            } catch (BerDataValueFormatException e) {
                throw new Asn1DecodingException("Malformed data value", e);
            }
        }
    }

    public static <T> T parse(ByteBuffer byteBuffer, Class<T> cls) throws Asn1DecodingException {
        try {
            BerDataValue dataValue = new ByteBufferBerDataValueReader(byteBuffer).readDataValue();
            if (dataValue != null) {
                return (T) parse(dataValue, cls);
            }
            throw new Asn1DecodingException("Empty input");
        } catch (BerDataValueFormatException e) {
            throw new Asn1DecodingException("Failed to decode top-level data value", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> T parseSequence(BerDataValue berDataValue, Class<T> cls) throws Asn1DecodingException {
        return (T) parseSequence(berDataValue, cls, false);
    }
}
