package com.aidaole.base.datas.entities;

public class RespPhonePasswordLogin {
    public int loginType;
    public int code;
    public Account account;
    public String token;
    public Profile profile;
    public String cookie;

    @Override
    public String toString() {
        return "RespPhonePasswordLogin{" +
                "loginType=" + loginType +
                ", code=" + code +
                ", account=" + account +
                ", token='" + token + '\'' +
                ", profile=" + profile +
                ", cookie='" + cookie + '\'' +
                '}';
    }

    public static class Account {
        public int id;
        public String userName;
        public int type;
        public int status;
        public int whitelistAuthority;
        public long createTime;
        public String salt;
        public int tokenVersion;
        public int ban;
        public int baoyueVersion;
        public int donateVersion;
        public int vipType;
        public long viptypeVersion;
        public boolean anonimousUser;
        public boolean uninitialized;

        @Override
        public String toString() {
            return "Account{" +
                    "id=" + id +
                    ", userName='" + userName + '\'' +
                    ", type=" + type +
                    ", status=" + status +
                    ", whitelistAuthority=" + whitelistAuthority +
                    ", createTime=" + createTime +
                    ", salt='" + salt + '\'' +
                    ", tokenVersion=" + tokenVersion +
                    ", ban=" + ban +
                    ", baoyueVersion=" + baoyueVersion +
                    ", donateVersion=" + donateVersion +
                    ", vipType=" + vipType +
                    ", viptypeVersion=" + viptypeVersion +
                    ", anonimousUser=" + anonimousUser +
                    ", uninitialized=" + uninitialized +
                    '}';
        }
    }

    public static class Profile {
        public String backgroundUrl;
        public boolean followed;
        public String backgroundImgIdStr;
        public String avatarImgIdStr;
        public boolean defaultAvatar;
        public int userType;
        public int vipType;
        public int authStatus;
        public int djStatus;
        public String detailDescription;
        public Object expertTags;
        public int accountStatus;
        public String nickname;
        public long birthday;
        public int gender;
        public int province;
        public int city;
        public long avatarImgId;
        public long backgroundImgId;
        public String avatarUrl;
        public boolean mutual;
        public Object remarkName;
        public String description;
        public int userId;
        public String signature;
        public int authority;
        public int followeds;
        public int follows;
        public int eventCount;
        public Object avatarDetail;
        public int playlistCount;
        public int playlistBeSubscribedCount;

        @Override
        public String toString() {
            return "Profile{" +
                    "backgroundUrl='" + backgroundUrl + '\'' +
                    ", followed=" + followed +
                    ", backgroundImgIdStr='" + backgroundImgIdStr + '\'' +
                    ", avatarImgIdStr='" + avatarImgIdStr + '\'' +
                    ", defaultAvatar=" + defaultAvatar +
                    ", userType=" + userType +
                    ", vipType=" + vipType +
                    ", authStatus=" + authStatus +
                    ", djStatus=" + djStatus +
                    ", detailDescription='" + detailDescription + '\'' +
                    ", expertTags=" + expertTags +
                    ", accountStatus=" + accountStatus +
                    ", nickname='" + nickname + '\'' +
                    ", birthday=" + birthday +
                    ", gender=" + gender +
                    ", province=" + province +
                    ", city=" + city +
                    ", avatarImgId=" + avatarImgId +
                    ", backgroundImgId=" + backgroundImgId +
                    ", avatarUrl='" + avatarUrl + '\'' +
                    ", mutual=" + mutual +
                    ", remarkName=" + remarkName +
                    ", description='" + description + '\'' +
                    ", userId=" + userId +
                    ", signature='" + signature + '\'' +
                    ", authority=" + authority +
                    ", followeds=" + followeds +
                    ", follows=" + follows +
                    ", eventCount=" + eventCount +
                    ", avatarDetail=" + avatarDetail +
                    ", playlistCount=" + playlistCount +
                    ", playlistBeSubscribedCount=" + playlistBeSubscribedCount +
                    '}';
        }
    }
}


