package com.sun.tools.javac.jvm;

import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.util.ByteBuffer;
import com.sun.tools.javac.util.InvalidUtfException;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Names;
import defpackage.s22;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class PoolWriter {
    public static final int MAX_ENTRIES = 65535;
    public static final int MAX_STRING_LENGTH = 65535;
    private static final int POOL_BUF_SIZE = 32767;
    private final Names names;
    private final Types types;
    LinkedHashSet<Symbol.ClassSymbol> innerClasses = new LinkedHashSet<>();
    Map<PoolConstant.Dynamic.BsmKey, Integer> bootstrapMethods = new LinkedHashMap();
    final SharedSignatureGenerator signatureGen = new SharedSignatureGenerator();
    final WriteablePoolHelper pool = new WriteablePoolHelper();

    /* JADX INFO: renamed from: com.sun.tools.javac.jvm.PoolWriter$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$code$TypeTag;

        static {
            int[] iArr = new int[TypeTag.values().length];
            $SwitchMap$com$sun$tools$javac$code$TypeTag = iArr;
            try {
                iArr[TypeTag.UNINITIALIZED_THIS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$code$TypeTag[TypeTag.UNINITIALIZED_OBJECT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public class WriteablePoolHelper {
        private final Map<Object, Integer> keysToPos = new HashMap(64);
        final ByteBuffer poolbuf = new ByteBuffer(32767);
        int currentIndex = 1;
        ArrayDeque<PoolConstant> todo = new ArrayDeque<>();
        String overflowString = null;

        public WriteablePoolHelper() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public <P extends PoolConstant> int writeIfNeeded(P p) {
            Object objPoolKey = p.poolKey(PoolWriter.this.types);
            Integer num = this.keysToPos.get(objPoolKey);
            if (num == null) {
                Map<Object, Integer> map = this.keysToPos;
                int i = this.currentIndex;
                this.currentIndex = i + 1;
                Integer numValueOf = Integer.valueOf(i);
                map.put(objPoolKey, numValueOf);
                boolean zIsEmpty = this.todo.isEmpty();
                this.todo.addLast(p);
                if (zIsEmpty) {
                    while (!this.todo.isEmpty()) {
                        writeConstant(this.todo.peekFirst());
                        this.todo.removeFirst();
                    }
                }
                num = numValueOf;
            }
            return num.intValue();
        }

        public void reset() {
            this.keysToPos.clear();
            this.currentIndex = 1;
            this.todo.clear();
            this.overflowString = null;
            this.poolbuf.reset();
        }

        public void writeConstant(PoolConstant poolConstant) {
            int iPoolTag = poolConstant.poolTag();
            switch (iPoolTag) {
                case 1:
                    this.poolbuf.appendByte(iPoolTag);
                    byte[] utf = ((Name) poolConstant).toUtf();
                    this.poolbuf.appendChar(utf.length);
                    this.poolbuf.appendBytes(utf, 0, utf.length);
                    if (this.overflowString == null && utf.length > 65535) {
                        this.overflowString = new String(utf);
                        break;
                    }
                    break;
                case 2:
                case 13:
                case 14:
                default:
                    ru7.a("Unexpected constant tag: ", iPoolTag);
                    break;
                case 3:
                    this.poolbuf.appendByte(iPoolTag);
                    this.poolbuf.appendInt(((Integer) ((PoolConstant.LoadableConstant.BasicConstant) poolConstant).data).intValue());
                    break;
                case 4:
                    this.poolbuf.appendByte(iPoolTag);
                    this.poolbuf.appendFloat(((Float) ((PoolConstant.LoadableConstant.BasicConstant) poolConstant).data).floatValue());
                    break;
                case 5:
                    this.currentIndex++;
                    this.poolbuf.appendByte(iPoolTag);
                    this.poolbuf.appendLong(((Long) ((PoolConstant.LoadableConstant.BasicConstant) poolConstant).data).longValue());
                    break;
                case 6:
                    this.currentIndex++;
                    this.poolbuf.appendByte(iPoolTag);
                    this.poolbuf.appendDouble(((Double) ((PoolConstant.LoadableConstant.BasicConstant) poolConstant).data).doubleValue());
                    break;
                case 7:
                    Type type = (Type) poolConstant;
                    Name nameTypeSig = type.hasTag(TypeTag.ARRAY) ? PoolWriter.this.typeSig(type) : ClassFile.externalize(type.tsym.flatName());
                    this.poolbuf.appendByte(iPoolTag);
                    this.poolbuf.appendChar(PoolWriter.this.putName(nameTypeSig));
                    if (type.hasTag(TypeTag.CLASS)) {
                        PoolWriter.this.enterInner((Symbol.ClassSymbol) type.tsym);
                    }
                    break;
                case 8:
                    Name nameFromString = PoolWriter.this.names.fromString((String) ((PoolConstant.LoadableConstant.BasicConstant) poolConstant).data);
                    this.poolbuf.appendByte(iPoolTag);
                    this.poolbuf.appendChar(PoolWriter.this.putName(nameFromString));
                    break;
                case 9:
                case 10:
                case 11:
                    Symbol symbol = (Symbol) poolConstant;
                    this.poolbuf.appendByte(iPoolTag);
                    this.poolbuf.appendChar(PoolWriter.this.putClass((Symbol.ClassSymbol) symbol.owner));
                    this.poolbuf.appendChar(PoolWriter.this.putNameAndType(symbol));
                    break;
                case 12:
                    PoolConstant.NameAndType nameAndType = (PoolConstant.NameAndType) poolConstant;
                    this.poolbuf.appendByte(iPoolTag);
                    this.poolbuf.appendChar(PoolWriter.this.putName(nameAndType.name));
                    this.poolbuf.appendChar(PoolWriter.this.putDescriptor(nameAndType.type));
                    break;
                case 15:
                    Symbol.MethodHandleSymbol methodHandleSymbol = (Symbol.MethodHandleSymbol) poolConstant;
                    this.poolbuf.appendByte(iPoolTag);
                    this.poolbuf.appendByte(methodHandleSymbol.referenceKind());
                    this.poolbuf.appendChar(PoolWriter.this.putMember(methodHandleSymbol.baseSymbol()));
                    break;
                case 16:
                    this.poolbuf.appendByte(iPoolTag);
                    this.poolbuf.appendChar(PoolWriter.this.putDescriptor(((Type.MethodType) poolConstant).baseType()));
                    break;
                case 17:
                    Symbol.DynamicVarSymbol dynamicVarSymbol = (Symbol.DynamicVarSymbol) poolConstant;
                    this.poolbuf.appendByte(iPoolTag);
                    this.poolbuf.appendChar(PoolWriter.this.makeBootstrapEntry(dynamicVarSymbol));
                    this.poolbuf.appendChar(PoolWriter.this.putNameAndType(dynamicVarSymbol));
                    break;
                case 18:
                    Symbol.DynamicMethodSymbol dynamicMethodSymbol = (Symbol.DynamicMethodSymbol) poolConstant;
                    this.poolbuf.appendByte(iPoolTag);
                    this.poolbuf.appendChar(PoolWriter.this.makeBootstrapEntry(dynamicMethodSymbol));
                    this.poolbuf.appendChar(PoolWriter.this.putNameAndType(dynamicMethodSymbol));
                    break;
                case 19:
                    Symbol.ModuleSymbol moduleSymbol = (Symbol.ModuleSymbol) poolConstant;
                    int iPutName = PoolWriter.this.putName(moduleSymbol.name);
                    this.poolbuf.appendByte(moduleSymbol.poolTag());
                    this.poolbuf.appendChar(iPutName);
                    break;
                case 20:
                    Name nameExternalize = ClassFile.externalize(((Symbol.PackageSymbol) poolConstant).flatName());
                    this.poolbuf.appendByte(iPoolTag);
                    this.poolbuf.appendChar(PoolWriter.this.putName(nameExternalize));
                    break;
            }
        }
    }

    public PoolWriter(Types types, Names names) {
        this.types = types;
        this.names = names;
    }

    private Name classSig(Type type) {
        this.signatureGen.reset();
        List<Type> typeArguments = type.getTypeArguments();
        if (typeArguments.nonEmpty()) {
            this.signatureGen.assembleParamsSig(typeArguments);
        }
        this.signatureGen.assembleSig(this.types.supertype(type));
        Iterator<Type> it = this.types.interfaces(type).iterator();
        while (it.hasNext()) {
            this.signatureGen.assembleSig(it.next());
        }
        return this.signatureGen.toName();
    }

    private Type descriptorType(Symbol symbol) {
        Kinds.Kind kind = symbol.kind;
        Kinds.Kind kind2 = Kinds.Kind.MTH;
        Types types = this.types;
        return kind == kind2 ? symbol.externalType(types) : symbol.erasure(types);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int makeBootstrapEntry(PoolConstant.Dynamic dynamic) {
        PoolConstant.Dynamic.BsmKey bsmKey = dynamic.bsmKey(this.types);
        Integer numValueOf = this.bootstrapMethods.get(bsmKey);
        if (numValueOf == null) {
            numValueOf = Integer.valueOf(this.bootstrapMethods.size());
            this.bootstrapMethods.put(bsmKey, numValueOf);
        }
        return numValueOf.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Name typeSig(Type type) {
        this.signatureGen.reset();
        this.signatureGen.assembleSig(type);
        return this.signatureGen.toName();
    }

    public void enterInner(Symbol.ClassSymbol classSymbol) {
        if (classSymbol.type.isCompound()) {
            pe1.a("Unexpected intersection type: ", classSymbol.type);
            return;
        }
        classSymbol.complete();
        if (classSymbol.owner.enclClass() == null || this.innerClasses.contains(classSymbol)) {
            return;
        }
        enterInner(classSymbol.owner.enclClass());
        this.innerClasses.add(classSymbol);
    }

    public int putClass(Type type) {
        return this.pool.writeIfNeeded(this.types.erasure(type));
    }

    public int putConstant(Object obj) {
        if (obj instanceof Integer) {
            return putConstant(PoolConstant.LoadableConstant.Int(((Integer) obj).intValue()));
        }
        if (obj instanceof Float) {
            return putConstant(PoolConstant.LoadableConstant.Float(((Float) obj).floatValue()));
        }
        if (obj instanceof Long) {
            return putConstant(PoolConstant.LoadableConstant.Long(((Long) obj).longValue()));
        }
        if (obj instanceof Double) {
            return putConstant(PoolConstant.LoadableConstant.Double(((Double) obj).doubleValue()));
        }
        if (obj instanceof String) {
            return putConstant(PoolConstant.LoadableConstant.String((String) obj));
        }
        s22.a("unexpected constant: ", obj);
        return 0;
    }

    public int putDescriptor(Type type) {
        return putName(typeSig(this.types.erasure(type)));
    }

    public int putDynamic(PoolConstant.Dynamic dynamic) {
        return this.pool.writeIfNeeded(dynamic);
    }

    public int putMember(Symbol symbol) {
        return this.pool.writeIfNeeded(symbol);
    }

    public int putModule(Symbol.ModuleSymbol moduleSymbol) {
        return this.pool.writeIfNeeded(moduleSymbol);
    }

    public int putName(Name name) {
        return this.pool.writeIfNeeded(name);
    }

    public int putNameAndType(Symbol symbol) {
        return this.pool.writeIfNeeded(new PoolConstant.NameAndType(symbol.name, descriptorType(symbol)));
    }

    public int putPackage(Symbol.PackageSymbol packageSymbol) {
        return this.pool.writeIfNeeded(packageSymbol);
    }

    public int putSignature(Symbol symbol) {
        Kinds.Kind kind = symbol.kind;
        Kinds.Kind kind2 = Kinds.Kind.TYP;
        Type type = symbol.type;
        return kind == kind2 ? putName(classSig(type)) : putName(typeSig(type));
    }

    public void reset() {
        this.innerClasses.clear();
        this.bootstrapMethods.clear();
        this.pool.reset();
    }

    public int size() {
        return this.pool.currentIndex;
    }

    public void writePool(OutputStream outputStream) throws IOException, ClassWriter.PoolOverflow {
        String str = this.pool.overflowString;
        if (str != null) {
            throw new ClassWriter.StringOverflow(str);
        }
        int size = size();
        if (size > 65535) {
            throw new ClassWriter.PoolOverflow();
        }
        outputStream.write(size >> 8);
        outputStream.write(size);
        ByteBuffer byteBuffer = this.pool.poolbuf;
        outputStream.write(byteBuffer.elems, 0, byteBuffer.length);
    }

    public class SharedSignatureGenerator extends Types.SignatureGenerator {
        ByteBuffer sigbuf;

        /* JADX WARN: Illegal instructions before constructor call */
        public SharedSignatureGenerator() {
            Types types = PoolWriter.this.types;
            Objects.requireNonNull(types);
            super();
            this.sigbuf = new ByteBuffer();
        }

        @Override // com.sun.tools.javac.code.Types.SignatureGenerator
        public void append(char c) {
            this.sigbuf.appendByte(c);
        }

        @Override // com.sun.tools.javac.code.Types.SignatureGenerator
        public void assembleSig(Type type) {
            int i = AnonymousClass1.$SwitchMap$com$sun$tools$javac$code$TypeTag[type.getTag().ordinal()];
            if (i == 1 || i == 2) {
                assembleSig(PoolWriter.this.types.erasure(((UninitializedType) type).qtype));
            } else {
                super.assembleSig(type);
            }
        }

        @Override // com.sun.tools.javac.code.Types.SignatureGenerator
        public void classReference(Symbol.ClassSymbol classSymbol) {
            PoolWriter.this.enterInner(classSymbol);
        }

        public void reset() {
            this.sigbuf.reset();
        }

        public Name toName() {
            try {
                return this.sigbuf.toName(PoolWriter.this.names);
            } catch (InvalidUtfException e) {
                x01.a(e);
                return null;
            }
        }

        @Override // com.sun.tools.javac.code.Types.SignatureGenerator
        public void append(byte[] bArr) {
            this.sigbuf.appendBytes(bArr);
        }

        @Override // com.sun.tools.javac.code.Types.SignatureGenerator
        public void append(Name name) {
            this.sigbuf.appendName(name);
        }
    }

    public int putClass(Symbol.ClassSymbol classSymbol) {
        return putClass(classSymbol.type);
    }

    public int putDescriptor(Symbol symbol) {
        return putDescriptor(descriptorType(symbol));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int putConstant(PoolConstant.LoadableConstant loadableConstant) {
        int iPoolTag = loadableConstant.poolTag();
        if (iPoolTag != 7) {
            WriteablePoolHelper writeablePoolHelper = this.pool;
            if (iPoolTag != 16) {
                return writeablePoolHelper.writeIfNeeded(loadableConstant);
            }
            return writeablePoolHelper.writeIfNeeded(this.types.erasure((Type) loadableConstant));
        }
        return putClass((Type) loadableConstant);
    }
}
