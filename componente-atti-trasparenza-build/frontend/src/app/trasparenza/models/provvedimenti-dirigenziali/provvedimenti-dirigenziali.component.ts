import { Component, OnInit } from '@angular/core';

import { environment, comuneBaseUrl } from '@env/environment';

@Component({
  selector: 'app-provvedimenti-dirigenziali',
  templateUrl: './provvedimenti-dirigenziali.component.html',
  styleUrls: ['./provvedimenti-dirigenziali.component.scss']
})
export class ProvvedimentiDirigenzialiComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  getComuneBaseUrl() {
    return comuneBaseUrl;
  }

}
