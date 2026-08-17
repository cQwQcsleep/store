package com.sun.xml.internal.stream;

import com.sun.org.apache.xerces.internal.xni.XMLResourceIdentifier;
import com.sun.xml.internal.stream.util.ThreadLocalBufferAllocator;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Entity {
    public boolean inExternalSubset;
    public String name;

    public static class ScannedEntity extends Entity {
        public static final int DEFAULT_BUFFER_SIZE = 8192;
        public static final int DEFAULT_INTERNAL_BUFFER_SIZE = 1024;
        public static final int DEFAULT_XMLDECL_BUFFER_SIZE = 28;
        public int baseCharOffset;
        public char[] ch;
        public int count;
        public String encoding;
        public XMLResourceIdentifier entityLocation;
        public int fLastCount;
        public int fTotalCountTillLastLoad;
        public boolean isExternal;
        public boolean isGE;
        public boolean literal;
        public boolean mayReadChunks;
        public int position;
        public Reader reader;
        public int startPosition;
        public InputStream stream;
        public String version;
        public int fBufferSize = 8192;
        public int lineNumber = 1;
        public int columnNumber = 1;
        boolean declaredEncoding = false;
        boolean externallySpecifiedEncoding = false;
        public String xmlVersion = "1.0";
        public boolean xmlDeclChunkRead = false;

        public ScannedEntity(boolean z, String str, XMLResourceIdentifier xMLResourceIdentifier, InputStream inputStream, Reader reader, String str2, boolean z2, boolean z3, boolean z4) {
            this.ch = null;
            this.isGE = z;
            this.name = str;
            this.entityLocation = xMLResourceIdentifier;
            this.stream = inputStream;
            this.reader = reader;
            this.encoding = str2;
            this.literal = z2;
            this.mayReadChunks = z3;
            this.isExternal = z4;
            int i = z4 ? 8192 : 1024;
            char[] charBuffer = ThreadLocalBufferAllocator.getBufferAllocator().getCharBuffer(i);
            this.ch = charBuffer;
            if (charBuffer == null) {
                this.ch = new char[i];
            }
        }

        public void close() throws IOException {
            ThreadLocalBufferAllocator.getBufferAllocator().returnCharBuffer(this.ch);
            this.ch = null;
            this.reader.close();
        }

        public String getEncodingName() {
            return this.encoding;
        }

        public InputStream getEntityInputStream() {
            return this.stream;
        }

        public Reader getEntityReader() {
            return this.reader;
        }

        public String getEntityVersion() {
            return this.version;
        }

        public boolean isDeclaredEncoding() {
            return this.declaredEncoding;
        }

        public boolean isEncodingExternallySpecified() {
            return this.externallySpecifiedEncoding;
        }

        @Override // com.sun.xml.internal.stream.Entity
        public final boolean isExternal() {
            return this.isExternal;
        }

        @Override // com.sun.xml.internal.stream.Entity
        public final boolean isUnparsed() {
            return false;
        }

        public void setDeclaredEncoding(boolean z) {
            this.declaredEncoding = z;
        }

        public void setEncodingExternallySpecified(boolean z) {
            this.externallySpecifiedEncoding = z;
        }

        public void setEntityVersion(String str) {
            this.version = str;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("name=\"" + this.name + '\"');
            stringBuffer.append(",ch=".concat(new String(this.ch)));
            stringBuffer.append(",position=" + this.position);
            stringBuffer.append(",count=" + this.count);
            return stringBuffer.toString();
        }
    }

    public Entity(String str, boolean z) {
        this.name = str;
        this.inExternalSubset = z;
    }

    public void clear() {
        this.name = null;
        this.inExternalSubset = false;
    }

    public boolean isEntityDeclInExternalSubset() {
        return this.inExternalSubset;
    }

    public abstract boolean isExternal();

    public abstract boolean isUnparsed();

    public void setValues(Entity entity) {
        this.name = entity.name;
        this.inExternalSubset = entity.inExternalSubset;
    }

    public static class InternalEntity extends Entity {
        public String text;

        public InternalEntity() {
            clear();
        }

        @Override // com.sun.xml.internal.stream.Entity
        public void clear() {
            super.clear();
            this.text = null;
        }

        @Override // com.sun.xml.internal.stream.Entity
        public final boolean isExternal() {
            return false;
        }

        @Override // com.sun.xml.internal.stream.Entity
        public final boolean isUnparsed() {
            return false;
        }

        public void setValues(InternalEntity internalEntity) {
            super.setValues((Entity) internalEntity);
            this.text = internalEntity.text;
        }

        public InternalEntity(String str, String str2, boolean z) {
            super(str, z);
            this.text = str2;
        }

        @Override // com.sun.xml.internal.stream.Entity
        public void setValues(Entity entity) {
            super.setValues(entity);
            this.text = null;
        }
    }

    public static class ExternalEntity extends Entity {
        public XMLResourceIdentifier entityLocation;
        public String notation;

        public ExternalEntity(String str, XMLResourceIdentifier xMLResourceIdentifier, String str2, boolean z) {
            super(str, z);
            this.entityLocation = xMLResourceIdentifier;
            this.notation = str2;
        }

        @Override // com.sun.xml.internal.stream.Entity
        public void clear() {
            super.clear();
            this.entityLocation = null;
            this.notation = null;
        }

        @Override // com.sun.xml.internal.stream.Entity
        public final boolean isExternal() {
            return true;
        }

        @Override // com.sun.xml.internal.stream.Entity
        public final boolean isUnparsed() {
            return this.notation != null;
        }

        public void setValues(ExternalEntity externalEntity) {
            super.setValues((Entity) externalEntity);
            this.entityLocation = externalEntity.entityLocation;
            this.notation = externalEntity.notation;
        }

        public ExternalEntity() {
            clear();
        }

        @Override // com.sun.xml.internal.stream.Entity
        public void setValues(Entity entity) {
            super.setValues(entity);
            this.entityLocation = null;
            this.notation = null;
        }
    }

    public Entity() {
        clear();
    }
}
