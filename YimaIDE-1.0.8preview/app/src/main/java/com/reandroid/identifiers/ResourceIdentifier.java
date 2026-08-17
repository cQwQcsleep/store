package com.reandroid.identifiers;

import com.intellij.psi.PsiKeyword;
import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.model.ResourceEntry;
import com.reandroid.utils.HexUtil;
import java.io.IOException;
import org.xmlpull.v1.XmlSerializer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ResourceIdentifier extends Identifier {
    public static final int NAME_LENGTH_MAX = 100;
    public static final int NAME_LENGTH_MIN = 1;
    private Boolean mHasGoodName;

    public ResourceIdentifier() {
        this(0, null);
    }

    public static String buildResourceName(char c, String str, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        if (c != 0) {
            sb.append(c);
        }
        if (str != null) {
            sb.append(str);
            sb.append(':');
        }
        if (str2 != null) {
            sb.append(str2);
            sb.append('/');
        }
        sb.append(str3);
        return sb.toString();
    }

    private PackageBlock getPackageBlock() {
        PackageIdentifier packageIdentifier = getPackageIdentifier();
        if (packageIdentifier != null) {
            return packageIdentifier.getPackageBlock();
        }
        return null;
    }

    private static boolean isAtoZ(char c) {
        if (c < 'A' || c > 'Z') {
            return c >= 'a' && c <= 'z';
        }
        return true;
    }

    private static boolean isDigits(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isGoodFirstChar(char c) {
        return isAtoZ(c) || c == '_' || c == '$';
    }

    public static boolean isGoodName(String str) {
        int length;
        if (str == null || (length = str.length()) < 1 || length > 100) {
            return false;
        }
        char[] charArray = str.toCharArray();
        if (!isGoodFirstChar(charArray[0])) {
            return false;
        }
        int length2 = charArray.length;
        for (int i = 1; i < length2; i++) {
            if (!isGoodNameChar(charArray[i])) {
                return false;
            }
        }
        return true;
    }

    private static boolean isGoodNameChar(char c) {
        return isAtoZ(c) || isDigits(c) || c == '_' || c == '.' || c == '$' || c == '-';
    }

    public String generateUniqueName() {
        String typeName = getTypeName();
        if (typeName == null) {
            typeName = "res";
        }
        return typeName + "_" + getHexId();
    }

    @Override // com.reandroid.identifiers.Identifier
    public String getHexId() {
        return HexUtil.toHex8(getResourceId());
    }

    public int getPackageId() {
        TypeIdentifier typeIdentifier = getTypeIdentifier();
        if (typeIdentifier != null) {
            return typeIdentifier.getPackageId();
        }
        return 0;
    }

    public PackageIdentifier getPackageIdentifier() {
        TypeIdentifier typeIdentifier = getTypeIdentifier();
        if (typeIdentifier != null) {
            return typeIdentifier.getPackageIdentifier();
        }
        return null;
    }

    public String getPackageName() {
        TypeIdentifier typeIdentifier = getTypeIdentifier();
        if (typeIdentifier != null) {
            return typeIdentifier.getPackageName();
        }
        return null;
    }

    public int getResourceId() {
        return getId() | (getPackageId() << 24) | (getTypeId() << 16);
    }

    public String getResourceName(char c, boolean z, boolean z2) {
        return buildResourceName(c, z ? getPackageName() : null, z2 ? getTypeName() : null, getName());
    }

    public int getTypeId() {
        TypeIdentifier typeIdentifier = getTypeIdentifier();
        if (typeIdentifier != null) {
            return typeIdentifier.getId();
        }
        return 0;
    }

    public TypeIdentifier getTypeIdentifier() {
        return (TypeIdentifier) getParent();
    }

    public String getTypeName() {
        TypeIdentifier typeIdentifier = getTypeIdentifier();
        if (typeIdentifier != null) {
            return typeIdentifier.getName();
        }
        return null;
    }

    @Override // com.reandroid.identifiers.Identifier
    public long getUniqueId() {
        return ((long) getResourceId()) & 4294967295L;
    }

    public boolean hasGoodName() {
        if (this.mHasGoodName == null) {
            this.mHasGoodName = Boolean.valueOf(isGoodName(getName()));
        }
        return this.mHasGoodName.booleanValue();
    }

    public boolean isGeneratedName() {
        String name = getName();
        if (name != null && name.contains("_0x")) {
            return generateUniqueName().equals(name);
        }
        return false;
    }

    public boolean renameBadSpec() {
        if (hasGoodName()) {
            return renameDollarPrefix();
        }
        setName(generateUniqueName());
        return renameSpec();
    }

    public boolean renameDollarPrefix() {
        if (!Identifier.isAapt()) {
            return false;
        }
        String name = getName();
        if (name.charAt(0) != '$') {
            return false;
        }
        setName(name.substring(1));
        return renameSpec();
    }

    public boolean renameSpec() {
        ResourceEntry resource;
        String name;
        PackageBlock packageBlock = getPackageBlock();
        if (packageBlock == null || (resource = packageBlock.getResource(getResourceId())) == null || (name = getName()) == null || name.equals(resource.getName())) {
            return false;
        }
        resource.setName(name);
        return true;
    }

    public boolean renameSpecGenerated() {
        setName(generateUniqueName());
        return renameSpec();
    }

    @Override // com.reandroid.identifiers.Identifier
    public void setId(int i) {
        super.setId(i & 65535);
    }

    @Override // com.reandroid.identifiers.Identifier
    public void setName(String str) {
        super.setName(str);
        this.mHasGoodName = null;
    }

    @Override // com.reandroid.identifiers.Identifier
    public void setTag(Object obj) {
        TypeIdentifier typeIdentifier = getTypeIdentifier();
        if (typeIdentifier == null) {
            super.setTag(obj);
            return;
        }
        Object tag = getTag();
        if (tag != null) {
            typeIdentifier.removeTag(tag);
        }
        typeIdentifier.addTag(obj, this);
        super.setTag(obj);
    }

    public void setTypeIdentifier(TypeIdentifier typeIdentifier) {
        setParent(typeIdentifier);
    }

    @Override // com.reandroid.identifiers.Identifier
    public String toString() {
        return getHexId() + " " + getResourceName();
    }

    public void write(XmlSerializer xmlSerializer) throws IOException {
        xmlSerializer.text("\n  ");
        xmlSerializer.startTag(null, PsiKeyword.PUBLIC);
        xmlSerializer.attribute(null, "id", getHexId());
        xmlSerializer.attribute(null, "type", getTypeName());
        xmlSerializer.attribute(null, "name", getName());
        xmlSerializer.endTag(null, PsiKeyword.PUBLIC);
    }

    public ResourceIdentifier(int i, String str) {
        super(i, str);
    }

    public String getResourceName(PackageIdentifier packageIdentifier) {
        return getResourceName('@', packageIdentifier != getPackageIdentifier(), true);
    }

    public String getResourceName() {
        return getResourceName(null);
    }
}
