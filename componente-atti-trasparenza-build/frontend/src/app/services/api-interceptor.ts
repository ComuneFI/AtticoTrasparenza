import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class APIInterceptor implements HttpInterceptor {
  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let apiReq;
      
    //let custom_base_url = req.url.substring(4); // remove "/api"
    let custom_base_url = req.url;
    apiReq = req.clone({ url: `${custom_base_url}` });
    //console.log("URL: ", req);
      
    return next.handle(apiReq);
  }
}
