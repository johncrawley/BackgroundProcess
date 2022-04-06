package com.jacstuff.backgroundprocess;

import android.os.Parcel;
import android.os.Parcelable;

public class WebsiteQuery implements Parcelable {

    private String url;
    private String queryStr;
    private String successMessage;
    private boolean isSuccessOnQueryFound; // if false, the service will post a success message when the query is NOT found


    public static final Parcelable.Creator<WebsiteQuery> CREATOR =
            new Parcelable.Creator<WebsiteQuery>(){

                @Override
                public WebsiteQuery createFromParcel(Parcel parcel) {
                    return new WebsiteQuery(parcel);
                }

                @Override
                public WebsiteQuery[] newArray(int i) {
                    return new WebsiteQuery[i];
                }
            };

    @Override
    public int describeContents() {
        return 0;
    }


    public WebsiteQuery(String url, String query, String successMessage, boolean isSuccessOnQueryFound){

        this.url = url;
        this.queryStr = query;
        this.successMessage = successMessage;
        this.isSuccessOnQueryFound = isSuccessOnQueryFound;
    }


    // Parcelling Part
    public WebsiteQuery(Parcel in){
        this.url = in.readString();
        this.queryStr = in.readString();
        this.successMessage = in.readString();
        this.isSuccessOnQueryFound = in.readInt() != 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeString(this.queryStr);
        parcel.writeString(this.successMessage);
        parcel.writeInt(isSuccessOnQueryFound ? 1 : 0);
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public boolean isSuccessOnQueryFound() {
        return isSuccessOnQueryFound;
    }

    public void setSuccessOnQueryFound(boolean successOnQueryFound) {
        isSuccessOnQueryFound = successOnQueryFound;
    }

}

