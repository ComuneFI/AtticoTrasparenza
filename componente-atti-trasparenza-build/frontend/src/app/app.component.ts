import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { TranslateService } from '@ngx-translate/core';
import { merge } from 'rxjs';
import { filter, map, mergeMap } from 'rxjs/operators';

import { environment, comuneBaseUrl } from '@env/environment';
import { Logger, I18nService } from '@app/core';

import { CommonUtils } from './trasparenza/utils/common-utils';

const log = new Logger('App');

@Component({
  selector: 'app-root',
  // templateUrl: './app.component.html',
  template: `
    <router-outlet></router-outlet>
  `,
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private titleService: Title,
              private translateService: TranslateService,
              private i18nService: I18nService,
              public utils: CommonUtils) {

   }

  ngOnInit() {
    // Setup logger
    if (environment.production) {
      Logger.enableProductionMode();
    }

    // let hi: string = "hi";
    // log.debug(`testo ${hi}`);


    // Setup translations
    this.i18nService.init(environment.defaultLanguage, environment.supportedLanguages);

    const onNavigationEnd = this.router.events.pipe(filter(event => event instanceof NavigationEnd));

    // Change page title on navigation or language change, based on route data
    merge(this.translateService.onLangChange, onNavigationEnd)
      .pipe(
        map(() => {
          let route = this.activatedRoute;
          while (route.firstChild) {
            route = route.firstChild;
          }
          return route;
        }),
        filter(route => route.outlet === 'primary'),
        mergeMap(route => route.data)
      )
      .subscribe(event => {
        const title = event['title'];
        if (title) {
          this.titleService.setTitle(this.translateService.instant(title));
        }
      });

      const urlParams = new URLSearchParams(window.location.search);
      const tipoAtto = urlParams.get(environment.queryParameters.getAttoByTipoNumeroAnno.tipo);
      const numeroAtto = urlParams.get(environment.queryParameters.getAttoByTipoNumeroAnno.numero);
      const annoAtto = urlParams.get(environment.queryParameters.getAttoByTipoNumeroAnno.anno);

      if (tipoAtto != null && numeroAtto != null && annoAtto != null) {
        // console.log('urlParams', urlParams);
        const path = this.utils.getPathByTipoAtto(tipoAtto);
        if (path != null) {
          const qs = {};
          qs[environment.queryParameters.getAttoByTipoNumeroAnno.tipo] = tipoAtto;
          qs[environment.queryParameters.getAttoByTipoNumeroAnno.numero] = numeroAtto;
          qs[environment.queryParameters.getAttoByTipoNumeroAnno.anno] = annoAtto;
          this.router.navigate([path], { queryParams: qs });
        }
      }
  }

}
