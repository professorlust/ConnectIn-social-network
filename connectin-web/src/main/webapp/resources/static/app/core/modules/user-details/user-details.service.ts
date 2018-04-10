import {Inject, Injectable} from 'angular2/core';
import {Headers, Http, Request, RequestOptions} from "angular2/http";
import {UrlConfigService} from  '../../../config/url-config.service';
import {TokenService} from "../../security/token/token.service";
@Injectable()
export class UserDetailsService {

    constructor(private http: Http, @Inject(UrlConfigService) private urlConfigService: UrlConfigService
        ,@Inject(TokenService)  private tokenService) {
        this.http = http;
        this.urlConfigService = urlConfigService;
        this.tokenService = tokenService;
    }

    getUserDetails() {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Authorization', this.tokenService.getUserToken());
        let options = new RequestOptions({headers: headers});
        return this.http.get(this.urlConfigService.baseUrl + this.urlConfigService.getUserDetailsUrl, options)
    }
}