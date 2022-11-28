import { Component, OnInit } from '@angular/core';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { TranslateService } from '@ngx-translate/core';
import { merge } from 'rxjs';
import { filter, map, mergeMap } from 'rxjs/operators';

import { environment, comuneBaseUrl } from '@env/environment';
import { Logger, I18nService } from '@app/core';

import { Articolo }  from './articolo';

@Component({
  selector: 'articolo',
  templateUrl: './articolo.component.html',
  styleUrls: ['./articolo.component.scss']
})
export class ArticoloComponent implements OnInit {
  articolo: Articolo;

  constructor() {
        this.articolo = {
            titolo: "Creare componenti Angular 2",
            autore: "Mario Rossi",
            testo:  "Creare componenti con Angular 2 \xE9 molto semplice."
        };
  }

  ngOnInit() {
  }

}
