package com.sun.jna.platform.win32;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import com.sun.jna.win32.W32APITypeMapper;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface Sspi {
    public static final int ISC_REQ_ALLOCATE_MEMORY = 256;
    public static final int ISC_REQ_CONFIDENTIALITY = 16;
    public static final int ISC_REQ_CONNECTION = 2048;
    public static final int ISC_REQ_DELEGATE = 1;
    public static final int ISC_REQ_EXTENDED_ERROR = 16384;
    public static final int ISC_REQ_INTEGRITY = 65536;
    public static final int ISC_REQ_MUTUAL_AUTH = 2;
    public static final int ISC_REQ_REPLAY_DETECT = 4;
    public static final int ISC_REQ_SEQUENCE_DETECT = 8;
    public static final int ISC_REQ_STREAM = 32768;
    public static final int MAX_TOKEN_SIZE = 12288;
    public static final int SECBUFFER_DATA = 1;
    public static final int SECBUFFER_EMPTY = 0;
    public static final int SECBUFFER_TOKEN = 2;
    public static final int SECBUFFER_VERSION = 0;
    public static final int SECPKG_ATTR_ACCESS_TOKEN = 18;
    public static final int SECPKG_ATTR_AUTHENTICATION_ID = 20;
    public static final int SECPKG_ATTR_AUTHORITY = 6;
    public static final int SECPKG_ATTR_CLIENT_SPECIFIED_TARGET = 27;
    public static final int SECPKG_ATTR_CONTEXT_DELETED = 33;
    public static final int SECPKG_ATTR_CREDENTIAL_NAME = 16;
    public static final int SECPKG_ATTR_DCE_INFO = 3;
    public static final int SECPKG_ATTR_ENDPOINT_BINDINGS = 26;
    public static final int SECPKG_ATTR_FLAGS = 14;
    public static final int SECPKG_ATTR_KEY_INFO = 5;
    public static final int SECPKG_ATTR_LAST_CLIENT_TOKEN_STATUS = 30;
    public static final int SECPKG_ATTR_LIFESPAN = 2;
    public static final int SECPKG_ATTR_LOGOFF_TIME = 21;
    public static final int SECPKG_ATTR_NAMES = 1;
    public static final int SECPKG_ATTR_NATIVE_NAMES = 13;
    public static final int SECPKG_ATTR_NEGOTIATION_INFO = 12;
    public static final int SECPKG_ATTR_NEGO_KEYS = 22;
    public static final int SECPKG_ATTR_NEGO_PKG_INFO = 31;
    public static final int SECPKG_ATTR_NEGO_STATUS = 32;
    public static final int SECPKG_ATTR_PACKAGE_INFO = 10;
    public static final int SECPKG_ATTR_PASSWORD_EXPIRY = 8;
    public static final int SECPKG_ATTR_PROMPTING_NEEDED = 24;
    public static final int SECPKG_ATTR_PROTO_INFO = 7;
    public static final int SECPKG_ATTR_SESSION_KEY = 9;
    public static final int SECPKG_ATTR_SIZES = 0;
    public static final int SECPKG_ATTR_STREAM_SIZES = 4;
    public static final int SECPKG_ATTR_SUBJECT_SECURITY_ATTRIBUTES = 128;
    public static final int SECPKG_ATTR_TARGET = 19;
    public static final int SECPKG_ATTR_TARGET_INFORMATION = 17;
    public static final int SECPKG_ATTR_UNIQUE_BINDINGS = 25;
    public static final int SECPKG_ATTR_USER_FLAGS = 11;
    public static final int SECPKG_ATTR_USE_VALIDATED = 15;
    public static final int SECPKG_CRED_ATTR_NAMES = 1;
    public static final int SECPKG_CRED_INBOUND = 1;
    public static final int SECPKG_CRED_OUTBOUND = 2;
    public static final int SECPKG_FLAG_ACCEPT_WIN32_NAME = 512;
    public static final int SECPKG_FLAG_APPCONTAINER_CHECKS = 8388608;
    public static final int SECPKG_FLAG_APPCONTAINER_PASSTHROUGH = 4194304;
    public static final int SECPKG_FLAG_ASCII_BUFFERS = 16384;
    public static final int SECPKG_FLAG_CLIENT_ONLY = 64;
    public static final int SECPKG_FLAG_CONNECTION = 16;
    public static final int SECPKG_FLAG_DATAGRAM = 8;
    public static final int SECPKG_FLAG_DELEGATION = 131072;
    public static final int SECPKG_FLAG_EXTENDED_ERROR = 128;
    public static final int SECPKG_FLAG_FRAGMENT = 32768;
    public static final int SECPKG_FLAG_GSS_COMPATIBLE = 4096;
    public static final int SECPKG_FLAG_IMPERSONATION = 256;
    public static final int SECPKG_FLAG_INTEGRITY = 1;
    public static final int SECPKG_FLAG_LOGON = 8192;
    public static final int SECPKG_FLAG_MULTI_REQUIRED = 32;
    public static final int SECPKG_FLAG_MUTUAL_AUTH = 65536;
    public static final int SECPKG_FLAG_NEGOTIABLE = 2048;
    public static final int SECPKG_FLAG_NEGOTIABLE2 = 2097152;
    public static final int SECPKG_FLAG_NEGO_EXTENDER = 1048576;
    public static final int SECPKG_FLAG_PRIVACY = 2;
    public static final int SECPKG_FLAG_RESTRICTED_TOKENS = 524288;
    public static final int SECPKG_FLAG_STREAM = 1024;
    public static final int SECPKG_FLAG_TOKEN_ONLY = 4;
    public static final int SECPKG_NEGOTIATION_COMPLETE = 0;
    public static final int SECPKG_NEGOTIATION_DIRECT = 3;
    public static final int SECPKG_NEGOTIATION_IN_PROGRESS = 2;
    public static final int SECPKG_NEGOTIATION_OPTIMISTIC = 1;
    public static final int SECPKG_NEGOTIATION_TRY_MULTICRED = 4;
    public static final int SECQOP_WRAP_NO_ENCRYPT = -2147483647;
    public static final int SECQOP_WRAP_OOB_DATA = 1073741824;
    public static final int SECURITY_NATIVE_DREP = 16;
    public static final int SECURITY_NETWORK_DREP = 0;
    public static final int SEC_WINNT_AUTH_IDENTITY_ANSI = 1;
    public static final int SEC_WINNT_AUTH_IDENTITY_UNICODE = 2;

    public static class CredHandle extends SecHandle {
    }

    public static class CtxtHandle extends SecHandle {
    }

    @Structure.FieldOrder({"pPkgInfo"})
    public static class PSecPkgInfo extends Structure {
        public SecPkgInfo.ByReference pPkgInfo;

        public static class ByReference extends PSecPkgInfo implements Structure.ByReference {
            @Override // com.sun.jna.platform.win32.Sspi.PSecPkgInfo, com.sun.jna.Structure
            public /* bridge */ /* synthetic */ Structure[] toArray(int i) {
                return super.toArray(i);
            }
        }

        @Override // com.sun.jna.Structure
        public SecPkgInfo.ByReference[] toArray(int i) {
            return (SecPkgInfo.ByReference[]) this.pPkgInfo.toArray(i);
        }
    }

    @Structure.FieldOrder({"dwLower", "dwUpper"})
    public static class SECURITY_INTEGER extends Structure {
        public int dwLower;
        public int dwUpper;
    }

    @Structure.FieldOrder({"User", "UserLength", "Domain", "DomainLength", "Password", "PasswordLength", "Flags"})
    public static class SEC_WINNT_AUTH_IDENTITY extends Structure {
        public String Domain;
        public int DomainLength;
        public int Flags;
        public String Password;
        public int PasswordLength;
        public String User;
        public int UserLength;

        public SEC_WINNT_AUTH_IDENTITY() {
            super(W32APITypeMapper.UNICODE);
            this.Flags = 2;
        }

        @Override // com.sun.jna.Structure
        public void write() {
            String str = this.User;
            this.UserLength = str == null ? 0 : str.length();
            String str2 = this.Domain;
            this.DomainLength = str2 == null ? 0 : str2.length();
            String str3 = this.Password;
            this.PasswordLength = str3 != null ? str3.length() : 0;
            super.write();
        }
    }

    @Structure.FieldOrder({"ulVersion", "cBuffers", "pBuffers"})
    public static class SecBufferDesc extends Structure {
        public Pointer pBuffers;
        public int ulVersion = 0;
        public int cBuffers = 1;
    }

    @Structure.FieldOrder({"dwLower", "dwUpper"})
    public static class SecHandle extends Structure {
        public Pointer dwLower;
        public Pointer dwUpper;

        public static class ByReference extends SecHandle implements Structure.ByReference {
        }

        public boolean isNull() {
            return this.dwLower == null && this.dwUpper == null;
        }
    }

    @Structure.FieldOrder({"Flags"})
    public static class SecPkgContext_Flags extends Structure {
        public int Flags;

        public static class ByReference extends SecPkgContext_Flags implements Structure.ByReference {
        }

        public SecPkgContext_Flags() {
            super(W32APITypeMapper.DEFAULT);
        }
    }

    @Structure.FieldOrder({"sSignatureAlgorithmName", "sEncryptAlgorithmName", "KeySize", "SignatureAlgorithm", "EncryptAlgorithm"})
    public static class SecPkgContext_KeyInfo extends Structure {
        public int EncryptAlgorithm;
        public int KeySize;
        public int SignatureAlgorithm;
        public Pointer sEncryptAlgorithmName;
        public Pointer sSignatureAlgorithmName;

        public SecPkgContext_KeyInfo() {
            super(W32APITypeMapper.DEFAULT);
        }

        public synchronized void free() {
            try {
                Pointer pointer = this.sSignatureAlgorithmName;
                if (pointer != null) {
                    Secur32.INSTANCE.FreeContextBuffer(pointer);
                    this.sSignatureAlgorithmName = null;
                }
                Pointer pointer2 = this.sEncryptAlgorithmName;
                if (pointer2 != null) {
                    Secur32.INSTANCE.FreeContextBuffer(pointer2);
                    this.sEncryptAlgorithmName = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }

        public synchronized String getEncryptAlgorithmName() {
            try {
                if (this.sEncryptAlgorithmName == null) {
                    return null;
                }
                boolean z = Boolean.getBoolean("w32.ascii");
                Pointer pointer = this.sEncryptAlgorithmName;
                return z ? pointer.getString(0L) : pointer.getWideString(0L);
            } catch (Throwable th) {
                throw th;
            }
        }

        public synchronized String getSignatureAlgorithmName() {
            try {
                if (this.sSignatureAlgorithmName == null) {
                    return null;
                }
                boolean z = Boolean.getBoolean("w32.ascii");
                Pointer pointer = this.sSignatureAlgorithmName;
                return z ? pointer.getString(0L) : pointer.getWideString(0L);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Structure.FieldOrder({"tsStart", "tsExpiry"})
    public static class SecPkgContext_Lifespan extends Structure {
        public TimeStamp tsExpiry;
        public TimeStamp tsStart;

        public static class ByReference extends SecPkgContext_Lifespan implements Structure.ByReference {
        }

        public SecPkgContext_Lifespan() {
            super(W32APITypeMapper.DEFAULT);
        }
    }

    @Structure.FieldOrder({"PackageInfo", "NegotiationState"})
    public static class SecPkgContext_NegotiationInfo extends Structure {
        public int NegotiationState;
        public PSecPkgInfo PackageInfo;

        public static class ByReference extends SecPkgContext_NegotiationInfo implements Structure.ByReference {
        }

        public SecPkgContext_NegotiationInfo() {
            super(W32APITypeMapper.DEFAULT);
        }

        public synchronized void free() {
            PSecPkgInfo pSecPkgInfo = this.PackageInfo;
            if (pSecPkgInfo != null) {
                Secur32.INSTANCE.FreeContextBuffer(pSecPkgInfo.pPkgInfo.getPointer());
                this.PackageInfo = null;
            }
        }
    }

    @Structure.FieldOrder({"PackageInfo"})
    public static class SecPkgContext_PackageInfo extends Structure {
        public SecPkgInfo.ByReference PackageInfo;

        public static class ByReference extends SecPkgContext_PackageInfo implements Structure.ByReference {
        }

        public SecPkgContext_PackageInfo() {
            super(W32APITypeMapper.DEFAULT);
        }
    }

    @Structure.FieldOrder({"SessionKeyLength", "SessionKey"})
    public static class SecPkgContext_SessionKey extends Structure {
        public Pointer SessionKey;
        public int SessionKeyLength;

        public static class ByReference extends SecPkgContext_SessionKey implements Structure.ByReference {
        }

        public SecPkgContext_SessionKey() {
            super(W32APITypeMapper.DEFAULT);
        }

        public synchronized void free() {
            Pointer pointer = this.SessionKey;
            if (pointer != null) {
                Secur32.INSTANCE.FreeContextBuffer(pointer);
                this.SessionKey = null;
            }
        }

        public byte[] getSessionKey() {
            Pointer pointer = this.SessionKey;
            if (pointer == null) {
                return null;
            }
            return pointer.getByteArray(0L, this.SessionKeyLength);
        }
    }

    @Structure.FieldOrder({"cbMaxToken", "cbMaxSignature", "cbBlockSize", "cbSecurityTrailer"})
    public static class SecPkgContext_Sizes extends Structure {
        public int cbBlockSize;
        public int cbMaxSignature;
        public int cbMaxToken;
        public int cbSecurityTrailer;

        public static class ByReference extends SecPkgContext_Sizes implements Structure.ByReference {
        }

        public SecPkgContext_Sizes() {
            super(W32APITypeMapper.DEFAULT);
        }

        @Override // com.sun.jna.Structure
        public String toString() {
            return "SecPkgContext_Sizes{cbMaxToken=" + this.cbMaxToken + ", cbMaxSignature=" + this.cbMaxSignature + ", cbBlockSize=" + this.cbBlockSize + ", cbSecurityTrailer=" + this.cbSecurityTrailer + '}';
        }
    }

    @Structure.FieldOrder({"sUserName"})
    public static class SecPkgCredentials_Names extends Structure {
        public Pointer sUserName;

        public static class ByReference extends SecPkgCredentials_Names implements Structure.ByReference {
        }

        public SecPkgCredentials_Names() {
            super(W32APITypeMapper.DEFAULT);
        }

        public synchronized int free() {
            Pointer pointer = this.sUserName;
            if (pointer == null) {
                return 0;
            }
            int iFreeContextBuffer = Secur32.INSTANCE.FreeContextBuffer(pointer);
            this.sUserName = null;
            return iFreeContextBuffer;
        }

        public synchronized String getUserName() {
            try {
                if (this.sUserName == null) {
                    return null;
                }
                boolean z = Boolean.getBoolean("w32.ascii");
                Pointer pointer = this.sUserName;
                return z ? pointer.getString(0L) : pointer.getWideString(0L);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Structure.FieldOrder({"fCapabilities", "wVersion", "wRPCID", "cbMaxToken", SchemaSymbols.ATTVAL_NAME, "Comment"})
    public static class SecPkgInfo extends Structure {
        public String Comment;
        public String Name;
        public int cbMaxToken;
        public int fCapabilities;
        public short wRPCID;
        public short wVersion;

        public static class ByReference extends SecPkgInfo implements Structure.ByReference {
        }

        public SecPkgInfo() {
            super(W32APITypeMapper.DEFAULT);
            this.wVersion = (short) 1;
        }
    }

    public static class TimeStamp extends SECURITY_INTEGER {
    }

    @Structure.FieldOrder({"cbBuffer", "BufferType", "pvBuffer"})
    public static class SecBuffer extends Structure {
        public int BufferType;
        public int cbBuffer;
        public Pointer pvBuffer;

        public SecBuffer(int i, byte[] bArr) {
            this.BufferType = 0;
            this.cbBuffer = bArr.length;
            Memory memory = new Memory(bArr.length);
            this.pvBuffer = memory;
            memory.write(0L, bArr, 0, bArr.length);
            this.BufferType = i;
        }

        public byte[] getBytes() {
            Pointer pointer = this.pvBuffer;
            if (pointer == null) {
                return null;
            }
            return pointer.getByteArray(0L, this.cbBuffer);
        }

        public static class ByReference extends SecBuffer implements Structure.ByReference {
            public ByReference() {
            }

            public ByReference(int i, int i2) {
                super(i, i2);
            }

            public ByReference(int i, byte[] bArr) {
                super(i, bArr);
            }
        }

        public SecBuffer(int i, int i2) {
            this.BufferType = 0;
            this.cbBuffer = i2;
            this.pvBuffer = new Memory(i2);
            this.BufferType = i;
        }

        public SecBuffer() {
            this.BufferType = 0;
        }
    }

    @Structure.FieldOrder({"secHandle"})
    public static class PSecHandle extends Structure {
        public SecHandle.ByReference secHandle;

        public static class ByReference extends PSecHandle implements Structure.ByReference {
        }

        public PSecHandle(SecHandle secHandle) {
            super(secHandle.getPointer());
            read();
        }

        public PSecHandle() {
        }
    }
}
