package com.reandroid.dex.smali.formatters;

import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexClass;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ClassComment extends SmaliComment {

    public static class ClassExtendComment implements ClassComment {
        private final DexClassRepository classRepository;

        public ClassExtendComment(DexClassRepository dexClassRepository) {
            this.classRepository = dexClassRepository;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ClassExtendComment) && this.classRepository == ((ClassExtendComment) obj).classRepository;
        }

        public int hashCode() {
            return ObjectsUtil.hash(this.classRepository, getClass());
        }

        @Override // com.reandroid.dex.smali.formatters.ClassComment
        public void writeComment(SmaliWriter smaliWriter, TypeKey typeKey) throws IOException {
            DexClass dexClass = this.classRepository.getDexClass(typeKey);
            if (dexClass == null || dexClass.isFinal()) {
                return;
            }
            SmaliComment.writeDeclarationComment(smaliWriter, "extended-by:", dexClass.getExtending());
        }
    }

    public static class ClassImplementComment implements ClassComment {
        private final DexClassRepository classRepository;

        public ClassImplementComment(DexClassRepository dexClassRepository) {
            this.classRepository = dexClassRepository;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof ClassImplementComment) && this.classRepository == ((ClassImplementComment) obj).classRepository;
        }

        public int hashCode() {
            return ObjectsUtil.hash(this.classRepository, getClass());
        }

        @Override // com.reandroid.dex.smali.formatters.ClassComment
        public void writeComment(SmaliWriter smaliWriter, TypeKey typeKey) throws IOException {
            DexClass dexClass = this.classRepository.getDexClass(typeKey);
            if (dexClass == null || !dexClass.isInterface()) {
                return;
            }
            SmaliComment.writeDeclarationComment(smaliWriter, "implemented-by:", dexClass.getExtending());
        }
    }

    void writeComment(SmaliWriter smaliWriter, TypeKey typeKey) throws IOException;
}
