import { Component, OnInit } from '@angular/core';

import { environment, comuneBaseUrl } from '@env/environment';
import { OnScrollDirective } from './../../trasparenza/directives/onscroll.directive';

@Component({
  selector: 'app-shell',
  templateUrl: './shell.component.html',
  styleUrls: ['./shell.component.scss']
})
export class ShellComponent implements OnInit {

  constructor() { }

  ngOnInit() { }

  isMenuEnabled() {
    return environment.displayMenu;
  }

}
