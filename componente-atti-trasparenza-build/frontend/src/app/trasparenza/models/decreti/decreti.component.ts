import { Component, OnInit } from '@angular/core';

import { environment, comuneBaseUrl } from '@env/environment';

@Component({
  selector: 'app-decreti',
  templateUrl: './decreti.component.html',
  styleUrls: ['./decreti.component.scss']
})
export class DecretiComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  getComuneBaseUrl() {
    return comuneBaseUrl;
  }

}
