package com.reandroid.arsc.value;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockCounter;
import com.reandroid.arsc.chunk.TypeBlock;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.FixedLengthString;
import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.model.ResourceLibrary;
import com.reandroid.common.Namespace;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.HexUtil;
import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class LibraryInfo extends Block implements JSONConvert<JSONObject>, ResourceLibrary {
    private final IntegerItem mPackageId;
    private final FixedLengthString mPackageName;

    public LibraryInfo() {
        IntegerItem integerItem = new IntegerItem();
        this.mPackageId = integerItem;
        FixedLengthString fixedLengthString = new FixedLengthString(Fcntl.S_IRUSR);
        this.mPackageName = fixedLengthString;
        integerItem.setIndex(0);
        integerItem.setParent(this);
        fixedLengthString.setIndex(1);
        fixedLengthString.setParent(this);
    }

    @Override // com.reandroid.arsc.base.Block
    public int countBytes() {
        if (isNull()) {
            return 0;
        }
        return this.mPackageId.countBytes() + this.mPackageName.countBytes();
    }

    public void fromJson(JSONObject jSONObject) {
        setId(jSONObject.getInt(TypeBlock.NAME_id));
        setName(jSONObject.getString(TypeBlock.NAME_name));
    }

    @Override // com.reandroid.arsc.base.Block
    public byte[] getBytes() {
        if (isNull()) {
            return null;
        }
        return Block.addBytes(this.mPackageId.getBytes(), this.mPackageName.getBytes());
    }

    public int getId() {
        return this.mPackageId.get();
    }

    public String getName() {
        return this.mPackageName.get();
    }

    public String getPrefix() {
        return ResourceLibrary.toPrefix(getName());
    }

    public String getUri() {
        return (getId() == 1 && Namespace.PREFIX_ANDROID.equals(getName())) ? Namespace.URI_ANDROID : Namespace.URI_RES_AUTO;
    }

    public void merge(LibraryInfo libraryInfo) {
        if (libraryInfo == null || libraryInfo == this) {
            return;
        }
        if (getId() == libraryInfo.getId()) {
            setName(libraryInfo.getName());
        } else {
            eq7.a("Can not add different id libraries: ", getId(), "!=", libraryInfo.getId());
        }
    }

    @Override // com.reandroid.arsc.base.Block
    public void onCountUpTo(BlockCounter blockCounter) {
        if (blockCounter.FOUND) {
            return;
        }
        blockCounter.setCurrent(this);
        if (blockCounter.END == this) {
            blockCounter.FOUND = true;
        } else {
            this.mPackageId.onCountUpTo(blockCounter);
            this.mPackageName.onCountUpTo(blockCounter);
        }
    }

    @Override // com.reandroid.arsc.base.Block
    public void onReadBytes(BlockReader blockReader) throws IOException {
        this.mPackageId.readBytes(blockReader);
        this.mPackageName.readBytes(blockReader);
    }

    @Override // com.reandroid.arsc.base.Block
    public int onWriteBytes(OutputStream outputStream) throws IOException {
        return this.mPackageId.writeBytes(outputStream) + this.mPackageName.writeBytes(outputStream);
    }

    public boolean packageNameMatches(String str) {
        return ResourceLibrary.packageNameMatches(this, str);
    }

    public void setId(int i) {
        this.mPackageId.set(i);
    }

    public void setName(String str) {
        this.mPackageName.set(str);
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(TypeBlock.NAME_id, getId());
        jSONObject.put(TypeBlock.NAME_name, getName());
        return jSONObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LIBRARY{");
        sb.append(HexUtil.toHex2((byte) getId()));
        sb.append(':');
        String name = getName();
        if (name == null) {
            name = "NULL";
        }
        sb.append(name);
        sb.append('}');
        return sb.toString();
    }
}
