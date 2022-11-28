import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { TranslateService, LangChangeEvent } from '@ngx-translate/core';

import { CommonUtils } from './../../utils/common-utils';

@Component({
  selector: 'app-dettaglio-ord',
  templateUrl: './dettaglio-ord.component.html',
  styleUrls: ['./dettaglio-ord.component.scss']
})
export class DettaglioOrdComponent implements OnInit, OnDestroy {
  contentObj: any;

  googleOffVar = '';
  googleOnVar = '';

  constructor(public translateService: TranslateService,
              public utils: CommonUtils) {}

  @Input() set content(value: any) {
    console.log(value);
    if (value && value.dataFinePubblicazione && value.dataFinePubblicazione > 0) {
      this.googleOffVar = '<!--googleoff: all-->';
      this.googleOnVar = '<!--googleon: all-->';
    } else {
      this.googleOffVar = '';
      this.googleOnVar = '';
    }
    this.contentObj = value;
  }

  get content() {
    return this.contentObj;
  }

  ngOnInit() {
  }

  ngOnDestroy() {
  }
}
