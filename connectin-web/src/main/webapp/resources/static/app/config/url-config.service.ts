import {Injectable} from "angular2/core";

@Injectable()
export class UrlConfigService {


    public httpUrl = 'http://';
    public baseUrl;
    public port = 9091;
    public appPort = 9092;
    public appBaseUrl;
    public mainAPIBaseUrl = "//connectin-microservice.herokuapp.com";
    public mainHost = "//connectin-multimodule.herokuapp.com";
    public host = "localhost";
    public apiBaseUrl = "/connectin/api/";
    public userFeedUrl = "user/feed";
    public postUrl = 'posts/add';
    public tokenUrl = "token";
    public commentAddUrl= "comment/add";
    public commentGetUrl= "comment/";
    public likePostUrl = "user/likes/add/post/";
    public likeCommentUrl = "user/likes/add/comment/";
    public getUserDetailsUrl = "user/details?";
    public getUserPostsUrl = "/posts/list";
    public getUserConnections = "/user/connections/details";
    constructor() {
        this.appBaseUrl = this.httpUrl + this.host + ':' + this.appPort + this.apiBaseUrl;
        this.baseUrl = this.httpUrl + this.host + ':' + this.port + this.apiBaseUrl;
        // this.baseUrl = this.mainAPIBaseUrl+this.apiBaseUrl;
        // this.appBaseUrl =  this.mainHost+ this.apiBaseUrl

    }


    getUserFeedUrl() {
        return this.baseUrl + this.userFeedUrl;
    }
    getAddPostUrl() {
        return this.baseUrl + this.postUrl;
    }

    getTokenUrl() {
        return this.appBaseUrl + this.tokenUrl;
    }
}