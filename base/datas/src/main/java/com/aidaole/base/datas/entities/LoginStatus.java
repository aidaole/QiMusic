package com.aidaole.base.datas.entities;

public class LoginStatus {

    public Data data;

    public static class Data {
        public int code;
        public Account account;
        public static class Account {
            public long id;
            public String userName;
            public int type;
            public int status;
            public int whitelistAuthority;
            public long createTime;
            public int tokenVersion;
            public int ban;
            public int baoyueVersion;
            public int donateVersion;
            public int vipType;
            public boolean anonimousUser;
            public boolean paidFee;

            @Override
            public String toString() {
                return "Account{" +
                        "id=" + id +
                        ", userName='" + userName + '\'' +
                        ", type=" + type +
                        ", status=" + status +
                        ", whitelistAuthority=" + whitelistAuthority +
                        ", createTime=" + createTime +
                        ", tokenVersion=" + tokenVersion +
                        ", ban=" + ban +
                        ", baoyueVersion=" + baoyueVersion +
                        ", donateVersion=" + donateVersion +
                        ", vipType=" + vipType +
                        ", anonimousUser=" + anonimousUser +
                        ", paidFee=" + paidFee +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Data{" +
                    "code=" + code +
                    ", account=" + account +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "LoginStatus{" +
                "data=" + data +
                '}';
    }
}


