package com.reandroid.dex.common;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class RegisterFormat {
    public static final RegisterFormat NONE = new RegisterFormat("NONE", new RegisterType[0]);
    public static final RegisterFormat OUT;
    public static final RegisterFormat OUT_RANGE;
    public static final RegisterFormat READ;
    public static final RegisterFormat READ_READ;
    public static final RegisterFormat READ_READ_READ;
    public static final RegisterFormat READ_WRITE;
    public static final RegisterFormat WRITE;
    public static final RegisterFormat WRITE_READ;
    public static final RegisterFormat WRITE_READ_READ;
    private final int hash;
    private final String name;
    private final RegisterType[] types;

    static {
        RegisterType registerType = RegisterType.READ;
        READ = new RegisterFormat("READ", new RegisterType[]{registerType});
        RegisterType registerType2 = RegisterType.WRITE;
        WRITE = new RegisterFormat("WRITE", new RegisterType[]{registerType2});
        READ_READ = new RegisterFormat("READ_READ", new RegisterType[]{registerType, registerType});
        READ_WRITE = new RegisterFormat("READ_WRITE", new RegisterType[]{registerType, registerType2});
        READ_READ_READ = new RegisterFormat("READ_READ_READ", new RegisterType[]{registerType, registerType, registerType});
        WRITE_READ = new RegisterFormat("WRITE_READ", new RegisterType[]{registerType2, registerType});
        WRITE_READ_READ = new RegisterFormat("WRITE_READ_READ", new RegisterType[]{registerType2, registerType, registerType});
        OUT = new RegisterFormat("OUT", new RegisterType[0]) { // from class: com.reandroid.dex.common.RegisterFormat.1
            @Override // com.reandroid.dex.common.RegisterFormat
            public RegisterType get(int i) {
                return RegisterType.READ;
            }

            @Override // com.reandroid.dex.common.RegisterFormat
            public boolean isOut() {
                return true;
            }
        };
        OUT_RANGE = new RegisterFormat("OUT_RANGE", new RegisterType[]{registerType, registerType}) { // from class: com.reandroid.dex.common.RegisterFormat.2
            @Override // com.reandroid.dex.common.RegisterFormat
            public boolean isOut() {
                return true;
            }

            @Override // com.reandroid.dex.common.RegisterFormat
            public boolean isRange() {
                return true;
            }
        };
    }

    private RegisterFormat(String str, RegisterType[] registerTypeArr) {
        this.name = str;
        this.types = registerTypeArr;
        this.hash = (registerTypeArr != null ? 1 + (registerTypeArr.length * 31) : 1) + (str.hashCode() * 31);
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public RegisterType get(int i) {
        return isOut() ? RegisterType.READ : this.types[i];
    }

    public int hashCode() {
        return this.hash;
    }

    public boolean isOut() {
        return false;
    }

    public boolean isRange() {
        return false;
    }

    public int size() {
        RegisterType[] registerTypeArr = this.types;
        if (registerTypeArr != null) {
            return registerTypeArr.length;
        }
        return 0;
    }

    public String toString() {
        return this.name;
    }
}
