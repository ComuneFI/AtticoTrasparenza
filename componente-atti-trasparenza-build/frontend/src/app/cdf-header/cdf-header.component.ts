import { Component, OnInit } from '@angular/core';

import { environment, comuneBaseUrl } from '@env/environment';

@Component({
  selector: 'app-cdf-header',
  templateUrl: './cdf-header.component.html',
  styleUrls: ['./cdf-header.component.scss']
})
export class CdfHeaderComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  isMenuEnabled() {
    return environment.displayMenu;
  }

  getComuneBaseUrl() {
    return comuneBaseUrl;
  }

  getScrollTop() {
    return $('html').scrollTop();
  }

  resetSticky() {
    $('#header').removeClass('is-sticky');
  }

  onScroll(e: any) {
    if (this.getScrollTop() > 0) {
      if ( !$('#header').hasClass('is-sticky') ) {
        $('#header').addClass('is-sticky');
      }
    } else {
      this.resetSticky();
    }
  }
}
