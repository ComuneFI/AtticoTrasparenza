import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { TranslateService } from '@ngx-translate/core';
import { merge } from 'rxjs';
import { filter, map, mergeMap } from 'rxjs/operators';

import { environment, comuneBaseUrl } from '@env/environment';
import { Logger, I18nService } from '@app/core';

import { HttpClient, HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpHeaders } from '@angular/common/http';

const log = new Logger('App');

@Component({
  selector: 'test-component',
  templateUrl: './test.component.html',
  /*template: `
    <test-outlet></test-outlet>
  `,*/
  styleUrls: ['./test.component.scss']
})
export class TestComponent implements OnInit {

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private titleService: Title,
              private translateService: TranslateService,
              private i18nService: I18nService,
              private http: HttpClient) {
  
  
   }

  ngOnInit() {
    // Setup logger
    if (environment.production) {
      Logger.enableProductionMode();
    }

    let ok : string = "ok";
    //log.debug(`funziona ${ok}!`);
      
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json'
        //'Authorization': 'my-auth-token'
      })
    };
      
    let endpoint : string = "https://api.github.com/users/seeschweiler";
      
    /*this.http.get<any[]>(endpoint).subscribe(data => {
      console.log(data);
    });*/
      
    /*this.http.get(endpoint).subscribe(data => {
      console.log(data);
    });*/
      
    /*interface UserResponse {
      login: string;
      bio: string;
      company: string;
    }
      
    this.http.get<UserResponse>(endpoint).subscribe(data => {
      console.log("User Login: " + data.login);
      console.log("Bio: " + data.bio);
      console.log("Company: " + data.company);
    });*/
  }
    
}