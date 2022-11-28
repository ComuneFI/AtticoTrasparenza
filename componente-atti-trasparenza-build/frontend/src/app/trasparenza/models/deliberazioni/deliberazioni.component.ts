import { Component, OnInit } from '@angular/core';

import { environment, comuneBaseUrl } from '@env/environment';

import { CommonUtils } from './../../utils/common-utils';

@Component({
  selector: 'app-deliberazioni',
  templateUrl: './deliberazioni.component.html',
  styleUrls: ['./deliberazioni.component.scss']
})
export class DeliberazioniComponent implements OnInit {
  breadcrumbsType = '';
  breadcrumbsTypes = environment.breadcrumbs.types.section.deliberazioni;

  constructor(public utils: CommonUtils) { }

  ngOnInit() {
    this.breadcrumbsType = this.utils.getValueFromQueryString(this.utils.getQueryString(), environment.breadcrumbs.types.attribute);
    // console.log('ACCESS: ', this.breadcrumbsType);
  }

  getComuneBaseUrl() {
    return comuneBaseUrl;
  }

}
