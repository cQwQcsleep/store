package com.sun.tools.javac.jvm;

import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.jvm.PoolConstant;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Name;
import com.sun.tools.javac.util.Pair;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface PoolConstant {

    public interface Dynamic extends PoolConstant {

        public static class BsmKey {
            public final LoadableConstant bsm;
            private final Object bsmKey;
            private final List<?> staticArgKeys;
            public final LoadableConstant[] staticArgs;

            private BsmKey(final Types types, LoadableConstant loadableConstant, LoadableConstant[] loadableConstantArr) {
                this.bsm = loadableConstant;
                this.bsmKey = loadableConstant.poolKey(types);
                this.staticArgs = loadableConstantArr;
                this.staticArgKeys = (List) Stream.of((Object[]) loadableConstantArr).map(new Function() { // from class: v3b
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((PoolConstant.LoadableConstant) obj).poolKey(types);
                    }
                }).collect(List.collector());
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof BsmKey)) {
                    return false;
                }
                BsmKey bsmKey = (BsmKey) obj;
                return Objects.equals(this.bsmKey, bsmKey.bsmKey) && Objects.equals(this.staticArgKeys, bsmKey.staticArgKeys);
            }

            public int hashCode() {
                return this.bsmKey.hashCode() + this.staticArgKeys.hashCode();
            }
        }

        public static final class PoolKey {
            private final BsmKey bsmKey;
            private final Object dynamicType;
            private final Name name;

            public PoolKey(Name name, BsmKey bsmKey, Object obj) {
                this.name = name;
                this.bsmKey = bsmKey;
                this.dynamicType = obj;
            }

            public BsmKey bsmKey() {
                return this.bsmKey;
            }

            public Object dynamicType() {
                return this.dynamicType;
            }

            public final boolean equals(Object obj) {
                if (!(obj instanceof PoolKey)) {
                    return false;
                }
                PoolKey poolKey = (PoolKey) obj;
                return Objects.equals(this.dynamicType, poolKey.dynamicType) && Objects.equals(this.bsmKey, poolKey.bsmKey) && Objects.equals(this.name, poolKey.name);
            }

            public final int hashCode() {
                return (((Objects.hashCode(this.name) * 31) + Objects.hashCode(this.bsmKey)) * 31) + Objects.hashCode(this.dynamicType);
            }

            public Name name() {
                return this.name;
            }

            public final String toString() {
                return "PoolKey[name=" + Objects.toString(this.name) + ", bsmKey=" + Objects.toString(this.bsmKey) + ", dynamicType=" + Objects.toString(this.dynamicType) + "]";
            }
        }

        LoadableConstant bootstrapMethod();

        default BsmKey bsmKey(Types types) {
            return new BsmKey(types, bootstrapMethod(), staticArgs());
        }

        PoolConstant dynamicType();

        Name name();

        @Override // com.sun.tools.javac.jvm.PoolConstant
        default Object poolKey(Types types) {
            return new PoolKey(name(), bsmKey(types), dynamicType().poolKey(types));
        }

        LoadableConstant[] staticArgs();
    }

    public interface LoadableConstant extends PoolConstant {

        public static class BasicConstant implements LoadableConstant {
            Object data;
            int tag;

            private BasicConstant(int i, Object obj) {
                this.tag = i;
                this.data = obj;
            }

            @Override // com.sun.tools.javac.jvm.PoolConstant
            public Object poolKey(Types types) {
                return this.data;
            }

            @Override // com.sun.tools.javac.jvm.PoolConstant
            public int poolTag() {
                return this.tag;
            }
        }

        static LoadableConstant Double(double d) {
            return new BasicConstant(6, Double.valueOf(d));
        }

        static LoadableConstant Float(float f) {
            return new BasicConstant(4, Float.valueOf(f));
        }

        static LoadableConstant Int(int i) {
            return new BasicConstant(3, Integer.valueOf(i));
        }

        static LoadableConstant Long(long j) {
            return new BasicConstant(5, Long.valueOf(j));
        }

        static LoadableConstant String(String str) {
            return new BasicConstant(8, str);
        }
    }

    public static final class NameAndType implements PoolConstant {
        final Name name;
        final Type type;

        public NameAndType(Name name, Type type) {
            this.name = name;
            this.type = type;
        }

        @Override // com.sun.tools.javac.jvm.PoolConstant
        public Object poolKey(Types types) {
            return new Pair(this.name, new Types.UniqueType(this.type, types));
        }

        @Override // com.sun.tools.javac.jvm.PoolConstant
        public int poolTag() {
            return 12;
        }
    }

    default Object poolKey(Types types) {
        return this;
    }

    int poolTag();
}
