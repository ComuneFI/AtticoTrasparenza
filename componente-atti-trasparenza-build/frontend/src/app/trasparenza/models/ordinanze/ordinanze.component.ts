import { Component, OnInit } from '@angular/core';

import { environment, comuneBaseUrl } from '@env/environment';

@Component({
  selector: 'app-ordinanze',
  templateUrl: './ordinanze.component.html',
  styleUrls: ['./ordinanze.component.scss']
})
export class OrdinanzeComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  getComuneBaseUrl() {
    return comuneBaseUrl;
  }

}
