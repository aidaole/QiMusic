package com.aidaole.base.datas.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TopPlayList implements Serializable {
    @SerializedName("total")
    public int total;
    @SerializedName("code")
    public int code;
    @SerializedName("more")
    public boolean more;
    @SerializedName("cat")
    public String cat;
    @SerializedName("playlists")
    public List<Playlists> playlists;

    public static class Playlists implements Serializable {
        @SerializedName("name")
        public String name;
        @SerializedName("id")
        public long id;
        @SerializedName("trackNumberUpdateTime")
        public long trackNumberUpdateTime;
        @SerializedName("status")
        public int status;
        @SerializedName("userId")
        public long userId;
        @SerializedName("createTime")
        public long createTime;
        @SerializedName("updateTime")
        public long updateTime;
        @SerializedName("subscribedCount")
        public int subscribedCount;
        @SerializedName("trackCount")
        public int trackCount;
        @SerializedName("cloudTrackCount")
        public int cloudTrackCount;
        @SerializedName("coverImgUrl")
        public String coverImgUrl;
        @SerializedName("coverImgId")
        public long coverImgId;
        @SerializedName("description")
        public String description;
        @SerializedName("playCount")
        public int playCount;
        @SerializedName("trackUpdateTime")
        public long trackUpdateTime;
        @SerializedName("specialType")
        public int specialType;
        @SerializedName("totalDuration")
        public int totalDuration;
        @SerializedName("creator")
        public Creator creator;
        @SerializedName("tracks")
        public Object tracks;
        @SerializedName("subscribed")
        public boolean subscribed;
        @SerializedName("commentThreadId")
        public String commentThreadId;
        @SerializedName("newImported")
        public boolean newImported;
        @SerializedName("adType")
        public int adType;
        @SerializedName("highQuality")
        public boolean highQuality;
        @SerializedName("privacy")
        public int privacy;
        @SerializedName("ordered")
        public boolean ordered;
        @SerializedName("anonimous")
        public boolean anonimous;
        @SerializedName("coverStatus")
        public int coverStatus;
        @SerializedName("recommendInfo")
        public Object recommendInfo;
        @SerializedName("socialPlaylistCover")
        public Object socialPlaylistCover;
        @SerializedName("recommendText")
        public Object recommendText;
        @SerializedName("shareCount")
        public int shareCount;
        @SerializedName("coverImgId_str")
        public String coverImgIdStr;
        @SerializedName("alg")
        public String alg;
        @SerializedName("commentCount")
        public int commentCount;
        @SerializedName("tags")
        public List<String> tags;
        @SerializedName("subscribers")
        public List<Subscribers> subscribers;

        public static class Creator implements Serializable {
            @SerializedName("defaultAvatar")
            public boolean defaultAvatar;
            @SerializedName("province")
            public int province;
            @SerializedName("authStatus")
            public int authStatus;
            @SerializedName("followed")
            public boolean followed;
            @SerializedName("avatarUrl")
            public String avatarUrl;
            @SerializedName("accountStatus")
            public int accountStatus;
            @SerializedName("gender")
            public int gender;
            @SerializedName("city")
            public int city;
            @SerializedName("birthday")
            public long birthday;
            @SerializedName("userId")
            public int userId;
            @SerializedName("userType")
            public int userType;
            @SerializedName("nickname")
            public String nickname;
            @SerializedName("signature")
            public String signature;
            @SerializedName("description")
            public String description;
            @SerializedName("detailDescription")
            public String detailDescription;
            @SerializedName("avatarImgId")
            public long avatarImgId;
            @SerializedName("backgroundImgId")
            public long backgroundImgId;
            @SerializedName("backgroundUrl")
            public String backgroundUrl;
            @SerializedName("authority")
            public int authority;
            @SerializedName("mutual")
            public boolean mutual;
            @SerializedName("expertTags")
            public Object expertTags;
            @SerializedName("experts")
            public Object experts;
            @SerializedName("djStatus")
            public int djStatus;
            @SerializedName("vipType")
            public int vipType;
            @SerializedName("remarkName")
            public Object remarkName;
            @SerializedName("authenticationTypes")
            public int authenticationTypes;
            @SerializedName("avatarDetail")
            public AvatarDetail avatarDetail;
            @SerializedName("anchor")
            public boolean anchor;
            @SerializedName("avatarImgIdStr")
            public String avatarImgIdStr;
            @SerializedName("backgroundImgIdStr")
            public String backgroundImgIdStr;

            public static class AvatarDetail implements Serializable {
                @SerializedName("userType")
                public int userType;
                @SerializedName("identityLevel")
                public int identityLevel;
                @SerializedName("identityIconUrl")
                public String identityIconUrl;
            }
        }

        public static class Subscribers implements Serializable {
            @SerializedName("defaultAvatar")
            public boolean defaultAvatar;
            @SerializedName("province")
            public int province;
            @SerializedName("authStatus")
            public int authStatus;
            @SerializedName("followed")
            public boolean followed;
            @SerializedName("avatarUrl")
            public String avatarUrl;
            @SerializedName("accountStatus")
            public int accountStatus;
            @SerializedName("gender")
            public int gender;
            @SerializedName("city")
            public int city;
            @SerializedName("birthday")
            public long birthday;
            @SerializedName("userId")
            public int userId;
            @SerializedName("userType")
            public int userType;
            @SerializedName("nickname")
            public String nickname;
            @SerializedName("signature")
            public String signature;
            @SerializedName("description")
            public String description;
            @SerializedName("detailDescription")
            public String detailDescription;
            @SerializedName("avatarImgId")
            public long avatarImgId;
            @SerializedName("backgroundImgId")
            public long backgroundImgId;
            @SerializedName("backgroundUrl")
            public String backgroundUrl;
            @SerializedName("authority")
            public int authority;
            @SerializedName("mutual")
            public boolean mutual;
            @SerializedName("expertTags")
            public Object expertTags;
            @SerializedName("experts")
            public Object experts;
            @SerializedName("djStatus")
            public int djStatus;
            @SerializedName("vipType")
            public int vipType;
            @SerializedName("remarkName")
            public Object remarkName;
            @SerializedName("authenticationTypes")
            public int authenticationTypes;
            @SerializedName("avatarDetail")
            public Object avatarDetail;
            @SerializedName("anchor")
            public boolean anchor;
            @SerializedName("avatarImgIdStr")
            public String avatarImgIdStr;
            @SerializedName("backgroundImgIdStr")
            public String backgroundImgIdStr;
        }
    }
}
