import { Component, OnInit } from '@angular/core';

import { environment, comuneBaseUrl } from '@env/environment';

@Component({
  selector: 'app-cdf-footer',
  templateUrl: './cdf-footer.component.html',
  styleUrls: ['./cdf-footer.component.scss']
})
export class CdfFooterComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  getComuneBaseUrl() {
    return comuneBaseUrl;
  }
}
