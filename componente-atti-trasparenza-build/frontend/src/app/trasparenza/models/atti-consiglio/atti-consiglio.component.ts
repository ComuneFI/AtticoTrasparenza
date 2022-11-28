import { Component, OnInit } from '@angular/core';

import { environment, comuneBaseUrl } from '@env/environment';

@Component({
  selector: 'app-atti-consiglio',
  templateUrl: './atti-consiglio.component.html',
  styleUrls: ['./atti-consiglio.component.scss']
})
export class AttiConsiglioComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  getComuneBaseUrl() {
    return comuneBaseUrl;
  }

}
