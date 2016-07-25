package me.jinqiang.android.material.live.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by yanjinqiang on 2016/7/7.
 */
public class DouyuEntity implements Parcelable{


    /**
     * error : 0
     * data : [{"room_id":"424559","room_src":"http://rpic.douyucdn.cn/z1607/07/17/424559_160707173557.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/424559_160707173557.jpg","isVertical":0,"cate_id":"1","room_name":"LPL夏季赛SAT vs WE","show_status":"1","subject":"","show_time":"1467878852","owner_uid":"25436380","specific_catalog":"","specific_status":"0","vod_quality":"0","nickname":"riot赛事二","online":957692,"url":"/424559","game_url":"/directory/game/LOL","game_name":"英雄联盟","child_id":"37","avatar":"http://uc.douyutv.com/upload/avatar/025/43/63/80_avatar_big.jpg","fans":"253587","ranktype":0,"anchor_city":"鱼塘"},{"room_id":"288016","room_src":"http://rpic.douyucdn.cn/z1607/07/17/288016_160707173557.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/288016_160707173557.jpg","isVertical":0,"cate_id":"1","room_name":"LPL夏季赛 SAT vs WE","show_status":"1","subject":"","show_time":"1467878841","owner_uid":"19344409","specific_catalog":"lpl","specific_status":"1","vod_quality":"0","nickname":"Riot拳头","online":564272,"url":"/lpl","game_url":"/directory/game/LOL","game_name":"英雄联盟","child_id":"37","avatar":"http://uc.douyutv.com/upload/avatar/019/34/44/09_avatar_big.jpg","fans":"638991","ranktype":0,"anchor_city":"鱼塘"},{"room_id":"580213","room_src":"http://staticlive.douyutv.com/upload/web_pic/4a7cfc590a9c1428a2fb7f32f527bb61_thumb.png","vertical_src":"http://staticlive.douyutv.com/upload/web_pic/4a7cfc590a9c1428a2fb7f32f527bb61_thumb.png","isVertical":0,"cate_id":"207","room_name":"熙行法兰西","show_status":"1","subject":"","show_time":"1466043474","owner_uid":"42902772","specific_catalog":"","specific_status":"0","vod_quality":"0","nickname":"Feiran谌","online":3916,"url":"/580213","game_url":"/directory/game/qezb","game_name":"企鹅直播","child_id":"0","avatar":"http://uc.douyutv.com/upload/avatar/face/201603/991a5cd588b0f54e21a7fb7edcfabf10_big.jpg","fans":"1060","ranktype":1,"anchor_city":"鱼塘"},{"room_id":"16101","room_src":"http://rpic.douyucdn.cn/z1607/07/17/16101_160707173244.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/16101_160707173244.jpg","isVertical":0,"cate_id":"1","room_name":"微笑： 忙完以后就是好好当人~","show_status":"1","subject":"","show_time":"1467873349","owner_uid":"391270","specific_catalog":"weixiao","specific_status":"1","vod_quality":"0","nickname":"微笑","online":246328,"url":"/weixiao","game_url":"/directory/game/LOL","game_name":"英雄联盟","child_id":"35","avatar":"http://uc.douyutv.com/upload/avatar/000/39/12/70_avatar_big.jpg","fans":"1252476","ranktype":0,"anchor_city":"鱼塘"},{"room_id":"442078","room_src":"http://staticlive.douyutv.com/upload/web_pic/1cc060fd5014f29b56e01aedfa66077c_thumb.jpg","vertical_src":"http://staticlive.douyutv.com/upload/web_pic/1cc060fd5014f29b56e01aedfa66077c_thumb.jpg","isVertical":0,"cate_id":"207","room_name":"奥运强国专题【东道主巴西】","show_status":"1","subject":"","show_time":"1461035539","owner_uid":"26667576","specific_catalog":"","specific_status":"0","vod_quality":"0","nickname":"科比pp","online":1,"url":"/442078","game_url":"/directory/game/qezb","game_name":"企鹅直播","child_id":"0","avatar":"http://uc.douyutv.com/upload/avatar/026/66/75/76_avatar_big.jpg","fans":"8296","ranktype":1,"anchor_city":"鱼塘"},{"room_id":"3484","room_src":"http://rpic.douyucdn.cn/z1607/07/17/3484_160707173540.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/3484_160707173540.jpg","isVertical":0,"cate_id":"4","room_name":"SSL2016S2选人直播","show_status":"1","subject":"","show_time":"1467881931","owner_uid":"117761","specific_catalog":"scboy","specific_status":"1","vod_quality":"0","nickname":"scboy","online":51636,"url":"/scboy","game_url":"/directory/game/SC","game_name":"星际争霸","child_id":"123","avatar":"http://uc.douyutv.com/upload/avatar/000/11/77/61_avatar_big.jpg","fans":"246396","ranktype":1,"anchor_city":"鱼塘"},{"room_id":"274874","room_src":"http://rpic.douyucdn.cn/z1607/07/17/274874_160707173532.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/274874_160707173532.jpg","isVertical":0,"cate_id":"124","room_name":"跟着\u201c艾特\u201d去草原！艺考的孩子回来啦","show_status":"1","subject":"","show_time":"1467882863","owner_uid":"17562136","specific_catalog":"piliman","specific_status":"0","vod_quality":"0","nickname":"霹雳爷们儿","online":188042,"url":"/274874","game_url":"/directory/game/outdoor","game_name":"Outdoor","child_id":"0","avatar":"http://uc.douyutv.com/upload/avatar/017/56/21/36_avatar_big.jpg","fans":"777414","ranktype":0,"anchor_city":"鱼塘"},{"room_id":"414818","room_src":"http://rpic.douyucdn.cn/z1607/07/17/414818_160707172637.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/414818_160707172637.jpg","isVertical":0,"cate_id":"40","room_name":"胜哥:强化增幅天空罐子盒子搞起来！","show_status":"1","subject":"","show_time":"1467859745","owner_uid":"11235324","specific_catalog":"","specific_status":"0","vod_quality":"0","nickname":"胜哥002","online":179491,"url":"/414818","game_url":"/directory/game/DNF","game_name":"地下城与勇士","child_id":"60","avatar":"http://uc.douyutv.com/upload/avatar/011/23/53/24_avatar_big.jpg","fans":"304326","ranktype":0,"anchor_city":"鱼塘"},{"room_id":"48699","room_src":"http://rpic.douyucdn.cn/z1607/07/17/48699_160707173630.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/48699_160707173630.jpg","isVertical":0,"cate_id":"2","room_name":"衣锦夜行：登顶登顶","show_status":"1","subject":"","show_time":"1467860055","owner_uid":"350786","specific_catalog":"yechui","specific_status":"1","vod_quality":"0","nickname":"衣锦夜行","online":159273,"url":"/yechui","game_url":"/directory/game/How","game_name":"炉石传说","child_id":"167","avatar":"http://uc.douyutv.com/upload/avatar/000/35/07/86_avatar_big.jpg","fans":"62849","ranktype":0,"anchor_city":"鱼塘"},{"room_id":"601878","room_src":"http://staticlive.douyutv.com/upload/web_pic/99b9de562cc8e2703d38ec54efaddcbf_thumb.jpg","vertical_src":"http://staticlive.douyutv.com/upload/web_pic/99b9de562cc8e2703d38ec54efaddcbf_thumb.jpg","isVertical":0,"cate_id":"207","room_name":"台湾姥姥杯","show_status":"1","subject":"","show_time":"1464396156","owner_uid":"11607885","specific_catalog":"","specific_status":"0","vod_quality":"0","nickname":"tenneyson","online":144,"url":"/601878","game_url":"/directory/game/qezb","game_name":"企鹅直播","child_id":"0","avatar":"http://uc.douyutv.com/upload/avatar/011/60/78/85_avatar_big.jpg","fans":"3354","ranktype":1,"anchor_city":"鱼塘"},{"room_id":"583170","room_src":"http://staticlive.douyutv.com/upload/web_pic/121e0fcf1c7b65552ae783fc8872a1f3_thumb.jpg","vertical_src":"http://staticlive.douyutv.com/upload/web_pic/121e0fcf1c7b65552ae783fc8872a1f3_thumb.jpg","isVertical":0,"cate_id":"207","room_name":"洛阳-市长杯-小学生五人制足球赛","show_status":"1","subject":"","show_time":"1467163321","owner_uid":"38727933","specific_catalog":"","specific_status":"0","vod_quality":"0","nickname":"拼命的折腾","online":1846,"url":"/583170","game_url":"/directory/game/qezb","game_name":"企鹅直播","child_id":"0","avatar":"http://uc.douyutv.com/upload/avatar/face/201603/404f50aafea243f5f31f27a7c454b399_big.jpg","fans":"2732","ranktype":1,"anchor_city":"鱼塘"},{"room_id":"442076","room_src":"http://staticlive.douyutv.com/upload/web_pic/c2c935c11cd485ca9bc57667946e2ca7_thumb.jpg","vertical_src":"http://staticlive.douyutv.com/upload/web_pic/c2c935c11cd485ca9bc57667946e2ca7_thumb.jpg","isVertical":0,"cate_id":"207","room_name":"【直播】台湾府城杯","show_status":"1","subject":"","show_time":"1463187637","owner_uid":"2214037","specific_catalog":"","specific_status":"0","vod_quality":"0","nickname":"SanKarim","online":63,"url":"/442076","game_url":"/directory/game/qezb","game_name":"企鹅直播","child_id":"0","avatar":"http://uc.douyutv.com/upload/avatar/002/21/40/37_avatar_big.jpg","fans":"3195","ranktype":1,"anchor_city":"鱼塘"},{"room_id":"60062","room_src":"http://rpic.douyucdn.cn/z1607/07/17/60062_160707173616.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/60062_160707173616.jpg","isVertical":0,"cate_id":"1","room_name":"又是一轮晋级赛！","show_status":"1","subject":"","show_time":"1467871661","owner_uid":"1722115","specific_catalog":"fzz1","specific_status":"1","vod_quality":"0","nickname":"风中追风Fzz1","online":140433,"url":"/fzz1","game_url":"/directory/game/LOL","game_name":"英雄联盟","child_id":"36","avatar":"http://uc.douyutv.com/upload/avatar/001/72/21/15_avatar_big.jpg","fans":"881217","ranktype":0,"anchor_city":"鱼塘"},{"room_id":"534740","room_src":"http://rpic.douyucdn.cn/z1607/07/17/534740_160707173626.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/534740_160707173626.jpg","isVertical":0,"cate_id":"19","room_name":"【六神】欢迎收看：黎明杀鸡！","show_status":"1","subject":"","show_time":"1467865529","owner_uid":"28206057","specific_catalog":"","specific_status":"0","vod_quality":"0","nickname":"ll小六ll","online":129826,"url":"/534740","game_url":"/directory/game/TVgame","game_name":"主机游戏","child_id":"96","avatar":"http://uc.douyutv.com/upload/avatar/028/20/60/57_avatar_big.jpg","fans":"177888","ranktype":0,"anchor_city":"鱼塘"},{"room_id":"84452","room_src":"http://rpic.douyucdn.cn/z1607/07/17/84452_160707173252.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/84452_160707173252.jpg","isVertical":0,"cate_id":"19","room_name":"【阿飞】和房管的战后余生","show_status":"1","subject":"","show_time":"1467867528","owner_uid":"113660","specific_catalog":"aihehui","specific_status":"0","vod_quality":"0","nickname":"主播阿飞","online":123054,"url":"/84452","game_url":"/directory/game/TVgame","game_name":"主机游戏","child_id":"171","avatar":"http://uc.douyutv.com/upload/avatar/000/11/36/60_avatar_big.jpg","fans":"345359","ranktype":0,"anchor_city":"鱼塘"},{"room_id":"586281","room_src":"http://staticlive.douyutv.com/upload/web_pic/50eec31c8a74395a84de8ca2f2ff3c0f_thumb.png","vertical_src":"http://staticlive.douyutv.com/upload/web_pic/50eec31c8a74395a84de8ca2f2ff3c0f_thumb.png","isVertical":0,"cate_id":"207","room_name":"篮球宝贝带你起飞","show_status":"1","subject":"","show_time":"1464223464","owner_uid":"14357092","specific_catalog":"","specific_status":"0","vod_quality":"0","nickname":"gq5628565","online":737,"url":"/586281","game_url":"/directory/game/qezb","game_name":"企鹅直播","child_id":"0","avatar":"http://uc.douyutv.com/upload/avatar/face/201603/c11284d19adb97c345e99c5e478fa1a0_big.jpg","fans":"1105","ranktype":1,"anchor_city":"鱼塘"},{"room_id":"600878","room_src":"http://rpic.douyucdn.cn/z1607/07/17/600878_160707173626.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/600878_160707173626.jpg","isVertical":0,"cate_id":"2","room_name":"国服第一快攻德","show_status":"1","subject":"","show_time":"1467865102","owner_uid":"354200","specific_catalog":"","specific_status":"0","vod_quality":"0","nickname":"炉石丶贪睡之萨满","online":115534,"url":"/600878","game_url":"/directory/game/How","game_name":"炉石传说","child_id":"158","avatar":"http://uc.douyutv.com/upload/avatar/000/35/42/00_avatar_big.jpg","fans":"69978","ranktype":1,"anchor_city":"鱼塘"},{"room_id":"465140","room_src":"http://rpic.douyucdn.cn/z1607/07/17/465140_160707173644.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/465140_160707173644.jpg","isVertical":0,"cate_id":"3","room_name":"DPL 主舞台 直播","show_status":"1","subject":"","show_time":"1465954371","owner_uid":"23509922","specific_catalog":"","specific_status":"0","vod_quality":"0","nickname":"marstv2015","online":108383,"url":"/465140","game_url":"/directory/game/DOTA2","game_name":"DOTA2","child_id":"0","avatar":"http://uc.douyutv.com/upload/avatar/face/201605/18/05507ee9b72157bb2c21297d4d49e627_big.jpg","fans":"12221","ranktype":0,"anchor_city":"鱼塘"},{"room_id":"244317","room_src":"http://rpic.douyucdn.cn/z1607/07/17/244317_160707173621.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/244317_160707173621.jpg","isVertical":0,"cate_id":"1","room_name":"炼金1200法强,出肉算我输！","show_status":"1","subject":"","show_time":"1467868819","owner_uid":"14774184","specific_catalog":"cangshu","specific_status":"1","vod_quality":"0","nickname":"仓鼠炼金","online":102868,"url":"/cangshu","game_url":"/directory/game/LOL","game_name":"英雄联盟","child_id":"32","avatar":"http://uc.douyutv.com/upload/avatar/014/77/41/84_avatar_big.jpg","fans":"245855","ranktype":0,"anchor_city":"鱼塘"},{"room_id":"59073","room_src":"http://rpic.douyucdn.cn/z1607/07/17/59073_160707173259.jpg","vertical_src":"http://rpic.douyucdn.cn/z1607/07/17/59073_160707173259.jpg","isVertical":0,"cate_id":"77","room_name":"早起来一发，早上好呀","show_status":"1","subject":"","show_time":"1467858294","owner_uid":"1318518","specific_catalog":"","specific_status":"0","vod_quality":"0","nickname":"希象传媒丶范简简","online":1957,"url":"/59073","game_url":"/directory/game/COS","game_name":"英魂之刃","child_id":"71","avatar":"http://uc.douyutv.com/upload/avatar/face/201605/10/30423c5924c89c318290c7f35495f27d_big.jpg","fans":"75352","ranktype":1,"anchor_city":"鱼塘"}]
     */

