import { Component, OnInit } from '@angular/core';
import { finalize } from 'rxjs/operators';

import { QuoteService } from './quote.service';

import {ElementRef, ViewChild} from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  quote: string;
  isLoading: boolean;

  // @ViewChild("myBtn") btn : ElementRef;

  constructor(private quoteService: QuoteService) { }

  ngOnInit() {
    /*this.isLoading = true;
    this.quoteService.getRandomQuote({ category: 'dev' })
      .pipe(finalize(() => { this.isLoading = false; }))
      .subscribe((quote: string) => { this.quote = quote; });*/
  }

  saluta() {
    // alert("Hi");
    // this.btn.nativeElement.style.backgroundColor = '#5789D8';

    /*this.quoteService.getRandomQuote({ category: 'explicit' })
      .pipe(finalize(() => { this.isLoading = false; }))
      .subscribe((quote: string) => {
        this.quote = quote;
      });*/
  }

}
