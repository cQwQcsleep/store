package moe.shizuku.api;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: /workspace/unpacked/classes.dex */
public class BinderContainer implements Parcelable {
    public static final Parcelable.Creator<BinderContainer> CREATOR = new Parcelable.Creator<BinderContainer>() { // from class: moe.shizuku.api.BinderContainer.1
        @Override // android.os.Parcelable.Creator
        public BinderContainer createFromParcel(Parcel parcel) {
            return new BinderContainer(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public BinderContainer[] newArray(int i) {
            return new BinderContainer[i];
        }
    };
    public IBinder binder;

    public BinderContainer(IBinder iBinder) {
        this.binder = iBinder;
    }

    protected BinderContainer(Parcel parcel) {
        this.binder = parcel.readStrongBinder();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.binder);
    }
}