    private int error;
    /**
     * room_id : 424559
     * room_src : http://rpic.douyucdn.cn/z1607/07/17/424559_160707173557.jpg
     * vertical_src : http://rpic.douyucdn.cn/z1607/07/17/424559_160707173557.jpg
     * isVertical : 0
     * cate_id : 1
     * room_name : LPL夏季赛SAT vs WE
     * show_status : 1
     * subject :
     * show_time : 1467878852
     * owner_uid : 25436380
     * specific_catalog :
     * specific_status : 0
     * vod_quality : 0
     * nickname : riot赛事二
     * online : 957692
     * url : /424559
     * game_url : /directory/game/LOL
     * game_name : 英雄联盟
     * child_id : 37
     * avatar : http://uc.douyutv.com/upload/avatar/025/43/63/80_avatar_big.jpg
     * fans : 253587
     * ranktype : 0
     * anchor_city : 鱼塘
     */

    private List<DataBean> data;

    protected DouyuEntity(Parcel in) {
        error = in.readInt();
    }

    public static final Creator<DouyuEntity> CREATOR = new Creator<DouyuEntity>() {
        @Override
        public DouyuEntity createFromParcel(Parcel in) {
            return new DouyuEntity(in);
        }

        @Override
        public DouyuEntity[] newArray(int size) {
            return new DouyuEntity[size];
        }
    };

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(error);
    }

    public static class DataBean {
        private String room_id;
        private String room_src;
        private String vertical_src;
        private int isVertical;
        private String cate_id;
        private String room_name;
        private String show_status;
        private String subject;
        private String show_time;
        private String owner_uid;
        private String specific_catalog;
        private String specific_status;
        private String vod_quality;
        private String nickname;
        private int online;
        private String url;
        private String game_url;
        private String game_name;
        private String child_id;
        private String avatar;
        private String fans;
        private int ranktype;
        private String anchor_city;

        public String getRoom_id() {
            return room_id;
        }

        public void setRoom_id(String room_id) {
            this.room_id = room_id;
        }

        public String getRoom_src() {
            return room_src;
        }

        public void setRoom_src(String room_src) {
            this.room_src = room_src;
        }

        public String getVertical_src() {
            return vertical_src;
        }

        public void setVertical_src(String vertical_src) {
            this.vertical_src = vertical_src;
        }

        public int getIsVertical() {
            return isVertical;
        }

        public void setIsVertical(int isVertical) {
            this.isVertical = isVertical;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getRoom_name() {
            return room_name;
        }

        public void setRoom_name(String room_name) {
            this.room_name = room_name;
        }

        public String getShow_status() {
            return show_status;
        }

        public void setShow_status(String show_status) {
            this.show_status = show_status;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getShow_time() {
            return show_time;
        }

        public void setShow_time(String show_time) {
            this.show_time = show_time;
        }

        public String getOwner_uid() {
            return owner_uid;
        }

        public void setOwner_uid(String owner_uid) {
            this.owner_uid = owner_uid;
        }

        public String getSpecific_catalog() {
            return specific_catalog;
        }

        public void setSpecific_catalog(String specific_catalog) {
            this.specific_catalog = specific_catalog;
        }

        public String getSpecific_status() {
            return specific_status;
        }

        public void setSpecific_status(String specific_status) {
            this.specific_status = specific_status;
        }

        public String getVod_quality() {
            return vod_quality;
        }

        public void setVod_quality(String vod_quality) {
            this.vod_quality = vod_quality;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getGame_url() {
            return game_url;
        }

        public void setGame_url(String game_url) {
            this.game_url = game_url;
        }

        public String getGame_name() {
            return game_name;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }

        public String getChild_id() {
            return child_id;
        }

        public void setChild_id(String child_id) {
            this.child_id = child_id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getFans() {
            return fans;
        }

        public void setFans(String fans) {
            this.fans = fans;
        }

        public int getRanktype() {
            return ranktype;
        }

        public void setRanktype(int ranktype) {
            this.ranktype = ranktype;
        }

        public String getAnchor_city() {
            return anchor_city;
        }

        public void setAnchor_city(String anchor_city) {
            this.anchor_city = anchor_city;
        }
    }

    @Override
    public String toString() {
        return "DouyuEntity{" +
                "data=" + data.get(0).toString() +
                '}';
    }
}
