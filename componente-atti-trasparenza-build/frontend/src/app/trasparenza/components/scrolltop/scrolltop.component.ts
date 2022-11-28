import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-scrolltop',
  templateUrl: './scrolltop.component.html',
  styleUrls: ['./scrolltop.component.scss']
})
export class ScrollTopComponent implements OnInit {
  currentUrl = '';
  constructor(private router: Router) { }

  ngOnInit() {
    // this.currentUrl = this.router.url;
    /*$(window).on('scroll', () => {
      if (this.getScrollTop() > 0) {
        $('#scrollto-top').addClass('active');
      } else {
        this.reset();
      }
    });*/
  }

  onScroll(e: any) {
    if (this.getScrollTop() > 0) {
      $('#scrollto-top').addClass('active');
    } else {
      this.reset();
    }
  }

  reset() {
    $('#scrollto-top').removeClass('active');
  }

  getScrollTop() {
    return $('html').scrollTop();
  }

  goToTop() {
    $('html').stop().animate({scrollTop : 0}, 300, 'swing', () => {
       // console.log('Finished animating');
       this.reset();
    });
  }

  getCurrentUrl() {
    let currentUrl = window.location.href;
    const anchorIndex = currentUrl.lastIndexOf('#');
    if (anchorIndex >= 0) {
      if (currentUrl.length >= anchorIndex + 1) {
        if ( currentUrl.charAt(anchorIndex + 1) === '/' ) {
          return currentUrl;
        }
      }
      currentUrl = currentUrl.substring(0, anchorIndex);
    }

    return currentUrl;
  }

}
